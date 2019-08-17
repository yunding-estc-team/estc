package com.competition.shiro;

import com.competition.entity.User;
import com.competition.service.UserService;
import com.competition.util.CheckAccountType;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

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
        User user;
        //从PrincipalCollection中获取用户信息
        String userName = (String) principals.getPrimaryPrincipal();
        CheckAccountType checkAccountType = new CheckAccountType();
        //查询数据库
        if(checkAccountType.checkPhone(userName)) {
            user = userService.findByPhoneNumber(userName);
        }else {
            user = userService.findByEmail(userName);
        }
        //定义一个集合保存角色
        Set<String> roles = new HashSet<>();
        //把数据库查出的角色添加到配置
        roles.add(user.getUserType());
        //给资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        //从数据库查询权限数组
        String isActive = user.getIsActive();
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
        CheckAccountType checkAccountType = new CheckAccountType();
        User user;
        //保存在前台输入的账号和密码
        UserNamePasswordTelphoneToken token = (UserNamePasswordTelphoneToken) authenticationToken;
        if(!token.isLoginType()) {
            SimpleAuthenticationInfo info;
            //密码MD5加密盐值
            ByteSource salt = ByteSource.Util.bytes("xlong");
            if(checkAccountType.checkPhone(token.getUsername())) {
                //从数据库查询账号和密码
                user = userService.findByPhoneNumber(token.getUsername());
                //用户名不存在，返回空值报错
                if (user == null) { return null;}
                //得到用户权限集合
                List<String> list = new ArrayList<>(Arrays.asList(user.getIsActive().split(",")));
                //若用户不包含次字段，则说明用户已被封号
                if (!list.contains("user:login")){
                    throw new AccountException("账号封停！");
                }
//                判断密码
                info = new SimpleAuthenticationInfo(user.getUserPhone(), user.getPassword(), salt, getName());
            }else {
                user = userService.findByEmail(token.getUsername());
                //得到用户权限集合
                List<String> list = new ArrayList<>(Arrays.asList(user.getIsActive().split(",")));
                if (!list.contains("user:login")) {
                    throw new AccountException("账号封停！");
                }
//               判断密码
                info = new SimpleAuthenticationInfo(user.getUserEmail(), user.getPassword(), salt, getName());
            }
            return info;
        }else {
            ByteSource salt = ByteSource.Util.bytes("xlong");
            Object result = new SimpleHash("MD5", "ok", salt, 1);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getUsername(),result,salt,getName());
            return info;
        }
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("xlong");
        int hashIterations = 1;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }
}
