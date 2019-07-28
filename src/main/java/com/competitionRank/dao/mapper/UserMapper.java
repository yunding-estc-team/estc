package com.competitionRank.dao.mapper;

import com.competitionRank.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;

import java.util.List;


public interface UserMapper {
    @Select("SELECT * FROM user  WHERE user_id=#{userId}")
    public UserDO getUserById(String userId);
}
