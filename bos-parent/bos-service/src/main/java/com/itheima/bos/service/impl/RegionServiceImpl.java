package com.itheima.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IRegionDao;
import com.itheima.bos.domain.Region;
import com.itheima.bos.service.IRegionService;
import com.itheima.bos.utils.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {

	@Autowired
	IRegionDao regionDao;
	
	@Override
	public void saveBatch(List<Region> regionList) {
		for(Region region:regionList) {
			regionDao.saveOrUpdate(region);
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		regionDao.pageQuery(pageBean);
	}

	@Override
	public List<Region> findListByQ(String q) {
		// TODO Auto-generated method stub
		return regionDao.findListByQ(q);
	}

	@Override
	public List<Region> findAll() {
		// TODO Auto-generated method stub
		return regionDao.findAll();
	}

	@Override
	public void save(Region model) {
		// TODO Auto-generated method stub
		regionDao.save(model);
	}

	@Override
	public void update(Region model) {
		// TODO Auto-generated method stub
		regionDao.update(model);
	}
	
	@Override
	public void deleteBatch(String ids) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(ids)) {
			String[] staffIds = ids.split(",");
			for(String id:staffIds) {
				regionDao.delete(regionDao.findById(id));
			}
		}
	}

}
