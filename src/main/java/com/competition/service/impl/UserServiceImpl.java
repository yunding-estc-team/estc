package com.competition.service.impl;

import com.competition.entity.User;
import com.competition.dao.UserMapper;
import com.competition.form.SearchForm;
import com.competition.form.SearchPost;
import com.competition.response.PermissionClass;
import com.competition.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
     *
     * @param phoneNumber
     * @return
     */
    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userMapper.findByPhoneNumber(phoneNumber);
    }

    /**
     * 设置属性
     *
     * @param userId
     * @param isActive
     */
    //String userId = JwtHelper.parse(token).getId();
    @Override
    public void setPermission(String userId, String isActive) {

        String isActives = userMapper.selectById(userId).getIsActive();
        List<String> list = new ArrayList<String>(Arrays.asList(isActives.split(",")));
        if (list.contains(isActive)) {
            list.remove(isActive);
        } else {
            list.add(isActive);
        }
        StringBuilder updateIsActive = new StringBuilder();
        for (String s : list) {
            updateIsActive.append(",").append(s);
        }
        User user = new User();
        user.setIsActive(updateIsActive.toString());
        user.setUserId(userId);
        userMapper.updateById(user);
    }

    /**
     * 根据id获得permissionClass对象，id对应用户的权限对象
     *
     * @param userId
     * @return
     */
    @Override
    public PermissionClass getIsActive(String userId) {
        PermissionClass permissionClass = new PermissionClass();
        String isActives = userMapper.selectById(userId).getIsActive();
        List<String> list = new ArrayList<>(Arrays.asList(isActives.split(",")));
        if (list.contains("user:login")) {
            permissionClass.setLogin(true);
        }
        if (list.contains("user:comment")) {
            permissionClass.setComment(true);
        }
        if (list.contains("user:attention")) {
            permissionClass.setAttention(true);
        }
        if (list.contains("user:wiki")) {
            permissionClass.setWiki(true);
        }
        return permissionClass;
    }

    /**
     * 通过用户输入的信息模糊查询个人或赛事名称
     */
    @Override
    public List<String> searchName(SearchForm searchForm) {
        // 获取分页信息
        Integer pageCurrent = searchForm.getPageCurrent();
        Integer pageSize = searchForm.getPageSize();
        Integer aParam = (pageCurrent - 1) * pageSize;

        List<String> list = userMapper.searchUserName(searchForm.getName(), aParam, pageSize);
        System.out.println(list);
        List<String> list1 = userMapper.searchCompetitionName(searchForm.getName(), aParam, pageSize);
        System.out.println(list1);
        list.addAll(list1);
        System.out.println(list);
        return list;
    }

    @Override
    public List<SearchPost> searchAll(SearchForm searchForm) {
        // 获取分页信息
        Integer pageCurrent = searchForm.getPageCurrent();
        Integer pageSize = searchForm.getPageSize();
        Integer aParam = (pageCurrent - 1) * pageSize;
        System.out.println(searchForm.getType());
        if (searchForm.getType().equals("null")) {
            List<SearchPost> list = userMapper.searchAllCompetition(searchForm.getName(), pageCurrent, pageSize);
            return list;
        } else {
            if (searchForm.getType().equals("student")) {
                List<SearchPost> list = userMapper.searchAllStd(searchForm.getName(), searchForm.getType(), pageCurrent, pageSize);
                return list;
            } else{
                List<SearchPost> list = userMapper.searchAllOrg(searchForm.getName(), searchForm.getType(), pageCurrent, pageSize);
                return list;
            }
        }
    }
}
