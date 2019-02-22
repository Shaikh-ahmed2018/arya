package com.centris.campus.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JDate {

	private static SimpleDateFormat sdfNormal = new SimpleDateFormat(
			"dd/MM/yyyy");

	private static SimpleDateFormat sdfNormalUs = new SimpleDateFormat(
			"MM/dd/yyyy");
	private static SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyy-MM-dd");
	private static FieldPosition fpDate = new FieldPosition(
			SimpleDateFormat.DATE_FIELD);
	private static FieldPosition fpDate1 = new FieldPosition(
			SimpleDateFormat.YEAR_FIELD);
	public static SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
	@SuppressWarnings("unused")
	private static SimpleDateFormat sdfTime = new SimpleDateFormat("S");
	private String afterCalculation;
	static {
		sdfNormal.setLenient(false);
		sdfSQL.setLenient(false);
		sdfNormalUs.setLenient(false);
	}

	public static String getDateandMonth() throws ParseException {
		DateFormat formatter = new SimpleDateFormat("MMM-yyyy");
		String stringdate = formatter.format(new java.util.Date());
		return stringdate;
	}

	public static String toDateString(Date date) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
		String stringdate = formatter.format(date);
		return stringdate;
	}

	public static String toDateStringTimeStamp(Date date) throws ParseException {
		Timestamp timeStamp = new Timestamp(date.getTime());
		String stringdate = timeStamp.toString();
		return stringdate;
	}

	public static String toNormalDate(String str) throws ParseException {
		if (str == null) {
			str = "";
			return str;
		}
		StringBuffer sb = new StringBuffer();
		Date dt = sdfSQL.parse(str);
		sdfNormal.format(dt, sb, fpDate);
		return sb.toString();
	}

	public static String toSQLDate(String str) throws ParseException {
		if (str == "") {
			str = null;
			return str;
		}
		StringBuffer sb = new StringBuffer();
		Date dt = sdfNormal.parse(str);
		sdfSQL.format(dt, sb, fpDate);
		return sb.toString();
	}

	public static String parseSQLDateToUSDate(String str) throws ParseException {
		if (str == "") {
			str = null;
			return str;
		}
		StringBuffer sb = new StringBuffer();
		Date dt = sdfSQL.parse(str);
		sdfNormalUs.format(dt, sb, fpDate1);
		return sb.toString();
	}

	public static String getElapsedTime(Date date_start, Date date_end) {
		long start_ms = date_start.getTime();
		long end_ms = date_end.getTime();
		long total_ms = end_ms - start_ms;
		long total_sec = (total_ms - (total_ms % 1000)) / 1000;
		long total_min = total_sec / 60;
		@SuppressWarnings("unused")
		long total_hr = total_min / 60;

		long rem_ms = total_ms % 1000;
		long rem_sec = total_sec % 60;
		long rem_min = (total_min) % 60;
		long rem_hr = total_min / 60;

		String rem_time = "" + rem_hr + "Hr:" + rem_min + "Min:" + rem_sec
				+ "Sec:" + rem_ms + "Ms";
		return rem_time;
	}

	@SuppressWarnings("deprecation")
	public static String getTimeString(Date date_string) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd-MMM-yy hh:mm:ss aa");
		String formattedDate = dateFormat.format(new Date()).toString();

		return formattedDate;
	}

	@SuppressWarnings("deprecation")
	public static Date parseStringToDate(String s_date, String s_split) {
		int i_year = 0, i_month = 0, i_date = 0;

		if (s_split.equals("-")) {
			String s_tokens[] = s_date.split("-");

			i_year = Integer.parseInt(s_tokens[0]);
			i_month = Integer.parseInt(s_tokens[1]);
			i_date = Integer.parseInt(s_tokens[2]);
		}
		if (s_split.equals("/")) {
			String s_tokens[] = s_date.split("/");

			i_year = Integer.parseInt(s_tokens[2]);
			i_month = Integer.parseInt(s_tokens[1]);
			i_date = Integer.parseInt(s_tokens[0]);
		}

		Date dt_parsed = new Date(i_year - 1900, i_month - 1, i_date);
		return dt_parsed;
	}

	public static String parseUSDateToNormalDate(String s_date) {
		String s_tokens[] = s_date.split("-");

		String s_year = s_tokens[0];
		String s_month = s_tokens[1];
		String s_day = s_tokens[2];

		String s_normal_date = s_day + "-" + s_month + "-" + s_year;
		return s_normal_date;
	}

	@SuppressWarnings("deprecation")
	public static String calcGMTTime() {
		Date d = new Date();

		d.setHours(d.getHours() - 2);

		return d.toLocaleString();

	}

	public Time getCalculationTime(String getTime, int increment) {

		Calendar cal = Calendar.getInstance();
		try {
			Date date = df.parse(getTime);
			cal.setTime(date);
			cal.add(Calendar.MINUTE, increment);
			Date date1 = cal.getTime();

			afterCalculation = df.format(date1);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		Time time = java.sql.Time.valueOf(afterCalculation);
		return time;

	}

	public String getTodayDate() {
		Date date = new Date();
		String date1 = sdfSQL.format(date);
		return date1;

	}

	public static Time getDifferenceTime(String selectedTime) {
		String afterSubract = "";
		Calendar cal = Calendar.getInstance();
		try {
			Date selectedTimedate = df.parse(selectedTime);
			cal.setTime(selectedTimedate);
			cal.add(Calendar.MINUTE, -5);
			Date date1 = cal.getTime();

			afterSubract = df.format(date1);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		Time time = java.sql.Time.valueOf(afterSubract);
		return time;

	}

	public static java.sql.Date getSqlDate1(String date) {
		java.util.Date sd;
		java.util.Date sd1;
		java.sql.Date currdate1 = null;
		java.sql.Date currdate = null;

		try {
			sd = new SimpleDateFormat("MM/dd/yyyy").parse(date);
			currdate1 = new java.sql.Date(sd.getTime());
			SimpleDateFormat sd2 = new SimpleDateFormat("yyyy-MM-dd");
			sd1 = new SimpleDateFormat("yyyy-MM-dd").parse(sd2
					.format(currdate1));
			currdate = new java.sql.Date(sd1.getTime());

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return currdate;
	}
}