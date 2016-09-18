package com.dengyuecang.www.service.community.model;

import java.util.Date;
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

    private String shareUrl;

    private List<String> tags;

    private IndexAuthor author;

    private String timestamp;

    private String ifZan;

    private String ifCollection;

    private String zanCount;

    private String commentCount;

    private String browseCount;

    private String collectionCount;

    private String wordCount;

    private String content;

    private String summary;

    private String squareCrop;

    private Date ctime;

    private String status;

    private String statusName;

    private String ifIndex;

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

    public String getIfCollection() {
        return ifCollection;
    }

    public void setIfCollection(String ifCollection) {
        this.ifCollection = ifCollection;
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

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(String browseCount) {
        this.browseCount = browseCount;
    }

    public String getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(String collectionCount) {
        this.collectionCount = collectionCount;
    }

    public String getWordCount() {
        return wordCount;
    }

    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getSquareCrop() {
        return squareCrop;
    }

    public void setSquareCrop(String squareCrop) {
        this.squareCrop = squareCrop;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getIfIndex() {
        return ifIndex;
    }

    public void setIfIndex(String ifIndex) {
        this.ifIndex = ifIndex;
    }
}
