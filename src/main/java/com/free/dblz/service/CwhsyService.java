package com.free.dblz.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.free.common.service.BaseService;
import com.free.common.utils.NewPager;
import com.free.dblz.model.Cwhsy;
import com.free.dblz.model.CwhsyContent;


/**
 * 常委会审议
 * @ClassName: CwhsyService 
 * @Description: TODO
 * @Company 天津南大通用数据技术股份有限公司
 * @author 庞 
 * @date Apr 2, 2015 4:20:24 PM 
 * @version V1.0
 *
 */

@Service
public class CwhsyService extends BaseService {
	
	@Autowired
	private Dao dao;

	//删除
	public int delete(Integer id) {
		dao.clear(CwhsyContent.class,Cnd.where("fid","=",id));
		return dao.delete(Cwhsy.class,id);
	}

	//插入
	/*public void insert(Cwhsy record) {
		//先检查排序
		if(record.getOrdercol() != null){
			//判断之前是否存在
			List<Zyjhwj> list= dao.query(Zyjhwj.class, Cnd.where("ordercol","=",record.getOrdercol()));
			if(list.size() > 0){
				Sql sqls = Sqls.create("update t_dblz_cwhsy set ordercol = ordercol + 1 where ordercol >= "+record.getOrdercol());
				dao.execute(sqls);
			}
			
		}
		record = (Cwhsy)super.insert(record);
		CwhsyContent content = record.getContent();
		content.setFid(record.getId());
		dao.insert(content);
	}*/

	//取单条
	public Cwhsy fetch(Integer id) {
		Cwhsy cwhsy = dao.fetch(Cwhsy.class,id);
		CwhsyContent content = dao.fetch(CwhsyContent.class, Cnd.where("fid","=",id));
		cwhsy.setContent(content);
		return cwhsy;
	}

	//更新非空
	public int updateIgnoreNull(Cwhsy record) {
		return dao.updateIgnoreNull(record);
	}

	//更新
	public int update(Cwhsy record) {
		
		//先检查排序
		if(record.getOrdercol() != null){
			//提取排序号
			Cwhsy old = dao.fetch(Cwhsy.class,record.getId());
			if(old.getOrdercol() == null || !old.getOrdercol().equals(record.getOrdercol())){//新旧排序号不一致
				if(old.getOrdercol() == null){//如果之前的号为空就和新增一样
					Sql sqls = Sqls.create("update t_dblz_cwhsy set ordercol = ordercol + 1 where ordercol >= "+record.getOrdercol());
					dao.execute(sqls);
				}else{
					
					if(old.getOrdercol() > record.getOrdercol()){//旧号大于新号--升
						Sql sqls = Sqls.create("update t_dblz_cwhsy set ordercol = ordercol + 1 where ordercol BETWEEN "+record.getOrdercol()+" and "+old.getOrdercol());
						dao.execute(sqls);
					}else{//信号大于旧号--降
						Sql sqls = Sqls.create("update t_dblz_cwhsy set ordercol = ordercol - 1 where ordercol BETWEEN "+old.getOrdercol()+" and "+record.getOrdercol());
						dao.execute(sqls);
					}
					
					
				}
				
			}
			
		}
		
		
		CwhsyContent content = record.getContent();
		content.setFid(record.getId());
		if(content.getId() != null){
			dao.update(content);
		}else{
			dao.insert(content);
		}
		
		return dao.updateIgnoreNull(record);
	}

	//查询
	public List<Cwhsy> query(Condition c){
		return dao.query(Cwhsy.class,c, null);
	}
	
	//分页查询
	public Map queryPage(NewPager page){
		page.setOrder("IF(ISNULL(ordercol),100000000,ordercol)");
		page.setOrderBy(page.ASC);
		Criteria cri = getCriteriaFromPage(page);
		cri.getOrderBy().asc("IF(ISNULL(ordercol),100000000,ordercol)").desc("cjsj");
	    List<Cwhsy> list = dao.query(Cwhsy.class, cri, page);
	    page.setRecordCount(dao.count(Cwhsy.class, cri));
	    
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
		
	    return map;
	}
	/**
	 * 提取当前最大序号
	 * @return
	 */
	public Integer getMaxOrderCol(){
		Sql sql = Sqls.create("select max(ordercol) cou from t_dblz_cwhsy");
		sql.setCallback(new SqlCallback() {
	        public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
	            List<String> list = new LinkedList<String>();
	            while (rs.next())
	                list.add(rs.getString("cou"));
	            return list;
	        }
	    });
		dao.execute(sql);
		List<String> list = sql.getList(String.class);
		if(list.get(0) != null){
			return Integer.valueOf(list.get(0));
		}else{
			return Integer.valueOf(0);
		}
	}
	
}
