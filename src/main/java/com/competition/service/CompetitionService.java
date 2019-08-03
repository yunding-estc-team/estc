package com.competition.service;

import com.competition.entity.Competition;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GuoHaodong
 * @since 2019-08-02
 */
public interface CompetitionService extends IService<Competition> {
	/**
	 * 增加点击量
	 * @param competition
	 */
	public void addClick(Competition competition);
}
