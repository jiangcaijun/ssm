package com.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.ssm.annotation.Log;
import com.ssm.redis.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController{

	private Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private RedisUtil redisUtil;

	@Log("进入index")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		LOG.info("进入index");
		return "index";
	}

	/**
	 * 获取request的hashCode，验证其是否线程安全
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/testRequestHashCode", method = RequestMethod.POST)
	public Object testRequestHashCode(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("requestHashCode",request.hashCode());
		return renderSuccess(jsonObject.toJSONString());
	}

	@RequestMapping(value = "/activeMQTest", method = RequestMethod.GET)
	public String activeMQTest(Model model) {
		return "guest/activeMQTest";
	}


}