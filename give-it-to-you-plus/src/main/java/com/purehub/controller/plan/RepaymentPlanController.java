package com.purehub.controller.plan;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.purehub.common.result.R;
import com.purehub.mapper.RepaymentPlanMapper;
import com.purehub.pojo.RepaymentPlan;
import com.purehub.pojo.RepaymentResult;
import com.purehub.service.plan.RepaymentPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@Tag(name = "还款计划相关接口")
public class RepaymentPlanController {
    @Autowired
    private RepaymentPlanService repaymentPlanService;
    @Autowired
    private RepaymentPlanMapper repaymentPlanMapper;

    @GetMapping("/getAllPlans")
    @Operation(summary = "获取所有还款计划")
    public R selectAllPlan(@RequestParam Integer userId,
                           @RequestParam Integer current,
                           @RequestParam Integer size,
                           @RequestParam(required = false) Integer currentTerm,
                           @RequestParam(required = false) Integer applicationId) {
        QueryWrapper<RepaymentPlan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Page<RepaymentPlan> page = new Page<>(current, size);
        IPage<RepaymentPlan> plans = repaymentPlanService.page(page, queryWrapper);
        return R.ok(repaymentPlanMapper.selectPage(plans, currentTerm, applicationId));
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