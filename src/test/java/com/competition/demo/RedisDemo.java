package com.competition.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author:Cui
 * @date:2019/8/14
 * @type: class(类)
 * @description:
 * @action:
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisDemo {

    @Autowired
    RedisTemplate<String,String> template;
    @Test
    public void redisT(){
        template.opsForValue().set("dd","fdas");
        log.info(template.opsForValue().get("dd"));
    }
}
