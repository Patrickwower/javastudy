package com.dengyuecang.www.controller.api.community;

import com.dengyuecang.www.controller.api.community.model.CommentRequest;
import com.dengyuecang.www.controller.api.community.model.EvaluteRequest;
import com.dengyuecang.www.controller.api.community.model.TopicCommentRequest;
import com.dengyuecang.www.service.community.ITopicService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.omg.CORBA.Request;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by acang on 16/7/11.
 */

@RestController
@RequestMapping("/api/community/topic")
public class TopicController {

    @Resource
    private ITopicService topicServiceImpl;

    @RequestMapping("/everyday")
    @ResponseBody
    public RespData everydayTopic(@RequestHeader HttpHeaders headers){

        try {

            return topicServiceImpl.getEveryDayTopic(headers);

        }catch (Exception e){
            e.printStackTrace();

        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    public RespData detail(){

        return null;
    }

    @RequestMapping("/commentList")
    public RespData commentList(TopicCommentRequest request){

        try {

            return topicServiceImpl.comments(request);

        }catch (Exception e){
            e.printStackTrace();

        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @ResponseBody
    public RespData comment(@RequestHeader HttpHeaders headers,CommentRequest request){

        try{

            return  topicServiceImpl.comment(headers, request);

        }catch (Exception e){
            e.printStackTrace();

        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping("/evaluate")
    @ResponseBody
    public RespData evaluate(@RequestHeader HttpHeaders headers, EvaluteRequest evaluteRequest){

        try{

            return  topicServiceImpl.evaluate(headers, evaluteRequest);

        }catch (Exception e){
            e.printStackTrace();

        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping("topicData")
    @ResponseBody
    public RespData topicData(String topicId){

        try{

            return  topicServiceImpl.topicData(topicId);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

}
