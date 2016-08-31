package com.dengyuecang.www.service.publish.impl;

import com.dengyuecang.www.controller.api.publish.model.ArticlePublishRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.community.Article;
import com.dengyuecang.www.entity.community.ArticleUpdateLog;
import com.dengyuecang.www.entity.community.Category;
import com.dengyuecang.www.entity.community.Tag;
import com.dengyuecang.www.service.common.CommonConstant;
import com.dengyuecang.www.service.publish.IPublishArticleService;
import com.dengyuecang.www.service.publish.model.CategoryResponse;
import com.dengyuecang.www.utils.JsonUtils;
import com.dengyuecang.www.utils.RegexUtils;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import net.sf.json.util.JSONUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Time;
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

    @Override
    public RespData articleAdd(HttpHeaders headers,ArticlePublishRequest articlePublishRequest) {

        Article article = new Article();

        article.setStatus("100");
        article.setTimestamp(System.currentTimeMillis());
        article.setContent(StringUtils.isEmpty(articlePublishRequest.getContent())?"":articlePublishRequest.getContent());

        String regEx_html = "<[^>]+>";

        String summary = article.getContent().replace(regEx_html,"");

        article.setSummary(summary);

        if (StringUtils.isNotEmpty(articlePublishRequest.getWordCount())){
            article.setWordCount(Integer.valueOf(articlePublishRequest.getWordCount()));
        }else{
            article.setWordCount(0);
        }

        article.setCover(StringUtils.isEmpty(articlePublishRequest.getCover())?"":articlePublishRequest.getCover());

        //如果没有设置封面,则从文本中取出第一张图片作为封面图
        if (StringUtils.isEmpty(article.getCover())){

            List<String> imgList = RegexUtils.getImgsFromHtml(article.getContent());

            if (imgList.size()>0){
                article.setCover(imgList.get(0));
            }

        }

        article.setCtime(new Date());
        article.setTitle(StringUtils.isEmpty(articlePublishRequest.getTitle())?"":articlePublishRequest.getTitle());

        String memberId = headers.getFirst("memberId");

        if (StringUtils.isEmpty(memberId)){

            memberId = articlePublishRequest.getMemberId();

        }

        if (StringUtils.isEmpty(memberId)){

            return RespCode.getRespData(RespCode.UNLOGIN);

        }

        Member member = memberDao.get(Member.class,memberId);

        article.setMember(member);

//        article.set

        String catid = articlePublishRequest.getCategory();

        if (StringUtils.isNotEmpty(catid)){

            Category cat = categoryDao.get(Category.class,catid);

            List<Category> catlist = new ArrayList<Category>();

            catlist.add(cat);

            article.setCategories(catlist);

        }

        if (StringUtils.isNotEmpty(articlePublishRequest.getTags())){

            String tagstring = articlePublishRequest.getTags();

            String[] tags = tagstring.split(",");

            List<Tag> tagList = new ArrayList<Tag>();

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

        articleDao.save(article);

        Map<String,String> response = new HashMap<String,String>();

        response.put("articleId",article.getId());

        return RespCode.getRespData(RespCode.SUCESS,response);
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

            String[] tagArray = new String[(article.getTags().size())];

            for (int i = 0; i <article.getTags().size(); i++) {
                tagArray[i] = article.getTags().get(i).getName();
            }

            articleUpdateLog.setTags(StringUtils.join(tagArray,","));

            articleUpdateLog.setCid(article.getCategories().get(0).getId());

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
            article.setCover(articlePublishRequest.getCover());

            //内容
            article.setContent(articlePublishRequest.getContent());

            if (StringUtils.isNotEmpty(articlePublishRequest.getWordCount())){
                article.setWordCount(Integer.valueOf(articlePublishRequest.getWordCount()));
            }else{
                article.setWordCount(0);
            }

            article.setUtime(new Date());

//            article.setCategories(null);article.setTags(null);

            articleDao.saveOrUpdate(article);

            //tags标签
            String tagstring = articlePublishRequest.getTags();

            String[] tags = tagstring.split(",");

            List<Tag> tagList = new ArrayList<Tag>();

            for (String tag :
                    tags) {

                Query q = tagDao.createQuery("from Tag t where t.name=?");

                q.setString(0,tag);

                Tag tagModel = (Tag) q.uniqueResult();

                if (tagModel==null){

                    tagModel=new Tag();
                    tagModel.setCtime(new Date());
                    tagModel.setCreater(article.getMember().getId());
                    tagModel.setName(tag);
                    tagModel.setStatus("100");

                    tagDao.save(tagModel);

                }

                tagList.add(tagModel);

            }

            article.setTags(tagList);

            //分类
            String catid = articlePublishRequest.getCategory();

            if (StringUtils.isNotEmpty(catid)){

                Category cat = categoryDao.get(Category.class,catid);

                List<Category> catlist = new ArrayList<Category>();

                catlist.add(cat);

                article.setCategories(catlist);

            }

            return RespCode.getRespData(RespCode.SUCESS);

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

        return RespCode.getRespData(RespCode.SUCESS,categories);
    }

    @Override
    public RespData articleDel(HttpHeaders headers, ArticlePublishRequest articlePublishRequest) {



        return null;
    }
}
