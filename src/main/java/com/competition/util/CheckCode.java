package com.competition.util;

import com.competition.form.PasswordForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@Slf4j
public class CheckCode {
    public Boolean checkcode(PasswordForm passwordForm, RedisTemplate<String,String> template){

        String nowcode =passwordForm.getCode();
        log.info(nowcode);

        String truecode=template.opsForValue().get(passwordForm.getAddress());
        log.info(truecode);
        if(nowcode.equals(truecode)) {
            System.out.println("chenggong");
            return true;
        }else{
            return false;
        }

    }
}
