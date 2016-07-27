package com.dengyuecang.www.controller.api.publish;

import com.dengyuecang.www.controller.api.publish.model.VerifyInviteCodeRequest;
import com.dengyuecang.www.service.members.IPublishMembersService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by acang on 16/7/24.
 */
@RestController
@RequestMapping("/api/publish/member")
public class PublishMemberController {

    @Resource
    private IPublishMembersService publishMembersServiceImpl;

    public RespData login(){

        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/verifyInviteCode",method = RequestMethod.POST)
    public RespData verifyInviteCode(@RequestHeader HttpHeaders headers,VerifyInviteCodeRequest inviteRequest){

        try{
            return publishMembersServiceImpl.verifyInviteCode(headers,inviteRequest);
        }catch (Exception e){
            e.printStackTrace();;
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

}
