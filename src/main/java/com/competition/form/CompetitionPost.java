package com.competition.form;

import com.competition.util.Individual;
import lombok.Data;

/**
 * 前端提交的发布赛事表单
 * @author GuoHaodong
 * @date 2019-0803 09:40:14
 */
@Data
public class CompetitionPost {
	private String id;
	private String name;
	private String cover;
	private Individual individual;
	private String type;
	private String host;
	private String introduce;
	private String content;
	private String joinLink;
}
