package com.competition.service.impl;

import com.competition.entity.User;
import com.competition.dao.UserMapper;
import com.competition.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GuoHaodong
 * @since 2019-07-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findById(String userId) {
        return userMapper.selectById(userId);
    }

    /**
     * 通过手机号查询用户
     * @param phoneNumber
     * @return
     */
    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userMapper.findByPhoneNumber(phoneNumber);
    }
}
