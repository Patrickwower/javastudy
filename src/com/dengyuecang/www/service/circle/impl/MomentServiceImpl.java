package com.dengyuecang.www.service.circle.impl;

import com.dengyuecang.www.controller.api.circle.model.MomentEvaluateRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.circle.Moment;
import com.dengyuecang.www.service.circle.IMomentService;
import com.dengyuecang.www.service.circle.model.MomentCreater;
import com.dengyuecang.www.service.circle.model.MomentImg;
import com.dengyuecang.www.service.circle.model.MomentInterest;
import com.dengyuecang.www.service.circle.model.MomentResponse;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by acang on 16/6/22.
 */
@Service
public class MomentServiceImpl extends BaseService<Moment> implements IMomentService{


    @Resource(name="hibernateBaseDao")
    private BaseDao<Member> memberDao;

    @Override
    public BaseDao<Moment> getSuperDao() {
        return null;
    }


    @Override
    public RespData queryList(HttpHeaders headers, MomentRequest momentRequest) {

        try {
            List<MomentResponse> momentList = new ArrayList<MomentResponse>();

            Map<String,Object> response = new HashMap<String,Object>();

            response.put("moments",prepareMoment());

            return RespCode.getRespData(RespCode.SUCCESS,response);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    private List<MomentResponse> prepareMoment(){

        try {

            MomentResponse momentResponse = new MomentResponse();

            MomentImg momentImg = new MomentImg();

            MomentCreater momentCreater = new MomentCreater();

            MomentInterest momentInterest = new MomentInterest();



            momentResponse.setCreater(momentCreater);
            momentResponse.setInterest(momentInterest);
            momentResponse.getImgs().add(momentImg);

            momentResponse.setContent("今天搞了一把好吉他,希望它能助我成大神");
            momentResponse.setDate("");
            momentResponse.setMomentId("1234");
            momentResponse.setZanCount("123");
            momentResponse.setTimestamp(1111111111);


            List<MomentResponse> l = new ArrayList<MomentResponse>();
            l.add(momentResponse);
            return l;

        }catch (Exception e){

        }

        return null;
    }

    @Override
    public RespData evaluate(HttpHeaders httpHeaders, MomentEvaluateRequest momentEvaluateRequest) {

        try {

            Map<String,Object> response = new HashMap<String,Object>();

            response.put("tag","ok");

            return RespCode.getRespData(RespCode.SUCCESS,response);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }
}
