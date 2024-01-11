package com.purehub.controller.credit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.purehub.pojo.CreditScore;
import com.purehub.service.credit.CreditScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "信用相关接口")
public class CreditScoreController {

  @Autowired
  private CreditScoreService creditScoreService;

  @GetMapping("/credit/score/info")
  @Operation(summary = "获取用户分信息")
  public CreditScore getCreditScoreInfo(@RequestParam("userId") Integer userId) {
    QueryWrapper<CreditScore> creditScoreQueryWrapper = new QueryWrapper<>();
    creditScoreQueryWrapper.eq("user_id", userId);
    return creditScoreService.getOne(creditScoreQueryWrapper);
  }

  @PutMapping("/credit/limit/sub")
  @Operation(summary = "减少用户额度")
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
