package com.competition.controller;


import com.competition.entity.CompetitionWiki;
import com.competition.entity.CompetitionWikiReply;
import com.competition.form.questionAndAnswer;
import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
import com.competition.service.CompetitionWikiReplyService;
import com.competition.service.CompetitionWikiService;
import com.competition.service.UserAnnounceService;
import com.competition.util.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  问答部分前端控制器
 * </p>
 *
 * @author createdBy:GuoHaodong
 * @author updayedBy:HaoJun 2019/08/04
 * @since 2019-08-02
 *
 * @TODO 在组织回答问题后向个人用户发送一条系统消息
 * @TODO 修改url + 修改接口文档 2019/08/04 2019/08/05
 * @TODO 添加两个“历史”操作 2019/08/04 2019/08/05
 * @TODO 分页
 *
 */
@RestController
@Slf4j
@RequestMapping("/wiki")
public class CompetitionWikiController {


    /**
     * 服务层调用 / 创建服务层对象
     */
    @Autowired
    CompetitionWikiService wikiService;
    @Autowired
    CompetitionWikiReplyService replyService;
    @Autowired
    UserAnnounceService announceService;

    /**
     * 学生用户进行提问
     *
     * @param theQuestions 前端传回的问题信息，包括 userId + content
     * @param authorization token获取
     * @return 提示录入正常
     *
     * @TODO 等待测试 2019/08/04  2019/08/06
     *
     */
    @PostMapping("/usersAsk")
    public ReturnVO postQuestions(@RequestBody CompetitionWiki theQuestions, @RequestHeader String authorization) {

        // 获取token中存储的ID
        String userId = JwtHelper.parserToken(authorization).getId();

        // 日志记录
        log.info("学生用户进行提问\n学生userId:" + userId);

        // 将用户id信息并入入参，统一存入数据库
        theQuestions.setUserId(userId);
        wikiService.save(theQuestions);
        return new ReturnVO(ReturnCode.SUCCESS);
    }

    /**
     * ORG获取用户提问
     *
     * @param authorization token
     * @return 问题列表（时间倒序）
     *
     * @TODO 等待测试 2019/08/04  2019/08/06
     *
     */
    @PostMapping("/getUsersAsk")
    public ReturnVO getQuestions(@RequestHeader String authorization) {

        // 当前组织Id获取
        String userId = JwtHelper.parserToken(authorization).getId();

        // 日志记录
        log.info("组织用户获取提问\n组织userId:" + userId);

        // 获取问题列表并返回
        List<CompetitionWiki> questions = wikiService.getQuestionsByHost(userId);
        return new ReturnVO(ReturnCode.SUCCESS, questions);
    }

    /**
     * 组织用户回答提问
     *
     * @param theAnswers    答案，包括 competitionWikiId + content
     * @param authorization token
     * @return 200
     *
     * @TODO 等待测试 2019/08/04 2019/08/06
     *
     */
    @PostMapping("/usersAnwser")
    public ReturnVO postAnswers(@RequestBody CompetitionWikiReply theAnswers, @RequestHeader String authorization) {

        // 当前组织Id获取
        String userId = JwtHelper.parserToken(authorization).getId();

        // 日志记录
        log.info("组织用户进行回答\n组织userId:" + userId);

        // 将用户id并入对象，存入数据库
        theAnswers.setUserId(userId);
        replyService.save(theAnswers);

        // 向用户发送系统通知（系统通知存入数据库）
        announceService.pushAnnounce(theAnswers);

        // 向前端返回成功信息
        return new ReturnVO(ReturnCode.SUCCESS);
    }

    /**
     * 某个赛事获取问答信息（完整信息）
     *
     * @param competitionId 赛事id
     * @return 问答列表（倒序排序）
     *
     * @TODO 有待测试 2019/08/04 2019/08/06
     *
     */
    @PostMapping("/competitionGetAll")
    public ReturnVO getAll(@RequestBody String competitionId) {

        // 日志记录
        log.info("某个赛事获取问答信息\ncompetitionId:" + competitionId);

        // 获取问答并提交
        List<questionAndAnswer> theQA = wikiService.getAllAboutCompetition(competitionId);
        return new ReturnVO(ReturnCode.SUCCESS, theQA);
    }

}

