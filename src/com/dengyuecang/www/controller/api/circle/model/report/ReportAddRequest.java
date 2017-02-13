package com.dengyuecang.www.controller.api.circle.model.report;

/**
 * Created by acang on 2017/2/13.
 */
public class ReportAddRequest {

    private String content;

    private String momentId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }
}
