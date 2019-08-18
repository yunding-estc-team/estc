package com.competition.VO;

import com.competition.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsMutator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.Random;

/**
 * @author GuoHaodong
 * @date 2019-0731 16:26:19
 */
@Data
@EqualsAndHashCode

public class TokenObjectVO {

	/**
	 * 用户id
	 */
	private String id;
	/**
	 * 用户类型
	 */
	private String type;

	/**
	 * 用户转换成tokenObject
	 * @param u 用户对象需要有id,type
	 * @return tokenObt ject
	 */
	public static TokenObjectVO fromUser(User u){
		TokenObjectVO vo = new TokenObjectVO();
		vo.setId(u.getUserId());;
		vo.setType(u.getUserType());
		return vo;
	}

}
