package com.dengyuecang.www.service.circle.impl;

import com.dengyuecang.www.controller.api.circle.model.MomentEvaluateRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentPublishRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentUpdateRequest;
import com.dengyuecang.www.controller.api.circle.model.evaluation.EvaluationRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.circle.*;
import com.dengyuecang.www.service.IStaticResourceService;
import com.dengyuecang.www.service.circle.IInterestTypeService;
import com.dengyuecang.www.service.circle.IMessageService;
import com.dengyuecang.www.service.circle.IMomentService;
import com.dengyuecang.www.service.circle.common.MessageCommonConstant;
import com.dengyuecang.www.service.circle.model.MomentCreater;
import com.dengyuecang.www.service.circle.model.MomentImg;
import com.dengyuecang.www.service.circle.model.MomentInterest;
import com.dengyuecang.www.service.circle.model.MomentResponse;
import com.dengyuecang.www.service.circle.model.evaluation.EvaluationDiscussant;
import com.dengyuecang.www.service.circle.model.evaluation.EvaluationResponse;
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
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by acang on 16/6/22.
 */
@Service
public class MomentServiceImpl extends BaseService<Moment> implements IMomentService{


    @Resource(name="hibernateBaseDao")
    private BaseDao<Member> memberDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<Moment> momentDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<MomentEvaluation> momentEvaluationDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<MomentComment> momentCommentDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<InterestBar> interestBarDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<MomentImage> momentImageDao;

    @Resource
    private IStaticResourceService staticResourceServiceImpl;

    @Resource
    private IInterestTypeService interestTypeServiceImpl;

    @Resource
    private IMessageService messageServiceImpl;

    @Override
    public BaseDao<Moment> getSuperDao() {
        return null;
    }


