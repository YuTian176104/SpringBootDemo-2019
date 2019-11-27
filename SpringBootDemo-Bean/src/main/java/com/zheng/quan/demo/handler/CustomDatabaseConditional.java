package com.zheng.quan.demo.handler;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class CustomDatabaseConditional implements Condition {

	/**
	 * 	数据源装配条件 <br/>
	 * @author ZHENG.Q <br/>
	 * @createDate 2019年11月26日 下午8:31:57 <br/>
	 * @param context
	 * @param metadata
	 * @return
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// 取出环境配置
		Environment env = context.getEnvironment();
		// 判断属性配置文件中是否存在对应的数据源配置项目，完全匹配才会返回true即：满足条件可以装配
		return env.containsProperty("database.driverName")
				&& env.containsProperty("database.url")
				&& env.containsProperty("database.username")
				&& env.containsProperty("database.password");
	}

}
