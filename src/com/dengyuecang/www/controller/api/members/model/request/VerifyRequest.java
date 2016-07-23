package com.dengyuecang.www.controller.api.members.model.request;

/**
 * Created by acang on 16/7/19.
 */
public class VerifyRequest {

    private String appkey;

    private String mobile;

    private String zone;

    private String code;

    private String pwd;

    public VerifyRequest() {
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
