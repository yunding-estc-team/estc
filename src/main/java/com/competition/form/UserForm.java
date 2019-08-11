package com.competition.form;

import com.competition.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * @author GuoHaodong
 * @date 2019-0804 18:26:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserForm extends User implements IBaseDTO<User>{

	private List<Map<String,String>> history;
//	private String password;
//	private String portrait;
//	private String introduction;
//	private String userName;
//	private String realName;
//	private Integer userNo;
//	private String userSchool;
//	private String userMajor;
//	private String userSex;
//	private String userBirth;
//	private String userPhone;
//	private String userEmail;
//	private String file;
//	private String hash;
//	private String isActive;


	/**
	 * form 转换entity
	 * @return UserEntity
	 */
	@Override
	public User toEntity() {
		return this;
	}

	@Override
	public void fromEntity(User entity) {
		//todo 按需要补齐
	}

}
