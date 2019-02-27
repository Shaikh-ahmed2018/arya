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
import com.centris.campus.dao.SuddenHolidayListDao;
import com.centris.campus.pojo.SuddenHolidaysPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SuddenHolidaySqlUtil;
import com.centris.campus.vo.SuddenHolidaySMSVO;

public class SuddenHolidayListDaoImpl implements SuddenHolidayListDao {

	private static Logger logger = Logger.getLogger(SuddenHolidayListDaoImpl.class);
	public ArrayList<SuddenHolidaySMSVO> SuddenHolidayList() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in SuddenHolidayListDaoImpl: SuddenHolidayList : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SuddenHolidaySMSVO> list = new ArrayList<SuddenHolidaySMSVO>();
		Connection conn = null;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement(SuddenHolidaySqlUtil.GET_SUDDEN_HOLIDAY_DETAILS);
			
			System.out.println("SUDDEN_HOLIDAY "+pstmt);
			
			rs=pstmt.executeQuery();
			while (rs.next()) {
				
				SuddenHolidaySMSVO vo = new SuddenHolidaySMSVO();
				
				vo.setDate(HelperClass.convertDatabaseToUI(rs.getString("SUDDENHOLIDAYS_DATE")));
				vo.setSmstext(rs.getString("SUDDENHOLIDAYS_CONTENT"));
				vo.setSuddenholidayscode(rs.getString("SUDDENHOLIDAYS_CODE"));
				
				System.out.println("SUDDENHOLIDAYS_DATE "+HelperClass.convertDatabaseToUI(rs.getString("SUDDENHOLIDAYS_DATE")));
				System.out.println("SUDDENHOLIDAYS_CONTENT "+rs.getString("SUDDENHOLIDAYS_CONTENT"));
				System.out.println("SUDDENHOLIDAYS_CODE "+rs.getString("SUDDENHOLIDAYS_CODE"));
				
				list.add(vo);
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
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
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
		
		return list;
	}
	
	
	
	
	public String AddSuddenHoliday(SuddenHolidaysPojo upojo){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidayListDaoImpl : storeSuudenHolidaysDetails Starting");
			
			PreparedStatement pstmt=null;
			int count=0;
			String status=null;
			Connection conn = null;
			
			
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
				pstmt= conn.prepareStatement(SuddenHolidaySqlUtil.STORE_SUDDENHOLIDAYS_SMS_DETAILS);
						//SQLUtilConstants.STORE_SUDDENHOLIDAYS_SMS_DETAILS);
			//(SUDDENHOLIDAYS_CODE,SUDDENHOLIDAYS_DATE,SUDDENHOLIDAYS_CONTENT,isSection,isStudent,CREATE_TIME,CREATED_BY) values(?,?,?,?,?,?,?)
				
				pstmt.setString(1, upojo.getSuddenholidayscode());
				pstmt.setString(2, upojo.getHdate());
				pstmt.setString(3, upojo.getSmstext().trim());
				pstmt.setInt(4, upojo.getIssection());
				pstmt.setInt(5, upojo.getIsstudent());
				pstmt.setTimestamp(6, upojo.getCreatedate());
				pstmt.setString(7, upojo.getCreatedby());
				
				System.out.println("STORE_SUDDENHOLIDAYS_SMS_DETAILS "+pstmt);
				
				count =pstmt.executeUpdate();
				
				System.out.println("Insert Query ::::" +pstmt);
				if(count>0)
				{
					
					Runnable r = new SMSThread(upojo.getSuddenholidayscode(),
							upojo.getHdate(), "Holiday");
					new Thread(r).start();
					
					status="Holiday Details Sent Successfully";
					System.out.println(status);
				}
				else
				{
					status="Holiday Details Sending Failed";
					System.out.println(status);
				}
				
				
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			finally {

				try {
					
					if (pstmt != null && !pstmt.isClosed()) {
						pstmt.close();
					}
					if (conn != null && !conn.isClosed()) {

						conn.close();
					}

				} catch (Exception exception) {
					logger.error(exception.getMessage(), exception);
					exception.printStackTrace();
				}
			}
			
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in SuddenHolidayListDaoImpl : storeSuudenHolidaysDetails  Ending");
		
		return status;
	}
	
	
	
public String storeSuudenHolidaysSections(SuddenHolidaysPojo suddenholidayspojo){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidayListDaoImpl : SuddenHolidayListDaoImpl : Starting");
		
		PreparedStatement ps_sectiondetails=null;
		int count=0;
		ResultSet rs_all_sections=null;
		String status=null;
		
		try {
			
			
			ps_sectiondetails=(PreparedStatement)JDBCConnection.getStatement(SuddenHolidaySqlUtil.STORE_SUDDENHOLIDAYS_SECTION_DETAILS);

			for(int i=0;i<suddenholidayspojo.getSectionid().length;i++){
				
				ps_sectiondetails.setString(1, suddenholidayspojo.getSuddenholidayscode());
				ps_sectiondetails.setString(2, suddenholidayspojo.getSectionid()[i]);
				ps_sectiondetails.setTimestamp(3, suddenholidayspojo.getCreatedate());
				ps_sectiondetails.setString(4, suddenholidayspojo.getCreatedby());
				
				System.out.println("SECTION_DETAILS "+ps_sectiondetails);
				
				count=ps_sectiondetails.executeUpdate();
				System.out.println("ps_sectiondetails Query ::::" +ps_sectiondetails);
				if(count>0)
				{
					status = "Holiday Details Sent Successfully";
					System.out.println(status);
				}
				else
				{
					status = "Holiday Details Sending Failed";
					System.out.println(status);
				}
				
			}
			
			
			
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {
				if (rs_all_sections != null && !rs_all_sections.isClosed()) {
					rs_all_sections.close();
				}
				if (ps_sectiondetails != null && !ps_sectiondetails.isClosed()) {
					ps_sectiondetails.close();
				}
				

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidayListDaoImpl : storeSuudenHolidaysSections  Ending");
		
		return status;
	}





public boolean validateSuddenHolidaysSms(String date, String smstext) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SuddenHolidayListDaoImpl : validateSuudenHolidayssSms Starting");
	
	PreparedStatement ps_checkvalidation=null;
	int count=0;
	ResultSet rs_checkvalidation=null;
	boolean status=false;
	Connection conn = null;
	
	
	try {
		
		conn =  JDBCConnection.getConnection();
		
		ps_checkvalidation=conn.prepareStatement(SuddenHolidaySqlUtil.VALIDATE_SUDDENHOLIDAYS_SMS);
		
		
		ps_checkvalidation.setString(1,date);
		ps_checkvalidation.setString(2, smstext.trim());
		
		
		rs_checkvalidation=ps_checkvalidation.executeQuery();
		
		System.out.println("ps_checkvalidation" +ps_checkvalidation);
		
		while(rs_checkvalidation.next()){
			
			count=rs_checkvalidation.getInt(1);
			
		}
		
		if(count>0){
			
			status=true;
		}else{
			
			status=false;
		}
		
	}catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	finally {

		try {
			if (rs_checkvalidation != null && !rs_checkvalidation.isClosed()) {
				rs_checkvalidation.close();
			}
			if (ps_checkvalidation != null && !ps_checkvalidation.isClosed()) {
				ps_checkvalidation.close();
			}
			if (conn != null && !conn.isClosed()) {

				conn.close();
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SuddenHolidayListDaoImpl : validateSuudenHolidayssSms ending");
	
	return status;
}

}
