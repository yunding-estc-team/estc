package com.competition.util;



import com.alibaba.fastjson.JSONObject;
import com.competition.util.HTTPUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.awt.geom.RectangularShape;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 发送验证码
 * @author xlong
 * @date 2019-06-01 15:55
 */
public class SendMessage {
    private static String url ="http://pay-api.xinjigame.com/api/phoneValidateCode.jhtml";
    public static String sendMessage(String phoneNum)  {
        Map<String, String> messageParam = new HashMap<>(1);
        messageParam.put("phoneNum",phoneNum);
        String res = null;
        try {
            res = HTTPUtils.get(url, messageParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(res);
        return jsonObject.getString("validateCode");
    }


    /**
     * 发送邮箱验证码
     * @return
     */


    public String sendEmail(String userEmail) throws EmailException {

        // 不用更改
        HtmlEmail email = new HtmlEmail();

        // 需要修改，根据发件人邮箱，126邮箱为smtp.126.com,163邮箱为smtp.163.com，QQ为smtp.qq.com
        email.setHostName("smtp.163.com");

        // 设置字符编码
        email.setCharset("UTF-8");

        // 收件地址
        email.addTo(userEmail);

        // 此处填发件人邮箱地址和用户名,用户名可以任意填写
        email.setFrom("15034106669@163.com", "" + "课外科技竞赛排行榜");


        // 此处填写发件人邮箱地址和客户端授权码
        email.setAuthentication("15034106669@163.com", "CJKAFXF5zxhfxf");

        // 生成用户验证码
        Random random = new Random();
        String trueCode = Float.valueOf(random.nextFloat()).toString().substring(2,8);

        // 此处填写邮件名，邮件名可任意填写
        email.setSubject("赛谱课外科技竞赛排行榜 | 用户注册验证码");

        // 此处填写邮件内容
        email.setMsg("尊敬的用户您好,您本次注册的验证码是:\n<p style=\"color:#F63; font-size:20px font-weight:bold \">" + trueCode + "</p>\n[善用复制粘贴哦~]"+"\n本邮件由系统自动发出，请勿回复");

        email.send();
        System.out.println("发送成功");

        return trueCode;
    }

}
