package com.purehub.controller.application;

import com.purehub.pojo.ProductLoanApplication;
import com.purehub.service.application.ProductLoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductLoanApplicationController {

  @Autowired
  private ProductLoanApplicationService productLoanApplicationService;

  @PostMapping("/application/product")
  public Integer createProductLoanApplication(@RequestBody ProductLoanApplication productLoanApplication) {
    System.out.println("### createProductLoanApplication");
    productLoanApplicationService.save(productLoanApplication);
    return productLoanApplication.getApplicationId();
  }

}
