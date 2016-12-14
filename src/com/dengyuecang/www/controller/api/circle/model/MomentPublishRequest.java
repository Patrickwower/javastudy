package com.dengyuecang.www.controller.api.circle.model;

/**
 * Created by acang on 2016/12/14.
 */
public class MomentPublishRequest {

    private String content;

    private String interestBarId;

    private String public_level;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInterestBarId() {
        return interestBarId;
    }

    public void setInterestBarId(String interestBarId) {
        this.interestBarId = interestBarId;
    }

    public String getPublic_level() {
        return public_level;
    }

    public void setPublic_level(String public_level) {
        this.public_level = public_level;
    }
}
