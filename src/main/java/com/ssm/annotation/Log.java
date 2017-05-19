package com.ssm.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

	/**
	 * 操作事件,idea test
	 */
	String value();
	/**
	 * 字段组装描述内容，
	 * 如{"name=名称","status=状态,1=成功;2=失败"}，
	 * 表单参数为：name=张三&status=1这样生成的描述信息为：
	 * 名称=张三,状态=成功
	 */
	String[] entry() default {};
}