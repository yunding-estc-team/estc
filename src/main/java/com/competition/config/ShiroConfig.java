package com.competition.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.competition.shiro.UserRealm;
import xyz.xlong99.service.CompetitionService1;
import xyz.xlong99.service.impl.CompetitionService1Impl;
import xyz.xlong99.service.impl.UserService1Impl;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author:Cui
 * @date:2019/7/31
 * @description:
 * @action:
 */

public class ShiroConfig {

    @Bean
    public CompetitionService1 getCompetitionService1(){
        return new CompetitionService1Impl();
    }
    @Bean
    public UserService1Impl getUserService1(){
        return new UserService1Impl();
    }
    //    为html标签引用而注入的bean
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
    /**
     *创建 ShiroFilterFactoryBean
     * @param defaultSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultSecurityManager")DefaultSecurityManager defaultSecurityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //创建安全管理器
        factoryBean.setSecurityManager(defaultSecurityManager);
        Map<String,String> filter = new LinkedHashMap<String, String>();
        filter.put("/shiro/login","anon");
        filter.put("/shiro/logout","logout");
        filter.put("/shiro/testThymeleaf","authc");
//        filter.put("/shiro/publish","authc,roles[organization]");
//        filter.put("/shiro/participate","authc,roles[student]");
        filter.put("/shiro/attention","perms[user:attention]");
        filter.put("/shiro/praise","perms[user:praise]");
        filter.put("/shiro/wiki","perms[user:wiki]");
        filter.put("/shiro/comment","perms[user:comment]");
        //todo 生产环境打开
//        filter.put("/*","authc");
        //修改调整登录页面
        factoryBean.setLoginUrl("/shiro/toLogin");
        factoryBean.setUnauthorizedUrl("/shiro/noAuth");
        factoryBean.setFilterChainDefinitionMap(filter);
        return factoryBean;
    }
    /**
     * 创建 defaultSecurityManager
     * @param userRealm
     * @return
     */
    @Bean("defaultSecurityManager")
    public DefaultWebSecurityManager getDefaultSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(userRealm);
        return defaultSecurityManager;
    }
    /**
     * 创建userRealm
     * @return
     */
    @Bean("userRealm")
    public UserRealm getUserRealm(@Qualifier("credentialsMatcher")HashedCredentialsMatcher credentialsMatcher){
        UserRealm userRealm = new UserRealm();
        credentialsMatcher.setHashIterations(1);
        credentialsMatcher.setHashAlgorithmName("MD5");
        userRealm.setCredentialsMatcher(credentialsMatcher);
        return userRealm;
    }
    /**
     * 设置密码加密方式基础Bean
     * @return
     */
    @Bean("credentialsMatcher")
    public HashedCredentialsMatcher getHashedCredentialsMatcher(){
        return new HashedCredentialsMatcher();
    }
}
