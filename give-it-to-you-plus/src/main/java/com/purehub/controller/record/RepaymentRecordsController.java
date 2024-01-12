package com.purehub.controller.record;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.purehub.common.result.R;
import com.purehub.pojo.*;
import com.purehub.service.application.LoanApplicationService;
import com.purehub.service.credit.CreditScoreService;
import com.purehub.service.plan.RepaymentPlanService;
import com.purehub.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import com.purehub.service.record.RepaymentRecordService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Tag(name = "还款相关接口")
@RestController
public class RepaymentRecordsController {

    @Autowired
    private RepaymentRecordService repaymentRecordsService;

    @Operation(summary = "分页查询所有还款记录")
    @GetMapping("/findAllRepaymentRecords")
    public R findAllRecords(@RequestParam Integer userId,@RequestParam Integer current, @RequestParam Integer size)
    {
        QueryWrapper<RepaymentRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        try {
            Page<RepaymentRecord> page = new Page<>(current, size); // 当前页码和每页记录数
            IPage<RepaymentRecord> records = repaymentRecordsService.page(page, queryWrapper);
            return R.ok(records);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail();
        }
    }
    @PostMapping("/addRecord")
    @Operation(summary = "添加还款记录")
    public R findAllRecords(@RequestBody RepaymentRecord record)
    {
        //判断余额是否足够还款
        if(!repaymentRecordsService.isEnough(record.getPaymentAmount(), record.getUserId()))
            return R.fail("余额不足，请充值再来~");
        //改变当前期数
        QueryWrapper<RepaymentRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("plan_id", record.getPlanId());
        if(!repaymentRecordsService.changeCurrentTerm(record.getPlanId()))
            return R.ok("已全部还清!");
        if(repaymentRecordsService.getOne(queryWrapper) == null)
            return repaymentRecordsService.insertFirstRecord(record) ? R.ok() : R.fail();
        return repaymentRecordsService.updatePaymentDateAndActualAmount(record) ? R.ok() : R.fail();
    }
    @PostMapping("/addRecordBatch")
    @Operation(summary = "批量添加还款记录")
    public R addRecordBatch(@RequestBody List<RepaymentRecord> records)
    {
       return repaymentRecordsService.addRecordsBatch(records) ? R.ok() : R.fail();
    }
}
