package com.dengyuecang.www.service.circle.impl;

import cn.jiguang.common.utils.StringUtils;
import com.dengyuecang.www.controller.api.circle.model.AddInterestBarRequest;
import com.dengyuecang.www.controller.tool.model.FileUploadRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.StaticResource;
import com.dengyuecang.www.entity.circle.*;
import com.dengyuecang.www.service.IStaticResourceService;
import com.dengyuecang.www.service.circle.IInterestBarService;
import com.dengyuecang.www.service.circle.common.InterestBarCommonConstant;
import com.dengyuecang.www.service.circle.model.MomentInterest;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
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
public class InterestBarServiceImpl extends BaseService<InterestBar> implements IInterestBarService{


    @Resource(name="hibernateBaseDao")
    private BaseDao<Member> memberDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<Moment> momentDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<InterestBar> interestBarDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<InterestType> interestTypeDao;

    @Resource
    private IStaticResourceService staticResourceServiceImpl;

    @Override
    public BaseDao<InterestBar> getSuperDao() {
        return null;
    }


    @Override
    public RespData addInterestBar(HttpHeaders headers, AddInterestBarRequest addInterestBarRequest) {

        String memberId = headers.getFirst("memberId");

        InterestBar interestBar = new InterestBar();

        interestBar.setName(addInterestBarRequest.getName());

        interestBar.setDetail(addInterestBarRequest.getDetail());

        String types = addInterestBarRequest.getTypes();

        String[] typeArray = types.split(",");

        String hql = "from InterestType where name=? ";

        Set<InterestType> typeSet = new HashSet<InterestType>();

        for (String type :
                typeArray) {
            Query q = interestTypeDao.createQuery(hql);

            q.setString(0,type);

            InterestType interestType = (InterestType) q.uniqueResult();

            if (interestType==null){

                interestType = new InterestType();
                interestType.setName(type);
                interestType.setCreater(memberId);
                interestType.setParent_id("");

                interestTypeDao.save(interestType);
            }

            typeSet.add(interestType);

//            interestBar.getTypes().add(interestType);

        }

        interestBar.setTypes(typeSet);

        interestBar.setCreater(memberId);

        interestBar.setCtime(new Date());

        interestBar.setTimestamp(System.currentTimeMillis());

        if (StringUtils.isNotEmpty(addInterestBarRequest.getCover_img())){
            interestBar.setImg_url(addInterestBarRequest.getCover_img());
        }else {
            interestBar.setImg_url(prepareInterestBarCover(memberId));
        }

        interestBar.setStatus("100");

        interestBar.setCover("");

        interestBarDao.save(interestBar);

        Map<String,Object> response = new HashMap<String,Object>();

        return RespCode.getRespData(RespCode.SUCCESS,response);

    }

    /**
     * 保存头像
     */
    private String saveCover(HttpHeaders headers, MultipartFile file, HttpServletRequest servletRequest){

        FileUploadRequest fileUploadRequest = new FileUploadRequest();

        fileUploadRequest.setType("image");

        fileUploadRequest.setUsefor("circle/interestBar");

        RespData rd = staticResourceServiceImpl.imgUpload(headers,file,servletRequest,fileUploadRequest);

        StaticResource sr = (StaticResource)rd.getData();

        return sr.getUrlPath();
    }

    @Override
    public RespData addInterestBar(HttpHeaders headers, MultipartFile file, HttpServletRequest servletRequest, AddInterestBarRequest addInterestBarRequest) {

        if (file!=null){

            String cover_img = this.saveCover(headers,file,servletRequest);
            addInterestBarRequest.setCover_img(cover_img);

        }else {
            addInterestBarRequest.setCover_img("");
        }

        return this.addInterestBar(headers, addInterestBarRequest);
    }

