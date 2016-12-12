package com.dengyuecang.www.entity.feedback;

import com.dengyuecang.www.entity.Member;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lxrent on 2016/12/8.
 */
@Entity
@Table(name="dyc_feedback")
public class Feedback implements Serializable {

    private String id;
    private Member member;
    private Date ctime;
    private String content;

    public Feedback() {
    }

    public Feedback(String id,Member member, Date ctime, String content) {
        this.id = id;
        this.member = member;
        this.ctime = ctime;
        this.content = content;
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

    @OneToOne
    @JoinColumn(name="memberId")
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
