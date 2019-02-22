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

import com.centris.campus.dao.AcadamicYearPlanDao;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.pojo.AcadamicYearPlanPOJO;
import com.centris.campus.util.AcadamicYearPlanSqlUtil;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AcadamicYearPlanVO;
import com.centris.campus.vo.LocationVO;


public class AcadamicYearPlanDaoImpl implements AcadamicYearPlanDao{

	private static final Logger logger = Logger.getLogger(AcademicYearMasterDaoImpl.class);

	public String insertAcadamicYearPlan(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcadamicYearPlanDaoImpl: insertAcadamicYearPlan : Starting");

		List<LocationVO> locationList = new ArrayList<LocationVO>();
		PreparedStatement ps_insertPlan = null;
		Connection conn = null;
		String status = MessageConstants.FALSE;
		int count=0;
		try {

			conn = JDBCConnection.getSeparateConnection();
			ps_insertPlan = conn.prepareStatement("insert into campus_accyearplan(AccYearPlanCode,Title,StartTime,EndTime,AcadamicYear,CreatedBy,CreateDate,locationId)values(?,?,?,?,?,?,?,?)");
		
				ps_insertPlan.setString(1,acadamicYearPlanPOJO.getEventid());
				ps_insertPlan.setString(2,acadamicYearPlanPOJO.getTeachertype());
				ps_insertPlan.setString(3,acadamicYearPlanPOJO.getStarttime());
				ps_insertPlan.setString(4,acadamicYearPlanPOJO.getEndtime());
				ps_insertPlan.setString(5,acadamicYearPlanPOJO.getAcadamicYear());
				ps_insertPlan.setString(6,acadamicYearPlanPOJO.getCreatedby());
				ps_insertPlan.setTimestamp(7,HelperClass.getCurrentTimestamp());
				ps_insertPlan.setString(8,acadamicYearPlanPOJO.getLocationId());
				count = ps_insertPlan.executeUpdate();
			

			if(count>0){
				status = MessageConstants.TRUE;
			}
			else
			{
				status = MessageConstants.FALSE;

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
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
				+ " Control in AcadamicYearPlanDaoImpl: insertAcadamicYearPlan: Ending");

		return status;
	}

	public AcadamicYearPlanVO editAcadamicYearPlan(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcadamicYearPlanDaoImpl: editAcadamicYearPlan : Starting");

		PreparedStatement ps_getEvent = null;
		ResultSet rs_getEvent = null;
		Connection conn = null;
		AcadamicYearPlanVO eventdetails = new AcadamicYearPlanVO();
		try {

			conn = JDBCConnection.getSeparateConnection();
			ps_getEvent = conn.prepareStatement(AcadamicYearPlanSqlUtil.GET_SINGLE_ACADAMICYEAR_PLAN);
			ps_getEvent.setString(1, acadamicYearPlanPOJO.getEventid());
			rs_getEvent = ps_getEvent.executeQuery();

			while(rs_getEvent.next()){

				eventdetails.setEventid(rs_getEvent.getString("AccYearPlanCode"));
				eventdetails.setTitle(rs_getEvent.getString("Title"));
				eventdetails.setDate(HelperClass.convertDatabaseToUI(rs_getEvent.getString("Date")));
				eventdetails.setStarttime(rs_getEvent.getString("StartTime"));
				eventdetails.setWeekday(rs_getEvent.getString("WeekDay"));
				eventdetails.setEndtime(rs_getEvent.getString("EndTime"));
				eventdetails.setFile(rs_getEvent.getString("InvitationFile"));
				eventdetails.setDescription(rs_getEvent.getString("Description"));
				eventdetails.setVenue_details(rs_getEvent.getString("venue_details"));
				eventdetails.setLocationId(rs_getEvent.getString("locationId"));
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if (rs_getEvent != null&& (!rs_getEvent.isClosed())) {
					rs_getEvent.close();
				}
				if (ps_getEvent != null&& (!ps_getEvent.isClosed())) {
					ps_getEvent.close();
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
				+ " Control in AcadamicYearPlanDaoImpl: editAcadamicYearPlan: Ending");

		return eventdetails;
	}

	public ArrayList<AcadamicYearPlanVO> getSearchDetails(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcadamicYearPlanDaoImpl: getSearchDetails : Starting");

		PreparedStatement ps_getEvent = null;
		ResultSet rs_getEvent = null;
		Connection conn = null;
		ArrayList<AcadamicYearPlanVO> eventlist = new ArrayList<AcadamicYearPlanVO>();
		try {
			String locationId=null;
			if(acadamicYearPlanPOJO.getLocationId().equalsIgnoreCase("all")){
				locationId="%%";
			}
			else{
				locationId=acadamicYearPlanPOJO.getLocationId();
			}
			conn = JDBCConnection.getSeparateConnection();

			ps_getEvent = conn.prepareStatement(AcadamicYearPlanSqlUtil.GET_SEARCH_ACADAMICYEAR_PLAN);

			ps_getEvent.setString(1,"%"+acadamicYearPlanPOJO.getSerachText().trim()+"%");
			ps_getEvent.setString(2,"%"+acadamicYearPlanPOJO.getSerachText().trim()+"%");
			ps_getEvent.setString(3,"%"+acadamicYearPlanPOJO.getSerachText().trim()+"%");
			ps_getEvent.setString(4,"%"+acadamicYearPlanPOJO.getSerachText().trim()+"%");
			ps_getEvent.setString(5,"%"+acadamicYearPlanPOJO.getSerachText()+"%");
			ps_getEvent.setString(6,acadamicYearPlanPOJO.getAcadamicYear());
			ps_getEvent.setString(7,locationId);
			rs_getEvent = ps_getEvent.executeQuery();

			while(rs_getEvent.next()){

				AcadamicYearPlanVO  eventdetailsVO  = new AcadamicYearPlanVO();

				eventdetailsVO.setEventid(rs_getEvent.getString("AccYearPlanCode"));
				eventdetailsVO.setTitle(rs_getEvent.getString("Title"));
				eventdetailsVO.setDate(HelperClass.convertDatabaseToUI(rs_getEvent.getString("Date")));
				eventdetailsVO.setStarttime(rs_getEvent.getString("StartTime"));
				eventdetailsVO.setWeekday(rs_getEvent.getString("WeekDay"));
				eventdetailsVO.setEndtime(rs_getEvent.getString("EndTime"));
				eventdetailsVO.setFile(rs_getEvent.getString("InvitationFile"));
				eventdetailsVO.setVenue_details(rs_getEvent.getString("venue_details"));
				eventdetailsVO.setDescription(rs_getEvent.getString("Description"));

				eventlist.add(eventdetailsVO);
			}




		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if (rs_getEvent != null&& (!rs_getEvent.isClosed())) {
					rs_getEvent.close();
				}
				if (ps_getEvent != null&& (!ps_getEvent.isClosed())) {
					ps_getEvent.close();
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
				+ " Control in AcadamicYearPlanDaoImpl: getSearchDetails : Ending");

		return eventlist;
	}

	public ArrayList<AcadamicYearPlanVO> getAllEventDetails(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcadamicYearPlanDaoImpl: getAllEventDetails : Starting");

		PreparedStatement ps_getEvent = null;
		ResultSet rs_getEvent = null;
		Connection conn = null;
		ArrayList<AcadamicYearPlanVO> eventlist = new ArrayList<AcadamicYearPlanVO>();
		try {

			conn = JDBCConnection.getSeparateConnection();

			ps_getEvent = conn.prepareStatement(AcadamicYearPlanSqlUtil.GET_ALL_ACADAMICYEAR_PLANS);
			ps_getEvent.setString(1, acadamicYearPlanPOJO.getAcadamicYear());
			ps_getEvent.setString(2, acadamicYearPlanPOJO.getLocationId());

			System.out.println("GET_ALL_ACADAMICYEAR_PLANS::"+ps_getEvent);

			rs_getEvent = ps_getEvent.executeQuery();

			while(rs_getEvent.next()){

				AcadamicYearPlanVO  eventdetailsVO  = new AcadamicYearPlanVO();

				eventdetailsVO.setEventid(rs_getEvent.getString("AccYearPlanCode"));
				eventdetailsVO.setTitle(rs_getEvent.getString("Title"));
				eventdetailsVO.setStarttime(rs_getEvent.getString("StartTime"));
				eventdetailsVO.setEndtime(rs_getEvent.getString("EndTime"));
				eventdetailsVO.setLocationName(rs_getEvent.getString("Location_Name"));
				System.out.println("Title::"+rs_getEvent.getString("Title"));
				eventlist.add(eventdetailsVO);
			}




		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if (rs_getEvent != null&& (!rs_getEvent.isClosed())) {
					rs_getEvent.close();
				}
				if (ps_getEvent != null&& (!ps_getEvent.isClosed())) {
					ps_getEvent.close();
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
				+ " Control in AcadamicYearPlanDaoImpl: getAllEventDetails : Ending");

		return eventlist;
	}

	public String deleteAcadamicYearPlan(String[] event_code) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcadamicYearPlanDaoImpl: deleteAcadamicYearPlan : Starting");

		PreparedStatement ps_deletePlan = null;
		Connection conn = null;
		String status = MessageConstants.FALSE;
		int no =0;

		try {


			for(int i=0;i<event_code.length;i++){

				conn = JDBCConnection.getSeparateConnection();

				System.out.println(event_code[i]);
				ps_deletePlan = conn.prepareStatement(AcadamicYearPlanSqlUtil.DELETE_ACADAMICYEAR_PLAN);

				ps_deletePlan.setString(1, event_code[i]);

				System.out.println(ps_deletePlan);
				no = ps_deletePlan.executeUpdate();

				if (no > 0) {

					status = "Acadamic Year Plan Deleted Successfully";
				} else {

					status = MessageConstants.FALSE;
				}


			}
			/*}
		}*/	

			/*

			conn = JDBCConnection.getSeparateConnection();

			ps_deletePlan = conn.prepareStatement(AcadamicYearPlanSqlUtil.DELETE_ACADAMICYEAR_PLAN);

			ps_deletePlan.setString(1,acadamicYearPlanPOJO.getEventid());

			int count = ps_deletePlan.executeUpdate();

			if(count>0){


			}



			 */} catch (SQLException e) {
				 logger.error(e.getMessage(), e);
				 e.printStackTrace();
			 } catch (Exception e1) {
				 logger.error(e1.getMessage(), e1);
				 e1.printStackTrace();
			 } finally {
				 try {

					 if (ps_deletePlan != null&& (!ps_deletePlan.isClosed())) {
						 ps_deletePlan.close();
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
				+ " Control in AcadamicYearPlanDaoImpl: deleteAcadamicYearPlan : Ending");

		return status;
	}

	public String updateAcadamicYearPlan(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcadamicYearPlanDaoImpl: updateAcadamicYearPlan : Starting");

		PreparedStatement ps_insertPlan = null;
		Connection conn = null;
		String status = MessageConstants.FALSE;

		try {

			conn = JDBCConnection.getSeparateConnection();

			ps_insertPlan = conn.prepareStatement("update campus_accyearplan set Title=?,StartTime=?,EndTime=?,ModifiedBy=?,ModifiedDate=?,locationId=? where AccYearPlanCode=?");

			ps_insertPlan.setString(1,acadamicYearPlanPOJO.getTeachertype());
			ps_insertPlan.setString(2,acadamicYearPlanPOJO.getStarttime());
			ps_insertPlan.setString(3,acadamicYearPlanPOJO.getEndtime());
			ps_insertPlan.setString(4,acadamicYearPlanPOJO.getCreatedby());
			ps_insertPlan.setTimestamp(5,HelperClass.getCurrentTimestamp());
			ps_insertPlan.setString(6,acadamicYearPlanPOJO.getLocationId());
			ps_insertPlan.setString(7,acadamicYearPlanPOJO.getEventid());
			int count = ps_insertPlan.executeUpdate();

			System.out.println(ps_insertPlan);


			if(count>0){

				status = MessageConstants.TRUE;
			}



		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
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
				+ " Control in AcadamicYearPlanDaoImpl: updateAcadamicYearPlan: Ending");

		return status;
	}

	public String validateAcadamicYearPlan(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcadamicYearPlanDaoImpl: validateAcadamicYearPlan : Starting");

		PreparedStatement ps_validatePlan = null;
		ResultSet rs_validatePlan = null;
		Connection conn = null;
		String status = MessageConstants.FALSE;
		int count=0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			ps_validatePlan = conn.prepareStatement(AcadamicYearPlanSqlUtil.VALIDATE_ACADAMICYEAR_PLAN);

			ps_validatePlan.setString(1,HelperClass.convertUIToDatabase(acadamicYearPlanPOJO.getDate()));
			ps_validatePlan.setString(2,acadamicYearPlanPOJO.getStarttime());
			ps_validatePlan.setString(3,acadamicYearPlanPOJO.getEndtime());
			ps_validatePlan.setString(4, acadamicYearPlanPOJO.getVenue());

			System.out.println("ps_validatePlan::::"+ps_validatePlan);

			rs_validatePlan  = ps_validatePlan.executeQuery();

			while(rs_validatePlan.next()){

				count = rs_validatePlan.getInt(1);
			}


			if(count>0){

				status = MessageConstants.TRUE;
			}



		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if (rs_validatePlan != null&& (!rs_validatePlan.isClosed())) {
					rs_validatePlan.close();
				}
				if (ps_validatePlan != null&& (!ps_validatePlan.isClosed())) {
					ps_validatePlan.close();
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
				+ " Control in AcadamicYearPlanDaoImpl: validateAcadamicYearPlan: Ending");

		return status;

	}

	public String validateAcadamicYearPlanUpdate(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcadamicYearPlanDaoImpl: validateAcadamicYearPlan : Starting");

		PreparedStatement ps_validatePlan = null;
		ResultSet rs_validatePlan = null;
		Connection conn = null;
		String status = MessageConstants.FALSE;
		int count=0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			ps_validatePlan = conn.prepareStatement(AcadamicYearPlanSqlUtil.VALIDATE_ACADAMICYEAR_PLAN_UPDATE);

			ps_validatePlan.setString(1,HelperClass.convertUIToDatabase(acadamicYearPlanPOJO.getDate()));
			ps_validatePlan.setString(2,acadamicYearPlanPOJO.getStarttime());
			ps_validatePlan.setString(3,acadamicYearPlanPOJO.getEndtime());
			ps_validatePlan.setString(4,acadamicYearPlanPOJO.getVenue());
			ps_validatePlan.setString(5,acadamicYearPlanPOJO.getEventid());

			System.out.println(ps_validatePlan);

			rs_validatePlan  = ps_validatePlan.executeQuery();

			while(rs_validatePlan.next()){

				count = rs_validatePlan.getInt(1);
			}


			if(count>0){

				status = MessageConstants.TRUE;
			}



		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if (rs_validatePlan != null&& (!rs_validatePlan.isClosed())) {
					rs_validatePlan.close();
				}
				if (ps_validatePlan != null&& (!ps_validatePlan.isClosed())) {
					ps_validatePlan.close();
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
				+ " Control in AcadamicYearPlanDaoImpl: validateAcadamicYearPlan: Ending");

		return status;
	}

}
