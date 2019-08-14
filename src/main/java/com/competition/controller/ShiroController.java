package com.competition.controller;

import com.competition.entity.User;
import com.competition.service.UserService;
import com.competition.shiro.UserNamePasswordTelphoneToken;
import com.competition.shiro.UsernamePasswordEntity;
import com.competition.util.CheckAccountType;
import com.competition.util.SendMessage;
import org.apache.commons.mail.EmailException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.competition.util.SendMessage.sendMessage;

/**
 * @author:Cui
 * @date:2019/8/2
 * @description:
 * @action:
 */
@Controller
@RequestMapping("/shiro")
public class ShiroController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    RedisTemplate<String,String> template;

    /**
     * hello测试
     * @return
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "ok";
    }
    /**
     * 发表比赛页面请求
     * @return
     */
    @RequestMapping("/publish")
    public String publish(){
        return "testshiro/publish";
    }

    /**
     * 参与比赛页面请求
     * @return
     */
    @RequestMapping("/participate")
    public String participate(){
        return "testshiro/participate";
    }

    /**
     * 未授权页面请求
     * @return
     */
    @RequestMapping("/noAuth")
    public String noAuth(){
        return "noAuth";
    }

    /**
     * 登录页面请求
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * 关注页面请求
     * @return
     */
    @RequestMapping("/attention")
    public String attention(){
        return "power/attention";
    }

    /**
     * 评论页面请求
     * @return
     */
    @RequestMapping("/comment")
    public String comment(){
        return "power/comment";
    }

    /**
     * 问答页面请求
     * @return
     */
    @RequestMapping("/wiki")
    public String wiki(){
        return "power/wiki";
    }

    /**
     *主页请求
     * @param model
     * @return
     */
    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model){
        //把数据存入model
        model.addAttribute("name","黑马程序员");
        //返回test.html
        return "test";
    }

    /**
     * 登录页面请求
     * @param name
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/loginByPassword")
    public String loginByPassword(@RequestParam("name")String name, @RequestParam("password")String password, Model model){
        /**
         *shiro编写认证操作
         */
        Subject subject = SecurityUtils.getSubject();

        //封装用户数据
        if(!subject.isAuthenticated()){
            UsernamePasswordEntity entity = new UsernamePasswordEntity();
            entity.setUserName(name).setPassword(password).setLoginType(false);
            UserNamePasswordTelphoneToken token = new UserNamePasswordTelphoneToken(entity);
            //执行登录操作
            try {
                subject.login(token);
                return "redirect:testThymeleaf";
            }catch (UnknownAccountException e){
                model.addAttribute("msg","用户不存在");
                logger.error("用户不存在");
                return "login";
            }catch (AccountException e){
                model.addAttribute("msg","此号已被封停！");
                logger.error("此号被封！");
                return "login";
            }catch (IncorrectCredentialsException e){
                model.addAttribute("msg","密码错误");
                logger.error("密码错误");
                return "login";
            }
        }
        return "redirect:testThymeleaf";
    }
    @RequestMapping("loginByCode")
    public String loginByCode(@RequestParam("name")String name,@RequestParam("code")String code,Model model){
        /**
         *shiro编写认证操作
         */
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            if(template.opsForValue().get(name).equals(code)){
                UsernamePasswordEntity entity = new UsernamePasswordEntity();
//                参数传入
                entity.setLoginType(true).setUserName(name).setPassword("ok");
//                传入entity对象
                UserNamePasswordTelphoneToken token = new UserNamePasswordTelphoneToken(entity);
                //执行登录功能
                try{
                    subject.login(token);
                    return "redirect:testThymeleaf";
                }catch (IncorrectCredentialsException e){
                    System.out.println("验证码登录异常！");
                    model.addAttribute("msg","验证码登录异常！");
                    return "login";
                }catch (AccountException e){
                    model.addAttribute("msg","账号封停！");
                    return "login";
                }
            }else {
                model.addAttribute("msg", "验证码错误！");
                return "login";
            }
        }
        return "redirect:testThymeleaf";
    }
    @PostMapping("loginByCodeSend")
    public String loginByCodeSend(@RequestParam("name")String name,Model model){
        CheckAccountType checkAccountType = new CheckAccountType();
        //判断用户输入是手机号还是邮箱
        if(checkAccountType.checkPhone(name)){
            User user = userService.findByPhoneNumber(name);
            if(user!=null) {
                //发送验证码
                String truecode = sendMessage(name);
                //redis存储验证码
                template.opsForValue().set(name, truecode);
                System.out.println(template.opsForValue().get(name));
                model.addAttribute("msg","验证码发送成功!");
                return "login";
            }else {
                model.addAttribute("msg","用户名不存在！");
                System.out.println("用户名不存在");
                return "login";
            }
        }else if(checkAccountType.checkEmail(name)) {
            User user = userService.findByEmail(name);
            if(user!=null) {
                SendMessage sendMessage = new SendMessage();
                //发送验证码
                String truecode = null;
                try {
                    truecode = sendMessage.sendEmail(name);
                } catch (EmailException e) {
                    System.out.println("发送验证码出错！");
                }
                //redis存储验证码
                template.opsForValue().set(name, truecode);
                model.addAttribute("msg", "验证码发送成功！");
                return "login";
            }else {
                model.addAttribute("msg","用户名不存在！");
                return "login";
            }
        }else{
            model.addAttribute("手机号或邮箱错误！");
            return "login";
        }

    }
}
