package com.competition.form;

import lombok.Data;

import java.util.UUID;

/**
 * 推送通知
 *
 * @author HaoJun
 * @since 2019-08
 */
@Data
public class SystemAnnounce {

    /**
     * 主键：announceID
     */
    private String id = UUID.randomUUID().toString().replaceAll("-","");

    /**
     * 当前用户id
     */
    private String userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 通知的内容
     */
    private String announce;

    /**
     * 需要被跳转的链接
     */
    private String src;

    /**
     * 是否已读，默认未读
     */
    private Integer read = 0;

}
