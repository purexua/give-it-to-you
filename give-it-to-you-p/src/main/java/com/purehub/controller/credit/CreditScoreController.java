package com.purehub.controller.credit;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.purehub.pojo.CreditScore;
import com.purehub.service.credit.CreditScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @PutMapping("/credit/score")
  public String updateLimitAmountAfterLoanApplication(@RequestParam("userId") Integer userId, @RequestParam("amount") Double amount) {
    //根据userId 查询出来creditScore 修改 limitAmount = limitAmount-amount 如果 修改后的limitAmount<0 则返回失败
    CreditScore creditScore = creditScoreService.getById(userId);
    if (creditScore.getLimitAmount() - amount < 0) {
      return "fail - limitAmount low";
    } else {
      UpdateWrapper<CreditScore> updateWrapper = new UpdateWrapper<>();
      updateWrapper.eq("user_id", userId);
      updateWrapper.set("limit_amount", creditScore.getLimitAmount() - amount);
      creditScoreService.update(updateWrapper);
      return "success";
    }
  }

  @PutMapping("/credit/score/return")
  public String updateLimitAmountAfterLoanReturn(@RequestParam("userId") Integer userId, @RequestParam("amount") Double amount) {
    //根据userId 查询出来creditScore 修改 limitAmount = limitAmount+amount
    CreditScore creditScore = creditScoreService.getById(userId);
    UpdateWrapper<CreditScore> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("user_id", userId);
    updateWrapper.set("limit_amount", creditScore.getLimitAmount() + amount);
    creditScoreService.update(updateWrapper);
    return "success";
  }
}
