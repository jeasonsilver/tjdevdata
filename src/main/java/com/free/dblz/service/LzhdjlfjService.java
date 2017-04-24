package com.free.dblz.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.free.dblz.model.Lzhdjlfj;

@Service
public class LzhdjlfjService {
	
	@Autowired
	private Dao dao;

	public int delete(Integer id) {
		return dao.delete(id);
	}
	public int delByFid(Integer fid){
		return dao.clear(Lzhdjlfj.class, Cnd.where("fid","=",fid));
	}

	public Lzhdjlfj insert(Lzhdjlfj record) {
		return dao.insert(record);
	}

	public Lzhdjlfj fetch(Integer id) {
		return dao.fetch(Lzhdjlfj.class,id);
	}

	public int updateIgnoreNull(Lzhdjlfj record) {
		return dao.updateIgnoreNull(record);
	}

	public int update(Lzhdjlfj record) {
		return dao.update(record);
	}
	
	public QueryResult queryPage(Pager page){
	    List<Lzhdjlfj> list = dao.query(Lzhdjlfj.class, null, page);
	    page.setRecordCount(dao.count(Lzhdjlfj.class));
	    return new QueryResult(list, page);
	}
	
	public List<Lzhdjlfj> queryByFid(Integer fid){
		return dao.query(Lzhdjlfj.class, Cnd.where("fid","=",fid));
	}
}
