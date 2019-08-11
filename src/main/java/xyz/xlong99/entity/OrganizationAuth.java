package xyz.xlong99.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xlong
 * @date 2019-08-08 16:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrganizationAuth {
    /**
     * 用户唯一标识,采用uuid(主键)
     */
    private String userId;

    /**
     * 昵称
     */
    private String userName;
    /**
     * 学校
     */
    private String userSchool;
    /**
     * 专业
     */
    private String userMajor;
    /**
     * 认证文件照片
     */
    private String file;
    /**
     * 删除照片对应的hash值
     */
    private String hash;
    /**
     * 是否经过审查,0未审查，1已通过，2未通过
     */
    private String checkout;
}
