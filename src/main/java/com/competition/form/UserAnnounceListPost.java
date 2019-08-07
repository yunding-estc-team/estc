package com.competition.form;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 作用：向前端传递信息
 * 场景：消息盒子，提供消息列表
 *
 * @author HaoJun
 * @since  2019-08
 */
@Data
public class UserAnnounceListPost {

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
