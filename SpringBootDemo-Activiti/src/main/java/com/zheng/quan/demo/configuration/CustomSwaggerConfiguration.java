package com.zheng.quan.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class CustomSwaggerConfiguration {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				// 详细信息定制
				.apiInfo(apiInfo())
				.select()
				// 指定当前包路径
				.apis(RequestHandlerSelectors.basePackage("com.zheng.quan.demo.controller"))
				// 扫描所有
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                 //标题
                .title("Spring Boot2中采用Swagger2构建RESTful APIs")
                .description("通过访问swagger-ui.html,实现接口测试、文档生成")
                .termsOfServiceUrl("http://localhost:9090")
                //设置联系方式
                .contact(new Contact("ZHENG.Q", "http://localhost:9090/swagger-ui.html", "xxxxx@qq.com"))
                .version("1.0.0")
                .build();
    }
}
