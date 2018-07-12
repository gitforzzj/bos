package com.itheima.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Noticebill;
import com.itheima.bos.service.INoticebillService;
import com.itheima.bos.web.action.base.BaseAction;
import com.itheima.crm.service.Customer;
import com.itheima.crm.service.ICustomerService;
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {

	@Autowired
	private ICustomerService customerService;
	@Autowired
	private INoticebillService noticebillService;
	
	public String findCustomerByTelephone() {
		String telephone = model.getTelephone();
		Customer customer = customerService.findCustomerByTelephone(telephone);
		this.java2json(customer, new String[] {});
		return NONE;
	}
	
	public String add() {
		
		noticebillService.save(model);
		return "noticebill_add";
	}
	
	public String pageQuery() {
		noticebillService.pageQuery(pageBean);
		java2json(pageBean,new String[] {"currentPage","pageSize","detachedCriteria","decidedzones"});
		return NONE;
	}
	
}
