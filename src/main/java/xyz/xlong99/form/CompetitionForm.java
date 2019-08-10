package xyz.xlong99.form;
import com.competition.entity.Competition;
import com.competition.entity.UserAnnounce;
import com.competition.form.IBaseDTO;
import lombok.Data;

import java.util.UUID;

/**
 * @author xlong
 * @date 2019-08-09 21:55
 */
@Data
public class CompetitionForm implements IBaseDTO<Competition> {
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

    @Override
    public Competition toEntity() {
        Competition competition = new Competition();
        competition.setCompetitionId(UUID.randomUUID().toString());
        competition.setName(this.name);
        competition.setCover(this.cover);
        competition.setIsIndividual(this.isIndividual);
        competition.setType(this.type);
        competition.setHost("admin");
        competition.setIntroduce(this.introduce);
        competition.setContent(this.content);
        competition.setJoinLink(this.joinLink);
        competition.setCheckout("1");
        return competition;
    }

    @Override
    public void fromEntity(Competition entity) {

    }


}
