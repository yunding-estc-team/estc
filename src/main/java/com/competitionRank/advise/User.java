package com.competitionRank.advise;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class User {
    @Pointcut("execution(* com.competitionRank.dao.service.*.*(..))")
    private void test(){};

    private Logger logger = LoggerFactory.getLogger(User.class);

}
