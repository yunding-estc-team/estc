package xyz.xlong99.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 获奖信息审核实体类
 * @author xlong
 * @date 2019-08-08 10:49
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Reward {
    private String competitionId;
    private String userId;
    private String reward;
    private String certificate;
}
