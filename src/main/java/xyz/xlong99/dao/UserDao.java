package xyz.xlong99.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.xlong99.entity.Organization;
import xyz.xlong99.entity.Student;

import java.util.List;

/**
 * @author xlong
 * @date 2019-08-07 08:47
 */
public interface UserDao{

    /**
     * 获取学生用户列表(分页)
     * @return 返回学生的list集合
     */
    @Select(" SELECT portrait,introduction,user_name,realname,user_no,user_school,user_major,user_sex,user_birth,user_phone,user_email,is_active,ip_address,checkout,user_type" +
            " FROM `user`" +
            " WHERE user_type = 'student' ")
    List<Student> selectAllStudent();
    /**
     * 获取组织用户列表(分页)
     * @return 返回组织的list集合
     */
    @Select(" SELECT user_id,portrait,introduction,user_name,realname,user_school,user_phone,user_email,is_active,ip_address,checkout,user_type" +
            " FROM `user`" +
            " WHERE user_type = 'organization' ")
    List<Organization> selectAllOrganization();
    /**
     * 通过用户id修改学生用户资料
     */
    @Update(" UPDATE `user` " +
            "SET portrait=#{portrait},introduction=#{introduction},user_name=#{userName},realname=#{realname},user_no=#{userNo},user_school=#{userSchool},user_major=#{userMajor},user_sex=#{userSex},user_birth=#{userBirth},ip_address=#{ipAddress},checkout=#{checkout}" +
            " WHERE user_id = #{userId} ")
    void updateStudent(Student student);

    /**
     * 通过id修改组织用户资料
     */
    @Update(" UPDATE `user`" +
            " SET portrait=#{portrait},introduction=#{introduction},user_name=#{userName},realname=#{realname},user_school=#{userSchool},ip_address=#{ipAddress},checkout=#{checkout} " +
            " WHERE user_id = #{userId} ")
    void updateOrganization(Organization organization);

    /**
     * 用id查询用户
     * @param userId
     * @return
     */
    @Select(" SELECT is_active FROM `user` WHERE user_id = #{userId} ")
    String selectIsActive(String userId);

    @Update(" UPDATE `user` SET is_active=#{isActive}  WHERE user_id = #{userId}")
    void updateIsActive(String isActive,String userId);
}
