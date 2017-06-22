package com.ssm.annotation;

import com.ssm.model.OperLog;
import com.ssm.model.User;
import com.ssm.service.LogService;
import com.ssm.service.UserService;
import com.ssm.utils.ConstantVar;
import com.ssm.utils.IPAddressUtil;
import com.ssm.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 日志切面
 */
@Aspect
@Component
public class LogAspect {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserService userService;

	// 注入Service用于把日志保存数据库
	@Autowired
	private LogService logService;

	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

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
	public void after(JoinPoint joinPoint, Object rvt) {
		try {
			String targetName = joinPoint.getTarget().getClass().getName(); // 请求类名称
			String methodName = joinPoint.getSignature().getName(); // 请求方法
			Object[] arguments = joinPoint.getArgs();
			Class<?> targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();
			String value = "";
			StringBuffer descr = new StringBuffer();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					@SuppressWarnings("rawtypes")
					Class[] clazzs = method.getParameterTypes();
					if (clazzs.length == arguments.length) {
						if(method.getAnnotation(Log.class) != null){ // 如果包含注解@log()

							value = method.getAnnotation(Log.class).value();
							String[] anEntry = method.getAnnotation(Log.class).entry();
							for (String en : anEntry) {
								String[] entry = en.split(",");
								String[] nameArray = entry[0].split("=");
								String val = StringUtils.defaultString(request.getParameter(nameArray[0]), "");
								if (!StringUtils.isBlank(val)) {
									if (entry.length == 2) {
										String[] valueEntry = entry[1].split(";");
										for (String valueArray : valueEntry) {
											String[] vals = valueArray.split("=");
											if (vals[0].equalsIgnoreCase(val)) {
												val = vals[1];
												break;
											}
										}
									}
									descr.append(',');
									descr.append(nameArray[1]);
									descr.append('=');
									descr.append(val);
								}
							}
							if (descr.length() > 0) {
								descr.deleteCharAt(0);
							}
							break;
						}
					}
				}
			}
			OperLog operLog = new OperLog();

			if (request.getRequestURI().contains("/login") && "loginPost".equalsIgnoreCase(joinPoint.getSignature().getName())) {
				// 用户登录日志记录
				operLog.setUserId(request.getParameter("username"));
				Subject curUser = SecurityUtils.getSubject();
				User loginUser = (User) curUser.getSession().getAttribute(ConstantVar.LOGIN_USER);
				if (loginUser != null) {
					operLog.setUserId(loginUser.getId());
					operLog.setUserName(loginUser.getUserName());
					operLog.setOperStatus(ConstantVar.OPER_LOG_STATUS.OPER_LOG_STATUS_SUCCESS_4ENUM.getValue());
				} else {
					operLog.setOperStatus(ConstantVar.OPER_LOG_STATUS.OPER_LOG_STATUS_FAIL_4ENUM.getValue());
				}
			}else if (request.getRequestURI().contains("/logout")
					&& "logout".equalsIgnoreCase(joinPoint.getSignature().getName())) {
				// 退出日志
				Result result = (Result) rvt;
				String userName = result.getMsg();
				operLog.setUserId(userName);
				
		        User loginUser = userService.findUserByUserName(userName);
				operLog.setUserName(loginUser.getUserName());
			} else {
				Subject curUser = SecurityUtils.getSubject();
				if(curUser.getPrincipal()!=null){
					//从session中获取当前登录用户的User对象
					User loginUser = (User) curUser.getSession().getAttribute(ConstantVar.LOGIN_USER);
					operLog.setUserName(loginUser.getUserName());
					operLog.setUserId(loginUser.getId());
				}

			}
			if(new Integer(operLog.getOperStatus())!=null){
                operLog.setOperStatus(ConstantVar.OPER_LOG_STATUS.OPER_LOG_STATUS_SUCCESS_4ENUM.getValue());
            }
            operLog.setClientIp(IPAddressUtil.getIpAddress(request));
            operLog.setReqUrl(request.getRequestURI());
            joinPoint.getSignature();
            operLog.setMethod(joinPoint.getSignature().getDeclaringTypeName()+","+joinPoint.getSignature().getName());
            operLog.setOperEvent(value);
            operLog.setLogDesc("该方法实际入参为："+descr.toString()); // 描述信息
			// 保存数据库
			logService.insertLog(operLog);
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("后置通知异常:异常信息:", e.getMessage());
			e.printStackTrace();
		}
	}
}