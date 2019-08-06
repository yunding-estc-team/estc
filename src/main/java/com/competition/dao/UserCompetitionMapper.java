package com.competition.dao;

import com.competition.entity.UserCompetition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.competition.form.UserRewardForm;
import com.competition.response.CompetitionRewardVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GuoHaodong
 * @since 2019-08-02
 */
public interface UserCompetitionMapper extends BaseMapper<UserCompetition> {

	/**
	 * 查询主办方所举办的赛事的获奖情况
	 * @param host 主办方id
	 * @return
	 */
	@Select("SELECT uc.competition_id ,uc.reward ,c.name ,u.realname FROM user_competition uc " +
			"inner join competition c on uc.competition_id = c.competition_id " +
			"inner join user u on uc.user_id = u.user_id " +
			"WHERE uc.competition_id in " +
				"(SELECT competition_id " +
				"FROM competition " +
				"WHERE host =#{host})")
	List<CompetitionRewardVO> listReward(String host);

	@Select("select competition_id from competition where host=#{host}")
	public List<String> listHost(String host);

}
