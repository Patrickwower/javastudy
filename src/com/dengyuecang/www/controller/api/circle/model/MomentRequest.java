package com.dengyuecang.www.controller.api.circle.model;

/**
 * Created by acang on 2016/12/12.
 */
public class MomentRequest {

//    private String

    public String timestamp;

    public String pageSize = "10";

    public String memberId;

    public String restingId;

    public String status;

    public MomentRequest() {
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
