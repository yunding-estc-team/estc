package com.competitionRank;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({com.competitionRank.config.SpringConfig.class,
		com.competitionRank.config.RedisConfig.class
		})
@MapperScan(value = "com.competitionRank.dao.mapper")
public class SpringbootDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}

}
