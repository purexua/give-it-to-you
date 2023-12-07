package com.purehub.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product_interest_rate")
public class ProductInterestRate {
  @TableId(value = "rate_id",type = IdType.AUTO)
  Integer rateId;
  String productType;
  Integer term;
  Double interestRate;
  Double loanAmount;
}
