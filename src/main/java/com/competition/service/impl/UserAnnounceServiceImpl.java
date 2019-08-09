package com.competition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.competition.dao.CompetitionWikiMapper;
import com.competition.dao.UserAnnounceMapper;
import com.competition.dao.UserMapper;
import com.competition.entity.CompetitionWiki;
import com.competition.entity.CompetitionWikiReply;
import com.competition.entity.User;
import com.competition.entity.UserAnnounce;
import com.competition.form.PageForm;
import com.competition.form.SystemAnnounce;
import com.competition.form.UserAnnounceListPost;
import com.competition.service.UserAnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GuoHaodong
 * @author updatedBy:HaoJun 20190805
 * @since 2019-08-02
 */
@Service
public class UserAnnounceServiceImpl extends ServiceImpl<UserAnnounceMapper, UserAnnounce> implements UserAnnounceService {

    /**
     * mapper层调用 / 创建对象
     */
    @Autowired
    UserAnnounceMapper announceMapper;
    @Autowired
    CompetitionWikiMapper wikiMapper;
    @Autowired
    UserMapper userMapper;

    /**
     * 获取当前登录用户系统消息列表
     *
     * @param userId 当前用户id token
     * @return 问题列表
     */
    @Override
    public List<UserAnnounceListPost> getAnnounceByUserId(String userId, PageForm pageForm) {

        // 获取用户类型
        User user = userMapper.selectOne(new QueryWrapper<User>().select("user_type").lambda().eq(User::getUserId, userId));
        String userType = user.getUserType();

        // 获取分页信息
        Integer pageCurrent = pageForm.getPageCurrent();
        Integer pageSize = pageForm.getPageSize();
        Integer aParam = (pageCurrent - 1) * pageSize;

        // 传入底层
        return announceMapper.selectAnnounceByUserId(userId, userType, aParam, pageSize);
    }

    /**
     * 调用mapper层，更改消息状态：已读
     *
     * @param announce 前端传回的信息，包括 announceId + hasRead (id + hasRead)
     */
    @Override
    public void updateAnnounceReadById(UserAnnounce announce) {
        // 获取（分离）参数
        String announceId = announce.getId();
        String hasRead = announce.getHasRead();

        // 底层调用
        announceMapper.updateAnnounceReadById(announceId,hasRead);
    }

    /**
     * 推送固定信息
     */
    @Override
    public void pushAnnounce(CompetitionWikiReply theAnswer) {

        /*
         * 获取提问信息
         * 1. 条件：wikiId
         * 2. 表单：competition_wiki 表单
         * 3. 结果：问题(CompetitionWiki)对象
         */
        CompetitionWiki competitionWiki = wikiMapper.selectOne(new QueryWrapper<CompetitionWiki>()
                                                    .lambda()
                                                    .eq(CompetitionWiki::getWikiId, theAnswer.getCompetitionWikiId()));

        // 编辑推送信息
        SystemAnnounce systemAnnounce = new SystemAnnounce();
        String userId = competitionWiki.getUserId();
        systemAnnounce.setUserId(userId);
        String title = "[系统通知] 提问被回答";
        systemAnnounce.setTitle(title);
        String announce = "您的提问【" + competitionWiki.getContent() + "】已被回答。";
        systemAnnounce.setAnnounce(announce);

        // 转化为实体类
        UserAnnounce userAnnounce = systemAnnounce.toEntity();

        // 插入数据库
        announceMapper.insert(userAnnounce);
    }

}
