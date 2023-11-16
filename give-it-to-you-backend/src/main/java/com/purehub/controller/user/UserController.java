package com.purehub.controller.user;

import com.purehub.entity.Result;
import com.purehub.entity.User;
import com.purehub.service.user.UserService;
import com.purehub.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @ResponseBody
  @GetMapping("/login")
  public Result login(@RequestParam String userName) {
    User user = userService.login(userName); //从数据库获取到的user
    //当查询到的用户不为空，创建JWT,并发送给前端
    if(user!=null){
      Map<String, Object> claims = new HashMap<>();
      claims.put("id",user.getUserId());
      String jwt = JwtUtils.generateJwt(claims);
      return Result.success(jwt);
    }
    return Result.error("未查找到用户");
  }

  @ResponseBody
  @PostMapping("/register")
  public String register(@RequestBody User user) {
    return userService.register(user);
  }
}
