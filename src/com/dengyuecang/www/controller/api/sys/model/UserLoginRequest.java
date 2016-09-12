package com.dengyuecang.www.controller.api.sys.model;

/**
 * Created by acang on 16/9/6.
 */
public class UserLoginRequest {

    private String username;

    private String pwd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
