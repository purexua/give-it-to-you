package com.purehub.dao.user;

import com.purehub.entity.User;
import com.purehub.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
  @Autowired
  private UserMapper userMapper;

  public User selectUserByUserName(String userName) {
    return userMapper.selectUserByUserName(userName);
  }

  public void insertUser(User user) {
    userMapper.insertUser(user);
  }
}
