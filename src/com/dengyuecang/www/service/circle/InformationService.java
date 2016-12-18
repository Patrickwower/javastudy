package com.dengyuecang.www.service.circle;

import com.dengyuecang.www.controller.api.circle.model.ImproveInformationRequest;
import com.dengyuecang.www.controller.api.circle.model.LoginRequest;
import com.dengyuecang.www.controller.api.circle.model.RegisterRequest;
import com.dengyuecang.www.entity.MemberInfo;
import com.dengyuecang.www.service.circle.model.UpdateInfo;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lxrent on 2016/12/13.
 */
public interface InformationService extends IBaseService<MemberInfo> {

    public RespData personInformation(HttpHeaders headers);

    public RespData memberInfo(HttpHeaders headers);

    public RespData information(HttpHeaders headers, String memberId);

    public RespData interestType();

    public RespData updateinfo(HttpHeaders headers, UpdateInfo updateinfo);

    public RespData register(HttpHeaders headers,RegisterRequest registerRequest)throws Exception;

    public RespData improveInformation(HttpHeaders headers, MultipartFile file, ImproveInformationRequest improveInformationRequest, HttpServletRequest servletRequest);

    public RespData login(HttpHeaders headers,LoginRequest loginRequest);



}
