package com.dengyuecang.www.service.sys;

import com.dengyuecang.www.controller.api.sys.model.*;
import com.dengyuecang.www.entity.StaticProvince;
import com.dengyuecang.www.entity.community.Article;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;

public interface ISysArticleService extends IBaseService<Article>{

	/**
	 * 文章列表
	 * @param headers
	 * @return
	 */
	public RespData articleList(HttpHeaders headers, ArticleListRequest articleListRequest);

	/**
	 * 文章禁用
	 */
	public RespData forbidden(HttpHeaders headers, String articleId);

	/**
	 * 文章禁用
	 */
	public RespData enable(HttpHeaders headers, String articleId);

	/**
	 * 文章上头条列表的接口
	 * @param headers
	 * @param articleId
     * @return
     */
	public RespData toIndex(HttpHeaders headers,String articleId);

	/**
	 * 首页热门文章列表
	 */
	public RespData indexList(HttpHeaders headers, IndexListRequest indexListRequest);

	/**
	 * 首页热门文章排序接口
	 */
	public RespData indexSort(HttpHeaders headers, IndexSortRequest indexSortRequest);

	/**
	 * 首页热门文章列表
	 */
	public RespData bannerList(HttpHeaders headers, BannerListRequest bannerListRequest);


	/**
	 * 文章上banner列表的接口
	 * @param headers
	 * @param articleId
	 * @return
	 */
	public RespData toBanner(HttpHeaders headers,String articleId);

	/**
	 * banner中信息上线
	 * @param headers
	 * @param recommendId
	 * @return
	 */
	public RespData bannerUp(HttpHeaders headers,String recommendId);

	/**
	 * banner中信息下线
	 * @param headers
	 * @param recommendId
	 * @return
	 */
	public RespData bannerDown(HttpHeaders headers,String recommendId);

}
