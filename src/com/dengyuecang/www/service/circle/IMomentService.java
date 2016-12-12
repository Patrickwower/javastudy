package com.dengyuecang.www.service.circle;

import com.dengyuecang.www.controller.api.circle.model.MomentEvaluateRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentRequest;
import com.dengyuecang.www.entity.circle.Moment;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;


/**
 * Created by acang on 16/6/22.
 */
public interface IMomentService extends IBaseService<Moment>{

    public RespData queryList(HttpHeaders headers, MomentRequest momentRequest);

    public RespData evaluate(HttpHeaders httpHeaders, MomentEvaluateRequest momentEvaluateRequest);

}
