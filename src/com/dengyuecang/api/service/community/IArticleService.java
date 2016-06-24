package com.dengyuecang.api.service.community;

import com.dengyuecang.api.controller.community.model.LongTextRequest;
import com.dengyuecang.api.entity.community.Article;
import com.longinf.lxcommon.service.IBaseService;

import java.util.List;

/**
 * Created by acang on 16/6/22.
 */
public interface IArticleService extends IBaseService<Article>{

    public void save(LongTextRequest longText);

    public List<Article> queryArticle(String memberId);

    public Article queryArticleById(String articleId);

}
