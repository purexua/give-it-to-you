package com.purehub.controller.record;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.purehub.pojo.RepaymentPlan;
import com.purehub.pojo.RepaymentRecord;
import com.purehub.pojo.RepaymentResult;
import com.purehub.pojo.User;
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
        if(!isEnough(record.getPaymentAmount(), record.getUserId()))
            return repaymentResult.error("余额不足，请充值再来~");
        QueryWrapper<RepaymentRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("plan_id", record.getPlanId());
        if(repaymentRecordsService.getOne(queryWrapper) == null)
            return insertFirstRecord(record);
        return updatePaymentDateAndActualAmount(record);
    }

    public RepaymentResult updatePaymentDateAndActualAmount(RepaymentRecord record){
        QueryWrapper<RepaymentRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("plan_id", record.getPlanId());
        RepaymentRecord repaymentRecord = repaymentRecordsService.getOne(queryWrapper);
        record.setPaymentAmount(repaymentRecord.getPaymentAmount() + record.getPaymentAmount());
        return repaymentRecordsService.updateById(record) ? repaymentResult.success(null,"更新成功")
                : repaymentResult.error("更新失败");
    }

    public RepaymentResult insertFirstRecord(RepaymentRecord record){
        return repaymentRecordsService.save(record) ? repaymentResult.success(null,"插入成功")
                : repaymentResult.error("插入失败");
    }
    public Boolean isEnough(Double need, Integer userId)
    {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        if( userService.getOne(queryWrapper).getWallet() < need )
            return false;
        return true;
    }
}
