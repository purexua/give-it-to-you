package com.purehub.controller.record;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.purehub.common.ReportGenerator;
import com.purehub.common.result.R;
import com.purehub.pojo.*;
import com.purehub.service.application.LoanApplicationService;
import com.purehub.service.credit.CreditScoreService;
import com.purehub.service.plan.RepaymentPlanService;
import com.purehub.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import com.purehub.service.record.RepaymentRecordService;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Tag(name = "还款相关接口")
@RestController
public class RepaymentRecordsController {

    @Autowired
    private RepaymentPlanService repaymentPlanService;
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
    @DeleteMapping ("/deleteRecord")
    @Operation(summary = "删除还款记录")
    public R deleteRecord(@RequestParam Integer planID, @RequestParam Integer recordId)
    {
        if(Objects.equals(repaymentPlanService.getById(planID).getPlanId(), planID))
        {
            return R.fail();
        }else {
            return repaymentRecordsService.removeById(recordId) ? R.ok() : R.fail();
        }
    }
    @GetMapping ("/report")
    @Operation(summary = "生成还款记录报表")
    public ResponseEntity<FileSystemResource> downloadReport() throws SQLException, IOException {

        ReportGenerator.generateReport();
        // 报表文件路径
        String filePath = "./report.xlsx";

        // 创建文件资源对象
        File file = new File(filePath);
        FileSystemResource resource = new FileSystemResource(file);

        // 构建响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.xlsx");

        // 设置响应内容类型
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        // 返回响应实体
        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
