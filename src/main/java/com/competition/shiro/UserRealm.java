package com.competition.shiro;

import com.competition.entity.User;
import com.competition.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
import java.util.Set;

/**
 * @author:Cui
 * @date:2019/7/31
 * @description:
 * @action:
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserRealm.class);

    /**
     * 授权逻辑：当shiro框架检测到有用到权限的地方会执行授权逻辑
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权逻辑。。");
        //从PrincipalCollection中获取用户信息
        User principal = (User)principals.getPrimaryPrincipal();
        //定义一个集合保存角色
        Set<String> roles = new HashSet<>();
        //把数据库查出的角色添加到配置
        roles.add(principal.getUserName());
        //给资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        //从数据库查询权限数组
        String isActive = principal.getIsActive();
        String[] arr = isActive.split(",");
        //遍历数组添加权限
        for (String s : arr) {
            info.addStringPermission(s);
        }
        return info;
    }

    /**
     * 认证逻辑：当执行login方法时会自动执行
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("执行认证逻辑");
        //保存在前台输入的账号和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //从数据库查询账号和密码
        User user = userService.findByPhoneNumber(token.getUsername());
        if(user==null){
            return null;
        }
        ByteSource salt = ByteSource.Util.bytes("xlong");
        //判断密码
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),salt,getName());
        return info;
    }
}
