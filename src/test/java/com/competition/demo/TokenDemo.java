package com.competition.demo;

import com.competition.VO.TokenObjectVO;
import com.competition.response.ReturnVO;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

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
		vo.setType("organization");
//		vo.setType("student");
//		vo.setType("admin");
		vo.setId("1");
		logger.info(vo.getId());
		String token = JwtHelper.generateToken(vo);
		logger.info(token);
		/**
		 * 解析token
		 */
		 TokenObjectVO parse = JwtHelper.getTokenInfo(token);
		logger.info(parse.getId());
		Claims claims = JwtHelper.parserToken(token);
		logger.info(claims.getExpiration().toString());
		claims.setExpiration(new Date(System.currentTimeMillis()+100000000000L*60*60));
		logger.info("设置过期时间成功");
		logger.info(claims.getExpiration().toString());
		logger.info("可以认为永久");

	}

//	/**
//	 * 获取token中数据示例
//	 */
//	@RequestMapping("/updatePassword")
//	public ReturnVO updatePassword(@RequestBody String password, @RequestHeader String authorization) {
		// 获取token中存储的ID
//		String userId = JwtHelper.parserToken(authorization).getId();
        // 获取token中储存的用户类型
//		String userType = JwtHelper.parserToken(authorization).getSubject();
//		return new ReturnVO();
//	}

}
