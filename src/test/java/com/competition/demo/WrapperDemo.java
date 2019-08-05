package com.competition.demo;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.competition.dao.CompetitionWikiMapper;
import com.competition.entity.CompetitionWiki;
import com.competition.service.CompetitionWikiService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*import com.competition.dao.CompetitionWikiMapper;
import com.competition.entity.CompetitionWiki;
import com.competition.service.CompetitionWikiService;*/

/**
 * @author GuoHaodong
 * @date 2019-0801 09:27:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WrapperDemo {
	@Autowired
	CompetitionWikiService competitionWikiService;
	@Autowired
	CompetitionWikiMapper competitionWikiMapper;
	@Test
	public void setCompetitionWikiServiceT(){
		CompetitionWiki competitionWiki = new CompetitionWiki();
		competitionWiki.setWikiId("");
//		competitionWiki.setContent("测试内容");
		competitionWiki.insert();
		competitionWikiService.save(competitionWiki);
		competitionWikiMapper.insert(competitionWiki);

	}
	@Test
	public void updateT(){
		CompetitionWiki competitionWiki = new CompetitionWiki();
		competitionWiki.setWikiId("134");

		competitionWiki.insertOrUpdate();
//		competitionWikiService.getById();
		competitionWikiMapper.updateById(competitionWiki);
		competitionWikiMapper.update(competitionWiki,Wrappers.<CompetitionWiki>lambdaQuery().eq(CompetitionWiki::getWikiId,1234));
	}
}
