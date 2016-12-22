package com.dengyuecang.www.controller.api.circle;

import com.dengyuecang.www.controller.api.circle.model.*;
import com.dengyuecang.www.service.circle.IInformationService;
import com.dengyuecang.www.service.circle.model.UpdateInfo;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by lxrent on 2016/12/13.
 */
@RestController
@RequestMapping("/api/circle/circleMember")
public class CircleMemberController {

    @Resource
    private IInformationService infoServiceImpl;

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

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    public RespData updateinfo(@RequestHeader HttpHeaders headers, UpdateInfo updateInfo){

        try {
            return  infoServiceImpl.updateinfo(headers,updateInfo);
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
    public RespData improveInformation(@RequestHeader HttpHeaders headers, @RequestParam(value = "file", required = true) MultipartFile file, ImproveInformationRequest improveInformationRequest, HttpServletRequest servletRequest){

        try {
            return infoServiceImpl.improveInformation(headers,file,improveInformationRequest,servletRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping(value = "/updatehead",method = RequestMethod.POST)
    @ResponseBody
    public RespData updateHead(@RequestHeader HttpHeaders headers, @RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest servletRequest){
        try{

            return infoServiceImpl.updateHead(headers,file,servletRequest);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public RespData login(@RequestHeader HttpHeaders headers,LoginRequest loginRequest){

        try {
            return infoServiceImpl.login(headers, loginRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping(value = "/modifyPwd",method = RequestMethod.POST)
    @ResponseBody
    public RespData modifyPwd(@RequestHeader HttpHeaders headers,ModifyPwdRequest modifyPwdRequest){

        try {
            return infoServiceImpl.modifyPwd(headers, modifyPwdRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping(value = "/findPwdByVerify")
    @ResponseBody
    public RespData findPwdByVerify(@RequestHeader HttpHeaders headers, FindPwdByVerifyRequest findPwdByVerifyRequest){

        try {
            return infoServiceImpl.findPwdByVerify(headers,findPwdByVerifyRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping(value = "/resetPwd")
    @ResponseBody
    public RespData resetPwd(@RequestHeader HttpHeaders headers, ResetPwdRequest resetPwdRequest){

        try {
            return infoServiceImpl.resetPwd(headers,resetPwdRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

}
