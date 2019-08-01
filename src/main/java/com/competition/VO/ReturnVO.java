package com.competition.VO;

/**
 * 统一返回值使用AOP实现
 * @author guohaodong
 */
public class ReturnVO {
	private String code;
	private String msg;
	private Object data;

	public ReturnVO() {}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
