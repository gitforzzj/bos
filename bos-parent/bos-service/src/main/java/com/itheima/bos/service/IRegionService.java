package com.itheima.bos.service;

import java.util.List;

import com.itheima.bos.domain.Region;
import com.itheima.bos.utils.PageBean;

public interface IRegionService {

	public void saveBatch(List<Region> regionList);

	public void pageQuery(PageBean pageBean);

	public List<Region> findListByQ(String q);

	public List<Region> findAll();

	public void save(Region model);

	public void update(Region model);

	public void deleteBatch(String ids);

}
