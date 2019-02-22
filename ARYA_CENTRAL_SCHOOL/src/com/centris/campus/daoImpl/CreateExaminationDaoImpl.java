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

import com.centris.campus.forms.CreateExaminationForm;
import com.centris.campus.pojo.AcademicYearPojo;
import com.centris.campus.pojo.LstmsAccadamicYear;
import com.centris.campus.pojo.LstmsExaminationPOJO;
import com.centris.campus.util.ExamSqlUtils;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.StreamSqlUtils;
import com.centris.campus.vo.ExaminationDetailsVo;

public class CreateExaminationDaoImpl {
	private static final Logger logger = Logger
			.getLogger(CreateExaminationDaoImpl.class);

	public ArrayList<LstmsAccadamicYear> getAccadamicYearsDaoImpl() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreateExaminationDaoImpl: getAccadamicYearsDaoImpl  Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LstmsAccadamicYear> AllAccYrs = null;
		Connection conn = null;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ExamSqlUtils.GET_ALL_ACCYEARS);
			AllAccYrs = new ArrayList<LstmsAccadamicYear>();
			
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
			
	     LstmsAccadamicYear LAY = new LstmsAccadamicYear();
		    LAY.setAccyearid(String.valueOf(rs.getString("acadamic_id")));
			LAY.setAccyear(rs.getString("acadamic_year"));
				
			  AllAccYrs.add(LAY);
				
			}
			
		} catch (Exception e) {
			logger.error(e);
		
		
	}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreateExaminationDaoImpl: getAccadamicYearsDaoImpl  Ending");
		
		return AllAccYrs;
}

	public List getAllExamNames(CreateExaminationForm examform) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreateExaminationDaoImpl:getAllExamNames Starting");
		List<String> examnamelist = new ArrayList<String>();

		PreparedStatement pstmt = null;
		ResultSet resultsetexamname = null;
		Connection conn = null;
		String accyear = examform.getAccyear();	
				
				try {
					
					conn = JDBCConnection.getSeparateConnection();
					pstmt = conn.prepareStatement(ExamSqlUtils.GET_ALL_EXAMNAMES);
					
					
					pstmt.setString(1, accyear);
					resultsetexamname = pstmt.executeQuery();
					
					while (resultsetexamname.next()){
						
						examnamelist.add(resultsetexamname.getString("examname"));
						
					}
					
				}catch (Exception exception) {
					logger.error(exception.getMessage(), exception);
					exception.getStackTrace();
					
				}
		
		
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.END_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in CreateExaminationDaoImpl:getAllExamNames  Ending");
				return examnamelist;
	}

	public int createExamination(LstmsExaminationPOJO exam) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreateExaminationDaoImpl:createExaminationDaoImpl  Starting");
		
		PreparedStatement pstmt = null;
		int result =0;
		int rs=0;
		Connection conn = null;
		
		
			try {
				System.out.println("Insert dao");
				
				String str=IDGenerator.getPrimaryKeyID("campus_examination");
				
				conn = JDBCConnection.getSeparateConnection();
				
				pstmt = conn.prepareStatement(ExamSqlUtils.insertExaminationQuery);
				
				pstmt.setString(1, str);
				pstmt.setString(2, exam.getExamName());
				pstmt.setString(3, exam.getDescription());
				pstmt.setString(4, exam.getAccadamicyear());
				pstmt.setString(5, exam.getExamdate());
				pstmt.setString(6, exam.getEnddate());
				pstmt.setString(7, exam.getCreateuser());
				
				System.out.println("Insert "+pstmt);
				
				rs = pstmt.executeUpdate();
				
				System.out.println(" pstmt.executeUpdate "+ pstmt.executeUpdate());
				
				
				
			} catch (SQLException sqlExp) {
				logger.error(sqlExp.getMessage(), sqlExp);
				sqlExp.getStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
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
		
		/*try {
			System.out.println("create dao");
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement(ExamSqlUtils.insertExaminationQuery);
			
			
			System.out.println("examid is "+exam.getExamId());
			System.out.println("examid is "+exam.getExamName());
			System.out.println("examid is "+exam.getDescription());
			System.out.println("examid is "+ exam.getAccadamicyear());
			System.out.println("examid is "+exam.getExamdate());
			System.out.println("examid is "+exam.getEnddate());
			System.out.println("examid is "+exam.getCreateuser());
			
			
			pstmt.setString(1, exam.getExamId());
			pstmt.setString(2, exam.getExamName());
			pstmt.setString(3, exam.getDescription());
			pstmt.setString(4, exam.getAccadamicyear());
			pstmt.setString(5, exam.getExamdate());
			pstmt.setString(6, exam.getEnddate());
			pstmt.setString(7, exam.getCreateuser());
			System.out.println("pstmt "+pstmt);
			rs = pstmt.executeUpdate();
			
			System.out.println(" pstmt.executeUpdate "+ pstmt.executeUpdate());
			
			
			
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
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
			}*/
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreateExaminationDaoImpl:createExaminationDaoImpl  Ending");
		
		return result;
	}


