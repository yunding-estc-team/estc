package com.competition.entity;

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
 * @since 2019-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserAnnounce extends Model<UserAnnounce> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 被通知的用户id
     */
    private String userId;

    /**
     * 通知的内容
     */
    private String announce;

    /**
     * 跳转的链接
     */
    private String src;

    /**
     * 是否已读
     */
    private String read;

    /**
     * 创建时间
     */
    @TableField("createAt")
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    @TableField("updateAt")
    private LocalDateTime updateAt;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
