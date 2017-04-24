package com.free.system.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.free.common.service.BaseService;

/**
 * 报表统计Service
 * @ClassName: ModularService 
 * @Description: TODO
 * @Company 天津南大通用数据技术股份有限公司
 * @author 庞 
 * @date Apr 7, 2015 11:52:41 AM 
 * @version V1.0
 *
 */
@Service
public class ModularService extends BaseService {
	
	Logger logger = Logger.getLogger(ModularService.class);
	
	@Autowired
	private Dao dao;
	
	/*根据条件统计数量*/
	public int count(Class<?> T,String param,String... quarter) {
		return dao.count(T,Cnd.wrap((param.equals("") ? "" : param+" and ") + " cjsj BETWEEN STR_TO_DATE('" + quarter[0].toString() + " 00:00:00','%Y-%m-%d %H:%i:%s') AND STR_TO_DATE('" + quarter[1].toString() +" 23:59:59','%Y-%m-%d %H:%i:%s')"));
	}
	
	/**
	 * 
	 * @param T
	 * @param param
	 * @param dateSection
	 * @return
	 */
	public int countDept(Class<?> T,String param,String... dateSection){
		String deptCount = getSQL(T,param,dateSection);
		return dao.count(T,Cnd.wrap(deptCount));
	}
	
	/**
	 * 根据对象类型组装SQL
	 * @param T
	 * @param param
	 * @param dateSection
	 * @return
	 */
	public String getSQL(Class<?> T , String param ,String... dateSection){
		Field[] field = T.getDeclaredFields();
		for(Field f : field){
			f.getName();
		}
		String deptSql = "SELECT DISTINCT A.ID,A.DEPTNAME FROM T_SYSTEM_DEPARTMENT A,T_SYSTEM_USER_DEPT B WHERE A.ID = B.DEPTID AND fid <> 0 ORDER BY A.ID";
		String modularSql = "SELECT E.DEPTID,COUNT(C.ID) AS NUM " +
							" FROM "+getTableName(T) +" C,T_SYSTEM_USER D,T_SYSTEM_USER_DEPT E "+
							" WHERE 1 = 1 "+
							" AND " + param + 
							" AND C."+getCreateIdAttr(T)+" = D.ID "+
							" AND D.ID = E.USERID  "+
							" C.CJSJ BETWEEN STR_TO_DATE('" + dateSection[0].toString() + " 00:00:00','%Y-%m-%d %H:%i:%s') AND STR_TO_DATE('" + dateSection[1].toString() +" 23:59:59','%Y-%m-%d %H:%i:%s') "+
							" GROUP BY E.DEPTID ";
		String deptCount = " SELECT M.ID,N.NUM FROM (" + deptSql + ") M LEFT JOIN (" + modularSql +") N ON(M.ID = N.DEPTID)";
		return deptCount;
	}
	
	/**
	 * 获取数据库对象的创建人
	 * 查找createId,如果没有查找cjrid,如果还没有输出错误日志,并返回null
	 * @param T
	 * @return
	 */
	private String getCreateIdAttr(Class<?> T){
		String createId = "createid";
		try {
			T.getDeclaredField(createId);
			return createId;
		} catch (Exception e) {
			try {
				createId = "cjrid";
				T.getDeclaredField(createId);
				return createId;
			} catch (Exception e1) {
				/*表结构错误*/
				logger.error(T.getName() + "没有创建人");
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 获取表名
	 * @param T
	 * @return
	 */
	private String getTableName(Class<?> T){
		String tableName = "";
		Annotation[] annotation = T.getClass().getAnnotations();
		for(Annotation ann : annotation){
			Table t = (Table)T.getClass().getAnnotation(ann.annotationType());
			tableName = t.value();
		}
		return tableName;
	}
	
}
