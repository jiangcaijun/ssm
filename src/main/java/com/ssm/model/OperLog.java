package com.ssm.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/***
 * @description 对应t_log表
 * */
@Data
public class OperLog implements Serializable {

	private static final long serialVersionUID = -8690056878905494181L;

	private Long id;
	private String userId;// '操作用户ID',
	private String userName;// '操作人名称',
	@JSONField (format="yyyy-MM-dd HH:mm:ss") 
	private Date operTime;// '操作时间(yyyy-MM-dd HH:mm:ss)',
	private String clientIp;// '登录IP',
	private String reqUrl;// 访问url
	private String method;// 请求方法
	private String operEvent;// 操作事件（删除，新增，修改，查询，登录，退出）',
	private int operStatus;// '操作状态（1：成功，2：失败）',
	private String logDesc;// 描述信息',
}