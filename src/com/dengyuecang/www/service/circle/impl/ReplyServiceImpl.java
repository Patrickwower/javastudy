package com.dengyuecang.www.service.circle.impl;

import com.dengyuecang.www.controller.api.circle.model.comment.CommentAddRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.CommentDeleteRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.CommentRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.reply.ReplyAddRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.reply.ReplyDeleteRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.circle.Moment;
import com.dengyuecang.www.entity.circle.MomentComment;
import com.dengyuecang.www.entity.circle.MomentCommentReply;
import com.dengyuecang.www.service.circle.IMomentCommentService;
import com.dengyuecang.www.service.circle.IReplyService;
import com.dengyuecang.www.service.circle.model.comment.CommentDiscussant;
import com.dengyuecang.www.service.circle.model.comment.CommentResponse;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by acang on 16/6/22.
 */
@Service
public class ReplyServiceImpl extends BaseService<MomentCommentReply> implements IReplyService{


    @Resource(name="hibernateBaseDao")
    private BaseDao<Member> memberDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<Moment> momentDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<MomentComment> momentCommentDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<MomentCommentReply> replyDao;




    @Override
    public BaseDao<MomentCommentReply> getSuperDao() {
        return null;
    }


    @Override
    public RespData add(HttpHeaders headers, ReplyAddRequest replyAddRequest) {

        try {
            String memberId = headers.getFirst("memberId");

            Member member = memberDao.get(Member.class,memberId);

            String commentId = replyAddRequest.getCommentId();

            String content = replyAddRequest.getContent();

            MomentCommentReply reply = new MomentCommentReply();

            reply.setTimestamp(System.currentTimeMillis());

            reply.setCtime(new Date());

            reply.setContent(content);

            MomentComment mc = momentCommentDao.get(MomentComment.class,commentId);

            if (member!=null){
                reply.setDiscussant(member);
            }

            if (mc!=null){
                reply.setComment(mc);
                reply.setSort(mc.getReplys().size());
            }

            Member at = new Member();

            if (memberId.equals(mc.getDiscussant().getId())){
                at = mc.getMoment().getCreater();
            }else{
                at = mc.getDiscussant();
            }

            reply.setAt(at);

            replyDao.save(reply);

            return RespCode.getRespData(RespCode.SUCCESS,new HashMap<String,String>());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    @Override
    public RespData delete(HttpHeaders headers, ReplyDeleteRequest replyDeleteRequest) {

        try {
            String memberId = headers.getFirst("memberId");

            String replyId = replyDeleteRequest.getReplyId();

            MomentCommentReply reply = replyDao.get(MomentCommentReply.class,replyId);

            if ("1".equals(canDelete(memberId,reply.getDiscussant().getId()))){
                replyDao.delete(reply);
                return RespCode.getRespData(RespCode.SUCCESS,new HashMap<String,String>());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    private String canDelete(String memberId,String replyDiscussantId){

        if (memberId.equals(replyDiscussantId)){
            return "1";
        }

        return "0";
    }

    @Override
    public RespData list(HttpHeaders headers, CommentRequest commentRequest) {
        return null;
    }
}
