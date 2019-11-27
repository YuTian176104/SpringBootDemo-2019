package com.zheng.quan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:jdbc.properties"}, ignoreResourceNotFound = false)
// ignoreResourceNotFound 默认是false即：找不到配置文件会报错
public class SpringBootDemoBeanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoBeanApplication.class, args);
	}

}
