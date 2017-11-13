package com.ssm.utils;

import lombok.Data;

/**
 * 统一返回类接口
 * @Autor jiangcaijun
 * @Date 2017/11/9
 * @Time 10:21
 */
@Data
public class Result {
    /**
     * 表示返回成功或失败，true为成功
     */
    private Boolean success = true;

    private String msg;

    private Object obj;

}
