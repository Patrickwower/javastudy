package com.dengyuecang.www.service.circle;

import com.dengyuecang.www.controller.api.circle.model.comment.CommentRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.reply.ReplyAddRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.reply.ReplyDeleteRequest;
import com.dengyuecang.www.controller.api.circle.model.message.MessageRequest;
import com.dengyuecang.www.entity.circle.Message;
import com.dengyuecang.www.entity.circle.MomentCommentReply;
import com.dengyuecang.www.service.circle.model.message.MessageAdd;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;


/**
 * Created by acang on 16/6/22.
 */
public interface IMessageService extends IBaseService<Message>{

    public RespData add(HttpHeaders headers, MessageAdd messageAdd);

    public RespData read(HttpHeaders headers, MessageRequest messageRequest);

    public RespData readAll(HttpHeaders headers, MessageRequest messageRequest);

//    public RespData delete(HttpHeaders headers, ReplyDeleteRequest replyDeleteRequest);

    public RespData list(HttpHeaders headers, MessageRequest messageRequest);

    public int messageSize(String recipientId);

    public int unReadMessageSize(String recipientId);

}
