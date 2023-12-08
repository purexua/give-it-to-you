package com.purehub.controller.application;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.purehub.pojo.LoanApplication;
import com.purehub.service.application.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ProductLoanApplicationController {

  @Autowired
  private LoanApplicationService loanApplicationService;

  @PostMapping("/application/product")
  public String createProductLoanApplication(@RequestBody LoanApplication productLoanApplication) {
    productLoanApplication.setApplicationTime(Timestamp.valueOf(LocalDateTime.now()));
    loanApplicationService.save(productLoanApplication);
    System.out.println("### 创建贷款申请成功 - 产品贷款申请");
    return "success";
  }

  @PostMapping("/application/personal")
  public String createPersonalLoanApplication(@RequestBody LoanApplication personalLoanApplication) {
    loanApplicationService.save(personalLoanApplication);
    System.out.println("### 创建贷款申请成功 - 个性化贷款申请");
    return "success";
  }

  // 获取所有贷款申请信息
  @GetMapping("/application/info/page")
  public List<LoanApplication> getLoanApplicationInfoAfterPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
    Page<LoanApplication> page = new Page<>(pageNum, pageSize);
    loanApplicationService.page(page, null);
    System.out.println("### 获取所有贷款申请信息成功");
    return page.getRecords();
  }

  //获取所有贷款申请信息的总条数
  @GetMapping("/application/info/count")
  public Long getLoanApplicationInfoCount(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
    Page<LoanApplication> page = new Page<>(pageNum, pageSize);
    loanApplicationService.page(page, null);
    System.out.println("### 获取所有贷款申请信息的总条数成功");
    return page.getTotal();
  }

  @PutMapping("/application/change/status")
  public String updateLoanApplicationStatus(@RequestParam Integer applicationId, @RequestParam String status) {
    LoanApplication loanApplication = loanApplicationService.getById(applicationId);
    loanApplication.setStatus(status);
    loanApplication.setApprovalTime(Timestamp.valueOf(LocalDateTime.now()));
    loanApplicationService.updateById(loanApplication);
    System.out.println("### 修改贷款申请状态成功");
    return "success";
  }
}
