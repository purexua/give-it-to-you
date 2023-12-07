package com.purehub.controller.application;

import com.purehub.pojo.LoanApplication;
import com.purehub.service.application.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductLoanApplicationController {

  @Autowired
  private LoanApplicationService loanApplicationService;

  @PostMapping("/application/product")
  public Integer createProductLoanApplication(@RequestBody LoanApplication productLoanApplication) {
    loanApplicationService.save(productLoanApplication);
    System.out.println("### 创建贷款申请成功 - 产品贷款申请");
    return productLoanApplication.getApplicationId();
  }

  @PostMapping("/application/personal")
  public Integer createPersonalLoanApplication(@RequestBody LoanApplication personalLoanApplication) {
    loanApplicationService.save(personalLoanApplication);
    System.out.println("### 创建贷款申请成功 - 个性化贷款申请");
    return personalLoanApplication.getApplicationId();
  }
}
