package com.free.system.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.free.common.service.BaseService;
import com.free.common.utils.NewPager;
import com.free.system.model.Code;
import com.free.system.model.Role;

@Service
public class CodeDicService extends BaseService{
	
	@Autowired
	private Dao dao;
	
	public Code fetch(Integer id) {
		return dao.fetch(Code.class,id);
	}
	public Code insert(Code record) {
		return dao.insert(record);
	}
	public int delete(Integer id) {
		return dao.delete(Code.class,id);
	}
	public int updateIgnoreNull(Code record) {
		return dao.updateIgnoreNull(record);
	}
	public  Map<String, String> getRealCodeMapBySection(String section) {
		List<Code> list = dao.query(Code.class, Cnd.where("section","=",section));
		Map<String, String> codeMap = new LinkedHashMap<String, String>();
		for(Code cde:list){
			codeMap.put(cde.getCode(), cde.getCodename());
		}
		return codeMap;
	}
	
	public  Map<String, String> getRealCodeMapBySectionName(String sectionname) {
		List<Code> list = dao.query(Code.class, Cnd.where("sectionname","=",sectionname).asc("code"));
		Map<String, String> codeMap = new LinkedHashMap<String, String>();
		for(Code cde:list){
			codeMap.put(cde.getCode(), cde.getCodename());
		}
		return codeMap;
	}
	
	/**
	 * 将code表中的内容map按分类放放总maps中
	 * @return
	 */
	public  Map<String,Map> getAllMap() {
		List<Code> sectionnames = dao.query(Code.class, Cnd.where(null).groupBy("sectionname"));
		List<Code> list = dao.query(Code.class, Cnd.where(null).asc("sectionname").asc("code"));
		Map maps = new HashMap();
		for (Code sectionname : sectionnames) {
			Map map = new LinkedHashMap();
			for (Code code : list) {
				if (code.getSectionname().equals(sectionname.getSectionname())){
					map.put(code.getCode(), code.getCodename());
				}
			}
			maps.put(sectionname.getSectionname(),map);
		}
		return maps;
	}
	public Map<String,Object> queryPage(NewPager page){
		Criteria cri = getCriteriaFromPage(page);
		
	    List<Code> list = dao.query(Code.class, cri, page);
	    page.setRecordCount(dao.count(Code.class, cri));
	    
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
	    return map;
	}
}
