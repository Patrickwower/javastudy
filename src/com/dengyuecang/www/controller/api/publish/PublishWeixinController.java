package com.dengyuecang.www.controller.api.publish;

import com.dengyuecang.www.controller.api.publish.model.WeixinLoginRequest;
import com.dengyuecang.www.service.members.IPublishMembersService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by acang on 16/7/25.
 */
@RestController
@RequestMapping("/api/publish/weixin")
public class PublishWeixinController {

    @Resource
    private IPublishMembersService publishMembersServiceImpl;

    @RequestMapping("/login")
    @ResponseBody
    public RespData login(@RequestHeader HttpHeaders headers, WeixinLoginRequest weixinLoginRequest){


        try {
            return publishMembersServiceImpl.login(headers,weixinLoginRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

}
