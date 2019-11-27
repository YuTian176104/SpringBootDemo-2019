package com.zheng.quan.demo.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.zheng.quan.demo.service.Animal;
import com.zheng.quan.demo.service.Person;

//@Component
public class BussinessPerson implements Person, 
// 实现以下接口演示Bean的定义、初始化、生存期、销毁（Bean的生命周期）
	BeanNameAware, BeanFactoryAware, 
	ApplicationContextAware, InitializingBean, DisposableBean {
	
	private static final Logger logger = LoggerFactory.getLogger(BussinessPerson.class);

	/**
	 * 依赖注入，默认是根据类型
	 * Dog是Animal的实现类
	 * Cat也是Animal的实现类
	 * 默认是根据类型注入，当有两个相同类型的实现时，便会根据属性的名称和Bean的名称进行匹配
	 */
	
	/**
	 * 使用带有参数的构造方法进行装配
	 * 此处Qualifier注解是为了避免歧义性
	 */
	private Animal animal;
	
	public BussinessPerson(@Autowired @Qualifier("dog") Animal animal) {
		System.out.println("延迟依赖注入");
		this.animal = animal;
	}

	@Override
	public void services() {
		this.animal.use();
	}

	@Override
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	@PostConstruct
	public void init() {
		System.out.println("【" + this.getClass().getSimpleName() + "】调用使用注解@PostConstruct定义的自定义初始化方法init()");
	}
	
	@PreDestroy
	public void preDestroy() {
		System.out.println("【" + this.getClass().getSimpleName() + "】调用使用注解@PreDestroy定义的自定义销毁方法preDestroy()");
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("【" + this.getClass().getSimpleName() + "】	DisposableBean方法");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("【" + this.getClass().getSimpleName() + "】调用InitializingBean的afterPropertiesSet方法");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("【" + this.getClass().getSimpleName() + "】调用ApplicationContextAware的setApplicationContext方法");
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("【" + this.getClass().getSimpleName() + "】调用BeanFactoryAware的setBeanFactory方法");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("【" + this.getClass().getSimpleName() + "】调用BeanNameAware的setBeanName方法");
	}
	
	/**
	 * 使用Qualifier注解消除歧义性
	 */
//	@Autowired
//	@Qualifier(value = "dog")
//	private Animal animal;
//	
//	@Override
//	public void services() {
//		this.animal.use();
//	}
//
//	@Override
//	public void setAnimal(Animal animal) {
//		this.animal = animal;
//	}
	
	/**
	 * 当根据类型不能唯一确定注入对象时，根据Bean名称注入
	 */
////	@Autowired
//	private Animal dog;
//	
//	@Override
//	public void services() {
//		this.dog.use();
//	}
//
//	@Override
//	@Autowired
//	public void setAnimal(Animal dog) {
//		this.dog = dog;
//	}
	
	/**
	 * 默认根据类型注入
	 */
//	@Autowired
//	private Animal animal;
//	
//	@Override
//	public void services() {
//		this.animal.use();
//	}
//
//	@Override
//	public void setAnimal(Animal animal) {
//		this.animal = animal;
//	}

}
