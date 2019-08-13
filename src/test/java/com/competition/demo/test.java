package com.competition.demo;

import xyz.xlong99.entity.Student;
import xyz.xlong99.service.CompetitionService;
import xyz.xlong99.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author:Cui
 * @date:2019/8/8
 * @type:class(类)
 * @description:
 * @action:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class test {
    @Autowired
    private UserService userService1;

    @Autowired
    private CompetitionService competitionService1;
    @Test
    public void testupdate(){
        Student student = new Student();
        student.setUserId("492bc0e49fd54b76aeb0c8babbce906b");
//        student.setIsActive("user:wiki,user:comment,user:praise");
        student.setIntroduction("sad");
        student.setPortrait("asd");
        student.setRealname("adsd");
        userService1.changePermission(student.getUserId(),"user:attention");
    }
    @Test
    public void testSetPermission(){

    }
}
