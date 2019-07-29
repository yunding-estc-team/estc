package com.competition.service.impl;

import com.competition.entity.User;
import com.competition.dao.UserMapper;
import com.competition.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
