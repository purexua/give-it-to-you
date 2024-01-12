package com.purehub.service.record.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purehub.common.result.R;
import com.purehub.mapper.RepaymentRecordMapper;
import com.purehub.pojo.*;
import com.purehub.service.application.LoanApplicationService;
import com.purehub.service.credit.CreditScoreService;
import com.purehub.service.plan.RepaymentPlanService;
import com.purehub.service.record.RepaymentRecordService;
import com.purehub.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepaymentRecordServiceImpl extends ServiceImpl<RepaymentRecordMapper, RepaymentRecord> implements RepaymentRecordService {
    @Autowired
    private CreditScoreService creditScoreService;
    @Autowired
    private LoanApplicationService loanApplicationService;
    @Autowired
    private UserService userService;
    @Autowired
    private RepaymentPlanService repaymentPlanService;
    //更新还款时间和还款总数
    public Boolean updatePaymentDateAndActualAmount(RepaymentRecord record){

        QueryWrapper<RepaymentRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("plan_id", record.getPlanId());

        RepaymentRecord repaymentRecord = getOne(queryWrapper);
        record.setPaymentAmount(repaymentRecord.getPaymentAmount() + record.getPaymentAmount());

        return update(record, queryWrapper) && changeUserWallet(record);

    }

    //插入第一条还款记录
    public Boolean insertFirstRecord(RepaymentRecord record){
        return save(record) && changeUserWallet(record);
    }
    //判断用户余额是否足够
    public Boolean isEnough(Double need, Integer userId)
    {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return userService.getOne(queryWrapper).getWallet() >= need;
    }
    //改变当前期数
    public Boolean changeCurrentTerm(Integer planId)
    {
        QueryWrapper<RepaymentPlan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("plan_id", planId);
        RepaymentPlan repaymentPlan = repaymentPlanService.getOne(queryWrapper);
        if( repaymentPlan.getTerm() > repaymentPlan.getCurrentTerm()) {

            repaymentPlan.setCurrentTerm(repaymentPlan.getCurrentTerm() + 1);

        }else if(repaymentPlan.getTerm().equals(repaymentPlan.getCurrentTerm()))
        {
            //改额度
            //获取需要改的额度
            Integer applicationId = repaymentPlanService.getOne(queryWrapper).getApplicationId();

            QueryWrapper<LoanApplication> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("application_id", applicationId);
            Double amount = loanApplicationService.getOne(queryWrapper1).getRequestedAmount();

            QueryWrapper<CreditScore> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("user_id", repaymentPlan.getUserId());

            CreditScore creditScoreServiceOne = creditScoreService.getOne(queryWrapper2);
            creditScoreServiceOne.setLimitAmount(creditScoreServiceOne.getLimitAmount() + amount);

            return creditScoreService.update(creditScoreServiceOne,queryWrapper2) && repaymentPlanService.remove(queryWrapper);
        }else
        {
            return false;
        }
        return repaymentPlanService.update(repaymentPlan, queryWrapper);
    }
    //扣除还款金额
    public Boolean changeUserWallet(RepaymentRecord record){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", record.getUserId());
        User user = userService.getOne(userQueryWrapper);
        user.setWallet(user.getWallet() - record.getPaymentAmount());

        return userService.update(user, userQueryWrapper);
    }
    //单个还款
    public Boolean addOneRecords(RepaymentRecord record)
    {
        //改变当前期数
        QueryWrapper<RepaymentRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("plan_id", record.getPlanId());
        if(!changeCurrentTerm(record.getPlanId()))
            return false;
        if(getOne(queryWrapper) == null)
            return insertFirstRecord(record);
        return updatePaymentDateAndActualAmount(record);
    }
    //批量还款
    public Boolean addRecordsBatch(List<RepaymentRecord> records)
    {
        Double sum = 0.0;
        for (RepaymentRecord record : records) {
            sum += record.getPaymentAmount();
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if(records.isEmpty()) return false;
        userQueryWrapper.eq("user_id", records.get(0).getUserId());
        User user = userService.getOne(userQueryWrapper);
        if(sum > user.getWallet()) return false;
        for (RepaymentRecord record:
                records) {
            if(!addOneRecords(record)) return false;
        }
        return true;
    }
}
