package com.dengyuecang.www.service.community;

import com.dengyuecang.www.controller.api.community.model.CommentRequest;
import com.dengyuecang.www.controller.api.community.model.EvaluteRequest;
import com.dengyuecang.www.controller.api.community.model.TopicCommentRequest;
import com.dengyuecang.www.entity.community.Topic;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;

/**
 * Created by acang on 16/7/11.
 */
public interface ITopicService extends IBaseService<Topic> {

    public RespData getEveryDayTopic(HttpHeaders headers);

    public RespData queryTopic(String topicId);

    public RespData comments(TopicCommentRequest request);

    public RespData comment(HttpHeaders headers, CommentRequest request);

    public RespData evaluate(HttpHeaders headers, EvaluteRequest evaluteRequest);

    public RespData topicData(String topicId);
}
