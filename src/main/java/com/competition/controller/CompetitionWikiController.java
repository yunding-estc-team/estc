package com.competition.controller;


import com.competition.entity.CompetitionWiki;
import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
import com.competition.service.CompetitionWikiService;
import com.competition.service.impl.CompetitionWikiServiceImpl;
import com.competition.util.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author createdBy:GuoHaodong
 * @author updayedBy:HaoJun
 * @since 2019-08-02
 */
@RestController
@Slf4j
@RequestMapping("/wiki")
public class CompetitionWikiController {


    /**
     * 服务层调用
     */
    @Autowired
    CompetitionWikiService wikiService;

    /**
     * 学生用户进行提问
     * @param question 前端传回信息，包括 userId + content
     * @param authorization token获取
     * @return 提示录入正常
     */
    @PostMapping("/usersAsk")
    public ReturnVO postQuestions(@RequestBody CompetitionWiki question, @RequestHeader String authorization) {
        // 获取token中存储的ID
        String userId = JwtHelper.parserToken(authorization).getId();
        // 将id信息并入入参，统一存入数据库
        question.setUserId(userId);
        wikiService.save(question);
        return new ReturnVO(ReturnCode.SUCCESS);
    }



}

