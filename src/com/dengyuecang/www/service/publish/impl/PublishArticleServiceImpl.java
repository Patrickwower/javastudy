package com.dengyuecang.www.service.publish.impl;

import com.dengyuecang.www.controller.api.publish.model.ArticlePublishRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.StaticResource;
import com.dengyuecang.www.entity.community.*;
import com.dengyuecang.www.service.IStaticResourceService;
import com.dengyuecang.www.service.common.CommonConstant;
import com.dengyuecang.www.service.publish.IPublishArticleService;
import com.dengyuecang.www.service.publish.model.CategoryResponse;
import com.dengyuecang.www.utils.RegexUtils;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.dengyuecang.www.utils.img.ImgUtils;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by acang on 16/7/27.
 */
@Service
public class PublishArticleServiceImpl extends BaseService<Article> implements IPublishArticleService{


    @Resource(name = "hibernateBaseDao")
    private BaseDao<Member> memberDao;

    @Resource(name = "hibernateBaseDao")
    private BaseDao<Category> categoryDao;

    @Resource(name = "hibernateBaseDao")
    private BaseDao<Tag> tagDao;

    @Resource(name = "hibernateBaseDao")
    private BaseDao<Article> articleDao;

    @Resource(name = "hibernateBaseDao")
    private BaseDao<ArticleUpdateLog> articleUpdateLogDao;

    @Resource(name = "hibernateBaseDao")
    private BaseDao<ArticleIndex> articleIndexDao;

    @Resource
    private IStaticResourceService staticResourceServiceImpl;


