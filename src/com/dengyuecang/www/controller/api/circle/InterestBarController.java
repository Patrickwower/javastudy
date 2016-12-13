package com.dengyuecang.www.controller.api.circle;

import com.dengyuecang.www.controller.api.circle.model.AddInterestBarRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentEvaluateRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentRequest;
import com.dengyuecang.www.service.circle.IMomentService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by acang on 16/7/11.
 */

@RestController
@RequestMapping("/api/circle/interest")
public class InterestBarController {

    @Resource
    private IMomentService momentServiceImpl;

    @RequestMapping("/add")
    public RespData evaluate(@RequestHeader HttpHeaders headers, AddInterestBarRequest addInterestBarRequest){

        try {
//            return momentServiceImpl.evaluate(headers, momentEvaluateRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }



}
