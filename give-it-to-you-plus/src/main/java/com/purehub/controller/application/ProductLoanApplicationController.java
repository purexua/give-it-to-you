package com.purehub.controller.application;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.purehub.pojo.*;
import com.purehub.service.application.LoanApplicationService;
import com.purehub.service.credit.CreditScoreService;
import com.purehub.service.rate.ProductInterestRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "产品贷款相关接口")
public class ProductLoanApplicationController {
  @Autowired
  private ProductInterestRateService productInterestRateService;
  @Autowired
  private LoanApplicationService loanApplicationService;
  @Autowired
  private CreditScoreService creditScoreService;

  @PostMapping("/application/product")
  @Operation(summary = "申请产品贷款")
  public String createProductLoanApplication(@RequestBody LoanApplication productLoanApplication) {
    productLoanApplication.setApplicationTime(Timestamp.valueOf(LocalDateTime.now()));
    loanApplicationService.save(productLoanApplication);
    System.out.println("### 创建贷款申请成功 - 产品贷款申请");
    return "success";
  }

  @PostMapping("/application/personal")
  @Operation(summary = "申请个性化贷款")
  public String createPersonalLoanApplication(@RequestBody LoanApplication personalLoanApplication) {
    personalLoanApplication.setApplicationTime(Timestamp.valueOf(LocalDateTime.now()));
    loanApplicationService.save(personalLoanApplication);
    System.out.println("### 创建贷款申请成功 - 个性化贷款申请");
    return "success";
  }

  // 获取所有贷款申请信息
  @GetMapping("/application/info/page")
  @Operation(summary = "获取所有贷款申请信息")
  public List<LoanApplication> getLoanApplicationInfoAfterPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
    Page<LoanApplication> page = new Page<>(pageNum, pageSize);
    loanApplicationService.page(page, null);
    System.out.println("### 获取所有贷款申请信息成功");
    return page.getRecords();
  }
  //获取所有贷款申请信息的总条数
  @GetMapping("/application/info/count")
  @Operation(summary = "获取所有贷款申请信息的总条数")
  public Long getLoanApplicationInfoCount(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
    Page<LoanApplication> page = new Page<>(pageNum, pageSize);
    loanApplicationService.page(page, null);
    System.out.println("### 获取所有贷款申请信息的总条数成功");
    return page.getTotal();
  }

  @PutMapping("/application/change/status")
  @Operation(summary = "改变申请状态")
  public String updateLoanApplicationStatus(@RequestParam Integer applicationId, @RequestParam String status) {
    LoanApplication loanApplication = loanApplicationService.getById(applicationId);
    loanApplication.setStatus(status);
    loanApplication.setApprovalTime(Timestamp.valueOf(LocalDateTime.now()));
    loanApplicationService.updateById(loanApplication);
    System.out.println("### 修改贷款申请状态成功");
    return "success";
  }
  @GetMapping("/application/record")
  @Operation(summary = "获取申请记录")
  public RepaymentResult getLoanApplicationInfoCount(@RequestParam Integer current, @RequestParam Integer size, @RequestParam Integer userId) {
    Page<LoanApplication> page = new Page<>(current, size);
    QueryWrapper<LoanApplication> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("user_id",userId);
    IPage<LoanApplication> pages = loanApplicationService.page(page,queryWrapper);
    return pages != null ? new RepaymentResult().success(pages,"成功获取所有的申请记录") : new RepaymentResult().error("获取失败");
  }
  @DeleteMapping("/application/deleterecord")
  @Operation(summary = "删除申请记录")
  public RepaymentResult deleteRecord(@RequestParam Integer applicationId) {
    //改申请表
    QueryWrapper<LoanApplication> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("application_id",applicationId);
    LoanApplication loanApplicationServiceOne = loanApplicationService.getOne(queryWrapper);

    QueryWrapper<CreditScore> queryWrapper1 = new QueryWrapper<>();
    queryWrapper1.eq("user_id", loanApplicationServiceOne.getUserId());
    //改额度
    CreditScore creditScoreServiceOne = creditScoreService.getOne(queryWrapper1);
    creditScoreServiceOne.setLimitAmount(creditScoreServiceOne.getLimitAmount() + loanApplicationServiceOne.getRequestedAmount());
    //更新额度&&删除记录
    return loanApplicationService.remove(queryWrapper) && creditScoreService.update(creditScoreServiceOne,queryWrapper1) ? new RepaymentResult().success(null,"撤回成功") : new RepaymentResult().error("撤回失败");
  }
  @GetMapping("application/getAllType")
  @Operation(summary = "获取所有产品")
  public RepaymentResult getAllProductType()
  {
    if(productInterestRateService.list() == null)
      return new RepaymentResult().error("获取产品失败");
    return new RepaymentResult().success(productInterestRateService.list(),"获取产品成功");
  }
  @GetMapping("application/getAllTypeUser")
  @Operation(summary = "根据用户获取产品")
  public RepaymentResult getAllProductTypeUser()
  {
    List<ProductInterestRate> productInterestRateList = productInterestRateService.list();
    for (int i = 0; i < productInterestRateList.size(); i++) {
    }
    return new RepaymentResult().success(productInterestRateService.list(),"获取产品成功");
  }
}
