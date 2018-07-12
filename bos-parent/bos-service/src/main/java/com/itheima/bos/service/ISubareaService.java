package com.itheima.bos.service;

import java.util.List;

import com.itheima.bos.domain.Region;
import com.itheima.bos.domain.Subarea;
import com.itheima.bos.utils.PageBean;

public interface ISubareaService {

	public void save(Subarea model);

	public void pageQuery(PageBean pageBean);

	public List<Subarea> findAll();

	public List<Subarea> findListNotAssociation();

	public List<Subarea> finListByDecidedzoneId(String decidedzoneId);

	public List<Object> findSubareasGroupByProvince();

	public void saveBatch(List<Subarea> subareaList);

}
