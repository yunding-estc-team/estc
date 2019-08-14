package com.competition.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.competition.service.UserService;
import com.competition.shiro.UserRealm;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.xlong99.service.CompetitionService;
import xyz.xlong99.service.impl.CompetitionServiceImpl;
import xyz.xlong99.service.impl.UserServiceImpl;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author:Cui
 * @date:2019/7/31
 * @description:
 * @action:
 */

public class ShiroConfig {

	@Autowired
    UserService userService;

	@Resource
    xyz.xlong99.service.UserService userServiceX;

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
