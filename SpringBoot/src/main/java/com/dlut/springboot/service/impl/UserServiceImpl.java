package com.dlut.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dlut.springboot.entity.User;
import com.dlut.springboot.mapper.UserMapper;
import com.dlut.springboot.service.UserService;
import org.springframework.stereotype.Service;

@Service
//@Transactional  //事务
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
