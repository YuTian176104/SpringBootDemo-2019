package com.zheng.quan.demo.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zheng.quan.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Override
	public void printUserInfo() {
		logger.info("User Service Implement.");
	}

}
