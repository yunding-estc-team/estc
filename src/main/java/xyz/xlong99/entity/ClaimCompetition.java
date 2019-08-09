package xyz.xlong99.entity;

/**
 * @author:Cui
 * @date:2019/8/9
 * @type: class(类)
 * @description:认领比赛的对象
 * @action:
 */
public class ClaimCompetition {

    private String id;

    private String userId;

    private String competitionId;

    private String realname;

    private String name;

    private String description;

    private String file;

    private String hash;

    private String checkout;

    @Override
    public String toString() {
        return "ClaimCompetition{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", competitionId='" + competitionId + '\'' +
                ", realname='" + realname + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", file='" + file + '\'' +
                ", hash='" + hash + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
