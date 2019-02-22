package com.centris.campus.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.centris.campus.admin.LstmsParents;
import com.centris.campus.admin.LstmsPrinciple;
import com.centris.campus.admin.LstmsTeachers;
import com.centris.campus.admin.lstmsUser;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.pojo.UploadStaffXlsPOJO;
import com.centris.campus.vo.BankVo;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.HelperClassVo;
import com.centris.campus.vo.LibrarySearchIssueDetailsVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TeacherVo;
import com.centris.campus.vo.TimeTableVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.classVo;

public class HelperClass implements Comparator<LibrarySearchIssueDetailsVO>{
	private static final Logger logger = Logger.getLogger(HelperClass.class);
	public static boolean con = true;

	public static Time getCurrentTime() {
		Time t = null;
		try {
			t = new Time(new Date().getTime());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return t;
	}

	public static java.sql.Date getCurrentSqlDate(String date) {
		java.util.Date sd;
		java.sql.Date currdate = null;

		try {
			sd = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			currdate = new java.sql.Date(sd.getTime());

		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return currdate;
	}

	public static java.sql.Date getCurrentSqlDate() {
		java.util.Date sd = new java.util.Date();
		java.sql.Date currdate = null;
		try {
			currdate = new java.sql.Date(sd.getTime());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return currdate;
	}

	public static Time getStringToTime(String getTime) {
		Time time = null;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date d = null;
		try {
			d = sdf.parse(getTime);
			time = new Time(d.getTime());
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return time;

	}

	public static java.sql.Date getSQLDate(java.util.Date date1) {

		java.sql.Date sqldate = new java.sql.Date(date1.getTime());
		return sqldate;
	}

	public static String getCurrentUserID(HttpServletRequest request) {

		String current_user = null;

		try {
			
			HttpSession ses = request.getSession(false);
			
			UserDetailVO userDetailsVo = (UserDetailVO)ses.getAttribute(MessageConstants.USER_DETAILS);
			
			current_user=userDetailsVo.getUserId();

			/*if (user.equalsIgnoreCase("Admin")) {
				lstmsUser Admin_user = (lstmsUser) ses
						.getAttribute(MessageConstants.USER);
				current_user = Admin_user.getId();

			} else if (user.equalsIgnoreCase("Teacher")) {
				LstmsTeachers teacher_user = (LstmsTeachers) ses
						.getAttribute(MessageConstants.USER);
				current_user = teacher_user.getTeacherId();
			} else if (user.equalsIgnoreCase("Principle")) {
				LstmsPrinciple teacher_user = (LstmsPrinciple) ses
						.getAttribute(MessageConstants.USER);
				current_user = teacher_user.getId();
			} else {
				LstmsParents parent_user = (LstmsParents) ses
						.getAttribute(MessageConstants.USER);
				current_user = parent_user.getParentId();
			}*/
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return current_user;
	}

	public static String getCurrentUser(HttpServletRequest request) {

		String user = null;

		HttpSession ses = request.getSession(false);
		try {
			user = (String) ses.getAttribute("user");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return user;
	}
	
	public static String getPrivigeValue(HttpServletRequest request) {

		String user = null;

		HttpSession ses = request.getSession(false);
		try {
			user = (String) ses.getAttribute("Priveliges");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return user;
	}

	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static String getCurrentYearID() throws SQLException {

		CallableStatement proc = null;
		String accYear = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			proc = conn.prepareCall("{ call getCurrentAccYear() }");
			proc.execute();
			rs = proc.getResultSet();

			while (rs.next()) {
				accYear = rs.getString("ACCYEARID");

			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}

		return accYear;
	}
	
	public static String getCurrentAcadamicYear() throws SQLException {

		CallableStatement proc = null;
		String accYear = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			proc = conn.prepareCall("{ call getCurrentAccYear() }");
			proc.execute();
			rs = proc.getResultSet();

			while (rs.next()) {
				accYear = rs.getString("acadamic_year");

			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}

		return accYear;
	}

	public static java.sql.Date getSqlDateFromDdMmYyFormat(String date) {
		java.util.Date sd;
		java.sql.Date currdate = null;

		try {
			sd = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			currdate = new java.sql.Date(sd.getTime());

		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return currdate;
	}

	public static String getDateFromSQLDateinDDMMYYYYFormat(java.sql.Date date) {

		String strDate = null;

		try {
			java.util.Date utildate = new Date(date.getTime());
			strDate = new SimpleDateFormat("dd-MM-yyyy").format(utildate);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return strDate;
	}

	public static String convertDatabaseToUI(String date) {

		String currdate = null;

		try {
			Date sd = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			currdate = new SimpleDateFormat("dd-MM-yyyy").format(sd);

		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return currdate;
	}

	public static String convertUIToDatabase(String date) {

		String currdate = null;

		try {
			Date sd = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			currdate = new SimpleDateFormat("yyyy-MM-dd").format(sd);

		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return currdate;
	}

	/* Written by Arul gor Getting PreviousYear */
	public static int getPreviousYear() {
		Calendar prevYear = Calendar.getInstance();
		prevYear.add(Calendar.YEAR, -1);
		return prevYear.get(Calendar.YEAR);
	}

	/* Written by Arul gor Getting NextYear */
	public static int getNextYear() {
		Calendar nextYear = Calendar.getInstance();
		nextYear.add(Calendar.YEAR, +1);
		return nextYear.get(Calendar.YEAR);
	}

	/* Written by Arul gor Getting CurrentMonthNo */
	public static int getCurrentMonthNo() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}

	/* Written by Arul gor Getting Current Academic Year */
	public static String getAcademicYear() {
		int currentyear = Calendar.getInstance().get(Calendar.YEAR);
		int nextYear = getNextYear();
		int prevYear = getPreviousYear();
		int currentMonth = getCurrentMonthNo();
		currentMonth = ++currentMonth;
		String accYear = null;
		if (currentMonth <= 6) {
			accYear = Integer.toString(prevYear) + "/"
					+ Integer.toString(currentyear);
		} else {
			accYear = Integer.toString(currentyear) + "/"
					+ Integer.toString(nextYear);
		}
		return accYear;
	}

	public static long getDateDifference(String startdate, String endDate) {
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		long dateDifference = 0l;
		try {
			Date sdate = sd.parse(startdate);
			long slong = sdate.getTime();
			Date edate = sd.parse(endDate);
			long elong = edate.getTime();
			dateDifference = elong - slong;
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return dateDifference;
	}
	public static String getMonthName(String month) {

		Map<String, String> monthMap = new HashMap<String, String>();
		monthMap.put("01", "JAN");
		monthMap.put("02", "FEB");
		monthMap.put("03", "MAR");
		monthMap.put("04", "APR");
		monthMap.put("05", "MAY");
		monthMap.put("06", "JUN");
		monthMap.put("07", "JUL");
		monthMap.put("08", "AUG");
		monthMap.put("09", "SEP");
		monthMap.put("10", "OCT");
		monthMap.put("11", "NOV");
		monthMap.put("12", "DEC");
		return monthMap.get(month.length() < 2 ? "0" + month : month);
	}

	public static String getMonthFullName(String month) {

		Map<String, String> monthMap = new HashMap<String, String>();
		monthMap.put("01", "January");
		monthMap.put("02", "February");
		monthMap.put("03", "March");
		monthMap.put("04", "April");
		monthMap.put("05", "May");
		monthMap.put("06", "June");
		monthMap.put("07", "July");
		monthMap.put("08", "August");
		monthMap.put("09", "September");
		monthMap.put("10", "October");
		monthMap.put("11", "November");
		monthMap.put("12", "December");
		return monthMap.get(month.length() < 2 ? "0" + month : month);
	}

	public static int getCurrentYear() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	public static int getMonthDifference(String startDate, String endDate) {
		int monthDiff = 0;

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(format.parse(startDate));
			Calendar c2 = Calendar.getInstance();
			c2.setTime(format.parse(endDate));
			if (c2.get(Calendar.YEAR) > c1.get(Calendar.YEAR)) {
				int temp = 11 - c1.get(Calendar.MONTH);
				monthDiff = temp + c2.get(Calendar.MONTH) + 1;
			} else if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
				monthDiff = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
			}

		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return monthDiff;
	}

	public static java.sql.Date getSqlDateFromYyMmDdFormat(String date) {
		java.util.Date sd;
		java.sql.Date currdate = null;

		try {
			sd = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			currdate = new java.sql.Date(sd.getTime());

		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return currdate;
	}

	public static String convertDatabaseToUIWithDateTime(String date) {

		String currdate = null;

		try {
			Date sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
			currdate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(sd);

		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return currdate;
	}

	public static String convertUItoDatabaseWithDateTime(String date) {

		String currdate = null;

		try {
			Date sd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date);
			currdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sd);

		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return currdate;
	}

	public static java.sql.Date getTwentythDayDate() {
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sqlDate);
			cal.add(Calendar.DATE, -20);
			sqlDate = new java.sql.Date(cal.getTimeInMillis());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return sqlDate;
	}

	public static int getDaysByMonthAndYear(int month, int year) {
		int days = 0;
		try {
			Calendar c1 = Calendar.getInstance();
			c1.clear();
			c1.set(year, (month - 1), 1);
			days = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return days;
	}

	public static List<String> getDateListBetweenDates(String fromDate,
			String toDate) {
		List<String> dateList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date fDate = sdf.parse(fromDate);
			Date tDate = sdf.parse(toDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(fDate);
			cal.add(Calendar.DATE, -1);
			while (cal.getTime().before(tDate)) {
				cal.add(Calendar.DATE, 1);
				if (sdf.format(cal.getTime()) != null)
					dateList.add(sdf.format(cal.getTime()));
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return dateList;
	}

	public static ArrayList<String> getNextAccYearDetails(String curentyear) {

		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_NEXT_ACCADAMIC_YEAR);

			String num = curentyear.substring(3);

			int temper = Integer.parseInt(num) + 1;

			pstmt.setString(1, "ACY" + temper);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				list.add(rs.getString("acadamic_id"));
				list.add(rs.getString("acadamic_year"));

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return list;
	}

	public static List<HelperClassVo> getAllAcademicYear() {

		CallableStatement proc = null;
		List<HelperClassVo> accYear = new ArrayList<HelperClassVo>();
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			proc = conn.prepareCall("{ call getAllAccYear() }");
			proc.execute();
			rs = proc.getResultSet();

			while (rs.next()) {
				HelperClassVo vo = new HelperClassVo();
				vo.setAccId(rs.getString("ACCYEARID"));
				vo.setAccName(rs.getString("acadamic_year"));
				accYear.add(vo);
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		return accYear;
	}

	public static String getCurrentYearIDSelected(HttpServletRequest request)
			throws SQLException {

		String accYear = null;
		try {
			HttpSession session = request.getSession(false);
			accYear = (String) session.getAttribute("accYear");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.getStackTrace();
		}

		return accYear;
	}

	public static String genaratePasswordForTeacher(TeacherRegistrationPojo teaPojo) {

		String pswd = null;
		String[] sp = { "@", "#", "%", "&", "*", "_" };
		Random rand = new Random();

		String pswd1 = teaPojo.getTfastname().toString().substring(teaPojo.getTfastname().length() - 3,	teaPojo.getTfastname().length()).toLowerCase();
		String pswd2 = teaPojo.getTeachermobno().toString().substring(1, 3);

		String pswd4 = sp[rand.nextInt((5 - 1) + 1) + 1];
		/*String pswd5 = teaPojo.getTeacheremail().toString().substring(1, 3)
				.toUpperCase();*/

		pswd = pswd1 + pswd2 + pswd4 ;
		pswd = pswd.replace(" ", "").trim();
		return pswd;
	}
//	public static String generatePasswordForStaff(
//			List<UploadStaffXlsPOJO> successlist) {
//
//		String pswd = null;
//		String[] sp = { "@", "#", "%", "&", "*", "_" };
//		Random rand = new Random();
//
//		String pswd1 = ((UploadStaffXlsPOJO) successlist).getFirstname().toString().substring(successlist.getFirstname().length() - 3,	successlist.getFirstname().length()).toLowerCase();
//		String pswd2 = successlist.getMobileno().toString().substring(1, 3);
//
//		String pswd4 = sp[rand.nextInt((5 - 1) + 1) + 1];
//		String pswd5 = successlist.getEmail().toString().substring(1, 3)
//				.toUpperCase();
//
//		pswd = pswd1 + pswd2 + pswd4 + pswd5;
//		pswd = pswd.replace(" ", "").trim();
//		return pswd;
//	}
	
	
	
	public static String getLastThirtyDateFromNow(String date) {
		String lastThirdDate = "";
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {

			Calendar now = Calendar.getInstance();
			Date fDate = sd.parse(date);
			now.setTime(fDate);
			now.add(Calendar.DATE, -30);
			lastThirdDate = sd.format(now.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lastThirdDate;
	}
	
	public static String getWeekDay(String date) {
		
		String finalDay=null;
		
		try {
			  SimpleDateFormat format1=new SimpleDateFormat("dd-MM-yyyy");
			  Date dt1;
			  dt1 = format1.parse(date);
			  DateFormat format2=new SimpleDateFormat("EEEE"); 
			   finalDay=format2.format(dt1);
			  
			  System.out.println("finalDay :: "+finalDay);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalDay;
	}
	
	public static String addTimeArray(String[] getTime) {
		String time = null;
		long cal_time = 0l;
		TimeZone UTC = TimeZone.getTimeZone("UTC");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setTimeZone(UTC);
		Date d = null;
		try {
			for (int i = 0; i < getTime.length; i++) {
				d = sdf.parse(getTime[i]);
				cal_time = cal_time + d.getTime();
			}
			long seconds = (cal_time / 1000);
			long lhours = seconds / 3600;
			long min = seconds % 3600;
			long lmin1 = min / 60;
			long lsec = min % 60;

			String hours = (lhours + "").length() > 1 ? (lhours + "") : "0"
					+ lhours;
			String min1 = (lmin1 + "").length() > 1 ? (lmin1 + "") : "0"
					+ lmin1;
			String sec = (lsec + "").length() > 1 ? (lsec + "") : "0" + lsec;
			time = hours + ":" + min1 + ":" + sec;

		} catch (ParseException e) {
			
			e.printStackTrace();
		}

		return time;

	}
	public static String className(String name){
		
		String className = null;
		if(name.equalsIgnoreCase("1")){
			 className = "Pre-KG";
		}
		else if(name.equalsIgnoreCase("2")){
			 className = "LKG";
		}
		else if(name.equalsIgnoreCase("3")){
			className = "UKG";
		}
		else if(name.equalsIgnoreCase("4")){
			className = "I";
		}
		else if(name.equalsIgnoreCase("5")){
			className = "II";
		}
		else if(name.equalsIgnoreCase("6")){
			className = "III";
		}
		else if(name.equalsIgnoreCase("7")){
			className = "IV";
		}
		else if(name.equalsIgnoreCase("8")){
			className = "V";
		}
		else if(name.equalsIgnoreCase("9")){
			className = "VI";
		}
		else if(name.equalsIgnoreCase("10")){
			className = "VII";
		}
		else if(name.equalsIgnoreCase("11")){
			className = "VIII";
		}
		else if(name.equalsIgnoreCase("12")){
			className = "IX";
		}
		else if(name.equalsIgnoreCase("13")){
			className = "X";
		}
		else if(name.equalsIgnoreCase("14")){
			className = "XI";
		}
		else if(name.equalsIgnoreCase("15")){
			className = "XII";
		}
		
		return className;
	}
public static int getPastDateofAcademicYear(HttpServletRequest request) {
		
		
	java.util.Date sd = new java.util.Date();
	java.sql.Date currdate = null;
		CallableStatement proc = null;
		PreparedStatement pstmt=null;
		int pastDate =0;
		ResultSet rs = null;
		Connection conn = null;
		HttpSession ses = request.getSession(false);
		try {
		String academic_year=(String) ses.getAttribute("current_academicYear");
			
			currdate = new java.sql.Date(sd.getTime());
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt=conn.prepareStatement("SELECT acadamic_id ACCYEARID,startDate,endDate,acadamic_year FROM campus_acadamicyear WHERE acadamic_id=?");
			pstmt.setString(1, academic_year);
			
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				String startDate=rs.getString("startDate");
				List<String> dateList = new ArrayList<String>();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
					Date fDate = sdf.parse(startDate);
					Date tDate = sdf.parse(currdate.toString());
					Calendar cal = Calendar.getInstance();
					cal.setTime(fDate);
					cal.add(Calendar.DATE, -1);
					while (cal.getTime().before(tDate)) {
						cal.add(Calendar.DATE, 1);
						if (sdf.format(cal.getTime()) != null)
							dateList.add(sdf.format(cal.getTime()));
					}
				pastDate=dateList.size();
				pastDate=-pastDate;
				
				System.out.println("pastDate"+pastDate);
				if(dateList.size()==0){
					 fDate = sdf.parse(currdate.toString());
					 tDate = sdf.parse(startDate);
					 cal = Calendar.getInstance();
					cal.setTime(fDate);
					cal.add(Calendar.DATE, -1);
					while (cal.getTime().before(tDate)) {
						cal.add(Calendar.DATE, 1);
						if (sdf.format(cal.getTime()) != null)
							dateList.add(sdf.format(cal.getTime()));
					}
				pastDate=dateList.size()-2;
				}
				
			}
			
			
			  
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		return pastDate;
	}

public static List<HelperClassVo> getAllAcademicYearStartDate() {

	CallableStatement proc = null;
	List<HelperClassVo> startDate = new ArrayList<HelperClassVo>();
	ResultSet rs = null;
	Connection conn = null;
	try {

		conn = JDBCConnection.getSeparateConnection();
		proc = conn.prepareCall("{ CALL getCurrentAccYearStartDateAndEndDate() }");
		proc.execute();
		rs = proc.getResultSet();

		while (rs.next()) {
			HelperClassVo vo = new HelperClassVo();
			vo.setStartDate(rs.getString("startDate"));
			startDate.add(vo);
		}
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (proc != null && !proc.isClosed()) {
				proc.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return startDate;
}

public static List<HelperClassVo> getAllAcademicYearEndDate() {

	CallableStatement proc = null;
	List<HelperClassVo> endDate = new ArrayList<HelperClassVo>();
	ResultSet rs = null;
	Connection conn = null;
	try {

		conn = JDBCConnection.getSeparateConnection();
		proc = conn.prepareCall("{ CALL getCurrentAccYearStartDateAndEndDate() }");
		proc.execute();
		rs = proc.getResultSet();


		while (rs.next()) {
			HelperClassVo vo = new HelperClassVo();
			vo.setEndDate(rs.getString("endDate"));
			endDate.add(vo);
		}
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (proc != null && !proc.isClosed()) {
				proc.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return endDate;
}

public static int getForDateofAcademicYear(HttpServletRequest request) {
	
	
	java.util.Date sd = new java.util.Date();
	java.sql.Date currdate = null;
	
	PreparedStatement pstmt=null;
		
		int forDate =0;
		ResultSet rs = null;
		Connection conn = null;
		
		HttpSession ses = request.getSession(false);
		try {
		String academic_year=(String) ses.getAttribute("current_academicYear");
			
			currdate = new java.sql.Date(sd.getTime());
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement("SELECT acadamic_id ACCYEARID,startDate,endDate,acadamic_year FROM campus_acadamicyear WHERE acadamic_id=?");
			pstmt.setString(1, academic_year);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				String endDate=rs.getString("endDate");
				List<String> dateList = new ArrayList<String>();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
					Date fDate = sdf.parse(currdate.toString());
					Date tDate = sdf.parse(endDate);
					Calendar cal = Calendar.getInstance();
					cal.setTime(fDate);
					cal.add(Calendar.DATE, -1);
					while (cal.getTime().before(tDate)) {
						cal.add(Calendar.DATE, 1);
						if (sdf.format(cal.getTime()) != null)
							dateList.add(sdf.format(cal.getTime()));
					}
				forDate=dateList.size();	
				if(forDate==0){
					 fDate = sdf.parse(endDate);
					 tDate = sdf.parse(currdate.toString());
					 cal = Calendar.getInstance();
					cal.setTime(fDate);
					cal.add(Calendar.DATE, -1);
					while (cal.getTime().before(tDate)) {
						cal.add(Calendar.DATE, 1);
						if (sdf.format(cal.getTime()) != null)
							dateList.add(sdf.format(cal.getTime()));
					}
					
					forDate=dateList.size();
					forDate=-forDate+2;
				}
			}
			
			
			  
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}

		return forDate;
	}
public static int getForDateofAcademicYear(String academic_year) {
	
	
	java.util.Date sd = new java.util.Date();
	java.sql.Date currdate = null;
	
	PreparedStatement pstmt=null;
		
		int forDate =0;
		ResultSet rs = null;
		Connection conn = null;
		
	
		try {
		
			
			currdate = new java.sql.Date(sd.getTime());
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement("SELECT acadamic_id ACCYEARID,startDate,endDate,acadamic_year FROM campus_acadamicyear WHERE acadamic_id=?");
			pstmt.setString(1, academic_year);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				String endDate=rs.getString("endDate");
				List<String> dateList = new ArrayList<String>();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
					Date fDate = sdf.parse(currdate.toString());
					Date tDate = sdf.parse(endDate);
					Calendar cal = Calendar.getInstance();
					cal.setTime(fDate);
					cal.add(Calendar.DATE, -1);
					while (cal.getTime().before(tDate)) {
						cal.add(Calendar.DATE, 1);
						if (sdf.format(cal.getTime()) != null)
							dateList.add(sdf.format(cal.getTime()));
					}
				forDate=dateList.size();	
				if(forDate==0){
					 fDate = sdf.parse(endDate);
					 tDate = sdf.parse(currdate.toString());
					 cal = Calendar.getInstance();
					cal.setTime(fDate);
					cal.add(Calendar.DATE, -1);
					while (cal.getTime().before(tDate)) {
						cal.add(Calendar.DATE, 1);
						if (sdf.format(cal.getTime()) != null)
							dateList.add(sdf.format(cal.getTime()));
					}
					
					forDate=dateList.size();
					forDate=-forDate+2;
				}
			}
			
			
			  
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}

		return forDate;
	}
public static int getPastDateofAcademicYear(String academic_year) {
	
	
	java.util.Date sd = new java.util.Date();
	java.sql.Date currdate = null;
		CallableStatement proc = null;
		PreparedStatement pstmt=null;
		int pastDate =0;
		ResultSet rs = null;
		Connection conn = null;
		try {
			
			currdate = new java.sql.Date(sd.getTime());
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt=conn.prepareStatement("SELECT acadamic_id ACCYEARID,startDate,endDate,acadamic_year FROM campus_acadamicyear WHERE acadamic_id=?");
			pstmt.setString(1, academic_year);
			
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				String startDate=rs.getString("startDate");
				List<String> dateList = new ArrayList<String>();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
					Date fDate = sdf.parse(startDate);
					Date tDate = sdf.parse(currdate.toString());
					Calendar cal = Calendar.getInstance();
					cal.setTime(fDate);
					cal.add(Calendar.DATE, -1);
					while (cal.getTime().before(tDate)) {
						cal.add(Calendar.DATE, 1);
						if (sdf.format(cal.getTime()) != null)
							dateList.add(sdf.format(cal.getTime()));
					}
				pastDate=dateList.size();
				pastDate=-pastDate;
				
				System.out.println("pastDate"+pastDate);
				if(dateList.size()==0){
					 fDate = sdf.parse(currdate.toString());
					 tDate = sdf.parse(startDate);
					 cal = Calendar.getInstance();
					cal.setTime(fDate);
					cal.add(Calendar.DATE, -1);
					while (cal.getTime().before(tDate)) {
						cal.add(Calendar.DATE, 1);
						if (sdf.format(cal.getTime()) != null)
							dateList.add(sdf.format(cal.getTime()));
					}
				pastDate=dateList.size()-2;
				}
				
			}
			
			
			  
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		return pastDate;
	}


public static String getAcademicYearFace(String academic_year) {
	
	
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn = null;
		String academicYearFace=null;
		try {
			
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt=conn.prepareStatement("SELECT acadamic_year FROM campus_acadamicyear WHERE acadamic_id=?");
			pstmt.setString(1, academic_year);
			
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				academicYearFace=rs.getString("acadamic_year");
				}
			
			
			  
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		return academicYearFace;
	}

public static int totalDaysBetweenTwoDates(String startdate,String enddate) throws ParseException{
	
	 Calendar cal1 = new GregorianCalendar();
     Calendar cal2 = new GregorianCalendar();

     SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

     Date date = sdf.parse(startdate);
     cal1.setTime(date);
     date = sdf.parse(enddate);
     cal2.setTime(date);

	return daysBetween(cal1.getTime(),cal2.getTime());
}

public static int daysBetween(Date d1, Date d2){
    return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
}
 


public static List<HelperClassVo> getAllLocation() {

	CallableStatement proc = null;                                                                                               
	List<HelperClassVo> location = new ArrayList<HelperClassVo>();
	ResultSet rs = null;
	Connection conn = null;
	try {

		conn = JDBCConnection.getSeparateConnection();
		proc = conn.prepareCall("{ call getAllSchoolList() }");
		proc.execute();
		rs = proc.getResultSet();

		while (rs.next()) {
			HelperClassVo vo = new HelperClassVo();
			vo.setLocationId(rs.getString("Location_Id"));
			vo.setLocationName(rs.getString("Location_Name"));
			location.add(vo);
		}
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (proc != null && !proc.isClosed()) {
				proc.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return location;
}
public static List<classVo> getAllClassList(String location) {

	PreparedStatement proc = null;                                                                                               
	List<classVo> classList = new ArrayList<classVo>();
	ResultSet rs = null;
	Connection conn = null;
	try {

		conn = JDBCConnection.getSeparateConnection();
		proc = conn.prepareStatement("SELECT * FROM `campus_classdetail` WHERE locationId=? order by LENGTH(classdetail_id_int),classdetail_id_int");
		proc.setString(1, location);
		proc.execute();
		rs = proc.getResultSet();

		while (rs.next()) {
			classVo vo = new classVo();
			vo.setClassId(rs.getString("classdetail_id_int"));
			vo.setClassName(rs.getString("classdetails_name_var"));
			classList.add(vo);
		}
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (proc != null && !proc.isClosed()) {
				proc.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return classList;
}

public static List<FeeNameVo> getTermList(String location,String accyear) {

	PreparedStatement proc = null;                                                                                               
	List<FeeNameVo> TermList = new ArrayList<FeeNameVo>();
	ResultSet rs = null;
	Connection conn = null;
	try {

		conn = JDBCConnection.getSeparateConnection();
		proc = conn.prepareStatement("SELECT * FROM `campus_fee_termdetails` WHERE locationId=? AND accyear=? order by LENGTH(termid),termid");
		proc.setString(1, location);
		proc.setString(2, accyear);
		proc.execute();
		rs = proc.getResultSet();

		while (rs.next()) {
			FeeNameVo vo = new FeeNameVo();
			vo.setTermId(rs.getString("termid"));
			vo.setTerm(rs.getString("termname"));
			TermList.add(vo);
		}
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (proc != null && !proc.isClosed()) {
				proc.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return TermList;
}
public static List<HelperClassVo> getHouse(String locationId, String accyearid) {

	CallableStatement proc = null;                                                                                               
	List<HelperClassVo> house = new ArrayList<HelperClassVo>();
	ResultSet rs = null;
	Connection conn = null;
	try {

		conn = JDBCConnection.getSeparateConnection();
		proc = conn.prepareCall("{ call getHouse(?,?) }");
		proc.setString(1, locationId);
		proc.setString(2, accyearid);
		proc.execute();
		rs = proc.getResultSet();

		while (rs.next()) {
			HelperClassVo vo = new HelperClassVo();
			vo.setHouseId(rs.getString("house_id"));
			vo.setHouseName(rs.getString("housename"));
			house.add(vo);
		}
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (proc != null && !proc.isClosed()) {
				proc.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return house;
}
	public static String[] removeElements(String[] input, String deleteMe) {
    if (input != null) {
        List<String> list = new ArrayList<String>(Arrays.asList(input));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(deleteMe)) {
                list.remove(i);
            }
        }
        return list.toArray(new String[0]);
    } else {
        return new String[0];
    }
}


	public static String getMothNumberByShortName(String stmonth) {
		String monthname = null;
		
		if(stmonth.toLowerCase().startsWith("jan")){
			monthname = "01";
		}
		else if(stmonth.toLowerCase().startsWith("feb")){
			monthname ="02";
		}
		else if(stmonth.toLowerCase().startsWith("mar")){
			monthname = "03";
		}else if(stmonth.toLowerCase().startsWith("apr")){
			monthname = "04";
		}else if(stmonth.toLowerCase().startsWith("may")){
			monthname = "05";
		}else if(stmonth.toLowerCase().startsWith("jun")){
			monthname = "06";
		}
		else if(stmonth.toLowerCase().startsWith("jul")){
			monthname = "07";
		}
		else if(stmonth.toLowerCase().startsWith("aug")){
			monthname = "08";
		}
		else if(stmonth.toLowerCase().startsWith("sep")){
			monthname ="09";
		}
		else if(stmonth.toLowerCase().startsWith("oct")){
			monthname = "10";
		}
		else if(stmonth.toLowerCase().startsWith("nov")){
			monthname = "11";
		}
		else if(stmonth.toLowerCase().startsWith("dec")){
			monthname = "12";
		}
		return monthname;
	}
	
	
	

	public static String getSchoolName(String locationId) {
		
		
		CallableStatement proc = null; 
		ResultSet rs = null;
		Connection conn = null;
		String schoolName=null;
		try {
			
			
			conn = JDBCConnection.getSeparateConnection();
			
			proc=conn.prepareCall("{ call getSchoolName(?) }");
			proc.setString(1, locationId);
			
			
			rs = proc.executeQuery();

			while (rs.next()) {
				
				schoolName=rs.getString("Location_Name");
				}
			
			
			  
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		return schoolName;
	}

	public static String convertExceltoUIformat(String ddDate) {
		String dateformat = null;
		
		System.out.println("In Helper Class="+ddDate);
		dateformat = ddDate.split("/")[1]+"-"+ddDate.split("/")[0]+"-"+ddDate.split("/")[2];
		return dateformat;
	}

	public static String validateDate(String date) {
		String flag="false";
		if(Integer.parseInt(date.split("-")[0])<=31 && Integer.parseInt(date.split("-")[1]) <=12){
			flag="true";
		}
		
		return flag;
	}

	@Override
	public int compare(LibrarySearchIssueDetailsVO o1, LibrarySearchIssueDetailsVO o2) {
		return o1.getSubname().compareTo(o2.getSubname());
	}
	
public static List<TeacherVo> getAllTeacherList() {
		
		
		CallableStatement proc = null; 
		ResultSet rs = null;
		Connection conn = null;
		List<TeacherVo> teacherList=new ArrayList<TeacherVo>();
		try {
			
			
			conn = JDBCConnection.getSeparateConnection();
			
			proc=conn.prepareCall("{ call getAllTeacher() }");
			
			rs = proc.executeQuery();

			while (rs.next()) {
				TeacherVo vo=new TeacherVo();
				vo.setTeacherId(rs.getString("TeacherID"));				
				vo.setTeacherName(rs.getString("Abbreviative_Id")+"-"+rs.getString("FirstName")+" "+rs.getString("LastName"));
				vo.setAbbrvation(rs.getString("Abbreviative_Id"));
				teacherList.add(vo);
			
			}
			
			
			  
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		return teacherList;
	}
public static List<TeacherVo> getTodayTeacherList(String object,String timetableId) {
	
	PreparedStatement pstmt=null;
	ResultSet prs = null;

	PreparedStatement proc = null; 
	ResultSet rs = null;
	Connection conn = null;
	String teacherID="";
	List<TeacherVo> teacherList=new ArrayList<TeacherVo>();
	try {
		
		
		conn = JDBCConnection.getSeparateConnection();
		
		pstmt=conn.prepareStatement("SELECT GROUP_CONCAT(DISTINCT "+object+") AS teacher FROM today_timetable WHERE id!=?");
		pstmt.setString(1, timetableId);
		prs=pstmt.executeQuery();
		while(prs.next()){
		
			for(int k=0;k<prs.getString("teacher").split(",").length;k++){
				teacherID+="'"+prs.getString("teacher").split(",")[k]+"'"+",";
			}
			teacherID = teacherID.replaceAll(",$", "");
		}
		
		proc=conn.prepareStatement("SELECT cts.TeacherID,Abbreviative_Id,FirstName,LastName,registerId FROM `campus_teacher_attendence` cts JOIN campus_teachers ct  ON ct.TeacherID=cts.TeacherID WHERE cts.AttendenceDate=CURDATE() AND cts.AttendenceStatus='Present' AND ct.teachingType='TEACHING' AND cts.TeacherID NOT IN("+teacherID+")");
		
		System.out.println(proc);
	
		rs = proc.executeQuery();

		while (rs.next()) {
			TeacherVo vo=new TeacherVo();
			vo.setTeacherId(rs.getString("TeacherID"));				
			vo.setTeacherName(rs.getString("Abbreviative_Id")+"-"+rs.getString("FirstName")+" "+rs.getString("LastName"));
			vo.setAbbrvation(rs.getString("Abbreviative_Id"));
			teacherList.add(vo);
		
		}
		
		
		  
		
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (proc != null && !proc.isClosed()) {
				proc.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return teacherList;
}

public static Comparator<TimeTableVo> TimeTableVoComparator = new Comparator<TimeTableVo>() {

	public int compare(TimeTableVo s1, TimeTableVo s2) {
	   String Dayname1 = s1.getDayid().toUpperCase();
	   String Dayname12 = s2.getDayid().toUpperCase();

	   //ascending order
	   return Dayname1.compareTo(Dayname12);

	   //descending order
	   //return StudentName2.compareTo(StudentName1);
    }
};

public static List<TeacherVo> getTodayTeacherListToday() {
	
	

	CallableStatement proc = null; 
	ResultSet rs = null;
	Connection conn = null;
	List<TeacherVo> teacherList=new ArrayList<TeacherVo>();
	try {
		
		
		conn = JDBCConnection.getSeparateConnection();
		
		
		proc=conn.prepareCall("{CALL getTodayTeacher()}");
		
		System.out.println(proc);
	
		rs = proc.executeQuery();

		while (rs.next()) {
			TeacherVo vo=new TeacherVo();
			vo.setTeacherId(rs.getString("TeacherID"));				
			vo.setTeacherName(rs.getString("Abbreviative_Id")+"-"+rs.getString("FirstName")+" "+rs.getString("LastName"));
			vo.setAbbrvation(rs.getString("Abbreviative_Id"));
			teacherList.add(vo);
		
		}
		
		
		  
		
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (proc != null && !proc.isClosed()) {
				proc.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return teacherList;
}

public static List<TeacherVo> getTodayForSubstitutionTeacherList(String object,String timetableId) {
	
	PreparedStatement pstmt=null;
	ResultSet prs = null;
	
	PreparedStatement pstmt0=null;
	ResultSet prs0 = null;

	PreparedStatement proc = null; 
	ResultSet rs = null;
	Connection conn = null;
	String teacherID="";
	List<TeacherVo> teacherList=new ArrayList<TeacherVo>();
	try {
		
		
		conn = JDBCConnection.getSeparateConnection();
		
		pstmt0=conn.prepareStatement("SELECT classid From campus_timetable_student WHERE timetable_id=?");
		pstmt0.setString(1, timetableId);
		prs0=pstmt0.executeQuery();
		if(prs0.next()) {
		
		pstmt=conn.prepareStatement("SELECT GROUP_CONCAT(DISTINCT "+object+") AS teacher FROM today_timetable WHERE id!=?");
		pstmt.setString(1, timetableId);
		prs=pstmt.executeQuery();
		while(prs.next()){
		
			for(int k=0;k<prs.getString("teacher").split(",").length;k++){
				teacherID+="'"+prs.getString("teacher").split(",")[k]+"'"+",";
			}
			teacherID = teacherID.replaceAll(",$", "");
		}
		String query="";
		if(prs0.getString("classid").equalsIgnoreCase("CCD1") || prs0.getString("classid").equalsIgnoreCase("CCD2") || prs0.getString("classid").equalsIgnoreCase("CCD3")) {
			query="SELECT cts.TeacherID,Abbreviative_Id,FirstName,LastName,registerId FROM `campus_teacher_attendence` cts JOIN campus_teachers ct  ON ct.TeacherID=cts.TeacherID join campus_designation cd on cd.DesignationCode=ct.designation WHERE ct.isActive = 'Y' AND cd.designationName='KG' AND  cts.AttendenceDate=CURDATE() AND cts.AttendenceStatus='Present' AND ct.teachingType='TEACHING' AND cts.TeacherID NOT IN("+teacherID+")";
		}
		else if(prs0.getString("classid").equalsIgnoreCase("CCD4") || prs0.getString("classid").equalsIgnoreCase("CCD5") || prs0.getString("classid").equalsIgnoreCase("CCD6") || prs0.getString("classid").equalsIgnoreCase("CCD7") || prs0.getString("classid").equalsIgnoreCase("CCD8")) {
			query="SELECT cts.TeacherID,Abbreviative_Id,FirstName,LastName,registerId FROM `campus_teacher_attendence` cts JOIN campus_teachers ct  ON ct.TeacherID=cts.TeacherID join campus_designation cd on cd.DesignationCode=ct.designation WHERE ct.isActive = 'Y' AND cd.designationName!='KG' AND cd.designationName !='PGT' AND cd.designationName !='TGT'   AND  cts.AttendenceDate=CURDATE() AND cts.AttendenceStatus='Present' AND ct.teachingType='TEACHING' AND cts.TeacherID NOT IN("+teacherID+")";
		}
		else if(prs0.getString("classid").equalsIgnoreCase("CCD9") || prs0.getString("classid").equalsIgnoreCase("CCD10") || prs0.getString("classid").equalsIgnoreCase("CCD11") || prs0.getString("classid").equalsIgnoreCase("CCD12") || prs0.getString("classid").equalsIgnoreCase("CCD13")) {
			query="SELECT cts.TeacherID,Abbreviative_Id,FirstName,LastName,registerId FROM `campus_teacher_attendence` cts JOIN campus_teachers ct  ON ct.TeacherID=cts.TeacherID join campus_designation cd on cd.DesignationCode=ct.designation WHERE ct.isActive = 'Y' AND cd.designationName!='KG' AND cd.designationName !='PGT'  AND  cts.AttendenceDate=CURDATE() AND cts.AttendenceStatus='Present' AND ct.teachingType='TEACHING' AND cts.TeacherID NOT IN("+teacherID+")";
		}
		else {
			query="SELECT cts.TeacherID,Abbreviative_Id,FirstName,LastName,registerId FROM `campus_teacher_attendence` cts JOIN campus_teachers ct  ON ct.TeacherID=cts.TeacherID join campus_designation cd on cd.DesignationCode=ct.designation WHERE ct.isActive = 'Y' AND cd.designationName!='KG' AND cd.designationName !='PRT' AND  cts.AttendenceDate=CURDATE() AND cts.AttendenceStatus='Present' AND ct.teachingType='TEACHING' AND cts.TeacherID NOT IN("+teacherID+")";
		}
		proc=conn.prepareStatement(query);
		
	
	
		rs = proc.executeQuery();

		while (rs.next()) {
			TeacherVo vo=new TeacherVo();
			vo.setTeacherId(rs.getString("TeacherID"));				
			vo.setTeacherName(rs.getString("Abbreviative_Id")+"-"+rs.getString("FirstName")+" "+rs.getString("LastName"));
			vo.setAbbrvation(rs.getString("Abbreviative_Id"));
			teacherList.add(vo);
		
		}
		
	}
		  
		
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (proc != null && !proc.isClosed()) {
				proc.close();
			}
			
			
			
			if (prs != null && !prs.isClosed()) {
				prs.close();
			}
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
			
			if (prs0 != null && !prs0.isClosed()) {
				prs0.close();
			}
			if (pstmt0 != null && !pstmt0.isClosed()) {
				pstmt0.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return teacherList;
}
public static int getDiffYears(Date first, Date last) {
    Calendar a = getCalendar(first);
    Calendar b = getCalendar(last);
    int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
    if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || 
        (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
        diff--;
    }
    return diff;
}
public static Calendar getCalendar(Date date) {
    Calendar cal = Calendar.getInstance(Locale.US);
    cal.setTime(date);
    return cal;
}
public static List<BankVo> getBankDetails() {
	
	
 
	PreparedStatement proc = null; 
	ResultSet rs = null;
	Connection conn = null;
	List<BankVo> bankList=new ArrayList<BankVo>();
	try {
		
		
		conn = JDBCConnection.getSeparateConnection();
		
		
		proc=conn.prepareCall("SELECT * FROM `campus_online_bank` where isActive='Y'");
		
		System.out.println(proc);
	
		rs = proc.executeQuery();

		while (rs.next()) {
			BankVo vo=new BankVo();
			vo.setSno(rs.getString("Sno"));				
			vo.setBank_name(rs.getString("bank_name"));
			vo.setBank_url(rs.getString("bank_url"));
			bankList.add(vo);
		
		}
		
		
		  
		
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (proc != null && !proc.isClosed()) {
				proc.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return bankList;
}
public static String gettermName(String termid) {
	
	
	PreparedStatement proc = null; 
	ResultSet rs = null;
	Connection conn = null;
	String termName=null;
	try {
		
		
		conn = JDBCConnection.getSeparateConnection();
		
		proc=conn.prepareCall("SELECT termname FROM `campus_fee_termdetails` WHERE termid=?");
		proc.setString(1, termid);
		
		
		rs = proc.executeQuery();

		while (rs.next()) {
			
			termName=rs.getString("termname");
			}
		
		
		  
		
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (proc != null && !proc.isClosed()) {
				proc.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return termName;
}

public static String getTransporttermName(String termid) {
	PreparedStatement proc = null; 
	ResultSet rs = null;
	Connection conn = null;
	String termName=null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		proc=conn.prepareCall("SELECT termname FROM `campus_fee_transport_termdetails` WHERE termid=?");
		proc.setString(1, termid);
		rs = proc.executeQuery();
		while (rs.next()) {
			termName=rs.getString("termname");
			}
		
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (proc != null && !proc.isClosed()) {
				proc.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return termName;
}
public static String getRelegionName(String religionId) {
	PreparedStatement proc = null; 
	ResultSet rs = null;
	Connection conn = null;
	String religion=null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		proc=conn.prepareCall("SELECT religion FROM `campus_religion` WHERE religionId=?");
		proc.setString(1, religionId);
		rs = proc.executeQuery();
		while (rs.next()) {
			religion=rs.getString("religion");
			}
		
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (proc != null && !proc.isClosed()) {
				proc.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return religion;
}
public static String getCasteName(String casteId) {
	PreparedStatement proc = null; 
	ResultSet rs = null;
	Connection conn = null;
	String caste=null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		proc=conn.prepareCall("SELECT caste FROM `campus_caste` WHERE casteId=?");
		proc.setString(1, casteId);
		rs = proc.executeQuery();
		while (rs.next()) {
			caste=rs.getString("caste");
			}
		
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (proc != null && !proc.isClosed()) {
				proc.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return caste;
}
public static String getCasteCategoryName(String castCatId) {
	PreparedStatement proc = null; 
	ResultSet rs = null;
	Connection conn = null;
	String casteCategory=null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		proc=conn.prepareCall("SELECT casteCategory FROM `campus_caste_category` WHERE castCatId=?");
		proc.setString(1, castCatId);
		rs = proc.executeQuery();
		while (rs.next()) {
			casteCategory=rs.getString("casteCategory");
			}
		
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (proc != null && !proc.isClosed()) {
				proc.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return casteCategory;
}
public static String getOccupationName(String occupationId) {
	PreparedStatement proc = null; 
	ResultSet rs = null;
	Connection conn = null;
	String occupation=null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		proc=conn.prepareCall("SELECT occupation FROM `campus_occupation` WHERE occupationId=?");
		proc.setString(1, occupationId);
		rs = proc.executeQuery();
		while (rs.next()) {
			occupation=rs.getString("occupation");
			}
		
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (proc != null && !proc.isClosed()) {
				proc.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return occupation;
}
public static String getLocationName(String Location_Id) {
	PreparedStatement proc = null; 
	ResultSet rs = null;
	Connection conn = null;
	String Location_name=null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		proc=conn.prepareCall("SELECT Location_name FROM `campus_location` WHERE Location_Id=?");
		proc.setString(1, Location_Id);
		rs = proc.executeQuery();
		while (rs.next()) {
			Location_name=rs.getString("Location_name");
			}
		
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (proc != null && !proc.isClosed()) {
				proc.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return Location_name;
}

public static String getClassName(String classId,String locationId) {


	PreparedStatement pstmt=null;
	ResultSet rs = null;
	Connection conn = null;
	String classname=null;
	try {


		conn = JDBCConnection.getSeparateConnection();

		pstmt=conn.prepareStatement("SELECT classdetails_name_var FROM campus_classdetail WHERE classdetail_id_int=? AND locationId=?");
		pstmt.setString(1, classId);
		pstmt.setString(2, locationId);


		rs = pstmt.executeQuery();

		while (rs.next()) {

			classname=rs.getString("classdetails_name_var");
		}

	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return classname;
}

public static String getDesignationName(String DesignationCode) {


	PreparedStatement pstmt=null;
	ResultSet rs = null;
	Connection conn = null;
	String designationName=null;
	try {


		conn = JDBCConnection.getSeparateConnection();

		pstmt=conn.prepareStatement("SELECT designationName FROM campus_designation WHERE DesignationCode=?");
		pstmt.setString(1, DesignationCode);


		rs = pstmt.executeQuery();

		while (rs.next()) {

			designationName=rs.getString("designationName");
		}

	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
	}
	return designationName;
}

public static Comparator<ReportMenuVo> SubjectComparator = new Comparator<ReportMenuVo>() {

	public int compare(ReportMenuVo s1, ReportMenuVo s2) {
		String Subname1 = s1.getSubjectName().toUpperCase();
		String Subname2 = s2.getSubjectName().toUpperCase();

		//ascending order
		return Subname1.compareTo(Subname2);

		//descending order
		//return StudentName2.compareTo(StudentName1);
	}
};
public static String convertUIToDatabaseSettingReports(String date) {

	String currdate = null;

	try {
		if(date.contains("-")){
			System.out.println("it contais - ");
			Date sd = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			currdate = new SimpleDateFormat("yyyy-MM-dd").format(sd);
			
		}else{
			System.out.println("it contais /");
			Date sd = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			currdate = new SimpleDateFormat("yyyy-MM-dd").format(sd);
		}
	} catch (ParseException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	return currdate;
}
public static String getWeekDaySettingReports(String date) {
	String finalDay=null;
	try {
		if(date.contains("-")){
			 SimpleDateFormat format1=new SimpleDateFormat("dd-MM-yyyy");
			  Date dt1;
			  dt1 = format1.parse(date);
			  DateFormat format2=new SimpleDateFormat("EEEE"); 
			   finalDay=format2.format(dt1);
			  
			  System.out.println("finalDay :: "+finalDay);
		}
		else{
		  SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
		  Date dt1;
		  dt1 = format1.parse(date);
		  DateFormat format2=new SimpleDateFormat("EEEE"); 
		   finalDay=format2.format(dt1);
		  System.out.println("finalDay :: "+finalDay);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return finalDay;
}
}