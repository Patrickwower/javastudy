package com.dengyuecang.www.service.circle.model.evaluation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acang on 2017/1/12.
 */
public class EvaluationDiscussant {

    private String discussantId;

    private String nickname;

    private String headUrl;

    private List<String> interestTags = new ArrayList<String>();

    public String getDiscussantId() {
        return discussantId;
    }

    public void setDiscussantId(String discussantId) {
        this.discussantId = discussantId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public List<String> getInterestTags() {
        return interestTags;
    }

    public void setInterestTags(List<String> interestTags) {
        this.interestTags = interestTags;
    }
}
