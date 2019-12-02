package com.zheng.quan.demo.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Description: 使用Component注解让IOC容器扫描该类 <br/> 
 * 需要在配置类中使用ComponentScan注解扫描该类
 * Project: SpringBootDemo <br/>
 * ClassName: User.java <br/>
 * Copyright: Copyright (c) 2019 <br/>
 * @author ZHENG.Q <br/>
 * @version 1.0 2019年10月31日 下午8:23:55 <br/>
 */
@Component("user")// 使用属性名指定生成Bean的名称，否则类名首字母小写为Bean名
public class User {

	@Value(value = "2")
	private Integer userId;
	
	@Value(value = "李四")
	private String userName;
	
	@Value(value = "你好!")
	private String remark;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
