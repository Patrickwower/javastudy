package com.dengyuecang.www.controller.api.h5.model;

/**
 * Created by acang on 2016/11/9.
 */
public class BigBoxUserInfo {

    private String id;

    private String name;

    private String location;

    private String mobile;

    private String role;

    private String exclusiveImg;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getExclusiveImg() {
        return exclusiveImg;
    }

    public void setExclusiveImg(String exclusiveImg) {
        this.exclusiveImg = exclusiveImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
