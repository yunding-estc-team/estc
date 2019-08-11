package com.competition.dao;

import com.competition.response.CompetitionRewardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author GuoHaodong
 * @date 2019-0806 11:41:38
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCompetitionMapperTest {

	@Autowired
	UserCompetitionMapper userCompetitionMapper;
	@Test
	public void listRewardTest(){
		String hostId = "1";

		List<String> strings = userCompetitionMapper.listHost(hostId);
		log.info(strings.get(0));
		List<CompetitionRewardVO> rewardVOS = userCompetitionMapper.listReward(hostId,1,2);
		log.info(rewardVOS.get(0).toString());
	}
}
