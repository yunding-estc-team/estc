package com.competition.form;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 前端传回的问题信息
 * 暂时废弃
 *
 * @author createdBy:HaoJun
 * @since 2019-08-04
 */
@Data
public class wikiQuestion {

    /**
     * 提问人id
     */
    private String userId;

    /**
     * 赛事的id
     */
    private String competitionId;

    /**
     * 问题的内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime creatAt;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;

}
