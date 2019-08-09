package xyz.xlong99.service.impl;

import com.competition.entity.Competition;
import xyz.xlong99.dao.CompetitionDao;
import xyz.xlong99.entity.ClaimCompetition;
import xyz.xlong99.service.CompetitionService1;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author:Cui
 * @date:2019/8/8
 * @type: class(类)
 * @description:
 * @action:
 */
public class CompetitionService1Impl implements CompetitionService1 {

    @Autowired
    private CompetitionDao competitionDao;

    /**
     * 获取需要审核的比赛
     * @return competitionDao.selectCompetitionList()
     */
    @Override
    public List<Competition> getCheckoutList() {
        return competitionDao.selectCheckoutList();
    }

    /**
     * 设置需要审核的比赛
     * @param competitionId
     * @param code
     */
    @Override
    public void setCheckoutCompetition(String competitionId, String code) {
        competitionDao.updateCheckout(competitionId,code);
    }

    /**
     * 获取有认领需求的对象
     * @return competitionDao.selectCheckoutList()
     */
    @Override
    public List<ClaimCompetition> getClaimList() {
        return competitionDao.selectClaimList();
    }

    /**
     * 设置需认领比赛的code
     * @param ClaimCompetitionId
     * @param checkout
     */
    @Override
    public void setClaimCompetition(String ClaimCompetitionId, String checkout) {
        competitionDao.updateClaim(ClaimCompetitionId,checkout);
    }
}
