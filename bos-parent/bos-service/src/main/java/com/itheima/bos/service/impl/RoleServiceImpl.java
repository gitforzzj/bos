package com.itheima.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IRoleDao;
import com.itheima.bos.domain.Function;
import com.itheima.bos.domain.Role;
import com.itheima.bos.service.IRoleService;
import com.itheima.bos.utils.PageBean;
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public void save(Role model, String functionIds) {
		roleDao.save(model);
		String[] ids = functionIds.split(",");
		for (String id : ids) {
			Function f = new Function(id);
			model.getFunctions().add(f);
		}
	}

	@Override
	public void pageBean(PageBean pageBean) {
		// TODO Auto-generated method stub
		roleDao.pageQuery(pageBean);
	}

	@Override
	public List<Role> findAll() {
		
		
		return roleDao.findAll();
	}

}