    @Override
    public RespData delete(HttpHeaders headers, String interestBarId) {

        try {
            InterestBar bar = interestBarDao.get(InterestBar.class,interestBarId);

            if (bar!=null){
                bar.setStatus("200");
                interestBarDao.saveOrUpdate(bar);


                String momentDelHql = "update Moment m set m.status='200' where m.interestBar.id=? ";

                Query q = momentDao.createQuery(momentDelHql);

                q.setString(0,interestBarId);

                q.executeUpdate();

            }

            return RespCode.getRespData(RespCode.SUCCESS,new HashMap<String,String>());

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    private String prepareInterestBarCover(String memberId){

        List<InterestBar> barList = queryList(memberId);

        List<String> imgs = new ArrayList<String>();

        imgs.addAll(InterestBarCommonConstant.INTEREST_BAR_COVER);

        List<String> tmp = new ArrayList<String>();

        for (InterestBar interestBar:barList
             ) {
            imgs.remove(interestBar.getImg_url());
        }

        Random r = new Random();

        if (imgs.size()==0){
            imgs.addAll(InterestBarCommonConstant.INTEREST_BAR_COVER);
            return imgs.get(r.nextInt(imgs.size()));
        }

        return imgs.get(r.nextInt(imgs.size()));
    }

    @Override
    public RespData queryList(HttpHeaders headers) {

        String memberId = headers.getFirst("memberId");

        Map<String,Object> response = new HashMap<String,Object>();

        response.put("interestBars",this.queryList(memberId));

        return RespCode.getRespData(RespCode.SUCCESS,response);

    }

    private List<InterestBar> queryList(String memberId){

        String hql = "from InterestBar ib where ib.creater=? ";

        Query q = interestBarDao.createQuery(hql);

        q.setString(0,memberId);

        List<InterestBar> interestBars = q.list();

        return interestBars;
    }

    @Override
    public RespData update(HttpHeaders headers, AddInterestBarRequest addInterestBarRequest) {

        String memberId = headers.getFirst("memberId");

        InterestBar interestBar = new InterestBar();

        String bar_id = addInterestBarRequest.getBar_id();

        if (bar_id!=null){
            interestBar = interestBarDao.get(InterestBar.class,bar_id);
        }

        interestBar.setName(addInterestBarRequest.getName());

        interestBar.setDetail(addInterestBarRequest.getDetail());

        String types = addInterestBarRequest.getTypes();

        String[] typeArray = types.split(",");

        String hql = "from InterestType where name=? ";

        Set<InterestType> typeSet = new HashSet<InterestType>();

        for (String type :
                typeArray) {
            Query q = interestTypeDao.createQuery(hql);

            q.setString(0,type);

            InterestType interestType = (InterestType) q.uniqueResult();

            if (interestType==null){

                interestType = new InterestType();
                interestType.setName(type);
                interestType.setCreater(memberId);
                interestType.setParent_id("");

                interestTypeDao.save(interestType);
            }

            typeSet.add(interestType);

        }

        interestBar.setTypes(typeSet);

        if (StringUtils.isNotEmpty(addInterestBarRequest.getCover_img())){
            interestBar.setImg_url(addInterestBarRequest.getCover_img());
        }else {
            interestBar.setImg_url(prepareInterestBarCover(memberId));
        }

        interestBarDao.saveOrUpdate(interestBar);

        Map<String,Object> response = new HashMap<String,Object>();

        return RespCode.getRespData(RespCode.SUCCESS,response);
    }

    @Override
    public RespData update(HttpHeaders headers, MultipartFile file, HttpServletRequest servletRequest, AddInterestBarRequest addInterestBarRequest) {

        if (file!=null){

            String cover_img = this.saveCover(headers,file,servletRequest);
            addInterestBarRequest.setCover_img(cover_img);

        }else {
            addInterestBarRequest.setCover_img("");
        }

        return this.update(headers,addInterestBarRequest);
    }

    public RespData detail(HttpHeaders headers, AddInterestBarRequest addInterestBarRequest){

        String bar_id = addInterestBarRequest.getBar_id();

        InterestBar interestBar = interestBarDao.get(InterestBar.class,bar_id);

        if (interestBar==null){
            return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,"不存在此兴趣档案");
        }

        MomentInterest mi = new MomentInterest();

        mi.setImgurl(interestBar.getImg_url());

        mi.setName(interestBar.getName());

        mi.setBar_id(interestBar.getId());

        for (InterestType it:interestBar.getTypes()){

            mi.getTypes().add(it.getName());

        }

        mi.setDetail(interestBar.getDetail());

        Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        mi.setCtime(f.format(interestBar.getCtime()));

        mi.setCover(interestBar.getCover());

        Map<String,Object> response = new HashMap<String,Object>();

        response.put("interestBar",mi);

        return RespCode.getRespData(RespCode.SUCCESS,response);
    }

}
