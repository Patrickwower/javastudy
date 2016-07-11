package com.dengyuecang.www.service.community.model;

/**
 * Created by acang on 16/7/7.
 */
public class IndexRecommend {

    private String id;

    private String bgimg;

    public IndexRecommend() {
    }

    public IndexRecommend(String id, String bgimg) {
        this.id = id;
        this.bgimg = bgimg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBgimg() {
        return bgimg;
    }

    public void setBgimg(String bgimg) {
        this.bgimg = bgimg;
    }
}
