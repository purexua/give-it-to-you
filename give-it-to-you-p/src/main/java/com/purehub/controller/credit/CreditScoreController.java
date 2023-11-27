package com.purehub.controller.credit;

import com.purehub.pojo.CreditScore;
import com.purehub.service.credit.CreditScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditScoreController {

  @Autowired
  private CreditScoreService creditScoreService;

  @GetMapping("/credit/score")
  public CreditScore getCreditScore(@RequestParam("userId") Integer userId) {
    return creditScoreService.getById(userId);
  }
}
