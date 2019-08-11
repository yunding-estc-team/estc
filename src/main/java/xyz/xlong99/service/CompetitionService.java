package xyz.xlong99.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
public interface CompetitionService extends IService<Competition>{
//    /**
//     * 获取已审核的赛事列表
//     * @return
//     */
//    List<Competition> getCompetitionList();

    /**
     * 按规则排序获取赛事列表
     * @param page
     * @param order
     * @param sort
     * @return
     */
    List<Competition> orderCompetition(String page,String sort,String order);

    /**
     * 更新比赛信息
     * @param competition
     */
    void updateCompetition(Competition competition);

    /**
     * 获取未审核的赛事
     * @return
     */
    List<Competition> getCheckoutList(String page,String sort,String order);

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
    List<ClaimCompetition> getClaimList(String page,String sort,String order);

    /**
     * 设置认领比赛成功与否
     * @param claimCompetition
     * @param checkout
     */
    void setClaimCompetition(ClaimCompetition claimCompetition,String checkout);
}
