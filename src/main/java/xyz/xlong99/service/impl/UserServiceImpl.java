package xyz.xlong99.service.impl;

import xyz.xlong99.util.ActiveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.xlong99.dao.UserDao;
import xyz.xlong99.entity.Organization;
import xyz.xlong99.entity.Student;
import xyz.xlong99.service.UserService;

import java.util.List;

/**
 * @author:Cui
 * @date:2019/8/8
 * @type: class(类)
 * @description:
 * @action:
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<Student> findAllStudent(Integer page) {
        Integer startNum = (page-1)*10;
        Integer lastNum = page*10;
        return userDao.selectAllStudent(startNum,lastNum);
    }

    @Override
    public List<Organization> findAllOrganization(Integer page) {
        Integer startNum = (page-1)*10;
        Integer lastNum = page*10;
        return userDao.selectAllOrganization(startNum,lastNum);
    }

    @Override
    public void updateStudent(Student student) {
        userDao.updateStudent(student);
    }

    @Override
    public void updateOrganization(Organization organization) {
        userDao.updateOrganization(organization);
    }

    /**
     * 设置权限
     * @param userId
     * @param isActive
     */
    @Override
    public void changePermission(String userId,String isActive){
        userDao.updateIsActive(ActiveUtil.setPermission(
                userDao.selectIsActive(userId),isActive),userId);
    }
}
