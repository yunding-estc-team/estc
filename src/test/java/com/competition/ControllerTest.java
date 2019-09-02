package com.competition;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.competition.entity.Competition;
import com.competition.entity.User;
import com.competition.entity.UserAttention;
import com.competition.response.CompetitionRewardVO;
import com.competition.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

/**
 * @author GuoHaodong
 * @date 2019-0807 09:24:39
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {
	@Autowired
	UserCompetitionService userCompetitionService;
	@Autowired
	CompetitionService competitionService;


	@Test
	public void listReward(){
		List<CompetitionRewardVO> list = userCompetitionService.listReward("1",0,1);
	}


	// 计算hot
	@Autowired
	UserAttentionService userAttentionService;
	@Test
	public void competitionHistory(){
		competitionService.list(Wrappers.<Competition>lambdaQuery().ne(Competition::getName,null)).forEach(competition -> {
			//关注
			double attention = 0;
			try {
				attention = userAttentionService.count(Wrappers.<UserAttention>lambdaQuery()
						.eq(UserAttention::getCompetitionId,competition.getCompetitionId())
				);
			} catch (Exception ignore) {
			}
			//点击量
			double click = competition.getClickCount();

			//保研率
			double rgr = competition.getRgr();

			Double hot =rgr*0.5+click*0.1+attention*0.3;

			String s = hot.toString().substring(0,hot.toString().indexOf('.'));
			log.info(s);
			competition.setHot(Integer.valueOf(s));
			competitionService.updateById(competition);
		});
	}

	// 生成id
	@Autowired
	private UserService userService;
	@Test
	public void generateCompetitionUUID(){
		User user = new User();
		for (int i=0;i<19;i++){
			user.setUserId(UUID.randomUUID().toString());
			userService.save(user);
		}
	}
	@Autowired
	CompetitionCheckoutService userCompetition;
	@Test
	public void competitionCheckout(){
			List list1 = userCompetitionService.list();
			List list = userCompetition.list();
	}
}
