package com.dengyuecang.www.service.feedback;


import com.dengyuecang.www.entity.feedback.Feedback;
import com.dengyuecang.www.service.feedback.model.FeedbackRequest;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;

/**
 * Created by lxrent on 2016/12/8.
 */
public interface FeedbackService extends IBaseService<Feedback> {

    public RespData feedback(String id,String content,String ctime);

    public RespData save(HttpHeaders headers, FeedbackRequest feedbackRequest);
}
