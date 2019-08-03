package com.competition.controller;


import com.competition.entity.Competition;
import com.competition.form.CompetitionPost;
import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
import com.competition.service.CompetitionService;
import com.competition.util.RankHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GuoHaodong
 * @since 2019-08-02
 * @date 2019-08-02 17:02:22
 */
@RestController
@Slf4j
public class CompetitionController {
	@Autowired
	CompetitionService competitionService;
	/**
	 * 获取赛事信息
	 */
	@GetMapping("competitionInfo")
	public ReturnVO getInfoById(String competitionId){
		log.info("获取赛事信息\n赛事id:"+competitionId);

		Competition competition = competitionService.getById(competitionId);

		return new ReturnVO(ReturnCode.SUCCESS,competition);
	}
	/**
	 * 获取赛事排行榜
	 */
	@GetMapping("competitionRank")
	public List<Competition> rank(List<Competition> competitionList){
		return RankHelper.listCompetitionRank(competitionList);
	}

	/**
	 * 发布赛事
	 */
	@PostMapping("competition")
	public ReturnVO postCompetition(Competition competition){
		competitionService.save(competition);
		return new ReturnVO(ReturnCode.SUCCESS);
	}


	/**
	 * 认领赛事
	 */
	@PostMapping("claim")
	public ReturnVO claim(){
		return new ReturnVO();
	}

	/**
	 * 赛事访问量获取
	 */
	@GetMapping("clickCount")
	public ReturnVO clickCount(@RequestBody CompetitionPost competitionPost){
		Competition competition = new Competition();
		competition.setClickCount(competitionService.getById(competitionPost.getId()).getClickCount());
		return new ReturnVO(ReturnCode.SUCCESS,competition);
	}

	/**
	 * 关注赛事
	 */
	@PutMapping("attention")
	public ReturnVO attention(@RequestBody CompetitionPost competitionPost){
		competitionService.addClick(new Competition().setCompetitionId(competitionPost.getId()));
		return new ReturnVO(ReturnCode.SUCCESS);
	}

	/**
	 * 赛事评分
	 */
	@PutMapping("star")
	public ReturnVO star(@RequestBody CompetitionPost competitionPost){
		competitionService.updateById(new Competition().setCompetitionId(competitionPost.getId()));
		return new ReturnVO(ReturnCode.SUCCESS);
	}

	/**
	 *
	 */
}

