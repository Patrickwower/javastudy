package com.dengyuecang.www.service.circle;

import com.dengyuecang.www.controller.api.circle.model.comment.CommentAddRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.CommentDeleteRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.CommentRequest;
import com.dengyuecang.www.entity.circle.MomentComment;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;



/**
 * Created by acang on 16/6/22.
 */
public interface IMomentCommentService extends IBaseService<MomentComment>{

    public RespData add(HttpHeaders headers, CommentAddRequest commentAddRequest);

    public RespData delete(HttpHeaders headers, CommentDeleteRequest commentDeleteRequest);

//    public RespData delete(HttpHeaders headers);

    public RespData reply(HttpHeaders headers);

    public RespData list(HttpHeaders headers, CommentRequest commentRequest);

    public RespData listAt(HttpHeaders headers);

}
