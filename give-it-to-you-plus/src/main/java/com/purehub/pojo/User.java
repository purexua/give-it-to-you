package com.purehub.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("user")
public class User {
  @TableId(value = "user_id", type = IdType.AUTO)
  private Integer userId;
  private String userName;
  private String password;
  private String realName;
  private String idCard;
  private String email;
  private String address;
  private String phoneNumber;
  private String healthStatus;
  private String creditStatus;
  private String role;
  private Double wallet;
  private Timestamp registerTime;

  @TableLogic
  private Integer deleted;

  @Version
  private Integer version;
}
