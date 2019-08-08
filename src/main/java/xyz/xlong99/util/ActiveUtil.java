package xyz.xlong99.util;

import com.competition.dao.UserMapper;
import com.competition.response.PermissionClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xlong
 * @date 2019-08-07 10:34
 */
public class ActiveUtil {
    @Autowired
    private UserMapper userMapper;

    public  PermissionClass getIsActive(String userId){
        PermissionClass permissionClass = new PermissionClass();
        String isActives = userMapper.selectById(userId).getIsActive();
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
        if(list.contains("user:praise")){
            permissionClass.setPraise(true);
        }
        if(list.contains("user:wiki")){
            permissionClass.setWiki(true);
        }
        return permissionClass;
    }
}
