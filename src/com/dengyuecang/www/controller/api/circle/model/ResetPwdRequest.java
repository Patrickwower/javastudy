package com.dengyuecang.www.controller.api.circle.model;

/**
 * Created by acang on 2016/12/20.
 */
public class ResetPwdRequest {

    private String apply_id;

    private String mobile;

    private String pwd;

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
