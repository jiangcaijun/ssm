package com.ssm.annotation;

import com.ssm.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 */
@Aspect
@Component
@Order(3)
public class LogAspect {

	private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

	// 注入Service用于把日志保存数据库
	@Autowired
	private LogService logService;

	// Controller层切点
	@Pointcut("@annotation(com.ssm.annotation.Log)") //@annotation用于匹配当前执行方法持有指定注解的方法；
	public void logAspect() {
	}

	/**
	 * 后置通知 用于拦截Controller层记录用户的操作
	 *
	 * @param joinPoint
	 *            切点
     * @param rvt
     *            指定一个 returning 属性，该属性值为 rvt , 表示 允许在 增强处理方法中使用名为rvt的形参，该形参代表目标方法的返回值。
	 */
	@AfterReturning(returning = "rvt", pointcut = "logAspect()")
	public void AfterReturning(JoinPoint joinPoint, Object rvt) {
		// 保存数据库
		logService.saveByJoinPoint(joinPoint,null);
	}
}