package com.dengyuecang.www.service.sys.impl;

import com.dengyuecang.www.controller.api.sys.model.ArticleListRequest;
import com.dengyuecang.www.entity.StaticProvince;
import com.dengyuecang.www.entity.community.Article;
import com.dengyuecang.www.entity.sys.User;
import com.dengyuecang.www.service.sys.ISysArticleService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class SysArticleServiceImpl extends BaseService<Article> implements ISysArticleService{

	Logger log = LoggerFactory.getLogger(SysArticleServiceImpl.class);
	
	@Resource(name = "hibernateBaseDao")
	private BaseDao<StaticProvince> provinceDao;

	@Resource(name = "hibernateBaseDao")
	private BaseDao<User> userDao;

	@Resource(name = "hibernateBaseDao")
	private BaseDao<Article> articleDao;


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

			List<Article> ll = articleDao.pagedQuery(articleCriteria,articleListRequest.getPageModel());

			long rowsTotal = (long)articleCriteria.setProjection(Projections.rowCount()).uniqueResult();

			return RespCode.getRespData(RespCode.SUCESS,ll);

		}catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}
}
