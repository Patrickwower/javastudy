package com.dengyuecang.www.controller.api.circle;

import com.dengyuecang.www.controller.api.circle.model.ImproveInformationRequest;
import com.dengyuecang.www.controller.api.circle.model.LoginRequest;
import com.dengyuecang.www.controller.api.circle.model.RegisterRequest;
import com.dengyuecang.www.service.circle.InformationService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created by lxrent on 2016/12/13.
 */
@RestController
@RequestMapping("/api/circle/circleMember")
public class CircleMemberController {

    @Resource
    private InformationService infoServiceImpl;

    @RequestMapping(value = "/information",method = RequestMethod.POST)
    @ResponseBody
    public RespData information(@RequestHeader HttpHeaders headers,String memberId){

        try {
            return infoServiceImpl.information(headers, memberId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public RespData register(@RequestHeader HttpHeaders headers,RegisterRequest registerRequest){

        try {
            return infoServiceImpl.register(headers, registerRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping(value = "/improveInformation",method = RequestMethod.POST)
    @ResponseBody
    public RespData improveInformation(@RequestHeader HttpHeaders headers, @RequestParam(value = "file", required = true) MultipartFile file, ImproveInformationRequest improveInformationRequest){

        try {
//            return infoServiceImpl.information(headers, memberId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public RespData login(@RequestHeader HttpHeaders headers,LoginRequest loginRequest){

        try {
//            return infoServiceImpl.information(headers, memberId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

}
