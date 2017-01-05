package com.dengyuecang.www.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by acang on 2017/1/5.
 */
@Entity
@Table(name = "dyc_member_follow")
public class MemberFollow implements Serializable{

    private String id;

    private Member follow;

    private Member followed;

    private Date ctime;

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
    @JoinColumn(name = "followed_id")
    public Member getFollowed() {
        return followed;
    }

    public void setFollowed(Member followed) {
        this.followed = followed;
    }

    @OneToOne
    @JoinColumn(name = "follow_id")
    public Member getFollow() {
        return follow;
    }

    public void setFollow(Member follow) {
        this.follow = follow;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
