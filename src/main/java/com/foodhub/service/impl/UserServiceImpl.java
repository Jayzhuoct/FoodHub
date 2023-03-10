package com.foodhub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.foodhub.entity.User;
import com.foodhub.mapper.UserMapper;
import com.foodhub.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
