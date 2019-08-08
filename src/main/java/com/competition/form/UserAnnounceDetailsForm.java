package com.competition.form;

import com.competition.entity.UserAnnounce;
import lombok.Data;

/**
 * 作用：获取前端信息
 * 场景：消息列表，获取某条信息的详细信息
 * 参数：消息id(announceId)  已读(hasRead)  当前页（继承）  页面展示条数（继承）
 *
 * @author HaoJun
 * @since  2019-08
 */
@Data
public class UserAnnounceDetailsForm extends PageForm {

    /**
     * 消息id
     */
    private String id;

    /**
     * 已读
     */
    private String hasRead;

}
