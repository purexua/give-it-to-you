package com.purehub.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName("repayment_plan")
public class RepaymentPlanPlus {
  @TableId("plan_id")
  private Integer planId;
  private Integer userId;
  private Integer applicationId;
  private Integer installment;
  private LocalDate dueDate;
  private Double amountDue;
  private String paymentStatus;
}
