package com.purehub.controller.user;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
}
