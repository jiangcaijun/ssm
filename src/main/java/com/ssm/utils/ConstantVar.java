package com.ssm.utils;

import lombok.Data;

/**
 * @Autor jiangcaijun
 * @Date 2017/11/9
 * @Time 10:20
 */
public class ConstantVar {
    public static final String LOGIN_USER = "loginUser";

    public enum OPER_LOG_STATUS {
        OPER_LOG_STATUS_SUCCESS_4ENUM(1),
        OPER_LOG_STATUS_FAIL_4ENUM(2);

        private int value;

        private OPER_LOG_STATUS(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
