package com.purehub.controller.rate;

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

  @GetMapping("/rate/product/info")
  public List<ProductInterestRate> getAllProductInterestRate() {
    System.out.println("### 获取所有产品利率信息");
    return productInterestRateService.list();
  }
}
