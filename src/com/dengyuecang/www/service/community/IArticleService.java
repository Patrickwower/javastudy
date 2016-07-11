package com.dengyuecang.www.service.community;

import com.dengyuecang.www.controller.api.community.model.ArticleRequest;
import com.dengyuecang.www.controller.community.model.LongTextRequest;
import com.dengyuecang.www.entity.community.Article;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;

import java.util.List;

/**
 * Created by acang on 16/6/22.
 */
public interface IArticleService extends IBaseService<Article>{

    public void save(LongTextRequest longText);

    public List<Article> queryArticle(String memberId);

    public Article queryArticleById(String articleId);

    public RespData index();

    public RespData queryArticles(ArticleRequest articleRequest);

}
