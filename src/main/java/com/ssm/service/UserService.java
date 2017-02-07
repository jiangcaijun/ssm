package com.ssm.service;

import com.ssm.model.User;

public interface UserService {
	public int insert(User user);
	
	
	/**
	* @Description: 根据id获取user
	* @param @param id
	* @param @return    参数
	* @return User    返回类型
	*/
	public User getUser(int id);
}
