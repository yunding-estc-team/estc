package com.competition.advise;

import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

/**
 * @author GuoHaodong
 * @since 2019-0803 11:34:49
 * @date 2019-08-04 19:27:40
 */
@Aspect
@Component
@Slf4j
public class Competition {
	@Pointcut("execution(* com.competition.controller.CompetitionController.*(..))")
	public void competitionAOP(){}

	@AfterThrowing(pointcut = "competitionAOP()",throwing = "ex")
	public ReturnVO deRecovery(Exception ex){
		log.error("捕获异常"+ex.getLocalizedMessage());

		if(ex instanceof NullPointerException){
			log.warn(ex.getMessage());
			return new ReturnVO(ReturnCode.FAILURE_1);
		}if (ex instanceof DataAccessException) {
			log.error(ex.getMessage());
			log.info("数据库访问异常,查看error日志");
			return new ReturnVO();
		}
		else{
			log.info("出现未捕获的异常"+ex.getClass());
			return new ReturnVO(ReturnCode.FAILURE);
		}
	}
}
