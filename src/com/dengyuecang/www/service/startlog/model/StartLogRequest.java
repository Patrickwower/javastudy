package com.dengyuecang.www.service.startlog.model;

import com.dengyuecang.www.entity.Member;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lxrent on 2016/12/12.
 */
public class StartLogRequest implements Serializable {

    public String id;
    public Member member;
    public Date logtime;

    public StartLogRequest() {
    }

    public StartLogRequest(String id, Member member, Date logtime) {
        this.id = id;
        this.member = member;
        this.logtime = logtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getLogtime() {
        return logtime;
    }

    public void setLogtime(Date logtime) {
        this.logtime = logtime;
    }
}
