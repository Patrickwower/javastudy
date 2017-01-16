package com.dengyuecang.www.service.circle.model;

import com.dengyuecang.www.entity.Member;

import java.util.Date;

/**
 * Created by Administrator on 2017/1/10.
 */
public class MemberFollow {

    private String id;

    private Member follow_id;

    private Member followed_id;

    private Date ctime;

    public MemberFollow() {
    }

    public MemberFollow(String id, Member follow_id, Member followed_id, Date ctime) {
        this.id = id;
        this.follow_id = follow_id;
        this.followed_id = followed_id;
        this.ctime = ctime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Member getFollow_id() {
        return follow_id;
    }

    public void setFollow_id(Member follow_id) {
        this.follow_id = follow_id;
    }

    public Member getFollowed_id() {
        return followed_id;
    }

    public void setFollowed_id(Member followed_id) {
        this.followed_id = followed_id;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

}
