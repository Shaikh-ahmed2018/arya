package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.LibraryDAO;
import com.centris.campus.forms.LibraryStockEntryDetailsForm;
import com.centris.campus.pojo.LibraryLocationPojo;
import com.centris.campus.pojo.LibrarySubsciberDetailsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LibrarySqlUtils;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.vo.CategoryTypeVO;
import com.centris.campus.vo.LibraryJournalSubscriptionVo;
import com.centris.campus.vo.LibraryLocationVO;
import com.centris.campus.vo.LibrarySearchIssueDetailsVO;
import com.centris.campus.vo.LibrarySearchSubscriberVO;
import com.centris.campus.vo.LibraryStockEntryVO;
import com.centris.campus.vo.LibrarySubscribVO;
import com.centris.campus.vo.LibraryVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.SubCategoryType1VO;
import com.centris.campus.vo.SubCategoryType2VO;
import com.centris.campus.vo.SubCategoryTypeVO;
import com.centris.campus.vo.TeacherVo;
import com.centris.campus.vo.eventRegVo;


public class LibraryDAOIMPL implements LibraryDAO {

	private static final Logger logger = Logger.getLogger(LibraryDAOIMPL.class);
	 static ArrayList<LibrarySearchIssueDetailsVO> jobCandidate = new ArrayList<LibrarySearchIssueDetailsVO>();     
	@Override
	public String insertCategoryTypeDetail(CategoryTypeVO insert_categoryType) {

		PreparedStatement pstmt = null;
		Connection con = null;
		String status = null;
		int count = 0;
		String code = insert_categoryType.getCategorytypecode();
		String hiddencode = insert_categoryType.getHiddencode();
		try {
			con = JDBCConnection.getSeparateConnection();
			System.out.println(insert_categoryType.getHidden_category_id());
			if (!insert_categoryType.getHidden_category_id().equals("")) {

				if (!code.equals(hiddencode)) {

					count = validateCategoryType(insert_categoryType);
					if (count == 1) {
						status = "Catagory Type Code is Already Exist";
						System.out
						.println("Catagory type code is already exist");

					} else {

						pstmt = con
								.prepareStatement(SQLUtilConstants.UPDATE_CATEGORY_TYPE);
						pstmt.setString(1, insert_categoryType
								.getCategorytypecode().trim());
						pstmt.setString(2, insert_categoryType
								.getCategorytypename().trim());
						pstmt.setString(3, insert_categoryType.getDescription()
								.trim());
						pstmt.setString(4, insert_categoryType.getStatus());
						pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
						pstmt.setString(6, insert_categoryType.getCreated_by());
						pstmt.setString(7,
								insert_categoryType.getHidden_category_id());
						System.out.println(pstmt);
						count = pstmt.executeUpdate();

						if (count > 0) {
							status = "Updating Record Progressing...";
						} else {
							status = "Failed To Update The Record";
						}
					}
				} else {

					pstmt = con
							.prepareStatement(SQLUtilConstants.UPDATE_CATEGORY_TYPE);
					pstmt.setString(1, insert_categoryType
							.getCategorytypecode().trim());
					pstmt.setString(2, insert_categoryType
							.getCategorytypename().trim());
					pstmt.setString(3, insert_categoryType.getDescription()
							.trim());
					pstmt.setString(4, insert_categoryType.getStatus());
					pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
					pstmt.setString(6, insert_categoryType.getCreated_by());
					pstmt.setString(7,
							insert_categoryType.getHidden_category_id());
					System.out.println(pstmt);
					count = pstmt.executeUpdate();

					if (count > 0) {
						status = "Updating Record Progressing...";
					} else {
						status = "Failed To Update The Record";
					}
				}
			} else {
				if (!code.equals(hiddencode)) {

					count = validateCategoryType(insert_categoryType);
					if (count == 1) {
						status = "Catagory Type Code is Already Exist";
						System.out
						.println("Catagory type code is already exist");

					} else {
						pstmt = con
								.prepareStatement(SQLUtilConstants.INSERT_CATEGORY_TYPE);
						pstmt.setString(1,
								insert_categoryType.getCategorytypecode());
						pstmt.setString(2, insert_categoryType
								.getCategorytypename().trim());
						pstmt.setString(3, insert_categoryType.getDescription()
								.trim());
						pstmt.setString(4, insert_categoryType.getStatus());
						pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
						pstmt.setString(6, insert_categoryType.getCreated_by());

						count = pstmt.executeUpdate();

						if (count > 0) {
							status = "Adding Record Progressing...";
						} else {

							status = "Failed To Add The Record";
						}

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return status;
	}

	@Override
	public List<CategoryTypeVO> getCategoryDetails() {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con.prepareStatement(SQLUtilConstants.CATEGORY_DETAILS);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CategoryTypeVO categorylist = new CategoryTypeVO();
				categorylist.setCategory_id(rs.getString("category_id"));
				categorylist.setCategorytypecode(rs.getString("category_code"));
				categorylist.setCategorytypename(rs.getString("category_name"));
				categorylist.setDescription(rs.getString("description"));
				categorylist.setStatus(rs.getString("status"));
				list.add(categorylist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public List<CategoryTypeVO> getSubCategoryDetails() {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con.prepareStatement(SQLUtilConstants.SUB_CATEGORY_DETAILS);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CategoryTypeVO categorylist = new CategoryTypeVO();

				// sub.`subcategory_id`,cat.`category_name`,
				// sub.`category_code`,
				// sub.`subcategory_code`,sub.`subcategory_name`,sub.`description`,sub.`status`
				categorylist.setSubcategoryid(rs.getString("subcategory_id"));
				categorylist.setCategory_id(rs.getString("category_code"));/* subcategory_id */
				categorylist.setCategoryname(rs.getString("category_name"));
				categorylist.setCategorytypecode(rs.getString("subcategory_code"));
				categorylist.setCategorytypename(rs.getString("subcategory_name"));
				categorylist.setDescription(rs.getString("description"));
				categorylist.setStatus(rs.getString("status"));
				list.add(categorylist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public List<StudentRegistrationVo> studentSearchbyadmissionNo(
			StudentRegistrationVo registrationVo, String locid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionDaoImpl : studentSearchbyadmissionNo Starting");
		String searchTerm = registrationVo.getSearchTerm() + "%";
		List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			if (registrationVo.getLocationId().equalsIgnoreCase("all")) {
				registrationVo.setLocationId("%%");
			}

			conn = JDBCConnection.getSeparateConnection();
			pstmObj = conn
					.prepareStatement(LibrarySqlUtils.STUDENT_SEARCH_BY_ADMISSION);

			pstmObj.setString(1, searchTerm);
			/*pstmObj.setString(2, searchTerm);
			pstmObj.setString(3, searchTerm);
			pstmObj.setString(4, searchTerm);
			pstmObj.setString(5, searchTerm);*/
			//pstmObj.setString(6, registrationVo.getAcademicYear());

			System.out.println("admission No query is " + pstmObj);

			rs = pstmObj.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setSubscriberNumber(rs.getString("subscriberNumber")+" - "+rs.getString("student")); 
				studentRegistrationVo.setStudentId(rs.getString("student_id_int")+"-"+rs.getString("subscriberId"));

				studentRegistrationVo.setSubscriberId(rs.getString("subscriberId"));
				registrationList.add(studentRegistrationVo);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in ElectionDaoImpl : studentSearchbyadmissionNo Ending");

		return registrationList;
	}

	@Override
	public List<TeacherVo> teacherSearchbyId(TeacherVo teacherVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getAllTeacherDetails Starting");

		ArrayList<TeacherVo> getTeacherDetails = new ArrayList<TeacherVo>();
		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			if (teacherVo.getLocid().equalsIgnoreCase("all")) {
				teacherVo.setLocid("%%");
			}
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(LibrarySqlUtils.TEACHER_SEARCH_BY_ADMISSION);
			pstmt.setString(1, "%" + teacherVo.getSearchTerm() + "%");
			pstmt.setString(2, "%" + teacherVo.getSearchTerm() + "%");
			pstmt.setString(3, "%" + teacherVo.getSearchTerm() + "%");
			pstmt.setString(4, "%" + teacherVo.getSearchTerm() + "%");
			System.out.println(" query is " + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TeacherVo teachervo = new TeacherVo();
				teachervo.setSubscriberNumber(rs.getString("subscriberNumber")+ " - "+ rs.getString("teacher"));
				teachervo.setTeacherId(rs.getString("TeacherID")+"-"+rs.getString("subscriberId"));

				getTeacherDetails.add(teachervo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
				+ " Control in TeacherDaoImpl : getAllTeacherDetails Ending");
		return getTeacherDetails;

	}

	@Override
	public CategoryTypeVO editCategoryType(String id) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		CategoryTypeVO catVO = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_CATEGORY_TYPE);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				catVO = new CategoryTypeVO();
				catVO.setCategory_id(rs.getString("category_id"));
				catVO.setCategorytypecode(rs.getString("category_code"));
				catVO.setCategorytypename(rs.getString("category_name"));
				catVO.setStatus(rs.getString("status"));
				catVO.setDescription(rs.getString("description"));
				catVO.setHiddencode(rs.getString("category_code"));

			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
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
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return catVO;

	}

	@Override
	public String inactiveCategoryType(String[] catIdlist) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");
		PreparedStatement pstmt0 = null;
		ResultSet rs0 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		Connection conn = null;
		int count = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		String category_code = null;
		String status = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			for(int i=0; i<catIdlist.length;  i++){
				pstmt0 = conn.prepareStatement("SELECT * FROM library_category WHERE category_id=?");
				pstmt0.setString(1, catIdlist[i]);
				rs0 = pstmt0.executeQuery();
				while (rs0.next()) {
					category_code = rs0.getString("category_code");
				}

				pstmt = conn.prepareStatement(SQLUtilConstants.INACTIVE_CATEGORY_TYPE);
				pstmt.setString(1, catIdlist[i]);
				count = pstmt.executeUpdate();
				System.out.println("CATEGORY "+pstmt);
				if (count > 0) {

					pstmt1 = conn
							.prepareStatement(SQLUtilConstants.INACTIVE_SUB_CATEGORY_TYPE);
					pstmt1.setString(1, category_code);
					count1 = pstmt1.executeUpdate();
					System.out.println("CATEGORY 1"+pstmt1);
					if (count1 > 0) {
						pstmt2 = conn
								.prepareStatement(SQLUtilConstants.INACTIVE_SUB1_CATEGORY_TYPE);
						pstmt2.setString(1, category_code);
						count2 = pstmt2.executeUpdate();
						System.out.println("CATEGORY 2"+pstmt2);
						if (count2 > 0) {
							pstmt3 = conn
									.prepareStatement(SQLUtilConstants.INACTIVE_SUB2_CATEGORY_TYPE);
							pstmt3.setString(1, category_code);
							count3 = pstmt3.executeUpdate();
							System.out.println("CATEGORY 3"+pstmt3);
							if (count3 > 0) {
								pstmt4 = conn.prepareStatement(SQLUtilConstants.INACTIVE_SUB3_CATEGORY_TYPE);
								pstmt4.setString(1, category_code);
								count4 = pstmt4.executeUpdate();
								System.out.println("CATEGORY 4"+pstmt4);
								if (count4 > 0) {

									status = "Record Inactivated";
								} else {
									status = "Failed To Add The Record";
								}
							} else {
								status = "Record Inactivated";
							}
						} else {
							status = "Record Inactivated";
						}
					} else {
						status = "Record Inactivated";
					}
				} else {

					status = "Failed To Add The Record";
				}
			}
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs0 != null && (!rs0.isClosed())) {
					rs0.close();
				}
				if (pstmt0 != null && (!pstmt0.isClosed())) {
					pstmt0.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmt3 != null && (!pstmt3.isClosed())) {
					pstmt3.close();
				}
				if (pstmt4 != null && (!pstmt4.isClosed())) {
					pstmt4.close();
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
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return status;
	}

	@Override
	public String insertLibraryLocations(LibraryLocationPojo insert_libLoc) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: insertLibraryLocations : Starting");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.INSERT_LIBRARY_LOCATIONS);
			pstmt.setString(1,IDGenerator.getPrimaryKeyID("campus_library_location"));
			pstmt.setString(2, insert_libLoc.getSchoolName());
			pstmt.setString(3, insert_libLoc.getLibraryLocations());
			pstmt.setString(4, insert_libLoc.getDescription());
			pstmt.setString(5, insert_libLoc.getCurrentuserid());
			count = pstmt.executeUpdate();
System.out.println("INSERT LIB "+pstmt);
			if (count > 0) {
				result = "success";
			} else {
				result = "fail";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<LibraryLocationPojo> getLibLocationsDetails(String location) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<LibraryLocationPojo> list = new ArrayList<LibraryLocationPojo>();
		try {
			if(location.equalsIgnoreCase("all")){
				location = "%%";
			}
			con = JDBCConnection.getSeparateConnection();
			pstmt = con.prepareStatement(LibrarySqlUtils.GET_LIBRARY_LOCATIONS);
			pstmt.setString(1, location);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibraryLocationPojo categorylist = new LibraryLocationPojo();
				categorylist.setLibrarylocid(rs.getString("library_loc_id"));
				categorylist.setLocationid(rs.getString("loc_id"));
				categorylist.setSchoolName(rs.getString("Location_Name"));
				categorylist.setLibraryLocations(rs.getString("library_location_name"));
				categorylist.setDescription(rs.getString("Description"));
				list.add(categorylist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public String insertSubCategoryType1Detail(SubCategoryType1VO sub1) {

		PreparedStatement pstmt = null;
		Connection con = null;
		String status = null;
		int count = 0;
		try {
			con = JDBCConnection.getSeparateConnection();
			System.out.println("sub1.getHidden_subcategory1_id() "
					+ sub1.getHidden_subcategory1_id());
			if (!sub1.getHidden_subcategory1_id().equals("")) {

				String id = sub1.getSubcategorytype1code();
				System.out.println("id is " + id);
				String Hid = sub1.getHiddenid();
				System.out.println("Hid is " + Hid);
				boolean countid = false;

				if (!id.equalsIgnoreCase(Hid)) {
					System.out.println("Validating--------------");
					countid = validateSubcategoryType1(sub1);
					System.out.println("countid is " + countid);

					if (countid == false) {
						status = "Sub Catagory Type 1 Code is Already Exist";
						System.out
						.println("Sub catagory type 1 code is already exist");
					} else {
						pstmt = con
								.prepareStatement(LibrarySqlUtils.UPDATE_SUB_CATEGORY_TYPE_1);
						pstmt.setString(1, sub1.getCategorytypecode());
						pstmt.setString(2, sub1.getSubcategorytypecode());
						pstmt.setString(3, sub1.getSubcategorytype1code());
						pstmt.setString(4, sub1.getSubcategorytype1name());
						pstmt.setString(5, sub1.getDescription());
						pstmt.setString(6, sub1.getStatus());
						pstmt.setTimestamp(7, HelperClass.getCurrentTimestamp());
						pstmt.setString(8, sub1.getCreated_by());
						pstmt.setString(9, sub1.getHidden_subcategory1_id());
						System.out.println(pstmt);
						count = pstmt.executeUpdate();

						if (count > 0) {
							status = "Updating Record Progressing...";
						} else {
							status = "Failed To Update The Record";
						}
					}
				} else {
					System.out.println("DIRECTLY TO ELSE STATEMENT");

					pstmt = con
							.prepareStatement(LibrarySqlUtils.UPDATE_SUB_CATEGORY_TYPE_1);
					pstmt.setString(1, sub1.getCategorytypecode());
					pstmt.setString(2, sub1.getSubcategorytypecode());
					pstmt.setString(3, sub1.getSubcategorytype1code());
					pstmt.setString(4, sub1.getSubcategorytype1name());
					pstmt.setString(5, sub1.getDescription());
					pstmt.setString(6, sub1.getStatus());
					pstmt.setTimestamp(7, HelperClass.getCurrentTimestamp());
					pstmt.setString(8, sub1.getCreated_by());
					pstmt.setString(9, sub1.getHidden_subcategory1_id());
					System.out.println(pstmt);
					count = pstmt.executeUpdate();

					if (count > 0) {
						status = "Updating Record Progressing...";
					} else {
						status = "Failed To Update The Record";
					}
				}
			} else {
				boolean countid = false;
				System.out.println("sub1.getSubcategorytype1code() "
						+ sub1.getSubcategorytype1code());
				if (sub1.getSubcategorytype1code() != null) {
					countid = validateSubcategoryType1(sub1);
					System.out.println("countid is " + countid);
					if (countid == true) {
						status = "Sub Catagory Type 1 Code is Already Exist";
					} else {
						pstmt = con
								.prepareStatement(SQLUtilConstants.INSERT_SUB_CATEGORY_TYPE_1);
						pstmt.setString(1, sub1.getCategorytypecode());
						pstmt.setString(2, sub1.getSubcategorytypecode());
						pstmt.setString(3, sub1.getSubcategorytype1code());
						pstmt.setString(4, sub1.getSubcategorytype1name());
						pstmt.setString(5, sub1.getDescription());
						pstmt.setString(6, sub1.getStatus());
						pstmt.setTimestamp(7, HelperClass.getCurrentTimestamp());
						pstmt.setString(8, sub1.getCreated_by());

						count = pstmt.executeUpdate();

						if (count > 0) {
							status = "Adding Record Progressing...";
						} else {

							status = "Failed To Add The Record";
						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return status;
	}

	@Override
	public List<SubCategoryType1VO> getSubCategoryType1Details() {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<SubCategoryType1VO> list = new ArrayList<SubCategoryType1VO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con
					.prepareStatement(SQLUtilConstants.CATEGORY_TYPE_1_DETAILS);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SubCategoryType1VO subcategorytype1list = new SubCategoryType1VO();
				subcategorytype1list.setCategorytypecode(rs
						.getString("category_code"));
				subcategorytype1list.setCategorytypename(rs
						.getString("category_name"));
				subcategorytype1list.setSubcategorytypecode(rs
						.getString("subcategory_code"));
				subcategorytype1list.setSubcategorytypename(rs
						.getString("subcategory_name"));
				subcategorytype1list.setSubcategorytypecode(rs
						.getString("subcategory1_code"));
				subcategorytype1list.setSubcategorytype1_id(rs
						.getString("subcategory1_id"));
				subcategorytype1list.setSubcategorytype1code(rs
						.getString("subcategory1_code"));
				subcategorytype1list.setSubcategorytype1name(rs
						.getString("subcategory1_name"));
				subcategorytype1list.setStatus(rs.getString("status"));
				subcategorytype1list
				.setDescription(rs.getString("description"));

				list.add(subcategorytype1list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public SubCategoryType1VO editSubCategoryType1(String id) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		SubCategoryType1VO catVO = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.EDIT_SUB_CATEGORY_TYPE_1);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				catVO = new SubCategoryType1VO();
				catVO.setSubcategorytype1_id(rs.getString("subcategory1_id"));
				catVO.setCategorytypecode(rs.getString("category_code"));
				catVO.setSubcategorytypecode(rs.getString("subcategory_code"));
				catVO.setSubcategorytype1code(rs.getString("subcategory1_code"));
				catVO.setSubcategorytype1name(rs.getString("subcategory1_name"));
				catVO.setStatus(rs.getString("status"));
				catVO.setDescription(rs.getString("description"));

			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
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
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return catVO;

	}

	@Override
	public List<SubCategoryTypeVO> getSubCategoryByCategory(String categoryCode) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<SubCategoryTypeVO> list = new ArrayList<SubCategoryTypeVO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con
					.prepareStatement(SQLUtilConstants.GET_CATEGORY_TYPE_DETAILS);
			pstmt.setString(1, categoryCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SubCategoryTypeVO subcategorytypelist = new SubCategoryTypeVO();
				subcategorytypelist.setSubcategorytypecode(rs
						.getString("subcategory_code"));
				subcategorytypelist.setSubcategorytypename(rs
						.getString("subcategory_name"));
				list.add(subcategorytypelist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getStudentIssuedList(
			String locid, String accyear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: getStudentIssuedList : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		ArrayList<LibrarySearchIssueDetailsVO> stulist = new ArrayList<LibrarySearchIssueDetailsVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(LibrarySqlUtils.GET_ISSUED_STUDENT_LIST);
			pstmt.setString(1, accyear);
			pstmt.setString(2, locid);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibrarySearchIssueDetailsVO vo = new LibrarySearchIssueDetailsVO();
				count++;
				vo.setSlno(count);
				/* vo.setStuid(rs.getString("student_id_int")); */
				vo.setStuname(rs.getString("student"));
				vo.setStuSubNo(rs.getString("subscriberNumber"));
				vo.setRollno(rs.getString("student_rollno"));
				vo.setStandard(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setBookname(rs.getString("book_title"));
				vo.setBookauthor(rs.getString("author"));
				vo.setIssuedate( HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
				vo.setStatus(rs.getString("status"));
				stulist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: getStudentIssuedList  : Ending");
		return stulist;
	}

	/*public String saveSubscriberDetails(LibrarySubsciberDetailsPojo pojo) {
		Connection conn = null;
		PreparedStatement psmt,psmt1,psmt2 = null;
		ResultSet rs = null;
		IDGenerator id = new IDGenerator();
		String key = null;
		int count1 =0;
		ResultSet rs1= null;
		String result= null;
		int count2= 0;
		int count3= 0;
		try {

			conn = JDBCConnection.getSeparateConnection();
			psmt1=conn.prepareStatement("SELECT COUNT(`subscriberNumber`) FROM `campus_library_subscriber_details` WHERE `subscriberNumber`=? AND `accyear_ID`=? AND `loc_ID`=?");
			psmt1.setString(1, pojo.getSubscriberNo());
			psmt1.setString(2, pojo.getAccId());
			psmt1.setString(3, pojo.getLocationName());
			rs1=psmt1.executeQuery();
			while(rs1.next()){
				count2=rs1.getInt(1);
			}
			if(count2==0){
			psmt2=conn.prepareStatement("SELECT COUNT(`staffId`) FROM `campus_library_subscriber_details` WHERE `staffId`=? AND `accyear_ID`=? AND `loc_ID`=? AND `subscriberType`='teacher' ");
			psmt2.setString(1, pojo.getStaffid());
			psmt2.setString(2, pojo.getAccId());
			psmt2.setString(3, pojo.getLocationName());
			System.out.println(psmt2);
			rs=psmt2.executeQuery();
			while(rs.next()){
				count3=rs.getInt(1);
			}
			if(count3!=0){	
				result="dupStaff";
			}else{
			key = id.getPrimaryKeyID("campus_library_subscriber_details");
			psmt = conn.prepareStatement(LibrarySqlUtils.saveSubscriberDetails);
			psmt.setString(1, key);
			psmt.setString(2, pojo.getSuscriberType());
			psmt.setString(3, pojo.getSubscriberNo());

			psmt.setString(4, pojo.getDepositType());
			psmt.setString(5, pojo.getEnterAmount());
			psmt.setString(6, pojo.getCardNo());
			psmt.setString(7, pojo.getCheckNo());
			psmt.setString(8, pojo.getPaymentDate());

			psmt.setString(9, pojo.getOtherUserName());
			psmt.setString(10, pojo.getOtherUserGender());
			psmt.setString(11, pojo.getOtherUserContact());
			psmt.setString(12, pojo.getOtherUserAddr());
			psmt.setString(13, pojo.getOtherUserEmail());

			psmt.setString(14, pojo.getStudentIdHidden());
			psmt.setString(15, pojo.getClassId());
			psmt.setString(16, pojo.getSectionId());

			psmt.setString(17, pojo.getStaffid());
			psmt.setString(18, pojo.getDeptId());
			psmt.setString(19, pojo.getDesgId());
			psmt.setString(20, pojo.getStatus());
			psmt.setString(21, pojo.getLocationName());
			psmt.setString(22, pojo.getAccId());

			System.out.println(psmt);
			count1 = psmt.executeUpdate();

			if (count1 > 0) {
				result = "true";
			} else {
				result = "false";
			}
			}
			}else{
				result="dupSubNo";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/

	public String saveSubscriberDetails(LibrarySubsciberDetailsPojo pojo) {
		Connection conn = null;
		PreparedStatement psmt=null,psmt1= null;
		IDGenerator id = new IDGenerator();
		String key = null;
		int count1 =0;
		ResultSet rs1= null;
		String result= null;
		int count2= 0;
		try {

			conn = JDBCConnection.getSeparateConnection();
			
			psmt1=conn.prepareStatement("SELECT COUNT(`subscriberNumber`) FROM `campus_library_subscriber_details` WHERE `subscriberNumber`=? AND `accyear_ID`=? AND `loc_ID`=?");
			psmt1.setString(1, pojo.getSubscriberNo());
			psmt1.setString(2, pojo.getAccId());
			psmt1.setString(3, pojo.getLocationName());
			rs1=psmt1.executeQuery();
			while(rs1.next()){
				count2=rs1.getInt(1);
			}
			if(count2==0){
				key = id.getPrimaryKeyID("campus_library_subscriber_details");
				psmt = conn.prepareStatement(LibrarySqlUtils.saveSubscriberDetails);
				psmt.setString(1, key);
				psmt.setString(2, pojo.getSuscriberType());
				psmt.setString(3, pojo.getSubscriberNo());

				psmt.setString(4, pojo.getDepositType());
				psmt.setString(5, pojo.getEnterAmount());
				psmt.setString(6, pojo.getCardNo());
				psmt.setString(7, pojo.getCheckNo());
				psmt.setString(8, pojo.getPaymentDate());

				psmt.setString(9, pojo.getOtherUserName());
				psmt.setString(10, pojo.getOtherUserGender());
				psmt.setString(11, pojo.getOtherUserContact());
				psmt.setString(12, pojo.getOtherUserAddr());
				psmt.setString(13, pojo.getOtherUserEmail());

				psmt.setString(14, pojo.getStudentIdHidden());
				psmt.setString(15, pojo.getClassId());
				psmt.setString(16, pojo.getSectionId());

				psmt.setString(17, pojo.getStaffid());
				psmt.setString(18, pojo.getDeptId());
				psmt.setString(19, pojo.getDesgId());
				psmt.setString(20, pojo.getStatus());
				psmt.setString(21, pojo.getLocationName());
				psmt.setString(22, pojo.getAccId());
				psmt.setString(23, pojo.getCardNoAmount());
				psmt.setString(24, pojo.getCheckNoAmount());

				System.out.println(psmt);
				count1 = psmt.executeUpdate();

				if (count1 > 0) {
					result = "true";
				} else {
					result = "false";
				}
			}else{
				result="dupSubNo";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {

			try {
				if (rs1 != null && !rs1.isClosed()) {
					rs1.close();
				}
				if (psmt != null && !psmt.isClosed()) {
					psmt.close();
				}
				if (psmt1 != null && !psmt1.isClosed()) {
					psmt1.close();
				}
				
				if (conn != null && !conn.isClosed()) {

					conn.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		return result;
	}
	public ArrayList<LibrarySubscribVO> getStudentData(String academicYear,
			String admissionNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySubscribVO> studentData = new ArrayList<LibrarySubscribVO>();

		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(LibrarySqlUtils.studentInfo);

			pstmt.setString(1, admissionNo);
			pstmt.setString(2, academicYear);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySubscribVO title = new LibrarySubscribVO();
				title.setStudentName(rs.getString("studentName"));
				title.setRollNumber(rs.getString("student_rollno"));
				title.setStudentIdHidden(rs.getString("student_id_int"));
				title.setGender(rs.getString("student_gender_var"));
				title.setClassName(rs.getString("classdetails_name_var"));
				title.setClassId(rs.getString("classdetail_id_int"));
				title.setSectionName(rs.getString("classsection_name_var"));
				title.setSectionId(rs.getString("classsection_id_int"));
				title.setImageUrl(rs.getString("student_imgurl_var"));
				title.setPhoneNumber(rs.getString("mobileno"));
				title.setEmail(rs.getString("email"));
				title.setPresentAddress(rs.getString("presentAddress"));

				studentData.add(title);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibrarySubscribVO> getStaffData(String staffid,
			String locId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySubscribVO> teacherData = new ArrayList<LibrarySubscribVO>();

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.staffInfo);
			pstmt.setString(1, staffid);
			pstmt.setString(2, locId);
			System.out.println(pstmt);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibrarySubscribVO title = new LibrarySubscribVO();
				title.setStaffRegisterId(rs.getString("registerId"));
				title.setTeacherName(rs.getString("StaffName"));
				title.setAbbrId(rs.getString("Abbreviative_Id"));
				title.setDepartment(rs.getString("DEPT_NAME"));
				title.setDesignation(rs.getString("designationName"));
				title.setEmail(rs.getString("emailId"));
				title.setPresentAddress(rs.getString("presentAddress"));
				title.setPhoneNumber(rs.getString("mobileNo"));
				title.setImageUrl(rs.getString("imagePath"));
				title.setGender(rs.getString("gender"));
				title.setDepartmentId(rs.getString("department"));
				title.setDesigantionId(rs.getString("designation"));
				title.setStaffID(rs.getString("TeacherID"));
				title.setImageUrl(rs.getString("imagePath"));
				teacherData.add(title);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		return teacherData;
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStudentListDetails(String academic_year, String location, String select,String classname,String sectionid)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;


		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {
			System.out.println(select);
			conn = JDBCConnection.getSeparateConnection();
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistdetailsbysubNo);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistdetailsbysubName);
			}
			pstmt.setString(1, academic_year);
			pstmt.setString(2, location);
			pstmt.setString(3, classname);
			pstmt.setString(4, sectionid);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public String inactiveSubCategoryType1(String[] id) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		PreparedStatement pstmt0 = null;
		ResultSet rs0=null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		Connection conn = null;
		int count = 0;
		int count1 = 0;
		int count2 = 0;
		String status = null;
		String subcategory1_code=null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			conn.setAutoCommit(false);
			for(int i=0 ;i<id.length;i++){
				pstmt0=conn.prepareStatement("SELECT * FROM library_subcategory1 WHERE subcategory1_id=?");
				pstmt0.setString(1, id[i]);
				rs0=pstmt0.executeQuery();
				while(rs0.next()){
					subcategory1_code=rs0.getString("subcategory1_code");
				}

				pstmt = conn.prepareStatement(LibrarySqlUtils.INACTIVE_SUB_CATEGORY_TYPE_1);
				pstmt.setString(1, id[i]);
				count = pstmt.executeUpdate();
				if (count > 0) {
					pstmt1 = conn.prepareStatement("UPDATE library_subcategory2 SET `status`='Inactive' WHERE subcategory1_code=?");
					pstmt1.setString(1, subcategory1_code);
					count1 = pstmt1.executeUpdate();
					if (count1 > 0) {
						pstmt2 = conn.prepareStatement("UPDATE library_subcategory3 SET `status`='Inactive' WHERE subcategory1_code=?");
						pstmt2.setString(1, subcategory1_code);
						count2 = pstmt2.executeUpdate();
						if (count2 > 0) {
							conn.commit();
							status = "Record Inactivated";
						}
						else{
							status = "Failed To Add The Record";
						}

					}
					else{
						conn.commit();
						status = "Record Inactivated";
					}
				} else {

					status = "Failed To Add The Record";
				}

			}
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs0 != null && !rs0.isClosed()) {
					rs0.close();
				}
				if (pstmt0 != null && (!pstmt0.isClosed())) {
					pstmt0.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1  != null && (!pstmt1 .isClosed())) {
					pstmt1 .close();
				}
				if (pstmt2  != null && (!pstmt2 .isClosed())) {
					pstmt2 .close();
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
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return status;
	}

	@Override
	public boolean validateSubcategoryType1(SubCategoryType1VO sub1) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in validateStreamDetailsDaoImpl : validateStreamNameDetailsDao Starting");
		boolean SubCategoryType1_validate = false;
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(LibrarySqlUtils.VALIDATE_CATEGORY_TYPE_1);
			pstmt.setString(1, sub1.getSubcategorytype1code());
			System.out.println("pstmt is " + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				SubCategoryType1_validate = true;
			} else {
				SubCategoryType1_validate = false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
				+ " Control in validateDetailsDaoImpl : searchStreamNameDetailsDao  Ending");

		return SubCategoryType1_validate;
	}

	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getIssueStudentClassList(
			String locid, String accyear, String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL:getIssueStudentClassList : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		ArrayList<LibrarySearchIssueDetailsVO> clslist = new ArrayList<LibrarySearchIssueDetailsVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(LibrarySqlUtils.GET_ISSUED_CLASS_LIST);
			pstmt.setString(1, accyear);
			pstmt.setString(2, locid);
			pstmt.setString(3, classname);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibrarySearchIssueDetailsVO vo = new LibrarySearchIssueDetailsVO();
				count++;
				vo.setSlno(count);
				/* vo.setStuid(rs.getString("student_id_int")); */
				vo.setStuname(rs.getString("student"));
				vo.setStuSubNo(rs.getString("subscriberNumber"));
				vo.setRollno(rs.getString("student_rollno"));
				vo.setStandard(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setBookname(rs.getString("book_title"));
				vo.setBookauthor(rs.getString("author"));
				vo.setIssuedate( HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
				vo.setStatus(rs.getString("status"));
				clslist.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: getIssueStudentClassList: Ending");
		return clslist;
	}

	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getIssueStudentSectionList(
			String locid, String accyear, String classname, String sectionnm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL:getIssueStudentSectionList : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		ArrayList<LibrarySearchIssueDetailsVO> Seclist = new ArrayList<LibrarySearchIssueDetailsVO>();
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(LibrarySqlUtils.GET_ISSUED_SECTION_LIST);
			pstmt.setString(1, accyear);
			pstmt.setString(2, locid);
			pstmt.setString(3, classname);
			pstmt.setString(4, sectionnm);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibrarySearchIssueDetailsVO vo = new LibrarySearchIssueDetailsVO();
				count++;
				vo.setSlno(count);
				/* vo.setStuid(rs.getString("student_id_int")); */
				vo.setStuname(rs.getString("student"));
				vo.setStuSubNo(rs.getString("subscriberNumber"));
				vo.setRollno(rs.getString("student_rollno"));
				vo.setStandard(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setBookname(rs.getString("book_title"));
				vo.setBookauthor(rs.getString("author"));
				vo.setIssuedate(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
				vo.setStatus(rs.getString("status"));
				Seclist.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL:getIssueStudentSectionList: Ending");

		return Seclist;
	}

	/*--------------------------------------- StudentListByClassName-----------------------------*/

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStudentListByClassName(
			String academic_year, String location, String classname,String sectionid,String select) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();

		try {

			conn = JDBCConnection.getSeparateConnection();

			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistdetailsByClassName_BY_SUBSCRIBER_NO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistdetailsByClassName_BY_SUBSCRIBER_NAME);
			}
			//pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistdetailsByClassName);
			pstmt.setString(1, academic_year);
			pstmt.setString(2, location);
			pstmt.setString(3, classname);
			// pstmt.setString(4,sectionid);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs
						.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public LibraryLocationVO editLibraryLocation(String id) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in librarylocations: getEditlibrarylocations : Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
	
		LibraryLocationVO liblocPO = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(LibrarySqlUtils.EDIT_LIBRARY_LOCATIONS);
			pstmt.setString(1, id.trim());

			rs = pstmt.executeQuery();
					
					System.out.println("edit query>>"+pstmt);

			while (rs.next()) {
				liblocPO = new LibraryLocationVO();

				liblocPO.setLibraryLocations(rs
						.getString("library_location_name"));
				liblocPO.setLibrarylocid(id);
				liblocPO.setLocationid(rs.getString("loc_id"));
				liblocPO.setDescription(rs.getString("Description"));
				liblocPO.setSchoolName(rs.getString("Location_Name"));

			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
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
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return liblocPO;

	}

	@Override
	public String updateLibLocations(LibraryLocationPojo insert_libLoc) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in librarylocations: getEditlibrarylocations : Starting");
		Date d = HelperClass.getCurrentTimestamp();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		CategoryTypeVO catVO = null;
		LibraryLocationVO liblocPO = null;
		String result = null;
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(LibrarySqlUtils.UPDATE_LIB_LOCATIONS);
			pstmt.setString(1, insert_libLoc.getLibraryLocations());
			pstmt.setString(2, insert_libLoc.getSchoolName());
			pstmt.setString(3, insert_libLoc.getDescription());
			pstmt.setString(4, insert_libLoc.getCurrentuserid());
			pstmt.setString(5, insert_libLoc.getHiddenlibid());
			System.out.println(pstmt);
			System.out
			.println("............................inside update daoimpl");
			count = pstmt.executeUpdate();
			if (count > 0) {
				result = "successfully";
			} else {
				result = "failed";
			}

		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
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
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return result;

	}

	public ArrayList<LibrarySearchSubscriberVO> getStudentListBySection(
			String academic_year, String location, String classname,
			String sectionid,String select) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();

		try {

			conn = JDBCConnection.getSeparateConnection();

			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistdetailsBySection_BY_SUBSCRIBER_NO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistdetailsBySection_BY_SUBSCRIBER_NAME);
			}

			pstmt.setString(1, academic_year);
			pstmt.setString(2, location);
			pstmt.setString(3, classname);
			pstmt.setString(4, sectionid);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	/*-----------------------------------------search Any wher--------------------------------------*/
	public List<LibrarySearchSubscriberVO> searchsubscriberList(
			String searchTextVal, String academic_year, String location) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistSearchByAnyWhere);
			pstmt.setString(1, "%" + searchTextVal + "%");
			pstmt.setString(2, "%" + searchTextVal + "%");
			pstmt.setString(3, "%" + searchTextVal + "%");
			pstmt.setString(4, "%" + searchTextVal + "%");
			pstmt.setString(5, "%" + searchTextVal + "%");
			pstmt.setString(6, "%" + searchTextVal + "%");
			pstmt.setString(7, "%" + searchTextVal + "%");
			pstmt.setString(8, "%" + searchTextVal + "%");
			/*
			 * pstmt.setString(9,"%" + searchTextVal + "%");
			 * pstmt.setString(10,"%" + searchTextVal + "%");
			 */
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	/*-----------------------------------------search Start With--------------------------------------*/
	public List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByStartWith(String searchTextVal,String location,
			String select,String classname, String sectionid,String academic_year) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();

		try {

			conn = JDBCConnection.getSeparateConnection();
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistSearchByStatWith_BY_SUB_NO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistSearchByStatWith_BY_SUB_NAME);
			}
			//pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistSearchByStatWith);
			pstmt.setString(1, searchTextVal + "%");
			pstmt.setString(2, searchTextVal + "%");
			pstmt.setString(3, searchTextVal + "%");
			pstmt.setString(4, searchTextVal + "%");
			pstmt.setString(5, searchTextVal + "%");
			pstmt.setString(6, searchTextVal + "%");
			pstmt.setString(7, searchTextVal + "%");
			pstmt.setString(8, searchTextVal + "%");
			pstmt.setString(9, location);
			pstmt.setString(10, academic_year);
			pstmt.setString(11, classname);
			pstmt.setString(12, sectionid);
			//pstmt.setString(10, academic_year);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs
						.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	/*-----------------------------------------search Ends With--------------------------------------*/
	public List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByEndsWith(String searchTextVal,String location,
			String select,String classname,String sectionid, String academic_year) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();

		try {

			conn = JDBCConnection.getSeparateConnection();
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistSearchByEndsWith_BY_SUB_NO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistSearchByEndsWith_BY_SUB_NAME);
			}
			//pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistSearchByEndsWith);
			pstmt.setString(1, "%" + searchTextVal);
			pstmt.setString(2, "%" + searchTextVal);
			pstmt.setString(3, "%" + searchTextVal);
			pstmt.setString(4, "%" + searchTextVal);
			pstmt.setString(5, "%" + searchTextVal);
			pstmt.setString(6, "%" + searchTextVal);
			pstmt.setString(7, "%" + searchTextVal);
			pstmt.setString(8, "%" + searchTextVal);
			pstmt.setString(9, location);
			pstmt.setString(10, academic_year);
			pstmt.setString(11, classname);
			pstmt.setString(12, sectionid);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs
						.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibrarySearchSubscriberVO> getStaffListDetails(String accyear_ID, String Loc_ID, 
			String select,String department,String designation) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {

			conn = JDBCConnection.getSeparateConnection();
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.STAFF_LIST_DETAILS_ORDER_BY_SUBNO1);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.STAFF_LIST_DETAILS_ORDER_BY_SUBNAME);
			}
			pstmt.setString(1, Loc_ID);
			pstmt.setString(2, accyear_ID);
			pstmt.setString(3, Loc_ID);
			pstmt.setString(4, department);
			pstmt.setString(5, designation);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStaffdetailsByDepartment(
			String accyear_ID, String Loc_ID,String department,String designation) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(LibrarySqlUtils.STAFF_LIST_DETAILS_BY_DEPARTMENT);
			pstmt.setString(1, Loc_ID);
			pstmt.setString(2, accyear_ID);
			pstmt.setString(3, Loc_ID);
			pstmt.setString(4, department);
			pstmt.setString(5, designation);
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));
				
				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStaffdetailsByDesignation(
			String accyear_ID, String Loc_ID, String department,
			String designation) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(LibrarySqlUtils.STAFF_LIST_DETAILS_BY_DESIGNATION);
			pstmt.setString(1, Loc_ID);
			pstmt.setString(2, accyear_ID);
			pstmt.setString(3, Loc_ID);
			pstmt.setString(4, department);
			pstmt.setString(5, designation);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByAnyWhere(String searchTextVal,String location,String select,
			String department,String designation,String accyear) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.STAFF_LIST_SEACH_BY_ANYWHERE_BY_SUB_NO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.STAFF_LIST_SEACH_BY_ANYWHERE_BY_SUB_NAME);
			}
			pstmt.setString(1, "%" + searchTextVal + "%");
			pstmt.setString(2, "%" + searchTextVal + "%");
			pstmt.setString(3, "%" + searchTextVal + "%");
			pstmt.setString(4, "%" + searchTextVal + "%");
			pstmt.setString(5, "%" + searchTextVal + "%");
			pstmt.setString(6, "%" + searchTextVal + "%");
			pstmt.setString(7, location);
			pstmt.setString(8, location);
			pstmt.setString(9,department);
			pstmt.setString(10,designation);
			pstmt.setString(11,accyear);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}


	@Override
	public ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByStartWith(String searchTextVal,String location,
			String select,String department,String designation,String accyear) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.STAFF_LIST_SEACH_BY_STARTWITH_BY_SUB_NO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.STAFF_LIST_SEACH_BY_STARTWITH_BY_SUB_NAME);
			}
			//pstmt = conn.prepareStatement(LibrarySqlUtils.STAFF_LIST_SEACH_BY_STARTWITH);
			pstmt.setString(1, searchTextVal + "%");
			pstmt.setString(2, searchTextVal + "%");
			pstmt.setString(3, searchTextVal + "%");
			pstmt.setString(4, searchTextVal + "%");
			pstmt.setString(5, searchTextVal + "%");
			pstmt.setString(6, searchTextVal + "%");
			pstmt.setString(7, location);
			pstmt.setString(8, location);
			
			pstmt.setString(9, department);
			pstmt.setString(10, designation);
			pstmt.setString(11, accyear);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByEndsWith(String searchTextVal,
			String location,String select,String department,String designation,String accyear) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.STAFF_LIST_SEACH_BY_STARTWITH_BY_SUB_NO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.STAFF_LIST_SEACH_BY_STARTWITH_BY_SUB_NAME);
			}
			pstmt.setString(1, "%" + searchTextVal);
			pstmt.setString(2, "%" + searchTextVal);
			pstmt.setString(3, "%" + searchTextVal);
			pstmt.setString(4, "%" + searchTextVal);
			pstmt.setString(5, "%" + searchTextVal);
			pstmt.setString(6, "%" + searchTextVal);
			pstmt.setString(7, location);
			pstmt.setString(8, location);
			
			pstmt.setString(9, department);
			pstmt.setString(10, designation);
			pstmt.setString(11, accyear);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));
				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}
	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStaffListFilterByLocationAndAcyearid(
			String accyear_ID, String loc_ID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(LibrarySqlUtils.STAFF_LIST_FILTER_BY_ACCYEAR_AND_LOCATION);
			pstmt.setString(1, loc_ID);
			pstmt.setString(2, accyear_ID);
			pstmt.setString(3, loc_ID);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public String insertSubCategoryTypeDetail(SubCategoryTypeVO sub) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		String result = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			System.out.println("sub id " + sub.getSubcategory_id());
			String id = sub.getSubcategorytypecode();
			String hid = sub.getSubcategory_id();

			if (sub.getSubcategory_id().equals("")) {
				System.out.println(id);
				System.out.println(hid);
				boolean countid = false;
				boolean countname = false;

				System.out.println("Validating--------------");
				countid = validateSubcategoryType(sub);
				System.out.println("countid is " + countid);
				System.out.println("countname is " + countname);

				if (countid == true) {
					result = "Sub Category Type Code Already Exist";
					System.out
					.println("Sub catagory type code is already exist");
				} else {

					pstmt = conn
							.prepareStatement(SQLUtilConstants.INSERT_SUB_CATEGORY_TYPE);
					System.out.println(pstmt);
					pstmt.setString(1, sub.getCategorytypecode());
					pstmt.setString(2, sub.getSubcategorytypecode());
					pstmt.setString(3, sub.getSubcategorytypename());
					pstmt.setString(4, sub.getDescription());
					pstmt.setString(5, sub.getStatus());
					pstmt.setTimestamp(6, HelperClass.getCurrentTimestamp());
					pstmt.setString(7, sub.getCreated_by());

					count = pstmt.executeUpdate();
					if (count > 0) {
						result = "Adding Sub Catagory Type Progressing...";
					} else {
						result = "Failed To Add Sub Catagory Type";
					}
				}
			} else {

				if (!id.equalsIgnoreCase(hid)) {

					pstmt = conn
							.prepareStatement(SQLUtilConstants.UPDATE_SUBCATEGORY_TYPE);
					pstmt.setString(1, sub.getCategorytypecode());
					pstmt.setString(2, sub.getSubcategorytypecode());
					pstmt.setString(3, sub.getSubcategorytypename());
					pstmt.setString(4, sub.getDescription());
					pstmt.setString(5, sub.getStatus());
					pstmt.setTimestamp(6, HelperClass.getCurrentTimestamp());
					pstmt.setString(7, sub.getCreated_by());
					pstmt.setString(8, sub.getSubcategory_id());
					System.out.println(pstmt);
					count = pstmt.executeUpdate();
					System.out.println(pstmt);
					count = pstmt.executeUpdate();

					if (count > 0) {
						result = "Updating Sub Catagory Type Progressing...";
					} else {

						result = "Failed To Add Sub Catagory Type";
					}

				} else {
					boolean countid = false;
					countid = validateSubcategoryType(sub);

					if (countid == true) {
						result = "Sub catagory type code is already exist";
						System.out
						.println("Sub catagory type code is already exist");
					} else {
						pstmt = conn
								.prepareStatement(SQLUtilConstants.UPDATE_SUBCATEGORY_TYPE);
						pstmt.setString(1, sub.getCategorytypecode());
						pstmt.setString(2, sub.getSubcategorytypecode());
						pstmt.setString(3, sub.getSubcategorytypename());
						pstmt.setString(4, sub.getDescription());
						pstmt.setString(5, sub.getStatus());
						pstmt.setTimestamp(6, HelperClass.getCurrentTimestamp());
						pstmt.setString(7, sub.getCreated_by());
						pstmt.setString(8, sub.getSubcategory_id());
						System.out.println(pstmt);
						count = pstmt.executeUpdate();
						System.out.println(pstmt);
						count = pstmt.executeUpdate();

						if (count > 0) {
							result = "Updating Sub Catagory Type Progressing...";
						} else {

							result = "Failed To Add Sub Catagory Type";
						}
					}
				}
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public List<SubCategoryType1VO> getSubCategory1ByCategoryAndSubCategory(
			String subCategoryTypeCode) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<SubCategoryType1VO> list = new ArrayList<SubCategoryType1VO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con
					.prepareStatement(SQLUtilConstants.GET_CATEGORY_TYPE_1_DETAILS);
			pstmt.setString(1, subCategoryTypeCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SubCategoryType1VO subcategorytypelist = new SubCategoryType1VO();
				subcategorytypelist.setSubcategorytype1code(rs
						.getString("subcategory1_code"));
				subcategorytypelist.setSubcategorytype1name(rs
						.getString("subcategory1_name"));
				System.out.println(subcategorytypelist);
				list.add(subcategorytypelist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public boolean validateSubcategoryType2(SubCategoryType2VO sub2) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in validateStreamDetailsDaoImpl : validateStreamNameDetailsDao Starting");
		boolean SubCategoryType2_validate = false;
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(LibrarySqlUtils.VALIDATE_CATEGORY_TYPE_2);
			pstmt.setString(1, sub2.getSubcategorytype2code().trim());
			System.out.println("pstmt is " + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				SubCategoryType2_validate = true;
			} else {
				SubCategoryType2_validate = false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
		return SubCategoryType2_validate;
	}

	@Override
	public String insertSubCategoryType2Detail(SubCategoryType2VO sub2) {
		PreparedStatement pstmt = null;
		Connection con = null;
		String status = null;
		ResultSet rs = null;
		int count = 0;
		try {
			if (!sub2.getHidden_subcat2_id().equals("")) {
				String id = sub2.getSubcategorytype2code();
				System.out.println("id is " + id);
				String Hid = sub2.getHiddenSubcat2code();
				System.out.println("Hid is " + Hid);
				boolean countid = false;
				if (!id.equalsIgnoreCase(Hid)) {
					System.out.println("Validating--------------");
					countid = validateSubcategoryType2(sub2);
					System.out.println("countid is " + countid);

					if (countid == true) {
						status = "Sub Catagory Type 2 Code is Already Exist";
						System.out.println("Sub catagory type 2 code is already exist");
					} else {

						con = JDBCConnection.getSeparateConnection();
						pstmt = con
								.prepareStatement(LibrarySqlUtils.UPDATE_SUB_CATEGORY_TYPE_2);
						pstmt.setString(1, sub2.getCategorytypecode());
						pstmt.setString(2, sub2.getSubcategorytypecode());
						pstmt.setString(3, sub2.getSubcategorytype1code());
						pstmt.setString(4, sub2.getSubcategorytype2code());
						pstmt.setString(5, sub2.getSubcategorytype2name());
						pstmt.setString(6, sub2.getDescription());
						pstmt.setString(7, sub2.getStatus());
						pstmt.setTimestamp(8, HelperClass.getCurrentTimestamp());
						pstmt.setString(9, sub2.getCreated_by());
						pstmt.setString(10, sub2.getHidden_subcat2_id());
						System.out.println(pstmt);

						count = pstmt.executeUpdate();

						if (count > 0) {
							status = "Updating Record Progressing...";
						} else {

							status = "Failed To Update The Record";
						}

					}
				} else {

					con = JDBCConnection.getSeparateConnection();
					pstmt = con
							.prepareStatement(LibrarySqlUtils.UPDATE_SUB_CATEGORY_TYPE_2);
					pstmt.setString(1, sub2.getCategorytypecode());
					pstmt.setString(2, sub2.getSubcategorytypecode());
					pstmt.setString(3, sub2.getSubcategorytype1code());
					pstmt.setString(4, sub2.getSubcategorytype2code());
					pstmt.setString(5, sub2.getSubcategorytype2name());
					pstmt.setString(6, sub2.getDescription());
					pstmt.setString(7, sub2.getStatus());
					pstmt.setTimestamp(8, HelperClass.getCurrentTimestamp());
					pstmt.setString(9, sub2.getCreated_by());
					pstmt.setString(10, sub2.getHidden_subcat2_id());
					
					System.out.println(pstmt);
					count = pstmt.executeUpdate();
					if (count > 0) {
						status = "Updating Record Progressing...";
					} else {
						status = "Failed To Update The Record";
					}
				}
			} else {
				boolean countid = false;
				if (sub2.getSubcategorytype1code() != null) {
					countid = validateSubcategoryType2(sub2);
					System.out.println("countid is " + countid);
					if (countid == true) {
						status = "Sub Catagory Type 2 Code is Already Exist";
					} else {

						con = JDBCConnection.getSeparateConnection();
						pstmt = con
								.prepareStatement(LibrarySqlUtils.INSERT_SUB_CATEGORY_TYPE_2);
						pstmt.setString(1, sub2.getCategorytypecode());
						pstmt.setString(2, sub2.getSubcategorytypecode());
						pstmt.setString(3, sub2.getSubcategorytype1code());
						pstmt.setString(4, sub2.getSubcategorytype2code());
						pstmt.setString(5, sub2.getSubcategorytype2name());
						pstmt.setString(6, sub2.getDescription());
						pstmt.setString(7, sub2.getStatus());
						pstmt.setTimestamp(8, HelperClass.getCurrentTimestamp());
						pstmt.setString(9, sub2.getCreated_by());

						System.out.println(pstmt);

						count = pstmt.executeUpdate();

						if (count > 0) {
							status = "Adding Record Progressing...";
						} else {

							status = "Failed To Add The Record";
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public String deleteLibraryLocations(String[] librarylocid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: insertLibraryLocations : Starting");

		ResultSet location_rs = null;

		PreparedStatement location_pstmt = null;
		PreparedStatement ps_deletelocation = null;
		ResultSet rs_deleteliblocation = null;
		ResultSet rs_deleteliblocation1 = null;
		int no = 0;
		String status = null;
		int count=0;
		Connection conn = null;
		String status1 = null;

		try {
			for(int i=0;i<librarylocid.length;i++){
			conn = JDBCConnection.getSeparateConnection();

			location_pstmt = conn
					.prepareStatement("SELECT COUNT(*) libloc FROM `campus_library_stock_entry` WHERE `library_location`=?");

			location_pstmt.setString(1, librarylocid[i]);
			rs_deleteliblocation = location_pstmt.executeQuery();
   
			while (rs_deleteliblocation.next()) {

				no = rs_deleteliblocation.getInt("libloc");

				if (no > 0) {
					count++;
				} 
			}
			rs_deleteliblocation.close();
			location_pstmt.close();
			if(no == 0){
				PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) libloc FROM `campus_library_book_issue_details` WHERE `location_id`=?");
				pstmt.setString(1,librarylocid[i]);
				
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
					no = rs.getInt("libloc");
					if (no > 0) {
						count++;
					} 
				}
				rs.close();
				pstmt.close();
				
				if(no == 0){
						
					PreparedStatement pstmtleave = conn.prepareStatement("SELECT COUNT(*) libloc FROM `campus_library_book_return_details` WHERE `location_id`=?");
					pstmtleave.setString(1, librarylocid[i]);
					
					ResultSet rsleave = pstmtleave.executeQuery();
					while(rsleave.next()){
						no = rsleave.getInt("libloc");
						if (no > 0) {
							count++;
						} 
					}
					pstmtleave.close();
					rsleave.close();
					
					if(no == 0){
						
						PreparedStatement pstmt2 = conn.prepareStatement("SELECT COUNT(*) libloc FROM `campus_library_reservation_details` WHERE `library_location`=?");
						pstmt2.setString(1, librarylocid[i]);
						System.out.println("lib resrv details ..."+pstmt2);
						ResultSet rs2 = pstmt2.executeQuery();
						while(rs2.next()){
							no = rs2.getInt("libloc");
							if (no > 0) {
								count++;
							} 
						}
						pstmt2.close();
						rs2.close();
						
						if(no == 0){
							
							PreparedStatement pstmt3 = conn.prepareStatement("SELECT COUNT(*) libloc FROM `campus_library_subscriber_details` WHERE `loc_ID`=?");
							pstmt3.setString(1, librarylocid[i]);
							
							ResultSet rs3 = pstmt3.executeQuery();
							while(rs3.next()){
								no = rs3.getInt("libloc");
								if (no > 0) {
									count++;
								} 
							}
							pstmt3.close();
							rs3.close();
					if(no == 0 ){
						
						ps_deletelocation = (PreparedStatement) JDBCConnection
								.getStatement(LibrarySqlUtils.DELETE_LIBRARY_LOCATIONS);
					
						ps_deletelocation.setString(1, librarylocid[i]);
						
						
						no = ps_deletelocation.executeUpdate();
						
						if(no > 0){
							status = "true";
						}
						else{
							status = "false";
						}
				}
			}
		 }	
	   }
			}
			}
		}catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();

			} finally {
			try {

				try {

					if (rs_deleteliblocation != null
							&& (!rs_deleteliblocation.isClosed())) {

						rs_deleteliblocation.close();
					}
					if (location_rs != null && (!location_rs.isClosed())) {

						location_rs.close();
					}
					if (ps_deletelocation != null
							&& (!ps_deletelocation.isClosed())) {

						ps_deletelocation.close();
					}
					if (location_pstmt != null
							&& (!location_pstmt.isClosed())) {

						location_pstmt.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public List<LibrarySearchIssueDetailsVO> getIssueDetailsByAnyWhere(
			String searchTextVal, String locid, String accyear) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchIssueDetailsVO> studentData = new ArrayList<LibrarySearchIssueDetailsVO>();
		int count = 0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(LibrarySqlUtils.GET_ISSUED_ANYWR_LIST);
			pstmt.setString(1, "%" + searchTextVal + "%");
			pstmt.setString(2, "%" + searchTextVal + "%");
			pstmt.setString(3, "%" + searchTextVal + "%");
			pstmt.setString(4, "%" + searchTextVal + "%");
			pstmt.setString(5, "%" + searchTextVal + "%");
			pstmt.setString(6, "%" + searchTextVal + "%");
			pstmt.setString(7, "%" + searchTextVal + "%");

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				LibrarySearchIssueDetailsVO vo = new LibrarySearchIssueDetailsVO();
				count++;
				vo.setSlno(count);
				vo.setStuname(rs.getString("student"));
				vo.setStuSubNo(rs.getString("subscriberNumber"));
				vo.setRollno(rs.getString("student_rollno"));
				vo.setStandard(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));
				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;

	}

	@Override
	public ArrayList<LibraryLocationVO> getSchoolLocations(String id) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in librarylocations: getEditlibrarylocations : Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		ArrayList<LibraryLocationVO> liblocPO = new ArrayList<LibraryLocationVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(LibrarySqlUtils.GET_SEL_SCHOOL_LOCATIONS);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibraryLocationVO categorylist = new LibraryLocationVO();

				categorylist.setLibrarylocid(rs.getString("library_loc_id"));
				categorylist.setLocationid(rs.getString("loc_id"));
				categorylist.setSchoolName(rs.getString("Location_Name"));
				categorylist.setLibraryLocations(rs
						.getString("library_location_name"));
				categorylist.setDescription(rs.getString("Description"));
				liblocPO.add(categorylist);

			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
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
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return liblocPO;

	}

	@Override
	public List<LibrarySearchIssueDetailsVO> getIssueDetailsByStartwith(
			String searchTextVal, String locid, String accyear, String selection) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<LibrarySearchIssueDetailsVO> studentData = new ArrayList<LibrarySearchIssueDetailsVO>();
		int count = 0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(LibrarySqlUtils.GET_ISSUED_ANYWR_LIST);

			if (selection.equalsIgnoreCase("start")) {
				pstmt.setString(1, searchTextVal + "%");
				pstmt.setString(2, searchTextVal + "%");
				pstmt.setString(3, searchTextVal + "%");
				pstmt.setString(4, searchTextVal + "%");
				pstmt.setString(5, searchTextVal + "%");
				pstmt.setString(6, searchTextVal + "%");
				pstmt.setString(7, searchTextVal + "%");
				pstmt.setString(8, searchTextVal + "%");
				pstmt.setString(9, searchTextVal + "%");
				pstmt.setString(10, searchTextVal + "%");
			} else if (selection.equalsIgnoreCase("end")) {
				pstmt.setString(1, "%" + searchTextVal);
				pstmt.setString(2, "%" + searchTextVal);
				pstmt.setString(3, "%" + searchTextVal);
				pstmt.setString(4, "%" + searchTextVal);
				pstmt.setString(5, "%" + searchTextVal);
				pstmt.setString(6, "%" + searchTextVal);
				pstmt.setString(7, "%" + searchTextVal);
				pstmt.setString(8, "%" + searchTextVal);
				pstmt.setString(9, "%" + searchTextVal);
				pstmt.setString(10, "%" + searchTextVal);
			} else {
				pstmt.setString(1, "%" + searchTextVal + "%");
				pstmt.setString(2, "%" + searchTextVal + "%");
				pstmt.setString(3, "%" + searchTextVal + "%");
				pstmt.setString(4, "%" + searchTextVal + "%");
				pstmt.setString(5, "%" + searchTextVal + "%");
				pstmt.setString(6, "%" + searchTextVal + "%");
				pstmt.setString(7, "%" + searchTextVal + "%");
				pstmt.setString(8, "%" + searchTextVal + "%");
				pstmt.setString(9, "%" + searchTextVal + "%");
				pstmt.setString(10, "%" + searchTextVal + "%");
			}
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				LibrarySearchIssueDetailsVO vo = new LibrarySearchIssueDetailsVO();
				count++;
				vo.setSlno(count);
				vo.setStuname(rs.getString("student"));
				vo.setStuSubNo(rs.getString("subscriberNumber"));
				vo.setRollno(rs.getString("student_rollno"));
				vo.setStandard(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setBookname(rs.getString("book_title"));
				vo.setBookauthor(rs.getString("author"));
				vo.setIssuedate( HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
				vo.setStatus(rs.getString("status"));
				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public SubCategoryTypeVO editSubCategoryType(String id) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		SubCategoryTypeVO catVO = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(SQLUtilConstants.EDIT_SUB_CATEGORY_TYPE);

			pstmt.setString(1, id);
			System.out.println("pstmt " + pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				catVO = new SubCategoryTypeVO();

				// `category_code`,`subcategory_code`,`subcategory_name`,`description`,`status`
				catVO.setCategory_id(rs.getString("category_code"));
				catVO.setSubcategory_id(rs.getString("subcategory_id"));
				catVO.setSubcategory_code(rs.getString("subcategory_code"));
				catVO.setSubcategory_name(rs.getString("subcategory_name"));
				catVO.setStatus(rs.getString("status"));
				catVO.setDescription(rs.getString("description"));

			}

		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
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

		return catVO;

	}

	@Override
	public String inactiveSubCategoryType(String[] id, SubCategoryTypeVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		ResultSet rs0 = null;
		PreparedStatement pstmt0 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		String subcategory_code = null;
		Connection conn = null;

		int count = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		String status = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			for(int i=0 ;i<id.length;i++){
				pstmt0 = conn.prepareStatement("SELECT * FROM library_subcategory WHERE subcategory_id=?");
				pstmt0.setString(1, id[i]);

				rs0 = pstmt0.executeQuery();
				while (rs0.next()) {
					subcategory_code = rs0.getString("category_code");
				}

				pstmt = conn.prepareStatement(SQLUtilConstants.INACTIVE_SUBCATEGORY_TYPE);
				pstmt.setString(1, "Inactive");
				pstmt.setString(2, id[i]);
				count = pstmt.executeUpdate();
				if (count > 0) {
					pstmt1 = conn.prepareStatement(SQLUtilConstants.INACTIVE_SUB1_CATEGORY_TYPE);
					pstmt1.setString(1, subcategory_code);
					count1 = pstmt1.executeUpdate();
					if (count1 > 0) {
						pstmt2 = conn.prepareStatement(SQLUtilConstants.INACTIVE_SUB2_CATEGORY_TYPE);
						pstmt2.setString(1, subcategory_code);
						count2 = pstmt2.executeUpdate();
						if (count2 > 0) {
							pstmt3 = conn.prepareStatement(SQLUtilConstants.INACTIVE_SUB3_CATEGORY_TYPE);
							pstmt3.setString(1, subcategory_code);
							count3 = pstmt3.executeUpdate();

							if (count3 > 0) {
								status = "Record Inactivated";
							} else {
								status = "Failed To Add The Record";
							}
						} else {
							status = "Record Inactivated";
						}
					} else {
						status = "Record Inactivated";
					}
				} else {
					status = "Failed To Add The Record";
				}
			}
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs0 != null && (!rs0.isClosed())) {
					rs0.close();
				}
				if (pstmt0 != null && (!pstmt0.isClosed())) {
					pstmt0.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmt3 != null && (!pstmt3.isClosed())) {
					pstmt3.close();
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
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");
		return status;
	}

	@Override
	public List<SubCategoryType2VO> getSubCategoryType2Details() {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<SubCategoryType2VO> list = new ArrayList<SubCategoryType2VO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con
					.prepareStatement(LibrarySqlUtils.CATEGORY_TYPE_2_DETAILS);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SubCategoryType2VO subcategorytype2list = new SubCategoryType2VO();
				subcategorytype2list.setCategorytypecode(rs
						.getString("category_code"));
				subcategorytype2list.setCategorytypename(rs
						.getString("category_name"));
				subcategorytype2list.setSubcategorytypecode(rs
						.getString("subcategory_code"));
				subcategorytype2list.setSubcategorytypename(rs
						.getString("subcategory_name"));
				subcategorytype2list.setSubcategorytype1code(rs
						.getString("subcategory1_code"));
				subcategorytype2list.setSubcategorytype1name(rs
						.getString("subcategory1_name"));
				subcategorytype2list.setSubcategorytype2_id(rs
						.getString("subcategory2_id"));
				subcategorytype2list.setSubcategorytype2code(rs
						.getString("subcategory2_code"));
				subcategorytype2list.setSubcategorytype2name(rs
						.getString("subcategory2_name"));
				subcategorytype2list.setStatus(rs.getString("status"));
				subcategorytype2list
				.setDescription(rs.getString("description"));

				list.add(subcategorytype2list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public SubCategoryType2VO editSubCategoryType2(String id) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		SubCategoryType2VO catVO = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.EDIT_SUB_CATEGORY_TYPE_2);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				catVO = new SubCategoryType2VO();
				catVO.setHidden_subcat2_id(rs.getString("subcategory2_id"));
				catVO.setSubcategorytype2_id(rs.getString("subcategory2_id"));
				catVO.setCategorytypecode(rs.getString("category_code"));
				catVO.setSubcategorytypecode(rs.getString("subcategory_code"));
				catVO.setSubcategorytype1code(rs.getString("subcategory1_code"));
				catVO.setSubcategorytype2code(rs.getString("subcategory2_code"));
				catVO.setSubcategorytype2name(rs.getString("subcategory2_name"));
				catVO.setStatus(rs.getString("status"));
				catVO.setDescription(rs.getString("description"));

			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
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
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return catVO;

	}

	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getTeacherList(String locid,
			String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: getTeacherList : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		ArrayList<LibrarySearchIssueDetailsVO> stfflist = new ArrayList<LibrarySearchIssueDetailsVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(LibrarySqlUtils.GET_ISSUED_STAFF_LIST);
			pstmt.setString(1, accyear);
			pstmt.setString(2, locid);
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibrarySearchIssueDetailsVO vo = new LibrarySearchIssueDetailsVO();
				count++;
				vo.setSlno(count);
				vo.setStuSubNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setBookname(rs.getString("book_title"));
				vo.setBookauthor(rs.getString("author"));
				vo.setIssuedate( HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
				vo.setStatus(rs.getString("status"));
				stfflist.add(vo);
			}
			

		} catch (Exception e) {
			e.printStackTrace();

		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: getStudentIssuedList  : Ending");
		return stfflist;
	}

	@Override
	public boolean validateLibLocationUpdate(LibraryLocationVO lib) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in validateLibraryLocationsDaoImpl : validateLibraryLocationsDaoImpl Starting");
		boolean Lib_Loc_Name_validate_update = false;
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(LibrarySqlUtils.VALIDATE_LIB_LOC_NAME);
			/*
			 * pstmt.setString(1, sub1.getCategorytypecode().trim());
			 * pstmt.setString(2, sub1.getSubcategorytypecode().trim());
			 */
			pstmt.setString(1, lib.getSchoolName().trim());
			pstmt.setString(2, lib.getLibraryLocations().trim());
			System.out.println("pstmt is " + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			System.out.println("...........");
			System.out.println(count);
			if (count > 0) {
				Lib_Loc_Name_validate_update = true;
			} else {
				Lib_Loc_Name_validate_update = false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
				+ " Control in validateDetailsDaoImpl : searchStreamNameDetailsDao  Ending");

		return Lib_Loc_Name_validate_update;
	}

	@Override
	public List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByAnyWhere(String searchTextVal,String location,String academic_year,
			String select,String classname, String sectionid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {

			conn = JDBCConnection.getSeparateConnection();
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistSearchByAnyWhere_BY_SUBNO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistSearchByAnyWhere_BY_SUBNAME);
			}
			//pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistSearchByAnyWhere);
			pstmt.setString(1, "%" + searchTextVal + "%");
			pstmt.setString(2, "%" + searchTextVal + "%");
			pstmt.setString(3, "%" + searchTextVal + "%");
			pstmt.setString(4, "%" + searchTextVal + "%");
			pstmt.setString(5, "%" + searchTextVal + "%");
			pstmt.setString(6, "%" + searchTextVal + "%");
			pstmt.setString(7, "%" + searchTextVal + "%");
			pstmt.setString(8, "%" + searchTextVal + "%");
			pstmt.setString(9, location);
			pstmt.setString(10, academic_year);
			pstmt.setString(11, classname);
			pstmt.setString(12, sectionid);
			//pstmt.setString(10, academic_year);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public List<CategoryTypeVO> getSubCategoryDetails(String cattype,
			String status) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con
					.prepareStatement(SQLUtilConstants.SUB_CATEGORY_ON_SEARCH);
			pstmt.setString(1, status);
			pstmt.setString(2, cattype);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				CategoryTypeVO categorylist = new CategoryTypeVO();
				// sub.`subcategory_id`,cat.`category_name`,
				// sub.`category_code`,
				// sub.`subcategory_code`,sub.`subcategory_name`,sub.`description`,sub.`status`
				categorylist.setSubcategoryid(rs.getString("subcategory_id"));
				categorylist.setCategory_id(rs.getString("category_code"));/* subcategory_id */
				categorylist.setCategoryname(rs.getString("category_name"));
				categorylist.setCategorytypecode(rs
						.getString("subcategory_code"));
				categorylist.setCategorytypename(rs
						.getString("subcategory_name"));
				categorylist.setDescription(rs.getString("description"));
				categorylist.setStatus(rs.getString("status"));
				list.add(categorylist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	public List<CategoryTypeVO> getSubCategoryDetails1() {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<CategoryTypeVO> list1 = new ArrayList<CategoryTypeVO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con
					.prepareStatement(SQLUtilConstants.SUB_CATEGORY_DETAILS1);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CategoryTypeVO categorylist = new CategoryTypeVO();

				// sub.`subcategory_id`,cat.`category_name`,
				// sub.`category_code`,
				// sub.`subcategory_code`,sub.`subcategory_name`,sub.`description`,sub.`status`

				categorylist.setCategory_id(rs.getString("category_code"));/* subcategory_id */
				categorylist.setCategoryname(rs.getString("category_name"));

				list1.add(categorylist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list1;
	}

	@Override
	public List<CategoryTypeVO> getSubCategoryTypeName(String categoryName) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDaoImpl : getSubCategoryTypeName Starting");

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con.prepareStatement(SQLUtilConstants.GET_SUBCATEGORY_NAME);
			pstmt.setString(1, categoryName.trim());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CategoryTypeVO categorylist = new CategoryTypeVO();
				categorylist.setSubcategorycode(rs
						.getString("subcategory_code"));
				categorylist.setSubcategoryname(rs
						.getString("subcategory_name"));

				list.add(categorylist);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDaoImpl : getSubCategoryTypeName  Ending");

		return list;
	}

	public ArrayList<LibrarySubscribVO> getSubscriberDetailsListPage(
			String academicYear, String locId, String classId,
			String sectionName, String suscriberType, String department,
			String designation, String otherName) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;
		ArrayList<LibrarySubscribVO> list = new ArrayList<LibrarySubscribVO>();

		try {
			conn = JDBCConnection.getSeparateConnection();
			if (suscriberType.equalsIgnoreCase("studentwise")) {
				psmt = conn.prepareStatement(LibrarySqlUtils.getSubscriberDetailsListPage_student);
				psmt.setString(1, classId);
				psmt.setString(2, sectionName);
				psmt.setString(3, locId);
				psmt.setString(4, academicYear);

				System.out.println("for student" + psmt);
				rs = psmt.executeQuery();
				while (rs.next()) {

					sno++;
					LibrarySubscribVO vo = new LibrarySubscribVO();
					vo.setSno(sno);
					vo.setSubscriberNo(rs.getString("subscriberNumber"));
					vo.setStudentName(rs.getString("studentName"));
					vo.setAdminssionNo(rs.getString("student_admissionno_var"));
					vo.setRollNumber(rs.getString("student_rollno"));
					vo.setClassName(rs.getString("classdetails_name_var"));
					vo.setSectionName(rs.getString("classsection_name_var"));
					vo.setStatus(rs.getString("status"));

					list.add(vo);
				}

			} else if (suscriberType.equalsIgnoreCase("staffwise")) {
				psmt = conn
						.prepareStatement(LibrarySqlUtils.getSubscriberDetailsListPage_staffwise);
				psmt.setString(1, locId);
				psmt.setString(2, academicYear);
				psmt.setString(3, department);
				psmt.setString(4, designation);

				System.out.println("for staff" + psmt);
				rs = psmt.executeQuery();
				while (rs.next()) {

					sno++;
					LibrarySubscribVO vo = new LibrarySubscribVO();
					vo.setSno(sno);
					vo.setSubscriberNo(rs.getString("subscriberNumber"));
					vo.setStaffName(rs.getString("staffName"));
					vo.setDepartment(rs.getString("DEPT_NAME"));
					vo.setDesignation(rs.getString("designationName"));
					vo.setStatus(rs.getString("status"));

					list.add(vo);
				}

			} else if (suscriberType.equalsIgnoreCase("other")) {
				psmt = conn.prepareStatement(LibrarySqlUtils.getSubscriberDetailsListPage_others);
				psmt.setString(1, locId);
				psmt.setString(2, academicYear);
				//psmt.setString(3, otherName);

				System.out.println("for others" + psmt);
				rs = psmt.executeQuery();
				while (rs.next()) {

					sno++;
					LibrarySubscribVO vo = new LibrarySubscribVO();
					vo.setSno(sno);
					vo.setSubscriberId(rs.getString("subscriberId"));
					vo.setSubscriberNo(rs.getString("subscriberNumber"));
					vo.setOtherUserName(rs.getString("otherName"));
					vo.setOtherUserContact(rs.getString("otherContactNo"));
					vo.setOtherUserGender(rs.getString("otherGender"));
					vo.setOtherUserAddr(rs.getString("otherAddress"));
					vo.setStatus(rs.getString("status"));
					vo.setOtherUserEmail(rs.getString("otherEmail"));
					list.add(vo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				conn.close();
				psmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return list;
	}

	@Override
	public List<CategoryTypeVO> getSubCategoryList(String catcode,
			String subcatcode, String status) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con.prepareStatement(SQLUtilConstants.GET_SUBCATEGORY_LIST);
			pstmt.setString(1, status);
			pstmt.setString(2, catcode);
			pstmt.setString(3, subcatcode);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				CategoryTypeVO subcategorylist = new CategoryTypeVO();
				// sub.`subcategory_id`,cat.`category_name`,
				// sub.`category_code`,
				// sub.`subcategory_code`,sub.`subcategory_name`,sub.`description`,sub.`status`
				subcategorylist
				.setSubcategoryid(rs.getString("subcategory_id"));
				subcategorylist.setCategory_id(rs.getString("category_code"));/* subcategory_id */
				subcategorylist.setCategoryname(rs.getString("category_name"));
				subcategorylist.setCategorytypecode(rs
						.getString("subcategory_code"));
				subcategorylist.setCategorytypename(rs
						.getString("subcategory_name"));
				subcategorylist.setDescription(rs.getString("description"));
				subcategorylist.setStatus(rs.getString("status"));
				list.add(subcategorylist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getTeacherDesgList(
			String locid, String accyear, String dept, String desg) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL:  getTeacherDeptList: Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		ArrayList<LibrarySearchIssueDetailsVO> stffdesg = new ArrayList<LibrarySearchIssueDetailsVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUED_DESG_LIST);
			pstmt.setString(1, accyear);
			pstmt.setString(2, locid);
		
			pstmt.setString(3, dept);
			pstmt.setString(4, desg);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibrarySearchIssueDetailsVO vo = new LibrarySearchIssueDetailsVO();
				count++;
				vo.setSlno(count);
				vo.setStuSubNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setBookname(rs.getString("book_title"));
				vo.setBookauthor(rs.getString("author"));
				vo.setIssuedate(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
				vo.setStatus(rs.getString("status"));
				stffdesg.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL:  getTeacherDeptList: Ending");
		return stffdesg;
	}

	@Override
	public List<LibrarySearchIssueDetailsVO> getIssueByStartwith(
			LibrarySearchIssueDetailsVO vo, String selection) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<LibrarySearchIssueDetailsVO> StafData = new ArrayList<LibrarySearchIssueDetailsVO>();
		int count = 0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUED_LIST);

			if (selection.equalsIgnoreCase("start")) {
				pstmt.setString(1, vo.getSearchText() + "%");
				pstmt.setString(2, vo.getSearchText() + "%");
				pstmt.setString(3, vo.getSearchText() + "%");
				pstmt.setString(4, vo.getSearchText() + "%");
				pstmt.setString(5, vo.getSearchText() + "%");
				pstmt.setString(6, vo.getSearchText() + "%");
				pstmt.setString(7, vo.getSearchText() + "%");
				pstmt.setString(8, vo.getSearchText() + "%");
				pstmt.setString(9, vo.getSearchText() + "%");
				pstmt.setString(10, vo.getSearchText() + "%");
				pstmt.setString(11, vo.getAcademicYear());
				pstmt.setString(12, vo.getLocid());
				pstmt.setString(13, vo.getLocid());
				pstmt.setString(14, vo.getStaffDepartment());
				pstmt.setString(15, vo.getStaffDesignation());
			} else if (selection.equalsIgnoreCase("end")) {
				pstmt.setString(1, "%" + vo.getSearchText());
				pstmt.setString(2, "%" + vo.getSearchText());
				pstmt.setString(3, "%" + vo.getSearchText());
				pstmt.setString(4, "%" + vo.getSearchText());
				pstmt.setString(5, "%" + vo.getSearchText());
				pstmt.setString(6, "%" + vo.getSearchText());
				pstmt.setString(7, "%" + vo.getSearchText());
				pstmt.setString(8, "%" + vo.getSearchText());
				pstmt.setString(9, "%" + vo.getSearchText());
				pstmt.setString(10, "%" + vo.getSearchText());
				pstmt.setString(11, vo.getAcademicYear());
				pstmt.setString(12, vo.getLocid());
				pstmt.setString(13, vo.getLocid());
				pstmt.setString(14, vo.getStaffDepartment());
				pstmt.setString(15, vo.getStaffDesignation());

			} else {
				pstmt.setString(1, "%" + vo.getSearchText() + "%");
				pstmt.setString(2, "%" + vo.getSearchText() + "%");
				pstmt.setString(3, "%" + vo.getSearchText() + "%");
				pstmt.setString(4, "%" + vo.getSearchText() + "%");
				pstmt.setString(5, "%" + vo.getSearchText() + "%");
				pstmt.setString(6, "%" + vo.getSearchText() + "%");
				pstmt.setString(7, "%" + vo.getSearchText() + "%");
				pstmt.setString(8, "%" + vo.getSearchText() + "%");
				pstmt.setString(9, "%" + vo.getSearchText() + "%");
				pstmt.setString(10, "%" + vo.getSearchText() + "%");
				pstmt.setString(11, vo.getAcademicYear());
				pstmt.setString(12, vo.getLocid());
				pstmt.setString(13, vo.getLocid());
				pstmt.setString(14, vo.getStaffDepartment());
				pstmt.setString(15, vo.getStaffDesignation());

			}
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				LibrarySearchIssueDetailsVO vo1 = new LibrarySearchIssueDetailsVO();
				count++;
				vo1.setSlno(count);
				vo1.setStuSubNo(rs.getString("subscriberNumber"));
				vo1.setStaffName(rs.getString("teacher"));
				vo1.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo1.setStaffDesignation(rs.getString("designationName"));
				vo1.setBookname(rs.getString("book_title"));
				vo1.setBookauthor(rs.getString("author"));
				vo1.setIssuedate( HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
				vo1.setStatus(rs.getString("status"));
				StafData.add(vo1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return StafData;
	}

	@Override
	public String inactiveSubCategoryType2(String[] id) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");
		PreparedStatement pstmt0= null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		Connection conn = null;
		ResultSet rs=null;
		int count = 0;
		int count1 = 0;
		String status = null;
		String subcategory2_code=null;
		try {
			conn = JDBCConnection.getSeparateConnection();


			conn.setAutoCommit(false);

			for(int i=0 ;i<id.length;i++){

				pstmt0=conn.prepareStatement("SELECT * from library_subcategory2 WHERE subcategory2_id=?");
				pstmt0.setString(1, id[i]);
				rs=pstmt0.executeQuery();
				while(rs.next()){
					subcategory2_code=rs.getString("subcategory2_code");
				}
				pstmt = conn.prepareStatement(LibrarySqlUtils.INACTIVE_SUB_CATEGORY_TYPE_2);
				pstmt.setString(1, id[i]);
				System.out.println(pstmt);
				count = pstmt.executeUpdate();
				if (count > 0) {

					pstmt1 = conn.prepareStatement("UPDATE library_subcategory3 SET `status`='Inactive' WHERE subcategory2_code=?");
					pstmt1.setString(1, subcategory2_code);
					count1 = pstmt1.executeUpdate();
					System.out.println(pstmt1);
					if(count1 > 0){
						conn.commit();
						status = "Record Inactivated";
					}else{
						status = "Failed To Add The Record";
					}
				} 
				else 
				{
					status = "Failed To Inactivate The Record";
				}
			}

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return status;
	}

	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getOthersList(String locid,
			String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL:  getOthersList: Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LibrarySearchIssueDetailsVO> OtherData = new ArrayList<LibrarySearchIssueDetailsVO>();
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUED_OTHER_LIST);
			pstmt.setString(1, locid);
			pstmt.setString(2, accyear);
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibrarySearchIssueDetailsVO vo = new LibrarySearchIssueDetailsVO();
				count++;
				vo.setSlno(count);
				vo.setStuSubNo(rs.getString("subscriberNumber"));
				vo.setOthersName(rs.getString("otherName"));
				vo.setContactNo(rs.getString("otherContactNo"));
				vo.setEmailId(rs.getString("otherEmail"));
				vo.setBookname(rs.getString("book_title"));
				vo.setBookauthor(rs.getString("author"));
				vo.setIssuedate(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
				vo.setStatus(rs.getString("status"));
				OtherData.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				;
			{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			if (pstmt != null)
				;
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			if (conn != null)
				;
			{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: getStudentIssuedList  : Ending");

		return OtherData;
	}

	@Override
	public List<LibrarySearchIssueDetailsVO> getIssueotherByStartwith(
			String searchTextVal, String locid, String accyear, String selection) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<LibrarySearchIssueDetailsVO> OtherData = new ArrayList<LibrarySearchIssueDetailsVO>();
		int count = 0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_OTHER_START_LIST);

			if (selection.equalsIgnoreCase("start")) {
				pstmt.setString(1, searchTextVal + "%");
				pstmt.setString(2, searchTextVal + "%");
				pstmt.setString(3, searchTextVal + "%");
				pstmt.setString(4, searchTextVal + "%");
				pstmt.setString(5, searchTextVal + "%");
				pstmt.setString(6, searchTextVal + "%");
				pstmt.setString(7, searchTextVal + "%");
				pstmt.setString(8, searchTextVal + "%");
				pstmt.setString(9, searchTextVal + "%");
				pstmt.setString(10, searchTextVal + "%");
				
			} else if (selection.equalsIgnoreCase("end")) {
				pstmt.setString(1, "%" + searchTextVal);
				pstmt.setString(2, "%" + searchTextVal);
				pstmt.setString(3, "%" + searchTextVal);
				pstmt.setString(4, "%" + searchTextVal);
				pstmt.setString(5, "%" + searchTextVal);
				pstmt.setString(6, "%" + searchTextVal);
				pstmt.setString(7, "%" + searchTextVal);
				pstmt.setString(8, "%" + searchTextVal);
				pstmt.setString(9, "%" + searchTextVal);
				pstmt.setString(10, "%" + searchTextVal);
			} else {
				pstmt.setString(1, "%" + searchTextVal + "%");
				pstmt.setString(2, "%" + searchTextVal + "%");
				pstmt.setString(3, "%" + searchTextVal + "%");
				pstmt.setString(4, "%" + searchTextVal + "%");
				pstmt.setString(5, "%" + searchTextVal + "%");
				pstmt.setString(6, "%" + searchTextVal + "%");
				pstmt.setString(7, "%" + searchTextVal + "%");
				pstmt.setString(8, "%" + searchTextVal + "%");
				pstmt.setString(9, "%" + searchTextVal + "%");
				pstmt.setString(10, "%" + searchTextVal + "%");
			}
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				LibrarySearchIssueDetailsVO vo = new LibrarySearchIssueDetailsVO();
				count++;
				vo.setSlno(count);
				vo.setStuSubNo(rs.getString("subscriberNumber"));
				vo.setOthersName(rs.getString("otherName"));
				vo.setContactNo(rs.getString("otherContactNo"));
				vo.setEmailId(rs.getString("otherEmail"));
				vo.setBookname(rs.getString("book_title"));
				vo.setBookauthor(rs.getString("author"));
				vo.setIssuedate(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
				vo.setStatus(rs.getString("status"));
				OtherData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return OtherData;
	}

	@Override
	public List<CategoryTypeVO> getbystatusList(String catcode,
			String subcatcode, String status) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con.prepareStatement(SQLUtilConstants.GET_SUBCATBY_STATUS);
			pstmt.setString(1, catcode);
			pstmt.setString(2, subcatcode);
			pstmt.setString(3, status);

			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				CategoryTypeVO subcategorylist = new CategoryTypeVO();
				// sub.`subcategory_id`,cat.`category_name`,
				// sub.`category_code`,
				// sub.`subcategory_code`,sub.`subcategory_name`,sub.`description`,sub.`status`
				subcategorylist
				.setSubcategoryid(rs.getString("subcategory_id"));
				subcategorylist.setCategory_id(rs.getString("category_code"));/* subcategory_id */
				subcategorylist.setCategoryname(rs.getString("category_name"));
				subcategorylist.setCategorytypecode(rs
						.getString("subcategory_code"));
				subcategorylist.setCategorytypename(rs
						.getString("subcategory_name"));
				subcategorylist.setDescription(rs.getString("description"));
				subcategorylist.setStatus(rs.getString("status"));
				list.add(subcategorylist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	public ArrayList<LibrarySearchIssueDetailsVO> getTeacherDeptList(
			String locid, String accyear, String dept) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL:  getTeacherDeptList: Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		ArrayList<LibrarySearchIssueDetailsVO> stffdept = new ArrayList<LibrarySearchIssueDetailsVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUED_DEPT_LIST);
			pstmt.setString(1, accyear);
			pstmt.setString(2, locid);
			pstmt.setString(3, dept);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibrarySearchIssueDetailsVO vo = new LibrarySearchIssueDetailsVO();
				count++;
				vo.setSlno(count);
				vo.setStuSubNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setBookname(rs.getString("book_title"));
				vo.setBookauthor(rs.getString("author"));
				vo.setIssuedate(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
				vo.setStatus(rs.getString("status"));
				stffdept.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL:  getTeacherDeptList: Ending");
		return stffdept;
	}

	@Override
	public List<CategoryTypeVO> SearchCategoryTypeList(String catcode,
			String subcatcode, String status, String searchname) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
		String sql="";
		try {
			con = JDBCConnection.getSeparateConnection();
			System.out.println("ttt"+"   "+catcode);
			if(catcode.equalsIgnoreCase("all")){
				System.out.println("zzzzzzzzzzzzzzz"+catcode);
				sql="SELECT  ls.`subcategory_id`,ls.`subcategory_code`,ls.status,ls.`subcategory_name`,ls.`description`,lc.`category_name`  FROM `library_subcategory` ls LEFT JOIN `library_category` lc ON lc.`category_code` = ls.`category_code`  where lc.`category_code` like '%%' and ";
			}else if(subcatcode == "" || subcatcode.equalsIgnoreCase("all")){
				System.out.println("yyyyyyyyyyyyyyyy"+catcode);
				sql="SELECT  ls.`subcategory_id`,ls.`subcategory_code`,ls.status,ls.`subcategory_name`,ls.`description`,lc.`category_name`  FROM `library_subcategory` ls LEFT JOIN `library_category` lc ON lc.`category_code` = ls.`category_code`  where lc.`category_code` like '%%' and ls.subcategory_code like '%%' and ";
			}else{
				System.out.println("xxxxxxxxxxxxxxxx"+subcatcode);
				sql="SELECT  ls.`subcategory_id`,ls.`subcategory_code`,ls.status,ls.`subcategory_name`,ls.`description`,lc.`category_name`  FROM `library_subcategory` ls LEFT JOIN `library_category` lc ON lc.`category_code` = ls.`category_code` where ";
			}

			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;

			if(!catcode.equals("all")){ 
				if(catcode != null){
					map.put("lc.category_code",catcode);
					vec.add("lc.category_code");
				}  
			}
			
			if(!subcatcode.equals("all")){ 
				if(subcatcode != null){
					map.put("ls.subcategory_code",subcatcode);
					vec.add("ls.subcategory_code");
				}  
			}
			
			System.out.println("now----checkking ="+subcatcode);

			if(!status.equals("")){ 
				if(status != null){
					map.put("ls.status",status);
					vec.add("ls.status");
				}  
			}

			Enumeration<String> e = vec.elements();

			while ( e.hasMoreElements() ) {
				key = e.nextElement().toString();
				val = map.get(key).toString();

				if(count == 0) {
					wheresql= key+" LIKE '"+val+"'";
					count++;
					System.out.println("mmmmmmvvvvvvvv"+wheresql);
				}else {
					wheresql = wheresql+" and "+key+"='"+val+"'";
					System.out.println("vvvvvvvv"+wheresql);
				}
			}

			String query=sql+""+wheresql+" and "+"(lc.category_name LIKE ? or ls.subcategory_code like ? or ls.subcategory_name like ? or ls.status like ?)";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%"+searchname+"%");
			pstmt.setString(2, "%"+searchname+"%");
			pstmt.setString(3, "%"+searchname+"%");
			pstmt.setString(4, "%"+searchname+"%");
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CategoryTypeVO subcategorylist = new CategoryTypeVO();
				subcategorylist.setSubcategoryid(rs.getString("subcategory_id"));
				subcategorylist.setCategoryname(rs.getString("category_name"));
				subcategorylist.setCategorytypecode(rs.getString("subcategory_code"));
				subcategorylist.setCategorytypename(rs.getString("subcategory_name"));
				subcategorylist.setDescription(rs.getString("description"));
				subcategorylist.setStatus(rs.getString("status"));
				list.add(subcategorylist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public List<SubCategoryType1VO> getTabByCategoryType(String cattype,
			String status, String subcacode, String subcacode1) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<SubCategoryType1VO> list = new ArrayList<SubCategoryType1VO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con
					.prepareStatement(SQLUtilConstants.SUB_CATEGORY_TYPE1_ON_CHANGE);
			pstmt.setString(1, cattype);
			pstmt.setString(2, status);
			pstmt.setString(3, subcacode);
			pstmt.setString(4, subcacode1);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				SubCategoryType1VO subcategorytype1list = new SubCategoryType1VO();
				subcategorytype1list.setCategorytypecode(rs
						.getString("category_code"));
				subcategorytype1list.setCategorytypename(rs
						.getString("category_name"));
				subcategorytype1list.setSubcategorytypecode(rs
						.getString("subcategory_code"));
				subcategorytype1list.setSubcategorytypename(rs
						.getString("subcategory_name"));
				subcategorytype1list.setSubcategorytypecode(rs
						.getString("subcategory1_code"));
				subcategorytype1list.setSubcategorytype1_id(rs
						.getString("subcategory1_id"));
				subcategorytype1list.setSubcategorytype1code(rs
						.getString("subcategory1_code"));
				subcategorytype1list.setSubcategorytype1name(rs
						.getString("subcategory1_name"));
				subcategorytype1list.setStatus(rs.getString("status"));
				subcategorytype1list
				.setDescription(rs.getString("description"));

				list.add(subcategorytype1list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public String insertSubCategoryTypeDetail3(SubCategoryTypeVO sub) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		String result = null;
		ResultSet rs = null;
		String id = sub.getSubcategorytypecode3();
		String hid = sub.getSubcategory_id();

		try {
			conn = JDBCConnection.getSeparateConnection();
			System.out.println("sub id " + sub.getSubcategory_id());
			if (sub.getSubcategory_id().equals("")) {

				boolean countid = false;
				boolean countname = false;

				System.out.println("Validating--------------");
				countid = validateSubcategoryType3(sub);
				System.out.println("countid is " + countid);
				System.out.println("countname is " + countname);

				if (countid == true) {
					result = "Sub Category Type3 Code Already Exist";

					System.out.println("Sub catagory type 3 code is already exist");

					System.out.println("Sub catagory type 3 code is already exist");

				} else {

					pstmt = conn.prepareStatement(SQLUtilConstants.INSERT_SUB_CATEGORY_TYPE3);
					pstmt.setString(1, sub.getCategorytypecode());
					pstmt.setString(2, sub.getSubcategory_code());
					pstmt.setString(3, sub.getSubcategory_code1());
					pstmt.setString(4, sub.getSubcategory_code2());
					pstmt.setString(5, sub.getSubcategorytypecode3());
					pstmt.setString(6, sub.getSubcategorytypename3());
					pstmt.setString(7, sub.getDescription());
					pstmt.setString(8, sub.getStatus());
					pstmt.setString(9, sub.getCreated_by());
					pstmt.setTimestamp(10, HelperClass.getCurrentTimestamp());
					pstmt.setString(11, sub.getModify_by());
					pstmt.setString(12, sub.getModify_date());

					System.out.println(pstmt);

					count = pstmt.executeUpdate();
					if (count > 0) {
						result = "Adding Sub Catagory Type 3 Progressing...";
					} else {
						result = "Failed To Update Sub Catagory Type 3";
					}
				}

			} else {

				if (!id.equalsIgnoreCase(hid)) {

					System.out.println(sub.getSubcategory_id());

					pstmt = conn
							.prepareStatement(SQLUtilConstants.UPDATE_SUBCATEGORY_TYPE3);

					pstmt.setString(1, sub.getCategorytypecode());
					pstmt.setString(2, sub.getSubcategory_code());
					pstmt.setString(3, sub.getSubcategory_code1());
					pstmt.setString(4, sub.getSubcategory_code2());
					pstmt.setString(5, sub.getSubcategorytypecode3());
					pstmt.setString(6, sub.getSubcategorytypename3());
					pstmt.setString(7, sub.getDescription());
					pstmt.setString(8, sub.getStatus());
					pstmt.setString(9, sub.getModify_by());
					pstmt.setTimestamp(10, HelperClass.getCurrentTimestamp());
					pstmt.setString(11, sub.getSubcategory_id());

					System.out.println(pstmt);

					count = pstmt.executeUpdate();

					if (count > 0) {
						result = "Updating Sub Catagory Type 3 Progressing...";
					} else {

						result = "Failed To Update Sub Catagory Type 3";
					}

				} else {
					boolean countid = false;
					countid = validateSubcategoryType3(sub);

					if (countid == true) {
						result = "Sub catagory type 3 code is already exist";
						System.out
						.println("Sub catagory type 3 code is already exist");
					} else {
						System.out.println(sub.getSubcategory_id());

						pstmt = conn
								.prepareStatement(SQLUtilConstants.UPDATE_SUBCATEGORY_TYPE3);

						pstmt.setString(1, sub.getCategorytypecode());
						pstmt.setString(2, sub.getSubcategory_code());
						pstmt.setString(3, sub.getSubcategory_code1());
						pstmt.setString(4, sub.getSubcategory_code2());
						pstmt.setString(5, sub.getSubcategorytypecode3());
						pstmt.setString(6, sub.getSubcategorytypename3());
						pstmt.setString(7, sub.getDescription());
						pstmt.setString(8, sub.getStatus());
						pstmt.setString(9, sub.getModify_by());
						pstmt.setTimestamp(10,
								HelperClass.getCurrentTimestamp());
						pstmt.setString(11, sub.getSubcategory_id());

						System.out.println(pstmt);

						count = pstmt.executeUpdate();

						if (count > 0) {
							result = "Updating Sub Catagory Type 3 Progressing...";
						} else {

							result = "Failed To Update Sub Catagory Type 3";
						}

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public List<SubCategoryTypeVO> getSubCategoryDetails3() {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<SubCategoryTypeVO> list = new ArrayList<SubCategoryTypeVO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con.prepareStatement(SQLUtilConstants.SUB_CATEGORY_DETAILS3);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SubCategoryTypeVO subcategorylist = new SubCategoryTypeVO();
				subcategorylist.setSubcategory_id(rs.getString("subcategory4_id"));
				subcategorylist.setCategorytypecode(rs.getString("category_name"));
				subcategorylist.setSubcategory_code(rs.getString("subcategory_name"));/* subcategory_id */
				subcategorylist.setSubcategory_code1(rs.getString("subcategory1_name"));
				subcategorylist.setSubcategory_code2(rs.getString("subcategory2_name"));
				subcategorylist.setSubcategorytypecode3(rs.getString("subcategory3_code"));
				subcategorylist.setSubcategorytypename3(rs.getString("subcategory3_name"));
				subcategorylist.setDescription(rs.getString("description"));
				subcategorylist.setStatus(rs.getString("status"));
				list.add(subcategorylist);
			}
			System.out.println("vmmmmmmmmmmvvvvvvvvvvv"+list);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
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
				+ " Control in LibraryDaoImpl : getAccessionNoList : Ending");

		return list;
	}

	public ArrayList<LibraryStockEntryVO> getAccessionNoList() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDaoImpl: getAccessionNoList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibraryStockEntryVO> accession_list = new ArrayList<LibraryStockEntryVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ACCESSION_NO);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibraryStockEntryVO vo = new LibraryStockEntryVO();
				vo.setAccessionNo(rs.getString("Accession_number"));
				vo.setEntry_id(rs.getString("Entry_id"));
				vo.setItemType(rs.getString("Item_type"));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setAuthor(rs.getString("Author"));
				vo.setCategory(rs.getString("Category"));
				vo.setShelfNo(rs.getString("Shelf_No"));
				vo.setCurrentStatus(rs.getString("Availability_status"));

				accession_list.add(vo);
			}
			System.out.println("inside getAccessionList...");
			System.out.println(accession_list);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
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
				+ " Control in LibraryDaoImpl : getAccessionNoList : Ending");

		return accession_list;
	}

	public ArrayList<CategoryTypeVO> getclassdescrlist(String cateid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDaoImpl: getclassdescrlist : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryTypeVO> clslist = new ArrayList<CategoryTypeVO>();

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_CLASSDESC_LIST);
			pstmt.setString(1, cateid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CategoryTypeVO vo = new CategoryTypeVO();
				vo.setSubcategorycode(rs.getString("subcategory_code"));
				vo.setSubcategoryname(rs.getString("subcategory_name"));
				clslist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDaoImpl :getclassdescrlist : Ending");
		return clslist;
	}

	public ArrayList<CategoryTypeVO> getlibcategorysectionlist(String cateid,
			String classid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDaoImpl: getlibcategorysectionlist : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryTypeVO> seclist = new ArrayList<CategoryTypeVO>();

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_SECTION_LIST);
			System.out.println(pstmt);
			pstmt.setString(1,cateid);
			pstmt.setString(2, classid);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				CategoryTypeVO vo = new CategoryTypeVO();
				vo.setSubcategorycode1(rs.getString("subcategory1_code"));
				vo.setSubcategoryname1(rs.getString("subcategory1_name"));
				seclist.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDaoImpl : getlibcategorysectionlist : Ending");
		return seclist;
	}

	@Override
	public ArrayList<CategoryTypeVO> getlibcategorydivisionlist(String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDaoImpl: getlibcategorysectionlist : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryTypeVO> divlist = new ArrayList<CategoryTypeVO>();

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_DIVISION_LIST);
			System.out.println(pstmt);
			pstmt.setString(1, sectionid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CategoryTypeVO vo = new CategoryTypeVO();
				vo.setSubcategorycode2(rs.getString("subcategory2_code"));
				vo.setSubcategoryname2(rs.getString("subcategory2_name"));
				divlist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDaoImpl : getlibcategorysectionlist : Ending");
		return divlist;
	}

	@Override
	public String saveStockEnteryDetails(LibraryStockEntryDetailsForm libform) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: saveStockEnteryDetails: Starting");
		Connection conn = null;
		PreparedStatement pstmt = null,pstmt1= null;
		ResultSet rs = null;
		IDGenerator id=new IDGenerator();
		int count=0;
		String result=null;
		String Hiddenid =libform.getEnteryid();
		String accno =libform.getAccessionNo();
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			System.out.println(libform.getEnteryid());
			if(!libform.getEnteryid().equalsIgnoreCase("") && libform.getEnteryid()!=null)
		    	{  
				 if(!libform.getAccessionNo().equalsIgnoreCase(libform.getHaccessionNo())){
				     String status=validateStockEnteryDetails(accno);
				 	  if(status.equalsIgnoreCase("true")){
								result="Accession Number already present";
								System.out.println("Accession Number already present");
				         }
				 	  else{
							pstmt = conn.prepareStatement(LibrarySqlUtils.STOCK_ENTRY_UPDATE);
							pstmt.setString(1, libform.getAccessionNo());
							pstmt.setString(2, libform.getItemType());
							pstmt.setString(3, HelperClass.convertUIToDatabase(libform.getRegdate()));
							pstmt.setString(4, libform.getBookTitle());
							pstmt.setString(5, libform.getAuthor());
							pstmt.setString(6, libform.getCategory());
							pstmt.setString(7, libform.getClassDescription());
							pstmt.setString(8, libform.getSectionDescription());
							pstmt.setString(9, libform.getLanguge());
							pstmt.setString(10, libform.getDivisionDescription());
							pstmt.setString(11, libform.getDdc());
							pstmt.setString(12, libform.getPublisher());
							pstmt.setString(13, libform.getNo_of_Copies());
							pstmt.setString(14, libform.getEdition());
							pstmt.setString(15, libform.getiSBNNo());
							pstmt.setString(16, libform.getBillNo());
							pstmt.setString(17, libform.getSize());
							pstmt.setString(18, libform.getSuppliedBy());
							pstmt.setString(19, libform.getGeneralInfo());
							pstmt.setString(20, libform.getPublicationYear());
							pstmt.setString(21, libform.getEditor());
							pstmt.setString(22, libform.getTotalCost());
							pstmt.setString(23, HelperClass.convertUIToDatabase(libform.getBillDate()));
							pstmt.setString(24, libform.getCost());
							pstmt.setString(25, libform.getNo_of_Pages());
							pstmt.setString(26, libform.getContentSearch());
							pstmt.setString(27, libform.getShelfNo());
							pstmt.setString(28, libform.getLocation());
							pstmt.setString(29, libform.getCurrentStatus());
							pstmt.setString(30, libform.getCreatedby());
							pstmt.setString(31, libform.getBookPhoto());
							pstmt.setString(32, libform.getEnteryid());
							
							count = pstmt.executeUpdate();

							if(count >0){
								pstmt1 = conn.prepareStatement("UPDATE campus_library_stock_entry SET total_available_copies=No_of_copies-no_of_issued WHERE Entry_id=?");
								pstmt1.setString(1, libform.getEnteryid());
								pstmt1.executeUpdate();
								result = "addsuccess";
							}
							else{
								result = "fail";
							}
				 	   }
				 }
				else{
				
				pstmt = conn.prepareStatement(LibrarySqlUtils.STOCK_ENTRY_UPDATE);
				pstmt.setString(1, libform.getAccessionNo());
				pstmt.setString(2, libform.getItemType());
				pstmt.setString(3, HelperClass.convertUIToDatabase(libform.getRegdate()));
				pstmt.setString(4, libform.getBookTitle());
				pstmt.setString(5, libform.getAuthor());
				pstmt.setString(6, libform.getCategory());
				pstmt.setString(7, libform.getClassDescription());
				pstmt.setString(8, libform.getSectionDescription());
				pstmt.setString(9, libform.getLanguge());
				pstmt.setString(10, libform.getDivisionDescription());
				pstmt.setString(11, libform.getDdc());
				pstmt.setString(12, libform.getPublisher());
				pstmt.setString(13, libform.getNo_of_Copies());
				pstmt.setString(14, libform.getEdition());
				pstmt.setString(15, libform.getiSBNNo());
				pstmt.setString(16, libform.getBillNo());
				pstmt.setString(17, libform.getSize());
				pstmt.setString(18, libform.getSuppliedBy());
				pstmt.setString(19, libform.getGeneralInfo());
				pstmt.setString(20, libform.getPublicationYear());
				pstmt.setString(21, libform.getEditor());
				pstmt.setString(22, libform.getTotalCost());
				pstmt.setString(23, HelperClass.convertUIToDatabase(libform.getBillDate()));
				pstmt.setString(24, libform.getCost());
				pstmt.setString(25, libform.getNo_of_Pages());
				pstmt.setString(26, libform.getContentSearch());
				pstmt.setString(27, libform.getShelfNo());
				pstmt.setString(28, libform.getLocation());
				pstmt.setString(29, libform.getCurrentStatus());
				pstmt.setString(30, libform.getCreatedby());
				pstmt.setString(31, libform.getBookPhoto());
				pstmt.setString(32, libform.getEnteryid());
				
				System.out.println(pstmt);

				count = pstmt.executeUpdate();

				if(count >0){
					pstmt1 = conn.prepareStatement("UPDATE campus_library_stock_entry SET total_available_copies=No_of_copies-no_of_issued WHERE Entry_id=?");
					pstmt1.setString(1, libform.getEnteryid());
					pstmt1.executeUpdate();
					result = "addsuccess";
				}
				else{
					result = "updatefail";
				}
				 
			}
		    	}
			
		      else  {
		         		
				String status=validateStockEnteryDetails(accno);
				if(status.equalsIgnoreCase("true")){
					result="Accession Number already present";
					System.out.println("Accession Number already present");
			         }
		         		
				else{
				pstmt = conn.prepareStatement(LibrarySqlUtils.STOCK_ENTRY_SAVE);
				pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_library_stock_entry"));
				pstmt.setString(2, libform.getAccessionNo());
				pstmt.setString(3, libform.getItemType());
				pstmt.setString(4, HelperClass.convertUIToDatabase(libform.getRegdate()));
				pstmt.setString(5, libform.getBookTitle());
				pstmt.setString(6, libform.getAuthor());
				pstmt.setString(7, libform.getCategory());
				pstmt.setString(8, libform.getClassDescription());
				pstmt.setString(9, libform.getSectionDescription());
				pstmt.setString(10, libform.getLanguge());
				pstmt.setString(11, libform.getDivisionDescription());
				pstmt.setString(12, libform.getDdc());
				pstmt.setString(13, libform.getPublisher());
				pstmt.setString(14, libform.getNo_of_Copies());
				pstmt.setString(15, libform.getEdition());
				pstmt.setString(16, libform.getiSBNNo());
				pstmt.setString(17, libform.getBillNo());
				pstmt.setString(18, libform.getSize());
				pstmt.setString(19, libform.getSuppliedBy());
				pstmt.setString(20, libform.getGeneralInfo());
				pstmt.setString(21, libform.getPublicationYear());
				pstmt.setString(22, libform.getEditor());
				pstmt.setString(23, libform.getTotalCost());
				pstmt.setString(24, HelperClass.convertUIToDatabase(libform.getBillDate()));
				pstmt.setString(25, libform.getCost());
				pstmt.setString(26, libform.getNo_of_Pages());
				pstmt.setString(27, libform.getContentSearch());
				pstmt.setString(28, libform.getShelfNo());
				pstmt.setString(29, libform.getLocation());
				pstmt.setString(30, libform.getCurrentStatus());
				pstmt.setString(31, libform.getCreatedby());
				pstmt.setString(32, libform.getBookPhoto());
				pstmt.setString(33, libform.getNo_of_Copies());
			
				count = pstmt.executeUpdate();
				System.out.println("save"+pstmt);
				if(count >0){

					result = "success";
				}
				else{
					result = "fail";
				}

				}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt1 != null) {
				try {
					pstmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDaoImpl :  saveStockEnteryDetails: Ending");
		return result;
	}



	@Override
	public List<LibraryStockEntryVO> getBookIssueDetailsByAccessionNo(LibraryStockEntryVO libVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getBookIssueDetailsByAccessionNo : getBookIssueDetailsByAccessionNo Starting");
		List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
		PreparedStatement pstmObj = null;
		PreparedStatement psmt1 = null;
		ResultSet rs = null;
		ResultSet rs1= null;
		Connection conn = null;
		String count=null;
		int count1=0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			psmt1=conn.prepareStatement("SELECT total_available_copies FROM campus_library_stock_entry WHERE Entry_id=?");
			psmt1.setString(1,libVo.getAccessionNo());
			System.out.println(psmt1);
			rs1=psmt1.executeQuery();
			while(rs1.next()){
				count=rs1.getString(1);
				count1=rs1.getInt(1);
			}
			System.out.println(count1);
			System.out.println(count1);
			System.out.println(count1);
			System.out.println(count1);
			System.out.println(count1);
			System.out.println(count1);
			System.out.println(count1);
			
			if(count!="0" && count1 >0){
			pstmObj = conn.prepareStatement(LibrarySqlUtils.GET_BOOK_ISSUE_DETAIL_BY_ACCESSIONID);
			pstmObj.setString(1, libVo.getAccessionNo());
			System.out.println(pstmObj);
			rs = pstmObj.executeQuery();
			System.out.println(pstmObj);
			while (rs.next()) {
				LibraryStockEntryVO form = new LibraryStockEntryVO();
				form.setItemType(rs.getString("Item_type"));
				form.setBookTitle(rs.getString("Book_title"));
				form.setAuthor(rs.getString("Author"));
				form.setCategory(rs.getString("Category"));
				form.setDdc(rs.getString("DDC"));
				form.setCurrentStatus(rs.getString("Availability_status"));
				form.setImageurl(rs.getString("Stock_Entry_Image"));
				form.setShelfNo(rs.getString("Shelf_No"));
				form.setLocation(rs.getString("library_location_name"));
				form.setLibLocId(rs.getString("library_loc_id"));
				libVoList.add(form);
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
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in getBookIssueDetailsByAccessionNo : getBookIssueDetailsByAccessionNo Ending");
		return libVoList;

	}
	@Override
	public String insertBookIssueDetails(LibraryStockEntryVO insert_issue) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in insertBookIssueDetails: insertBookIssueDetails : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		String result = null;
		int count = 0;
		IDGenerator id=new IDGenerator();
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.INSERT_BOOK_ISSUE_DETAILS);
           System.out.println(insert_issue.getToDate()+"To Date-==----");
           System.out.println(insert_issue.getIssued_date()+"Issue Date-==----");
           
			pstmt.setString(1, insert_issue.getAccessionNo());
			pstmt.setString(2, insert_issue.getIssued_to());
			pstmt.setString(3, insert_issue.getIssued_user_id());
			pstmt.setString(4, insert_issue.getLocation());
			pstmt.setString(5, HelperClass.convertUIToDatabase(insert_issue.getIssued_date()));
			pstmt.setString(6, HelperClass.convertUIToDatabase(insert_issue.getToDate()));
			pstmt.setString(7, "Issued");
			pstmt.setString(8, id.getPrimaryKeyID("campus_library_book_issue_details"));
			pstmt.setString(9, insert_issue.getSubscriberId());
			System.out.println(pstmt);

			count = pstmt.executeUpdate();
			
			if (count > 0) {
				result = "success";
				pstmt1 = conn.prepareStatement(LibrarySqlUtils.UPDATE_STOCKENTRY_BY_ISSUE);
				pstmt1.setString(1, insert_issue.getAccessionNo());
				System.out.println("*****************************************************");
				System.out.println(pstmt1);
				System.out.println("*****************************************************");
				pstmt1.executeUpdate();
			} else {
				result = "fail";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
		return result;
	}

	@Override
	public List<SubCategoryType1VO> getTableBycategorytypeandSub(
			String cattype, String status, String category, String subcacode1) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<SubCategoryType1VO> list = new ArrayList<SubCategoryType1VO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			// sub1.`category_code` LIKE ? AND sub1.`status` LIKE ? AND
			// sub1.`subcategory_code` LIKE ? AND sub1.`subcategory1_code` LIKE
			// ?
			pstmt = con
					.prepareStatement(SQLUtilConstants.SUB_CATEGORY_TYPE1_ON_CHANGE_1);
			pstmt.setString(1, category);
			pstmt.setString(2, status);
			pstmt.setString(3, cattype);
			pstmt.setString(4, subcacode1);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				SubCategoryType1VO subcategorytype1list = new SubCategoryType1VO();
				subcategorytype1list.setCategorytypecode(rs
						.getString("category_code"));
				subcategorytype1list.setCategorytypename(rs
						.getString("category_name"));
				subcategorytype1list.setSubcategorytypecode(rs
						.getString("subcategory_code"));
				subcategorytype1list.setSubcategorytypename(rs
						.getString("subcategory_name"));
				subcategorytype1list.setSubcategorytypecode(rs
						.getString("subcategory1_code"));
				subcategorytype1list.setSubcategorytype1_id(rs
						.getString("subcategory1_id"));
				subcategorytype1list.setSubcategorytype1code(rs
						.getString("subcategory1_code"));
				subcategorytype1list.setSubcategorytype1name(rs
						.getString("subcategory1_name"));
				subcategorytype1list.setStatus(rs.getString("status"));
				subcategorytype1list
				.setDescription(rs.getString("description"));

				list.add(subcategorytype1list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public SubCategoryTypeVO editSubCategoryType3(String id) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		SubCategoryTypeVO subcategorylist = null;
		ArrayList<SubCategoryTypeVO> list = new ArrayList<SubCategoryTypeVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(SQLUtilConstants.EDIT_SUB_CATEGORY_TYPE3);

			pstmt.setString(1, id);
			System.out.println("pstmt " + pstmt);
			rs = pstmt.executeQuery();

			System.out.println(rs);

			while (rs.next()) {
				subcategorylist = new SubCategoryTypeVO();

				// `category_code`,`subcategory_code`,`subcategory_name`,`description`,`status`
				subcategorylist.setSubcategory4_id(rs
						.getString("subcategory4_id"));
				subcategorylist.setCategory_id(rs.getString("category_code"));
				subcategorylist.setSubcategory_id(rs
						.getString("subcategory_code"));/* subcategory_id */
				subcategorylist.setSubcategory_id1(rs
						.getString("subcategory1_code"));
				subcategorylist.setSubcategory_id2(rs
						.getString("subcategory2_code"));
				subcategorylist.setSubcategorytypecode3(rs
						.getString("subcategory3_code"));
				subcategorylist.setSubcategorytypename3(rs
						.getString("subcategory3_name"));
				subcategorylist.setDescription(rs.getString("description"));
				subcategorylist.setStatus(rs.getString("status"));
				list.add(subcategorylist);

			}

		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
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

		return subcategorylist;

	}

	@Override
	public List<SubCategoryType1VO> getTableBycategorytypeandSub1(
			String cattype, String status, String category, String subcacode) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<SubCategoryType1VO> list = new ArrayList<SubCategoryType1VO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con
					.prepareStatement(LibrarySqlUtils.SUB_CATEGORY_TYPE1_ON_CHANGE_2);

			// sub1.`category_code` LIKE ? AND sub1.`status` LIKE ? AND
			// sub1.`subcategory_code` LIKE ? AND sub1.`subcategory1_code` LIKE
			// ?

			pstmt.setString(1, category);
			pstmt.setString(2, status);
			pstmt.setString(3, subcacode);
			pstmt.setString(4, cattype);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				SubCategoryType1VO subcategorytype1list = new SubCategoryType1VO();
				subcategorytype1list.setCategorytypecode(rs
						.getString("category_code"));
				subcategorytype1list.setCategorytypename(rs
						.getString("category_name"));
				subcategorytype1list.setSubcategorytypecode(rs
						.getString("subcategory_code"));
				subcategorytype1list.setSubcategorytypename(rs
						.getString("subcategory_name"));
				subcategorytype1list.setSubcategorytypecode(rs
						.getString("subcategory1_code"));
				subcategorytype1list.setSubcategorytype1_id(rs
						.getString("subcategory1_id"));
				subcategorytype1list.setSubcategorytype1code(rs
						.getString("subcategory1_code"));
				subcategorytype1list.setSubcategorytype1name(rs
						.getString("subcategory1_name"));
				subcategorytype1list.setStatus(rs.getString("status"));
				subcategorytype1list
				.setDescription(rs.getString("description"));

				list.add(subcategorytype1list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public List<SubCategoryType1VO> getTableByStatus(String status,
			String categorycode, String subcategorycode, String subcategory1code) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<SubCategoryType1VO> list = new ArrayList<SubCategoryType1VO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con
					.prepareStatement(LibrarySqlUtils.SUB_CATEGORY_TYPE1_STATUS);
			pstmt.setString(1, status);
			pstmt.setString(2, categorycode);
			pstmt.setString(3, subcategorycode);
			pstmt.setString(4, subcategory1code);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				SubCategoryType1VO subcategorytype1list = new SubCategoryType1VO();
				subcategorytype1list.setCategorytypecode(rs
						.getString("category_code"));
				subcategorytype1list.setCategorytypename(rs
						.getString("category_name"));
				subcategorytype1list.setSubcategorytypecode(rs
						.getString("subcategory_code"));
				subcategorytype1list.setSubcategorytypename(rs
						.getString("subcategory_name"));
				subcategorytype1list.setSubcategorytypecode(rs
						.getString("subcategory1_code"));
				subcategorytype1list.setSubcategorytype1_id(rs
						.getString("subcategory1_id"));
				subcategorytype1list.setSubcategorytype1code(rs
						.getString("subcategory1_code"));
				subcategorytype1list.setSubcategorytype1name(rs
						.getString("subcategory1_name"));
				subcategorytype1list.setStatus(rs.getString("status"));
				subcategorytype1list
				.setDescription(rs.getString("description"));

				list.add(subcategorytype1list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public List<SubCategoryType2VO> getTabBySub2CategoryType(String cattype,
			String status, String subcategory, String subcategory1,
			String subcategory2) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<SubCategoryType2VO> list = new ArrayList<SubCategoryType2VO>();
		try {

			con = JDBCConnection.getSeparateConnection();
			pstmt = con.prepareStatement(LibrarySqlUtils.SUB_CATEGORY_TYPE2_ON_CHANGE);
			pstmt.setString(1, cattype);
			pstmt.setString(2, subcategory);
			pstmt.setString(3, subcategory1);
			pstmt.setString(4, subcategory2);
			pstmt.setString(5, status);

			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				SubCategoryType2VO subcategorytype2list = new SubCategoryType2VO();
				subcategorytype2list.setCategorytypecode(rs
						.getString("category_code"));
				subcategorytype2list.setCategorytypename(rs
						.getString("category_name"));
				subcategorytype2list.setSubcategorytypecode(rs
						.getString("subcategory_code"));
				subcategorytype2list.setSubcategorytypename(rs
						.getString("subcategory_name"));
				subcategorytype2list.setSubcategorytype1code(rs
						.getString("subcategory1_code"));
				subcategorytype2list.setSubcategorytype1name(rs
						.getString("subcategory1_name"));
				subcategorytype2list.setSubcategorytype2_id(rs
						.getString("subcategory2_id"));
				subcategorytype2list.setSubcategorytype2code(rs
						.getString("subcategory2_code"));
				subcategorytype2list.setSubcategorytype2name(rs
						.getString("subcategory2_name"));
				subcategorytype2list.setStatus(rs.getString("status"));
				subcategorytype2list
				.setDescription(rs.getString("description"));

				list.add(subcategorytype2list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public List<SubCategoryType2VO> getTabBySub2subCategoryType(String cattype,
			String status, String subcategory, String subcategory1,
			String subcategory2) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<SubCategoryType2VO> list = new ArrayList<SubCategoryType2VO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con
					.prepareStatement(LibrarySqlUtils.SUB_CATEGORY_TYPE2_ON_CHANGE1);
			pstmt.setString(1, cattype);
			pstmt.setString(2, subcategory);
			pstmt.setString(3, subcategory1);
			pstmt.setString(4, subcategory2);
			pstmt.setString(5, status);

			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				SubCategoryType2VO subcategorytype2list = new SubCategoryType2VO();
				subcategorytype2list.setCategorytypecode(rs
						.getString("category_code"));
				subcategorytype2list.setCategorytypename(rs
						.getString("category_name"));
				subcategorytype2list.setSubcategorytypecode(rs
						.getString("subcategory_code"));
				subcategorytype2list.setSubcategorytypename(rs
						.getString("subcategory_name"));
				subcategorytype2list.setSubcategorytype1code(rs
						.getString("subcategory1_code"));
				subcategorytype2list.setSubcategorytype1name(rs
						.getString("subcategory1_name"));
				subcategorytype2list.setSubcategorytype2_id(rs
						.getString("subcategory2_id"));
				subcategorytype2list.setSubcategorytype2code(rs
						.getString("subcategory2_code"));
				subcategorytype2list.setSubcategorytype2name(rs
						.getString("subcategory2_name"));
				subcategorytype2list.setStatus(rs.getString("status"));
				subcategorytype2list
				.setDescription(rs.getString("description"));

				list.add(subcategorytype2list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public List<SubCategoryType2VO> getTabBySub2subCategory1Type(
			String cattype, String status, String subcategory,
			String subcategory1, String subcategory2) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<SubCategoryType2VO> list = new ArrayList<SubCategoryType2VO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con
					.prepareStatement(LibrarySqlUtils.SUB_CATEGORY_TYPE2_ON_CHANGE2);
			pstmt.setString(1, cattype);
			pstmt.setString(2, subcategory);
			pstmt.setString(3, subcategory1);
			pstmt.setString(4, subcategory2);
			pstmt.setString(5, status);

			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				SubCategoryType2VO subcategorytype2list = new SubCategoryType2VO();
				subcategorytype2list.setCategorytypecode(rs
						.getString("category_code"));
				subcategorytype2list.setCategorytypename(rs
						.getString("category_name"));
				subcategorytype2list.setSubcategorytypecode(rs
						.getString("subcategory_code"));
				subcategorytype2list.setSubcategorytypename(rs
						.getString("subcategory_name"));
				subcategorytype2list.setSubcategorytype1code(rs
						.getString("subcategory1_code"));
				subcategorytype2list.setSubcategorytype1name(rs
						.getString("subcategory1_name"));
				subcategorytype2list.setSubcategorytype2_id(rs
						.getString("subcategory2_id"));
				subcategorytype2list.setSubcategorytype2code(rs
						.getString("subcategory2_code"));
				subcategorytype2list.setSubcategorytype2name(rs
						.getString("subcategory2_name"));
				subcategorytype2list.setStatus(rs.getString("status"));
				subcategorytype2list
				.setDescription(rs.getString("description"));

				list.add(subcategorytype2list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public List<SubCategoryType1VO> searchSubCatType1(String searchname, String catcode, String subcatcode, String subcatcode1,String status) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		String sql=null;
		ArrayList<SubCategoryType1VO> list = new ArrayList<SubCategoryType1VO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			if(catcode == "%%" ){
				System.out.println("111111111");
				sql="SELECT sub1.subcategory1_id,sub1.category_code,sub1.subcategory_code,sub1.subcategory1_code,sub1.subcategory1_name,sub1.description,sub1.status,sub.subcategory_name,cat.category_name FROM library_subcategory1 sub1 LEFT JOIN library_subcategory sub ON sub.subcategory_code=sub1.subcategory_code LEFT JOIN library_category cat ON cat.category_code=sub1.category_code WHERE sub1.category_code LIKE '%%' and ";
			}else if(subcatcode == "%%" ){
				System.out.println("2222111111111");
				sql="SELECT sub1.subcategory1_id,sub1.category_code,sub1.subcategory_code,sub1.subcategory1_code,sub1.subcategory1_name,sub1.description,sub1.status,sub.subcategory_name,cat.category_name FROM library_subcategory1 sub1 LEFT JOIN library_subcategory sub ON sub.subcategory_code=sub1.subcategory_code LEFT JOIN library_category cat ON cat.category_code=sub1.category_code WHERE sub1.category_code LIKE '%%' and sub1.subcategory1_code LIKE '%%' and ";
			}else if(subcatcode1 == "%%" ){
				System.out.println("33333333111111111");
				sql="SELECT sub1.subcategory1_id,sub1.category_code,sub1.subcategory_code,sub1.subcategory1_code,sub1.subcategory1_name,sub1.description,sub1.status,sub.subcategory_name,cat.category_name FROM library_subcategory1 sub1 LEFT JOIN library_subcategory sub ON sub.subcategory_code=sub1.subcategory_code LEFT JOIN library_category cat ON cat.category_code=sub1.category_code WHERE sub1.category_code LIKE '%%' and sub1.subcategory1_code LIKE '%%' and sub1.subcategory1_code LIKE '%%' and ";
			}else{
				System.out.println("111111111");
				sql="SELECT sub1.subcategory1_id,sub1.category_code,sub1.subcategory_code,sub1.subcategory1_code,sub1.subcategory1_name,sub1.description,sub1.status,sub.subcategory_name,cat.category_name FROM library_subcategory1 sub1 LEFT JOIN library_subcategory sub ON sub.subcategory_code=sub1.subcategory_code LEFT JOIN library_category cat ON cat.category_code=sub1.category_code WHERE";
			}
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;

			if(!catcode.equals("all")){ 
				if(catcode != null){
					map.put("sub1.category_code",catcode);
					vec.add("sub1.category_code");
				}  
			}
			if(!subcatcode.equals("all")){ 
				if(subcatcode != null){
					map.put("sub1.subcategory_code",subcatcode);
					vec.add("sub1.subcategory_code");
				}  
			}
			if(!subcatcode1.equals("all")){ 
				if(subcatcode1 != null){
					map.put("sub1.subcategory1_code",subcatcode1);
					vec.add("sub1.subcategory1_code");
				}  
			}
			if(!status.equals("all")){ 
				if(status != null){
					map.put("sub1.status",status);
					vec.add("sub1.status");
				}  
			}
			Enumeration e = vec.elements();

			while ( e.hasMoreElements() ) {
				key = e.nextElement().toString();
				val = map.get(key).toString();

				if(count == 0) {
					wheresql= key+" like '"+val+"'";
					count++;
					System.out.println("mmmmmmvvvvvvvv"+wheresql);
				}else {
					wheresql = wheresql+" and "+key+" like '"+val+"'";
					System.out.println("wheresql"+wheresql);
				}
			}

			String query=sql+""+wheresql+" and "+"(sub1.subcategory1_name LIKE ? OR sub1.description LIKE ? OR sub.subcategory_name LIKE ? OR cat.category_name LIKE ? OR sub1.status LIKE ?)";
			//	pstmt = con.prepareStatement(LibrarySqlUtils.SUB_CATEGORY_TYPE1_SEARCH);
			pstmt = con.prepareStatement(query);
			System.out.println(query);
			System.out.println("searchname"+searchname);

			pstmt.setString(1, "%" + searchname + "%");
			pstmt.setString(2, "%" + searchname + "%");
			pstmt.setString(3, "%" + searchname + "%");
			pstmt.setString(4, "%" + searchname + "%");
			pstmt.setString(5, "%" + searchname + "%");
			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				SubCategoryType1VO subcategorytype1list = new SubCategoryType1VO();
				subcategorytype1list.setCategorytypecode(rs.getString("category_code"));
				subcategorytype1list.setCategorytypename(rs.getString("category_name"));
				subcategorytype1list.setSubcategorytypecode(rs.getString("subcategory_code"));
				subcategorytype1list.setSubcategorytypename(rs.getString("subcategory_name"));
				subcategorytype1list.setSubcategorytypecode(rs.getString("subcategory1_code"));
				subcategorytype1list.setSubcategorytype1_id(rs.getString("subcategory1_id"));
				subcategorytype1list.setSubcategorytype1code(rs.getString("subcategory1_code"));
				subcategorytype1list.setSubcategorytype1name(rs.getString("subcategory1_name"));
				subcategorytype1list.setStatus(rs.getString("status"));
				subcategorytype1list.setDescription(rs.getString("description"));
				list.add(subcategorytype1list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public List<SubCategoryType2VO> getTableBySub2Status(String cattype,
			String status, String subcategory, String subcategory1,
			String subcategory2) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<SubCategoryType2VO> list = new ArrayList<SubCategoryType2VO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con
					.prepareStatement(LibrarySqlUtils.SUB_CATEGORY_TYPE2_ON_STATUS);
			pstmt.setString(1, cattype);
			pstmt.setString(2, subcategory);
			pstmt.setString(3, subcategory1);
			pstmt.setString(4, subcategory2);
			pstmt.setString(5, status);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				SubCategoryType2VO subcategorytype2list = new SubCategoryType2VO();
				subcategorytype2list.setCategorytypecode(rs
						.getString("category_code"));
				subcategorytype2list.setCategorytypename(rs
						.getString("category_name"));
				subcategorytype2list.setSubcategorytypecode(rs
						.getString("subcategory_code"));
				subcategorytype2list.setSubcategorytypename(rs
						.getString("subcategory_name"));
				subcategorytype2list.setSubcategorytype1code(rs
						.getString("subcategory1_code"));
				subcategorytype2list.setSubcategorytype1name(rs
						.getString("subcategory1_name"));
				subcategorytype2list.setSubcategorytype2_id(rs
						.getString("subcategory2_id"));
				subcategorytype2list.setSubcategorytype2code(rs
						.getString("subcategory2_code"));
				subcategorytype2list.setSubcategorytype2name(rs
						.getString("subcategory2_name"));
				subcategorytype2list.setStatus(rs.getString("status"));
				subcategorytype2list
				.setDescription(rs.getString("description"));

				list.add(subcategorytype2list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public String ValidateSubcat(String subname) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		int count = 0;
		String subname_available = null;

		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con.prepareStatement(SQLUtilConstants.CHECK_SUBCATCODE);
			pstmt.setString(1, subname);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				count = rs.getInt("count");
				System.out.println(count);
			}
			if (count > 0) {

				subname_available = "true";

			} else {

				subname_available = "false";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		System.out.println(subname_available);
		return subname_available;
	}

	@Override
	public String ValidateSubcat3(String subname) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		int count = 0;
		String subname_available = null;

		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con.prepareStatement(SQLUtilConstants.CHECK_SUBCATCODE3);
			pstmt.setString(1, subname);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				count = rs.getInt("count");
			}

			if (count > 0) {

				subname_available = "true";

			} else {

				subname_available = "false";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		System.out.println(subname_available);

		return subname_available;
	}

	@Override
	public String ValidateSubcatupdate(String subname) {
		return null;
	}

	@Override
	public List<SubCategoryType1VO> getSubCategory2ByCategoryAndSubCategory(
			String subCategoryTypeCode) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<SubCategoryType1VO> list = new ArrayList<SubCategoryType1VO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con
					.prepareStatement(SQLUtilConstants.GET_CATEGORY_TYPE_2_DETAILS);
			pstmt.setString(1, subCategoryTypeCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SubCategoryType1VO subcategorytypelist = new SubCategoryType1VO();
				subcategorytypelist.setSubcategorytype1code(rs
						.getString("subcategory2_code"));
				subcategorytypelist.setSubcategorytype1name(rs
						.getString("subcategory2_name"));
				list.add(subcategorytypelist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public String inactiveSubCategoryType3(String[] id, SubCategoryTypeVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		PreparedStatement pstmt = null;
		Connection conn = null;
		int count = 0;
		String status = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			for(int i=0 ;i<id.length;i++){
				pstmt = conn.prepareStatement(SQLUtilConstants.INACTIVE_SUBCATEGORY_TYPE3);
				pstmt.setString(1, "Inactive");
				pstmt.setString(2, id[i]);
				count = pstmt.executeUpdate();
				System.out.println(pstmt);
				if (count > 0) {
					status = "Record Inactivated";
				} else {

					status = "Failed To Add The Record";
				}
			}


		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return status;
	}

	@Override
	public List<CategoryTypeVO> getSubCategoryDetails3(String cattype,
			String subcatcode, String subcatcode1, String subcatcode2,
			String subcatcode3, String status) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con.prepareStatement(SQLUtilConstants.SUB_CATEGORY3);
			pstmt.setString(1, cattype);
			pstmt.setString(2, subcatcode);
			pstmt.setString(3, subcatcode1);
			pstmt.setString(4, subcatcode2);
			pstmt.setString(5, subcatcode3);
			pstmt.setString(6, status);
			rs = pstmt.executeQuery();
			System.out.println("kjdsfhshgfosghosg:::" + pstmt);

			while (rs.next()) {
				CategoryTypeVO categorylist = new CategoryTypeVO();
				// sub.`subcategory_id`,cat.`category_name`,subcategoryid
				// sub.`category_code`,
				// sub.`subcategory_code`,sub.`subcategory_name`,sub.`description`,sub.`status`
				categorylist.setSubcategoryid(rs.getString("subcategory4_id"));
				categorylist.setCategoryname(rs.getString("category_name"));
				categorylist.setSubcategoryname(rs
						.getString("subcategory_name"));/* subcategory_id */
				categorylist.setSubcategoryname1(rs
						.getString("subcategory1_name"));
				categorylist.setSubcategoryname2(rs
						.getString("subcategory2_name"));
				categorylist.setSubcategorycode3(rs
						.getString("subcategory3_code"));
				categorylist.setSubcategoryname3(rs
						.getString("subcategory3_name"));
				categorylist.setDescription(rs.getString("description"));
				categorylist.setStatus(rs.getString("status"));
				list.add(categorylist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public List<CategoryTypeVO> getSubCategoryList3(String catcode,
			String subcatcode, String status) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con.prepareStatement(SQLUtilConstants.SUB_CATEGORY_2);
			pstmt.setString(1, catcode);
			pstmt.setString(2, status);
			pstmt.setString(3, subcatcode);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				CategoryTypeVO categorylist = new CategoryTypeVO();
				// sub.`subcategory_id`,cat.`category_name`,
				// sub.`category_code`,
				// sub.`subcategory_code`,sub.`subcategory_name`,sub.`description`,sub.`status`
				categorylist.setCategoryname(rs.getString("category_name"));
				categorylist.setSubcategoryname(rs
						.getString("subcategory_name"));/* subcategory_id */
				categorylist.setSubcategoryname1(rs
						.getString("subcategory1_name"));
				categorylist.setSubcategoryname2(rs
						.getString("subcategory2_name"));
				categorylist.setSubcategorycode3(rs
						.getString("subcategory3_code"));
				categorylist.setSubcategoryname3(rs
						.getString("subcategory3_name"));
				categorylist.setDescription(rs.getString("description"));
				categorylist.setStatus(rs.getString("status"));
				list.add(categorylist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public List<SubCategoryType1VO> getSubCategory3ByCategoryAndSubCategory(
			String subCategoryTypeCode) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<SubCategoryType1VO> list = new ArrayList<SubCategoryType1VO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con
					.prepareStatement(SQLUtilConstants.GET_CATEGORY_TYPE_3_DETAILS);
			pstmt.setString(1, subCategoryTypeCode);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				SubCategoryType1VO subcategorytypelist = new SubCategoryType1VO();
				subcategorytypelist.setSubcategorytype1code(rs
						.getString("subcategory3_code"));
				subcategorytypelist.setSubcategorytype1name(rs
						.getString("subcategory3_name"));
				list.add(subcategorytypelist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public List<CategoryTypeVO> SearchCategoryType3List(String catcode,
			String subcatcode, String subcatcode1, String subcatcode2,
			String subcatcode3, String status, String searchname) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
		String sql="";
		try {
			con = JDBCConnection.getSeparateConnection();
			System.out.println(catcode+subcatcode+subcatcode1+subcatcode2+subcatcode3);
			if(catcode == "%%" ){
				System.out.println("111111111");
				sql="SELECT ls3.`subcategory4_id`,ls3.`subcategory3_code`,ls3.`subcategory3_name`,ls3.`description`,ls3.`status`,ls2.`subcategory2_name`,ls1.`subcategory1_name`,ls.`subcategory_name`,lc.`category_name` FROM `library_subcategory3` ls3 LEFT JOIN `library_subcategory2` ls2 ON ls3.`subcategory2_code`=ls2.`subcategory2_code` LEFT JOIN `library_subcategory1` ls1 ON ls3.`subcategory1_code`=ls1.`subcategory1_code` LEFT JOIN `library_subcategory` ls ON ls3.`subcategory_code`=ls.`subcategory_code` LEFT JOIN `library_category` lc ON ls3.`category_code`=lc.`category_code`  where lc.`category_code` like '%%' and ";
			}else if(subcatcode == "%%" ){
				System.out.println("2222111111111");
				sql="SELECT ls3.`subcategory4_id`,ls3.`subcategory3_code`,ls3.`subcategory3_name`,ls3.`description`,ls3.`status`,ls2.`subcategory2_name`,ls1.`subcategory1_name`,ls.`subcategory_name`,lc.`category_name` FROM `library_subcategory3` ls3 LEFT JOIN `library_subcategory2` ls2 ON ls3.`subcategory2_code`=ls2.`subcategory2_code` LEFT JOIN `library_subcategory1` ls1 ON ls3.`subcategory1_code`=ls1.`subcategory1_code` LEFT JOIN `library_subcategory` ls ON ls3.`subcategory_code`=ls.`subcategory_code` LEFT JOIN `library_category` lc ON ls3.`category_code`=lc.`category_code`  where lc.`category_code` like '%%' and ls.subcategory_code like '%%' and ";
			}else if(subcatcode1 == "%%" ){
				System.out.println("33333333111111111");
				sql="SELECT ls3.`subcategory4_id`,ls3.`subcategory3_code`,ls3.`subcategory3_name`,ls3.`description`,ls3.`status`,ls2.`subcategory2_name`,ls1.`subcategory1_name`,ls.`subcategory_name`,lc.`category_name` FROM `library_subcategory3` ls3 LEFT JOIN `library_subcategory2` ls2 ON ls3.`subcategory2_code`=ls2.`subcategory2_code` LEFT JOIN `library_subcategory1` ls1 ON ls3.`subcategory1_code`=ls1.`subcategory1_code` LEFT JOIN `library_subcategory` ls ON ls3.`subcategory_code`=ls.`subcategory_code` LEFT JOIN `library_category` lc ON ls3.`category_code`=lc.`category_code`  where lc.`category_code` like '%%' and ls.subcategory_code like '%%' and ls1.subcategory1_code like '%%' and ";
			}else if(subcatcode2 == "%%" ){
				System.out.println("444444444111111111");
				sql="SELECT ls3.`subcategory4_id`,ls3.`subcategory3_code`,ls3.`subcategory3_name`,ls3.`description`,ls3.`status`,ls2.`subcategory2_name`,ls1.`subcategory1_name`,ls.`subcategory_name`,lc.`category_name` FROM `library_subcategory3` ls3 LEFT JOIN `library_subcategory2` ls2 ON ls3.`subcategory2_code`=ls2.`subcategory2_code` LEFT JOIN `library_subcategory1` ls1 ON ls3.`subcategory1_code`=ls1.`subcategory1_code` LEFT JOIN `library_subcategory` ls ON ls3.`subcategory_code`=ls.`subcategory_code` LEFT JOIN `library_category` lc ON ls3.`category_code`=lc.`category_code`  where lc.`category_code` like '%%' and ls.subcategory_code like '%%' and ls1.subcategory1_code like '%%' and ls2.subcategory2_code like '%%' and ";
			}else if(subcatcode3 == "%%" ){
				System.out.println("555555555111111111");
				sql="SELECT ls3.`subcategory4_id`,ls3.`subcategory3_code`,ls3.`subcategory3_name`,ls3.`description`,ls3.`status`,ls2.`subcategory2_name`,ls1.`subcategory1_name`,ls.`subcategory_name`,lc.`category_name` FROM `library_subcategory3` ls3 LEFT JOIN `library_subcategory2` ls2 ON ls3.`subcategory2_code`=ls2.`subcategory2_code` LEFT JOIN `library_subcategory1` ls1 ON ls3.`subcategory1_code`=ls1.`subcategory1_code` LEFT JOIN `library_subcategory` ls ON ls3.`subcategory_code`=ls.`subcategory_code` LEFT JOIN `library_category` lc ON ls3.`category_code`=lc.`category_code`  where lc.`category_code` like '%%' and ls.subcategory_code like '%%' and ls1.subcategory1_code like '%%' and ls2.subcategory2_code like '%%' and ls3.subcategory3_code like '%%' and ";
			}else{
				System.out.println("666666666111111111");
				sql="SELECT ls3.`subcategory4_id`,ls3.`subcategory3_code`,ls3.`subcategory3_name`,ls3.`description`,ls3.`status`,ls2.`subcategory2_name`,ls1.`subcategory1_name`,ls.`subcategory_name`,lc.`category_name` FROM `library_subcategory3` ls3 LEFT JOIN `library_subcategory2` ls2 ON ls3.`subcategory2_code`=ls2.`subcategory2_code` LEFT JOIN `library_subcategory1` ls1 ON ls3.`subcategory1_code`=ls1.`subcategory1_code` LEFT JOIN `library_subcategory` ls ON ls3.`subcategory_code`=ls.`subcategory_code` LEFT JOIN `library_category` lc ON ls3.`category_code`=lc.`category_code` where ";
			}

			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;

			System.out.println("vvmmvmvmv  "+catcode+" "+subcatcode+" "+subcatcode1+" "+subcatcode2+" "+subcatcode3+" "+status);

			if(!catcode.equals("all")){ 
				if(catcode != null){
					map.put("lc.category_code",catcode);
					vec.add("lc.category_code");
				}  
			}

			if(!subcatcode.equals("all")){ 
				if(subcatcode != null){
					map.put("ls.subcategory_code",subcatcode);
					vec.add("ls.subcategory_code");
				}  
			}

			if(!subcatcode1.equals("all")){ 
				if(subcatcode1 != null){
					map.put("ls1.subcategory1_code",subcatcode1);
					vec.add("ls1.subcategory1_code");
				}  
			}

			if(!subcatcode2.equals("all")){ 
				if(subcatcode2 != null){
					map.put("ls2.subcategory2_code",subcatcode2);
					vec.add("ls2.subcategory2_code");
				}  
			}

			if(!subcatcode3.equals("all")){ 
				if(subcatcode3 != null){
					map.put("ls3.subcategory3_code",subcatcode3);
					vec.add("ls3.subcategory3_code");
				}  
			}

			if(!status.equals("")){ 
				if(status != null){
					map.put("ls3.status",status);
					vec.add("ls3.status");
				}  
			}

			Enumeration e = vec.elements();

			while ( e.hasMoreElements() ) {
				key = e.nextElement().toString();
				val = map.get(key).toString();

				if(count == 0) {
					wheresql= key+" like '"+val+"'";
					count++;
					System.out.println("mmmmmmvvvvvvvv"+wheresql);
				}else {
					wheresql = wheresql+" and "+key+" like '"+val+"'";
					System.out.println("wheresql"+wheresql);
				}
			}

			String query=sql+""+wheresql+" and "+"(lc.category_name LIKE ? or ls.subcategory_name like ? or ls1.subcategory1_name like ? or ls2.subcategory2_name like ? or ls3.subcategory3_name like ? or ls3.status like ?)";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%"+searchname+"%");
			pstmt.setString(2, "%"+searchname+"%");
			pstmt.setString(3, "%"+searchname+"%");
			pstmt.setString(4, "%"+searchname+"%");
			pstmt.setString(5, "%"+searchname+"%");
			pstmt.setString(6, "%"+searchname+"%");
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CategoryTypeVO subcategorylist = new CategoryTypeVO();
				subcategorylist.setHiddencode(rs.getString("subcategory4_id"));
				subcategorylist.setCategoryname(rs.getString("category_name"));
				subcategorylist.setSubcategoryname(rs.getString("subcategory_name"));
				subcategorylist.setSubcategoryname1(rs.getString("subcategory1_name"));
				subcategorylist.setSubcategoryname2(rs.getString("subcategory2_name"));
				subcategorylist.setSubcategorycode3(rs.getString("subcategory3_code"));
				subcategorylist.setSubcategoryname3(rs.getString("subcategory3_name"));
				subcategorylist.setDescription(rs.getString("description"));
				subcategorylist.setStatus(rs.getString("status"));
				list.add(subcategorylist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return list;
	}

	public ArrayList<CategoryTypeVO> getcategorylist(String cateid,
			String status) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con.prepareStatement(SQLUtilConstants.CATEGORY_DETAIL_LIST);
			pstmt.setString(1, cateid);
			pstmt.setString(2, status);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CategoryTypeVO categorylist = new CategoryTypeVO();
				categorylist.setCategory_id(rs.getString("category_id"));
				categorylist.setCategorytypecode(rs.getString("category_code"));
				categorylist.setCategorytypename(rs.getString("category_name"));
				categorylist.setDescription(rs.getString("description"));
				categorylist.setStatus(rs.getString("status"));
				list.add(categorylist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	public ArrayList<LibrarySubscribVO> getOtherSubscribeNunmber(String loc,
			String subscriberType) {
		PreparedStatement psmt = null;
		Connection conn = null;
		ResultSet rs = null;
		ArrayList<LibrarySubscribVO> list = new ArrayList<LibrarySubscribVO>();

		try {

			conn = JDBCConnection.getSeparateConnection();
			if (subscriberType.equalsIgnoreCase("others")) {
				psmt = conn
						.prepareStatement(LibrarySqlUtils.getSubscriberNumber_other);
				psmt.setString(1, loc);
				System.out.println(psmt);
				rs = psmt.executeQuery();
				while (rs.next()) {
					LibrarySubscribVO vo = new LibrarySubscribVO();
					vo.setSubscriberNo(rs.getString("subscriberNumber"));
					vo.setOtherId(rs.getString("subscriberId"));
					list.add(vo);
				}
			} else if (subscriberType.equalsIgnoreCase("student")) {
				psmt = conn
						.prepareStatement(LibrarySqlUtils.getSubscriberNumber_student);
				psmt.setString(1, loc);
				rs = psmt.executeQuery();
				while (rs.next()) {
					LibrarySubscribVO vo = new LibrarySubscribVO();
					vo.setSubscriberNo(rs.getString("subscriberNumber"));
					vo.setOtherId(rs.getString("subscriberId"));
					list.add(vo);
				}
			} else {
				psmt = conn
						.prepareStatement(LibrarySqlUtils.getSubscriberNumber_staff);
				psmt.setString(1, loc);
				rs = psmt.executeQuery();
				while (rs.next()) {
					LibrarySubscribVO vo = new LibrarySubscribVO();
					vo.setSubscriberNo(rs.getString("subscriberNumber"));
					vo.setOtherId(rs.getString("subscriberId"));
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				psmt.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<LibrarySubscribVO> showBlockListedData(String loc,String subscriberType, String subscriberNo) {
		Connection conn=null;
		PreparedStatement psmt =null;
		ResultSet rs= null;
		ArrayList<LibrarySubscribVO> list = new ArrayList<LibrarySubscribVO>();
		try{
			conn=JDBCConnection.getSeparateConnection();
			if(subscriberType.equalsIgnoreCase("others")){
				psmt=conn.prepareStatement(LibrarySqlUtils.showBlockListedData_others);
				psmt.setString(1, loc);
				psmt.setString(2, subscriberNo);
				System.out.println("showBlockListedData"+psmt);
				rs=psmt.executeQuery();
				while(rs.next()){
					LibrarySubscribVO vo = new LibrarySubscribVO();
					vo.setOtherId(rs.getString("subscriberId"));
					vo.setOtherUserName(rs.getString("otherName"));
					vo.setOtherUserGender(rs.getString("otherGender"));
					vo.setOtherUserEmail(rs.getString("otherEmail"));
					vo.setOtherUserContact(rs.getString("otherContactNo"));
					vo.setOtherUserAddr(rs.getString("otherAddress"));
					vo.setDepositType(rs.getString("depositType"));
					vo.setStatus(rs.getString("status"));
					vo.setPaymentDate(rs.getString("paymentDate"));
					vo.setCardNo(rs.getString("cardNumber"));
					vo.setCheckNo(rs.getString("checkNumber"));
					vo.setEnterAmount(rs.getString("cashAmount"));
					list.add(vo);
				}
			}
			else if(subscriberType.equalsIgnoreCase("teacher")){
				psmt=conn.prepareStatement(LibrarySqlUtils.showBlockListedData_staff);
				psmt.setString(1, subscriberNo);
				psmt.setString(2, loc);
				System.out.println(psmt);
				rs=psmt.executeQuery();

				while(rs.next()){
					LibrarySubscribVO vo = new LibrarySubscribVO();
					vo.setStaffID(rs.getString("staffId"));
					vo.setAbbrId(rs.getString("Abbreviative_Id"));
					vo.setStaffName(rs.getString("staffName"));
					vo.setGender(rs.getString("gender"));
					vo.setDepartment(rs.getString("DEPT_NAME"));
					vo.setDesignation(rs.getString("designationName"));
					vo.setPresentAddress(rs.getString("presentAddress"));
					vo.setEmail(rs.getString("emailId"));
					vo.setPhoneNumber(rs.getString("mobileNo"));
					vo.setStatus(rs.getString("status"));
					vo.setPaymentDate(rs.getString("paymentDate"));
					vo.setRegid(rs.getString("registerId"));
					vo.setImageUrl(rs.getString("imagePath"));
					list.add(vo);
				}
			}else{
				psmt=conn.prepareStatement(LibrarySqlUtils.showBlockListedData_student);
				psmt.setString(1, subscriberNo);
				psmt.setString(2, loc);
				System.out.println(psmt);
				rs=psmt.executeQuery();

				while(rs.next()){
					LibrarySubscribVO vo = new LibrarySubscribVO();
					vo.setAdminssionNo(rs.getString("student_admissionno_var"));
					vo.setStudentName(rs.getString("studentName"));
					vo.setGender(rs.getString("student_gender_var"));
					vo.setRollNumber(rs.getString("student_rollno"));
					vo.setClassName(rs.getString("classdetails_name_var"));
					vo.setSectionName(rs.getString("classsection_name_var"));
					vo.setEmail(rs.getString("email"));
					vo.setPhoneNumber(rs.getString("mobileno"));
					vo.setPresentAddress(rs.getString("presentAddress"));
					vo.setStatus(rs.getString("status"));
					vo.setPaymentDate(rs.getString("paymentDate"));
					vo.setImageUrl(rs.getString("student_imgurl_var"));
					list.add(vo);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(psmt!=null && !(psmt.isClosed())){
					psmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}




			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<SubCategoryType2VO> getTabBySub2subCategory2Type(
			String cattype, String status, String subcategory,
			String subcategory1, String subcategory2) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<SubCategoryType2VO> list = new ArrayList<SubCategoryType2VO>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con
					.prepareStatement(LibrarySqlUtils.SUB_CATEGORY_TYPE2_ON_CHANGE3);
			pstmt.setString(1, cattype);
			pstmt.setString(2, subcategory);
			pstmt.setString(3, subcategory1);
			pstmt.setString(4, subcategory2);
			pstmt.setString(5, status);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				SubCategoryType2VO subcategorytype2list = new SubCategoryType2VO();
				subcategorytype2list.setCategorytypecode(rs
						.getString("category_code"));
				subcategorytype2list.setCategorytypename(rs
						.getString("category_name"));
				subcategorytype2list.setSubcategorytypecode(rs
						.getString("subcategory_code"));
				subcategorytype2list.setSubcategorytypename(rs
						.getString("subcategory_name"));
				subcategorytype2list.setSubcategorytype1code(rs
						.getString("subcategory1_code"));
				subcategorytype2list.setSubcategorytype1name(rs
						.getString("subcategory1_name"));
				subcategorytype2list.setSubcategorytype2_id(rs
						.getString("subcategory2_id"));
				subcategorytype2list.setSubcategorytype2code(rs
						.getString("subcategory2_code"));
				subcategorytype2list.setSubcategorytype2name(rs
						.getString("subcategory2_name"));
				subcategorytype2list.setStatus(rs.getString("status"));
				subcategorytype2list
				.setDescription(rs.getString("description"));

				list.add(subcategorytype2list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public List<SubCategoryType2VO> searchSubCatType2(String searchname, String categorytype, String subcategorytype, String subcategorytype1, String subcategorytype2,String status) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<SubCategoryType2VO> list = new ArrayList<SubCategoryType2VO>();
		String sql=null;
		try {
			con = JDBCConnection.getSeparateConnection();
			if(categorytype == "%%" ){
				System.out.println("111111111");
				sql="SELECT sub2.subcategory2_id,sub2.`category_code`,sub2.`subcategory_code`,sub2.`subcategory1_code`,sub2.`subcategory2_code`,sub2.`subcategory2_name`,sub2.`description`,sub2.`status`,sub1.`subcategory1_name`,lsc.`subcategory_name`,lc.`category_name` FROM `library_subcategory2` sub2 LEFT JOIN `library_subcategory1` sub1 ON sub2.`subcategory1_code`=sub1.`subcategory1_code` LEFT JOIN `library_subcategory` lsc ON sub2.`subcategory_code`=lsc.`subcategory_code` LEFT JOIN `library_category` lc ON sub2.`category_code`=lc.`category_code` WHERE sub2.`category_code` LIKE '%%' and ";
			}else if(subcategorytype == "%%" ){
				System.out.println("2222111111111");
				sql="SELECT sub2.subcategory2_id,sub2.`category_code`,sub2.`subcategory_code`,sub2.`subcategory1_code`,sub2.`subcategory2_code`,sub2.`subcategory2_name`,sub2.`description`,sub2.`status`,sub1.`subcategory1_name`,lsc.`subcategory_name`,lc.`category_name` FROM `library_subcategory2` sub2 LEFT JOIN `library_subcategory1` sub1 ON sub2.`subcategory1_code`=sub1.`subcategory1_code` LEFT JOIN `library_subcategory` lsc ON sub2.`subcategory_code`=lsc.`subcategory_code` LEFT JOIN `library_category` lc ON sub2.`category_code`=lc.`category_code` WHERE sub2.`category_code` LIKE '%%' AND sub2.`subcategory_code` LIKE '%%' and ";
			}else if(subcategorytype1 == "%%" ){
				System.out.println("33333333111111111");
				sql="SELECT sub2.subcategory2_id,sub2.`category_code`,sub2.`subcategory_code`,sub2.`subcategory1_code`,sub2.`subcategory2_code`,sub2.`subcategory2_name`,sub2.`description`,sub2.`status`,sub1.`subcategory1_name`,lsc.`subcategory_name`,lc.`category_name` FROM `library_subcategory2` sub2 LEFT JOIN `library_subcategory1` sub1 ON sub2.`subcategory1_code`=sub1.`subcategory1_code` LEFT JOIN `library_subcategory` lsc ON sub2.`subcategory_code`=lsc.`subcategory_code` LEFT JOIN `library_category` lc ON sub2.`category_code`=lc.`category_code` WHERE sub2.`category_code` LIKE '%%' AND sub2.`subcategory_code` LIKE '%%' AND sub2.`subcategory1_code` LIKE '%%' and ";
			}else if(subcategorytype2 == "%%" ){
				System.out.println("33333333111111111");
				sql="SELECT sub2.subcategory2_id,sub2.`category_code`,sub2.`subcategory_code`,sub2.`subcategory1_code`,sub2.`subcategory2_code`,sub2.`subcategory2_name`,sub2.`description`,sub2.`status`,sub1.`subcategory1_name`,lsc.`subcategory_name`,lc.`category_name` FROM `library_subcategory2` sub2 LEFT JOIN `library_subcategory1` sub1 ON sub2.`subcategory1_code`=sub1.`subcategory1_code` LEFT JOIN `library_subcategory` lsc ON sub2.`subcategory_code`=lsc.`subcategory_code` LEFT JOIN `library_category` lc ON sub2.`category_code`=lc.`category_code` WHERE sub2.`category_code` LIKE '%%' AND sub2.`subcategory_code` LIKE '%%' AND sub2.`subcategory1_code` LIKE '%%' AND sub2.`subcategory2_code` LIKE '%%' and ";
			}else{
				System.out.println("111111111");
				sql="SELECT sub2.subcategory2_id,sub2.`category_code`,sub2.`subcategory_code`,sub2.`subcategory1_code`,sub2.`subcategory2_code`,sub2.`subcategory2_name`,sub2.`description`,sub2.`status`,sub1.`subcategory1_name`,lsc.`subcategory_name`,lc.`category_name` FROM `library_subcategory2` sub2 LEFT JOIN `library_subcategory1` sub1 ON sub2.`subcategory1_code`=sub1.`subcategory1_code` LEFT JOIN `library_subcategory` lsc ON sub2.`subcategory_code`=lsc.`subcategory_code` LEFT JOIN `library_category` lc ON sub2.`category_code`=lc.`category_code` WHERE ";
			}
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;

			if(!categorytype.equals("all")){ 
				if(categorytype != null){
					map.put("sub2.`category_code`",categorytype);
					vec.add("sub2.`category_code`");
				}  
			}
			if(!subcategorytype.equals("all")){ 
				if(subcategorytype != null){
					map.put("sub2.`subcategory_code`",subcategorytype);
					vec.add("sub2.`subcategory_code`");
				}  
			}
			if(!subcategorytype1.equals("all")){ 
				if(subcategorytype1 != null){
					map.put("sub2.`subcategory1_code`",subcategorytype1);
					vec.add("sub2.`subcategory1_code`");
				}  
			}
			if(!subcategorytype2.equals("all")){ 
				if(subcategorytype2 != null){
					map.put("sub2.`subcategory2_code`",subcategorytype2);
					vec.add("sub2.`subcategory2_code`");
				}  
			}

			if(!status.equals("all")){ 
				if(status != null){
					map.put("sub2.status",status);
					vec.add("sub2.status");
				}  
			}

			Enumeration e = vec.elements();

			con = JDBCConnection.getSeparateConnection();

			while ( e.hasMoreElements() ) {
				key = e.nextElement().toString();
				val = map.get(key).toString();

				if(count == 0) {
					wheresql= key+" like '"+val+"'";
					count++;
					System.out.println("mmmmmmvvvvvvvv"+wheresql);
				}else {
					wheresql = wheresql+" and "+key+" like '"+val+"'";
					System.out.println("wheresql"+wheresql);
				}
			}

			String query=sql+""+wheresql+" and "+"(sub2.`subcategory2_code` LIKE ? OR sub2.`subcategory2_name` LIKE ? OR sub2.`description` LIKE ? OR sub1.`subcategory1_name` LIKE ? OR lsc.`subcategory_name` LIKE ? OR lc.`category_name` LIKE ? OR sub2.status LIKE ?)";
			//	pstmt = con.prepareStatement(LibrarySqlUtils.SUB_CATEGORY_TYPE1_SEARCH);
			pstmt = con.prepareStatement(query);
			System.out.println(query);
			System.out.println("searchname"+searchname);

			//	pstmt = con.prepareStatement(LibrarySqlUtils.SUB_CATEGORY_TYPE2_SEARCH);
			pstmt.setString(1, "%" + searchname + "%");
			pstmt.setString(2, "%" + searchname + "%");
			pstmt.setString(3, "%" + searchname + "%");
			pstmt.setString(4, "%" + searchname + "%");
			pstmt.setString(5, "%" + searchname + "%");
			pstmt.setString(6, "%" + searchname + "%");
			pstmt.setString(7, "%" + searchname + "%");
			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			while (rs.next()) {
				SubCategoryType2VO subcategorytype2list = new SubCategoryType2VO();
				subcategorytype2list.setCategorytypecode(rs.getString("category_code"));
				subcategorytype2list.setCategorytypename(rs.getString("category_name"));
				subcategorytype2list.setSubcategorytypecode(rs.getString("subcategory_code"));
				subcategorytype2list.setSubcategorytypename(rs.getString("subcategory_name"));
				subcategorytype2list.setSubcategorytype1code(rs.getString("subcategory1_code"));
				subcategorytype2list.setSubcategorytype1name(rs.getString("subcategory1_name"));
				subcategorytype2list.setSubcategorytype2_id(rs.getString("subcategory2_id"));
				subcategorytype2list.setSubcategorytype2code(rs.getString("subcategory2_code"));
				subcategorytype2list.setSubcategorytype2name(rs.getString("subcategory2_name"));
				subcategorytype2list.setStatus(rs.getString("status"));
				subcategorytype2list.setDescription(rs.getString("description"));
				list.add(subcategorytype2list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public int validateCategoryType(CategoryTypeVO sub1) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in validateStreamDetailsDaoImpl : validateStreamNameDetailsDao Starting");
		int SubCategoryType1_validate = 0;
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(LibrarySqlUtils.VALIDATE_CATEGORY_TYPE);
			pstmt.setString(1, sub1.getCategorytypecode());
			System.out.println("pstmt is " + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
		return count;

	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getOthersListDetails(
			String location, String select,String academic_year) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		/* int count = 0; */
		try {
			System.out.println(select);
			conn = JDBCConnection.getSeparateConnection();

			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.OtherslistdetailsbysubNo1);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.OtherslistdetailsbysubName);
			}
			pstmt.setString(1, location);
			pstmt.setString(2, academic_year);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				/* count++; */
				/* vo.setSlno(count); */
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setOthereName(rs.getString("otherName"));
				vo.setOthereContactNumber(rs.getString("otherContactNo"));
				vo.setOthereMail_Id(rs.getString("otherEmail"));
				vo.setOthereAddress(rs.getString("otherAddress"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByAnyWhere(String searchTextVal,String location,
			String select,String accyear) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.OTHER_LIST_SEACH_BY_ANYWHERE_BY_SUB_NO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.OTHER_LIST_SEACH_BY_ANYWHERE_BY_SUB_NAME);
			}
			//pstmt = conn.prepareStatement(LibrarySqlUtils.OTHER_LIST_SEACH_BY_ANYWHERE);
			pstmt.setString(1, "%" + searchTextVal + "%");
			pstmt.setString(2, "%" + searchTextVal + "%");
			pstmt.setString(3, "%" + searchTextVal + "%");
			pstmt.setString(4, "%" + searchTextVal + "%");
			pstmt.setString(5, "%" + searchTextVal + "%");
			pstmt.setString(6, "%" + searchTextVal + "%");
			pstmt.setString(7, location);
			pstmt.setString(8, accyear);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setOthereName(rs.getString("otherName"));
				vo.setOthereContactNumber(rs.getString("otherContactNo"));
				vo.setOthereMail_Id(rs.getString("otherEmail"));
				vo.setOthereAddress(rs.getString("otherAddress"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByStartWith(String searchTextVal,String location,String select,String accyear){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.OTHER_LIST_SEACH_BY_ANYWHERE_BY_SUB_NO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.OTHER_LIST_SEACH_BY_ANYWHERE_BY_SUB_NAME);
			}
			//pstmt = conn.prepareStatement(LibrarySqlUtils.OTHER_LIST_SEACH_BY_STARTWITH);
			pstmt.setString(1, searchTextVal + "%");
			pstmt.setString(2, searchTextVal + "%");
			pstmt.setString(3, searchTextVal + "%");
			pstmt.setString(4, searchTextVal + "%");
			pstmt.setString(5, searchTextVal + "%");
			pstmt.setString(6, searchTextVal + "%");
			pstmt.setString(7, location);
			pstmt.setString(8, accyear);
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setOthereName(rs.getString("otherName"));
				vo.setOthereContactNumber(rs.getString("otherContactNo"));
				vo.setOthereMail_Id(rs.getString("otherEmail"));
				vo.setOthereAddress(rs.getString("otherAddress"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}


	@Override
	public ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByEndsWith(String searchTextVal,String location,String select,String accyear) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();

		try {
			conn = JDBCConnection.getSeparateConnection();
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.OTHER_LIST_SEACH_BY_ANYWHERE_BY_SUB_NO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.OTHER_LIST_SEACH_BY_ANYWHERE_BY_SUB_NAME);
			}
			pstmt.setString(1, "%" + searchTextVal);
			pstmt.setString(2, "%" + searchTextVal);
			pstmt.setString(3, "%" + searchTextVal);
			pstmt.setString(4, "%" + searchTextVal);
			pstmt.setString(5, "%" + searchTextVal);
			pstmt.setString(6, "%" + searchTextVal);
			pstmt.setString(7, location);
			pstmt.setString(8, accyear);
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setOthereName(rs.getString("otherName"));
				vo.setOthereContactNumber(rs.getString("otherContactNo"));
				vo.setOthereMail_Id(rs.getString("otherEmail"));
				vo.setOthereAddress(rs.getString("otherAddress"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}
	@Override
	public LibrarySubscribVO gotosubscribersDetails(String location,
			String subId, String academic_year, String subscriberType) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		Connection conn = null;
		LibrarySubscribVO vo = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			System.out.println(subscriberType);
			if (subscriberType.equalsIgnoreCase("Student")) {
				pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_STUDENT_DETAIL_BY_SUBSCRIBER_DETAILS);
				pstmt1.setString(1,academic_year);
				pstmt1.setString(2,location);
				pstmt1.setString(3,subId);
				System.out.println(pstmt1);
				rs1 = pstmt1.executeQuery();
				while (rs1.next()) {
					vo = new LibrarySubscribVO();
					
					vo.setSubscriberId(rs1.getString("subscriberId"));
					vo.setLocation(rs1.getString("loc_ID"));
					vo.setLoc_ID(rs1.getString("library_location_name"));
					vo.setMainLoc(rs1.getString("Location_Id"));
					
					vo.setSubscriberType(rs1.getString("subscriberType"));
					vo.setSubscriberNo(rs1.getString("subscriberNumber"));
					vo.setAdminssionNo(rs1.getString("student_admissionno_var"));
					vo.setSubscriberName(rs1.getString("student"));
					vo.setGender(rs1.getString("student_gender_var"));
					vo.setRollNumber(rs1.getString("student_rollno"));
					vo.setClassName(rs1.getString("classdetails_name_var"));
					vo.setSectionName(rs1.getString("classsection_name_var"));
					vo.setContactNumber(rs1.getString("MobileNo"));
					vo.setEmail(rs1.getString("EmailId"));
					vo.setImageUrl(rs1.getString("student_imgurl_var"));
					vo.setDepositType(rs1.getString("depositType"));
					vo.setStatus(rs1.getString("status"));
					vo.setPaymentDate(rs1.getString("paymentDate"));
					vo.setOtherUserAddr(rs1.getString("presentAddress"));

				}
			}

			else if (subscriberType.equalsIgnoreCase("staff")) {
				pstmt1 = conn
						.prepareStatement(LibrarySqlUtils.GET_STAFF_DETAIL_BY_SUBSCRIBER_DETAILS);

				pstmt1.setString(1, academic_year);
				pstmt1.setString(2, location);
				pstmt1.setString(3, subId);
				System.out.println(pstmt1);
				rs1 = pstmt1.executeQuery();
				while (rs1.next()) {
					vo = new LibrarySubscribVO();
					vo.setSubscriberId(rs1.getString("subscriberId"));
					vo.setLocation(rs1.getString("loc_ID"));
					vo.setLoc_ID(rs1.getString("library_location_name"));
					vo.setMainLoc(rs1.getString("Location_Id"));
					
					vo.setSubscriberType(rs1.getString("subscriberType"));
					vo.setSubscriberNo(rs1.getString("subscriberNumber"));
					vo.setStaffID(rs1.getString("staffId"));
					vo.setAbbrId(rs1.getString("Abbreviative_Id"));
					vo.setSubscriberName(rs1.getString("teacher"));
					vo.setGender(rs1.getString("gender"));
					vo.setDepartment(rs1.getString("DEPT_NAME"));
					vo.setDesignation(rs1.getString("designationName"));
					vo.setContactNumber(rs1.getString("mobileNo"));
					vo.setEmail(rs1.getString("emailId"));
					vo.setImageUrl(rs1.getString("imagePath"));
					vo.setDepositType(rs1.getString("depositType"));
					vo.setStatus(rs1.getString("status"));
					vo.setPaymentDate(rs1.getString("paymentDate"));
					vo.setPresentAddress(rs1.getString("presentAddress"));

				}
			} else if (subscriberType.equalsIgnoreCase("Others")) {
				pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_OTHERS_DETAIL_BY_SUBSCRIBER_DETAILS);
				pstmt1.setString(1, academic_year);
				pstmt1.setString(2, location);
				pstmt1.setString(3, subId);
				System.out.println(pstmt1);
				rs1 = pstmt1.executeQuery();

				while (rs1.next()) {
					vo = new LibrarySubscribVO();
					vo.setSubscriberId(rs1.getString("subscriberId"));
					vo.setLocation(rs1.getString("loc_ID"));
					vo.setLoc_ID(rs1.getString("library_location_name"));
					vo.setMainLoc(rs1.getString("Location_Id"));
					
					vo.setSubscriberType(rs1.getString("subscriberType"));
					vo.setSubscriberNo(rs1.getString("subscriberNumber"));
					vo.setSubscriberName(rs1.getString("otherName"));
					vo.setGender(rs1.getString("otherGender"));
					vo.setContactNumber(rs1.getString("otherContactNo"));
					vo.setEmail(rs1.getString("otherEmail"));
					vo.setDepositType(rs1.getString("depositType"));
					vo.setStatus(rs1.getString("status"));
					vo.setPaymentDate(rs1.getString("paymentDate"));
					vo.setOtherUserAddr(rs1.getString("otherAddress"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
		return vo;
	}

	@Override
	public String updateSubscriberDetails(LibrarySubscribVO resultData) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;

		Connection conn = null;

		String result = null;
		int resultData1 = 0;

		try {

			String subscriberType = resultData.getSubscriberType();

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement("SELECT subscriberType FROM campus_library_subscriber_details WHERE subscriberId=?");
			pstmt.setString(1, resultData.getSubscriberId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				subscriberType = rs.getString(1);
			}

			if (subscriberType.equalsIgnoreCase("student")) {
				pstmt1 = conn
						.prepareStatement(LibrarySqlUtils.UPDATE_STUDENT_DETAIL_BY_SUBSCRIBER_DETAILS);

				pstmt1.setString(1, resultData.getDepositType());
				pstmt1.setString(2, resultData.getEnterAmount());
				pstmt1.setString(3, resultData.getCardNo());
				pstmt1.setString(4, resultData.getCheckNo());
				pstmt1.setString(5, resultData.getStatus());
				pstmt1.setString(6, resultData.getPaymentDate());
				pstmt1.setString(7, resultData.getOtherUserAddr());
				pstmt1.setString(8, resultData.getSubscriberId());
				pstmt1.setString(9, resultData.getLoc_ID());
				pstmt1.setString(10, resultData.getAccyear());

				System.out.println("student data" + pstmt1);

				resultData1 = pstmt1.executeUpdate();
			} else if (subscriberType.equalsIgnoreCase("teacher")) {
				pstmt1 = conn
						.prepareStatement(LibrarySqlUtils.UPDATE_STAFF_DETAIL_BY_SUBSCRIBER_DETAILS);

				pstmt1.setString(1, resultData.getDepositType());
				pstmt1.setString(2, resultData.getEnterAmount());
				pstmt1.setString(3, resultData.getCardNo());
				pstmt1.setString(4, resultData.getCheckNo());
				pstmt1.setString(5, resultData.getStatus());
				pstmt1.setString(6, resultData.getPaymentDate());

				pstmt1.setString(7, resultData.getSubscriberId());
				pstmt1.setString(8, resultData.getLoc_ID());
				pstmt1.setString(9, resultData.getAccyear());

				System.out.println("Staff data" + pstmt1);

				resultData1 = pstmt1.executeUpdate();
			} else if (subscriberType.equalsIgnoreCase("others")) {
				pstmt1 = conn
						.prepareStatement(LibrarySqlUtils.UPDATE_OTHERS_DETAIL_BY_SUBSCRIBER_DETAILS);

				pstmt1.setString(1, resultData.getOtherUserName());
				pstmt1.setString(2, resultData.getOtherUserGender());
				pstmt1.setString(3, resultData.getOtherUserContact());
				pstmt1.setString(4, resultData.getOtherUserEmail());

				pstmt1.setString(5, resultData.getDepositType());
				pstmt1.setString(6, resultData.getEnterAmount());
				pstmt1.setString(7, resultData.getCardNo());
				pstmt1.setString(8, resultData.getCheckNo());
				pstmt1.setString(9, resultData.getStatus());
				pstmt1.setString(10, resultData.getPaymentDate());
				pstmt1.setString(11, resultData.getOtherUserAddr());
				pstmt1.setString(12, resultData.getSubscriberId());
				pstmt1.setString(13, resultData.getLoc_ID());
				pstmt1.setString(14, resultData.getAccyear());

				System.out.println("Others data" + pstmt1);

				resultData1 = pstmt1.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}

				if (pstmt1 != null && (!pstmt1.isClosed()))
				{ pstmt1.close(); }

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}

		}
		if(resultData1>0){
			result="true";
		}
		else{
			result="failure";
		}

		return result;
	}


	@Override
	public LibrarySubscribVO IssueStatementBySubScriberType(String location, String subId, String academic_year,String subscriberType) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1=null;
		Connection conn = null;

		LibrarySubscribVO vo = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			System.out.println(subscriberType);
			if(subscriberType.equalsIgnoreCase("Student")){
				pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_STUDENT_DETAIL_BY_GOTO_ISSUESTATEMENT);
				pstmt1.setString(1,academic_year);
				pstmt1.setString(2,location);
				pstmt1.setString(3,subId);
				rs1=pstmt1.executeQuery();

				while(rs1.next()){
					vo = new LibrarySubscribVO();
					vo.setSubscriberId(rs1.getString("subscriberId"));
					vo.setSubscriberType(rs1.getString("subscriberType"));

					vo.setSubscriberName(rs1.getString("student")); /*student name*/
					vo.setRollNumber(rs1.getString("student_rollno")); /*roll No*/
					vo.setAdminssionNo(rs1.getString("student_admissionno_var")); /*Admission No*/
					vo.setSubscriberNo(rs1.getString("subscriberNumber")); /*sub No*/
					vo.setClassName(rs1.getString("classdetails_name_var"));  /*class name*/
					vo.setSectionName(rs1.getString("classsection_name_var")); /*section name*/
					vo.setStatus(rs1.getString("status")); /*status*/
				}
			}

			else if(subscriberType.equalsIgnoreCase("staff")){
				pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_STAFF_DETAIL_BY_GOTO_ISSUESTATEMENT);

				pstmt1.setString(1,subId);
				pstmt1.setString(2,academic_year);
				pstmt1.setString(3,location);

				System.out.println(pstmt1);
				rs1=pstmt1.executeQuery();				
				while(rs1.next()){
					vo = new LibrarySubscribVO();
					vo.setSubscriberId(rs1.getString("subscriberId"));
					vo.setSubscriberType(rs1.getString("subscriberType"));

					vo.setSubscriberNo(rs1.getString("subscriberNumber"));
					vo.setSubscriberName(rs1.getString("teacher"));
					vo.setDepartment(rs1.getString("DEPT_NAME"));
					vo.setDesignation(rs1.getString("designationName"));
					vo.setStatus(rs1.getString("status"));

				}

			}
			else if(subscriberType.equalsIgnoreCase("Others")){
				pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_OTHERS_DETAIL_BY_GOTO_ISSUESTATEMENT);

				pstmt1.setString(1,subId);
				pstmt1.setString(2,academic_year);
				pstmt1.setString(3,location);

				rs1=pstmt1.executeQuery();

				while(rs1.next()){
					vo = new LibrarySubscribVO();
					vo.setSubscriberId(rs1.getString("subscriberId"));
					vo.setSubscriberType(rs1.getString("subscriberType"));

					vo.setSubscriberNo(rs1.getString("subscriberNumber"));
					vo.setSubscriberName(rs1.getString("otherName"));
					vo.setContactNumber(rs1.getString("otherContactNo"));
					vo.setStatus(rs1.getString("status"));
				}

			}			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} 

		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			}
			catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return vo;
	}


	@Override
	public ArrayList<LibrarySearchSubscriberVO> IssueStatementTable(String location,String subId,String academic_year,String subscriberType) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {

			conn = JDBCConnection.getSeparateConnection();
			if(subscriberType.equalsIgnoreCase("Student"))
			{
				pstmt = conn.prepareStatement(LibrarySqlUtils.STUDENT_ISSUE_STATEMENT_TABLE);
				System.out.println(subscriberType);
			}
			else if(subscriberType.equalsIgnoreCase("staff"))
			{
				pstmt = conn.prepareStatement(LibrarySqlUtils.STUDENT_ISSUE_STATEMENT_TABLE);	
			}
			else if(subscriberType.equalsIgnoreCase("others"))
			{
				pstmt = conn.prepareStatement(LibrarySqlUtils.STUDENT_ISSUE_STATEMENT_TABLE);	
			}

			pstmt.setString(1,subId);
			pstmt.setString(2,academic_year);
			pstmt.setString(3,location);

			System.out.println(pstmt);

			rs=pstmt.executeQuery();

			while(rs.next()){
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setIssueId(rs.getString("issued_id"));
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setSubscriberType(rs.getString("subscriberType"));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setBookAuthor(rs.getString("Author"));
				vo.setIssueDate(rs.getString("book_issued_date"));

				studentData.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}

				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			}
			catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		return studentData;
	}

	@Override
	public LibrarySubscribVO issuestatementissue(String subId,String issueId,String subscriberType) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1=null;
		Connection conn = null;


		LibrarySubscribVO vo = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			if(subscriberType.equalsIgnoreCase("Student")){

				pstmt1=conn.prepareStatement(LibrarySqlUtils.GET_STUDENT_ISSUESTATEMENT_DETAIL_BY_ISSUE);

				//pstmt1.setString(1,issueId);
				pstmt1.setString(1,subId);

				System.out.println(pstmt1);
				rs1=pstmt1.executeQuery();

				while(rs1.next()){
					vo = new LibrarySubscribVO();
					vo.setSubscriberId(rs1.getString("subscriberId"));
					vo.setIssueId(rs1.getString("issued_id"));
					vo.setSubscriberType(rs1.getString("subscriberType"));
					vo.setAccessionNo(rs1.getString("Accession_number"));
					vo.setAdminssionNo(rs1.getString("student_admissionno_var"));
					vo.setClassName(rs1.getString("classdetails_name_var"));
					vo.setSectionName(rs1.getString("classsection_name_var"));
					vo.setItemType(rs1.getString("Item_type"));
					vo.setBookTitle(rs1.getString("Book_title"));
					vo.setAuthor(rs1.getString("Author"));
					vo.setCategory(rs1.getString("Category"));
					vo.setDdc(rs1.getString("DDC"));
					vo.setStatus(rs1.getString("Availability_status"));
					vo.setIssuedTo(rs1.getString("issued_to"));
					vo.setSubscriberName(rs1.getString("student"));
					vo.setImageUrl(rs1.getString("Stock_Entry_Image"));
					vo.setShelfNno(rs1.getString("Shelf_No"));

					vo.setLocation(rs1.getString("library_location_name"));
					vo.setIssuedDate(rs1.getString("book_issued_date"));
				}
			}
			else if(subscriberType.equalsIgnoreCase("staff")){
				pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_STAFF_ISSUESTATEMENT_DETAIL_BY_ISSUE);

				pstmt1.setString(1,subId);
				System.out.println(pstmt1);
				rs1=pstmt1.executeQuery();				
				while(rs1.next()){
					vo = new LibrarySubscribVO();
					vo.setSubscriberId(rs1.getString("subscriberId"));
					vo.setIssueId(rs1.getString("issued_id"));
					vo.setSubscriberType(rs1.getString("subscriberType"));
					vo.setAccessionNo(rs1.getString("Accession_number"));
					vo.setItemType(rs1.getString("item_type"));
					vo.setDepartment(rs1.getString("DEPT_NAME"));
					vo.setDesignation(rs1.getString("designationName"));
					vo.setItemType(rs1.getString("Item_type"));
					vo.setBookTitle(rs1.getString("Book_title"));
					vo.setAuthor(rs1.getString("Author"));
					vo.setCategory(rs1.getString("Category"));
					vo.setDdc(rs1.getString("DDC"));
					vo.setStatus(rs1.getString("Availability_status"));
					vo.setIssuedTo(rs1.getString("issued_to"));
					vo.setSubscriberName(rs1.getString("teacher"));
					vo.setImageUrl(rs1.getString("Stock_Entry_Image"));
					vo.setShelfNno(rs1.getString("Shelf_No"));

					vo.setLocation(rs1.getString("library_location_name"));
					vo.setIssuedDate(rs1.getString("book_issued_date"));
				}
			}
			
			else if(subscriberType.equalsIgnoreCase("others")){
				pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_OTHERS_ISSUESTATEMENT_DETAIL_BY_ISSUE);

				pstmt1.setString(1,subId);
				System.out.println(pstmt1);
				rs1=pstmt1.executeQuery();				
				while(rs1.next()){
					vo = new LibrarySubscribVO();
					vo.setSubscriberId(rs1.getString("subscriberId"));
					vo.setIssueId(rs1.getString("issued_id"));
					vo.setSubscriberType(rs1.getString("subscriberType"));
					vo.setAccessionNo(rs1.getString("Accession_number"));
					vo.setContactNumber(rs1.getString("otherContactNo"));
					vo.setOtherUserAddr(rs1.getString("otherAddress"));
					vo.setItemType(rs1.getString("Item_type"));
					vo.setBookTitle(rs1.getString("Book_title"));
					vo.setAuthor(rs1.getString("Author"));
					vo.setCategory(rs1.getString("Category"));
					vo.setDdc(rs1.getString("DDC"));
					vo.setStatus(rs1.getString("Availability_status"));
					vo.setIssuedTo(rs1.getString("issued_to"));
					vo.setSubscriberName(rs1.getString("otherName"));
					vo.setImageUrl(rs1.getString("Stock_Entry_Image"));
					vo.setShelfNno(rs1.getString("Shelf_No"));

					vo.setLocation(rs1.getString("library_location_name"));
					vo.setIssuedDate(rs1.getString("book_issued_date"));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			}
			catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return vo;
	}



	@Override
	public boolean validateSubcategoryType(SubCategoryTypeVO sub) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in validateStreamDetailsDaoImpl : validateStreamNameDetailsDao Starting");
		boolean SubCategoryType_validate = false;
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(LibrarySqlUtils.VALIDATE_SUB_CATEGORY_TYPE);
			pstmt.setString(1, sub.getSubcategorytypecode());
			System.out.println("pstmt is " + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				SubCategoryType_validate = true;
			} else {
				SubCategoryType_validate = false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
				+ " Control in validateDetailsDaoImpl : searchStreamNameDetailsDao  Ending");



		return SubCategoryType_validate;

	}


	public List<LibraryStockEntryVO> getAccessionNo(LibraryStockEntryVO registrationVo)
	{
		logger.setLevel(Level.DEBUG);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getAccessionNo : getAccessionNo Starting");

		ArrayList<LibraryStockEntryVO> getAccessionDetails = new ArrayList<LibraryStockEntryVO>();
		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			/*
			 * if(registrationVo.getLocid().equalsIgnoreCase("all")){
			 * registrationVo.setLocid("%%"); }
			 */
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ACCESSION_NUMBER_IN_RETURN);
			pstmt.setString(1,registrationVo.getAccessionNo()+"%");
			pstmt.setString(2,registrationVo.getSubscriberId());
			System.out.println(" query is " + pstmt);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibraryStockEntryVO libvo = new LibraryStockEntryVO();
				libvo.setAccessionNo(rs.getString("Accession_number"));
				libvo.setAccessionNoId(rs.getString("Entry_id"));
				getAccessionDetails.add(libvo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
				+ " Control in getAccessionNo : getAccessionNo Ending");
		return getAccessionDetails;
	}

	@Override
	public boolean validateSubcategoryType3(SubCategoryTypeVO sub) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in validateStreamDetailsDaoImpl : validateStreamNameDetailsDao Starting");
		boolean SubCategoryType3_validate = false;
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(LibrarySqlUtils.VALIDATE_SUB_CATEGORY_TYPE3);
			pstmt.setString(1, sub.getSubcategorytypecode3());
			System.out.println("pstmt is " + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				SubCategoryType3_validate = true;
			} else {
				SubCategoryType3_validate = false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
				+ " Control in validateDetailsDaoImpl : searchStreamNameDetailsDao  Ending");

		return SubCategoryType3_validate;
	}


	@Override
	public List<LibraryStockEntryDetailsForm> getStockEntryBookList() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: getStockEntryBookList: Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<LibraryStockEntryDetailsForm> StockList = new ArrayList<LibraryStockEntryDetailsForm>();
		try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.STOCK_ENTRY_LIST);
			System.out.println(pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				LibraryStockEntryDetailsForm from=new LibraryStockEntryDetailsForm();


				from.setEnteryid(rs.getString("Entry_id"));
				from.setAccessionNo(rs.getString("Accession_number"));
				from.setItemType(rs.getString("Item_type"));
				from.setRegdate( HelperClass.convertDatabaseToUI(rs.getString("Reg_date")));
				from.setBookTitle(rs.getString("Book_title"));
				from.setAuthor(rs.getString("Author"));
				from.setDdc(rs.getString("DDC"));
				from.setNo_of_Copies(rs.getString("No_of_copies"));
				from.setLocation(rs.getString("library_location_name"));
				from.setCurrentStatus(rs.getString("Availability_status"));
				StockList.add(from);
			}


		} catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			if(rs!=null){
				try{
					rs.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(conn !=null){
				try{
					conn.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL :getStockEntryBookList  Ending");

		return StockList;
	}

	@Override
	public LibraryStockEntryDetailsForm editStockEntryDetail(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: editStockEntryDetail Starting");
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		LibraryStockEntryDetailsForm lfrom=null;
		try {
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.STOCK_ENTRY_EDIT);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();

			while(rs.next()){
				lfrom= new LibraryStockEntryDetailsForm();
				lfrom.setAccessionNo(rs.getString("Accession_number"));
				lfrom.setItemType(rs.getString("Item_type"));
				lfrom.setRegdate(HelperClass.convertDatabaseToUI(rs.getString("Reg_date")));
				lfrom.setBookTitle(rs.getString("Book_title"));
				lfrom.setAuthor(rs.getString("Author"));
				lfrom.setCategory(rs.getString("Category"));
				lfrom.setClassDescription(rs.getString("Class_desc"));
				lfrom.setSectionDescription(rs.getString("Section_desc"));
				lfrom.setDivisionDescription(rs.getString("Division_desc"));
				lfrom.setLanguge(rs.getString("Language"));
				lfrom.setDdc(rs.getString("DDC"));
				lfrom.setPublisher(rs.getString("Publisher"));
				System.out.println("////////////////////////////////"+rs.getString("Publisher"));
				lfrom.setNo_of_Copies(rs.getString("No_of_copies"));
				lfrom.setCost(rs.getString("Cost_per_copy"));
				lfrom.setTotalCost(rs.getString("Total_Cost"));
				lfrom.setBillNo(rs.getString("Bill_No"));
				lfrom.setSize(rs.getString("Size"));
				lfrom.setSuppliedBy(rs.getString("Supplied_By"));
				lfrom.setGeneralInfo(rs.getString("General_Info"));
				lfrom.setPublicationYear(rs.getString("Publish_year"));
				lfrom.setEdition(rs.getString("Edition"));
				lfrom.setEditor(rs.getString("Editor"));
				lfrom.setiSBNNo(rs.getString("ISBN_No"));
				lfrom.setBillDate(HelperClass.convertDatabaseToUI(rs.getString("Bill_date")));
				lfrom.setNo_of_Pages(rs.getString("No_of_pages"));
				lfrom.setContentSearch(rs.getString("Content_search"));
				lfrom.setShelfNo(rs.getString("Shelf_No"));
				lfrom.setLocation(rs.getString("Library_location"));
				lfrom.setCurrentStatus(rs.getString("Availability_status"));
				lfrom.setBookPhoto(rs.getString("Stock_Entry_Image"));



			}}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(rs!=null){
				try{
					rs.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL :editStockEntryDetail  Ending");
		return lfrom;
	}


	public String blockTheSubscriber(String subscribeNo) {
		PreparedStatement psmt = null;
		int count=0;
		ResultSet rs =null;
		Connection conn =null;
		String status =null;

		try{
			conn=JDBCConnection.getSeparateConnection();
			psmt=conn.prepareStatement(LibrarySqlUtils.blockTheSubscriber);
			psmt.setString(1, subscribeNo);
			System.out.println("block --------------------------------------------------"+psmt);
			count=psmt.executeUpdate();
			if(count>0){
				status="true";
			}else{
				status="false";
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				conn.close();
				psmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return status;
	}

	public ArrayList<LibrarySubscribVO> getStaffRegId(String loc, String searchterm) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySubscribVO> teacherData = new ArrayList<LibrarySubscribVO>();

		try{
			conn=JDBCConnection.getSeparateConnection();
			//psmt=conn.prepareStatement("SELECT `registerId` FROM `campus_teachers` WHERE `Loc_ID`=? AND  registerId LIKE ?" );
			psmt=conn.prepareStatement("SELECT t.`registerId` FROM `campus_teachers` t LEFT JOIN campus_library_location cll ON cll.loc_id=t.`Loc_ID` WHERE  registerId LIKE ?  AND cll.library_loc_id = ?");
			psmt.setString(2, loc);
			psmt.setString(1, searchterm+"%");
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ AUTOCOMPLETE FOR STAFF REG_ID"+psmt);
			rs=psmt.executeQuery();
			while (rs.next()) {
				LibrarySubscribVO title = new LibrarySubscribVO();
				title.setTeacherId(rs.getString("registerId"));
				teacherData.add(title);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
		return teacherData;
	}

	public String duplicateDataCheck(LibrarySubsciberDetailsPojo pojo) {
		Connection conn =null;
		ResultSet rs = null;
		PreparedStatement psmt =null;
		int count =0;
		String result =null;

		try
		{	conn=JDBCConnection.getSeparateConnection();
		if(pojo.getSuscriberType().equalsIgnoreCase("student")){
			psmt=conn.prepareStatement("SELECT COUNT(`studentId`) FROM `campus_library_subscriber_details` WHERE `subscriberType`='student' AND studentId=? AND `loc_ID`=? AND `accyear_ID`=?");
			psmt.setString(1, pojo.getStudentIdHidden());
			psmt.setString(2, pojo.getLocationName());
			psmt.setString(3, pojo.getAccId());
			System.out.println(psmt);
			rs=psmt.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
			if (count > 0) {
				result = "dupStudent";
			} else {
				result = "noDup";
			}
		}else{
			conn=JDBCConnection.getSeparateConnection();
			psmt=conn.prepareStatement("SELECT COUNT(`staffId`) FROM `campus_library_subscriber_details` WHERE `staffId`=? AND `subscriberType`='teacher' AND `loc_ID`=? AND `accyear_ID`=? ");
			psmt.setString(1, pojo.getStaffid());
			psmt.setString(2, pojo.getLocationName());
			psmt.setString(3, pojo.getAccId());
			System.out.println("staff  ^^^^^^^^^^^^^^^ duplicateDataCheck ^^^^^^^^^^^^^^^^^^^^^^^^ Data "+psmt);
			rs=psmt.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
				System.out.println("count"+count);
			}
			if (count > 0) {
				result = "dupTeacher";
			} else {
				result = "noDup";
			}
		}

		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public String activeSubCategoryType(String[] id, SubCategoryTypeVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		ResultSet rs0 = null;
		PreparedStatement pstmt0 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		String subcategory_code = null;
		Connection conn = null;
		int count = 0;
		String status = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			for (int i = 0; i < id.length; i++) {
				pstmt0 = conn.prepareStatement("SELECT * FROM library_subcategory WHERE subcategory_id=?");
				pstmt0.setString(1, id[i]);
				rs0 = pstmt0.executeQuery();
				while (rs0.next()) {
					subcategory_code = rs0.getString("category_code");
				}
				pstmt = conn.prepareStatement(SQLUtilConstants.INACTIVE_SUBCATEGORY_TYPE);
				pstmt.setString(1, "Active");
				pstmt.setString(2, id[i]);
				count = pstmt.executeUpdate();
				if (count > 0){
					pstmt1 = conn.prepareStatement(SQLUtilConstants.ACTIVE_CATEGORY_TYPE);
					pstmt1.setString(1, subcategory_code);
					count = pstmt1.executeUpdate();
					if (count > 0) {
						status = "Record Activated";
					} else {
						status = "Failed To Activate The Record";
					}
				} else {
					status = "Failed To Activate The Record";
				}
			}
			System.out.println(pstmt);
			System.out.println(pstmt1);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs0 != null && (!rs0.isClosed())) {
					rs0.close();
				}
				if (pstmt0 != null && (!pstmt0.isClosed())) {
					pstmt0.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
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
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");
		return status;
	}


	@Override
	public String activeSubCategoryType3(String[] id, SubCategoryTypeVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		ResultSet rs0 = null;
		PreparedStatement pstmt0 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		String category_code = null;
		Connection conn = null;

		int count = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		String status = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			for(int i=0 ;i<id.length;i++){
				pstmt0 = conn.prepareStatement("SELECT * FROM library_subcategory3 WHERE subcategory4_id=?");
				pstmt0.setString(1, id[i]);

				rs0 = pstmt0.executeQuery();
				while (rs0.next()) {
					category_code = rs0.getString("category_code");
				}

				pstmt = conn.prepareStatement(SQLUtilConstants.INACTIVE_SUBCATEGORY_TYPE3);
				pstmt.setString(1, "Active");
				pstmt.setString(2, id[i]);
				count = pstmt.executeUpdate();
				if (count > 0) {
					pstmt1 = conn
							.prepareStatement(SQLUtilConstants.ACTIVE_SUB2_CATEGORY_TYPE);
					pstmt1.setString(1, category_code);
					count1 = pstmt1.executeUpdate();
					if (count > 0) {
						pstmt1 = conn
								.prepareStatement(SQLUtilConstants.ACTIVE_SUB1_CATEGORY_TYPE);
						pstmt1.setString(1, category_code);
						count1 = pstmt1.executeUpdate();
						if (count1 > 0) {
							pstmt2 = conn
									.prepareStatement(SQLUtilConstants.ACTIVE_SUB_CATEGORY_TYPE);
							pstmt2.setString(1, category_code);
							count2 = pstmt2.executeUpdate();
							if (count2 > 0) {
								pstmt3 = conn
										.prepareStatement(SQLUtilConstants.ACTIVE_CATEGORY_TYPE);
								pstmt3.setString(1, category_code);
								count3 = pstmt3.executeUpdate();

								if (count3 > 0) {
									status = "Record Activated";
								} else {
									status = "Failed To Activate The Record";
								}
							} else {
								status = "Record Activated";
							}
						} else {
							status = "Record Activated";
						}
					} else {
						status = "Record Activated";
					}
				} else {
					status = "Failed To Activate The Record";
				}

			}
			System.out.println(pstmt0);
			System.out.println(pstmt);
			System.out.println(pstmt1);
			System.out.println(pstmt2);
			System.out.println(pstmt3);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs0 != null && (!rs0.isClosed())) {
					rs0.close();
				}
				if (pstmt0 != null && (!pstmt0.isClosed())) {
					pstmt0.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmt3 != null && (!pstmt3.isClosed())) {
					pstmt3.close();
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
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");
		return status;
	}



	public ArrayList<LibrarySubscribVO> getBlockListedStaffData(String accyear,String location) {
		Connection conn =null;
		ResultSet rs = null;
		PreparedStatement psmt =null;
		ArrayList<LibrarySubscribVO> list = new ArrayList<LibrarySubscribVO>();
		try
		{	
			conn=JDBCConnection.getSeparateConnection();
			psmt=conn.prepareStatement(LibrarySqlUtils.getBlockListedStaffData);
			psmt.setString(1,location );
			psmt.setString(2, accyear);
			System.out.println(psmt);
			rs=psmt.executeQuery();
			while(rs.next()){
				LibrarySubscribVO vo = new LibrarySubscribVO();
				vo.setSubscriberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("staffName"));
				vo.setDepartment(rs.getString("DEPT_NAME"));
				vo.setDesignation(rs.getString("designationName"));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<LibrarySubscribVO> getBlockListedStudentData(java.lang.String accyear, java.lang.String location) {
		Connection conn =null;
		ResultSet rs = null;
		PreparedStatement psmt =null;
		ArrayList<LibrarySubscribVO> list = new ArrayList<LibrarySubscribVO>();
		try
		{	

			conn=JDBCConnection.getSeparateConnection();
			psmt=conn.prepareStatement(LibrarySqlUtils.getBlockListedStudentData);
			psmt.setString(1,location );
			psmt.setString(2, accyear);
			System.out.println(psmt);
			rs=psmt.executeQuery();
			while(rs.next()){
				LibrarySubscribVO vo = new LibrarySubscribVO();
				vo.setSubscriberNo(rs.getString("subscriberNumber"));
				vo.setAdminssionNo(rs.getString("student_admissionno_var"));
				vo.setStudentName(rs.getString("studentName"));
				vo.setClassName(rs.getString("classdetails_name_var"));
				vo.setSectionName(rs.getString("classsection_name_var"));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return list;
	}
	public ArrayList<LibrarySubscribVO> getBlockListedOtherData(String accyear,String location) {
		Connection conn =null;
		ResultSet rs = null;
		PreparedStatement psmt =null;
		ArrayList<LibrarySubscribVO> list = new ArrayList<LibrarySubscribVO>();
		try
		{	
			conn=JDBCConnection.getSeparateConnection();
			psmt=conn.prepareStatement(LibrarySqlUtils.getBlockListedOtherData);
			psmt.setString(1,location );
			psmt.setString(2, accyear);
			System.out.println(psmt);
			rs=psmt.executeQuery();
			while(rs.next()){
				LibrarySubscribVO vo = new LibrarySubscribVO();
				vo.setSubscriberNo(rs.getString("subscriberNumber"));
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setOtherUserName(rs.getString("otherName"));
				vo.setOtherUserContact(rs.getString("otherContactNo"));
				vo.setOtherUserEmail(rs.getString("otherEmail"));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return list;
	}

				@Override
				public String validateStockEnteryDetails(String accno) {
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in LibraryDAOIMPL: validateStockEnteryDetails : Starting");

					Connection conn =null;
					PreparedStatement pstmt=null;
					ResultSet rs=null;
					int count= 0;
					String	result=null;
					try{
						conn=JDBCConnection.getSeparateConnection();
						pstmt=conn.prepareStatement(LibrarySqlUtils.VALIDATE_ACCNO_COUNT);
						pstmt.setString(1, accno.trim());
						rs=pstmt.executeQuery();
						while(rs.next()){

							count= rs.getInt(1);
						}
						if(count>0){
							result="true";
						}
						else{
							result="false";
						}
					}
					catch(Exception e){
						e.printStackTrace();
					}
					finally{
						if(rs!=null){
							try{
								rs.close();
							}
							catch(Exception e){
								e.printStackTrace();
							}
						}
						if(pstmt!=null){
							try{
								pstmt.close();
							}
							catch(Exception e){
								e.printStackTrace();
							}
						}
						if(conn!=null){
							try{
								conn.close();
							}
							catch(Exception e){
								e.printStackTrace();
							}
						}
					}

					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())+ " Control in LibraryDAOIMPL: validateStockEnteryDetails  : Ending");
					return  result;
				}


			@Override

			public String insertBookReturnDetails(LibraryStockEntryVO insert_issue) {
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in insertBookIssueDetails: insertBookIssueDetails : Starting");
				Connection conn = null;
				PreparedStatement pstmt = null;
				PreparedStatement pstmt1 = null;
				PreparedStatement pstmt2= null;
				ResultSet rs = null;
				String result = null;
				int count = 0;
				IDGenerator id=new IDGenerator();
				try {
					conn = JDBCConnection.getSeparateConnection();
					pstmt = conn.prepareStatement(LibrarySqlUtils.INSERT_BOOK_RETURN_DETAILS);
					pstmt.setString(1, insert_issue.getAccessionNo());
					pstmt.setString(2, insert_issue.getIssued_to());
					pstmt.setString(3, insert_issue.getIssued_user_id());
					pstmt.setString(4, insert_issue.getLocation());
					pstmt.setString(5, HelperClass.convertUIToDatabase(insert_issue.getIssued_date()));
					pstmt.setString(6, HelperClass.convertUIToDatabase(insert_issue.getIssued_return_date()));
					pstmt.setString(7,"Returned");
					pstmt.setString(8,id.getPrimaryKeyID("campus_library_book_return_details"));
					pstmt.setString(9, insert_issue.getSubscriberId());
					pstmt.setString(10, insert_issue.getIssueId());
					count = pstmt.executeUpdate();
                   System.out.println(pstmt);
					if (count > 0) {
						result = "success";
						/*UPDATE campus_library_stock_entry SET no_of_issued=no_of_issued-1,total_available_copies=total_available_copies+1 WHERE Accession_number LIKE ?*/
                        pstmt1= conn.prepareStatement(LibrarySqlUtils.UPDATE_ISSUE_DETAILS_BY_RETURN);
                        pstmt1.setString(1, insert_issue.getSubscriberId());
                        pstmt1.setString(2, insert_issue.getAccessionNo());
						System.out.println(pstmt1);
						pstmt1.executeUpdate();
						
						pstmt2= conn.prepareStatement(LibrarySqlUtils.UPDATE_STOCKENTRY_BY_RETURN);
                        pstmt2.setString(1, insert_issue.getAccessionNo());
                        System.out.println("*****************************");
						System.out.println(pstmt2);
						System.out.println("*****************************");
						pstmt2.executeUpdate();
					} else {
						result = "fail";
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (rs != null && (!rs.isClosed())) {
							rs.close();
						}
						if (pstmt != null && (!pstmt.isClosed())) {
							pstmt.close();
						}
						if (pstmt1 != null && (!pstmt1.isClosed())) {
							pstmt1.close();
						}
						if (pstmt2!= null && (!pstmt2.isClosed())) {
							pstmt2.close();
						}
						if (conn != null && (!conn.isClosed())) {
							conn.close();
						}
					} catch (SQLException e) {

						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}
		return result;
	}

	public String unblockSubscriber(String id) {
		Connection conn =null;
		int rs =0;
		PreparedStatement psmt =null;
		String result =null;
		ArrayList<LibrarySubscribVO> list = new ArrayList<LibrarySubscribVO>();
		try
		{	
			conn=JDBCConnection.getSeparateConnection();
			psmt=conn.prepareStatement(LibrarySqlUtils.unblockSubscriber);
			psmt.setString(1,id );
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+psmt);
			rs=psmt.executeUpdate();
			System.out.println("^^^^^^^^^^^^^^rsrs^^^^^^^^^^^^^^^"+rs);
			if(rs>0){
				result="true";
			}
			else{
				result="false";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return result;
	}

			

	public List<LibraryStockEntryVO> getBookReturnDetailsByAccessionNo(LibraryStockEntryVO libVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getBookReturnDetailsByAccessionNo : getBookReturnDetailsByAccessionNo Starting");
		List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmObj = conn.prepareStatement(LibrarySqlUtils.GET_BOOK_RETURN_DETAIL_BY_ACCESSIONID);
			pstmObj.setString(1, libVo.getSubscriberId().split("-")[1]);
			pstmObj.setString(2, libVo.getAccessionNo()+"%");
			System.out.println(pstmObj);

			rs = pstmObj.executeQuery();
			while (rs.next()) {
				LibraryStockEntryVO form = new LibraryStockEntryVO();
				form.setIssueId(rs.getString("issued_id"));
				form.setItemType(rs.getString("Item_type"));
				form.setBookTitle(rs.getString("Book_title"));
				form.setAuthor(rs.getString("Author"));
				form.setCategory(rs.getString("Category"));
				form.setDdc(rs.getString("DDC"));
				form.setCurrentStatus(rs.getString("Availability_status"));
				form.setImageurl(rs.getString("Stock_Entry_Image"));
				form.setShelfNo(rs.getString("Shelf_No"));
				form.setLocation(rs.getString("library_location_name"));
				form.setLibLocId(rs.getString("library_loc_id"));
				form.setIssued_date(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
				libVoList.add(form);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in getBookReturnDetailsByAccessionNo : getBookReturnDetailsByAccessionNo Ending");
		return libVoList;
	}

	@Override
	public List<LibraryStockEntryVO> getAccessionNoByIssue(LibraryStockEntryVO registrationVo) {
		logger.setLevel(Level.DEBUG);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getAccessionNo : getAccessionNo Starting");

		ArrayList<LibraryStockEntryVO> getAccessionDetails = new ArrayList<LibraryStockEntryVO>();
		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			/*if(registrationVo.getLocid().equalsIgnoreCase("all")){
					registrationVo.setLocid("%%");
				}*/
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ACCESSION_NUMBER_BY_ISSUE);
			pstmt.setString(1,registrationVo.getAccessionNo()+"%");

			System.out.println(" query is "+pstmt);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibraryStockEntryVO libvo = new LibraryStockEntryVO();
				libvo.setAccessionNo(rs.getString("Accession_number"));
				libvo.setAccessionNoId(rs.getString("Entry_id"));
				getAccessionDetails.add(libvo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
				+ " Control in getAccessionNo : getAccessionNo Ending");
		return getAccessionDetails;
	}


	@Override

	public ArrayList<CategoryTypeVO> getCategoryListBySearch(String cateid, String status, String searchname) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
		String sql="";
		try {
			con = JDBCConnection.getSeparateConnection();
			if(cateid == "%%" ){
				sql="select * from library_category where category_code like '%%' and ";
			}else{
				sql="select * from library_category where ";
			}

			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;

			if(!cateid.equals("%%")){ 
				if(cateid != null){
					map.put("category_code",cateid);
					vec.add("category_code");
				}  
			}
			if(!status.equals("")){ 
				if(status != null){
					map.put("status",status);
					vec.add("status");
				}  
			}

			Enumeration e = vec.elements();

			while ( e.hasMoreElements() ) {
				key = e.nextElement().toString();
				val = map.get(key).toString();

				if(count == 0) {
					wheresql=key+"='"+val+"'";
					count++;
				}else {
					wheresql = wheresql+" and "+key+"='"+val+"'";
				}
			}
			String query=sql+""+wheresql+" and "+"(category_code like ? or category_name like ? or status like ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%"+searchname+"%");
			pstmt.setString(2, "%"+searchname+"%");
			pstmt.setString(3, "%"+searchname+"%");
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CategoryTypeVO categorylist = new CategoryTypeVO();
				categorylist.setCategory_id(rs.getString("category_id"));
				categorylist.setCategorytypecode(rs.getString("category_code"));
				categorylist.setCategorytypename(rs.getString("category_name"));
				categorylist.setDescription(rs.getString("description"));
				categorylist.setStatus(rs.getString("status"));
				list.add(categorylist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override

	public String activeSubCategoryType1(String[] id,
			SubCategoryType1VO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		ResultSet rs0 = null;
		PreparedStatement pstmt0 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String subcategory_code = null;
		Connection conn = null;
		String category_code=null;
		int count = 0;
		int count1 =0;
		int count2 =0;
		String status = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			for (int i = 0; i < id.length; i++) {
				pstmt0 = conn.prepareStatement("SELECT * FROM library_subcategory1 WHERE subcategory1_id=?");
				pstmt0.setString(1, id[i]);
				rs0 = pstmt0.executeQuery();
				while (rs0.next()) {
					category_code=rs0.getString("category_code");
					subcategory_code = rs0.getString("subcategory_code");
				}
				pstmt = conn.prepareStatement(LibrarySqlUtils.ACTIVE_SUBCATEGORY_TYPE1);
				pstmt.setString(1, id[i]);
				count = pstmt.executeUpdate();
				if (count > 0){
					pstmt1 = conn.prepareStatement(LibrarySqlUtils.ACTIVE_SUBCATEGORY_TYPE);
					pstmt1.setString(1, subcategory_code);
					count1 = pstmt1.executeUpdate();
					if (count1 > 0) {
						pstmt2 = conn.prepareStatement(LibrarySqlUtils.ACTIVE_CATEGORY_TYPE);
						pstmt2.setString(1, category_code);
						count2 = pstmt2.executeUpdate();
						if (count2 > 0) {
							status = "Record Activated";
						} else {
							status = "Failed To Activate The Record";
						}
					} else {
						status = "Failed To Activate The Record";
					}
				} else {
					status = "Failed To Activate The Record";
				}
			}
			System.out.println(pstmt);
			System.out.println(pstmt1);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs0 != null && (!rs0.isClosed())) {
					rs0.close();
				}
				if (pstmt0 != null && (!pstmt0.isClosed())) {
					pstmt0.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
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
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");
		return status;
	}

	@Override
	public String activeSubCategoryType2(String[] id,
			SubCategoryType2VO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		ResultSet rs0 = null;
		PreparedStatement pstmt0 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		String subcategory_code = null;
		String subcategory1_code=null;
		Connection conn = null;
		String category_code=null;
		int count = 0;
		int count1 =0;
		int count2 =0;
		int count3 =0;
		String status = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			for (int i = 0; i < id.length; i++) {
				pstmt0 = conn.prepareStatement("SELECT * FROM library_subcategory2 WHERE subcategory2_id=?");
				pstmt0.setString(1, id[i]);
				rs0 = pstmt0.executeQuery();
				while (rs0.next()) {
					category_code=rs0.getString("category_code");
					subcategory_code = rs0.getString("subcategory_code");
					subcategory1_code=rs0.getString("subcategory1_code");
				}
				pstmt = conn.prepareStatement(LibrarySqlUtils.ACTIVE_SUBCATEGORY_TYPE2);
				pstmt.setString(1, id[i]);
				count = pstmt.executeUpdate();
				if (count > 0){
					pstmt1 = conn.prepareStatement(LibrarySqlUtils.ACTIVE_SUBCATEGORY_TYPE1_IN_2);
					pstmt1.setString(1, subcategory1_code);
					count1 = pstmt1.executeUpdate();
					if (count1 > 0) {
						pstmt2 = conn.prepareStatement(LibrarySqlUtils.ACTIVE_SUBCATEGORY_TYPE_IN_2);
						pstmt2.setString(1, subcategory_code);
						count2 = pstmt2.executeUpdate();
						if (count2 > 0) {
							pstmt3 = conn.prepareStatement(LibrarySqlUtils.ACTIVE_CATEGORY_TYPE_IN_2);
							pstmt3.setString(1, category_code);
							count3 = pstmt2.executeUpdate();
							if (count3 > 0) {
								status = "Record Activated";
							} else {
								status = "Failed To Activate The Record";
							}
						} else {
							status = "Failed To Activate The Record";
						}
					} else {
						status = "Failed To Activate The Record";
					}
				} else {
					status = "Failed To Activate The Record";
				}
			}
			System.out.println(pstmt);
			System.out.println(pstmt1);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs0 != null && (!rs0.isClosed())) {
					rs0.close();
				}
				if (pstmt0 != null && (!pstmt0.isClosed())) {
					pstmt0.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
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
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");
		return status;
	}

	@Override
	public String activeCategoryType(String[] id, CategoryTypeVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: activeCategoryType : Starting");
		PreparedStatement pstmt = null;
		Connection conn = null;
		int count = 0;
		String status = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			for (int i = 0; i < id.length; i++) {

				pstmt = conn.prepareStatement(LibrarySqlUtils.ACTIVE_CATEGORY_TYPE_IN_1);
				pstmt.setString(1, id[i]);
				count = pstmt.executeUpdate();
				if (count > 0){
					status = "Record Activated";
				}else{
					status = "Failed To Activate The Record";
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LibraryDAOIMPL: activeCategoryType  : Ending");
		return status;
	}

	@Override
	public LibrarySubscribVO GOtOIssueReturns(String subId, String subscriberType,String issueId) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1=null;
		Connection conn = null;


		LibrarySubscribVO vo = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			if(subscriberType.equalsIgnoreCase("Student")){

				System.out.println(subscriberType);
				System.out.println(subscriberType);
				pstmt1=conn.prepareStatement(LibrarySqlUtils.GET_STUDENT_ISSUERETURN_DETAIL);

				pstmt1.setString(1,subId);

				System.out.println(pstmt1);
				rs1=pstmt1.executeQuery();

				while(rs1.next()){
					vo = new LibrarySubscribVO();
					vo.setSubscriberId(rs1.getString("subscriberId"));
					vo.setIssueId(rs1.getString("return_id"));
					vo.setSubscriberType(rs1.getString("subscriberType"));
					vo.setSubscriberName(rs1.getString("student"));
					vo.setAccessionNo(rs1.getString("Accession_number"));
					vo.setAdminssionNo(rs1.getString("student_admissionno_var"));
					vo.setClassName(rs1.getString("classdetails_name_var"));
					vo.setSectionName(rs1.getString("classsection_name_var"));
					vo.setItemType(rs1.getString("item_type"));
					vo.setBookTitle(rs1.getString("book_titile"));
					vo.setAuthor(rs1.getString("author"));
					vo.setCategory(rs1.getString("category"));
					vo.setDdc(rs1.getString("DDC"));
					vo.setIssuedTo(rs1.getString("issued_to"));
					vo.setStatus(rs1.getString("current_status"));
					vo.setImageUrl(rs1.getString("Stock_Entry_Image"));
					vo.setShelfNno(rs1.getString("shelf_no"));

					vo.setLocationName(rs1.getString("library_location_name"));
					vo.setIssuedDate(rs1.getString("book_issued_date"));
					vo.setReturnedDate(rs1.getString("book_returned_date"));
				}
			}
			else if(subscriberType.equalsIgnoreCase("staff")){
				pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_STAFF_ISSUERETURN_DETAIL);

				pstmt1.setString(1,subId);
				System.out.println(pstmt1);
				rs1=pstmt1.executeQuery();				
				while(rs1.next()){
					vo = new LibrarySubscribVO();
					vo.setSubscriberId(rs1.getString("subscriberId"));
					vo.setIssueId(rs1.getString("return_id"));
					vo.setSubscriberType(rs1.getString("subscriberType"));
					vo.setSubscriberName(rs1.getString("teacher"));
					vo.setAccessionNo(rs1.getString("Accession_number"));
					vo.setDepartment(rs1.getString("DEPT_NAME"));
					vo.setDesignation(rs1.getString("designationName"));
					vo.setItemType(rs1.getString("item_type"));
					vo.setBookTitle(rs1.getString("book_titile"));
					vo.setAuthor(rs1.getString("author"));
					vo.setCategory(rs1.getString("category"));
					vo.setDdc(rs1.getString("DDC"));
					vo.setStatus(rs1.getString("current_status"));
					vo.setIssuedTo(rs1.getString("issued_to"));
					vo.setImageUrl(rs1.getString("Stock_Entry_Image"));
					vo.setShelfNno(rs1.getString("shelf_no"));

					vo.setLocationName(rs1.getString("library_location_name"));
					vo.setIssuedDate(rs1.getString("book_issued_date"));
					vo.setReturnedDate(rs1.getString("book_returned_date"));
				}
			}
			else if(subscriberType.equalsIgnoreCase("others")){
				pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_OTHERS_ISSUERETURN_DETAIL);

				pstmt1.setString(1,subId);
				System.out.println(pstmt1);
				rs1=pstmt1.executeQuery();				
				while(rs1.next()){
					vo = new LibrarySubscribVO();
					vo.setSubscriberId(rs1.getString("subscriberId"));
					vo.setIssueId(rs1.getString("return_id"));
					vo.setSubscriberType(rs1.getString("subscriberType"));
					vo.setSubscriberName(rs1.getString("others"));
					vo.setAccessionNo(rs1.getString("Accession_number"));
					vo.setContactNumber(rs1.getString("otherContactNo"));
					vo.setOtherUserAddr(rs1.getString("otherAddress"));
					vo.setItemType(rs1.getString("item_type"));
					vo.setBookTitle(rs1.getString("book_titile"));
					vo.setAuthor(rs1.getString("author"));
					vo.setCategory(rs1.getString("category"));
					vo.setDdc(rs1.getString("DDC"));
					vo.setStatus(rs1.getString("current_status"));
					vo.setIssuedTo(rs1.getString("issued_to"));
					vo.setImageUrl(rs1.getString("Stock_Entry_Image"));
					vo.setShelfNno(rs1.getString("shelf_no"));

					vo.setLocationName(rs1.getString("library_location_name"));
					vo.setIssuedDate(rs1.getString("book_issued_date"));
					vo.setReturnedDate(rs1.getString("book_returned_date"));
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			}
			catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return vo;
	}

	public List<StudentRegistrationVo> studentSearchbyadmissionNoForlibrary(
			StudentRegistrationVo registrationVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : studentSearchbyadmissionNoForlibrary Starting");
		String searchTerm = registrationVo.getSearchTerm() + "%";
		List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmObj = conn
					.prepareStatement(LibrarySqlUtils.STUDENT_SEARCH_BY_ADMISSION_FOR_LIBRARY);

			pstmObj.setString(1, searchTerm);
			pstmObj.setString(2, registrationVo.getLocationId());
			//pstmObj.setString(3, registrationVo.getAcademicYear());

			System.out.println("admission No query is "+pstmObj);

			rs = pstmObj.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setAdmissionNo(rs.getString("student_admissionno_var"));
				registrationList.add(studentRegistrationVo);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in LibraryDAOIMPL : studentSearchbyadmissionNoForlibrary Ending");

		return registrationList;
	}

	@Override
	public String publisherSettings(LibraryStockEntryVO obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : publisherSettings Starting");
		Connection conn=null;
		PreparedStatement pstmt=null;

		int count=0;
		String result=null; 
		String hiddenid=obj.getEntry_id();
		String pub=obj.getPublisher();
		String address =obj.getAddress();
		String email = obj.getEmail();
		String telphone = obj.getTelephone();
		String mobilenum = obj.getMobilenum();
		
		try{
			conn=JDBCConnection.getSeparateConnection();
			if(!obj.getEntry_id().equalsIgnoreCase("") && obj.getEntry_id()!=null){


				if(!obj.getPublisher().equalsIgnoreCase(obj.getEntry_id())){
					String status=validationpubsettings(pub, address, email,telphone,mobilenum);
					if(status.equalsIgnoreCase("true")){
						result=" data already present";
					}
					else{
						pstmt = conn.prepareStatement(LibrarySqlUtils.UPDATE_PUBLISHER_SETTING);
						pstmt.setString(1, obj.getPublisher());
						pstmt.setString(2, obj.getAddress());
						pstmt.setString(3, obj.getEmail());
						pstmt.setString(4, obj.getFax());
						pstmt.setString(5,obj.getTelephone());
						pstmt.setString(6, obj.getMobilenum());
						pstmt.setString(7, HelperClass.convertUIToDatabase(obj.getPubdate()));
						pstmt.setString(8, obj.getCreatedby());
						pstmt.setTimestamp(9, HelperClass.getCurrentTimestamp());
						pstmt.setString(10, obj.getEntry_id());

						count=pstmt.executeUpdate();
						System.out.println(pstmt);
						if(count >0){
							result = "success";
						}
						else{
							result = "fail";
						}
					}


					pstmt = conn.prepareStatement(LibrarySqlUtils.UPDATE_PUBLISHER_SETTING);
					pstmt.setString(1, obj.getPublisher());
					pstmt.setString(2, obj.getAddress());
					pstmt.setString(3, obj.getEmail());
					pstmt.setString(4, obj.getFax());
					pstmt.setString(5,obj.getTelephone());
					pstmt.setString(6, obj.getMobilenum());
					pstmt.setString(7, HelperClass.convertUIToDatabase(obj.getPubdate()));
					pstmt.setString(8, obj.getCreatedby());
					pstmt.setTimestamp(9, HelperClass.getCurrentTimestamp());
					pstmt.setString(10, obj.getEntry_id());

					count=pstmt.executeUpdate();
					System.out.println(pstmt);
					if(count >0){
						result = "success";
					}
					else{
						result = "fail";
					}
				}
			}
			else{
				String status=validationpubsettings(pub, address, email,telphone,mobilenum);
				if(status.equalsIgnoreCase("true")){
					result="data  already present";
				}
				else{
					
					pstmt=conn.prepareStatement(LibrarySqlUtils.SAVE_PUBLISHER_SETTING);
					pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_library_publisher_settings"));
					pstmt.setString(2, obj.getPublisher());
					pstmt.setString(3, obj.getAddress());
					pstmt.setString(4, obj.getEmail());
					pstmt.setString(5, obj.getFax());
					pstmt.setString(6,obj.getTelephone());
					pstmt.setString(7, obj.getMobilenum());
					pstmt.setString(8, HelperClass.convertUIToDatabase(obj.getPubdate()));
					pstmt.setString(9, obj.getCreatedby());
					count=pstmt.executeUpdate();
					System.out.println("//////////////////"+pstmt);

					if(count >0){

						result = "addsuccess";
					}
					else{
						result = "fail";
					}
				
			}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : publisherSettings Ending");
		return result;
	}

	@Override
	public List<LibraryStockEntryVO> getPublisherSettingList() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LibraryStockEntryVO> publist=new ArrayList<LibraryStockEntryVO>();
		try{

			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.LIST_PUBLICATION_SETTING);
			rs=pstmt.executeQuery();
			while(rs.next()){
				LibraryStockEntryVO vo =new LibraryStockEntryVO();
				vo.setEntry_id(rs.getString("Entry_id"));
				vo.setPublisher(rs.getString("Publisher_Name"));
				vo.setEmail(rs.getString("E_Mail"));
				vo.setFax(rs.getString("Fax"));
				vo.setTelephone(rs.getString("Tel_Phone_Number"));
				vo.setMobilenum(rs.getString("Mobile_Number"));
				vo.setAddress(rs.getString("Address"));
				
				publist.add(vo);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{

			if(rs!=null){
				try{
					rs.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Ending");
		return publist;
	}


	@Override
	public LibraryStockEntryVO editpublisherSetting(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		LibraryStockEntryVO vo =null;

		try{

			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.EDIT_PUB_SETTING);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				vo = new LibraryStockEntryVO();
				vo.setPublisher(rs.getString("Publisher_Name"));
				vo.setAddress(rs.getString("Address"));
				vo.setEmail(rs.getString("E_Mail"));
				vo.setFax(rs.getString("Fax"));
				vo.setTelephone(rs.getString("Tel_Phone_Number"));
				vo.setMobilenum(rs.getString("Mobile_Number"));
				vo.setPubdate(HelperClass.convertDatabaseToUI(rs.getString("Pub_Date")));
				
			}

		}
	
		catch(Exception  e){
			e.printStackTrace();

		}
		finally{

			if(rs!=null){
				try{
					rs.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Ending");
		return vo ;
	}

	@Override
	public String deletepublisherSetting(String deleteId[]) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : deletepublisherSetting Starting");


		PreparedStatement pst = null;
		PreparedStatement pstmt=null;
		ResultSet rs_deleteliblocation=null;
		Connection conn = null;
		int result = 0;
		int no=0;
		int count=0;
		String status = null;

		try{
			
			for(int i=0;i<deleteId.length;i++){
				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement("SELECT COUNT(*) Publisher FROM campus_library_stock_entry WHERE Publisher =?");
				pstmt.setString(1, deleteId[i]);
				rs_deleteliblocation = pstmt.executeQuery();
	   
				while (rs_deleteliblocation.next()) {

					no = rs_deleteliblocation.getInt("Publisher");

					if (no > 0) {
						count++;
					} 
				}
				rs_deleteliblocation.close();
				pstmt.close();
				if(no == 0){
					PreparedStatement pstmt1 = conn.prepareStatement("SELECT COUNT(*) Publisher_Id FROM campus_library_journal_subscription WHERE Publisher_Id=?");
					pstmt1.setString(1,deleteId[i]);
					
					ResultSet rs = pstmt1.executeQuery();
					while(rs.next()){
						no = rs.getInt(1);
						if (no > 0) {
							count++;
						} 
					}
					rs.close();
					pstmt1.close();
				}
				if(no == 0 ){
				
					pst = conn.prepareStatement(LibrarySqlUtils.DETAIL_PUBLISHER_SETTINGS);
					pst.setString(1,deleteId[i]);
					result = pst.executeUpdate();
					if (result > 0) {
						status = MessageConstants.DELETE_PUBLISHER_SETTINGS_SUCCESS;
					} else {
						status = MessageConstants.DELETE_PUBLISHER_SETTINGS_FAIL;
					}
        
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

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

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : deletepublisherSetting Ending");

		return status;
	}

	@Override
	public String validationpubsettings(String pub, String address, String email, String telphone, String mobilenum) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : validationpubsettings Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet  rs=null;
		int count=0;
		String result=null;
		try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.VALIDATE_PUB_SETTING);
			pstmt.setString(1,pub);
			pstmt.setString(2,address);
			pstmt.setString(3,email);
			pstmt.setString(4,telphone);
			pstmt.setString(5,mobilenum);
			rs=pstmt.executeQuery();
			System.out.println(pstmt);
			while(rs.next()){
				count= rs.getInt(1);

			}
			if(count>0){
				result="true";
			}
			else{
				result="false";
			}

		}
		catch(Exception e){
			e.printStackTrace();

		}
		finally{
			if(rs!=null){
				try{
					rs.close();
				}
				catch(Exception e){
					e.printStackTrace();							
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(Exception e){
					e.printStackTrace();							
				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch(Exception e){
					e.printStackTrace();							
				}
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : validationpubsettings Ending");


		return result;
	}

	@Override
	public ArrayList<ReportMenuVo> getLibraryLocation() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getlocationList : Starting");

		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.GET_LIBRARY_LOCATION);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo vo =new ReportMenuVo();
				vo.setLocationId(rs.getString("library_loc_id"));
				vo.setLocationName(rs.getString("library_location_name"));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
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
				+ " Control in ReportsMenuDaoImpl : getlocationList : Ending");

		return list;
	}






	@Override
	public String TransferStudent(String subscriberId[], String locid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getlocationList : Starting");
		PreparedStatement pst = null;
		Connection conn = null;
		int result = 0;

		String status = null;

		try{
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(SQLUtilConstants.TRANSFER_SUBSCRIBER_DETAIL);
			pst.setString(1, locid);
			for(int i=0;i<subscriberId.length;i++){
				pst.setString(2,subscriberId[i]);
				result = pst.executeUpdate();
			}		

			if (result > 0) {
				status = MessageConstants.TRANSFER_SUBSCRIBER_DETAIL_SUCCESS;
			} else {
				status = MessageConstants.TRANSFER_SUBSCRIBER_DETAIL_FAIL;

			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

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
				+ " Control in ReportsMenuDaoImpl : getlocationList : Ending");
		return status;
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getTranferStudentListDetails(String academic_year, String location, String select,String classname, String sectionid, String libloc) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try{
			
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.TRANSFER_SUBSCRIBER_LIST);
			pstmt.setString(1, academic_year);
			pstmt.setString(2, location);
			pstmt.setString(3, classname);
			pstmt.setString(4, sectionid);
			pstmt.setString(5, libloc);
			rs=pstmt.executeQuery();
			System.out.println("//////////////////"+pstmt);
			while(rs.next()){
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));
				vo.setLibLoc(rs.getString("library_location_name"));

				studentData.add(vo);
			}



		}
		catch(Exception e){
			e.printStackTrace();

		}
		finally{
			if(rs!=null){
				try{
					rs.close();
				}
				catch(Exception  e){
					e.printStackTrace();

				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(Exception  e){
					e.printStackTrace();

				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch(Exception  e){
					e.printStackTrace();

				}
			}
		}




		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getlocationList : Ending");
		return studentData;

	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getTrasferStudentListBySection(
			String academic_year, String location, String classname,
			String sectionid,String select,String liblocation) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.TRANSFER_studentlistdetailsBySection_BY_SUBSCRIBER_NO);

			pstmt.setString(1, academic_year);
			pstmt.setString(2, location);
			pstmt.setString(3, classname);
			pstmt.setString(4, sectionid);
			pstmt.setString(5, liblocation);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));
				vo.setLibLoc(rs.getString("library_location_name"));
				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getTrasferStudentListByClassName(
			String academic_year, String location, String classname,
			String select,String liblocation) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.TRANSFER_studentlistdetailsByClassName_BY_SUBSCRIBER_NO);


			pstmt.setString(1,academic_year);
			pstmt.setString(2,location);
			pstmt.setString(3,classname);
			pstmt.setString(4,liblocation);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs
						.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));
				vo.setLibLoc(rs.getString("library_location_name"));
				
				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getTransferStaffListDetails(
			String accyear_ID, String loc_ID, String select,
			String department, String designation,String libloc) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(LibrarySqlUtils.TRANSFER_STAFF_LIST_DETAILS_ORDER_BY_SUBNO);

			pstmt.setString(1, loc_ID);
			pstmt.setString(2, accyear_ID);
			pstmt.setString(3, loc_ID);
			pstmt.setString(4, department);
			pstmt.setString(5, designation);
			pstmt.setString(6, libloc);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));
				vo.setLibLoc(rs.getString("library_location_name"));
				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getTransferOthersListDetails(String location,String select,String academic_year,String libloc) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		/* int count = 0; */
		try {
			System.out.println(select);
			conn = JDBCConnection.getSeparateConnection();


			pstmt = conn.prepareStatement(LibrarySqlUtils.TRANSFER_OtherslistdetailsbysubNo);

			pstmt.setString(1, location);
			pstmt.setString(2, academic_year);
			pstmt.setString(3, libloc);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				/* count++; */
				/* vo.setSlno(count); */
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setOthereName(rs.getString("otherName"));
				vo.setOthereContactNumber(rs.getString("otherContactNo"));
				vo.setOthereMail_Id(rs.getString("otherEmail"));
				vo.setOthereAddress(rs.getString("otherAddress"));
				vo.setStatus(rs.getString("status"));
				vo.setLibLoc(rs.getString("library_location_name"));
				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getlocationStudentList(
			String libloc, String select) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getlocationStudentList Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;


		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {
			System.out.println(select);
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistdetailslibloc);

			pstmt.setString(1, libloc);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getlocationStudentList Ending");
		return studentData;
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getliblocationstafflist(
			String libloc, String select) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getliblocationstafflist Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(LibrarySqlUtils.STAF_TRANSFER_LIST_LIBLOC);

			pstmt.setString(1, libloc);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getliblocationstafflist Ending");

		return studentData;
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getliblocatinotherlist(String academic_year, String location, String libloc,String select) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getliblocationstafflist Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		/* int count = 0; */
		try {
			System.out.println(select);
			conn = JDBCConnection.getSeparateConnection();


			pstmt = conn.prepareStatement(LibrarySqlUtils.OTHER_LIBLOC_LIST);


			pstmt.setString(1, libloc);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				/* count++; */
				/* vo.setSlno(count); */
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setOthereName(rs.getString("otherName"));
				vo.setOthereContactNumber(rs.getString("otherContactNo"));
				vo.setOthereMail_Id(rs.getString("otherEmail"));
				vo.setOthereAddress(rs.getString("otherAddress"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getliblocationstafflist Ending");

		return studentData;
	}

	@Override
	public List<LibrarySearchSubscriberVO> TransferSubscriberbySearch(String searchTextVal, String location,String academic_year,
			String liblocid, String select,String classname,String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : TransferSubscriberbySearch Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {

			if(location.equalsIgnoreCase("all")){
				location = "%%";
			}
			if(liblocid.equalsIgnoreCase("all")){
				liblocid = "%%";
			}
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(LibrarySqlUtils.TransferstudentlistSearch);

			//pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistSearchByAnyWhere);
			pstmt.setString(1, "%" + searchTextVal + "%");
			pstmt.setString(2, "%" + searchTextVal + "%");
			pstmt.setString(3, "%" + searchTextVal + "%");
			pstmt.setString(4, "%" + searchTextVal + "%");
			pstmt.setString(5, "%" + searchTextVal + "%");
			pstmt.setString(6, "%" + searchTextVal + "%");
			pstmt.setString(7, "%" + searchTextVal + "%");
			pstmt.setString(8, "%" + searchTextVal + "%");
			pstmt.setString(9, "%" + searchTextVal + "%");
			pstmt.setString(10, location);
			pstmt.setString(11, academic_year);
			pstmt.setString(12, classname);
			pstmt.setString(13, sectionid);
			/*pstmt.setString(9, location);
			pstmt.setString(10, liblocid);*/
			//pstmt.setString(10, academic_year);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));
				vo.setLibLoc(rs.getString("library_location_name"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : TransferSubscriberbySearch Ending");
		return studentData;
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> TransferSubscriberbyStaffSearch(String searchTextVal, String location,
			String liblocid,String select,String department,String designation,String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : TransferSubscriberbyStaffSearch Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {
			if(location.equalsIgnoreCase("all")){
				location = "%%";
			}
			if(liblocid.equalsIgnoreCase("all")){
				liblocid = "%%";
			}
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.TRANSFER_STAFF_SEARCH);

			//pstmt = conn.prepareStatement(LibrarySqlUtils.STAFF_LIST_SEACH_BY_ANYWHERE);
			pstmt.setString(1, "%" + searchTextVal + "%");
			pstmt.setString(2, "%" + searchTextVal + "%");
			pstmt.setString(3, "%" + searchTextVal + "%");
			pstmt.setString(4, "%" + searchTextVal + "%");
			pstmt.setString(5, "%" + searchTextVal + "%");
			pstmt.setString(6, "%" + searchTextVal + "%");
			pstmt.setString(7, "%" + searchTextVal + "%");
			pstmt.setString(8, "%" + searchTextVal + "%");
			pstmt.setString(9, location);
			pstmt.setString(10, accyear);
			pstmt.setString(11, location);
			pstmt.setString(12, department);
			pstmt.setString(13, designation);
			pstmt.setString(14, liblocid);
			//pstmt.setString(8, accyear);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));
				vo.setLibLoc(rs.getString("library_location_name"));
				
				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : TransferSubscriberbyStaffSearch Ending");
		return studentData;
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> TransferSubscriberbyotherSearch(
			String searchTextVal, String location, String select,String accyear,
			String liblocid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : TransferSubscriberbyotherSearch Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			
			
			if(liblocid==null) {
				liblocid="%%";
			}
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.TRANSFER_OTHER_SEARCH);
			pstmt.setString(1, "%" + searchTextVal + "%");
			pstmt.setString(2, "%" + searchTextVal + "%");
			pstmt.setString(3, "%" + searchTextVal + "%");
			pstmt.setString(4, "%" + searchTextVal + "%");
			pstmt.setString(5, "%" + searchTextVal + "%");
			pstmt.setString(6, "%" + searchTextVal + "%");
			pstmt.setString(7, "%" + searchTextVal + "%");
			pstmt.setString(8, location);
			pstmt.setString(9, liblocid);
			pstmt.setString(10,accyear);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setOthereName(rs.getString("otherName"));
				vo.setOthereContactNumber(rs.getString("otherContactNo"));
				vo.setOthereMail_Id(rs.getString("otherEmail"));
				vo.setOthereAddress(rs.getString("otherAddress"));
				vo.setStatus(rs.getString("status"));
				vo.setLibLoc(rs.getString("library_location_name"));
				
				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : TransferSubscriberbyotherSearch Ending");
		return studentData;
	}


	@Override
	public String addSupplierSettings(LibraryStockEntryVO obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : addSupplierSettings Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		int count=0;
		String result=null;
		String hiddenid=obj.getEntry_id();
		String suplier=obj.getSuppliedBy();
		String supadd=obj.getSupplieraddress();
		String emailid=obj.getEmail();
		String telephone=obj.getTelephone();
		String supnum=obj.getMobilenum();

		try{
			
			conn=JDBCConnection.getSeparateConnection();


			if(!obj.getEntry_id().equalsIgnoreCase("") && obj.getEntry_id()!=null)
			{
				if(!obj.getSuppliedBy().equalsIgnoreCase(obj.getEntry_id())){
					String status=validationsubsettings(suplier,supadd,emailid,telephone,supnum);

					if(status.equalsIgnoreCase("true")){
						result=" data already present";
					}
					
					else{
						pstmt = conn.prepareStatement(LibrarySqlUtils.UPDATE_SUPPLIER_SETTING);
						pstmt.setString(1, obj.getSuppliedBy());
                        pstmt.setString(2, obj.getSupplieraddress());
                        pstmt.setString(3, obj.getEmail());
                        pstmt.setString(4, obj.getFax());
                        pstmt.setString(5, obj.getTelephone());
                        pstmt.setString(6, obj.getMobilenum());
                        pstmt.setString(7, HelperClass.convertUIToDatabase(obj.getSupdate()));
						pstmt.setString(8, obj.getCreatedby());
						pstmt.setTimestamp(9, HelperClass.getCurrentTimestamp());
						pstmt.setString(10, obj.getEntry_id());

						count=pstmt.executeUpdate();
						System.out.println(pstmt);
						if(count >0){
							result = "success";
						}
						else{
							result = "fail";
						}
					}

					pstmt = conn.prepareStatement(LibrarySqlUtils.UPDATE_SUPPLIER_SETTING);
					pstmt.setString(1, obj.getSuppliedBy());
                    pstmt.setString(2, obj.getSupplieraddress());
                    pstmt.setString(3, obj.getEmail());
                    pstmt.setString(4, obj.getFax());
                    pstmt.setString(5, obj.getTelephone());
                    pstmt.setString(6, obj.getMobilenum());
                    pstmt.setString(7, HelperClass.convertUIToDatabase(obj.getSupdate()));
					pstmt.setString(8, obj.getCreatedby());
					pstmt.setTimestamp(9, HelperClass.getCurrentTimestamp());
					pstmt.setString(10, obj.getEntry_id());

					count=pstmt.executeUpdate();
					System.out.println(pstmt);
					if(count >0){
						result = "success";
					}
					else{
						result = "fail";
					}
				}
		   	}
			else{
				String status=validationsubsettings(suplier,supadd,emailid,telephone,supnum);
				if(status.equalsIgnoreCase("true")){
					result=" data already present";
				}

				else{

				
					pstmt=conn.prepareStatement(LibrarySqlUtils.SAVE_SUPPLIER_SETTING);
					pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_library_Supplier_settings"));
					pstmt.setString(2, obj.getSuppliedBy());
					pstmt.setString(3, obj.getSupplieraddress());
					pstmt.setString(4, obj.getEmail());
					pstmt.setString(5, obj.getFax());
					pstmt.setString(6, obj.getTelephone());
					pstmt.setString(7, obj.getMobilenum());
					pstmt.setString(8, HelperClass.convertUIToDatabase(obj.getSupdate()));
					pstmt.setString(9, obj.getCreatedby());
					count=pstmt.executeUpdate();
					System.out.println("//////////////////"+pstmt);

					if(count >0){

						result = "addsuccess";
					}
					else{
						result = "fail";
					}



				}
			}


		}		    
		catch(Exception e){
			e.printStackTrace();
		}
		finally{

			if(pstmt!=null)
			{
				try{
					pstmt.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(conn!=null)
			{
				try{
					conn.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}



		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : addSupplierSettings Ending");
		return result;
	}


	@Override
	public List<LibraryStockEntryVO> getSupplierSettingList() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getSupplierSettingList Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<LibraryStockEntryVO> suplist=new ArrayList<LibraryStockEntryVO>();
		try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.LIST_SUPPLIER_SETTINGS);
			rs=pstmt.executeQuery();
			while(rs.next()){
				LibraryStockEntryVO vo =new LibraryStockEntryVO();
				vo.setEntry_id(rs.getString("Entry_id"));
				vo.setSuppliedBy(rs.getString("Supplier_Name"));
				vo.setSupplieraddress(rs.getString("Supplier_Address"));
				vo.setEmail(rs.getString("E_Mail"));
				vo.setTelephone(rs.getString("Tele_Phone"));
				vo.setMobilenum(rs.getString("Mobile_Number"));
				vo.setFax(rs.getString("fax"));
				
				suplist.add(vo);
			}

			
		}
		catch (Exception e){
			e.printStackTrace();

		}
		finally{

			if(rs!=null){
				try{
					rs.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getSupplierSettingList Ending");
		return suplist;
	}

	@Override
	public LibraryStockEntryVO editSupplierSetting(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : editSupplierSetting Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		LibraryStockEntryVO vo =null;

		try{

			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.EDIT_SUB_SETTING);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				vo = new LibraryStockEntryVO();
				vo.setSuppliedBy(rs.getString("Supplier_Name"));
				vo.setSupplieraddress(rs.getString("Supplier_Address"));
				vo.setEmail(rs.getString("E_Mail"));
				vo.setFax(rs.getString("fax"));
				vo.setTelephone(rs.getString("Tele_Phone"));
				vo.setMobilenum(rs.getString("Mobile_Number"));
			    vo.setSupdate( HelperClass.convertDatabaseToUI(rs.getString("Sup_Date")));
				
				
			}

			
		}
		catch(Exception  e){
			e.printStackTrace();

		}
		finally{

			if(rs!=null){
				try{
					rs.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : editSupplierSetting Ending");
		return vo ;
	}

	@Override
	public String deleteSupplierSetting(String[] deleteId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : deleteSupplierSetting Starting");


		PreparedStatement pst = null;
		PreparedStatement pstmt=null;
		ResultSet rs_deleteliblocation=null;
		Connection conn = null;
		int result = 0;
		int no=0;
		int count=0;
		String status = null;

		try{
			
			for(int i=0;i<deleteId.length;i++){
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("SELECT COUNT(*) Supplied_By FROM campus_library_stock_entry WHERE Supplied_By =?");
			pstmt.setString(1, deleteId[i]);
			rs_deleteliblocation = pstmt.executeQuery();
   
			while (rs_deleteliblocation.next()) {

				no = rs_deleteliblocation.getInt("Supplied_By");

				if (no > 0) {
					count++;
				} 
			}
			rs_deleteliblocation.close();
			pstmt.close();
			
			if(no==0){
				
				pstmt = conn.prepareStatement("SELECT COUNT(*) Supplier_Id FROM campus_library_journal_subscription WHERE Supplier_Id =?");
				pstmt.setString(1, deleteId[i]);
				rs_deleteliblocation = pstmt.executeQuery();
	   
				while (rs_deleteliblocation.next()) {

					no = rs_deleteliblocation.getInt("Supplier_Id");

					if (no > 0) {
						count++;
					} 
				}
				rs_deleteliblocation.close();
				pstmt.close();
			}
			if(no==0){
			pst = conn.prepareStatement(LibrarySqlUtils.DELETE_SUPPLIER_SETTINGS);
		
				pst.setString(1,deleteId[i]);
				result = pst.executeUpdate();
			

			if (result > 0) {
				status = MessageConstants.DELETE_SUPPLIER_SETTINGS_SUCCESS;
			} else {
				status = MessageConstants.DELETE_SUPPLIER_SETTINGS_FAIL;
				}
			  }
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

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

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : deleteSupplierSetting Ending");

		return status;
	}
	
	@Override
	public String validationsubsettings(String suplier,
			String supadd, String emailid, String telephone, String supnum) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : validationsubsettings Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet  rs=null;
		int count=0;
		String result=null;
		try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.VALIDATE_SUP_SETTING);
			pstmt.setString(1,suplier);
			pstmt.setString(2,supadd);
			pstmt.setString(3,emailid);
			pstmt.setString(4,telephone);
			pstmt.setString(5,supnum);
			rs=pstmt.executeQuery();
			while(rs.next()){
				count= rs.getInt(1);

			}
			if(count>0){
				result="true";
			}
			else{
				result="false";
			}

		}
		catch(Exception e){
			e.printStackTrace();

		}
		finally{
			if(rs!=null){
				try{
					rs.close();
				}
				catch(Exception e){
					e.printStackTrace();							
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(Exception e){
					e.printStackTrace();							
				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch(Exception e){
					e.printStackTrace();							
				}
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : validationsubsettings Ending");


		return result;
	}
	@Override
	public List<TeacherVo> othersSearchbyId(TeacherVo registrationVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getAllTeacherDetails Starting");

		ArrayList<TeacherVo> getTeacherDetails = new ArrayList<TeacherVo>();
		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			if (registrationVo.getLocid().equalsIgnoreCase("all")) {
				registrationVo.setLocid("%%");
			}
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(LibrarySqlUtils.OTHERS_SEARCH_BY_SUBSCRIBER_NUMBER);
			pstmt.setString(1, "%" + registrationVo.getSearchTerm() + "%");
			pstmt.setString(2, "%" + registrationVo.getSearchTerm() + "%");
			System.out.println(" query is " + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TeacherVo teachervo = new TeacherVo();
				teachervo.setSubscriberNumber(rs.getString("subscriberNumber")+ " - "+ rs.getString("otherName"));
				teachervo.setTeacherId(rs.getString("subscriberId")+"-"+rs.getString("subscriberId"));
				//teachervo.setSubscriberId(rs.getString("subscriberId"));
				getTeacherDetails.add(teachervo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
				+ " Control in TeacherDaoImpl : getAllTeacherDetails Ending");
		return getTeacherDetails;
	}


	@Override
	public ArrayList<LibraryStockEntryVO> publisherDetailsSearch(
			String searchTextVal, String pub) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : publisherDetailsSearch Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibraryStockEntryVO> List=new ArrayList<LibraryStockEntryVO>();
		int count = 0;
		try {

			
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(LibrarySqlUtils.PublisherDetailsSearch);

			//pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistSearchByAnyWhere);
			pstmt.setString(1, "%"+searchTextVal+"%");
			pstmt.setString(2, "%"+searchTextVal+"%");
			pstmt.setString(3, "%"+searchTextVal+"%");	
			pstmt.setString(4, "%"+searchTextVal+"%");
			pstmt.setString(5, "%"+searchTextVal+"%");
			pstmt.setString(6, "%"+searchTextVal+"%");
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				LibraryStockEntryVO vo = new LibraryStockEntryVO();
			    vo.setEntry_id(rs.getString("Entry_id"));
				vo.setPublisher(rs.getString("Publisher_Name"));
				vo.setAddress(rs.getString("Address"));
				vo.setEmail(rs.getString("E_Mail"));
				vo.setFax(rs.getString("Fax"));
				vo.setTelephone(rs.getString("Tel_Phone_Number"));
				vo.setMobilenum(rs.getString("Mobile_Number"));
				List.add(vo);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : publisherDetailsSearch Ending");
		return List;
	}

	@Override
	public ArrayList<LibraryStockEntryVO> SupplierDetailsSearch(
			String searchTextVal, String sup) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : SupplierDetailsSearch Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibraryStockEntryVO> List=new ArrayList<LibraryStockEntryVO>();
		int count = 0;
		try {

			
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(LibrarySqlUtils.SupplierDetailsSearch);
			pstmt.setString(1,"%%"+ searchTextVal+"%%");
			pstmt.setString(2,"%%"+searchTextVal+"%%");
			pstmt.setString(3,"%%"+ searchTextVal+"%%");
			pstmt.setString(4,"%%"+searchTextVal+"%%");
			pstmt.setString(5,"%%"+ searchTextVal+"%%");
			pstmt.setString(6,"%%"+searchTextVal+"%%");
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				LibraryStockEntryVO vo = new LibraryStockEntryVO();
			    vo.setEntry_id(rs.getString("Entry_id"));
			    vo.setSuppliedBy(rs.getString("Supplier_Name"));
			    vo.setSupplieraddress(rs.getString("Supplier_Address"));
				vo.setEmail(rs.getString("E_Mail"));
				vo.setFax(rs.getString("Fax"));
				vo.setTelephone(rs.getString("Tele_Phone"));
				vo.setMobilenum(rs.getString("Mobile_Number"));
				List.add(vo);
			} 
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : SupplierDetailsSearch Ending");
		return List;
	}
	


	@Override
	public List<LibraryStockEntryVO> getStudentIssueDetailsBySubscriberNo(LibraryStockEntryVO libVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getBookIssueDetailsByAccessionNo : getBookIssueDetailsByAccessionNo Starting");
		List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmObj = conn.prepareStatement(LibrarySqlUtils.GET_STUDENT_ISSUE_DETAIL_BY_SUBSCRIBER_NO);
			pstmObj.setString(1, libVo.getAccessionNo());
			rs = pstmObj.executeQuery();
			System.out.println(pstmObj);
			while (rs.next()) {
				LibraryStockEntryVO form = new LibraryStockEntryVO();
				
				form.setAdmissionNo(rs.getString("student_admissionno_var"));
				form.setClassName(rs.getString("classdetails_name_var"));
				form.setSectionName(rs.getString("classsection_name_var"));
				libVoList.add(form);
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in getBookIssueDetailsByAccessionNo : getBookIssueDetailsByAccessionNo Ending");
		return libVoList;
	}

	@Override
	public List<LibraryStockEntryVO> getTeacherIssueDetails(LibraryStockEntryVO libVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getBookIssueDetailsByAccessionNo : getBookIssueDetailsByAccessionNo Starting");
		List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmObj = conn.prepareStatement(LibrarySqlUtils.GET_TEACHER_ISSUE_DETAIL);
			pstmObj.setString(1, libVo.getAccessionNo());
			rs = pstmObj.executeQuery();
			System.out.println(pstmObj);
			while (rs.next()) {
				LibraryStockEntryVO form = new LibraryStockEntryVO();
				form.setDepartment(rs.getString("DEPT_NAME"));
				form.setDesignation(rs.getString("designationName"));
				libVoList.add(form);
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in getBookIssueDetailsByAccessionNo : getBookIssueDetailsByAccessionNo Ending");
		return libVoList;
	}

	@Override
	public String insertBookReservationDetails(LibraryStockEntryVO insert_issue) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in insertBookReservationDetails: insertBookReservationDetails : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		String result = null;
		int count = 0;
	
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.insert_book_reservation_details);

			pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_library_reservation_details"));
			pstmt.setString(2, insert_issue.getAccessionNo());
			pstmt.setString(3, insert_issue.getItemType());
			pstmt.setString(4, insert_issue.getBookTitle());
			pstmt.setString(5, insert_issue.getAuthor());
			pstmt.setString(6, insert_issue.getCategory());
			pstmt.setString(7, insert_issue.getDdc());
			pstmt.setString(8, insert_issue.getCurrentStatus());
			pstmt.setString(9, insert_issue.getIssued_user_id());
			pstmt.setString(10, insert_issue.getIssued_to());
			
			pstmt.setString(11, insert_issue.getShelfNo());
			pstmt.setString(12, insert_issue.getLocation());
			pstmt.setString(13, HelperClass.convertUIToDatabase(insert_issue.getFromDate()));
			pstmt.setString(14,HelperClass.convertUIToDatabase(insert_issue.getToDate()));
			pstmt.setString(15, insert_issue.getAccyear());
			pstmt.setString(16, insert_issue.getSubscriberId());
			
			
			
			System.out.println("insert reserve book  details>>>"+pstmt);

			count = pstmt.executeUpdate();
			
			if (count > 0) {
				result = "success";
				/*pstmt1 = conn.prepareStatement(LibrarySqlUtils.UPDATE_STOCKENTRY_BY_ISSUE);
				pstmt1.setString(1, insert_issue.getAccessionNo());
				System.out.println("*****************************************************");
				System.out.println(pstmt1);
				System.out.println("*****************************************************");
				pstmt1.executeUpdate();*/
			} else {
				result = "fail";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
		return result;
	}

	@Override
	public List<LibraryStockEntryDetailsForm> getReservationListDetails() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: getReservationListDetails: Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;

		ArrayList<LibraryStockEntryDetailsForm> ReservationList = new ArrayList<LibraryStockEntryDetailsForm>();
		try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.reservation_details_list);
			System.out.println("getReservationListDetails1"+pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				LibraryStockEntryDetailsForm from=new LibraryStockEntryDetailsForm();

				from.setAccessionNo(rs.getString("Accession_number"));
				from.setReservationId(rs.getString("reservation_id"));
				from.setAuthor(rs.getString("author"));
				from.setBookTitle(rs.getString("book_title"));
				from.setLocation(rs.getString("library_location_name"));
				from.setCurrentStatus(rs.getString("current_status"));
				from.setIssued_to(rs.getString("issued_to"));
				from.setFrom_date(HelperClass.convertDatabaseToUI(rs.getString("from_date")));
				from.setTo_date(HelperClass.convertDatabaseToUI(rs.getString("to_date")));
				 String usertype=rs.getString("userType");
				if(usertype.equalsIgnoreCase("Teacher"))
					
				{
					pstmt1=conn.prepareStatement(LibrarySqlUtils.teacher);
					pstmt1.setString(1,rs.getString("subscriber_id"));
					pstmt1.setString(2,rs.getString("issued_to"));
					rs1=pstmt1.executeQuery();
					System.out.println("teacher query>>"+pstmt1);
					while(rs1.next())
					{
						from.setSubscriberName(rs1.getString("teacheer"));
						from.setDepartment(rs1.getString("DEPT_NAME"));
						from.setDesignation(rs1.getString("designationName"));
						
					}
				}
				else if(usertype.equalsIgnoreCase("Student"))
				{

					/*pstmt1=conn.prepareStatement(LibrarySqlUtils.student_name_details);*/
					pstmt1=conn.prepareStatement(LibrarySqlUtils.student);
					
				
					pstmt1.setString(1,rs.getString("subscriber_id"));
					pstmt1.setString(2,rs.getString("issued_to"));
					rs1=pstmt1.executeQuery();
					System.out.println("student query>>"+pstmt1);
					while(rs1.next())
					{
						from.setSubscriberName(rs1.getString("student"));
						from.setClassDescription(rs1.getString("classdetails_name_var"));
						from.setDivisionDescription(rs1.getString("classsection_name_var"));
						from.setAdmissionNo(rs1.getString("student_admissionno_var"));
						
					}
				
				}
				else if(usertype.equalsIgnoreCase("other"))				
				{
					pstmt1=conn.prepareStatement(LibrarySqlUtils.other);
					pstmt1.setString(1,rs.getString("subscriber_id"));
					rs1=pstmt1.executeQuery();
					System.out.println("other query>>"+pstmt1);
					while(rs1.next())
					{
						from.setSubscriberName(rs1.getString("otherName"));
						from.setContactNo(rs1.getString("otherContactNo"));
						from.setAddress(rs1.getString("otherAddress"));
					
					}
				
									
				}
				
				
				ReservationList.add(from);
			}


		} catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			if(rs!=null){
				try{
					rs.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(conn !=null){
				try{
					conn.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL :getReservationListDetails  Ending");

		return ReservationList;
	}

	@Override
	public List<LibraryStockEntryVO> getOtherIssueDetails(LibraryStockEntryVO libVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getBookIssueDetailsByAccessionNo : getBookIssueDetailsByAccessionNo Starting");
		List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmObj = conn.prepareStatement(LibrarySqlUtils.GET_OTHER_ISSUE_DETAIL);
			pstmObj.setString(1, libVo.getAccessionNo());
			rs = pstmObj.executeQuery();
			System.out.println(pstmObj);
			while (rs.next()) {
				LibraryStockEntryVO form = new LibraryStockEntryVO();
				form.setContactNo(rs.getString("otherContactNo"));
				form.setAddress(rs.getString("otherAddress"));
				libVoList.add(form);
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in getBookIssueDetailsByAccessionNo : getBookIssueDetailsByAccessionNo Ending");
		return libVoList;
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> IssueReturnTable(String location, String subId, String academic_year,
			String subscriberType) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {

			conn = JDBCConnection.getSeparateConnection();
			if(subscriberType.equalsIgnoreCase("Student"))
			{
				pstmt = conn.prepareStatement(LibrarySqlUtils.SUBSCRIBER_RETURN_STATEMENT_TABLE);
				System.out.println(subscriberType);
			}
			else if(subscriberType.equalsIgnoreCase("staff"))
			{
				pstmt = conn.prepareStatement(LibrarySqlUtils.SUBSCRIBER_RETURN_STATEMENT_TABLE);	
			}
			else if(subscriberType.equalsIgnoreCase("others"))
			{
				pstmt = conn.prepareStatement(LibrarySqlUtils.SUBSCRIBER_RETURN_STATEMENT_TABLE);	
			}

			pstmt.setString(1,subId);
			pstmt.setString(2,academic_year);
			pstmt.setString(3,location);

			System.out.println(pstmt);

			rs=pstmt.executeQuery();

			while(rs.next()){
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setIssueId(rs.getString("return_id"));
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setSubscriberType(rs.getString("subscriberType"));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setBookAuthor(rs.getString("Author"));
				vo.setIssueDate(rs.getString("book_issued_date"));
				vo.setReturnDate(rs.getString("book_returned_date"));

				studentData.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}

				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			}
			catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		return studentData;
	}

	@Override
	public String addGeneralSettings(LibraryStockEntryVO obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getBookIssueDetailsByAccessionNo Starting");
		 Connection conn=null;
		 PreparedStatement pstmt=null;
		 ResultSet rs=null;
		 int count=0;
		 String result=null;
		 
		try{
			conn=JDBCConnection.getSeparateConnection();
			if(!obj.getEntry_id().equalsIgnoreCase("") && obj.getEntry_id()!=null)
			{
				pstmt=conn.prepareStatement(LibrarySqlUtils.UPDATE_GEN_SETTINGS);
				pstmt.setString(1, obj.getItemType());
				pstmt.setString(2, obj.getNoofdaytaken());
				pstmt.setString(3, obj.getAmountperday());
				pstmt.setString(4, obj.getFineamount());
				pstmt.setString(5, obj.getFineincperday());
				pstmt.setString(6, HelperClass.convertUIToDatabase(obj.getTodaydate()));
				pstmt.setString(7,  HelperClass.convertUIToDatabase(obj.getDuedate()));
				pstmt.setString(8, obj.getCreatedby());
				pstmt.setTimestamp(9, HelperClass.getCurrentTimestamp());
				pstmt.setString(10, obj.getEntry_id());
				count=pstmt.executeUpdate();
				System.out.println(pstmt);
				if(count >0){
					result = "success";
				}
				else{
					result = "fail";
				}
			}
			else{
			
			pstmt=conn.prepareStatement(LibrarySqlUtils.SAVE_GENAREL_SETTINGS);
			pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_library_generalsettings"));
			pstmt.setString(2, obj.getItemType());
			pstmt.setString(3, obj.getNoofdaytaken());
			pstmt.setString(4, obj.getAmountperday());
			pstmt.setString(5, obj.getFineamount());
			pstmt.setString(6, obj.getFineincperday());
			pstmt.setString(7,  HelperClass.convertUIToDatabase(obj.getTodaydate()));
			pstmt.setString(8,  HelperClass.convertUIToDatabase(obj.getDuedate()));
			pstmt.setString(9, obj.getCreatedby());
			count=pstmt.executeUpdate();
			System.out.println("//////////////////"+pstmt);
			
			if(count >0){

				result = "addsuccess";
			}
			else{
				result = "fail";
			}
		}
		}
		
		catch(Exception e){
			e.printStackTrace();
			
		}
		finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getBookIssueDetailsByAccessionNo Ending");
		return result;
	}

	@Override
	public List<LibraryStockEntryVO> getGenarelSettingList() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getSupplierSettingList Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<LibraryStockEntryVO> genlist=new ArrayList<LibraryStockEntryVO>();
		try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.LIST_GENAREL_SETTINGS);
			rs=pstmt.executeQuery();
			while(rs.next()){
				LibraryStockEntryVO vo =new LibraryStockEntryVO();
				vo.setEntry_id(rs.getString("Entry_id"));
				vo.setItemType(rs.getString("Item_Description"));
				vo.setNoofdaytaken(rs.getString("Num_Of_Day_Item_teken"));
				vo.setAmountperday(rs.getString("Amount_per_day"));
				vo.setFineamount(rs.getString("Fine_Amount"));
				vo.setFineincperday(rs.getString("Fine_per_Day"));
				genlist.add(vo);
			}
		
		}
		catch (Exception e){
			e.printStackTrace();

		}
		finally{

			if(rs!=null){
				try{
					rs.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getSupplierSettingList Ending");
		return genlist;
	}

	@Override
	public LibraryStockEntryVO editGenarelSetting(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : editSupplierSetting Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		LibraryStockEntryVO vo =null;

		try{

			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.EDIT_GEN_SETTING);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				vo = new LibraryStockEntryVO();
				vo.setItemType(rs.getString("Item_Description"));
				vo.setNoofdaytaken(rs.getString("Num_Of_Day_Item_teken"));
				vo.setAmountperday(rs.getString("Amount_per_day"));
				vo.setFineamount(rs.getString("Fine_Amount"));
				vo.setFineincperday(rs.getString("Fine_per_Day"));
				vo.setTodaydate(HelperClass.convertDatabaseToUI(rs.getString("Issue_Date")));
				vo.setDuedate(HelperClass.convertDatabaseToUI(rs.getString("Return_Date")));
				
			
			}

			
		}
		catch(Exception  e){
			e.printStackTrace();

		}
		finally{

			if(rs!=null){
				try{
					rs.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : editSupplierSetting Ending");
		return vo ;
	}

	@Override
	public String editGenarelSetting(String[] deleteId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : deleteSupplierSetting Starting");


		PreparedStatement pst = null;
		Connection conn = null;
		int result = 0;
		String status = null;

		try{
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(LibrarySqlUtils.DELETE_GENAREL_SETTINGS);
			for(int i=0;i<deleteId.length;i++){
				pst.setString(1,deleteId[i]);
				result = pst.executeUpdate();
			}

			if (result > 0) {
				status = MessageConstants.DELETE_SUPPLIER_SETTINGS_SUCCESS;
			} else {
				status = MessageConstants.DELETE_SUPPLIER_SETTINGS_FAIL;

			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

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

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : deleteSupplierSetting Ending");

		return status;
	}

	@Override
	public ArrayList<LibraryStockEntryVO> GenarelDetailsSearch(
			String searchTextVal) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : GenarelDetailsSearch Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibraryStockEntryVO> List=new ArrayList<LibraryStockEntryVO>();
		int count = 0;
		try {

			
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(LibrarySqlUtils.GenarelDetailsSearch);
			pstmt.setString(1, "%%"+searchTextVal+"%%");
			pstmt.setString(2, "%%"+searchTextVal+"%%");
			pstmt.setString(3, "%%"+searchTextVal+"%%");
			pstmt.setString(4, "%%"+searchTextVal+"%%");
			pstmt.setString(5, "%%"+searchTextVal+"%%");
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				LibraryStockEntryVO vo = new LibraryStockEntryVO();
			    vo.setEntry_id(rs.getString("Entry_id"));
			     vo.setItemType(rs.getString("Item_Description"));
			     vo.setNoofdaytaken(rs.getString("Num_Of_Day_Item_teken"));
			     vo.setAmountperday(rs.getString("Amount_per_day"));
			     vo.setFineamount(rs.getString("Fine_Amount"));
			     vo.setFineincperday(rs.getString("Fine_per_Day"));
				List.add(vo);
			} 
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : GenarelDetailsSearch Ending");
		return List;
	}
	@Override
	public LibrarySubscribVO IssueReturnBySubScriberType(String location, String subId, String academic_year,String subscriberType) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1=null;
		Connection conn = null;

		LibrarySubscribVO vo = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			
			if(subscriberType.equalsIgnoreCase("Student")){
				pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_STUDENT_DETAIL_BY_GOTO_ISSUESTATEMENT);
				pstmt1.setString(1,academic_year);
				pstmt1.setString(2,location);
				pstmt1.setString(3,subId);
				rs1=pstmt1.executeQuery();

				while(rs1.next()){
					vo = new LibrarySubscribVO();
					vo.setSubscriberId(rs1.getString("subscriberId"));
					vo.setSubscriberType(rs1.getString("subscriberType"));
					vo.setSubscriberName(rs1.getString("student")); /*student name*/
					vo.setRollNumber(rs1.getString("student_rollno")); /*roll No*/
					vo.setAdminssionNo(rs1.getString("student_admissionno_var")); /*Admission No*/
					vo.setSubscriberNo(rs1.getString("subscriberNumber")); /*sub No*/
					vo.setClassName(rs1.getString("classdetails_name_var"));  /*class name*/
					vo.setSectionName(rs1.getString("classsection_name_var")); /*section name*/
					vo.setStatus(rs1.getString("status")); /*status*/
				}
			}

			else if(subscriberType.equalsIgnoreCase("staff")){
				pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_STAFF_DETAIL_BY_GOTO_ISSUESTATEMENT);
				pstmt1.setString(1,subId);
				pstmt1.setString(2,academic_year);
				pstmt1.setString(3,location);

				
				rs1=pstmt1.executeQuery();				
				while(rs1.next()){
					vo = new LibrarySubscribVO();
					vo.setSubscriberId(rs1.getString("subscriberId"));
					vo.setSubscriberType(rs1.getString("subscriberType"));
					vo.setSubscriberNo(rs1.getString("subscriberNumber"));
					vo.setSubscriberName(rs1.getString("teacher"));
					vo.setDepartment(rs1.getString("DEPT_NAME"));
					vo.setDesignation(rs1.getString("designationName"));
					vo.setStatus(rs1.getString("status"));

				}

			}
			else if(subscriberType.equalsIgnoreCase("Others")){
				pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_OTHERS_DETAIL_BY_GOTO_ISSUESTATEMENT);
				pstmt1.setString(1,subId);
				pstmt1.setString(2,academic_year);
				pstmt1.setString(3,location);
				rs1=pstmt1.executeQuery();

				while(rs1.next()){
					vo = new LibrarySubscribVO();
					vo.setSubscriberId(rs1.getString("subscriberId"));
					vo.setSubscriberType(rs1.getString("subscriberType"));
					vo.setSubscriberNo(rs1.getString("subscriberNumber"));
					vo.setSubscriberName(rs1.getString("otherName"));
					vo.setContactNumber(rs1.getString("otherContactNo"));
					vo.setStatus(rs1.getString("status"));
				}
			}			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} 
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			}
			catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return vo;
	}

	@Override
	public ArrayList<LibraryVO> getcodeList() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : GenarelDetailsSearch Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibraryVO> List=new ArrayList<LibraryVO>();
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.CATEGORY_CODE);
		
			rs = pstmt.executeQuery();
			while (rs.next()) {
			
				LibraryVO vo = new LibraryVO();
			    vo.setCode_id(rs.getString("category_id"));
			     vo.setCode(rs.getString("category_code"));
				List.add(vo);
			} 
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : GenarelDetailsSearch Ending");
		return List;
	}

	@Override
	public List<LibraryStockEntryVO> getcodeName (LibraryStockEntryVO registrationVo) {
		logger.setLevel(Level.DEBUG);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getAccessionNo : getAccessionNo Starting");

		ArrayList<LibraryStockEntryVO> getAccessionDetails = new ArrayList<LibraryStockEntryVO>();
		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_CODENAME);
			pstmt.setString(1,registrationVo.getCodeName()+"%");

			

			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibraryStockEntryVO libvo = new LibraryStockEntryVO();
				libvo.setCodeName(rs.getString("category_name"));
				libvo.setCodeId(rs.getString("category_id"));
				getAccessionDetails.add(libvo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
				+ " Control in getAccessionNo : getAccessionNo Ending");
		return getAccessionDetails;
	}

	@Override
	public List<LibraryStockEntryVO> getCodeByCodeName(LibraryStockEntryVO libVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getBookIssueDetailsByAccessionNo : getBookIssueDetailsByAccessionNo Starting");
		List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			pstmObj = conn.prepareStatement(LibrarySqlUtils.GET_CODE_BY_CODENAME);
			pstmObj.setString(1, libVo.getCodeName());
			rs = pstmObj.executeQuery();
			
			while (rs.next()) {
				LibraryStockEntryVO form = new LibraryStockEntryVO();
				form.setCodeId(rs.getString("category_code"));
				libVoList.add(form);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in getBookIssueDetailsByAccessionNo : getBookIssueDetailsByAccessionNo Ending");
		return libVoList;
	}

	@Override
	public List<LibraryStockEntryVO> getBookReservationDetailsByAccNo(
			LibraryStockEntryVO libVo) {logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in getBookReservationDetailsByAccNo : getBookReservationDetailsByAccNo Starting");
			List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
			PreparedStatement pstmObj = null;
			PreparedStatement psmt1 = null;
			ResultSet rs = null;
			ResultSet rs1= null;
			Connection conn = null;
			int count=0;
			try {
				conn = JDBCConnection.getSeparateConnection();
				
				pstmObj = conn.prepareStatement(LibrarySqlUtils.get_book_details_by_entry_id);
				pstmObj.setString(1, libVo.getAccessionNo());
				rs = pstmObj.executeQuery();
				
				while (rs.next()) {
					LibraryStockEntryVO form = new LibraryStockEntryVO();
					form.setItemType(rs.getString("Item_type"));
					form.setBookTitle(rs.getString("Book_title"));
					form.setAuthor(rs.getString("Author"));
					form.setCatid(rs.getString("category"));
					form.setCategory(rs.getString("category_name"));
					form.setDdc(rs.getString("DDC"));
					form.setCurrentStatus(rs.getString("Availability_status"));
					form.setImageurl(rs.getString("Stock_Entry_Image"));
					form.setShelfNo(rs.getString("Shelf_No"));
					form.setLocation(rs.getString("library_location_name"));
					form.setLibLocId(rs.getString("library_loc_id"));
					libVoList.add(form);
					
					
				}
				
				
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				try {
					if (rs != null && (!rs.isClosed())) {
						rs.close();
					}
					if (pstmObj != null && (!pstmObj.isClosed())) {
						pstmObj.close();
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
					+ " Control in getBookReservationDetailsByAccNo : getBookReservationDetailsByAccNo Ending");
			return libVoList;}

	@Override
	public List<LibraryStockEntryVO> getAccessionList() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getAccessionList : getAccessionList Starting");
		List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmObj = conn.prepareStatement(LibrarySqlUtils.get_accession_list);
		
			rs = pstmObj.executeQuery();
			
			
			while (rs.next()) {
				LibraryStockEntryVO form = new LibraryStockEntryVO();
				form.setAccessionNoId(rs.getString("accession_no"));
				form.setAccessionNo(rs.getString("Accession_number"));
				libVoList.add(form);
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in getAccessionList : getAccessionList Ending");
		return libVoList;
		}

	@Override
	public List<LibraryStockEntryVO> getTeachSubscriberName(
			LibraryStockEntryVO libVo) {logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in getSubscriberName : getSubscriberName Starting");
			List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
			PreparedStatement pstmObj = null;
			ResultSet rs = null;
			Connection conn = null;
			try {
				conn = JDBCConnection.getSeparateConnection();
				pstmObj = conn.prepareStatement(LibrarySqlUtils.get_subscriber_name);
				pstmObj.setString(1, libVo.getUserType());
			
				rs = pstmObj.executeQuery();
				
				
				while (rs.next()) {
					LibraryStockEntryVO form = new LibraryStockEntryVO();
					form.setSubscriberId(rs.getString("issued_to"));
					form.setSubscriberName(rs.getString("FirstName"));
					libVoList.add(form);
				}
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				try {
					if (rs != null && (!rs.isClosed())) {
						rs.close();
					}
					if (pstmObj != null && (!pstmObj.isClosed())) {
						pstmObj.close();
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
					+ " Control in getSubscriberName : getSubscriberName Ending");
			return libVoList;
			}

	@Override
	public List<LibraryStockEntryVO> getStuSubscriberName(
			LibraryStockEntryVO libVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getStuSubscriberName : getStuSubscriberName Starting");
		List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			pstmObj = conn.prepareStatement(LibrarySqlUtils.get_student_subscriber_name);
			pstmObj.setString(1, libVo.getUserType());
			rs = pstmObj.executeQuery();
			
			
			while (rs.next()) {
				LibraryStockEntryVO form = new LibraryStockEntryVO();
				form.setSubscriberId(rs.getString("issued_to"));
				form.setSubscriberName(rs.getString("student_fname_var"));
				libVoList.add(form);
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in getStuSubscriberName : getStuSubscriberName Ending");
		return libVoList;
		}

	@Override
	public List<LibraryStockEntryVO> getStudentSubNo(LibraryStockEntryVO libVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getStudentSubNo : getStudentSubNo Starting");
		List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			pstmObj = conn.prepareStatement(LibrarySqlUtils.get_student_sub_no);
			pstmObj.setString(1, libVo.getUserType());
			rs = pstmObj.executeQuery();
			
			
			while (rs.next()) {
				LibraryStockEntryVO form = new LibraryStockEntryVO();
				form.setSubscriberId(rs.getString("subscriber_id"));
				form.setSubscriberName(rs.getString("subscriber_id"));
				libVoList.add(form);
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in getStudentSubNo : getStudentSubNo Ending");
		return libVoList;
		}

	@Override
	public List<LibraryStockEntryVO> getTeacherSubNo(LibraryStockEntryVO libVo) {logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in getTeacherSubNo : getTeacherSubNo Starting");
	List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
	PreparedStatement pstmObj = null;
	ResultSet rs = null;
	Connection conn = null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		
		pstmObj = conn.prepareStatement(LibrarySqlUtils.get_student_sub_no);
		pstmObj.setString(1, libVo.getUserType());
		rs = pstmObj.executeQuery();
		
		
		while (rs.next()) {
			LibraryStockEntryVO form = new LibraryStockEntryVO();
			form.setSubscriberId(rs.getString("subscriber_id"));
			form.setSubscriberName(rs.getString("subscriber_id"));
			libVoList.add(form);
		}
		
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmObj != null && (!pstmObj.isClosed())) {
				pstmObj.close();
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
			+ " Control in getTeacherSubNo : getTeacherSubNo Ending");
	return libVoList;
	}

	@Override
	public List<LibraryStockEntryVO> getStuAccessionNo(LibraryStockEntryVO libVo) {logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in getStuAccessionNo : getStuAccessionNo Starting");
	List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
	PreparedStatement pstmObj = null;
	ResultSet rs = null;
	Connection conn = null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		
		pstmObj = conn.prepareStatement(LibrarySqlUtils.get_student_accession_list);
		pstmObj.setString(1, libVo.getUserType());
		rs = pstmObj.executeQuery();
		
		
		while (rs.next()) {
			LibraryStockEntryVO form = new LibraryStockEntryVO();
			form.setAccessionNoId(rs.getString("accession_no"));
			form.setAccessionNo(rs.getString("Accession_number"));
			libVoList.add(form);
		}
		
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmObj != null && (!pstmObj.isClosed())) {
				pstmObj.close();
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
			+ " Control in getStuAccessionNo : getStuAccessionNo Ending");
	return libVoList;
	}

	@Override
	public List<LibraryStockEntryVO> getTeachAccessionNo(
			LibraryStockEntryVO libVo) {logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in getTeachAccessionNo : getTeachAccessionNo Starting");
			List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
			PreparedStatement pstmObj = null;
			ResultSet rs = null;
			Connection conn = null;
			try {
				conn = JDBCConnection.getSeparateConnection();
				
				pstmObj = conn.prepareStatement(LibrarySqlUtils.get_teacher_accession_list);
				pstmObj.setString(1, libVo.getUserType());
				rs = pstmObj.executeQuery();
				
				
				while (rs.next()) {
					LibraryStockEntryVO form = new LibraryStockEntryVO();
					form.setAccessionNoId(rs.getString("accession_no"));
					form.setAccessionNo(rs.getString("Accession_number"));
					libVoList.add(form);
				}
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				try {
					if (rs != null && (!rs.isClosed())) {
						rs.close();
					}
					if (pstmObj != null && (!pstmObj.isClosed())) {
						pstmObj.close();
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
					+ " Control in getTeachAccessionNo : getTeachAccessionNo Ending");
			return libVoList;
}

	@Override
	public List<LibraryStockEntryVO> getOtherSubName(LibraryStockEntryVO libVo) {logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in getOtherSubName : getOtherSubName Starting");
	List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
	PreparedStatement pstmObj = null;
	ResultSet rs = null;
	Connection conn = null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		pstmObj = conn.prepareStatement(LibrarySqlUtils.get_other_subscriber_name);
		pstmObj.setString(1, libVo.getUserType());
	
		rs = pstmObj.executeQuery();
		
		
		while (rs.next()) {
			LibraryStockEntryVO form = new LibraryStockEntryVO();
			form.setSubscriberId(rs.getString("issued_to"));
			form.setSubscriberName(rs.getString("otherName"));
			libVoList.add(form);
		}
		
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmObj != null && (!pstmObj.isClosed())) {
				pstmObj.close();
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
			+ " Control in getOtherSubName : getOtherSubName Ending");
	return libVoList;
}

	@Override
	public List<LibraryStockEntryVO> getOtherSubNo(LibraryStockEntryVO libVo) {logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in getOtherSubNo : getOtherSubNo Starting");
	List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
	PreparedStatement pstmObj = null;
	ResultSet rs = null;
	Connection conn = null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		
		pstmObj = conn.prepareStatement(LibrarySqlUtils.get_student_sub_no);
		pstmObj.setString(1, libVo.getUserType());
		rs = pstmObj.executeQuery();
	
		
		while (rs.next()) {
			LibraryStockEntryVO form = new LibraryStockEntryVO();
			form.setSubscriberId(rs.getString("subscriber_id"));
			form.setSubscriberName(rs.getString("subscriber_id"));
			libVoList.add(form);
		}
		
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmObj != null && (!pstmObj.isClosed())) {
				pstmObj.close();
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
			+ " Control in getOtherSubNo : getOtherSubNo Ending");
	return libVoList;
}

	@Override
	public List<LibraryStockEntryVO> getOtherAccessionNo(
			LibraryStockEntryVO libVo) {logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in getOtherAccessionNo : getOtherAccessionNo Starting");
			List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
			PreparedStatement pstmObj = null;
			ResultSet rs = null;
			Connection conn = null;
			try {
				conn = JDBCConnection.getSeparateConnection();
				
				pstmObj = conn.prepareStatement(LibrarySqlUtils.get_teacher_accession_list);
				pstmObj.setString(1, libVo.getUserType());
				rs = pstmObj.executeQuery();
				System.out.println("student accession number list query>>"+pstmObj);
				
				while (rs.next()) {
					LibraryStockEntryVO form = new LibraryStockEntryVO();
					form.setAccessionNoId(rs.getString("accession_no"));
					form.setAccessionNo(rs.getString("Accession_number"));
					libVoList.add(form);
				}
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				try {
					if (rs != null && (!rs.isClosed())) {
						rs.close();
					}
					if (pstmObj != null && (!pstmObj.isClosed())) {
						pstmObj.close();
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
					+ " Control in getOtherAccessionNo : getOtherAccessionNo Ending");
			return libVoList;
		}

	@Override
	public List<LibraryStockEntryVO> getFromDateList(LibraryStockEntryVO libVo) {logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in getFromDateList : getFromDateList Starting");
	List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
	PreparedStatement pstmObj = null;
	ResultSet rs = null;
	Connection conn = null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		
		pstmObj = conn.prepareStatement(LibrarySqlUtils.get_from_date_list);
	
		rs = pstmObj.executeQuery();
		
		
		while (rs.next()) {
			LibraryStockEntryVO form = new LibraryStockEntryVO();
			form.setFromDate(HelperClass.convertDatabaseToUI(rs.getString("from_date")));
		
			libVoList.add(form);
		}
		
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmObj != null && (!pstmObj.isClosed())) {
				pstmObj.close();
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
			+ " Control in getFromDateList : getFromDateList Ending");
	return libVoList;
}

	@Override
	public List<LibraryStockEntryVO> getToDateList(LibraryStockEntryVO libVo) {logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in getToDateList : getToDateList Starting");
	List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
	PreparedStatement pstmObj = null;
	ResultSet rs = null;
	Connection conn = null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		
		pstmObj = conn.prepareStatement(LibrarySqlUtils.get_to_date_list);
	
		rs = pstmObj.executeQuery();
		
		
		while (rs.next()) {
			LibraryStockEntryVO form = new LibraryStockEntryVO();
			form.setToDate(HelperClass.convertDatabaseToUI(rs.getString("to_date")));
		
			libVoList.add(form);
		}
		
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmObj != null && (!pstmObj.isClosed())) {
				pstmObj.close();
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
			+ " Control in getToDateList : getToDateList Ending");
	return libVoList;
}

	@Override
	public LibraryStockEntryVO editReservationBook(String id) {
		logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in editReservationBook: editReservationBook : Starting");

	PreparedStatement pstmt = null;
	PreparedStatement pstmt1=null;
	ResultSet rs=null;
	ResultSet rs1 = null;
	Connection conn = null;

	LibraryStockEntryVO rsrv = null;
	try {
		conn = JDBCConnection.getSeparateConnection();

		pstmt = conn
				.prepareStatement(LibrarySqlUtils.get_data_to_edit_reservation_book);
		pstmt.setString(1, id);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			rsrv = new LibraryStockEntryVO();

			rsrv.setAccessionNo(rs.getString("Accession_number"));
			System.out.println("acc number......"+rs.getString("Accession_number"));
			rsrv.setItemType(rs.getString("item_type"));
			rsrv.setBookTitle(rs.getString("book_title"));
			rsrv.setAuthor(rs.getString("author"));
			rsrv.setCatid(rs.getString("category"));
			rsrv.setCategory(rs.getString("category_name"));
			rsrv.setDdc(rs.getString("ddc"));
			rsrv.setCurrentStatus(rs.getString("current_status"));
			rsrv.setUserType(rs.getString("userType"));
			rsrv.setShelfNo(rs.getString("shelf_no"));
			rsrv.setLocation(rs.getString("library_location_name"));
			rsrv.setLibLocId(rs.getString("library_location"));
			rsrv.setFromDate(HelperClass.convertDatabaseToUI(rs.getString("from_date")));
			rsrv.setToDate(HelperClass.convertDatabaseToUI(rs.getString("to_date")));
			rsrv.setIssued_to(rs.getString("issued_to"));
			rsrv.setHiddenReserveId(rs.getString("reservation_id"));
			rsrv.setEntry_id(rs.getString("Entry_id"));
			rsrv.setImageurl(rs.getString("Stock_Entry_Image"));
			
			
			String usertype=rs.getString("userType");
			
			if(usertype.equalsIgnoreCase("Teacher"))
			
			{
				pstmt1=conn.prepareStatement(LibrarySqlUtils.teacher);
				pstmt1.setString(1,rs.getString("subscriber_id"));
				pstmt1.setString(2,rs.getString("issued_to"));
				rs1=pstmt1.executeQuery();
				
				while(rs1.next())
				{
					rsrv.setSubscriberName(rs1.getString("teachNo"));
					rsrv.setDepartment(rs1.getString("DEPT_NAME"));
					rsrv.setDesignation(rs1.getString("designationName"));
					
				}
			}
			else if(usertype.equalsIgnoreCase("Student"))
			{

				/*pstmt1=conn.prepareStatement(LibrarySqlUtils.student_name_details);*/
				pstmt1=conn.prepareStatement(LibrarySqlUtils.student);
				
			
				pstmt1.setString(1,rs.getString("subscriber_id"));
				pstmt1.setString(2,rs.getString("issued_to"));
				rs1=pstmt1.executeQuery();
				
				while(rs1.next())
				{
					rsrv.setSubscriberName(rs1.getString("subNo"));
					rsrv.setClassName(rs1.getString("classdetails_name_var"));
					rsrv.setDivisionDescription(rs1.getString("classsection_name_var"));
					rsrv.setAdmissionNo(rs1.getString("student_admissionno_var"));
					
				}
			
			}
			else if(usertype.equalsIgnoreCase("other"))				
			{
				pstmt1=conn.prepareStatement(LibrarySqlUtils.other);
				pstmt1.setString(1,rs.getString("subscriber_id"));
				rs1=pstmt1.executeQuery();
				
				while(rs1.next())
				{
					rsrv.setSubscriberName(rs1.getString("otherNo"));
					rsrv.setContactNo(rs1.getString("otherContactNo"));
					rsrv.setAddress(rs1.getString("otherAddress"));
				
				}
			
								
			}
			
			
			System.out.println("edit query for reserve book"+pstmt);

		}
	} catch (SQLException sqle) {

		logger.error(sqle.getMessage(), sqle);
		sqle.printStackTrace();
	} catch (Exception e) {

		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {

		try {

			if (rs != null && (!rs.isClosed())&& rs1!=null&& rs1.isClosed()) {

				rs.close();
				rs1.close();
			}
			if (pstmt != null && (!pstmt.isClosed())) {

				pstmt.close();
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
			+ " Control in editReservationBook: editReservationBook  : Ending");

	return rsrv;

}

	@Override
	public String updateBookReservationDetails(LibraryStockEntryVO insert_issue) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in updateBookReservationDetails: updateBookReservationDetails : Starting");
		Date d = HelperClass.getCurrentTimestamp();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		CategoryTypeVO catVO = null;
		LibraryLocationVO liblocPO = null;
		String result = null;
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(LibrarySqlUtils.update_reservation_details);
			pstmt.setString(1, insert_issue.getAccessionNo());
			pstmt.setString(2, insert_issue.getItemType());
			pstmt.setString(3, insert_issue.getBookTitle());
			pstmt.setString(4, insert_issue.getAuthor());
			pstmt.setString(5, insert_issue.getCategory());
			pstmt.setString(6, insert_issue.getDdc());
			pstmt.setString(7, insert_issue.getCurrentStatus());
			pstmt.setString(8, insert_issue.getIssued_user_id());
			pstmt.setString(9, insert_issue.getIssued_to());
			pstmt.setString(10, insert_issue.getShelfNo());
			pstmt.setString(11, insert_issue.getLocation());
			pstmt.setString(12, HelperClass.convertUIToDatabase(insert_issue.getFromDate()));
			pstmt.setString(13, HelperClass.convertUIToDatabase(insert_issue.getToDate()));
			pstmt.setString(14, insert_issue.getHiddenReserveId());
			
			
			count = pstmt.executeUpdate();
			if (count > 0) {
				result = "successfully";
			} else {
				result = "failed";
			}

		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
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
				+ " Control in updateBookReservationDetails: updateBookReservationDetails  : Ending");

		return result;

	}

	@Override
	public String deleteReservedBook(String[] librarylocid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: deleteReservedBook : Starting");

		Connection conn = null;
		PreparedStatement pstmt = null;

		String result = null;
		int count = 0;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.delete_reserved_book_details);
			for (int i = 0; i < librarylocid.length; i++) {
				pstmt.setString(1, librarylocid[i]);
			
				count = pstmt.executeUpdate();

				if (count > 0) {
					result = "deletesuccess";
				} else {
					result = "deletefail";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
		return result;
	}

	@Override
	public boolean validateReservedBook(LibraryStockEntryVO reserve) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in validateReservedBook : validateReservedBook Starting");
		boolean validate_book = false;
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(LibrarySqlUtils.validate_reserved_book);
			
			pstmt.setString(1, reserve.getAccessionNo().trim());
			pstmt.setString(2, reserve.getSubscriberId());
			pstmt.setString(3, reserve.getFromDate().trim());
			pstmt.setString(4, reserve.getToDate().trim());
			
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			
			
			if (count > 0) {
				validate_book = true;
			} else {
				validate_book = false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
				+ " Control in validateReservedBook : validateReservedBook  Ending");

		return validate_book;
	}


	
	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStudentListDetails(String academic_year,String location,
			String select,String classname,String sectionid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {
			System.out.println(select);
			conn = JDBCConnection.getSeparateConnection();
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.MostWantedstudentlistdetailsbysubNo);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.MostWantedstudentlistdetailsbysubName);
			}
			pstmt.setString(1, academic_year);
			pstmt.setString(2, location);
			pstmt.setString(3, classname);
			pstmt.setString(4, sectionid);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public List<LibrarySearchSubscriberVO> SearchMostWantedStudentDetailsByAnyWhere(String searchTextVal,String location,String academic_year,
        String select,String startwith, String classname, String sectionid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
		if (startwith.equalsIgnoreCase("anywhere")) {
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.SearchMostWantedStudentDetailsByAnyWhere_BY_SUBNO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.SearchMostWantedStudentDetailsByAnyWhere_BY_SUBNAME);
			}
			pstmt.setString(1, "%" + searchTextVal + "%");
			pstmt.setString(2, "%" + searchTextVal + "%");
			pstmt.setString(3, "%" + searchTextVal + "%");
			pstmt.setString(4, "%" + searchTextVal + "%");
			pstmt.setString(5, "%" + searchTextVal + "%");
			pstmt.setString(6, "%" + searchTextVal + "%");
			pstmt.setString(7, "%" + searchTextVal + "%");
			pstmt.setString(8, "%" + searchTextVal + "%");
			pstmt.setString(9, location);
			pstmt.setString(10, academic_year);
			pstmt.setString(11, classname);
			pstmt.setString(12, sectionid);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));
				studentData.add(vo);
			}
		}
		else if (startwith.equalsIgnoreCase("startwith")) 
		{
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.SearchMostWantedStudentDetailsByAnyWhere_BY_SUBNO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.SearchMostWantedStudentDetailsByAnyWhere_BY_SUBNAME);
			}
			pstmt.setString(1, searchTextVal + "%");
			pstmt.setString(2, searchTextVal + "%");
			pstmt.setString(3, searchTextVal + "%");
			pstmt.setString(4, searchTextVal + "%");
			pstmt.setString(5, searchTextVal + "%");
			pstmt.setString(6, searchTextVal + "%");
			pstmt.setString(7, searchTextVal + "%");
			pstmt.setString(8, searchTextVal + "%");
			pstmt.setString(9, location);
			pstmt.setString(10, academic_year);
			pstmt.setString(11, classname);
			pstmt.setString(12, sectionid);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));
				studentData.add(vo);
			}
		}
		else if (startwith.equalsIgnoreCase("endswith")) 
		{
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.SearchMostWantedStudentDetailsByAnyWhere_BY_SUBNO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.SearchMostWantedStudentDetailsByAnyWhere_BY_SUBNAME);
			}
			pstmt.setString(1, "%" + searchTextVal);
			pstmt.setString(2, "%" + searchTextVal);
			pstmt.setString(3, "%" + searchTextVal);
			pstmt.setString(4, "%" + searchTextVal);
			pstmt.setString(5, "%" + searchTextVal);
			pstmt.setString(6, "%" + searchTextVal);
			pstmt.setString(7, "%" + searchTextVal);
			pstmt.setString(8, "%" + searchTextVal);
			pstmt.setString(9, location);
			pstmt.setString(10, academic_year);
			pstmt.setString(11, classname);
			pstmt.setString(12, sectionid);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs
						.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));
				studentData.add(vo);
			}
		}
		
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStudentListByClassName(String academic_year,
			String location,String classname,String sectionid,String select) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();

		try {

			conn = JDBCConnection.getSeparateConnection();

			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.MostWantedStudentList_ClassName_BY_SUBSCRIBER_NO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.MostWantedStudentList_ClassName_BY_SUBSCRIBER_NAME);
			}
			//pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistdetailsByClassName);
			pstmt.setString(1, academic_year);
			pstmt.setString(2, location);
			pstmt.setString(3, classname);
			// pstmt.setString(4,sectionid);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStudentListBySection(String academic_year,String location,
			String classname,String sectionid,String select) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();

		try {

			conn = JDBCConnection.getSeparateConnection();

			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.MostWantedStudentList_Section_BY_SUBSCRIBER_NO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.MostWantedStudentList_Section_BY_SUBSCRIBER_NAME);
			}

			pstmt.setString(1, academic_year);
			pstmt.setString(2, location);
			pstmt.setString(3, classname);
			pstmt.setString(4, sectionid);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				vo.setRollNO(rs.getString("student_rollno"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStaffListDetails(String academic_year,
			String location,String select,String department,String designation) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {

			conn = JDBCConnection.getSeparateConnection();
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_MOSTWANTED_STAFF_LIST_DETAILS_ORDER_BY_SUBNO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_MOSTWANTED_STAFF_LIST_DETAILS_ORDER_BY_SUBNAME);
			}
			pstmt.setString(1, location);
			pstmt.setString(2, academic_year);
			pstmt.setString(3, location);
			pstmt.setString(4, department);
			pstmt.setString(5, designation);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibrarySearchSubscriberVO> SearchMostWantedStaffDetailsByAnyWhere(String searchTextVal,String location,String select,String startwith) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
		if (startwith.equalsIgnoreCase("anywhere")) {
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_MOSTWANTED_STAFF_LIST_SEACH_ANYWHERE_BY_SUB_NO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_MOSTWANTED_STAFF_LIST_SEACH_ANYWHERE_BY_SUB_NAME);
			}
			//pstmt = conn.prepareStatement(LibrarySqlUtils.STAFF_LIST_SEACH_BY_ANYWHERE);
			pstmt.setString(1, "%" + searchTextVal + "%");
			pstmt.setString(2, "%" + searchTextVal + "%");
			pstmt.setString(3, "%" + searchTextVal + "%");
			pstmt.setString(4, "%" + searchTextVal + "%");
			pstmt.setString(5, "%" + searchTextVal + "%");
			pstmt.setString(6, "%" + searchTextVal + "%");
			pstmt.setString(7, location);
			pstmt.setString(8, location);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		}
		else if (startwith.equalsIgnoreCase("startwith")) {
			
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_MOSTWANTED_STAFF_LIST_SEARCH_STARTWITH_BY_SUB_NO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_MOSTWANTED_STAFF_LIST_SEARCH_STARTWITH_BY_SUB_NAME);
			}
			//pstmt = conn.prepareStatement(LibrarySqlUtils.STAFF_LIST_SEACH_BY_STARTWITH);
			pstmt.setString(1, searchTextVal + "%");
			pstmt.setString(2, searchTextVal + "%");
			pstmt.setString(3, searchTextVal + "%");
			pstmt.setString(4, searchTextVal + "%");
			pstmt.setString(5, searchTextVal + "%");
			pstmt.setString(6, searchTextVal + "%");
			pstmt.setString(7, location);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		}
		
		else if (startwith.equalsIgnoreCase("endswith")) {
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_MOSTWANTED_STAFF_LIST_SEARCH_ENDSWITH_BY_SUB_NO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_MOSTWANTED_STAFF_LIST_SEARCH_ENDSWITH_BY_SUB_NAME);
			}
			pstmt.setString(1, "%" + searchTextVal);
			pstmt.setString(2, "%" + searchTextVal);
			pstmt.setString(3, "%" + searchTextVal);
			pstmt.setString(4, "%" + searchTextVal);
			pstmt.setString(5, "%" + searchTextVal);
			pstmt.setString(6, "%" + searchTextVal);
			pstmt.setString(7, location);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));
				studentData.add(vo);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStaffdetailsByDepartment(String accyear_ID,String loc_ID,String department,String designation, String select) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			if (select.equalsIgnoreCase("subscriberno")) {
			pstmt = conn.prepareStatement(LibrarySqlUtils.MOSTWANTED_STAFF_LIST_DETAILS_BY_DEPARTMENT_SUBNO);
			}
			else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.MOSTWANTED_STAFF_LIST_DETAILS_BY_DEPARTMENT_SUBNAME);
			}
			pstmt.setString(1, loc_ID);
			pstmt.setString(2, accyear_ID);
			pstmt.setString(3, loc_ID);
			pstmt.setString(4, department);
			pstmt.setString(5, designation);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStaffdetailsByDesignation(String accyear_ID,
			String loc_ID,String department, java.lang.String designation,String select) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();

			if (select.equalsIgnoreCase("subscriberno")) {
			pstmt = conn.prepareStatement(LibrarySqlUtils.MOSTWANTED_STAFF_LIST_DETAILS_BY_DESIGNATION_SUBNO);
			}
			else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.MOSTWANTED_STAFF_LIST_DETAILS_BY_DESIGNATION_SUBNAME);
			}
			pstmt.setString(1, loc_ID);
			pstmt.setString(2, accyear_ID);
			pstmt.setString(3, loc_ID);
			pstmt.setString(4, department);
			pstmt.setString(5, designation);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedOthersListDetails(String location,String select,String academic_year) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		/* int count = 0; */
		try {
			System.out.println(select);
			conn = JDBCConnection.getSeparateConnection();

			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_MOSTWANTED_OTHERS_LIST_BY_SUBNO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_MOSTWANTED_OTHERS_LIST_BY_SUBNAME);
			}
			pstmt.setString(1, location);
			pstmt.setString(2, academic_year);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				/* count++; */
				/* vo.setSlno(count); */
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setOthereName(rs.getString("otherName"));
				vo.setOthereContactNumber(rs.getString("otherContactNo"));
				vo.setOthereMail_Id(rs.getString("otherEmail"));
				vo.setOthereAddress(rs.getString("otherAddress"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibrarySearchSubscriberVO> SearchMostWantedOthersDetailsByAnyWhere(String searchTextVal,
			String location,String select,String startwith) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
		if (startwith.equalsIgnoreCase("anywhere")){
			if (select.equalsIgnoreCase("subscriberno")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.MOSTWANTED_OTHER_LIST_SEACH_BY_ANYWHERE_BY_SUB_NO);
			} else if (select.equalsIgnoreCase("subscribername")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.MOSTWANTED_OTHER_LIST_SEACH_BY_ANYWHERE_BY_SUB_NAME);
			}
			pstmt.setString(1, "%" + searchTextVal + "%");
			pstmt.setString(2, "%" + searchTextVal + "%");
			pstmt.setString(3, "%" + searchTextVal + "%");
			pstmt.setString(4, "%" + searchTextVal + "%");
			pstmt.setString(5, "%" + searchTextVal + "%");
			pstmt.setString(6, "%" + searchTextVal + "%");
			pstmt.setString(7, location);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setOthereName(rs.getString("otherName"));
				vo.setOthereContactNumber(rs.getString("otherContactNo"));
				vo.setOthereMail_Id(rs.getString("otherEmail"));
				vo.setOthereAddress(rs.getString("otherAddress"));
				vo.setStatus(rs.getString("status"));

				studentData.add(vo);
			}
		}
	else if (startwith.equalsIgnoreCase("startwith")){
		if (select.equalsIgnoreCase("subscriberno")) {
			pstmt = conn.prepareStatement(LibrarySqlUtils.MOSTWANTED_OTHER_LIST_SEACH_BY_STARTWITH_BY_SUB_NO);
		} else if (select.equalsIgnoreCase("subscribername")) {
			pstmt = conn.prepareStatement(LibrarySqlUtils.MOSTWANTED_OTHER_LIST_SEACH_BY_STARTWITH_BY_SUB_NAME);
		}
		pstmt.setString(1, searchTextVal + "%");
		pstmt.setString(2, searchTextVal + "%");
		pstmt.setString(3, searchTextVal + "%");
		pstmt.setString(4, searchTextVal + "%");
		pstmt.setString(5, searchTextVal + "%");
		pstmt.setString(6, searchTextVal + "%");
		pstmt.setString(7, location);

		System.out.println(pstmt);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
			vo.setSubscriberId(rs.getString("subscriberId"));
			vo.setLocation(rs.getString("loc_ID"));
			vo.setSubssciberNo(rs.getString("subscriberNumber"));
			vo.setOthereName(rs.getString("otherName"));
			vo.setOthereContactNumber(rs.getString("otherContactNo"));
			vo.setOthereMail_Id(rs.getString("otherEmail"));
			vo.setOthereAddress(rs.getString("otherAddress"));
			vo.setStatus(rs.getString("status"));

			studentData.add(vo);
		}
	}
	else if (startwith.equalsIgnoreCase("endswith")){
		if (select.equalsIgnoreCase("subscriberno")) {
			pstmt = conn.prepareStatement(LibrarySqlUtils.MOSTWANTED_OTHER_LIST_SEACH_BY_ENDSWITH_BY_SUB_NO);
		} else if (select.equalsIgnoreCase("subscribername")) {
			pstmt = conn.prepareStatement(LibrarySqlUtils.MOSTWANTED_OTHER_LIST_SEACH_BY_ENDSWITH_BY_SUB_NAME);
		}
		pstmt.setString(1, "%" + searchTextVal);
		pstmt.setString(2, "%" + searchTextVal);
		pstmt.setString(3, "%" + searchTextVal);
		pstmt.setString(4, "%" + searchTextVal);
		pstmt.setString(5, "%" + searchTextVal);
		pstmt.setString(6, "%" + searchTextVal);
		pstmt.setString(7, location);
		System.out.println(pstmt);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
			vo.setSubscriberId(rs.getString("subscriberId"));
			vo.setLocation(rs.getString("loc_ID"));
			vo.setSubssciberNo(rs.getString("subscriberNumber"));
			vo.setOthereName(rs.getString("otherName"));
			vo.setOthereContactNumber(rs.getString("otherContactNo"));
			vo.setOthereMail_Id(rs.getString("otherEmail"));
			vo.setOthereAddress(rs.getString("otherAddress"));
			vo.setStatus(rs.getString("status"));

			studentData.add(vo);
		}
	}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public String savejournalsubscriptiondetail(LibraryJournalSubscriptionVo obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : savejournalsubscriptiondetail Starting");
		 Connection conn=null;
		 PreparedStatement pstmt=null;
		 int count=0;
		 String result=null;
		 String accessionno="";
		 String Entry_Id=obj.getEntryId();
		 
		 try{
			 String id = IDGenerator.getPrimaryKeyID("campus_library_journal_subscription");
			 conn=JDBCConnection.getSeparateConnection();
			 if(!obj.getEntryId().equalsIgnoreCase("") && obj.getEntryId()!=null){
				 
				 if(obj.getIsaccessionno().equalsIgnoreCase("true")){
					 accessionno = id+obj.getCode();
					 
				 }
				 pstmt=conn.prepareStatement(LibrarySqlUtils.SAVE_JOURNAL_SUBSCRIBER_UPDATE);
				 pstmt.setString(1,accessionno);
				 pstmt.setString(2, obj.getName());
				 pstmt.setString(3, obj.getCode());
				 pstmt.setString(4, obj.getJournaltype());
				 pstmt.setString(5,obj.getPublisher());
				 pstmt.setString(6, obj.getSupplier());
				 pstmt.setString(7, obj.getRatepercopy());
				 pstmt.setString(8, obj.getNumberofcopy());
				 pstmt.setString(9, obj.getTotalamount());
				 pstmt.setString(10, obj.getAnnualratepercopy());
				 pstmt.setString(11, obj.getDepartment());
				 pstmt.setString(12, obj.getOtherdetails());
				 pstmt.setString(13, HelperClass.convertUIToDatabase(obj.getDuedate()));
				 pstmt.setString(14, obj.getSubscriptionperiode());
				 pstmt.setString(15, HelperClass.convertUIToDatabase(obj.getTodate()));
				 pstmt.setString(16, HelperClass.convertUIToDatabase(obj.getDateon()));
				 pstmt.setString(17, obj.getCreatedby());
				pstmt.setTimestamp(18,HelperClass.getCurrentTimestamp());
				pstmt.setString(19, obj.getEntryId());
				
				   System.out.println("pstm///////////////////////"+pstmt);
				 count=pstmt.executeUpdate();
				 if(count>0){
					 result="success";
				 }
				 else{
					 result="fail";
				 }
				 
				 
			 }
			 else{
			 if(obj.getIsaccessionno().equalsIgnoreCase("true")){
				 accessionno = id+obj.getCode();
				 
			 }
			 pstmt=conn.prepareStatement(LibrarySqlUtils.SAVE_JOURNAL_SUBSCRIBER_ACCNO);
			 pstmt.setString(1,id);
			 pstmt.setString(2,accessionno);
			 pstmt.setString(3, obj.getName());
			 pstmt.setString(4, obj.getCode());
			 pstmt.setString(5, obj.getJournaltype());
			 pstmt.setString(6,obj.getPublisher());
			 pstmt.setString(7, obj.getSupplier());
			 pstmt.setString(8, obj.getRatepercopy());
			 pstmt.setString(9, obj.getNumberofcopy());
			 pstmt.setString(10, obj.getTotalamount());
			 pstmt.setString(11, obj.getAnnualratepercopy());
			 pstmt.setString(12, obj.getDepartment());
			 pstmt.setString(13, obj.getOtherdetails());
			 pstmt.setString(14, HelperClass.convertUIToDatabase(obj.getDuedate()));
			 pstmt.setString(15, obj.getSubscriptionperiode());
			 pstmt.setString(16, HelperClass.convertUIToDatabase(obj.getTodate()));
			 pstmt.setString(17, HelperClass.convertUIToDatabase(obj.getDateon()));
			 pstmt.setString(18, obj.getCreatedby());
			 pstmt.setString(19, obj.getAccyear());
             System.out.println("pstm///////////////////////"+pstmt);
			 count=pstmt.executeUpdate();
			 if(count>0){
				 result="addsuccess";
			 }
			 else{
				 result="fail";
			 }
			 } 
		 }

		 
		
		catch(Exception e){
			e.printStackTrace();
		}
		 finally{
			 if(pstmt!=null){
				 try{
					 pstmt.close();
				 }
				 catch(Exception e){
					 e.printStackTrace();
				 }
			 }
			 if(conn!=null){
				 try{
					 conn.close();
				 }
				 catch(Exception e){
					 e.printStackTrace();
				 }
			 }
		 }
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : savejournalsubscriptiondetail  Ending");
		return result;
	}

public ArrayList<LibrarySearchSubscriberVO> getAllBookDetails(LibrarySubsciberDetailsPojo pojo)
{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();

	if (pojo.getSelect().equalsIgnoreCase("all")) {
			if (pojo.getOrderBy().equalsIgnoreCase("accessionNo")) 
			{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ALL_BOOK_DETAILS_BY_ACCNO);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("title")) 
			{
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ALL_BOOK_DETAILS_BY_TITLE);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("author")) 
			{
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ALL_BOOK_DETAILS_BY_AUTHOR);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("location")) 
			{
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ALL_BOOK_DETAILS_BY_LOCATION);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("category")) 
			{
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ALL_BOOK_DETAILS_BY_CATEGORY);
			}
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setEntryId(rs.getString("Entry_id"));
				vo.setAccessionNo(rs.getString("Accession_number"));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setBookAuthor(rs.getString("Author"));
				vo.setLocation(rs.getString("library_location_name"));
				vo.setCategory(rs.getString("category_name"));
				studentData.add(vo);
		  }
			
		 }
	else if (pojo.getSelect().equalsIgnoreCase("issued")) 
			{
				if (pojo.getOrderBy().equalsIgnoreCase("accessionNo")) 
				{
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_BOOK_DETAILS_BY_ACCNO);
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("title")) 
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_BOOK_DETAILS_BY_TITLE);
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("author")) 
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_BOOK_DETAILS_BY_AUTHOR);
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("location")) 
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_BOOK_DETAILS_BY_LOCATION);
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("category")) 
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_BOOK_DETAILS_BY_CATEGORY);
				}
				System.out.println(pstmt);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
					vo.setEntryId(rs.getString("Entry_id"));
					vo.setAccessionNo(rs.getString("Accession_number"));
					vo.setBookTitle(rs.getString("Book_title"));
					vo.setBookAuthor(rs.getString("Author"));
					vo.setLocation(rs.getString("library_location_name"));
					vo.setCategory(rs.getString("category_name"));
					studentData.add(vo);
			  }
				
			}
	else if (pojo.getSelect().equalsIgnoreCase("available")) 
	{
		if (pojo.getOrderBy().equalsIgnoreCase("accessionNo")) 
		{
		pstmt = conn.prepareStatement(LibrarySqlUtils.GET_AVAILABLE_BOOK_DETAILS_BY_ACCNO);
		}
		else if (pojo.getOrderBy().equalsIgnoreCase("title")) 
		{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_AVAILABLE_BOOK_DETAILS_BY_TITLE);
		}
		else if (pojo.getOrderBy().equalsIgnoreCase("author")) 
		{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_AVAILABLE_BOOK_DETAILS_BY_AUTHOR);
		}
		else if (pojo.getOrderBy().equalsIgnoreCase("location")) 
		{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_AVAILABLE_BOOK_DETAILS_BY_LOCATION);
		}
		else if (pojo.getOrderBy().equalsIgnoreCase("category")) 
		{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_AVAILABLE_BOOK_DETAILS_BY_CATEGORY);
		}
		System.out.println(pstmt);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
			vo.setEntryId(rs.getString("Entry_id"));
			vo.setAccessionNo(rs.getString("Accession_number"));
			vo.setBookTitle(rs.getString("Book_title"));
			vo.setBookAuthor(rs.getString("Author"));
			vo.setLocation(rs.getString("library_location_name"));
			vo.setCategory(rs.getString("category_name"));
			studentData.add(vo);
	  }
	}
	else if (pojo.getSelect().equalsIgnoreCase("notavailable")) 
	{
		if (pojo.getOrderBy().equalsIgnoreCase("accessionNo")) 
		{
		pstmt = conn.prepareStatement(LibrarySqlUtils.GET_NOT_AVAILABLE_BOOK_DETAILS_BY_ACCNO);
		}
		else if (pojo.getOrderBy().equalsIgnoreCase("title")) 
		{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_NOT_AVAILABLE_BOOK_DETAILS_BY_TITLE);
		}
		else if (pojo.getOrderBy().equalsIgnoreCase("author")) 
		{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_NOT_AVAILABLE_DETAILS_BY_AUTHOR);
		}
		else if (pojo.getOrderBy().equalsIgnoreCase("location")) 
		{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_NOT_AVAILABLE_BOOK_DETAILS_BY_LOCATION);
		}
		else if (pojo.getOrderBy().equalsIgnoreCase("category")) 
		{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_NOT_AVAILABLE_BOOK_DETAILS_BY_CATEGORY);
		}
		System.out.println(pstmt);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
			vo.setEntryId(rs.getString("Entry_id"));
			vo.setAccessionNo(rs.getString("Accession_number"));
			vo.setBookTitle(rs.getString("Book_title"));
			vo.setBookAuthor(rs.getString("Author"));
			vo.setLocation(rs.getString("library_location_name"));
			vo.setCategory(rs.getString("category_name"));
			studentData.add(vo);
	   }
	}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} 
			catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchByaccNoandTitleAnyWhere(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {

			conn = JDBCConnection.getSeparateConnection();
			if (pojo.getSearch().equalsIgnoreCase("anywhere"))
			{
				
			  if(pojo.getOrderBy().equalsIgnoreCase("all")){
					
				if (pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") && !pojo.getSelect().equalsIgnoreCase("publisher") && !pojo.getSelect().equalsIgnoreCase("supplier")
						&& !pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_ACCNO);
				}
				else if (!pojo.getSelect().equalsIgnoreCase("accessionNo")&& pojo.getSelect().equalsIgnoreCase("title") &&
						!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_TITLE);
				}
				else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
						pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_AUTHOR);
				}
				else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
						!pojo.getSelect().equalsIgnoreCase("author") && pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_LOCATION);
				}
				else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
						!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && pojo.getSelect().equalsIgnoreCase("category"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_CATEGORY);
				 }
				}
			  else if(pojo.getOrderBy().equalsIgnoreCase("issued")){
					
				if (pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
						!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_ACCNO_BY_ISSUED);
				}
				else if (!pojo.getSelect().equalsIgnoreCase("accessionNo")&& pojo.getSelect().equalsIgnoreCase("title") &&
						!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_TITLE_BY_ISSUED);
				}
				else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
						pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_AUTHOR_BY_ISSUED);
				}
				else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
						!pojo.getSelect().equalsIgnoreCase("author") && pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_LOCATION_BY_ISSUED);
				}
				else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
						!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && pojo.getSelect().equalsIgnoreCase("category"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_CATEGORY_BY_ISSUED);
				}
			   }
			  else if(pojo.getOrderBy().equalsIgnoreCase("available")){
					
					if (pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_ACCNO_BY_AVAILABLE);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo")&& pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_TITLE_BY_AVAILABLE);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_AUTHOR_BY_AVAILABLE);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_LOCATION_BY_AVAILABLE);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_CATEGORY_BY_AVAILABLE);
					}
				 }
			  else if(pojo.getOrderBy().equalsIgnoreCase("notavailable")){
					
					if (pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_ACCNO_BY_UNAVAILABLE);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo")&& pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_TITLE_BY_UNAVAILABLE);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_AUTHOR_BY_UNAVAILABLE);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_LOCATION_BY_UNAVAILABLE);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_CATEGORY_BY_UNAVAILABLE);
					}
				 }
			  
					pstmt.setString(1, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(2, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(3, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(4, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(5, "%" + pojo.getSearchTextVal() + "%");

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setCategory(rs.getString("category_name"));
						vo.setBookAuthor(rs.getString("Author"));
						studentData.add(vo);
					}
		 }
			else if (pojo.getSearch().equalsIgnoreCase("startwith")){
				
				if(pojo.getOrderBy().equalsIgnoreCase("all")){
					
					if (pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_ACCNO);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo")&& pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_TITLE);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_AUTHOR);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_LOCATION);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_CATEGORY);
					 }
					}
				  else if(pojo.getOrderBy().equalsIgnoreCase("issued")){
						
					if (pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_ACCNO_BY_ISSUED);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo")&& pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_TITLE_BY_ISSUED);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_AUTHOR_BY_ISSUED);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_LOCATION_BY_ISSUED);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_CATEGORY_BY_ISSUED);
					}
				   }
				  else if(pojo.getOrderBy().equalsIgnoreCase("available")){
						
						if (pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
								!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_ACCNO_BY_AVAILABLE);
						}
						else if (!pojo.getSelect().equalsIgnoreCase("accessionNo")&& pojo.getSelect().equalsIgnoreCase("title") &&
								!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_TITLE_BY_AVAILABLE);
						}
						else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
								pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_AUTHOR_BY_AVAILABLE);
						}
						else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
								!pojo.getSelect().equalsIgnoreCase("author") && pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_LOCATION_BY_AVAILABLE);
						}
						else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
								!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_CATEGORY_BY_AVAILABLE);
						}
					 }
				  else if(pojo.getOrderBy().equalsIgnoreCase("notavailable")){
						
						if (pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
								!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_ACCNO_BY_UNAVAILABLE);
						}
						else if (!pojo.getSelect().equalsIgnoreCase("accessionNo")&& pojo.getSelect().equalsIgnoreCase("title") &&
								!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_TITLE_BY_UNAVAILABLE);
						}
						else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
								pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_AUTHOR_BY_UNAVAILABLE);
						}
						else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
								!pojo.getSelect().equalsIgnoreCase("author") && pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_LOCATION_BY_UNAVAILABLE);
						}
						else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
								!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_CATEGORY_BY_UNAVAILABLE);
						}
					 }
				
				
					pstmt.setString(1, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(2, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(3, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(4, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(5, "%" + pojo.getSearchTextVal() + "%");

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setCategory(rs.getString("category_name"));
						vo.setBookAuthor(rs.getString("Author"));
						studentData.add(vo);
					}
		 }
			else if (pojo.getSearch().equalsIgnoreCase("endswith")){
				
				if(pojo.getOrderBy().equalsIgnoreCase("all")){
					
					if (pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_ACCNO);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo")&& pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_TITLE);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_AUTHOR);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_LOCATION);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_CATEGORY);
					 }
					}
				  else if(pojo.getOrderBy().equalsIgnoreCase("issued")){
						
					if (pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_ACCNO_BY_ISSUED);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo")&& pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_TITLE_BY_ISSUED);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_AUTHOR_BY_ISSUED);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_LOCATION_BY_ISSUED);
					}
					else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
							!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && pojo.getSelect().equalsIgnoreCase("category"))
					{
						pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_CATEGORY_BY_ISSUED);
					}
				   }
				  else if(pojo.getOrderBy().equalsIgnoreCase("available")){
						
						if (pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
								!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_ACCNO_BY_AVAILABLE);
						}
						else if (!pojo.getSelect().equalsIgnoreCase("accessionNo")&& pojo.getSelect().equalsIgnoreCase("title") &&
								!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_TITLE_BY_AVAILABLE);
						}
						else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
								pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_AUTHOR_BY_AVAILABLE);
						}
						else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
								!pojo.getSelect().equalsIgnoreCase("author") && pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_LOCATION_BY_AVAILABLE);
						}
						else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
								!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_CATEGORY_BY_AVAILABLE);
						}
					 }
				  else if(pojo.getOrderBy().equalsIgnoreCase("notavailable")){
						
						if (pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
								!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_ACCNO_BY_UNAVAILABLE);
						}
						else if (!pojo.getSelect().equalsIgnoreCase("accessionNo")&& pojo.getSelect().equalsIgnoreCase("title") &&
								!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_TITLE_BY_UNAVAILABLE);
						}
						else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
								pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_AUTHOR_BY_UNAVAILABLE);
						}
						else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
								!pojo.getSelect().equalsIgnoreCase("author") && pojo.getSelect().equalsIgnoreCase("location") && !pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_LOCATION_BY_UNAVAILABLE);
						}
						else if (!pojo.getSelect().equalsIgnoreCase("accessionNo") && !pojo.getSelect().equalsIgnoreCase("title") &&
								!pojo.getSelect().equalsIgnoreCase("author") && !pojo.getSelect().equalsIgnoreCase("location") && pojo.getSelect().equalsIgnoreCase("category"))
						{
							pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ACCNO_TITLE_AUTHOR_LOCATION_CATEGORY_ORDERBY_CATEGORY_BY_UNAVAILABLE);
						}
					 }
				
					pstmt.setString(1, "%" + pojo.getSearchTextVal());
					pstmt.setString(2, "%" + pojo.getSearchTextVal());
					pstmt.setString(3, "%" + pojo.getSearchTextVal());
					pstmt.setString(4, "%" + pojo.getSearchTextVal());
					pstmt.setString(5, "%" + pojo.getSearchTextVal());

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setCategory(rs.getString("category_name"));
						vo.setBookAuthor(rs.getString("Author"));
						studentData.add(vo);
					}
			
		 }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public List<LibraryJournalSubscriptionVo> getJournalSubscriptionList() {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryDAOIMPL : getPublisherSettingList Starting");

			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			ArrayList<LibraryJournalSubscriptionVo> journal=new ArrayList<LibraryJournalSubscriptionVo>();
			try{

				conn=JDBCConnection.getSeparateConnection();
				pstmt=conn.prepareStatement(LibrarySqlUtils.LIST_Journal_Subscription);
				rs=pstmt.executeQuery();
				while(rs.next()){
					LibraryJournalSubscriptionVo vo =new LibraryJournalSubscriptionVo();
					vo.setEntryId(rs.getString("Entry_id"));
					vo.setName(rs.getString("Name"));
					vo.setAccessionno(rs.getString("Accession_No"));
					vo.setJournaltype(rs.getString("Journal_Type"));
					vo.setRatepercopy(rs.getString("Rate_Per_Copy"));
					vo.setNumberofcopy(rs.getString("No_Of_Copy"));
					vo.setSubscriptionperiode(rs.getString("Subscription_periode"));
					vo.setTodate( HelperClass.convertDatabaseToUI(rs.getString("To_date")));
					vo.setDuedate(HelperClass.convertDatabaseToUI(rs.getString("Due_date")));
					vo.setPublisher(rs.getString("Publisher_Name"));
					vo.setSupplier(rs.getString("Supplier_Name"));
					
					journal.add(vo);
				}
				
				           
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{

				if(rs!=null){
					try{
						rs.close();
					}
					catch (Exception e){
						e.printStackTrace();

					}
				}
				if(pstmt!=null){
					try{
						pstmt.close();
					}
					catch (Exception e){
						e.printStackTrace();

					}
				}
				if(conn!=null){
					try{
						conn.close();
					}
					catch (Exception e){
						e.printStackTrace();

					}
				}

			}


			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryDAOIMPL : getPublisherSettingList Ending");
			return journal;
		}

	@Override
	public LibraryJournalSubscriptionVo editeLibraryJournalSubscription(
			String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : editeLibraryJournalSubscription Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		LibraryJournalSubscriptionVo vo =null;

		try{

			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.EDIT_JOURNAL_SUBSCRIPTION);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				vo = new LibraryJournalSubscriptionVo();
				vo.setName(rs.getString("NAME"));
				vo.setAccessionno(rs.getString("Accession_No"));
				vo.setCode(rs.getString("CODE"));
				vo.setJournaltype(rs.getString("Journal_Type"));
				vo.setPublisher(rs.getString("Publisher_Id"));
				vo.setSupplier(rs.getString("Supplier_Id"));
				vo.setRatepercopy(rs.getString("Rate_Per_Copy"));
				vo.setNumberofcopy(rs.getString("No_Of_Copy"));
				vo.setDepartment(rs.getString("Department"));
				vo.setOtherdetails(rs.getString("Other_Details"));
				vo.setDateon(HelperClass.convertDatabaseToUI(rs.getString("Date_On")));
				vo.setSubscriptionperiode(rs.getString("Subscription_periode"));
				vo.setTodate(HelperClass.convertDatabaseToUI(rs.getString("To_date")));
				vo.setDuedate(HelperClass.convertDatabaseToUI(rs.getString("Due_date")));
				vo.setTotalamount(rs.getString("Total_Amount"));
				vo.setAnnualratepercopy(rs.getString("Anual_Rate_Per_Copy"));
			
				
				
			}

		}
	

		catch(Exception  e){
			e.printStackTrace();

		}
		finally{

			if(rs!=null){
				try{
					rs.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Ending");
		return vo ;
	}

	@Override
	public String deleteJournalSbscription(String[] deleteId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : deletepublisherSetting Starting");


		PreparedStatement pst = null;
		Connection conn = null;
		int result = 0;
		String status = null;

		try{
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(LibrarySqlUtils.DETAIL_JOURNAL_SUBSCRIPTION);
			for(int i=0;i<deleteId.length;i++){
				pst.setString(1,deleteId[i]);
				result = pst.executeUpdate();
			}

			if (result > 0) {
				status = MessageConstants.DELETE_JOURNAL_SUBSCRIPTION_SUCCESS;
			} else {
				status = MessageConstants.DELETE_JOURNAL_SUBSCRIPTION_FAIL;

			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

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

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : deletepublisherSetting Ending");

		return status;
	}

	@Override
	public List<LibraryJournalSubscriptionVo> getJournalSubscriptioncodelist() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LibraryJournalSubscriptionVo> journal=new ArrayList<LibraryJournalSubscriptionVo>();
		try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.LIST_CODE_LIST);
			rs=pstmt.executeQuery();
			System.out.println("LIST "+pstmt);
			while(rs.next()){
				LibraryJournalSubscriptionVo vo =new LibraryJournalSubscriptionVo();
				vo.setEntryId(rs.getString("Entry_id"));
				vo.setCode(rs.getString("CODE"));
				journal.add(vo);
			}
			
			           
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{

			if(rs!=null){
				try{
					rs.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Ending");
		return journal;
	}

	@Override
	public List<LibraryJournalSubscriptionVo> getnamelist() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LibraryJournalSubscriptionVo> journal=new ArrayList<LibraryJournalSubscriptionVo>();
		try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.LIST_NAME_LIST);
			rs=pstmt.executeQuery();
			System.out.println("LIST "+pstmt);
			while(rs.next()){
				LibraryJournalSubscriptionVo vo =new LibraryJournalSubscriptionVo();
				vo.setEntryId(rs.getString("Entry_id"));
				vo.setName(rs.getString("NAME"));
				journal.add(vo);
			}
			
			           
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{

			if(rs!=null){
				try{
					rs.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Ending");
		return journal;
	}

	@Override
	public ArrayList<LibraryJournalSubscriptionVo> journalsubscriptionDetailsSearch(
			String searchTextVal , String name) {
	
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryDAOIMPL : publisherDetailsSearch Starting");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Connection conn = null;
			ArrayList<LibraryJournalSubscriptionVo> List=new ArrayList<LibraryJournalSubscriptionVo>();
			int count = 0;
			try {

				
				conn = JDBCConnection.getSeparateConnection();

				pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_JOURNAL_SUBSCRIPTION);

				//pstmt = conn.prepareStatement(LibrarySqlUtils.studentlistSearchByAnyWhere);
				pstmt.setString(1, "%"+searchTextVal+"%");
				pstmt.setString(2, "%"+searchTextVal+"%");
				pstmt.setString(3, "%"+searchTextVal+"%");
				pstmt.setString(4, "%"+searchTextVal+"%");
				pstmt.setString(5, "%"+searchTextVal+"%");
				pstmt.setString(5, "%"+searchTextVal+"%");
				pstmt.setString(6, "%"+searchTextVal+"%");
				pstmt.setString(7, "%"+searchTextVal+"%");
				pstmt.setString(8, "%"+searchTextVal+"%");
			
				
				System.out.println(pstmt);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					
				
					LibraryJournalSubscriptionVo vo = new LibraryJournalSubscriptionVo();
					vo.setEntryId(rs.getString("Entry_id"));
					vo.setName(rs.getString("NAME"));
					vo.setAccessionno(rs.getString("Accession_No"));
					vo.setJournaltype(rs.getString("Journal_Type"));
					vo.setPublisher(rs.getString("Publisher_Name"));
					vo.setSupplier(rs.getString("Supplier_Name"));
					vo.setRatepercopy(rs.getString("Rate_Per_Copy"));
					vo.setNumberofcopy(rs.getString("No_Of_Copy"));
					vo.setSubscriptionperiode(rs.getString("Subscription_periode"));
					vo.setTodate(HelperClass.convertDatabaseToUI(rs.getString("To_date")));
					vo.setDuedate(HelperClass.convertDatabaseToUI(rs.getString("Due_date")));
				
				 
					List.add(vo);
				} 
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null && (!rs.isClosed())) {
						rs.close();
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
				}
			}


			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryDAOIMPL : publisherDetailsSearch Ending");
			return List;
	}


	public ArrayList<LibrarySearchSubscriberVO> getAllBookPublisherDetails(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
				
					
	if (pojo.getSelect().equalsIgnoreCase("all")) {
			if (pojo.getOrderBy().equalsIgnoreCase("publisher")) 
			{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ALL_BOOK_DETAILS_BY_PUBLISHER);
			}
		 }
	else if (pojo.getSelect().equalsIgnoreCase("issued")) 
			{
				if (pojo.getOrderBy().equalsIgnoreCase("publisher")) 
				{
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_BOOK_DETAILS_BY_PUBLISHER);
				}
			}
	else if (pojo.getSelect().equalsIgnoreCase("available")) 
	{
		   if (pojo.getOrderBy().equalsIgnoreCase("publisher")) 
		    {
		      pstmt = conn.prepareStatement(LibrarySqlUtils.GET_AVAILABLE_BOOK_DETAILS_BY_PUBLISHER);
		    }
	}
			else if (pojo.getSelect().equalsIgnoreCase("notavailable")) 
			{
				  if (pojo.getOrderBy().equalsIgnoreCase("publisher")) 
				 {
				   pstmt = conn.prepareStatement(LibrarySqlUtils.GET_NOT_AVAILABLE_BOOK_DETAILS_BY_PUBLISHER);
				 }
			}
					System.out.println(pstmt);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setBookAuthor(rs.getString("Author"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setPublisher(rs.getString("Publisher_Name"));
						studentData.add(vo);
				  }
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (rs != null && (!rs.isClosed())) {
							rs.close();
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
					} catch (Exception e1) {

						logger.error(e1.getMessage(), e1);
						e1.printStackTrace();
					}
				}
				return studentData;
			}	


	@Override
	public List<LibraryStockEntryVO> getstockEntryList(String locId, String itemId, String regdateId, String booktitle, String authorId, String pubId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LibraryStockEntryVO> stocklist=new ArrayList<LibraryStockEntryVO>();
		try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.LIST_STOCK_STATEMENT);
			pstmt.setString(1, locId);
			pstmt.setString(2, itemId);
			pstmt.setString(3, booktitle);
			pstmt.setString(4, authorId);
			pstmt.setString(5, pubId);
			
			
			rs=pstmt.executeQuery();
			System.out.println("LIST "+pstmt);
			while(rs.next()){
				LibraryStockEntryVO vo =new LibraryStockEntryVO();
				vo.setEntry_id(rs.getString("Entry_id"));
				vo.setAccessionNo(rs.getString("Accession_number"));
				vo.setItemType(rs.getString("Item_type"));
				vo.setRegDate( HelperClass.convertDatabaseToUI(rs.getString("Reg_date")));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setAuthor(rs.getString("Author"));
				vo.setDdc(rs.getString("DDC"));
	        	vo.setNo_of_Copies(rs.getString("No_of_copies"));
	        	vo.setLocation(rs.getString("library_location_name"));
	        	vo.setCurrentStatus(rs.getString("Availability_status"));
	        	vo.setPublisher(rs.getString("Publisher_Name"));
				stocklist.add(vo);
			}
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{

			if(rs!=null){
				try{
					rs.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Ending");
		return stocklist;
	}


	public List<LibrarySearchSubscriberVO> SearchBookSearchByPublisherAnyWhere(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			if (pojo.getSearch().equalsIgnoreCase("anywhere")){
				
				if (pojo.getOrderBy().equalsIgnoreCase("all"))
				{
					if (pojo.getSelect().equalsIgnoreCase("publisher"))
					{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_PUBLISHER);
				 }
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("issued"))
				{
					if (pojo.getSelect().equalsIgnoreCase("publisher"))
					{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_PUBLISHER_BY_ISSUE);
				  }
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("available"))
				{
					if (pojo.getSelect().equalsIgnoreCase("publisher"))
					{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_PUBLISHER_BY_AVAILABLE);
				   }
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable"))
				{
					if (pojo.getSelect().equalsIgnoreCase("publisher"))
					{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_PUBLISHER_BY_UNAVAILABLE);
				    }
				}
					pstmt.setString(1, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(2, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(3, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(4, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(5, "%" + pojo.getSearchTextVal() + "%");

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setPublisher(rs.getString("Publisher_Name"));
						vo.setBookAuthor(rs.getString("Author"));
						studentData.add(vo);
					}
			
		 }
			else if (pojo.getSearch().equalsIgnoreCase("startwith")){
				if (pojo.getOrderBy().equalsIgnoreCase("all"))
				{
					if (pojo.getSelect().equalsIgnoreCase("publisher"))
					{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_PUBLISHER);
				    }
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("issued"))
				{
					if (pojo.getSelect().equalsIgnoreCase("publisher"))
					{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_PUBLISHER_BY_ISSUE);
				    }
				}	
				else if (pojo.getOrderBy().equalsIgnoreCase("available"))
				{
					if (pojo.getSelect().equalsIgnoreCase("publisher"))
					{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_PUBLISHER_BY_AVAILABLE);
				    }
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable"))
				{
					if (pojo.getSelect().equalsIgnoreCase("publisher"))
					{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_PUBLISHER_BY_UNAVAILABLE);
				  }
				}
					pstmt.setString(1, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(2, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(3, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(4, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(5, "%" + pojo.getSearchTextVal() + "%");

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setPublisher(rs.getString("Publisher_Name"));
						vo.setBookAuthor(rs.getString("Author"));
						studentData.add(vo);
					}
			
		 }
			else if (pojo.getSearch().equalsIgnoreCase("endswith")){
				
				if (pojo.getOrderBy().equalsIgnoreCase("all"))
				{
					if (pojo.getSelect().equalsIgnoreCase("publisher"))
					{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_PUBLISHER);
				    }
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("issued"))
				{
					if (pojo.getSelect().equalsIgnoreCase("publisher"))
					{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_PUBLISHER_BY_ISSUE);
				    }
				}	
				else if (pojo.getOrderBy().equalsIgnoreCase("available"))
				{
					if (pojo.getSelect().equalsIgnoreCase("publisher"))
					{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_PUBLISHER_BY_AVAILABLE);
				    }
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable"))
				{
					if (pojo.getSelect().equalsIgnoreCase("publisher"))
					{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_PUBLISHER_BY_UNAVAILABLE);
				  }
				}
					pstmt.setString(1, "%" + pojo.getSearchTextVal());
					pstmt.setString(2, "%" + pojo.getSearchTextVal());
					pstmt.setString(3, "%" + pojo.getSearchTextVal());
					pstmt.setString(4, "%" + pojo.getSearchTextVal());
					pstmt.setString(5, "%" + pojo.getSearchTextVal());

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setPublisher(rs.getString("Publisher_Name"));
						vo.setBookAuthor(rs.getString("Author"));
						studentData.add(vo);
					}
			
		 }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public List<LibraryStockEntryVO> getlocationlist(String locId, String itemId, String regdateId, 
			String booktitle, String authorId, String pubId) {
			logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LibraryStockEntryVO> stocklist=new ArrayList<LibraryStockEntryVO>();
		int count=0;
		
		try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.GET_LIST_LOCATION);
			pstmt.setString(1, locId);
			pstmt.setString(2, itemId);
			pstmt.setString(3, authorId);
			pstmt.setString(4, booktitle);
			pstmt.setString(5, pubId);
		
			rs=pstmt.executeQuery();
			System.out.println("LIST "+pstmt);
			while(rs.next()){
				count++;
				LibraryStockEntryVO vo =new LibraryStockEntryVO();
				vo.setCount(count);
				vo.setEntry_id(rs.getString("Entry_id"));
				vo.setAccessionNo(rs.getString("Accession_number"));
				vo.setItemType(rs.getString("Item_type"));
				vo.setRegDate( HelperClass.convertDatabaseToUI(rs.getString("Reg_date")));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setAuthor(rs.getString("Author"));
				vo.setDdc(rs.getString("DDC"));
	        	vo.setNo_of_Copies(rs.getString("No_of_copies"));
	        	vo.setLocation(rs.getString("library_location_name"));
	        	vo.setCurrentStatus(rs.getString("Availability_status"));
	        	vo.setPublisher(rs.getString("Publisher_Name"));
	        	vo.setSuppliedBy(rs.getString("Supplier_Name"));
				stocklist.add(vo);
			}
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{

			if(rs!=null){
				try{
					rs.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Ending");
		return stocklist;
	}

	@Override
	public List<LibraryStockEntryVO> getitemlistList() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LibraryStockEntryVO> stocklist=new ArrayList<LibraryStockEntryVO>();
		try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.LIST_ITEM);
			rs=pstmt.executeQuery();
			System.out.println("LIST "+pstmt);
			while(rs.next()){
				LibraryStockEntryVO vo =new LibraryStockEntryVO();
				vo.setItemType(rs.getString("Item_type"));
				stocklist.add(vo);
			}
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{

			if(rs!=null){
				try{
					rs.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Ending");
		return stocklist;
	}

	@Override
	public List<LibraryStockEntryVO> booklist() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LibraryStockEntryVO> stocklist=new ArrayList<LibraryStockEntryVO>();
		try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.LIST_BOOK);
			rs=pstmt.executeQuery();
			System.out.println("LIST "+pstmt);
			while(rs.next()){
				LibraryStockEntryVO vo =new LibraryStockEntryVO();
				vo.setBookTitle(rs.getString("Book_title"));
				stocklist.add(vo);
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{

			if(rs!=null){
				try{
					rs.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Ending");
		return stocklist;
	}

	@Override
	public List<LibraryStockEntryVO> authorlist() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LibraryStockEntryVO> stocklist=new ArrayList<LibraryStockEntryVO>();
		try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.LIST_AUTHOR);
			rs=pstmt.executeQuery();
			System.out.println("LIST "+pstmt);
			while(rs.next()){
				LibraryStockEntryVO vo =new LibraryStockEntryVO();
				vo.setAuthor(rs.getString("Author"));
				stocklist.add(vo);
			}
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{

			if(rs!=null){
				try{
					rs.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getPublisherSettingList Ending");
		return stocklist;
	}
	
	public ArrayList<LibrarySearchIssueDetailsVO> getSubscriberDetailStudentExcelReport(LibrarySearchIssueDetailsVO obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: gettransportfeeDetails : Starting");
		PreparedStatement pstmt= null;
	
		ResultSet rs=null;
		
		Connection conn = null;
	
		int slno = 0;
		ArrayList<LibrarySearchIssueDetailsVO> list = new ArrayList<LibrarySearchIssueDetailsVO>();
	
		try{
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement("SELECT DISTINCT clsd.subscriberNumber, CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var)END studentname, stu.student_admissionno_var,cscd.student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_library_subscriber_details clsd LEFT JOIN campus_student stu ON clsd.studentId = stu.student_id_int LEFT JOIN campus_classdetail ccd ON clsd.Class_Id = ccd.classdetail_id_int LEFT JOIN campus_classsection ccs ON clsd.section_Id = ccs.classsection_id_int LEFT JOIN campus_student_classdetails cscd ON clsd.studentId = cscd.student_id_int WHERE cscd.fms_acadamicyear_id_int LIKE ?  AND cscd.locationId LIKE ? AND cscd.classdetail_id_int LIKE ? AND cscd.classsection_id_int LIKE ? ORDER BY ccd.classdetails_name_var,ccs.classsection_name_var,studentname");
		
			pstmt.setString(1,obj.getAccyrid());
			pstmt.setString(2,obj.getLocationid());
			pstmt.setString(3,obj.getClassid());
			pstmt.setString(4,obj.getSectionid());
			
			rs = pstmt.executeQuery();
			
			System.out.println("subscriberdetail query>>>"+pstmt);
			
			while(rs.next()){
				LibrarySearchIssueDetailsVO vo = new LibrarySearchIssueDetailsVO();
				slno++;
				vo.setSlno(slno);
				
				vo.setSubscriberno(rs.getString("subscriberNumber"));
				vo.setStuname(rs.getString("studentname"));
				vo.setAdmission(rs.getString("student_admissionno_var"));
				vo.setRollno(rs.getString("student_rollno"));
				vo.setClassname(rs.getString("classdetails_name_var"));
				vo.setSecname(rs.getString("classsection_name_var"));	
				
				list.add(vo);
				}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
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
		return list;
	}


	public ArrayList<LibrarySearchSubscriberVO> getAllBookItemTypeDetails(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();

	if (pojo.getSelect().equalsIgnoreCase("all")) {
			if (pojo.getOrderBy().equalsIgnoreCase("itemtype")) 
			{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ALL_BOOK_DETAILS_BY_ITEMTYPE);
			}
		 }
	else if (pojo.getSelect().equalsIgnoreCase("issued")) 
			{
				if (pojo.getOrderBy().equalsIgnoreCase("itemtype")) 
				{
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_BOOK_DETAILS_BY_ITEMTYPE);
				}
			}
	else if (pojo.getSelect().equalsIgnoreCase("available")) 
	{
		   if (pojo.getOrderBy().equalsIgnoreCase("itemtype")) 
		    {
		      pstmt = conn.prepareStatement(LibrarySqlUtils.GET_AVAILABLE_BOOK_DETAILS_BY_ITEMTYPE);
		    }
	}
	else if (pojo.getSelect().equalsIgnoreCase("notavailable")) 
	{
		  if (pojo.getOrderBy().equalsIgnoreCase("itemtype")) 
		 {
		   pstmt = conn.prepareStatement(LibrarySqlUtils.GET_NOT_AVAILABLE_BOOK_DETAILS_BY_ITEMTYPE);
		 }
	}
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setEntryId(rs.getString("Entry_id"));
				vo.setAccessionNo(rs.getString("Accession_number"));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setBookAuthor(rs.getString("Author"));
				vo.setLocation(rs.getString("library_location_name"));
				vo.setItemType(rs.getString("Item_type"));
				studentData.add(vo);
		  }
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}
	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getStaffSubscriberDetailReport(
			LibrarySearchIssueDetailsVO obj) {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: gettransportfeeDetails : Starting");
			PreparedStatement pstmt= null;
		
			ResultSet rs=null;
			
			Connection conn = null;
		
			int slno = 0;
			ArrayList<LibrarySearchIssueDetailsVO> list = new ArrayList<LibrarySearchIssueDetailsVO>();
		
			try{
				conn = JDBCConnection.getSeparateConnection();
				
				pstmt = conn.prepareStatement("SELECT DISTINCT clsd.subscriberNumber, CASE WHEN cteach.FirstName IS NULL THEN cteach.LastName ELSE CONCAT(cteach.FirstName,' ',cteach.LastName)END staffname,cteach.department ,cteach.designation,clsd.status FROM campus_library_subscriber_details clsd LEFT JOIN campus_teachers cteach ON cteach.TeacherID = clsd.staffId LEFT JOIN campus_library_location cll ON cll.library_loc_id = clsd.loc_ID WHERE clsd.accyear_ID LIKE ?  AND cll.loc_ID LIKE ? AND cteach.department LIKE ? AND cteach.designation LIKE ? ");
			
				pstmt.setString(1,obj.getAccyrid());
				pstmt.setString(2,obj.getLocationid());
				pstmt.setString(3,obj.getDepartment());
				pstmt.setString(4,obj.getDesigantion());
				
				rs = pstmt.executeQuery();
				
				System.out.println("subscriberdetail staff query>>>"+pstmt);
				
				while(rs.next()){
					LibrarySearchIssueDetailsVO vo = new LibrarySearchIssueDetailsVO();
					slno++;
					vo.setSlno(slno);
					
					vo.setSubscriberno(rs.getString("subscriberNumber"));
					vo.setStaffName(rs.getString("staffname"));
					vo.setDepartmentname(rs.getString("department"));
					vo.setDesignationname(rs.getString("designation"));
					vo.setStatus(rs.getString("status"));
					
					list.add(vo);
					}
			
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
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
			return list;
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchByItemTypeAnyWhere(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			if (pojo.getSearch().equalsIgnoreCase("anywhere")){
				
				if (pojo.getOrderBy().equalsIgnoreCase("all"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ITEMTYPE);
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("issued"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ITEMTYPE_IN_ISSUE);
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("available"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ITEMTYPE_IN_AVAILABLE);
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ITEMTYPE_IN_UNAVAILABLE);
				}
					
					pstmt.setString(1, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(2, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(3, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(4, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(5, "%" + pojo.getSearchTextVal() + "%");

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setBookAuthor(rs.getString("Author"));
						vo.setItemType(rs.getString("Item_type"));
						studentData.add(vo);
					}
			
		 }
			else if (pojo.getSearch().equalsIgnoreCase("startwith")){
				
				if (pojo.getOrderBy().equalsIgnoreCase("all"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ITEMTYPE);
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("issued"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ITEMTYPE_IN_ISSUE);
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("available"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ITEMTYPE_IN_AVAILABLE);
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ITEMTYPE_IN_UNAVAILABLE);
				}
					pstmt.setString(1, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(2, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(3, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(4, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(5, "%" + pojo.getSearchTextVal() + "%");

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setBookAuthor(rs.getString("Author"));
						vo.setItemType(rs.getString("Item_type"));
						studentData.add(vo);
					}
		 }
			else if (pojo.getSearch().equalsIgnoreCase("endswith")){
				if (pojo.getOrderBy().equalsIgnoreCase("all"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ITEMTYPE);
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("issued"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ITEMTYPE_IN_ISSUE);
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("available"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ITEMTYPE_IN_AVAILABLE);
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable"))
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_ITEMTYPE_IN_UNAVAILABLE);
				}
					pstmt.setString(1, "%" + pojo.getSearchTextVal());
					pstmt.setString(2, "%" + pojo.getSearchTextVal());
					pstmt.setString(3, "%" + pojo.getSearchTextVal());
					pstmt.setString(4, "%" + pojo.getSearchTextVal());
					pstmt.setString(5, "%" + pojo.getSearchTextVal());

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setBookAuthor(rs.getString("Author"));
						vo.setItemType(rs.getString("Item_type"));
						studentData.add(vo);
					}
		 }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	@Override
	public List<LibraryStockEntryVO> getReservationListReport(String location, String accId, String subId, String accNo, String bookId,String fromdat, String todate,String date) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: getReservationListDetails: Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
         int count=0;
		ArrayList<LibraryStockEntryVO> ReservationList = new ArrayList<LibraryStockEntryVO>();
		try{
			
			if(subId.equalsIgnoreCase("all")){
				subId="%%";
			}
			conn=JDBCConnection.getSeparateConnection();
			
			 if(date.equalsIgnoreCase("allfordate")){	 
			pstmt=conn.prepareStatement(LibrarySqlUtils.RESERVATION_DETAIL_REPORT);
			pstmt.setString(1, location);
			pstmt.setString(2, accId);
			pstmt.setString(3, subId);
			pstmt.setString(4, accNo);
			pstmt.setString(5, bookId);
			
			
			
			rs=pstmt.executeQuery();
			while(rs.next()){
				count++;
				LibraryStockEntryVO from=new LibraryStockEntryVO();
                from.setCount(count);
				from.setAccessionNo(rs.getString("Accession_number"));
				from.setReservationId(rs.getString("reservation_id"));
				from.setAuthor(rs.getString("author"));
				from.setBookTitle(rs.getString("book_title"));
				from.setLocation(rs.getString("library_location_name"));
				from.setCurrentStatus(rs.getString("current_status"));
				from.setIssued_to(rs.getString("issued_to"));
				from.setFromDate(HelperClass.convertDatabaseToUI(rs.getString("from_date")));
				from.setToDate(HelperClass.convertDatabaseToUI(rs.getString("to_date")));
				from.setSubscriberName(rs.getString("subsCriberName"));
				from.setUserType(rs.getString("userType"));
				ReservationList.add(from);
			}
			 }
			 
			
		else if(date.equalsIgnoreCase("all")){
			pstmt=conn.prepareStatement(LibrarySqlUtils.RESERVATION_DETAIL_REPORT_DATE);
			pstmt.setString(1, location);
			pstmt.setString(2, accId);
			pstmt.setString(3, subId);
			pstmt.setString(4, accNo);
			pstmt.setString(5, bookId);
			pstmt.setString(6, HelperClass.convertUIToDatabase(fromdat));
			pstmt.setString(7, HelperClass.convertUIToDatabase(todate));
			pstmt.setString(8, HelperClass.convertUIToDatabase(fromdat));
			pstmt.setString(9, HelperClass.convertUIToDatabase(todate));
			
			
			
			rs=pstmt.executeQuery();
			while(rs.next()){
				count++;
				LibraryStockEntryVO from=new LibraryStockEntryVO();
                from.setCount(count);
				from.setAccessionNo(rs.getString("Accession_number"));
				from.setReservationId(rs.getString("reservation_id"));
				from.setAuthor(rs.getString("author"));
				from.setBookTitle(rs.getString("book_title"));
				from.setLocation(rs.getString("library_location_name"));
				from.setCurrentStatus(rs.getString("current_status"));
				from.setIssued_to(rs.getString("issued_to"));
				from.setFromDate(HelperClass.convertDatabaseToUI(rs.getString("from_date")));
				from.setToDate(HelperClass.convertDatabaseToUI(rs.getString("to_date")));
				from.setSubscriberName(rs.getString("subsCriberName"));
				from.setUserType(rs.getString("userType"));
				ReservationList.add(from);
			}
			
			
		}
		
		
		
		
		} 
	
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			if(rs!=null){
				try{
					rs.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(conn !=null){
				try{
					conn.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL :getReservationListDetails  Ending");

		return ReservationList;
	}

	@Override
	public List<LibraryStockEntryDetailsForm> getReservationAccNo(String subtype,String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: getReservationAccNo: Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LibraryStockEntryDetailsForm> list= new ArrayList<LibraryStockEntryDetailsForm>();
		try{
			
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.GET_ACCNO_RESERVATION);
			pstmt.setString(1, subtype);
			/*pstmt.setString(2, accyear);*/
			rs=pstmt.executeQuery();
			System.out.println("//////////////"+pstmt);
			while(rs.next()){
				LibraryStockEntryDetailsForm vo= new LibraryStockEntryDetailsForm();
				vo.setEnteryid(rs.getString("Entry_id"));
				vo.setAccessionNo(rs.getString("Accession_number"));
				list.add(vo);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			 if(rs!=null){
				 try{
				 rs.close();
			 }
				 catch(SQLException e){
					 e.printStackTrace();
				 }
			 }
			 
			 if(pstmt!=null){
				 try{
				 pstmt.close();
			 }
				 catch(SQLException e){
					 e.printStackTrace();
				 }
			 }
			 
			 
			 if(conn!=null){
				 try{
				 conn.close();
			 }
				 catch(SQLException e){
					 e.printStackTrace();
				 }
			 }
			 
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL :getReservationAccNo  Ending");

		return list;
		
	}

	@Override
	public List<LibraryStockEntryDetailsForm> getbooktitleList(String subtype, String accyear, String accNo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: getReservationAccNo: Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LibraryStockEntryDetailsForm> list= new ArrayList<LibraryStockEntryDetailsForm>();
		try{
			
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.GET_BOOK_RESERVATION);
			/*pstmt.setString(1, subtype);
			pstmt.setString(2, accyear);
			pstmt.setString(3, accNo);*/
			
			rs=pstmt.executeQuery();
			System.out.println("/////book/////////"+pstmt);
			while(rs.next()){
				LibraryStockEntryDetailsForm vo= new LibraryStockEntryDetailsForm();
				vo.setEnteryid(rs.getString("Entry_id"));
				vo.setBookTitle(rs.getString("Book_title"));
				list.add(vo);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			 if(rs!=null){
				 try{
				 rs.close();
			 }
				 catch(SQLException e){
					 e.printStackTrace();
				 }
			 }
			 
			 if(pstmt!=null){
				 try{
				 pstmt.close();
			 }
				 catch(SQLException e){
					 e.printStackTrace();
				 }
			 }
			 
			 
			 if(conn!=null){
				 try{
				 conn.close();
			 }
				 catch(SQLException e){
					 e.printStackTrace();
				 }
			 }
			 
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL :getReservationAccNo  Ending");

		return list;
	}
	
	
	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getOtherSubscriberDetailReport(
			LibrarySearchIssueDetailsVO obj) {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: gettransportfeeDetails : Starting");
			PreparedStatement pstmt= null;
		
			ResultSet rs=null;
			
			Connection conn = null;
		
			int slno = 0;
			ArrayList<LibrarySearchIssueDetailsVO> list = new ArrayList<LibrarySearchIssueDetailsVO>();
		
			try{
				conn = JDBCConnection.getSeparateConnection();
				
				pstmt = conn.prepareStatement("SELECT DISTINCT clsd.subscriberNumber, clsd.otherName,clsd.otherGender,clsd.otherContactNo,clsd.otherEmail,clsd.otherAddress,clsd.status FROM campus_library_subscriber_details clsd LEFT JOIN campus_library_location cll ON cll.library_loc_id = clsd.loc_ID WHERE clsd.accyear_ID LIKE ?  AND cll.loc_ID LIKE ? AND clsd.subscriberType ='others'");
			
				pstmt.setString(1,obj.getAccyrid());
				pstmt.setString(2,obj.getLocationid());
				/*pstmt.setString(3,obj.getOthersNameText());*/
				
				rs = pstmt.executeQuery();
				
				System.out.println("subscriberdetail other query>>>"+pstmt);
				
				while(rs.next()){
					LibrarySearchIssueDetailsVO vo = new LibrarySearchIssueDetailsVO();
					slno++;
					vo.setSlno(slno);
					
					vo.setSubscriberno(rs.getString("subscriberNumber"));
					vo.setOthersName(rs.getString("otherName"));
					vo.setOthersGender(rs.getString("otherGender"));
					vo.setOthersContact(rs.getString("otherContactNo"));
					vo.setOthersMailId(rs.getString("otherEmail"));
					vo.setOthersAdress(rs.getString("otherAddress"));
					vo.setStatus(rs.getString("status"));
					
					list.add(vo);
					}
			
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
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
			return list;
	}

	@Override
	public List<LibraryStockEntryVO> getNewArrivalListReport(String checkedVal,String fromDate,String toDate) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: getNewArrivalListReport: Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int slno=0;
		ArrayList<LibraryStockEntryVO> arrivalList = new ArrayList<LibraryStockEntryVO>();
		try{
			conn=JDBCConnection.getSeparateConnection();
			if(checkedVal.equalsIgnoreCase("accNo"))
			{
				pstmt=conn.prepareStatement(LibrarySqlUtils.get_stock_list_by_accNo);
				pstmt.setString(1,HelperClass.convertUIToDatabase(fromDate ));
				pstmt.setString(2,HelperClass.convertUIToDatabase(toDate ));
				System.out.println("1pstmt"+pstmt);
				rs=pstmt.executeQuery();
			}
			else if(checkedVal.equalsIgnoreCase("category"))
			{
				
			pstmt=conn.prepareStatement(LibrarySqlUtils.get_stock_list_by_category);
			pstmt.setString(1,HelperClass.convertUIToDatabase(fromDate ));
			pstmt.setString(2,HelperClass.convertUIToDatabase(toDate ));
			System.out.println("2pstmt"+pstmt);
			rs=pstmt.executeQuery();
			}
			
		
			else if(checkedVal.equalsIgnoreCase("author"))
			{
			pstmt=conn.prepareStatement(LibrarySqlUtils.get_stock_list_by_author);
			pstmt.setString(1,HelperClass.convertUIToDatabase(fromDate ));
			pstmt.setString(2,HelperClass.convertUIToDatabase(toDate ));
			System.out.println("3pstmt"+pstmt);
			rs=pstmt.executeQuery();
			}
			else if(checkedVal.equalsIgnoreCase("title"))
			{
			pstmt=conn.prepareStatement(LibrarySqlUtils.get_stock_list_by_title);
			pstmt.setString(1,HelperClass.convertUIToDatabase(fromDate ));
			pstmt.setString(2,HelperClass.convertUIToDatabase(toDate ));
			System.out.println("4pstmt"+pstmt);
			rs=pstmt.executeQuery();
			}
			
			while(rs.next()){
				LibraryStockEntryVO from=new LibraryStockEntryVO();
				slno++;
				from.setSlno(slno);
				from.setCategory(rs.getString("category_name"));
				from.setEntry_id(rs.getString("Entry_id"));
				from.setAccessionNo(rs.getString("Accession_number"));
				from.setItemType(rs.getString("Item_type"));
				from.setRegDate(HelperClass.convertDatabaseToUI(rs.getString("Reg_date")));
				from.setBookTitle(rs.getString("Book_title"));
				from.setAuthor(rs.getString("Author"));
				from.setdDC(rs.getString("DDC"));
				from.setNo_of_Copies(rs.getString("No_of_copies"));
				from.setLocation(rs.getString("library_location_name"));
				from.setCurrentStatus(rs.getString("Availability_status"));
				arrivalList.add(from);
			}


		} catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			if(rs!=null){
				try{
					rs.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(conn !=null){
				try{
					conn.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL :getNewArrivalListReport  Ending");

		return arrivalList;
	}


	


	
	
	
	

			
	public ArrayList<LibrarySearchSubscriberVO> getAllBookDDCDetails(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();

	if (pojo.getSelect().equalsIgnoreCase("all")) {
			if (pojo.getOrderBy().equalsIgnoreCase("ddc")) 
			{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ALL_BOOK_DETAILS_BY_DDC);
			}
		 }
	else if (pojo.getSelect().equalsIgnoreCase("issued")) 
			{
				if (pojo.getOrderBy().equalsIgnoreCase("ddc")) 
				{
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_BOOK_DETAILS_BY_DDC);
				}
			}
	else if (pojo.getSelect().equalsIgnoreCase("available")) 
	{
		   if (pojo.getOrderBy().equalsIgnoreCase("ddc")) 
		    {
		      pstmt = conn.prepareStatement(LibrarySqlUtils.GET_AVAILABLE_BOOK_DETAILS_BY_DDC);
		    }
	}
	else if (pojo.getSelect().equalsIgnoreCase("notavailable")) 
	{
		  if (pojo.getOrderBy().equalsIgnoreCase("ddc")) 
		 {
		   pstmt = conn.prepareStatement(LibrarySqlUtils.GET_NOT_AVAILABLE_BOOK_DETAILS_BY_DDC);
		 }
	}
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setEntryId(rs.getString("Entry_id"));
				vo.setAccessionNo(rs.getString("Accession_number"));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setBookAuthor(rs.getString("Author"));
				vo.setLocation(rs.getString("library_location_name"));
				vo.setDdc(rs.getString("DDC"));
				studentData.add(vo);
		  }
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchByDDCAnyWhere(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			if (pojo.getSearch().equalsIgnoreCase("anywhere")){
				
				if (pojo.getOrderBy().equalsIgnoreCase("all")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_DDC);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("issued")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_DDC_BY_ISSUE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("available")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_DDC_BY_AVAILABLE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_DDC_BY_UNAVAILABLE);
				 }
					pstmt.setString(1, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(2, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(3, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(4, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(5, "%" + pojo.getSearchTextVal() + "%");

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setBookAuthor(rs.getString("Author"));
						vo.setDdc(rs.getString("DDC"));
						studentData.add(vo);
					}
			
		 }
			else if (pojo.getSearch().equalsIgnoreCase("startwith")){
				
				if (pojo.getOrderBy().equalsIgnoreCase("all")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_DDC);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("issued")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_DDC_BY_ISSUE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("available")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_DDC_BY_AVAILABLE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_DDC_BY_UNAVAILABLE);
				 }
					pstmt.setString(1, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(2, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(3, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(4, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(5, "%" + pojo.getSearchTextVal() + "%");

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setBookAuthor(rs.getString("Author"));
						vo.setDdc(rs.getString("DDC"));
						studentData.add(vo);
					}
			
		 }
			else if (pojo.getSearch().equalsIgnoreCase("endswith")){
				
				if (pojo.getOrderBy().equalsIgnoreCase("all")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_DDC);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("issued")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_DDC_BY_ISSUE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("available")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_DDC_BY_AVAILABLE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_DDC_BY_UNAVAILABLE);
				 }
					pstmt.setString(1, "%" + pojo.getSearchTextVal());
					pstmt.setString(2, "%" + pojo.getSearchTextVal());
					pstmt.setString(3, "%" + pojo.getSearchTextVal());
					pstmt.setString(4, "%" + pojo.getSearchTextVal());
					pstmt.setString(5, "%" + pojo.getSearchTextVal());

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setBookAuthor(rs.getString("Author"));
						vo.setDdc(rs.getString("DDC"));
						studentData.add(vo);
					}
			
		 }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookContentDetails(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();

	if (pojo.getSelect().equalsIgnoreCase("all")) {
			
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ALL_BOOK_DETAILS_BY_CONTENT);
			
		 }
	else if (pojo.getSelect().equalsIgnoreCase("issued")) 
			{
			
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_BOOK_DETAILS_BY_CONTENT);
				
			}
	else if (pojo.getSelect().equalsIgnoreCase("available")) 
	{
		  
		      pstmt = conn.prepareStatement(LibrarySqlUtils.GET_AVAILABLE_BOOK_DETAILS_BY_CONTENT);
		    
	}
	else if (pojo.getSelect().equalsIgnoreCase("notavailable")) 
	{
		
		   pstmt = conn.prepareStatement(LibrarySqlUtils.GET_NOT_AVAILABLE_BOOK_DETAILS_BY_CONTENT);
		
	}
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setEntryId(rs.getString("Entry_id"));
				vo.setAccessionNo(rs.getString("Accession_number"));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setBookAuthor(rs.getString("Author"));
				vo.setLocation(rs.getString("library_location_name"));
				vo.setContent(rs.getString("Content_search"));
				studentData.add(vo);
		  }
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchByContentAnyWhere(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			if (pojo.getSearch().equalsIgnoreCase("anywhere")){
				
				if (pojo.getOrderBy().equalsIgnoreCase("all")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_CONTENT);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("issued")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_CONTENT_BY_ISSUE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("available")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_CONTENT_BY_AVAILABLE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_CONTENT_BY_UNAVAILABLE);
				 }
					pstmt.setString(1, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(2, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(3, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(4, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(5, "%" + pojo.getSearchTextVal() + "%");

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setBookAuthor(rs.getString("Author"));
						vo.setContent(rs.getString("Content_search"));
						studentData.add(vo);
					}
			
		 }
			else if (pojo.getSearch().equalsIgnoreCase("startwith")){
				
				if (pojo.getOrderBy().equalsIgnoreCase("all")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_CONTENT);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("issued")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_CONTENT_BY_ISSUE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("available")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_CONTENT_BY_AVAILABLE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_CONTENT_BY_UNAVAILABLE);
				 }
					pstmt.setString(1, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(2, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(3, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(4, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(5, "%" + pojo.getSearchTextVal() + "%");

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setBookAuthor(rs.getString("Author"));
						vo.setContent(rs.getString("Content_search"));
						studentData.add(vo);
					}
			
		 }
			else if (pojo.getSearch().equalsIgnoreCase("endswith")){
				
				if (pojo.getOrderBy().equalsIgnoreCase("all")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_CONTENT);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("issued")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_CONTENT_BY_ISSUE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("available")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_CONTENT_BY_AVAILABLE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_CONTENT_BY_UNAVAILABLE);
				 }
					pstmt.setString(1, "%" + pojo.getSearchTextVal());
					pstmt.setString(2, "%" + pojo.getSearchTextVal());
					pstmt.setString(3, "%" + pojo.getSearchTextVal());
					pstmt.setString(4, "%" + pojo.getSearchTextVal());
					pstmt.setString(5, "%" + pojo.getSearchTextVal());

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setBookAuthor(rs.getString("Author"));
						vo.setContent(rs.getString("Content_search"));
						studentData.add(vo);
					}
			
		 }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookLanguageDetails(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();

	if (pojo.getSelect().equalsIgnoreCase("all")) {
			
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ALL_BOOK_DETAILS_BY_LANGUAGE);
			
		 }
	else if (pojo.getSelect().equalsIgnoreCase("issued")) 
			{
			
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_BOOK_DETAILS_BY_LANGUAGE);
				
			}
	else if (pojo.getSelect().equalsIgnoreCase("available")) 
	{
		  
		      pstmt = conn.prepareStatement(LibrarySqlUtils.GET_AVAILABLE_BOOK_DETAILS_BY_LANGUAGE);
		    
	}
	else if (pojo.getSelect().equalsIgnoreCase("notavailable")) 
	{
		
		   pstmt = conn.prepareStatement(LibrarySqlUtils.GET_NOT_AVAILABLE_BOOK_DETAILS_BY_LANGUAGE);
		
	}
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setEntryId(rs.getString("Entry_id"));
				vo.setAccessionNo(rs.getString("Accession_number"));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setBookAuthor(rs.getString("Author"));
				vo.setLocation(rs.getString("library_location_name"));
				vo.setLanguage(rs.getString("Language"));
				studentData.add(vo);
		  }
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchByLanguageAnyWhere(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			if (pojo.getSearch().equalsIgnoreCase("anywhere")){
				
				if (pojo.getOrderBy().equalsIgnoreCase("all")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_LANGUAGE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("issued")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_LANGUAGE_BY_ISSUE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("available")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_LANGUAGE_BY_AVAILABLE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_LANGUAGE_BY_UNAVAILABLE);
				 }
					pstmt.setString(1, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(2, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(3, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(4, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(5, "%" + pojo.getSearchTextVal() + "%");

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setBookAuthor(rs.getString("Author"));
						vo.setLanguage(rs.getString("Language"));
						studentData.add(vo);
					}
			
		 }
			else if (pojo.getSearch().equalsIgnoreCase("startwith")){
				
				if (pojo.getOrderBy().equalsIgnoreCase("all")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_LANGUAGE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("issued")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_LANGUAGE_BY_ISSUE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("available")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_LANGUAGE_BY_AVAILABLE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_LANGUAGE_BY_UNAVAILABLE);
				 }
					pstmt.setString(1, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(2, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(3, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(4, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(5, "%" + pojo.getSearchTextVal() + "%");

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setBookAuthor(rs.getString("Author"));
						vo.setLanguage(rs.getString("Language"));
						studentData.add(vo);
					}
			
		 }
			else if (pojo.getSearch().equalsIgnoreCase("endswith")){
				
				if (pojo.getOrderBy().equalsIgnoreCase("all")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_LANGUAGE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("issued")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_LANGUAGE_BY_ISSUE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("available")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_LANGUAGE_BY_AVAILABLE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_LANGUAGE_BY_UNAVAILABLE);
				 }
					pstmt.setString(1, "%" + pojo.getSearchTextVal());
					pstmt.setString(2, "%" + pojo.getSearchTextVal());
					pstmt.setString(3, "%" + pojo.getSearchTextVal());
					pstmt.setString(4, "%" + pojo.getSearchTextVal());
					pstmt.setString(5, "%" + pojo.getSearchTextVal());

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setBookAuthor(rs.getString("Author"));
						vo.setLanguage(rs.getString("Language"));
						studentData.add(vo);
					}
			
		 }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookSupplierDetails(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();

	if (pojo.getSelect().equalsIgnoreCase("all")) {
			
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ALL_BOOK_DETAILS_BY_SUPPLIED_BY);
			
		 }
	else if (pojo.getSelect().equalsIgnoreCase("issued")) 
			{
			
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_BOOK_DETAILS_BY_SUPPLIED_BY);
				
			}
	else if (pojo.getSelect().equalsIgnoreCase("available")) 
	{
		  
		      pstmt = conn.prepareStatement(LibrarySqlUtils.GET_AVAILABLE_BOOK_DETAILS_BY_SUPPLIED_BY);
		    
	}
	else if (pojo.getSelect().equalsIgnoreCase("notavailable")) 
	{
		
		   pstmt = conn.prepareStatement(LibrarySqlUtils.GET_NOT_AVAILABLE_BOOK_DETAILS_BY_SUPPLIED_BY);
		
	}
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setEntryId(rs.getString("Entry_id"));
				vo.setAccessionNo(rs.getString("Accession_number"));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setBookAuthor(rs.getString("Author"));
				vo.setLocation(rs.getString("library_location_name"));
				vo.setSupplier(rs.getString("Supplier_Name"));
				studentData.add(vo);
		  }
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchBySupplierAnyWhere(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			if (pojo.getSearch().equalsIgnoreCase("anywhere")){
				
				if (pojo.getOrderBy().equalsIgnoreCase("all")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_SUPPLIED_BY);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("issued")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_SUPPLIED_BY_ISSUE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("available")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_SUPPLIED_BY_AVAILABLE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_SUPPLIED_BY_UNAVAILABLE);
				 }
					pstmt.setString(1, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(2, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(3, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(4, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(5, "%" + pojo.getSearchTextVal() + "%");

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setBookAuthor(rs.getString("Author"));
						vo.setSupplier(rs.getString("Supplier_Name"));
						studentData.add(vo);
					}
			
		 }
			else if (pojo.getSearch().equalsIgnoreCase("startwith")){
				
				if (pojo.getOrderBy().equalsIgnoreCase("all")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_SUPPLIED_BY);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("issued")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_SUPPLIED_BY_ISSUE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("available")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_SUPPLIED_BY_AVAILABLE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_SUPPLIED_BY_UNAVAILABLE);
				 }
					pstmt.setString(1, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(2, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(3, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(4, "%" + pojo.getSearchTextVal() + "%");
					pstmt.setString(5, "%" + pojo.getSearchTextVal() + "%");

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setBookAuthor(rs.getString("Author"));
						vo.setSupplier(rs.getString("Supplier_Name"));
						studentData.add(vo);
					}
			
		 }
			else if (pojo.getSearch().equalsIgnoreCase("endswith")){
				
				if (pojo.getOrderBy().equalsIgnoreCase("all")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_SUPPLIED_BY);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("issued")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_SUPPLIED_BY_ISSUE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("available")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_SUPPLIED_BY_AVAILABLE);
				 }
				else if (pojo.getOrderBy().equalsIgnoreCase("notavailable")) 
				 {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_BOOK_DETAILS_BY_SUPPLIED_BY_UNAVAILABLE);
				 }
					pstmt.setString(1, "%" + pojo.getSearchTextVal());
					pstmt.setString(2, "%" + pojo.getSearchTextVal());
					pstmt.setString(3, "%" + pojo.getSearchTextVal());
					pstmt.setString(4, "%" + pojo.getSearchTextVal());
					pstmt.setString(5, "%" + pojo.getSearchTextVal());

					System.out.println(pstmt);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
						vo.setEntryId(rs.getString("Entry_id"));
						vo.setAccessionNo(rs.getString("Accession_number"));
						vo.setBookTitle(rs.getString("Book_title"));
						vo.setLocation(rs.getString("library_location_name"));
						vo.setBookAuthor(rs.getString("Author"));
						vo.setSupplier(rs.getString("Supplier_Name"));
						studentData.add(vo);
					}
			
		 }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public LibraryStockEntryDetailsForm gotostockDetails(java.lang.String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: editStockEntryDetail Starting");
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		LibraryStockEntryDetailsForm lfrom=null;
		try {
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.STOCK_ENTRY_EDIT);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();

			while(rs.next()){
				lfrom= new LibraryStockEntryDetailsForm();
				lfrom.setAccessionNo(rs.getString("Accession_number"));
				lfrom.setItemType(rs.getString("Item_type"));
				lfrom.setRegdate(HelperClass.convertDatabaseToUI(rs.getString("Reg_date")));
				lfrom.setBookTitle(rs.getString("Book_title"));
				lfrom.setAuthor(rs.getString("Author"));
				lfrom.setCategory(rs.getString("Category"));
				lfrom.setClassDescription(rs.getString("Class_desc"));
				lfrom.setSectionDescription(rs.getString("Section_desc"));
				lfrom.setDivisionDescription(rs.getString("Division_desc"));
				lfrom.setLanguge(rs.getString("Language"));
				lfrom.setDdc(rs.getString("DDC"));
				lfrom.setPublisher(rs.getString("Publisher"));
				lfrom.setNo_of_Copies(rs.getString("No_of_copies"));
				lfrom.setCost(rs.getString("Cost_per_copy"));
				lfrom.setTotalCost(rs.getString("Total_Cost"));
				lfrom.setBillNo(rs.getString("Bill_No"));
				lfrom.setSize(rs.getString("Size"));
				lfrom.setSuppliedBy(rs.getString("Supplied_By"));
				lfrom.setGeneralInfo(rs.getString("General_Info"));
				lfrom.setPublicationYear(rs.getString("Publish_year"));
				lfrom.setEdition(rs.getString("Edition"));
				lfrom.setEditor(rs.getString("Editor"));
				lfrom.setiSBNNo(rs.getString("ISBN_No"));
				lfrom.setBillDate(HelperClass.convertDatabaseToUI(rs.getString("Bill_date")));
				lfrom.setNo_of_Pages(rs.getString("No_of_pages"));
				lfrom.setContentSearch(rs.getString("Content_search"));
				lfrom.setShelfNo(rs.getString("Shelf_No"));
				lfrom.setLocation(rs.getString("Library_location"));
				lfrom.setCurrentStatus(rs.getString("Availability_status"));
				lfrom.setBookPhoto(rs.getString("Stock_Entry_Image"));
				lfrom.setTest("test");
				
			}}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(rs!=null){
				try{
					rs.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL :editStockEntryDetail  Ending");
		return lfrom;
	}

	public LibrarySubscribVO IssueStatementByStockEntryId(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt1 = null;
		ResultSet rs1=null;
		Connection conn = null;
		LibrarySubscribVO vo = null;

		try {

			    conn = JDBCConnection.getSeparateConnection();
				pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_GOTO_ISSUESTATEMENT_BY_STOCK_ENTRY);
				pstmt1.setString(1,pojo.getEntryId());
				rs1=pstmt1.executeQuery();

				while(rs1.next()){
					vo = new LibrarySubscribVO();
					vo.setEntryId(rs1.getString("Entry_id"));
					vo.setAccessionNo(rs1.getString("Accession_number"));
					vo.setBookTitle(rs1.getString("Book_title"));
					vo.setAuthor(rs1.getString("Author"));
					vo.setDdc(rs1.getString("DDC")); 
					vo.setLocation(rs1.getString("library_location_name")); 
					vo.setStatus(rs1.getString("Availability_status")); 
				}
		  
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} 
		finally {
			try {
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			}
			catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return vo;
	}

	public ArrayList<LibrarySearchSubscriberVO>IssueStatementTableByStockEntryId(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		int count=0;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(LibrarySqlUtils.ISSUE_STATEMENT_TABLE_BY_ISSUE);
				
				pstmt.setString(1,pojo.getEntryId());
				System.out.println(pstmt);
				rs=pstmt.executeQuery();

				while(rs.next()){
					count++;
					LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
					vo.setSlno(count);
					vo.setSubssciberNo(rs.getString("subscriberNumber"));
					vo.setSubscriberType(rs.getString("subscriberType"));
					vo.setIssueDate(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
					studentData.add(vo);
				}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}

				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			}
			catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		return studentData;
	}

	public ArrayList<LibrarySearchSubscriberVO> ReturnStatementTableByStockEntryId(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		int count=0;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			    conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(LibrarySqlUtils.ISSUE_STATEMENT_TABLE_BY_RETURN);
				
				pstmt.setString(1,pojo.getEntryId());
				System.out.println(pstmt);
				rs=pstmt.executeQuery();

				while(rs.next()){
					count++;
					LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
					vo.setSlno(count);
					vo.setSubssciberNo(rs.getString("subscriberNumber"));
					vo.setSubscriberType(rs.getString("subscriberType"));
					vo.setIssueDate(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
					vo.setReturnDate(HelperClass.convertDatabaseToUI(rs.getString("book_returned_date")));
					studentData.add(vo);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}

				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			}
			catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibrarySearchSubscriberVO> getOverDueStatement(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		
		try {
			System.out.println(pojo.getSuscriberType());
			conn = JDBCConnection.getSeparateConnection();
			if (pojo.getSuscriberType().equalsIgnoreCase("Student")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.STUDENTS_OVERDUE_DETAILS);
			
			pstmt.setString(1, pojo.getAccId());
			pstmt.setString(2, pojo.getLocationName());
			
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setAccessionNo(rs.getString("Accession_number"));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setToDate(rs.getString("to_date"));
				vo.setReturnDate(HelperClass.convertDatabaseToUI(rs.getString("book_returned_date")));
				vo.setBookAuthor(rs.getString("Author"));
				vo.setIssueDate(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));

				studentData.add(vo);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public List<LibrarySubscribVO> getClassByLibraryLocation(java.lang.String locationid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		ArrayList<LibrarySubscribVO> studentData = new ArrayList<LibrarySubscribVO>();
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement(LibrarySqlUtils.STUDENTS_CLASSLIST);
			pstmt.setString(1, locationid);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySubscribVO vo = new LibrarySubscribVO();
				vo.setClassId(rs.getString("classdetail_id_int"));
				vo.setClassName(rs.getString("classdetails_name_var"));

				studentData.add(vo);
			}
		}
		 catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public List<LibrarySubscribVO> getLibraryClassSection(String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getSection  Starting");
		String classval=searchTerm.split(",")[0];
		String location=searchTerm.split(",")[1];
		
		if(classval.equalsIgnoreCase("all")){
			classval="%%";
		}

		if(location.equalsIgnoreCase("all")){
			location="%%";
		}

		List<LibrarySubscribVO> list = new ArrayList<LibrarySubscribVO>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pst = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_LIBRARY_SECTION);
			pst.setString(1, classval.trim());
			pst.setString(2, location);
			System.out.println("pst "+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				LibrarySubscribVO registrationVo = new LibrarySubscribVO();
				registrationVo.setSectioncode(rs
						.getString("classsection_id_int"));
				registrationVo.setSectionnaem(rs
						.getString("classsection_name_var"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
				+ " Control in StudentRegistrationDaoImpl : getSection  Ending");

		return list;
	}

	public ArrayList<LibrarySearchSubscriberVO> getStudentOverDueStatementByClass(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		
		try {
			System.out.println(pojo.getSuscriberType());
			conn = JDBCConnection.getSeparateConnection();
			
			if (pojo.getSuscriberType().equalsIgnoreCase("Student")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.STUDENTS_OVERDUE_DETAILS_BY_CLASS);
			
				pstmt.setString(1, pojo.getAccId());
				pstmt.setString(2, pojo.getLocationName());
		    pstmt.setString(3, pojo.getClassId());
			
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setAccessionNo(rs.getString("Accession_number"));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setToDate(rs.getString("to_date"));
				vo.setReturnDate(rs.getString("book_returned_date"));
				vo.setBookAuthor(rs.getString("Author"));
				vo.setIssueDate(rs.getString("book_issued_date"));

				studentData.add(vo);
			}
		} 
			else if (pojo.getSuscriberType().equalsIgnoreCase("staff")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.STAFF_OVERDUE_DETAILS_BY_DEPARTMENT);
			
				pstmt.setString(1, pojo.getAccId());
				pstmt.setString(2, pojo.getLocationName());
		    pstmt.setString(3, pojo.getDepartment());
			
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("teacher"));
				vo.setClassid(rs.getString("DEPT_NAME"));
				vo.setDivision(rs.getString("designationName"));
				vo.setAccessionNo(rs.getString("Accession_number"));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setToDate(rs.getString("to_date"));
				vo.setReturnDate(rs.getString("book_returned_date"));
				vo.setBookAuthor(rs.getString("Author"));
				vo.setIssueDate(rs.getString("book_issued_date"));

				studentData.add(vo);
			}
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibrarySearchSubscriberVO> getStudentOverDueStatementBySection(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		
		try {
			System.out.println(pojo.getSuscriberType());
			conn = JDBCConnection.getSeparateConnection();
			if (pojo.getSuscriberType().equalsIgnoreCase("Student")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.STUDENTS_OVERDUE_DETAILS_BY_SECTION);
			
				pstmt.setString(1, pojo.getAccId());
				pstmt.setString(2, pojo.getLocationName());
		    pstmt.setString(3, pojo.getClassId());
		    pstmt.setString(4, pojo.getSectionId());
			
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setAccessionNo(rs.getString("Accession_number"));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setToDate(rs.getString("to_date"));
				vo.setReturnDate(rs.getString("book_returned_date"));
				vo.setBookAuthor(rs.getString("Author"));
				vo.setIssueDate(rs.getString("book_issued_date"));

				studentData.add(vo);
			}
		}
			else if (pojo.getSuscriberType().equalsIgnoreCase("staff")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.STAFF_OVERDUE_DETAILS_BY_DESIGNATION);
				pstmt.setString(1, pojo.getAccId());
				pstmt.setString(2, pojo.getLocationName());
				pstmt.setString(3, pojo.getDepartment());
		        pstmt.setString(4, pojo.getDesignation());
			
		        System.out.println(pstmt);
			    rs = pstmt.executeQuery();

			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("teacher"));
				vo.setClassid(rs.getString("DEPT_NAME"));
				vo.setDivision(rs.getString("designationName"));
				vo.setAccessionNo(rs.getString("Accession_number"));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setToDate(rs.getString("to_date"));
				vo.setReturnDate(rs.getString("book_returned_date"));
				vo.setBookAuthor(rs.getString("Author"));
				vo.setIssueDate(rs.getString("book_issued_date"));

				studentData.add(vo);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibrarySearchSubscriberVO> SearchOverDueStudentDetailsByAnyWhere(
			LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		
		try {
			System.out.println(pojo.getSuscriberType());
			conn = JDBCConnection.getSeparateConnection();
		  if (pojo.getSelect().equalsIgnoreCase("Student")) {
			if (pojo.getSearch().equalsIgnoreCase("anywhere")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_STUDENTS_OVERDUE_DETAILS);
			
			pstmt.setString(1,"%"+ pojo.getSearchTextVal() +"%");
			pstmt.setString(2, "%"+ pojo.getSearchTextVal() +"%");
		    pstmt.setString(3,"%"+ pojo.getSearchTextVal() +"%");
		    pstmt.setString(4, "%"+ pojo.getSearchTextVal() +"%");
		    pstmt.setString(5,"%"+ pojo.getSearchTextVal() +"%");
			pstmt.setString(6, "%"+ pojo.getSearchTextVal() +"%");
	        pstmt.setString(7,"%"+ pojo.getSearchTextVal() +"%");
	        pstmt.setString(8, "%"+ pojo.getSearchTextVal() +"%");
	        pstmt.setString(9,"%"+ pojo.getSearchTextVal() +"%");
			pstmt.setString(10, "%"+ pojo.getSearchTextVal() +"%");
		    pstmt.setString(11,"%"+ pojo.getSearchTextVal() +"%");
		    pstmt.setString(12,"%"+ pojo.getSearchTextVal() +"%");
		    
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			}
			else if (pojo.getSearch().equalsIgnoreCase("startwith")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_STUDENTS_OVERDUE_DETAILS);
			
			pstmt.setString(1, pojo.getSearchTextVal() +"%");
			pstmt.setString(2,  pojo.getSearchTextVal() +"%");
		    pstmt.setString(3, pojo.getSearchTextVal() +"%");
		    pstmt.setString(4,  pojo.getSearchTextVal() +"%");
		    pstmt.setString(5, pojo.getSearchTextVal() +"%");
			pstmt.setString(6,  pojo.getSearchTextVal() +"%");
	        pstmt.setString(7, pojo.getSearchTextVal() +"%");
	        pstmt.setString(8,  pojo.getSearchTextVal() +"%");
	        pstmt.setString(9, pojo.getSearchTextVal() +"%");
			pstmt.setString(10, pojo.getSearchTextVal() +"%");
		    pstmt.setString(11, pojo.getSearchTextVal() +"%");
		    pstmt.setString(12, pojo.getSearchTextVal() +"%");
		    
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			}
			else if (pojo.getSearch().equalsIgnoreCase("endwith")) {
				pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_STUDENTS_OVERDUE_DETAILS);
			
			pstmt.setString(1,"%"+ pojo.getSearchTextVal());
			pstmt.setString(2, "%"+ pojo.getSearchTextVal());
		    pstmt.setString(3,"%"+ pojo.getSearchTextVal() );
		    pstmt.setString(4, "%"+ pojo.getSearchTextVal() );
		    pstmt.setString(5,"%"+ pojo.getSearchTextVal() );
			pstmt.setString(6,"%"+  pojo.getSearchTextVal());
	        pstmt.setString(7,"%"+ pojo.getSearchTextVal() );
	        pstmt.setString(8, "%"+ pojo.getSearchTextVal() );
	        pstmt.setString(9,"%"+ pojo.getSearchTextVal());
			pstmt.setString(10,"%"+ pojo.getSearchTextVal() );
		    pstmt.setString(11, "%"+pojo.getSearchTextVal());
		    pstmt.setString(12, "%"+pojo.getSearchTextVal());
		    
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			}
			
			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStudentName(rs.getString("student"));
				vo.setClassid(rs.getString("classdetails_name_var"));
				vo.setDivision(rs.getString("classsection_name_var"));
				vo.setAccessionNo(rs.getString("Accession_number"));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setToDate(rs.getString("to_date"));
				vo.setReturnDate(rs.getString("book_returned_date"));
				vo.setBookAuthor(rs.getString("Author"));
				vo.setIssueDate(rs.getString("book_issued_date"));

				studentData.add(vo);
			}
		}
		  else if (pojo.getSelect().equalsIgnoreCase("staff")) {
			  
				if (pojo.getSearch().equalsIgnoreCase("anywhere")) {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_STAFF_OVERDUE_DETAILS);
				
				pstmt.setString(1,pojo.getAccId());
				pstmt.setString(2, pojo.getLocationName());
				pstmt.setString(3,pojo.getDepartment());
				pstmt.setString(4, pojo.getDesignation());
					
				pstmt.setString(5,"%"+ pojo.getSearchTextVal() +"%");
				pstmt.setString(6, "%"+ pojo.getSearchTextVal() +"%");
			    pstmt.setString(7,"%"+ pojo.getSearchTextVal() +"%");
			    pstmt.setString(8, "%"+ pojo.getSearchTextVal() +"%");
			    pstmt.setString(9,"%"+ pojo.getSearchTextVal() +"%");
				pstmt.setString(10, "%"+ pojo.getSearchTextVal() +"%");
		        pstmt.setString(11,"%"+ pojo.getSearchTextVal() +"%");
		        pstmt.setString(12, "%"+ pojo.getSearchTextVal() +"%");
		        pstmt.setString(13,"%"+ pojo.getSearchTextVal() +"%");
				pstmt.setString(14, "%"+ pojo.getSearchTextVal() +"%");
			    pstmt.setString(15,"%"+ pojo.getSearchTextVal() +"%");
			    
				System.out.println(pstmt);
				rs = pstmt.executeQuery();
				}
				else if (pojo.getSearch().equalsIgnoreCase("startwith")) {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_STAFF_OVERDUE_DETAILS);
				
					pstmt.setString(1,pojo.getAccId());
					pstmt.setString(2, pojo.getLocationName());
					pstmt.setString(3,pojo.getDepartment());
					pstmt.setString(4, pojo.getDesignation());
						
					pstmt.setString(5, pojo.getSearchTextVal() +"%");
					pstmt.setString(6,  pojo.getSearchTextVal() +"%");
				    pstmt.setString(7, pojo.getSearchTextVal() +"%");
				    pstmt.setString(8,  pojo.getSearchTextVal() +"%");
				    pstmt.setString(9, pojo.getSearchTextVal() +"%");
					pstmt.setString(10, pojo.getSearchTextVal() +"%");
			        pstmt.setString(11, pojo.getSearchTextVal() +"%");
			        pstmt.setString(12, pojo.getSearchTextVal() +"%");
			        pstmt.setString(13, pojo.getSearchTextVal() +"%");
					pstmt.setString(14, pojo.getSearchTextVal() +"%");
				    pstmt.setString(15, pojo.getSearchTextVal() +"%");
			    
				System.out.println(pstmt);
				rs = pstmt.executeQuery();
				}
				else if (pojo.getSearch().equalsIgnoreCase("endwith")) {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_STAFF_OVERDUE_DETAILS);
				
					pstmt.setString(1,pojo.getAccId());
					pstmt.setString(2, pojo.getLocationName());
					pstmt.setString(3,pojo.getDepartment());
					pstmt.setString(4, pojo.getDesignation());
						
					pstmt.setString(5,"%"+ pojo.getSearchTextVal() );
					pstmt.setString(6, "%"+ pojo.getSearchTextVal() );
				    pstmt.setString(7,"%"+ pojo.getSearchTextVal());
				    pstmt.setString(8, "%"+ pojo.getSearchTextVal());
				    pstmt.setString(9,"%"+ pojo.getSearchTextVal() );
					pstmt.setString(10, "%"+ pojo.getSearchTextVal());
			        pstmt.setString(11,"%"+ pojo.getSearchTextVal());
			        pstmt.setString(12, "%"+ pojo.getSearchTextVal());
			        pstmt.setString(13,"%"+ pojo.getSearchTextVal());
					pstmt.setString(14, "%"+ pojo.getSearchTextVal());
				    pstmt.setString(15,"%"+ pojo.getSearchTextVal());
			    
				System.out.println(pstmt);
				rs = pstmt.executeQuery();
				}
				
				while (rs.next()) {
					LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
					vo.setSubscriberId(rs.getString("subscriberId"));
					vo.setSubssciberNo(rs.getString("subscriberNumber"));
					vo.setStudentName(rs.getString("teacher"));
					vo.setClassid(rs.getString("DEPT_NAME"));
					vo.setDivision(rs.getString("designationName"));
					vo.setAccessionNo(rs.getString("Accession_number"));
					vo.setBookTitle(rs.getString("Book_title"));
					vo.setToDate(rs.getString("to_date"));
					vo.setReturnDate(rs.getString("book_returned_date"));
					vo.setBookAuthor(rs.getString("Author"));
					vo.setIssueDate(rs.getString("book_issued_date"));

					studentData.add(vo);
				}
			}
		  else if (pojo.getSelect().equalsIgnoreCase("Others")) {
			  
				if (pojo.getSearch().equalsIgnoreCase("anywhere")) {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_OTHERS_OVERDUE_DETAILS);
				
				pstmt.setString(1,pojo.getAccId());
				pstmt.setString(2, pojo.getLocationName());
					
				pstmt.setString(3,"%"+ pojo.getSearchTextVal() +"%");
				pstmt.setString(4, "%"+ pojo.getSearchTextVal() +"%");
			    pstmt.setString(5,"%"+ pojo.getSearchTextVal() +"%");
			    pstmt.setString(6, "%"+ pojo.getSearchTextVal() +"%");
			    pstmt.setString(7,"%"+ pojo.getSearchTextVal() +"%");
				pstmt.setString(8, "%"+ pojo.getSearchTextVal() +"%");
		        pstmt.setString(9,"%"+ pojo.getSearchTextVal() +"%");
		        pstmt.setString(10, "%"+ pojo.getSearchTextVal() +"%");
		        pstmt.setString(11,"%"+ pojo.getSearchTextVal() +"%");
				pstmt.setString(12, "%"+ pojo.getSearchTextVal() +"%");
			    
				System.out.println(pstmt);
				rs = pstmt.executeQuery();
				}
				else if (pojo.getSearch().equalsIgnoreCase("startwith")) {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_OTHERS_OVERDUE_DETAILS);
				
					pstmt.setString(1,pojo.getAccId());
					pstmt.setString(2, pojo.getLocationName());
						
					pstmt.setString(3, pojo.getSearchTextVal() +"%");
					pstmt.setString(4,  pojo.getSearchTextVal() +"%");
				    pstmt.setString(5, pojo.getSearchTextVal() +"%");
				    pstmt.setString(6,  pojo.getSearchTextVal() +"%");
				    pstmt.setString(7, pojo.getSearchTextVal() +"%");
					pstmt.setString(8, pojo.getSearchTextVal() +"%");
			        pstmt.setString(9, pojo.getSearchTextVal() +"%");
			        pstmt.setString(10, pojo.getSearchTextVal() +"%");
			        pstmt.setString(11, pojo.getSearchTextVal() +"%");
					pstmt.setString(12, pojo.getSearchTextVal() +"%");
			    
				System.out.println(pstmt);
				rs = pstmt.executeQuery();
				}
				else if (pojo.getSearch().equalsIgnoreCase("endwith")) {
					pstmt = conn.prepareStatement(LibrarySqlUtils.SEARCH_OTHERS_OVERDUE_DETAILS);
				
					pstmt.setString(1,pojo.getAccId());
					pstmt.setString(2,pojo.getLocationName());
						
					pstmt.setString(3,"%"+ pojo.getSearchTextVal() );
					pstmt.setString(4, "%"+ pojo.getSearchTextVal() );
				    pstmt.setString(5,"%"+ pojo.getSearchTextVal());
				    pstmt.setString(6, "%"+ pojo.getSearchTextVal());
				    pstmt.setString(7,"%"+ pojo.getSearchTextVal() );
					pstmt.setString(8, "%"+ pojo.getSearchTextVal());
			        pstmt.setString(9,"%"+ pojo.getSearchTextVal());
			        pstmt.setString(10, "%"+ pojo.getSearchTextVal());
			        pstmt.setString(11,"%"+ pojo.getSearchTextVal());
					pstmt.setString(12, "%"+ pojo.getSearchTextVal());
			    
				System.out.println(pstmt);
				rs = pstmt.executeQuery();
				}
				
				while (rs.next()) {
					LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
					vo.setSubscriberId(rs.getString("subscriberId"));
					vo.setSubssciberNo(rs.getString("subscriberNumber"));
					vo.setStudentName(rs.getString("teacher"));
					vo.setClassid(rs.getString("DEPT_NAME"));
					vo.setDivision(rs.getString("designationName"));
					vo.setAccessionNo(rs.getString("Accession_number"));
					vo.setBookTitle(rs.getString("Book_title"));
					vo.setToDate(rs.getString("to_date"));
					vo.setReturnDate(rs.getString("book_returned_date"));
					vo.setBookAuthor(rs.getString("Author"));
					vo.setIssueDate(rs.getString("book_issued_date"));

					studentData.add(vo);
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibraryStockEntryDetailsForm> getStudentOverDueByOrderwise(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null,pstmt1=null;
		ResultSet rs = null,rs1=null;
		Connection conn = null;
		ArrayList<LibraryStockEntryDetailsForm> ReservationList = new ArrayList<LibraryStockEntryDetailsForm>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			 if(pojo.getSelect().equalsIgnoreCase("all")){
				 
				if(pojo.getOrderBy().equalsIgnoreCase("subscriberno")){
					pstmt = conn.prepareStatement(LibrarySqlUtils.ALL_OVERDUE_DETAILS_ORDER_BY_SUBNO);
				}
				else if(pojo.getOrderBy().equalsIgnoreCase("accno")){
					pstmt = conn.prepareStatement(LibrarySqlUtils.ALL_OVERDUE_DETAILS_ORDER_BY_ACCNO);
				}
				else if(pojo.getOrderBy().equalsIgnoreCase("title")||pojo.getOrderBy().equalsIgnoreCase("name")){
					pstmt = conn.prepareStatement(LibrarySqlUtils.ALL_OVERDUE_DETAILS_ORDER_BY_TITLE);
				}
				else if(pojo.getOrderBy().equalsIgnoreCase("author")){
					pstmt = conn.prepareStatement(LibrarySqlUtils.ALL_OVERDUE_DETAILS_ORDER_BY_AUTHOR);
				}
				pstmt.setString(1,pojo.getAccId());
				pstmt.setString(2,pojo.getLocationName());
				
				System.out.println(pstmt);
				rs=pstmt.executeQuery();
				while(rs.next()){
					LibraryStockEntryDetailsForm from=new LibraryStockEntryDetailsForm();

					from.setSubscriber_id(rs.getString("subscriberId"));
					from.setAccessionNo(rs.getString("Accession_number"));
					from.setSubscriberNo(rs.getString("subscriberNumber"));
					from.setAuthor(rs.getString("author"));
					from.setBookTitle(rs.getString("book_title"));
					from.setStudentId(rs.getString("studentId"));
					from.setStaffId(rs.getString("staffId"));
					from.setFrom_date(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
					from.setTo_date(HelperClass.convertDatabaseToUI(rs.getString("to_date")));
					from.setReturnDate(HelperClass.convertDatabaseToUI(rs.getString("book_returned_date")));
					
					String usertype=rs.getString("subscriberType");
					
					if(usertype.equalsIgnoreCase("teacher"))
					{
					    if(pojo.getOrderBy().equalsIgnoreCase("name")){
							pstmt = conn.prepareStatement(LibrarySqlUtils.ALL_OVERDUE_DETAILS_ORDER_BY_STAFF_NAME);
						}else{
						pstmt=conn.prepareStatement(LibrarySqlUtils.OVER_DUE_TEACHER);
						}
					    pstmt.setString(1,rs.getString("subscriberId"));
						pstmt.setString(2,rs.getString("staffId"));
						rs1=pstmt.executeQuery();
						System.out.println("teacher query>>"+pstmt);
						while(rs1.next())
						{
							from.setSubscriberName(rs1.getString("teacher"));
							from.setDepartment(rs1.getString("DEPT_NAME"));
							from.setDesignation(rs1.getString("designationName"));
						}
					}
					else if(usertype.equalsIgnoreCase("student"))
					{
						if(pojo.getOrderBy().equalsIgnoreCase("name")){
							pstmt = conn.prepareStatement(LibrarySqlUtils.ALL_OVERDUE_DETAILS_ORDER_BY_STUDENT_NAME);
						}else{
						pstmt=conn.prepareStatement(LibrarySqlUtils.OVER_DUE_STUDENT);
						}
						pstmt.setString(1,rs.getString("subscriberId"));
						pstmt.setString(2,rs.getString("studentId"));
						rs1=pstmt.executeQuery();
						System.out.println("student query>>"+pstmt);
						while(rs1.next())
						{
							from.setSubscriberName(rs1.getString("student"));
							from.setDepartment(rs1.getString("classdetails_name_var"));
							from.setDesignation(rs1.getString("classsection_name_var"));
						}
					}
					else if(usertype.equalsIgnoreCase("others"))				
					{
						if(pojo.getOrderBy().equalsIgnoreCase("name")){
							pstmt= conn.prepareStatement(LibrarySqlUtils.ALL_OVERDUE_DETAILS_ORDER_BY_OTHERS_NAME);
						}else{
						pstmt=conn.prepareStatement(LibrarySqlUtils.OVER_DUE_OTHERS);
						}
						pstmt.setString(1,rs.getString("subscriberId"));
						rs1=pstmt.executeQuery();
						System.out.println("other query>>"+pstmt);
						while(rs1.next())
						{
							from.setSubscriberName(rs1.getString("otherName"));
							from.setDepartment(rs1.getString("otherContactNo"));
							from.setDesignation(rs1.getString("otherEmail"));
						
						}
					
										
					}
					ReservationList.add(from);
				}
	       }
			else if(pojo.getSelect().equalsIgnoreCase("Student")){
				
			if(pojo.getOrderBy().equalsIgnoreCase("subscriberno")){
				pstmt = conn.prepareStatement(LibrarySqlUtils.STUDENTS_OVERDUE_DETAILS_ORDER_BY_SUBNO);
			}
			else if(pojo.getOrderBy().equalsIgnoreCase("name")){
				pstmt = conn.prepareStatement(LibrarySqlUtils.STUDENTS_OVERDUE_DETAILS_ORDER_BY_NAME);
			}
			else if(pojo.getOrderBy().equalsIgnoreCase("accno")){
				pstmt = conn.prepareStatement(LibrarySqlUtils.STUDENTS_OVERDUE_DETAILS_ORDER_BY_ACCNO);
			}
			else if(pojo.getOrderBy().equalsIgnoreCase("title")){
				pstmt = conn.prepareStatement(LibrarySqlUtils.STUDENTS_OVERDUE_DETAILS_ORDER_BY_TITLE);
			}
			else if(pojo.getOrderBy().equalsIgnoreCase("author")){
				pstmt = conn.prepareStatement(LibrarySqlUtils.STUDENTS_OVERDUE_DETAILS_ORDER_BY_AUTHOR);
			}
			
			pstmt.setString(1, pojo.getAccId());
			pstmt.setString(2, pojo.getLocationName());
		    pstmt.setString(3, pojo.getClassId());
		    pstmt.setString(4, pojo.getSectionId());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibraryStockEntryDetailsForm from=new LibraryStockEntryDetailsForm();
				from.setSubscriber_id(rs.getString("subscriberId"));
				from.setSubscriberNo(rs.getString("subscriberNumber"));
				from.setSubscriberName(rs.getString("student"));
				from.setDepartment(rs.getString("classdetails_name_var"));
				from.setDesignation(rs.getString("classsection_name_var"));
				from.setAccessionNo(rs.getString("Accession_number"));
				from.setBookTitle(rs.getString("Book_title"));
				from.setTo_date(rs.getString("to_date"));
				from.setReturnDate(rs.getString("book_returned_date"));
				from.setAuthor(rs.getString("Author"));
				from.setFrom_date(rs.getString("book_issued_date"));

				ReservationList.add(from);
		}
			
	 } 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
		return ReservationList;
	}

	public List<LibrarySearchIssueDetailsVO> getAllOverDueListDetails(LibrarySubsciberDetailsPojo pojo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL: getReservationListDetails: Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		int count = 0;
		List<LibrarySearchIssueDetailsVO> ReservationList = new ArrayList<LibrarySearchIssueDetailsVO>();
		try{
			conn=JDBCConnection.getSeparateConnection();
			if(pojo.getSelect().equalsIgnoreCase("all"))
			{	
				if(pojo.getOrderBy().equalsIgnoreCase("subscriberno")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT);
				}else if(pojo.getOrderBy().equalsIgnoreCase("name")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_NAME);
				}else if(pojo.getOrderBy().equalsIgnoreCase("accno")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_ACCNO);
				}else if(pojo.getOrderBy().equalsIgnoreCase("title")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_TITLE);
				}else if(pojo.getOrderBy().equalsIgnoreCase("author")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_AUTHOR);
				}
			
			pstmt.setString(1,pojo.getLocationName());
			System.out.println(pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				count++;
				LibrarySearchIssueDetailsVO from=new LibrarySearchIssueDetailsVO();
				from.setSlno(count);
				from.setSubid(rs.getString("subscriberId"));
				from.setAccessionNo(rs.getString("Accession_number"));
				from.setSubscriberno(rs.getString("subscriberNumber"));
				from.setBookauthor(rs.getString("author"));
				from.setBookname(rs.getString("book_title"));
				from.setStuid(rs.getString("studentId"));
				from.setStaffid(rs.getString("staffId"));
				from.setFromdate(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
				from.setTodate(HelperClass.convertDatabaseToUI(rs.getString("to_date")));
				from.setUsertype(rs.getString("subscriberType"));
				if(rs.getString("book_returned_date").equalsIgnoreCase("-")){
					from.setReturneddate("-");
					from.setStatus("Not Returned");
				}else{
					from.setReturneddate(HelperClass.convertDatabaseToUI(rs.getString("book_returned_date")));
					from.setStatus(rs.getString("status"));
				}
				
				String usertype=rs.getString("subscriberType");
				
				if(usertype.equalsIgnoreCase("teacher"))
				{
					pstmt1=conn.prepareStatement(LibrarySqlUtils.OVER_DUE_TEACHER);
					pstmt1.setString(1,rs.getString("subscriberId"));
					pstmt1.setString(2,rs.getString("staffId"));
					rs1=pstmt1.executeQuery();
					System.out.println("teacher query>>"+pstmt1);
					while(rs1.next())
					{
						from.setSubname(rs1.getString("teacher"));
						from.setDepartment(rs1.getString("DEPT_NAME"));
						from.setDesigantion(rs1.getString("designationName"));
					}
				}
				else if(usertype.equalsIgnoreCase("student"))
				{
					pstmt1=conn.prepareStatement(LibrarySqlUtils.OVER_DUE_STUDENT);
					pstmt1.setString(1,rs.getString("subscriberId"));
					pstmt1.setString(2,rs.getString("studentId"));
					rs1=pstmt1.executeQuery();
					System.out.println("student query>>"+pstmt1);
					while(rs1.next())
					{
						from.setSubname(rs1.getString("student"));
						from.setDepartment(rs1.getString("classdetails_name_var"));
						from.setDesigantion(rs1.getString("classsection_name_var"));
					}
				}
				else if(usertype.equalsIgnoreCase("others"))				
				{
					pstmt1=conn.prepareStatement(LibrarySqlUtils.OVER_DUE_OTHERS);
					pstmt1.setString(1,rs.getString("subscriberId"));
					rs1=pstmt1.executeQuery();
					System.out.println("other query>>"+pstmt1);
					while(rs1.next())
					{
						from.setSubname(rs1.getString("otherName"));
						from.setDepartment(rs1.getString("otherContactNo"));
						from.setDesigantion(rs1.getString("otherEmail"));
					}
				}
				ReservationList.add(from);
			}
			if(pojo.getOrderBy().equalsIgnoreCase("name")){
				Collections.sort(ReservationList, new HelperClass());
			}
			
		}
			else if(pojo.getSelect().equalsIgnoreCase("Student")){
				if(pojo.getOrderBy().equalsIgnoreCase("subscriberno")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_BY_STUDENT);
				}else if(pojo.getOrderBy().equalsIgnoreCase("name")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_BY_STUDENT_NAME);
				}else if(pojo.getOrderBy().equalsIgnoreCase("accno")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_BY_STUDENT_ACCNO);
				}else if(pojo.getOrderBy().equalsIgnoreCase("title")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_BY_STUDENT_TITLE);
				}else if(pojo.getOrderBy().equalsIgnoreCase("author")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_BY_STUDENT_AUTHOR);
				}
				
				pstmt.setString(1,pojo.getLocationName());
				pstmt.setString(2,pojo.getClassId());
				pstmt.setString(3,pojo.getSectionId());
				System.out.println(pstmt);
				rs=pstmt.executeQuery();
				while(rs.next()){
					count++;
					LibrarySearchIssueDetailsVO from=new LibrarySearchIssueDetailsVO();
					from.setSlno(count);
					from.setSubscriberno(rs.getString("subscriberId"));
					from.setAccessionNo(rs.getString("Accession_number"));
					from.setSubscriberno(rs.getString("subscriberNumber"));
					from.setBookauthor(rs.getString("author"));
					from.setBookname(rs.getString("book_title"));
					from.setStuid(rs.getString("studentId"));
					from.setFromdate(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
					from.setTodate(HelperClass.convertDatabaseToUI(rs.getString("to_date")));
					//from.setReturneddate(HelperClass.convertDatabaseToUI(rs.getString("book_returned_date")));
					from.setSubname(rs.getString("student"));
					from.setDepartment(rs.getString("classdetails_name_var"));
					from.setDesigantion(rs.getString("classsection_name_var"));
					from.setUsertype(rs.getString("subscriberType"));
					if(rs.getString("book_returned_date").equalsIgnoreCase("-")){
						from.setReturneddate("-");
						from.setStatus("Not Returned");
					}else{
						from.setReturneddate(HelperClass.convertDatabaseToUI(rs.getString("book_returned_date")));
						from.setStatus(rs.getString("status"));
					}
					ReservationList.add(from);
			}
				
		}
			else if(pojo.getSelect().equalsIgnoreCase("staff")){
				
				if(pojo.getOrderBy().equalsIgnoreCase("subscriberno")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_BY_STAFF);
				}else if(pojo.getOrderBy().equalsIgnoreCase("name")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_BY_STAFF_NAME);
				}else if(pojo.getOrderBy().equalsIgnoreCase("accno")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_BY_STAFF_ACCNO);
				}else if(pojo.getOrderBy().equalsIgnoreCase("title")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_BY_STAFF_TITLE);
				}else if(pojo.getOrderBy().equalsIgnoreCase("author")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_BY_STAFF_AUTHOR);
				}
				pstmt.setString(1,pojo.getLocationName());
				pstmt.setString(2,pojo.getDepartment());
				pstmt.setString(3,pojo.getDesignation());
				System.out.println(pstmt);
				rs=pstmt.executeQuery();
				while(rs.next()){
					count++;
					LibrarySearchIssueDetailsVO from=new LibrarySearchIssueDetailsVO();
					from.setSlno(count);
					from.setSubscriberno(rs.getString("subscriberId"));
					from.setAccessionNo(rs.getString("Accession_number"));
					from.setSubscriberno(rs.getString("subscriberNumber"));
					from.setBookauthor(rs.getString("author"));
					from.setBookname(rs.getString("book_title"));
					from.setStaffid(rs.getString("staffId"));
					from.setFromdate(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
					from.setTodate(HelperClass.convertDatabaseToUI(rs.getString("to_date")));
					//from.setReturneddate(HelperClass.convertDatabaseToUI(rs.getString("book_returned_date")));
					if(rs.getString("book_returned_date").equalsIgnoreCase("-")){
						from.setReturneddate("-");
						from.setStatus("Not Returned");
					}else{
						from.setReturneddate(HelperClass.convertDatabaseToUI(rs.getString("book_returned_date")));
						from.setStatus(rs.getString("status"));
					}
					
					from.setSubname(rs.getString("teacher"));
					from.setDepartment(rs.getString("DEPT_NAME"));
					from.setDesigantion(rs.getString("designationName"));
					from.setUsertype(rs.getString("subscriberType"));
					ReservationList.add(from);
			}
				
		}
			else if(pojo.getSelect().equalsIgnoreCase("Others")){
				
				if(pojo.getOrderBy().equalsIgnoreCase("subscriberno")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_BY_OTHERS);
				}else if(pojo.getOrderBy().equalsIgnoreCase("name")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_BY_OTHERS_NAME);
				}else if(pojo.getOrderBy().equalsIgnoreCase("accno")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_BY_OTHERS_ACCNO);
				}else if(pojo.getOrderBy().equalsIgnoreCase("title")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_BY_OTHERS_TITLE);
				}else if(pojo.getOrderBy().equalsIgnoreCase("author")){
					pstmt=conn.prepareStatement(LibrarySqlUtils.LIBRARY_OVERDUE_STATEMENT_BY_OTHERS_AUTHOR);
				}
				
				pstmt.setString(1,pojo.getLocationName());
				
				System.out.println(pstmt);
				rs=pstmt.executeQuery();
				while(rs.next()){
					count++;
					LibrarySearchIssueDetailsVO from=new LibrarySearchIssueDetailsVO();
					from.setSlno(count);
					from.setSubscriberno(rs.getString("subscriberId"));
					from.setAccessionNo(rs.getString("Accession_number"));
					from.setSubscriberno(rs.getString("subscriberNumber"));
					from.setBookauthor(rs.getString("author"));
					from.setBookname(rs.getString("book_title"));
					from.setFromdate(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
					from.setTodate(HelperClass.convertDatabaseToUI(rs.getString("to_date")));
					//from.setReturneddate(HelperClass.convertDatabaseToUI(rs.getString("book_returned_date")));
					if(rs.getString("book_returned_date").equalsIgnoreCase("-")){
						from.setReturneddate("-");
						from.setStatus("Not Returned");
					}else{
						from.setReturneddate(HelperClass.convertDatabaseToUI(rs.getString("book_returned_date")));
						from.setStatus(rs.getString("status"));
					}
					
					from.setSubname(rs.getString("otherName"));
					from.setDepartment(rs.getString("otherContactNo"));
					from.setDesigantion(rs.getString("otherEmail"));
					from.setUsertype(rs.getString("subscriberType"));
					ReservationList.add(from);
			}
				
		}
			
	}	

		 catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			if(rs1!=null){
				try{
					rs1.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try{
					rs.close();
				}
				
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			
			if(pstmt1!=null){
				try{
					pstmt1.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(conn !=null){
				try{
					conn.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL :getReservationListDetails  Ending");

		return ReservationList;
	}

	public ArrayList<LibrarySubsciberDetailsPojo> getAllIssueReturnDetails(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null,pstmt1= null,pstmt2= null,pstmt3=null,pstmt4=null,pstmt5=null,pstmt6=null;
		ResultSet rs = null,rs1=null,rs2= null,rs3= null,rs4=null,rs5=null,rs6=null;
		Connection conn = null;
		ArrayList<LibrarySubsciberDetailsPojo> ReservationList = new ArrayList<LibrarySubsciberDetailsPojo>();
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection();

	 if (pojo.getStatement().equalsIgnoreCase("issue")) 
			{
				if (pojo.getSelect().equalsIgnoreCase("all") &&!pojo.getSelect().equalsIgnoreCase("others") && !pojo.getSelect().equalsIgnoreCase("student") &&!pojo.getSelect().equalsIgnoreCase("staff")) 
				{
					if (pojo.getOrderBy().equalsIgnoreCase("subno")) 
					{
			         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_RETURN_DETAILS_IN_ISSUE_ORDERBY_SUBNO);
					}
					else if (pojo.getOrderBy().equalsIgnoreCase("accno")) 
					{
			         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_RETURN_DETAILS_IN_ISSUE_ORDERBY_ACCNO);
					}
					else if (pojo.getOrderBy().equalsIgnoreCase("title")) 
					{
			         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_RETURN_DETAILS_IN_ISSUE_ORDERBY_TITLE);
					}
					else if (pojo.getOrderBy().equalsIgnoreCase("author")) 
					{
			         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_RETURN_DETAILS_IN_ISSUE_ORDERBY_AUTHOR);
					}
					else{
				      pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_RETURN_DETAILS_IN_ISSUE);
					}
				pstmt.setString(1,pojo.getLocationName());
				
				System.out.println(pstmt);
				rs=pstmt.executeQuery();
				while(rs.next()){
					count++;
					LibrarySubsciberDetailsPojo from=new LibrarySubsciberDetailsPojo();
					from.setSlno(count);
					from.setSubscriber_id(rs.getString("subscriberId"));
					from.setAccessionNo(rs.getString("Accession_number"));
					from.setSubscriberNo(rs.getString("subscriberNumber"));
					from.setAuthor(rs.getString("author"));
					from.setBookTitle(rs.getString("Book_title"));
					from.setStudent(rs.getString("studentId"));
					from.setStaffID(rs.getString("staffId"));
					from.setFrom_date(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date"))+"/"+HelperClass.convertDatabaseToUI(rs.getString("to_date")));
					String usertype=rs.getString("subscriberType");
					
					if(usertype.equalsIgnoreCase("teacher") && !usertype.equalsIgnoreCase("student") && !usertype.equalsIgnoreCase("others"))
					{
						pstmt1=conn.prepareStatement(LibrarySqlUtils.OVER_DUE_TEACHER);
						pstmt1.setString(1,rs.getString("subscriberId"));
						pstmt1.setString(2,rs.getString("staffId"));
						rs1=pstmt1.executeQuery();
						System.out.println("teacher query>>"+pstmt1);
						while(rs1.next())
						{
							from.setSubscriberName(rs1.getString("teacher"));
							from.setDepartment(rs1.getString("DEPT_NAME"));
							from.setDesignation(rs1.getString("designationName"));
						}
					}
					else if(usertype.equalsIgnoreCase("student") && !usertype.equalsIgnoreCase("teacher") && !usertype.equalsIgnoreCase("others"))
					{
						pstmt2=conn.prepareStatement(LibrarySqlUtils.OVER_DUE_STUDENT);
						pstmt2.setString(1,rs.getString("subscriberId"));
						pstmt2.setString(2,rs.getString("studentId"));
						rs2=pstmt2.executeQuery();
						System.out.println("student query>>"+pstmt2);
						while(rs2.next())
						{
							from.setSubscriberName(rs2.getString("student"));
							from.setDepartment(rs2.getString("classdetails_name_var"));
							from.setDesignation(rs2.getString("classsection_name_var"));
						}
					}
					else if(usertype.equalsIgnoreCase("others") && !usertype.equalsIgnoreCase("student") && !usertype.equalsIgnoreCase("teacher"))				
					{
						pstmt3=conn.prepareStatement(LibrarySqlUtils.OVER_DUE_OTHERS);
						pstmt3.setString(1,rs.getString("subscriberId"));
						rs3=pstmt3.executeQuery();
						System.out.println("other query>>"+pstmt3);
						while(rs3.next())
						{
							from.setSubscriberName(rs3.getString("otherName"));
							from.setDepartment(rs3.getString("otherContactNo"));
							from.setDesignation(rs3.getString("otherEmail"));
						}
					}
					ReservationList.add(from);
				   }
				}
				else if (pojo.getSelect().equalsIgnoreCase("Student") && !pojo.getSelect().equalsIgnoreCase("all") && !pojo.getSelect().equalsIgnoreCase("others") && !pojo.getSelect().equalsIgnoreCase("staff")) 
				{
					if (pojo.getOrderBy().equalsIgnoreCase("subno")) 
					{
			         	pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_STUDENT_ISSUE_RETURN_DETAILS_IN_ISSUE_ORDERBY_SUBNO);
					}
					else if (pojo.getOrderBy().equalsIgnoreCase("accno")) 
					{
			         	pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_STUDENT_ISSUE_RETURN_DETAILS_IN_ISSUE_ORDERBY_ACCNO);
					}
					else if (pojo.getOrderBy().equalsIgnoreCase("title")) 
					{
			         	pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_STUDENT_ISSUE_RETURN_DETAILS_IN_ISSUE_ORDERBY_TITLE);
					}
					else if (pojo.getOrderBy().equalsIgnoreCase("author")) 
					{
			         	pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_STUDENT_ISSUE_RETURN_DETAILS_IN_ISSUE_ORDERBY_AUTHOR);
					}
					else{
				      pstmt1 = conn.prepareStatement(LibrarySqlUtils.GET_STUDENT_ISSUE_RETURN_DETAILS_IN_ISSUE);
					}
					pstmt1.setString(1,pojo.getLocationName());
					System.out.println(pstmt1);
					rs1=pstmt1.executeQuery();
					while(rs1.next()){
						count++;
						LibrarySubsciberDetailsPojo from=new LibrarySubsciberDetailsPojo();
						from.setSlno(count);
						from.setSubscriber_id(rs1.getString("subscriberId"));
						from.setAccessionNo(rs1.getString("Accession_number"));
						from.setSubscriberNo(rs1.getString("subscriberNumber"));
						from.setAuthor(rs1.getString("author"));
						from.setBookTitle(rs1.getString("Book_title"));
						from.setFrom_date(HelperClass.convertDatabaseToUI(rs1.getString("book_issued_date"))+"/"+HelperClass.convertDatabaseToUI(rs1.getString("to_date")));
						from.setSuscriberType(rs1.getString("subscriberType"));
						from.setSubscriberName(rs1.getString("student"));
						from.setDepartment(rs1.getString("classdetails_name_var"));
						from.setDesignation(rs1.getString("classsection_name_var"));
						
						ReservationList.add(from);
				    }
				}
				else if (pojo.getSelect().equalsIgnoreCase("staff")  && !pojo.getSelect().equalsIgnoreCase("all") && !pojo.getSelect().equalsIgnoreCase("others") && !pojo.getSelect().equalsIgnoreCase("Student")) 
				{
					if (pojo.getOrderBy().equalsIgnoreCase("subno")) 
					{
			         	pstmt3 = conn.prepareStatement(LibrarySqlUtils.GET_STAFF_ISSUE_RETURN_DETAILS_IN_ISSUE_ORDERBY_SUBNO);
					}
					else if (pojo.getOrderBy().equalsIgnoreCase("accno")) 
					{
			         	pstmt3 = conn.prepareStatement(LibrarySqlUtils.GET_STAFF_ISSUE_RETURN_DETAILS_IN_ISSUE_ORDERBY_ACCNO);
					}
					else if (pojo.getOrderBy().equalsIgnoreCase("title")) 
					{
			         	pstmt3 = conn.prepareStatement(LibrarySqlUtils.GET_STAFF_ISSUE_RETURN_DETAILS_IN_ISSUE_ORDERBY_TITLE);
					}
					else if (pojo.getOrderBy().equalsIgnoreCase("author")) 
					{
			         	pstmt3 = conn.prepareStatement(LibrarySqlUtils.GET_STAFF_ISSUE_RETURN_DETAILS_IN_ISSUE_ORDERBY_AUTHOR);
					}
					else{
				      pstmt3 = conn.prepareStatement(LibrarySqlUtils.GET_STAFF_ISSUE_RETURN_DETAILS_IN_ISSUE);
					}
					pstmt3.setString(1,pojo.getLocationName());
					
					System.out.println(pstmt3);
					rs3=pstmt3.executeQuery();
					
					while(rs3.next()){
						count++;
						LibrarySubsciberDetailsPojo from=new LibrarySubsciberDetailsPojo();
                        from.setSlno(count);
						from.setSubscriber_id(rs3.getString("subscriberId"));
						from.setAccessionNo(rs3.getString("Accession_number"));
						from.setSubscriberNo(rs3.getString("subscriberNumber"));
						from.setAuthor(rs3.getString("author"));
						from.setBookTitle(rs3.getString("Book_title"));
						from.setFrom_date(HelperClass.convertDatabaseToUI(rs3.getString("book_issued_date"))+"/"+HelperClass.convertDatabaseToUI(rs3.getString("to_date")));
						from.setSuscriberType(rs3.getString("subscriberType"));
						from.setSubscriberName(rs3.getString("teacher"));
						from.setDepartment(rs3.getString("DEPT_NAME"));
						from.setDesignation(rs3.getString("designationName"));
						ReservationList.add(from);
				   }
				}
				else if (pojo.getSelect().equalsIgnoreCase("others") && !pojo.getSelect().equalsIgnoreCase("Student") && !pojo.getSelect().equalsIgnoreCase("staff")  && !pojo.getSelect().equalsIgnoreCase("all")) 
				{
					if (pojo.getOrderBy().equalsIgnoreCase("subno")) 
					{
			         	pstmt5 = conn.prepareStatement(LibrarySqlUtils.GET_OTHERS_ISSUE_RETURN_DETAILS_IN_ISSUE_ORDERBY_SUBNO);
					}
					else if (pojo.getOrderBy().equalsIgnoreCase("accno")) 
					{
			         	pstmt5 = conn.prepareStatement(LibrarySqlUtils.GET_OTHERS_ISSUE_RETURN_DETAILS_IN_ISSUE_ORDERBY_ACCNO);
					}
					else if (pojo.getOrderBy().equalsIgnoreCase("title")) 
					{
			         	pstmt5= conn.prepareStatement(LibrarySqlUtils.GET_OTHERS_ISSUE_RETURN_DETAILS_IN_ISSUE_ORDERBY_TITLE);
					}
					else if (pojo.getOrderBy().equalsIgnoreCase("author")) 
					{
			         	pstmt5 = conn.prepareStatement(LibrarySqlUtils.GET_OTHERS_ISSUE_RETURN_DETAILS_IN_ISSUE_ORDERBY_AUTHOR);
					}
					else{
				      pstmt5 = conn.prepareStatement(LibrarySqlUtils.GET_OTHERS_ISSUE_RETURN_DETAILS_IN_ISSUE);
					}
					pstmt5.setString(1,pojo.getLocationName());
					
					System.out.println(pstmt5);
					rs5=pstmt5.executeQuery();
					while(rs5.next()){
						count++;
						LibrarySubsciberDetailsPojo from=new LibrarySubsciberDetailsPojo();
						from.setSlno(count);
						from.setSubscriber_id(rs5.getString("subscriberId"));
						from.setAccessionNo(rs5.getString("Accession_number"));
						from.setSubscriberNo(rs5.getString("subscriberNumber"));
						from.setAuthor(rs5.getString("author"));
						from.setBookTitle(rs5.getString("Book_title"));
						from.setFrom_date(HelperClass.convertDatabaseToUI(rs5.getString("book_issued_date"))+"/"+HelperClass.convertDatabaseToUI(rs5.getString("to_date")));
						from.setSuscriberType(rs5.getString("subscriberType"));
						from.setSubscriberName(rs5.getString("otherName"));
						from.setDepartment(rs5.getString("otherContactNo"));
						from.setDesignation(rs5.getString("otherEmail"));
						ReservationList.add(from);
				   }
				}
			}
	else if (pojo.getStatement().equalsIgnoreCase("return")) 
	{
		if (pojo.getSelect().equalsIgnoreCase("all") &&!pojo.getSelect().equalsIgnoreCase("others") && !pojo.getSelect().equalsIgnoreCase("student") &&!pojo.getSelect().equalsIgnoreCase("staff")) 
		{
			if (pojo.getOrderBy().equalsIgnoreCase("subno")) 
			{
	         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_RETURN_DETAILS_IN_RETURNS_ORDERBY_SUBNO);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("accno")) 
			{
	         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_RETURN_DETAILS_IN_RETURNS_ORDERBY_ACCNO);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("title")) 
			{
	         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_RETURN_DETAILS_IN_RETURNS_ORDERBY_TITLE);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("author")) 
			{
	         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_RETURN_DETAILS_IN_RETURNS_ORDERBY_AUTHOR);
			}
			else{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_RETURN_DETAILS_IN_RETURNS);
			}
			pstmt.setString(1,pojo.getLocationName());
			
			System.out.println(pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				LibrarySubsciberDetailsPojo from=new LibrarySubsciberDetailsPojo();
                count++;
                from.setSlno(count);
				from.setSubscriber_id(rs.getString("subscriberId"));
				from.setAccessionNo(rs.getString("Accession_number"));
				from.setSubscriberNo(rs.getString("subscriberNumber"));
				from.setAuthor(rs.getString("author"));
				from.setBookTitle(rs.getString("Book_title"));
				from.setStudentId(rs.getString("studentId"));
				from.setStaffId(rs.getString("staffId"));
				from.setFrom_date(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date"))+"/"+HelperClass.convertDatabaseToUI(rs.getString("book_returned_date")));
				String usertype=rs.getString("subscriberType");
				
				if(usertype.equalsIgnoreCase("teacher"))
				{
					pstmt1=conn.prepareStatement(LibrarySqlUtils.OVER_DUE_TEACHER);
					pstmt1.setString(1,rs.getString("subscriberId"));
					pstmt1.setString(2,rs.getString("staffId"));
					rs1=pstmt1.executeQuery();
					System.out.println("teacher query>>"+pstmt1);
					while(rs1.next())
					{
						from.setSubscriberName(rs1.getString("teacher"));
						from.setDepartment(rs1.getString("DEPT_NAME"));
						from.setDesignation(rs1.getString("designationName"));
					}
				}
				else if(usertype.equalsIgnoreCase("student"))
				{
					pstmt2=conn.prepareStatement(LibrarySqlUtils.OVER_DUE_STUDENT);
					pstmt2.setString(1,rs.getString("subscriberId"));
					pstmt2.setString(2,rs.getString("studentId"));
					rs2=pstmt2.executeQuery();
					System.out.println("student query>>"+pstmt2);
					while(rs2.next())
					{
						from.setSubscriberName(rs2.getString("student"));
						from.setDepartment(rs2.getString("classdetails_name_var"));
						from.setDesignation(rs2.getString("classsection_name_var"));
					}
				}
				else if(usertype.equalsIgnoreCase("others"))				
				{
					pstmt3=conn.prepareStatement(LibrarySqlUtils.OVER_DUE_OTHERS);
					pstmt3.setString(1,rs.getString("subscriberId"));
					rs3=pstmt3.executeQuery();
					System.out.println("other query>>"+pstmt3);
					while(rs3.next())
					{
						from.setSubscriberName(rs3.getString("otherName"));
						from.setDepartment(rs3.getString("otherContactNo"));
						from.setDesignation(rs3.getString("otherEmail"));
					}
				}
				ReservationList.add(from);
			}
		}
		else if (pojo.getSelect().equalsIgnoreCase("Student") && !pojo.getSelect().equalsIgnoreCase("all") && !pojo.getSelect().equalsIgnoreCase("others") && !pojo.getSelect().equalsIgnoreCase("staff")) 
		{
			if (pojo.getOrderBy().equalsIgnoreCase("subno")) 
			{
	         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_STUDENT_ISSUE_RETURN_DETAILS_IN_RETURNS_ORDERBY_SUBNO);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("accno")) 
			{
	         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_STUDENT_ISSUE_RETURN_DETAILS_IN_RETURNS_ORDERBY_ACCNO);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("title")) 
			{
	         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_STUDENT_ISSUE_RETURN_DETAILS_IN_RETURNS_ORDERBY_TITLE);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("author")) 
			{
	         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_STUDENT_ISSUE_RETURN_DETAILS_IN_RETURNS_ORDERBY_AUTHOR);
			}
			else{
			  pstmt = conn.prepareStatement(LibrarySqlUtils.GET_STUDENT_ISSUE_RETURN_DETAILS_IN_RETURNS);
			}
			pstmt.setString(1,pojo.getLocationName());
			System.out.println(pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				count++;
				LibrarySubsciberDetailsPojo from=new LibrarySubsciberDetailsPojo();
				from.setSlno(count);
				from.setSubscriber_id(rs.getString("subscriberId"));
				from.setAccessionNo(rs.getString("Accession_number"));
				from.setSubscriberNo(rs.getString("subscriberNumber"));
				from.setAuthor(rs.getString("author"));
				from.setBookTitle(rs.getString("Book_title"));
				from.setFrom_date(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date"))+"/"+HelperClass.convertDatabaseToUI(rs.getString("book_returned_date")));
				from.setSuscriberType(rs.getString("subscriberType"));
				from.setSubscriberName(rs.getString("student"));
				from.setDepartment(rs.getString("classdetails_name_var"));
				from.setDesignation(rs.getString("classsection_name_var"));
				
				ReservationList.add(from);
		   }
		}
		else if (pojo.getSelect().equalsIgnoreCase("staff")  && !pojo.getSelect().equalsIgnoreCase("all") && !pojo.getSelect().equalsIgnoreCase("others") && !pojo.getSelect().equalsIgnoreCase("Student")) 
		{
			if (pojo.getOrderBy().equalsIgnoreCase("subno")) 
			{
	         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_STAFF_ISSUE_RETURN_DETAILS_IN_RETURNS_ORDERBY_SUBNO);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("accno")) 
			{
	         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_STAFF_ISSUE_RETURN_DETAILS_IN_RETURNS_ORDERBY_ACCNO);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("title")) 
			{
	         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_STAFF_ISSUE_RETURN_DETAILS_IN_RETURNS_ORDERBY_TITLE);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("author")) 
			{
	         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_STAFF_ISSUE_RETURN_DETAILS_IN_RETURNS_ORDERBY_AUTHOR);
			}
			else{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_STAFF_ISSUE_RETURN_DETAILS_IN_RETURNS);
			}
			pstmt.setString(1,pojo.getLocationName());
			
			System.out.println(pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				count++;
				LibrarySubsciberDetailsPojo from=new LibrarySubsciberDetailsPojo();
                  from.setSlno(count);
				from.setSubscriber_id(rs.getString("subscriberId"));
				from.setAccessionNo(rs.getString("Accession_number"));
				from.setSubscriberNo(rs.getString("subscriberNumber"));
				from.setAuthor(rs.getString("author"));
				from.setBookTitle(rs.getString("Book_title"));
				from.setFrom_date(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date"))+"/"+HelperClass.convertDatabaseToUI(rs.getString("book_returned_date")));
				from.setSuscriberType(rs.getString("subscriberType"));
				from.setSubscriberName(rs.getString("teacher"));
				from.setDepartment(rs.getString("DEPT_NAME"));
				from.setDesignation(rs.getString("designationName"));
				ReservationList.add(from);
		  }
		}
		else if (pojo.getSelect().equalsIgnoreCase("others") && !pojo.getSelect().equalsIgnoreCase("Student") && !pojo.getSelect().equalsIgnoreCase("staff")  && !pojo.getSelect().equalsIgnoreCase("all")) 
		{
			if (pojo.getOrderBy().equalsIgnoreCase("subno")) 
			{
	         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_OTHER_ISSUE_RETURN_DETAILS_IN_RETURNS_ORDERBY_SUBNO);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("accno")) 
			{
	         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_OTHER_ISSUE_RETURN_DETAILS_IN_RETURNS_ORDERBY_ACCNO);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("title")) 
			{
	         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_OTHER_ISSUE_RETURN_DETAILS_IN_RETURNS_ORDERBY_TITLE);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("author")) 
			{
	         	pstmt = conn.prepareStatement(LibrarySqlUtils.GET_OTHER_ISSUE_RETURN_DETAILS_IN_RETURNS_ORDERBY_AUTHOR);
			}
			else{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_OTHER_ISSUE_RETURN_DETAILS_IN_RETURNS);
			}
			pstmt.setString(1,pojo.getLocationName());
			
			System.out.println(pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				count++;
				LibrarySubsciberDetailsPojo from=new LibrarySubsciberDetailsPojo();
                from.setSlno(count);
				from.setSubscriber_id(rs.getString("subscriberId"));
				from.setAccessionNo(rs.getString("Accession_number"));
				from.setSubscriberNo(rs.getString("subscriberNumber"));
				from.setAuthor(rs.getString("author"));
				from.setBookTitle(rs.getString("Book_title"));
				from.setFrom_date(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date"))+"/"+HelperClass.convertDatabaseToUI(rs.getString("book_returned_date")));
				from.setSuscriberType(rs.getString("subscriberType"));
				from.setSubscriberName(rs.getString("otherName"));
				from.setDepartment(rs.getString("otherContactNo"));
				from.setDesignation(rs.getString("otherEmail"));
				ReservationList.add(from);
		  }
		}
	}
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		finally {
			try {
				if (rs!= null && (!rs.isClosed())) {
					rs.close();
				}
				if (rs1!= null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs2!= null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (rs3!= null && (!rs3.isClosed())) {
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
				if (pstmt!= null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1!= null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2!= null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmt3!= null && (!pstmt3.isClosed())) {
					pstmt3.close();
				}
				if (pstmt4!= null && (!pstmt4.isClosed())) {
					pstmt4.close();
				}
				if (pstmt5!= null && (!pstmt5.isClosed())) {
					pstmt5.close();
				}
				if (pstmt6!= null && (!pstmt6.isClosed())) {
					pstmt6.close();
				}
				if (conn!= null && (!conn.isClosed())) {
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
		return ReservationList;
	}
	
	public ArrayList<LibrarySearchSubscriberVO> getAllBookDetailsDownloadandPrint(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();

	if (pojo.getSelect().equalsIgnoreCase("all")) {
			if (pojo.getOrderBy().equalsIgnoreCase("accessionNo")) 
			{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ALL_BOOK_DETAILS_BY_ACCNO);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("title")) 
			{
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ALL_BOOK_DETAILS_BY_TITLE);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("author")) 
			{
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ALL_BOOK_DETAILS_BY_AUTHOR);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("location")) 
			{
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ALL_BOOK_DETAILS_BY_LOCATION);
			}
			else if (pojo.getOrderBy().equalsIgnoreCase("category")) 
			{
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ALL_BOOK_DETAILS_BY_CATEGORY);
			}
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setEntryId(rs.getString("Entry_id"));
				vo.setAccessionNo(rs.getString("Accession_number"));
				vo.setBookTitle(rs.getString("Book_title"));
				vo.setBookAuthor(rs.getString("Author"));
				vo.setLocation(rs.getString("library_location_name"));
				vo.setCategory(rs.getString("Category"));
				studentData.add(vo);
		  }
			
		 }
	else if (pojo.getSelect().equalsIgnoreCase("issued")) 
			{
				if (pojo.getOrderBy().equalsIgnoreCase("accessionNo")) 
				{
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_BOOK_DETAILS_BY_ACCNO);
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("title")) 
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_BOOK_DETAILS_BY_TITLE);
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("author")) 
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_BOOK_DETAILS_BY_AUTHOR);
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("location")) 
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_BOOK_DETAILS_BY_LOCATION);
				}
				else if (pojo.getOrderBy().equalsIgnoreCase("category")) 
				{
					pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_BOOK_DETAILS_BY_CATEGORY);
				}
				System.out.println(pstmt);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
					vo.setEntryId(rs.getString("Entry_id"));
					vo.setAccessionNo(rs.getString("Accession_number"));
					vo.setBookTitle(rs.getString("Book_title"));
					vo.setBookAuthor(rs.getString("Author"));
					vo.setLocation(rs.getString("library_location_name"));
					vo.setCategory(rs.getString("Category"));
					studentData.add(vo);
			  }
				
			}
	else if (pojo.getSelect().equalsIgnoreCase("available")) 
	{
		if (pojo.getOrderBy().equalsIgnoreCase("accessionNo")) 
		{
		pstmt = conn.prepareStatement(LibrarySqlUtils.GET_AVAILABLE_BOOK_DETAILS_BY_ACCNO);
		}
		else if (pojo.getOrderBy().equalsIgnoreCase("title")) 
		{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_AVAILABLE_BOOK_DETAILS_BY_TITLE);
		}
		else if (pojo.getOrderBy().equalsIgnoreCase("author")) 
		{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_AVAILABLE_BOOK_DETAILS_BY_AUTHOR);
		}
		else if (pojo.getOrderBy().equalsIgnoreCase("location")) 
		{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_AVAILABLE_BOOK_DETAILS_BY_LOCATION);
		}
		else if (pojo.getOrderBy().equalsIgnoreCase("category")) 
		{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_AVAILABLE_BOOK_DETAILS_BY_CATEGORY);
		}
		System.out.println(pstmt);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
			vo.setEntryId(rs.getString("Entry_id"));
			vo.setAccessionNo(rs.getString("Accession_number"));
			vo.setBookTitle(rs.getString("Book_title"));
			vo.setBookAuthor(rs.getString("Author"));
			vo.setLocation(rs.getString("library_location_name"));
			vo.setCategory(rs.getString("Category"));
			studentData.add(vo);
	  }
	}
	else if (pojo.getSelect().equalsIgnoreCase("notavailable")) 
	{
		if (pojo.getOrderBy().equalsIgnoreCase("accessionNo")) 
		{
		pstmt = conn.prepareStatement(LibrarySqlUtils.GET_NOT_AVAILABLE_BOOK_DETAILS_BY_ACCNO);
		}
		else if (pojo.getOrderBy().equalsIgnoreCase("title")) 
		{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_NOT_AVAILABLE_BOOK_DETAILS_BY_TITLE);
		}
		else if (pojo.getOrderBy().equalsIgnoreCase("author")) 
		{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_NOT_AVAILABLE_DETAILS_BY_AUTHOR);
		}
		else if (pojo.getOrderBy().equalsIgnoreCase("location")) 
		{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_NOT_AVAILABLE_BOOK_DETAILS_BY_LOCATION);
		}
		else if (pojo.getOrderBy().equalsIgnoreCase("category")) 
		{
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_NOT_AVAILABLE_BOOK_DETAILS_BY_CATEGORY);
		}
		System.out.println(pstmt);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
			vo.setEntryId(rs.getString("Entry_id"));
			vo.setAccessionNo(rs.getString("Accession_number"));
			vo.setBookTitle(rs.getString("Book_title"));
			vo.setBookAuthor(rs.getString("Author"));
			vo.setLocation(rs.getString("library_location_name"));
			vo.setCategory(rs.getString("Category"));
			studentData.add(vo);
	  }
	}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibraryStockEntryVO> getJournalNameList(
			java.lang.String accyear) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibraryStockEntryVO> journalName = new ArrayList<LibraryStockEntryVO>();

		try{
			conn=JDBCConnection.getSeparateConnection();
			psmt=conn.prepareStatement("SELECT `Name`,Entry_id FROM `campus_library_journal_subscription` WHERE `accyear`=?" );
			psmt.setString(1, accyear);
			
			System.out.println("journal list query>>>"+psmt);
			rs=psmt.executeQuery();
			while (rs.next()) {
				LibraryStockEntryVO journal = new LibraryStockEntryVO();
				journal.setJournalId(rs.getString("Entry_id"));
				journal.setJournalName(rs.getString("Name"));
				journalName.add(journal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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

		return journalName;
	}

	public ArrayList<LibraryJournalSubscriptionVo> getJournalListReport(
			java.lang.String checkedVal, java.lang.String fromdate,
			java.lang.String toDate, String accyear, String journalName) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getJournalListReport Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int slno=0;
		ArrayList<LibraryJournalSubscriptionVo> journal=new ArrayList<LibraryJournalSubscriptionVo>();
		try{

			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(LibrarySqlUtils.get_journal_list_pdf_report);
			
			pstmt.setString(1, accyear);
			pstmt.setString(2, journalName);
			pstmt.setString(3,HelperClass.convertUIToDatabase(fromdate));
			pstmt.setString(4, HelperClass.convertUIToDatabase(toDate));
			pstmt.setString(5,HelperClass.convertUIToDatabase(fromdate));
			pstmt.setString(6, HelperClass.convertUIToDatabase(toDate));
			
			System.out.println("journal  pdf query 19576>>>"+pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				LibraryJournalSubscriptionVo vo =new LibraryJournalSubscriptionVo();
				slno++;
				vo.setSlno(slno);
				vo.setEntryId(rs.getString("Entry_id"));
				vo.setName(rs.getString("Name"));
				vo.setAccessionno(rs.getString("Accession_No"));
				vo.setJournaltype(rs.getString("Journal_Type"));
				vo.setRatepercopy(rs.getString("Rate_Per_Copy"));
				vo.setNumberofcopy(rs.getString("No_Of_Copy"));
				vo.setSubscriptionperiode(rs.getString("Subscription_periode"));
				vo.setTodate( HelperClass.convertDatabaseToUI(rs.getString("To_date")));
				vo.setDuedate(HelperClass.convertDatabaseToUI(rs.getString("Due_date")));
				vo.setPublisher(rs.getString("Publisher_Name"));
				vo.setSupplier(rs.getString("Supplier_Name"));
				
				journal.add(vo);
			}
			
			           
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{

			if(rs!=null){
				try{
					rs.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch (Exception e){
					e.printStackTrace();

				}
			}

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryDAOIMPL : getJournalListReport Ending");
		return journal;
	}
	

	public List<LibrarySubsciberDetailsPojo> IndividualSearchInIssueReturnStatement(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null,pstmt1= null,pstmt2= null,pstmt3=null;
		ResultSet rs = null,rs1=null,rs2= null,rs3= null;
		Connection conn = null;
		ArrayList<LibrarySubsciberDetailsPojo> ReservationList = new ArrayList<LibrarySubsciberDetailsPojo>();
		try {
			conn = JDBCConnection.getSeparateConnection();

	 if (pojo.getStatement().equalsIgnoreCase("issue")) 
			{
		 
			
		       pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_RETURN_DETAILS_IN_ISSUE_INDIVIDUAL);
					
				pstmt.setString(1,pojo.getLocationName());
				pstmt.setString(2,"%"+pojo.getSearchTextVal()+"%");
				
				System.out.println(pstmt);
				rs=pstmt.executeQuery();
				while(rs.next()){
					LibrarySubsciberDetailsPojo from=new LibrarySubsciberDetailsPojo();

					from.setSubscriber_id(rs.getString("subscriberId"));
					from.setAccessionNo(rs.getString("Accession_number"));
					from.setSubscriberNo(rs.getString("subscriberNumber"));
					from.setStudentId(rs.getString("studentId"));
					from.setStaffId(rs.getString("staffId"));
					
					if(rs.getString("subscriberType").equalsIgnoreCase("teacher"))
					{
						pstmt1=conn.prepareStatement(LibrarySqlUtils.ISSUE_RETURN_TEACHER);
						pstmt1.setString(1,rs.getString("subscriberId"));
						pstmt1.setString(2,rs.getString("staffId"));
						rs1=pstmt1.executeQuery();
						System.out.println("teacher query>>"+pstmt1);
						while(rs1.next())
						{
							from.setSubscriberName(rs1.getString("subscriberNumber")+"-"+rs1.getString("teacher"));
						}
					}
					else if(rs.getString("subscriberType").equalsIgnoreCase("student"))
					{
						pstmt2=conn.prepareStatement(LibrarySqlUtils.ISSUE_RETURN_STUDENT);
						pstmt2.setString(1,rs.getString("subscriberId"));
						pstmt2.setString(2,rs.getString("studentId"));
						rs2=pstmt2.executeQuery();
						System.out.println("student query>>"+pstmt2);
						while(rs2.next())
						{
							System.out.println(rs2.getString("subscriberNumber")+"-"+rs2.getString("student"));
							from.setSubscriberName(rs2.getString("subscriberNumber")+"-"+rs2.getString("student"));
						}
					}
					else if(rs.getString("subscriberType").equalsIgnoreCase("others"))				
					{
						pstmt3=conn.prepareStatement(LibrarySqlUtils.ISSUE_RETURN_OTHERS);
						pstmt3.setString(1,rs.getString("subscriberId"));
						rs3=pstmt3.executeQuery();
						System.out.println("other query>>"+pstmt3);
						while(rs3.next())
						{
							System.out.println(rs3.getString("subscriberNumber")+"-"+rs3.getString("otherName"));
							from.setSubscriberName(rs3.getString("subscriberNumber")+"-"+rs3.getString("otherName"));
						
						}
					}
					ReservationList.add(from);
				   }
				}
			
			
	else if (pojo.getStatement().equalsIgnoreCase("return")) 
	{
		
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ISSUE_RETURN_DETAILS_IN_RETURNS_INDIVIDUAL);
			
			pstmt.setString(1,pojo.getLocationName());
			pstmt.setString(2,"%"+pojo.getSearchTextVal()+"%");
			
			System.out.println(pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				LibrarySubsciberDetailsPojo from=new LibrarySubsciberDetailsPojo();

				from.setSubscriber_id(rs.getString("subscriberId"));
				from.setAccessionNo(rs.getString("Accession_number"));
				from.setSubscriberNo(rs.getString("subscriberNumber"));
				from.setStudentId(rs.getString("studentId"));
				from.setStaffId(rs.getString("staffId"));
				String usertype=rs.getString("subscriberType");
				
				if(usertype.equalsIgnoreCase("teacher"))
				{
					pstmt1=conn.prepareStatement(LibrarySqlUtils.ISSUE_RETURN_TEACHER);
					pstmt1.setString(1,rs.getString("subscriberId"));
					pstmt1.setString(2,rs.getString("staffId"));
					rs1=pstmt1.executeQuery();
					System.out.println("teacher query>>"+pstmt1);
					while(rs1.next())
					{
						from.setSubscriberName(rs1.getString("subscriberNumber")+"-"+rs1.getString("teacher"));
					}
				}
				else if(usertype.equalsIgnoreCase("student"))
				{
					pstmt2=conn.prepareStatement(LibrarySqlUtils.ISSUE_RETURN_STUDENT);
					pstmt2.setString(1,rs.getString("subscriberId"));
					pstmt2.setString(2,rs.getString("studentId"));
					rs2=pstmt2.executeQuery();
					System.out.println("student query>>"+pstmt2);
					while(rs2.next())
					{
						from.setSubscriberName(rs2.getString("subscriberNumber")+"-"+rs2.getString("student"));
					}
				}
				else if(usertype.equalsIgnoreCase("others"))				
				{
					pstmt3=conn.prepareStatement(LibrarySqlUtils.ISSUE_RETURN_OTHERS);
					pstmt3.setString(1,rs.getString("subscriberId"));
					rs3=pstmt3.executeQuery();
					System.out.println("other query>>"+pstmt3);
					while(rs3.next())
					{
						from.setSubscriberName(rs3.getString("subscriberNumber")+"-"+rs3.getString("otherName"));
					}
				}
				ReservationList.add(from);
			}
		}
		
	
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		finally {
			try {
				if (rs!= null && (!rs.isClosed())) {
					rs.close();
				}
				if (rs1!= null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs2!= null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (rs3!= null && (!rs3.isClosed())) {
					rs3.close();
				}
				
				if (pstmt!= null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1!= null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2!= null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmt3!= null && (!pstmt3.isClosed())) {
					pstmt3.close();
				}
				if (conn!= null && (!conn.isClosed())) {
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
		return ReservationList;
	}

	public List<LibraryStockEntryVO> getBookIssueReturnDetailsByAccessionNo(LibraryStockEntryVO libVo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getBookReturnDetailsByAccessionNo : getBookReturnDetailsByAccessionNo Starting");
		List<LibraryStockEntryVO> libVoList = new ArrayList<LibraryStockEntryVO>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			 if (libVo.getStatement().equalsIgnoreCase("issue")) 
				{
			pstmObj = conn.prepareStatement(LibrarySqlUtils.GET_BOOK_ISSUE_DETAIL_BY_ACCESSIONID_IN_ISSUE_RETURN);
			pstmObj.setString(1, libVo.getSubscriberId());
			pstmObj.setString(2, libVo.getAccessionNo()+"%");
			pstmObj.setString(3, libVo.getLocation());
			System.out.println(pstmObj);

			rs = pstmObj.executeQuery();
			while (rs.next()) {
				LibraryStockEntryVO form = new LibraryStockEntryVO();
				form.setSubscriberId(rs.getString("sub_id"));
				form.setBookTitle(rs.getString("Book_title"));
				form.setIssued_date(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
				form.setIssued_return_date(HelperClass.convertDatabaseToUI(rs.getString("to_date")));
				libVoList.add(form);
			}
		}
			 else if (libVo.getStatement().equalsIgnoreCase("return")) 
				{
				 pstmObj = conn.prepareStatement(LibrarySqlUtils.GET_BOOK_RETURN_DETAIL_BY_ACCESSIONID_IN_ISSUE_RETURN);
					pstmObj.setString(1, libVo.getSubscriberId());
					pstmObj.setString(2, libVo.getAccessionNo()+"%");
					pstmObj.setString(3, libVo.getLocation());
					System.out.println(pstmObj);


					rs = pstmObj.executeQuery();
					while (rs.next()) {
						LibraryStockEntryVO form = new LibraryStockEntryVO();
						form.setSubscriberId(rs.getString("sub_id"));
						form.setBookTitle(rs.getString("Book_title"));
						form.setIssued_date(HelperClass.convertDatabaseToUI(rs.getString("book_issued_date")));
						form.setIssued_return_date(HelperClass.convertDatabaseToUI(rs.getString("book_returned_date")));
						libVoList.add(form);
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
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in getBookReturnDetailsByAccessionNo : getBookReturnDetailsByAccessionNo Ending");
		return libVoList;
	}

	public List<LibraryStockEntryVO> getIssueReturnAccessionNo(LibraryStockEntryVO registrationVo) {
		logger.setLevel(Level.DEBUG);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getAccessionNo : getAccessionNo Starting");

		ArrayList<LibraryStockEntryVO> getAccessionDetails = new ArrayList<LibraryStockEntryVO>();
		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			if(registrationVo.getStatement().equalsIgnoreCase("issue")){
				
			pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ACCESSION_NUMBER);
			pstmt.setString(1,registrationVo.getSubscriberId());
			System.out.println(" query is " + pstmt);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibraryStockEntryVO libvo = new LibraryStockEntryVO();
				libvo.setAccessionNo(rs.getString("Accession_number"));
				libvo.setAccessionNoId(rs.getString("Entry_id"));
				getAccessionDetails.add(libvo);
			}
		}
			else if(registrationVo.getStatement().equalsIgnoreCase("return")){
				
				pstmt = conn.prepareStatement(LibrarySqlUtils.GET_ACCESSION_NUMBER_BY_ISSUERETURN_RETURN);
				pstmt.setString(1,registrationVo.getSubscriberId());
				System.out.println(" query is " + pstmt);

				rs = pstmt.executeQuery();
				while (rs.next()) {
					LibraryStockEntryVO libvo = new LibraryStockEntryVO();
					libvo.setAccessionNo(rs.getString("Accession_number"));
					libvo.setAccessionNoId(rs.getString("Entry_id"));
					getAccessionDetails.add(libvo);
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
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
				+ " Control in getAccessionNo : getAccessionNo Ending");
		return getAccessionDetails;
	}
	
	@Override
	public ArrayList<LibrarySearchSubscriberVO> getTransferStaffdetailsByDepartment(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(LibrarySqlUtils.TRANSFER_STAFF_LIST_DETAILS_BY_DEPARTMENT);
			pstmt.setString(1, pojo.getLocationName());
			pstmt.setString(2, pojo.getAccId());
			pstmt.setString(3, pojo.getLocationName());
			pstmt.setString(4, pojo.getDepartment());
			pstmt.setString(5, pojo.getDesignation());
			pstmt.setString(6, pojo.getLibLoc());
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));
				vo.setLibLoc(rs.getString("library_location_name"));
				
				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}

	public ArrayList<LibrarySearchSubscriberVO> getTransferStaffdetailsByDesignation(LibrarySubsciberDetailsPojo pojo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<LibrarySearchSubscriberVO> studentData = new ArrayList<LibrarySearchSubscriberVO>();
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.TRANSFER_STAFF_LIST_DETAILS_BY_DESIGNATION);
			pstmt.setString(1, pojo.getLocationName());
			pstmt.setString(2, pojo.getAccId());
			pstmt.setString(3, pojo.getLocationName());
			pstmt.setString(4, pojo.getDepartment());
			pstmt.setString(5, pojo.getDesignation());
			pstmt.setString(6, pojo.getLibLoc());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				LibrarySearchSubscriberVO vo = new LibrarySearchSubscriberVO();
				vo.setSlno(count);
				vo.setSubscriberId(rs.getString("subscriberId"));
				vo.setLocation(rs.getString("loc_ID"));
				vo.setSubssciberNo(rs.getString("subscriberNumber"));
				vo.setStaffName(rs.getString("teacher"));
				vo.setStaffDepartment(rs.getString("DEPT_NAME"));
				vo.setStaffDesignation(rs.getString("designationName"));
				vo.setStatus(rs.getString("status"));
				vo.setLibLoc(rs.getString("library_location_name"));

				studentData.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return studentData;
	}
	
	public String dateOverLapValidate(String date, String accyear,
			String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : dateOverLapValidate Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String lastExistingDate=null;
		String status = "";
		String no = "";
		Connection conn = null;
		try
		{
			List<String> datevalid=null;
			conn = JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement("SELECT enddate FROM campus_fee_termdetails where accyear=? AND locationId=? ORDER BY enddate DESC LIMIT 1");
			pstmt.setString(1, accyear);
			pstmt.setString(2, locationId);
			System.out.println(pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				no=rs.getString(1);

				System.out.println("hello");
				 datevalid=HelperClass.getDateListBetweenDates(date, no);
				 if(datevalid.size()==0){
				datevalid=HelperClass.getDateListBetweenDates(no,date);
				lastExistingDate="-"+Integer.toString(datevalid.size()-2);
				 }
				 else{
					 lastExistingDate=Integer.toString(datevalid.size());
				 }

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
				+ " Control in TermMasterDAOIMPL : dateOverLapValidate Ending");

		return lastExistingDate;
	}

	
	public List<LibraryStockEntryVO> getLibraryLocationBySchool(String id) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TermMasterDAOIMPL : getLibraryLocationBySchool Starting");
	
	ArrayList<LibraryStockEntryVO> libraryLoc = new ArrayList<LibraryStockEntryVO>();
	PreparedStatement psmt = null;
	ResultSet rs = null;
	Connection conn = null;
	try{
	conn=JDBCConnection.getSeparateConnection();
	psmt=conn.prepareStatement("SELECT `library_loc_id`,`library_location_name` FROM `campus_library_location` WHERE `loc_id`=?");
	psmt.setString(1, id);
	System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+psmt);
	rs=psmt.executeQuery();
	while(rs.next()){
		LibraryStockEntryVO vo = new LibraryStockEntryVO();
		vo.setLibraryLocationName(rs.getString("library_loc_id"));
		vo.setLibraryLocationId(rs.getString("library_location_name"));
		libraryLoc.add(vo);
	}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : getLibraryLocationBySchool Ending");
		return libraryLoc;
	}

	public List<LibraryStockEntryVO> getAccessionNoByIssueStatus(
			LibraryStockEntryVO registrationVo) {
		logger.setLevel(Level.DEBUG);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getAccessionNo : getAccessionNo Starting");

		ArrayList<LibraryStockEntryVO> getAccessionDetails = new ArrayList<LibraryStockEntryVO>();
		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			/*if(registrationVo.getLocid().equalsIgnoreCase("all")){
					registrationVo.setLocid("%%");
				}*/
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(LibrarySqlUtils.get_acc_no_by_status);
			pstmt.setString(1,registrationVo.getAccessionNo()+"%");

			System.out.println(" query is "+pstmt);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				LibraryStockEntryVO libvo = new LibraryStockEntryVO();
				libvo.setAccessionNo(rs.getString("Accession_number"));
				libvo.setAccessionNoId(rs.getString("Entry_id"));
				getAccessionDetails.add(libvo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
				+ " Control in getAccessionNo : getAccessionNo Ending");
		return getAccessionDetails;
	}

}
	



