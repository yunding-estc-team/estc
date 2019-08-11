package com.competition.form;

import lombok.Data;

/**
 * 作用：获取前端信息
 * 场景：赛事详情页，获取问答信息
 * 属性：比赛id，页码，每页展示条数
 *
 * @author HaoJun
 * @since  2019-08
 */
@Data
public class CompetitionWikiForm extends PageForm{

    /**
     * 赛事id
     */
    private String competitionId;

}
