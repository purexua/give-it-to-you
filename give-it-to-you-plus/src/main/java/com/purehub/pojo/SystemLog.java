package com.purehub.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@TableName("system_log")
@Data
public class SystemLog {
  @TableId(value = "log_id", type = IdType.AUTO)
  Integer logId;
  Integer userId;
  Timestamp logTime;
  String action;
}
