package com.ssm.aop;

import com.ssm.annotation.Log;
import com.ssm.model.OperLog;
import com.ssm.model.User;
import com.ssm.service.LogService;
import com.ssm.utils.IPAddressUtil;
import com.ssm.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.aop.ThrowsAdvice;
import com.ssm.utils.ConstantVar;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * aop：异常处理
 */
public class ExceptionHandler implements ThrowsAdvice{
    private static final Logger LOG = Logger.getLogger(ExceptionHandler.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LogService logService;

    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        LOG.error("出现Exception:url为" + request.getRequestURI() + ";错误类型为"+e.getStackTrace()[0]+"");
        OperLog operLog = new OperLog();
        StringBuffer operEvent = new StringBuffer();
        String descr4Exception = "";   // 具体错误信息

        try {
            String targetName = joinPoint.getTarget().getClass().getName(); // 请求类名称
            String methodName = joinPoint.getSignature().getName(); // 请求方法
            Object[] arguments = joinPoint.getArgs();
            Class<?> targetClass = null;
            targetClass = Class.forName(targetName);

            Method[] methods = targetClass.getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        if(method.getAnnotation(Log.class) != null){ // 如果包含注解@log()
                            operEvent.append(method.getAnnotation(Log.class).value());
                            operEvent.append("。");
                            break;
                        }
                    }
                }
            }
            operEvent.append("该方法实际入参为：");
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                operEvent.append(joinPoint.getArgs()[i]);
                operEvent.append(",");
            }
            operEvent.deleteCharAt(operEvent.length()-1); //删除最后一个 ","
            operEvent.append("。Exception类型为：");
            operEvent.append(e.getClass());
            descr4Exception = createExceptionDetail(e);
            LOG.error(descr4Exception);

            Subject curUser = SecurityUtils.getSubject();
            if (request.getRequestURI().contains("/logout")
                    && "logout".equalsIgnoreCase(joinPoint.getSignature().getName())) {
                // 退出日志
                String userId = (String) arguments[0];
                operLog.setUserId(userId);
            }
            if(curUser.getPrincipal()!=null){
                //从session中获取当前登录用户的User对象
                User loginUser = (User) curUser.getSession().getAttribute(ConstantVar.LOGIN_USER);
                operLog.setUserName(loginUser.getUserName());
                operLog.setUserId(loginUser.getId());
            }
            operLog.setClientIp(IPAddressUtil.getIpAddress(request));
        }catch (ClassNotFoundException e1) {
            e1.printStackTrace();
            LOG.error("实例化失败：ClassNotFoundException");
        }catch (IOException e2) {
            e2.printStackTrace();
            operLog.setClientIp("未知IP：IOException");
        }

        operLog.setReqUrl(request.getRequestURI());
        operLog.setMethod(joinPoint.getSignature().getDeclaringTypeName()+","+joinPoint.getSignature().getName());
        operLog.setOperEvent((operEvent.toString()).length()>255?(operEvent.toString()).substring(0,255):operEvent.toString());
        operLog.setOperStatus(ConstantVar.OPER_LOG_STATUS_FAIL);
        operLog.setLogDesc("具体Exception信息为："+ descr4Exception);
        try{
            // 保存到数据库
            logService.insertLog(operLog);
        }catch (Exception ex){
            ex.printStackTrace();
            LOG.error("log保存数据库失败");
        }

//        if(false){  //ResponseBody
//
//            return renderError("数据错误了，ExceptionHandler");
//        }
    }

    /**
     * 异常数组转成字符串
     *
     * @param e
     * @return
     * @author
     * @2016-8-18 下午5:43:20
     */
    private String createExceptionDetail(Exception e) {
        e.printStackTrace();
        StackTraceElement[] stackTraceArray = e.getStackTrace();
        StringBuilder detail = new StringBuilder();
        for (int i = 0; i < stackTraceArray.length; i++) {
            //255位，此处是考虑数据库相应字段的大小限制
            if((detail.toString()+stackTraceArray[i]).length() > 255){
                return detail.toString();
            }
            detail.append(stackTraceArray[i] + "\r\n");
        }
        return detail.toString();
    }

    /**
     * ajax失败
     * @param msg 失败的消息
     * @return {Object}
     */
    private Object renderError(String msg) {
        Result result = new Result();
        result.setMsg(msg);
        return result;
    }
}
