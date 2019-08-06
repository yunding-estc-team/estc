package com.competition.demo;

import com.competition.VO.TokenObjectVO;
import com.competition.util.JwtHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import net.bytebuddy.description.ByteCodeElement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

/**
 * @author GuoHaodong
 * @date 2019-0731 14:58:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenDemo {
	Logger logger = LoggerFactory.getLogger(TokenDemo.class);
	@Test
	public void tokenGenerator(){

		/**
		 * 生成token
		 */
		TokenObjectVO vo = new TokenObjectVO();
		vo.setType("admin");
		vo.setId("1");
		logger.info(vo.getId());
		String token = JwtHelper.generateToken(vo);
		logger.info(token);
		/**
		 * 解析token
		 */
//		 TokenObjectVO parse = JwtHelper.getTokenInfo(token);
//		logger.info(parse.getId());
//		Claims claims = JwtHelper.parserToken(token);
//		logger.info(claims.getExpiration().toLocaleString());
//		claims.setExpiration(new Date(System.currentTimeMillis()+10000*60*60));
//		logger.info("设置过期时间成功");
//		logger.info(claims.getExpiration().toLocaleString());
		/**
		 *eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1NjUwNTY2OTQsImV4cCI6MTU2NTA2MDI5NH0.ebyiS-kkE-CJAqYaK30coB6lCUEt0GOLaxB7QyesmFQ
		 *eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1NjUwNTY2OTQsImV4cCI6MTU2NTA2MDI5NH0.ebyiS-kkE-CJAqYaK30coB6lCUEt0GOLaxB7QyesmFQ
 		 */
	}
}
