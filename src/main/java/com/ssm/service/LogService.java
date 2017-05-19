package com.ssm.service;

import com.ssm.model.OperLog;

/**
 * @description：操作日志
 */
public interface LogService {

    public void insertLog(OperLog log); // 插入日志

}
