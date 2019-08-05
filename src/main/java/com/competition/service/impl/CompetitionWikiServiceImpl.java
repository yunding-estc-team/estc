package com.competition.service.impl;

import com.competition.entity.CompetitionWiki;
import com.competition.dao.CompetitionWikiMapper;
import com.competition.form.questionAndAnswer;
import com.competition.service.CompetitionWikiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GuoHaodong
 * @author updatedBy:HaoJun 2019-08-04
 * @since 2019-08-02
 */
@Service
public class CompetitionWikiServiceImpl extends ServiceImpl<CompetitionWikiMapper, CompetitionWiki> implements CompetitionWikiService {

    /**
     * mapper层对象
     */
    @Autowired
    CompetitionWikiMapper wikiMapper;

    /**
     * 获取用户提问
     * @param host 组织用户id
     * @return 问题列表
     */
    @Override
    public List<CompetitionWiki> getQuestionsByHost(String host) {
        return wikiMapper.selectQuestionByHost(host);
    }

    /**
     * 获取关于某个赛事的问答整体
     *
     * @param competitionId 赛事id
     * @return 问答列表
     */
    @Override
    public List<questionAndAnswer> getAllAboutCompetition(String competitionId) {
        return wikiMapper.selectAllAboutCompetition(competitionId);
    }


}
