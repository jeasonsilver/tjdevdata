package com.free.dblz.service;

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
import com.free.dblz.model.Lzhdjl;

/**
 * 代表履职--履职信息记录
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */

@Service
public class LzhdjlService extends BaseService {
	
	@Autowired
	private Dao dao;

	//删除
	public int delete(Integer id) {
		return dao.delete(Lzhdjl.class,id);
	}

	//插入
	public Lzhdjl insert(Lzhdjl record) {
		return dao.insert(record);
	}

	//取单条
	public Lzhdjl fetch(Integer id) {
		return dao.fetch(Lzhdjl.class,id);
	}

	//更新非空
	public int updateIgnoreNull(Lzhdjl record) {
		return dao.updateIgnoreNull(record);
	}

	//更新
	public int update(Lzhdjl record) {
		return dao.update(record);
	}

	//查询
	public List<Lzhdjl> query(Condition c){
		return dao.query(Lzhdjl.class,c, null);
	}
	
	//分页查询
	public Map queryPage(NewPager page){
		Criteria cri = getCriteriaFromPage(page);
		
	    List<Lzhdjl> list = dao.query(Lzhdjl.class, cri, page);
	    page.setRecordCount(dao.count(Lzhdjl.class, cri));
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
	    return map;
	}
	
}
