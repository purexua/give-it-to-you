package com.purehub.controller.user;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
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

  @ResponseBody
  @GetMapping("/login")
  public User login(@RequestParam String userName) {
    //根据userName 查询单个用户
    LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(User::getUserName, userName).last("limit 1");
    System.out.println("userName = " + userName + "login !");
    return userService.getOne(wrapper);
  }

  @ResponseBody
  @PostMapping("/register")
  public String register(@RequestBody User user) {
    if (userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUserName, user.getUserName())) != null) {
      return "fail";
    } else {
      userService.save(user);
      return "success";
    }
  }

  @ResponseBody
  @PutMapping("/user/password")
  public String updatePassword(@RequestParam Integer userId, @RequestParam String password) {
    User user = userService.getById(userId);
    user.setPassword(password);
    userService.updateById(user);
    return "success";
  }

  @ResponseBody
  @PutMapping("/user")
  public String updateUser(@RequestBody User user) {
    userService.updateById(user);
    return "success";
  }

  @ResponseBody
  @PutMapping("/user/recharge")
  public String recharge(@RequestParam Integer userId, @RequestParam Integer balance) {
    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    userQueryWrapper.eq("user_id", userId);
    User user = userService.getOne(userQueryWrapper);
    user.setBalance(user.getBalance() + balance);
    userService.update(user, userQueryWrapper);
    return "success";
  }

  @ResponseBody
  @PutMapping("/changeBalance")
  public String changeBalance(@RequestParam Integer userId, @RequestParam Integer balance) {
    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    userQueryWrapper.eq("user_id", userId);
    User user = userService.getOne(userQueryWrapper);
    user.setBalance(user.getBalance() - balance);
    userService.update(user, userQueryWrapper);
    return "success";
  }
  @GetMapping("/getUserInfo")
  public User getUserInfo(@RequestParam Integer userId) {
    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    userQueryWrapper.eq("user_id", userId);
    return userService.getOne(userQueryWrapper);
  }
}
