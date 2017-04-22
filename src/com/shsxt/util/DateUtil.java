package com.shsxt.util;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * String与Date转换
 * 
 * <pre>
 * 历史记录：
 * 	新建 2012-8-13 guobaoli 
 * </pre>
 */
public class DateUtil {

	

	/**
	 * 把String转换成Date转换逻辑
	 * 
	 * @throws Exception
	 * 
	 */
	public static Date getStringToDate(String strDate, String format)
			throws Exception {
		// Date date=null;
		if (format != null && !format.equals("")) {
			try {
				DateFormat sdf = new SimpleDateFormat(format);
				return sdf.parse(strDate);
			} catch (ParseException e) {
				System.out.println("根据输入日期[" + strDate + "]以及格式 [" + format + "] 返回日期类型错误");
				throw new Exception("返回日期类型错误", e);
			}
		} else {
			throw new Exception("日期类型不能为空");
		}
	}

	/**
	 * 把Date转换成String转换逻辑
	 * 
	 * @throws Exception
	 */
	public static String getDateToString(Date dateTime, String format)
			throws Exception {

		if (format == null && format.equals("")) {
			System.out.println("根据输入日期[" + dateTime + "]以及格式 [" + format + "] 返回日期类型错误");
			throw new Exception("日期类型不能为空");
		} else {
			DateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(dateTime);
		}
	}

	/**
	 * Date日期间隔计算
	 * 
	 * @throws Exception
	 */
	public static int getDiffDate(Date date1, Date date2) {
		long dt1 = date1.getTime();
		long dt2 = date2.getTime();
		Long time = 24 * 3600 * 1000L;
		if (dt1 > dt2) {
			return (int) (((dt1 - dt2) / time));
		} else {
			return (int) (((dt2 - dt1) / time));
		}
	}

	/**
	 * String日期间隔计算
	 * 
	 * @throws Exception
	 */
	public static int getStringToDiffDate(String str1, String str2)
			throws Exception {
		return getDiffDate(getStringToDate(str1, "yyyyMMdd"), getStringToDate(str2,
				"yyyyMMdd"));
	}

	/**
	 * String自定义format日期间隔计算
	 * 
	 * @throws Exception
	 */
	public static int getStringFormatToDiffDate(String str1, String str2,
			String format) throws Exception {
		if (format.equals("yyyyMMdd") && format.equals("yyMMdd") && str1.equals("")
				&& str2.equals("")) {
			System.out.println("根据输入字符串日期[" + str1 + "," + str2 + "]以及格式 [" + format
					+ "] 计算日期间隔错误");
			throw new Exception("日期格式不对或者字符串日期为空");
		} else {
			return getDiffDate(getStringToDate(str1, format), getStringToDate(str2,
					format));
		}
	}

	/**
	 * Date日期增加计算
	 * */
	public static Date getAddDate(Date date, int addDate) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.add(Calendar.DATE, addDate);
		return cale.getTime();
	}

	/**
	 * String日期增加计算
	 * @throws Exception 
	 * */
	public static Date getStringToAddDate(String str, int addDate)
			throws Exception {
		return getAddDate(getStringToDate(str, "yyyyMMdd"), addDate);
	}

	/**
	 * format日期增加计算
	 * @throws Exception 
	 * */

	public static Date getStringFormatToAddDate(String str, int addDate,
			String format) throws Exception {
		return getAddDate(getStringToDate(str, format), addDate);
	}

	/**
	 * 按照format获取当前日期
	 * @throws Exception 
	 * @throws Exception 
	 * */
	public static String getNowDateTime(String format) throws Exception {
		return getDateToString(new Date(System.currentTimeMillis()), format);
	}
	

	/**
	 * <pre>
	 * 2012-9-13 zhushenghong
	 * 	字符串日期格式化
	 * </pre>
	 * 
	 * @param date
	 * @param formatStr1
	 * @param formatStr2
	 * @return String
	 * @throws ParseException
	 */
	public static String DateStr2DateStr(String date, String formatStr1, String formatStr2) {
	    
	    SimpleDateFormat sdf1 = new SimpleDateFormat(formatStr1);
	    
	    SimpleDateFormat sdf2 = new SimpleDateFormat(formatStr2);
	    
	    try {
			return sdf2.format(sdf1.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	    
	}
	
	/**
	 * 格式化数字，例如：12345转化为12,345
	 * 
	 * @param dValue
	 *            被格式化的数值
	 * @param iScale
	 *            小数点后保留位数,不足补0
	 * @return java.lang.String
	 * @see String
	 */
	public static String formatNumber(double dValue, int iScale) {
		DecimalFormat df = null;
		StringBuffer sPattern = new StringBuffer(",##0");
		if (iScale > 0) {
			sPattern.append(".");
			for (int i = 0; i < iScale; i++) {
				sPattern.append("0");
			}
		}
		df = new DecimalFormat(sPattern.toString());
		return df.format(dValue);
	}
	/**
	 * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
	 * 
	 * @param sdate
	 * @param num
	 * @return
	 */
	public static String getWeek(String sdate, String num) {
		// 再转换为时间
		Date dd = strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (num.equals("1")) // 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		else if (num.equals("2")) // 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		else if (num.equals("3")) // 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		else if (num.equals("4")) // 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		else if (num.equals("5")) // 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		else if (num.equals("6")) // 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		else if (num.equals("0")) // 返回星期日所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	/**
	 * 根据一个日期，返回是星期几的数字
	 * 
	 * @param sdate
	 * @return
	 */
	public static int getIntWeek(String sdate) {
		// 再转换为时间
		Date date = strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return c.get(Calendar.DAY_OF_WEEK);
	}
	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	
	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate2(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	
	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate3(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	/**
	/**
	 * 把日期字符串从一个格式转换为另一个格式
	 * @param strDate 日期字符串
	 * @param formFormat 日期字符串的格式
	 * @param toFormat 要转换的格式
	 * @return
	 */
	public static String strToDateFormat(String strDate,String formFormat,String toFormat){
		try {
			SimpleDateFormat formSdf = new SimpleDateFormat(formFormat);
			SimpleDateFormat toSdf = new SimpleDateFormat(toFormat);
			Date d = formSdf.parse(strDate);
			return toSdf.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	
}
