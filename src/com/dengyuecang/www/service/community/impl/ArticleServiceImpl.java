package com.dengyuecang.www.service.community.impl;

import com.dengyuecang.www.controller.api.community.model.ArticleRequest;
import com.dengyuecang.www.controller.community.model.LongTextRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.community.Article;
import com.dengyuecang.www.entity.community.ArticleRecommend;
import com.dengyuecang.www.entity.community.TopicEveryday;
import com.dengyuecang.www.service.community.IArticleService;
import com.dengyuecang.www.service.community.model.IndexArticle;
import com.dengyuecang.www.service.community.model.IndexRecommend;
import com.dengyuecang.www.service.community.model.IndexResponse;
import com.dengyuecang.www.service.community.model.IndexTopic;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by acang on 16/6/22.
 */
@Service
public class ArticleServiceImpl extends BaseService<Article> implements IArticleService{

    @Resource(name="hibernateBaseDao")
    private BaseDao<Article> articleDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<Member> memberDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<ArticleRecommend> recommendDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<TopicEveryday> topicEverydayDao;

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

        article.setTimestamp(System.currentTimeMillis());

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



    @Override
    public RespData index() {

        IndexResponse iResponse = new IndexResponse();

        try {
            //-------------------------------------------------------------------
            List<IndexRecommend> recommends = new ArrayList<IndexRecommend>();

            //查找推荐文章区域列表
            List<ArticleRecommend> recommendList = recommendDao.createQuery("from ArticleRecommend where status='100' order by ctime desc").list();

            for (ArticleRecommend recommend :
                    recommendList) {

                IndexRecommend iRecommend = new IndexRecommend();

                iRecommend.setId(recommend.getId());
                iRecommend.setBgimg(recommend.getArticle().getCover());

                recommends.add(iRecommend);
            }

            iResponse.setRecommends(recommends);
//-------------------------------------------------------------------
            IndexTopic iTopic = new IndexTopic();

            Date today = new Date();

            Format f1 = new SimpleDateFormat("d");

            String day = f1.format(today);

            Format f2 = new SimpleDateFormat("MMM", Locale.ENGLISH);

            String month = f2.format(today);

            iTopic.setDay(day);

            iTopic.setMonth(month);

            Format f3 = new SimpleDateFormat("yyyy-MM-dd");

            //查询每日话题
            TopicEveryday topicEveryday = (TopicEveryday)topicEverydayDao.createQuery("from TopicEveryday where date_format(issueDate,'%Y-%m-%d')='"+f3.format(today)+"' ").uniqueResult();

            if (topicEveryday!=null){
                iTopic.setId(topicEveryday.getTopic().getId());

                iTopic.setName(topicEveryday.getTopic().getTitle());
            }

            iResponse.setTopic(iTopic);
//-------------------------------------------------------------------

            List<IndexArticle> articles = new ArrayList<IndexArticle>();

            //查询文章列表

            Query q = articleDao.createQuery("from Article where status='100' order by timestamp desc ");

            q.setFirstResult(0);

            q.setMaxResults(10);

            List<Article> articleList = q.list();

            for (Article article :
                    articleList) {

                IndexArticle iArticle = new IndexArticle();

                iArticle.setId(article.getId());

                iArticle.setBgimg(article.getCover());

                iArticle.setAuthor(article.getMember().getMemberInfo().getNickname());

                if (article.getTags().size()>0){
                    iArticle.setBigtag(article.getTags().get(0).getName());
                }else{
                    iArticle.setBigtag("其他");
                }

                iArticle.setTitle(article.getTitle()==null?"":article.getTitle());

                iArticle.setTimestamp(article.getTimestamp()+"");

                articles.add(iArticle);
            }

            iResponse.setArticles(articles);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.SUCESS,iResponse);
    }

    @Override
    public RespData queryArticles(ArticleRequest articleRequest) {

        int limit = 10;

        if (articleRequest.getPageSize()!=null){
            limit = Integer.valueOf(articleRequest.getPageSize());
        }

        long timestamp = 0l;

        if (articleRequest.getTimestamp()!=null){
            timestamp = Long.valueOf(articleRequest.getTimestamp());
        }

        //查询文章列表
        Query q = articleDao.createQuery("from Article where status='100' and timestamp<"+timestamp+"order by timestamp desc ");

        q.setFirstResult(0);

        q.setMaxResults(limit);

        List<Article> articleList = q.list();

        List<IndexArticle> articles = new ArrayList<IndexArticle>();

        for (Article article :
                articleList) {

            IndexArticle iArticle = new IndexArticle();

            iArticle.setId(article.getId());

            iArticle.setBgimg(article.getCover());

            iArticle.setAuthor(article.getMember().getMemberInfo().getNickname());

            if (article.getTags().size()>0){
                iArticle.setBigtag(article.getTags().get(0).getName());
            }else{
                iArticle.setBigtag("其他");
            }

            iArticle.setTitle(article.getTitle()==null?"":article.getTitle());

            iArticle.setTimestamp(article.getTimestamp()+"");

            articles.add(iArticle);
        }



        return RespCode.getRespData(RespCode.SUCESS,articles);
    }


}
