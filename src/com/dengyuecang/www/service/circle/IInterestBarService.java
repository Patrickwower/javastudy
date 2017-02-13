package com.dengyuecang.www.service.circle;

import com.dengyuecang.www.controller.api.circle.model.AddInterestBarRequest;
import com.dengyuecang.www.entity.circle.InterestBar;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by acang on 16/6/22.
 */
public interface IInterestBarService extends IBaseService<InterestBar>{

    public RespData addInterestBar(HttpHeaders headers, AddInterestBarRequest addInterestBarRequest);

    public RespData addInterestBar(HttpHeaders headers, MultipartFile file, HttpServletRequest servletRequest, AddInterestBarRequest addInterestBarRequest);

    public RespData delete(HttpHeaders headers, String interestBarId);

    public RespData queryList(HttpHeaders headers);

    public RespData update(HttpHeaders headers, AddInterestBarRequest addInterestBarRequest);

    public RespData update(HttpHeaders headers, MultipartFile file, HttpServletRequest servletRequest, AddInterestBarRequest addInterestBarRequest);

    public RespData detail(HttpHeaders headers, AddInterestBarRequest addInterestBarRequest);

}
