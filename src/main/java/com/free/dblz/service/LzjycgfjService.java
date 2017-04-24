package com.free.dblz.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.free.dblz.model.Lzjycgfj;

@Service
public class LzjycgfjService {
	
	@Autowired
	private Dao dao;

	public int delete(Integer id) {
		return dao.delete(id);
	}
	public int delByFid(Integer fid){
		return dao.clear(Lzjycgfj.class, Cnd.where("fid","=",fid));
	}

	public Lzjycgfj insert(Lzjycgfj record) {
		return dao.insert(record);
	}

	public Lzjycgfj fetch(Integer id) {
		return dao.fetch(Lzjycgfj.class,id);
	}

	public int updateIgnoreNull(Lzjycgfj record) {
		return dao.updateIgnoreNull(record);
	}

	public int update(Lzjycgfj record) {
		return dao.update(record);
	}
	
	public QueryResult queryPage(Pager page){
	    List<Lzjycgfj> list = dao.query(Lzjycgfj.class, null, page);
	    page.setRecordCount(dao.count(Lzjycgfj.class));
	    return new QueryResult(list, page);
	}
	
	public List<Lzjycgfj> queryByFid(Integer fid){
		return dao.query(Lzjycgfj.class, Cnd.where("fid","=",fid));
	}
}
