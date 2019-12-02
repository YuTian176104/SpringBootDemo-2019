package com.zheng.quan.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

	private static final Logger logger = LogManager.getLogger(DemoService.class);
	
	public void printUserInfo() {
		logger.info("DemoService User Service Implement.");
	}
	
}
