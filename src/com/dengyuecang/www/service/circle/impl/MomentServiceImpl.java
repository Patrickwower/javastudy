package com.dengyuecang.www.service.circle.impl;

import com.dengyuecang.www.controller.api.circle.model.MomentEvaluateRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.circle.Moment;
import com.dengyuecang.www.service.circle.IMomentService;
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

            response.put("moments",momentList);

            return RespCode.getRespData(RespCode.SUCCESS,response);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
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
