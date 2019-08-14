package com.competition.shiro;

/**
 * @author:Cui
 * @date:2019/8/13
 * @type: class(类)
 * @description:
 * @action:
 */
public class UsernamePasswordEntity {

    private String userName;

    private String password;

    private String code;

    private boolean loginType;

    public boolean isLoginType() {
        return loginType;
    }

    public UsernamePasswordEntity setLoginType(boolean loginType) {
        this.loginType = loginType;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UsernamePasswordEntity setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UsernamePasswordEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCode() {
        return code;
    }

    public UsernamePasswordEntity setCode(String code) {
        this.code = code;
        return this;
    }
}
