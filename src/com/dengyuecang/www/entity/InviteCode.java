package com.dengyuecang.www.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by acang on 16/7/26.
 */
@Entity
@Table(name="dyc_invite_code")
public class InviteCode implements Serializable {

    //0初始 100已发放 200已认证
    private String code;

    private Date ctime;

    private String status;

    private Date etime;

    public InviteCode() {
    }

    @Id
    @Column(name = "code", unique = true, nullable = false, length = 100)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }
}
