package com.ssm.service;

import com.ssm.model.OperLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @description：操作日志
 */
public interface LogService {

    void insertLog(OperLog log); // 插入日志

    void saveByJoinPoint(JoinPoint joinPoint, Exception e);
}
