package com.dengyuecang.www.service.sys;

import com.dengyuecang.www.controller.api.sys.model.ArticleListRequest;
import com.dengyuecang.www.controller.api.sys.model.IndexListRequest;
import com.dengyuecang.www.controller.api.sys.model.IndexSortRequest;
import com.dengyuecang.www.controller.api.sys.model.UserLoginRequest;
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

}
