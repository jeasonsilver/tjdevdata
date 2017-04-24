package com.free.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.free.common.service.BaseService;
import com.free.common.utils.NewPager;
import com.free.system.model.Authorize;

/**
 * 
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */

@Service
public class AuthorizeService extends BaseService {
	
	@Autowired
	private Dao dao;

	public int delete(Integer id) {
		return dao.delete(Authorize.class,id);
	}

	public Authorize insert(Authorize record) {
		return dao.insert(record);
	}

	public Authorize fetch(Integer id) {
		return dao.fetch(Authorize.class,id);
	}

	public int updateIgnoreNull(Authorize record) {
		return dao.updateIgnoreNull(record);
	}

	public int update(Authorize record) {
		return dao.update(record);
	}

	public List<Authorize> query(Condition c){
		return dao.query(Authorize.class,c, null);
	}
	
	public Map<String,Object> queryPage(NewPager page){
		Criteria cri = getCriteriaFromPage(page);
		
	    List<Authorize> list = dao.query(Authorize.class, cri, page);
	    page.setRecordCount(dao.count(Authorize.class, cri));
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
	    return map;
	}
	
}
