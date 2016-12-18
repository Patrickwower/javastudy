package com.dengyuecang.www.controller.api.circle.model;

/**
 * Created by acang on 2016/12/15.
 */
public class LoginRequest {

    private String mobile;

    private String pwd;

    private String loginId;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
