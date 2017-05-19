package com.ssm.service.impl;

import com.ssm.dao.OperLogMapper;
import com.ssm.model.OperLog;
import com.ssm.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private OperLogMapper operLogMapper;

    @Override
    public void insertLog(OperLog operLog) {
        operLogMapper.insertSelective(operLog);
    }

}
