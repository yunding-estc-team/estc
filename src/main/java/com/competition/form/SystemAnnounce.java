package com.competition.form;

import com.competition.entity.UserAnnounce;
import lombok.Data;

import java.util.UUID;

/**
 * 推送系统通知
 *
 * @author HaoJun
 * @since 2019-08
 */
@Data
public class SystemAnnounce implements IBaseDTO<UserAnnounce>{

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
    private String hasRead = "0";

    @Override
    public UserAnnounce toEntity() {
        UserAnnounce userAnnounce = new UserAnnounce();
        userAnnounce.setId(id);
        userAnnounce.setUserId(userId);
        userAnnounce.setTitle(title);
        userAnnounce.setAnnounce(announce);
        userAnnounce.setSrc(src);
        userAnnounce.setHasRead(hasRead);
        return userAnnounce;
    }

    @Override
    public void fromEntity(UserAnnounce entity) {
        id = entity.getId();
        userId = entity.getUserId();
        title = entity.getTitle();
        announce = entity.getAnnounce();
        src = entity.getSrc();
        hasRead = entity.getHasRead();
    }
}
