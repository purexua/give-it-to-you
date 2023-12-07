package com.purehub.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("repayment_plan")
public class RepaymentPlan {
  @TableId(value = "plan_id", type = IdType.AUTO)
  Integer planId;
  Integer userId;
  Integer applicationId;
  Integer term;
  Integer currentTerm;
  Double dueAmount;
  Double totalAmount;
  Timestamp planStartTime;
  @TableLogic
  Integer isPaid;
}
