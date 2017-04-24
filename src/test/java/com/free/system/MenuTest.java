package com.free.system;


import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*import com.free.system.model.Menu;
import com.free.system.service.MenuService;*/
import com.google.gson.Gson;

/**
 * 测试类
 * @author mf
 *
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class MenuTest {

	private static final Logger logger = Logger.getLogger(MenuTest.class);
	
	private MenuService menuService;
	@Autowired
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	@Test
	public void getAllTest() {
		List<Menu> articles = menuService.getAll();
		System.out.println("=================");
		System.out.println(new Gson().toJson(articles));
		logger.info(new Gson().toJson(articles));
	}
}
*/