package com.competition.util;

import com.baomidou.mybatisplus.extension.api.R;

/**
 * @author GuoHaodong
 * @date 2019/7/30 上午9:17
 */
public enum ReturnCode {
	/**
	 * 每个枚举后面的description是所表示的意思,用于返回值
	 * 可以使用ordinal()方式获取在枚举中的排名
	 */
	SUCCESS("成功"),FAILURE_1(""),FAILURE_2(""),FAILURE_3("");

	private String description;

	public String getDescription() {
		return description;
	}
	private ReturnCode(String description){
		this.description = description;
	}
}
