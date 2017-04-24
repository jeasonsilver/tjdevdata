package com.free.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*import com.free.hywj.model.Hywjxx;
import com.free.system.model.User;
import com.free.system.model.UserRole;
import com.free.system.service.UserService;*/
import com.google.gson.Gson;

/**
 * 人大代表信息
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml", "classpath:spring-nutzdao.xml" })
public class UserServiceTest {
	
	private static final Logger logger = Logger.getLogger(UserServiceTest.class);
	@Autowired
	private UserService service;
	@Autowired
	private Dao dao;
	
	
	
	@Test
	public void delete() {
		logger.info(service.delete(4));
	}
	@Test
	@Rollback(value=false)
	public void deleteUserRole() {
		User user = dao.fetch(User.class, 1);
		//user = dao.fetchLinks(user, "userRoles");
		dao.clearLinks(user, "userRoles");
	}
	@Test
	public void queryUserRole() {
		User user = dao.fetch(User.class, 3);
		dao.fetchLinks(user, "userRoles");
		logger.info(new Gson().toJson(user));
	}

	@Test
	public void queryUserRole2() {
		Hywjxx hywjxx = dao.fetch(Hywjxx.class,3);
		dao.fetchLinks(hywjxx, "hywjfjs");
		
		logger.info(new Gson().toJson(hywjxx));
	}

	@Test
	public void insertUserRole() {
		User user = new User();
		user.setId(1);
		List<UserRole> userRoles = new ArrayList<UserRole>();
		userRoles.add(new UserRole(1,2));
		userRoles.add(new UserRole(1,3));
		user.setUserRoles(userRoles);
		dao.insertLinks(user,"userRoles");
		
		//logger.info(dao.deleteLinks(user, "userRoles"));
	}
	@Test
	public void insert() {
		User entity = new User();
	
		entity.setDeptid("0");
		entity.setLoginname("2");
		entity.setDbzh("3");
		entity.setXm("4");
		entity.setPassword("5");
		entity.setXb("6");
		entity.setMz("7");
		entity.setCsrq("8");
		entity.setWhcd("9");
		entity.setZzmm("10");
		entity.setDbt("11");
		entity.setWjsdd("12");
		entity.setJszc("13");
		entity.setZy("14");
		entity.setZw("15");
		entity.setDwmc("16");
		entity.setDwdh("17");
		entity.setDwdz("18");
		entity.setDwmb("19");
		entity.setDzyz("20");
		entity.setCz("21");
		entity.setJtzz("22");
		entity.setJtyb("23");
		entity.setJtdh("24");
		entity.setYddh("25");
		entity.setSfdc("26");
		entity.setMemo("27");
		entity.setZp("28");
		entity.setCjr("31");
		entity.setXgr("32");
		entity.setSfqy("3");

		logger.info(service.insert(entity));
	}
	
	@Test
	public void fetch() {
		logger.info(new Gson().toJson(service.fetch(1)));
	}
	
	@Test
	public void query() {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginname", "admin%");
		params.put("sign", "like");
		logger.info(new Gson().toJson(service.query(null)));
	}
	

	@Test
	public void update() {
		User entity = new User();
		entity.setId(1);
		entity.setDeptid("u123");
		entity.setLoginname(null);
		
		entity.setDbzh("u3");
		entity.setXm("u4");
		entity.setPassword("u5");
		entity.setXb("u6");
		entity.setMz("u7");
		entity.setCsrq("u8");
		entity.setWhcd("u9");
		entity.setZzmm("u10");
		entity.setDbt("u11");
		entity.setWjsdd("u12");
		entity.setJszc("u13");
		entity.setZy("u14");
		entity.setZw("u15");
		entity.setDwmc("u16");
		entity.setDwdh("u17");
		entity.setDwdz("u18");
		entity.setDwmb("u19");
		entity.setDzyz("u20");
		entity.setCz("u21");
		entity.setJtzz("u22");
		entity.setJtyb("u23");
		entity.setJtdh("u24");
		entity.setYddh("u25");
		entity.setSfdc("u26");
		entity.setMemo("u27");
		entity.setZp("u28");
		entity.setCjr("u31");
		entity.setXgr("u32");
		entity.setSfqy("u");
		logger.info(new Gson().toJson(service.update(entity)));
	}

	@Test
	public void updateIgnoreNull() {
		User entity = new User();
		entity.setId(2);
		entity.setLoginname(null);
		
		entity.setDeptid(null);
		entity.setDbzh("u3");
		entity.setXm("u4");
		entity.setPassword("u5");
		entity.setXb("u6");
		entity.setMz("u7");
		entity.setCsrq("u8");
		entity.setWhcd("u9");
		entity.setZzmm("u10");
		entity.setDbt("u11");
		entity.setWjsdd("u12");
		entity.setJszc("u13");
		entity.setZy("u14");
		entity.setZw("u15");
		entity.setDwmc("u16");
		entity.setDwdh("u17");
		entity.setDwdz("u18");
		entity.setDwmb("u19");
		entity.setDzyz("u20");
		entity.setCz("u21");
		entity.setJtzz("u22");
		entity.setJtyb("u23");
		entity.setJtdh("u24");
		entity.setYddh("u25");
		entity.setSfdc("u26");
		entity.setMemo("u27");
		entity.setZp("u28");
		entity.setCjr("u31");
		entity.setXgr("u32");
		entity.setSfqy("u");
		logger.info(new Gson().toJson(service.updateIgnoreNull(entity)));
	}
//
//	@Test
//	public void getAll() {
//		List<User> lists = service.selectAll();
//		logger.info(new Gson().toJson(lists));
//	}
//
	@Test
	public void queryPage(){
		Pager pager= new Pager();
		pager.setPageNumber(1);
		pager.setPageSize(10);
		logger.info(new Gson().toJson(service.queryPage(pager)));
	}
	
}
*/