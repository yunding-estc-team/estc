package xyz.xlong99.util;

import com.competition.response.PermissionClass;
import xyz.xlong99.entity.Organization;
import xyz.xlong99.entity.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xlong
 * @date 2019-08-07 10:34
 */
public class ActiveUtil {


    /**
     * 设置单个组织对象PermissionClass权限
     * @param organization
     * @return
     */
    public static Organization permissionHelper (Organization organization){

        //生成Permission对象，并用isActive属性实例化
        PermissionClass permissionClass = new PermissionClass();
        String isActives = organization.getIsActive();
        List<String> list = new ArrayList<>(Arrays.asList(isActives.split(",")));
        if(list.contains("user:login")){
            permissionClass.setLogin(true);
        }
        if(list.contains("user:comment")){
            permissionClass.setComment(true);
        }
        if(list.contains("user:attention")){
            permissionClass.setAttention(true);
        }
        if(list.contains("user:wiki")){
            permissionClass.setWiki(true);
        }
        organization.setPermissionClass(permissionClass);
        return organization;
    }

    /**
     * 设置组织list对象的PermissionClass权限
     * @param list
     * @return
     */
    public static List<Organization> permissionOrgHelper(List<Organization> list){
        List<Organization> list1 = new ArrayList<>();
        for (Organization organization : list) {
            Organization organization1 = permissionHelper(organization);
            list1.add(organization1);
        }
        return list1;
    }
    /**
     * 设置单个组织对象PermissionClass权限
     * @param student
     * @return
     */
    public static Student permissionHelper (Student student){

        //生成Permission对象，并用isActive属性实例化
        PermissionClass permissionClass = new PermissionClass();
        String isActives = student.getIsActive();
        List<String> list = new ArrayList<>(Arrays.asList(isActives.split(",")));
        if(list.contains("user:login")){
            permissionClass.setLogin(true);
        }
        if(list.contains("user:comment")){
            permissionClass.setComment(true);
        }
        if(list.contains("user:attention")){
            permissionClass.setAttention(true);
        }
        if(list.contains("user:wiki")){
            permissionClass.setWiki(true);
        }
        student.setPermissionClass(permissionClass);
        return student;
    }

    /**
     * 设置组织list对象的PermissionClass权限
     * @param list
     * @return
     */
    public static List<Student> permissionStdHelper(List<Student> list){
        List<Student> list1 = new ArrayList<>();
        for (Student std : list) {
            Student student = permissionHelper(std);
            list1.add(student);
        }
        return list1;
    }


    /**
     * 调用方法修改权限
     * @param isActives
     * @param isActive
     */
    public static String setPermission(String isActives, String isActive){
        //定义存放权限的list
        List<String> list = new ArrayList<>(Arrays.asList(isActives.split(",")));
        //如果权限为空则添加
        if(list==null) {
            list.add(isActive);
        }else {
            if (list.contains(isActive)) {
                list.remove(isActive);
            } else {
                list.add(isActive);
            }
        }
        StringBuilder updateIsActive=new StringBuilder();
        for(String s : list){
            updateIsActive.append(",").append(s);
        }
        return updateIsActive.toString();
    }
}
