package com.competition.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.competition.entity.CompetitionWiki;
import com.competition.form.questionAndAnswer;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GuoHaodong
 * @author updatedBy:HaoJun 2019/08/04
 * @since 2019-08-02
 */
public interface CompetitionWikiService extends IService<CompetitionWiki> {

    /**
     * 调用 mapper 层，获取当前登录组织的提问
     *
     * @param host 组织用户id
     * @return 问题列表
     */
    List<CompetitionWiki> getQuestionsByHost(String host);

    /**
     * 调用 mapper 层，获取某个赛事的问答信息
     *
     * @param competitionId 赛事id
     * @return 问题列表
     */
    List<questionAndAnswer> getAllAboutCompetition(String competitionId);

}
