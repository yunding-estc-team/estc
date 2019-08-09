package com.competition.service.impl;

import com.competition.entity.Competition;
import com.competition.dao.CompetitionMapper;
import com.competition.service.CompetitionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GuoHaodong
 * @since 2019-08-02
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class CompetitionServiceImpl extends ServiceImpl<CompetitionMapper, Competition> implements CompetitionService {
	@Autowired
	CompetitionMapper mapper;
	@Override
	public void addClick(Competition competition) {
		mapper.addClick(competition);
	}
}
