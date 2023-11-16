package com.purehub.entity;

import lombok.Data;

@Data
public class User {
 Integer userId;
  String userName;
  String password;
  String realName;
  String idCard;
  String email;
  String address;
  String phoneNumber;
  Integer balance;
  String role;
}
