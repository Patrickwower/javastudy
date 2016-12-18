package com.dengyuecang.www.controller.api.circle.model;

/**
 * Created by acang on 2016/12/15.
 */
public class RegisterRequest {

    private String mobile;

    private String verifyCode;

    //验证码用
    private String appkey;
    private String zone;

    private String inviteCode;

    private String pwd;



    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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
}
