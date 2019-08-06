package xyz.xlong99.controller;

import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xlong
 * @date 2019-08-05 20:41
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {
    /**
     * 获取用户列表
     * @param method 用户类型，学生或组织
     */
    @RequestMapping("/getUser")
    public ReturnVO getUser(String method){
        return new ReturnVO(ReturnCode.SUCCESS);
    }

    /**
     * 修改用户资料
     * @param
     * @return
     */
    @RequestMapping("/updateUser")
    public ReturnVO updateUser(){
        return new ReturnVO(ReturnCode.SUCCESS);
    }
    /**
     * 修改权限
     */
    @RequestMapping("/changeActive")
    public ReturnVO changeActive(){
        return new ReturnVO(ReturnCode.SUCCESS);
    }
}
