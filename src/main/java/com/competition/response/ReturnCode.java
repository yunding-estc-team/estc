package com.competition.response;

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
	FAILURE_4("用户已存在"),
	FAILURE_5("用户名不存在"),
	FAILURE_6("用户名或密码错误"),
	FAILURE_7("验证码错误"),
	FAILURE_8("账号被封停！"),
	FAILURE_9("格式错误"),
	FAILURE("未知错误");

	private String description;

	public String getDescription() {
		return description;
	}
	ReturnCode(String description){
		this.description = description;
	}
}
