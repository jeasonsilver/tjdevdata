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

/*import com.free.common.service.PageQuery;
import com.free.system.model.User;
import com.free.system.service.UserService;*/
import com.google.gson.Gson;

/**
 * 测试类
 * @author mf
 *
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml", "classpath:spring-nutzdao.xml" })
public class UserTest2 {

	private static final Logger logger = Logger.getLogger(UserTest2.class);
	@Autowired
	private UserService service;

	@Test
	public void creat() {
		service.create();
	}

	@Test
	public void findByCondition() {
		PageQuery query = new PageQuery();
		query.setPageNumber(1);
		query.setPageSize(3);
		Map<String, Object> params = new HashMap<String, Object>();
		query.setParams(params);
		
		logger.info(new Gson().toJson(service.selectByCondition(query)));
	}
	@Test
	public void deleteByPrimaryKey() {
		logger.info(service.deleteByPrimaryKey(1));
	}

	@Test
	public void insert() {
		User user = new User();
		//user.setId(1);
		user.setLoginname("test1");
		user.setDzyz("test@163.com");
		user.setXm("测试人员");
		user.setPassword("测试人员");
		user.setYddh("13912345678");
		logger.info(service.insert(user));
	}

	@Test
	public void selectByPrimaryKey() {
		logger.info(new Gson().toJson(service.selectByPrimaryKey(1)));
	}
	@Test
	public void findEntityByCondition() {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginname", "test");
		logger.info(new Gson().toJson(service.selectEntityByCondition(params)));
	}
	@Test
	public void updateByPrimaryKeySelective() {
		User user = new User();
		user.setId(1);
		user.setXm("测试人员2");
		user.setLoginname(null);
		logger.info(new Gson().toJson(service.updateByPrimaryKeySelective(user)));
	}
	@Test
	public void updateByPrimaryKey() {
		User user = new User();
		user.setId(1);
		user.setXm("测试人员3");
		user.setLoginname(null);//只要不设值，全为空
		logger.info(new Gson().toJson(service.updateByPrimaryKey(user)));
	}
	@Test
	public void getAll() {
		List<User> users = service.selectAll();
		logger.info(new Gson().toJson(users));
	}
}
*/