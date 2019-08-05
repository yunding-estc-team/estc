package com.competition.advise;

import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author GuoHaodong
 * @date 2019-0804 11:55:29
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalEx {
	@Autowired
	ReturnVO returnVO;

	@ExceptionHandler(DataAccessException.class)
	public ReturnVO e(DataAccessException ex){
		log.error("数据库访问异常"+ ex.getLocalizedMessage());
		returnVO.setData(ex);
		returnVO.setCode("203");
		returnVO.setMsg("数据库访问异常");
		return returnVO;
	}

	@ExceptionHandler(NullPointerException.class)
	public ReturnVO e(NullPointerException ex){
		log.error("空指针异常");
		returnVO.setCode("203");
		returnVO.setMsg("空指针异常");
		return returnVO;
	}

	@ExceptionHandler(ArithmeticException.class)
	public ReturnVO e(ArithmeticException ex){
		log.error("数字计算异常");
		returnVO.setMsg("数字计算异常");
		returnVO.setCode("203");
		return returnVO;
	}

}
