package com.competition.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.competition.entity.UserAnnounce;
import com.competition.form.UserAnnounceListPost;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GuoHaodong
 * @author updatedBy:HaoJun 2019/08/05
 * @since 2019-08-02
 */
public interface UserAnnounceMapper extends BaseMapper<UserAnnounce> {

    /**
     * 获取当前用户的系统消息"列表"
     * 1. 条件：userId = #{userId} 0:向全体用户发送通知
     * 2. 查询表单：user_announce
     * 3. 返回结果（list）：主键 - id | 标题 - title | 是否已读 - hasRead | 创建时间 - creatAt
     *
     * @param userId 当前用户id | token
     * @param userType 用户类型所对应的数字 0：全部  1：个人  2：组织
     * @return 消息列表(list)
     *
     * @TODO 传输参数 区分对个人/组织的全体消息
     *
     */
    @Select("SELECT `id` AS announce_id,`title`, `has_read`, `createAt` " +
            "FROM user_announce " +
            "WHERE user_id = #{userId} OR user_id = 'allUser' OR user_id = #{userType} " +
            "ORDER BY createAt  DESC " +
            "LIMIT #{aParam}, #{pageSize}" +
            ";")
    List<UserAnnounceListPost> selectAnnounceByUserId(@Param("userId") String userId, @Param("userType") String userType,
                                                      @Param("aParam") Integer aParam, @Param("pageSize") Integer pageSize);

    /**
     * 更新消息状态：已读
     * user_annoucne表，在 id = announceId 的行，将 has_read更改为1（已读）
     *
     * @param announceId 消息id
     * @param hasRead 消息状态
     */
    @Update("UPDATE user_announce SET `has_read` = #{hasRead} WHERE `id` = #{announceId};")
    void updateAnnounceReadById(@Param("announceId") String announceId, @Param("hasRead") String hasRead);

}
