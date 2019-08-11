package xyz.xlong99.controller;

import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
import xyz.xlong99.util.ActiveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.xlong99.entity.Organization;
import xyz.xlong99.entity.Student;
import xyz.xlong99.service.UserService;

import java.util.List;

/**
 * @author xlong
 * @date 2019-08-05 20:41
 *
 */
@RestController("adminUserController")
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取学生列表
     * @return
     */
    @RequestMapping("/getStudent")
    public ReturnVO getStudent(String page,String sort,String order){
        List<Student> students = userService.findAllStudent(page, sort, order);
        return new ReturnVO(ReturnCode.SUCCESS,ActiveUtil.permissionStdHelper(students));
    }

    /**
     * 获取组织列表
     * @return
     */
    @RequestMapping("/getOrganization")
    public ReturnVO getOrganization(String page,String sort,String order){
        List<Organization> organizations = userService.findAllOrganization(page, sort, order);
        return new ReturnVO(ReturnCode.SUCCESS,ActiveUtil.permissionOrgHelper(organizations));
    }

    /**
     * 修改学生资料
     * @param
     * @return
     */
    @RequestMapping("/updateStudent")
    public ReturnVO updateStudent(Student student){
        userService.updateStudent(student);
        return new ReturnVO(ReturnCode.SUCCESS);
    }
    /**
     * 修改组织资料
     * @param
     * @return
     */
    @RequestMapping("/updateOrgnization")
    public ReturnVO updateOrgnization(Organization organization){
        userService.updateOrganization(organization);
        return new ReturnVO(ReturnCode.SUCCESS);
    }

    /**
     * 修改权限
     * @param userId
     * @param isActive
     * @return
     */
    @RequestMapping("/changePermission")
    public ReturnVO changePermission(String userId,String isActive){
        userService.changePermission(userId,isActive);
        return new ReturnVO(ReturnCode.SUCCESS);
    }

}
