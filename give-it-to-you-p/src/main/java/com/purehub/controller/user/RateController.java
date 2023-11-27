package com.purehub.controller.user;

import com.purehub.pojo.ProductInterestRate;
import com.purehub.service.rate.ProductInterestRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RateController {

  @Autowired
  private ProductInterestRateService productInterestRateService;

  @GetMapping("/rate/product")
  public List<ProductInterestRate> getAllProductInterestRate() {
    System.out.println("### getAllProductInterestRate");
    return productInterestRateService.list();
  }
}
