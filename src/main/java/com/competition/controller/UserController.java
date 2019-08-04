package com.competition.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.competition.VO.TokenObjectVO;
import com.competition.dao.UserMapper;
import com.competition.entity.User;
import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
import com.competition.service.UserService;
import com.competition.util.JwtHelper;
import com.competition.util.SendMessage;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import javax.xml.transform.Templates;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LiuJingqiang
 * @since 2019-08-02
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    /**
     * 校验用户是否存在
     */
    @RequestMapping("/checkUser")
    public ReturnVO  checkUser(@RequestBody String email){
        User user =userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUserEmail,email));
        Boolean bool = (user == null);
        if(bool){
            return new ReturnVO(ReturnCode.SUCCESS);
        }else{
            return new ReturnVO(ReturnCode.FAILURE_4);
        }
    }

    /**
     * 判断用户类型
     */
    @Autowired
    RedisTemplate<String,String> template;

    /**
     * 发送邮箱验证码
     */
    @RequestMapping("/msg/email")
    public ReturnVO sendEmmailCode(@RequestBody String emailadress){
        SendMessage sendMessage =new SendMessage();
        try {
            String truecode =sendMessage.sendEmail(emailadress);
            template.opsForValue().set(emailadress,truecode);
            return new ReturnVO(ReturnCode.SUCCESS);
        } catch (EmailException e) {
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAILURE_2);
        }
    }

    /**
     * 注册界面验证邮箱验证码并将账号密码填入数据库
     */


    /**
     * 注册后填写个人信息
     */

    @RequestMapping("/identify")
    public ReturnVO identify(@RequestBody User user){
        userService.updateById(user);
        /**
         * 缺少学生证资料上传
         */
        return new ReturnVO(ReturnCode.SUCCESS);
    }
    /**
     * 忘记（更新）密码
     */

    @RequestMapping("/updatePassword")
    public ReturnVO updatePassword(@RequestBody String password, @RequestHeader String authorization) {
        /**获取token中存储的ID*/
        String sm = JwtHelper.parserToken(authorization).getId();
//        /**获取token中储存的用户类型*/
//        JwtHelper.parserToken(authorization).getSubject();
        User user = new User();
        user.setPassword(password);
        user.setUserId(sm);
        try {
            userService.updateById(user);
            return  new ReturnVO(ReturnCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return  new ReturnVO(ReturnCode.FAILURE_3);
        }
    }

    /**
     * 修改密码
     */
    @RequestMapping("/changePassword")
    public ReturnVO changePassword(@RequestBody Map<String ,String> map, @RequestHeader String authorization){
        User user =new User();
        String sm = JwtHelper.parserToken(authorization).getId();
        User nowuser =userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUserId,sm));
        if(map.get("password") == nowuser.getPassword()){
            user.setUserId(sm);
            user.setPassword(map.get("newpassword"));
            return  new ReturnVO(ReturnCode.SUCCESS);
        }else{
            return  new ReturnVO(ReturnCode.FAILURE_3);
        }
    }

    /**
     * 验证码校验
     */
    @RequestMapping("/msg/check")
    public ReturnVO checkEmailCode(@RequestBody Map<String,String> map){
        String nowcode =map.get("code");
        String truecode=template.opsForValue().get(map.get("emailadress"));


        return new ReturnVO(ReturnCode.SUCCESS);
    }

    /**
     * 个人信息获取
     */
        @RequestMapping("/info")
        public ReturnVO selectInfo(@RequestHeader String authorization){
            String sm = JwtHelper.parserToken(authorization).getId();
            User user =userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUserId,sm));
        return new ReturnVO(ReturnCode.SUCCESS,user);

    }
    /**
     * sign in
     *
     */
    @RequestMapping("signin")
    public String signin(){
        TokenObjectVO tokenObjectVO = new TokenObjectVO();
        tokenObjectVO.setId("liu");
        tokenObjectVO.setType("admin");
        return JwtHelper.generateToken(tokenObjectVO);
    }

}

