package com.competition.form;

import com.competition.entity.CompetitionWiki;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 作用：向前端传递信息
 * 场景：组织用户回答问题之前，先获取问题列表
 *
 * @author HaoJun
 * @since 2019-08
 */
@Data
public class QuestionListToOrgPost implements IBaseDTO<CompetitionWiki>{

    /**
     * 问题id
     */
    private String wikiId;

    /**
     * 比赛名称
     */
    private String competitionName;

    /**
     * 问题
     */
    private String content;

    /**
     * 提问时间
     */
    private LocalDateTime creatAt;

    /**
     * form -> entity
     * 不能将比赛名称转换为 entity 中的属性
     *
     * @return entity
     */
    @Override
    public CompetitionWiki toEntity() {
        CompetitionWiki competitionWiki = new CompetitionWiki();
        competitionWiki.setWikiId(wikiId);
        competitionWiki.setContent(content);
        competitionWiki.setCreateAt(creatAt);
        return competitionWiki;
    }

    /**
     * entity -> form
     * competitionName = null;
     *
     * @param entity 需要转化成form的entity
     */
    @Override
    public void fromEntity(CompetitionWiki entity) {
        this.wikiId = entity.getWikiId();
        this.content = entity.getContent();
        this.creatAt = entity.getCreateAt();
    }
}
