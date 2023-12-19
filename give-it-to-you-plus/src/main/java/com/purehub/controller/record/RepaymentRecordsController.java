package com.purehub.controller.record;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.purehub.pojo.*;
import com.purehub.service.application.LoanApplicationService;
import com.purehub.service.credit.CreditScoreService;
import com.purehub.service.plan.RepaymentPlanService;
import com.purehub.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import com.purehub.service.record.RepaymentRecordService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class RepaymentRecordsController {
    @Autowired
    private CreditScoreService creditScoreService;
    @Autowired
    private LoanApplicationService loanApplicationService;
    @Autowired
    private RepaymentRecordService repaymentRecordsService;
    @Autowired
    private UserService userService;
    @Autowired
    private RepaymentPlanService repaymentPlanService;
    private static RepaymentResult repaymentResult = new RepaymentResult();

    @GetMapping("/findAllRepaymentRecords")
    public RepaymentResult findAllRecords(@RequestParam Integer userId)
    {
        List<RepaymentRecord> repaymentRecordList = repaymentRecordsService.list();
        return repaymentResult.success(repaymentRecordList, "获取所有还款记录成功");
    }
    @PostMapping("/addRecord")
    public RepaymentResult findAllRecords(@RequestBody RepaymentRecord record)
    {
        //判断余额是否足够还款
        if(!isEnough(record.getPaymentAmount(), record.getUserId()))
            return repaymentResult.error("余额不足，请充值再来~");
        //改变当前期数
        QueryWrapper<RepaymentRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("plan_id", record.getPlanId());
        if(!changeCurrentTerm(record.getPlanId()))
            return repaymentResult.success(null, "已全部还清!");
        if(repaymentRecordsService.getOne(queryWrapper) == null)
            return insertFirstRecord(record);
        return updatePaymentDateAndActualAmount(record);
    }

    public RepaymentResult updatePaymentDateAndActualAmount(RepaymentRecord record){

        QueryWrapper<RepaymentRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("plan_id", record.getPlanId());

        RepaymentRecord repaymentRecord = repaymentRecordsService.getOne(queryWrapper);
        record.setPaymentAmount(repaymentRecord.getPaymentAmount() + record.getPaymentAmount());

        return repaymentRecordsService.update(record, queryWrapper) && changeUserWallet(record) ? repaymentResult.success(null,"还款成功"):
        repaymentResult.error("还款失败");

    }

    public RepaymentResult insertFirstRecord(RepaymentRecord record){
        return repaymentRecordsService.save(record) && changeUserWallet(record) ? repaymentResult.success(null,"还款成功")
                : repaymentResult.error("还款失败");
    }
    public Boolean isEnough(Double need, Integer userId)
    {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return userService.getOne(queryWrapper).getWallet() >= need;
    }
    public Boolean changeCurrentTerm(Integer planId)
    {
        QueryWrapper<RepaymentPlan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("plan_id", planId);
        RepaymentPlan repaymentPlan = repaymentPlanService.getOne(queryWrapper);
        if( repaymentPlan.getTerm() > repaymentPlan.getCurrentTerm()) {

            repaymentPlan.setCurrentTerm(repaymentPlan.getCurrentTerm() + 1);

        }else if(repaymentPlan.getTerm() == repaymentPlan.getCurrentTerm())
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
    public Boolean changeUserWallet(RepaymentRecord record){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", record.getUserId());
        User user = userService.getOne(userQueryWrapper);
        user.setWallet(user.getWallet() - record.getPaymentAmount());

        return userService.update(user, userQueryWrapper);
    }
}
