package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.ClassTeacherLsitDao;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.util.RoleMasterSqlConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.StreamSqlUtils;
import com.centris.campus.util.SubjectSqlUtils;
import com.centris.campus.util.TimeTableSqlConstants;
import com.centris.campus.vo.ClassTeacherVo;
import com.centris.campus.vo.StreamDetailsVO;

public class ClassTeacherLsitDaoImpl implements ClassTeacherLsitDao{


	private static final Logger logger = Logger.getLogger(ClassTeacherLsitDaoImpl.class);
	
	
	public ArrayList<ClassTeacherVo> getClassTeacherListDao() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : getClassTeacherListDao Starting");
		
		ArrayList<ClassTeacherVo> tealist = new ArrayList<ClassTeacherVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(ReportsMenuSqlConstants.CLASS_TEACHER_MAPPED_LIST);
			
			
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				
				ClassTeacherVo vo = new ClassTeacherVo();
				
				vo.setClassName(rs.getString("classdetails_name_var"));
				vo.setSectionName(rs.getString("classsection_name_var"));
				vo.setTeacherName(rs.getString("teachername"));
				vo.setClassId(rs.getString("classdetail_id_int"));
				vo.setSectionId(rs.getString("classsection_id_int"));
				vo.setTeacherId(rs.getString("teacherid"));
				vo.setLocationId(rs.getString("locationId"));
				
				tealist.add(vo);
				
			}
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : getClassTeacherListDao Ending");
		
		return tealist;
	}



	public ClassTeacherVo editClassTeacherDao(ClassTeacherVo vo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : getClassTeacherListDao Starting");
		
		
		ClassTeacherVo classteacher = null;
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			
		
			if(vo.getTeacherId().contains("TEA")){
				
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASS_SECTION_TEACHER);
				
				
				pstmt.setString(1, vo.getClassId());
				pstmt.setString(2, vo.getSectionId());
				pstmt.setString(3, vo.getTeacherId());
				
			
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					
					classteacher = new ClassTeacherVo();
					
					classteacher.setClassId(rs.getString("classdetail_id_int"));
					classteacher.setClassName(rs.getString("classdetails_name_var"));
					classteacher.setSectionId(rs.getString("classsection_id_int"));
					classteacher.setSectionName(rs.getString("classsection_name_var"));
					classteacher.setTeacherId(rs.getString("TeacherID"));
					classteacher.setTeacherName(rs.getString("teachername"));
					
				
					
				}
				
			}
			
			else{
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASS_SECTION);
				pstmt.setString(1, vo.getClassId());
				pstmt.setString(2, vo.getSectionId());
				
			
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					
					classteacher = new ClassTeacherVo();
					
					classteacher.setClassId(rs.getString("classdetail_id_int"));
					classteacher.setClassName(rs.getString("classdetails_name_var"));
					classteacher.setSectionId(rs.getString("classsection_id_int"));
					classteacher.setSectionName(rs.getString("classsection_name_var"));
					classteacher.setTeacherId("-");
					classteacher.setTeacherName("-");
					
				
					
					
				}
				
			}
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : getClassTeacherListDao Ending");
		
		return classteacher;
	}



	
	public String saveClassTeacherDao(ClassTeacherVo vo) {
		
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : saveClassTeacherDao Starting");
		
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		
		
		
		
		if(vo.getTeacherId1().contains("-")){
			
			
			try {
				conn = JDBCConnection.getSeparateConnection();
			
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.SAVE_CLASS_TEACHER);
				
				
				pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_classteacher"));
				pstmt.setString(2, vo.getClassId());
				pstmt.setString(3, vo.getSectionId());
				pstmt.setString(4, vo.getTeacherId());
				pstmt.setTimestamp(5, time_stamp);
				pstmt.setString(6, vo.getCreateuser());
				pstmt.setString(7, vo.getLocationId());
				
				
				
				
				result1 = pstmt.executeUpdate();
				
				
				if (result1 == 1) {
					result_Status = MessageConstants.ClassTeacherSuccessMsg;
				} else {
					result_Status = MessageConstants.ClassTeacherErrorMsg;
				}
				
				
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
			
			
		}
		
		
		else{
			
			
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.UPDATE_CLASS_TEACHER);
				
			
				pstmt.setString(1, vo.getTeacherId());
				pstmt.setString(2, vo.getClassId());
				pstmt.setString(3, vo.getSectionId());
				
				
				
				
				result1 = pstmt.executeUpdate();
				
				
				
				if (result1 == 1) {
					
					result_Status = MessageConstants.ClassTeacherSuccessMsg;
					
				} else {
					
					result_Status = MessageConstants.ClassTeacherErrorMsg;
					
				}
				
				
				
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : saveClassTeacherDao Ending");
		
		return result_Status;
	}



	
	public boolean validateClassTeacherDao(ClassTeacherVo vo) {
		
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : validateClassTeacherDao Starting");
		
		boolean classteacher_validate = false;

		int i = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.VALIDATE_CLASS_TEACHER);
			
			pstmt.setString(1, vo.getClassId());
			pstmt.setString(2, vo.getSectionId());
			pstmt.setString(3, vo.getTeacherId());
			
			
			 rs = pstmt.executeQuery();
			
			while (rs.next()) {
				i = rs.getInt(1);
			}
			
			if (i > 0) {
				classteacher_validate = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : validateClassTeacherDao Ending");
		
		return classteacher_validate;
	}



	
	public ArrayList<ClassTeacherVo> getSearchClassTeacherListDao(
			String searchTerm) {
	
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : getSearchClassTeacherListDao Starting");
		
		
		
		ArrayList<ClassTeacherVo> tealist = new ArrayList<ClassTeacherVo>();
		int count=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.SEARCH_CLASS_TEACHER);
			pstmt.setString(1, "%"+searchTerm+"%");
			pstmt.setString(2, "%"+searchTerm+"%");
			pstmt.setString(3, "%"+searchTerm+"%");
			pstmt.setString(4, "%"+searchTerm+"%");
			
			
			
			
			
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()){
				ClassTeacherVo voobj = new ClassTeacherVo();
				
				voobj.setClassName(rs.getString("classdetails_name_var"));
				voobj.setSectionName(rs.getString("classsection_name_var"));
				voobj.setTeacherName(rs.getString("teachername"));
				voobj.setClassId(rs.getString("classdetail_id_int"));
				voobj.setSectionId(rs.getString("classsection_id_int"));
				voobj.setTeacherId(rs.getString("teacherid"));
				
				
				
				
				tealist.add(voobj);
				
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : getSearchClassTeacherListDao Ending");
		
		
		
		
		
		
		
		
		
		return tealist;
	}

}
