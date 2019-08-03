package com.competition.VO;

import lombok.Data;

/**
 * @author GuoHaodong
 * @date 2019-0802 17:33:03
 */
@Data
public class CompetitionVO {
	/**
	 * 比赛名称
	 */
	private String name;

	/**
	 * 封面海报的图片路径
	 */
	private String cover;

	/**
	 * 是否为个人赛,1true0false
	 */
	private String isIndividual;

	/**
	 * 比赛类型
	 */
	private String type;

	/**
	 * 主办方id
	 */
	private String host;

	/**
	 * 比赛介绍
	 */
	private String introduce;

	/**
	 * 比赛内容(主题)
	 */
	private String content;

	/**
	 * 报名链接
	 */
	private String join;

	/**
	 * 保研率
	 */
	private Integer rgr;

	/**
	 * 是否经过审查,0未审查，1已通过，2未通过
	 */
	private String checkout;

	/**
	 * 点击量
	 */
	private Integer clickCount;

	/**
	 * 热度值
	 */
	private Integer hot;

}
