package com.competition.response;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 生成统一的返回值
 * @author guohaodong
 */
@Data
//@Component
public class ReturnVO {
	private String code;
	private String msg;
	private Object data;

	/**
	 * 成功有返回数据
	 * @param code ReturnCode 枚举用于标记请求状态
	 * @param data 需要传输的数据
	 */
	public ReturnVO(ReturnCode code ,Object data) {
		this.code = "20"+code.ordinal();
		this.msg = code.getDescription();
		this.data = data;
	}

	/**
	 * 成功/失败 没有返回数据
	 * @param code ReturnCode 枚举标记状态
	 */
	public ReturnVO(ReturnCode code){
		this.code = "20"+code.ordinal();
		this.msg = code.getDescription();
	};

	/**
	 * 无参构造
	 */
	public ReturnVO(){};

	/**
	 * 静态方法
	 */
	public static ReturnVO success(Object o){
		return new ReturnVO(ReturnCode.SUCCESS,o);
	}
	public static ReturnVO success (){
		return new ReturnVO(ReturnCode.SUCCESS);
	}

	public static ReturnVO failure(ReturnCode code,Object o){
		return new ReturnVO(code,o);
	}

	public static ReturnVO failure(ReturnCode code) {
		return new ReturnVO(code);
	}

}
