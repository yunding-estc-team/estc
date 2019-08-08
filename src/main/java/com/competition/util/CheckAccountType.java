package com.competition.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 检验手机号或邮箱是否符合标准、
 * @author LiuJingqiang
 */

public class CheckAccountType {
    public boolean checkPhone(String account) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;

        String s = "^[1](([3][0-9])|([4][5,7,9])|([5][0-9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$";
        if (StringUtils.isNotBlank(account)) {
            p = Pattern.compile(s);
            m = p.matcher(account);
            b = m.matches();
        }
        return b;
    }
    public boolean checkEmail(String account){
        if(account.endsWith("@qq.com")||account.endsWith("@126.com")||account.endsWith("@163.com")||account.endsWith("@yeah.net")){
            return true;
        }else{
            return false;
        }
    }
}