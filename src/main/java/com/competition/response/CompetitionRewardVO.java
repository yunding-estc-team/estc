package com.competition.response;

import com.competition.entity.UserCompetition;
import lombok.Data;

/**
 * @author GuoHaodong
 * @date 2019-0806 11:03:49
 * 赛事获奖信息
 */
@Data
public class CompetitionRewardVO  {
	private String name;
	private String reward;
	private String competitionId;
	private String realName;
}
