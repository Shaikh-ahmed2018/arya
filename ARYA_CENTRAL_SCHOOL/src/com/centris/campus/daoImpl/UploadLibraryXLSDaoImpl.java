package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.runner.Request;

import com.centris.campus.pojo.UploadLibraryXlsPOJO;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LibrarySqlUtils;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.SendMail;
import com.centris.campus.util.StringUtilContants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.util.UploadStudentXLSqlUtil;
import com.centris.campus.vo.StockEntryVo;
import com.centris.campus.vo.UploadLibraryXlsVO;
import com.centris.campus.vo.UploadStudentXlsVO;
import com.itextpdf.text.log.SysoLogger;


public class UploadLibraryXLSDaoImpl {

	private static Logger logger = Logger
			.getLogger(UploadLibraryXLSDaoImpl.class);

	public int checkEmpCountBeforeInsert() {
		int beforeInsertCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(UploadStudentXLSqlUtil.CHECK_BEFORINSERT_COUNT);
			System.out.println("CHECK_BEFORINSERT_COUNT:::" + pstmt);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				beforeInsertCount = rs.getInt(1);

			}
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
				+ " Control in UploadStudentXLSDaoImpl: Ending");

		return beforeInsertCount;
	}


	public int checkCategorycode(String studentId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;

		try {

			ps_emp_count = connection
					.prepareStatement(UploadStudentXLSqlUtil.checkLibrarytypeCode);
			ps_emp_count.setString(1, studentId);
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return 0;
	}

	public int checkLibrarytypeName(String code,String name, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");
		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;
		try {
			ps_emp_count = connection.prepareStatement(UploadStudentXLSqlUtil.CHECK_CATEGORY_NAME);
			ps_emp_count.setString(1, code);
			ps_emp_count.setString(2, name);
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return 0;
	}

	public int checkClassCode(String classname, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;

		try {

			ps_emp_count = connection
					.prepareStatement(UploadStudentXLSqlUtil.CHECK_CLASS_ID);
			ps_emp_count.setString(1, classname);
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return 0;
	}

	public int checkSectionCode(String sectionname, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;

		try {

			ps_emp_count = connection
					.prepareStatement(UploadStudentXLSqlUtil.CHECK_SECTION_ID);
			ps_emp_count.setString(1, sectionname);
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return 0;
	}

	public Set<UploadLibraryXlsVO> insertLibXSL(List<UploadLibraryXlsPOJO> successlist,Connection connection, String username) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + "Control in UploadEmpXSLDaoImpl : insertEmpXSL : Starting");

		Set<UploadLibraryXlsVO> failurelistOnDiompl = new LinkedHashSet<UploadLibraryXlsVO>();
		Map<String, String> studentIDAdmissionNOMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstm=null,pstm1=null,pstm2=null,pstm3=null,pstm4=null,pstm5=null;
		ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs4=null,rs5=null;


		try{
			System.out.println("DAOIMPL Is Working Library Excel file Upload");
			conn = JDBCConnection.getSeparateConnection();
			conn.setAutoCommit(false);

			for(int i=0;i<successlist.size();i++){
				UploadLibraryXlsVO uploaLibraryXSLVo = new UploadLibraryXlsVO();
				Timestamp createdDate = HelperClass.getCurrentTimestamp();
				uploaLibraryXSLVo.setCategorytypecode(successlist.get(i).getCategorytypecode());
				uploaLibraryXSLVo.setCategorytypename(successlist.get(i).getCategorytypename());
				uploaLibraryXSLVo.setCategorystatus(successlist.get(i).getCategorystatus());
				uploaLibraryXSLVo.setCategorydescription(successlist.get(i).getCategorydescription());
				uploaLibraryXSLVo.setSubcategorytypecode(successlist.get(i).getSubcategorytypecode());
				uploaLibraryXSLVo.setSubcategorytypename(successlist.get(i).getSubcategorytypename());
				uploaLibraryXSLVo.setSubcategorystatus(successlist.get(i).getSubcategorystatus());
				uploaLibraryXSLVo.setSubcategorydescription(successlist.get(i).getSubcategorydescription());

				uploaLibraryXSLVo.setSubcategorytype1code(successlist.get(i).getSubcategorytype1code());
				uploaLibraryXSLVo.setSubcategorytype1name(successlist.get(i).getSubcategorytype1name());
				uploaLibraryXSLVo.setSubcategory1status(successlist.get(i).getSubcategory1status());
				uploaLibraryXSLVo.setSubcategory1description(successlist.get(i).getSubcategory1description());

				uploaLibraryXSLVo.setSubcategorytype2code(successlist.get(i).getSubcategorytype2code());
				uploaLibraryXSLVo.setSubcategorytype2name(successlist.get(i).getSubcategorytype2name());
				uploaLibraryXSLVo.setSubcategory2status(successlist.get(i).getSubcategory2status());
				uploaLibraryXSLVo.setSubcategory2description(successlist.get(i).getSubcategory2description());

				uploaLibraryXSLVo.setSubcategorytype3code(successlist.get(i).getSubcategorytype3code());
				uploaLibraryXSLVo.setSubcategorytype3name(successlist.get(i).getSubcategorytype3name());
				uploaLibraryXSLVo.setSubcategory3status(successlist.get(i).getSubcategory3status());
				uploaLibraryXSLVo.setSubcategory3description(successlist.get(i).getSubcategory3description());

				pstm1 =conn.prepareStatement("SELECT COUNT(`category_code`) FROM `library_category` WHERE `category_code`=?");
				pstm1.setString(1, successlist.get(i).getCategorytypecode());
				rs1 = pstm1.executeQuery();
				while(rs1.next()){
					if(rs1.getInt(1)==0){

						System.out.println("INSIDE SECOND IF LOOP");
						pstm=conn.prepareStatement(SQLUtilConstants.INSERT_CATEGORY_TYPE);
						pstm.setString(1,uploaLibraryXSLVo.getCategorytypecode());
						pstm.setString(2, uploaLibraryXSLVo.getCategorytypename());
						pstm.setString(3, uploaLibraryXSLVo.getCategorydescription());
						pstm.setString(4, uploaLibraryXSLVo.getCategorystatus());
						pstm.setTimestamp(5,createdDate);
						pstm.setString(6,username);
						pstm.executeUpdate();
						conn.commit();/*uploaLibraryXSLVo.getCreated_by()*/
						pstm.close();
					}
				}
				pstm2 =conn.prepareStatement("SELECT COUNT(`subcategory_code`) FROM `library_subcategory` WHERE `subcategory_code`=?");
				pstm2.setString(1, successlist.get(i).getSubcategorytypecode());
				rs2 = pstm2.executeQuery();
				while(rs2.next()){
					if(rs2.getInt(1)==0){
						// `category_code`,`subcategory_code`,`subcategory_name`,`description`,`status`,`create_time`,`createdby
						pstm=conn.prepareStatement(SQLUtilConstants.INSERT_SUB_CATEGORY_TYPE);

						pstm.setString(1,uploaLibraryXSLVo.getCategorytypecode());
						pstm.setString(2, uploaLibraryXSLVo.getSubcategorytypecode());
						pstm.setString(3, uploaLibraryXSLVo.getSubcategorytypename());
						pstm.setString(4, uploaLibraryXSLVo.getSubcategorydescription());
						pstm.setString(5, uploaLibraryXSLVo.getSubcategorystatus());
						pstm.setTimestamp(6,createdDate);
						pstm.setString(7,username); /*uploaLibraryXSLVo.getCreated_by()*/
						pstm.executeUpdate();
						conn.commit();
						pstm.close();
					}
				}
				pstm3 =conn.prepareStatement("SELECT COUNT(`subcategory1_code`) FROM `library_subcategory1` WHERE `subcategory1_code`=?");
				pstm3.setString(1, successlist.get(i).getSubcategorytype1code());
				rs3 = pstm3.executeQuery();
				while(rs3.next()){
					if(rs3.getInt(1)==0){
						pstm=conn.prepareStatement(SQLUtilConstants.INSERT_SUB_CATEGORY_TYPE_1);
						// category_code,subcategory_code,subcategory1_code,subcategory1_name,description,`status`,created_time,createdby				 
						pstm.setString(1,uploaLibraryXSLVo.getCategorytypecode());
						pstm.setString(2, uploaLibraryXSLVo.getSubcategorytypecode());
						pstm.setString(3, uploaLibraryXSLVo.getSubcategorytype1code());
						pstm.setString(4, uploaLibraryXSLVo.getSubcategorytype1name());
						pstm.setString(5, uploaLibraryXSLVo.getSubcategory1description());
						pstm.setString(6, uploaLibraryXSLVo.getSubcategory1status());
						pstm.setTimestamp(7,createdDate);
						pstm.setString(8,username); /*uploaLibraryXSLVo.getCreated_by()*/
						pstm.executeUpdate();
						conn.commit();
						pstm.close();
					}
				}
				pstm4 =conn.prepareStatement("SELECT COUNT(`subcategory2_code`) FROM `library_subcategory2` WHERE `subcategory2_code`=? ");
				pstm4.setString(1, successlist.get(i).getSubcategorytype2code());
				rs4 = pstm4.executeQuery();
				while(rs4.next()){
					if(rs4.getInt(1)==0){
						//`category_code`,`subcategory_code`,`subcategory1_code`,`subcategory2_code`,`subcategory2_name`,`description`,`status`,`createdtime`,`created_by`									 
						pstm=conn.prepareStatement(LibrarySqlUtils.INSERT_SUB_CATEGORY_TYPE_2);
						pstm.setString(1,uploaLibraryXSLVo.getCategorytypecode());
						pstm.setString(2, uploaLibraryXSLVo.getSubcategorytypecode());
						pstm.setString(3, uploaLibraryXSLVo.getSubcategorytype1code());
						pstm.setString(4, uploaLibraryXSLVo.getSubcategorytype2code());
						pstm.setString(5, uploaLibraryXSLVo.getSubcategorytype2name());
						pstm.setString(6, uploaLibraryXSLVo.getSubcategory2description());
						pstm.setString(7, uploaLibraryXSLVo.getSubcategory2status());
						pstm.setTimestamp(8,createdDate);
						pstm.setString(9,username); /*uploaLibraryXSLVo.getCreated_by()*/
						pstm.executeUpdate();
						conn.commit();
						pstm.close();
					}
				}
				pstm5 =conn.prepareStatement("SELECT COUNT(`subcategory3_code`) FROM `library_subcategory3` WHERE `subcategory3_code`=?");
				pstm5.setString(1, successlist.get(i).getSubcategorytype3code());
				rs5 = pstm5.executeQuery();
				while(rs5.next()){
					if(rs5.getInt(1)==0){
						pstm=conn.prepareStatement(SQLUtilConstants.INSERT_SUB_CATEGORY_TYPE3);
						pstm.setString(1,uploaLibraryXSLVo.getCategorytypecode());
						pstm.setString(2, uploaLibraryXSLVo.getSubcategorytypecode());
						pstm.setString(3, uploaLibraryXSLVo.getSubcategorytype1code());
						pstm.setString(4, uploaLibraryXSLVo.getSubcategorytype2code());
						pstm.setString(5, uploaLibraryXSLVo.getSubcategorytype3code());
						pstm.setString(6, uploaLibraryXSLVo.getSubcategorytype3name());
						pstm.setString(7, uploaLibraryXSLVo.getSubcategory3description());
						pstm.setString(8, uploaLibraryXSLVo.getSubcategory3status());
						pstm.setString(9,uploaLibraryXSLVo.getCreated_by());
						pstm.setTimestamp(10,createdDate);
						pstm.setString(11,username);
						pstm.setTimestamp(12,createdDate);
						pstm.executeUpdate();
						conn.commit();
						pstm.close();
					}
				}
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}finally{

			try {
				if (rs1!= null && (!rs1.isClosed())) {

					rs1.close();
				}if (rs2!= null && (!rs2.isClosed())) {

					rs2.close();
				}if (rs3!= null && (!rs3.isClosed())) {

					rs3.close();
				}if (rs4!= null && (!rs4.isClosed())) {

					rs4.close();
				}
				if (rs5!= null && (!rs5.isClosed())) {

					rs5.close();
				}
				if (pstm1!= null && (!pstm1.isClosed())) {

					pstm1.close();
				}
				if (pstm2 != null && (!pstm2.isClosed())) {

					pstm2.close();
				}
				if (pstm3 != null && (!pstm3.isClosed())) {

					pstm3.close();
				}
				if (pstm4 != null && (!pstm4.isClosed())) {

					pstm4.close();
				}
				if (pstm5 != null && (!pstm5.isClosed())) {

					pstm5.close();
				}
				if(conn !=null && (!conn.isClosed())){
					conn.close();
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
				+ " Control in UploadEmpXSLDaoImpl : insertEmpXSL : Ending");


		return failurelistOnDiompl;
	}


	public String getStreamId(String category, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String steramId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_STREAM_ID);
			psmt.setString(1, category);
			rs = psmt.executeQuery();

			while (rs.next()) {
				steramId=rs.getString("classstream_id_int");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return steramId;
	}


	public String getClassId(String classname, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String classId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_CLASS_ID);
			psmt.setString(1, classname);
			rs = psmt.executeQuery();

			if (rs.next()) {
				classId=rs.getString("classdetail_id_int");
			}
			else{
				classId = "notfound";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return classId;
	}


	public String getSectionId(String sectionname, String classId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sectionId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_SECTION_ID);
			psmt.setString(1, sectionname);
			psmt.setString(2, classId);
			rs = psmt.executeQuery();

			while (rs.next()) {
				sectionId=rs.getString("classsection_id_int");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return sectionId;
	}


	public String getReligionId(String religion, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String religionId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_RELIGION_ID);
			psmt.setString(1, religion);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				religionId=rs.getString("religionId");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return religionId;
	}


	public String getSiblingId(String sibilingadminno, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String siblingId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_SIBLING_ID);
			psmt.setString(1, sibilingadminno);
			rs = psmt.executeQuery();

			while (rs.next()) {
				siblingId=rs.getString("student_id");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return siblingId;
	}


	public String getCasteId(String caste, String religionId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String casteId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_CASTE_ID);
			psmt.setString(1, caste);
			psmt.setString(2, religionId);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				casteId=rs.getString("casteId");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return casteId;
	}


	public String getTransportTypeId(String transcategory, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String trasportTypeId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_TRANSPORT_ID);
			psmt.setString(1, transcategory);
			rs = psmt.executeQuery();

			while (rs.next()) {
				trasportTypeId=rs.getString("type_id");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return trasportTypeId;
	}


	public String getTransportLocationId(String translocation,
			Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String transportLocationId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_TRANSPORT_LOCATION_ID);
			psmt.setString(1, translocation);
			rs = psmt.executeQuery();

			while (rs.next()) {
				transportLocationId=rs.getString("stage_id");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return transportLocationId;
	}


	public String getRouteId(String route, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String routeId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_ROUTE_ID);
			psmt.setString(1, route);
			System.out.println("Route Id: SQL::  "+psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				routeId=rs.getString("RouteCode");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return routeId;
	}


	public String getSpecilizationId(String specilization, String stream, String classId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String specilizationId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_SPECILIZATINON_ID);
			psmt.setString(1, specilization);
			psmt.setString(2, stream);
			psmt.setString(3, classId);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				specilizationId=rs.getString("Specialization_Id");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return specilizationId;
	}


	public String getCasteCategoryId(String casteCategory, String casteId, String religionId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String casteCategoryId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_CASTE_CATEGORY_ID);
			psmt.setString(1, casteCategory);
			psmt.setString(2, religionId);
			psmt.setString(3,casteId);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				casteCategoryId=rs.getString("castCatId");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return casteCategoryId;
	}


	public String getSchoolLocationId(String schoolName, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String schoolNameId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_SCHOOL_LOCATION_ID);
			psmt.setString(1, schoolName);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			if (rs.next()) {
				schoolNameId=rs.getString("Location_Id");
			}
			else{
				schoolNameId = "notfound";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return schoolNameId;
	}


	public String getAcademicYearId(String academicYear, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String academicYearId=null;

		try {

			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_ACADEMIC_YEAR_ID);
			psmt.setString(1, academicYear);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			if (rs.next()) {
				academicYearId=rs.getString("acadamic_id");
			}else{
				academicYearId = "notfound";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return academicYearId;
	}


	public int checkAdmissionDate(String academicYearId, String dateofJoin, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result=0;
		String startDate=null;
		String endDate=null;

		try {

			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_START_END_ACADEMIC_YEAR);
			psmt.setString(1, academicYearId);
			psmt.setString(2, HelperClass.convertUIToDatabase(dateofJoin));
			psmt.setString(3,  HelperClass.convertUIToDatabase(dateofJoin));
			System.out.println(psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
			}


		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return result;
	}


	public int checkClassByStream(String stream, String classId,Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result=0;


		try {

			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.CHECK_CLASS_BY_STREAM);
			psmt.setString(1, stream);
			psmt.setString(2, classId);

			System.out.println(psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
			}


		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return result;
	}


	public int checkSectionByClass(String classId, String sectionId,Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result=0;


		try {

			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.CHECK_SECTION_BY_CLASS);
			psmt.setString(1, classId);
			psmt.setString(2, sectionId);

			System.out.println(psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
			}


		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return result;
	}


	public int checkSpecilizationByStream(String stream, String classId,String specilizationId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result=0;


		try {

			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.CHECK_SPECILIZATION_BY_CLASS_STREAM);
			psmt.setString(1, specilizationId);
			psmt.setString(2, classId);
			psmt.setString(3, stream);

			System.out.println(psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
			}


		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return result;
	}


	public String getOccupationId(String occupation,Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String occupationId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_OCCUPATION_ID);
			psmt.setString(1, occupation);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				occupationId=rs.getString("occupationId");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return occupationId;
	}


	public String getHouseId(String housename, String schoolLocationId, String academicYearId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String occupationId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_HOUSE_ID);
			psmt.setString(1, housename);
			psmt.setString(2, schoolLocationId);
			psmt.setString(3, academicYearId);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				occupationId=rs.getString("house_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return occupationId;
	}


	public String getClassIdByLoc(String classId, Connection connection, String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String class_id = null;
		try {

			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_CLASS_ID_BY_LOC);
			psmt.setString(1, classId);
			psmt.setString(2, locationid);
			rs = psmt.executeQuery();

			if (rs.next()) {
				class_id=rs.getString("classdetail_id_int");
			}
			else{
				class_id = "notfound";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
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
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return class_id;
	}


	public Set<UploadLibraryXlsVO> insertsubscriberDetailXSL(List<UploadLibraryXlsPOJO> successlist,
			Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + "Control in UploadEmpXSLDaoImpl : insertEmpXSL : Starting");
	
		Set<UploadLibraryXlsVO> failurelistOnDiompl = new LinkedHashSet<UploadLibraryXlsVO>();
		
		Connection conn = null;
		PreparedStatement pstm=null,pstm1=null,pstm2=null,pstm3=null,pstm4=null,pstm5=null,pstm6=null;
		ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs4=null,rs5=null,rs6=null,rs7=null;
		String stuId="";
		String accId="",loc="",staffId="";
		IDGenerator id = new IDGenerator();
		String key=null;
		 int count=0,count1=0;
	
		try{
			 key = id.getPrimaryKeyID("campus_library_subscriber_details");
			System.out.println("DAOIMPL Is Working Library Excel file Upload");
			    conn = JDBCConnection.getSeparateConnection();
			    conn.setAutoCommit(false);
			    
			 for(int i=0;i<successlist.size();i++){
				 UploadLibraryXlsVO uploaLibraryXSLVo = new UploadLibraryXlsVO();
				    uploaLibraryXSLVo.setLocation(successlist.get(i).getLocation());
					 uploaLibraryXSLVo.setSubscriberType(successlist.get(i).getSubscriberType());
					 uploaLibraryXSLVo.setSubscriberNumber(successlist.get(i).getSubscriberNumber());
					 uploaLibraryXSLVo.setStaffID(successlist.get(i).getStaffID());
					 uploaLibraryXSLVo.setAdmissionNumber(successlist.get(i).getAdmissionNumber());
					 uploaLibraryXSLVo.setContactNumber(successlist.get(i).getContactNumber());
					 uploaLibraryXSLVo.setEmailID(successlist.get(i).getEmailID());
					 uploaLibraryXSLVo.setDepositType(successlist.get(i).getDepositType());
					 uploaLibraryXSLVo.setName(successlist.get(i).getName());
					 uploaLibraryXSLVo.setGender(successlist.get(i).getGender());
					 uploaLibraryXSLVo.setAmount(successlist.get(i).getAmount());
					 uploaLibraryXSLVo.setCradNo(successlist.get(i).getCradNo());
					 uploaLibraryXSLVo.setStatus(successlist.get(i).getStatus());
					 uploaLibraryXSLVo.setDate(successlist.get(i).getDate());
					 uploaLibraryXSLVo.setAddress(successlist.get(i).getAddress());
					 
					 System.out.println("Inside the DAOIMPL for loop");
					 
						 pstm =conn.prepareStatement("SELECT  library_loc_id FROM campus_library_location WHERE library_location_name=?");
							pstm.setString(1, successlist.get(i).getLocation());
							System.out.println("Location Validate"+pstm);
							 rs2 = pstm.executeQuery();
							 while(rs2.next()){
								 loc=rs2.getString(1);
							 }
					 
					 if(successlist.get(i).getSubscriberType().equalsIgnoreCase("student")){
						pstm1 =conn.prepareStatement("SELECT student_id_int,fms_acadamicyear_id_int FROM campus_student WHERE student_admissionno_var=?");
						pstm1.setString(1, successlist.get(i).getAdmissionNumber());
						System.out.println("Location and accyear Validate"+pstm1);
						 rs3 = pstm1.executeQuery();
						 while(rs3.next()){
							 stuId=rs3.getString(1);
							 accId=rs3.getString(2);
						 }
						 pstm =conn.prepareStatement("SELECT  count(subscriberNumber) FROM campus_library_subscriber_details WHERE subscriberNumber=?");
							pstm.setString(1, successlist.get(i).getSubscriberNumber());
							System.out.println("subscriberNumber Duplicate"+pstm);
							 rs4 = pstm.executeQuery();
							 while(rs4.next()){
								 count=rs4.getInt(1);
							 }
							 System.out.println("counting subscriber No"+count);
						 
						if(count <= 0 ){
						 System.out.println("INSIDE DAOIMPL-----------  Student");
						 pstm2=conn.prepareStatement(SQLUtilConstants.INSERT_STUDENT_DETAILS_IN_LIB_SUBSCRIBER_DETAILS);
						    pstm2.setString(1,loc);
							pstm2.setString(2,"student");
							pstm2.setString(3,uploaLibraryXSLVo.getSubscriberNumber());
							pstm2.setString(4,stuId);
							pstm2.setString(5,accId);
							pstm2.setString(6,uploaLibraryXSLVo.getDate());
							pstm2.setString(7,uploaLibraryXSLVo.getDepositType());
							pstm2.setString(8,uploaLibraryXSLVo.getAmount());
							pstm2.setString(9,uploaLibraryXSLVo.getCradNo());
							pstm2.setString(10,uploaLibraryXSLVo.getStatus());
							pstm2.setString(11,key);
							
							System.out.println("student...==="+pstm2);
							pstm2.executeUpdate();
							
							 conn.commit();
							 pstm2.close();
						}
					 }

					 else if(successlist.get(i).getSubscriberType().equalsIgnoreCase("staff") || successlist.get(i).getSubscriberType().equalsIgnoreCase("teacher"))
					 {
						 System.out.println("INSIDE DAOIMPL----------- Staff");
							 pstm6 =connection.prepareStatement("SELECT TeacherID,count(TeacherID) FROM campus_teachers WHERE registerId=?");
							 pstm6.setString(1, successlist.get(i).getAdmissionNumber());
								System.out.println("Inside the Teacher Validations and COUNT"+pstm6);
								 rs5 = pstm6.executeQuery();
								 while(rs5.next()){
									 staffId=rs5.getString(1);
									 count1=rs5.getInt(2);
								 }
								 pstm5 =conn.prepareStatement("SELECT  count(subscriberNumber) FROM campus_library_subscriber_details WHERE subscriberNumber=?");
									pstm5.setString(1, successlist.get(i).getSubscriberNumber());
									System.out.println("subscriberNumber Duplicate"+pstm);
									 rs6 = pstm5.executeQuery();
									 while(rs6.next()){
										 count=rs6.getInt(1);
									 }
								 if(count <= 0 && count1 >0){
						 pstm4=conn.prepareStatement(SQLUtilConstants.INSERT_STAFF_DETAILS_IN_LIB_SUBSCRIBER_DETAILS);
						 pstm4.setString(1,loc);
							pstm4.setString(2,"teacher");
							pstm4.setString(3,uploaLibraryXSLVo.getSubscriberNumber());
							pstm4.setString(4,staffId);
							pstm4.setString(5,uploaLibraryXSLVo.getDate());
							pstm4.setString(6,uploaLibraryXSLVo.getDepositType());
							pstm4.setString(7,uploaLibraryXSLVo.getAmount());
							pstm4.setString(8,uploaLibraryXSLVo.getCradNo());
							pstm4.setString(9,uploaLibraryXSLVo.getStatus());
							pstm4.setString(10,key);
							
							System.out.println("teacher..==="+pstm4);
							pstm4.executeUpdate();
							 conn.commit();
							 pstm4.close();
					 }
					}
                    else if(successlist.get(i).getSubscriberType().equalsIgnoreCase("others") || successlist.get(i).getSubscriberType().equalsIgnoreCase("other")){
                    	System.out.println("INSIDE DAOIMPL----------- Others");
                    	pstm4 =conn.prepareStatement("SELECT  count(subscriberNumber) FROM campus_library_subscriber_details WHERE subscriberNumber=?");
						pstm4.setString(1, successlist.get(i).getSubscriberNumber());
						System.out.println("subscriberNumber Duplicate"+pstm);
						 rs7 = pstm4.executeQuery();
						 while(rs7.next()){
							 count=rs7.getInt(1);
						 }
						 if(count<=0){
                    	    pstm5=conn.prepareStatement(SQLUtilConstants.INSERT_OTHERS_DETAILS_IN_LIB_SUBSCRIBER_DETAILS);
							pstm5.setString(1,"others");
							pstm5.setString(2,uploaLibraryXSLVo.getSubscriberNumber());
							pstm5.setString(3,uploaLibraryXSLVo.getName());
							pstm5.setString(4,uploaLibraryXSLVo.getGender());
							pstm5.setString(5,uploaLibraryXSLVo.getContactNumber());
							pstm5.setString(6,uploaLibraryXSLVo.getEmailID());
							pstm5.setString(7,uploaLibraryXSLVo.getAddress());
							pstm5.setString(8,uploaLibraryXSLVo.getDate());
							pstm5.setString(9,uploaLibraryXSLVo.getDepositType());
							pstm5.setString(10,uploaLibraryXSLVo.getAmount());
							pstm5.setString(11,uploaLibraryXSLVo.getCradNo());
							pstm5.setString(12,uploaLibraryXSLVo.getStatus());
							pstm5.setString(13,key);
							
							System.out.println("Others..==="+pstm5);
							pstm5.executeUpdate();
							 conn.commit();
							 pstm5.close();
                   }
                 }
			 }
		 }catch (SQLException sqle) {
		       	sqle.printStackTrace();
			   logger.error(sqle.getMessage(),sqle);
		    } 
          catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}finally{
			
			try {
				if (rs1!= null && (!rs1.isClosed())) {

					rs1.close();
				}if (rs2!= null && (!rs2.isClosed())) {

					rs2.close();
				}if (rs3!= null && (!rs3.isClosed())) {

					rs3.close();
				}
				if (rs4!= null && (!rs4.isClosed())) {

					rs4.close();
				}
				if (rs5!= null && (!rs5.isClosed())) {

					rs5.close();
				}
				if (rs6!= null && (!rs6.isClosed())) {

					rs6.close();
				}
				if (rs7!= null && (!rs7.isClosed())) {

					rs7.close();
				}
				if (pstm1!= null && (!pstm1.isClosed())) {

				pstm1.close();
				}
				if (pstm2 != null && (!pstm2.isClosed())) {

					pstm2.close();
				}
				if (pstm3 != null && (!pstm3.isClosed())) {

					pstm3.close();
				}
				if (pstm4 != null && (!pstm4.isClosed())) {

					pstm4.close();
				}
				if (pstm5 != null && (!pstm5.isClosed())) {

					pstm5.close();
				}
				if (pstm6!= null && (!pstm6.isClosed())) {
					pstm6.close();
				}
			if(conn !=null && (!conn.isClosed())){
				conn.close();
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
				+ " Control in UploadEmpXSLDaoImpl : insertEmpXSL : Ending");
		
		
		return failurelistOnDiompl;
	}
	


	public Set<StockEntryVo> insertStockEntryXSL(List<StockEntryVo> successlist, Connection connection, String username) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + "Control in UploadEmpXSLDaoImpl : insertEmpXSL : Starting");
		Set<StockEntryVo> failurelistOnDiompl = new LinkedHashSet<StockEntryVo>();
		Map<String, String> studentIDAdmissionNOMap = new HashMap<String, String>();
		IDGenerator id=new IDGenerator();
		Connection conn = null;
		PreparedStatement pstmt=null,pstmt1=null,pstmt2=null,pstmt3=null,pstmt4=null,pstmt5=null,pstmt6=null,pstmt7=null,pstmt8=null,pstmt9=null;
		ResultSet rs=null, rs1=null, rs2=null, rs3=null, rs4=null,rs5=null,rs6=null,rs7=null,rs8=null,rs9=null;
		try{
			System.out.println("DAOIMPL Is Working Library Excel file Upload");
			conn = JDBCConnection.getSeparateConnection();
			conn.setAutoCommit(false);
			for(int i=0; i<successlist.size(); i++){
				
				StockEntryVo StockEntryVo = new StockEntryVo();
				Timestamp createdDate = HelperClass.getCurrentTimestamp();

				StockEntryVo.setAccessionno(successlist.get(i).getAccessionno());
				StockEntryVo.setAuthor(successlist.get(i).getAuthor());
				StockEntryVo.setBilldate(successlist.get(i).getBilldate());
				StockEntryVo.setBillno(successlist.get(i).getBillno());
				StockEntryVo.setBooktitle(successlist.get(i).getBooktitle());
				StockEntryVo.setCategory(successlist.get(i).getCategory());
				StockEntryVo.setContentsearch(successlist.get(i).getContentsearch());
				StockEntryVo.setCostpercopy(successlist.get(i).getCostpercopy());
				StockEntryVo.setCurrentstatus(successlist.get(i).getCurrentstatus());
				StockEntryVo.setEdition(successlist.get(i).getEdition());
				StockEntryVo.setEditor(successlist.get(i).getEditor());
				StockEntryVo.setGeneralinfo(successlist.get(i).getGeneralinfo());
				StockEntryVo.setIsbnno(successlist.get(i).getIsbnno());
				StockEntryVo.setItemtype(successlist.get(i).getItemtype());
				StockEntryVo.setLanguage(successlist.get(i).getLanguage());
				StockEntryVo.setLibrarylocation(successlist.get(i).getLibrarylocation());
				StockEntryVo.setNoofcopies(successlist.get(i).getNoofcopies());
				StockEntryVo.setPublicationyear(successlist.get(i).getPublicationyear());
				StockEntryVo.setPublisher(successlist.get(i).getPublisher());
				StockEntryVo.setRegisterdDate(successlist.get(i).getRegisterdDate());
				StockEntryVo.setShelfno(successlist.get(i).getShelfno());
				StockEntryVo.setSize(successlist.get(i).getSize());
				StockEntryVo.setSubcategorytype1(successlist.get(i).getSubcategorytype1());
				StockEntryVo.setSubcategorytype2(successlist.get(i).getSubcategorytype2());
				StockEntryVo.setSubCtegory(successlist.get(i).getSubCtegory());
				StockEntryVo.setSuppliedby(successlist.get(i).getSuppliedby());
				StockEntryVo.setTotalcost(successlist.get(i).getTotalcost());
				StockEntryVo.setNoofpages(successlist.get(i).getNoofpages());
				int count=0;
                pstmt5=conn.prepareStatement("SELECT COUNT(`Accession_number`) FROM `campus_library_stock_entry` WHERE `Accession_number`=?");
                pstmt5.setString(1, StockEntryVo.getAccessionno());
                rs5=pstmt5.executeQuery();
                while(rs5.next()){
                	count=rs5.getInt(1);
                }
                String Category=null;
                String SubCategory=null;
                String SubCategoryType1=null;
                String SubCategoryType2=null;
                String LibraryLocation=null;
                String supplier=null;
                String publisher=null;
                
                if(count==0){
				pstmt = conn.prepareStatement(LibrarySqlUtils.STOCK_ENTRY_EXCEL_SAVE);
				pstmt.setString(1, id.getPrimaryKeyID("campus_library_stock_entry"));
				pstmt.setString(2,StockEntryVo.getAccessionno());
				pstmt.setString(3,  StockEntryVo.getItemtype());
				pstmt.setString(4, HelperClass.convertUIToDatabase(StockEntryVo.getRegisterdDate()));
				pstmt.setString(5,  StockEntryVo.getBooktitle());
				pstmt.setString(6,  StockEntryVo.getAuthor());
	     pstmt1=conn.prepareStatement("SELECT `category_code` FROM `library_category` WHERE `category_name`=?");
	     pstmt1.setString(1,StockEntryVo.getCategory());
	     rs1=pstmt1.executeQuery();
	     while(rs1.next()){
	    	 Category=rs1.getString(1);
	     }
	     pstmt2=conn.prepareStatement("SELECT `subcategory_code` FROM `library_subcategory` WHERE `subcategory_name`=?");
	     pstmt2.setString(1,StockEntryVo.getSubCtegory());
	     rs2=pstmt2.executeQuery();
	     while(rs2.next()){
	    	 SubCategory=rs2.getString(1);
	     }	    
	     pstmt3=conn.prepareStatement("SELECT `subcategory1_code` FROM `library_subcategory1` WHERE `subcategory1_name`=?");
	     pstmt3.setString(1,StockEntryVo.getSubcategorytype1());
	     rs3=pstmt3.executeQuery();
	     while(rs3.next()){
	    	 SubCategoryType1=rs3.getString(1);
	     }	  
	     pstmt4=conn.prepareStatement("SELECT `subcategory2_code` FROM `library_subcategory2` WHERE `subcategory2_name`=?");
	     pstmt4.setString(1,StockEntryVo.getSubcategorytype2());
	     rs4=pstmt4.executeQuery();
	     while(rs4.next()){
	    	 SubCategoryType2=rs4.getString(1);
	     }
	     String DDC=Category+":"+SubCategory+":"+SubCategoryType1+":"+SubCategoryType2;
	     
	     pstmt7=conn.prepareStatement("SELECT `Entry_Id` FROM `campus_library_supplier_settings` WHERE `Supplier_Name`=?");
	     pstmt7.setString(1,StockEntryVo.getSuppliedby());
	     rs7=pstmt7.executeQuery();
	     while(rs7.next()){
	    	 supplier=rs7.getString(1);
	     }
	     pstmt8=conn.prepareStatement("SELECT`Entry_id` FROM `campus_library_publisher_settings` WHERE `Publisher_Name`=?");
	     pstmt8.setString(1,StockEntryVo.getPublisher());
	     rs8=pstmt8.executeQuery();
	     while(rs8.next()){
	    	 publisher=rs8.getString(1);
	     }
	    /* pstmt9=conn.prepareStatement("SELECT `Entry_Id` FROM `campus_library_supplier_settings` WHERE `Supplier_Name`=?");
	     pstmt9.setString(1,StockEntryVo.getSuppliedby());
	     rs9=pstmt9.executeQuery();
	     while(rs9.next()){
	    	 supplier=rs9.getString(1);
	     }*/
	     pstmt6=conn.prepareStatement("SELECT `library_loc_id`  FROM `campus_library_location` WHERE `library_location_name`=?");
	     pstmt6.setString(1,StockEntryVo.getLibrarylocation());
	     rs6=pstmt6.executeQuery();
	     while(rs6.next()){
	    	 LibraryLocation=rs6.getString(1);
	     }
				pstmt.setString(7,Category);
				pstmt.setString(8,SubCategory);
				pstmt.setString(9,SubCategoryType1);
				pstmt.setString(10,StockEntryVo.getLanguage());
				pstmt.setString(11,SubCategoryType2);
				pstmt.setString(12,DDC);
				pstmt.setString(13,publisher);
				pstmt.setString(14,StockEntryVo.getNoofcopies());
				pstmt.setString(15,StockEntryVo.getEdition());
				pstmt.setString(16,StockEntryVo.getIsbnno());
				pstmt.setString(17,StockEntryVo.getBillno());
				pstmt.setString(18,StockEntryVo.getSize());
				pstmt.setString(19,supplier);
				pstmt.setString(20,StockEntryVo.getGeneralinfo());
				pstmt.setString(21,StockEntryVo.getPublicationyear());
				pstmt.setString(22,StockEntryVo.getEditor());
				pstmt.setString(23,StockEntryVo.getTotalcost());
				pstmt.setString(24,HelperClass.convertUIToDatabase(StockEntryVo.getBilldate()));
				pstmt.setString(25,StockEntryVo.getCostpercopy());
				pstmt.setString(26,StockEntryVo.getNoofpages());
				pstmt.setString(27,StockEntryVo.getContentsearch());
				pstmt.setString(28,StockEntryVo.getShelfno());
				pstmt.setString(29,LibraryLocation);
				pstmt.setString(30,StockEntryVo.getCurrentstatus());
				pstmt.setString(31,username);
				pstmt.executeUpdate();
				conn.commit();
                }
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}finally{
			
			try {
				if(rs6!=null){
					rs6.close();
				}if(rs5!=null){
					rs5.close();
				}if(rs4!=null){
					rs4.close();
				}if(rs3!=null){
					rs3.close();
				}if(rs2!=null){
					rs2.close();
				}if(rs!=null){
					rs.close();
				}if(pstmt!=null){
					pstmt.close();}
				if(pstmt1!=null){
					pstmt1.close();
				}if(pstmt2!=null){
					pstmt2.close();
				}if(pstmt3!=null){
					pstmt3.close();
				}if(pstmt4!=null){
					pstmt4.close();
				}if(pstmt5!=null){
					pstmt5.close();
				}if(pstmt6!=null){
					pstmt6.close();
				}if(conn!=null){
					conn.close();
				}
			 }catch (SQLException e) {
					e.printStackTrace();
				}
			}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : insertEmpXSL : Ending");
		return failurelistOnDiompl;
	}
}
