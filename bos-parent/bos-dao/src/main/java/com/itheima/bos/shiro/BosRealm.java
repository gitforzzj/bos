package com.itheima.bos.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itheima.bos.dao.IFunctionDao;
import com.itheima.bos.dao.IUserDao;
import com.itheima.bos.domain.Function;
import com.itheima.bos.domain.User;

public class BosRealm extends AuthorizingRealm{

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IFunctionDao functionDao;
	//认证方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken mytoken = (UsernamePasswordToken)token;
		String username = mytoken.getUsername();
		User user = userDao.findUserByUsername(username);
		if(user==null) {
			return null;
		}
		//用户，用户密码，会和token提交的密码进行比较，错误抛出异常，第三个参数随意字符串
		AuthenticationInfo info = new SimpleAuthenticationInfo(user,mytoken.getPassword(),this.getName());
		return info;
	}

	//授权方法
		@Override
		protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			User user = (User)SecurityUtils.getSubject().getPrincipal();
			List<Function> list= null;
			if(user.getUsername().equals("admin")) {
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
				list = functionDao.findByCriteria(detachedCriteria);
			}else {
				list=functionDao.findFunctionListByUserId(user.getId());
			}
			
			for(Function function:list) {
				info.addStringPermission(function.getCode());
			}
			
			return info;
		}
		
	
}
