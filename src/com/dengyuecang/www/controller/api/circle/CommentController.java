package com.dengyuecang.www.controller.api.circle;

import com.dengyuecang.www.controller.api.circle.model.MomentEvaluateRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentPublishRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.CommentAddRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.CommentDeleteRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.CommentRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.reply.ReplyAddRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.reply.ReplyDeleteRequest;
import com.dengyuecang.www.service.circle.IMomentCommentService;
import com.dengyuecang.www.service.circle.IMomentService;
import com.dengyuecang.www.service.circle.IReplyService;
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
@RequestMapping("/api/circle/comment")
public class CommentController {

    @Resource
    private IMomentCommentService momentCommentServiceImpl;

    @Resource
    private IReplyService replyServiceImpl;

    @RequestMapping("/add")
    @ResponseBody
    public RespData add(@RequestHeader HttpHeaders headers, CommentAddRequest commentAddRequest) {

        try {
            return momentCommentServiceImpl.add(headers, commentAddRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }

    @RequestMapping("/delete")
    @ResponseBody
    public RespData delete(@RequestHeader HttpHeaders headers, CommentDeleteRequest commentDeleteRequest) {

        try {
            return momentCommentServiceImpl.delete(headers, commentDeleteRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }

    @RequestMapping("/reply/add")
    @ResponseBody
    public RespData replyAdd(@RequestHeader HttpHeaders headers, ReplyAddRequest replyAddRequest) {

        try {
            return replyServiceImpl.add(headers, replyAddRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }

    @RequestMapping("/reply/delete")
    @ResponseBody
    public RespData replyDelete(@RequestHeader HttpHeaders headers, ReplyDeleteRequest replyDeleteRequest) {

        try {
            return replyServiceImpl.delete(headers, replyDeleteRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }

    @RequestMapping("/list")
    @ResponseBody
    public RespData list(@RequestHeader HttpHeaders headers, CommentRequest commentRequest) {

        try {
            return momentCommentServiceImpl.list(headers, commentRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }

    @RequestMapping("/listAt")
    @ResponseBody
    public RespData listAt(@RequestHeader HttpHeaders headers, MomentRequest momentRequest) {

        try {
//            return momentServiceImpl.queryList(headers, momentRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }


}
