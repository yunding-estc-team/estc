package com.competition.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2019-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CompetitionCheckout extends Model<CompetitionCheckout> {

    private static final long serialVersionUID=1L;

    /**
     * 赛事id
     */
    @TableField("competitionId")
    private String competitionId;

    /**
     * 主键
     */
    private String id;

    /**
     * 审核材料路径
     */
    private String file;

    /**
     * 审核材料删除hash
     */
    private String hash;

    /**
     * 认领赛事,主办方id
     */
    private String userId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
