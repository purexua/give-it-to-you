package com.purehub.controller.rate;

import com.purehub.pojo.ProductInterestRate;
import com.purehub.service.rate.ProductInterestRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "利率相关接口")
public class RateController {
  @Autowired
  private ProductInterestRateService productInterestRateService;

  @GetMapping("/rate/product/info")
  @Operation(summary = "获取所有利率")
  public List<ProductInterestRate> getAllProductInterestRate() {
    System.out.println("### 获取所有产品利率信息");
    return productInterestRateService.list();
  }

  @PostMapping("/rate/product/add")
  @Operation(summary = "添加利率")
  public String addProductInterestRate(@RequestBody ProductInterestRate productInterestRate) {
    productInterestRateService.save(productInterestRate);
    System.out.println("### 添加产品利率信息成功");
    return "success";
  }

  @DeleteMapping("/rate/product/delete")
  @Operation(summary = "删除产品利率")
  public String deleteProductInterestRate(@RequestParam Integer rateId) {
    productInterestRateService.removeById(rateId);
    System.out.println("### 删除产品利率信息成功 - " + rateId);
    return "success";
  }

  @PutMapping("/rate/product/update")
  @Operation(summary = "更新产品利率")
  public String updateProductInterestRate(@RequestBody ProductInterestRate productInterestRate) {
    productInterestRateService.updateById(productInterestRate);
    System.out.println("### 修改产品利率信息成功 - " + productInterestRate.toString());
    return "success";
  }
}
