package com.free.dblz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.free.common.Const;
import com.free.common.service.BaseService;
import com.free.common.utils.NewPager;
import com.free.common.utils.TimeUtil;
import com.free.common.utils.UserUtils;
import com.free.dblz.model.Lzjycg;
import com.free.dblz.model.LzjycgContent;
import com.free.system.model.Department;


/**
 * 代表履职--履职信息记录
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */

@Service
public class LzjycgService extends BaseService {
	
	@Autowired
	private Dao dao;

	//删除
	public int delete(Integer id) {
		return dao.deleteWith(dao.fetch(Lzjycg.class,id),"content");
	}

	//插入
	public Lzjycg insert(Lzjycg record) {
		record.setCjsj(TimeUtil.getCurrentTimestamp());
		record.setCjr(UserUtils.getUser().getXm());
		record.setCjrid(UserUtils.getUser().getId());
		
		//工作人员直接审核通过
		if(StringUtils.isBlank(UserUtils.getUser().getRepresentflag()) || "0".equals(UserUtils.getUser().getRepresentflag())){
			//完全是工作人员
			record.setShzt("1");
		}else{
			//即是代表又是工作人员
			if(UserUtils.getUser().getDeptid() != null){
				Department dept = dao.fetch(Department.class,UserUtils.getUser().getDeptid());
				if(!dept.getFid().equals(Const.DEPARTMENT_FID_DBT) && !dept.getId().equals(Const.DEPARTMENT_FID_DBT)){
					record.setShzt("1");
				}
			}
		}
		
		if(record.getContent() == null){
			record.setContent(new LzjycgContent());
		}
		
		return dao.insertWith(record, "content");
	}

	//取单条
	public Lzjycg fetch(Integer id) {
		return dao.fetchLinks(dao.fetch(Lzjycg.class,id), "content");
	}

	//更新非空
	public int updateIgnoreNull(Lzjycg record) {
		return dao.updateIgnoreNull(record);
	}

	//更新
	public void update(Lzjycg record) {
		dao.updateIgnoreNull(record);
		record.getContent().setId(record.getContentid());
		dao.updateIgnoreNull(record.getContent());
	}

	//查询
	public List<Lzjycg> query(Condition c){
		return dao.query(Lzjycg.class,c, null);
	}
	
	//分页查询
	public Map queryPage(NewPager page){
		Criteria cri = getCriteriaFromPage(page);
		
	    List<Lzjycg> list = dao.query(Lzjycg.class, cri, page);
	    page.setRecordCount(dao.count(Lzjycg.class, cri));
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
	    return map;
	}
	
}
