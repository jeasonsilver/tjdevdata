package com.free.common.service;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.free.common.utils.NewPager;
import com.free.common.utils.SearchFilter;
import com.free.common.utils.SearchFilter.Operator;
import com.free.common.utils.UserUtils;
import com.free.common.utils.reflection.ReflectionUtils;

public class BaseService {

	@Autowired
	private Dao dao;
	
	public Object insert(Object record) {
		ReflectionUtils.invokeSetterMethod(record, "cjr",UserUtils.getUser().getXm());
		ReflectionUtils.invokeSetterMethod(record, "createid",UserUtils.getUser().getId());
		ReflectionUtils.invokeSetterMethod(record, "deptname",UserUtils.getUser().getDeptname());
		if(StringUtils.isNotBlank(UserUtils.getUser().getFbdw())){
			ReflectionUtils.invokeSetterMethod(record, "fbdw",UserUtils.getUser().getFbdw());
		}else{
			ReflectionUtils.invokeSetterMethod(record, "fbdw",UserUtils.getUser().getDeptname());
		}
		
		return dao.insert(record);
	}
	// 拼开查询条件
	public Criteria getCriteriaFromPage(NewPager page) {
		Iterator it = page.getFilters().entrySet().iterator();
		Criteria cri = Cnd.cri();
		while (it.hasNext()) {
			Map.Entry item = (Entry) it.next(); 
			SearchFilter sf = (SearchFilter)item.getValue();
			
			String op ="=";
			if (Operator.LIKE.equals(sf.operator)){
				op="LIKE";
				cri.where().andLike(sf.fieldName,sf.value.toString(),false);
			}else if(Operator.GT.equals(sf.operator)){
				cri.where().and(Cnd.exp(sf.fieldName, ">",sf.value.toString()));
			}else if(Operator.GTE.equals(sf.operator)){
				cri.where().and(Cnd.exp(sf.fieldName, ">=",sf.value.toString()));
			}else if (Operator.LT.equals(sf.operator)) {
				cri.where().and(Cnd.exp(sf.fieldName, "<",sf.value.toString()));
			}else if (Operator.LTE.equals(sf.operator)) {
				cri.where().and(Cnd.exp(sf.fieldName, "<=",sf.value.toString()));
			}else if (Operator.IN.equals(sf.operator)) {
				cri.where().andIn(sf.fieldName, sf.value.toString());
			}else if (Operator.INSQL.equals(sf.operator)) {
				cri.where().andInBySql(sf.fieldName, sf.value.toString());
			}else{
				cri.where().and(sf.fieldName,op,sf.value.toString());
			}
		}
		if (page.DESC.equals(page.getOrder())){
			cri.getOrderBy().desc(page.getOrderBy());
		}
		if (page.ASC.equals(page.getOrder())){
			cri.getOrderBy().asc(page.getOrderBy());
		} 
		String[] s= page.getOrders();
		if (s!=null){
			for (int i = 0; i < s.length/2; i++) {
				if (page.DESC.equals(s[i*2+1])){
					cri.getOrderBy().desc(s[i*2]);
				}else{
					cri.getOrderBy().asc(s[i*2]);
				}
			}
		}
		if (page.getOrder()==null && page.getOrders()==null ){
			cri.getOrderBy().desc("cjsj");
		}
		return cri;
	}
}
