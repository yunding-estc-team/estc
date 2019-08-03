package com.competition.controller;

import com.competition.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
        return "/testshiro/publish";
    }

    /**
     * 参与比赛页面请求
     * @return
     */
    @RequestMapping("/participate")
    public String participate(){
        return "/testshiro/participate";
    }

    /**
     * 未授权页面请求
     * @return
     */
    @RequestMapping("/noAuth")
    public String noAuth(){
        return "/noAuth";
    }

    /**
     * 登录页面请求
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/login";
    }

    /**
     * 关注页面请求
     * @return
     */
    @RequestMapping("/attention")
    public String attention(){
        return "/power/attention";
    }

    /**
     * 评论页面请求
     * @return
     */
    @RequestMapping("/comment")
    public String comment(){
        return "/power/comment";
    }

    /**
     * 点赞页面请求
     * @return
     */
    @RequestMapping("/praise")
    public String praise(){
        return "/power/praise";
    }

    /**
     * 问答页面请求
     * @return
     */
    @RequestMapping("/wiki")
    public String wiki(){
        return "/power/wiki";
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
    @RequestMapping("/login")
    public String login(@RequestParam("name")String name, @RequestParam("password")String password, Model model){
        /**
         *shiro编写认证操作
         */
        Subject subject = SecurityUtils.getSubject();

        //封装用户数据
        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(name,password);
            //执行登录操作
            try {
                subject.login(token);
                return "redirect:testThymeleaf";
            }catch (UnknownAccountException e){
                model.addAttribute("msg","用户不存在");
                logger.error("用户不存在");
                return "/login";
            }catch (IncorrectCredentialsException e){
                model.addAttribute("msg","密码错误");
                logger.error("密码错误");
                return "/login";
            }
        }
        return "redirect:testThymeleaf";
    }
}
