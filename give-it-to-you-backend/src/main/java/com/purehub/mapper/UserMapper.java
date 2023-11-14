package com.purehub.mapper;

import com.purehub.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
  User selectUserByUserName(String userName);
  void insertUser(User user);
}
