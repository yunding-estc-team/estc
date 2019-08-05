package com.competition.form;

import com.competition.entity.UserCompetition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * @author GuoHaodong
 * @date 2019-0805 09:20:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserRewardForm extends UserCompetition {
	@NonNull
	private String competitionName;
	@NonNull
	private String pageCurrent;
	@NonNull
	private String pageSize;
}
