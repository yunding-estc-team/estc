package com.competition.service.impl;

import com.competition.entity.UserCompetition;
import com.competition.dao.UserCompetitionMapper;
import com.competition.response.CompetitionRewardVO;
import com.competition.service.UserCompetitionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GuoHaodong
 * @since 2019-08-02
 */
@Service
public class UserCompetitionServiceImpl extends ServiceImpl<UserCompetitionMapper, UserCompetition> implements UserCompetitionService {
	@Autowired
	UserCompetitionMapper userCompetitionMapper;

	/**
	 * 列出特定主办方的赛事获奖信息
	 */
	@Override
	public List<CompetitionRewardVO> listReward(String id){
		return userCompetitionMapper.listReward(id);
	}

}
