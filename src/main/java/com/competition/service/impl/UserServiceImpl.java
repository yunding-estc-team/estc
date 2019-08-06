package com.competition.service.impl;

import com.competition.entity.User;
import com.competition.dao.UserMapper;
import com.competition.response.PermissionClass;
import com.competition.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GuoHaodong
 * @since 2019-08-02
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
    /**
     *  设置属性
     * @param userId
     * @param isActive
     */
    //String userId = JwtHelper.parse(token).getId();
    @Override
    public void setPermission(String userId,String isActive){

        String isActives =  userMapper.selectById(userId).getIsActive();
        List<String> list = new ArrayList<String>(Arrays.asList(isActives.split(",")));
        if(list.contains(isActive)){
            list.remove(isActive);
        }else {
            list.add(isActive);
        }
        StringBuilder updateIsActive=new StringBuilder();
        for(String s : list){
            updateIsActive.append(",").append(s);
        }
        User user = new User();
        user.setIsActive(updateIsActive.toString());
        user.setUserId(userId);
        userMapper.updateById(user);
    }

    /**
     * 根据id获得permissionClass对象，id对应用户的权限对象
     * @param userId
     * @return
     */
    @Override
    public PermissionClass getIsActive(String userId){
        PermissionClass permissionClass = new PermissionClass();
        String isActives = userMapper.selectById(userId).getIsActive();
        List<String> list = new ArrayList<>(Arrays.asList(isActives.split(",")));
        if(list.contains("user:login")){
            permissionClass.setLogin(true);
        }
        if(list.contains("user:comment")){
            permissionClass.setComment(true);
        }
        if(list.contains("user:attention")){
            permissionClass.setAttention(true);
        }
        if(list.contains("user:praise")){
            permissionClass.setPraise(true);
        }
        if(list.contains("user:wiki")){
            permissionClass.setWiki(true);
        }
        return permissionClass;
    }
}
