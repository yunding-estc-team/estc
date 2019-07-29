package com.competition.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * Configuration 标注配置类
 * EnableAspectJAutoProxy 启动切面
 * @author guohaodong
 */

@EnableAspectJAutoProxy
@MapperScan("com.competition.dao")
public class SpringConfig {
}


