package com.purehub.controller.plan;

import com.purehub.pojo.RepaymentPlan;
import com.purehub.service.plan.RepaymentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
public class RepaymentPlanController {

  @Autowired
  private RepaymentPlanService repaymentPlanService;

  @PostMapping("/plan/add")
  public String addRepaymentPlan(@RequestBody RepaymentPlan repaymentPlan) {
    repaymentPlan.setDueAmount(repaymentPlan.getTotalAmount() / repaymentPlan.getTerm());
    repaymentPlan.setPlanStartTime(Timestamp.valueOf(LocalDateTime.now()));
    repaymentPlanService.save(repaymentPlan);
    return "success";
  }
}
