package xyz.xlong99.service.impl;

import com.competition.entity.Competition;
import xyz.xlong99.dao.CompetitionDao;
import xyz.xlong99.entity.ClaimCompetition;
import xyz.xlong99.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author:Cui
 * @date:2019/8/8
 * @type:class(类)
 * @description:
 * @action:
 */
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionDao competitionDao;

    /**
     * 获取需要审核的比赛
     * @return competitionDao.selectCompetitionList()
     */
    @Override
    public List<Competition> getCheckoutList(Integer page) {
        Integer startNum = (page-1)*10;
        Integer lastNum = page*10;
        return competitionDao.selectCheckoutList(startNum,lastNum);
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
    public List<ClaimCompetition> getClaimList(Integer page) {
        Integer startNum = (page-1)*2;
        Integer lastNum = page*2;
        return competitionDao.selectClaimList(startNum,lastNum);
    }

    /**
     * 设置需认领比赛的code
     * @param claimCompetition
     * @param checkout
     */
    @Override
    public void setClaimCompetition(ClaimCompetition claimCompetition, String checkout) {
        competitionDao.updateClaim(claimCompetition.getId(),checkout);
        if(checkout.equals("1")){
            competitionDao.updateCompetitionHost(claimCompetition.getUserId(),claimCompetition.getCompetitionId());
        }

    }
}
