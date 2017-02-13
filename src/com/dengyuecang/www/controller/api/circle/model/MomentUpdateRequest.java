package com.dengyuecang.www.controller.api.circle.model;

/**
 * Created by acang on 2016/12/14.
 */
public class MomentUpdateRequest {

    private String momentId;

    private String content;

    private String interestBarId;

    private String public_level;

    private String img_height;

    private String img_width;

    private String occurrence_time;

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

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

    public String getImg_height() {
        return img_height;
    }

    public void setImg_height(String img_height) {
        this.img_height = img_height;
    }

    public String getImg_width() {
        return img_width;
    }

    public void setImg_width(String img_width) {
        this.img_width = img_width;
    }

    public String getOccurrence_time() {
        return occurrence_time;
    }

    public void setOccurrence_time(String occurrence_time) {
        this.occurrence_time = occurrence_time;
    }
}
