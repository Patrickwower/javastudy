package com.dengyuecang.api.service.community.impl;

import com.dengyuecang.api.controller.community.model.LongTextRequest;
import com.dengyuecang.api.entity.Member;
import com.dengyuecang.api.entity.community.Article;
import com.dengyuecang.api.service.community.IArticleService;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by acang on 16/6/22.
 */
@Service
public class ArticleServiceImpl extends BaseService<Article> implements IArticleService{

    @Resource(name="hibernateBaseDao")
    private BaseDao<Article> articleDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<Member> memberDao;



    @Override
    public BaseDao<Article> getSuperDao() {
        return null;
    }

    @Override
    public void save(LongTextRequest longText) {

        Member member = memberDao.get(Member.class,"8a2a67a25505963401550a65958a000f");

        Article article = new Article();

        article.setContent(longText.getContent());

        article.setTitle(longText.getTitle());

        article.setCover("");

        article.setCtime(new Date());

        article.setMember(member);

        article.setUtime(new Date());

        articleDao.save(article);


    }

    @Override
    public List<Article> queryArticle(String memberId) {


        String hql = "select a from Article a where a.member.id=? ";

        Query q = articleDao.createQuery(hql);

        q.setString(0,memberId);

        List<Article> list = q.list();

        return list;
    }

    @Override
    public Article queryArticleById(String articleId) {

        return articleDao.get(Article.class,articleId);

    }
}
