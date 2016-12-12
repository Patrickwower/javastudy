package com.dengyuecang.www.entity.startlog;

import java.util.Date;

/**
 * Created by lxrent on 2016/12/9.
 */
public class StartLog {

    private String id;
    private Date logtime;
    private String userId;

    public StartLog(String id, Date logtime, String userId) {
        this.id = id;
        this.logtime = logtime;
        this.userId = userId;
    }

    public StartLog() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLogtime() {
        return logtime;
    }

    public void setLogtime(Date logtime) {
        this.logtime = logtime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
