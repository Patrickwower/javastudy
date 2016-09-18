package com.dengyuecang.www.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.dao.params.PageModel;

@Repository
public class HibernateBaseDao<T extends Serializable> implements BaseDao<T> {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public boolean contain(T o) {
		return getSession().contains(o);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public T get(Class<T> cla, Serializable id) {
		return getSession().get(cla, id);
	}

	public T load(Class<T> cla, Serializable id) {
		return getSession().load(cla, id);
	}

	public Serializable getIdentifier(T entity) {
		return getSession().getIdentifier(entity);
	}

	public void lock(T entity, LockMode lkMode) {
		getSession().lock(entity, lkMode);
	}

	public void merge(T entity) {
		getSession().merge(entity);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void refresh(T entity) {
		getSession().refresh(entity);
	}

	public void replicate(T entity, ReplicationMode repMode) {
		getSession().replicate(entity, repMode);
	}

	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public Criteria createCriteria(Class<T> entity) {
		return getSession().createCriteria(entity);
	}

	public Query createQuery(String hqlSql) {
		return getSession().createQuery(hqlSql);
	}

	@SuppressWarnings("unchecked")
	public List<T> pagedQuery(Criteria criteria, PageModel<T> page) {
		int total = criteria.list().size(); // 总记录数
		long pageNo = page.getPage();
		long pageSize = page.getRows();

		long rowsTotal = (long)criteria.setProjection(Projections.rowCount()).uniqueResult();

		page.setTotalPages(rowsTotal);

		criteria.setProjection(null);

		List<T> items = criteria.setFirstResult((int) ((pageNo - 1) * pageSize)).setMaxResults((int) pageSize).list();

		page.setList(items);
		page.setTotal(total);
		return items;
	}

	@SuppressWarnings("unchecked")
	public List<T> all(Criteria criteria) {
		return criteria.list();
	}
}
