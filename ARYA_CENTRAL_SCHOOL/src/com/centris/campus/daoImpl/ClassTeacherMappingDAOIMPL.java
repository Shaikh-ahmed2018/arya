package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.centris.campus.actions.AdminMenuAction;
import com.centris.campus.dao.ClassTeacherMappingDAO;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.ClassTeacherMappingVO;
import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.TermMasterVo;


public class ClassTeacherMappingDAOIMPL implements ClassTeacherMappingDAO {
	
	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);

	
	public ArrayList<ClassTeacherMappingVO> getclass(ClassTeacherMappingVO vo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getclass Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;

		ArrayList<ClassTeacherMappingVO> classList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_CLASS);
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setClassid(rs.getString("classdetail_id_int").trim());
				val.setClassname(rs.getString("classdetails_name_var").trim());
				classList.add(val);
			
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
			
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getclass Ending");

		return classList;
	}


	
	
	public ArrayList<ClassTeacherMappingVO> getsection(ClassTeacherMappingVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getsection Starting");

		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;
		
		ArrayList<ClassTeacherMappingVO> sectionList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_SECTION);
			pstmt.setString(1, vo.getClassid());
			pstmt.setString(2, vo.getClassid());
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setSectionid(rs.getString("classsection_id_int").trim());
				val.setSectionname(rs.getString("classsection_name_var").trim());
				sectionList.add(val);
			
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
			
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getsection Ending");

		return sectionList;
	}



	
	public ArrayList<ClassTeacherMappingVO> getteacher(ClassTeacherMappingVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getteacher Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;

		ArrayList<ClassTeacherMappingVO> teacherList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_TEACHER);
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setTeacherid(rs.getString("TeacherID").trim());
				val.setTeachername(rs.getString("FirstName").trim());
				teacherList.add(val);
			
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
			
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getteacher Ending");

		return teacherList;
	}




	public String addmappingdetails(ClassTeacherMappingVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : addmappingdetails Starting");
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;
		
		

	

		try {
			
				String str = IDGenerator.getPrimaryKeyID("campus_classteacher");
				conn = JDBCConnection.getSeparateConnection();

				pstmt = conn.prepareStatement(SQLUtilConstants.ADD_CLASS_TEACHER);
				pstmt.setString(1,str);
				pstmt.setString(2,vo.getClassid());
				pstmt.setString(3,vo.getSectionid());
				pstmt.setString(4,vo.getTeacherid());
				pstmt.setString(5,vo.getCreateuser());
				pstmt.setTimestamp(6,HelperClass.getCurrentTimestamp());
				result1 = pstmt.executeUpdate();

				if (result1 == 1) {
					result_Status = MessageConstants.SuccessMappingMsg;
				} else {
					result_Status = MessageConstants.ErrorMappingMsg;
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			} finally {
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
				+ " Control in ClassTeacherMappingDAOIMPL : addmappingdetails Ending");

		
		return result_Status;

	}



	
	public ArrayList<ClassTeacherMappingVO> teachermapping(ClassTeacherMappingVO vo) 
	
	{
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : teachermapping Starting");
		
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;
		ArrayList<ClassTeacherMappingVO> MappingList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_MAPPING_DETAILS);
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setClassteacherid(rs.getString("CTCode"));
				val.setClassid(rs.getString("ClassCode"));
				val.setSectionid(rs.getString("SectionCode"));
				val.setTeacherid(rs.getString("TeacherCode"));
				val.setClassname(rs.getString("classdetails_name_var"));
				val.setSectionname(rs.getString("classsection_name_var"));
				val.setTeachername(rs.getString("FirstName"));
				MappingList.add(val);
				
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : teachermapping Ending");

		return MappingList;
	
		
	}


	public LinkedHashMap<String, ArrayList<ClassTeacherMappingVO>> getclassdetails(
			ClassTeacherMappingVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getclassdetails Starting");
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;
		
		LinkedHashMap<String, ArrayList<ClassTeacherMappingVO>> allowanceMap=new LinkedHashMap<String, ArrayList<ClassTeacherMappingVO>>();
		
		ArrayList<ClassTeacherMappingVO> allowanceList=new ArrayList<ClassTeacherMappingVO>();
		
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_CLASS_DETAILS);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setClassid(rs.getString("ClassCode"));
				
				if(allowanceList.size()!=0){
					
					if(!(allowanceList.get(allowanceList.size()-1).getClassid().equalsIgnoreCase(rs.getString("ClassCode")))){
					
					allowanceMap.put(allowanceList.get(allowanceList.size()-1).getClassname(),allowanceList);
					
					count=0;
					allowanceList = new ArrayList<ClassTeacherMappingVO>();
				}
				
				}
				
				count++;
				val.setClassteacherid(rs.getString("CTCode"));
				val.setClassid(rs.getString("ClassCode"));
				val.setSectionid(rs.getString("SectionCode"));
				val.setTeacherid(rs.getString("TeacherCode"));
				val.setClassname(rs.getString("classdetails_name_var"));
				val.setSectionname(rs.getString("classsection_name_var"));
				val.setTeachername(rs.getString("FirstName"));
				allowanceList.add(val);
			
			
		}
			
			if(allowanceList.size()!=0)
			
			{
				
				allowanceMap.put(allowanceList.get(allowanceList.size()-1).getClassname(),allowanceList);
				
			}
			
			
		}
		
		catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
		}
		
			
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getclassdetails Ending");
		
		JSONArray array=new JSONArray();
		array.put(allowanceMap);
		return allowanceMap;
	}




	
	public ArrayList<ClassTeacherMappingVO> editclassdetails(
			ClassTeacherMappingVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : editclassdetails Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		ArrayList<ClassTeacherMappingVO> MappingList = new ArrayList<ClassTeacherMappingVO>();

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("");
			/*pstmt.setString(1,vo.getId());*/
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO remain = new ClassTeacherMappingVO();
				
				
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : editclassdetails Ending");
		return MappingList;
	}




	
	public ArrayList<ClassTeacherMappingVO> getclassupdate(
			ClassTeacherMappingVO vo) 
			
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getclassupdate Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;

		ArrayList<ClassTeacherMappingVO> classList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_CLASS_UPDATE);
			pstmt.setString(1, vo.getClassteacherid());
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setClassid(rs.getString("classdetail_id_int").trim());
				val.setClassname(rs.getString("classdetails_name_var").trim());
				classList.add(val);
			
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
			
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getclassupdate Ending");

		return classList;
	}




	
	public ArrayList<ClassTeacherMappingVO> getupclasslist(
			ClassTeacherMappingVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getclassupdate Starting");

		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;

		ArrayList<ClassTeacherMappingVO> classList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_CLASS_VALUES);
			pstmt1.setString(1, vo.getClassteacherid());
			rs1 = pstmt1.executeQuery();
			while (rs1.next())

			{
			
			String classid=rs1.getString("ClassCode");
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_CLASS_UPVALUES);
			pstmt.setString(1, classid);
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setClassid(rs.getString("classdetail_id_int").trim());
				val.setClassname(rs.getString("classdetails_name_var").trim());
				classList.add(val);
			
			}
			
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
			
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getclassupdate Ending");

		return classList;
	}




	
	public ArrayList<ClassTeacherMappingVO> getsectionupdate(
			ClassTeacherMappingVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getsectionupdate Starting");

		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;
		
		ArrayList<ClassTeacherMappingVO> sectionList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_SECTION_UPDATE);
			pstmt.setString(1, vo.getClassid());
			pstmt.setString(2, vo.getClassid());
			pstmt.setString(3, vo.getClassteacherid());
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setSectionid(rs.getString("classsection_id_int").trim());
				val.setSectionname(rs.getString("classsection_name_var").trim());
				sectionList.add(val);
			
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
			
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getsectionupdate Ending");

		return sectionList;
	}




	
	public ArrayList<ClassTeacherMappingVO> getupdateteacherlist(
			ClassTeacherMappingVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getupdateteacherlist Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;

		ArrayList<ClassTeacherMappingVO> teaList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_CLASS_TEACHER_UPDATE);
			pstmt.setString(1, vo.getClassteacherid());
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setTeacherid(rs.getString("TeacherID").trim());
				val.setTeachername(rs.getString("FirstName").trim());
				teaList.add(val);
			
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
			
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getupdateteacherlist Ending");

		return teaList;
	}




	
	public ArrayList<ClassTeacherMappingVO> getupteacherlist(
			ClassTeacherMappingVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getupteacherlist Starting");

		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;

		ArrayList<ClassTeacherMappingVO> teaList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_TEACHER_COUNT);
			pstmt1.setString(1, vo.getClassteacherid());
			rs1 = pstmt1.executeQuery();
			while (rs1.next())

			{
			
			String teaid=rs1.getString("TeacherCode");
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_TEACHER_UPDATE);
			pstmt.setString(1, teaid);
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setTeacherid(rs.getString("TeacherID").trim());
				val.setTeachername(rs.getString("FirstName").trim());
				teaList.add(val);
			
			}
			
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
			
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getupteacherlist Ending");

		return teaList;
	}




	
	public String updatemappingDetails(ClassTeacherMappingVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : updatemappingDetails Starting");
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;
		
		

	

		try {
			
				conn = JDBCConnection.getSeparateConnection();

				pstmt = conn.prepareStatement(SQLUtilConstants.UPDATE_MAPPING_DETAILS);
				pstmt.setString(1,vo.getClassid());
				pstmt.setString(2,vo.getSectionid());
				pstmt.setString(3,vo.getTeacherid());
				pstmt.setString(4,vo.getClassteacherid());
				result1 = pstmt.executeUpdate();

				if (result1 == 1) {
					result_Status = MessageConstants.UpdateMappingMsg;
				} else {
					result_Status = MessageConstants.ErrorUpMappingMsg;
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			} finally {
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
				+ " Control in ClassTeacherMappingDAOIMPL : updatemappingDetails Ending");

		
		return result_Status;

	}




	
	public String deletemappingDetails(ClassTeacherMappingVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : deletemappingDetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String status="";
		int no=0;

		Connection conn = null;

		try
		
		{
		
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.DELETE_TEACHER_MAPPING);
			pstmt.setString(1, vo.getClassteacherid());
			no = pstmt.executeUpdate();
			if(no>0)
			{
				
				status=MessageConstants.DEL_MAP_SUCCESS;
				
			}
			
			else
			{
				status=MessageConstants.DEL_MAP_ERROR;
			}
		
		}
		
		catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : deletemappingDetails Ending");
		
		return status;
	}




	
	public ArrayList<ClassTeacherMappingVO> getDownloadDetails(
			ClassTeacherMappingVO vo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getDownloadDetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;

		ArrayList<ClassTeacherMappingVO> getDownloadList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_DOWNLOAD_EXCEL);
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setClassteacherid(rs.getString("CTCode"));
				val.setClassid(rs.getString("ClassCode"));
				val.setSectionid(rs.getString("SectionCode"));
				val.setTeacherid(rs.getString("TeacherCode"));
				val.setClassname(rs.getString("classdetails_name_var"));
				val.setSectionname(rs.getString("classsection_name_var"));
				val.setTeachername(rs.getString("FirstName"));
				getDownloadList.add(val);
			
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
			
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getDownloadDetails Ending");

		return getDownloadList;
	}

}
