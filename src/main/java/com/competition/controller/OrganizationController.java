package com.competition.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.competition.entity.Competition;
import com.competition.entity.CompetitionWikiReply;
import com.competition.entity.User;
import com.competition.entity.UserCompetition;
import com.competition.form.*;
import com.competition.response.ReturnVO;
import com.competition.service.*;
import com.competition.util.JwtHelper;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author GuoHaodong
 * @date 2019-0804 16:37:31
 */

@RestController
@Slf4j
@RequestMapping("organization")
public class OrganizationController {

	@Autowired
	CompetitionService competitionService;
	@Autowired
	UserCompetitionService userCompetitionService;
	@Autowired
	CompetitionWikiReplyService competitionWikiReplyService;
	@Autowired
	UserService userService;
	/**
	 * 设置获奖用户
	 * @param userRewardForm 获奖者的学校,学号,奖项名称
	 */
	//todo 非空检验
	@PutMapping("laureate")
	public ReturnVO laureate(@RequestBody UserRewardForm userRewardForm){
		userCompetitionService.update(Wrappers.<UserCompetition>lambdaUpdate((UserCompetition) userRewardForm));
		return ReturnVO.success();
	}

	/**
	 * 获取所主办赛事信息
	 * @param authorization token字符串
	 */
	@GetMapping("history")
	public ReturnVO history(@RequestHeader String authorization){
		String id = JwtHelper.getTokenInfo(authorization).getId();
		log.info("获取id:"+id+"主办方的历史主办赛事信息");
		return ReturnVO.success(
				competitionService.list(Wrappers.<Competition>lambdaQuery().select(Competition::getName).eq(Competition::getHost,id))
		);

	}

	/**
	 * 组织所主办赛事获奖信息
	 * @param authorization token
	 * @param pageForm 当前页,分页大小
	 */
	//todo 分页
	@GetMapping("/laureateInfo")
	public ReturnVO laureateInfo(@RequestHeader String authorization, @RequestBody PageForm pageForm) {
		String id = JwtHelper.getTokenInfo(authorization).getId();

		log.info("获取id:"+id+"所主办赛事获奖信息");

		return ReturnVO.success(
			userCompetitionService.listReward(id)
		);
	}



	/**
	 * 回答问题
	 */
	@PutMapping("/wiki")
	public ReturnVO putWiki(@RequestHeader String authorization ,@RequestBody WikiForm wikiForm){

		//解析token
		String userId = JwtHelper.getTokenInfo(authorization).getId();

		CompetitionWikiReply reply = new CompetitionWikiReply();
		reply.setUserId(userId);
		reply.setCompetitionWikiId(wikiForm.getWikiId());
		reply.setContent(wikiForm.getReply());
		reply.setId(UUID.randomUUID().toString());

		return ReturnVO.success(competitionWikiReplyService.save(reply));
	}

	/**
	 * 用户认证(资质上传)
	 */
	@PostMapping("checkout")
	public ReturnVO checkout(@RequestHeader String authorization, @RequestBody FileForm fileForm){

		// 解析token
		String userId = JwtHelper.getTokenInfo(authorization).getId();

		//存入数据
		User user = new User();
		user.setUserId(userId);
		user.setFile(fileForm.getPath());
		user.setHash(fileForm.getHash());

		userService.save(user);
		return ReturnVO.success();
	}

	/**
	 * 提供赛事信息
	 */
	@PostMapping("competition")
	public ReturnVO competitionInfo(@RequestBody CompetitionForm competitionForm, @RequestHeader String authorization){

		//解析token
		String userId = JwtHelper.getTokenInfo(authorization).getId();

		//存储信息
		//todo 方法替换
		Competition competition = new Competition();
		competition.setName(competitionForm.getName());
		competition.setCover(competitionForm.getCover());
		competition.setContent(competitionForm.getContent());
		competition.setHost(userId);
		competition.setIntroduce(competition.getIntroduce());
		competition.setCompetitionId(UUID.randomUUID().toString());
		return new ReturnVO();
	}

	/**
	 * 更新赛事信息
	 */
	@PutMapping("competition")
	public ReturnVO updataCompetition(@RequestBody CompetitionForm competitionForm,@RequestHeader String authorization) {

		//解析token
		String userId = JwtHelper.getTokenInfo(authorization).getId();

		//更新信息
		//TODO 方法替换

		return ReturnVO.success();
	}


}
