package com.ssm.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	private static final Logger LOG = Logger.getLogger(IndexController.class);

	@RequestMapping("index")
	public String index(){
		LOG.info("进入index");
		return "hello";
	}
	
}
