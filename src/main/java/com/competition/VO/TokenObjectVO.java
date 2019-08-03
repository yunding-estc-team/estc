package com.competition.VO;

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

}
