package com.competitionRank.dao.service.impl;

import com.competitionRank.dao.mapper.UserMapper;
import com.competitionRank.dao.service.UserService;
import com.competitionRank.entity.UserDO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDO selectById() {
        return userMapper.getUserById("1");
    }
}
