package xyz.xlong99.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.competition.entity.UserAnnounce;
import com.competition.form.FileForm;
import com.competition.response.ReturnCode;
import com.competition.response.ReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.xlong99.form.AnnounceForm;
import xyz.xlong99.service.AnnounceService;
import xyz.xlong99.service.impl.AnnounceServiceImpl;

/**
 * @author xlong
 * @date 2019-08-06 17:07
 */
@RestController("adminAnnounceController")
@RequestMapping("/admin/Announce")
public class AnnounceController {
    @Autowired
    private AnnounceService announceService;
    /**
     * 给用户发通知,给全体用户发通知id为allUser，用户群体id为student和organization
     */
    @RequestMapping("/add")
    public ReturnVO announceOne(@RequestBody AnnounceForm announceForm){
        UserAnnounce userAnnounce = announceForm.toEntity();
        announceService.save(userAnnounce);
        return new ReturnVO(ReturnCode.SUCCESS);
    }

}