    @Override
    public RespData queryList(HttpHeaders headers, MomentRequest momentRequest) {

        try {

            String memberId = headers.getFirst("memberId");

            int limit = 5;

            if (StringUtils.isNotEmpty(momentRequest.getPageSize())){
                limit = Integer.valueOf(momentRequest.getPageSize());
            }

            long timestamp = System.currentTimeMillis();

            if (StringUtils.isNotEmpty(momentRequest.getTimestamp())){
                timestamp = Long.valueOf(momentRequest.getTimestamp());
            }

            String status = "100";

            if (StringUtils.isNotEmpty(momentRequest.getStatus())){

                status = momentRequest.getStatus();
            }

            String hql = "from Moment where status='"+status+"' and timestamp<"+timestamp;

            String hqlPublicLevel = " and public_level='0' ";

            String hqlSort = " order by timestamp desc ";

            if (StringUtils.isNotEmpty(momentRequest.getInterestBar_id())){

                String interestBarId = momentRequest.getInterestBar_id();

                InterestBar ib = interestBarDao.get(InterestBar.class,interestBarId);

                hql += " and interestBar.id='"+interestBarId+"' ";

                if (ib!=null){

                    if (ib.getCreater().equals(memberId)){

                        hqlPublicLevel = " ";

                    }

                }

                hqlSort = " order by occurrence_timestamp desc ";

            }

            hql += hqlPublicLevel + hqlSort;

            //查询文章列表
            Query q = momentDao.createQuery(hql);

            q.setFirstResult(0);

            q.setMaxResults(limit);

            List<Moment> moments = q.list();

            Map<String,Object> response = new HashMap<String,Object>();

            response.put("moments",this.fromMomentToResponse(memberId, moments, momentRequest.getInterestBar_id()));

            return RespCode.getRespData(RespCode.SUCCESS,response);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    @Override
    public RespData queryListByParam(HttpHeaders headers, MomentRequest momentRequest) {

        try {

            String memberId = headers.getFirst("memberId");

            int limit = 5;

            if (StringUtils.isNotEmpty(momentRequest.getPageSize())){
                limit = Integer.valueOf(momentRequest.getPageSize());
            }

            long timestamp = System.currentTimeMillis();

            if (StringUtils.isNotEmpty(momentRequest.getTimestamp())){
                timestamp = Long.valueOf(momentRequest.getTimestamp());
            }

            String status = "100";

            if (StringUtils.isNotEmpty(momentRequest.getStatus())){

                status = momentRequest.getStatus();
            }

            String hql = "";

            if (momentRequest.getFilter()==null||"all".equals(momentRequest.getFilter())){

                hql = "from Moment where public_level='0' and status='"+status+"' and timestamp<"+timestamp;

                if (StringUtils.isNotEmpty(momentRequest.getInterestBar_id())){
                    hql += " and interestBar.id='"+momentRequest.getInterestBar_id()+"' ";
                }

                hql += " order by timestamp desc ";

            }else if ("follow".equals(momentRequest.getFilter())){

                hql = "select m from Moment m,MemberFollow mf where m.creater=mf.followed and mf.follow.id='"+memberId+"' and m.public_level='0' and m.status='"+status+"' and m.timestamp<"+timestamp;

                hql += " order by m.timestamp desc ";

            }


            //查询文章列表
            Query q = momentDao.createQuery(hql);

            q.setFirstResult(0);

            q.setMaxResults(limit);

            List<Moment> moments = q.list();

            Map<String,Object> response = new HashMap<String,Object>();

            response.put("moments",this.fromMomentToResponse(memberId, moments, null));

            return RespCode.getRespData(RespCode.SUCCESS,response);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());


    }

    private List<Moment> queryList(String hql,int limit){

        try {

            //查询文章列表
            Query q = momentDao.createQuery(hql);

            q.setFirstResult(0);

            q.setMaxResults(limit);

            List<Moment> moments = q.list();

            return moments;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private List<MomentResponse> fromMomentToResponse(String memberId, List<Moment> moments,String interestBarId){

        List<MomentResponse> response = new ArrayList<MomentResponse>();

        try {

            for (Moment moment:moments){

                MomentResponse momentResponse = new MomentResponse();

                momentResponse = this.momentToresponse(memberId,moment);

                if (StringUtils.isNotEmpty(interestBarId)){
                    momentResponse.setTimestamp(moment.getOccurrence_timestamp()+"");

                    //返回时间格式
                    Format f = new SimpleDateFormat("MMM.d,yyyy,hh:mmaa", Locale.ENGLISH);
                    momentResponse.setDate(f.format(moment.getOccurrence_time()).toUpperCase());
                }

                response.add(momentResponse);

            }

            return response;

        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public MomentResponse momentToresponse(String memberId,Moment moment){

        MomentResponse momentResponse = new MomentResponse();

        MomentImg momentImg = new MomentImg();

        MomentCreater momentCreater = new MomentCreater();

        MomentInterest momentInterest = new MomentInterest();

        momentResponse.setPublic_level(moment.getPublic_level());

        momentResponse.setCoverimg(moment.getImageList().get(0).getThumbnail_url_path());
        momentResponse.setTimestamp(moment.getTimestamp()+"");

        //返回时间格式
        Format f = new SimpleDateFormat("MMM.d,yyyy,hh:mmaa", Locale.ENGLISH);
        momentResponse.setDate(f.format(moment.getCtime()).toUpperCase());


        momentResponse.setContent(moment.getContent());
        momentResponse.setMomentId(moment.getId());

        //点赞数
        momentResponse.setZanCount(zanCount(moment.getId()));
        //当前用户是否点赞
        momentResponse.setIfZan(ifZan(memberId,moment.getId())+"");



        //评论数
        momentResponse.setCommentCount(commentCount(moment.getId()));
        //当前用户是否评论
        momentResponse.setIfComment(ifComment(memberId,moment.getId())+"");

        //----------------------------------------------------------------------------------------
        Member creater = moment.getCreater();

        momentCreater.setMemberId(creater.getId());
        momentCreater.setHeadUrl(creater.getMemberInfo().getIcon());
        momentCreater.setNickname(creater.getMemberInfo().getNickname());

        momentResponse.setCreater(momentCreater);
        //----------------------------------------------------------------------------------------
        InterestBar interestBar = moment.getInterestBar();

        momentInterest.setBar_id(interestBar.getId());
        momentInterest.setName(interestBar.getName());

        momentResponse.setInterest(momentInterest);
        //----------------------------------------------------------------------------------------
        MomentImage momentImage = moment.getImageList().get(0);

        momentImg.setSource_url(momentImage.getSource_url_path());

        momentImg.setHeight(momentImage.getHeight());

        momentImg.setWidth(momentImage.getWidth());

        momentResponse.getImgs().add(momentImg);

        return momentResponse;
    }

    @Override
    public RespData detail(HttpHeaders headers, String momentId) {

        try {

            String memberId = headers.getFirst("memberId");

            Member member = memberDao.get(Member.class,memberId);

            Moment moment = momentDao.get(Moment.class,momentId);

            //被删除了的
            if ("200".equals(moment.getStatus())){
                return RespCode.getRespData(RespCode.MOMENT_DELETED);
            }

            //非自的且私密的动态
            if (!memberId.equals(moment.getCreater().getId())&&"1".equals(moment.getPublic_level())){
                return RespCode.getRespData(RespCode.MOMENT_DENIED);
            }

            MomentResponse momentResponse = this.momentToresponse(memberId,moment);

            Map<String,Object> response = new HashMap<String,Object>();

            response.put("moment",momentResponse);

            return RespCode.getRespData(RespCode.SUCCESS, response);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());

    }

    private List<MomentResponse> prepareMoment(){

        try {

            MomentResponse momentResponse = new MomentResponse();

            MomentImg momentImg = new MomentImg();

            momentImg.setSource_url("1233333333");

            MomentCreater momentCreater = new MomentCreater();

            MomentInterest momentInterest = new MomentInterest();



            momentResponse.setCreater(momentCreater);
            momentResponse.setInterest(momentInterest);
            momentResponse.getImgs().add(momentImg);

            momentResponse.setContent("今天搞了一把好吉他,希望它能助我成大神");
            momentResponse.setDate("");
            momentResponse.setMomentId("1234");
            momentResponse.setZanCount("123");
            momentResponse.setTimestamp("1111111");


            List<MomentResponse> l = new ArrayList<MomentResponse>();
            l.add(momentResponse);
            return l;

        }catch (Exception e){

        }

        return null;
    }

    @Override
    public RespData evaluate(HttpHeaders headers, MomentEvaluateRequest momentEvaluateRequest) {

        try {

            String memberId = headers.getFirst("memberId");

            String momentId = momentEvaluateRequest.getMomentId();

            String hql = "from MomentEvaluation me where me.operator.id=? and me.moment.id=? ";

            Query q = momentEvaluationDao.createQuery(hql);

            q.setString(0,memberId);

            q.setString(1,momentId);

            MomentEvaluation me = (MomentEvaluation) q.uniqueResult();

            int ifZan = 0;

            if (me!=null){

                if (me.getEvaluation().equals(momentEvaluateRequest.getEvaluation())){

                    momentEvaluationDao.delete(me);

                }else {
                    me.setEvaluation(momentEvaluateRequest.getEvaluation());

                    me.setCtime(new Date());

                    me.setTimestamp(System.currentTimeMillis());

                    momentEvaluationDao.saveOrUpdate(me);
                }

            }else{

                Member operator = memberDao.get(Member.class,memberId);

                MomentEvaluation momentEvaluatation = new MomentEvaluation();

                momentEvaluatation.setOperator(operator);

                momentEvaluatation.setEvaluation(momentEvaluateRequest.getEvaluation());

                momentEvaluatation.setCtime(new Date());

                momentEvaluatation.setTimestamp(System.currentTimeMillis());

                Moment moment = new Moment();

                try{

                    moment = momentDao.get(Moment.class,momentEvaluateRequest.getMomentId());

                }catch (Exception e){
                    e.printStackTrace();
                }

                momentEvaluatation.setMoment(moment);

                momentEvaluationDao.save(momentEvaluatation);

                //站内消息
                MessageAdd messageAdd = new MessageAdd();

                messageAdd.setType(MessageCommonConstant.TYPE_ZAN);
                messageAdd.setMomentId(moment.getId());
                messageAdd.setServiceId(momentEvaluatation.getId());
                messageAdd.setSenderId(memberId);
                messageAdd.setRecipientId(moment.getCreater().getId());

                messageServiceImpl.add(headers,messageAdd);

                ifZan = 1;
            }


            Map<String,String> response = new HashMap<String,String>();

            response.put("ifZan",ifZan+"");

            response.put("zanCount",zanCount(momentId));

            return RespCode.getRespData(RespCode.SUCCESS, response);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    @Override
    public RespData add(HttpHeaders headers, MultipartFile file, MomentPublishRequest momentPublishRequest, HttpServletRequest servletRequest) {


        try {

            Moment moment = new Moment();

            moment.setCtime(new Date());

            moment.setTimestamp(System.currentTimeMillis());

            moment.setContent(momentPublishRequest.getContent());

            String memberId = headers.getFirst("memberId");
            Member creater = memberDao.get(Member.class,memberId);
            if (creater!=null)moment.setCreater(creater);

            String interestBarId = momentPublishRequest.getInterestBarId();
            InterestBar interestBar = interestBarDao.get(InterestBar.class,interestBarId);

            boolean useThisPicFlag = false;

            //如果封面为空,就把档案下所有动态的第一条的图作为封面
            if (StringUtils.isEmpty(interestBar.getCover())){

                    String hqlMoment = "from Moment m where m.interestBar.id=? and m.status='100' order by m.timestamp";

                    Query qMoment = momentDao.createQuery(hqlMoment);

                    qMoment.setString(0,interestBar.getId());

                    List<Moment> ms = qMoment.list();

                    if (ms.size()>0){
                        interestBar.setCover(ms.get(0).getImageList().get(0).getThumbnail_url_path());
                    }else{
                        useThisPicFlag = true;
                    }

//                interestBarDao.saveOrUpdate(interestBar);
            }

            if (interestBar!=null)moment.setInterestBar(interestBar);

            moment.setPublic_level(momentPublishRequest.getPublic_level());

            moment.setStatus("100");

            //设置发生时间

            if (StringUtils.isNotEmpty(momentPublishRequest.getOccurrence_time())){

                String occurrence_time = momentPublishRequest.getOccurrence_time();

                Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Date oc_time = (Date) f.parseObject(occurrence_time);

                moment.setOccurrence_time(oc_time);

                moment.setOccurrence_timestamp(oc_time.getTime());

            }else {

                moment.setOccurrence_time(moment.getCtime());

                moment.setOccurrence_timestamp(moment.getTimestamp());

            }

            momentDao.save(moment);

            MomentImage momentImage = new MomentImage();

            momentImage.setMoment(moment);

            momentImage.setSort("1");

            Map<String,String> urls = staticResourceServiceImpl.storeImageForCircleMoment(headers,file,servletRequest,momentPublishRequest.getImg_height(),momentPublishRequest.getImg_width());

            momentImage.setSource_url_path(urls.get("source_url"));

            momentImage.setThumbnail_url_path(urls.get("thumbnail_url"));

            momentImage.setHeight(momentPublishRequest.getImg_height());

            momentImage.setWidth(momentPublishRequest.getImg_width());

            momentImageDao.save(momentImage);

            //如果封面为空,就把档案下所有动态的第一条的图作为封面
            if (useThisPicFlag)interestBar.setCover(momentImage.getThumbnail_url_path());
            interestBarDao.saveOrUpdate(interestBar);

            return RespCode.getRespData(RespCode.SUCCESS,new HashMap<String,String>());

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    @Override
    public RespData edit(HttpHeaders headers, String id, MultipartFile file, MomentUpdateRequest momentUpdateRequest, HttpServletRequest servletRequest) {

        try {

            if (StringUtils.isNotEmpty(momentUpdateRequest.getMomentId())){

                String momentId = momentUpdateRequest.getMomentId();

                Moment moment = momentDao.get(Moment.class,momentId);

                String content = momentUpdateRequest.getContent();

                if (StringUtils.isNotEmpty(content)){

                    moment.setContent(momentUpdateRequest.getContent());

                }

                String interestBarId = momentUpdateRequest.getInterestBarId();

                if (StringUtils.isNotEmpty(interestBarId)){

                    InterestBar interestBar = interestBarDao.get(InterestBar.class,interestBarId);

                    if (interestBar!=null){

                        moment.setInterestBar(interestBar);

                    }

                }

                String public_level = momentUpdateRequest.getPublic_level();

                if (StringUtils.isNotEmpty(public_level)){
                    moment.setPublic_level(public_level);
                }

                String occurrence_time = momentUpdateRequest.getOccurrence_time();

                if (StringUtils.isNotEmpty(occurrence_time)){

                    Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date oc_time = (Date) f.parseObject(occurrence_time);

                    moment.setOccurrence_time(oc_time);

                    moment.setOccurrence_timestamp(oc_time.getTime());

                }

                List<MomentImage> imageList = new ArrayList<MomentImage>();
System.out.println("file是:"+file);
                if (file!=null){

                    for (MomentImage mi:
                            moment.getImageList()) {

                        momentImageDao.delete(mi);

                    }

                    MomentImage momentImage = new MomentImage();

                    momentImage.setMoment(moment);

                    momentImage.setSort("1");

                    Map<String,String> urls = staticResourceServiceImpl.storeImageForCircleMoment(headers,file,servletRequest,momentUpdateRequest.getImg_height(),momentUpdateRequest.getImg_width());

                    momentImage.setSource_url_path(urls.get("source_url"));

                    momentImage.setThumbnail_url_path(urls.get("thumbnail_url"));

                    momentImage.setHeight(momentUpdateRequest.getImg_height());

                    momentImage.setWidth(momentUpdateRequest.getImg_width());

                    momentImageDao.save(momentImage);

                }

                momentDao.saveOrUpdate(moment);

                return RespCode.getRespData(RespCode.SUCCESS,new HashMap<String,String>());
            }

        }catch (Exception e){

            e.printStackTrace();

        }
        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    @Override
    public RespData delete(HttpHeaders headers, String momentId) {

        try {

            String memberId = headers.getFirst("memberId");

            String hql = "from Moment m where m.id=? and m.creater.id=? ";

            Query q = momentDao.createQuery(hql);

            q.setString(0,momentId);

            q.setString(1,memberId);

            Moment moment = (Moment) q.uniqueResult();

            Map<String,String> response = new HashMap<String,String>();
            if (moment==null){
                response.put("msg","非本人动态,或其他异常,动态无法删除");
                return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,response);
            }

            moment.setStatus("200");

            momentDao.saveOrUpdate(moment);

            response.put("msg","删除成功");
            return RespCode.getRespData(RespCode.SUCCESS,response);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    @Override
    public RespData evaluationList(HttpHeaders headers, EvaluationRequest evaluationRequest) {

        try{

            String memberId = headers.getFirst("memberId");

            int limit = 20;

            if (StringUtils.isNotEmpty(evaluationRequest.getPageSize())){
                limit = Integer.valueOf(evaluationRequest.getPageSize());
            }

            long timestamp = System.currentTimeMillis();

            if (StringUtils.isNotEmpty(evaluationRequest.getTimestamp())){
                timestamp = Long.valueOf(evaluationRequest.getTimestamp());
            }

            String hql = "from MomentEvaluation me where me.moment.id=? and me.timestamp<"+timestamp;

            hql += " order by timestamp desc ";

            //查询文章列表
            Query q = momentEvaluationDao.createQuery(hql);

            q.setString(0,evaluationRequest.getMomentId());

            q.setFirstResult(0);

            q.setMaxResults(limit);

            List<MomentEvaluation> evaluations = q.list();

            Map<String,Object> response = new HashMap<String,Object>();

            response.put("evaluations",this.fromEvaluationToResponse(memberId, evaluations));

            return RespCode.getRespData(RespCode.SUCCESS,response);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    private List<EvaluationResponse> fromEvaluationToResponse(String memberId, List<MomentEvaluation> evaluations){

        List<EvaluationResponse> responses = new ArrayList<EvaluationResponse>();

        for (MomentEvaluation evaluation :
                evaluations) {

            EvaluationResponse response = new EvaluationResponse();

            response.setEvaluationId(evaluation.getId());

            Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            response.setCtime(f.format(evaluation.getCtime()));

            response.setTimestamp(evaluation.getTimestamp()+"");

            Member discussant = evaluation.getOperator();

            EvaluationDiscussant ed = new EvaluationDiscussant();

            ed.setDiscussantId(discussant.getId());

            ed.setHeadUrl(discussant.getMemberInfo().getIcon());

            ed.setNickname(discussant.getMemberInfo().getNickname());

            ed.setInterestTags(interestTypeServiceImpl.queryInterestTagsByMemberId(discussant.getId()));

            response.setDiscussant(ed);

            responses.add(response);
        }

        return responses;
    }



    private String zanCount(String momentId){

        String hqlZanCount = "select count(me.id) from MomentEvaluation me where me.moment.id=? and me.evaluation='0' ";

        Query q = momentEvaluationDao.createQuery(hqlZanCount);

        q.setString(0,momentId);

        long zanCount = (long)q.uniqueResult();

        return zanCount+"";

    }

    public int ifZan(String memberId,String momentId){

        if (memberId==null)return 0;

        //话题点赞数
        String hqlZanCount = "from MomentEvaluation me where me.moment.id=? and me.operator.id=? ";

        Query q = momentEvaluationDao.createQuery(hqlZanCount);

        q.setString(0,momentId);

        q.setString(1,memberId);

        List<MomentEvaluation> l = q.list();

        if (l.size()>0){
            return 1;
        }

        return 0;
    }

    public String commentCount(String momentId){

        String hqlCommentCount = "select count(mc.id) from MomentComment mc where mc.moment.id=? ";

        Query q = momentCommentDao.createQuery(hqlCommentCount);

        q.setString(0,momentId);

        long commentCount = (long)q.uniqueResult();

        return commentCount+"";

    }

    public int ifComment(String memberId,String momentId){

        if (memberId==null)return 0;

        //话题点赞数
        String hqlComment = "from MomentComment mc where mc.moment.id=? and mc.discussant.id=? ";

        Query q = momentCommentDao.createQuery(hqlComment);

        q.setString(0,momentId);

        q.setString(1,memberId);

        List<MomentComment> l = q.list();

        if (l.size()>0){
            return 1;
        }

        return 0;

    }

}
