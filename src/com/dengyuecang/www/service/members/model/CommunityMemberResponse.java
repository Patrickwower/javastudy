package com.dengyuecang.www.service.members.model;

import java.util.*;

/**
 * Created by acang on 16/7/18.
 */
public class CommunityMemberResponse {

    private String id;

    private String nickname;

    private String head;

    private String introduction;

    private String fucosCount;

    private String fucosedCount;

    private String zanCount;

    private String sex;

    private String city;

    private String school;

    private String enrollment;

    private Date ctime;

    private String timestamp;

    private List<RelatedAccount> accounts = new ArrayList<RelatedAccount>();

    public CommunityMemberResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFucosCount() {
        return fucosCount;
    }

    public void setFucosCount(String fucosCount) {
        this.fucosCount = fucosCount;
    }

    public String getFucosedCount() {
        return fucosedCount;
    }

    public void setFucosedCount(String fucosedCount) {
        this.fucosedCount = fucosedCount;
    }

    public String getZanCount() {
        return zanCount;
    }

    public void setZanCount(String zanCount) {
        this.zanCount = zanCount;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public List<RelatedAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<RelatedAccount> accounts) {
        this.accounts = accounts;
    }
}
