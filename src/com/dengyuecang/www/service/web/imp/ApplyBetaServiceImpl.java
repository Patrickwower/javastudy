package com.dengyuecang.www.service.web.imp;

import com.dengyuecang.www.controller.api.web.Model.ApplyBetaRequest;
import com.dengyuecang.www.entity.web.ApplyBeta;
import com.dengyuecang.www.service.web.IApplyBetaService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by acang on 16/7/21.
 */
@Service
public class ApplyBetaServiceImpl extends BaseService<ApplyBeta> implements IApplyBetaService{

    @Resource(name = "hibernateBaseDao")
    private BaseDao<ApplyBeta> applyBetaDao;

    @Override
    public RespData addApply(ApplyBetaRequest applyBetaRequest) {

        ApplyBeta ab = new ApplyBeta();

        ab.setCtime(new Date());

        ab.setDevice(applyBetaRequest.getDevice());

        ab.setDistrict(applyBetaRequest.getDistrict());

        ab.setEmail(applyBetaRequest.getEmail());

        ab.setKnow_from(applyBetaRequest.getFrom());

        ab.setInteresting(applyBetaRequest.getInteresting());

        ab.setSchool(applyBetaRequest.getSchool());

        applyBetaDao.save(ab);

        Map<String, String> response = new HashMap<String, String>();

        return RespCode.getRespData(RespCode.SUCESS,response);
    }

    @Override
    public BaseDao<ApplyBeta> getSuperDao() {
        return null;
    }
}
