package com.competition.response;

/**
 * @author:Cui
 * @date:2019/8/6
 * @description:
 * @action:
 */
public class PermissionClass {

    private boolean login;

    private boolean praise;

    private boolean comment;

    private boolean attention;

    private boolean wiki;

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public boolean isPraise() {
        return praise;
    }

    public void setPraise(boolean praise) {
        this.praise = praise;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public boolean isAttention() {
        return attention;
    }

    public void setAttention(boolean attention) {
        this.attention = attention;
    }

    public boolean isWiki() {
        return wiki;
    }

    public void setWiki(boolean wiki) {
        this.wiki = wiki;
    }

    @Override
    public String toString() {
        return "PermissionClass{" +
                "login=" + login +
                ", praise=" + praise +
                ", comment=" + comment +
                ", attention=" + attention +
                ", wiki=" + wiki +
                '}';
    }
}
