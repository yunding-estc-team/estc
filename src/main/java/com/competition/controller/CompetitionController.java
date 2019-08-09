package com.competition.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.competition.entity.Competition;
import com.competition.entity.CompetitionCheckout;
import com.competition.entity.UserAttention;
import com.competition.form.CompetitionForm;
import com.competition.form.FileForm;
import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
import com.competition.service.CompetitionService;
import com.competition.service.UserAttentionService;
import com.competition.util.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
@RequestMapping("/competition")
public class CompetitionController {
	@Autowired
	CompetitionService competitionService;

	/**
	 * 获取赛事信息
	 */
	@GetMapping("info")
	public ReturnVO getInfoById(@RequestBody CompetitionForm competitionForm){
		log.info("获取赛事信息\n赛事id:"+ competitionForm.getId());

		Competition competition = competitionService.getById(competitionForm.getId());
		competitionService.addClick(competition);
		log.info(competition.getCompetitionId());
		log.info("访问量加1");
		return new ReturnVO(ReturnCode.SUCCESS,competition);
	}

	/**
	 * 获取赛事排行榜
	 */
	@GetMapping("rank")
	public ReturnVO rank(Page<Competition> page){
		log.info("获取排行榜");
		IPage iPage = competitionService.page(page,Wrappers.<Competition>lambdaQuery()
				.select(Competition::getHot)
				.orderByDesc(Competition::getHot));
		log.info("分页信息"+iPage.getRecords().toString());
		return new ReturnVO(ReturnCode.SUCCESS,iPage);
	}

	/**
	 * 排行榜源数据获取
	 */
	@GetMapping("data/{id}")
	public ReturnVO data(@PathVariable String id) {
		//todo
		// 热度,报名链接点击次数,升学辅助,点击量,主办方资质(能力),
		return ReturnVO.success();
	}

	/**
	 * 发布赛事
	 */
	@PostMapping("new")
	public ReturnVO postCompetition(Competition competition){
		competitionService.save(competition);
		return new ReturnVO(ReturnCode.SUCCESS);
	}


	/**
	 * 认领赛事
	 */
	@PostMapping("claim")
	public ReturnVO claim(@RequestBody FileForm fileForm,@RequestHeader String authorization){
		String userId = JwtHelper.getTokenInfo(authorization).getId();
		CompetitionCheckout checkout = new CompetitionCheckout();

		checkout.setCompetitionId(fileForm.getId());
		checkout.setFile(fileForm.getPath());
		checkout.setHash(fileForm.getHash());
		checkout.setUserId(userId);
		checkout.setDescription(fileForm.getDescription());
		checkout.setId(UUID.randomUUID().toString());

		return new ReturnVO();
	}

	/**
	 * 赛事访问量获取
	 */
	@GetMapping("clickCount")
	public ReturnVO clickCount(@RequestBody CompetitionForm competitionForm){
		Competition competition = new Competition();
		competition.setClickCount(competitionService.getById(competitionForm.getId()).getClickCount());
		return new ReturnVO(ReturnCode.SUCCESS,competition);
	}

	/**
	 * 关注赛事
	 */
	@Autowired
	UserAttentionService userAttentionService;
	@Autowired
	UserAttention userAttention;
	@PutMapping("attention")
	public ReturnVO attention(@RequestBody CompetitionForm competitionForm, @RequestHeader String authorization){
		//解析token,并设置userAttention
		userAttention.setUserId(JwtHelper.parserToken(authorization).getId());
		userAttention.setCompetitionId(competitionForm.getId());
		userAttention.setId(String.valueOf(UUID.randomUUID()));

		//保存用户关注
		userAttentionService.save(userAttention);
		return new ReturnVO(ReturnCode.SUCCESS);
	}


	/**
	 * 报名链接(计数器)
	 */
	@GetMapping("joinLink")
	public ReturnVO joinLink(@RequestBody CompetitionForm competitionForm){
		Competition competition = competitionService.getOne(Wrappers.<Competition>query().lambda()
				.select(Competition::getJoinLink)
				.eq(Competition::getCompetitionId, competitionForm.getId()));
		return new ReturnVO(ReturnCode.SUCCESS,competition);
	}

	/**
	 * 获取赛事的评分(通过算法得到)
	 */
	@GetMapping("hot")
	public ReturnVO hot(@RequestBody CompetitionForm competitionForm){
		return new ReturnVO(ReturnCode.SUCCESS,
		 competitionService.getOne(Wrappers.<Competition>lambdaQuery()
				 .select(Competition::getHot)
				 .eq(Competition::getCompetitionId, competitionForm.getId()))
		);
	}

	/**
	 * 获取历史问答
	 */
}

