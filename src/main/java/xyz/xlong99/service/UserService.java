package xyz.xlong99.service;

import xyz.xlong99.entity.Organization;
import xyz.xlong99.entity.Student;

import java.util.List;

/**
 * @author:Cui
 * @date:2019/8/8
 * @type: interface(接口)
 * @description:
 * @action:
 */
public interface UserService {

    /**
     * 查询所有学生列表
     * @return
     */
    List<Student> findAllStudent(String page,String sort,String order);

    /**
     * 查询所有组织列表
     * @return
     */
    List<Organization> findAllOrganization(String page,String sort,String order);

    /**
     * 修改学生属性
     */
    void updateStudent(Student student);

    /**
     * 修改组织信息
     */
    void updateOrganization(Organization organization);

    /**
     * 修改权限
     * @param userId
     * @param isActive
     */
    void changePermission(String userId,String isActive);

}
