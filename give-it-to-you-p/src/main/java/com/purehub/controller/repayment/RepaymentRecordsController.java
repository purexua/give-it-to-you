package com.purehub.controller.repayment;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.purehub.pojo.RepaymentPlan;
import com.purehub.pojo.RepaymentRecord;
import com.purehub.pojo.RepaymentResult;
import com.purehub.pojo.User;
import com.purehub.service.Repayment.RepaymentPlanService;
import com.purehub.service.Repayment.RepaymentRecordsService;
import com.purehub.service.user.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class RepaymentRecordsController {
    @Autowired
    private RepaymentRecordsService repaymentRecordsService;
    @Autowired
    private RepaymentPlanService repaymentPlanService;
    @Autowired
    private UserService userService;
    @GetMapping("/findAllRepaymentRecords")
    public RepaymentResult findAllRecords(@RequestParam Integer userId)
    {
        List<RepaymentRecord> repaymentRecordList = repaymentRecordsService.findAllRecords(userId);
        return new RepaymentResult().success(repaymentRecordList, "获取所有还款记录成功");
    }
    @GetMapping("/changeInstallment")
    public RepaymentResult updateInstallment(@RequestParam Long applicationId, @RequestParam Integer userId, @RequestParam Integer due){
        if(!determineBalance(due, userId))
            return  new RepaymentResult().success(null,"余额不足");
        QueryWrapper<RepaymentPlan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("application_id", applicationId);
        RepaymentPlan repaymentPlan = repaymentPlanService.getOne(queryWrapper);
        repaymentPlan.setInstallment(repaymentPlan.getInstallment()+1);
        try{
            if(repaymentPlanService.findNowInstall(applicationId) - 1  == repaymentPlanService.findTotalTerm(applicationId))
            {
                repaymentPlanService.remove(queryWrapper);
                return new RepaymentResult().success(null, "1");
            }else{
                repaymentPlanService.update(repaymentPlan, queryWrapper);
                return new RepaymentResult().success(null, "还款成功");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            return new RepaymentResult().error("还款失败");
        }
    }
    @GetMapping("/updateRecords")
    public RepaymentResult updatePaymentDateAndActualAmount(@RequestParam String paymentDate, @RequestParam Long applicationId){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").ISO_DATE_TIME;
            LocalDate date = LocalDate.parse(paymentDate, formatter);
            repaymentRecordsService.updateRecords(date,applicationId);
            return new RepaymentResult().success(null,"更新记录成功");
        }catch (Exception e)
        {
            e.printStackTrace();
            return new RepaymentResult().error("更新记录失败");
        }
    }
    @GetMapping("/insertRecords")
    public RepaymentResult insertFirstRecord(@RequestParam String date, @RequestParam Long applicationId, @RequestParam Integer amount){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").ISO_DATE_TIME;
            LocalDate date1 = LocalDate.parse(date, formatter);
            repaymentRecordsService.insertRecord(date1, applicationId, amount);
            return new RepaymentResult().success(null,"插入记录成功");
        }catch (Exception e)
        {
            e.printStackTrace();
            return new RepaymentResult().error("插入记录失败");
        }
    }
    public Boolean determineBalance(Integer due, Integer userId){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", userId);
        if(due <= userService.getOne(userQueryWrapper).getBalance())
            return true;
        return false;
    }

    @GetMapping("/getRealBalance")
    public RepaymentResult getRealBalance(@RequestParam Integer userId)
    {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Integer balance = userService.getOne(queryWrapper).getBalance();
        return new RepaymentResult().success(balance, "获取用户现在的余额成功");
    }

}
