package com.competition.service;

import com.competition.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.competition.form.SearchForm;
import com.competition.form.SearchPost;
import com.competition.response.PermissionClass;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GuoHaodong
 * @since 2019-08-02
 */
public interface UserService extends IService<User> {

    /**
     * 通过useid查询用户
     * @param userId
     * @return
     */
    User findById(String userId);

    /**
     * 通过手机号查询用户
     * @param phoneNumber
     * @return
     */
    User findByPhoneNumber(String phoneNumber);

    /**
     * 设置用户属性
     * @param userId
     * @param isActive
     */
    void setPermission(String userId,String isActive);

    /**
     * 根据id获得permissionClass对象，id对应用户的权限对象
     * @param userId
     * @return
     */
    PermissionClass getIsActive(String userId);

    /**
     * 通过用户输入的信息模糊查询个人或赛事名称
     */
    List<String> searchName(SearchForm searchForm);

    /**
     * 通过用户输入的信息模糊查询个人或赛事名称
     */
    List<SearchPost> searchAll(SearchForm searchForm);
}
