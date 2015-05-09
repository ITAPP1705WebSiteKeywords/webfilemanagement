package edu.cqu.common;
import java.text.*;
import java.util.*;
public class DateUtil {
	// format Calendar object
	public static String format(String dateFormat, Calendar calendar) {
		try {
			return new SimpleDateFormat(dateFormat).format(calendar.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	// formateDate object as dateFormat
	public static String format(String dateFormat, Date date) {
		try {
			return new SimpleDateFormat(dateFormat).format(date);
		} catch (Exception e) {
			return null;
		}
	}
	// parse dateString
	public static Date parse(String dateFormat, String dateString) {
		try {
			return new SimpleDateFormat(dateFormat).parse(dateString);
		} catch (Exception ex) {
			return null;
		}
	}
	// transfer date object to Calendar object
	public static Calendar toCalendar(Date date) {
		if (date != null) {
			Calendar cl = Calendar.getInstance();
			cl.setTime(date);
			return cl;
		}
		return null;
	}
	// transfer Calendar object to date object
	public static Date toDate(Calendar calendar) {
		return (calendar == null) ? null : calendar.getTime();
	}
	// return current year
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
}
