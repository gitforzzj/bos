package com.itheima.bos.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.itheima.bos.domain.Staff;
import com.itheima.bos.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	protected final static String HOME ="home";
	protected final static String LIST ="list";
	
	protected PageBean pageBean = new PageBean();
	protected DetachedCriteria detachedCriteria=null;
	
	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}
	
	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}
	
	protected T model;
	
	public T getModel() {
		return model;
	}
	
	public void java2json(Object o,String[] exclude) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(exclude);
		String json = JSONObject.fromObject(o,jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param o为list集合
	 * @param exclude排除要转为json的对象
	 */
	public void java2json(List o,String[] exclude) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(exclude);
		String json = JSONArray.fromObject(o,jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BaseAction() {
		ParameterizedType genericSuperclass=(ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		Class<T> entityClass = (Class<T>) actualTypeArguments[0];
		
		detachedCriteria = DetachedCriteria.forClass(entityClass);
		pageBean.setDetachedCriteria(detachedCriteria);
		try {
			model = entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
}
