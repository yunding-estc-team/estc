package com.competition.form;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 作用：向前端传递信息
 * 场景：消息盒子，获取问答信息详情
 *
 * @author HaoJun
 * @since 2019-08
 */
@Data
public class CompetitionWikiPost {

    /**
     * 问题
     */
    private String userQuestion;

    /**
     * 回答
     */
    private String userAnswer;

    /**
     * 问答时间
     * 将 回答时间 作为问答时间
     */
    private LocalDateTime answerCreatAt;

}
