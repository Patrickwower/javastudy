package com.dengyuecang.www.service.circle;

import com.dengyuecang.www.controller.api.circle.model.*;
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
public interface IInformationService extends IBaseService<MemberInfo> {

    public RespData information(HttpHeaders headers, String memberId);

    public RespData updateinfo(HttpHeaders headers, UpdateInfo updateinfo);

    public RespData register(HttpHeaders headers,RegisterRequest registerRequest)throws Exception;

    public RespData improveInformation(HttpHeaders headers, MultipartFile file, ImproveInformationRequest improveInformationRequest, HttpServletRequest servletRequest);

    public RespData login(HttpHeaders headers,LoginRequest loginRequest);

    public RespData modifyPwd(HttpHeaders headers, ModifyPwdRequest modifyPwdRequest);

    public RespData findPwdByVerify(HttpHeaders headers, FindPwdByVerifyRequest findPwdByVerifyRequest);

    public RespData resetPwd(HttpHeaders headers,ResetPwdRequest resetPwdRequest);

    public RespData updateHead(HttpHeaders headers, MultipartFile file, HttpServletRequest servletRequest);

}
