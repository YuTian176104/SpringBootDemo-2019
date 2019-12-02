package com.zheng.quan.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description: 使用Controller注解控制器时，返回数据需要使用ResponseBody注解接口 <br/>
 * 	使用RestController注解控制器时，不需要使用ResponseBody注解接口，默认是返回数据
 * 	但是返回页面，需要返回ModelAndView对象
 * Project: SpringBootDemo <br/>
 * ClassName: IndexController.java <br/>
 * Copyright: Copyright (c) 2019 <br/>
 * 
 * @author ZHENG.Q <br/>
 * @version 1.0 2019年10月30日 下午8:21:58 <br/>
 */
@RestController
@RequestMapping("/")
public class IndexController {

	/**
	 *	返回数据 <br/>
	 * @author ZHENG.Q <br/>
	 * @createDate 2019年10月30日 下午9:04:59 <br/>
	 * @return
	 */
	@RequestMapping
	public Map<String, Object> home() {
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("msg", "Hello World!");
		return resMap;
	}

	/**
	 * 	返回jsp页面
	 * TODO <br/>
	 * @author ZHENG.Q <br/>
	 * @createDate 2019年10月30日 下午9:05:41 <br/>
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}

}
