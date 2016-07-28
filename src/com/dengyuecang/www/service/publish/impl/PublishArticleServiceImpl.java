package com.dengyuecang.www.service.publish.impl;

import com.dengyuecang.www.controller.api.publish.model.ArticlePublishRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.community.Article;
import com.dengyuecang.www.entity.community.Category;
import com.dengyuecang.www.entity.community.Tag;
import com.dengyuecang.www.service.common.CommonConstant;
import com.dengyuecang.www.service.publish.IPublishArticleService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
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

    @Override
    public RespData articleAdd(HttpHeaders headers,ArticlePublishRequest articlePublishRequest) {

        Article article = new Article();

        article.setStatus("100");
        article.setTimestamp(System.currentTimeMillis());
        article.setContent(StringUtils.isEmpty(articlePublishRequest.getContent())?"":articlePublishRequest.getContent());

        article.setCover(StringUtils.isEmpty(articlePublishRequest.getCover())?"":articlePublishRequest.getCover());
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

        response.put("memberId",article.getId());

        return RespCode.getRespData(RespCode.SUCESS,response);
    }

    @Override
    public BaseDao<Article> getSuperDao() {
        return null;
    }

    @Override
    public RespData articleUpdate(HttpHeaders headers, ArticlePublishRequest articlePublishRequest) {



        return null;
    }
}
