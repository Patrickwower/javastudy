package com.dengyuecang.www.utils.sharesdk;

/**
 * Created by acang on 16/7/19.
 */
public class SmsVerifyRequest {

    //应用的appkey
    private String appkey;
    //电话号码
    private String phone;
    //区域号码86
    private String zone;
    //验证码
    private String code;

    public SmsVerifyRequest() {
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getParams(){
        String params = "";

        params += "appkey="+this.getAppkey()+"&";

        params += "phone="+this.getPhone()+"&";

        params += "zone="+this.getZone()+"&";

        params += "code="+this.getCode();

        return params;
    }
}
