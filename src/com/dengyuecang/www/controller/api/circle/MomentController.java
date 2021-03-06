package com.dengyuecang.www.controller.api.circle;

import com.dengyuecang.www.controller.api.circle.model.MomentEvaluateRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentPublishRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentUpdateRequest;
import com.dengyuecang.www.controller.api.circle.model.evaluation.EvaluationRequest;
import com.dengyuecang.www.service.circle.IMomentService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(MomentController.class);

    @Resource
    private IMomentService momentServiceImpl;


    @RequestMapping("/list")
    @ResponseBody
    public RespData list(@RequestHeader HttpHeaders headers, MomentRequest momentRequest) {

        try {
            log.info("查询动态列表");
            return momentServiceImpl.queryList(headers, momentRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }


    @RequestMapping("/listByParam")
    @ResponseBody
    public RespData listByParam(@RequestHeader HttpHeaders headers, MomentRequest momentRequest) {

        try {
            return momentServiceImpl.queryListByParam(headers, momentRequest);
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

    @RequestMapping("/evaluation/list")
    @ResponseBody
    public RespData evaluateList(@RequestHeader HttpHeaders headers, EvaluationRequest evaluationRequest) {

        try {

            return momentServiceImpl.evaluationList(headers, evaluationRequest);

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
    public RespData edit(@RequestHeader HttpHeaders headers, String id, @RequestParam(value="file", required = false)MultipartFile file, MomentUpdateRequest momentUpdateRequest, HttpServletRequest servletRequest){

        try {

            return momentServiceImpl.edit(headers,id,file,momentUpdateRequest,servletRequest);

        }catch (Exception e){

            e.printStackTrace();

        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }

    @RequestMapping("/delete")
    @ResponseBody
    public RespData delete(@RequestHeader HttpHeaders headers, String momentId){

        try {
            return momentServiceImpl.delete(headers,momentId);

        }catch (Exception e){

            e.printStackTrace();

        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }

    @RequestMapping("/detail")
    @ResponseBody
    public RespData detail(@RequestHeader HttpHeaders headers, String momentId){

        try {
            return momentServiceImpl.detail(headers,momentId);

        }catch (Exception e){

            e.printStackTrace();

        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }


}
