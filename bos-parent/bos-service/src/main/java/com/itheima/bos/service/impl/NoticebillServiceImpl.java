package com.itheima.bos.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IDecidedzoneDao;
import com.itheima.bos.dao.INoticebillDao;
import com.itheima.bos.dao.IWorkbillDao;
import com.itheima.bos.domain.Decidedzone;
import com.itheima.bos.domain.Noticebill;
import com.itheima.bos.domain.Staff;
import com.itheima.bos.domain.User;
import com.itheima.bos.domain.Workbill;
import com.itheima.bos.service.INoticebillService;
import com.itheima.bos.utils.BOSUtils;
import com.itheima.bos.utils.PageBean;
import com.itheima.crm.service.ICustomerService;
@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService {
	@Autowired
	private INoticebillDao noticebillDao;
	@Autowired
	private IDecidedzoneDao decidedzoneDao;
	@Autowired
	private IWorkbillDao workbillDao;
	@Autowired
	private ICustomerService customerService;
	
	@Override
	public void save(Noticebill model) {
		User user = BOSUtils.getLoginUser();
		model.setUserId(user.getId());
		noticebillDao.save(model);
		String pickaddress = model.getPickaddress();
		String decidedzoneId = customerService.findDecidedzoneIdByAddress(pickaddress);
		if(decidedzoneId!=null) {
			Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
			Staff staff = decidedzone.getStaff();
			model.setStaffId(staff.getId());
			model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
			Workbill workbill = new Workbill();
			workbill.setAttachbilltimes(0);
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
			workbill.setNoticebillId(model.getId());
			workbill.setPickstate(Workbill.PIKCSTATE_NO);
			workbill.setRemark(model.getRemark());
			workbill.setStaffId(staff.getId());
			workbill.setType(Workbill.TYPE_1);
			workbillDao.save(workbill);
		}else {
			model.setOrdertype(Noticebill.ORDERTYPE_MAN);
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		workbillDao.pageQuery(pageBean);
	}

}
