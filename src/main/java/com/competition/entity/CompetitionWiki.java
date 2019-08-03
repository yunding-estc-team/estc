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
 * @since 2019-08-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CompetitionWiki extends Model<CompetitionWiki> {

    private static final long serialVersionUID=1L;

    /**
     * 唯一标识的问答id
     */
    private String wikiId;

    /**
     * 比赛id
     */
    private String competitionId;

    /**
     * 提问人id
     */
    private String userId;

    /**
     * 内容
     */
    private String content;

    @TableField("createAt")
    private LocalDateTime createAt;

    @TableField("updateAt")
    private LocalDateTime updateAt;


    @Override
    protected Serializable pkVal() {
        return this.wikiId;
    }

}
