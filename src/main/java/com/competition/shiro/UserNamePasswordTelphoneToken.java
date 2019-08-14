package com.competition.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

/**
 * @author:Cui
 * @date:2019/8/13
 * @type: class(类)
 * @description:
 * @action:
 */
public class UserNamePasswordTelphoneToken extends UsernamePasswordToken implements Serializable {

    private String userCode;

    private boolean loginType;

    public UserNamePasswordTelphoneToken(UsernamePasswordEntity usernamePasswordEntity){
        if (usernamePasswordEntity.getPassword() != null) {
            this.setPassword(usernamePasswordEntity.getPassword().toCharArray());
        } else {
            this.setPassword(null);
        }
        this.setUsername(usernamePasswordEntity.getUserName());
        if (usernamePasswordEntity.getCode() != null) {
            this.setUserCode(usernamePasswordEntity.getCode());
        } else {
            this.setUserCode(null);
        }
        this.loginType = usernamePasswordEntity.isLoginType();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public boolean isLoginType() {
        return loginType;
    }

    public void setLoginType(boolean loginType) {
        this.loginType = loginType;
    }
}
