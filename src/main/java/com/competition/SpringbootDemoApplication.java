package com.competition;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;

/**
 * @author AsparagusFern
 */
@SpringBootApplication(scanBasePackages = {"com.competition","xyz.xlong99"})
@Import({com.competition.config.SpringConfig.class,
		com.competition.config.RedisConfig.class,
		com.competition.config.MybatisPlusConfig.class,
		com.competition.config.ShiroConfig.class,
		com.competition.config.SwaggerConfig.class
		})
public class SpringbootDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}

}
