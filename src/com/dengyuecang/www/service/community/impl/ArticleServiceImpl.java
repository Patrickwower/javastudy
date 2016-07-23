package com.dengyuecang.www.service.community.impl;

import com.dengyuecang.www.controller.api.community.model.*;
import com.dengyuecang.www.controller.community.model.LongTextRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.community.*;
import com.dengyuecang.www.service.community.IArticleService;
import com.dengyuecang.www.service.community.model.*;
import com.dengyuecang.www.service.members.model.CommunityMemberResponse;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.mapping.Index;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Resource(name="hibernateBaseDao")
    private BaseDao<ArticleEvaluate> articleEvaluateDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<ArticleComment> articleCommentDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<FocusMember> focusDao;



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

            articles = this.toIndexArticleList(null,articles,articleList);

            iResponse.setArticles(articles);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.SUCESS,iResponse);
    }

    private List<IndexArticle> toIndexArticleList(String memberId, List<IndexArticle> articles, List<Article> articleList){

        for (Article article :
                articleList) {

            IndexArticle iArticle = new IndexArticle();

            iArticle.setId(article.getId());

            iArticle.setBgimg(article.getCover());

            iArticle.setCategory("");

            if (article.getCategories().size()>0){
                iArticle.setCategory(article.getCategories().get(0).getName());
            }

            IndexAuthor iAuthor = new IndexAuthor();

            iAuthor.setHead(article.getMember().getMemberInfo().getIcon());

            iAuthor.setId(article.getMember().getId());

            iAuthor.setNickname(article.getMember().getMemberInfo().getNickname());

            iAuthor.setIfFocus("false");

            if (StringUtils.isNotEmpty(memberId)){

                String focusId = article.getMember().getId();

                String hql = "from FocusMember fm where fm.member.id=? and fm.focus.id=? ";

                Query q = focusDao.createQuery(hql);

                q.setString(0,memberId);

                q.setString(1,focusId);

                FocusMember fm = (FocusMember) q.uniqueResult();

                if (fm!=null){
                    iAuthor.setIfFocus("true");
                }
            }

            iArticle.setAuthor(iAuthor);

            List<String> tags = new ArrayList<String>();

            if (article.getTags().size()>0){

                for (Tag tag :
                        article.getTags()) {
                    tags.add(tag.getName());
                }

            }else{
                tags.add("其他");
            }
            iArticle.setTags(tags);

            iArticle.setTitle(article.getTitle()==null?"":article.getTitle());

            iArticle.setTimestamp(article.getTimestamp()+"");

            iArticle.setZanCount(zanCount(article.getId()));

            iArticle.setIfZan(ifZan(memberId,article.getId())+"");

            articles.add(iArticle);
        }

        return articles;
    }

    @Override
    public RespData queryArticles(HttpHeaders headers, ArticleRequest articleRequest) {

        int limit = 10;

        if (StringUtils.isNotEmpty(articleRequest.getPageSize())){
            limit = Integer.valueOf(articleRequest.getPageSize());
        }

        long timestamp = System.currentTimeMillis();

        if (StringUtils.isNotEmpty(articleRequest.getTimestamp())){
            timestamp = Long.valueOf(articleRequest.getTimestamp());
        }

        //查询文章列表
        Query q = articleDao.createQuery("from Article where status='100' and timestamp<"+timestamp+" order by timestamp desc ");

        q.setFirstResult(0);

        q.setMaxResults(limit);

        List<Article> articleList = q.list();

        List<IndexArticle> articles = new ArrayList<IndexArticle>();

        String memberId = headers.getFirst("memberId");

        articles = this.toIndexArticleList(memberId,articles,articleList);

        return RespCode.getRespData(RespCode.SUCESS,articles);
    }

    @Override
    public RespData getArticle(String articleId) {

        Article article = articleDao.get(Article.class,articleId);

        IndexArticle iArticle = new IndexArticle();






        return null;
    }

    @Override
    public RespData comments(ArticleCommentRequest request) {

        int limit = 10;

        if (StringUtils.isNotEmpty(request.getPageSize())){
            limit = Integer.valueOf(request.getPageSize());
        }

        long timestamp = System.currentTimeMillis();

        if (StringUtils.isNotEmpty(request.getTimestamp())){
            timestamp = Long.valueOf(request.getTimestamp());
        }

        String hql = "from ArticleComment ac where ac.article.id=? and timestamp<"+timestamp+" order by timestamp desc ";

        Query q = articleDao.createQuery(hql);

        q.setFirstResult(0);

        q.setMaxResults(limit);

        q.setString(0,request.getArticleId());

        List<ArticleComment> articleComments = q.list();

        List<ArticleCommentResponse> articleCommentResponses = new ArrayList<ArticleCommentResponse>();

        for (ArticleComment articleComment: articleComments
                ) {

            ArticleCommentResponse articleCommentResponse = new ArticleCommentResponse();

            articleCommentResponse.setComment(articleComment.getComment());

            articleCommentResponse.setId(articleComment.getId());

            articleCommentResponse.setCtime(articleComment.getCtime());

            articleCommentResponse.setTimestamp(articleComment.getTimestamp()+"");

            Member discussant = articleComment.getDiscussant();

            ArticleDiscussant articleDiscussant = new ArticleDiscussant();

            articleDiscussant.setId(discussant.getId());

            articleDiscussant.setHead(discussant.getMemberInfo().getIcon());

            articleDiscussant.setNickname(discussant.getMemberInfo().getNickname());

            articleCommentResponse.setDiscussant(articleDiscussant);

            articleCommentResponses.add(articleCommentResponse);
        }

        return RespCode.getRespData(RespCode.SUCESS,articleCommentResponses);

    }

    @Override
    public RespData comment(HttpHeaders headers, CommentRequest request) {

        ArticleComment comment = new ArticleComment();

        comment.setComment(request.getComment());

        comment.setTimestamp(System.currentTimeMillis());

        comment.setCtime(new Date());

        Article article = new Article();

        try{

            article = articleDao.get(Article.class,request.getArticleId());

        }catch (Exception e){
            e.printStackTrace();
        }

        comment.setArticle(article);

        String memberId = headers.getFirst("memberId");

        Member discussant = memberDao.get(Member.class,memberId);

        comment.setDiscussant(discussant);

        articleCommentDao.save(comment);

        Map<String,String> response = new HashMap<String,String>();

        return RespCode.getRespData(RespCode.SUCESS, response);

    }

    @Override
    public RespData evalute(HttpHeaders headers, EvaluteRequest evaluteRequest) {

        String memberId = headers.getFirst("memberId");

        String articleId = evaluteRequest.getArticleId();

        String hql = "from ArticleEvaluate ae where ae.discussant.id=? and ae.article.id=? ";

        Query q = articleEvaluateDao.createQuery(hql);

        q.setString(0,memberId);

        q.setString(1,articleId);

        ArticleEvaluate ae = (ArticleEvaluate) q.uniqueResult();

        if (ae!=null){

            if (ae.getEvaluation().equals(evaluteRequest.getEvaluation())){

                articleEvaluateDao.delete(ae);

            }else {
                ae.setEvaluation(evaluteRequest.getEvaluation());

                ae.setCtime(new Date());

                ae.setTimestamp(System.currentTimeMillis());

                articleEvaluateDao.saveOrUpdate(ae);
            }

        }else{

            Member discussant = memberDao.get(Member.class,memberId);

            ArticleEvaluate articleEvaluate = new ArticleEvaluate();

            articleEvaluate.setDiscussant(discussant);

            articleEvaluate.setEvaluation(evaluteRequest.getEvaluation());

            articleEvaluate.setCtime(new Date());

            articleEvaluate.setTimestamp(System.currentTimeMillis());

            Article article = new Article();

            try{

                article = articleDao.get(Article.class,evaluteRequest.getArticleId());

            }catch (Exception e){
                e.printStackTrace();
            }

            articleEvaluate.setArticle(article);

            articleEvaluateDao.save(articleEvaluate);
        }


        Map<String,String> response = new HashMap<String,String>();

        return RespCode.getRespData(RespCode.SUCESS, response);


    }

    @Override
    public RespData articleData(String articleId) {

        //点赞数

        String zanCount = this.zanCount(articleId);

        //评论数

        String commentCount = this.commentCount(articleId);

        //浏览次数


        Map<String,String> response = new HashMap<String,String>();

        response.put("zanCount",zanCount);
        response.put("commentCount",commentCount);

        return RespCode.getRespData(RespCode.SUCESS,response);
    }

    @Override
    public RespData focusAuthor(HttpHeaders headers, FocusAuthorRequest request) {

        String memberId = headers.getFirst("memberId");

        String focusId = request.getAuthorId();

        String hql = "from FocusMember fm where fm.member.id=? and fm.focus.id=? ";

        Query q = focusDao.createQuery(hql);

        q.setString(0,memberId);

        q.setString(1,focusId);

        FocusMember fm = (FocusMember) q.uniqueResult();

        if (fm!=null){

            focusDao.delete(fm);

        }else{

            Member member = memberDao.get(Member.class,memberId);

            Member focusMember = memberDao.get(Member.class,focusId);

            fm = new FocusMember();

            fm.setMember(member);

            fm.setFocus(focusMember);

            fm.setCtime(new Date());

            fm.setTimestamp(System.currentTimeMillis());

            focusDao.save(fm);
        }


        Map<String,String> response = new HashMap<String,String>();

        return RespCode.getRespData(RespCode.SUCESS,response);

    }

    @Override
    public RespData recommends(HttpHeaders headers) {

        List<IndexArticle> articles = new ArrayList<IndexArticle>();

        List<Article> articleList = new ArrayList<Article>();

        //查找推荐文章区域列表
        List<ArticleRecommend> recommendList = recommendDao.createQuery("from ArticleRecommend where status='100' order by ctime desc").list();

        for (ArticleRecommend recommend :
                recommendList) {

            articleList.add(recommend.getArticle());
        }

        String memberId = headers.getFirst("memberId");

        articles = toIndexArticleList(memberId,articles,articleList);


        Map<String,Object> response = new HashMap<String,Object>();

        response.put("recommend",articles);

        return RespCode.getRespData(RespCode.SUCESS,response);

    }

    @Override
    public RespData listByAuthor(HttpHeaders headers, ArticleRequest articleRequest) {

        String memberId = "";

        if (StringUtils.isNotEmpty(articleRequest.getMemberId())){

            memberId = articleRequest.getMemberId();

        }else if (StringUtils.isNotEmpty(headers.getFirst("memberId"))){

            memberId = headers.getFirst("memberId");

        }else{

            Map<String,String> response = new HashMap<String,String>();

            return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,response);

        }

        int limit = 10;

        if (articleRequest.getPageSize()!=null){
            limit = Integer.valueOf(articleRequest.getPageSize());
        }

        long timestamp = System.currentTimeMillis();

        if (articleRequest.getTimestamp()!=null){
            timestamp = Long.valueOf(articleRequest.getTimestamp());
        }

        //查询文章列表
        Query q = articleDao.createQuery("from Article a where a.status='100' and a.timestamp<"+timestamp+" and a.member.id=? order by a.timestamp desc ");

        q.setString(0,memberId);

        q.setFirstResult(0);

        q.setMaxResults(limit);

        List<Article> articleList = q.list();

        List<IndexArticle> articles = new ArrayList<IndexArticle>();

        articles = this.toIndexArticleList(memberId,articles,articleList);

        return RespCode.getRespData(RespCode.SUCESS,articles);

    }

    @Override
    public RespData focusList(HttpHeaders headers, FocusAuthorRequest focusRequest) {

        Map<String,Object> response = new HashMap<String,Object>();

        String memberId = headers.getFirst("memberId");

        if (StringUtils.isEmpty(memberId)){

            return RespCode.getRespData(RespCode.HEADER_MEMBERID_NEEDED,response);
        }

        int limit = 10;

        if (StringUtils.isNotEmpty(focusRequest.getPageSize())){
            limit = Integer.valueOf(focusRequest.getPageSize());
        }

        long timestamp = System.currentTimeMillis();

        if (StringUtils.isNotEmpty(focusRequest.getTimestamp())){
            timestamp = Long.valueOf(focusRequest.getTimestamp());
        }

        String hql = "from FocusMember fm where fm.member.id=? and fm.timestamp<"+timestamp+" order by fm.timestamp desc";

        Query q = focusDao.createQuery(hql);

        q.setString(0,memberId);

        q.setFirstResult(0);

        q.setMaxResults(limit);

        List<FocusMember> fms = q.list();

        List<CommunityMemberResponse> cmrs = new ArrayList<CommunityMemberResponse>();

        for (FocusMember fm : fms) {
            CommunityMemberResponse cMemberResponse = new CommunityMemberResponse();

            cMemberResponse.setIntroduction(fm.getFocus().getMemberInfo().getIntroduction());

            cMemberResponse.setCtime(fm.getCtime());

            cMemberResponse.setHead(fm.getFocus().getMemberInfo().getIcon());

            cMemberResponse.setId(fm.getFocus().getId());

            cMemberResponse.setNickname(fm.getFocus().getMemberInfo().getNickname());

            cMemberResponse.setTimestamp(fm.getTimestamp()+"");

            cmrs.add(cMemberResponse);
        }

        response.put("focusMembers",cmrs);

        return RespCode.getRespData(RespCode.SUCESS,response);
    }

    private String zanCount(String articleId){

        String hqlZanCount = "select count(ae.id) from ArticleEvaluate ae where ae.article.id=? and ae.evaluation='0' ";

        Query q = articleEvaluateDao.createQuery(hqlZanCount);

        q.setString(0,articleId);

        long zanCount = (long)q.uniqueResult();

        return zanCount+"";
    }

    private String commentCount(String articleId){

        String hqlCommentCount = "select count(ac.id) from ArticleComment ac where ac.article.id=? ";

        Query q = articleCommentDao.createQuery(hqlCommentCount);

        q.setString(0,articleId);

        long commentCount = (long)q.uniqueResult();

        return commentCount+"";
    }

    private String viewCount(){

        return "";
    }

    public boolean ifZan(String memberId,String articleId){

        if (memberId==null)return false;

        //话题点赞数
        String hqlZanCount = "from ArticleEvaluate ae where ae.article.id=? and ae.discussant.id=? ";

        Query q = articleEvaluateDao.createQuery(hqlZanCount);

        q.setString(0,articleId);

        q.setString(1,memberId);

        List<ArticleEvaluate> l = q.list();

        if (l.size()>0){
            return true;
        }

        return false;
    }

}
