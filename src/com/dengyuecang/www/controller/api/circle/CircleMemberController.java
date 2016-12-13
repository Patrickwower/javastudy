package com.dengyuecang.www.controller.api.circle;

import com.dengyuecang.www.service.circle.InformationService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by lxrent on 2016/12/13.
 */
@RestController
@RequestMapping("/circleMember")
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

}
