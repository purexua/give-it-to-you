package com.purehub.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product_interest_rate")
public class ProductInterestRate {
  @TableId(value = "p_rate_id")
  Integer pRateId;
  String productCategory;
  Integer term;
  Double interestRate;
  Double loanAmount;
}
