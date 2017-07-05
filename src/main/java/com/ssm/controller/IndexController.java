package com.ssm.controller;

import com.ssm.annotation.Log;
import com.ssm.annotation.Redis;
import com.ssm.redis.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	private static final Logger LOG = Logger.getLogger(IndexController.class);

	@Autowired
	private RedisUtil redisUtil;

	@Log("进入index")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		LOG.info("进入index");
		return "hello";
	}

	@Redis
	@Log(value = "进入guest，此处模拟抛出异常")
	@RequestMapping(value = "/guestError", method = RequestMethod.GET)
	public String guestError(Model model) {
		LOG.info("进入guest的index");
		if(true) {
			throw new RuntimeException();
		}
		return "guest/guestIndex";
	}
	@Log(value = "进入guest", entry = { "parameter1=参数1","parameter2=参数2", })
	@RequestMapping(value = "/guest", method = RequestMethod.GET)
	public String guest(Model model,String parameter1,Integer parameter2) {
		LOG.info("进入guest的index");
		String key = parameter1;
		// 1.从缓存中命中
		try {
			String redisJson = redisUtil.get(key);
			if (StringUtils.isNotBlank(redisJson)) {
				LOG.info("从缓存中命中："+redisJson);
			}else{
				// 2.如果没被命中，执行原有逻辑
				LOG.info("没被命中");

				// 此处模拟从数据库获取数据
				String json = "getFromDB()";

				// 3.将查询出来的结果加入缓存
				redisUtil.set(key, json);
				redisUtil.expire(key, 60 * 60 * 24);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "guest/guestIndex";
	}
	
}