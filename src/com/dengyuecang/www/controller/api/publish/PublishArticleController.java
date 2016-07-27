package com.dengyuecang.www.controller.api.publish;

import com.dengyuecang.www.controller.api.community.model.ArticleRequest;
import com.dengyuecang.www.controller.api.publish.model.ArticlePublishRequest;
import com.dengyuecang.www.service.community.IArticleService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by acang on 16/7/24.
 */
@RestController
@RequestMapping("/api/publish/article")
public class PublishArticleController {

    @Resource
    private IArticleService articleServiceImpl;

    public RespData save(@RequestHeader HttpHeaders headers, ArticlePublishRequest articlePublishRequest){



        return null;
    }

    public RespData update(@RequestHeader HttpHeaders headers, ArticlePublishRequest articlePublishRequest){



        return null;
    }

    @RequestMapping(value = "/myarticles",method = RequestMethod.POST)
    @ResponseBody
    public RespData list(@RequestHeader HttpHeaders headers, ArticleRequest articleRequest){

        try {
            return articleServiceImpl.listByAuthor(headers,articleRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }

//    public RespData

}
