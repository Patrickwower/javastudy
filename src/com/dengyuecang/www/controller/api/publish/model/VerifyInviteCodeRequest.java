package com.dengyuecang.www.controller.api.publish.model;

/**
 * Created by acang on 16/7/27.
 */
public class VerifyInviteCodeRequest {

    private String memberId;

    private String inviteCode;

    public VerifyInviteCodeRequest() {
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
}
