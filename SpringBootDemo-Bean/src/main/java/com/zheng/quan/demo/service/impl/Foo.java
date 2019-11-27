package com.zheng.quan.demo.service.impl;

public class Foo {

	public void initMethod() {
		System.out.println("【" + this.getClass().getSimpleName() + "】初始化了。。");
	}
	
	public void destroy() {
		System.out.println("【" + this.getClass().getSimpleName() + "】被销毁了。。");
	}
	
}
