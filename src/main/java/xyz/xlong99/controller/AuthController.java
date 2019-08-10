package xyz.xlong99.controller;

import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.xlong99.service.AuthService;

/**
 * @author xlong
 * @date 2019-08-06 10:10
 */
@RestController
@RequestMapping("/admin/Auth")
public class AuthController {
    @Autowired
    AuthService authService;

    /**
     * 获取认证中的申请
     *
     * @param method 查询类型，组织或者是学生，或者是得奖审核
     */
    @RequestMapping("/getCheckingUser")
    public ReturnVO getChecking(String method) {
        if ("organization".equals(method)) {

            System.out.println(method);

            return new ReturnVO(ReturnCode.SUCCESS, authService.getOrganizationAuth());

        } else if ("student".equals(method)) {

            return new ReturnVO(ReturnCode.SUCCESS, authService.getStudentAuth());

        } else if ("reward".equals(method)) {

            return new ReturnVO(ReturnCode.SUCCESS, authService.getRewardAuth());

        } else {

            return new ReturnVO(ReturnCode.FAILURE_2);

        }
    }
//    /**
//     * 获取认证中的获奖信息啊
//     */
//    @RequestMapping("/getCheckingReward")
//    public Map<String, String> getCheckingReward(){
//        Map<String, String> message = new HashMap<>(2);
//        return message;
//    }

    /**
     * 修改认证状态
     *
     * @param id     用户id或者得奖认证的id
     * @param method 修改类型，或者是得奖审核、比赛认领
     * @param code   状态代码
     */
    @RequestMapping("/updateAuth")
    public ReturnVO updateAuth(String id, String method, String code) {
        if ("organization".equals(method)) {
            authService.updateUserAuth(id, code);

            return new ReturnVO(ReturnCode.SUCCESS);

        } else if ("student".equals(method)) {
            authService.updateUserAuth(id, code);

            return new ReturnVO(ReturnCode.SUCCESS);

        } else if ("reward".equals(method)) {
            authService.updateRewardAuth(id,code);
            return new ReturnVO(ReturnCode.SUCCESS);

        } else {
            return new ReturnVO(ReturnCode.FAILURE_2);

        }

    }


}
