package com.dengyuecang.www.service.community;

import com.dengyuecang.www.controller.api.community.model.*;
import com.dengyuecang.www.controller.community.model.LongTextRequest;
import com.dengyuecang.www.entity.community.Article;
import com.dengyuecang.www.service.community.model.IndexArticle;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.params.PageModel;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;

import java.util.List;

/**
 * Created by acang on 16/6/22.
 */
public interface IArticleService extends IBaseService<Article>{

   public void save(LongTextRequest longText);

   public List<Article> queryArticle(String memberId);

   public Article queryArticleById(String articleId);

   public RespData index();

   /**
    * 文章列表,普通文章列表
    * @param headers
    * @param articleRequest
    * @return
     */
   public RespData queryArticles(HttpHeaders headers, ArticleRequest articleRequest);

    /**
     * 热门文章首页列表
     * @param headers
     * @param pageModel
     * @return
     */
   public RespData hotList(HttpHeaders headers, PageModel pageModel);

   /**
    * 草稿列表
    * @param headers
    * @param articleRequest
    * @return
     */
   public RespData drafts(HttpHeaders headers, ArticleRequest articleRequest);


   public RespData getArticle(String articleId);

   public RespData comments(HttpHeaders headers,ArticleCommentRequest request);

   public RespData comment(HttpHeaders headers, CommentRequest request);

   public RespData evaluate(HttpHeaders headers, EvaluteRequest evaluteRequest);

   public RespData comment_evaluate(HttpHeaders headers, EvaluteRequest evaluteRequest);

   public RespData collection(HttpHeaders headers, CollectionRequest collectionRequest);

   public RespData articleData(HttpHeaders headers, String articleId);

   public RespData focusAuthor(HttpHeaders headers, FocusAuthorRequest request);

   public RespData recommends(HttpHeaders headers);

   public RespData listByAuthor(HttpHeaders headers, ArticleRequest articleRequest);

   public RespData focusList(HttpHeaders headers, FocusAuthorRequest focusRequest);

   public Integer addBrowse(HttpHeaders headers,String articleId);

   public RespData browse(HttpHeaders headers,String articleId);

   public RespData collections(HttpHeaders headers,CollectionRequest collectionRequest);

   public IndexArticle toIndexArticle(String memberId, Article article);

   public List<IndexArticle> toIndexArticleList(String memberId, List<IndexArticle> articles, List<Article> articleList);

   public RespData delete(HttpHeaders headers,String articleId);

   public RespData commentDelete(HttpHeaders headers,String commentId);

}
