package com.itheima.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Function;
import com.itheima.bos.service.IFunctionService;
import com.itheima.bos.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {

	@Autowired
	private IFunctionService service;
	
	public String listajax() {
		List<Function> list = service.findAll();
		this.java2json(list, new String[] {"parentFunction","roles","code","description","page","generatemenu","zindex"});
		return NONE;
	}
	
	public String findMenu() {
		List<Function> list = service.findMenu();
		this.java2json(list, new String[] {"children","roles","parentFunction"});
		return NONE;
	}
	
	public String add() {
		service.save(model);
		return LIST;
	}
	
	public String pageQuery() {
		String page = model.getPage();
		pageBean.setCurrentPage(Integer.parseInt(page));
		service.pageQuery(pageBean);
		this.java2json(pageBean, new String[] {"parentFunction","children","roles"});
		return NONE;
	}
	
}
