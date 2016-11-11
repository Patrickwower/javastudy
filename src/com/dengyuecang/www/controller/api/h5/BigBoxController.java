package com.dengyuecang.www.controller.api.h5;

import com.dengyuecang.www.controller.api.h5.model.BigBoxUserInfo;
import com.dengyuecang.www.service.bigbox.IBigBoxService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by acang on 16/7/11.
 */

@RestController
@RequestMapping("/api/bigbox/h5")
public class BigBoxController {

    @Resource
    private IBigBoxService bigBoxServiceImpl;

    @RequestMapping("/collectInfo")
    @ResponseBody
    public RespData collectInfo(@RequestHeader HttpHeaders headers, BigBoxUserInfo bigBoxUserInfo){

        try {

            return bigBoxServiceImpl.getPic(headers,bigBoxUserInfo);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }



}
