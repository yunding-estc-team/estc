package com.competition.dao;

import com.competition.entity.Competition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GuoHaodong
 * @since 2019-08-02
 */
public interface CompetitionMapper extends BaseMapper<Competition> {

	/**
	 * 更新点击量
	 * @param competition
	 */
	@Update("UPDATE competition SET click_count = click_count+1 WHERE competition_id=#{competitionId} ")
	void addClick(Competition competition);

}
