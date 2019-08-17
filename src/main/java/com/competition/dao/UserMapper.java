package com.competition.dao;

import com.competition.entity.Competition;
import com.competition.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.competition.form.SearchPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GuoHaodong
 * @since 2019-08-02
 */
//@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 通过手机号查询用户
     * @param phoneNumber
     * @return
     */
    @Select("select * from user where user_phone = #{phoneNumber}")
    User findByPhoneNumber(String phoneNumber);

    /**
     * 通过邮箱查询用户
     * @param email
     * @return
     */
    @Select("select * from user where user_email = #{email}")
    User findByEmail(String email);

    /**
     * 通过用户输入的信息模糊查询个人名称
     *
     * @param name
     */
    @Select("select user_name from user where user_name like CONCAT('%',#{name},'%')"+
//            "ORDER BY createAt DESC " +
            " LIMIT #{aParam}, #{pageSize};")
    List<String> searchUserName(String name,Integer aParam, Integer pageSize);

    @Select("select name from competition where name like CONCAT('%',#{name},'%')"+
//            "ORDER BY createAt DESC " +
            " LIMIT #{aParam}, #{pageSize};")
    List<String> searchCompetitionName(String name,Integer aParam, Integer pageSize );
    /**
     * 通过用户输入的信息模糊搜索
     */
    @Select("select * from user WHERE 1=1 and user_name like CONCAT('%',#{name},'%') and user_type = #{type}"+
//            "ORDER BY createAt DESC " +
            "LIMIT #{aParam}, #{pageSize};")
    List<SearchPost>  searchAllStd(String name,String type,Integer aParam,Integer pageSize);

    @Select("select * from user WHERE 1=1 and user_name like CONCAT('%',#{name},'%') and user_type = #{type}"+
//            "ORDER BY createAt DESC " +
            "LIMIT #{aParam}, #{pageSize};")
    List<SearchPost> searchAllOrg(String name,String type,Integer aParam,Integer pageSize);


    @Select("select * from competition WHERE 1=1 and name like CONCAT('%',#{name},'%')"+
//            "ORDER BY createAt DESC " +
            "LIMIT #{aParam}, #{pageSize};")
    List<SearchPost> searchAllCompetition(String name,Integer aParam, Integer pageSize);

}
