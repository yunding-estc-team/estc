package com.competition.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.competition.VO.TokenObjectVO;
import com.competition.dao.UserMapper;
import com.competition.entity.User;
import com.competition.entity.UserCompetition;
import com.competition.form.FileForm;
import com.competition.form.PageForm;
import com.competition.form.PasswordForm;
import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
import com.competition.service.UserCompetitionService;
import com.competition.service.UserService;
import com.competition.util.CheckAccountType;
import com.competition.util.CheckCode;
import com.competition.util.JwtHelper;
import com.competition.util.SendMessage;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.regexp.internal.REUtil;
import javafx.print.PaperSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import sun.security.jca.GetInstance;
import sun.security.util.Password;

import javax.xml.transform.Templates;
import java.security.PublicKey;
import java.util.Map;
import java.util.UUID;

import static com.competition.util.SendMessage.sendMessage;

/**
 * <p>
 *  前端控制器
         * </p>
        *
        * @author LiuJingqiang
        * @since 2019-08-02
        */
@Slf4j
@RestController
@RequestMapping("/user")
public class  UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserCompetitionService userCompetitionService;
    @Autowired
    RedisTemplate<String,String> template;



    /**
     * 校验用户是否存在
     */
    @PostMapping("/checkUser")
    public ReturnVO  checkUser(@RequestBody String email){
        User user =userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUserEmail,email));
        Boolean bool = (user != null);
        if(bool){
            return new ReturnVO(ReturnCode.SUCCESS);
        }else{
            return new ReturnVO(ReturnCode.FAILURE_4);
        }
    }

    /**
     * 判断用户类型(邮箱或手机)
     */
    @PostMapping("/checkAccountType")
    public ReturnVO checkAccountType(@RequestBody String Account){
        CheckAccountType checkAccountType = new CheckAccountType();
        if(Account.length() == 11){
            if(checkAccountType.checkPhone(Account)){
                return new ReturnVO(ReturnCode.SUCCESS,"手机号");
            }else{
                return new ReturnVO(ReturnCode.FAILURE_2);
            }
        }else if(checkAccountType.checkEmail(Account)) {
            return new ReturnVO(ReturnCode.SUCCESS,"邮箱");
        }else{
            return new ReturnVO(ReturnCode.FAILURE_2);
        }

    }
    /**
     * 发送邮箱验证码
     */
    @PostMapping("/msg/email")
    public ReturnVO sendEmmailCode(@RequestBody String adress){
        SendMessage sendMessage =new SendMessage();
        try {
            //发送验证码
            String truecode =sendMessage.sendEmail(adress);
            //redis存储验证码
            template.opsForValue().set(adress,truecode);
            return new ReturnVO(ReturnCode.SUCCESS);
        } catch (EmailException e) {
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAILURE_2);
        }
    }

    /**
     * 发送手机验证码
     */
    @PostMapping("/msg/phone")
    public ReturnVO sendPhoneCode(@RequestBody String phoneNum){
        SendMessage sendMessage =new SendMessage();
        String adress =phoneNum;
        try {
            //发送验证码
            String truecode =sendMessage(phoneNum);
            //redis存储验证码
            template.opsForValue().set(adress,truecode);
            return new ReturnVO(ReturnCode.SUCCESS,truecode);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAILURE_3);
        }
    }



    /**
     * 注册后上传学生证认证)
     */


    @PostMapping("/uploadStd")
    public ReturnVO uploadStd(@RequestHeader String authorization, @RequestBody FileForm fileForm){
        //解析Token
        String userId = JwtHelper.getTokenInfo(authorization).getId();
        //存入数据
        User user = new User();
        user.setUserId(userId);
        user.setFile(fileForm.getPath());
        user.setHash(fileForm.getHash());

        userService.save(user);
        return new ReturnVO(ReturnCode.SUCCESS);
    }
    /**
     * 忘记密码
     */

    @PostMapping("/updatePassword")
    public ReturnVO updatePassword(@RequestBody PasswordForm passwordForm) {
        CheckCode checkCode =new CheckCode();
        //校验验证码是否正确
        if(checkCode.checkcode(passwordForm,template)) {
            User user = new User();
            //存储    新的密码
            user.setPassword(passwordForm.getNewpassword());
            try {
                userService.updateById(user);
                return new ReturnVO(ReturnCode.SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                return new ReturnVO(ReturnCode.FAILURE_3);
            }
        }else{
            return new ReturnVO(ReturnCode.FAILURE_2);
        }
    }
    /**
     * 修改密码
     */
    @PostMapping("/changePassword")
    public ReturnVO changePassword(@RequestBody PasswordForm passwordForm, @RequestHeader String authorization){
        User user =new User();
        String id = JwtHelper.parserToken(authorization).getId();
        User nowuser =userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUserId,id));
        //校验原密码是否正确
        if(passwordForm.getPassword().equals(nowuser.getPassword())){
            //存储新的密码
            user.setUserId(id);
            user.setPassword(passwordForm.getNewpassword());
            user.update(new QueryWrapper<User>().lambda().eq(User::getUserId,id));
            return  new ReturnVO(ReturnCode.SUCCESS);
        }else{
            return  new ReturnVO(ReturnCode.FAILURE_3);
        }
    }

    /**
     * 个人信息获取
     */
    @GetMapping("/info")
    public ReturnVO selectInfo(@RequestHeader String authorization){
        //解析token
        String s = JwtHelper.parserToken(authorization).getId();
        //数据库查询
        User user =userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUserId,s));

        return new ReturnVO(ReturnCode.SUCCESS,user);

    }

    /**
     * 编辑个人资料
     */
    @PostMapping("/updateInfo")
    public ReturnVO updateInof(@RequestBody User user, @RequestHeader String authorization){
        //解析token
        String id =JwtHelper.parserToken(authorization).getId();
        user.setUserId("10");
        user.update(new QueryWrapper<User>().lambda().eq(User::getUserId,id));
        return new ReturnVO(ReturnCode.SUCCESS);
    }

    /**
     * 获取获奖赛事相关信息（作分页）
     */

    @PostMapping("/selectJoinCompetion")
    public ReturnVO selectJoinCompetion(@RequestHeader String authorization, @RequestBody PageForm pageForm){
        //解析token
        String s =JwtHelper.parserToken(authorization).getId();

        //mybatis plus插件分页
        IPage<UserCompetition> userCompetitionIPage =userCompetitionService.page(
                new Page<UserCompetition>(pageForm.getPageCurrent(),pageForm.getPageSize()),
                Wrappers.<UserCompetition>lambdaQuery()
                        .eq(UserCompetition::getUserId,s));
        return new ReturnVO(ReturnCode.SUCCESS,userCompetitionIPage);

    }

    /**
     * 注册
     */


    @PostMapping("/register")
    public ReturnVO register(@RequestBody PasswordForm passwordForm){
        User user =new User();
        CheckCode checkCode =new CheckCode();
        log.info(passwordForm.toString());
        if(checkCode.checkcode(passwordForm,template)) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            user.setUserName(passwordForm.getUsername());
            user.setPassword(passwordForm.getPassword());
            user.setUserEmail(passwordForm.getAddress());
            user.setUserType(passwordForm.getType());
            user.setUserId(uuid);
            user.insert();
            return new ReturnVO(ReturnCode.SUCCESS);
        }else{
            return new ReturnVO(ReturnCode.FAILURE_3);
        }
    }


    /**
     * 获奖信息录入
     */@PostMapping("/insertPrizeInfo")
    public ReturnVO insertPrizeInfo(@RequestBody UserCompetition userCompetition,@RequestHeader String authorization) {
        String id = JwtHelper.parserToken(authorization).getId();
        userCompetition.setUserId(id);
        userCompetition.insert();
        return new ReturnVO(ReturnCode.SUCCESS);
    }


    /**
     * sign in
     *
     */
    @RequestMapping("signin")
    public String signin(){
        TokenObjectVO tokenObjectVO = new TokenObjectVO();
        tokenObjectVO.setId("10");
        //      tokenObjectVO.setType("admin");


        return JwtHelper.generateToken(tokenObjectVO);
    }
}

