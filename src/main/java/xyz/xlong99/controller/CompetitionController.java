package xyz.xlong99.controller;

import java.util.HashMap;
import java.util.Map;

import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
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
    private CompetitionService competitionService1;
    /**
     * 获取全部比赛信息
     * @return
     */
    @RequestMapping("/getCompetitionList")
    public ReturnVO getCompetitionList(){
        return new ReturnVO(ReturnCode.SUCCESS);
    }
    /**
     * 按照特定规则排序比赛
     */
    @RequestMapping("/orderCompetition")
    public ReturnVO orderCompetition(String key,String rule){
        //todo 分页
        return new ReturnVO(ReturnCode.SUCCESS);
    }
    /**
     * 添加赛事信息
     */
    @RequestMapping("/addCompetition")
    public Map<String, String> addCompetition(){
        Map<String, String> message = new HashMap<>(2);
        return message;
    }
    /**
     * 修改赛事信息
     */
    @RequestMapping("/updateCompetition")
    public Map<String, String> updateCompetition(){
        Map<String, String> message = new HashMap<>(2);
        return message;
    }
    /**
     * 获取未审核的赛事
     */
    @RequestMapping("/getCheck")
    public ReturnVO getCheck(){
        return new ReturnVO(ReturnCode.SUCCESS,competitionService1.getCheckoutList());
    }
    /**
     * 修改赛事审核状态
     */
    @RequestMapping("/updateCheck")
    public ReturnVO updateCheck(String competitionId,String code){
        competitionService1.setCheckoutCompetition(competitionId,code);
        return new ReturnVO(ReturnCode.SUCCESS);
    }
    /**
     * 获取比赛认领申请
     */
    @RequestMapping("/getClaim")
    public ReturnVO getClaim(){
        return new ReturnVO(ReturnCode.SUCCESS,competitionService1.getClaimList());
    }
    /**
     * 修改比赛认领状态
     */
    @RequestMapping("/updateClaim")
    public ReturnVO updateClaim(String competitionId,String code){
        competitionService1.setClaimCompetition(competitionId,code);
        return new ReturnVO(ReturnCode.SUCCESS);
    }
}
