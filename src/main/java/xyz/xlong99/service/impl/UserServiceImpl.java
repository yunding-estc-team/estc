package xyz.xlong99.service.impl;

import org.springframework.stereotype.Service;
import xyz.xlong99.util.ActiveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.xlong99.dao.UserDao;
import xyz.xlong99.entity.Organization;
import xyz.xlong99.entity.Student;
import xyz.xlong99.service.UserService;
import xyz.xlong99.util.SortUtil;

import java.util.List;

/**
 * @author:Cui
 * @date:2019/8/8
 * @type: class(类)
 * @description:
 * @action:
 */
@Service("UserServiceX")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<Student> findAllStudent(String page,String sort,String order) {
        Integer startNum = (Integer.parseInt(page)-1)*10;
        String orderSql = SortUtil.toSortSql(sort, order);
        return userDao.selectAllStudent(startNum,orderSql);
    }

    @Override
    public List<Organization> findAllOrganization(String page,String sort,String order) {
        Integer startNum = (Integer.parseInt(page)-1)*10;
        String orderSql = SortUtil.toSortSql(sort, order);
        return userDao.selectAllOrganization(startNum,orderSql);
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
