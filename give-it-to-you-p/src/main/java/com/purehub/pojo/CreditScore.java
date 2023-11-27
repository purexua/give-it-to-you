package com.purehub.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("credit_score")
public class CreditScore {
  @TableId(value = "score_id")
  private Integer ScoreId;
  private Integer userId;
  private Double  limitAmount;
}
