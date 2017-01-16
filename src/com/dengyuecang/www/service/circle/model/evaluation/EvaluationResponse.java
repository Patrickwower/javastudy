package com.dengyuecang.www.service.circle.model.evaluation;

/**
 * Created by acang on 2017/1/12.
 */
public class EvaluationResponse {

    private String evaluationId;

    private String ctime;

    private String timestamp;

    private EvaluationDiscussant discussant;

    public String getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(String evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public EvaluationDiscussant getDiscussant() {
        return discussant;
    }

    public void setDiscussant(EvaluationDiscussant discussant) {
        this.discussant = discussant;
    }
}
