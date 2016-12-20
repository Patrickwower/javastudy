package com.dengyuecang.www.controller.api.circle.model;

/**
 * Created by acang on 2016/12/20.
 */
public class FindPwdByVerifyRequest {

    private String verifyCode;

    //验证码用
    private String appkey;
    private String zone;

    private String mobile;

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
