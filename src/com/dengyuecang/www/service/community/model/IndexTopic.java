package com.dengyuecang.www.service.community.model;

/**
 * Created by acang on 16/7/7.
 */
public class IndexTopic {

    private String id;

    private String name;

    private String day;

    private String month;

    public IndexTopic() {
    }

    public IndexTopic(String id, String name, String day, String month) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.month = month;
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
}
