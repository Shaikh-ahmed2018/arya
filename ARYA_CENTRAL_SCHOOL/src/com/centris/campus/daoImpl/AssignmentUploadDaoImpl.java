package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.AssignmentUploadDao;
import com.centris.campus.forms.AssignmentUploadForm;
import com.centris.campus.util.AssignmentUploadUtilConstants;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AssignmentUploadVo;
import com.centris.campus.vo.AssignmentViewVO;
import com.centris.campus.vo.ProjectVO;
import com.centris.campus.vo.UserDetailVO;

public class AssignmentUploadDaoImpl implements AssignmentUploadDao{
	
	 private static final Logger logger = Logger.getLogger(AssignmentUploadDaoImpl.class);

	 @Override
	 	public String insertAssignment(AssignmentViewVO view) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AssignmentUploadDaoImpl : insertAssignment : Starting");
			
			PreparedStatement ps_getstudents = null;
			Connection conn = null;
			
			PreparedStatement ps_insetassDetails = null;
			int detailsCount=0;

			int count=0;
			String status=null;
			
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				String assignmentID=new IDGenerator().getPrimaryKeyID("campus_assignment");
				
				ps_getstudents = conn.prepareStatement(AssignmentUploadUtilConstants.INSERT_ASSIGNMENT);
				ps_getstudents.setString(1,assignmentID);
				ps_getstudents.setString(2, view.getClassid());
				ps_getstudents.setString(3, view.getSectionid());
				ps_getstudents.setString(4, view.getSpecializationId());
				ps_getstudents.setString(5, view.getLocation());
				ps_getstudents.setString(6, HelperClass.convertUIToDatabase(view.getAssignmentdate()));
				ps_getstudents.setString(7, HelperClass.convertUIToDatabase(view.getCompletiondate()));
				ps_getstudents.setString(8, view.getAssignmentname());
				ps_getstudents.setString(9, view.getDescription());
				ps_getstudents.setString(10, view.getSubjectname());
				ps_getstudents.setString(11, view.getMaxmarks());
				ps_getstudents.setString(12, view.getAccYear());
				ps_getstudents.setString(13, view.getUserId());
				ps_getstudents.setTimestamp(14, HelperClass.getCurrentTimestamp());
				
				
				count=ps_getstudents.executeUpdate();
				
				if(count>0){
					
					
						ps_insetassDetails = conn.prepareStatement(AssignmentUploadUtilConstants.INSERT_ASSIGNMENT_DETAILS);
						
						for(int i=0;i<view.getStudentId().length;i++){
							
							ps_insetassDetails.setString(1, view.getAccYear());
							ps_insetassDetails.setString(2, view.getLocation());
							ps_insetassDetails.setString(3, assignmentID);
							ps_insetassDetails.setString(4, view.getStudentId()[i]);
							ps_insetassDetails.setString(5, view.getMaxmarks());
							ps_insetassDetails.setString(6, HelperClass.convertUIToDatabase(view.getCompletiondate()));
							ps_insetassDetails.setString(7, view.getSpec_Id()[i]);
							ps_insetassDetails.setString(8, view.getUserId());
							ps_insetassDetails.setTimestamp(9, HelperClass.getCurrentTimestamp());
							
							detailsCount=ps_insetassDetails.executeUpdate();
						
						}
					
				}
				
				if(detailsCount>0){
					
					status="true";
				}else{
					
					status="false";
				}
				
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					
					if (ps_getstudents != null&& (!ps_getstudents.isClosed())) {
						ps_getstudents.close();
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
					+ " Control in AssignmentUploadDaoImpl : insertAssignment: Ending");
			
