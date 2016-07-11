package com.dengyuecang.www.service.community.model;

/**
 * Created by acang on 16/7/7.
 */
public class IndexArticle {

    private String id;

    private String bgimg;

    private String bigtag;

    private String title;

    private String author;

    private String timestamp;

    public IndexArticle() {
    }

    public IndexArticle(String id, String bgimg, String bigtag, String title, String author) {
        this.id = id;
        this.bgimg = bgimg;
        this.bigtag = bigtag;
        this.title = title;
        this.author = author;
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

    public String getBigtag() {
        return bigtag;
    }

    public void setBigtag(String bigtag) {
        this.bigtag = bigtag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
