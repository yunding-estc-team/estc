package com.competition.form;

import lombok.Data;

/**
 * @author GuoHaodong
 * @date 2019-0806 14:59:01
 * wiki 提交的数据
 */
@Data
public class WikiForm {

	/**
	 * 问题
	 */
	private String wiki;

	/**
	 * 问题id
	 */
	private String wikiId;

	/**
	 * 回复
	 */
	private String reply;

	/**
	 * 回复id
	 */
	private String replyId;
}
