package com.ssm.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {
	static Map<String, String> map;//中文时间格式
	static {
		map = new HashMap<String, String>();
		map.put("yyyy-MM-dd HH:mm:ss", "(\\d{1,4}[-]\\d{1,2}[-]\\d{1,2} \\d{1,2}[:]\\d{1,2}[:]\\d{1,2})");
		map.put("yyyy/MM/dd HH:mm:ss", "(\\d{1,4}[/]\\d{1,2}[/]\\d{1,2} \\d{1,2}[:]\\d{1,2}[:]\\d{1,2})");
		map.put("yyyy MM dd HH:mm:ss", "(\\d{1,4}[\\s]\\d{1,2}[\\s]\\d{1,2} \\d{1,2}[:]\\d{1,2}[:]\\d{1,2})");
		map.put("yyyy MM dd HH mm ss", "(\\d{1,4}[\\s]\\d{1,2}[\\s]\\d{1,2} \\d{1,2}[\\s]\\d{1,2}[\\s]\\d{1,2})");
		//英文时间格式
		map.put("dd/MMM/yyyy:HH:mm:ss", "(\\d{1,2}[/][a-zA-Z]{1,3}[/]\\d{1,4}[:]\\d{1,2}[:]\\d{1,2}[:]\\d{1,2})");
		map.put("MMM dd yyyy HH:mm:ss", "([a-zA-Z]{1,3}[\\s]\\d{1,2}[\\s]\\d{1,4}[\\s]\\d{1,2}[:]\\d{1,2}[:]\\d{1,2})");
		map.put("MMM dd HH:mm:ss yyyy", "([a-zA-Z]{1,3}[\\s]\\d{1,2}[\\s]\\d{1,2}[:]\\d{1,2}[:]\\d{1,2}[\\s]\\d{1,4})");
		map.put("MMM dd HH:mm:ss yyyy", "([a-zA-Z]{1,3}[\\s]\\d{1,2}[\\s]\\d{1,2}[:]\\d{1,2}[:]\\d{1,2}[\\s]\\d{1,4})");
//		时间格式: “Fri Aug 28 18:08:30 CST 2015”， 模式: “EEE MMM d HH:mm:ss ‘CST’ yyyy” 
//		SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz") 星期五 2003.05.30 at 01:45:32 下午 CST
//		Fri Nov 11 00:00:00 CST 1222 
	}

	/**
	 * 查看 content中有几个str
	 * 
	 * @param content
	 * @param str
	 * @return
	 */
	public static int findStringNumber(String content, String str) {
		int cnt = 0;
		int offset = 0;
		while ((offset = content.indexOf(str, offset)) != -1) {
			offset = offset + str.length();
			cnt++;
		}
		return cnt;
	}

	/**
	 * 
	 * @param value1
	 *            原始值
	 * @param value2
	 *            当value1为null时候的值
	 * @return
	 */
	public static String getString(Object value1, Object value2) {

		if (value1 == null || String.valueOf(value1).equals("")) {
			return String.valueOf(value2);
		}
		return String.valueOf(value1);
	}

	/**
	 * 去掉字符串数组中的空元素
	 * 
	 * @param s1
	 * @return
	 */
	public static String[] deleteEmptyElem(String[] s1) {

		if (s1 == null) {
			return new String[] {};
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s1.length; i++) {
			if ("".equals(s1[i])) {
				continue;
			}
			sb.append(s1[i]);
			if (i != s1.length - 1) {
				sb.append(";");
			}
		}
		s1 = sb.toString().split(";");
		return s1;

	}

	/**
	 * 判断处置单位相应级别
	 * 
	 * @param areacode
	 * @return
	 */
	public static String areacodeProcess(String areacode) {
		for (int i = areacode.length() - 1; i >= 0; i--) {
			if (!areacode.substring(i, i + 1).equals("0")) {
				areacode = areacode.substring(0, i + 1);
				break;
			}
		}
		// if ((areacode.length() == 3) || (areacode.length() == 5) ||
		// (areacode.length() == 8) || (areacode.length() == 11)) {
		// areacode = areacode + "0";
		// }
		// if ((areacode.length() == 7) || (areacode.length() == 10)) {
		// areacode = areacode + "00";
		// }
		return areacode;
	}

	/**
	 * 去掉ip地址中的‘.’ 
	 * @param parentString
	 * @return
	 */
	public static String ipToString (String parentString) {
		String IPString = parentString.replace(".", "");
		return IPString;

	}
	
	/**
	 * 字符串中筛选出ip(一个字符串中只能筛选出一条ip)
	 * 
	 * @param parentString
	 * @return
	 */
	public static String getIp(String parentString) {
		String regexString = ".*(\\d{3}(\\.\\d{1,3}){3}).*";
		String IPString = parentString.replaceAll(regexString, "$1");
		return IPString;

	}

	/**
	 * 查询出字符串中所有ip
	 * 
	 * @param param
	 * @return
	 */
	public static List<String> getIps(String param) {
		List<String> result = new ArrayList<String>();
		String pa = "(\\b(([1][0-9][0-9])|([2][0-4][0-9])|([2][5][0-5])|([1-9][0-9])|([0-9]))\\."
    			+ "(([1][0-9][0-9])|([2][0-4][0-9])|([2][5][0-5])|([1-9][0-9])|([0-9]))\\."
    			+ "(([1][0-9][0-9])|([2][0-4][0-9])|([2][5][0-5])|([1-9][0-9])|([0-9]))\\."
    			+ "(([1][0-9][0-9])|([2][0-4][0-9])|([2][5][0-5])|([1-9][0-9])|([0-9]))\\b)";
		Matcher m = Pattern.compile(pa).matcher(param);
		while (m.find()) {
			result.add(m.group(1));
		}
		return result;
	}

	/**
	 * 能匹配出yyyy-MM-dd HH:mm:ss和 yyyy/MM/dd HH:mm:ss 和yyyy MM dd HH:mm:ss 和 yyyy MM dd HH mm ss
	 * 
	 * @param text
	 * @return
	 */
	public static List<Date> getTime(String text) {
		String dateStr = text.replaceAll("\r?\n", " ");
		dateStr = dateStr.replaceAll("\\s+", " ");
		List<Date> result = new ArrayList<Date>();

		try {

			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();// 日期格式
				String value = entry.getValue();// 正则表达式

				List<String> matches = new ArrayList<String>();
				Pattern p = Pattern.compile(value, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

				Matcher matcher = p.matcher(dateStr);
				while (matcher.find() && matcher.groupCount() >= 1) {

					for (int i = 1; i <= matcher.groupCount(); i++) {
						String temp = matcher.group(i);
						matches.add(temp);
					}
				}

				if (matches.size() > 0) {
					for (int i = 0; i < matches.size(); i++) {
						SimpleDateFormat sDateFormat = new SimpleDateFormat(key);
						if(key.contains("MMM")){
							sDateFormat = new SimpleDateFormat(key,Locale.ENGLISH);
						}
						//暂定时间类型为字符串 中英文时间无法区分
						Date date = sDateFormat.parse(((String) matches.get(i)).trim());
						result.add(date);
					}
				}

			}
			

		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
	
/**
 * 匹配日志中的日期 时间 支取一次
 * @param text
 * @return
 */
	public static Date getfirstTime(String text) {
		String dateStr = text.replaceAll("\r?\n", " ");
		dateStr = dateStr.replaceAll("\\s+", " ");
		String timeStr="";
		Date time=null;
		try {

			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();// 日期格式
				String value = entry.getValue();// 正则表达式

//				List<String> matches = new ArrayList<String>();
				Pattern p = Pattern.compile(value, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
				SimpleDateFormat sdf=new SimpleDateFormat(key);
				if(key.contains("MMM")){
					sdf = new SimpleDateFormat(key,Locale.ENGLISH);
				}
				Matcher matcher = p.matcher(dateStr);
				while (matcher.find() && matcher.groupCount() >= 1) {
					//获取第一个时间
					timeStr = matcher.group(1);
					time=sdf.parse(timeStr);
//					for (int i = 1; i <= matcher.groupCount(); i++) {
//						timeStr = matcher.group(i);
//						time=sdf.parse(timeStr);
//					}
				}

				

			}
			return time;
		} catch (Exception e) {
			e.printStackTrace();
			return time;
		}
	}
	
	public static String getfirstStrTime(String text) {
		SimpleDateFormat sdfDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = text.replaceAll("\r?\n", " ");
		dateStr = dateStr.replaceAll("\\s+", " ");
		String timeStr="";
		try {

			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();// 日期格式
				String value = entry.getValue();// 正则表达式

				Pattern p = Pattern.compile(value, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
				SimpleDateFormat sdf=new SimpleDateFormat(key);
				if(key.contains("MMM")){
					sdf = new SimpleDateFormat(key,Locale.ENGLISH);
				}
				Matcher matcher = p.matcher(dateStr);
				while (matcher.find() && matcher.groupCount() >= 1) {
					//获取第一个时间
					timeStr = matcher.group(1);
					timeStr=sdfDateFormat.format(sdf.parse(timeStr)).toString();
				}

				

			}
			return timeStr;
		} catch (Exception e) {
			e.printStackTrace();
			return timeStr;
		}
	}
	public static String getMethod(String str) {
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		if (str.contains("post") || str.contains("POST")) {
			return "POST";
		} else if (str.contains("get") || str.contains("GET")) {
			return "GET";
		}
		return "";

	}
	
	//(\\s+((/(\\w+(-\\w+)*))+)(\\.?)(\\w+(-\\w+)*)((\\?)?)((\\w+(-\\w+)*)=((\\w+(-\\w+)*)(&?)))*)
	public static void main(String args[]){
		String  regx= "(\\s+((/(\\w+(-\\w+)*))+)(\\.?)(\\w+(-\\w+)*)((\\?)?)((\\w+(-\\w+)*)=((\\w+(-\\w+)*)(&?)))*)";
    	String text = "http:www.baidu.com/News/32016525162817.html 218.30.106.142 - - [12/Nov/2016:20:59:04 -0800] GET /News/32016525162817.html /Hr/Hr_More.asp?Param=48&id=123 HTTP/1.1 200 2594 - Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31";
		List<String> result = new ArrayList<String>();
		Matcher m = Pattern.compile(regx).matcher(text);
		while (m.find()) {
			result.add(m.group(1).trim());
		}
		System.out.println(result.toString());
	}
}
