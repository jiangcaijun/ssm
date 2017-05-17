package com.ssm.shiro;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * @description：自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 */
@Data
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = -1373760761780840081L;
    public String userId;
    public String userName;

    public ShiroUser( String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

}