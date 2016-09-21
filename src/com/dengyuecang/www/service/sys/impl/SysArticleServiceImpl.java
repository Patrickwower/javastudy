package com.dengyuecang.www.service.sys.impl;

import com.dengyuecang.www.controller.api.sys.model.ArticleListRequest;
import com.dengyuecang.www.controller.api.sys.model.BannerListRequest;
import com.dengyuecang.www.controller.api.sys.model.IndexListRequest;
import com.dengyuecang.www.controller.api.sys.model.IndexSortRequest;
import com.dengyuecang.www.entity.StaticProvince;
import com.dengyuecang.www.entity.community.Article;
import com.dengyuecang.www.entity.community.ArticleIndex;
import com.dengyuecang.www.entity.community.ArticleRecommend;
import com.dengyuecang.www.entity.sys.User;
import com.dengyuecang.www.service.common.CommonConstant;
import com.dengyuecang.www.service.community.IArticleService;
import com.dengyuecang.www.service.community.model.IndexArticle;
import com.dengyuecang.www.service.sys.ISysArticleService;
import com.dengyuecang.www.service.sys.model.SysBannerArticle;
import com.dengyuecang.www.service.sys.model.SysIndexArticle;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.dao.params.PageModel;
import com.longinf.lxcommon.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SysArticleServiceImpl extends BaseService<Article> implements ISysArticleService{

	Logger log = LoggerFactory.getLogger(SysArticleServiceImpl.class);
	
	@Resource(name = "hibernateBaseDao")
	private BaseDao<StaticProvince> provinceDao;

	@Resource(name = "hibernateBaseDao")
	private BaseDao<User> userDao;

	@Resource(name = "hibernateBaseDao")
	private BaseDao<Article> articleDao;

	@Resource
	private IArticleService articleServiceImpl;

	@Resource(name = "hibernateBaseDao")
	private BaseDao<ArticleIndex> articleIndexDao;

	@Resource(name = "hibernateBaseDao")
	private BaseDao<ArticleRecommend> articleRecommendDao;

	@Override
	public BaseDao<Article> getSuperDao() {
		return null;
	}


	@Override
	public RespData articleList(HttpHeaders headers, ArticleListRequest articleListRequest) {

		try {

			Criteria articleCriteria = articleDao.createCriteria(Article.class);

			if (StringUtils.isNotEmpty(articleListRequest.getAuthor())){

				articleCriteria.createCriteria("member").createCriteria("memberInfo").add(Restrictions.like("nickname", "%"+articleListRequest.getAuthor()+"%"));

			}

			if (StringUtils.isNotEmpty(articleListRequest.getTitle())){

				articleCriteria.add(Restrictions.like("title", "%"+articleListRequest.getTitle()+"%"));

			}

			if (StringUtils.isNotEmpty(articleListRequest.getStatus())){

				articleCriteria.add(Restrictions.eq("status", articleListRequest.getStatus()));

			}

			if (StringUtils.isNotEmpty(articleListRequest.getStime())){

				Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				articleCriteria.add(Restrictions.ge("ctime", f.parseObject(articleListRequest.getStime()+" 00:00:00")));

			}

			if (StringUtils.isNotEmpty(articleListRequest.getEtime())){

				Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				articleCriteria.add(Restrictions.le("ctime", f.parseObject(articleListRequest.getEtime()+" 23:59:59")));

			}

			articleCriteria.addOrder(Order.desc("ctime"));

			articleDao.pagedQuery(articleCriteria,articleListRequest.getPageModel());

			HashMap<String,Object> response = new HashMap<String,Object>();

			List<IndexArticle> articles = new ArrayList<IndexArticle>();

			String memberId = headers.getFirst("memberId");

			articles = articleServiceImpl.toIndexArticleList(memberId,articles,articleListRequest.getPageModel().getList());

			PageModel page = articleListRequest.getPageModel();

			page.setList(null);

			response.put("page",articleListRequest.getPageModel());

			response.put("articles",articles);

//			response.put("articles",ll);

			return RespCode.getRespData(RespCode.SUCESS,response);

		}catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public RespData forbidden(HttpHeaders headers, String articleId) {

		Article article = articleDao.get(Article.class,articleId);

		if (article!=null){

			if ("100".equals(article.getStatus())){
				article.setStatus("200");
			}else if ("200".equals(article.getStatus()))
			{
				article.setStatus("100");
			}

		}

		articleDao.saveOrUpdate(article);

		Map<String,String> response = new HashMap<String,String>();

		response.put("statusName", CommonConstant.ARTICLE_STATUS.get(article.getStatus()));

		return RespCode.getRespData(RespCode.SUCESS,response);
	}

	@Override
	public RespData enable(HttpHeaders headers, String articleId) {

		return this.forbidden(headers,articleId);
	}

	@Override
	public RespData toIndex(HttpHeaders headers, String articleId) {

		if (StringUtils.isEmpty(articleId)){
			return RespCode.getRespData(RespCode.ARTICLE_NOT_EXIST,new HashMap<String,String>());
		}

		Article article = articleDao.get(Article.class,articleId);

		if (article==null){
			return RespCode.getRespData(RespCode.ARTICLE_NOT_EXIST,new HashMap<String,String>());
		}

		List<ArticleIndex> aiList = articleIndexDao.createQuery("from ArticleIndex ai where ai.article.id=? ").setString(0,articleId).list();

		if (aiList.size()>0){
			return RespCode.getRespData(RespCode.ARTICLE_ALREADY_INDEX,new HashMap<String,String>());
		}

		article.setIf_index("1");

		articleDao.saveOrUpdate(article);

		ArticleIndex ai = new ArticleIndex();

		ai.setArticle(article);

		ai.setIndex_time(new Date());

		ai.setMax_sort("99999999");

		ai.setSort(99999999);

		ai.setTimestamp(System.currentTimeMillis());

		articleIndexDao.save(ai);

		Map<String,String> response = new HashMap<String,String>();

		return RespCode.getRespData(RespCode.SUCESS,response);

	}

	@Override
	public RespData toBanner(HttpHeaders headers, String articleId) {

		if (StringUtils.isEmpty(articleId)){
			return RespCode.getRespData(RespCode.ARTICLE_NOT_EXIST,new HashMap<String,String>());
		}

		Article article = articleDao.get(Article.class,articleId);

		if (article==null){
			return RespCode.getRespData(RespCode.ARTICLE_NOT_EXIST,new HashMap<String,String>());
		}

		List<ArticleRecommend> arList = articleIndexDao.createQuery("from ArticleRecommend ar where ar.article.id=? ").setString(0,articleId).list();

		if (arList.size()>0){
			return RespCode.getRespData(RespCode.ARTICLE_ALREADY_INDEX,new HashMap<String,String>());
		}

		article.setBanner("1");

		articleDao.saveOrUpdate(article);

		ArticleRecommend ar = new ArticleRecommend();

		ar.setArticle(article);

		ar.setCtime(new Date());

		ar.setStatus("99999999");

		ar.setSort("99999999");

		ar.setTimestamp(System.currentTimeMillis());

		ar.setStatus("100");

		ar.setType("article");

		articleRecommendDao.save(ar);

		Map<String,String> response = new HashMap<String,String>();

		return RespCode.getRespData(RespCode.SUCESS,response);

	}

	@Override
	public RespData bannerUp(HttpHeaders headers, String recommendId) {

		ArticleRecommend ar = articleRecommendDao.get(ArticleRecommend.class,recommendId);

		if (ar!=null){

			ar.setStatus("100");

			ar.setCtime(new Date());

			ar.setTimestamp(System.currentTimeMillis());

			articleRecommendDao.saveOrUpdate(ar);

			Map<String,String> response = new HashMap<String,String>();

			response.put("msg","上线成功");

			return RespCode.getRespData(RespCode.SUCESS,response);

		}

		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
	}

	@Override
	public RespData bannerDown(HttpHeaders headers, String recommendId) {

		ArticleRecommend ar = articleRecommendDao.get(ArticleRecommend.class,recommendId);

		if (ar!=null){

			ar.setStatus("200");

			articleRecommendDao.saveOrUpdate(ar);

			Map<String,String> response = new HashMap<String,String>();

			response.put("msg","下线成功");

			return RespCode.getRespData(RespCode.SUCESS,response);
		}

		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
	}

	@Override
	public RespData indexList(HttpHeaders headers, IndexListRequest indexListRequest) {

		try {

			Criteria indexCriteria = articleIndexDao.createCriteria(ArticleIndex.class);

			if (StringUtils.isNotEmpty(indexListRequest.getAuthor())){

				indexCriteria.createCriteria("article").createCriteria("member").createCriteria("memberInfo").add(Restrictions.like("nickname", "%"+indexListRequest.getAuthor()+"%"));

			}

			if (StringUtils.isNotEmpty(indexListRequest.getTitle())){

				indexCriteria.createCriteria("article").add(Restrictions.like("title", "%"+indexListRequest.getTitle()+"%"));

			}

			indexCriteria.addOrder(Order.asc("sort"));

			indexCriteria.addOrder(Order.desc("index_time"));

			articleDao.pagedQuery(indexCriteria,indexListRequest.getPageModel());

			List<ArticleIndex> articleIndexList = indexListRequest.getPageModel().getList();

			List<Article> articleList = new ArrayList<Article>();

			for (ArticleIndex articleIndex :
					articleIndexList) {

				articleList.add(articleIndex.getArticle());

			}

			HashMap<String,Object> response = new HashMap<String,Object>();

			List<IndexArticle> articles = new ArrayList<IndexArticle>();

			String memberId = headers.getFirst("memberId");

			articles = articleServiceImpl.toIndexArticleList(memberId,articles,articleList);

			List<SysIndexArticle> sysIndexArticleList = new ArrayList<SysIndexArticle>();

			for (ArticleIndex articleIndex :
					articleIndexList) {

				SysIndexArticle sysIndexArticle = new SysIndexArticle();

				sysIndexArticle.setId(articleIndex.getId());

				sysIndexArticle.setIndex_time(articleIndex.getIndex_time());

				sysIndexArticle.setSort(articleIndex.getSort());

				for (IndexArticle indexArticle:
					 articles) {

					if (indexArticle.getId().equals(articleIndex.getArticle().getId())){

						sysIndexArticle.setIndexArticle(indexArticle);

						break;
					}

				}


				sysIndexArticleList.add(sysIndexArticle);
			}

			PageModel page = indexListRequest.getPageModel();

			page.setList(null);

			response.put("page",indexListRequest.getPageModel());

			response.put("indexArticles",sysIndexArticleList);

			return RespCode.getRespData(RespCode.SUCESS,response);

		}catch (Exception e){
			e.printStackTrace();
		}

		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());

	}

	@Override
	public RespData indexSort(HttpHeaders headers, IndexSortRequest indexSortRequest) {

		List<ArticleIndex> indexList = articleIndexDao.createQuery("from ArticleIndex where id<>'"+indexSortRequest.getIndexId()+"' and sort<99999999 order by sort").list();

		int sortString = 99999999;

		if (StringUtils.isNotEmpty(indexSortRequest.getSort())){
			sortString = Integer.valueOf(indexSortRequest.getSort());
		}

		String indexId = indexSortRequest.getIndexId();

		ArticleIndex ai = articleIndexDao.get(ArticleIndex.class,indexId);
		int sortResult = 0;

		if (0==sortString||99999999==sortString){
			ai.setSort(99999999);

		}else {

			int sort = Integer.valueOf(sortString);


			int size = indexList.size();

			if (sort>size){

				ai.setSort(size+1);

			}else if(sort==size){

				ai.setSort(sortString);

				sortResult = sort;

			}else if(sort<size){

				ai.setSort(sortString);

				sortResult = sort;

			}

		}

		articleIndexDao.saveOrUpdate(ai);
		this.reSort(indexList,sortResult);


		return RespCode.getRespData(RespCode.SUCESS,new HashMap<String,String>());
	}

	@Override
	public RespData bannerList(HttpHeaders headers, BannerListRequest bannerListRequest) {

		try {

			Criteria recommendCriteria = articleRecommendDao.createCriteria(ArticleRecommend.class);

			if (StringUtils.isNotEmpty(bannerListRequest.getAuthor())){

				recommendCriteria.createCriteria("article").createCriteria("member").createCriteria("memberInfo").add(Restrictions.like("nickname", "%"+bannerListRequest.getAuthor()+"%"));

			}

			if (StringUtils.isNotEmpty(bannerListRequest.getTitle())){

				recommendCriteria.createCriteria("article").add(Restrictions.like("title", "%"+bannerListRequest.getTitle()+"%"));

			}

			if (StringUtils.isNotEmpty(bannerListRequest.getStatus())){

				recommendCriteria.add(Restrictions.eq("status",bannerListRequest.getStatus()));

			}

			recommendCriteria.addOrder(Order.asc("sort"));

			recommendCriteria.addOrder(Order.desc("ctime"));

			articleRecommendDao.pagedQuery(recommendCriteria,bannerListRequest.getPageModel());

			List<ArticleRecommend> articleRecommendList = bannerListRequest.getPageModel().getList();

			List<Article> articleList = new ArrayList<Article>();

			for (ArticleRecommend articleRecommend :
					articleRecommendList) {

				articleList.add(articleRecommend.getArticle());

			}

			HashMap<String,Object> response = new HashMap<String,Object>();

			List<IndexArticle> articles = new ArrayList<IndexArticle>();

			String memberId = headers.getFirst("memberId");

			articles = articleServiceImpl.toIndexArticleList(memberId,articles,articleList);

			List<SysBannerArticle> sysIndexArticleList = new ArrayList<SysBannerArticle>();

			for (ArticleRecommend articleRecommend :
					articleRecommendList) {

				SysBannerArticle sysBannerArticle = new SysBannerArticle();

				sysBannerArticle.setId(articleRecommend.getId());

				sysBannerArticle.setCtime(articleRecommend.getCtime());

				sysBannerArticle.setSort(articleRecommend.getSort());

				sysBannerArticle.setStatus(articleRecommend.getStatus());

				sysBannerArticle.setStatusName(CommonConstant.BANNER_STATUS.get(articleRecommend.getStatus()));

				for (IndexArticle indexArticle:
						articles) {

					if (indexArticle.getId().equals(articleRecommend.getArticle().getId())){

						sysBannerArticle.setIndexArticle(indexArticle);

						break;
					}

				}


				sysIndexArticleList.add(sysBannerArticle);
			}

			PageModel page = bannerListRequest.getPageModel();

			page.setList(null);

			response.put("page",bannerListRequest.getPageModel());

			response.put("banners",sysIndexArticleList);

			return RespCode.getRespData(RespCode.SUCESS,response);

		}catch (Exception e){
			e.printStackTrace();
		}

		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());

	}

	/**
	 * index热门排序重排机制
	 */
	private void reSort(List<ArticleIndex> indexList,int sort){

		int indexSort = 0;
		for (ArticleIndex aIndex :
				indexList) {
			indexSort++;
			if (sort!=0&&sort==indexSort){
				indexSort++;
			}
			if (indexSort <= CommonConstant.INDEX_HOT_MAX_SORT) {
				aIndex.setSort(indexSort);
			}else{
				aIndex.setSort(99999999);
			}
			articleIndexDao.saveOrUpdate(aIndex);
		}

	}

}
