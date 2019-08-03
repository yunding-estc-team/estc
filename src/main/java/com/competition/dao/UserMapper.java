package com.competition.dao;

import com.competition.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GuoHaodong
 * @since 2019-07-29
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
}
