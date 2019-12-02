package com.zheng.quan.demo.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zheng.quan.demo.DemoService;
import com.zheng.quan.demo.configuration.UserConfig;
import com.zheng.quan.demo.entity.User;
import com.zheng.quan.demo.service.UserService;
import com.zheng.quan.demo.service.impl.UserServiceImpl;

public class IoCTest {

	private static final Logger logger = LogManager.getLogger(IoCTest.class);
	
	private static ApplicationContext ctx;
	
	public static void main(String[] args) {
		// 加载配置类，相当于加载了配置文件
		ctx = new AnnotationConfigApplicationContext(UserConfig.class);
//		User user = ctx.getBean(User.class);
		User user = (User) ctx.getBean("initUser");
		logger.info(user.getRemark());
		com.zheng.quan.demo.vo.User user2 = (com.zheng.quan.demo.vo.User) ctx.getBean("user");
		logger.info(user2.getRemark());
		UserService userService = ctx.getBean(UserServiceImpl.class);
		userService.printUserInfo();
//		DemoService demoService = (DemoService) ctx.getBean("demoService");
//		demoService.printUserInfo();
	}
	
}