			return status;
		}


	@Override
		public ArrayList<AssignmentUploadVo> getStudentDetails(String section) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AssignmentUploadDaoImpl : getStudentDetails : Starting");
			
			PreparedStatement ps_getstudents = null;
			ResultSet rs_getstudents=null;
			Connection conn = null;
			
			ArrayList<AssignmentUploadVo> StudentList=new ArrayList<AssignmentUploadVo>();
			
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection();
			
				ps_getstudents = conn.prepareStatement(AssignmentUploadUtilConstants.GET_STUDENT_DETAILS);
				ps_getstudents.setString(1, section);
				
				rs_getstudents=ps_getstudents.executeQuery();
				
				while(rs_getstudents.next()){
					
					AssignmentUploadVo assignmentVo=new AssignmentUploadVo();
					
					assignmentVo.setStudentId(rs_getstudents.getString("student_id_int"));
					assignmentVo.setStudentName(rs_getstudents.getString("studentName"));
					assignmentVo.setAdmissionNo(rs_getstudents.getString("student_admissionno_var"));
					
					StudentList.add(assignmentVo);
				}
				
				
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					
					if (ps_getstudents != null&& (!ps_getstudents.isClosed())) {
						ps_getstudents.close();
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
					+ " Control in AssignmentUploadDaoImpl : getStudentDetails: Ending");
			
			return StudentList;
		}

	 
		public ArrayList<AssignmentUploadVo> getAssignment(String userId,String accYearString,String searchTerm) {
			   
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in AssignmentUploadDaoImpl : getAssignment : Starting");
				
				PreparedStatement ps_getassignment = null;
				ResultSet rs_assignment=null;
				Connection conn = null;
				
				ArrayList<AssignmentUploadVo> assignmentList=new ArrayList<AssignmentUploadVo>();
				
				try {
					
					conn = JDBCConnection.getSeparateConnection();
				
					ps_getassignment = conn.prepareStatement(AssignmentUploadUtilConstants.GET_ASSIGNMENT);
					
					ps_getassignment.setString(1,accYearString);
					ps_getassignment.setString(2, userId);
					ps_getassignment.setString(3, "%"+searchTerm+"%");
					
					rs_assignment=ps_getassignment.executeQuery();
					
					while(rs_assignment.next()){
						
						AssignmentUploadVo assignmentvo=new AssignmentUploadVo();
						
						assignmentvo.setAssignmentId(rs_assignment.getString("AssignmentCode"));
						assignmentvo.setClassname(rs_assignment.getString("classdetails_name_var"));
						assignmentvo.setSection(rs_assignment.getString("classsection_name_var"));
						assignmentvo.setAssigName(rs_assignment.getString("Name"));
						assignmentvo.setStartdate(HelperClass.convertDatabaseToUI(rs_assignment.getString("AssignmentDate")));
						assignmentvo.setEnddate(HelperClass.convertDatabaseToUI(rs_assignment.getString("CompletionDate")));
						assignmentvo.setSubjectname(rs_assignment.getString("subjectName"));
						assignmentvo.setMarks(rs_assignment.getString("MaxMarks"));
					
						assignmentList.add(assignmentvo);
					}
					
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				} finally {
					try {
						
						if (ps_getassignment != null&& (!ps_getassignment.isClosed())) {
							ps_getassignment.close();
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
						+ " Control in AssignmentUploadDaoImpl : getAssignment: Ending");
				
				return assignmentList;
			}


		@Override
		public ArrayList<AssignmentUploadVo> getAssignmentDetails(String assignmentId) {
			   
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in AssignmentUploadDaoImpl : getAssignmentDetails : Starting");
				
				PreparedStatement ps_getassignment = null;
				ResultSet rs_assignment=null;
				Connection conn = null;
				int count=0;
				
				ArrayList<AssignmentUploadVo> assignmentList=new ArrayList<AssignmentUploadVo>();
				
				try {
					
					conn = JDBCConnection.getSeparateConnection();
				
					ps_getassignment = conn.prepareStatement(AssignmentUploadUtilConstants.GET_ASSIGNMENT_DETAILS);
					
					ps_getassignment.setString(1,assignmentId);
					
					rs_assignment=ps_getassignment.executeQuery();
					
					while(rs_assignment.next()){
						
						AssignmentUploadVo assignmentvo=new AssignmentUploadVo();
						count++;
						
						assignmentvo.setSno(count);
						assignmentvo.setAssignmentId(assignmentId);
						assignmentvo.setStudentId(rs_assignment.getString("student_id_int"));
						assignmentvo.setAdmissionNo(rs_assignment.getString("student_admissionno_var"));
						assignmentvo.setStudentName(rs_assignment.getString("studentName"));
						if("".equalsIgnoreCase(rs_assignment.getString("ActualCompletionDate")) || rs_assignment.getString("ActualCompletionDate")==null){
							
							assignmentvo.setActualcompletedDate("");
							
						}else{
							
							assignmentvo.setActualcompletedDate(HelperClass.convertDatabaseToUI(rs_assignment.getString("ActualCompletionDate")));
						}
						assignmentvo.setAcquiredMarks(rs_assignment.getString("AcquiredMarks"));
						assignmentvo.setRemarks(rs_assignment.getString("Remarks"));
					
						assignmentList.add(assignmentvo);
					}
					
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				} finally {
					try {
						
						if (ps_getassignment != null&& (!ps_getassignment.isClosed())) {
							ps_getassignment.close();
						}
						if(rs_assignment != null && (!rs_assignment.isClosed()))
						{
							rs_assignment.close();
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
						+ " Control in AssignmentUploadDaoImpl : getAssignmentDetails: Ending");
				
				return assignmentList;
			}


		@Override
		public AssignmentUploadVo getSingleAssignment(String assignmentId) {
			   
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in AssignmentUploadDaoImpl : getSingleAssignment : Starting");
				
				PreparedStatement ps_getassignment = null;
				PreparedStatement ps = null;
				ResultSet rs_assignment=null;
				ResultSet rs=null;
				Connection conn = null;
				
				AssignmentUploadVo assignmentvo=new AssignmentUploadVo();
				
				try {
					
					conn = JDBCConnection.getSeparateConnection();
				
					ps_getassignment = conn.prepareStatement(AssignmentUploadUtilConstants.GET_SINGLE_ASSIGNMENT);
					
					ps_getassignment.setString(1,assignmentId);
					
					rs_assignment=ps_getassignment.executeQuery();
					
					while(rs_assignment.next()){
						
						assignmentvo.setAssignmentId(assignmentId);
						assignmentvo.setAssignmentId(rs_assignment.getString("AssignmentCode"));
						assignmentvo.setClassname(rs_assignment.getString("classdetails_name_var"));
						assignmentvo.setSection(rs_assignment.getString("classsection_name_var"));
						assignmentvo.setAssigName(rs_assignment.getString("Name"));
						assignmentvo.setStartdate(HelperClass.convertDatabaseToUI(rs_assignment.getString("AssignmentDate")));
						assignmentvo.setEnddate(HelperClass.convertDatabaseToUI(rs_assignment.getString("CompletionDate")));
						assignmentvo.setSubjectname(rs_assignment.getString("subjectName"));
						assignmentvo.setMarks(rs_assignment.getString("MaxMarks"));
						assignmentvo.setSpecializationId(rs_assignment.getString("specializationId"));
						
						if(rs_assignment.getString("specializationId").equals("-") || rs_assignment.getString("specializationId")==("-"))
						{
							assignmentvo.setSpecializationName("-");
						}
						else
						{
							ps = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?");
							ps.setString(1, rs_assignment.getString("specializationId"));
							
							rs = ps.executeQuery();
							while(rs.next()) {
								assignmentvo.setSpecializationName(rs.getString("Specialization_name"));
							}
							
						}
					
					}
					
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				} finally {
					try {
						
						if (ps_getassignment != null&& (!ps_getassignment.isClosed())) {
							ps_getassignment.close();
						}
						if(rs_assignment != null && (!rs_assignment.isClosed()))
						{
							rs_assignment.close();
						}
						if (ps != null&& (!ps.isClosed())) {
							ps_getassignment.close();
						}
						if(rs != null && (!rs.isClosed()))
						{
							rs.close();
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
						+ " Control in AssignmentUploadDaoImpl : getSingleAssignment: Ending");
				
				return assignmentvo;
			}


		@Override
		public String updateAssignmentDetails(AssignmentUploadVo assignmentVo) {
			   
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in AssignmentUploadDaoImpl : updateAssignmentDetails : Starting");
				
				PreparedStatement ps_getstudents = null;
				Connection conn = null;
				
				int count=0;
				String status=null;
				
				try {
					
					conn = JDBCConnection.getSeparateConnection();
				
					ps_getstudents = conn.prepareStatement(AssignmentUploadUtilConstants.UPDATE_ASSIGNMENT_DETAILS);
					
					String[] studentId=assignmentVo.getStudentId().split(",");
					String[] completionDate=assignmentVo.getActualcompletedDate().split(",");
					String[] marks=assignmentVo.getAcquiredMarks().split(",");
					String[] remarks=assignmentVo.getRemarks().split(",");
					
					
					
					for(int i=0;i<studentId.length;i++){
						
					ps_getstudents.setString(1, HelperClass.convertUIToDatabase(completionDate[i]));
					ps_getstudents.setString(2, marks[i]);
					if(remarks[i].equalsIgnoreCase("null")){
						ps_getstudents.setString(3, "");
					}else{
					ps_getstudents.setString(3, remarks[i]);
					}
					ps_getstudents.setString(4, assignmentVo.getUserID());
					ps_getstudents.setTimestamp(5, HelperClass.getCurrentTimestamp());
					ps_getstudents.setString(6, assignmentVo.getAssignmentId());
					ps_getstudents.setString(7, studentId[i]);
					
					count=ps_getstudents.executeUpdate();
					}
					
					if(count>0){
						
						status="true";
					}else{
						status="false";
					}
					
					
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				} finally {
					try {
						
						if (ps_getstudents != null&& (!ps_getstudents.isClosed())) {
							ps_getstudents.close();
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
						+ " Control in AssignmentUploadDaoImpl : updateAssignmentDetails: Ending");
				
				return status;
			}


		@Override
		public String deleteAssignment(AssignmentViewVO view) {
			   
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in AssignmentUploadDaoImpl : deleteAssignment : Starting");
				
				PreparedStatement ps_deleteassgnment = null;
				Connection conn = null;
				PreparedStatement ps_deleteassgnmentdetails = null;
				int count=0;
				int detailscount=0;
				String status=null;
				
				try {
					
					conn = JDBCConnection.getSeparateConnection();
				
					ps_deleteassgnment = conn.prepareStatement(AssignmentUploadUtilConstants.DELETE_ASSIGNMENT);
					
					for(int i=0;i<view.getAssignmentCode().length;i++)
					{
						ps_deleteassgnment.setString(1, view.getAssignmentCode()[i]);
						count=ps_deleteassgnment.executeUpdate();
					}
					
					
					
				if(count>0){
					
					ps_deleteassgnmentdetails = conn.prepareStatement(AssignmentUploadUtilConstants.DELETE_ASSIGNMENT_DETAILS);
					
					for(int i=0;i<view.getAssignmentCode().length;i++)
					{
						ps_deleteassgnmentdetails.setString(1, view.getAssignmentCode()[i]);
						detailscount=ps_deleteassgnmentdetails.executeUpdate();
					}
					
					
				}
					
					if(detailscount>0){
						
						status="true";
					}else{
						status="false";
					}
					
					
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				} finally {
					try {
						
						if (ps_deleteassgnment != null&& (!ps_deleteassgnment.isClosed())) {
							ps_deleteassgnment.close();
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
						+ " Control in AssignmentUploadDaoImpl : deleteAssignment: Ending");
				
				return status;
			}


		@Override
		public String addProject(ProjectVO viewVo) {
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in AssignmentUploadDaoImpl : addProject : Starting");
				
				PreparedStatement pstmt = null;
				Connection conn = null;
				PreparedStatement pstmt1 = null;
				int count=0,Count1=0;
				int detailscount=0;
				String status=null;
				
				try {
					
					conn = JDBCConnection.getSeparateConnection();
					String projectID=new IDGenerator().getPrimaryKeyID("campus_project");
					
					pstmt = conn.prepareStatement(AssignmentUploadUtilConstants.INSERT_PROJECT);
					pstmt.setString(1,projectID);
					pstmt.setString(2, viewVo.getProjectname());
					pstmt.setString(3, viewVo.getAccYear());
					pstmt.setString(4, viewVo.getClassid());
					pstmt.setString(5, viewVo.getSectionid());
					pstmt.setString(6, viewVo.getSpecializationId());
					pstmt.setString(7, viewVo.getSubjectid());
					pstmt.setString(8, viewVo.getLocation());
					pstmt.setString(9, HelperClass.convertUIToDatabase(viewVo.getAssigneddate()));
					pstmt.setString(10, HelperClass.convertUIToDatabase(viewVo.getSubmissiondate()));
					pstmt.setString(11, viewVo.getDescription());
					pstmt.setString(12, viewVo.getMaxmarks());
					pstmt.setString(13, viewVo.getUserId());
					pstmt.setTimestamp(14, HelperClass.getCurrentTimestamp());
					count=pstmt.executeUpdate();
					if(count>0){
						pstmt1 = conn.prepareStatement(AssignmentUploadUtilConstants.INSERT_PROJECT_STUDENTWISE);
							
							for(int i=0;i<viewVo.getStudentId().length;i++){
								
								pstmt1.setString(1, viewVo.getAccYear());
								pstmt1.setString(2, viewVo.getLocation());
								pstmt1.setString(3, projectID);
								pstmt1.setString(4, viewVo.getStudentId()[i]);
								pstmt1.setString(5, viewVo.getMaxmarks());
								pstmt1.setString(6, HelperClass.convertUIToDatabase(viewVo.getSubmissiondate()));
								pstmt1.setString(7, viewVo.getUserId());
								pstmt1.setTimestamp(8, HelperClass.getCurrentTimestamp());
								Count1=pstmt1.executeUpdate();
							
							}
						
					}
					
					if(Count1>0){
						
						status="true";
					}else{
						
						status="false";
					}
					
					
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				} finally {
					try {
						
						if (pstmt!= null&& (!pstmt.isClosed())) {
							pstmt.close();
						}
						if (pstmt1!= null&& (!pstmt1.isClosed())) {
							pstmt1.close();
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
						+ " Control in AssignmentUploadDaoImpl : addProject: Ending");
				
				return status;
			}

		
		public ArrayList<ProjectVO> getProjectList(String userId, String accYearString, String searchTerm) {
			
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AssignmentUploadDaoImpl : getProjectList : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			
			ArrayList<ProjectVO> List=new ArrayList<ProjectVO>();
			
			try {
				
				conn = JDBCConnection.getSeparateConnection();
			
				pstmt = conn.prepareStatement(AssignmentUploadUtilConstants.GET_PROJECT_LIST);
				pstmt.setString(1,accYearString);
				pstmt.setString(2, userId);
				pstmt.setString(3, "%"+searchTerm+"%");
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					
					ProjectVO vo=new ProjectVO();
					vo.setProjectcode(rs.getString("projectCode"));
					vo.setProjectname(rs.getString("projectName"));
					vo.setClassid(rs.getString("ClassId"));
					vo.setSectionid(rs.getString("SectionId"));
					vo.setAssigneddate(rs.getString("AssignedDate"));
					vo.setSubmissiondate(rs.getString("SubmissionDate"));
					vo.setMaxmarks(rs.getString("MaxMarks"));
					vo.setClassname(rs.getString("classdetails_name_var"));
					vo.setSectionname(rs.getString("classsection_name_var"));
					
					List.add(vo);
				}
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					
					if (pstmt != null&& (!pstmt.isClosed())) {
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
					+ " Control in AssignmentUploadDaoImpl : getProjectList: Ending");
			
			return List;
		}


		@Override
		public ArrayList<ProjectVO> getProjectDetails(String projectId) {
			
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AssignmentUploadDaoImpl : getProjectDetails : Starting");
			
			PreparedStatement ps_getProject = null;
			ResultSet rs_project = null;
			Connection conn = null;
			int count=0;
			
			ArrayList<ProjectVO> projectList=new ArrayList<ProjectVO>();
			
			try {
				
				conn = JDBCConnection.getSeparateConnection();
			
				ps_getProject = conn.prepareStatement(AssignmentUploadUtilConstants.GET_PROJECT_DETAILS);
				
				ps_getProject.setString(1,projectId);
				
				rs_project=ps_getProject.executeQuery();
				
				while(rs_project.next()){
					
					ProjectVO projectVo = new ProjectVO();
					count++;
					
					projectVo.setSno(count);
					projectVo.setProjectId(projectId);
					projectVo.setStudentid(rs_project.getString("student_id_int"));
					projectVo.setAdmissionNo(rs_project.getString("student_admissionno_var"));
					projectVo.setStudentname(rs_project.getString("studentName"));
					if("".equalsIgnoreCase(rs_project.getString("SubmissionDate")) || rs_project.getString("SubmissionDate")==null){
						
						projectVo.setActualcompletedate("");
						
					}else{
						
						projectVo.setActualcompletedate(HelperClass.convertDatabaseToUI(rs_project.getString("SubmissionDate")));
					}
					projectVo.setAcquiredmarks(rs_project.getString("obtainedMarks"));
					projectVo.setRemarks(rs_project.getString("Remarks"));
				
					projectList.add(projectVo);
				}
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					
					if (ps_getProject != null&& (!ps_getProject.isClosed())) {
						ps_getProject.close();
					}
					if(rs_project != null && (!rs_project.isClosed()))
					{
						rs_project.close();
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
					+ " Control in AssignmentUploadDaoImpl : getProjectDetails: Ending");
			
			return projectList;
		}


		@Override
		public ProjectVO getSingleProject(String projectId) {
			
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AssignmentUploadDaoImpl : getSingleProject : Starting");
			
			PreparedStatement ps_getproject = null,ps_getproject1 = null;
			PreparedStatement ps = null;
			ResultSet rs_project=null;
			ResultSet rs=null,rs0=null;
			Connection conn = null;
			
			ProjectVO projectvo = new ProjectVO();
			
			try {
				
				conn = JDBCConnection.getSeparateConnection();
			
				ps_getproject = conn.prepareStatement(AssignmentUploadUtilConstants.GET_SINGLE_PROJECT);
				
				ps_getproject.setString(1,projectId);
				rs_project=ps_getproject.executeQuery();
				
				while(rs_project.next()){
					
					projectvo.setProjectId(projectId);
					projectvo.setProjectcode(rs_project.getString("projectCode"));
					projectvo.setClassname(rs_project.getString("classdetails_name_var"));
					projectvo.setSectionname(rs_project.getString("classsection_name_var"));
					projectvo.setProjectname(rs_project.getString("projectName"));
					projectvo.setStartdate(HelperClass.convertDatabaseToUI(rs_project.getString("AssignedDate")));
					projectvo.setEnddate(HelperClass.convertDatabaseToUI(rs_project.getString("SubmissionDate")));
					projectvo.setMaxmarks(rs_project.getString("MaxMarks"));
					projectvo.setSpecializationId(rs_project.getString("SpecializationId"));
					projectvo.setSubjectid(rs_project.getString("SubjectId"));
					
					ps_getproject1=conn.prepareStatement("select subjectName from campus_subject where subjectID=?");
					ps_getproject1.setString(1,rs_project.getString("SubjectId"));
					
					rs0=ps_getproject1.executeQuery();
					while(rs0.next()){
						projectvo.setSubjectname(rs0.getString("subjectName"));
					}
					if(rs_project.getString("specializationId").equals("-") || rs_project.getString("specializationId")==("-"))
					{
						projectvo.setSpecialization("-");
					}
					else
					{
						ps = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?");
						ps.setString(1, rs_project.getString("specializationId"));
						
						rs = ps.executeQuery();
						while(rs.next()) {
							projectvo.setSpecialization(rs.getString("Specialization_name"));
						}
						
					}
				
				}
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					
					if (ps_getproject != null&& (!ps_getproject.isClosed())) {
						ps_getproject.close();
					}
					if(rs_project != null && (!rs_project.isClosed()))
					{
						rs_project.close();
					}
					if (ps != null&& (!ps.isClosed())) {
						ps_getproject.close();
					}
					if(rs != null && (!rs.isClosed()))
					{
						rs.close();
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
					+ " Control in AssignmentUploadDaoImpl : getSingleProject: Ending");
			
			return projectvo;
		}

		
		@Override
		public String updateProjectDetails(ProjectVO projectvo) {
			
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AssignmentUploadDaoImpl : updateProjectDetails : Starting");
			
			PreparedStatement ps_getstudents = null;
			Connection conn = null;
			
			int count=0;
			String status=null;
			
			try {
				
				conn = JDBCConnection.getSeparateConnection();
			
				ps_getstudents = conn.prepareStatement(AssignmentUploadUtilConstants.UPDATE_PROJECT_DETAILS);
				
				String[] studentId=projectvo.getStudentid().split(",");
				String[] completionDate=projectvo.getActualcompletedate().split(",");
				String[] marks=projectvo.getAcquiredmarks().split(",");
				String[] remarks=projectvo.getRemarks().split(",");
				
				
				
				for(int i=0;i<studentId.length;i++){
					
				ps_getstudents.setString(1, HelperClass.convertUIToDatabase(completionDate[i]));
				ps_getstudents.setString(2, marks[i]);
				if(remarks[i].equalsIgnoreCase("null")){
					ps_getstudents.setString(3, "");
				}else{
				ps_getstudents.setString(3, remarks[i]);
				}
				ps_getstudents.setString(4, projectvo.getUserId());
				ps_getstudents.setTimestamp(5, HelperClass.getCurrentTimestamp());
				ps_getstudents.setString(6, projectvo.getProjectId());
				ps_getstudents.setString(7, studentId[i]);
				
				count=ps_getstudents.executeUpdate();
				}
				
				if(count>0){
					
					status="true";
				}else{
					status="false";
				}
				
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					
					if (ps_getstudents != null&& (!ps_getstudents.isClosed())) {
						ps_getstudents.close();
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
					+ " Control in AssignmentUploadDaoImpl : updateProjectDetails: Ending");
			
			return status;
		}
		

		@Override
		public String deleteProject(ProjectVO project) {
			
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AssignmentUploadDaoImpl : deleteAssignment : Starting");
			
			PreparedStatement ps_deleteproject = null;
			Connection conn = null;
			PreparedStatement ps_deleteprojectdetails = null;
			int count=0;
			int detailscount=0;
			String status=null;
			
			try {
				
				conn = JDBCConnection.getSeparateConnection();
			
				ps_deleteproject = conn.prepareStatement(AssignmentUploadUtilConstants.DELETE_PROJECT);
				
				for(int i=0;i<project.getProjectCode().length;i++)
				{
					ps_deleteproject.setString(1, project.getProjectCode()[i]);
					count=ps_deleteproject.executeUpdate();
				}
				
				
				
			if(count>0){
				
				ps_deleteprojectdetails = conn.prepareStatement(AssignmentUploadUtilConstants.DELETE_PROJECT_DETAILS);
				
				for(int i=0;i<project.getProjectCode().length;i++)
				{
					ps_deleteprojectdetails.setString(1, project.getProjectCode()[i]);
					detailscount=ps_deleteprojectdetails.executeUpdate();
				}
				
				
			}
				
				if(detailscount>0){
					
					status="true";
				}else{
					status="false";
				}
				
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					
					if (ps_deleteprojectdetails != null&& (!ps_deleteprojectdetails.isClosed())) {
						ps_deleteprojectdetails.close();
					}
					if (ps_deleteproject != null&& (!ps_deleteproject.isClosed())) {
						ps_deleteproject.close();
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
					+ " Control in AssignmentUploadDaoImpl : deleteAssignment: Ending");
			
			return status;
		}

		
		
}
