package com.ssm.service.impl;

import com.ssm.annotation.Log;
import com.ssm.dao.OperLogMapper;
import com.ssm.model.OperLog;
import com.ssm.model.User;
import com.ssm.service.LogService;
import com.ssm.utils.ConstantVar;
import com.ssm.utils.IPAddressUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;

@Service(value="logService")
public class LogServiceImpl implements LogService {

    private static final Logger LOG = Logger.getLogger(LogServiceImpl.class);

    @Autowired
    private OperLogMapper operLogMapper;
    @Autowired
    private HttpServletRequest request;

    @Override
    public void insertLog(OperLog operLog) {
        operLogMapper.insertSelective(operLog);
    }

    @Override
    public void saveByJoinPoint(JoinPoint joinPoint, Exception e) {
        OperLog operLog = new OperLog();
        StringBuffer operEvent = new StringBuffer();

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

            if(joinPoint.getArgs().length > 0){
                operEvent.append("该方法实际入参为：");
                for (int i = 0; i < joinPoint.getArgs().length; i++) {
                    operEvent.append(joinPoint.getArgs()[i]);
                    operEvent.append(",");
                }
                operEvent.deleteCharAt(operEvent.length()-1); //删除最后一个 ","
                operEvent.append("。");
            }
            if(e != null){
                operEvent.append("Exception类型为：");
                operEvent.append(e.getClass());
                operLog.setLogDesc("具体Exception信息为："+ createExceptionDetail(e));
            }

            Subject curUser = SecurityUtils.getSubject();
            if (request.getRequestURI().contains("/logout")
                    && "logout".equalsIgnoreCase(joinPoint.getSignature().getName())) {
                // 退出日志
//                String userId = (String) arguments[0];
//                operLog.setUserId(userId);
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
        }catch (Exception e3){
            e3.printStackTrace();
        }

        operLog.setReqUrl(request.getRequestURI());
        operLog.setMethod(joinPoint.getSignature().getDeclaringTypeName()+","+joinPoint.getSignature().getName());
        operLog.setOperEvent((operEvent.toString()).length()>255?(operEvent.toString()).substring(0,255):operEvent.toString());
        if(e != null){
            operLog.setOperStatus(ConstantVar.OPER_LOG_STATUS.OPER_LOG_STATUS_FAIL_4ENUM.getValue());
        }else{
            operLog.setOperStatus(ConstantVar.OPER_LOG_STATUS.OPER_LOG_STATUS_SUCCESS_4ENUM.getValue());
        }
        operLogMapper.insertSelective(operLog);
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
}
