package com.competition;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.competition.entity.Competition;
import com.competition.entity.CompetitionCheckout;
import com.competition.entity.UserCompetition;
import com.competition.response.CompetitionRewardVO;
import com.competition.service.CompetitionCheckoutService;
import com.competition.service.CompetitionService;
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


	@Test
	public void competitionHistory(){
		IPage competitionList = competitionService.
				page(new Page<Competition>(1,1), Wrappers.<Competition>lambdaQuery()
						.select(Competition::getName)
						.eq(Competition::getHost,1));
		log.info(competitionList.toString());
	}

	@Autowired
	CompetitionCheckoutService userCompetition;
	@Test
	public void competitionCheckout(){
			List list1 = userCompetitionService.list();
			List list = userCompetition.list();
	}
}
