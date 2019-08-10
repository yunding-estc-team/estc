package xyz.xlong99.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.competition.entity.Competition;
import org.springframework.stereotype.Service;
import xyz.xlong99.dao.CompetitionDao;
import xyz.xlong99.entity.ClaimCompetition;
import xyz.xlong99.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.xlong99.util.SortUtil;

import java.util.List;

/**
 * @author:Cui
 * @date:2019/8/8
 * @type: class(类)
 * @description:
 * @action:
 */
@Service
public class CompetitionServiceImpl extends ServiceImpl<CompetitionDao,Competition> implements CompetitionService {

    @Autowired
    private CompetitionDao competitionDao;

//    @Override
//    public List<Competition> getCompetitionList() {
//        return competitionDao.getAllCompetition();
//    }

    @Override
    public List<Competition> orderCompetition(String page,String sort,String order) {
        int newPage = Integer.parseInt(page);
        newPage = (newPage-1)*10;
        String orderSql = SortUtil.toSortSql(sort, order);
        return competitionDao.orderCompetition(orderSql,String.valueOf(newPage));
    }

    @Override
    public void updateCompetition(Competition competition) {
        competitionDao.updateCompetition(competition);
    }

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
