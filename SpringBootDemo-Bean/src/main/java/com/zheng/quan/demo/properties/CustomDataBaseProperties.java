package com.zheng.quan.demo.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class CustomDataBaseProperties {

	@Value("${database.driverName}")
	private String driverName;
	
	@Value("${database.url}")
	private String url;
	
	private String username;
	
	private String password;

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	@Value("${database.username}")
	public void setUsername(String username) {
		System.out.println(username);
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	@Value("${database.password}")
	public void setPassword(String password) {
		System.out.println(password);
		this.password = password;
	}
}
