package com.competition.config;

import com.competition.entity.UserAttention;
import com.competition.response.ReturnVO;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * Configuration 标注配置类
 * EnableAspectJAutoProxy 启动切面
 * @author guohaodong
 */

@EnableAspectJAutoProxy
@MapperScan(basePackages = {"com.competition.dao","xyz.xlong99.dao"})

public class SpringConfig {
	@Bean
	public UserAttention userAttention(){
		return new UserAttention();
	}
	@Bean
	public ReturnVO returnVO(){
		return new ReturnVO();
	}
}


