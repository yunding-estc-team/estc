package com.competition.form;

import lombok.Data;

/**
 * 当前页,分页大小
 * @author GuoHaodong
 * @date 2019-0805 11:47:42
 */
@Data
public class PageForm {
	/**
	 * 当前页
	 */
	protected int pageCurrent;

	/**
	 * 每页显示的条数
	 */
	protected int pageSize;
}
