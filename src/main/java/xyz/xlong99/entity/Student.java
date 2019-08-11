package xyz.xlong99.entity;
import com.competition.response.PermissionClass;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author xlong
 * @date 2019-08-07 08:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Student  {
    private static final long serialVersionUID=1L;

    /**
     * 用户唯一标识,采用uuid(主键)
     */

    private String userId;

    /**
     * 头像的url,有logo之后增加默认值
     */
    private String portrait;

    /**
     * 自我介绍
     */
    private String introduction;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 学号，比赛发布者为空
     */
    private Integer userNo;

    /**
     * 学校
     */
    private String userSchool;

    /**
     * 专业
     */
    private String userMajor;

    /**
     * 0女1男
     */
    private String userSex;

    /**
     * 生日
     */
    private String userBirth;
    /**
     * 手机号
     */
    private String userPhone;

    /**
     * 邮箱
     */
    private String userEmail;

    private String isActive;

    private String ipAddress;

    /**
     * 是否经过审查,0未审查，1已通过，2未通过
     */
    private String checkout;

    @TableField("createAt")
    private LocalDateTime createAt;

    @TableField("updateAt")
    private LocalDateTime updateAt;

    /**
     * 用户类型标示
     */
    private String userType;
    /**
     * 权限
     */
    private PermissionClass permissionClass;
}
