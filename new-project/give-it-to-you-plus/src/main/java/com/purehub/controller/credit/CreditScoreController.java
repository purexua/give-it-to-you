package com.purehub.controller.credit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

  @GetMapping("/credit/score/info")
  public CreditScore getCreditScoreInfo(@RequestParam("userId") Integer userId) {
    QueryWrapper<CreditScore> creditScoreQueryWrapper = new QueryWrapper<>();
    creditScoreQueryWrapper.eq("user_id", userId);
    return creditScoreService.getOne(creditScoreQueryWrapper);
  }

  @PutMapping("/credit/limit/sub")
  public String updateCreditScoreLimitAmountAfterLoan(@RequestParam("userId") Integer userId, @RequestParam("loanAmount") Double loanAmount) {
    QueryWrapper<CreditScore> creditScoreQueryWrapper = new QueryWrapper<>();
    creditScoreQueryWrapper.eq("user_id", userId);
    CreditScore creditScore = creditScoreService.getOne(creditScoreQueryWrapper);
    creditScore.setLimitAmount(creditScore.getLimitAmount() - loanAmount);
    creditScoreService.update(creditScore, creditScoreQueryWrapper);
    System.out.println("### 更新贷款额度成功 - 扣减");
    return "success";
  }

}
