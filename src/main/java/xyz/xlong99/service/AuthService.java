package xyz.xlong99.service;


import xyz.xlong99.entity.OrganizationAuth;
import xyz.xlong99.entity.RewardAuth;
import xyz.xlong99.entity.StudentAuth;

import java.util.List;

/**
 * @author xlong
 * @date 2019-08-08 16:39
 */

public interface AuthService {
    /**
     * 获取学生认证信息
     * @return
     */
    List<StudentAuth> getStudentAuth();

    /**
     * 获取组织认证信息
     * @return
     */
    List<OrganizationAuth> getOrganizationAuth();

    /**
     * 获取得奖认证信息
     * @return
     */
    List<RewardAuth> getRewardAuth();

    /**
     * 修改用户(组织和学生)认证状态
     */
    void updateUserAuth(String userId,String code);

    /**
     * 修改得奖认证状态
     */
    void updateRewardAuth(String id,String code);

}
