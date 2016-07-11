package com.dengyuecang.www.entity.community;

import com.dengyuecang.www.entity.Member;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by acang on 16/7/5.
 */
@Entity
@Table(name="community_focus_member")
public class FocusMember {

    private String id;

    private Member member;

    private Member focus;

    private Date ctime;

    public FocusMember() {
    }

    public FocusMember(String id, Member member, Member focus, Date ctime) {
        this.id = id;
        this.member = member;
        this.focus = focus;
        this.ctime = ctime;
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
    @JoinColumn(name="member_id")
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @OneToOne
    @JoinColumn(name="focus_id")
    public Member getFocus() {
        return focus;
    }

    public void setFocus(Member focus) {
        this.focus = focus;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
