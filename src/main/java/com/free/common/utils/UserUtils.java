/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.free.common.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.free.common.service.MonitorRealm.Principal;
import com.free.system.model.User;
import com.free.system.model.UserRole;

/**
 * 用户工具类
 */
public class UserUtils  {

	//private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	
	public static boolean isAdmin(){
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User)currentUser.getSession().getAttribute("currentUser");
		if(user==null){
			throw new RuntimeException("用户为空");
		}
		List<UserRole> list = user.getUserRoles();
		for (UserRole userRole : list) {
			if(userRole.getRoleid()==3){
				return true;
			}
		}
		return false;
	}
	
	public static User getUser(){
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User)currentUser.getSession().getAttribute("currentUser");
		if(user==null){
			throw new RuntimeException("用户为空");
		}
		return user;
	}
	public static Principal getPrincipal(){
		Subject subject = SecurityUtils.getSubject();
		Principal principal = (Principal)subject.getPrincipal();
		return principal;
	}
	
//	public static User getUser2(){
//		User user = new User();
//		//User user = (User)getCache(CACHE_USER);
//		if (user == null){
//			try{
//				Subject subject = SecurityUtils.getSubject();
//				Principal principal = (Principal)subject.getPrincipal();
//				if (principal!=null){
//					user = userDao.get(principal.getId());
//				}
//			}catch (UnavailableSecurityManagerException e) {
//				
//			}catch (InvalidSessionException e){
//				
//			}
//		}
//		if (user == null){
//			user = new User();
//			try{
//				SecurityUtils.getSubject().logout();
//			}catch (UnavailableSecurityManagerException e) {
//				
//			}catch (InvalidSessionException e){
//				
//			}
//		}
//		return user;
//	}
	
	public static String hiddenTelephone(String yddh){
		if(StringUtils.isNotBlank(yddh)){
			int len = yddh.length();
			if(len >= 4){
				String lastFour = yddh.substring(len-4, len -1);
				return yddh.replace(lastFour, "****");
			}
		}
		return "";
	}
	
	
}
