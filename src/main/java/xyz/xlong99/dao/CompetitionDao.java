package xyz.xlong99.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface CompetitionDao extends BaseMapper<Competition  > {
//    /**
//     * 获取全部已审核的赛事
//     * @return
//     */
//    @Select("SELECT * FROM competition WHERE checkout = '1'")
//    List<Competition> getAllCompetition();

    /**
     * 按照规则排序比赛
     * @param orderSql
     * @param page 分页参数，(n-1)*10
     * @return
     */
    @Select("SELECT * FROM competition WHERE checkout = '1' ${orderSql} LIMIT ${page},10")
    List<Competition> orderCompetition(String orderSql,String page);

    /**
     * 更新赛事信息
     * @param competition 比赛
     */
    @Update("UPDATE competition " +
            "SET `name`=#{name},cover=#{cover},isIndividual=#{isIndividual},type=#{type},introduce=#{introduce},content=#{content},join_link=#{joinLink} " +
            "WHERE competition_id=#{competitionId}")
    void updateCompetition(Competition competition);

    /**
     * 获取未审核的赛事
     * @return
     */
    @Select("SELECT * FROM competition WHERE checkout = '0' ${orderSql} LIMIT #{startNum},10")
    List<Competition> selectCheckoutList(Integer startNum,String orderSql);

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
            "cc.user_id=u.user_id ${orderSql} LIMIT #{startNum},10 ")
    List<ClaimCompetition> selectClaimList(Integer startNum,String orderSql);

    /**
     * 设置认领比赛的成功与否
     * @param ClaimCompetitionId
     * @param checkout
     */
    @Update("UPDATE competition_checkout SET checkout=#{checkout} WHERE id=#{ClaimCompetitionId}")
    void updateClaim(String ClaimCompetitionId,String checkout);

    /**
     * 修改competition表
     * @param userId
     * @param competitionId
     */
    @Update("UPDATE competition SET host=#{userId} WHERE competition_id = #{competitionId}")
    void updateCompetitionHost(String userId,String competitionId);

}
