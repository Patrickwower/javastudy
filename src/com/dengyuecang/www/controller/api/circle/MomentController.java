package com.dengyuecang.www.controller.api.circle;

import com.dengyuecang.www.controller.api.circle.model.MomentEvaluateRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentPublishRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentRequest;
import com.dengyuecang.www.service.circle.IMomentService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

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
    @ResponseBody
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
    public RespData publish(@RequestHeader HttpHeaders headers, HttpServletRequest servletRequest,  @RequestParam(value = "file", required = true) MultipartFile file, MomentPublishRequest momentPublishRequest) {

        try {

            ImageIO.read(file.getInputStream()).getHeight();

            return momentServiceImpl.add(headers,file,momentPublishRequest,servletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }

    @RequestMapping("/edit")
    @ResponseBody
    public RespData edit(@RequestHeader HttpHeaders headers, String id, @RequestParam(value="file")MultipartFile file, MomentPublishRequest momentPublishRequest, HttpServletRequest servletRequest){

        try {
            return edit(headers,id,file,momentPublishRequest,servletRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

}
