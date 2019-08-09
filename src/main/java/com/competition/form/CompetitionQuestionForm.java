package com.competition.form;

import com.competition.entity.CompetitionWiki;
import lombok.Data;

import java.util.UUID;

/**
 * 作用：获取前端信息
 * 场景：赛事详情，个人用户提交问题
 *
 * @author HaoJun
 * @since  2019-08
 */
@Data
public class CompetitionQuestionForm implements IBaseDTO<CompetitionWiki>{

    /**
     * 问题id （主键）
     * 自动生成 UUID 进行填充
     */
    private String wikiId = UUID.randomUUID().toString().replaceAll("-","");

    /**
     * 比赛id
     * 通过json获取
     */
    private String competitionId;

    /**
     * 提问人id
     * 通过token获取
     */
    private String userId;

    /**
     * 问题的内容
     * 通过json获取
     */
    private String content;

    /**
     * form -> entity
     *
     * @return
     */
    @Override
    public CompetitionWiki toEntity() {
        CompetitionWiki competitionWiki = new CompetitionWiki();
        competitionWiki.setWikiId(wikiId);
        competitionWiki.setCompetitionId(competitionId);
        competitionWiki.setUserId(userId);
        competitionWiki.setContent(content);
        return competitionWiki;
    }

    /**
     * entity -> form
     *
     * @param entity 需要转化成form的entity
     */
    @Override
    public void fromEntity(CompetitionWiki entity) {
        this.wikiId = entity.getWikiId();
        this.competitionId = entity.getCompetitionId();
        this.userId = entity.getUserId();
        this.content = entity.getContent();
    }
}
