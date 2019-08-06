package com.competition.form;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用于向前端返回系统消息(列表)
 *
 * @author HaoJun
 * @since  2019-08
 */
@Data
public class UserAnnouncePost {

    /**
     * 消息id
     */
    private Integer announceId;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否已读
     */
    private String read;

    /**
     * 创建时间
     */
    private LocalDateTime creatAt;
}
