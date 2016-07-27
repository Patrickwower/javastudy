package com.dengyuecang.www.controller.api.publish.model;

/**
 * Created by acang on 16/7/25.
 */
public class WeixinLoginRequest {

    private String code;

    private String state;

    public WeixinLoginRequest() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
