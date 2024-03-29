package com.purehub.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.purehub.annotation.OperationLog;
import com.purehub.pojo.User;
import com.purehub.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@Tag(name = "用户相关接口")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/count")
  @Operation(summary = "获取用户count")
  public long count() {
    return userService.count();
  }
  @GetMapping("/login")
  @Operation(summary = "用户登录")
  @OperationLog(action = "登录",logTime = "2000-03-06 10:10:50")
  public User login(@RequestParam String userName) {
    //根据userName 查询单个用户
    LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(User::getUserName, userName).last("limit 1");
    System.out.println("### userName = " + userName + "login !");
    return userService.getOne(wrapper);
  }

  @PostMapping("/register")
  @Operation(summary = "用户注册")
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
  @Operation(summary = "获取用户信息")
  public User getUserInfo(@RequestParam Integer userId) {
    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    userQueryWrapper.eq("user_id", userId);
    return userService.getOne(userQueryWrapper);
  }

  @PutMapping("/user/change/password")
  @Operation(summary = "修改密码")
  public String updateUserPassword(@RequestParam Integer userId, @RequestParam String password) {
    User user = userService.getById(userId);
    user.setPassword(password);
    userService.updateById(user);
    System.out.println("### 用户修改密码成功");
    return "success";
  }

  @PutMapping("/user/change/info")
  @Operation(summary = "修改用户信息")
  public String updateUserInfo(@RequestBody User user) {
    userService.updateById(user);
    System.out.println("### 用户修改信息成功 - " + user);
    return "success";
  }

  @PutMapping("/user/recharge")
  @Operation(summary = "用户充值金额")
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
