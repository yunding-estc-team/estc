package com.competition.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.competition.entity.Competition;
import com.competition.entity.User;
import com.competition.entity.UserCompetition;
import com.competition.form.*;
import com.competition.response.ReturnVO;
import com.competition.service.*;
import com.competition.util.JwtHelper;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author GuoHaodong
 * @date 2019-0804 16:37:31
 */

@RestController
@Slf4j
@RequestMapping("/organization")
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
	 * @param userCompetition 获奖者的学校,学号,奖项名称
	 */
	@ApiParam
	@PutMapping("laureate")
	public ReturnVO laureate(@RequestBody UserCompetition userCompetition,@RequestHeader String authorization){

		userCompetition.setId(null);

		userCompetitionService
				.update(Wrappers.<UserCompetition>lambdaUpdate()
								.eq(UserCompetition::getCompetitionId,userCompetition.getCompetitionId())
								.eq(UserCompetition::getUserId,userCompetition.getUserId())
								.set(UserCompetition::getCheckout,1)
								.set(UserCompetition::getReward,userCompetition.getReward()));
		return ReturnVO.success();
	}

	/**
	 * 获取所主办赛事信息列表
	 * @param authorization token字符串
	 */
	@GetMapping("/history/pageSize/current")
	public ReturnVO history(@RequestHeader String authorization,@PathVariable int current,@PathVariable int pageSize){
		String id = JwtHelper.getTokenInfo(authorization).getId();
		log.info("获取id:"+id+"主办方的历史主办赛事信息");
		return ReturnVO.success(
				competitionService.
						page(new Page<>(current+1,pageSize),Wrappers.<Competition>lambdaQuery()
								.select(Competition::getName)
								.eq(Competition::getHost,id)).getRecords()
		);

	}

	/**
	 * 组织所主办赛事获奖信息
	 * @param authorization token
	 */
	@GetMapping("/laureateInfo/pageSize/current")
	public ReturnVO laureateInfo(@RequestHeader String authorization, @PathVariable int pageSize,@PathVariable int current) {
		String id = JwtHelper.getTokenInfo(authorization).getId();

		log.info("获取id:"+id+"所主办赛事获奖信息");

		return ReturnVO.success(
			userCompetitionService.listReward(id,current,pageSize	)
		);
	}

	/**
	 * 用户认证(资质上传)
	 * @param authorization token
	 * @param fileForm 文件路径,hash值
	 */
	@PostMapping("checkout")
	public ReturnVO checkout(@RequestHeader String authorization, @RequestBody FileForm fileForm){

		// 解析token
		String userId = JwtHelper.getTokenInfo(authorization).getId();

		log.info("用户资质上传---id:"+userId);

		//存入数据
		User user = new User();
		user.setUserId(userId);
		user.setFile(fileForm.getPath());
		user.setHash(fileForm.getHash());

		log.info("用户资质上传信息保存");
		log.info(user.toString());

		userService.updateById(user);
		return ReturnVO.success();
	}

	/**
	 * 提供赛事信息
	 * @param authorization token
	 * @param competitionForm 赛事信息
	 */
	@PostMapping("competition")
	public ReturnVO competitionInfo(@RequestBody CompetitionForm competitionForm, @RequestHeader String authorization){

		//解析token
		String userId = JwtHelper.getTokenInfo(authorization).getId();

		log.info("组织提供赛事信息----id:"+userId);

		//存储信息
		//转换为competitionEntity对象
		Competition competition = competitionForm.toEntity();
		CompetitionForm form = new CompetitionForm();
		form.fromEntity(competition);
		competition.setCompetitionId(UUID.randomUUID().toString());

		log.info("保存赛事信息");
		log.info(competition.toString());
		competitionService.save(competition);

		return ReturnVO.success();
	}

	/**
	 * 更新赛事信息
	 */
	@PutMapping("/competition")
	public ReturnVO updateCompetition(@RequestBody CompetitionForm competitionForm,@RequestHeader String authorization) {

		//解析token
		String userId = JwtHelper.getTokenInfo(authorization).getId();
		log.info("更新赛事信息---userId:"+userId);

		//更新信息
		competitionService.updateById(competitionForm.toEntity());
		return ReturnVO.success();
	}


}
