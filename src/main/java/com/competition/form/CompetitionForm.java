package com.competition.form;

import com.competition.entity.Competition;
import com.competition.util.Individual;
import lombok.Data;

/**
 * 前端提交的发布赛事表单
 * @author GuoHaodong
 * @date 2019-0803 09:40:14
 */
@Data
public class CompetitionForm implements IBaseDTO<Competition>{
	private String id;
	private String name;
	private String cover;
	private Individual individual;
	private String type;
	private String host;
	private String introduce;
	private String content;
	private String joinLink;

	/**
	 * 转换为entity方法
	 * @return
	 */
	@Override
	public Competition toEntity() {
		Competition competition = new Competition();

		competition.setIntroduce(this.introduce);
		competition.setCompetitionId(this.id);
		competition.setHost(this.host);
		competition.setContent(this.content);
		competition.setCover(this.cover);
		competition.setJoinLink(this.joinLink);
		competition.setIsIndividual(this.individual.getCode());
		competition.setName(this.name);
		competition.setType(this.type);

		return competition;
	}

	/**
	 * 转换为form
	 * @param entity 需要转化成form的entity
	 */
	@Override
	public void fromEntity(Competition entity) {
		this.content = entity.getContent();
		this.cover = entity.getCover();
		this.host = entity.getHost();
		this.id = entity.getCompetitionId();
		this.joinLink = entity.getJoinLink();
		this.name = entity.getName();
		this.type = entity.getType();

		if("1".equals(entity.getIsIndividual())) {
			this.individual = Individual.TRUE;
		}
		if ("0".equals(entity.getIsIndividual())) {
			this.individual = Individual.FALSE;
		}

		this.id = entity.getCompetitionId();
	}
}
