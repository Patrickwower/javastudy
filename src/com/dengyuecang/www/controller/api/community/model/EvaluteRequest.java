package com.dengyuecang.www.controller.api.community.model;

/**
 * Created by acang on 16/7/13.
 */
public class EvaluteRequest {

    private String articleId;

    private String topicId;

    private String evaluation;

    public EvaluteRequest() {
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
