package com.dengyuecang.www.service.circle.impl;

import com.dengyuecang.www.controller.api.circle.model.MomentEvaluateRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentPublishRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.CommentAddRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.CommentDeleteRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.CommentRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.circle.*;
import com.dengyuecang.www.service.IStaticResourceService;
import com.dengyuecang.www.service.circle.IMessageService;
import com.dengyuecang.www.service.circle.IMomentCommentService;
import com.dengyuecang.www.service.circle.IMomentService;
import com.dengyuecang.www.service.circle.common.MessageCommonConstant;
import com.dengyuecang.www.service.circle.model.MomentCreater;
import com.dengyuecang.www.service.circle.model.MomentImg;
import com.dengyuecang.www.service.circle.model.MomentInterest;
import com.dengyuecang.www.service.circle.model.MomentResponse;
import com.dengyuecang.www.service.circle.model.comment.CommentDiscussant;
import com.dengyuecang.www.service.circle.model.comment.CommentResponse;
import com.dengyuecang.www.service.circle.model.comment.Reply;
import com.dengyuecang.www.service.circle.model.message.MessageAdd;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by acang on 16/6/22.
 */
@Service
public class MomentCommentServiceImpl extends BaseService<MomentComment> implements IMomentCommentService{


    @Resource(name="hibernateBaseDao")
    private BaseDao<Member> memberDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<Moment> momentDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<MomentComment> momentCommentDao;

    @Resource
    private IMessageService messageServiceImpl;



    @Override
    public BaseDao<MomentComment> getSuperDao() {
        return null;
    }

