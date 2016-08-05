package com.dengyuecang.www.controller.api.publish;

import com.dengyuecang.www.controller.api.community.model.ArticleRequest;
import com.dengyuecang.www.controller.api.publish.model.ArticlePublishRequest;
import com.dengyuecang.www.service.community.IArticleService;
import com.dengyuecang.www.service.publish.IPublishArticleService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by acang on 16/7/24.
 */
@RestController
@RequestMapping("/api/publish/article")
public class PublishArticleController {

    private static final Logger log = LoggerFactory.getLogger(PublishArticleController.class);

    @Resource
    private IArticleService articleServiceImpl;

    @Resource
    private IPublishArticleService publishArticleServiceImpl;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public RespData save(@RequestHeader HttpHeaders headers, ArticlePublishRequest articlePublishRequest){

        try {


            log.info("保存文章接口,参数列表");
            log.info("文章标题:"+articlePublishRequest.getTitle());
            log.info("文章封面:"+articlePublishRequest.getCover());
            log.info("文章内容:"+articlePublishRequest.getContent());
            return publishArticleServiceImpl.articleAdd(headers,articlePublishRequest);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public RespData update(@RequestHeader HttpHeaders headers, ArticlePublishRequest articlePublishRequest){

        try {

            return publishArticleServiceImpl.articleUpdate(headers,articlePublishRequest);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

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

    @RequestMapping(value = "categories")
    @ResponseBody
    public RespData categorys(){

        try {

            return publishArticleServiceImpl.categoryList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

}
