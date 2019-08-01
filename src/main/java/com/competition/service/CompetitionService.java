package com.competition.service;

import com.competition.entity.Competition;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GuoHaodong
 * @since 2019-07-29
 */
public interface CompetitionService extends IService<Competition> {
	/**
	 * 获取赛事信息
	 * @param competition 有id属性的competition对象
	 * @return 根据id获取到的赛事详情
	 */
	public Competition getCompetitionInfo(Competition competition);

	/**
	 * 获取赛事排行
	 * @return
	 */
	public List<Competition> listCompetitionRank();
}
