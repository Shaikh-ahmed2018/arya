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

import com.centris.campus.pojo.UploadDriverXlsPOJO;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SendMail;
import com.centris.campus.util.StringUtilContants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.util.UploadDriverXLSqlUtil;
import com.centris.campus.util.UploadStageXLSqlUtil;
import com.centris.campus.util.UploadStudentXLSqlUtil;
import com.itextpdf.text.log.SysoLogger;


public class UploadDriverXLSDaoImpl {

	private static Logger logger = Logger
			.getLogger(UploadDriverXLSDaoImpl.class);

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


	public String insertDriverXSL(List<UploadDriverXlsPOJO> successlist,
			Connection connection) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : insertStageXSL : Starting");
	
		
		Map<String, String> studentIDAdmissionNOMap = new HashMap<String, String>();
		ResultSet rs_emp_count=null;
		
		PreparedStatement pstmcount = null;
		
		PreparedStatement psDriverAdd = null;
		
		PreparedStatement ps_emp_count= null;
		
		PreparedStatement countDuplicate=null;
		Connection conn = null;
		
		int countAfterInsert=0;
		int driverDuplicateCount = 0;
		int conutDuplicateRecord=0;
		
		
		 String returnCount=null;
		try{
			
			System.out.println("DAOIMPL Is Working Student Excel file Upload");
			    conn = JDBCConnection.getSeparateConnection();
			   
/*			    conn.setAutoCommit(false);
*/
			    psDriverAdd = conn.prepareStatement(UploadDriverXLSqlUtil.INSERT_DRIVER);
			    
				countDuplicate = conn.prepareStatement(UploadDriverXLSqlUtil.DRIVER_DUPLICATE);
				

				
			System.out.println("successlist:::"+successlist.size());
			
			 for(int i=0;i<successlist.size();i++){
				 
/*						conn.setAutoCommit(false);
*/				 
				 	
					//driver insert
					
				   String dirverCode=new IDGenerator().getPrimaryKeyID("transport_driver");
				   
				   Timestamp createdDate = HelperClass.getCurrentTimestamp();
//DriverCode,type,Name,FatherName,DOB,MobileNo,EmergencyContactNo, Experience,
				  
				   psDriverAdd.setString(1,dirverCode);
				   psDriverAdd.setString(2, "driver"); 
				   psDriverAdd.setString(3, successlist.get(i).getDriverName().trim());
				   psDriverAdd.setString(4, successlist.get(i).getFatherName().trim());
				   psDriverAdd.setString(5, HelperClass.convertUIToDatabase(successlist.get(i).getDob().trim()));
				   psDriverAdd.setString(6, successlist.get(i).getMobile().trim());
				   psDriverAdd.setString(7, successlist.get(i).getEmergencyContactNo().trim());
				   psDriverAdd.setString(8, successlist.get(i).getExperiance().trim());
//Address,DOJ,Age,Gender,DLIssuedDate,DLExpirayDate,LicencetoDrive,CreateDate,CreateUser			   
				   
				   psDriverAdd.setString(9, successlist.get(i).getAddress().trim());
				   psDriverAdd.setString(10, HelperClass.convertUIToDatabase(successlist.get(i).getDoj().trim()));
				   psDriverAdd.setString(11, successlist.get(i).getAge().trim());
				   psDriverAdd.setString(12, successlist.get(i).getGender().trim());
				   psDriverAdd.setString(13, HelperClass.convertUIToDatabase(successlist.get(i).getDrivingLicenseIssuedDate().trim()));
				   psDriverAdd.setString(14, HelperClass.convertUIToDatabase(successlist.get(i).getDrivingLicenseValidityDate().trim()));
				   psDriverAdd.setString(15, successlist.get(i).getLicenseToDrive().trim());
				   psDriverAdd.setTimestamp(16, createdDate);
				   
				   psDriverAdd.setString(17, successlist.get(i).getCreatedby().trim());
				
					System.out.println(psDriverAdd);
					
					psDriverAdd.executeUpdate();
					
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
			
			try {if (psDriverAdd != null && (!psDriverAdd.isClosed())) {

				psDriverAdd.close();
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
}
