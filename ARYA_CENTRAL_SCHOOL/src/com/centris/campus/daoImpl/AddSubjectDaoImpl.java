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

import com.centris.campus.actions.AddLabForm;
import com.centris.campus.dao.AddtSubjectDao;
import com.centris.campus.forms.AddSubjectForm;
import com.centris.campus.forms.SectionForm;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.SubjectSqlUtils;
import com.centris.campus.vo.ViewallSubjectsVo;

public class AddSubjectDaoImpl implements AddtSubjectDao {
	private static final Logger logger = Logger
			.getLogger(AddSubjectDaoImpl.class);

	@Override
	public synchronized ArrayList<ViewallSubjectsVo> subjectdetails(String schoolLocation) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : subjectdetails Starting");
		ArrayList<ViewallSubjectsVo> allsubject = new ArrayList<ViewallSubjectsVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SubjectSqlUtils.VIEW_SUBJECT_DETAILS);
			if(!schoolLocation.equalsIgnoreCase("all"))
			pstmt.setString(1, schoolLocation);
			else
			pstmt.setString(1, "%%");
			resultSet = pstmt.executeQuery();
			System.out.println("pstmt ======"+pstmt);

			while (resultSet.next()) {
				sno++;
				ViewallSubjectsVo obj = new ViewallSubjectsVo();
				
				String specCode=resultSet.getString("specialization");
				obj.setSubjectid(resultSet.getString("subjectID"));
				obj.setSubjectname(resultSet.getString("subjectName").trim());
				obj.setPath(resultSet.getString("syllabous"));
				obj.setDescription(resultSet.getString("decription"));
				obj.setClassname(resultSet.getString("classdetails_name_var"));
				obj.setSno(sno);
				obj.setTotalMarks(resultSet.getInt("totalMarks"));
				obj.setPassMarks(resultSet.getInt("passMarks"));
				obj.setLocationName(resultSet.getString("Location_Name"));
				obj.setLocationId(resultSet.getString("locationId"));
				if(!specCode.equalsIgnoreCase("-")){
				PreparedStatement pssp=conn.prepareStatement("SELECT Specialization_Name from campus_class_specialization where Specialization_Id=? ");
				pssp.setString(1, specCode);
				ResultSet rssp=pssp.executeQuery();
				while(rssp.next()){
					obj.setSpecializationName(rssp.getString("Specialization_name"));
				}
				
				}
				else{
					obj.setSpecializationName("General");
				}
			
				allsubject.add(obj);
			}
			System.out.println(allsubject.size());

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
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
				+ " Control in AddSubjectDaoImpl: subjectdetails Ending");
		return allsubject;

	}

	public synchronized boolean DeleteSubject(String[] voObj,String[] locationList) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : DeleteSubject Starting");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int sno = 0;
		boolean status = false;
		int count = 0;

		try {
			System.out.println("inside daoimpl:" +voObj.length);
			conn = JDBCConnection.getSeparateConnection();
			for(int i=0;i<voObj.length;i++)
			{
			pstmt = conn
					.prepareStatement(SubjectSqlUtils.DELETE_SUBJECT_DETAILS);
			pstmt.setString(1, voObj[i]);
			pstmt.setString(2, locationList[i]);
			System.out.println("pstmt" +pstmt);
			count = pstmt.executeUpdate();
			if (count > 0) {
				status = true;
			} else {

				status = false;
			}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
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
				+ " Control in AddSubjectDaoImpl: DeleteSubject Ending");
		return status;

	}

	public synchronized List<ViewallSubjectsVo> searchsubjectdetails(
			ViewallSubjectsVo voObj) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : searchsubjectdetails Starting");
		ArrayList<ViewallSubjectsVo> allsubject = new ArrayList<ViewallSubjectsVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SubjectSqlUtils.SEARCH_SUBJECT_DETAILS);
			
			pstmt.setString(1, "%" + voObj.getSearchName().trim() + "%");
			pstmt.setString(2, "%" + voObj.getSearchName().trim() + "%");
			pstmt.setString(3, "%" + voObj.getSearchName().trim() + "%");
			pstmt.setString(4, "%" + voObj.getSearchName().trim() + "%");
			pstmt.setString(5, "%" + voObj.getSearchName().trim() + "%");
			pstmt.setString(6, "%" + voObj.getSearchName().trim() + "%");
			pstmt.setString(7, "%" + voObj.getSearchName().trim() + "%");
			if(!voObj.getLocationId().equalsIgnoreCase("all"))
			pstmt.setString(8, voObj.getLocationId());
			else
			pstmt.setString(8, "%%");
			System.out.println(pstmt);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				sno++;
				ViewallSubjectsVo obj = new ViewallSubjectsVo();
				obj.setSubjectid(resultSet.getString("subjectID"));
				obj.setSubjectname(resultSet.getString("subjectName").trim());
				obj.setPath(resultSet.getString("syllabous"));
				obj.setDescription(resultSet.getString("decription"));
				/* obj.setStatus(resultSet.getString("status")); */
				obj.setClassname(resultSet.getString("classdetails_name_var"));
				obj.setLocationName(resultSet.getString("Location_Name"));
				obj.setSpecializationName(resultSet.getString("Specialization_name"));
				obj.setPassMarks(resultSet.getInt("passMarks"));
				obj.setTotalMarks(resultSet.getInt("totalMarks"));
				obj.setSno(sno);

				allsubject.add(obj);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
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
				+ " Control in AddSubjectDaoImpl: searchsubjectdetails Ending");
		return allsubject;
	}

	public synchronized boolean addSubject(AddSubjectForm obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : addSubject Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		boolean status = false;
		try {

			boolean flagSubExist = validateSubjectWithClass(obj);
			if (!flagSubExist) {
				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement("insert into campus_subject(subjectID,subjectName,syllabous,classid,decription,createdby,totalMarks,passMarks,locationId,specialization,Sub_Code,isLanguage,isExam) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

				pstmt.setString(1,	IDGenerator.getPrimaryKeyID("campus_subject"));
				pstmt.setString(2, obj.getSubjtname().trim());
				pstmt.setString(3, obj.getFilename());
				pstmt.setString(4, obj.getClassname().trim());
				pstmt.setString(5, obj.getDescription().trim());
				pstmt.setString(6, obj.getCreatedUserId().trim());
				pstmt.setInt(7, obj.getTotalMarks());
				pstmt.setInt(8, obj.getPassMarks());
				pstmt.setString(9, obj.getLocationId());
				pstmt.setString(10, obj.getSpecialization());
				pstmt.setString(11, obj.getSubjectCode());
				pstmt.setString(12, obj.getIsLang());
				pstmt.setString(13, obj.getIsExam());
				
				result = pstmt.executeUpdate();
				
				

				if (result > 0) {
					status = true;
				} else {
					status = false;
				}

			}

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
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : addSubject Ending");

		return status;
	}

	public synchronized ViewallSubjectsVo getSubjectDetails(ViewallSubjectsVo obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : getSubjectDetails Starting");
		ViewallSubjectsVo allsubject = new ViewallSubjectsVo();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SubjectSqlUtils.GET_SINGLE_SUBJECT_DETAILS);
			pstmt.setString(1, obj.getSubjectid());

			resultSet = pstmt.executeQuery();
             
			while (resultSet.next()) {

				allsubject.setSubjectid(resultSet.getString("subjectID"));
				allsubject.setSubjectname(resultSet.getString("subjectName").trim());
				allsubject.setPath(resultSet.getString("syllabous"));
				allsubject.setDescription(resultSet.getString("decription"));
				/* obj.setStatus(resultSet.getString("status")); */
				allsubject.setClassname(resultSet.getString("classid"));
				allsubject.setStatus("update");
				allsubject.setTotalMarks(resultSet.getInt("totalMarks"));
				allsubject.setPassMarks(resultSet.getInt("passMarks"));
				allsubject.setLocationId(resultSet.getString("locationId"));
				allsubject.setSubjectCode(resultSet.getString("Sub_Code"));
				allsubject.setSpecializationId(resultSet.getString("specialization"));
				allsubject.setIsLanguage(resultSet.getString("isLanguage"));
				allsubject.setIsExam(resultSet.getString("isExam"));
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
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
				+ " Control in AddSubjectDaoImpl: getSubjectDetails Ending");
		return allsubject;
	}

	public synchronized boolean updateSubjectDetails(AddSubjectForm obj) {
         System.out.println("it is coming inside fo update or not");
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: updateSubjectDetails Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("update campus_subject set subjectName=? ,syllabous=?,classid=?, decription=?, updateby=? ,updatedtime=?, totalMarks=?, passMarks=?,locationId=?,specialization=?,Sub_Code=?,isLanguage=?,isExam=? where subjectID=?");
			pstmt.setString(1, obj.getSubjtname().trim());
			pstmt.setString(2, obj.getFilename().trim());
			pstmt.setString(3, obj.getClassname().trim());
			pstmt.setString(4, obj.getDescription());
			pstmt.setString(5, obj.getCreatedUserId().trim());
			pstmt.setTimestamp(6, HelperClass.getCurrentTimestamp());
			pstmt.setInt(7, obj.getTotalMarks());
			pstmt.setInt(8, obj.getPassMarks());
			pstmt.setString(9, obj.getLocationId());
			pstmt.setString(10, obj.getSpecialization());
			pstmt.setString(11, obj.getSubjectCode());
			pstmt.setString(12, obj.getIsLang());
			pstmt.setString(13, obj.getIsExam());
			pstmt.setString(14, obj.getHiddensubjectId().trim());
			
			
			
			
			int res = pstmt.executeUpdate();
			if (res > 0) {
				result = true;
			} else {
				result = false;
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: updateSubjectDetails Ending");
		return result;
	}

	public synchronized boolean validateSubjectWithClass(
			AddSubjectForm addSubjectPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: validateSubjectWithClass Starting");
		Connection conn = null;
		boolean flagSubExist = false;
		PreparedStatement pstmt = null;
		try {
			int i = 0;
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SubjectSqlUtils.VALIDATE_CLASS_SUBJECT);
			pstmt.setString(1, addSubjectPojo.getSubjtname());
			pstmt.setString(2, addSubjectPojo.getClassname());
			System.out.println(pstmt);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				i = rs.getInt(1);
			}
			if (i > 0) {
				flagSubExist = true;
			}
			else{
				flagSubExist = false;
			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: validateSubjectWithClass Ending");

		return flagSubExist;
	}

	public synchronized String getpath(String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: getpath Starting");

		String path = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet resultSet = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SubjectSqlUtils.ViewAllSubjectsPath);
			pstmt.setString(1, classname);

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				path = resultSet.getString("syllabous");
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: getpath Ending");
		return path;
	}

	public String DdownloadsyllabuspathDao(String subjectid) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: DdownloadsyllabuspathDao Starting");

		
		String path = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SubjectSqlUtils.GET_SYLLABUS_PATH);
			pstmt.setString(1, subjectid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				
				path = rs.getString("syllabous");
				
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: DdownloadsyllabuspathDao Ending");
		
		return path;
	}

	@Override
	public String validateSubName(AddSubjectForm form1) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationMasterDAOImpl : validateLocName : Starting");

		PreparedStatement sub_pstmt = null;
		ResultSet sub_rs = null;

		String subname_available =null;
		int count = 0;

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			sub_pstmt = conn
					.prepareStatement(SQLUtilConstants.VALIDATE_SUB_NAME);
			

			sub_pstmt.setString(1, form1.getClassname());
			sub_pstmt.setString(2, form1.getSubjtname());
			sub_pstmt.setString(3, form1.getLocationId());
			sub_pstmt.setString(4, form1.getSpecialization());
			System.out.println("subject name:" +sub_pstmt);
			sub_rs = sub_pstmt.executeQuery();

			while (sub_rs.next()) {

				count = sub_rs.getInt("subject");

			}

			if (count > 0) {

				subname_available ="true";

			} else {

				subname_available ="false";
			}

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			logger.error(sqle);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (sub_rs != null && (!sub_rs.isClosed())) {

					sub_rs.close();
				}
				if (sub_pstmt != null && (!sub_pstmt.isClosed())) {

					sub_pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl : validateDeptName : Ending");

		return subname_available;
	}

	public List<ViewallSubjectsVo> getLangauageOnClassBased(AddSubjectForm form1) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationMasterDAOImpl : getLangauageOnClassBased : Starting");
		 List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();
		
		 
		 PreparedStatement sub_pstmt = null;
		ResultSet sub_rs = null;

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			sub_pstmt = conn.prepareStatement(SQLUtilConstants.getLangauageOnClassBased);
			

			sub_pstmt.setString(1, form1.getClassname());
			sub_pstmt.setString(2, form1.getLocationId());
			sub_pstmt.setString(3, form1.getSpecialization());
			System.out.println("subject name:" +sub_pstmt);
			sub_rs = sub_pstmt.executeQuery();

			while (sub_rs.next()) {
				ViewallSubjectsVo list=new ViewallSubjectsVo();
				list.setSubjectname(sub_rs.getString("subjectName"));
				list.setSubjectCode(sub_rs.getString("subjectID"));
				subjectlist.add(list);
			}

			

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			logger.error(sqle);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (sub_rs != null && (!sub_rs.isClosed())) {

					sub_rs.close();
				}
				if (sub_pstmt != null && (!sub_pstmt.isClosed())) {

					sub_pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl : getLangauageOnClassBased : Ending");

		return subjectlist;
	}

	public List<ViewallSubjectsVo> subjectdetailsOnchangeListingPage(String locationid, String classname, String specialization) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Starting");

		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Connection conn = null;
		int sno=0;
		ArrayList<ViewallSubjectsVo> allsubject = new ArrayList<ViewallSubjectsVo>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement("SELECT cl.Location_Name,ccd.classdetails_name_var,ccd.classdetail_id_int,ccs.* FROM campus_subject  ccs LEFT JOIN campus_classdetail ccd ON ccs.classid=ccd.classdetail_id_int AND ccd.locationId=ccs.locationId LEFT JOIN campus_classstream cstr  ON ccd.classstream_id_int=cstr.classstream_id_int  LEFT JOIN campus_location cl ON ccs.locationId=cl.Location_Id WHERE ccs.locationId LIKE ?  AND ccd.classdetail_id_int LIKE ? AND specialization like ?  ORDER BY CAST(SUBSTR(ccs.locationId,4) AS UNSIGNED),CAST(SUBSTR(ccd.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(ccs.specialization,4) AS UNSIGNED)");

			pst.setString(1, locationid);
			pst.setString(2, classname);
			pst.setString(3, specialization);
		
			
			System.out.println(pst);
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				sno++;
				ViewallSubjectsVo obj = new ViewallSubjectsVo();
				
				String specCode=resultSet.getString("specialization");
				obj.setSubjectid(resultSet.getString("subjectID"));
				obj.setSubjectname(resultSet.getString("subjectName").trim());
				obj.setPath(resultSet.getString("syllabous"));
				obj.setDescription(resultSet.getString("decription"));
				obj.setClassname(resultSet.getString("classdetails_name_var"));
				obj.setSno(sno);
				obj.setTotalMarks(resultSet.getInt("totalMarks"));
				obj.setPassMarks(resultSet.getInt("passMarks"));
				obj.setLocationName(resultSet.getString("Location_Name"));
				obj.setLocationId(resultSet.getString("locationId"));
				if(!specCode.equalsIgnoreCase("-")){
				PreparedStatement pssp=conn.prepareStatement("SELECT Specialization_Name from campus_class_specialization where Specialization_Id=? ");
				pssp.setString(1, specCode);
				ResultSet rssp=pssp.executeQuery();
				while(rssp.next()){
					obj.setSpecializationName(rssp.getString("Specialization_name"));
				}
				
				}
				else{
					obj.setSpecializationName("General");
				}
			
				allsubject.add(obj);
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Ending");

		return allsubject;


	}

	@Override
	public ArrayList<ViewallSubjectsVo> labdetails(String schoolLocation) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : labdetails Starting");
		ArrayList<ViewallSubjectsVo> all_lab = new ArrayList<ViewallSubjectsVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SubjectSqlUtils.VIEW_LAB_DETAILS);
			
			resultSet = pstmt.executeQuery();
			System.out.println("pstmt ======>><<<<<<<<<<<<<<>>>>>>>>>>"+pstmt);

			while (resultSet.next()) {
				sno++;
				ViewallSubjectsVo obj = new ViewallSubjectsVo(); 
				
				String specCode=resultSet.getString("Specialization_name");
			
				obj.setSubjectname(resultSet.getString("Lab_Name"));
				obj.setPath(resultSet.getString("Syllabus"));
				obj.setDescription(resultSet.getString("Description"));
				obj.setClassname(resultSet.getString("classdetails_name_var"));
				obj.setSno(sno);
				obj.setTotalMarks(resultSet.getInt("Total_Marks"));
				obj.setPassMarks(resultSet.getInt("Pass_Marks"));
				obj.setLocationName(resultSet.getString("Location_Name"));
			
				obj.setLabCode(resultSet.getString("Lab_Code"));
				System.out.println("specCode value is>>>>"+specCode);
				if(!specCode.equalsIgnoreCase("-")){
				PreparedStatement pssp=conn.prepareStatement("SELECT Specialization_Name from campus_class_specialization where Specialization_Id=? ");
				pssp.setString(1, specCode);
				ResultSet rssp=pssp.executeQuery();
				while(rssp.next()){
					obj.setSpecializationName(rssp.getString("Specialization_name"));
				}
				
				}
				else{
					obj.setSpecializationName("General");
				}
			
				all_lab.add(obj);
			}
			System.out.println(all_lab.size());

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
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
				+ " Control in AddSubjectDaoImpl: subjectdetails Ending");
		return all_lab;

	}
	public synchronized boolean validateLabWithClass(
			AddSubjectForm addSubjectPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: validateSubjectWithClass Starting");
		Connection conn = null;
		boolean flagLabExist = false;
		PreparedStatement pstmt = null;
		try {
			int i = 0;
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SubjectSqlUtils.VALIDATE_CLASS_LAB);
			pstmt.setString(1, addSubjectPojo.getSubjtname());
			pstmt.setString(2, addSubjectPojo.getClassname());
			System.out.println(pstmt);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				i = rs.getInt(1);
			}
			if (i > 0) {
				flagLabExist = true;
			}
			else{
				flagLabExist = false;
			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: validateSubjectWithClass Ending");

		return flagLabExist;
	}


	@Override
	public synchronized boolean addLab(AddSubjectForm obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : addSubject Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		boolean status = false;
		try {
					System.out.println("..............");
		/*	boolean flagLabExist = validateLabWithClass(obj);*/
		/*	if (!flagLabExist) {*/
				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(SubjectSqlUtils.INSERT_LAB_FORM);
		
				pstmt.setString(1,IDGenerator.getPrimaryKeyID("laboratory_details"));
				System.out.println("location name....."+obj.getLocationName());
				pstmt.setString(2, obj.getLocationName());
				System.out.println("class name....."+obj.getClassname());
				pstmt.setString(3, obj.getClassname());
				pstmt.setString(4, obj.getSubjtname());
				pstmt.setString(5, obj.getLab_name());
				pstmt.setInt(6, obj.getTotalMarks());
				System.out.println("getTotalMarks ....."+obj.getTotalMarks());
				pstmt.setString(7, obj.getSpecialization());
				pstmt.setInt(8, obj.getPassMarks());
				pstmt.setString(9, obj.getDescription());
				pstmt.setString(10, obj.getSubjectCode());
				pstmt.setString(11, obj.getFilename());
				System.out.println("laguage is:"+pstmt);
				
				result = pstmt.executeUpdate();
				System.out.println(pstmt);
				
				

				if (result > 0) {
					status = true;
				} else {
					status = false;
				}

			/*}*/

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
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : addSubject Ending");

		return status;
	}

	@Override
	public boolean DeleteLab(String[] idList, String[] locationList) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : DeleteSubject Starting");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int sno = 0;
		boolean status = false;
		int count = 0;

		try {
			System.out.println("inside daoimpl:" +idList.length);
			conn = JDBCConnection.getSeparateConnection();
			for(int i=0;i<idList.length;i++)
			{
			pstmt = conn
					.prepareStatement(SubjectSqlUtils.DELETE_LAB_DETAILS);
			pstmt.setString(1, idList[i]);
			pstmt.setString(2, locationList[i]);
			System.out.println("delete lab query...." +pstmt);
			count = pstmt.executeUpdate();
			System.out.println("count affected"+count);
			if (count > 0) {
				status = true;
			} else {

				status = false;
			}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
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
				+ " Control in AddSubjectDaoImpl: DeleteSubject Ending");
		return status;

	}

	@Override
	public AddSubjectForm getLabDetails(AddSubjectForm obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : getlabDetails Starting");
		AddSubjectForm all_lab = new AddSubjectForm();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
	

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SubjectSqlUtils.GET_SINGLE_LAB_DETAILS);
			pstmt.setString(1, obj.getLab_id());
			//lab_id,School_Name,Class_Name,Lab_Name,Total_Marks,Specialization,Pass_Marks,Description,Lab_Code,Syllabus
			resultSet = pstmt.executeQuery();
           System.out.println("get single lab details query"+pstmt);
           System.out.println("result set "+resultSet);
			while (resultSet.next()) {

				String specCode=resultSet.getString("Specialization");
				all_lab.setLab_id(resultSet.getString("lab_id"));
				all_lab.setLocationName(resultSet.getString("School_Name"));
				
				all_lab.setClassname(resultSet.getString("Class_Name"));
				all_lab.setSubjtname(resultSet.getString("Subject"));
				all_lab.setLab_name(resultSet.getString("Lab_Name"));
				all_lab.setSubjectCode(resultSet.getString("Lab_Code"));
				all_lab.setTotalMarks(resultSet.getInt("Total_Marks"));
				
				all_lab.setPassMarks(resultSet.getInt("Pass_Marks"));
				all_lab.setDescription(resultSet.getString("Description"));
				all_lab.setSyllabus(resultSet.getString("Syllabus"));
				System.out.println("Specialization>>>"+resultSet.getString("Specialization"));
				
				if(!specCode.equalsIgnoreCase("-")){
					PreparedStatement pssp=conn.prepareStatement("SELECT Specialization_Name,Specialization_Id from campus_class_specialization where Specialization_Id=? ");
					pssp.setString(1, specCode);
					ResultSet rssp=pssp.executeQuery();
					System.out.println("specialization NAME"+pssp);
					while(rssp.next()){
						all_lab.setSpecialization(rssp.getString("Specialization_name"));
						all_lab.setSpecId(rssp.getString("Specialization_Id"));
					}

				}
				else{
					all_lab.setSpecialization("General");

				}
			System.out.println("get specialization>>>"+all_lab.getSpecialization());
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
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
				+ " Control in AddSubjectDaoImpl: getLabDetails Ending");
		return all_lab;
	}

	@Override
	public boolean updateLabDetails(AddSubjectForm addSubjectForm) {
        System.out.println("it is coming inside fo update or not");
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: updateSubjectDetails Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
	//Lab_Name=? ,Syllabus=?,Class_Name=?, Description=? , Total_Marks=?, Pass_Marks=?,School_Name=?,Specialization=?,Lab_Code=?
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SubjectSqlUtils.UPDATE_LAB);
           
			pstmt.setString(1, addSubjectForm.getSubjtname());
			pstmt.setString(2, addSubjectForm.getLab_name());
			pstmt.setString(3, addSubjectForm.getFilename());
			
			pstmt.setString(4, addSubjectForm.getClassname().trim());
			pstmt.setString(5, addSubjectForm.getDescription());
		    pstmt.setInt(6, addSubjectForm.getTotalMarks());
			pstmt.setInt(7, addSubjectForm.getPassMarks());
			pstmt.setString(8, addSubjectForm.getLocationName());
			pstmt.setString(9, addSubjectForm.getSpecialization());
			pstmt.setString(10, addSubjectForm.getSubjectCode());
			pstmt.setString(11, addSubjectForm.getHiddensubjectId());
			
			System.out.println("addSubjectForm.getSpecialization()"+addSubjectForm.getSpecialization());
			System.out.println("update lab query" +pstmt);
			
			
			int res = pstmt.executeUpdate();
			if (res > 0) {
				result = true;
			} else {
				result = false;
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: updateSubjectDetails Ending");
		return result;
	}

	public List<AddSubjectForm> labdetailsOnchangeListingPage(
			String locationid, String classname, String specialization) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Starting");

		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Connection conn = null;
		int sno=0;
		ArrayList<AddSubjectForm> all_lab = new ArrayList<AddSubjectForm>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement("SELECT  ccs.Lab_Name,cl.Location_Name,ccd.classdetails_name_var,ccd.classdetail_id_int,ccs.* ,Specialization_name,csub.subjectName FROM laboratory_details  ccs JOIN campus_classdetail ccd ON ccs.Class_Name=ccd.classdetail_id_int  AND ccd.locationId=ccs.School_Name  JOIN campus_location cl ON ccs.School_Name=cl.Location_Id JOIN  campus_subject csub ON ccs.subject=csub.subjectID LEFT JOIN campus_class_specialization spe ON spe.Specialization_Id = ccs.Specialization  WHERE ccs.School_Name LIKE ?  AND ccd.classdetail_id_int LIKE ?  AND ccs.Specialization LIKE ?  ORDER BY cl.Location_Name,LENGTH(ccd.classdetail_id_int),ccd.classdetail_id_int,  spe.Specialization_name");
