package com.dengyuecang.www.controller.api.circle.model.evaluation;

/**
 * Created by acang on 2017/1/12.
 */
public class EvaluationRequest {

    private String momentId;

    private String timestamp;

    private String pageSize;

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
