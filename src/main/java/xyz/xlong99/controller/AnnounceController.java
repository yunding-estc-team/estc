package xyz.xlong99.controller;

import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xlong
 * @date 2019-08-06 17:07
 */
@RestController
@RequestMapping("/admin/Announce")
public class AnnounceController {
    /**
     * 给单个用户发通知
     */
    @RequestMapping("/announceOne")
    public ReturnVO announceOne(){
        return new ReturnVO(ReturnCode.SUCCESS);
    }
    /**
     * 给全体用户发通知
     */
    @RequestMapping("/announceAll")
    public ReturnVO announceAll(){
        return new ReturnVO(ReturnCode.SUCCESS);
    }
}
