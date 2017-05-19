package com.ssm.dao;

import com.ssm.model.OperLog;

public interface OperLogMapper {

    int insert(OperLog operLog);

    int insertSelective(OperLog operLog);

}