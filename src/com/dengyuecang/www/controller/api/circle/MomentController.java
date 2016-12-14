package com.dengyuecang.www.controller.api.circle;

import com.dengyuecang.www.controller.api.circle.model.MomentEvaluateRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentPublishRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentRequest;
import com.dengyuecang.www.controller.api.community.model.*;
import com.dengyuecang.www.service.circle.IMomentService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created by acang on 16/7/11.
 */

@RestController
@RequestMapping("/api/circle/moment")
public class MomentController {

    @Resource
    private IMomentService momentServiceImpl;


    @RequestMapping("/list")
    @ResponseBody
    public RespData list(@RequestHeader HttpHeaders headers, MomentRequest momentRequest) {

        try {
            return momentServiceImpl.queryList(headers, momentRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }


    @RequestMapping("/evaluate")
    public RespData evaluate(@RequestHeader HttpHeaders headers, MomentEvaluateRequest momentEvaluateRequest) {

        try {
            return momentServiceImpl.evaluate(headers, momentEvaluateRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping("/publish")
    @ResponseBody
    public RespData publish(@RequestHeader HttpHeaders headers, @RequestParam(value = "file", required = true) MultipartFile file, MomentPublishRequest momentPublishRequest) {

        try {
            return momentServiceImpl.add(headers,file,momentPublishRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }


}
