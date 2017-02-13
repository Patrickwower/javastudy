package com.dengyuecang.www.controller.api.circle;

import com.dengyuecang.www.controller.api.circle.model.AddInterestBarRequest;
import com.dengyuecang.www.service.circle.IInterestBarService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by acang on 16/7/11.
 */

@RestController
@RequestMapping("/api/circle/interest")
public class InterestBarController {

    @Resource
    private IInterestBarService interestBarServiceImpl;

    @RequestMapping("/add")
    @ResponseBody
    public RespData add(@RequestHeader HttpHeaders headers, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest servletRequest, AddInterestBarRequest addInterestBarRequest){

        try {
            return interestBarServiceImpl.addInterestBar(headers,file,servletRequest,addInterestBarRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RespData delete(@RequestHeader HttpHeaders headers, String interestBarId){

        try {
            return interestBarServiceImpl.delete(headers,interestBarId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping("/list")
    @ResponseBody
    public RespData list(@RequestHeader HttpHeaders headers){

        try {

            return interestBarServiceImpl.queryList(headers);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping("/update")
    @ResponseBody
    public RespData update(@RequestHeader HttpHeaders headers,@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest servletRequest, AddInterestBarRequest addInterestBarRequest){

        try {

            return interestBarServiceImpl.update(headers,file,servletRequest, addInterestBarRequest);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping("/detail")
    @ResponseBody
    public RespData detail(@RequestHeader HttpHeaders headers, AddInterestBarRequest addInterestBarRequest){

        try {

            return interestBarServiceImpl.detail(headers, addInterestBarRequest);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

}
