package com.dengyuecang.www.service.circle.impl;

import com.dengyuecang.www.controller.api.circle.model.message.MessageRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.circle.Message;
import com.dengyuecang.www.entity.circle.Moment;
import com.dengyuecang.www.entity.circle.MomentComment;
import com.dengyuecang.www.entity.circle.MomentCommentReply;
import com.dengyuecang.www.service.circle.IInformationService;
import com.dengyuecang.www.service.circle.IMessageService;
import com.dengyuecang.www.service.circle.IMomentService;
import com.dengyuecang.www.service.circle.common.MessageCommonConstant;
import com.dengyuecang.www.service.circle.model.MomentResponse;
import com.dengyuecang.www.service.circle.model.message.MessageAdd;
import com.dengyuecang.www.service.circle.model.message.MessageSender;
import com.dengyuecang.www.service.circle.model.message.MessageMoment;
import com.dengyuecang.www.service.circle.model.message.MessageResponse;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.dengyuecang.www.utils.jpush.JpushUtils;
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
public class MessageServiceImpl extends BaseService<Message> implements IMessageService{


    @Resource(name="hibernateBaseDao")
    private BaseDao<Member> memberDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<Moment> momentDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<MomentComment> momentCommentDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<MomentCommentReply> replyDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<Message> messageDao;

    @Resource
    private IMomentService momentServiceImpl;

    @Override
    public BaseDao<Message> getSuperDao() {
        return null;
    }


    @Override
    public RespData add(HttpHeaders headers, MessageAdd messageAdd) {

        try {
            String recipientId = messageAdd.getRecipientId();

            Member recipient = memberDao.get(Member.class,recipientId);

            final Message message = new Message();

            message.setCtime(new Date());

            message.setTimestamp(System.currentTimeMillis());

            message.setStatus("100");

            message.setType(messageAdd.getType());

            message.setContent(MessageCommonConstant.MESSAGE_TYPE_DETAIL.get(messageAdd.getType()));

            message.setServiceId(messageAdd.getServiceId());

            String momentId = messageAdd.getMomentId();

            if (StringUtils.isNotEmpty(momentId)) {
                Moment moment = momentDao.get(Moment.class,momentId);

                if (moment!=null){
                    message.setMoment(moment);
                }
            }

            //消息接收人
            message.setRecipient(recipient);

            //消息发送
            String senderId = messageAdd.getSenderId();

            Member sender = memberDao.get(Member.class,senderId);

            message.setSender(sender);

            messageDao.save(message);


            Runnable thread = new Runnable() {
                @Override
                public void run() {
                    JpushUtils.sendTo(
                            message.getRecipient().getMemberInfo().getMobile()
                            , message.getSender().getMemberInfo().getNickname()+"  "+message.getContent()
                            , message.getType());
                }
            };

            thread.run();

            return RespCode.getRespData(RespCode.SUCCESS,new HashMap<String,String>());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    @Override
    public RespData read(HttpHeaders headers, MessageRequest messageRequest) {

        try {

            String memberId = headers.getFirst("memberId");

            Message message = messageDao.get(Message.class,messageRequest.getMessageId());

            if (message!=null){

                message.setStatus("200");

                messageDao.saveOrUpdate(message);
            }

            Map<String,String> response = new HashMap<String,String>();

            response.put("messageSize",this.messageSize(memberId)+"");

            response.put("unReadMessageCount",this.unReadMessageSize(memberId)+"");

            return RespCode.getRespData(RespCode.SUCCESS,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());

    }

    @Override
    public RespData readAll(HttpHeaders headers, MessageRequest messageRequest) {

        try {

            String hql = "update Message m set m.status='200' where m.recipient.id=?";

            String recipientId = headers.getFirst("memberId");

            Query q = messageDao.createQuery(hql);

            q.setString(0,recipientId);

            q.executeUpdate();
            Map<String,String> response = new HashMap<String,String>();

            response.put("messageSize",this.messageSize(recipientId)+"");

            response.put("unReadMessageCount",this.unReadMessageSize(recipientId)+"");

            return RespCode.getRespData(RespCode.SUCCESS,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());

    }

    @Override
    public RespData list(HttpHeaders headers, MessageRequest messageRequest) {

        try {

            String memberId = headers.getFirst("memberId");

            int limit = 20;

            if (StringUtils.isNotEmpty(messageRequest.getPageSize())){
                limit = Integer.valueOf(messageRequest.getPageSize());
            }

            long timestamp = System.currentTimeMillis();

            if (StringUtils.isNotEmpty(messageRequest.getTimestamp())){
                timestamp = Long.valueOf(messageRequest.getTimestamp());
            }


            String hql = "from Message m where m.recipient.id=? and m.timestamp<"+timestamp;

            hql += " order by m.timestamp desc ";

            //查询文章列表
            Query q = messageDao.createQuery(hql);

            q.setString(0,memberId);

            q.setFirstResult(0);

            q.setMaxResults(limit);

            List<Message> messageList = q.list();

            Map<String,Object> response = new HashMap<String,Object>();

            response.put("messageList",this.fromMessageToResponse(memberId, messageList));

            return RespCode.getRespData(RespCode.SUCCESS,response);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());

    }

    @Override
    public int messageSize(String recipientId) {

            String hqlZanCount = "select count(m.id) from Message m where m.recipient.id=? ";

            Query q = messageDao.createQuery(hqlZanCount);

            q.setString(0,recipientId);

            long messageSize = (long)q.uniqueResult();

            return Integer.valueOf(messageSize+"");

    }

    @Override
    public int unReadMessageSize(String recipientId) {

        String hqlZanCount = "select count(m.id) from Message m where m.status='100' and m.recipient.id=? ";

        Query q = messageDao.createQuery(hqlZanCount);

        q.setString(0,recipientId);

        long messageSize = (long)q.uniqueResult();

        return Integer.valueOf(messageSize+"");

    }

    private List<MessageResponse> fromMessageToResponse(String memberId,List<Message> messageList){

        List<MessageResponse> responses = new ArrayList<MessageResponse>();

        for (Message message :
                messageList) {

            MessageResponse response = new MessageResponse();

            response.setTimestamp(message.getTimestamp()+"");

            Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            response.setCtime(f.format(message.getCtime()));

            response.setMessageId(message.getId());

            response.setType(message.getType());

            response.setServiceId(message.getServiceId());

            response.setStatus(message.getStatus());

//            CommunityMemberResponse memberResponse = informationServiceImpl.fromMemberToResponse(memberId,message.getOperator());

            //操作用户
            MessageSender mm = new MessageSender();

            mm.setMemberId(message.getSender().getId());

            mm.setNickname(message.getSender().getMemberInfo().getNickname());

            mm.setHeadurl(message.getSender().getMemberInfo().getIcon());

            response.setSender(mm);

            if (message.getMoment()!=null){

                //信息关联的动态(不是必有的,关注)
                MessageMoment mMoment = new MessageMoment();

                mMoment.setMomentId(message.getMoment().getId());

                mMoment.setCoverurl(message.getMoment().getImageList().get(0).getThumbnail_url_path());

                response.setMoment(mMoment);
            }

            responses.add(response);

        }

        return responses;

    }



}
