package com.dengyuecang.www.service.community.impl;

import com.dengyuecang.www.controller.api.community.model.CommentRequest;
import com.dengyuecang.www.controller.api.community.model.EvaluteRequest;
import com.dengyuecang.www.controller.api.community.model.TopicCommentRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.community.*;
import com.dengyuecang.www.service.community.ITopicService;
import com.dengyuecang.www.service.community.model.*;
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
public class TopicServiceImpl extends BaseService<Topic> implements ITopicService{

    @Resource(name="hibernateBaseDao")
    private BaseDao<Topic> topicDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<TopicComment> topicCommentDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<TopicEvaluate> topicEvaluateDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<Member> memberDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<TopicEveryday> topicEverydayDao;

//    @Resource(name="hibernateBaseDao")
//    private HibernateBaseDao baseDao;

    @Override
    public BaseDao<Topic> getSuperDao() {
        return null;
    }


    @Override
    public RespData getEveryDayTopic(HttpHeaders headers) {

        IndexTopic iTopic = new IndexTopic();

        Date today = new Date();

        Format f1 = new SimpleDateFormat("d");

        String day = f1.format(today);

        Format f2 = new SimpleDateFormat("MMM", Locale.ENGLISH);

        String month = f2.format(today);

        Format f3 = new SimpleDateFormat("yyyy");

        String year = f3.format(today);

        iTopic.setDay(day);

        iTopic.setMonth(month);

        iTopic.setYear(year);

        Format f4 = new SimpleDateFormat("yyyy-MM-dd");

        //查询每日话题
        TopicEveryday topicEveryday = (TopicEveryday)topicEverydayDao.createQuery("from TopicEveryday where date_format(issueDate,'%Y-%m-%d')='"+f4.format(today)+"' ").uniqueResult();

        if (topicEveryday!=null){
            iTopic.setId(topicEveryday.getTopic().getId());

            iTopic.setName(topicEveryday.getTopic().getTitle());
        }

        iTopic.setZanCount(zanCount(iTopic.getId()));

        String memberId = headers.getFirst("memberId");

        iTopic.setIfZan(ifZan(memberId,iTopic.getId())+"");

        iTopic.setBgimg("");

        Map<String,Object> response = new HashMap<String,Object>();

        response.put("topic",iTopic);

        return RespCode.getRespData(RespCode.SUCESS,response);
    }

    @Override
    public RespData queryTopic(String topicId) {
        return null;
    }

    @Override
    public RespData comments(TopicCommentRequest request) {

        int limit = 10;

        if (StringUtils.isNotEmpty(request.getPageSize())){
            limit = Integer.valueOf(request.getPageSize());
        }

        long timestamp = System.currentTimeMillis();

        if (StringUtils.isNotEmpty(request.getTimestamp())){
            timestamp = Long.valueOf(request.getTimestamp());
        }

        String hql = "from TopicComment tc where tc.topic.id=? and timestamp<"+timestamp+" order by timestamp desc ";

        Query q = topicDao.createQuery(hql);

        q.setFirstResult(0);

        q.setMaxResults(limit);

        q.setString(0,request.getTopicId());

        List<TopicComment> topicComments = q.list();

        List<TopicCommentResponse> topicCommentResponses = new ArrayList<TopicCommentResponse>();

        for (TopicComment topicComment: topicComments
             ) {

            TopicCommentResponse topicCommentResponse = new TopicCommentResponse();

            topicCommentResponse.setComment(topicComment.getComment());

            topicCommentResponse.setId(topicComment.getId());

            topicCommentResponse.setCtime(topicComment.getCtime());

            topicCommentResponse.setTimestamp(topicComment.getTimestamp()+"");

            Member discussant = topicComment.getDiscussant();

            TopicDiscussant topicDiscussant = new TopicDiscussant();

            topicDiscussant.setId(discussant.getId());

            topicDiscussant.setHead(discussant.getMemberInfo().getIcon());

            topicDiscussant.setNickname(discussant.getMemberInfo().getNickname());

            topicCommentResponse.setDiscussant(topicDiscussant);

            topicCommentResponses.add(topicCommentResponse);
        }

       return RespCode.getRespData(RespCode.SUCESS,topicCommentResponses);
    }

