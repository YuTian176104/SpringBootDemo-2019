package com.zheng.quan.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@Component
@ConfigurationProperties(prefix = "database")
public class CustomDataBaseProperties2 {

	private String driverName;
	
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

	public void setUsername(String username) {
		System.out.println("【" + this.getClass().getSimpleName() + "】-" + username);
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		System.out.println("【" + this.getClass().getSimpleName() + "】-" + password);
		this.password = password;
	}
}
