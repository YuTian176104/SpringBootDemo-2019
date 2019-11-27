package com.zheng.quan.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zheng.quan.demo.service.impl.Foo;

@Configuration
@ComponentScan(basePackages = {
		"com.zheng.quan.demo.*"
}, lazyInit = false)
// 延迟加载，默认是false
//@PropertySource(value = {"classpath:jdbc.properties"}, ignoreResourceNotFound = false)
public class AppConfig {

	@Bean(initMethod = "initMethod", destroyMethod = "destroy")
	public Foo foo() {
		return new Foo();
	}
	
}
