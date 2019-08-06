package com.competition.response;

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
	SUCCESS("成功"),
	FAILURE_1("信息缺失"),
	FAILURE_2("信息错误"),
	FAILURE_3("内部错误"),
	FAILURE_4("账号已存在"),
	FAILURE("未知错误");

	private String description;

	public String getDescription() {
		return description;
	}
	private ReturnCode(String description){
		this.description = description;
	}
}
