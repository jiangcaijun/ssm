package com.ssm.model;


import lombok.Data;

/**
* @Description: 用户表
* @author jiangcaijun
* @date 2016年12月15日 下午5:37:51 
*/
@Data
public class User {
    private String id;

    private String userName;

    private String password;

    private Integer age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}