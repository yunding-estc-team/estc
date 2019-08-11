package com.competition.form;

import com.competition.entity.User;
import lombok.Data;

@Data
public class SearchPost   {
    /**
     * 学生姓名
     */
    String user_name;
    /**
     * 赛事名称
     */
    String name;
    /**
     * 学生自我介绍
     */
    String introduction;
    /**
     * 赛事简介
     */
    String introduce;



}
