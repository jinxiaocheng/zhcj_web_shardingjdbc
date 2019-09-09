package com.escst.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 *
 * @author Administrator
 */
public class DateUtils {
	public static final String TO_YEAR = "yyyy";
	public static final String TO_MONTH = "yyyy-MM";
	public static final String TO_DATE = "yyyy-MM-dd";
	public static final String TO_HOUR = "yyyy-MM-dd HH";
	public static final String TO_MINUTE = "yyyy-MM-dd HH:mm";
	public static final String TO_SECOND = "yyyy-MM-dd HH:mm:ss";
	public static final String TO_MILLISECOND = "yyyy-MM-dd HH:mm:ss SSSS";
	public static final String TO_MONTH_N = "yyyyMM";
	public static final String TO_DATE_N = "yyyyMMdd";
	public static final String TO_MINUTE_N = "yyyyMMddHHmm";
	public static final String TO_SECOND_N = "yyyyMMddHHmmss";
	public static final String TO_MILLISECOND_N = "yyyyMMddHHmmssSSSS";

	public static final String TO_MONTH_CHINA = "yyyy年MM月";

	public static final String DATA_SEPARATE = " TO ";
	private static Logger logger = LoggerFactory.getLogger("DateUtils");



	/**
	 * @return String 返回日期时间
	 * @throws
	 * @Title: getCurrentDate
	 * @Description: 获取当前时间
	 */
	public static String getCurrentDate() {
		SimpleDateFormat formater = new SimpleDateFormat(TO_SECOND);
		String issueDate = "2001-12-25";
		return formater.format(new Date());
	}

	/**
	 * 获取当前年
	 *
	 * @return
	 */
	public static String getCurrentYear() {
		SimpleDateFormat formater = new SimpleDateFormat(TO_YEAR);
		return formater.format(new Date());
	}

	/**
	 * @param @return
	 * @return String
	 * @throws
	 * @Title: getCurrentMonth
	 * @Description: 获取当前月
	 * @author LUOFUJIA
	 * @date 2015年4月8日 下午2:51:50
	 */
	public static String getCurrentMonth() {
		SimpleDateFormat formater = new SimpleDateFormat(TO_MONTH_CHINA);
		return formater.format(new Date());
	}

	/**
	 * 将Date对象解析成指定格式的字符串。
	 *
	 * @param date    Date对象
	 * @param pattern 格式
	 * @return 返回指定格式的字符串。
	 */
	public static String format(Date date, String pattern) {
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		return formater.format(date);
	}

	/**
	 * 将指定格式的字符串解析成Date对象。
	 *
	 * @param str    字符串
	 * @param format 格式
	 * @return 返回对应的Date对象。
	 */
	public static Date parse(String str, String format) {
		SimpleDateFormat parser = new SimpleDateFormat(format);
		try {
			return parser.parse(str);
		} catch (ParseException e) {
			logger.error("字符串转换为date类型出错", e);
		}
		return null;
	}

