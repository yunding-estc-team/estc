package com.competition.form;

import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * @author GuoHaodong
 * @date 2019-0806 16:26:05
 * DTO转换DO
 */
public interface IBaseDTO<T extends Model<T>> {

	/**
	 * 转化为Entity
	 * @return T类型的entity
	 */
	T toEntity();

	/**
	 * 从entity转换
	 * @param entity 需要转化成form的entity
	 */
	void fromEntity(T entity);
}
