package com.dengyuecang.www.service.community.model;

/**
 * Created by acang on 16/7/7.
 */
public class IndexTopic {

    private String id;

    private String name;

    private String day;

    private String month;

    private String year;

    private String bgimg;

    private String zanCount;

    private String ifZan;

    public IndexTopic() {
    }

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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBgimg() {
        return bgimg;
    }

    public void setBgimg(String bgimg) {
        this.bgimg = bgimg;
    }

    public String getZanCount() {
        return zanCount;
    }

    public void setZanCount(String zanCount) {
        this.zanCount = zanCount;
    }

    public String getIfZan() {
        return ifZan;
    }

    public void setIfZan(String ifZan) {
        this.ifZan = ifZan;
    }
}
