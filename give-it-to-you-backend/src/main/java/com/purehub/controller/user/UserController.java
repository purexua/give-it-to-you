package com.purehub.controller.user;

import com.purehub.entity.User;
import com.purehub.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @ResponseBody
  @GetMapping("/login")
  public User login() {
    return userService.login("admin");
  }

  @ResponseBody
  @PostMapping("/register")
  public String register(@RequestBody User user) {
    return userService.register(user);
  }
}
