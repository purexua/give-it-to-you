package com.purehub.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("repayment_record")
public class RepaymentRecord {
  @TableId(value = "record_id", type = IdType.AUTO)
  Integer recordId;
  Integer userId;
  Integer planId;
  LocalDateTime paymentTime;
  Double paymentAmount;
  String paymentMethod;
}
