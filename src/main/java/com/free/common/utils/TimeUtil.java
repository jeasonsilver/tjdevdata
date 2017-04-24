package com.free.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 时间处理工具类
 * 
 * @author Administrator
 * 
 */
public class TimeUtil {

	/**
	 * 返回当前日期ID
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getTimeID() throws Exception {
		String id = TimeUtil.getCurYear() + TimeUtil.getCurMonth() + TimeUtil.getCurDay();
		id = id + TimeUtil.getCurHour() + TimeUtil.getCurMin() + TimeUtil.getCurSecond();
		return String.valueOf(id);
	}

	/**
	 * 返回当前年度
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return String.valueOf(year);
	}

	/**
	 * 返回去年
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getLastYear() throws Exception {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR) - 1;
		return String.valueOf(year);
	}

	/**
	 * 返回当前月份
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		String rtn = "";
		if (month < 10) {
			rtn = "0" + String.valueOf(month);
		} else {
			rtn = String.valueOf(month);
		}
		return String.valueOf(rtn);
	}

	/**
	 * 返回当前日期
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurDay() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		String rtn = "";
		if (day < 10) {
			rtn = "0" + String.valueOf(day);
		} else {
			rtn = String.valueOf(day);
		}
		return String.valueOf(rtn);
	}

	/**
	 * 返回当前小时
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurHour() throws Exception {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		String rtn = "";
		if (hour < 10) {
			rtn = "0" + String.valueOf(hour);
		} else {
			rtn = String.valueOf(hour);
		}
		return String.valueOf(rtn);
	}

	/**
	 * 返回当前分钟
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurMin() throws Exception {
		Calendar cal = Calendar.getInstance();
		int minute = cal.get(Calendar.MINUTE);
		String rtn = "";
		if (minute < 10) {
			rtn = "0" + String.valueOf(minute);
		} else {
			rtn = String.valueOf(minute);
		}
		return String.valueOf(rtn);
	}

	/**
	 * 返回当前秒
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurSecond() throws Exception {
		Calendar cal = Calendar.getInstance();
		int second = cal.get(Calendar.SECOND);
		String rtn = "";
		if (second < 10) {
			rtn = "0" + String.valueOf(second);
		} else {
			rtn = String.valueOf(second);
		}
		return String.valueOf(rtn);
	}

	/**
	 * 返回当前的时间戳
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 返回2005-03-06的日期格式
	 * 
	 * @return
	 */
	public static String getStringDate() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(new Date());
	}

	/**
	 * 返回2005-03-06的日期格式
	 * 
	 * @return
	 */
	public static String getStringDate(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return date != null ? f.format(date) : "";
	}

	/**
	 * 当前年前N年的 返回2005-01-01的日期格式
	 * 
	 * @return
	 */
	public static String getDateBefor(int n) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR) - n;
		return (year + "-01-01");
	}
	
	public static String getDateBeforeYear(int n) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String now = f.format(new Date());
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR) - n;
		String date = year + now.substring(4);
		return date;
	}

	public static String getDateAfter(String now, Integer afterDay) {
		try {
			Date d = new SimpleDateFormat("yyyy-MM-dd").parse(now);
			Calendar b = Calendar.getInstance();
			b.setTime(d);
			b.add(Calendar.DATE, afterDay);
			String afterDate = new SimpleDateFormat("yyyy-MM-dd").format((b.getTime()));
			return afterDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 后一个日期减前一个日期
	 * 
	 * @param 前一个日期
	 * @param 后一个日期
	 * @return
	 */
	public static long getDateBetween(String date1, String date2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long daysBetween = -1;
		try {
			Date d1 = sdf.parse(date1);
			Date d2 = sdf.parse(date2);
			daysBetween = (d2.getTime() - d1.getTime()) / (3600 * 24 * 1000) / 365;
		} catch (ParseException e) {
			return -1;// e.printStackTrace();
		}
		return daysBetween;
	}

	/**
	 * 等到当前日期三个月前的时间
	 * 
	 * @return
	 */
	public static String getStringDateBeforMonth(int month) {
		String threeMonths = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(Calendar.MONTH, -month);
			d = c.getTime();
			threeMonths = sdf.format(d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return threeMonths;
	}

	public static String getThreeMonths() {
		String threeMonths = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(Calendar.MONTH, -3);
			d = c.getTime();
			threeMonths = sdf.format(d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return threeMonths;
	}
	public static String getTimeTimestamp() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/***
	 * 按计生委统计方法，取年度
	 * 需要spring容器，lastDayOfYearStr需从数据库中获取
	 */
	public static int getJswYear() {
		Calendar c = Calendar.getInstance();
		if (c.get(Calendar.MONTH) > 8) {
			return c.get(Calendar.YEAR) + 1;
		} else {
			return c.get(Calendar.YEAR);
		}
				
				// 不从数据库里取
//		SystemConfigService systemConfigService = SpringContextHolder.getBean(SystemConfigService.class);
//		String lastDayOfYearStr = systemConfigService.getConfigValue("LAST_DAY_OF_YEAR");
//		Date lastDayOfYearDate = strToDate(lastDayOfYearStr);
//		return getYear(lastDayOfYearDate);
	}

	public static int getJswYear(String date) {
		Calendar c = Calendar.getInstance();
		c.setTime(strToDate(date));
		if (c.get(Calendar.MONTH) > 8) {
			return c.get(Calendar.YEAR) + 1;
		} else {
			return c.get(Calendar.YEAR);
		}
	}

	/**
	 * 生成年份列表
	 */
	public static Map<String, Integer> getYearList() {
		Map<String, Integer> yearList = new LinkedHashMap<String, Integer>();
		Calendar c = Calendar.getInstance();
		int currentYear = c.get(Calendar.YEAR);
		for (int startYear = currentYear - 10, endYear = currentYear; startYear <= endYear; startYear++) {
			yearList.put(startYear + "", startYear);
		}
		return yearList;
	}

	public static void main(String[] args) {
		System.out.print(TimeUtil.getCurYear("2013-03-32"));
	}

	public static Timestamp str2Timestamp(String string) {
		if (!string.substring(string.length() - 9, string.length()).equals(" 00:00:00")) {
			string = string + " 00:00:00";
		}
		return Timestamp.valueOf(string);
	}

	/**
	 * 取当前时间季度
	 */
	public static Integer getCurQuarter() {
		Integer month = Integer.valueOf(getCurMonth());
		return  getCurQuarter(month);
	}
	public static Integer getCurYear(String dateStr) {
		Date date = strToDate( dateStr); 
		return  date.getYear();
	}
	public static Integer getCurQuarter(String dateStr) {
		Date date = strToDate( dateStr); 
		return  getCurQuarter(date.getMonth()+1);
	}
	/**
	 * 取当前时间季度
	 */
	public static Integer getCurQuarter(int month) {
		int quarter = 1;
		if (month >= 1 && month <= 3) {
			quarter = 1;
		}
		if (month >= 4 && month <= 6) {
			quarter = 2;
		}
		if (month >= 7 && month <= 9) {
			quarter = 3;
		}
		if (month >= 10 && month <= 12) {
			quarter = 4;
		}
		return quarter;
	}
	
	public static Date strToDate(String dateStr) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date date = formatter.parse(dateStr, pos);
		return date;
	}

	/**
	 * 获取日期的年份
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 返回当前月份
	 * 
	 * @return
	 * @throws Exception
	 */
	public static int getMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		return month;

	}

	/**
	 * 将一个日期时间转化为午夜时间，即 23:59:59秒
	 * @param date
	 */
	public static Date dateToMidnight(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);

		return cal.getTime();
	}
	
}
