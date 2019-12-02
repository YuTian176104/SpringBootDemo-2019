package com.zheng.quan.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zheng.quan.demo.entity.User;

/**
 * Description: 配置类 <br/> 
 * Project: SpringBootDemo <br/>
 * ClassName: UserConfig.java <br/>
 * Copyright: Copyright (c) 2019 <br/>
 * @author ZHENG.Q <br/>
 * @version 1.0 2019年10月31日 下午8:33:35 <br/>
 */
@Configuration
// 默认只会扫描本类所在包和其子包
//@ComponentScan
@ComponentScan(
		basePackages = {
		"com.zheng.quan.demo.*"
}
// basePackages不使用.*则excludeFilters不起作用
,
excludeFilters = {
//		@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Component.class}),
		@Filter(type = FilterType.ANNOTATION, classes = {Service.class})
})
public class UserConfig {

	/**
	 * 使用Bean注解让IOC容器装配Bean <br/>
	 * 注解的name属性指定生成Bean的名称
	 * 如果不使用name属性则方法名为生成的Bean名称
	 * @author ZHENG.Q <br/>
	 * @createDate 2019年10月31日 下午8:20:49 <br/>
	 * @return
	 */
//	@Bean(name = "user")
	@Bean
	public User initUser() {
		User user = new User();
		user.setUserId(1);
		user.setUserName("权政");
		user.setRemark("Hello World!");
		return user;
	}
	
}
