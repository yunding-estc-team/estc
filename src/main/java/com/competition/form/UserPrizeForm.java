package com.competition.form;

import lombok.Data;

@Data
public class UserPrizeForm  {
    /**
     * 赛事名称
     */
    String competitionName ;

    /**
     * 奖项名称
     */
    String reward;

    /**
     *  附件
     */
    String cover;

}
