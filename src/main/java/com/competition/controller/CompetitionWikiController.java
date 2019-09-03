package com.competition.controller;


import com.competition.entity.CompetitionWiki;
import com.competition.entity.CompetitionWikiReply;
import com.competition.form.*;
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
 * @TODO 添加两个“历史”操作 2019/08/04 2019/08/--
 * @TODO 进行假删除操作 20190808 201908--
 *
 */
@RestController
@Slf4j
@CrossOrigin
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
     * 个人用户进行提问
     *
     * @param theQuestions 前端传回的问题信息，包括 userId + content
     * @param authorization token获取
     * @return 提示录入正常
     */
    @PostMapping("/usersAsk")
    public ReturnVO postQuestions(@RequestBody CompetitionQuestionForm theQuestions, @RequestHeader String authorization) {

        // 获取token中存储的ID
        String userId = JwtHelper.parserToken(authorization).getId();

        // 日志记录
        log.info("\n学生用户进行提问\n学生userId:" + userId);

        // 将用户id信息并入入参，统一存入数据库
        theQuestions.setUserId(userId);

        // 转换为 entity 并存入数据库
        CompetitionWiki competitionWiki = theQuestions.toEntity();
        wikiService.save(competitionWiki);

        // 结束
        return new ReturnVO(ReturnCode.SUCCESS);
    }

    /**
     * 组织获取用户提问
     *
     * @param authorization token
     * @return 问题列表（时间倒序）
     */
    @PostMapping("/getUsersAsk")
    public ReturnVO getQuestions(@RequestHeader String authorization, @RequestBody PageForm pageForm) {

        // 当前组织Id获取
        String userId = JwtHelper.parserToken(authorization).getId();

        // 日志记录
        log.info("\n组织用户获取提问\n组织userId:" + userId);

        // 获取问题列表并返回
        List<QuestionListToOrgPost> questions = wikiService.getQuestionsByHostId(userId, pageForm);
        System.out.println(questions);
        return new ReturnVO(ReturnCode.SUCCESS, questions);
    }

    /**
     * 组织用户回答提问
     *
     * @param theAnswers    答案，包括 competitionWikiId + content
     * @param authorization token
     * @return 200
     */
    @PostMapping("/usersAnswer")
    public ReturnVO postAnswers(@RequestBody CompetitionWikiReplyForm theAnswers, @RequestHeader String authorization) {

        // 当前组织Id获取
        String userId = JwtHelper.parserToken(authorization).getId();

        // 日志记录
        log.info("\n// 组织用户进行回答\n// 组织userId:" + userId);

        // 将用户id并入对象，存入数据库
        theAnswers.setUserId(userId);
        CompetitionWikiReply wikiReply = theAnswers.toEntity();
        replyService.save(wikiReply);

        // 向用户发送系统通知（系统通知存入数据库）
        announceService.pushAnnounce(wikiReply);

        // 向前端返回成功信息
        return new ReturnVO(ReturnCode.SUCCESS);
    }

    /**
     * 某个赛事获取问答信息（完整信息）
     *
     * @param competitionWikiForm 前端传回的赛事数据，包括：赛事id，页码，每页展示条数
     * @return 问答列表（倒序排序）
     */
    @PostMapping("/competitionGetAllWiki")
    public ReturnVO getAll(@RequestBody CompetitionWikiForm competitionWikiForm) {

        // 日志记录
        log.info("\n// 某个赛事获取问答信息\n// competitionId:" + competitionWikiForm.getCompetitionId());

        // 获取问答并提交
        List<CompetitionWikiPost> theQA = wikiService.getAllAboutCompetition(competitionWikiForm);
        return new ReturnVO(ReturnCode.SUCCESS, theQA);
    }

}

