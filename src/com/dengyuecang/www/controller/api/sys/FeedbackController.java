package com.dengyuecang.www.controller.api.sys;

import com.dengyuecang.www.service.feedback.FeedbackService;
import com.dengyuecang.www.service.feedback.model.FeedbackRequest;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by lxrent on 2016/12/8.
 */
@RestController
@RequestMapping("/api/sys/feedback")
public class FeedbackController {

    @Resource
    private FeedbackService feedbackServiceImpl;

    @RequestMapping(value="/content",method = RequestMethod.POST)
    @ResponseBody
    public RespData feedback(@RequestHeader HttpHeaders headers,FeedbackRequest feedbackRequest){

        try {

            return feedbackServiceImpl.save(headers,feedbackRequest);

        }catch (Exception e){

        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);


    }

}
