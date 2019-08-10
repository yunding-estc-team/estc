package xyz.xlong99.controller;

import com.competition.entity.Competition;
import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
import xyz.xlong99.form.CompetitionForm;
import xyz.xlong99.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xlong
 * @date 2019-08-05 20:49
 *
 */
@RestController("adminCompetitionController")
@RequestMapping("/admin/Competition")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;
//    /**
//     * 获取全部比赛信息
//     * @return
//     */
//    @RequestMapping("/getCompetitionList")
//    public ReturnVO getCompetitionList(){
//        return new ReturnVO(ReturnCode.SUCCESS,competitionService1.getCompetitionList());
//    }
    /**
     * 按照特定规则排序比赛
     */
    @RequestMapping("/orderCompetition")
    public ReturnVO orderCompetition(String page,String sort,String order){

        return new ReturnVO(ReturnCode.SUCCESS, competitionService.orderCompetition(page,sort,order));
    }
    /**
     * 添加赛事信息(用save方法)
     */
    @RequestMapping("/addCompetition")
    public ReturnVO addCompetition(CompetitionForm competitionForm){
        Competition competition = competitionForm.toEntity();
        competitionService.save(competition);
        return new ReturnVO(ReturnCode.SUCCESS);
    }
    /**
     * 修改赛事信息
     */
    @RequestMapping("/updateCompetition")
    public ReturnVO updateCompetition(Competition competition){
        competitionService.updateCompetition(competition);
        return new ReturnVO(ReturnCode.SUCCESS);
    }
    /**
     * 获取未审核的赛事
     */
    @RequestMapping("/getCheck")
    public ReturnVO getCheck(){
        return new ReturnVO(ReturnCode.SUCCESS, competitionService.getCheckoutList());
    }
    /**
     * 修改赛事审核状态
     */
    @RequestMapping("/updateCheck")
    public ReturnVO updateCheck(String competitionId,String code){
        competitionService.setCheckoutCompetition(competitionId,code);
        return new ReturnVO(ReturnCode.SUCCESS);
    }
    /**
     * 获取比赛认领申请
     */
    @RequestMapping("/getClaim")
    public ReturnVO getClaim(){
        return new ReturnVO(ReturnCode.SUCCESS, competitionService.getClaimList());
    }
    /**
     * 修改比赛认领状态
     */
    @RequestMapping("/updateClaim")
    public ReturnVO updateClaim(String competitionId,String code){
        competitionService.setClaimCompetition(competitionId,code);
        return new ReturnVO(ReturnCode.SUCCESS);
    }
}
