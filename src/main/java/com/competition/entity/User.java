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
public class User extends Model<User> {

    private static final long serialVersionUID=1L;

    /**
     * 用户唯一标识,采用uuid(主键)
     */
    @TableId
    private Integer userId;

    /**
     * md5加密后存储
     */
    private String password;

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
    private Integer userPhone;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 认证文件照片
     */
    private String file;

    /**
     * 删除照片对应的hash值
     */
    private String hash;

    private String isActive;

    private String ipAddress;

    /**
     * 是否经过审查,0未审查，1已通过，2未通过
     */
    private String check;

    @TableField("createAt")
    private LocalDateTime createAt;

    @TableField("updateAt")
    private LocalDateTime updateAt;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
