package com.zheng.quan.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zheng.quan.demo.configuration.AppConfig;
import com.zheng.quan.demo.entity.ScopeBean;
import com.zheng.quan.demo.service.Person;
import com.zheng.quan.demo.service.impl.BussinessPerson;

public class AppTest {

//	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
//		Person person = ctx.getBean(BussinessPerson.class);
//		person.services();
//		ctx.getBean("dataSource");
		
//		ScopeBean bean1 = ctx.getBean(ScopeBean.class);
//		ScopeBean bean2 = ctx.getBean(ScopeBean.class);
//		System.out.println(bean1.equals(bean2));
		ctx.close();
	}
	
}
