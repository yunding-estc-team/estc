package xyz.xlong99.form;

import com.baomidou.mybatisplus.annotation.TableId;
import com.competition.entity.UserAnnounce;
import com.competition.form.IBaseDTO;
import lombok.Data;

import java.util.UUID;

/**
 * @author xlong
 * @date 2019-08-08 10:13
 */
@Data
public class AnnounceForm implements IBaseDTO<UserAnnounce> {

    /**
     * 被通知的用户id
     */
    private String userId;
    /**
     * 通知的内容
     */
    private String announce;
    /**
     * 标题
     */
    private String title;

    @Override
    public UserAnnounce toEntity() {
        UserAnnounce userAnnounce = new UserAnnounce();
        userAnnounce.setId(UUID.randomUUID().toString());
        userAnnounce.setUserId(this.userId);
        userAnnounce.setAnnounce(this.announce);
        userAnnounce.setTitle(this.title);
        return null;
    }

    @Override
    public void fromEntity(UserAnnounce entity) {

    }
}
