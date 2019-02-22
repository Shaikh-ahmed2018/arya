package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.pojo.UploadStageXlsPOJO;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SendMail;
import com.centris.campus.util.StringUtilContants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.util.UploadStageXLSqlUtil;
import com.centris.campus.util.UploadStudentXLSqlUtil;
import com.itextpdf.text.log.SysoLogger;


public class UploadStageXLSDaoImpl {

	private static Logger logger = Logger
			.getLogger(UploadStageXLSDaoImpl.class);

	public int checkStageCountBeforeInsert() {
		int beforeInsertCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(UploadStageXLSqlUtil.CHECK_BEFORINSERT_COUNT);

			System.out.println("CHECK_BEFORINSERT_COUNT:::" + pstmt);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				beforeInsertCount = rs.getInt(1);

			}
			System.out.println("In DIOMPL Before Insert: "+beforeInsertCount);
		} catch (SQLException se) {
			se.printStackTrace();
			logger.error(se);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			try {

				if (conn != null) {
					conn.close();
					conn = null;
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle);
			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error(e1);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXLSDaoImpl: Ending");

		return beforeInsertCount;
	}

	public int checkStageName(String stageId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : checkStageID : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;

		try {

			ps_emp_count = connection
					.prepareStatement(UploadStageXLSqlUtil.STAGE_DUPLICATE);
			ps_emp_count.setString(1, stageId);
			rs_emp_count = ps_emp_count.executeQuery();

			while (rs_emp_count.next()) {
				int count = rs_emp_count.getInt(1);

				return count;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs_emp_count != null && (!rs_emp_count.isClosed())) {

					rs_emp_count.close();
				}

				if (ps_emp_count != null && (!ps_emp_count.isClosed())) {

					ps_emp_count.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : checkStageID : Ending");
		return 0;
	}


	public String insertStageXSL(List<UploadStageXlsPOJO> successlist,
			Connection connection) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : insertStageXSL : Starting");
	
		
		
		
		Map<String, String> studentIDAdmissionNOMap = new HashMap<String, String>();
		ResultSet rs_emp_count=null;
		
		PreparedStatement pstmcount = null;
		
		PreparedStatement psStageAdd = null;
		
		PreparedStatement ps_emp_count= null;
		
		PreparedStatement countDuplicate=null;
		Connection conn = null;
		
		int countAfterInsert=0;
		int stageDuplicateCount = 0;
		int conutDuplicateRecord=0;
		
		
		 String returnCount=null;
		try{
			
			System.out.println("DAOIMPL Is Working Student Excel file Upload");
			    conn = JDBCConnection.getSeparateConnection();
			   
/*			    conn.setAutoCommit(false);
*/
			    psStageAdd = conn.prepareStatement(UploadStageXLSqlUtil.INSERT_STAGE);
			    
				countDuplicate = conn.prepareStatement(UploadStageXLSqlUtil.STAGE_DUPLICATE);
				

				
			System.out.println("successlist:::"+successlist.size());
			
			 for(int i=0;i<successlist.size();i++){
				 
				 System.out.println("Inside FOR Loop");
				 
				 //duplicate count
					countDuplicate.setString(1, successlist.get(i).getStage_name());
					countDuplicate.setString(2, successlist.get(i).getAccyearid());
				
					ResultSet duplicateRs = countDuplicate.executeQuery();

					
					while (duplicateRs.next()) {
						
						stageDuplicateCount = duplicateRs.getInt(1);
						
					}
					//now not checking the duplicate
					stageDuplicateCount=0;

					if (stageDuplicateCount != 0) {

						studentIDAdmissionNOMap.put("errorMessage",
								"Stage Already Registered with these Details");
						conutDuplicateRecord++;
					} else {
						
/*						conn.setAutoCommit(false);
*/				 
				 	
					//stage insert
					
					
				   new IDGenerator();
				String stageId=IDGenerator.getPrimaryKeyID("campus_fee_stage");
				   
				   Timestamp createdDate = HelperClass.getCurrentTimestamp();
				   
				   System.out.println("createdDate++++"+createdDate);
				   psStageAdd.setString(1,stageId);
				   psStageAdd.setString(2, successlist.get(i).getStage_name().trim()); 
				   psStageAdd.setString(3, successlist.get(i).getAmount().trim());
				   psStageAdd.setString(4, successlist.get(i).getStage_description().trim());
				   psStageAdd.setString(5, successlist.get(i).getCreatedby().trim());
				   psStageAdd.setTimestamp(6, createdDate);
				   psStageAdd.setString(7, successlist.get(i).getAccyearid());
				
					System.out.println("ps_student_add"+psStageAdd);
					
					psStageAdd.executeUpdate();
					
					
									 
			 }
					
		}
			 
/*			 conn.commit();
*/		
			 }catch (SQLException sqle) {
		       	sqle.printStackTrace();
			   logger.error(sqle.getMessage(),sqle);
		    } 
          catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}finally{
			
			try {if (psStageAdd != null && (!psStageAdd.isClosed())) {

				psStageAdd.close();
			}

			if (rs_emp_count != null && (!rs_emp_count.isClosed())) {

				rs_emp_count.close();
			}

			if (ps_emp_count != null && (!ps_emp_count.isClosed())) {

				ps_emp_count.close();
			}

			if (pstmcount != null && (!pstmcount.isClosed())) {

				pstmcount.close();
			}

			

           } catch (SQLException sqle) {
			       	sqle.printStackTrace();
				   logger.error(sqle.getMessage(),sqle);
			     } catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(),e);
			
		        }
		
		
		       }
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : insertStageXSL : Ending");

		returnCount=""+conutDuplicateRecord++;
		System.out.println("In DAIOMPL duplicate Count= "+returnCount);
		return returnCount;
	}
	
	public String checkAccyearName(String accyear,Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : checkAccyearName : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;

		try {
			ps_emp_count = connection
					.prepareStatement("SELECT * FROM `campus_acadamicyear` WHERE acadamic_year=?");
			ps_emp_count.setString(1, accyear);
			rs_emp_count = ps_emp_count.executeQuery();

			while (rs_emp_count.next()) {
				String count = rs_emp_count.getString("acadamic_id");
				return count;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs_emp_count != null && (!rs_emp_count.isClosed())) {

					rs_emp_count.close();
				}

				if (ps_emp_count != null && (!ps_emp_count.isClosed())) {

					ps_emp_count.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : checkStageID : Ending");
		return "No academicYear";
	}
}
