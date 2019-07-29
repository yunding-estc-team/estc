package com.competition.advise;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 切点类
 * @author guohaodong
 */
@Component
@Aspect
public class User {
    @Pointcut(value = "execution(* com.competition.dao.service.*.*(..))")
    private void test(){ };

    private Logger logger = LoggerFactory.getLogger(User.class);

}
