package com.dengyuecang.www.service.community.model;

/**
 * Created by acang on 16/8/26.
 */
public class ArticleCommentAtMember {

    private String memberId;

    private String nickname;

    private String head;

    private String ifAuthor;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getIfAuthor() {
        return ifAuthor;
    }

    public void setIfAuthor(String ifAuthor) {
        this.ifAuthor = ifAuthor;
    }
}
