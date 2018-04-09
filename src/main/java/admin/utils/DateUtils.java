package admin.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 类名称：DateUtils   
 
 * 类描述：    日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 
 * 创建人：Administrator    
 
 * 创建时间：2017年11月6日 上午11:32:30      
 
 * @version
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
			"yyyy.MM.dd HH:mm", "yyyy.MM" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	* 方法名: getDateWx 
	* 描述: 20091227091010
	* 参数: @return    设定文件 
	* 时间:2018年3月14日下午2:03:26 
	* 返回: String    返回类型 
	* 作者: yzj 
	* @throws
	 */
	public static String getDateWx() {
		return getDate("yyyyMMddHHmmss");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		if (date == null) {
			return null;
		}
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 *
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	public static String toStr() {
		return toStr(new Date());
	}

	public static String toStr(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * 将一个 Date 格式化为日期/时间字符串
	 *
	 * @param date
	 *            要格式化为时间字符串的时间值
	 * @param pattern
	 *            日期和时间格式的模式
	 * @return 已格式化的时间字符串
	 * @author zhangyi
	 * @date 2016年10月11日 下午2:58:43
	 * @version 1.0.0
	 */
	public static String format(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 获取过去的小时
	 *
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 *
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 *
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
	}

	/**
	 * 获取两个日期之间的天数
	 *
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	public static String getFirstDayOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//获取当前月第一天：
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
		String first = format.format(c.getTime());
		return first;
	}

	public static Timestamp gettimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 
	* 方法名: getDoubleType 
	* 描述: 返回时间的 double 
	* 参数: @param dateString
	* 参数: @return
	* 参数: @throws ParseException    设定文件 
	* 时间:2017年11月15日上午10:50:59 
	* 返回: double    返回类型 
	* 作者: yzj 
	* @throws
	 */
	public static double getDoubleType(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(dateString);
		return date.getTime();
	}

	/**
	 * 
	* 方法名: calLastedTime 
	
	* 描述: 相减得到秒
	
	* 参数: @param startDate
	* 参数: @return    设定文件 
	
	* 时间:2017年12月14日下午2:29:57 
	
	* 返回: int    返回类型 
	
	* 作者: liulicai 
	
	* @throws
	 */
	public static int calLastedTime(Date startTime, Date endTime) {
		long a = startTime.getTime();
		long b = endTime.getTime();
		int c = (int) ((a - b) / 1000);
		return c;
	}

	/**
	 * 
	* 方法名: getPassHours 
	* 描述: 获取startTime 以后的时间   小时为单位 
	* 参数: @return    设定文件 
	* 时间:2018年3月16日下午7:22:41 
	* 返回: String    返回类型 
	* 作者: yzj 
	* @throws
	 */
	public static String getPassHours(String startTime, int hours) {
		// 获取一个小时以后的时间  
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = null;
		try {
			date = df.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

	/*	Calendar calendar = Calendar.getInstance();*/
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hours);

		/*   System.out.println("当前的时间：" + df.format(new Date()));  */
		System.out.println("一个小时前的时间：" + df.format(calendar.getTime()));
		return df.format(calendar.getTime());
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		getPassHours("2018-03-12 09:15:17",7*24);
		//      System.out.println(formatDate(parseDate("2010/3/6")));
		//      System.out.println(getDate("yyyy年MM月dd日 E"));
		//      long time = new Date().getTime()-parseDate("2012-11-19").getTime();
		//      System.out.println(time/(24*60*60*1000));
	}
}
