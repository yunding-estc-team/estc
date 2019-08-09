package com.competition.service.impl;

import com.competition.entity.CompetitionWiki;
import com.competition.dao.CompetitionWikiMapper;
import com.competition.form.CompetitionWikiForm;
import com.competition.form.CompetitionWikiPost;
import com.competition.form.PageForm;
import com.competition.form.QuestionListToOrgPost;
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
    CompetitionWikiMapper competitionWikiMapper;

    /**
     * 获取用户提问
     * @param hostId 组织用户id
     * @return 问题列表
     */
    @Override
    public List<QuestionListToOrgPost> getQuestionsByHostId(String hostId, PageForm pageForm) {

        // 获取分页信息
        Integer pageCurrent = pageForm.getPageCurrent();
        Integer pageSize = pageForm.getPageSize();
        Integer aParam = (pageCurrent-1)*pageSize;

        // 传入底层
        return competitionWikiMapper.selectQuestionByHostId(hostId, aParam, pageSize);
    }

    /**
     * 获取关于某个赛事的问答整体
     *
     * @param competitionWikiForm 前端传回的信息
     * @return 问答列表
     */
    @Override
    public List<CompetitionWikiPost> getAllAboutCompetition(CompetitionWikiForm competitionWikiForm) {
        String competitionId = competitionWikiForm.getCompetitionId();
        Integer pageCurrent = competitionWikiForm.getPageCurrent();
        Integer pageSize = competitionWikiForm.getPageSize();
        Integer aParam = (pageCurrent-1)* pageSize;
        return competitionWikiMapper.selectAllAboutCompetition(competitionId, aParam, pageSize);
    }


}
