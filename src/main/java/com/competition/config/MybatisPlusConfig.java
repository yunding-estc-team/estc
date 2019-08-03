package com.competition.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author GuoHaodong
 * @date 2019-0803 11:03:43
 */
@EnableTransactionManagement
public class MybatisPlusConfig {

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		 paginationInterceptor.setLimit(10);
		return paginationInterceptor;
	}
}
