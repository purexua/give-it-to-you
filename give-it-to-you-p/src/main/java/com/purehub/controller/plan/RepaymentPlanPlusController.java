package com.purehub.controller.plan;

import com.purehub.pojo.RepaymentPlanPlus;
import com.purehub.service.plan.RepaymentPlanPlusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepaymentPlanPlusController {

  @Autowired
  private RepaymentPlanPlusService repaymentPlanPlusService;

  @PostMapping("/repayment/plan/plus")
  public String createRepaymentPlanAfterLoan(@RequestParam Integer userId, @RequestParam Integer applicationId, @RequestParam Double amountDue) {
    //根据传的 三个参数 UserId applicationId  amountDue 加上其他参数 生成还款计划 ,其中 还款计划的 installment 为1 dueDate为当前时间
    System.out.println("### createRepaymentPlanAfterLoan" + userId + applicationId + amountDue);
    RepaymentPlanPlus repaymentPlanPlus = new RepaymentPlanPlus();
    repaymentPlanPlus.setUserId(userId);
    repaymentPlanPlus.setApplicationId(applicationId);
    repaymentPlanPlus.setAmountDue(amountDue);
    repaymentPlanPlus.setInstallment(1);
    repaymentPlanPlus.setPaymentStatus("unpaid");
    repaymentPlanPlus.setDueDate(java.time.LocalDate.now());
    System.out.println(repaymentPlanPlus);
    repaymentPlanPlusService.save(repaymentPlanPlus);
    System.out.println("???????????????????????????????????????");
    return "success";
  }

}
