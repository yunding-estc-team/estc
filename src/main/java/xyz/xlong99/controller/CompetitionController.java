package xyz.xlong99.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xlong
 * @date 2019-08-05 20:49
 */
@RestController
@RequestMapping("/admin/Competition")
public class CompetitionController {
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
    public Map<String, String> getCheck(){
        Map<String, String> message = new HashMap<>(2);
        return message;
    }
    /**
     * 修改赛事审核状态
     */
    @RequestMapping("/updateCheck")
    public Map<String, String> updateCheck(){
        Map<String, String> message = new HashMap<>(2);
        return message;
    }
    /**
     * 获取比赛认领申请
     */
    @RequestMapping("/getClaim")
    public Map<String, String> getClaim(){
        Map<String, String> message = new HashMap<>(2);
        return message;
    }
    /**
     * 修改比赛认领状态
     */
    @RequestMapping("/updateClaim")
    public Map<String, String> updateClaim(){
        Map<String, String> message = new HashMap<>(2);
        return message;
    }
}
