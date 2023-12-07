package com.purehub.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purehub.mapper.UserMapper;
import com.purehub.pojo.User;
import com.purehub.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
