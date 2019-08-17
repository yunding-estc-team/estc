//package com.competition;
//
//import com.baomidou.mybatisplus.core.conditions.Wrapper;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import com.competition.dao.CompetitionMapper;
//import com.competition.entity.Competition;
//import com.competition.entity.CompetitionWiki;
//import com.competition.service.CompetitionService;
//import com.competition.service.CompetitionWikiService;
//import com.competition.response.ReturnCode;
//import com.competition.util.JwtHelper;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Date;
//
//import static org.assertj.core.api.Assertions.assertThat;
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SpringbootDemoApplicationTests {
//    private static final Logger logger = LoggerFactory.getLogger(SpringbootDemoApplicationTests.class);
//	@Test
//	public void contextLoads() {
//		System.out.println("hello");
////		userMapper.getUserById("1");
//        logger.info("信息");
//        logger.debug("调试");
//        logger.error("错误");
//        logger.warn("警告");
//	}
//	@Test
//	public void selectById(){
//		System.out.println("1");
//	}
////	@Autowired
////	CompetitionMapper mapper;
//	@Autowired
//	CompetitionService service;
//
//	@Test
//	public void entityT(){
//		CompetitionWiki competitionWiki = new CompetitionWiki();
//		competitionWiki.setWikiId("1234");
//		competitionWiki.setContent("weizhi");
////		competitionWiki.insert();
//		logger.warn(competitionWiki.selectById().toString());
//
//	}
//	@Autowired
//	CompetitionWikiService competitionWikiService;
//	@Test
//	public void serviceT(){
//		CompetitionWiki competitionWiki = new CompetitionWiki();
//		competitionWiki.setWikiId("1234");
//		competitionWiki.setContent("weizhi");
//
//		String s = competitionWikiService.getById(1234).toString();
//
//		logger.debug("s" );
//		logger.info("dfa");
//
//
//
//	}
//
//
//	@Autowired
//	CompetitionService competitionService;
//	@Test
//	public void insertT(){
////		mapper.selectList(Wrappers.<Competition>lambdaQuery().select(Competition::getCompetitionId))
////					.forEach(x->{
////						assertThat(x.getCompetitionId()).isNotNull();
////					});
////		competitionService.getOne(Wrappers.<Competition>lambdaQuery().eq(Competition::getClickCount,1));
////		mapper.selectOne(Wrappers.<Competition>lambdaQuery().select(Competition::getCompetitionId));
////		System.out.println(
////				mapper.selectOne(Wrappers.<Competition>lambdaQuery().select(Competition::getCompetitionId,Competition::getClickCount))
////		);
//	}
//	@Test
//	public void selectT(){
//		Competition competition = new Competition();
//
//	}
//
//
//
//	@Test
//	public void testAOP() {
//	}
//	@Autowired
//	RedisTemplate<String,String> redisTemplate;
//
//	@Test
//	public void testReturncode(){
//		ReturnCode.SUCCESS.getDescription();
//	}
//
//	@Test
//	public void hashCodeT(){
//		String s = "2018005644";
//		System.out.println(s.hashCode());
//		String y = "2018005643";
//		System.out.println(y.hashCode()+"\n"+s.hashCode());
//	}
//	@Test
//	public void competitionC(){
//<<<<<<< HEAD
////		List<Organization> organizations = userService1.findAllOrganization();
////		System.out.println("organizations = " + organizations);
//=======
//		Competition competition = competitionService.getById("1800bb87da81438ab982c0416631054c");
//>>>>>>> origin/master
//	}
//
//
//}
