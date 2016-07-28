package com.dengyuecang.www.service.community.model;

import java.util.List;

/**
 * Created by acang on 16/7/7.
 */
public class IndexArticle {

    private String id;

    private String title;

    private String bgimg;

    private String category;

    private String url;

    private List<String> tags;

    private IndexAuthor author;

    private String timestamp;

    private String ifZan;

    private String zanCount;

    public IndexArticle() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getIfZan() {
        return ifZan;
    }

    public void setIfZan(String ifZan) {
        this.ifZan = ifZan;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public IndexAuthor getAuthor() {
        return author;
    }

    public void setAuthor(IndexAuthor author) {
        this.author = author;
    }

    public String getZanCount() {
        return zanCount;
    }

    public void setZanCount(String zanCount) {
        this.zanCount = zanCount;
    }
}