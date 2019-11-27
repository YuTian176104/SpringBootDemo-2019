package com.zheng.quan.demo.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.zheng.quan.demo.service.Animal;

@Component
@Primary
public class Cat implements Animal {

	@Override
	public void use() {
		System.out.println("猫【" + Cat.class.getSimpleName() + "】是用来抓老鼠的。");
	}

}
