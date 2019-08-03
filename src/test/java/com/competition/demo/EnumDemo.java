package com.competition.demo;

import com.competition.response.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author GuoHaodong
 * @date 2019-07-30 09:46:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EnumDemo {
	@Test
	public void enumDemo(){
		log.debug("haha");
		log.info("信息");
		log.warn("警告");
		log.error("错误");
		System.out.println(ReturnCode.SUCCESS);
		System.out.println(ReturnCode.SUCCESS.getDescription());
		System.out.println(ReturnCode.SUCCESS.ordinal());
	}
}
