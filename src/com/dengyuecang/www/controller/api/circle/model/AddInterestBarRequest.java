package com.dengyuecang.www.controller.api.circle.model;

/**
 * Created by acang on 2016/12/13.
 */
public class AddInterestBarRequest {

    private String bar_id;

    private String name;

    private String types;

    private String detail;

    public String getBar_id() {
        return bar_id;
    }

    public void setBar_id(String bar_id) {
        this.bar_id = bar_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
