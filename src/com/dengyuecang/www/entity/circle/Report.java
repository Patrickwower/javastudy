package com.dengyuecang.www.entity.circle;

import com.dengyuecang.www.entity.Member;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by acang on 2017/2/10.
 * 举报
 */
@Entity
@Table(name = "dyc_circle_report")
public class Report implements Serializable {

    private String id;

    private String content;

    private Moment moment;

    private Member member;

    private Date ctime;

    private long timestamp;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @OneToOne
    @JoinColumn(name = "moment_id")
    public Moment getMoment() {
        return moment;
    }

    public void setMoment(Moment moment) {
        this.moment = moment;
    }

    @OneToOne
    @JoinColumn(name = "creater")
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
