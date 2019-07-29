package com.competition.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {

    /**
     * 注入RedisTemplate
     * lettuce(生菜)线程安全,不使用jedis
     * @param factory 连接工厂
     * @return redisTemplate
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory factory){
        RedisTemplate<String,Object> template = new RedisTemplate<>();

        template.setKeySerializer(new StringRedisSerializer());

        template.setValueSerializer(new StringRedisSerializer());

        template.setConnectionFactory(factory);

        return template;
    }
}
