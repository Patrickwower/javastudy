package com.dengyuecang.www.controller.api.circle;

import com.dengyuecang.www.service.circle.InformationService;
import com.dengyuecang.www.utils.RespData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lxrent on 2016/12/13.
 */
@RestController
@RequestMapping("/api/circle/interest")
public class InterestTypeController {

    @Resource
    private InformationService informationServiceImpl;

    @RequestMapping("/list")
    @ResponseBody
    public RespData interestType(){

        return informationServiceImpl.interestType();

    }

}
