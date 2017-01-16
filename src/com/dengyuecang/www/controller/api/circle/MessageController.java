package com.dengyuecang.www.controller.api.circle;

import com.dengyuecang.www.controller.api.circle.model.MomentEvaluateRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentPublishRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentRequest;
import com.dengyuecang.www.controller.api.circle.model.evaluation.EvaluationRequest;
import com.dengyuecang.www.controller.api.circle.model.message.MessageRequest;
import com.dengyuecang.www.service.circle.IMessageService;
import com.dengyuecang.www.service.circle.IMomentService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.aspectj.bridge.IMessage;
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
@RequestMapping("/api/circle/message")
public class MessageController {

    @Resource
    private IMessageService messageServiceImpl;

    @RequestMapping("/list")
    @ResponseBody
    public RespData list(@RequestHeader HttpHeaders headers, MessageRequest messageRequest) {

        try {
            return messageServiceImpl.list(headers, messageRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }

    @RequestMapping("/read")
    @ResponseBody
    public RespData read(@RequestHeader HttpHeaders headers, MessageRequest messageRequest) {

        try {
            return messageServiceImpl.read(headers, messageRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }

    @RequestMapping("/readAll")
    @ResponseBody
    public RespData readAll(@RequestHeader HttpHeaders headers, MessageRequest messageRequest) {

        try {
            return messageServiceImpl.readAll(headers, messageRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }

}
