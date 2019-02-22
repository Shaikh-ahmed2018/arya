package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StudentTransferCertifivateReportDao;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.vo.ParentVO;

public class StudentTransferCertifivateReportDaoImpl implements StudentTransferCertifivateReportDao {
	
	
	private static final Logger logger = Logger.getLogger(StudentTransferCertifivateReportDaoImpl.class);

	
	public List<ClassPojo> getClassDetailsDao() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertifivateReportDaoImpl : getClassDetailsDao Starting");
		
		
		List<ClassPojo> claspojo = new ArrayList<ClassPojo>();

		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASS_DETAILS);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ClassPojo getclas = new ClassPojo();
				getclas.setClassId(rs.getString("classdetail_id_int"));
				getclas.setClassName(rs.getString("classdetails_name_var"));
				claspojo.add(getclas);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertifivateReportDaoImpl : getClassDetailsDao Ending");
		
		return claspojo;
	}



	public List<SectionPojo> getSectionListDao(String classname,String location) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertifivateReportDaoImpl : getSectionListDao Starting");
		
		List<SectionPojo> seclist = new ArrayList<SectionPojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(ReportsMenuSqlConstants.GET_SECTION_DETAILS);
			pstmt.setString(1, classname);
			pstmt.setString(2, location);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				SectionPojo pojo = new SectionPojo();
				
				pojo.setSectionId(rs.getString("classsection_id_int"));
				pojo.setSectionName(rs.getString("classsection_name_var"));
				
				
				seclist.add(pojo);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertifivateReportDaoImpl : getSectionListDao Ending");
		
		return seclist;
	}



	
	public List<ParentVO> getAllStudentNamesReportDao(String sectionid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertifivateReportDaoImpl : getAllStudentNamesReportDao Starting");
		
		List<ParentVO> seclist = new ArrayList<ParentVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn
					.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_DETAILS);
			
			pstmt.setString(1, sectionid);
			
			
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				ParentVO pojo = new ParentVO();
				
				pojo.setStudentid(rs.getString("student_id_int"));
				pojo.setStudentFnameVar(rs.getString("studentname"));
				
				
				seclist.add(pojo);
			}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertifivateReportDaoImpl : getAllStudentNamesReportDao Ending");
		
		return seclist;
	}

}
