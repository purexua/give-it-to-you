package com.purehub.controller.repayment;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.purehub.pojo.RepaymentPlan;
import com.purehub.pojo.RepaymentResult;
import com.purehub.pojo.User;
import com.purehub.service.Repayment.RepaymentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RepaymentPlanController {
    @Autowired
    private RepaymentPlanService repaymentPlanService;

    @GetMapping("/getAllPlans")
    public RepaymentResult selectAllPlan(@RequestParam Integer userId, @RequestParam Integer current,@RequestParam Integer size)
    {
        QueryWrapper<RepaymentPlan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",userId);
        try{
            Page<RepaymentPlan> page = new Page<>(current, size); // 当前页码和每页记录数
            IPage<RepaymentPlan> plans = repaymentPlanService.page(page, queryWrapper);
            return new RepaymentResult().success(plans, "操作成功");
        }catch (Exception e)
        {
            e.printStackTrace();
            return new RepaymentResult().error("操作失败");
        }
    }
}
