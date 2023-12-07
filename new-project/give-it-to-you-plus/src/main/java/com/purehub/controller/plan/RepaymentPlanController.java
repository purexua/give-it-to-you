package com.purehub.controller.plan;

import com.purehub.service.plan.RepaymentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepaymentPlanController {

  @Autowired
  private RepaymentPlanService repaymentPlanService;
}
