package xyz.xlong99.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.competition.entity.Competition;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.xlong99.entity.Student;

import java.util.List;

/**
 * @author xlong
 * @date 2019-08-07 08:47
 */
public interface UserDao extends BaseMapper<Competition> {

    /**
     * 获取学生用户列表(分页)
     * @return 返回学生的list集合
     */
    @Select("SELECT user_id,portrait,introduction,user_name,realname,user_no,user_school,user_major,user_sex,user_birth,user_phone,user_email,is_active,ip_address,checkout,createAt,updateAt,user_type" +
            " FROM `user`" +
            "WHERE user_type = 'student'")
    List<Student> getStudent();
    /**
     * 获取组织用户列表(分页)
     * @return 返回组织的list集合
     */
    @Select("SELECT user_id,portrait,introduction,user_name,realname,user_no,user_school,user_major,user_sex,user_birth,user_phone,user_email,is_active,ip_address,checkout,createAt,updateAt,user_type" +
            " FROM `user`" +
            "WHERE user_type = 'organization'")
    List<Student> getOrganization();
    /**
     * 通过用户id修改学生用户资料
     */
    @Update("")
    void updateStudent();

    /**
     * 通过id修改组织用户资料
     */
    @Update("")
    void updateOrganization();
}
