package com.competition.demo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
/*import com.competition.dao.CompetitionWikiMapper;
import com.competition.entity.CompetitionWiki;
import com.competition.service.CompetitionWikiService;*/
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.competition.dao.CompetitionWikiMapper;
import com.competition.entity.Competition;
import com.competition.entity.CompetitionWiki;
import com.competition.entity.User;
import com.competition.entity.UserCompetition;
import com.competition.form.CompetitionForm;
import com.competition.form.PageForm;
import com.competition.service.CompetitionService;
import com.competition.service.CompetitionWikiService;
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
	@Autowired
	CompetitionService competitionService;
	@Test
	public void selectT(){
		Competition competition = competitionService.getOne(Wrappers.<Competition>query().lambda()
			.select(Competition::getJoinLink)
			.eq(Competition::getCompetitionId, "1234"));
		log.info(competition.getJoinLink());

	}
	@Autowired
	UserCompetitionService userCompetitionService;
	@Test
	public void selectIn(){
		PageForm pageForm= new PageForm();
		pageForm.setPageCurrent(1);
		pageForm.setPageSize(5);
		log.info(competitionService.list().toString());

		Page<Competition> page = new Page<Competition>(pageForm.getPageCurrent(),pageForm.getPageSize());

		//子查询
		List<UserCompetition> userCompetition = userCompetitionService.list(Wrappers.<UserCompetition>lambdaQuery()
				.in(UserCompetition::getCompetitionId,
						competitionService.list(Wrappers.<Competition>lambdaQuery()
								.select(Competition::getCompetitionId))));
		log.info(String.valueOf(userCompetition.size()));
	}
}
