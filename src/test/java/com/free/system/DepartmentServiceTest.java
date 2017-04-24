package com.free.system;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.free.common.utils.NewPager;
import com.free.common.utils.SearchFilter;
import com.free.system.model.Department;
import com.free.system.service.DepartmentService;
import com.google.gson.Gson;

/**
 * 工作部门
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml", "classpath:spring-nutzdao.xml" })
public class DepartmentServiceTest {
	
	private static final Logger logger = Logger.getLogger(DepartmentServiceTest.class);
	@Autowired
	private DepartmentService service;

	@Test
	public void delete() {
		logger.info(service.delete(1));
	}
	
	@Test
	public void insert() {
		Department entity = new Department();
		
			entity.setDeptname("1");
			entity.setDeptphone("2");
			entity.setDeptadress("3");
			entity.setDeptfzr("4");
			entity.setDeptlxr("5");
			entity.setFid(0l);
	
		logger.info(service.insert(entity));
	}
	
	@Test
	public void fetch() {
		logger.info(new Gson().toJson(service.fetch(1)));
	}
	
	@Test
	public void query() {

		logger.info(new Gson().toJson(service.query(null)));
	}
	

	@Test
	public void updateIgnoreNull() {
		Department entity = new Department();
		entity.setId(1l);
		entity.setDeptname("u1");
		entity.setDeptphone("u2");
		entity.setDeptadress("u3");
		entity.setDeptfzr("u4");
		entity.setDeptlxr("u5");
		entity.setFid(6l);
		logger.info(new Gson().toJson(service.updateIgnoreNull(entity)));
	}

	@Test
	public void update() {
		Department entity = new Department();
		entity.setId(1l);
		entity.setDeptname("u1");
		entity.setDeptphone("u2");
		entity.setDeptadress("u3");
		entity.setDeptfzr("u4");
		entity.setDeptlxr("u5");
		entity.setFid(6l);
		logger.info(new Gson().toJson(service.update(entity)));
	}

	@Test
	public void selectByCondition(){
		Map<String, SearchFilter> filters = new HashMap<>();
		
		NewPager pager = new NewPager();
		pager.setPageNumber(1);
		pager.setPageSize(10);
		pager.setFilters(filters);
		
		logger.info(new Gson().toJson(service.queryPage(pager)));
	}
	
}
