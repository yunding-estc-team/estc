package xyz.xlong99.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xlong99.dao.AuthDao;
import xyz.xlong99.entity.OrganizationAuth;
import xyz.xlong99.entity.RewardAuth;
import xyz.xlong99.entity.StudentAuth;
import xyz.xlong99.service.AuthService;

import java.util.List;

/**
 * @author xlong
 * @date 2019-08-08 16:40
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthDao authDao;
    @Override
    public List<StudentAuth> getStudentAuth() {
        return authDao.getStudentAuth();
    }

    @Override
    public List<OrganizationAuth> getOrganizationAuth() {
        return authDao.getOrganizationAuth();
    }

    @Override
    public List<RewardAuth> getRewardAuth() {
        return authDao.getRewardAuth();
    }

    @Override
    public void updateUserAuth(String userId,String code) {
        authDao.updateUserAuth(userId,code);
    }

    @Override
    public void updateRewardAuth(String id,String code) {
        authDao.updateRewardAuth(id,code);
    }
}