    @Override
    public RespData comment(HttpHeaders headers, CommentRequest request) {

        TopicComment comment = new TopicComment();

        comment.setComment(request.getComment());

        comment.setTimestamp(System.currentTimeMillis());

        comment.setCtime(new Date());

        Topic topic = new Topic();

        try{

            topic = topicDao.get(Topic.class,request.getTopicId());

        }catch (Exception e){
            e.printStackTrace();
        }

        comment.setTopic(topic);

        String memberId = headers.getFirst("memberId");

        Member discussant = memberDao.get(Member.class,memberId);

        comment.setDiscussant(discussant);

        topicCommentDao.save(comment);

        Map<String,String> response = new HashMap<String,String>();

        return RespCode.getRespData(RespCode.SUCESS, response);
    }

    @Override
    public RespData evaluate(HttpHeaders headers, EvaluteRequest evaluteRequest) {

        String memberId = headers.getFirst("memberId");

        String topicId = evaluteRequest.getTopicId();

        String hql = "from TopicEvaluate te where te.discussant.id=? and te.topic.id=? ";

        Query q = topicEvaluateDao.createQuery(hql);

        q.setString(0,memberId);

        q.setString(1,topicId);

        TopicEvaluate te = (TopicEvaluate) q.uniqueResult();

        if (te!=null){

            if (te.getEvaluation().equals(evaluteRequest.getEvaluation())){

                topicEvaluateDao.delete(te);

            }else {
                te.setEvaluation(evaluteRequest.getEvaluation());

                te.setCtime(new Date());

                te.setTimestamp(System.currentTimeMillis());

                topicEvaluateDao.saveOrUpdate(te);
            }

        }else{

            Member discussant = memberDao.get(Member.class,memberId);

            TopicEvaluate topicEvaluate = new TopicEvaluate();

            topicEvaluate.setDiscussant(discussant);

            topicEvaluate.setEvaluation(evaluteRequest.getEvaluation());

            topicEvaluate.setCtime(new Date());

            topicEvaluate.setTimestamp(System.currentTimeMillis());

            Topic topic = new Topic();

            try{

                topic = topicDao.get(Topic.class,evaluteRequest.getTopicId());

            }catch (Exception e){
                e.printStackTrace();
            }

            topicEvaluate.setTopic(topic);

            topicEvaluateDao.save(topicEvaluate);
        }


        Map<String,String> response = new HashMap<String,String>();

        return RespCode.getRespData(RespCode.SUCESS, response);
    }

    @Override
    public RespData topicData(String topicId) {

        //话题点赞数
        String hqlZanCount = "select count(te.id) from TopicEvaluate te where te.topic.id=? and te.evaluation='0' ";

        Query q = topicEvaluateDao.createQuery(hqlZanCount);

        q.setString(0,topicId);

        long zanCount = (long)q.uniqueResult();

        Map<String,String> response = new HashMap<String,String>();

        response.put("zanCount",zanCount+"");

        return RespCode.getRespData(RespCode.SUCESS,response);
    }

    public String zanCount(String topicId){

        //话题点赞数
        String hqlZanCount = "select count(te.id) from TopicEvaluate te where te.topic.id=? and te.evaluation='0' ";

        Query q = topicEvaluateDao.createQuery(hqlZanCount);

        q.setString(0,topicId);

        long zanCount = (long)q.uniqueResult();

        return zanCount+"";
    }

    public boolean ifZan(String discussantId,String topicId){

        if (discussantId==null)return false;

        //话题点赞数
        String hqlZanCount = "from TopicEvaluate te where te.topic.id=? and te.discussant.id=? ";

        Query q = topicEvaluateDao.createQuery(hqlZanCount);

        q.setString(0,topicId);

        q.setString(1,discussantId);

        List<TopicEvaluate> l = q.list();

        if (l.size()>0){
            return true;
        }

        return false;
    }

}
