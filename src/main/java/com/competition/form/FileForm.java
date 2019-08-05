package com.competition.form;

import lombok.Data;

/**
 * @author GuoHaodong
 * @date 2019-0804 21:26:10
 */
@Data
public class FileForm {
	/**
	 * 提交文件的路径
	 */
	private String path;

	/**
	 * 提交文件的删除hash
	 */
	private String hash;

	/**
	 * 若为认领赛事,赛事id
	 */
	private String id;
}
