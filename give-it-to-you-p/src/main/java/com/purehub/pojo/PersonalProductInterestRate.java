package com.purehub.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */

@Data
@TableName("interest_rate")
public class PersonalProductInterestRate {
  @TableId(value = "rate_id", type = IdType.AUTO)
  private Integer rateId;
  private Double term;
  private Double interestRate;
  @TableField("max_loan_amount")
  private Double maxLoanAmount;
}
