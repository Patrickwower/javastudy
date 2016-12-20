package com.dengyuecang.www.service.circle.model;

/**
 * Created by lxrent on 2016/12/14.
 */
public class UpdateInfo {

    private String memberId;
    private String nickname;
    private String introduction;
    private String sex;
    private String province;
    private String city;
    private String area;
    private String school;

    public UpdateInfo() {
    }

    public UpdateInfo(String memberId, String nickname, String introduction, String sex, String province, String city, String area, String school) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.introduction = introduction;
        this.sex = sex;
        this.province = province;
        this.city = city;
        this.area = area;
        this.school = school;
    }

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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
