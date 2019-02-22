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

import com.centris.campus.dao.MarksUploadDao;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.MarksUploadUtilConstants;
import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.ScoredMarksEntryVO;
import com.centris.campus.vo.StudentMarksDetailsVo;
import com.centris.campus.vo.StudentMarksDisplayVO;

public class MarksUploadDaoImpl implements MarksUploadDao {
	private static final Logger logger = Logger
			.getLogger(MarksUploadDaoImpl.class);
	PreparedStatement pstmt = null;
	ResultSet resultSet = null;
	String status="false";

	public String getMarksUpload(MarksUploadVO mrakUploadVO) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadDaoImpl:getMarksUpload  Starting");
		String generatedID = null;
		String subexmcount = null;
		PreparedStatement pstmtsubexmcount = null;
		PreparedStatement pst = null;
		PreparedStatement pstmtmarksinsert = null;
		ResultSet rssubexmcount = null;
		ResultSet rst = null;
		Connection conn = null;
		
		int updatedStatusCount=0;
		int insertmarksStatus=0;
		int deletemarksStatus=0;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			
						pstmtsubexmcount = conn.prepareStatement(MarksUploadUtilConstants.GET_COUNT_EXAM_SUBJECT);
						pstmtsubexmcount.setString(1, mrakUploadVO.getExamination());
						pstmtsubexmcount.setString(2, mrakUploadVO.getStudSubId());
						pstmtsubexmcount.setString(3, mrakUploadVO.getClassid());
						pstmtsubexmcount.setString(4,  mrakUploadVO.getSectionid());
						
						System.out.println("count ::: "+pstmtsubexmcount);
						
						rssubexmcount = pstmtsubexmcount.executeQuery();

						while (rssubexmcount.next()) {
							subexmcount = rssubexmcount.getString("marks_upload_id");
						}
						
						System.out.println("subexmcount :: "+subexmcount);
						
						if(subexmcount!=null){
							
							PreparedStatement pstm = conn.prepareStatement(MarksUploadUtilConstants.DELETE_OLDMARKS);
							pstm.setString(1, subexmcount);
							
							System.out.println("if exist ::: "+pstm);
							
							deletemarksStatus=pstm.executeUpdate();
							
							if(deletemarksStatus>0){
								
								for(int i=0;i<mrakUploadVO.getScoredmarksArray().size();i++){
									
									pstmtmarksinsert = conn.prepareStatement(MarksUploadUtilConstants.ENTER_STUDENT_MARKS);
									pstmtmarksinsert.setString(1, subexmcount);
									pstmtmarksinsert.setString(2,mrakUploadVO.getStudentIdArray().get(i));
									pstmtmarksinsert.setString(3, mrakUploadVO.getScoredmarksArray().get(i));
									
									System.out.println("insert studentmarks exist:::: "+pstmtmarksinsert);
									
									insertmarksStatus=pstmtmarksinsert.executeUpdate();
								}
							
						
							}
							
						}else{
							generatedID = IDGenerator.getPrimaryKeyID("campus_marks_upload");
							
							pstmt = conn.prepareStatement(MarksUploadUtilConstants.INSERT_MARKS_UPLOAD);
						
							pstmt.setString(1, generatedID);
							pstmt.setString(2, mrakUploadVO.getExamination());
							pstmt.setString(3, mrakUploadVO.getStudSubId());
							pstmt.setString(4, mrakUploadVO.getClassid());
							pstmt.setString(5, mrakUploadVO.getSectionid());
							pstmt.setString(6, mrakUploadVO.getMaxmarks());
							pstmt.setString(7, mrakUploadVO.getReqmarks());
							
							System.out.println("if not exist :: "+pstmt);
							
							updatedStatusCount=pstmt.executeUpdate();
							
							if(updatedStatusCount>0){
								
								for(int i=0;i<mrakUploadVO.getScoredmarksArray().size();i++){
									
									pstmtmarksinsert = conn.prepareStatement(MarksUploadUtilConstants.ENTER_STUDENT_MARKS);
									pstmtmarksinsert.setString(1, generatedID);
									pstmtmarksinsert.setString(2,mrakUploadVO.getStudentIdArray().get(i));
									pstmtmarksinsert.setString(3, mrakUploadVO.getScoredmarksArray().get(i));
									
									System.out.println("if not exist insert marks :: "+pstmtmarksinsert);
									
									insertmarksStatus=pstmtmarksinsert.executeUpdate();
								}
							
						}
							
			}
						
						
		if(insertmarksStatus>0){
			
			status="true";
		}
						
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (rssubexmcount != null && !rssubexmcount.isClosed()) {
					rssubexmcount.close();
				}
				if (rst != null && !rst.isClosed()) {
					rst.close();
				}
				if (pstmtsubexmcount != null && !pstmtsubexmcount.isClosed()) {
					pstmtsubexmcount.close();
				}
				if (pst != null && !pst.isClosed()) {
					pst.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (pstmtmarksinsert != null && !pstmtmarksinsert.isClosed()) {
					pstmtmarksinsert.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadDaoImpl:getMarksUpload  Ending");
		return status;

	}

	public List<ScoredMarksEntryVO> showStudentMarks(
			StudentMarksDisplayVO studentmarksdisplay) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadDaoImpl:showStudentMarks  Starting");
		ScoredMarksEntryVO enterMarks = null;
		List<ScoredMarksEntryVO> scoredmarks = new ArrayList<ScoredMarksEntryVO>();
		ResultSet resultSet = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(MarksUploadUtilConstants.SHOW_STUDENT_MARKS);
			pstmt.setString(1, studentmarksdisplay.getStudSectionId());
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				enterMarks = new ScoredMarksEntryVO();
				enterMarks.setStudentAdmissionNo(resultSet
						.getString("student_admissionno_var"));
				enterMarks.setStudentFirstName(resultSet
						.getString("student_fname_var"));
				scoredmarks.add(enterMarks);
			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
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

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadDaoImpl:showStudentMarks  Ending");
		return scoredmarks;
	}

