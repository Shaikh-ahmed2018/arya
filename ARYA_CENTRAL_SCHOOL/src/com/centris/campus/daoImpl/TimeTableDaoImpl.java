package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.centris.campus.dao.TimeTableDao;
import com.centris.campus.pojo.TeacherTimeTablePojo;
import com.centris.campus.pojo.TimeTablePojo;
import com.centris.campus.util.FeeCollectionSqlUtils;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.TimeTableSqlConstants;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.TeacherTimeTableVo;
import com.centris.campus.vo.TimeTableVo;
import com.centris.campus.vo.ViewallSubjectsVo;

public class TimeTableDaoImpl implements TimeTableDao {
	private static final Logger logger = Logger
			.getLogger(TimeTableDaoImpl.class);

	@Override
	public synchronized ArrayList<TimeTableVo> getTimeTableDetails(String timetableId,String classid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		//ps_period=null,ps_period1=null,ps_period2=null,ps_period3=null,ps_period4=null,ps_period5=null,ps_period6=null,ps_period7=null,ps_period8=null;
		ResultSet rst = null;
		//rs_period=null,rs_period1=null,rs_period2=null,rs_period3=null,rs_period4=null,rs_period5=null,rs_period6=null,rs_period7=null,rs_period8=null;
		
		PreparedStatement pstmt1 = null;
		ResultSet rst1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rst2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rst3 = null;
		
		int count = 0;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection.prepareStatement(TimeTableSqlConstants.TIMETABLE_DETAILS_COUNT);
			pstmt.setString(1, timetableId.trim());
			rst = pstmt.executeQuery();
			
			while (rst.next()) {
				count = rst.getInt(1);
			}
			
			if (count > 0) {
				pstmt1 = connection.prepareStatement(TimeTableSqlConstants.TIMETABLE_GET_DETAILS);
				pstmt1.setString(1, timetableId.trim());
				//pstmt1.setString(2, classid);
			
							
				rst1 = pstmt1.executeQuery();

				
				while(rst1.next()) {
					
					TimeTableVo voObj = new TimeTableVo();
					voObj.setDayid(rst1.getString("daycode"));
					voObj.setDayname(rst1.getString("dayname"));
					
					
					voObj.setPeriodId11(rst1.getString("period1").split(",")[0]);
					voObj.setPeriodId21(rst1.getString("period2").split(",")[0]);
					voObj.setPeriodId31(rst1.getString("period3").split(",")[0]);
					voObj.setPeriodId41(rst1.getString("period4").split(",")[0]);
					voObj.setPeriodId51(rst1.getString("period5").split(",")[0]);
					voObj.setPeriodId61(rst1.getString("period6").split(",")[0]);
					voObj.setPeriodId71(rst1.getString("period7").split(",")[0]);
					voObj.setPeriodId81(rst1.getString("period8").split(",")[0]);
					voObj.setPeriodId91(rst1.getString("period9").split(",")[0]);
					
					
					voObj.setTeacherId11(rst1.getString("tperiod1").split(",")[0]);
					voObj.setTeacherId21(rst1.getString("tperiod2").split(",")[0]);
					voObj.setTeacherId31(rst1.getString("tperiod3").split(",")[0]);
					voObj.setTeacherId41(rst1.getString("tperiod4").split(",")[0]);
					voObj.setTeacherId51(rst1.getString("tperiod5").split(",")[0]);
					voObj.setTeacherId61(rst1.getString("tperiod6").split(",")[0]);
					voObj.setTeacherId71(rst1.getString("tperiod7").split(",")[0]);
					voObj.setTeacherId81(rst1.getString("tperiod8").split(",")[0]);
					voObj.setTeacherId91(rst1.getString("tperiod9").split(",")[0]);
					
					
					
					
					
					
					
					voObj.setPeriodId12(rst1.getString("period1").split(",")[1]);
					voObj.setPeriodId22(rst1.getString("period2").split(",")[1]);
					voObj.setPeriodId32(rst1.getString("period3").split(",")[1]);
					voObj.setPeriodId42(rst1.getString("period4").split(",")[1]);
					voObj.setPeriodId52(rst1.getString("period5").split(",")[1]);
					voObj.setPeriodId62(rst1.getString("period6").split(",")[1]);
					voObj.setPeriodId72(rst1.getString("period7").split(",")[1]);
					voObj.setPeriodId82(rst1.getString("period8").split(",")[1]);
					voObj.setPeriodId92(rst1.getString("period9").split(",")[1]);
					
					voObj.setTeacherId12(rst1.getString("tperiod1").split(",")[1]);
					voObj.setTeacherId22(rst1.getString("tperiod2").split(",")[1]);
					voObj.setTeacherId32(rst1.getString("tperiod3").split(",")[1]);
					voObj.setTeacherId42(rst1.getString("tperiod4").split(",")[1]);
					voObj.setTeacherId52(rst1.getString("tperiod5").split(",")[1]);
					voObj.setTeacherId62(rst1.getString("tperiod6").split(",")[1]);
					voObj.setTeacherId72(rst1.getString("tperiod7").split(",")[1]);
					voObj.setTeacherId82(rst1.getString("tperiod8").split(",")[1]);
					voObj.setTeacherId92(rst1.getString("tperiod9").split(",")[1]);
					
					
					
					
			
					voObj.setPeriodId13(rst1.getString("period1").split(",")[2]);
					voObj.setPeriodId23(rst1.getString("period2").split(",")[2]);
					voObj.setPeriodId33(rst1.getString("period3").split(",")[2]);
					voObj.setPeriodId43(rst1.getString("period4").split(",")[2]);
					voObj.setPeriodId53(rst1.getString("period5").split(",")[2]);
					voObj.setPeriodId63(rst1.getString("period6").split(",")[2]);
					voObj.setPeriodId73(rst1.getString("period7").split(",")[2]);
					voObj.setPeriodId83(rst1.getString("period8").split(",")[2]);
					voObj.setPeriodId93(rst1.getString("period9").split(",")[2]);
					
					voObj.setTeacherId13(rst1.getString("tperiod1").split(",")[2]);
					voObj.setTeacherId23(rst1.getString("tperiod2").split(",")[2]);
					voObj.setTeacherId33(rst1.getString("tperiod3").split(",")[2]);
					voObj.setTeacherId43(rst1.getString("tperiod4").split(",")[2]);
					voObj.setTeacherId53(rst1.getString("tperiod5").split(",")[2]);
					voObj.setTeacherId63(rst1.getString("tperiod6").split(",")[2]);
					voObj.setTeacherId73(rst1.getString("tperiod7").split(",")[2]);
					voObj.setTeacherId83(rst1.getString("tperiod8").split(",")[2]);
					voObj.setTeacherId93(rst1.getString("tperiod9").split(",")[2]);
					
					
					
					voObj.setPeriodId14(rst1.getString("period1").split(",")[3]);
					voObj.setPeriodId24(rst1.getString("period2").split(",")[3]);
					voObj.setPeriodId34(rst1.getString("period3").split(",")[3]);
					voObj.setPeriodId44(rst1.getString("period4").split(",")[3]);
					voObj.setPeriodId54(rst1.getString("period5").split(",")[3]);
					voObj.setPeriodId64(rst1.getString("period6").split(",")[3]);
					voObj.setPeriodId74(rst1.getString("period7").split(",")[3]);
					voObj.setPeriodId84(rst1.getString("period8").split(",")[3]);
					voObj.setPeriodId94(rst1.getString("period9").split(",")[3]);
					
					voObj.setTeacherId14(rst1.getString("tperiod1").split(",")[3]);
					voObj.setTeacherId24(rst1.getString("tperiod2").split(",")[3]);
					voObj.setTeacherId34(rst1.getString("tperiod3").split(",")[3]);
					voObj.setTeacherId44(rst1.getString("tperiod4").split(",")[3]);
					voObj.setTeacherId54(rst1.getString("tperiod5").split(",")[3]);
					voObj.setTeacherId64(rst1.getString("tperiod6").split(",")[3]);
					voObj.setTeacherId74(rst1.getString("tperiod7").split(",")[3]);
					voObj.setTeacherId84(rst1.getString("tperiod8").split(",")[3]);
					voObj.setTeacherId94(rst1.getString("tperiod9").split(",")[3]);
					
					
					
					
					
					
					/*List<String> subjectlist = new TimeTableDaoImpl().getSubjectDetailForClass(rst1.getString("period1"),classid);
					System.out.println("list size is "+subjectlist.size());
					for(int i=0;i<subjectlist.size();i++){
						System.out.println("list of record "+subjectlist.get(i));
						voObj.setPeriod1(subjectlist.get(i));
						voObj.setPeriod2(subjectlist.get(i));
						voObj.setPeriod3(subjectlist.get(i));
						voObj.setPeriod4(subjectlist.get(i));
						voObj.setPeriod5(subjectlist.get(i));
						voObj.setPeriod6(subjectlist.get(i));
						voObj.setPeriod7(subjectlist.get(i));
						voObj.setPeriod8(subjectlist.get(i));
						voObj.setPeriod9(subjectlist.get(i));
					}*/
					
					//voObj.setPeriod1(subjectlist);
					voObj.setPeriod11(getSubjectfName(rst1.getString("period1").split(",")[0],classid));
					voObj.setPeriod21(getSubjectfName(rst1.getString("period2").split(",")[0],classid));
					voObj.setPeriod31(getSubjectfName(rst1.getString("period3").split(",")[0],classid));
					voObj.setPeriod41(getSubjectfName(rst1.getString("period4").split(",")[0],classid));
					voObj.setPeriod51(getSubjectfName(rst1.getString("period5").split(",")[0],classid));
					voObj.setPeriod61(getSubjectfName(rst1.getString("period6").split(",")[0],classid));
					voObj.setPeriod71(getSubjectfName(rst1.getString("period7").split(",")[0],classid));
					voObj.setPeriod81(getSubjectfName(rst1.getString("period8").split(",")[0],classid));
					voObj.setPeriod91(getSubjectfName(rst1.getString("period9").split(",")[0],classid));
					
					
					voObj.setPeriod12(getSubjectfName(rst1.getString("period1").split(",")[1],classid));
					voObj.setPeriod22(getSubjectfName(rst1.getString("period2").split(",")[1],classid));
					voObj.setPeriod32(getSubjectfName(rst1.getString("period3").split(",")[1],classid));
					voObj.setPeriod42(getSubjectfName(rst1.getString("period4").split(",")[1],classid));
					voObj.setPeriod52(getSubjectfName(rst1.getString("period5").split(",")[1],classid));
					voObj.setPeriod62(getSubjectfName(rst1.getString("period6").split(",")[1],classid));
					voObj.setPeriod72(getSubjectfName(rst1.getString("period7").split(",")[1],classid));
					voObj.setPeriod82(getSubjectfName(rst1.getString("period8").split(",")[1],classid));
					voObj.setPeriod92(getSubjectfName(rst1.getString("period9").split(",")[1],classid));
					
					
					
					voObj.setPeriod13(getSubjectfName(rst1.getString("period1").split(",")[2],classid));
					voObj.setPeriod23(getSubjectfName(rst1.getString("period2").split(",")[2],classid));
					voObj.setPeriod33(getSubjectfName(rst1.getString("period3").split(",")[2],classid));
					voObj.setPeriod43(getSubjectfName(rst1.getString("period4").split(",")[2],classid));
					voObj.setPeriod53(getSubjectfName(rst1.getString("period5").split(",")[2],classid));
					voObj.setPeriod63(getSubjectfName(rst1.getString("period6").split(",")[2],classid));
					voObj.setPeriod73(getSubjectfName(rst1.getString("period7").split(",")[2],classid));
					voObj.setPeriod83(getSubjectfName(rst1.getString("period8").split(",")[2],classid));
					voObj.setPeriod93(getSubjectfName(rst1.getString("period9").split(",")[2],classid));
					
					
					
					voObj.setPeriod14(getSubjectfName(rst1.getString("period1").split(",")[3],classid));
					voObj.setPeriod24(getSubjectfName(rst1.getString("period2").split(",")[3],classid));
					voObj.setPeriod34(getSubjectfName(rst1.getString("period3").split(",")[3],classid));
					voObj.setPeriod44(getSubjectfName(rst1.getString("period4").split(",")[3],classid));
					voObj.setPeriod54(getSubjectfName(rst1.getString("period5").split(",")[3],classid));
					voObj.setPeriod64(getSubjectfName(rst1.getString("period6").split(",")[3],classid));
					voObj.setPeriod74(getSubjectfName(rst1.getString("period7").split(",")[3],classid));
					voObj.setPeriod84(getSubjectfName(rst1.getString("period8").split(",")[3],classid));
					voObj.setPeriod94(getSubjectfName(rst1.getString("period9").split(",")[3],classid));
					
					
					
					details.add(voObj);
					
					/*ps_period=connection.prepareStatement(TimeTableSqlConstants.GET_SUBJECT_NAME);
					ps_period.setString(1, rst1.getString("period1"));
					ps_period.setString(2, classid);
					
					rs_period=ps_period.executeQuery();
					
					while(rs_period.next()){
						voObj.setPeriod1(rs_period.getString("subjectName"));
						details.add(voObj);
					}
					
					ps_period1=connection.prepareStatement(TimeTableSqlConstants.GET_SUBJECT_NAME);
					ps_period1.setString(1, rst1.getString("period2"));
					ps_period1.setString(2, classid);
					
					rs_period1=ps_period1.executeQuery();
					
					while(rs_period1.next()){
						voObj.setPeriod2(rs_period1.getString("subjectName"));
						details.add(voObj);
					}
					
					ps_period2=connection.prepareStatement(TimeTableSqlConstants.GET_SUBJECT_NAME);
					ps_period2.setString(1, rst1.getString("period3"));
					ps_period2.setString(2, classid);
					
					rs_period2=ps_period2.executeQuery();
					
					while(rs_period2.next()){
						voObj.setPeriod3(rs_period2.getString("subjectName"));
						details.add(voObj);
					}
					
					ps_period3=connection.prepareStatement(TimeTableSqlConstants.GET_SUBJECT_NAME);
					ps_period3.setString(1, rst1.getString("period4"));
					ps_period3.setString(2, classid);
					
					rs_period3=ps_period3.executeQuery();
					
					while(rs_period3.next()){
						voObj.setPeriod4(rs_period3.getString("subjectName"));
						details.add(voObj);
					}
					
					ps_period4=connection.prepareStatement(TimeTableSqlConstants.GET_SUBJECT_NAME);
					ps_period4.setString(1, rst1.getString("period5"));
					ps_period4.setString(2, classid);
					
					rs_period4=ps_period4.executeQuery();
					
					while(rs_period4.next()){
						voObj.setPeriod5(rs_period4.getString("subjectName"));
						details.add(voObj);
					}
					
					ps_period5=connection.prepareStatement(TimeTableSqlConstants.GET_SUBJECT_NAME);
					ps_period5.setString(1, rst1.getString("period6"));
					ps_period5.setString(2, classid);
					
					rs_period5=ps_period5.executeQuery();
					
					while(rs_period5.next()){
						voObj.setPeriod6(rs_period5.getString("subjectName"));
						details.add(voObj);
					}
					
					ps_period6=connection.prepareStatement(TimeTableSqlConstants.GET_SUBJECT_NAME);
					ps_period6.setString(1, rst1.getString("period7"));
					ps_period6.setString(2, classid);
					
					rs_period6=ps_period6.executeQuery();
					
					while(rs_period6.next()){
						voObj.setPeriod7(rs_period6.getString("subjectName"));
						details.add(voObj);
					}
					
					ps_period7=connection.prepareStatement(TimeTableSqlConstants.GET_SUBJECT_NAME);
					ps_period7.setString(1, rst1.getString("period8"));
					ps_period7.setString(2, classid);
					
					rs_period7=ps_period7.executeQuery();
					
					while(rs_period7.next()){
						voObj.setPeriod8(rs_period7.getString("subjectName"));
						details.add(voObj);
					}
					
					ps_period8=connection.prepareStatement(TimeTableSqlConstants.GET_SUBJECT_NAME);
					ps_period8.setString(1, rst1.getString("period9"));
					ps_period8.setString(2, classid);
					
					rs_period8=ps_period8.executeQuery();
					
					while(rs_period8.next()){
						voObj.setPeriod9(rs_period8.getString("subjectName"));
						details.add(voObj);
					}*/
					
					
				}
			} else {

				pstmt1 = connection.prepareStatement(TimeTableSqlConstants.TIMETABLE_GET_DAYS);
				rst1 = pstmt1.executeQuery();
				
				while (rst1.next()) {
					TimeTableVo voObj = new TimeTableVo();
					voObj.setDayid(rst1.getString("daycode"));
					voObj.setDayname(rst1.getString("dayname"));
					
					
					
					voObj.setPeriod11("");
					voObj.setPeriod21("");
					voObj.setPeriod31("");
					voObj.setPeriod41("");
					voObj.setPeriod51("");
					voObj.setPeriod61("");
					voObj.setPeriod71("");
					voObj.setPeriod81("");
					voObj.setPeriod91("");
					voObj.setTeacherId11("");
					voObj.setTeacherId21("");
					voObj.setTeacherId31("");
					voObj.setTeacherId41("");
					voObj.setTeacherId51("");
					voObj.setTeacherId61("");
					voObj.setTeacherId71("");
					voObj.setTeacherId81("");
					voObj.setTeacherId91("");
					voObj.setPeriodId11("");
					voObj.setPeriodId21("");
					voObj.setPeriodId31("");
					voObj.setPeriodId41("");
					voObj.setPeriodId51("");
					voObj.setPeriodId61("");
					voObj.setPeriodId71("");
					voObj.setPeriodId81("");
					voObj.setPeriodId91("");
					
					
					
					
					
					
					voObj.setPeriod12("");
					voObj.setPeriod22("");
					voObj.setPeriod32("");
					voObj.setPeriod42("");
					voObj.setPeriod52("");
					voObj.setPeriod62("");
					voObj.setPeriod72("");
					voObj.setPeriod82("");
					voObj.setPeriod92("");
					voObj.setTeacherId12("");
					voObj.setTeacherId22("");
					voObj.setTeacherId32("");
					voObj.setTeacherId42("");
					voObj.setTeacherId52("");
					voObj.setTeacherId62("");
					voObj.setTeacherId72("");
					voObj.setTeacherId82("");
					voObj.setTeacherId92("");
					voObj.setPeriodId12("");
					voObj.setPeriodId22("");
					voObj.setPeriodId32("");
					voObj.setPeriodId42("");
					voObj.setPeriodId52("");
					voObj.setPeriodId62("");
					voObj.setPeriodId72("");
					voObj.setPeriodId82("");
					voObj.setPeriodId92("");
					
					
					
					
					
					
					voObj.setPeriod13("");
					voObj.setPeriod23("");
					voObj.setPeriod33("");
					voObj.setPeriod43("");
					voObj.setPeriod53("");
					voObj.setPeriod63("");
					voObj.setPeriod73("");
					voObj.setPeriod83("");
					voObj.setPeriod93("");
					voObj.setTeacherId13("");
					voObj.setTeacherId23("");
					voObj.setTeacherId33("");
					voObj.setTeacherId43("");
					voObj.setTeacherId53("");
					voObj.setTeacherId63("");
					voObj.setTeacherId73("");
					voObj.setTeacherId83("");
					voObj.setTeacherId93("");
					voObj.setPeriodId13("");
					voObj.setPeriodId23("");
					voObj.setPeriodId33("");
					voObj.setPeriodId43("");
					voObj.setPeriodId53("");
					voObj.setPeriodId63("");
					voObj.setPeriodId73("");
					voObj.setPeriodId83("");
					voObj.setPeriodId93("");
					
					
					
					
					
					voObj.setPeriod14("");
					voObj.setPeriod24("");
					voObj.setPeriod34("");
					voObj.setPeriod44("");
					voObj.setPeriod54("");
					voObj.setPeriod64("");
					voObj.setPeriod74("");
					voObj.setPeriod84("");
					voObj.setPeriod94("");
					voObj.setTeacherId14("");
					voObj.setTeacherId24("");
					voObj.setTeacherId34("");
					voObj.setTeacherId44("");
					voObj.setTeacherId54("");
					voObj.setTeacherId64("");
					voObj.setTeacherId74("");
					voObj.setTeacherId84("");
					voObj.setTeacherId94("");
					voObj.setPeriodId14("");
					voObj.setPeriodId24("");
					voObj.setPeriodId34("");
					voObj.setPeriodId44("");
					voObj.setPeriodId54("");
					voObj.setPeriodId64("");
					voObj.setPeriodId74("");
					voObj.setPeriodId84("");
					voObj.setPeriodId94("");
					
					
					details.add(voObj);
				}
			}
		}catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} 
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {
				if (rst1 != null && !rst1.isClosed()) {
					rst1.close();
				}
				if (rst2 != null && !rst2.isClosed()) {
					rst2.close();
				}
				if (rst3 != null && !rst3.isClosed()) {
					rst3.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
				}
				if (pstmt2 != null && !pstmt2.isClosed()) {
					pstmt2.close();
				}
				if (pstmt3!= null && !pstmt3.isClosed()) {
					pstmt3.close();
				}
				
				if (connection != null && !connection.isClosed()) {

					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Ending");
		return details;
	}

	
	@Override
	public synchronized ArrayList<TimeTableVo> getClassName() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getClassName Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TimeTableSqlConstants.TIMETABLE_GET_CLASSID);
			rst = pstmt.executeQuery();
			while (rst.next()) {
				TimeTableVo voobj = new TimeTableVo();
				voobj.setClassid(rst.getString("classdetail_id_int").trim());
				voobj.setClassname(rst.getString("classdetails_name_var")
						.trim());
				details.add(voobj);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getClassName Ending");
		return details;
	}

	@Override
	public synchronized ArrayList<TimeTableVo> getSectionName(String classid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSectionName Starting");
		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection
					.prepareStatement(TimeTableSqlConstants.TIMETABLE_GET_SECTIONID);
			pstmt.setString(1, classid.trim());
			rst = pstmt.executeQuery();
			while (rst.next()) {
				TimeTableVo voobj = new TimeTableVo();
				voobj.setSectionid(rst.getString("classsection_id_int"));
				voobj.setSectionname(rst.getString("classsection_name_var"));
				details.add(voobj);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSectionName Ending");
		return details;
	}

	@Override
	public String updateTimeTableDetails(TimeTablePojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : updateTimeTableDetails Starting");
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement ctdStatement=null;
		PreparedStatement ctdStatementdel=null;
		PreparedStatement tpstmt =null;
		ResultSet rst = null;
		String result = null;
		String classid=null;
		String sectionid=null;
		List<String> teacherIdArrayList=new ArrayList<String>();
		List<String> timtabeIdArraylist=new ArrayList<String>();
		List<String> dayidArraylist=new ArrayList<String>();
		List<String> periodArrayList=new ArrayList<String>();
		List<String> subjectArrayList=new ArrayList<String>();
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			
			pstmt2 = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_ID_BY_NAME);
			pstmt2.setString(1, pojo.getClassid().trim());
			rst=pstmt2.executeQuery();
		
			while(rst.next())
			{
				classid=rst.getString("classdetail_id_int");
			}
			rst.close();
			pstmt3 = connection.prepareStatement(TimeTableSqlConstants.GET_SECTION_ID_BY_NAME);
			pstmt3.setString(1, pojo.getSectionid().trim());
			pstmt3.setString(2, pojo.getClassid().trim());
			rst=pstmt3.executeQuery();
		
			while(rst.next())
			{
				sectionid=rst.getString("classsection_id_int");
			
			}
			System.out.println("time table id isssss "+pojo.getTimetableID());
			if ("-".equals(pojo.getTimetableID())) {
			
				String tableId = IDGenerator.getPrimaryKeyID("campus_timetable_student");
				pstmt = connection.prepareStatement(TimeTableSqlConstants.TIMETABLE_DETAILS_INSERT);
				
				pstmt.setString(1, tableId);
				pstmt.setString(2, classid.trim());
				pstmt.setString(3, sectionid.trim());
				pstmt.setString(4, pojo.getAccyearid().trim());
				pstmt.setString(5, HelperClass.getCurrentSqlDate() + " "+ HelperClass.getCurrentTime());
				pstmt.setString(6, pojo.getUserid());
				pstmt.setString(7, pojo.getLocationId());
				
				int status = pstmt.executeUpdate();

				if (status > 0) {

					for (int i = 0; i < pojo.getDayid().length; i++) {
						pstmt1 = connection.prepareStatement(TimeTableSqlConstants.TIMETABLE_DETAILS_INSERTDETAILS);
						pstmt1.setString(1, tableId);
						pstmt1.setString(2, pojo.getDayid()[i]);
						pstmt1.setString(3, pojo.getSperiod1().split(":")[i].replaceFirst("^,", ""));
						pstmt1.setString(4, pojo.getSperiod2().split(":")[i].replaceFirst("^,", ""));
						pstmt1.setString(5, pojo.getSperiod3().split(":")[i].replaceFirst("^,", ""));
						pstmt1.setString(6, pojo.getSperiod4().split(":")[i].replaceFirst("^,", ""));
						pstmt1.setString(7, pojo.getSperiod5().split(":")[i].replaceFirst("^,", ""));
						pstmt1.setString(8, pojo.getSperiod6().split(":")[i].replaceFirst("^,", ""));
						pstmt1.setString(9, pojo.getSperiod7().split(":")[i].replaceFirst("^,", ""));
						pstmt1.setString(10, pojo.getSperiod8().split(":")[i].replaceFirst("^,", ""));
						pstmt1.setString(11, pojo.getSperiod9().split(":")[i].replaceFirst("^,", ""));
						pstmt1.executeUpdate();
						
						tpstmt=connection.prepareStatement("insert into campus_timetable_teacherdetails (teachertimetable_id,daycode,period1,period2,period3,period4,period5,period6,period7,period8,period9) values  (?,?,?,?,?,?,?,?,?,?,?)");
						tpstmt.setString(1, tableId);
						tpstmt.setString(2, pojo.getDayid()[i]);
						tpstmt.setString(3, pojo.getStperiod1().split(":")[i].replaceFirst("^,", ""));
						tpstmt.setString(4, pojo.getStperiod2().split(":")[i].replaceFirst("^,", ""));
						tpstmt.setString(5, pojo.getStperiod3().split(":")[i].replaceFirst("^,", ""));
						tpstmt.setString(6, pojo.getStperiod4().split(":")[i].replaceFirst("^,", ""));
						tpstmt.setString(7, pojo.getStperiod5().split(":")[i].replaceFirst("^,", ""));
						tpstmt.setString(8, pojo.getStperiod6().split(":")[i].replaceFirst("^,", ""));
						tpstmt.setString(9, pojo.getStperiod7().split(":")[i].replaceFirst("^,", ""));
						tpstmt.setString(10, pojo.getStperiod8().split(":")[i].replaceFirst("^,", ""));
						tpstmt.setString(11, pojo.getStperiod9().split(":")[i].replaceFirst("^,", ""));
						tpstmt.executeUpdate();
						
						
						
						for(int l=0;l<pojo.getSperiod1().split(":")[i].replaceFirst("^,", "").split(",").length;l++) {
							String subjtId=pojo.getSperiod1().split(":")[i].replaceFirst("^,", "").split(",")[l];
							String techerId=pojo.getStperiod1().split(":")[i].replaceFirst("^,", "").split(",")[l];
							
							if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
								subjectArrayList.add(subjtId);
								teacherIdArrayList.add(techerId);
								periodArrayList.add("period1");
								dayidArraylist.add(pojo.getDayid()[i]);
								timtabeIdArraylist.add(tableId);
							}
							
							
							
							 subjtId=pojo.getSperiod2().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 techerId=pojo.getStperiod2().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
									subjectArrayList.add(subjtId);
									teacherIdArrayList.add(techerId);
									periodArrayList.add("period2");
									dayidArraylist.add(pojo.getDayid()[i]);
									timtabeIdArraylist.add(tableId);
								}
							 
							 
							 subjtId=pojo.getSperiod3().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 techerId=pojo.getStperiod3().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
									subjectArrayList.add(subjtId);
									teacherIdArrayList.add(techerId);
									periodArrayList.add("period3");
									dayidArraylist.add(pojo.getDayid()[i]);
									timtabeIdArraylist.add(tableId);
								}
							 
							 subjtId=pojo.getSperiod4().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 techerId=pojo.getStperiod4().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
									subjectArrayList.add(subjtId);
									teacherIdArrayList.add(techerId);
									periodArrayList.add("period4");
									dayidArraylist.add(pojo.getDayid()[i]);
									timtabeIdArraylist.add(tableId);
								}
							 
							 subjtId=pojo.getSperiod5().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 techerId=pojo.getStperiod5().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
									subjectArrayList.add(subjtId);
									teacherIdArrayList.add(techerId);
									periodArrayList.add("period5");
									dayidArraylist.add(pojo.getDayid()[i]);
									timtabeIdArraylist.add(tableId);
								}
							 
							 subjtId=pojo.getSperiod6().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 techerId=pojo.getStperiod6().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
									subjectArrayList.add(subjtId);
									teacherIdArrayList.add(techerId);
									periodArrayList.add("period6");
									dayidArraylist.add(pojo.getDayid()[i]);
									timtabeIdArraylist.add(tableId);
								}
							 
							 subjtId=pojo.getSperiod7().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 techerId=pojo.getStperiod7().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
									subjectArrayList.add(subjtId);
									teacherIdArrayList.add(techerId);
									periodArrayList.add("period7");
									dayidArraylist.add(pojo.getDayid()[i]);
									timtabeIdArraylist.add(tableId);
								}
							 
							 subjtId=pojo.getSperiod8().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 techerId=pojo.getStperiod8().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
									subjectArrayList.add(subjtId);
									teacherIdArrayList.add(techerId);
									periodArrayList.add("period8");
									dayidArraylist.add(pojo.getDayid()[i]);
									timtabeIdArraylist.add(tableId);
								}
							 
							 subjtId=pojo.getSperiod9().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 techerId=pojo.getStperiod9().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
									subjectArrayList.add(subjtId);
									teacherIdArrayList.add(techerId);
									periodArrayList.add("period9");
									dayidArraylist.add(pojo.getDayid()[i]);
									timtabeIdArraylist.add(tableId);
								}
							 
							
						}
						
						
					}
					
					
					for(int k=0;k<subjectArrayList.size();k++) {
						 ctdStatement=connection.prepareStatement("INSERT INTO `campus_timetable_details` (`teacherid`,`subjectid`,`timtable_id`,`daycode`,`period`,`accyearid`,`created_by`) VALUES(?,?,?,?,?,?,?)");
						ctdStatement.setString(1, teacherIdArrayList.get(k));
						ctdStatement.setString(2, subjectArrayList.get(k));
						ctdStatement.setString(3, timtabeIdArraylist.get(k));
						ctdStatement.setString(4, dayidArraylist.get(k));
						ctdStatement.setString(5, periodArrayList.get(k));
						ctdStatement.setString(6, pojo.getAccyearid().trim());
						ctdStatement.setString(7, pojo.getUserid());
						ctdStatement.executeUpdate();
					}
					
					
					result = "success";
					
				} else {
					
						result = "fail";
					
				}
			} else {
				
				pstmt2 = connection.prepareStatement(TimeTableSqlConstants.TIMETABLE_DETAILS_MODIFY);
				pstmt2.setString(1, pojo.getUserid());
				pstmt2.setString(2, HelperClass.getCurrentSqlDate() + " "+ HelperClass.getCurrentTime());
				pstmt2.setString(3, pojo.getTimetableID());
				
				pstmt2.executeUpdate();
				
				pstmt = connection.prepareStatement(TimeTableSqlConstants.TIMETABLE_DETAILS_DELETE);
				pstmt.setString(1, pojo.getTimetableID().trim());
				
				
				pstmt4 = connection.prepareStatement("delete from campus_timetable_teacherdetails where teachertimetable_id=?");
				pstmt4.setString(1, pojo.getTimetableID().trim());
				
				ctdStatementdel=connection.prepareStatement("delete from campus_timetable_details where timtable_id=?");
				ctdStatementdel.setString(1,pojo.getTimetableID().trim());
				int status = pstmt.executeUpdate();
				int status2 = pstmt4.executeUpdate();
				int status3=ctdStatementdel.executeUpdate();
				
				if(status2 > 0 && status > 0){
					for (int i = 0; i < pojo.getDayid().length; i++) {
						
					tpstmt=connection.prepareStatement("insert into campus_timetable_teacherdetails (teachertimetable_id,daycode,period1,period2,period3,period4,period5,period6,period7,period8,period9) values  (?,?,?,?,?,?,?,?,?,?,?)");
					tpstmt.setString(1, pojo.getTimetableID());
					tpstmt.setString(2, pojo.getDayid()[i]);
					tpstmt.setString(3, pojo.getStperiod1().split(":")[i].replaceFirst("^,", ""));
					tpstmt.setString(4, pojo.getStperiod2().split(":")[i].replaceFirst("^,", ""));
					tpstmt.setString(5, pojo.getStperiod3().split(":")[i].replaceFirst("^,", ""));
					tpstmt.setString(6, pojo.getStperiod4().split(":")[i].replaceFirst("^,", ""));
					tpstmt.setString(7, pojo.getStperiod5().split(":")[i].replaceFirst("^,", ""));
					tpstmt.setString(8, pojo.getStperiod6().split(":")[i].replaceFirst("^,", ""));
					tpstmt.setString(9, pojo.getStperiod7().split(":")[i].replaceFirst("^,", ""));
					tpstmt.setString(10, pojo.getStperiod8().split(":")[i].replaceFirst("^,", ""));
					tpstmt.setString(11, pojo.getStperiod9().split(":")[i].replaceFirst("^,", ""));
					tpstmt.executeUpdate();
					
				
			

						pstmt1 = connection.prepareStatement(TimeTableSqlConstants.TIMETABLE_DETAILS_INSERTDETAILS);
					
						pstmt1.setString(1, pojo.getTimetableID());
						pstmt1.setString(2, pojo.getDayid()[i]);
						pstmt1.setString(3, pojo.getSperiod1().split(":")[i].replaceFirst("^,", ""));
						pstmt1.setString(4, pojo.getSperiod2().split(":")[i].replaceFirst("^,", ""));
						pstmt1.setString(5, pojo.getSperiod3().split(":")[i].replaceFirst("^,", ""));
						pstmt1.setString(6, pojo.getSperiod4().split(":")[i].replaceFirst("^,", ""));
						pstmt1.setString(7, pojo.getSperiod5().split(":")[i].replaceFirst("^,", ""));
						pstmt1.setString(8, pojo.getSperiod6().split(":")[i].replaceFirst("^,", ""));
						pstmt1.setString(9, pojo.getSperiod7().split(":")[i].replaceFirst("^,", ""));
						pstmt1.setString(10, pojo.getSperiod8().split(":")[i].replaceFirst("^,", ""));
						pstmt1.setString(11, pojo.getSperiod9().split(":")[i].replaceFirst("^,", ""));
						pstmt1.executeUpdate();
						
						
						
						for(int l=0;l<pojo.getSperiod1().split(":")[i].replaceFirst("^,", "").split(",").length;l++) {
							String subjtId=pojo.getSperiod1().split(":")[i].replaceFirst("^,", "").split(",")[l];
							String techerId=pojo.getStperiod1().split(":")[i].replaceFirst("^,", "").split(",")[l];
							
							if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
								subjectArrayList.add(subjtId);
								teacherIdArrayList.add(techerId);
								periodArrayList.add("period1");
								dayidArraylist.add(pojo.getDayid()[i]);
								timtabeIdArraylist.add(pojo.getTimetableID());
							}
							
							
							
							 subjtId=pojo.getSperiod2().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 techerId=pojo.getStperiod2().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
									subjectArrayList.add(subjtId);
									teacherIdArrayList.add(techerId);
									periodArrayList.add("period2");
									dayidArraylist.add(pojo.getDayid()[i]);
									timtabeIdArraylist.add(pojo.getTimetableID());
								}
							 
							 
							 subjtId=pojo.getSperiod3().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 techerId=pojo.getStperiod3().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
									subjectArrayList.add(subjtId);
									teacherIdArrayList.add(techerId);
									periodArrayList.add("period3");
									dayidArraylist.add(pojo.getDayid()[i]);
									timtabeIdArraylist.add(pojo.getTimetableID());
								}
							 
							 subjtId=pojo.getSperiod4().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 techerId=pojo.getStperiod4().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
									subjectArrayList.add(subjtId);
									teacherIdArrayList.add(techerId);
									periodArrayList.add("period4");
									dayidArraylist.add(pojo.getDayid()[i]);
									timtabeIdArraylist.add(pojo.getTimetableID());
								}
							 
							 subjtId=pojo.getSperiod5().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 techerId=pojo.getStperiod5().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
									subjectArrayList.add(subjtId);
									teacherIdArrayList.add(techerId);
									periodArrayList.add("period5");
									dayidArraylist.add(pojo.getDayid()[i]);
									timtabeIdArraylist.add(pojo.getTimetableID());
								}
							 
							 subjtId=pojo.getSperiod6().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 techerId=pojo.getStperiod6().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
									subjectArrayList.add(subjtId);
									teacherIdArrayList.add(techerId);
									periodArrayList.add("period6");
									dayidArraylist.add(pojo.getDayid()[i]);
									timtabeIdArraylist.add(pojo.getTimetableID());
								}
							 
							 subjtId=pojo.getSperiod7().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 techerId=pojo.getStperiod7().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
									subjectArrayList.add(subjtId);
									teacherIdArrayList.add(techerId);
									periodArrayList.add("period7");
									dayidArraylist.add(pojo.getDayid()[i]);
									timtabeIdArraylist.add(pojo.getTimetableID());
								}
							 
							 subjtId=pojo.getSperiod8().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 techerId=pojo.getStperiod8().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
									subjectArrayList.add(subjtId);
									teacherIdArrayList.add(techerId);
									periodArrayList.add("period8");
									dayidArraylist.add(pojo.getDayid()[i]);
									timtabeIdArraylist.add(pojo.getTimetableID());
								}
							 
							 subjtId=pojo.getSperiod9().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 techerId=pojo.getStperiod9().split(":")[i].replaceFirst("^,", "").split(",")[l];
							 if(!subjtId.equalsIgnoreCase("-") && !techerId.equalsIgnoreCase("-")) {
									subjectArrayList.add(subjtId);
									teacherIdArrayList.add(techerId);
									periodArrayList.add("period9");
									dayidArraylist.add(pojo.getDayid()[i]);
									timtabeIdArraylist.add(pojo.getTimetableID());
								}
							 
							
						}
					}
					for(int k=0;k<subjectArrayList.size();k++) {
						 ctdStatement=connection.prepareStatement("INSERT INTO `campus_timetable_details` (`teacherid`,`subjectid`,`timtable_id`,`daycode`,`period`,`accyearid`,`created_by`) VALUES(?,?,?,?,?,?,?)");
						ctdStatement.setString(1, teacherIdArrayList.get(k));
						ctdStatement.setString(2, subjectArrayList.get(k));
						ctdStatement.setString(3, timtabeIdArraylist.get(k));
						ctdStatement.setString(4, dayidArraylist.get(k));
						ctdStatement.setString(5, periodArrayList.get(k));
						ctdStatement.setString(6, pojo.getAccyearid().trim());
						ctdStatement.setString(7, pojo.getUserid());
						ctdStatement.executeUpdate();
					}
				
						result = "success";
					
				} else {
						result = "fail";
					
				}
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (tpstmt != null && (!tpstmt.isClosed())) {
					tpstmt.close();
				}
				if (pstmt3 != null && (!pstmt3.isClosed())) {
					pstmt3.close();
				}
				if (pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmt4 != null && (!pstmt4.isClosed())) {
					pstmt4.close();
				}
				
				if (ctdStatement != null && (!ctdStatement.isClosed())) {
					ctdStatement.close();
				}
				if (ctdStatementdel != null && (!ctdStatementdel.isClosed())) {
					ctdStatementdel.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : updateTimeTableDetails Ending");

		return result;
	}
	
	
	

	@SuppressWarnings("resource")
	@Override
	public synchronized ArrayList<TimeTableVo> getTeacherTimeTableDetails(String teacherid, String accyearid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTeacherTimeTableDetails Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		int count = 0;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_DETAILS_COUNT);
			pstmt.setString(1, teacherid.trim());
			pstmt.setString(2, accyearid.trim());
			rst = pstmt.executeQuery();
			
			
			
			while (rst.next()) {
				count = rst.getInt(1);
			}
			if (count > 0) {
				pstmt = connection.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_GET_DETAILS);
				pstmt.setString(1, teacherid.trim());
				pstmt.setString(2, accyearid.trim());
				
				
				rst = pstmt.executeQuery();

				while (rst.next()) {
					TimeTableVo voObj = new TimeTableVo();
					voObj.setDayid(rst.getString("daycode"));
					voObj.setDayname(rst.getString("dayname"));
					voObj.setPeriodId1(rst.getString("period1"));
					voObj.setPeriodId2(rst.getString("period2"));
					voObj.setPeriodId3(rst.getString("period3"));
					voObj.setPeriodId4(rst.getString("period4"));
					voObj.setPeriodId5(rst.getString("period5"));
					voObj.setPeriodId6(rst.getString("period6"));
					voObj.setPeriodId7(rst.getString("period7"));
					voObj.setPeriodId8(rst.getString("period8"));
					voObj.setPeriodId9(rst.getString("period9"));
					
					voObj.setPeriod1(getSubjectfNameval(rst.getString("period1")));
					voObj.setPeriod2(getSubjectfNameval(rst.getString("period2")));
					voObj.setPeriod3(getSubjectfNameval(rst.getString("period3")));
					voObj.setPeriod4(getSubjectfNameval(rst.getString("period4")));
					voObj.setPeriod5(getSubjectfNameval(rst.getString("period5")));
					voObj.setPeriod6(getSubjectfNameval(rst.getString("period6")));
					voObj.setPeriod7(getSubjectfNameval(rst.getString("period7")));
					voObj.setPeriod8(getSubjectfNameval(rst.getString("period8")));
					voObj.setPeriod9(getSubjectfNameval(rst.getString("period9")));
					details.add(voObj);
					
					
				}
			} else {

				pstmt = connection.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_GET_DAYS);
				
				
				rst = pstmt.executeQuery();
				while (rst.next()) {
					TimeTableVo voObj = new TimeTableVo();
					voObj.setDayid(rst.getString("daycode"));
					voObj.setDayname(rst.getString("dayname"));
					voObj.setPeriod1("");
					voObj.setPeriod2("");
					voObj.setPeriod3("");
					voObj.setPeriod4("");
					voObj.setPeriod5("");
					voObj.setPeriod6("");
					voObj.setPeriod7("");
					voObj.setPeriod8("");
					voObj.setPeriod9("");
					voObj.setPeriodId1("");
					voObj.setPeriodId2("");
					voObj.setPeriodId3("");
					voObj.setPeriodId4("");
					voObj.setPeriodId5("");
					voObj.setPeriodId6("");
					voObj.setPeriodId7("");
					voObj.setPeriodId8("");
					voObj.setPeriodId9("");
					details.add(voObj);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTeacherTimeTableDetails Ending");
		return details;
	}

	private String getSubjectfNameval(String class_section) 
	
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSubjectfName Starting");

		PreparedStatement pstmt = null;
		ResultSet rst = null;
		PreparedStatement pstmt1 = null;
		ResultSet rst1 = null;
		Connection connection = null;
		String subjectName=null;
		String classname = "";
		String subjectname = "";
		
		try {
			
			if(!class_section.equalsIgnoreCase("-"))
				
			{
			
			String[] class_sectionval = class_section.split("-");
			
			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_NAME);
			pstmt.setString(1, class_sectionval[0]);
			rst = pstmt.executeQuery();

				if (rst.next()) {
					
					/*subjectName=rst.getString("subjectName");*/
					
					classname =  rst.getString("classdetails_name_var");
					
					
				}
				
				else
					
				{
					
					classname= " ";
					
				}
				
				
				pstmt1 = connection.prepareStatement(TimeTableSqlConstants.GET_SECTION_NAME);
				pstmt1.setString(1, class_sectionval[1]);
				rst1 = pstmt1.executeQuery();

					if (rst1.next()) {
						
						/*subjectName=rst.getString("subjectName");*/
						
						subjectname =  rst1.getString("classsection_name_var");
						
						
					}
					
					else
						
					{
						
						subjectname = " ";
						
					}
				
					
					subjectName = classname.concat("-"+subjectname);
			}
			
			else
				
			{
				
				subjectName = "-";
				
			}
				
				
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (rst1 != null && (!rst1.isClosed())) {
					rst1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSubjectfName Ending");
		return subjectName;
	}

	public synchronized ArrayList<TeacherTimeTableVo> getTeacherName() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTeacherName Starting");

		ArrayList<TeacherTimeTableVo> details = new ArrayList<TeacherTimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TimeTableSqlConstants.TEACHER_IDANDNAME);
			rst = pstmt.executeQuery();
			while (rst.next()) {
				TeacherTimeTableVo voobj = new TeacherTimeTableVo();
				voobj.setTeacherId(rst.getString("TeacherID").trim());
				voobj.setTeacherName(rst.getString("TeacherName").trim());
				details.add(voobj);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTeacherName Ending");
		return details;
	}

	@Override
	public String updateTeacherTimeTableDetails(TeacherTimeTablePojo pojo) {
		
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : updateTeacherTimeTableDetails Starting");
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rst = null;
		String result = "fail";
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			int count = 0;
			pstmt = connection
					.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_DETAILS_UPDATECOUNT);
			pstmt.setString(1, pojo.getTeacherid().trim());
			pstmt.setString(2, pojo.getAccyearid().trim());
			
			
			
			rst = pstmt.executeQuery();
			while (rst.next()) {
				count = rst.getInt(1);
			}
			if (count == 0) {
				String tableId = IDGenerator.getPrimaryKeyID("campus_timetable_teacher");
				pstmt = connection.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_DETAILS_INSERT);
				pstmt.setString(1, tableId);
				pstmt.setString(2, pojo.getTeacherid().trim());
				pstmt.setString(3, pojo.getAccyearid().trim());
				pstmt.setString(4, HelperClass.getCurrentSqlDate() + " "
						+ HelperClass.getCurrentTime());
				pstmt.setString(5, pojo.getUserid());
				
				
				
				int status = pstmt.executeUpdate();

				if (status > 0) {

					for (int i = 0; i < pojo.getDayid().length; i++) {

						pstmt1 = connection
								.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_DETAILS_INSERTDETAILS);
						pstmt1.setString(1, tableId);
						pstmt1.setString(2, pojo.getDayid()[i]);
						pstmt1.setString(3, pojo.getPeriod1()[i]);
						pstmt1.setString(4, pojo.getPeriod2()[i]);
						pstmt1.setString(5, pojo.getPeriod3()[i]);
						pstmt1.setString(6, pojo.getPeriod4()[i]);
						pstmt1.setString(7, pojo.getPeriod5()[i]);
						pstmt1.setString(8, pojo.getPeriod6()[i]);
						pstmt1.setString(9, pojo.getPeriod7()[i]);
						pstmt1.setString(10, pojo.getPeriod8()[i]);
						pstmt1.setString(11, pojo.getPeriod9()[i]);
						
						
						
						pstmt1.executeUpdate();
					}
					
				result = "success";
					
				}
			} else {
				
				
				
				String exist_id = null;
				pstmt = connection
						.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_DETAILS_ID);
				pstmt.setString(1, pojo.getTeacherid().trim());
				pstmt.setString(2, pojo.getAccyearid().trim());
				
				
				rst = pstmt.executeQuery();
				while (rst.next()) {
					exist_id = rst.getString("teachertimetable_id");
				}

				PreparedStatement pstmt2 = connection
						.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_DETAILS_DELETE);
				pstmt2.setString(1, pojo.getTeacherid().trim());
				pstmt2.setString(2, pojo.getAccyearid().trim());
				
				
				
				int status = pstmt2.executeUpdate();
				if (status > 0) {

					for (int i = 0; i < pojo.getDayid().length; i++) {

						pstmt1 = connection
								.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_DETAILS_INSERTDETAILS);
						pstmt1.setString(1, exist_id);
						pstmt1.setString(2, pojo.getDayid()[i]);
						pstmt1.setString(3, pojo.getPeriod1()[i]);
						pstmt1.setString(4, pojo.getPeriod2()[i]);
						pstmt1.setString(5, pojo.getPeriod3()[i]);
						pstmt1.setString(6, pojo.getPeriod4()[i]);
						pstmt1.setString(7, pojo.getPeriod5()[i]);
						pstmt1.setString(8, pojo.getPeriod6()[i]);
						pstmt1.setString(9, pojo.getPeriod7()[i]);
						pstmt1.setString(10, pojo.getPeriod8()[i]);
						pstmt1.setString(11, pojo.getPeriod9()[i]);
						
						pstmt1.executeUpdate();
					}
					
						result = "success";
					
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : updateTeacherTimeTableDetails Ending");

		return result;
	}

	@Override
	public ArrayList<TimeTableVo> getClassTimeTableList(String accyearid,String classId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		
		try {
			connection = JDBCConnection.getSeparateConnection();
			
			if(classId == null || classId == "ALL"){
				pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_TIMETABLE_LIST);
				pstmt.setString(1, accyearid.trim());
               System.out.println(pstmt);
				rst = pstmt.executeQuery();

				while (rst.next()) {

					TimeTableVo voObj = new TimeTableVo();

					if(rst.getString("timetable_id")==null || "".equalsIgnoreCase(rst.getString("timetable_id"))){

						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						voObj.setTimetableStatus("Yet to Create");
						voObj.setCreatedby("-");
						voObj.setCreateddate("-");
						voObj.setLastupdatedby("-");
						voObj.setLastupdated("-");
						voObj.setTimetableId("-");
						voObj.setTeachername(rst.getString("teachername"));

					}else{

						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						voObj.setTimetableStatus("Created");
						voObj.setCreatedby(getStaffName(rst.getString("created_by")));
						voObj.setCreateddate(rst.getString("created_date"));
						if(rst.getString("updated_by")==null || "".equalsIgnoreCase(rst.getString("updated_by"))){

							voObj.setLastupdatedby("-");
							voObj.setLastupdated("-");

						}else{

							voObj.setLastupdatedby(getStaffName(rst.getString("updated_by")));
							voObj.setLastupdated(rst.getString("updated_time"));
						}
						voObj.setTimetableId(rst.getString("timetable_id"));
						voObj.setTeachername(rst.getString("teachername"));

					}
					details.add(voObj);
				}
			

				pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_TIMETABLE_LIST_BY_CLASS);
				pstmt.setString(1, accyearid.trim());
				pstmt.setString(2, classId);
				rst = pstmt.executeQuery();

				while (rst.next()) {

					TimeTableVo voObj = new TimeTableVo();

					if(rst.getString("timetable_id")==null || "".equalsIgnoreCase(rst.getString("timetable_id"))){

						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						voObj.setTimetableStatus("Yet to Create");
						voObj.setCreatedby("-");
						voObj.setCreateddate("-");
						voObj.setLastupdatedby("-");
						voObj.setLastupdated("-");
						voObj.setTimetableId("-");
						voObj.setTeachername(rst.getString("teachername"));

					}else{

						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						voObj.setTimetableStatus("Created");
						voObj.setCreatedby(getStaffName(rst.getString("created_by")));
						voObj.setCreateddate(rst.getString("created_date"));
						if(rst.getString("updated_by")==null || "".equalsIgnoreCase(rst.getString("updated_by"))){

							voObj.setLastupdatedby("-");
							voObj.setLastupdated("-");

						}else{

							voObj.setLastupdatedby(getStaffName(rst.getString("updated_by")));
							voObj.setLastupdated(rst.getString("updated_time"));
						}

						voObj.setTimetableId(rst.getString("timetable_id"));
						voObj.setTeachername(rst.getString("teachername"));
					}
					details.add(voObj);
				}
			}else{
				pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_TIMETABLE_LIST_BY_CLASS);
				pstmt.setString(1, accyearid.trim());
				pstmt.setString(2, classId);
				rst = pstmt.executeQuery();

				while (rst.next()) {

					TimeTableVo voObj = new TimeTableVo();

					if(rst.getString("timetable_id")==null || "".equalsIgnoreCase(rst.getString("timetable_id"))){

						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						voObj.setTimetableStatus("Yet to Create");
						voObj.setCreatedby("-");
						voObj.setCreateddate("-");
						voObj.setLastupdatedby("-");
						voObj.setLastupdated("-");
						voObj.setTimetableId("-");
						voObj.setTeachername(rst.getString("teachername"));
					}else{

						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						voObj.setTimetableStatus("Created");
						voObj.setCreatedby(getStaffName(rst.getString("created_by")));
						voObj.setCreateddate(rst.getString("created_date"));
						if(rst.getString("updated_by")==null || "".equalsIgnoreCase(rst.getString("updated_by"))){

							voObj.setLastupdatedby("-");
							voObj.setLastupdated("-");

						}else{

							voObj.setLastupdatedby(getStaffName(rst.getString("updated_by")));
							voObj.setLastupdated(rst.getString("updated_time"));
						}

						voObj.setTimetableId(rst.getString("timetable_id"));
						voObj.setTeachername(rst.getString("teachername"));
					}
					details.add(voObj);
				}
			}
			
			/*else{

				pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_TEACHER_TIMETABLE_LIST);
				pstmt.setString(1, accyearid.trim());
				
				rst = pstmt.executeQuery();

					while (rst.next()) {
						
						TimeTableVo voObj = new TimeTableVo();
						
						if(rst.getString("teachertimetable_id")==null || "".equalsIgnoreCase(rst.getString("teachertimetable_id"))){
							
							voObj.setTeacherId(rst.getString("TeacherID"));
							voObj.setRegno(rst.getString("registerId"));
							voObj.setTeachername(rst.getString("teachername"));
							voObj.setTimetableStatus("Yet to Create");
							voObj.setCreatedby("-");
							voObj.setCreateddate("-");
							voObj.setLastupdatedby("-");
							voObj.setLastupdated("-");
							voObj.setTimetableId("-");
							
						}else{
							
							voObj.setTeacherId(rst.getString("TeacherID"));
							voObj.setRegno(rst.getString("registerId"));
							voObj.setTeachername(rst.getString("teachername"));
							voObj.setTimetableStatus("Created");
							voObj.setCreatedby(getStaffName(rst.getString("created_by")));
							voObj.setCreateddate(rst.getString("created_date"));
							if(rst.getString("updated_date")==null || "".equalsIgnoreCase(rst.getString("updated_date"))){
								
								voObj.setLastupdatedby("-");
								voObj.setLastupdated("-");
						
							}else{
								
								voObj.setLastupdatedby(getStaffName(rst.getString("updated_date")));
								voObj.setLastupdated(rst.getString("updated_time"));
							}
							
							voObj.setTimetableId(rst.getString("teachertimetable_id"));
						
						}
						details.add(voObj);
					}
				
				}*/
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Ending");
		return details;
	}
	
	public String getStaffName(String staffId) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getStaffName Starting");

		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		String staffname=null;
		
		try {
			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_STAFF_NAME);
			pstmt.setString(1, staffId);
			
			rst = pstmt.executeQuery();

				while (rst.next()) {
					
					staffname=rst.getString("StaffName");
				}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getStaffName Ending");
		return staffname;
	}
	
	public String getSubjectfName(String subjectId,String classid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSubjectfName Starting");

		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		String subjectName=null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			
			pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_SUBJECT_NAME);
			pstmt.setString(1, subjectId);
			//pstmt.setString(1, classid);
			
			System.out.println("subject list is "+pstmt);
			rst = pstmt.executeQuery();
			if (rst.next()) {
				subjectName=rst.getString("subjectName");
			}else{
				subjectName="";
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSubjectfName Ending");
		return subjectName;
	}

	public ArrayList<TimeTableVo> getClassSectionList() {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TimeTableDaoImpl : getClassSectionList Starting");

	PreparedStatement pstmt = null;
	ResultSet rst = null;
	Connection connection = null;
	ArrayList<TimeTableVo> classSectionList=new ArrayList<TimeTableVo>();
	
	try {
		connection = JDBCConnection.getSeparateConnection();

		pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_SECTION_LIST);
		
		rst = pstmt.executeQuery();

			while (rst.next()) {
				
				TimeTableVo timetableVo=new TimeTableVo();
				
				timetableVo.setClassid(rst.getString("classid"));
				timetableVo.setClassname(rst.getString("classname"));
				
				classSectionList.add(timetableVo);
			}
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {
			if (rst != null && (!rst.isClosed())) {
				rst.close();
			}
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (connection != null && (!connection.isClosed())) {
				connection.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TimeTableDaoImpl : getClassSectionList Ending");
	return classSectionList;
}

	public static void main(String[] args) {
	
		JSONArray array=new JSONArray();
		array.put(new TimeTableDaoImpl().getTeacherTimeTableDetails("TEA23", "ACY1"));
		
		
	}
	
	public String getClassNameDetailsDao(String classid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getClassNameDetailsDao Starting");
		
		TimeTableVo vo = new TimeTableVo();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		
		String classname = null;
		try {
			
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_NAME);
			pstmt.setString(1, classid);
			rst = pstmt.executeQuery();
			
			while(rst.next()){
				
				 classname = rst.getString("classdetails_name_var");
					
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getClassNameDetailsDao Ending");
		
		return classname;
	}
	
	public String getSectionNameDetailsDao(String sectionid) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSectionNameDetailsDao Starting");
		
		TimeTableVo vo = new TimeTableVo();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		
		String sectionname = null;
		try {
			
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_SECTION_NAME);
			pstmt.setString(1, sectionid);
			rst = pstmt.executeQuery();
			
			while(rst.next()){
				
				sectionname = rst.getString("classsection_name_var");
				 
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally {

			try {
				if (rst != null && !rst.isClosed()) {
					rst.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (connection != null && !connection.isClosed()) {

					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSectionNameDetailsDao Ending");
		
		return sectionname;
	}

	@Override
	public ArrayList<TimeTableVo> getClassTimeTableListByClass(String accyearid, String viewBy, String classId,String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		
		try {
			connection = JDBCConnection.getSeparateConnection();
			if(classId.equalsIgnoreCase("ALL")){

				pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_TIMETABLE_LIST);
				pstmt.setString(1, accyearid.trim());
				pstmt.setString(2, locationId);
				rst = pstmt.executeQuery();

				while (rst.next()) {

					TimeTableVo voObj = new TimeTableVo();

					if(rst.getString("timetable_id")==null || rst.getString("timetable_id").equalsIgnoreCase("")){

						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						voObj.setTimetableStatus("Yet to Create");
						voObj.setCreatedby("-");
						voObj.setCreateddate("-");
						voObj.setLastupdatedby("-");
						voObj.setLastupdated("-");
						voObj.setTimetableId("-");
						voObj.setTeachername(rst.getString("teachername"));
					}else{

						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						voObj.setTimetableStatus("Created");
						voObj.setCreatedby(getStaffName(rst.getString("created_by")));
						voObj.setCreateddate(rst.getString("created_date"));
						if(rst.getString("updated_by")==null || "".equalsIgnoreCase(rst.getString("updated_by"))){

							voObj.setLastupdatedby("-");
							voObj.setLastupdated("-");

						}else{

							voObj.setLastupdatedby(getStaffName(rst.getString("updated_by")));
							voObj.setLastupdated(rst.getString("updated_time"));
						}

						voObj.setTimetableId(rst.getString("timetable_id"));
						voObj.setTeachername(rst.getString("teachername"));
					}
					details.add(voObj);
				}
			

				
			
			}else{
				pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_TIMETABLE_LIST_BY_CLASS);
				pstmt.setString(1, accyearid.trim());
				pstmt.setString(2, classId);
				pstmt.setString(3, locationId);

				rst = pstmt.executeQuery();

				while (rst.next()) {

					TimeTableVo voObj = new TimeTableVo();

					if(rst.getString("timetable_id")==null || rst.getString("timetable_id").equalsIgnoreCase("")){

						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						voObj.setTimetableStatus("Yet to Create");
						voObj.setCreatedby("-");
						voObj.setCreateddate("-");
						voObj.setLastupdatedby("-");
						voObj.setLastupdated("-");
						voObj.setTimetableId("-");
						voObj.setTeachername(rst.getString("teachername"));
					}else{

						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						voObj.setTimetableStatus("Created");
						voObj.setCreatedby(getStaffName(rst.getString("created_by")));
						voObj.setCreateddate(rst.getString("created_date"));
						if(rst.getString("updated_by")==null || "".equalsIgnoreCase(rst.getString("updated_by"))){

							voObj.setLastupdatedby("-");
							voObj.setLastupdated("-");

						}else{

							voObj.setLastupdatedby(getStaffName(rst.getString("updated_by")));
							voObj.setLastupdated(rst.getString("updated_time"));
						}

						voObj.setTimetableId(rst.getString("timetable_id"));
						voObj.setTeachername(rst.getString("teachername"));
					}
					details.add(voObj);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Ending");
		return details;
	}

	@Override
	public ArrayList<TimeTableVo> getClassTimeTableListBySection(String accyearid, String classId, String sectionId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		
		try {
			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_TIMETABLE_LIST_BY_SECTION);
			pstmt.setString(1, accyearid.trim());
			pstmt.setString(2, classId);
			pstmt.setString(3, sectionId);
			System.out.println("pstmt is "+pstmt);

			rst = pstmt.executeQuery();

			while (rst.next()) {

				TimeTableVo voObj = new TimeTableVo();

				if(rst.getString("timetable_id")==null || "".equalsIgnoreCase(rst.getString("timetable_id"))){

					voObj.setClassid(rst.getString("classdetail_id_int"));
					voObj.setClassname(rst.getString("classdetails_name_var"));
					voObj.setSectionid(rst.getString("classsection_id_int"));
					voObj.setSectionname(rst.getString("classsection_name_var"));
					voObj.setTimetableStatus("Yet to Create");
					voObj.setCreatedby("-");
					voObj.setCreateddate("-");
					voObj.setLastupdatedby("-");
					voObj.setLastupdated("-");
					voObj.setTimetableId("-");
					voObj.setTeachername(rst.getString("teachername"));
				}else{

					voObj.setClassid(rst.getString("classdetail_id_int"));
					voObj.setClassname(rst.getString("classdetails_name_var"));
					voObj.setSectionid(rst.getString("classsection_id_int"));
					voObj.setSectionname(rst.getString("classsection_name_var"));
					voObj.setTimetableStatus("Created");
					voObj.setCreatedby(getStaffName(rst.getString("created_by")));
					voObj.setCreateddate(rst.getString("created_date"));
					if(rst.getString("updated_by")==null || "".equalsIgnoreCase(rst.getString("updated_by"))){

						voObj.setLastupdatedby("-");
						voObj.setLastupdated("-");

					}else{

						voObj.setLastupdatedby(getStaffName(rst.getString("updated_by")));
						voObj.setLastupdated(rst.getString("updated_time"));
					}

					voObj.setTimetableId(rst.getString("timetable_id"));
					voObj.setTeachername(rst.getString("teachername"));
				}
				details.add(voObj);
			}

			/*else{

				pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_TEACHER_TIMETABLE_LIST);
				pstmt.setString(1, accyearid.trim());

				rst = pstmt.executeQuery();

					while (rst.next()) {

						TimeTableVo voObj = new TimeTableVo();

						if(rst.getString("teachertimetable_id")==null || "".equalsIgnoreCase(rst.getString("teachertimetable_id"))){

							voObj.setTeacherId(rst.getString("TeacherID"));
							voObj.setRegno(rst.getString("registerId"));
							voObj.setTeachername(rst.getString("teachername"));
							voObj.setTimetableStatus("Yet to Create");
							voObj.setCreatedby("-");
							voObj.setCreateddate("-");
							voObj.setLastupdatedby("-");
							voObj.setLastupdated("-");
							voObj.setTimetableId("-");

						}else{

							voObj.setTeacherId(rst.getString("TeacherID"));
							voObj.setRegno(rst.getString("registerId"));
							voObj.setTeachername(rst.getString("teachername"));
							voObj.setTimetableStatus("Created");
							voObj.setCreatedby(getStaffName(rst.getString("created_by")));
							voObj.setCreateddate(rst.getString("created_date"));
							if(rst.getString("updated_date")==null || "".equalsIgnoreCase(rst.getString("updated_date"))){

								voObj.setLastupdatedby("-");
								voObj.setLastupdated("-");

							}else{

								voObj.setLastupdatedby(getStaffName(rst.getString("updated_date")));
								voObj.setLastupdated(rst.getString("updated_time"));
							}

							voObj.setTimetableId(rst.getString("teachertimetable_id"));

						}
						details.add(voObj);
					}

				}*/

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Ending");
		return details;
	}

	@Override
	public String getTeacherNameDetails(String classId, String sectionId) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getClassNameDetailsDao Starting");
		
		TimeTableVo vo = new TimeTableVo();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		
		String teachername = null;
		try {
			
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_TEACHERNAME_BY_CLASS_SECTION);
			pstmt.setString(1, classId);
			pstmt.setString(2, sectionId);
			rst = pstmt.executeQuery();
			
			while(rst.next()){
				teachername = rst.getString("teachername");
					
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getClassNameDetailsDao Ending");
		
		return teachername;
	}
	
	
	private List<String> getSubjectDetailForClass(String subId,String classid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSubjectfName Starting");

		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		String subjectName=null;
		List<String> arr = new ArrayList<String>();
		try {
			connection = JDBCConnection.getSeparateConnection();
			
			pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_SUBJECT_NAME);
			pstmt.setString(1, classid);
			
			rst = pstmt.executeQuery();
			while (rst.next()) {
				subjectName=rst.getString("subjectName");
				arr.add(subjectName);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSubjectfName Ending");
		return arr;
	}


	public ArrayList<TimeTableVo> getClassTimeTableTodayListByClass(
			String accyearid, String sectionid, String classId, String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getClassTimeTableTodayListByClass Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		PreparedStatement pstmt1 = null;
		ResultSet rst1 = null;
		Connection connection = null;
		if(locationId.equalsIgnoreCase("ALL")){
			locationId="%%";
		}
		if(classId.equalsIgnoreCase("ALL")){
			classId="%%";
		}
		if(sectionid.equalsIgnoreCase("ALL")){
			sectionid="%%";
		}
		try {
			connection = JDBCConnection.getSeparateConnection();

				pstmt = connection.prepareStatement("SELECT DISTINCT cd.classdetails_name_var,cd.classdetail_id_int,sec.classsection_name_var,sec.classsection_id_int,st.timetable_id FROM campus_timetable_student st JOIN campus_classdetail cd ON st.classId=cd.classdetail_id_int AND st.locationId=cd.locationId JOIN campus_classsection sec ON sec.classsection_id_int=st.sectionid AND st.locationId = sec.locationId  WHERE  st.accyearid=? AND st.locationId LIKE ? AND st.classid LIKE ? AND st.sectionid LIKE ? ORDER BY LENGTH(cd.classdetail_id_int),cd.classdetail_id_int,LENGTH(sec.classsection_id_int),sec.classsection_id_int");
				pstmt.setString(1, accyearid.trim());
				pstmt.setString(2, locationId);
				pstmt.setString(3, classId);
				pstmt.setString(4, sectionid);
				System.out.println("sjefhsodfhos="+pstmt);
				rst = pstmt.executeQuery();

				while (rst.next()) {
					
					
					int count=0;
					String timetable_id=rst.getString("timetable_id");
					
					TimeTableVo voObj = new TimeTableVo();

						voObj.setTimetableId(timetable_id);
						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						pstmt1 = connection.prepareStatement("SELECT tperiod1 AS teacher FROM `today_timetable` WHERE id=? UNION ALL SELECT tperiod2 FROM `today_timetable` WHERE id=? UNION ALL SELECT tperiod3 FROM `today_timetable` WHERE id=?  UNION ALL SELECT tperiod4 FROM `today_timetable` WHERE id=? UNION ALL SELECT tperiod5 FROM `today_timetable` WHERE id=? UNION ALL SELECT tperiod6 FROM `today_timetable` WHERE id=? UNION ALL SELECT tperiod7 FROM `today_timetable` WHERE id=? UNION ALL SELECT tperiod8 FROM `today_timetable` WHERE id=? UNION ALL SELECT tperiod9 FROM `today_timetable` WHERE id=? ");		
						for(int i=1;i<=9;i++)
							pstmt1.setString(i, timetable_id);
						
						rst1=pstmt1.executeQuery();
						while(rst1.next()){
							String teacherID="";
							for(int k=0;k<rst1.getString("teacher").split(",").length;k++){
								teacherID+="'"+rst1.getString("teacher").split(",")[k]+"'"+",";
							}
							teacherID = teacherID.replaceAll(",$", "");
							PreparedStatement chkpstmt=connection.prepareStatement("SELECT COUNT(*) FROM `campus_teacher_attendence` WHERE TeacherID IN("+teacherID+") AND AttendenceDate=CURDATE() AND AttendenceStatus='Present' ");
							
							
						
							ResultSet chkRs=chkpstmt.executeQuery();
							if(chkRs.next()){
								if(chkRs.getInt(1)==0)
								count++;
							}
							chkRs.close();
							chkpstmt.close();
						}
						
					
							voObj.setCount(count);
							details.add(voObj);
					
					
				}
			

			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (rst1 != null && (!rst1.isClosed())) {
					rst1.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getClassTimeTableTodayListByClass Ending");
		return details;
	}


	public synchronized ArrayList<TimeTableVo> getTodayTimeTableDetails(String timetableId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		PreparedStatement pstmt1 = null;
		ResultSet rst1 = null;
		
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();

		
			
				pstmt1 = connection.prepareStatement("SELECT DISTINCT vd.sectionid,vd.date,vd.classId,vd.today,vd.period1,vd.period2,vd.period3,vd.period4,vd.period5,vd.period6,vd.period7,vd.period8,vd.period9,vd.tperiod1,vd.tperiod2,vd.tperiod3,vd.tperiod4,vd.tperiod5,vd.tperiod6,vd.tperiod7,vd.tperiod8,vd.tperiod9 FROM today_timetable vd  WHERE vd.id=?");
				pstmt1.setString(1, timetableId.trim());
				//pstmt1.setString(2, classid);
			
							
				rst1 = pstmt1.executeQuery();

				
				while(rst1.next()) {
					String classid=rst1.getString("classId");
					TimeTableVo voObj = new TimeTableVo();
					
					voObj.setClassid(classid);
					voObj.setSectionid(rst1.getString("sectionid"));
					voObj.setDayid(rst1.getString("date"));
					voObj.setDayname(rst1.getString("today"));
					
					
					
					voObj.setPeriodId11(rst1.getString("period1").split(",")[0]);
					voObj.setPeriodId21(rst1.getString("period2").split(",")[0]);
					voObj.setPeriodId31(rst1.getString("period3").split(",")[0]);
					voObj.setPeriodId41(rst1.getString("period4").split(",")[0]);
					voObj.setPeriodId51(rst1.getString("period5").split(",")[0]);
					voObj.setPeriodId61(rst1.getString("period6").split(",")[0]);
					voObj.setPeriodId71(rst1.getString("period7").split(",")[0]);
					voObj.setPeriodId81(rst1.getString("period8").split(",")[0]);
					voObj.setPeriodId91(rst1.getString("period9").split(",")[0]);
					
					
					voObj.setTeacherId11(rst1.getString("tperiod1").split(",")[0]);
					voObj.setTeacherId21(rst1.getString("tperiod2").split(",")[0]);
					voObj.setTeacherId31(rst1.getString("tperiod3").split(",")[0]);
					voObj.setTeacherId41(rst1.getString("tperiod4").split(",")[0]);
					voObj.setTeacherId51(rst1.getString("tperiod5").split(",")[0]);
					voObj.setTeacherId61(rst1.getString("tperiod6").split(",")[0]);
					voObj.setTeacherId71(rst1.getString("tperiod7").split(",")[0]);
					voObj.setTeacherId81(rst1.getString("tperiod8").split(",")[0]);
					voObj.setTeacherId91(rst1.getString("tperiod9").split(",")[0]);
					
					
					
					
					
					
					
					voObj.setPeriodId12(rst1.getString("period1").split(",")[1]);
					voObj.setPeriodId22(rst1.getString("period2").split(",")[1]);
					voObj.setPeriodId32(rst1.getString("period3").split(",")[1]);
					voObj.setPeriodId42(rst1.getString("period4").split(",")[1]);
					voObj.setPeriodId52(rst1.getString("period5").split(",")[1]);
					voObj.setPeriodId62(rst1.getString("period6").split(",")[1]);
					voObj.setPeriodId72(rst1.getString("period7").split(",")[1]);
					voObj.setPeriodId82(rst1.getString("period8").split(",")[1]);
					voObj.setPeriodId92(rst1.getString("period9").split(",")[1]);
					
					voObj.setTeacherId12(rst1.getString("tperiod1").split(",")[1]);
					voObj.setTeacherId22(rst1.getString("tperiod2").split(",")[1]);
					voObj.setTeacherId32(rst1.getString("tperiod3").split(",")[1]);
					voObj.setTeacherId42(rst1.getString("tperiod4").split(",")[1]);
					voObj.setTeacherId52(rst1.getString("tperiod5").split(",")[1]);
					voObj.setTeacherId62(rst1.getString("tperiod6").split(",")[1]);
					voObj.setTeacherId72(rst1.getString("tperiod7").split(",")[1]);
					voObj.setTeacherId82(rst1.getString("tperiod8").split(",")[1]);
					voObj.setTeacherId92(rst1.getString("tperiod9").split(",")[1]);
					
					
					
					
			
					voObj.setPeriodId13(rst1.getString("period1").split(",")[2]);
					voObj.setPeriodId23(rst1.getString("period2").split(",")[2]);
					voObj.setPeriodId33(rst1.getString("period3").split(",")[2]);
					voObj.setPeriodId43(rst1.getString("period4").split(",")[2]);
					voObj.setPeriodId53(rst1.getString("period5").split(",")[2]);
					voObj.setPeriodId63(rst1.getString("period6").split(",")[2]);
					voObj.setPeriodId73(rst1.getString("period7").split(",")[2]);
					voObj.setPeriodId83(rst1.getString("period8").split(",")[2]);
					voObj.setPeriodId93(rst1.getString("period9").split(",")[2]);
					
					voObj.setTeacherId13(rst1.getString("tperiod1").split(",")[2]);
					voObj.setTeacherId23(rst1.getString("tperiod2").split(",")[2]);
					voObj.setTeacherId33(rst1.getString("tperiod3").split(",")[2]);
					voObj.setTeacherId43(rst1.getString("tperiod4").split(",")[2]);
					voObj.setTeacherId53(rst1.getString("tperiod5").split(",")[2]);
					voObj.setTeacherId63(rst1.getString("tperiod6").split(",")[2]);
					voObj.setTeacherId73(rst1.getString("tperiod7").split(",")[2]);
					voObj.setTeacherId83(rst1.getString("tperiod8").split(",")[2]);
					voObj.setTeacherId93(rst1.getString("tperiod9").split(",")[2]);
					
					
					
					voObj.setPeriodId14(rst1.getString("period1").split(",")[3]);
					voObj.setPeriodId24(rst1.getString("period2").split(",")[3]);
					voObj.setPeriodId34(rst1.getString("period3").split(",")[3]);
					voObj.setPeriodId44(rst1.getString("period4").split(",")[3]);
					voObj.setPeriodId54(rst1.getString("period5").split(",")[3]);
					voObj.setPeriodId64(rst1.getString("period6").split(",")[3]);
					voObj.setPeriodId74(rst1.getString("period7").split(",")[3]);
					voObj.setPeriodId84(rst1.getString("period8").split(",")[3]);
					voObj.setPeriodId94(rst1.getString("period9").split(",")[3]);
					
					voObj.setTeacherId14(rst1.getString("tperiod1").split(",")[3]);
					voObj.setTeacherId24(rst1.getString("tperiod2").split(",")[3]);
					voObj.setTeacherId34(rst1.getString("tperiod3").split(",")[3]);
					voObj.setTeacherId44(rst1.getString("tperiod4").split(",")[3]);
					voObj.setTeacherId54(rst1.getString("tperiod5").split(",")[3]);
					voObj.setTeacherId64(rst1.getString("tperiod6").split(",")[3]);
					voObj.setTeacherId74(rst1.getString("tperiod7").split(",")[3]);
					voObj.setTeacherId84(rst1.getString("tperiod8").split(",")[3]);
					voObj.setTeacherId94(rst1.getString("tperiod9").split(",")[3]);
					
					
					
					
					
				
					voObj.setPeriod11(getSubjectfName(rst1.getString("period1").split(",")[0],classid));
					voObj.setPeriod21(getSubjectfName(rst1.getString("period2").split(",")[0],classid));
					voObj.setPeriod31(getSubjectfName(rst1.getString("period3").split(",")[0],classid));
					voObj.setPeriod41(getSubjectfName(rst1.getString("period4").split(",")[0],classid));
					voObj.setPeriod51(getSubjectfName(rst1.getString("period5").split(",")[0],classid));
					voObj.setPeriod61(getSubjectfName(rst1.getString("period6").split(",")[0],classid));
					voObj.setPeriod71(getSubjectfName(rst1.getString("period7").split(",")[0],classid));
					voObj.setPeriod81(getSubjectfName(rst1.getString("period8").split(",")[0],classid));
					voObj.setPeriod91(getSubjectfName(rst1.getString("period9").split(",")[0],classid));
					
					
					voObj.setPeriod12(getSubjectfName(rst1.getString("period1").split(",")[1],classid));
					voObj.setPeriod22(getSubjectfName(rst1.getString("period2").split(",")[1],classid));
					voObj.setPeriod32(getSubjectfName(rst1.getString("period3").split(",")[1],classid));
					voObj.setPeriod42(getSubjectfName(rst1.getString("period4").split(",")[1],classid));
					voObj.setPeriod52(getSubjectfName(rst1.getString("period5").split(",")[1],classid));
					voObj.setPeriod62(getSubjectfName(rst1.getString("period6").split(",")[1],classid));
					voObj.setPeriod72(getSubjectfName(rst1.getString("period7").split(",")[1],classid));
					voObj.setPeriod82(getSubjectfName(rst1.getString("period8").split(",")[1],classid));
					voObj.setPeriod92(getSubjectfName(rst1.getString("period9").split(",")[1],classid));
					
					
					
					voObj.setPeriod13(getSubjectfName(rst1.getString("period1").split(",")[2],classid));
					voObj.setPeriod23(getSubjectfName(rst1.getString("period2").split(",")[2],classid));
					voObj.setPeriod33(getSubjectfName(rst1.getString("period3").split(",")[2],classid));
					voObj.setPeriod43(getSubjectfName(rst1.getString("period4").split(",")[2],classid));
					voObj.setPeriod53(getSubjectfName(rst1.getString("period5").split(",")[2],classid));
					voObj.setPeriod63(getSubjectfName(rst1.getString("period6").split(",")[2],classid));
					voObj.setPeriod73(getSubjectfName(rst1.getString("period7").split(",")[2],classid));
					voObj.setPeriod83(getSubjectfName(rst1.getString("period8").split(",")[2],classid));
					voObj.setPeriod93(getSubjectfName(rst1.getString("period9").split(",")[2],classid));
					
					
					
					voObj.setPeriod14(getSubjectfName(rst1.getString("period1").split(",")[3],classid));
					voObj.setPeriod24(getSubjectfName(rst1.getString("period2").split(",")[3],classid));
					voObj.setPeriod34(getSubjectfName(rst1.getString("period3").split(",")[3],classid));
					voObj.setPeriod44(getSubjectfName(rst1.getString("period4").split(",")[3],classid));
					voObj.setPeriod54(getSubjectfName(rst1.getString("period5").split(",")[3],classid));
					voObj.setPeriod64(getSubjectfName(rst1.getString("period6").split(",")[3],classid));
					voObj.setPeriod74(getSubjectfName(rst1.getString("period7").split(",")[3],classid));
					voObj.setPeriod84(getSubjectfName(rst1.getString("period8").split(",")[3],classid));
					voObj.setPeriod94(getSubjectfName(rst1.getString("period9").split(",")[3],classid));
					
					details.add(voObj);
					
					
					
					
				}
		 
		}catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} 
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (rst1 != null && (!rst1.isClosed())) {
					rst1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Ending");
		return details;
	}


	public String updateTodayTimeTableDetails(TimeTablePojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : updateTodayTimeTableDetails Starting");
		
		PreparedStatement tpstmt =null;
		PreparedStatement ctdStatement=null;
		PreparedStatement ctdStatement1=null;
		String result = null;
		Connection connection = null;
		List<String> teacherIdArrayList=new ArrayList<String>();
		List<String> timtabeIdArraylist=new ArrayList<String>();
		List<String> dayidArraylist=new ArrayList<String>();
		List<String> periodArrayList=new ArrayList<String>();
		List<String> subjectArrayList=new ArrayList<String>();
		try {
			int status=0;
			
			connection = JDBCConnection.getSeparateConnection();
			
					for (int i = 0; i < pojo.getDayid().length; i++) {
					tpstmt=connection.prepareStatement("update today_timetable set tperiod1=?,tperiod2=?,tperiod3=?,tperiod4=?,tperiod5=?,tperiod6=?,tperiod7=?,tperiod8=?,tperiod9=? where id=?");
					
					
					tpstmt.setString(1, pojo.getStperiod1().split(":")[i].replaceFirst("^,", ""));
					tpstmt.setString(2, pojo.getStperiod2().split(":")[i].replaceFirst("^,", ""));
					tpstmt.setString(3, pojo.getStperiod3().split(":")[i].replaceFirst("^,", ""));
					tpstmt.setString(4, pojo.getStperiod4().split(":")[i].replaceFirst("^,", ""));
					tpstmt.setString(5, pojo.getStperiod5().split(":")[i].replaceFirst("^,", ""));
					tpstmt.setString(6, pojo.getStperiod6().split(":")[i].replaceFirst("^,", ""));
					tpstmt.setString(7, pojo.getStperiod7().split(":")[i].replaceFirst("^,", ""));
					tpstmt.setString(8, pojo.getStperiod8().split(":")[i].replaceFirst("^,", ""));
					tpstmt.setString(9, pojo.getStperiod9().split(":")[i].replaceFirst("^,", ""));
					tpstmt.setString(10, pojo.getTimetableID());
					System.out.println("tpstmt="+tpstmt);
					status=tpstmt.executeUpdate();
					
					
					for(int l=0;l<pojo.getStperiod1().split(":")[i].replaceFirst("^,", "").split(",").length;l++) {
						String techerId=pojo.getStperiod1().split(":")[i].replaceFirst("^,", "").split(",")[l];
						
						if(!techerId.equalsIgnoreCase("-")) {
							teacherIdArrayList.add(techerId);
							periodArrayList.add("period1");
							dayidArraylist.add(pojo.getDayid()[i]);
							timtabeIdArraylist.add(pojo.getTimetableID());
						}
						
						
						
						 techerId=pojo.getStperiod2().split(":")[i].replaceFirst("^,", "").split(",")[l];
						 if(!techerId.equalsIgnoreCase("-")) {
								teacherIdArrayList.add(techerId);
								periodArrayList.add("period2");
								dayidArraylist.add(pojo.getDayid()[i]);
								timtabeIdArraylist.add(pojo.getTimetableID());
							}
						 
						 
						 techerId=pojo.getStperiod3().split(":")[i].replaceFirst("^,", "").split(",")[l];
						 if(!techerId.equalsIgnoreCase("-")) {
								teacherIdArrayList.add(techerId);
								periodArrayList.add("period3");
								dayidArraylist.add(pojo.getDayid()[i]);
								timtabeIdArraylist.add(pojo.getTimetableID());
							}
						 
						 techerId=pojo.getStperiod4().split(":")[i].replaceFirst("^,", "").split(",")[l];
						 if(!techerId.equalsIgnoreCase("-")) {
								teacherIdArrayList.add(techerId);
								periodArrayList.add("period4");
								dayidArraylist.add(pojo.getDayid()[i]);
								timtabeIdArraylist.add(pojo.getTimetableID());
							}
						 
						 techerId=pojo.getStperiod5().split(":")[i].replaceFirst("^,", "").split(",")[l];
						 if(!techerId.equalsIgnoreCase("-")) {
								teacherIdArrayList.add(techerId);
								periodArrayList.add("period5");
								dayidArraylist.add(pojo.getDayid()[i]);
								timtabeIdArraylist.add(pojo.getTimetableID());
							}
						 
						 techerId=pojo.getStperiod6().split(":")[i].replaceFirst("^,", "").split(",")[l];
						 if(!techerId.equalsIgnoreCase("-")) {
								teacherIdArrayList.add(techerId);
								periodArrayList.add("period6");
								dayidArraylist.add(pojo.getDayid()[i]);
								timtabeIdArraylist.add(pojo.getTimetableID());
							}
						 
						 techerId=pojo.getStperiod7().split(":")[i].replaceFirst("^,", "").split(",")[l];
						 if(!techerId.equalsIgnoreCase("-")) {
								teacherIdArrayList.add(techerId);
								periodArrayList.add("period7");
								dayidArraylist.add(pojo.getDayid()[i]);
								timtabeIdArraylist.add(pojo.getTimetableID());
							}
						 
						 techerId=pojo.getStperiod8().split(":")[i].replaceFirst("^,", "").split(",")[l];
						 if(!techerId.equalsIgnoreCase("-")) {
								teacherIdArrayList.add(techerId);
								periodArrayList.add("period8");
								dayidArraylist.add(pojo.getDayid()[i]);
								timtabeIdArraylist.add(pojo.getTimetableID());
							}
						 
						 techerId=pojo.getStperiod9().split(":")[i].replaceFirst("^,", "").split(",")[l];
						 if(!techerId.equalsIgnoreCase("-")) {
								teacherIdArrayList.add(techerId);
								periodArrayList.add("period9");
								dayidArraylist.add(pojo.getDayid()[i]);
								timtabeIdArraylist.add(pojo.getTimetableID());
							}
						 
						
					}
				}
				for(int k=0;k<teacherIdArrayList.size();k++) {
					 ctdStatement=connection.prepareStatement("UPDATE today_timetable_details_for_substitution SET teacherid=? WHERE timtable_id=?  AND period=? AND accyearid=? AND teacherid!=?");
					ctdStatement.setString(1, teacherIdArrayList.get(k));
					ctdStatement.setString(2, timtabeIdArraylist.get(k));
					ctdStatement.setString(3, periodArrayList.get(k));
					ctdStatement.setString(4, pojo.getAccyearid().trim());
					ctdStatement.setString(5, teacherIdArrayList.get(k));
					ctdStatement.executeUpdate();
					
					
					ctdStatement1=connection.prepareStatement("UPDATE today_timetable_details SET teacherid=? WHERE timtable_id=?  AND period=? AND accyearid=? AND teacherid!=?");
					ctdStatement1.setString(1, teacherIdArrayList.get(k));
					ctdStatement1.setString(2, timtabeIdArraylist.get(k));
					ctdStatement1.setString(3, periodArrayList.get(k));
					ctdStatement1.setString(4, pojo.getAccyearid().trim());
					ctdStatement1.setString(5, teacherIdArrayList.get(k));
					ctdStatement1.executeUpdate();
					
					System.out.println("ckecking==="+ctdStatement);
				}
			
				if (status > 0 ) {
					result="success";
				} else {
						result = "fail";
					
				}
				
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				
				if (tpstmt != null && (!tpstmt.isClosed())) {
					tpstmt.close();
				}
				
				if (ctdStatement != null && (!ctdStatement.isClosed())) {
					ctdStatement.close();
				}
				
				if (ctdStatement1 != null && (!ctdStatement1.isClosed())) {
					ctdStatement1.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : updateTodayTimeTableDetails Ending");

		return result;
	}


	public ArrayList<TimeTableVo> getTeacherTimeTableTodayListByClass() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTeacherTimeTableTodayListByClass Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		int count=0;
		PreparedStatement pstmt1 = null;
		ResultSet rst1 = null;
		String status="";
		
		Connection connection = null;
		
		
		try {
			connection = JDBCConnection.getSeparateConnection();
	
				pstmt1=connection.prepareStatement("SELECT COUNT(*) FROM `substitution_log_audit` WHERE createdTime LIKE ?");
				pstmt1.setString(1, HelperClass.getCurrentSqlDate()+"%");
				rst1=pstmt1.executeQuery();
				System.out.println("substitution_log_audit= "+pstmt1);
				
				while(rst1.next()) {
					count=rst1.getInt(1);
				}
				if(count==0) {
					pstmt = connection.prepareStatement("SELECT ct.`Abbreviative_Id`,ct.`FirstName`,GROUP_CONCAT(CONCAT(cc.`classdetails_name_var`,'-',cs.`classsection_name_var`)) class ,GROUP_CONCAT(csub.`subjectName`) subj,GROUP_CONCAT(ctd.period) period FROM `today_timetable_details_for_substitution` ctd JOIN `campus_timetable_day` cday ON cday.daycode=ctd.daycode JOIN `campus_teachers` ct ON ct.`TeacherID`=ctd.teacherid JOIN `campus_subject` csub ON csub.subjectID=ctd.subjectid  JOIN `campus_timetable_student` cts ON cts.`timetable_id`=ctd.timtable_id JOIN `campus_classsection` cs ON cs.`classsection_id_int`=cts.sectionid JOIN `campus_classdetail` cc ON (cc.`classdetail_id_int`=cs.classdetail_id_int AND cc.locationId=cs.locationId)  WHERE ctd.teacherid IN(SELECT TeacherID FROM `campus_teacher_attendence` WHERE `TeacherID` IN(SELECT DISTINCT `teacherid` FROM `today_timetable_details_for_substitution` ) AND `AttendenceDate`=CURDATE() AND AttendenceStatus!='Present') AND cday.dayname=DAYNAME(CURDATE()) GROUP BY ctd.teacherid");
					status="notSubstituted";
				}
				
				else {
					pstmt = connection.prepareStatement("SELECT ct.`Abbreviative_Id`,ct.`FirstName`,GROUP_CONCAT(CONCAT(cc.`classdetails_name_var`,'-',cs.`classsection_name_var`)) class,GROUP_CONCAT(csub.`subjectName`) subj,GROUP_CONCAT(ctd.period) period FROM `today_timetable_details` ctd JOIN `campus_timetable_day` cday ON cday.daycode=ctd.daycode JOIN `campus_teachers` ct ON ct.`TeacherID`=ctd.teacherid JOIN `campus_subject` csub ON csub.subjectID=ctd.subjectid  JOIN `campus_timetable_student` cts ON cts.`timetable_id`=ctd.timtable_id JOIN `campus_classsection` cs ON cs.`classsection_id_int`=cts.sectionid JOIN `campus_classdetail` cc ON (cc.`classdetail_id_int`=cs.classdetail_id_int AND cc.locationId=cs.locationId)  WHERE  cday.dayname=DAYNAME(CURDATE()) GROUP BY ctd.teacherid");
					status="substituted";
				}
				System.out.println("list= "+pstmt);
				rst = pstmt.executeQuery();

				while (rst.next()) {
					TimeTableVo vo=new TimeTableVo();
					vo.setTeachername(rst.getString("Abbreviative_Id")+"-"+rst.getString("FirstName"));
					vo.setClassname(rst.getString("class"));
					vo.setPeriod1(rst.getString("period")+"-"+rst.getString("subj"));
					vo.setStatus(status);
					details.add(vo);
				
				}
			
				
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (rst1 != null && (!rst1.isClosed())) {
					rst1.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}	
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTeacherTimeTableTodayListByClass Ending");
		return details;
	}


	public String getSubstitute(String userid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSubstitute Starting");

		
		PreparedStatement pstmt0 = null;
		PreparedStatement tpstmt=null;
		PreparedStatement trntmt=null;
		PreparedStatement pstmt = null;
		PreparedStatement upstmt=null;
		PreparedStatement tupstmt=null;
		ResultSet rst = null;
		int count=0;
		int abc=0;
		PreparedStatement pstmt1 = null;
		ResultSet rst1 = null;
		String status="";
		
		Connection connection = null;
		
		
		try {
			connection = JDBCConnection.getSeparateConnection();
			connection.setAutoCommit(false);
				trntmt=connection.prepareStatement("DELETE FROM today_timetable_details");
				trntmt.executeUpdate();
				pstmt0=connection.prepareStatement("INSERT INTO `substitution_log_audit`(createdBy) VALUES (?)");
				pstmt0.setString(1,userid);
				pstmt0.executeUpdate();
				pstmt = connection.prepareStatement("SELECT ct.designation,ctd.*  FROM `today_timetable_details_for_substitution` ctd JOIN `campus_timetable_day` cday ON cday.daycode=ctd.daycode JOIN `campus_teachers` ct ON ct.`TeacherID`=ctd.teacherid  WHERE ctd.teacherid IN(SELECT TeacherID FROM `campus_teacher_attendence` WHERE `TeacherID` IN(SELECT DISTINCT `teacherid` FROM `today_timetable_details_for_substitution` ) AND `AttendenceDate`=CURDATE() AND AttendenceStatus!='Present') AND cday.dayname=DAYNAME(CURDATE())");
				
				rst = pstmt.executeQuery();
				while (rst.next()) {
					String teacherId="";
				
					pstmt1=connection.prepareStatement("SELECT TeacherID FROM `campus_teachers` WHERE TeacherID IN(SELECT TeacherID FROM `campus_teacher_attendence` WHERE `AttendenceDate`=CURDATE() AND AttendenceStatus='Present') AND TeacherID NOT IN(SELECT DISTINCT ctd.`teacherid` FROM `today_timetable_details_for_substitution` ctd JOIN `campus_timetable_day` cday ON cday.daycode=ctd.daycode WHERE  cday.dayname=DAYNAME(CURDATE()) AND ctd.period =?) AND designation=? ORDER BY RAND() LIMIT 1");
					pstmt1.setString(1, rst.getString("period"));
					pstmt1.setString(2,rst.getString("designation"));
					System.out.println("Teacher="+pstmt1);
					rst1=pstmt1.executeQuery();
					if(rst1.next()) {
						teacherId=rst1.getString("TeacherID");
						
						 tpstmt=connection.prepareStatement("INSERT INTO `today_timetable_details` (`teacherid`,`subjectid`,`timtable_id`,`daycode`,`period`,`accyearid`,`created_by`) VALUES(?,?,?,?,?,?,?)");
						tpstmt.setString(1, teacherId);
						tpstmt.setString(2, rst.getString("subjectid"));
						tpstmt.setString(3, rst.getString("timtable_id"));
						tpstmt.setString(4, rst.getString("daycode"));
						tpstmt.setString(5, rst.getString("period"));
						tpstmt.setString(6, rst.getString("accyearid"));
						tpstmt.setString(7, userid);
						tpstmt.executeUpdate();
						upstmt=connection.prepareStatement("UPDATE today_timetable_details_for_substitution SET teacherid=? WHERE teacherid=? AND timtable_id=? AND daycode=? AND period=? AND accyearid=?");
						upstmt.setString(1, teacherId);
						upstmt.setString(2, rst.getString("teacherid"));
						upstmt.setString(3, rst.getString("timtable_id"));
						upstmt.setString(4, rst.getString("daycode"));
						upstmt.setString(5, rst.getString("period"));
						upstmt.setString(6, rst.getString("accyearid"));
						upstmt.executeUpdate();
						
						
						tupstmt=connection.prepareStatement("UPDATE today_timetable SET t"+rst.getString("period")+"=? WHERE  id=? AND daycode=?  AND accyearid=?");
						tupstmt.setString(1, teacherId+",-,-,-,");
						tupstmt.setString(2, rst.getString("timtable_id"));
						tupstmt.setString(3, rst.getString("daycode"));
						tupstmt.setString(4, rst.getString("accyearid"));
						tupstmt.executeUpdate();
						
						
						count++;
					}
					else {
						abc++;
						 tpstmt=connection.prepareStatement("INSERT INTO `today_timetable_details` (`teacherid`,`subjectid`,`timtable_id`,`daycode`,`period`,`accyearid`,`created_by`) VALUES(?,?,?,?,?,?,?)");
							tpstmt.setString(1, "TEM"+abc);
							tpstmt.setString(2, rst.getString("subjectid"));
							tpstmt.setString(3, rst.getString("timtable_id"));
							tpstmt.setString(4, rst.getString("daycode"));
							tpstmt.setString(5, rst.getString("period"));
							tpstmt.setString(6, rst.getString("accyearid"));
							tpstmt.setString(7, userid);
							tpstmt.executeUpdate();
							count++;
					}
				
				}
			
			
				if(count>0) {
					connection.commit();
					status="true";
				}
				else {
					status="false";
				}
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (rst1 != null && (!rst1.isClosed())) {
					rst1.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (tpstmt != null && (!tpstmt.isClosed())) {
					tpstmt.close();
				}
				if (upstmt != null && (!upstmt.isClosed())) {
					upstmt.close();
				}
				if (tupstmt != null && (!tupstmt.isClosed())) {
					tupstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSubstitute Ending");
		return status;
	}

	
	}