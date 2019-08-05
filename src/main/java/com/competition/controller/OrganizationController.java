package com.competition.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.competition.entity.Competition;
import com.competition.entity.User;
import com.competition.entity.UserCompetition;
import com.competition.form.CompetitionForm;
import com.competition.form.PageForm;
import com.competition.form.UserRewardForm;
import com.competition.response.ReturnVO;
import com.competition.service.CompetitionService;
import com.competition.service.UserCompetitionService;
import com.competition.util.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	public ReturnVO laureateInfo(@RequestBody String authorization, @RequestBody PageForm pageForm) {
		String id = JwtHelper.getTokenInfo(authorization).getId();

		log.info("获取id:"+id+"所主办赛事获奖信息");

		IPage<Competition> competitionPage = competitionService.page(
				new Page<Competition>(pageForm.getPageCurrent(),pageForm.getPageSize()),
					Wrappers.<Competition>lambdaQuery()
						.select(Competition::getName,Competition::getCompetitionId)
						.eq(Competition::getHost,id));

	List<Competition> list = competitionService.list(Wrappers.<Competition>lambdaQuery()
				.select(Competition::getCompetitionId,Competition::getName)
				.eq(Competition::getHost,id));
		List<UserRewardForm> formList = new ArrayList<>(5);
		list.forEach((x)->{

		});

		return ReturnVO.success(

		);
	}



	/**
	 * 回答问题
	 */

	/**
	 * 用户认证(资质上传)
	 */

	/**
	 * 提供赛事信息
	 */
	@PostMapping("competition")
	public ReturnVO competitionInfo(@RequestBody CompetitionForm competitionForm, @RequestHeader String authorization){
		return new ReturnVO();
	}

	/**
	 * 更新赛事信息
	 */


}
