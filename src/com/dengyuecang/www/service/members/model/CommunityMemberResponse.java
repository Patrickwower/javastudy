package com.dengyuecang.www.service.members.model;

import com.dengyuecang.www.service.circle.model.MomentInterest;

import java.util.*;

/**
 * Created by acang on 16/7/18.
 */
public class CommunityMemberResponse {

    private String id;

    private String nickname;

    private String head;

    private String introduction;

//    private String focusCount;
//
//    private String focusedCount;
//
//    private String ifFocus;
//
//    private String zanCount;

    private String sex;

    private String province;

    private String area;

    private String city;

    private String school;

    private String enrollment;

    private Date ctime;

    private String timestamp;

    private List<MomentInterest> interestBars;


    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    //    private List<RelatedAccount> accounts = new ArrayList<RelatedAccount>();

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

//    public String getFocusCount() {
//        return focusCount;
//    }
//
//    public void setFocusCount(String focusCount) {
//        this.focusCount = focusCount;
//    }
//
//    public String getFocusedCount() {
//        return focusedCount;
//    }
//
//    public void setFocusedCount(String focusedCount) {
//        this.focusedCount = focusedCount;
//    }
//
//    public String getIfFocus() {
//        return ifFocus;
//    }
//
//    public void setIfFocus(String ifFocus) {
//        this.ifFocus = ifFocus;
//    }
//
//    public String getZanCount() {
//        return zanCount;
//    }
//
//    public void setZanCount(String zanCount) {
//        this.zanCount = zanCount;
//    }

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

//    public List<RelatedAccount> getAccounts() {
//        return accounts;
//    }
//
//    public void setAccounts(List<RelatedAccount> accounts) {
//        this.accounts = accounts;
//    }


    public List getInterestBars() {
        return interestBars;
    }

    public void setInterestBars(List<MomentInterest> interestBars) {
        this.interestBars = interestBars;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
