package com.purehub.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("loan_application")
public class LoanApplication {
  @TableId(value = "application_id", type = IdType.AUTO)
  Integer applicationId;
  Integer userId;
  String productType;
  Integer term;
  Double requestedAmount;
  Double interestRate;
}
