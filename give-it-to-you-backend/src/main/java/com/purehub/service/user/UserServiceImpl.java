package com.purehub.service.user;

import com.purehub.dao.user.UserDao;
import com.purehub.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Override
  public User login(String userName) {
    System.out.println("userName: " + userName + " is login ing...");
    return userDao.selectUserByUserName(userName);
  }

  @Override
  public String register(User user) {
    System.out.println("userName: " + user.getUserName() + " is register ing...");
    if (userDao.selectUserByUserName(user.getUserName()) != null) {
      return "fail - username is exist";
    } else {
      userDao.insertUser(user);
      return "success";
    }
  }
}