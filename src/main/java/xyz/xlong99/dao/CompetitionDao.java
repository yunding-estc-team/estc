package xyz.xlong99.dao;

import com.competition.entity.Competition;
import xyz.xlong99.entity.ClaimCompetition;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author:Cui
 * @date:2019/8/8
 * @type:interface(接口)
 * @description:
 * @action:
 */
public interface CompetitionDao {

    /**
     * 获取未审核的赛事
     * @return
     */
    @Select("SELECT * FROM competition WHERE checkout = '0'")
    List<Competition> selectCheckoutList();

    /**
     * 审核赛事状态
     * @param competitionId
     * @param code
     */
    @Update("UPDATE competition SET checkout=#{code} WHERE competition_id=#{competitionId}")
    void updateCheckout(String competitionId,String code);

    /**
     * 获取认领比赛的列表
     * @return
     */
    @Select("SELECT cc.user_id,cc.id,cc.`hash`,cc.file,cc.description,cc.competition_id,cc.checkout,c.`name`,u.realname " +
            "FROM " +
            "competition_checkout cc INNER JOIN competition c ON " +
            "cc.competition_id = c.competition_id INNER JOIN `user` u ON " +
            "cc.user_id=u.user_id ; ")
    List<ClaimCompetition> selectClaimList();

    /**
     * 设置认领比赛的成功与否
     * @param ClaimCompetitionId
     * @param checkout
     */
    @Update("UPDATE competition_checkout SET checkout=#{checkout} WHERE id=#{ClaimCompetitionId}")
    void updateClaim(String ClaimCompetitionId,String checkout);
}
