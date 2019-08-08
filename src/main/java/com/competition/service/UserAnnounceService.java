package com.competition.service;

import com.competition.entity.CompetitionWikiReply;
import com.competition.entity.UserAnnounce;
import com.baomidou.mybatisplus.extension.service.IService;
import com.competition.form.PageForm;
import com.competition.form.UserAnnounceListPost;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GuoHaodong
 * @author updatedBy:HaoJun 2019/08/05
 * @since 2019-08-02
 */
public interface UserAnnounceService extends IService<UserAnnounce> {

    /**
     * 调用mapper层，获取当前用户系统消息列表
     *
     * @param userId 当前用户id token
     * @return 消息列表
     */
    List<UserAnnounceListPost> getAnnounceByUserId(String userId, PageForm pageForm);

    /**
     * 调用mapper层，更改消息状态：已读
     *
     * @param announce 前端传回的信息，包括 announceId + hasRead (id + hasRead)
     */
    void updateAnnounceReadById(UserAnnounce announce);

    /**
     * 组织回答时，向个人用户推送（数据库存入）固定信息
     *
     * @param  theAnswer 答案，包括 competitionWikiId + content
     */
    void pushAnnounce(CompetitionWikiReply theAnswer);

}
