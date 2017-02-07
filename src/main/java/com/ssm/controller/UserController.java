package com.ssm.controller;

import javax.annotation.Resource;

import com.ssm.model.User;
import com.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger LOG = Logger.getLogger(UserController.class);
	
//  下面两种@Resource只要使用一种即可,
//	@Resource(name="userService")
//	用@Resource后就不必使用@Autowired，可以降低耦合
//	用@Autowired
//	@Autowired
	private UserService userService;
	
	//最好是将@Resource放在setter方法上，因为这样更符合面向对象的思想，通过set、get去操作属性，而不是直接去操作属性。
	@Resource(name="userService")
	public void UserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 用户页
	 *
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userManager(Model model) {
		int id = 1;
        User user = userService.getUser(id);
        model.addAttribute("user", user);
		LOG.info(user.toString());
        return "user/showUser";
	}
	
	/**
	* @Title: testAjax
	* @Description: post请求（测试用）
	* @param @param model
	* @param @return    参数
	* @return String    返回类型
	* @throws
	*/
	@RequestMapping(value = "/testAjax", method = RequestMethod.POST)
	@ResponseBody
	public String test(Model model) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id","数据拿到了");
		
		return jsonObject.toString();
	}
	/**
	* @Title: testPOJO
	* @Description: TODO 这里没有在xml中启动Spring MVC的注解功能，却依然可以完成请求和注解POJO的映射
	* @param @param model
	* @param @return    参数
	* @return String    返回类型
	* @throws
	*/
	@RequestMapping(value = "/testPOJO", method = RequestMethod.POST)
	@ResponseBody
	public User testPOJO(Model model) {
		int id = 1;
        User user = userService.getUser(id);  
		return user;
	}
}