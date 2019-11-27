package com.zheng.quan.demo.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.zheng.quan.demo.handler.CustomDatabaseConditional;

/**
 * Description: 使用DBCP生成数据源 <br/> 
 * Project: SpringBootDemo-Bean <br/>
 * ClassName: DataSourceConfig.java <br/>
 * Copyright: Copyright (c) 2019 <br/>
 * @author ZHENG.Q <br/>
 * @version 1.0 2019年11月21日 下午8:22:17 <br/>
 */
@Configuration
public class DataSourceConfig {

	@Bean(name = "dataSource", destroyMethod = "close")
	@Conditional(CustomDatabaseConditional.class)
	public DataSource getDataSource2(@Value("${database.driverName}") String driverName,
			@Value("${database.url}") String url,
			@Value("${database.username}") String username,
			@Value("${database.password}") String password
			) {
		Properties props = new Properties();
		props.put("driver", driverName);
		props.put("url", url);
		props.put("username", username);
		props.put("password", password);
		
		DataSource dataSource = null;
		try {
			dataSource = BasicDataSourceFactory.createDataSource(props);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSource;
	}
	
//	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		Properties props = new Properties();
		props.put("driver", "com.mysql.jdbc.Driver");
		props.put("url", "jdbc:mysql://localhost:3306/demo2019");
		props.put("username", "root");
		props.put("password", "root123");
		
		DataSource dataSource = null;
		try {
			dataSource = BasicDataSourceFactory.createDataSource(props);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSource;
	}
	
}
