package com.purehub.controller.plan;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.purehub.pojo.RepaymentPlan;
import com.purehub.pojo.RepaymentResult;
import com.purehub.service.plan.RepaymentPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@Tag(name = "还款计划相关接口")
public class RepaymentPlanController {
    @Autowired
    private RepaymentPlanService repaymentPlanService;
    private static RepaymentResult repaymentResult = new RepaymentResult();

    @GetMapping("/getAllPlans")
    @Operation(summary = "获取所有还款计划")
    public RepaymentResult selectAllPlan(@RequestParam Integer userId, @RequestParam Integer current, @RequestParam Integer size) {
        QueryWrapper<RepaymentPlan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        try {
            Page<RepaymentPlan> page = new Page<>(current, size); // 当前页码和每页记录数
            IPage<RepaymentPlan> plans = repaymentPlanService.page(page, queryWrapper);
            return repaymentResult.success(plans, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return repaymentResult.error("操作失败");
        }
    }
    @PostMapping("/plan/add")
    @Operation(summary = "添加还款计划")
    public String addRepaymentPlan(@RequestBody RepaymentPlan repaymentPlan) {
        repaymentPlan.setDueAmount(repaymentPlan.getTotalAmount() / repaymentPlan.getTerm());
        repaymentPlan.setPlanStartTime(Timestamp.valueOf(LocalDateTime.now()));
        repaymentPlanService.save(repaymentPlan);
        return "success";
    }

}