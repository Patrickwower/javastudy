package com.dengyuecang.www.service.web;

import com.dengyuecang.www.controller.api.web.Model.ApplyBetaRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.web.ApplyBeta;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface IApplyBetaService extends IBaseService<ApplyBeta>{

	public RespData addApply(ApplyBetaRequest applyBetaRequest);

}
