package xyz.xlong99.service;

import com.competition.entity.Competition;
import xyz.xlong99.entity.ClaimCompetition;

import java.util.List;

/**
 * @author:Cui
 * @date:2019/8/8
 * @type: class(类)
 * @description:
 * @action:
 */
public interface CompetitionService {

    /**
     * 获取未审核的赛事
     * @return
     */
    List<Competition> getCheckoutList();

    /**
     * 设置审核赛事状态
     * @param competitionId
     * @param code
     */
    void setCheckoutCompetition(String competitionId,String code);

    /**
     * 获取认领比赛的列表
     * @return
     */
    List<ClaimCompetition> getClaimList();

    /**
     * 设置认领比赛成功与否
     * @param ClaimCompetitionId
     * @param checkout
     */
    void setClaimCompetition(String ClaimCompetitionId,String checkout);
}
