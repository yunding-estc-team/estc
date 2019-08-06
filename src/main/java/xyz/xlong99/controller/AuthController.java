package xyz.xlong99.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xlong
 * @date 2019-08-06 10:10
 */
@RestController
@RequestMapping("/admin/Auth")
public class AuthController {
    /**
     * 获取认证中的申请
     * @param method 查询类型，组织或者是学生，或者是得奖审核、比赛认领
     */
    @RequestMapping("/getCheckingUser")
    public Map<String, String> getChecking(String method){
        Map<String, String> message = new HashMap<>(2);
        return message;
    }
//    /**
//     * 获取认证中的获奖信息
//     */
//    @RequestMapping("/getCheckingReward")
//    public Map<String, String> getCheckingReward(){
//        Map<String, String> message = new HashMap<>(2);
//        return message;
//    }
    /**
     * 修改认证状态
     * @param method 修改类型，或者是得奖审核、比赛认领
     * @param code 状态代码
     */
    @RequestMapping("/updateAuth")
    public Map<String, String> updateAuth(String method,String code){
        Map<String, String> message = new HashMap<>(2);
        return message;
    }


}
