package com.dengyuecang.www.service.startlog;

import com.dengyuecang.www.entity.startlog.StartLog;
import com.dengyuecang.www.service.startlog.model.StartLogRequest;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;

/**
 * Created by lxrent on 2016/12/12.
 */
public interface StartLogService extends IBaseService<StartLog> {

    public RespData savenumber(HttpHeaders headers,String num);

    public RespData save(HttpHeaders headers);

}
