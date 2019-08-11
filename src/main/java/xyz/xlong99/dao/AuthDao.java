package xyz.xlong99.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import xyz.xlong99.entity.OrganizationAuth;
import xyz.xlong99.entity.RewardAuth;
import xyz.xlong99.entity.StudentAuth;

import java.util.List;

/**
 * @author xlong
 * @date 2019-08-08 10:46
 */
@Mapper
@Repository
public interface AuthDao {
    /**
     * 获取学生认证申请
     * @return
     */
    @Select("SELECT user_id,realname,user_no,user_school,user_major,user_sex,file,`hash`,checkout " +
            "FROM `user` " +
            "WHERE user_type = 'student' AND checkout = '3' ORDER " +
            "BY updateAt ASC")
    List<StudentAuth> getStudentAuth();
    /**
     * 获取组织认证申请
     * @return
     */
    @Select("SELECT user_id,user_name,user_school,user_major,file,`hash`,checkout " +
            "FROM `user` "+
            "WHERE user_type = 'organization' AND checkout = '3' " +
            "BY updateAt ASC")
    List<OrganizationAuth> getOrganizationAuth();

    /**
     * 获取得奖认证申请
     * @return
     */
    @Select("SELECT uc.id,uc.competition_id,c.`name`,uc.user_id,u.realname,u.user_school,uc.certificate,uc.`hash`,uc.checkout " +
            "FROM user_competition uc INNER JOIN competition c ON uc.competition_id = c.competition_id " +
            "INNER JOIN `user` u ON uc.user_id=u.user_id WHERE uc.checkout = '3' " +
            "ORDER BY uc.createAt ASC")
    List<RewardAuth> getRewardAuth();

    /**
     * 更新用户认证状态
     * @param userId
     * @param code
     */
    @Update("UPDATE `user` SET checkout = #{code} WHERE user_id = #{userId}")
    void updateUserAuth(String userId,String code);

    /**
     * 更新得奖认证状态
     * @param id
     * @param code
     */
    @Update("UPDATE user_competition SET checkout = #{code} WHERE id = #{userId}")
    void updateRewardAuth(String id,String code);

}