    @Override
    public RespData draftAdd(HttpHeaders headers,ArticlePublishRequest articlePublishRequest) {

        try {
            String memberId = headers.getFirst("memberId");

            if (StringUtils.isEmpty(memberId)){

                memberId = articlePublishRequest.getMemberId();

            }

            if (StringUtils.isEmpty(memberId)){

                return RespCode.getRespData(RespCode.UNLOGIN);

            }

            Article article = this.articleSave(headers,articlePublishRequest,"50");

            Map<String,String> response = new HashMap<String,String>();

            response.put("articleId",article.getId());

            return RespCode.getRespData(RespCode.SUCCESS,response);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    @Override
    public RespData draftPublish(HttpHeaders headers, ArticlePublishRequest articlePublishRequest) {

        articlePublishRequest.setStatus("100");

        return this.articleUpdate(headers,articlePublishRequest);

    }


    /**
     * 文章保存用,草稿和直接发布都走这,只是最终状态不同
     * @return
     */
    private Article articleSave(HttpHeaders headers,ArticlePublishRequest articlePublishRequest,String status){

        Article article = new Article();

        article.setStatus(status);
        article.setTimestamp(System.currentTimeMillis());
        article.setContent(StringUtils.isEmpty(articlePublishRequest.getContent())?"":articlePublishRequest.getContent());


        String summary = RegexUtils.getStringFromHtml(article.getContent());

        article.setSummary(summary);

        if (StringUtils.isNotEmpty(articlePublishRequest.getWordCount())){
            article.setWordCount(Integer.valueOf(articlePublishRequest.getWordCount()));
        }else{
            article.setWordCount(0);
        }

        article.setCover(StringUtils.isEmpty(articlePublishRequest.getCover())?"":articlePublishRequest.getCover());

        //如果没有设置封面,则从文本中取出第一张图片作为封面图

        article = this.fullCover(article);

        article.setCtime(new Date());
        article.setTitle(StringUtils.isEmpty(articlePublishRequest.getTitle())?"":articlePublishRequest.getTitle());

        String memberId = headers.getFirst("memberId");

        if (StringUtils.isEmpty(memberId)){

            memberId = articlePublishRequest.getMemberId();

        }

        Member member = memberDao.get(Member.class,memberId);

        article.setMember(member);

//        article.set

        String catid = articlePublishRequest.getCategory();

        if (StringUtils.isNotEmpty(catid)){

            Category cat = categoryDao.get(Category.class,catid);

            Set<Category> catlist = new HashSet<Category>();

            catlist.add(cat);

            article.setCategories(catlist);

        }

        if (StringUtils.isNotEmpty(articlePublishRequest.getTags())){

            String tagstring = articlePublishRequest.getTags();

            String[] tags = tagstring.split(",");

            Set<Tag> tagList = new HashSet<Tag>();

            for (String tag :
                    tags) {

                Query q = tagDao.createQuery("from Tag t where t.name=?");

                q.setString(0,tag);

                Tag tagModel = (Tag) q.uniqueResult();

                if (tagModel==null){

                    tagModel=new Tag();
                    tagModel.setCtime(new Date());
                    tagModel.setCreater(memberId);
                    tagModel.setName(tag);
                    tagModel.setStatus("100");

                    tagDao.save(tagModel);

                }

                tagList.add(tagModel);

            }

            article.setTags(tagList);
        }


        article.setUrl(CommonConstant.ARTICLE_DETAIL_URL);

        article.setShareUrl(CommonConstant.ARTICLE_SHARE_URL);

        article.setIf_index("0");

        article.setBanner("0");

        articleDao.save(article);

        return article;
    }


    @Override
    public RespData articleAdd(HttpHeaders headers,ArticlePublishRequest articlePublishRequest) {

        String memberId = headers.getFirst("memberId");

        if (StringUtils.isEmpty(memberId)){

            memberId = articlePublishRequest.getMemberId();

        }

        if (StringUtils.isEmpty(memberId)){

            return RespCode.getRespData(RespCode.UNLOGIN);

        }

        Article article = this.articleSave(headers,articlePublishRequest,"100");

        //直接放入首页热门

        if (CommonConstant.PUBLISH_TO_INDEX_HOT){

            ArticleIndex ai = new ArticleIndex();

            ai.setArticle(article);

            ai.setIndex_time(new Date());

            ai.setMax_sort("99999999");

            ai.setSort(99999999);

            ai.setTimestamp(System.currentTimeMillis());

            articleIndexDao.save(ai);

            article.setIf_index("1");

            articleDao.saveOrUpdate(article);
        }

        Map<String,String> response = new HashMap<String,String>();

        response.put("articleId",article.getId());

        return RespCode.getRespData(RespCode.SUCCESS,response);
    }

    public Article fullCover(Article article){

        //需在set了cover和content后执行,以为了补全没有cover的情况

        if (StringUtils.isEmpty(article.getCover())){

            List<String> imgList = RegexUtils.getImgsFromHtml(article.getContent());

            if (imgList.size()>0){
                article.setCover(imgList.get(0));

                StaticResource staticResource = staticResourceServiceImpl.getResourceByUrl(article.getCover());

                String imgPath = staticResource.getPath().substring(0,staticResource.getPath().lastIndexOf("."))+"_corp"+staticResource.getPath().substring(staticResource.getPath().lastIndexOf("."));

                String squareCover = "";

                try {

                    squareCover = staticResource.getUrlPath().substring(0,staticResource.getUrlPath().lastIndexOf("."))+"_corp"+staticResource.getUrlPath().substring(staticResource.getUrlPath().lastIndexOf("."));
                    ImgUtils.squareCrop(staticResource.getPath(),imgPath);
                }catch (Exception e){
                    e.printStackTrace();
                }



                article.setSquareCover(squareCover);

            }

        }

        return article;
    }


    @Override
    public BaseDao<Article> getSuperDao() {
        return null;
    }

    @Override
    public RespData articleUpdate(HttpHeaders headers, ArticlePublishRequest articlePublishRequest) {

        String articleId = articlePublishRequest.getArticleId();

        Article article = articleDao.get(Article.class,articleId);



        if (article!=null){

            ArticleUpdateLog articleUpdateLog = new ArticleUpdateLog();

//            String[] tagArray = new String[(article.getTags().size())];
//
//            for (int i = 0; i <article.getTags().size(); i++) {
//                tagArray[i] = article.getTags().iterator().next().getName();
//            }

//            articleUpdateLog.setTags(StringUtils.join(tagArray,","));

//            articleUpdateLog.setCid(article.getCategories().iterator().next().getId());

            articleUpdateLog.setTags("");

            articleUpdateLog.setCid("");

            articleUpdateLog.setCtime(new Date());

            articleUpdateLog.setArticle_id(article.getId());

            articleUpdateLog.setContent(article.getContent());

            articleUpdateLog.setCover(article.getCover());

            articleUpdateLog.setTitle(article.getTitle());

            articleUpdateLog.setTimestamp(System.currentTimeMillis());

            articleUpdateLog.setWordCount(article.getWordCount());

            articleUpdateLogDao.save(articleUpdateLog);

 //-------------------------------------------------------------------------------------
            //标题
            article.setTitle(articlePublishRequest.getTitle());
            //封面
//            article.setCover(articlePublishRequest.getCover());

            //如果没有设置封面,则从文本中取出第一张图片作为封面图
            article = this.fullCover(article);

//            if (StringUtils.isEmpty(articlePublishRequest.getCover())){
//
//                List<String> imgList = RegexUtils.getImgsFromHtml(articlePublishRequest.getContent());
//
//                if (imgList.size()>0){
//                    article.setCover(imgList.get(0));
//
//                    StaticResource staticResource = staticResourceServiceImpl.getResourceByUrl(article.getCover());
//
//                    String imgPath = staticResource.getPath().substring(0,staticResource.getPath().lastIndexOf("."))+"_corp"+staticResource.getPath().substring(staticResource.getPath().lastIndexOf("."));
//
//                    String squareCover = "";
//
//                    try {
//
//
//                        squareCover = staticResource.getUrlPath().substring(0,staticResource.getUrlPath().lastIndexOf("."))+"_corp"+staticResource.getUrlPath().substring(staticResource.getUrlPath().lastIndexOf("."));
//                        ImgUtils.squareCrop(staticResource.getPath(),imgPath);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//
//                    article.setSquareCover(squareCover);
//
//                }
//
//            }


            //内容
            article.setContent(articlePublishRequest.getContent());

            if (StringUtils.isNotEmpty(articlePublishRequest.getWordCount())){
                article.setWordCount(Integer.valueOf(articlePublishRequest.getWordCount()));
            }else{
                article.setWordCount(0);
            }

            article.setStatus(StringUtils.isEmpty(articlePublishRequest.getStatus())?article.getStatus():articlePublishRequest.getStatus());

            article.setUtime(new Date());

//            article.setCategories(null);article.setTags(null);

            articleDao.saveOrUpdate(article);

            //tags标签
//            String tagstring = articlePublishRequest.getTags();
//
//            String[] tags = tagstring.split(",");
//
//            Set<Tag> tagList = new HashSet<Tag>();
//
//            for (String tag :
//                    tags) {
//
//                Query q = tagDao.createQuery("from Tag t where t.name=?");
//
//                q.setString(0,tag);
//
//                Tag tagModel = (Tag) q.uniqueResult();
//
//                if (tagModel==null){
//
//                    tagModel=new Tag();
//                    tagModel.setCtime(new Date());
//                    tagModel.setCreater(article.getMember().getId());
//                    tagModel.setName(tag);
//                    tagModel.setStatus("100");
//
//                    tagDao.save(tagModel);
//
//                }
//
//                tagList.add(tagModel);
//
//            }
//
//            article.setTags(tagList);

            //分类
//            String catid = articlePublishRequest.getCategory();
//
//            if (StringUtils.isNotEmpty(catid)){
//
//                Category cat = categoryDao.get(Category.class,catid);
//
//                Set<Category> catlist = new HashSet<Category>();
//
//                catlist.add(cat);
//
//                article.setCategories(catlist);
//
//            }

            return RespCode.getRespData(RespCode.SUCCESS);

        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @Override
    public RespData categoryList() {

        List<Category> categoryList = categoryDao.createQuery("from Category ").list();

        List<CategoryResponse> categories = new ArrayList<CategoryResponse>();

        for (Category category :
                categoryList) {
            CategoryResponse cr = new CategoryResponse();

            cr.setId(category.getId());
            cr.setName(category.getName());

            categories.add(cr);
        }

        return RespCode.getRespData(RespCode.SUCCESS,categories);
    }

    @Override
    public RespData articleDel(HttpHeaders headers, ArticlePublishRequest articlePublishRequest) {



        return null;
    }
}