//SELECT cl.Location_Name,ccd.classdetails_name_var,ccd.classdetail_id_int,ccs.* FROM campus_subject  ccs LEFT JOIN campus_classdetail ccd ON ccs.classid=ccd.classdetail_id_int AND ccd.locationId=ccs.locationId LEFT JOIN campus_classstream cstr  ON ccd.classstream_id_int=cstr.classstream_id_int  LEFT JOIN campus_location cl ON ccs.locationId=cl.Location_Id WHERE ccs.locationId LIKE ?  AND ccd.classdetail_id_int LIKE ? AND specialization=?  ORDER BY CAST(SUBSTR(ccs.locationId,4) AS UNSIGNED),CAST(SUBSTR(ccd.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(ccs.specialization,4) AS UNSIGNED)");
			pst.setString(1, locationid);
			pst.setString(2, classname);
			pst.setString(3, specialization);
		
			
			System.out.println("pst query in listing"+pst);
			
//lab_id,School_Name,Class_Name,Lab_Name,Total_Marks,Specialization,Pass_Marks,Description,Lab_Code,Syllabus,
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				
				AddSubjectForm obj = new AddSubjectForm();
			
				String specCode=resultSet.getString("specialization");
				System.out.println("specCode value>>>>>>"+specCode);
				obj.setLab_id(resultSet.getString("lab_id"));
		  		obj.setLocationName(resultSet.getString("Location_Name"));
				obj.setClassname(resultSet.getString("classdetails_name_var"));
				obj.setSubjtname(resultSet.getString("subjectName"));
				System.out.println("subjectName value>>>>>>"+resultSet.getString("subjectName"));
				obj.setLab_name(resultSet.getString("Lab_Name"));
				obj.setSyllabus(resultSet.getString("Syllabus"));
				System.out.println("syllabus print.....>>>"+resultSet.getString("Syllabus"));
				obj.setTotalMarks(resultSet.getInt("Total_Marks"));
				obj.setPassMarks(resultSet.getInt("Pass_Marks"));
				
				obj.setDescription(resultSet.getString("Description"));
				obj.setSubjectCode(resultSet.getString("Lab_Code"));
				
				if(!specCode.equalsIgnoreCase("-")){
				PreparedStatement pssp=conn.prepareStatement("SELECT Specialization_Name from campus_class_specialization where Specialization_Id=? ");
				pssp.setString(1, specCode);
				
				ResultSet rssp=pssp.executeQuery();
				while(rssp.next()){
					obj.setSpecialization(rssp.getString("Specialization_name"));
				}
				}
				
				else{
					obj.setSpecialization("General");
				}
			
				all_lab.add(obj);
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Ending");

		return all_lab;


	}

	@Override
	public String validateLabName(AddSubjectForm form1) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationMasterDAOImpl : validateLocName : Starting");

		PreparedStatement sub_pstmt = null;
		ResultSet sub_rs = null;

		String subname_available =null;
		int count = 0;

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			sub_pstmt = conn
					.prepareStatement(SQLUtilConstants.VALIDATE_LAB_NAME);
			