    @Override
    public RespData add(HttpHeaders headers, CommentAddRequest commentAddRequest) {

        try {
            MomentComment momentComment = new MomentComment();

            momentComment.setContent(commentAddRequest.getContent());

            momentComment.setCtime(new Date());

            momentComment.setTimestamp(System.currentTimeMillis());

            String memberId = headers.getFirst("memberId");

            Member member = memberDao.get(Member.class,memberId);

            if (member!=null){
                momentComment.setDiscussant(member);
            }

            Moment moment = momentDao.get(Moment.class,commentAddRequest.getMomentId());

            if (moment!=null){
                momentComment.setMoment(moment);
            }

            momentCommentDao.save(momentComment);

            //站内消息
            MessageAdd messageAdd = new MessageAdd();

            messageAdd.setType(MessageCommonConstant.TYPE_COMMENT);
            messageAdd.setMomentId(moment.getId());
            messageAdd.setServiceId(momentComment.getId());
            messageAdd.setSenderId(memberId);
            messageAdd.setRecipientId(moment.getCreater().getId());

            messageServiceImpl.add(headers,messageAdd);

            Map<String,String> response = new HashMap<String,String>();

            response.put("commentSize",commentSize(commentAddRequest.getMomentId()));

            return RespCode.getRespData(RespCode.SUCCESS,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    @Override
    public RespData delete(HttpHeaders headers, CommentDeleteRequest commentDeleteRequest) {

        try {
            String memberId = headers.getFirst("memberId");

            String commentId = commentDeleteRequest.getCommentId();

            MomentComment momentComment = momentCommentDao.get(MomentComment.class,commentId);

            if (momentComment!=null){

                String canDelete = canDelete(memberId,momentComment.getDiscussant().getId());

                if ("1".equals(canDelete)){
                    momentCommentDao.delete(momentComment);

                }
                Map<String,String> response = new HashMap<String,String>();

                response.put("commentSize",commentSize(momentComment.getMoment().getId()));

                return RespCode.getRespData(RespCode.SUCCESS,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }



    @Override
    public RespData reply(HttpHeaders headers) {
        return null;
    }

    @Override
    public RespData list(HttpHeaders headers, CommentRequest commentRequest) {

        try {

            String memberId = headers.getFirst("memberId");

            int limit = 10;

            if (StringUtils.isNotEmpty(commentRequest.getPageSize())){
                limit = Integer.valueOf(commentRequest.getPageSize());
            }

            long timestamp = System.currentTimeMillis();

            if (StringUtils.isNotEmpty(commentRequest.getTimestamp())){
                timestamp = Long.valueOf(commentRequest.getTimestamp());
            }

            String hql = "from MomentComment mc where mc.moment.id=? and mc.timestamp<"+timestamp;

            hql += " order by mc.timestamp desc ";

            //查询文章列表
            Query q = momentCommentDao.createQuery(hql);

            q.setString(0,commentRequest.getMomentId());

            q.setFirstResult(0);

            q.setMaxResults(limit);

            List<MomentComment> comments = q.list();

            Map<String,Object> response = new HashMap<String,Object>();

            response.put("comments",fromCommentToResponse(memberId,comments));

            return RespCode.getRespData(RespCode.SUCCESS,response);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());

    }

    private List<CommentResponse> fromCommentToResponse(String memberId,List<MomentComment> comments){

        List<CommentResponse> commentResponseList = new ArrayList<CommentResponse>();

        for (MomentComment momentComment :
                comments) {

            CommentResponse commentResponse = this.commentToResponse(memberId, momentComment);

            commentResponseList.add(commentResponse);

        }

        return commentResponseList;
    }

    private CommentResponse commentToResponse(String memberId, MomentComment momentComment){

        CommentResponse commentResponse = new CommentResponse();

        commentResponse.setCommentId(momentComment.getId());

        commentResponse.setContent(momentComment.getContent());

        Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        commentResponse.setCtime(f.format(momentComment.getCtime()));

        CommentDiscussant discussant = new CommentDiscussant();

        Member commentDiscussant = momentComment.getDiscussant();

        discussant.setMemberId(commentDiscussant.getId());

        discussant.setNickname(commentDiscussant.getMemberInfo().getNickname());

        discussant.setHeadUrl(commentDiscussant.getMemberInfo().getIcon());

        commentResponse.setDiscussant(discussant);

        commentResponse.setTimestamp(momentComment.getTimestamp()+"");

        //0和1  0代表不能删,1代表能删
        commentResponse.setCanDelete(canDelete(memberId,commentDiscussant.getId()));

        List<MomentCommentReply> commentReplys = momentComment.getReplys();

        for (MomentCommentReply commentReply :
                commentReplys) {

            Reply reply = new Reply();

            reply.setReplyId(commentReply.getId());

            reply.setContent(commentReply.getContent());

            reply.setDiscussantId(commentReply.getDiscussant().getId());

            reply.setDiscussanName(commentReply.getDiscussant().getMemberInfo().getNickname());

            reply.setAtId(commentReply.getAt().getId());

            reply.setAtName(commentReply.getAt().getMemberInfo().getNickname());

            reply.setCanDelete(canDelete(memberId,commentReply.getDiscussant().getId()));

            commentResponse.getReplys().add(reply);
        }

        commentResponse.setMomentCreater(momentComment.getMoment().getCreater().getId());

        return commentResponse;

    }

    private String canDelete(String memberId,String discussantId){

        if (memberId.equals(discussantId))return "1";

        return "0";
    }

    @Override
    public RespData listAt(HttpHeaders headers) {
        return null;
    }

    @Override
    public String commentSize(String momentId) {

        String hqlCommentSize = "select count(mc.id) from MomentComment mc where mc.moment.id=? ";

        Query q = momentCommentDao.createQuery(hqlCommentSize);

        q.setString(0,momentId);

        long zanCount = (long)q.uniqueResult();

        return zanCount+"";

    }

    @Override
    public RespData detail(HttpHeaders headers, CommentRequest commentRequest) {

        try {

            String memberId = headers.getFirst("memberId");

            MomentComment momentComment = momentCommentDao.get(MomentComment.class,commentRequest.getCommentId());

            CommentResponse commentResponse = this.commentToResponse(memberId,momentComment);

            Map<String,Object> response = new HashMap<String,Object>();

            response.put("comment",commentResponse);

            return RespCode.getRespData(RespCode.SUCCESS, response);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());

    }
}
