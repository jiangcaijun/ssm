package com.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	/**
	 * 将时间转化为mills
	 * @param Date
	 * @return
	 * @throws ParseException
	 */
	public static long getMills(String Date) throws ParseException{
		return formatToDate(Date).getTime();
	}
	
	/**
	 * 将日期字符串转化为时间
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date formatToDate(String date) throws ParseException{
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		return sdf.parse(date);
	}
}
