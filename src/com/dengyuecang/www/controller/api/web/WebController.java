package com.dengyuecang.www.controller.api.web;

import com.dengyuecang.www.controller.api.web.Model.ApplyBetaRequest;
import com.dengyuecang.www.service.web.IApplyBetaService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by acang on 16/7/21.
 */

@RestController
@RequestMapping("/api/web")
public class WebController {

    @Resource
    private IApplyBetaService applyBetaServiceImpl;

    @RequestMapping(value="/applyBeta/add",method = RequestMethod.POST)
    @ResponseBody
    public RespData add(ApplyBetaRequest applyBetaRequest){

        try {

            return applyBetaServiceImpl.addApply(applyBetaRequest);

        }catch (Exception e){
            e.printStackTrace();;
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

}
