package xyz.xlong99.entity;

/**
 * @author xlong
 * @date 2019-08-08 16:44
 */
public class RewardAuth {
    /**
     * id，主键
     */
    private String id;
    /**
     * 比赛id
     */
    private String competitionId;
    /**
     * 比赛名字
     */
    private String name;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户姓名
     */
    private String realname;
    /**
     * 学校
     */
    private String userSchool;
    /**
     * 证书照片
     */
    private String certificate;
    /**
     * 删除照片对应的hash值
     */
    private String hash;
    /**
     * 是否经过审查,0未审查，1已通过，2未通过
     */
    private String checkout;
}
