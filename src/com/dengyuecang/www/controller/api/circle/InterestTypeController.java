package com.dengyuecang.www.controller.api.circle;

import com.dengyuecang.www.service.circle.IInformationService;
import com.dengyuecang.www.service.circle.IInterestTypeService;
import com.dengyuecang.www.utils.RespData;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lxrent on 2016/12/13.
 */
@RestController
@RequestMapping("/api/circle/interestType")
public class InterestTypeController {

    @Resource
    private IInterestTypeService interestTypeServiceImpl;

    @RequestMapping("/list")
    @ResponseBody
    public RespData interestType(HttpHeaders headers){

        return interestTypeServiceImpl.queryList();

    }

    @RequestMapping("/abc")
    @ResponseBody
    public RespData abc(){
        return interestTypeServiceImpl.queryList();
    }

}
