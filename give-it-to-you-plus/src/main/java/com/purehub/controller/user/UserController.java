package com.purehub.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.purehub.pojo.User;
import com.purehub.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/login")
  public User login(@RequestParam String userName) {
    //根据userName 查询单个用户
    LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(User::getUserName, userName).last("limit 1");
    System.out.println("### userName = " + userName + "login !");
    return userService.getOne(wrapper);
  }

  @PostMapping("/register")
  public String register(@RequestBody User user) {
    if (userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUserName, user.getUserName())) != null) {
      return "fail - user already exists";
    } else {
      userService.save(user);
      System.out.println("### 用户注册成功 - " + user);
      return "success";
    }
  }

  @GetMapping("/user/info")
  public User getUserInfo(@RequestParam Integer userId) {
    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    userQueryWrapper.eq("user_id", userId);
    return userService.getOne(userQueryWrapper);
  }

  @PutMapping("/user/change/password")
  public String updateUserPassword(@RequestParam Integer userId, @RequestParam String password) {
    User user = userService.getById(userId);
    user.setPassword(password);
    userService.updateById(user);
    System.out.println("### 用户修改密码成功");
    return "success";
  }

  @PutMapping("/user/change/info")
  public String updateUserInfo(@RequestBody User user) {
    userService.updateById(user);
    System.out.println("### 用户修改信息成功 - " + user);
    return "success";
  }

  @PutMapping("/user/recharge")
  public String recharge(@RequestParam Integer userId, @RequestParam("rechargeAmount") Integer wallet) {
    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    userQueryWrapper.eq("user_id", userId);
    User user = userService.getOne(userQueryWrapper);
    user.setWallet(user.getWallet() + wallet);
    userService.update(user, userQueryWrapper);
    System.out.println("### 用户充值成功金额 - " + wallet);
    return "success";
  }
}
