package com.dengyuecang.www.entity.startlog;

import com.dengyuecang.www.entity.Member;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lxrent on 2016/12/9.
 */
@Entity
@Table(name="dyc_startlog")
public class StartLog implements Serializable {

    private String id;
    private Date logtime;
    private Member member;

    public StartLog(String id, Date logtime, Member member) {
        this.id = id;
        this.logtime = logtime;
        this.member = member;
    }

    public StartLog() {
    }

    @GenericGenerator(name = "generator", strategy = "uuid")
    @Id
    @GeneratedValue(generator = "generator")

    @Column(name = "id", unique = true, nullable = false, length = 100)
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

    @OneToOne
    @JoinColumn(name="memberId")
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
