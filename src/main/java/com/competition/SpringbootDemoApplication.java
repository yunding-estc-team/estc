package com.competition;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author AsparagusFern
 */
@SpringBootApplication
@Import({com.competition.config.SpringConfig.class,
		com.competition.config.RedisConfig.class
		})
public class SpringbootDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}

}
