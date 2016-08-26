package com.dengyuecang.www.controller.api.community.model;

/**
 * Created by acang on 16/7/13.
 */
public class CollectionRequest {

    private String articleId;

    private String topicId;

    private String collection;

    public CollectionRequest() {
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
