package com.competition.util;

import lombok.Data;
import lombok.Getter;

/**
 * @author GuoHaodong
 * @date 2019-0803 09:43:43
 */
@Getter
public enum Individual {
	/**
	 * code 存入数据库的值
	 */
	TRUE("1"),FALSE("0");
	private String code;
	private Individual(String code){this.code = code;}
}
