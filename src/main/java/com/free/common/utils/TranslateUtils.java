package com.free.common.utils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.free.common.utils.spring.SpringContextHolder;
import com.free.system.service.CodeDicService;

public class TranslateUtils {
	
	/**
	 * 列表数据转码
	 * @param <T>
	 * @param page
	 * @param codeAttributes
	 * 			label是列名，value是编码英文名称
	 * @throws Exception
	 */
	public static <T> void retrievePageList(List<T> result,Map<String,String> codeAttributes) throws Exception{
		
		Set<Entry<String,String>> entrySet = codeAttributes.entrySet(); //需要转码的列
		for(Entry<String,String> entry:entrySet){
			//首先提取编码值
			CodeDicService codeDicService = SpringContextHolder.getBean("codeDicService");
			Map<String, String> map = codeDicService.getRealCodeMapBySectionName(entry.getValue()); //将编码值取出

			//提取数据
			for(T t:result){
				String value=BeanUtils.getProperty(t, entry.getKey());
				//转换编码
				value = map.get(value);
				BeanUtils.setProperty(t, entry.getKey(), value);
			}
		}
	}
	
	/**
	 * 翻译表单数据
	 * @param <T>
	 * @param form
	 * @param codeAttributes
	 * 		  label是列名，value是编码英文名称
	 * @throws Exception
	 */
	public static <T> void retrieveFormCode(T t,Map<String,String> codeAttributes) throws Exception{
		Set<Entry<String,String>> entrySet = codeAttributes.entrySet(); //需要转码的列
		for(Entry<String,String> entry:entrySet){ //迭代转码列
			//将列的数值取出，然后转码
			String value=BeanUtils.getProperty(t, entry.getKey());
			CodeDicService codeDicService = SpringContextHolder.getBean("codeDicService");
			Map<String, String> map = codeDicService.getRealCodeMapBySectionName(entry.getValue()); //将编码值取出
			value = map.get(value);
			BeanUtils.setProperty(t, entry.getKey(), value);
		}
	}


}
