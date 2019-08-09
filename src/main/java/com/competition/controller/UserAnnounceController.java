package com.competition.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.competition.entity.UserAnnounce;
import com.competition.form.PageForm;
import com.competition.form.UserAnnounceListPost;
import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
import com.competition.service.UserAnnounceService;
import com.competition.util.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GuoHaodong
 * @author updateBy:HaoJun 2019/08/04
 * @since 2019-08-02
 */
@RestController
@Slf4j
@RequestMapping("/userAnnounce")
public class UserAnnounceController {

    /**
     * 服务层调用 / 创建对象
     */
    @Autowired
    UserAnnounceService announceService;

    /**
     * 获取当前用户的系统通知列表
     *
     * @param authorization token
     * @return 消息列表
     *
     * @TODO 等待测试 20190805  20190806
     *
     */
    @PostMapping("/usersGetAnnounceList")
    public ReturnVO getAnncouce(@RequestHeader String authorization, @RequestBody PageForm pageForm) {

        // 当前用户Id获取
        String userId = JwtHelper.parserToken(authorization).getId();

        // 日志记录
        log.info("用户获取系统通知\nuserId:" + userId);

        // 通过下层获取消息列表并提交
        List<UserAnnounceListPost> userAnnounces = announceService.getAnnounceByUserId(userId, pageForm);
        return new ReturnVO(ReturnCode.SUCCESS,userAnnounces);
    }

    /**
     * 标记消息已读，并获取详细信息
     *
     * @param announce 前端传回的信息，包括 announceId + hasRead (id + hasRead)
     * @return 单条详细信息
     */
    @PostMapping("/userReadAnnounce")
    public ReturnVO makeAnnounceRead(@RequestBody UserAnnounce announce) {

        // 日志记录
        log.info("获取消息详情并标记已读\nannounceId:" + announce.getId());

        // 标记已读
        announceService.updateAnnounceReadById(announce);

        // 获取详细信息，并返回
        UserAnnounce detailedAnnounce
                = announceService.getOne(new QueryWrapper<UserAnnounce>().lambda().eq(UserAnnounce::getId, announce.getId()));
        return new ReturnVO(ReturnCode.SUCCESS,detailedAnnounce);
    }

}

