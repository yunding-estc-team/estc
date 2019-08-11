package xyz.xlong99.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.competition.entity.UserAnnounce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xlong99.dao.AnnounceDao;
import xyz.xlong99.service.AnnounceService;

/**
 * @author xlong
 * @date 2019-08-08 09:33
 */
@Service
public class AnnounceServiceImpl extends ServiceImpl<AnnounceDao, UserAnnounce> implements AnnounceService {
    @Autowired
    private AnnounceDao announceDao;
}
