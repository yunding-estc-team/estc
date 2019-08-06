package com.competition.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.regex;

/**
 * @author GuoHaodong
 * @date 2019-0806 14:02:42
 */
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket devApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("dev")
				.genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(false)
				.pathMapping("/")
				.select()
				.build()
				.apiInfo(devApiInfo());
	}

	@Bean
	private ApiInfo devApiInfo(){
		return new ApiInfoBuilder()
				.title("ESTC")
				.description("")
				.version("1.0")
				.contact(new Contact("郭浩东","https://github.com/asparagusfern","asparagusfern@yeah.net"))
				.build();
	}
}
