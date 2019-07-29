package com.competition.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author GuoHaodong
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserCompetition extends Model<UserCompetition> {

    private static final long serialVersionUID=1L;

    /**
     * 比赛id,与比赛表competition_id关联
     */
    @TableId
    private Integer competitionId;

    /**
     * 用户id,与密码表的user_id关联
     */
    private Integer userId;

    /**
     * 获得的奖项名称
     */
    private String reward;

    /**
     * 证书的照片链接
     */
    private String certificate;

    /**
     * 是否经过审查,0未审查，1已通过，2未通过
     */
    private String check;

    /**
     * 删除照片对应的hash值
     */
    private String hash;

    @TableField("createAt")
    private LocalDateTime createAt;

    @TableField("updateAt")
    private LocalDateTime updateAt;


    @Override
    protected Serializable pkVal() {
        return this.competitionId;
    }

}
