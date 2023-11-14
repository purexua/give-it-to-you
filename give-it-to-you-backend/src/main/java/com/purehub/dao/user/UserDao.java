package com.purehub.dao.user;

import com.purehub.entity.User;

public interface UserDao {
  User selectUserByUserName(String userName);

  void insertUser(User user);
}
