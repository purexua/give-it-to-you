package com.purehub.service.user;

import com.purehub.entity.User;

public interface UserService {
  User login(String userName);

  String register(User user);
}
