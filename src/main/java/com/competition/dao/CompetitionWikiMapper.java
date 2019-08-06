package com.competition.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.competition.entity.CompetitionWiki;
import com.competition.form.QuestionAndAnswer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GuoHaodong
 * @author updatedBy:HaoJun
 * @since 2019-08-02
 */
public interface CompetitionWikiMapper extends BaseMapper<CompetitionWiki> {

    /**
     * 问、答，两表联立，查询针对当前用户的提问
     * 1. 子查询：通过“用户id”查询 competition 表单，获取用户所举办的赛事
     * 2. 外层查询：通过“赛事id”查询 competition_wiki 表单，获取所有提问
     * 3. 查询结果排序：根据字段 creatAt 倒序排序
     *
     * @param host 登录组织id
     * @return 问题列表
     */
    @Select("SELECT * FROM `competition_wiki` WHERE competition_id in (SELECT competition_id FROM competition " +
            "WHERE `host` = #{host}) ORDER BY createAt DESC;")
    List<CompetitionWiki> selectQuestionByHost(@Param("host") String host);


    /**
     * 问答两表联立，查询某个赛事的所有提问
     * 1. 查询 问题、答案、回答时间
     * 2. 条件：两个表中问题id相同且wiki（问题）表中赛事id=参数
     * 3. 倒序排序
     * select * from table limit (start-1)*pageSize,pageSize; 其中start是页码，pageSize是每页显示的条数。
     *
     * @param competitionId 赛事id
     * @param aParam        aParam = (pageCurrent-1)*pageSize
     * @param bParam        bParam = pageSize
     * @return 问答列表
     */
    @Select("(SELECT competition_wiki.content AS userQuestion, competition_wiki_reply.content AS userAnswer, " +
            "competition_wiki_reply.createAt AS answerCreatAt " +
            "FROM competition_wiki, `competition_wiki_reply` " +
            "WHERE competition_wiki.wiki_id = competition_wiki_reply.competition_wiki_id " +
            "&& competition_wiki.competition_id = #{competitionId} " +
            "ORDER BY createAt DESC ) " +
            "limit ${aParam}, ${bParam}" +
            ";")
    List<QuestionAndAnswer> selectAllAboutCompetition(@Param("competitionId") String competitionId,
                                                      @Param("aParam") Integer aParam, @Param("bParam") Integer bParam);

}
