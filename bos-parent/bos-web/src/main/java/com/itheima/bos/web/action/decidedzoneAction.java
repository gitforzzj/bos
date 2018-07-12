package com.itheima.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Decidedzone;
import com.itheima.bos.service.IDecidedzoneService;
import com.itheima.bos.web.action.base.BaseAction;
import com.itheima.crm.service.Customer;
import com.itheima.crm.service.ICustomerService;

@Controller
@Scope("prototype")
public class decidedzoneAction extends BaseAction<Decidedzone> {
	@Autowired
	private ICustomerService proxy;
	
	private List<Integer> customerIds;
	
	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}

	public String findListNotAssociation() {
		List<Customer> list = proxy.findListNotAssociation();
		this.java2json(list, new String[] {});
		return NONE;
	}
	
	public String findListHasAssociation() {
		String id = model.getId();
		List<Customer> list = proxy.findListHasAssociation(id);
		this.java2json(list, new String[] {});
		return NONE;
	}
	
	public String assigncustomerstodecidedzone() {
		proxy.assigncustomerstodecidedzone(model.getId(), customerIds);
		return LIST;
	}
	
	private String[] subareaid;
	@Autowired
	private IDecidedzoneService decidedzoneService;
	
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}

	public String add() {
		decidedzoneService.save(model,subareaid);
		return LIST;
	}
	
	public String pageQuery() {
		decidedzoneService.pageQuery(pageBean);
		this.java2json(pageBean, new String[]{"currentPage","detachedCriteria",
				"pageSize","subareas","decidedzones"});
		return NONE;
	}
	
}
