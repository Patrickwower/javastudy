package com.dengyuecang.www.service.community.model;

import java.util.List;

/**
 * Created by acang on 16/7/7.
 */
public class IndexResponse {

    private List<IndexRecommend> recommends;

    private IndexTopic topic;

    private List<IndexArticle> articles;

    public IndexResponse() {
    }

    public IndexResponse(List<IndexRecommend> recommends, IndexTopic topic, List<IndexArticle> articles) {
        this.recommends = recommends;
        this.topic = topic;
        this.articles = articles;
    }

    public List<IndexRecommend> getRecommends() {
        return recommends;
    }

    public void setRecommends(List<IndexRecommend> recommends) {
        this.recommends = recommends;
    }

    public IndexTopic getTopic() {
        return topic;
    }

    public void setTopic(IndexTopic topic) {
        this.topic = topic;
    }

    public List<IndexArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<IndexArticle> articles) {
        this.articles = articles;
    }
}
