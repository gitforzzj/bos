package com.itheima.bos.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.IStaffService;
import com.itheima.bos.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	@Autowired
	private IStaffService staffService;
	
	private String ids;
	public String pageQuery() throws Exception {
		
		staffService.pageQuery(pageBean);
		
		java2json(pageBean,new String[] {"currentPage","pageSize","detachedCriteria","decidedzones"});
		return NONE;
	}
	public String listajax() {
		List<Staff> list = staffService.findListNotDelete();
		this.java2json(list, new String[] {"decidedzones"});
		return NONE;
	}
	
	public String edit() {
		Staff staff=staffService.findById(model.getId());
		staff.setName(model.getName());
		staff.setDeltag(model.getDeltag());
		staff.setHaspda(model.getHaspda());
		staff.setStandard(model.getStandard());
		staff.setStation(model.getStation());
		staffService.update(staff);
		return LIST;
	}
	
	/*@RequiresPermissions("staff-delete")*/
	public String deleteBatch() throws Exception{
		staffService.deleteBatch(ids);
		return LIST;
	}
	
	public String doRestore() throws Exception{
		staffService.doRestore(ids);
		return LIST;
	}
	
	
	public void setIds(String ids) {
		this.ids = ids;
	}

	
	public String add() {
		staffService.save(model);
		return LIST;
	}
	
	public String findByNameOrPhone() {
		List<Staff> list=staffService.findByNameOrPhone(model.getId());
		this.java2json(list, new String[] {"currentPage","pageSize","detachedCriteria","decidedzones"});
		return NONE;
	}
	
}
