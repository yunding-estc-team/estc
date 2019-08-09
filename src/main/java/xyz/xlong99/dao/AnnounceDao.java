package xyz.xlong99.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.competition.entity.Competition;
import com.competition.entity.UserAnnounce;
import org.apache.ibatis.annotations.Insert;

/**
 * @author xlong
 * @date 2019-08-08 08:49
 */
public interface AnnounceDao extends BaseMapper<UserAnnounce> {

}
