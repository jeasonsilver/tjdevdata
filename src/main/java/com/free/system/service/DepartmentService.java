package com.free.system.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.free.common.service.BaseService;
import com.free.common.utils.NewPager;
import com.free.system.model.Department;

/**
 * 工作部门
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */

@Service
public class DepartmentService extends BaseService {
	
	@Autowired
	private Dao dao;

	//删除
	public int delete(Integer id) {
		return dao.delete(Department.class,id);
	}

	//插入
	public Department insert(Department record) {
		return dao.insert(record);
	}

	//取单条
	public Department fetch(Integer id) {
		return dao.fetch(Department.class,id);
	}

	//更新非空
	public int updateIgnoreNull(Department record) {
		return dao.updateIgnoreNull(record);
	}

	//更新
	public int update(Department record) {
		return dao.update(record);
	}

	//查询
	public List<Department> query(Condition c){
		if(c == null){
			c = Cnd.orderBy().asc("IF(ISNULL(ordercol),100000000,ordercol)").asc("id");
		}
		return dao.query(Department.class,c, null);
	}
	
	//分页查询
	public QueryResult queryPage(NewPager page){
		page.setOrder("IF(ISNULL(ordercol),100000000,ordercol)");
		page.setOrderBy(page.ASC);
		Criteria cri = getCriteriaFromPage(page);
		cri.getOrderBy().asc("IF(ISNULL(ordercol),100000000,ordercol)");
	    List<Department> list = dao.query(Department.class, cri, page);
	    page.setRecordCount(dao.count(Department.class, cri));
	    return new QueryResult(list, page);
	}
	
}
