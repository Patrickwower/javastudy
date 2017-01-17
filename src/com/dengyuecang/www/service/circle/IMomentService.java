package com.dengyuecang.www.service.circle;

import com.dengyuecang.www.controller.api.circle.model.MomentEvaluateRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentPublishRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentRequest;
import com.dengyuecang.www.controller.api.circle.model.evaluation.EvaluationRequest;
import com.dengyuecang.www.entity.circle.Moment;
import com.dengyuecang.www.service.circle.model.MomentResponse;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by acang on 16/6/22.
 */
public interface IMomentService extends IBaseService<Moment>{

    public RespData queryList(HttpHeaders headers, MomentRequest momentRequest);

    public RespData queryListByParam(HttpHeaders headers, MomentRequest momentRequest);

    public RespData evaluate(HttpHeaders headers, MomentEvaluateRequest momentEvaluateRequest);

    public RespData add(HttpHeaders headers, MultipartFile file, MomentPublishRequest momentPublishRequest, HttpServletRequest servletRequest);

    //存有大问题
    public RespData edit(HttpHeaders headers,String id,MultipartFile file, MomentPublishRequest momentPublishRequest, HttpServletRequest servletRequest);

    public RespData delete(HttpHeaders headers,String momentId);

    public RespData evaluationList(HttpHeaders headers, EvaluationRequest evaluationRequest);

    public MomentResponse momentToresponse(String memberId, Moment moment);

    public RespData detail(HttpHeaders headers,String momentId);

}
