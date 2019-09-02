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
public class Competition extends Model<Competition> {

    private static final long serialVersionUID=1L;

    /**
     * 唯一标识的比赛id(主键)
     */
    @TableId
    private String competitionId;

    /**
     * 比赛名称
     */
    private String name;

    /**
     * 封面海报的图片路径
     */
    private String cover;

    /**
     * 是否为个人赛,1true0false
     */
    private String isIndividual;

    /**
     * 比赛类型
     */
    private String type;

    /**
     * 主办方id
     */
    private String host;

    /**
     * 比赛介绍
     */
    private String introduce;

    /**
     * 比赛内容(主题)
     */
    private String content;

    /**
     * 报名链接
     */
    private String joinLink;

    /**
     * 保研率
     */
    private Integer rgr;

    /**
     * 是否经过审查,0未审查，1已通过，2未通过
     */
    private String checkout;

    /**
     * 点击量
     */
    private Integer clickCount;

    /**
     * 热度值
     */
    private Integer hot;


    @TableField("createAt")
    private LocalDateTime createAt;

    @TableField("updateAt")
    private LocalDateTime updateAt;


    @Override
    protected Serializable pkVal() {
        return this.competitionId;
    }

}
