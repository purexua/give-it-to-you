package com.purehub.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product_loan_application")
public class ProductLoanApplication {
  @TableId(value = "application_id")
  Integer applicationId;
  Integer userId;
  String productCategory;
  Integer term;
  Double requestedAmount;
  Double interestRate;
}
