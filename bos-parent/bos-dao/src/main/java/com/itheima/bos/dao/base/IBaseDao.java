package com.itheima.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.bos.utils.PageBean;

public interface IBaseDao<T> {
	public void save(T entity);
	void saveOrUpdate(T entity);
	public void delete(T entity);
	public void update(T entity);
	public T findById(Serializable id);
	public List<T> findByCriteria(DetachedCriteria detachedCriteria);
	public List<T> findAll();
	public void executeUpdate(String string, Object...objects);
	public void pageQuery(PageBean pageBean);
}
