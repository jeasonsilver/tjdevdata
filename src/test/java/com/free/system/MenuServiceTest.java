package com.free.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.free.common.service.PageQuery;
import com.free.system.model.Menu;
import com.free.system.service.MenuService;
import com.google.gson.Gson;

/**
 * 菜单信息
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class MenuServiceTest {
	
	private static final Logger logger = Logger.getLogger(MenuServiceTest.class);
	@Autowired
	private MenuService service;

	@Test
	public void deleteByPrimaryKey() {
		logger.info(service.deleteByPrimaryKey(1));
	}
	
	@Test
	public void insert() {
		Menu entity = new Menu();
		
			entity.setMenuname("1");
			entity.setUrl("2");
			entity.setPid("3");
			entity.setSfxs("4");
			entity.setSx("5");
	
		logger.info(service.insert(entity));
	}
	
	@Test
	public void selectByPrimaryKey() {
		logger.info(new Gson().toJson(service.selectByPrimaryKey(1)));
	}

	@Test
	public void selectAllByCondition() {
		Map<String, Object> params = new HashMap();
		params.put("pid", 0);
		logger.info(new Gson().toJson(service.selectAllByCondition(params)));
	}
	
	@Test
	public void selectEntityByCondition() {

		Map<String, Object> params = new HashMap<String, Object>();
		//params.put("loginname", "test");
		logger.info(new Gson().toJson(service.selectEntityByCondition(params)));
	}
	

	@Test
	public void updateByPrimaryKeySelective() {
		Menu entity = new Menu();
		entity.setId(1l);
		entity.setMenuname("u1");
		entity.setUrl("u2");
		entity.setPid("u3");
		entity.setSfxs("u4");
		entity.setSx("u5");
		logger.info(new Gson().toJson(service.updateByPrimaryKeySelective(entity)));
	}

	@Test
	public void updateByPrimaryKey() {
		Menu entity = new Menu();
		entity.setId(1l);
		entity.setMenuname("u1");
		entity.setUrl("u2");
		entity.setPid("u3");
		entity.setSfxs("u4");
		entity.setSx("u5");
		logger.info(new Gson().toJson(service.updateByPrimaryKey(entity)));
	}

	@Test
	public void getAll() {
		List<Menu> lists = service.selectAll();
		logger.info(new Gson().toJson(lists));
	}
	
	@Test
	public void selectByCondition(){
		PageQuery query = new PageQuery();
		query.setPageNumber(1);
		query.setPageSize(10);
		logger.info(new Gson().toJson(service.selectByCondition(query)));
	}
	
}
*/