package com.dengyuecang.www.service.circle;

import com.dengyuecang.www.controller.api.circle.model.comment.CommentAddRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.CommentDeleteRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.CommentRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.reply.ReplyAddRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.reply.ReplyDeleteRequest;
import com.dengyuecang.www.entity.circle.MomentComment;
import com.dengyuecang.www.entity.circle.MomentCommentReply;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;


/**
 * Created by acang on 16/6/22.
 */
public interface IReplyService extends IBaseService<MomentCommentReply>{

    public RespData add(HttpHeaders headers, ReplyAddRequest replyAddRequest);

    public RespData delete(HttpHeaders headers, ReplyDeleteRequest replyDeleteRequest);

    public RespData list(HttpHeaders headers, CommentRequest commentRequest);

}
