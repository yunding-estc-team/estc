package com.competition.demo;

import com.competition.util.ReturnCode;
import net.bytebuddy.asm.Advice;
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
public class EnumDemo {
	@Test
	public void enumDemo(){
		System.out.println(ReturnCode.SUCCESS);
		System.out.println(ReturnCode.SUCCESS.getDescription());
		System.out.println(ReturnCode.SUCCESS.ordinal());
	}
}
