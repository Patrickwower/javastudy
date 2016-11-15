package com.dengyuecang.www.service.bigbox;

import com.dengyuecang.www.controller.api.h5.model.BigBoxUserInfo;
import com.dengyuecang.www.entity.bigbox.BigBoxUserinfo;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;

/**
 * Created by acang on 2016/11/9.
 */
public interface IBigBoxService extends IBaseService<BigBoxUserinfo> {

    public String addImg(HttpHeaders headers, BigBoxUserInfo bigBoxUserInfonfo);

    public RespData addPic(HttpHeaders headers, BigBoxUserInfo bigBoxUserInfonfo);

}
