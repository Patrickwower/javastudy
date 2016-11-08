package com.dengyuecang.www.service.community.impl;

import com.dengyuecang.www.controller.api.community.model.*;
import com.dengyuecang.www.controller.community.model.LongTextRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.community.*;
import com.dengyuecang.www.service.common.CommonConstant;
import com.dengyuecang.www.service.community.IArticleService;
import com.dengyuecang.www.service.community.model.*;
import com.dengyuecang.www.service.members.IMembersService;
import com.dengyuecang.www.service.members.model.CommunityMemberResponse;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.dao.params.PageModel;
import com.longinf.lxcommon.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
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

    @Resource(name="hibernateBaseDao")
    private BaseDao<LogArticleBrowse> logArticleBrowseDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<ArticleCollection> collectionDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<ArticleCommentEvaluate> commentEvaluateDao;

    @Resource
    private IMembersService membersServiceImpl;

    @Resource(name = "hibernateBaseDao")
    private BaseDao<ArticleIndex> articleIndexDao;

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

    @Override
    public IndexArticle toIndexArticle(String memberId,Article article){

        IndexArticle iArticle = new IndexArticle();

        iArticle.setId(article.getId());

        iArticle.setBgimg(article.getCover());

        iArticle.setCategory("");

        if (article.getCategories().size()>0){
            iArticle.setCategory(article.getCategories().iterator().next().getName());
        }

        IndexAuthor iAuthor = new IndexAuthor();

        iAuthor.setHead(article.getMember().getMemberInfo().getIcon());

        iAuthor.setId(article.getMember().getId());

        iAuthor.setNickname(article.getMember().getMemberInfo().getNickname());

        iAuthor.setIfFocus("0");

        if (StringUtils.isNotEmpty(memberId)){

            String focusId = article.getMember().getId();

            iAuthor.setIfFocus(membersServiceImpl.ifFocus(memberId,focusId)+"");

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

        iArticle.setCtime(article.getCtime());

        iArticle.setZanCount(zanCount(article.getId()));

        iArticle.setCommentCount(commentCount(article.getId()));

        iArticle.setBrowseCount(browseCount(article.getId()));

        iArticle.setCollectionCount(collectionCount(article.getId()));

        iArticle.setWordCount(article.getWordCount()+"");

        iArticle.setIfZan(ifZan(memberId,article.getId())+"");

        iArticle.setIfCollection(ifCollection(memberId,article.getId())+"");

        iArticle.setUrl(article.getUrl()+article.getId());

        iArticle.setShareUrl(article.getShareUrl()+article.getId());

        iArticle.setSummary(article.getSummary()==null?"":article.getSummary());

        iArticle.setSquareCrop(article.getSquareCover()==null?"":article.getSquareCover());

        iArticle.setContent(article.getContent());

        iArticle.setStatus(article.getStatus());

        iArticle.setStatusName(CommonConstant.ARTICLE_STATUS.get(article.getStatus()));

        iArticle.setIfIndex(article.getIf_index());

        iArticle.setIfBanner(article.getBanner());

        iArticle.setCanDelete(this.canDeleteArticle(article,memberId)+"");

        iArticle.setCanEdit(this.canEditArticle(article,memberId)+"");

        return iArticle;
    }

    private int canDeleteArticle(Article article,String memberId){

        if (article.getMember().getId().equals(memberId)){
            return 1;
        }

        return 0;
    }

    private int canEditArticle(Article article,String memberId){

        if (article.getMember().getId().equals(memberId)){
            return 1;
        }

        return 0;
    }

    @Override
    public List<IndexArticle> toIndexArticleList(String memberId, List<IndexArticle> articles, List<Article> articleList){

        for (Article article :
                articleList) {

            IndexArticle iArticle = toIndexArticle(memberId,article);

            articles.add(iArticle);
        }

        return articles;
    }

    @Override
    public RespData delete(HttpHeaders headers, String articleId) {

        if (StringUtils.isEmpty(articleId)){

            return RespCode.getRespData(RespCode.ARTICLE_NOT_EXIST,new HashMap<String,String>());

        }

        Article article = articleDao.get(Article.class,articleId);

        if (article==null){

            return RespCode.getRespData(RespCode.ARTICLE_NOT_EXIST,new HashMap<String,String>());

        }


        try {
            article.setStatus("300");

            articleDao.saveOrUpdate(article);

            Map<String,String> response = new HashMap<String,String>();

            response.put("articleId",articleId);

            return RespCode.getRespData(RespCode.SUCESS,response);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    @Override
    public RespData commentDelete(HttpHeaders headers, String commentId) {

        String memberId = headers.getFirst("memberId");

        if (StringUtils.isEmpty(memberId)){
            return RespCode.getRespData(RespCode.UNLOGIN,new HashMap<String,String>());
        }

        if (StringUtils.isEmpty(commentId)){

            return RespCode.getRespData(RespCode.COMMENT_ID_NEED,new HashMap<String,String>());

        }

        ArticleComment ac = articleCommentDao.get(ArticleComment.class,commentId);

        if (ac==null){
            return RespCode.getRespData(RespCode.COMMENT_NOT_EXIST,new HashMap<String,String>());
        }

        if (!ac.getArticle().getMember().getId().equals(memberId)){

            if (!ac.getDiscussant().getId().equals(memberId)){

                return RespCode.getRespData(RespCode.COMMENT_NOT_CURRENT_MEMBER,new HashMap<String,String>());
            }

        }

        try {

            ac.setStatus("200");

            articleCommentDao.saveOrUpdate(ac);

            Map<String,String> response = new HashMap<String,String>();

            response.put("commentId",commentId);

            return RespCode.getRespData(RespCode.SUCESS,response);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    /**
     * 评论是否上传
     *  //当前用户是否可以删除,0不可以,1可以
     *  文章作者和评论所有人可以删除
     */
    private int canDeleteComment(ArticleComment ac,String memberId){

        if (ac.getArticle().getMember().getId().equals(memberId)){

            return 1;

        }else{

            if (ac.getDiscussant().getId().equals(memberId)){

                return 1;
            }

        }

        return 0;
    }


    /**
     *
     * @param articleId
     * @param indexList
     * @return
     */
    private int ifInIndex(String articleId,List<ArticleIndex> indexList){

        for (ArticleIndex index : indexList){

            if (articleId.equals(index.getArticle().getId())){
                return 1;
            }
        }
        return 0;
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

        String status = "100";

        if (StringUtils.isNotEmpty(articleRequest.getStatus())){

            status = articleRequest.getStatus();
        }

        String hql = "from Article where status='"+status+"' and timestamp<"+timestamp+" order by timestamp desc ";

//          hql = "select b from ArticleIndex a,Article b where a.article.id=b.id and a.timestamp < "+timestamp+" order by a.sort,a.index_time desc";

        //查询文章列表
        Query q = articleDao.createQuery(hql);

        q.setFirstResult(0);

        q.setMaxResults(limit);

        List<Article> articleList = q.list();

        List<IndexArticle> articles = new ArrayList<IndexArticle>();

        String memberId = headers.getFirst("memberId");

        articles = this.toIndexArticleList(memberId,articles,articleList);

        return RespCode.getRespData(RespCode.SUCESS,articles);
    }

    @Override
    public RespData hotList(HttpHeaders headers, PageModel pageModel) {

        try {

//            pageModel.setRows(2);

            Criteria indexCriteria = articleIndexDao.createCriteria(ArticleIndex.class);

            indexCriteria.addOrder(Order.asc("sort"));

            indexCriteria.addOrder(Order.desc("index_time"));

            articleDao.pagedQuery(indexCriteria,pageModel);

            List<ArticleIndex> articleIndexList = pageModel.getList();

            if (pageModel.getRows()==2){

                if (articleIndexList.size()<2){

                    Criteria tmp_criteria  = articleIndexDao.createCriteria(ArticleIndex.class);

                    tmp_criteria.addOrder(Order.asc("sort"));

                    tmp_criteria.addOrder(Order.desc("index_time"));

                    pageModel = new PageModel();

                    pageModel.setRows(2);

                    articleDao.pagedQuery(tmp_criteria,pageModel);

                    articleIndexList = pageModel.getList();
                }

            }

            List<Article> articleList = new ArrayList<Article>();

            for (ArticleIndex articleIndex :
                    articleIndexList) {

                articleList.add(articleIndex.getArticle());

            }

            HashMap<String,Object> response = new HashMap<String,Object>();

            List<IndexArticle> articles = new ArrayList<IndexArticle>();

            String memberId = headers.getFirst("memberId");

            articles = this.toIndexArticleList(memberId,articles,articleList);

            response.put("articles",articles);

            pageModel.setList(null);

            response.put("page",pageModel.getPage());

            return RespCode.getRespData(RespCode.SUCESS,response);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    @Override
    public RespData drafts(HttpHeaders headers, ArticleRequest articleRequest) {


        //查找文章状态为50,即草稿的文章
        articleRequest.setStatus("50");

        return this.listByAuthor(headers,articleRequest);

    }

    @Override
    public RespData getArticle(String articleId) {

        Article article = articleDao.get(Article.class,articleId);

        IndexArticle iArticle = new IndexArticle();






        return null;
    }

    @Override
    public RespData comments(HttpHeaders headers,ArticleCommentRequest request) {

        int limit = 10;

        if (StringUtils.isNotEmpty(request.getPageSize())){
            limit = Integer.valueOf(request.getPageSize());
        }

        long timestamp = 0l;

        if (StringUtils.isNotEmpty(request.getTimestamp())){
            timestamp = Long.valueOf(request.getTimestamp());
        }

        String memberId = headers.getFirst("memberId");

        String listHql = "";

        if (StringUtils.isNotEmpty(request.getCommentId())){

            listHql += " and articleComment.id=? ";

        }

        String hql = "from ArticleComment ac where ac.status='100' and ac.article.id=? and timestamp > "+timestamp+" "+listHql+" order by timestamp ";

        Query q = articleDao.createQuery(hql);

        q.setFirstResult(0);

        q.setMaxResults(limit);

        q.setString(0,request.getArticleId());

        if (StringUtils.isNotEmpty(request.getCommentId())){

            ArticleComment articleComment = articleCommentDao.get(ArticleComment.class,request.getCommentId());

            if (articleComment!=null){q.setString(1,articleComment.getArticleComment().getId());}else {
                q.setString(1,"0");
            }

        }

        List<ArticleComment> articleComments = q.list();

        List<ArticleCommentResponse> articleCommentResponses = new ArrayList<ArticleCommentResponse>();

        for (ArticleComment articleComment: articleComments
                ) {

            ArticleCommentResponse articleCommentResponse = new ArticleCommentResponse();

            articleCommentResponse.setComment(articleComment.getComment());

            articleCommentResponse.setId(articleComment.getId());

            articleCommentResponse.setCtime(articleComment.getCtime());

            articleCommentResponse.setTimestamp(articleComment.getTimestamp()+"");

            if (articleComment.getArticleComment()!=null){
                articleCommentResponse.setIfHaveList("1");
            }

            if (StringUtils.isNotEmpty(articleComment.getAtMember())){

                String[] memberIds = articleComment.getAtMember().split(",");

                for (String atMemberId :
                        memberIds) {
                    Member member = memberDao.get(Member.class,atMemberId);

                    ArticleCommentAtMember atMember = new ArticleCommentAtMember();

                    if (member!=null){
                        atMember.setMemberId(atMemberId);
                        atMember.setHead(member.getMemberInfo().getIcon()==null?"":member.getMemberInfo().getIcon());
                        atMember.setNickname(member.getMemberInfo().getNickname());
                        atMember.setIfAuthor(ifAuthor(articleComment.getArticle().getMember().getId(),member.getId()));
                        articleCommentResponse.getAtMembers().add(atMember);
                    }

                }
            }

            Member discussant = articleComment.getDiscussant();

            ArticleDiscussant articleDiscussant = new ArticleDiscussant();

            articleDiscussant.setId(discussant.getId());

            articleDiscussant.setHead(discussant.getMemberInfo().getIcon()==null?"":discussant.getMemberInfo().getIcon());

            articleDiscussant.setNickname(discussant.getMemberInfo().getNickname());

            //该评论人是否是作者 0否,1是
            articleDiscussant.setIfAuthor(ifAuthor(articleComment.getArticle().getMember().getId(),articleDiscussant.getId()));

            articleCommentResponse.setDiscussant(articleDiscussant);

            articleCommentResponse.setZanCount(zanCountForComment(articleComment.getId()));

            articleCommentResponse.setIfZan(ifZanForComment(memberId,articleComment.getId()));

            //当前用户是否可以删除,0不可以,1可以
            articleCommentResponse.setCanDelete(this.canDeleteComment(articleComment,memberId)+"");

            articleCommentResponses.add(articleCommentResponse);
        }

        return RespCode.getRespData(RespCode.SUCESS,articleCommentResponses);

    }

    private String ifAuthor(String authorId,String memberId){

        if (authorId.equals(memberId))return "1";

        return "0";
    }

    @Override
    public RespData comment(HttpHeaders headers, CommentRequest request) {

        ArticleComment comment = new ArticleComment();

        comment.setComment(request.getComment());

        comment.setTimestamp(System.currentTimeMillis());

        comment.setCtime(new Date());


        //文章
        Article article = new Article();

        try{

            article = articleDao.get(Article.class,request.getArticleId());

        }catch (Exception e){
            e.printStackTrace();
        }

        comment.setArticle(article);

        //评论人
        String memberId = headers.getFirst("memberId");

        Member discussant = memberDao.get(Member.class,memberId);

        comment.setDiscussant(discussant);

        if (StringUtils.isNotEmpty(request.getCommentId())){
            //上级评论
            ArticleComment articleComment = articleCommentDao.get(ArticleComment.class,request.getCommentId());

            if (articleComment!=null){
                comment.setFatherComment(articleComment);
                if (articleComment.getArticleComment()==null){
                    comment.setArticleComment(articleComment);
                    articleComment.setArticleComment(articleComment);
                    articleCommentDao.saveOrUpdate(articleComment);
                }else{
                    comment.setArticleComment(articleComment.getArticleComment());
                }
            }
        }

        comment.setAtMember(request.getAtMember());

        comment.setStatus("100");

        articleCommentDao.save(comment);

        Map<String,String> response = new HashMap<String,String>();

        response.put("commentCount",commentCount(request.getArticleId()));

        return RespCode.getRespData(RespCode.SUCESS, response);

    }

    @Override
    public RespData evaluate(HttpHeaders headers, EvaluteRequest evaluteRequest) {

        String memberId = headers.getFirst("memberId");

        String articleId = evaluteRequest.getArticleId();

        String hql = "from ArticleEvaluate ae where ae.discussant.id=? and ae.article.id=? ";

        Query q = articleEvaluateDao.createQuery(hql);

        q.setString(0,memberId);

        q.setString(1,articleId);

        ArticleEvaluate ae = (ArticleEvaluate) q.uniqueResult();

        int ifZan = 0;

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

            ifZan = 1;
        }


        Map<String,String> response = new HashMap<String,String>();

        response.put("ifZan",ifZan+"");

        response.put("zanCount",zanCount(articleId));

        return RespCode.getRespData(RespCode.SUCESS, response);


    }

    @Override
    public RespData comment_evaluate(HttpHeaders headers, EvaluteRequest evaluteRequest) {

        String memberId = headers.getFirst("memberId");

        String articleId = evaluteRequest.getArticleId();

        String commentId = evaluteRequest.getCommentId();

        String hql = "from ArticleCommentEvaluate ace where ace.discussant.id=? and ace.article.id=? and ace.comment.id=? ";

        Query q = commentEvaluateDao.createQuery(hql);

        q.setString(0,memberId);

        q.setString(1,articleId);

        q.setString(2,commentId);

        ArticleCommentEvaluate ace = (ArticleCommentEvaluate) q.uniqueResult();

        int ifZan = 0;

        if (ace!=null){

            if (ace.getEvaluation().equals(evaluteRequest.getEvaluation())){

                commentEvaluateDao.delete(ace);

            }else {
                ace.setEvaluation(evaluteRequest.getEvaluation());

                ace.setCtime(new Date());

                ace.setTimestamp(System.currentTimeMillis());

                commentEvaluateDao.saveOrUpdate(ace);
            }

        }else{

            Member discussant = memberDao.get(Member.class,memberId);

            ace = new ArticleCommentEvaluate();

            ace.setDiscussant(discussant);

            ace.setEvaluation(evaluteRequest.getEvaluation());

            ace.setCtime(new Date());

            ace.setTimestamp(System.currentTimeMillis());

            Article article = new Article();

            try{

                article = articleDao.get(Article.class,evaluteRequest.getArticleId());

            }catch (Exception e){
                e.printStackTrace();
            }

            ace.setArticle(article);

            ArticleComment ac = new ArticleComment();

            try{

                ac = articleCommentDao.get(ArticleComment.class,evaluteRequest.getCommentId());

            }catch (Exception e){
                e.printStackTrace();
            }

            ace.setComment(ac);


            commentEvaluateDao.save(ace);

            ifZan = 1;
        }


        Map<String,String> response = new HashMap<String,String>();

        response.put("ifZan",ifZan+"");

        response.put("zanCounrt",zanCountForComment(commentId));

        return RespCode.getRespData(RespCode.SUCESS, response);

    }

    @Override
    public RespData collection(HttpHeaders headers, CollectionRequest collectionRequest) {

        String memberId = headers.getFirst("memberId");

        String articleId = collectionRequest.getArticleId();

        String hql = "from ArticleCollection ac where ac.discussant.id=? and ac.article.id=? ";

        Query q = articleEvaluateDao.createQuery(hql);

        q.setString(0,memberId);

        q.setString(1,articleId);

        ArticleCollection ac = (ArticleCollection) q.uniqueResult();

        int ifCollection = 0;

        if (ac!=null){


                collectionDao.delete(ac);

        }else{

            Member discussant = memberDao.get(Member.class,memberId);

            ac = new ArticleCollection();

            ac.setDiscussant(discussant);

            ac.setCtime(new Date());

            ac.setTimestamp(System.currentTimeMillis());

            Article article = new Article();

            try{

                article = articleDao.get(Article.class,collectionRequest.getArticleId());

            }catch (Exception e){
                e.printStackTrace();
            }

            ac.setArticle(article);

            collectionDao.save(ac);

            ifCollection = 1;
        }


        Map<String,String> response = new HashMap<String,String>();

        response.put("ifCollection",ifCollection+"");

        response.put("collectionCount",collectionCount(articleId));

        return RespCode.getRespData(RespCode.SUCESS, response);

    }

    @Override
    public RespData articleData(HttpHeaders headers,String articleId) {

        //浏览次数
        Map<String,Object> response = new HashMap<String,Object>();

        if (StringUtils.isNotEmpty(articleId)){
            //数据
            Article article = articleDao.get(Article.class,articleId);

            if (article!=null){

                //点赞数
                String zanCount = this.zanCount(articleId);

                //评论数
                String commentCount = this.commentCount(articleId);

                IndexArticle iArticle = toIndexArticle(headers.getFirst("memberId"),article);

                response.put("zanCount",zanCount);
                response.put("commentCount",commentCount);

                response.put("article",iArticle);

            }

        }

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

        if (StringUtils.isNotEmpty(articleRequest.getPageSize())){
            limit = Integer.valueOf(articleRequest.getPageSize());
        }

        long timestamp = System.currentTimeMillis();

        if (StringUtils.isNotEmpty(articleRequest.getTimestamp())){
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

    @Override
    public Integer addBrowse(HttpHeaders headers, String articleId) {

        LogArticleBrowse logArticleBrowse = new LogArticleBrowse();

        String memberId = headers.getFirst("memberId");

        if (StringUtils.isNotEmpty(memberId)){

            Member member = memberDao.get(Member.class,memberId);

            if (member!=null){
                logArticleBrowse.setMember(member);
            }
        }

        Article article = articleDao.get(Article.class,articleId);

        logArticleBrowse.setArticle(article);

        logArticleBrowse.setCtime(new Date());

        logArticleBrowseDao.save(logArticleBrowse);

        String browseCount = this.browseCount(articleId);

        return Integer.valueOf(browseCount);
    }

    @Override
    public RespData browse(HttpHeaders headers, String articleId) {

        Integer browseCount = this.addBrowse(headers,articleId);

        Map<String,String> response = new HashMap<String,String>();

        response.put("browseCount",browseCount+"");

        return RespCode.getRespData(RespCode.SUCESS,response);
    }

    @Override
    public RespData collections(HttpHeaders headers, CollectionRequest collectionRequest) {

        int limit = 10;

        if (StringUtils.isNotEmpty(collectionRequest.getPageSize())){
            limit = Integer.valueOf(collectionRequest.getPageSize());
        }

        long timestamp = System.currentTimeMillis();

        if (StringUtils.isNotEmpty(collectionRequest.getTimestamp())){
            timestamp = Long.valueOf(collectionRequest.getTimestamp());
        }

        String memberId = collectionRequest.getMemberId();

        if (StringUtils.isEmpty(memberId)){

            memberId = headers.getFirst("memberId");

        }

        //查询文章列表
        Query q = articleDao.createQuery("select a from Article a,ArticleCollection b where b.discussant.id=? and a.id=b.article.id and a.status='100' and a.timestamp<"+timestamp+" order by a.timestamp desc ");

        q.setString(0,memberId);

        q.setFirstResult(0);

        q.setMaxResults(limit);

        List<Article> articleList = q.list();

        List<IndexArticle> articles = new ArrayList<IndexArticle>();

        articles = this.toIndexArticleList(memberId,articles,articleList);

        return RespCode.getRespData(RespCode.SUCESS,articles);


    }

    private String zanCount(String articleId){

        String hqlZanCount = "select count(ae.id) from ArticleEvaluate ae where ae.article.id=? and ae.evaluation='0' ";

        Query q = articleEvaluateDao.createQuery(hqlZanCount);

        q.setString(0,articleId);

        long zanCount = (long)q.uniqueResult();

        return zanCount+"";
    }

    private String zanCountForComment(String commentId){

        String hqlZanCountForComment = "select count(ace.id) from ArticleCommentEvaluate ace where ace.comment.id=? and ace.evaluation='0' ";

        Query q = commentEvaluateDao.createQuery(hqlZanCountForComment);

        q.setString(0,commentId);

        long zanCountForComment = (long)q.uniqueResult();

        return zanCountForComment+"";
    }

    private String commentCount(String articleId){

        String hqlCommentCount = "select count(ac.id) from ArticleComment ac where ac.article.id=? ";

        Query q = articleCommentDao.createQuery(hqlCommentCount);

        q.setString(0,articleId);

        long commentCount = (long)q.uniqueResult();

        return commentCount+"";
    }

    private String browseCount(String articleId){

        String hqlBrowseCount = "select count(lab.id) from LogArticleBrowse lab where lab.article.id=? ";

        Query q = logArticleBrowseDao.createQuery(hqlBrowseCount);

        q.setString(0,articleId);

        long browseCount = (long)q.uniqueResult();

        return browseCount+"";
    }

    private String collectionCount(String articleId){

        String hqlCollectionCount = "select count(ac.id) from ArticleCollection ac where ac.article.id=? ";

        Query q = collectionDao.createQuery(hqlCollectionCount);

        q.setString(0,articleId);

        long collectionCount = (long)q.uniqueResult();

        return collectionCount+"";
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

    public String ifZanForComment(String memberId,String commentId){

        if (memberId==null)return "0";

        //评论是否点赞
        String hqlZanCount = "from ArticleCommentEvaluate ace where ace.comment.id=? and ace.discussant.id=? ";

        Query q = articleEvaluateDao.createQuery(hqlZanCount);

        q.setString(0,commentId);

        q.setString(1,memberId);

        List<ArticleCommentEvaluate> l = q.list();

        if (l.size()>0){
            return "1";
        }

        return "0";
    }

    public boolean ifCollection(String memberId,String articleId){

        if (memberId==null)return false;

        //话题点赞数
        String hqlCollectionCount = "from ArticleCollection ac where ac.article.id=? and ac.discussant.id=? ";

        Query q = collectionDao.createQuery(hqlCollectionCount);

        q.setString(0,articleId);

        q.setString(1,memberId);

        List<ArticleCollection> l = q.list();

        if (l.size()>0){
            return true;
        }

        return false;
    }
}
