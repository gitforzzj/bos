package com.itheima.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Role;
import com.itheima.bos.service.IRoleService;
import com.itheima.bos.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private String functionIds;
	@Autowired
	private IRoleService service;
	
	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}
	
	public String add() {
		service.save(model,functionIds);
		return LIST;
	}
	
	public String pageQuery() {
		service.pageBean(pageBean);
		this.java2json(pageBean, new String[] {"functions","users"});
		return NONE;
	}
	
	public String listajax() {
		List<Role> list = service.findAll();
		this.java2json(list, new String[] {"functions","users"});
		return NONE;
	}
}
