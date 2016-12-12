package com.dengyuecang.www.service.feedback.model;

import java.io.Serializable;

/**
 * Created by lxrent on 2016/12/8.
 */
public class FeedbackRequest implements Serializable {

    private String id;
    private String user;
    private String content;
    private String ctime;

    public FeedbackRequest() {
    }

    public FeedbackRequest(String id, String user, String content, String ctime) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.ctime = ctime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}

