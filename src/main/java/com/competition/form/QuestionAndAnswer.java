package com.competition.form;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 赛事详情页，向前端传回完整的问答信息
 *
 * @author HaoJun
 * @since 2019-08
 */
@Data
public class QuestionAndAnswer {

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
