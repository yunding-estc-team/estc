package com.competition;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.competition.entity.Competition;
import com.competition.entity.UserAttention;
import com.competition.response.CompetitionRewardVO;
import com.competition.service.CompetitionCheckoutService;
import com.competition.service.CompetitionService;
import com.competition.service.UserAttentionService;
import com.competition.service.UserCompetitionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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


	@Autowired
	UserAttentionService userAttentionService;
	@Test
	public void competitionHistory(){
		competitionService.list().forEach(competition -> {
			//关注
			double attention = userAttentionService.count(Wrappers.<UserAttention>lambdaQuery()
					.eq(UserAttention::getCompetitionId,competition.getCompetitionId())
			);
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

	@Autowired
	CompetitionCheckoutService userCompetition;
	@Test
	public void competitionCheckout(){
			List list1 = userCompetitionService.list();
			List list = userCompetition.list();
	}
}