//select count(*) subject from laboratory_details where Class_Name =? and Lab_Name =? and School_Name=? and Specialization=?

			sub_pstmt.setString(1, form1.getClassname());
			sub_pstmt.setString(2, form1.getSubjtname());
			sub_pstmt.setString(3, form1.getLocationId());
			sub_pstmt.setString(4, form1.getSpecialization());
			System.out.println("lab  name validating query....:" +sub_pstmt);
			sub_rs = sub_pstmt.executeQuery();

			while (sub_rs.next()) {

				count = sub_rs.getInt("subject");

			}

			if (count > 0) {

				subname_available ="true";

			} else {

				subname_available ="false";
			}

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			logger.error(sqle);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (sub_rs != null && (!sub_rs.isClosed())) {

					sub_rs.close();
				}
				if (sub_pstmt != null && (!sub_pstmt.isClosed())) {
 
					sub_pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl : validateDeptName : Ending");

		return subname_available;
	}

	public String DdownloadLabsyllabuspathDao(String subjectid) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: DdownloadsyllabuspathDao Starting");

		
		String path = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SubjectSqlUtils.GET_LAB_SYLLABUS_PATH); 
			pstmt.setString(1, subjectid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				
				path = rs.getString("Syllabus");
				
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: DdownloadsyllabuspathDao Ending");
		
		return path;
	}

	@Override
	public String getSubjectName(String subjectid) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: DdownloadsyllabuspathDao Starting");

		
		String subjectname = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SubjectSqlUtils.GET_SYLLABUS_PATH);
			pstmt.setString(1, subjectid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				
				subjectname = rs.getString("subjectName");
				
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: DdownloadsyllabuspathDao Ending");
		
		return subjectname;
	}

	

}
