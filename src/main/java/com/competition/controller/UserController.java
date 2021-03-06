package com.competition.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.competition.VO.TokenObjectVO;
import com.competition.dao.UserMapper;
import com.competition.entity.Competition;
import com.competition.entity.User;
import com.competition.entity.UserCompetition;
import com.competition.exception.MyIllegalFormatException;
import com.competition.form.*;
import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
import com.competition.service.CompetitionService;
import com.competition.service.UserCompetitionService;
import com.competition.service.UserService;
import com.competition.util.CheckAccountType;
import com.competition.util.CheckCode;
import com.competition.util.JwtHelper;
import com.competition.util.SendMessage;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.regexp.internal.REUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import javafx.print.PaperSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.shiro.authc.Account;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.omg.CORBA.Current;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import sun.plugin2.main.client.MessagePassingOneWayJSObject;
import sun.security.jca.GetInstance;
import sun.security.util.Password;

import javax.xml.transform.Templates;
import java.security.PublicKey;
import java.util.Date;
import java.util.List;
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
@CrossOrigin
@RestController
@RequestMapping("/user")
public class  UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserCompetitionService userCompetitionService;
    @Autowired
    RedisTemplate<String,String> template;
    @Autowired
    CheckCode checkCode;



    /**
     * 校验用户是否存在
     */
    @PostMapping("/checkUser")
    public ReturnVO  checkUser(@RequestBody PasswordForm passwordForm){
        User user =userService.getOne(new QueryWrapper<User>()
                .lambda().eq(User::getUserEmail,passwordForm.getAddress())
                .or().eq(User::getUserPhone,passwordForm.getAddress()));
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
    @PostMapping("/i")
    public ReturnVO checkAccountType(@RequestBody PasswordForm passwordForm){
        CheckAccountType checkAccountType = new CheckAccountType();
        if(checkAccountType.checkPhone(passwordForm.getAddress())) {
            return new ReturnVO(ReturnCode.SUCCESS, "phone");
        }else if(checkAccountType.checkEmail(passwordForm.getAddress())) {
            return new ReturnVO(ReturnCode.SUCCESS,"email");
        }else{
            return new ReturnVO(ReturnCode.FAILURE_2);
        }
    }
    /**
     * 发送邮箱验证码
     */
    @PostMapping("/msg/email")
    public ReturnVO sendEmailCode(@RequestBody PasswordForm passwordForm)throws MyIllegalFormatException {
        SendMessage sendMessage =new SendMessage();
            //发送验证码
        String truecode = null;
        try {
            truecode = sendMessage.sendEmail(passwordForm.getAddress());
        } catch (EmailException e) {
            throw new MyIllegalFormatException();
        }
        //redis存储验证码
            template.opsForValue().set(passwordForm.getAddress(),truecode);
            return new ReturnVO(ReturnCode.SUCCESS);
    }

    /**
     * 发送手机验证码
     */
    @PostMapping("/msg/phone")
    public ReturnVO sendPhoneCode(@RequestBody PasswordForm passwordForm) throws MyIllegalFormatException {
        SendMessage sendMessage =new SendMessage();
            //发送验证码
        String truecode = null;
        try {
            truecode = sendMessage(passwordForm.getAddress());
        } catch (Exception e) {
            throw new MyIllegalFormatException();
        }
        //redis存储验证码
            template.opsForValue().set(passwordForm.getAddress(),truecode);
            return new ReturnVO(ReturnCode.SUCCESS);
    }

    /**
     * 通过token获取验证码
     * @param authorization token
     * @return 正常返回成功
     */
    @PostMapping("/msg/tokenPhone")
    public ReturnVO sendPhoneCode(@RequestHeader String authorization) {
        // 解析token，查询数据库，获取手机号
        String phoneNumber = userService.getById(JwtHelper.getTokenInfo(authorization).getId()).getUserPhone();
        // 发送验证码
        String verifyCode = null;
        try {
            verifyCode = sendMessage(phoneNumber);
        } catch (Exception e) {

        }
        // 存贮到redis
        template.opsForValue().set(phoneNumber,verifyCode);
        return ReturnVO.success();
    }


    /**
     * 校验验证码
     */
    @PostMapping("/msg/checkCode")
    public ReturnVO checkCode(@RequestBody PasswordForm passwordForm){
       try {
           checkCode.checkcode(passwordForm, template);
           return new ReturnVO(ReturnCode.SUCCESS);
       }catch (Exception e){
           e.printStackTrace();
           return new ReturnVO(ReturnCode.FAILURE_7);
       }
    }

    /**
     * 通过token校验验证码
     */
    @PostMapping("/msg/tokenCheckCode")
    public ReturnVO checkCode(@RequestBody PasswordForm passwordForm,@RequestHeader  String authorization){
        // 解析token，查询数据库，获取手机号
        String phoneNumber = userService.getById(JwtHelper.getTokenInfo(authorization).getId()).getUserPhone();
        passwordForm.setAddress(phoneNumber);
        try {
            checkCode.checkcode(passwordForm, template);
            return new ReturnVO(ReturnCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAILURE_7);
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

    @PostMapping("/updatePasswordByPhone")
    public ReturnVO updatePasswordBy(@RequestBody PasswordForm passwordForm) {
        CheckCode checkCode =new CheckCode();
        //校验验证码是否正确
        if(checkCode.checkcode(passwordForm,template)) {
            User user =userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUserPhone,passwordForm.getAddress()));
            //存储    新的密码
            Object credentials =passwordForm.getPassword();
            Object result = new SimpleHash("MD5", credentials, "xlong", 1);
            user.setPassword(((SimpleHash) result).toHex());
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

    @PostMapping("/updatePasswordByEmail")
    public ReturnVO updatePasswordByEmail(@RequestBody PasswordForm passwordForm) {
        CheckCode checkCode =new CheckCode();
        //校验验证码是否正确
        if(checkCode.checkcode(passwordForm,template)) {
            User user =userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUserEmail,passwordForm.getAddress()));
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
            Object credentials =passwordForm.getPassword();
            Object result = new SimpleHash("MD5", credentials, "xlong", 1);
            user.setPassword(((SimpleHash) result).toHex());
            user.update(new QueryWrapper<User>().lambda().eq(User::getUserId,id));
            return  new ReturnVO(ReturnCode.SUCCESS);
        }else{
            return  new ReturnVO(ReturnCode.FAILURE_3);
        }
    }

    /**-
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
     * 编辑个人资料（非私密信息）
     */
    @PostMapping("/updateInfo")
    public ReturnVO updateInfo(@RequestBody User user, @RequestHeader String authorization){
        //解析token
        String id =JwtHelper.parserToken(authorization).getId();
        user.setUserId(id);
        user.setRealname(null);
        user.setUserMajor(null);
        user.setUserSchool(null);
        user.setUserNo(null);
        user.update(new QueryWrapper<User>().lambda().eq(User::getUserId,id));
        return new ReturnVO(ReturnCode.SUCCESS);
    }
    /**
     * 编辑个人隐私资料（需要重新认证）
     */
    @PostMapping("/updatePrivateInfo")
    public ReturnVO updatePrivateInfo(@RequestBody User user, @RequestHeader String authorization) {
        //解析token
        String id = JwtHelper.parserToken(authorization).getId();
        user.setUserId(id);
        user.setUserPhone(null);
        user.setUserEmail(null);
        user.setUserName(null);
        user.setIntroduction(null);
        user.setUserBirth(null);
        user.setUserSex(null);
        user.setUserType(null);
        user.setCheckout("3");
        user.update(new QueryWrapper<User>().lambda().eq(User::getUserId, id));
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
                       .eq(UserCompetition::getCompetitionId,s));
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
            user.setUserName(passwordForm.getUserName());
            Object credentials =passwordForm.getPassword();
            Object result = new SimpleHash("MD5", credentials, "xlong", 1);
            user.setPassword(((SimpleHash) result).toHex());
            user.setUserEmail(passwordForm.getEmail());
            user.setUserPhone(passwordForm.getPhone());
            user.setUserType(passwordForm.getType());
            user.setUserId(uuid);
            user.insert();
            return new ReturnVO(ReturnCode.SUCCESS);
        }else{
            return new ReturnVO(ReturnCode.FAILURE_3);
        }
    }

    @Autowired
    CompetitionService competitionService;
    /**
     * 获奖信息录入
     */@PostMapping("/insertPrizeInfo")
    public ReturnVO insertPrizeInfo(@RequestBody UserPrizeForm userPrizeForm, @RequestHeader String authorization) {
        UserCompetition userCompetition=new UserCompetition();
         String id = JwtHelper.parserToken(authorization).getId();
         Competition competition =competitionService
                    .getOne(new QueryWrapper<Competition>().
                                lambda().eq(Competition::getName,userPrizeForm.getCompetitionName()));
        userCompetition.setUserId(id);
        userCompetition.setCheckout("2");
        userCompetition.setCompetitionId(competition.getName());
        userCompetition.setReward(userPrizeForm.getReward());
        userCompetition.setCertificate(userPrizeForm.getCover());
        userCompetition.insert();
        return new ReturnVO(ReturnCode.SUCCESS);
    }
    /**
     * 搜索展示（输入框填充）
     */
    @PostMapping("/searchName")
    public ReturnVO searchName(@RequestBody SearchForm searchForm){
        return new ReturnVO(ReturnCode.SUCCESS,userService.searchName(searchForm));
    }
    /**
     * 搜索（功能）
     */
    @PostMapping("searchAll")
    public ReturnVO searchByName(@RequestBody SearchForm searchForm){
        return new ReturnVO(ReturnCode.SUCCESS,userService.searchAll(searchForm));
    }



    @PostMapping("/login")
    public ReturnVO login(@RequestBody PasswordForm passwordForm){
        User user=userService
                .getOne(new QueryWrapper<User>().
                        lambda().eq(User::getUserPhone,passwordForm.getPhone()));
        if(passwordForm.getPassword()==user.getPassword()) {
            TokenObjectVO tokenObjectVO = new TokenObjectVO();
            tokenObjectVO.setId(user.getUserId());
            return new ReturnVO(ReturnCode.SUCCESS,tokenObjectVO);
        }else{
            return new ReturnVO(ReturnCode.FAILURE_6);
        }
    }



    /**
     * sign in
     *
     */
    @RequestMapping("/signin")
    public String signin(){
        TokenObjectVO tokenObjectVO = new TokenObjectVO();
        tokenObjectVO.setId("sad");
        //      tokenObjectVO.setType("admin");
        return JwtHelper.generateToken(tokenObjectVO);
    }
}

