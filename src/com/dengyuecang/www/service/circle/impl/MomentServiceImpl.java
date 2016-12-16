package com.dengyuecang.www.service.circle.impl;

import com.dengyuecang.www.controller.api.circle.model.MomentEvaluateRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentPublishRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.circle.InterestBar;
import com.dengyuecang.www.entity.circle.Moment;
import com.dengyuecang.www.entity.circle.MomentEvaluation;
import com.dengyuecang.www.entity.circle.MomentImage;
import com.dengyuecang.www.service.IStaticResourceService;
import com.dengyuecang.www.service.circle.IMomentService;
import com.dengyuecang.www.service.circle.model.MomentCreater;
import com.dengyuecang.www.service.circle.model.MomentImg;
import com.dengyuecang.www.service.circle.model.MomentInterest;
import com.dengyuecang.www.service.circle.model.MomentResponse;
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
    private BaseDao<InterestBar> interestBarDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<MomentImage> momentImageDao;

    @Resource
    private IStaticResourceService staticResourceServiceImpl;


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

            String status = "0";

            if (StringUtils.isNotEmpty(momentRequest.getStatus())){

                status = momentRequest.getStatus();
            }

            String hql = "from Moment where public_level='"+status+"' and timestamp<"+timestamp+" order by timestamp desc ";

//          hql = "select b from ArticleIndex a,Article b where a.article.id=b.id and a.timestamp < "+timestamp+" order by a.sort,a.index_time desc";

            //查询文章列表
            Query q = momentDao.createQuery(hql);

            q.setFirstResult(0);

            q.setMaxResults(limit);

            List<Moment> moments = q.list();

            Map<String,Object> response = new HashMap<String,Object>();

            response.put("moments",this.fromMomentToResponse(memberId, moments));

            return RespCode.getRespData(RespCode.SUCCESS,response);



//            List<Moment> ll = momentDao.createQuery("from Moment").list();
//
//            List<MomentResponse> momentList = new ArrayList<MomentResponse>();
//
//            Map<String,Object> response = new HashMap<String,Object>();
//
//            response.put("moments",prepareMoment());
//
//            return RespCode.getRespData(RespCode.SUCCESS,response);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    private List<MomentResponse> fromMomentToResponse(String memberId, List<Moment> moments){

        List<MomentResponse> response = new ArrayList<MomentResponse>();

        Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {

            for (Moment moment:moments){


                MomentResponse momentResponse = new MomentResponse();

                MomentImg momentImg = new MomentImg();

                MomentCreater momentCreater = new MomentCreater();

                MomentInterest momentInterest = new MomentInterest();


                momentResponse.setCoverimg(moment.getImageList().get(0).getThumbnail_url_path());
                momentResponse.setTimestamp(moment.getTimestamp()+"");
                momentResponse.setDate(f.format(moment.getCtime()));
                momentResponse.setContent(moment.getContent());
                momentResponse.setMomentId(moment.getId());
                momentResponse.setZanCount(zanCount(moment.getId()));
                momentResponse.setIfZan(ifZan(memberId,moment.getId())+"");

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

                response.add(momentResponse);

            }


            return response;


        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
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
            if (interestBar!=null)moment.setInterestBar(interestBar);

            moment.setPublic_level(momentPublishRequest.getPublic_level());

            momentDao.save(moment);

            MomentImage momentImage = new MomentImage();

            momentImage.setMoment(moment);

            momentImage.setSort("1");

            Map<String,String> urls = staticResourceServiceImpl.storeImageForCircleMoment(headers,file,servletRequest);

            momentImage.setSource_url_path(urls.get("source_url"));

            momentImage.setThumbnail_url_path(urls.get("thumbnail_url"));

            momentImage.setHeight(ImageIO.read(new File(urls.get("source_path"))).getHeight()+"");

            momentImage.setWidth(ImageIO.read(new File(urls.get("source_path"))).getWidth()+"");

            momentImageDao.save(momentImage);

            return RespCode.getRespData(RespCode.SUCCESS,new HashMap<String,String>());

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    @Override
    public RespData edit(HttpHeaders headers, String id, MultipartFile file, MomentPublishRequest momentPublishRequest, HttpServletRequest servletRequest) {

        try {

//            Moment moment = momentDao.get(Moment.class,id);
            Moment moment = new Moment();

            moment.setCtime(new Date());

            moment.setTimestamp(System.currentTimeMillis());

            moment.setContent(momentPublishRequest.getContent());

            InterestBar interestBar = interestBarDao.get(InterestBar.class, id);

//            String memberId = headers.getFirst("memberId");

            moment.setPublic_level(momentPublishRequest.getPublic_level());

            if (interestBar!=null)moment.setInterestBar(interestBar);

            momentDao.save(moment);

            MomentImage momentImage = new MomentImage();

            momentImage.setMoment(moment);

            momentImage.setSort("1");

            Map<String,String> urls = staticResourceServiceImpl.storeImageForCircleMoment(headers,file,servletRequest);

            momentImage.setSource_url_path(urls.get("source_url"));

            momentImage.setThumbnail_url_path(urls.get("thumbnail_url"));

            momentImageDao.save(momentImage);

            return RespCode.getRespData(RespCode.SUCCESS,new HashMap<String,String>());

        }catch (Exception e){

            e.printStackTrace();

        }
        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }


    private String zanCount(String momentId){

        String hqlZanCount = "select count(me.id) from MomentEvaluation me where me.moment.id=? and me.evaluation='0' ";

        Query q = momentEvaluationDao.createQuery(hqlZanCount);

        q.setString(0,momentId);

        long zanCount = (long)q.uniqueResult();

        return zanCount+"";

    }

    public boolean ifZan(String memberId,String momentId){

        if (memberId==null)return false;

        //话题点赞数
        String hqlZanCount = "from MomentEvaluation me where me.moment.id=? and me.operator.id=? ";

        Query q = momentEvaluationDao.createQuery(hqlZanCount);

        q.setString(0,momentId);

        q.setString(1,memberId);

        List<MomentEvaluation> l = q.list();

        if (l.size()>0){
            return true;
        }

        return false;
    }


}