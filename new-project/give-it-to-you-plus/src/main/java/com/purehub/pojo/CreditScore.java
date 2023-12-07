package com.purehub.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("credit_score")
public class CreditScore {
  @TableId(value = "score_id", type = IdType.AUTO)
  private Integer scoreId;
  private Integer userId;
  private Integer creditScore;
  private Double monthlyIncome;
  private Double expenses;
  private String employmentStatus;
  private int jobDuration;
  private String educationLevel;
  private int overdueRecords;
  private Double assetsValue;
  private int socialConnections;
  private int legalDisputes;
  private String behaviorAnalysis;

}
