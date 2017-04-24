package com.free.common.tag;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.json.Json;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.support.json.JSONWriter;
import com.free.common.utils.CacheUtils;
import com.free.common.utils.TimeUtil;
import com.free.common.utils.spring.SpringContextHolder;
import com.free.system.service.CodeDicService;
//import com.free.xxpx.model.Magazine;


public class ELFuncUtil {
	
	private static Map<String, Map> getAllCodes(){
		Map<String, Map> map = (Map<String, Map>) CacheUtils.get("codes");
		if (map==null){
			CodeDicService codeDicService = (CodeDicService)SpringContextHolder.getBean("codeDicService"); //即可取到bean
			map = codeDicService.getAllMap(); 
			CacheUtils.put("codes", map);
		}
		return map;
	}
	 // 根据code和section 得到codename
	 public static String getCodeName(String code,String sectionname) {
		Map<String, Map> maps = getAllCodes();
		Map<String, String> map = maps.get(sectionname);
		Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry key = (Entry) it.next();
				if (key.getKey().equals(code)){
				    return (String)key.getValue();
				}
			}
		return null;	
	 }	 
	 // 用于页面，拼成json供前台查询
	 public static String getCodes(String sectionname) {
		Map<String, Map> maps = getAllCodes();
		Map<String, String> map = maps.get(sectionname);
		StringBuffer sb = new StringBuffer();

		sb.append("{");
			if (map!=null){
			 Iterator it = map.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry key = (Entry) it.next();
					sb.append("'"+key.getKey()+"':'"+(String)key.getValue()+"',");
				}
				
			}
		sb.append("}");
		return sb.toString();	
	 }	
	 
	 
	 // 用于页面，拼成json供前台查询
	/* public static String getMagazines() {
		Dao dao = (Dao)SpringContextHolder.getBean("dao"); 
		List<Magazine> list = dao.query(Magazine.class, null);
		StringBuffer sb = new StringBuffer();

		sb.append("{");
			if (list!=null){
				 for (Magazine mag : list) {
					sb.append("'"+mag.getId()+"':'"+mag.getName()+"',");
				 }
			}
		sb.append("}");
		return sb.toString();	
	 }	*/
	 
	 // 用于页面，拼成json供前台查询
	 public static String getDate() {
		return TimeUtil.getStringDate();	
	 }
	
	 // 得到会议文件中最新的届次、会次，返回Json {'jc':'16','hc':'01'}
	 public static String getLatestJcAndHc(String wjsslm) {
		 Dao dao = (Dao)SpringContextHolder.getBean("dao");
		 String str = "";
		 Sql sqls = Sqls.create("select max(jc) jc,max(hc) hc from t_hywj_hywjxx where wjsslm=@lm "
		 		+ "GROUP BY jc ORDER BY -jc  LIMIT 1");
		 sqls.params().set("lm", wjsslm);
		 sqls.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				Map m = new HashMap();
				while(rs.next()) {
					m.put("jc", rs.getString("jc"));
					m.put("hc", rs.getString("hc"));
				}
				return m;
			}
		});
		 dao.execute(sqls);
		 str = JSONUtils.toJSONString(sqls.getResult());
		 return str;
	 }
	 
	 // 查询议案建议中最新的届次会次，返回json {'jc':'16','hc':'03'}
	 public static String getLatestYajyJcHc(String type) {
		 Dao dao = (Dao)SpringContextHolder.getBean("dao");
		 String result = "";
		 String sql = "SELECT MAX(jc) jc,MAX(hc) hc from t_yajy_proposal GROUP BY jc ORDER BY -jc LIMIT 1";
		if ("prop".equals(type))
			sql = "SELECT MAX(jc) jc,MAX(hc) hc from t_yajy_proposal GROUP BY jc ORDER BY -jc LIMIT 1";
		else if ("news".equals(type))
			sql = "SELECT MAX(jc) jc,MAX(hc) hc from t_yajy_newspaper GROUP BY jc ORDER BY -jc LIMIT 1";
		else if ("sugg".equals(type))
			sql = "SELECT MAX(jc) jc,MAX(hc) hc from t_yajy_suggestion GROUP BY jc ORDER BY -jc LIMIT 1";
		else if ("zddb".equals(type))
			sql = "SELECT MAX(jc) jc,MAX(hc) hc from t_yajy_suggestion where iszddb='1' GROUP BY jc ORDER BY -jc LIMIT 1";
		Sql sqls = Sqls.create(sql);
		 sqls.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				Map m = new HashMap();
				while(rs.next()) {
					m.put("jc", rs.getString("jc"));
					m.put("hc", rs.getString("hc"));
				}
				return m;
			}
		});
		 dao.execute(sqls);
		 result = JSONUtils.toJSONString(sqls.getResult());
		return result;
	 }
}