public ExaminationDetailsVo editExamination(ExaminationDetailsVo examvo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreateExaminationDaoImpl:edtiExaminationDaoImpl  Starting");
		
		System.out.println("edit dao");
		
	
		ExaminationDetailsVo getexam = null;
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			getexam = new ExaminationDetailsVo();
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ExamSqlUtils.GET_EXAMID_ID);
			pstmt.setString(1, examvo.getExamid());
			rs = pstmt.executeQuery();
		
			System.out.println("pstmt"+pstmt);
			
			
			
			while (rs.next()) {
			getexam.setExamid(rs.getString("examid"));
			getexam.setExamName(rs.getString("examname"));
			getexam.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
			getexam.setEndDate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
			getexam.setAccyearId(rs.getString("acadamicyear"));
			getexam.setDescription(rs.getString("description"));
			getexam.setAccyear(rs.getString("acadamic_year"));
		
			
			
			
			
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreateExaminationDaoImpl:editExaminationDaoImpl  Ending");
		
		return getexam;
	}
	
	
	
	
	
	
	public String deleteExamination(ExaminationDetailsVo examvo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreateExaminationDaoImpl:deleteExaminationDaoImpl  Starting");
		
		
		System.out.println("delete dao");
		
		PreparedStatement pstmt = null;
		String check = "";
		int streamcount = 0;
		String examid = examvo.getExamid();
		int count = 0;
		Connection conn = null;
		ResultSet rs = null;
		try {
			
			
			
			if (streamcount == 0) {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(ExamSqlUtils.DELETE_EXAM_DETAILS);
			
			pstmt.setString(1, examid);
			System.out.println("pstmt"+pstmt);
			count=pstmt.executeUpdate();
			
		
	
		if (count > 0) {

			check = MessageConstants.DELETE_SUCCESS_EXAM;
		} else {
			check = MessageConstants.DELETE_FAIL_EXAM;
		}
		}
		else
		{
			
			check = MessageConstants.DELETE_WARNING_EXAM;
			
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
				+ " Control in CreateExaminationDaoImpl:deleteExaminationDaoImpl  Ending");
		
		return null;
	}


	public ArrayList<ExaminationDetailsVo> searchExamination(String searchName) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : searchExamDetailsDao Starting");
		
		
		ArrayList<ExaminationDetailsVo>  examlist = new ArrayList<ExaminationDetailsVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ExamSqlUtils.SEARCH_Exam_DETAILS);
			
			
		
			
			pstmt.setString(1, "%" + searchName + "%");
			pstmt.setString(2, "%" + searchName + "%");
			pstmt.setString(3, "%" + searchName + "%");
			pstmt.setString(4, "%" + searchName + "%");
			pstmt.setString(5, "%" + searchName + "%");
			pstmt.setString(6, "%" + searchName + "%");
			
			System.out.println("pstmt "+pstmt);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				sno++;
				ExaminationDetailsVo examvo = new ExaminationDetailsVo();	
				
				examvo.setSno(String.valueOf(sno));
				examvo.setExamid(rs.getString("examid"));
				examvo.setExamName(rs.getString("examname"));
				examvo.setStartDate(rs.getString("startdate"));
				examvo.setEndDate(rs.getString("enddate"));
				examvo.setAccyear(rs.getString("acadamicyear"));
				examvo.setDescription(rs.getString("description"));
				
				examlist.add(examvo);
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally {
			try {

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : searchExamDetailsDao  Ending");
		
		
		
		return examlist;
	}


	public boolean validExamination(ExaminationDetailsVo examvo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in validateStreamDetailsDaoImpl : validateExamNameDetailsDao Starting");
		
		boolean examname_validate = false;
		
		
		int count = 0;
		PreparedStatement pscheckExamName = null;
		ResultSet rsCheckExamName = null;
		Connection conn = null;
		
		if (examvo.getExamid().equalsIgnoreCase("")) {
			
			try {
				conn = JDBCConnection.getSeparateConnection();
				pscheckExamName = conn
						.prepareStatement(ExamSqlUtils.VALIDATE_EXAM_NAME_UPDATE);
				
				pscheckExamName
				.setString(1, examvo.getExamName().trim());
				pscheckExamName
				.setString(2, examvo.getAccyear().trim());
				
				rsCheckExamName = pscheckExamName.executeQuery();
				while (rsCheckExamName.next()) {
					
					
					count = rsCheckExamName.getInt(1);
					
					
				}

				if (count > 0) {

					examname_validate = true;

				} else {

					examname_validate = false;
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
		}

		else if(!examvo.getExamid().equalsIgnoreCase("")){
			
			
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				pscheckExamName = conn
						.prepareStatement(ExamSqlUtils.VALIDATE_EXAM_NAME_EDIT);
				
				pscheckExamName.setString(1, examvo.getExamid().trim());
				pscheckExamName.setString(2, examvo.getExamName().trim());
				rsCheckExamName = pscheckExamName.executeQuery();
				while (rsCheckExamName.next()) {
					
					
					count = rsCheckExamName.getInt(1);
					
				}
				if (count > 0) {

					examname_validate = true;

				} else {

					examname_validate = false;
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}finally {
				try {

					if (pscheckExamName != null
							&& (!pscheckExamName.isClosed())) {
						pscheckExamName.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
			
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in validateDetailsDaoImpl : validateNameDetailsDao  Ending");
		return examname_validate;
	}

	public int UpdateExamination(LstmsExaminationPOJO exam) {
		
		System.out.println("Update DAO working");
		
		PreparedStatement pstmt = null;
		int result =0;
		int rs=0;
		Connection conn = null;
		
		System.out.println("getExamId"+exam.getExamId());
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn
						.prepareStatement(ExamSqlUtils.UPDATE_EXAM_DETAILS);

				
			
				pstmt.setString(1, exam.getExamName());
				pstmt.setString(2, exam.getDescription());
				pstmt.setString(3, exam.getAccadamicyear());
				pstmt.setString(4, exam.getExamdate());
				pstmt.setString(5, exam.getEnddate());
				pstmt.setString(6, exam.getCreateuser());
				pstmt.setString(7, null);
				pstmt.setString(8, null);
				pstmt.setString(9, exam.getExamId());
				
				System.out.println("Update"+pstmt);
				
				rs = pstmt.executeUpdate();
				
				
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
			return result;
			
			
			
		
	}



	
}
