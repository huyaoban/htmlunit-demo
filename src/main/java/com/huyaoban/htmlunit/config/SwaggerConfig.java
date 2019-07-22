package com.huyaoban.htmlunit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置
 *
 * @author jerry.hu
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
	public Docket htmlunitApi() {
		return new Docket(DocumentationType.SWAGGER_2).enable(true).apiInfo(htmlunitInfo()).groupName("review")
            .select()
            .apis(RequestHandlerSelectors
						.basePackage("com.huyaoban.htmlunit.controller"))
            .apis(RequestHandlerSelectors
                .withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.any()).build();
    }

	private ApiInfo htmlunitInfo() {
		return new ApiInfoBuilder().title("review接口").description("review restful接口").version("1.0.0").build();
    }
}