	public List<ScoredMarksEntryVO> getStudentMarks(
			StudentMarksDisplayVO studentmarksdisplay) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadDaoImpl:getStudentMarks  Starting");
		ScoredMarksEntryVO enterMarks = new ScoredMarksEntryVO();
		ResultSet resultSet = null;
		ResultSet rs_maxMin = null;
		List<ScoredMarksEntryVO> scoredmarks = new ArrayList<ScoredMarksEntryVO>();
		ArrayList<StudentMarksDetailsVo> studentmarkslist=new ArrayList<StudentMarksDetailsVo>();
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement(MarksUploadUtilConstants.GET_STUDENT_MARKS);
			
			pstmt.setString(1, studentmarksdisplay.getStudSectionId());
			pstmt.setString(2, studentmarksdisplay.getStudClassId());
			pstmt.setString(3, studentmarksdisplay.getStudExamId());
			pstmt.setString(4, studentmarksdisplay.getStudSubjectId());
			pstmt.setString(5, studentmarksdisplay.getStudClassId());
			pstmt.setString(6, studentmarksdisplay.getStudSectionId());
			
			System.out.println("get marks :: "+pstmt);
			
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				
				count++;
				
				StudentMarksDetailsVo studentmarks = new StudentMarksDetailsVo();
				studentmarks.setStudentAdmissionNo(resultSet.getString("student_admissionno_var"));
				studentmarks.setStudentFirstName(resultSet.getString("name"));
				String marks = resultSet.getString("scoredmarks");
				studentmarks.setSerialNumber(count+"");
				
				if (marks == null) {
					marks = "";
				}
				studentmarks.setEnterMarks(marks);
				studentmarks.setStudentId(resultSet.getString("student_id_int"));
				
				studentmarkslist.add(studentmarks);
			}

			System.out.println("studentmarkslist :: "+studentmarkslist.size());
			
			pstmt = conn.prepareStatement(MarksUploadUtilConstants.GET_MAX_MIN_MARKS);
			pstmt.setString(1, studentmarksdisplay.getStudExamId());
			pstmt.setString(2, studentmarksdisplay.getStudSubjectId());
			pstmt.setString(3, studentmarksdisplay.getStudClassId());
			pstmt.setString(4, studentmarksdisplay.getStudSectionId());
			
			System.out.println("grt max marks :: "+pstmt);
			
			rs_maxMin = pstmt.executeQuery();
			
			while (rs_maxMin.next()) {
				
			
				
				enterMarks.setMaxMarks(rs_maxMin.getString("maximum_marks"));
				enterMarks.setMinMarks(rs_maxMin.getString("required_marks"));
				
			}
			
			enterMarks.setStudentmarkslist(studentmarkslist);
			scoredmarks.add(enterMarks);
			
			JSONArray array=new JSONArray();
			array.put(scoredmarks);
			
			System.out.println("final marks list :: "+array);

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {
			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (rs_maxMin != null && !rs_maxMin.isClosed()) {
					rs_maxMin.close();
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadDaoImpl:getStudentMarks  Ending");
		
		
		JSONArray array=new JSONArray();
		array.put(scoredmarks);
		
		
		System.out.println("array ::: "+array);
		
		return scoredmarks;
	}

	@Override
	public ArrayList<MarksUploadVO> getMarksUploadList(String serchterm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadDaoImpl:getMarksUpload  Starting");
		
		PreparedStatement pst = null;
		ResultSet rst = null;
		Connection conn = null;
		ArrayList<MarksUploadVO> marksUploadList=new ArrayList<MarksUploadVO>();
		
		try {
						conn = JDBCConnection.getSeparateConnection();
		
						pst = conn.prepareStatement(MarksUploadUtilConstants.GET_MARKSUPLOADED_LIST);
						
						pst.setString(1, "%"+serchterm.trim()+"%");
						pst.setString(2, "%"+serchterm.trim()+"%");
						pst.setString(3, "%"+serchterm.trim()+"%");
						pst.setString(4, "%"+serchterm.trim()+"%");
						
						System.out.println("get list :: "+pst);
					
						rst = pst.executeQuery();

						while (rst.next()) {
							
							MarksUploadVO marksvo=new MarksUploadVO();
							
							marksvo.setExamid(rst.getString("examid"));
							marksvo.setExamname(rst.getString("examname"));
							marksvo.setClassid(rst.getString("classdetail_id_int"));
							marksvo.setClassname(rst.getString("classdetails_name_var"));
							marksvo.setSectionid(rst.getString("classsection_id_int"));
							marksvo.setSectionname(rst.getString("classsection_name_var"));
							marksvo.setSubjectId(rst.getString("subjectID"));
							marksvo.setSubjectname(rst.getString("subjectName"));
							marksvo.setExamdate(rst.getString("examdate"));
							marksvo.setExamstarttime(rst.getString("examtime"));
							marksvo.setExamendtime(rst.getString("endtime"));
							
							marksUploadList.add(marksvo);
							
						}

					
		} catch (Exception e) {
			
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
			
				if (pst != null && !pst.isClosed()) {
					pst.close();
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadDaoImpl:getMarksUpload  Ending");
		return marksUploadList;

	}

}
