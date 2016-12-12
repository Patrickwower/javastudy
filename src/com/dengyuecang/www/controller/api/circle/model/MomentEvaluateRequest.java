package com.dengyuecang.www.controller.api.circle.model;

/**
 * Created by acang on 2016/12/12.
 */
public class MomentEvaluateRequest {

    private String momentId;

    private String evaluation;

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
}
