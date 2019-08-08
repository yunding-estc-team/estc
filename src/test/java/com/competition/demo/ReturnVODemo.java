package com.competition.demo;

import com.competition.response.ReturnVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author GuoHaodong
 * @date 2019-0804 20:59:17
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReturnVODemo {
	@Test
	public void returnVo() {
		log.info(ReturnVO.success().getMsg());
	}
}
