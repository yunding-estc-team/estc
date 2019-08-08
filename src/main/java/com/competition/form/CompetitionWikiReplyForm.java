package com.competition.form;

import com.competition.entity.CompetitionWikiReply;
import lombok.Data;

import java.util.UUID;

/**
 * 作用：获取前端信息
 * 场景：组织用户回答问题（提交答案）
 *
 * @author HaoJun
 * @since  2019-08
 */
@Data
public class CompetitionWikiReplyForm implements IBaseDTO<CompetitionWikiReply>{

    /**
     * 主键id
     */
    private String id = UUID.randomUUID().toString().replaceAll("-", "");

    /**
     * 问题id
     */
    private String competitionWikiId;

    /**
     * 回答人id
     */
    private String userId;

    /**
     * 答案内容
     */
    private String content;

    /**
     * form -> entity
     *
     * @return entity
     */
    @Override
    public CompetitionWikiReply toEntity() {
        CompetitionWikiReply wikiReply = new CompetitionWikiReply();
        wikiReply.setId(id);
        wikiReply.setCompetitionWikiId(competitionWikiId);
        wikiReply.setUserId(userId);
        wikiReply.setContent(content);
        return wikiReply;
    }

    /**
     * entity -> form
     *
     * @param entity 需要转化成form的entity
     */
    @Override
    public void fromEntity(CompetitionWikiReply entity) {
        this.id = entity.getId();
        this.competitionWikiId = entity.getCompetitionWikiId();
        this.userId = entity.getUserId();
        this.content = entity.getContent();
    }

}
