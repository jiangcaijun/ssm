package com.ssm.annotation;

import com.alibaba.fastjson.JSONObject;
import com.ssm.controller.BaseController;
import com.ssm.service.LogService;
import com.ssm.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Autor jiangcaijun
 * @Date 2017/11/9
 * @Time 13:38
 */
@Aspect
@Component
@Order(4)
public class ExceptionAspect {

    private static final Logger LOG = Logger.getLogger(ExceptionAspect.class);

    @Autowired
    private HttpServletRequest request;

    @Resource
    private LogService logService;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void exceptionAspect() {
    }

    @AfterReturning(returning = "rvt", pointcut = "exceptionAspect()")
    public void AfterReturning(JoinPoint joinPoint, Object rvt) {
        // 保存数据库
        logService.saveByJoinPoint(joinPoint,null);
    }

    @Around("exceptionAspect()")
    public Object around(ProceedingJoinPoint joinPoint){
        Object object = null;
        Result result = new Result();
        JSONObject jsonObject =  new JSONObject();
        try {
            object = joinPoint.proceed();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("出现Exception:url为" + request.getRequestURI() + ";错误类型为"+e.getStackTrace()[0]+"");
            String message = e.getMessage();
            if(StringUtils.isNotBlank(message)){
                if(message.length() > 100){
                    message = message.substring(0,100);
                }
                result.setMsg(message);
            }else {
                result.setMsg("" + e.getClass());
            }
            jsonObject.put("msg",result.getMsg());
            //将报错信息提交到数据库
            logService.saveByJoinPoint(joinPoint,e);
            //也可以按照需求，将报错信息发送给mail
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //request.getHeader("x-requested-with")为 null，则为传统同步请求，为 XMLHttpRequest，则为 Ajax 异步请求。
        if("XMLHttpRequest".equals(request.getHeader("x-requested-with"))){
            return result;//是ajax请求
        } else {
            //返回到错误页面
            ModelAndView mav = new ModelAndView("/errorpage/500");
            mav.addAllObjects(jsonObject);
            return mav;
        }
    }
}