package com.competition;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemoApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(SpringbootDemoApplicationTests.class);
	@Test
	public void contextLoads() {
		System.out.println("hello");
//		userMapper.getUserById("1");
        logger.info("信息");
        logger.debug("调试");
        logger.error("错误");
        logger.warn("警告");
	}
	@Test
	public void selectById(){
	}


	@Test
	public void testAOP() {
	}
	@Autowired
	RedisTemplate<String,String> redisTemplate;


}
