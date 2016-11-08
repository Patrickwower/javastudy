package com.dengyuecang.www.service.publish;

import com.dengyuecang.www.controller.api.publish.model.ArticlePublishRequest;
import com.dengyuecang.www.entity.community.Article;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;

/**
 * Created by acang on 16/7/27.
 */
public interface IPublishArticleService extends IBaseService<Article>{


    /**
     * 保存草稿接口
     * @param headers
     * @param articlePublishRequest
     * @return
     */
    public RespData draftAdd(HttpHeaders headers, ArticlePublishRequest articlePublishRequest);


    /**
     * 草稿发布接口
     * @param headers
     * @param articlePublishRequest
     * @return
     */
    public RespData draftPublish(HttpHeaders headers, ArticlePublishRequest articlePublishRequest);


    /**
     * 保存文章接口
     * @param headers
     * @param articlePublishRequest
     * @return
     */
    public RespData articleAdd(HttpHeaders headers, ArticlePublishRequest articlePublishRequest);

    public RespData articleUpdate(HttpHeaders headers, ArticlePublishRequest articlePublishRequest);

    public RespData categoryList();

    public RespData articleDel(HttpHeaders headers, ArticlePublishRequest articlePublishRequest);

    /**
     * 补全cover和放行展示图
     */
    public Article fullCover(Article article);

}
