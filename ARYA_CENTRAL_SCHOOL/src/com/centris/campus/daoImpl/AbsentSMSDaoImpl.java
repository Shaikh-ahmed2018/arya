package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.admin.SMSThread;
import com.centris.campus.dao.AbsentSMSDao;
import com.centris.campus.pojo.AbsentsSMSPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SuddenHolidaySqlUtil;
import com.centris.campus.vo.SuddenHolidaySMSVO;

public class AbsentSMSDaoImpl implements AbsentSMSDao{

	private static final Logger logger = Logger.getLogger(AbsentSMSDaoImpl.class);
	
public ArrayList<AbsentsSMSPojo> absentlist() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AbsentSMSDaoImpl : absentlist Starting");

		PreparedStatement ps_studentdetails = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<AbsentsSMSPojo> list = new ArrayList<AbsentsSMSPojo>();
		AbsentsSMSPojo absentsSMSPojo = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			ps_studentdetails = conn.prepareStatement(SuddenHolidaySqlUtil.GET_ABSENT_STUDENT_DETAILS);

			
			rs = ps_studentdetails.executeQuery();
			
			while (rs.next()) {
				
				absentsSMSPojo = new AbsentsSMSPojo();
				
				absentsSMSPojo.setSmstext(rs.getString("ABSENT_CONTENT"));
				absentsSMSPojo.setStudentName(rs.getString("name"));
				absentsSMSPojo.setSection(rs.getString("classsection_name_var"));
				absentsSMSPojo.setClassname(rs.getString("classdetails_name_var"));
				absentsSMSPojo.setDate(rs.getString("ABSENT_DATE"));
				
				list.add(absentsSMSPojo);
				
			}
	

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AbsentSMSDaoImpl : absentlist ending");
		return list;
	}

public int storeAbsentDetails(AbsentsSMSPojo absentpojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AbsentSMSDaoImpl : storeAbsentDetails Starting");
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	int count = 0;
	
     
     
     try{
    	 
    	 conn = JDBCConnection.getSeparateConnection();
    	 pstmt = conn.prepareStatement(SuddenHolidaySqlUtil.STORE_ABSENT_SMS_DETAILS);
    	 pstmt.setString(1, absentpojo.getAbsentcode());
			 pstmt.setString(2, absentpojo.getDate());
			 pstmt.setString(3, absentpojo.getSmstext().trim());
			 pstmt.setInt(4, absentpojo.getIssection());
			 pstmt.setInt(5, absentpojo.getIsstudent());
			 pstmt.setTimestamp(6, absentpojo.getCreatedate());
			 pstmt.setString(7, absentpojo.getCreatedby());

			 count = pstmt.executeUpdate();		
			 
			 if(count>0)
			 {
				
				
				/*status="Holiday Details Stored Successfully";
				System.out.println(status);*/
			 }
		
	
     } catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AbsentSMSDaoImpl : storeAbsentDetails  Ending");
		return count;
	
}

public int storeAbsentStudent(AbsentsSMSPojo absentpojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AbsentSMSDaoImpl : storeAbsentStudent Starting");

	PreparedStatement ps_studentdetails = null;
	int count = 0;
	Connection conn = null;
	try {

		conn = JDBCConnection.getSeparateConnection();
		ps_studentdetails = conn.prepareStatement(SuddenHolidaySqlUtil.STORE_ABSENT_STUDENT_DETAILS);

		
	
		
		for (int i = 0; i < absentpojo.getStudentid().length; i++) {

			
			
			ps_studentdetails.setString(1, absentpojo.getAbsentcode());
			ps_studentdetails.setString(2, absentpojo.getStudentid()[i]);
			ps_studentdetails.setTimestamp(3, absentpojo.getCreatedate());
			ps_studentdetails.setString(4, absentpojo.getCreatedby());

			count = ps_studentdetails.executeUpdate();
			
			
			
			if (count > 0) {


				Runnable r = new SMSThread(absentpojo.getAbsentcode(),
						absentpojo.getDate(), "Absent");
				new Thread(r).start();
				count=1;
			}
			else
			{
				count=0;
			}
		}

	

	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AbsentSMSDaoImpl : storeAbsentStudent ending");
	return count;
}

public int storeAbsentSections(AbsentsSMSPojo absentpojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())	+ " Control in SuddenHolidayListDaoImpl: SuddenHolidayList : Starting");
	
	PreparedStatement ps_sectiondetails = null;
	ResultSet rs = null;
	ArrayList<SuddenHolidaySMSVO> list = new ArrayList<SuddenHolidaySMSVO>();
	Connection conn = null;
	int count = 0;
	
	
	try {
		
		conn = JDBCConnection.getSeparateConnection();
		ps_sectiondetails = conn.prepareStatement(SuddenHolidaySqlUtil.STORE_ABSENT_SECTION_DETAILS);
		
		for (int i = 0; i < absentpojo.getSectionid().length; i++) {

			ps_sectiondetails.setString(1,
					absentpojo.getAbsentcode());
			ps_sectiondetails.setString(2,
					absentpojo.getSectionid()[i]);
			ps_sectiondetails.setTimestamp(3,
					absentpojo.getCreatedate());
			ps_sectiondetails.setString(4,
					absentpojo.getCreatedby());

			count = ps_sectiondetails.executeUpdate();
		
		}
		
		
		
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e1) {

		logger.error(e1.getMessage(), e1);
		e1.printStackTrace();
	} finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (ps_sectiondetails != null && (!ps_sectiondetails.isClosed())) {
				ps_sectiondetails.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
	}

	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SuddenHolidayListDaoImpl: SuddenHolidayList : Ending");
	
	return count;
}

public boolean validateAbsentSms(String date, String smstext) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AbsentSMSDaoImpl : absentlist Starting");

	PreparedStatement ps_studentdetails = null;
	ResultSet rs = null;
	Connection conn = null;
    boolean status = true;
    
	try {
		
		conn = JDBCConnection.getSeparateConnection();
		
		ps_studentdetails = conn.prepareStatement(SuddenHolidaySqlUtil.GET_COUNT_ABSENT);
		ps_studentdetails.setString(1, smstext);
		ps_studentdetails.setString(2, date);
		
		rs = ps_studentdetails.executeQuery();
		
		
		
		while (rs.next()) {
			
			int count = rs.getInt(1);
			
			if(count>0)
			{
				status = true;
			}
			else
			{
				status=false;
			}
		}
		
		
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AbsentSMSDaoImpl : absentlist ending");

	return status;
}

}
