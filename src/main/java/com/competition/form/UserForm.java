package com.competition.form;

import com.competition.entity.User;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author GuoHaodong
 * @date 2019-0804 18:26:14
 */
@Data
public class UserForm extends User {
	private List<Map<String,String>> history;

}
