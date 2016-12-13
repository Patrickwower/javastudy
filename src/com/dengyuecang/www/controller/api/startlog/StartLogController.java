package com.dengyuecang.www.controller.api.startlog;

import com.dengyuecang.www.service.startlog.StartLogService;
import com.dengyuecang.www.service.startlog.model.StartLogRequest;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by lxrent on 2016/12/12.
 */
@RestController
@RequestMapping("/startlog")
public class StartLogController {

    @Resource
    private StartLogService startLogServiceImpl;

    @RequestMapping(value = "/start",method = RequestMethod.POST)
    @ResponseBody
    public RespData startlog(@RequestHeader HttpHeaders headers) {

        try {

            return startLogServiceImpl.save(headers);

        }catch (Exception e){
            e.printStackTrace();
        }
        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }

}
