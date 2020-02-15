package com.zheng.quan.demo.configuration;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomProcessEngineConfiguration implements ProcessEngineConfigurationConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(CustomProcessEngineConfiguration.class);
	
	@Override
	public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
		processEngineConfiguration.setActivityFontName("宋体");
		processEngineConfiguration.setLabelFontName("宋体");
		processEngineConfiguration.setAnnotationFontName("宋体");
		logger.info(">>>流程图字体设置完成...");
		logger.info(processEngineConfiguration.getActivityFontName());
	}

}
