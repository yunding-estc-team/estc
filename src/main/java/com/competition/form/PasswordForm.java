package com.competition.form;

import lombok.Data;

/**
 * 前端提交的修改密码表单
 * @author LiuJingqiang
 */
@Data
public class PasswordForm {
    /**
     * 忘记密码时的验证码
     */

    String code;

    /**
     * 忘记密码时填入的验证邮箱
     */

    String address;

    /**
     * 填入的新密码
     */

    String newpassword;

    /**
     * 修改密码时的原密码
     */

    String password;

    /**
     * 注册时用户填入的昵称
     */
    String username;

    /**
     * 用户类型
     */
    String type;


}