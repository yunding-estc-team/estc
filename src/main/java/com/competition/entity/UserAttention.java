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
 * @since 2019-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserAttention extends Model<UserAttention> {

    private static final long serialVersionUID=1L;

    /**
     * 对应user表的id
     */
    @TableId
    private String userId;

    /**
     * 对应competition表的id
     */
    private String competitionId;

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

    private String id;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
