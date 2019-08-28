package com.dgpalife.resourcemanagement.common;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

	public static boolean isEmpty(String s) {
		return s == null || "".equals(s); // s == null | s.equals("");
											// //位与,逻辑与区别,非空字符串放置在前面,避免空指针
	}

	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}

//	public static Date formatDate(Date currentTime) {
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String dateString = formatter.format(currentTime);
//		ParsePosition pos = new ParsePosition(8);
//		Date currentTime_2 = formatter.parse(dateString, pos);
//		return currentTime_2;
//	}
}
