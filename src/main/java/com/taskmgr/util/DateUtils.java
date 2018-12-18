package com.taskmgr.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	static DateFormat format;
	
	public static Date stringToDate(String sdate) {
		format = new SimpleDateFormat("yyyy-mm-dd");
		try {			
			return format.parse(sdate);			
		}catch (Exception e) {
			return null;
		}		
	}
	public static String dateToString(Date date) {
		format = new SimpleDateFormat("yyyy-mm-dd");
		try {			
			return format.format(date);			
		}catch (Exception e) {
			return null;
		}		
	}
}