	/**
	 * 获取今天日期00:00:00开始。
	 *
	 * @return 返回当前日期。
	 */
	public static Date getTodayStart() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取今天日期23:59:59结束。
	 *
	 * @return 返回当前日期。
	 */
	public static Date getTodayEnd() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}

	/**
	 * 获取日期00:00:00开始。
	 *
	 * @param dateTime 时间字符串
	 * @return 返回当前日期。
	 */
	public static Date getDateStart(String dateTime) {
		Calendar cal = Calendar.getInstance();
		Date date = DateUtils.parse(dateTime, TO_DATE);
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取今天日期23:59:59结束。
	 *
	 * @param dateTime 时间字符串
	 * @return 返回当前日期。
	 */
	public static Date getDateEnd(String dateTime) {
		Calendar cal = Calendar.getInstance();
		Date date = DateUtils.parse(dateTime, TO_DATE);
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 59);
		// cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}

	/**
	 * 获取7天日期开始00:00:00
	 *
	 * @return 返回七天前日期
	 */
	public static Date getPreSevenday() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, -6);
		return cal.getTime();
	}

	/**
	 * 获取昨天日期00:00:00。
	 *
	 * @return 返回昨天日期。
	 */
	public static Date getYesToday() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	/**
	 * 获取昨天日期00:00:00。
	 *
	 * @return 返回昨天开始日期。
	 */
	public static Date getYesTodayEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 时间计算
	 *
	 * @param date 计算时间
	 * @param t    计算天数 正数为加 负数为减(单位：天)
	 * @return 时间
	 */
	public static Date addDate(Date date, int t) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, t);
		return cal.getTime();
	}

	/**
	 * 时间计算
	 *
	 * @param date 计算时间
	 * @param t    计算天数 正数为加 负数为减(单位：小时)
	 * @return 时间
	 */
	public static Date addTime(Date date, int t) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, t);
		return cal.getTime();
	}

	public static Date addMinute(Date date, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minute);
		return cal.getTime();
	}

	/**
	 * 获取今天开始的时间
	 *
	 * @param format 时间格式
	 * @return 时间的字符串格式
	 */
	public static String getTodayStartToString(String format) {
		return format(getTodayStart(), format);
	}

	/**
	 * 获取今天结束的时间
	 *
	 * @param format 时间格式
	 * @return 时间的字符串格式
	 */
	public static String getTodayEndToString(String format) {
		return format(getTodayEnd(), format);
	}

	/**
	 * 获取昨天结束的时间
	 *
	 * @param format 时间格式
	 * @return 时间的字符串格式
	 */
	public static String getYestdateEndToString(String format) {
		return format(getYesTodayEnd(), format);
	}

	/**
	 * 获取昨天开始的时间
	 *
	 * @param format 时间格式
	 * @return 时间的字符串格式
	 */
	public static String getYestdateStartToString(String format) {
		return format(getYesToday(), format);
	}

	/**
	 * 获取7天开始的时间
	 *
	 * @param format 时间格式
	 * @return 时间的字符串格式
	 */
	public static String getPreSevendayToString(String format) {
		return format(getPreSevenday(), format);
	}

	/**
	 * 返回昨天日期
	 *
	 * @param format 时间格式
	 * @return 时间的字符串格式
	 */
	public static String getYesTodayToString(String format) {
		return format(getYesToday(), format);
	}

	/**
	 * @return 查询一周时间格式
	 */
	public static String getWEEK() {
		return getPreSevendayToString(TO_MINUTE_N) + DATA_SEPARATE
				+ getTodayEndToString(TO_MINUTE_N);
	}

	/**
	 * @return 查询今天时间格式
	 */
	public static String getTODAY() {
		return getTodayStartToString(TO_MINUTE_N) + DATA_SEPARATE
				+ getTodayEndToString(TO_MINUTE_N);
	}

	/**
	 * @return 查询昨天时间格式
	 */
	public static String getYESTODAY() {
		return getYesTodayToString(TO_MINUTE_N) + DATA_SEPARATE
				+ getTodayStartToString(TO_MINUTE_N);
	}

	/**
	 * 生成指定时间从开始时间到结束时间的查询格式
	 *
	 * @param datetime 传入的指定时间
	 * @return 返回查询索引格式的 时间查询条件
	 */
	public static String getCUSTOMDATE(String datetime) {
		return format(getDateStart(datetime), TO_MINUTE_N) + DATA_SEPARATE
				+ format(getDateEnd(datetime), TO_MINUTE_N);
	}

	/**
	 * 生成指定时间从开始时间到结束时间的查询格式
	 *
	 * @param beginDate 开始时间
	 * @param endDate   结束时间
	 * @return 返回查询索引格式的 时间查询条件
	 */
	public static String getCUSTOMDATE(String beginDate, String endDate) {
		return format(getDateStart(beginDate), TO_MINUTE_N) + DATA_SEPARATE
				+ format(getDateEnd(endDate), TO_MINUTE_N);
	}

	/**
	 * 生成指定时间从开始时间到结束时间的查询格式
	 *
	 * @param beginDate 开始时间
	 * @param endDate   结束时间
	 * @return 返回查询索引格式的 时间查询条件
	 */
	public static String getCUSTOMDATE(Date beginDate, Date endDate) {
		return format(beginDate, TO_MINUTE_N) + DATA_SEPARATE
				+ format(endDate, TO_MINUTE_N);
	}

	/**
	 * 判断两个Date大小
	 *
	 * @param date     传入时间
	 * @param fromdate 待比较时间
	 * @return 如果date大于fromdate 返回true 否则返回false
	 */
	public static boolean dataCompare(Date date, Date fromdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar fromcal = Calendar.getInstance();
		fromcal.setTime(fromdate);
		if (cal.compareTo(fromcal) < 0) {
			return false;
		}
		if (cal.compareTo(fromcal) >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 时间格式转换
	 *
	 * @param dateStr     时间字符串
	 * @param formatbegin 原格式
	 * @param formatend   目标格式
	 * @return 时间目标格式字符串
	 */
	public static String changeFormat(String dateStr, String formatbegin,
									  String formatend) {
		SimpleDateFormat fortmat = new SimpleDateFormat(formatbegin);
		Date date = null;
		try {
			date = fortmat.parse(dateStr);
			SimpleDateFormat fortmat2 = new SimpleDateFormat(formatend);
			return fortmat2.format(date);
		} catch (ParseException e) {
			logger.error("字符串转换为date类型出错", e);
			return "";
		}
	}

	/**
	 * 开始时间
	 *
	 * @param date 时间
	 * @return 开始的时间
	 */
	public static Date getStartData(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 结束时间
	 *
	 * @param date 时间
	 * @return 结束的时间
	 */
	public static Date getEndData(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 59);
		// cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}

	/**
	 * 结束时间
	 *
	 * @param date 时间
	 * @return 结束的时间
	 */
	public static Date getTodayBulletinStartData(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 00);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	/**
	 * 结束时间
	 *
	 * @param date 时间
	 * @return 结束的时间
	 */
	public static Date getTodayBulletinEndData(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 8);
		cal.set(Calendar.MINUTE, 30);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 00);
		return cal.getTime();
	}


//	/**
//	 * 判断传入时间是否是同一天
//	 * 
//	 * @param fromdate
//	 *            比较日期
//	 * @param todate
//	 *            待比较日期
//	 * @return 是为true 否为false
//	 */
//	public static boolean isSameDay(Date fromdate, Date todate) {
//		String fromdatestring = coo.base.util.DateUtils.format(fromdate);
//		String todatestring = coo.base.util.DateUtils.format(todate);
//		if (fromdatestring.equals(todatestring)) {
//			return true;
//		}
//		return false;
//	}

	/**
	 * 获取今天是一个星期的天数
	 *
	 * @return 星期日到今天的天数
	 */
//	@SuppressWarnings("static-access")
//	public static int dayForWeek() {
//		Calendar c = Calendar.getInstance();
//		c.setFirstDayOfWeek(Calendar.MONDAY);
//		return c.get(c.DAY_OF_WEEK);
//	}

	/**
	 * 获取今天是一个月的第几天
	 *
	 * @return 每月1号到今天的天数
	 */
//	@SuppressWarnings("static-access")
//	public static int dayForMonth() {
//		Calendar c = Calendar.getInstance();
//		return c.get(c.DAY_OF_MONTH);
//	}

	/**
	 * 获取今天是一年的第几个星期
	 *
	 * @return 今天是一年的周数
	 */
//	@SuppressWarnings("static-access")
//	public static int weekForYear() {
//		Calendar c = Calendar.getInstance();
//		return c.get(c.WEEK_OF_YEAR);
//	}

	/**
	 * 获取今天是一年的第几天
	 *
	 * @return 一年的第几天
	 */
//	@SuppressWarnings("static-access")
//	public static int dayForYear() {
//		Calendar c = Calendar.getInstance();
//		return c.get(c.DAY_OF_YEAR);
//	}

	/**
	 * 获取今天是一年的第几天 dateTime格式：yyyy-MM-dd
	 *
	 * @param dateTime
	 *            日期字符串
	 * @return 一年的第几天
	 */
//	@SuppressWarnings("static-access")
//	public static int dayForYear(String dateTime) {
//		Calendar c = Calendar.getInstance();
//		Date date = DateUtils.parse(dateTime, TO_DATE);
//		c.setTime(date);
//		return c.get(c.DAY_OF_YEAR);
//	}

	/**
	 * 获取今天是在一年的第几个月
	 *
	 * @return 一年的第几个月
	 */
	public static int monthForYear() {
		java.text.Format mm = new SimpleDateFormat("MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		String mmDate = mm.format(c.getTime());
		Integer mmInt = Integer.valueOf(mmDate);
		return mmInt;
	}

	/**
	 * 获取一周天数列表
	 *
	 * @param format 时间格式
	 * @return 一周天数列表
	 */
	public static List<String> getIndexPathForWeek(String format) {
		List<String> dateStrList = new ArrayList<String>();
		java.text.Format yyyymmdd = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		// 7天前
		c.add(Calendar.DATE, -7);

		for (int i = 1; i < 8; i++) {
			// 时间加一天
			c.add(Calendar.DATE, 1);
			String yyyymmddDate = yyyymmdd.format(c.getTime());
			dateStrList.add(yyyymmddDate);
		}
		return dateStrList;
	}

	/**
	 * 根据某一天获取所属的本周 星期一的日期
	 *
	 * @param date 某一天日期时间
	 * @return 所属的本周 星期一的日期
	 */
	public static Date createWeekofdayToMonday(Date date) {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return calendar.getTime();
	}

	/**
	 * 根据某一天获取所属的本周 星期天的日期
	 *
	 * @param date 某一天日期时间
	 * @return 所属的本周 星期一的日期
	 */
	public static Date createWeekofdayToSunday(Date date) {
		Date tmpdate = createWeekofdayToMonday(date);
		Date adddate = addDate(tmpdate, 6);
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTime(adddate);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 根据某一天获取所属的本月第一天日期
	 *
	 * @param date 某一天日期时间
	 * @return 所属的本周 星期一的日期
	 */
	public static Date createMonthofdayToFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 根据某一天获取所属的本月最后一天日期
	 *
	 * @param date 某一天日期时间
	 * @return 所属的本周 星期一的日期
	 */
	public static Date createMonthofdayToLastDay(Date date) {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 59);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 返回上周 周一日期
	 *
	 * @param date 当前时间
	 * @return 周一日期
	 */
	public static Date createPreviousToMonday(Date date) {
		Date pdate = createWeekofdayToMonday(date);
		return addDate(pdate, -7);
	}

	/**
	 * 返回上周 周日日期
	 *
	 * @param date 当前时间
	 * @return 周日日期
	 */
	public static Date createPreviousToSunday(Date date) {
		Date pdate = createWeekofdayToMonday(date);
		pdate = addDate(pdate, -1);
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTime(pdate);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 返回前五周 周一日期
	 *
	 * @param date 当前时间
	 * @return 周一日期
	 */
	public static Date createFiveweekToMonday(Date date) {
		Date pdate = createWeekofdayToMonday(date);
		return addDate(pdate, -35);
	}

	/**
	 * 返回前五周 周日日期
	 *
	 * @param date 当前时间
	 * @return 周日日期
	 */
	public static Date createFiveweekToSunday(Date date) {
		return createWeekofdayToSunday(date);
	}

	/**
	 * 将long型转换为date型
	 *
	 * @param number 时间值
	 * @return
	 */
	public static String convertLongToDate(long number) {
		Date date = new Date(number);
		return format(date, TO_SECOND);
	}

	/**
	 * @param startDate 格式yyyy-mm-dd
	 * @param endDate   格式yyyy-mm-dd
	 * @return
	 * @desc 得到两个时间段的月
	 * @author zhouwei
	 * @date 2017年8月26日 下午5:12:04
	 */
	public static List<String> getMonthList(String startDate, String endDate) {
		List<String> list = new ArrayList<String>();
		Date sd = DateUtils.parse(startDate, DateUtils.TO_MONTH);
		Date ed = DateUtils.parse(endDate, DateUtils.TO_MONTH);
		Calendar cal = Calendar.getInstance();
		while (sd.compareTo(ed) <= 0) {
			list.add(DateUtils.format(sd, DateUtils.TO_MONTH_N));

			cal.setTime(sd);
			cal.add(Calendar.MONTH, 1);
			String sdStr = DateUtils.format(cal.getTime(), DateUtils.TO_MONTH);
			sd = DateUtils.parse(sdStr, DateUtils.TO_MONTH);
		}
		return list;
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return
	 * @desc 得到两个时间段的天
	 * @author niejing
	 * @date 2017年11月6日 下午4:06:30
	 */
	public static List<String> getDayList(String startDate, String endDate) {
		List<String> list = new ArrayList<String>();
		Date sd = DateUtils.parse(startDate, DateUtils.TO_DATE);
		Date ed = DateUtils.parse(endDate, DateUtils.TO_DATE);
		Calendar cal = Calendar.getInstance();
		while (sd.compareTo(ed) <= 0) {
			list.add(DateUtils.format(sd, DateUtils.TO_DATE));

			cal.setTime(sd);
			cal.add(Calendar.DATE, 1);
			String sdStr = DateUtils.format(cal.getTime(), DateUtils.TO_DATE);
			sd = DateUtils.parse(sdStr, DateUtils.TO_DATE);
		}
		return list;
	}

	/**
	 * @param type 1:日;2:周;3:月
	 * @return
	 * @desc 根据统计类型得到统计的开始时间和截止时间
	 * @author zhouwei
	 * @date 2017年9月15日 下午5:45:43
	 */
	public static String[] getDateSection(int type) {
		String startDate = "";
		String endDate = "";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);// 结束时间往前推1天
		endDate = DateUtils.format(cal.getTime(), DateUtils.TO_DATE);
		if (type == 1 || type == 2) {
			cal = Calendar.getInstance();
			cal.set(Calendar.DATE, 1);//设置为当月的第一天
			startDate = DateUtils.format(cal.getTime(), DateUtils.TO_DATE);
		} else if (type == 3) {
			cal = Calendar.getInstance();
			//每年的1月1日
			cal.set(Calendar.MONTH, 1);
			cal.set(Calendar.DATE, 1);
			startDate = DateUtils.format(cal.getTime(), DateUtils.TO_DATE);
		}
		return new String[]{startDate, endDate};
	}

	public static String[] getDateSlot(int type, String str) {
		String startDate = "";
		String endDate = "";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);// 结束时间当天
		endDate = DateUtils.format(cal.getTime(), DateUtils.TO_DATE);
		if (type == 1) {
			cal = Calendar.getInstance();
			cal.set(Calendar.DATE, 1);//设置为当月的第一天
			startDate = DateUtils.format(cal.getTime(), DateUtils.TO_DATE);

		} else if (type == 2) {
			startDate = getFirstDayOfMonth(str);
			endDate = getLastDayOfMonth(str);
		}
		return new String[]{startDate, endDate};
	}


	/**
	 * 获得该月第一天
	 *
	 * @param str
	 * @return
	 */
	public static String getFirstDayOfMonth(String str) {
		int month = Integer.valueOf(str.split("-")[1]);
		int year = Integer.valueOf(str.split("-")[0]);
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR, year);
		//设置月份
		cal.set(Calendar.MONTH, month - 1);
		//获取某月最小天数
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最小天数
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		//格式化日期
		String firstDayOfMonth = DateUtils.format(cal.getTime(), DateUtils.TO_DATE);
		return firstDayOfMonth;
	}

	/**
	 * 获得该月最后一天
	 *
	 * @param str
	 * @return
	 */
	public static String getLastDayOfMonth(String str) {
		int month = Integer.valueOf(str.split("-")[1]);
		int year = Integer.valueOf(str.split("-")[0]);
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR, year);
		//设置月份
		cal.set(Calendar.MONTH, month - 1);
		//获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		//格式化日期
		String lastDayOfMonth = DateUtils.format(cal.getTime(), DateUtils.TO_DATE);
		return lastDayOfMonth;
	}


	/**
	 * 获取任意时间的下一个月
	 * @param repeatDate
	 * @return yyyy-MM
	 */
	public static String getPreMonth(String repeatDate) {
		String lastMonth = "";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
		int year = Integer.parseInt(repeatDate.substring(0, 4));
		String monthsString = repeatDate.substring(4, 6);
		int month;
		if ("0".equals(monthsString.substring(0, 1))) {
			month = Integer.parseInt(monthsString.substring(1, 2));
		} else {
			month = Integer.parseInt(monthsString.substring(0, 2));
		}
		cal.set(year,month,Calendar.DATE);
		lastMonth = dft.format(cal.getTime());
		lastMonth = lastMonth.substring(0,4) + "-" + lastMonth.substring(4,6);
		return lastMonth;
	}

	public static void main(String[] args) {
		System.out.println(getPreMonth("201710"));
	}





	/**
	 * @desc 获取当月第一天到当前时间集合
	 */
	public static List<String> queryDayListByCurrentHour() {
		List<String> list = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		String yearMonth = DateUtils.format(cal.getTime(), DateUtils.TO_MONTH_N);
		for (int i = 1; i <= day; i++) {//当天不统计
			list.add(converString(i));
		}
		return list;
	}

	/**
	 * @desc 获取某一个月天数
	 */
	public static List<String> queryMonthDay(String str) {
		int month = Integer.valueOf(str.split("-")[1]);
		int year = Integer.valueOf(str.split("-")[0]);
		List<String> list = new ArrayList<String>();
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			for (int i = 1; i <= 31; i++) {
				list.add(converString(i));

			}
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			for (int i = 1; i <= 30; i++) {
				list.add(converString(i));
			}
		} else if (getYear(year)) {
			for (int i = 1; i <= 29; i++) {
				list.add(converString(i));
			}
		} else if (!getYear(year)) {
			for (int i = 1; i <= 28; i++) {
				list.add(converString(i));
			}
		}
		return list;

	}

	public static boolean getYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			return true;
		}
		return false;
	}


	private static String converString(int num) {
		if (num < 10) {
			return "0" + num;
		}
		return String.valueOf(num);
	}

	public static String getDateStr(String str) {
		int month = Integer.valueOf(str.split("-")[1]);
		String dateStr = "";
		switch (month) {
			case 1:
				dateStr = "一月";
				break;
			case 2:
				dateStr = "二月";
				break;
			case 3:
				dateStr = "三月";
				break;
			case 4:
				dateStr = "四月";
				break;
			case 5:
				dateStr = "五月";
				break;
			case 6:
				dateStr = "六月";
				break;
			case 7:
				dateStr = "七月";
				break;
			case 8:
				dateStr = "八月";
				break;
			case 9:
				dateStr = "九月";
				break;
			case 10:
				dateStr = "十月";
				break;
			case 11:
				dateStr = "十一月";
				break;
			case 12:
				dateStr = "十二月";
				break;
		}
		return dateStr;
	}


	/**
	 * @desc 获取两个日期相隔的天数
	 * @param date1
	 * @param date2
	 * @return
	 * @author dwj
	 * @date 2018/2/26 10:09
	 */
	public static int daysBetween(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}


	/**
	 * @param y
	 * @param z
	 * @return
	 * @desc 取两个数的百分比
	 * @author dwj
	 * @date 2018/2/23 9:15
	 */
	public static String getPercent(int y, int z) {
		// 创建一个数值格式化对象
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(2);
		String result = "";
		Float num = (float) y / (float) z * 100;
		//大于1000数据会转换异常
		if(num > 999){
		 	 result = String.valueOf(100);
		}else {
			 result = numberFormat.format(num);
		}
		return result;
	}

	/**
	 * 
	 * @desc 获取x分钟之前的时间 
	 * @param x
	 * @return 
	 * @author niejing
	 * @date 2018年8月2日 下午5:57:34
	 */
	public static String getXminutesAgoDate(int x){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -x);
		Date date = cal.getTime();
		SimpleDateFormat sd = new SimpleDateFormat(TO_SECOND);
		return sd.format(date);
	}

	/**
	 * 功能描述：返回小
	 *
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * @desc 获取两个时间差的小时分钟秒
	 * @author jincheng
	 * @date 2018-12-28 18:09
	 */
	public static String getDatePoor(Date endDate, Date nowDate) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		long sec = diff % nd % nh % nm / ns;
		return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
	}

}
