package com.competition.service.impl;

import com.competition.entity.Competition;
import com.competition.dao.CompetitionMapper;
import com.competition.service.CompetitionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GuoHaodong
 * @since 2019-07-29
 */
@Service
public class CompetitionServiceImpl extends ServiceImpl<CompetitionMapper, Competition> implements CompetitionService {

	@Override
	public Competition getCompetitionInfo(Competition competition) {
		return null;
	}

	@Override
	public List<Competition> listCompetitionRank() {
		return null;
	}
}
