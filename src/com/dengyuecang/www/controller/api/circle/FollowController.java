package com.dengyuecang.www.controller.api.circle;

import com.dengyuecang.www.controller.api.circle.model.follow.FollowRequest;
import com.dengyuecang.www.service.circle.IFollowService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/10.
 */
@RestController
@RequestMapping("/api/circle/follow")
public class FollowController {

    @Resource
    private IFollowService followServiceImpl;

    @RequestMapping("/onfollow")
    @ResponseBody
    public RespData onfollow(@RequestHeader HttpHeaders headers, String followed_id){

        try {
            return followServiceImpl.onFollow(headers,followed_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }

    @RequestMapping("/canclefollow")
    @ResponseBody
    public RespData canclefollow(@RequestHeader HttpHeaders headers, String followed_id){

        try {

            return followServiceImpl.cancleFollow(headers,followed_id);

        }catch (Exception e){
            e.printStackTrace();
        }
        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping("/followlist")
    @ResponseBody
    public RespData followList(@RequestHeader HttpHeaders headers, FollowRequest followRequest){
        try {
            return followServiceImpl.followList(headers,followRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping("/fanslist")
    @ResponseBody
    public RespData fansList(@RequestHeader HttpHeaders headers,FollowRequest followRequest){
        try{
            return followServiceImpl.fansList(headers,followRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

}
