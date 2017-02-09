package com.dengyuecang.www.controller.api.circle.model;

/**
 * Created by acang on 2016/12/15.
 */
public class ImproveInformationRequest {

    private String nickname;

    private String sex;

    private String age;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
