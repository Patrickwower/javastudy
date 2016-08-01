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

    public RespData articleAdd(HttpHeaders headers, ArticlePublishRequest articlePublishRequest);

    public RespData articleUpdate(HttpHeaders headers, ArticlePublishRequest articlePublishRequest);

    public RespData categoryList();

}
