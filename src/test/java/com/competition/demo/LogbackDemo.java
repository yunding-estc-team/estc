package com.competition.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * logback 示例程序
 *
 * 1. 注意是slf4j的Logger
 * 2. getLogger()一般是当前类的字节码
 * 3. 静态方法无需织入
 * @author AsparagusFern
 * @date 2019/7/29 下午5:00
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogbackDemo {
	Logger logger = LoggerFactory.getLogger(LogbackDemo.class);
	@Test
	public void test1(){
		logger.debug("调试");
		logger.error("错误");
		logger.warn("警告");
	}
}
