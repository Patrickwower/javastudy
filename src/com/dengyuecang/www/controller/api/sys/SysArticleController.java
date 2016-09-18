package com.dengyuecang.www.controller.api.sys;


import com.dengyuecang.www.controller.api.sys.model.ArticleListRequest;
import com.dengyuecang.www.controller.api.sys.model.IndexListRequest;
import com.dengyuecang.www.controller.api.sys.model.IndexSortRequest;
import com.dengyuecang.www.controller.api.sys.model.UserLoginRequest;
import com.dengyuecang.www.service.members.IMembersService;
import com.dengyuecang.www.service.sys.ISysArticleService;
import com.dengyuecang.www.service.sys.ISysService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.params.PageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/sys/article")
public class SysArticleController {

	Logger log = LoggerFactory.getLogger(SysArticleController.class);
	
	@Resource
	private ISysArticleService sysArticleServiceImpl;

	@Resource
	private IMembersService membersServiceImpl;

	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public RespData list(@RequestHeader HttpHeaders headers, ArticleListRequest articleListRequest,PageModel pageModel){
		
		try {

			log.info("文章列表");

			articleListRequest.setPageModel(pageModel);

			return sysArticleServiceImpl.articleList(headers,articleListRequest);
		
		} catch (Exception e) {
			
		}
		
		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
	}

	/**
	 * 获取个人信息
	 */
	@RequestMapping(value = "/author/information",method = RequestMethod.POST)
	@ResponseBody
	public RespData authorInformation(@RequestHeader HttpHeaders headers,String memberId){

		try {
			return membersServiceImpl.information(headers, memberId);
		}catch (Exception e){
			e.printStackTrace();
		}

		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
	}

	/**
	 * 文章禁用接口
	 */
	@RequestMapping(value = "/forbidden",method = RequestMethod.POST)
	@ResponseBody
	public RespData forbidden(@RequestHeader HttpHeaders headers,String articleId){

		try {

			return sysArticleServiceImpl.forbidden(headers, articleId);

		}catch (Exception e){
			e.printStackTrace();
		}

		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
	}

	/**
	 * 文章启用接口
	 */
	@RequestMapping(value = "/enable",method = RequestMethod.POST)
	@ResponseBody
	public RespData enable(@RequestHeader HttpHeaders headers,String articleId){

		try {

			return sysArticleServiceImpl.enable(headers, articleId);

		}catch (Exception e){
			e.printStackTrace();
		}

		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
	}

	/**
	 * 插入到首页的热门文章
	 * @param headers
	 * @param articleId
     * @return
     */
	@RequestMapping(value = "/toIndex",method = RequestMethod.POST)
	@ResponseBody
	public RespData toIndex(@RequestHeader HttpHeaders headers, String  articleId){

		try {

			return sysArticleServiceImpl.toIndex(headers,articleId);

		}catch (Exception e){
			e.printStackTrace();
		}

		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

	}

	/**
	 * 分页查询index列表
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/index/list",method = RequestMethod.POST)
	@ResponseBody
	public RespData indexList(@RequestHeader HttpHeaders headers, IndexListRequest indexListRequest, PageModel pageModel){

		try {

			indexListRequest.setPageModel(pageModel);

			return sysArticleServiceImpl.indexList(headers,indexListRequest);

		}catch (Exception e){
			e.printStackTrace();
		}

		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

	}

	/**
	 * 给index列表
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/index/sort",method = RequestMethod.POST)
	@ResponseBody
	public RespData indexSort(@RequestHeader HttpHeaders headers, IndexSortRequest indexSortRequest){

		try {

			return sysArticleServiceImpl.indexSort(headers,indexSortRequest);

		}catch (Exception e){
			e.printStackTrace();
		}

		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

	}


	/**
	 * 分页查询index列表
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/banner/list",method = RequestMethod.POST)
	@ResponseBody
	public RespData bannerList(@RequestHeader HttpHeaders headers, IndexListRequest indexListRequest, PageModel pageModel){

		try {

			return sysArticleServiceImpl.indexList(headers,indexListRequest);

		}catch (Exception e){
			e.printStackTrace();
		}

		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

	}

}
