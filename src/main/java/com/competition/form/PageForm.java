package com.competition.form;

import lombok.Data;

/**
 * 当前页,分页大小
 * @author GuoHaodong
 * @date 2019-0805 11:47:42
 */
@Data
public class PageForm {
	private int pageCurrent;
	private int pageSize;
}
