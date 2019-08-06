package com.competition.service;

import com.competition.entity.UserCompetition;
import com.baomidou.mybatisplus.extension.service.IService;
import com.competition.response.CompetitionRewardVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GuoHaodong
 * @since 2019-08-02
 */
public interface UserCompetitionService extends IService<UserCompetition> {

	/**
	 * 获取特定主办方的举办赛事的获奖记录
	 */
	List<CompetitionRewardVO> listReward(String id);

}
