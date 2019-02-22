package com.centris.campus.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.LocationDao;
import com.centris.campus.forms.LocationMasterForm;
import com.centris.campus.pojo.LocationMasterPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.LocationVO;

public class LocationDAOImpl implements LocationDao {

	private static Logger logger = Logger.getLogger(LocationDAOImpl.class);

	public List<LocationVO> getLocationDetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl: getLocationDetails : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn=null;
		ArrayList<LocationVO> list = new ArrayList<LocationVO>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.LOCATION_LIST);
			rs=pstmt.executeQuery();
			System.out.println("school "+pstmt);
			while(rs.next()){

				LocationVO locationList=new LocationVO();
				locationList.setLocation_id(rs.getString("Location_Id"));
				locationList.setLocationname(rs.getString("Location_Name"));
				locationList.setDesc(rs.getString("Description"));
				list.add(locationList);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl : getLocationDetails : Ending");

		return list;
	}

	@Override
	public String insertLocationDetailsAction(LocationMasterPojo locationpojo,String updateId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl: insertLocationDetailsAction : Starting");

		PreparedStatement pstmt = null;

		Connection conn = null;
		int count = 0;
		String status = null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			if(updateId.equalsIgnoreCase("")){
				pstmt=conn.prepareStatement(SQLUtilConstants.INSERTLOCATION);
				pstmt.setString(1, locationpojo.getLocation_id().trim());
				pstmt.setString(2, locationpojo.getLocationname().trim());
				pstmt.setString(3, locationpojo.getDesc());
				pstmt.setString(4, locationpojo.getCreatedBy());
				pstmt.setString(5, "Y");
				count=pstmt.executeUpdate();
				if (count > 0) {
					status = MessageConstants.ADD_LOCATION_SUCCESS;
				} else {
					status = MessageConstants.ADD_LOCATION_FAIL;
				}
			}
			else if(!(updateId.equalsIgnoreCase(""))){
					pstmt=conn.prepareStatement(SQLUtilConstants.UPDATELOCATION);
					pstmt.setString(1, locationpojo.getLocationname().trim());
					pstmt.setString(2, locationpojo.getDesc());
					pstmt.setString(3, locationpojo.getCreatedBy());
					pstmt.setString(4, "Y");
					pstmt.setString(5, updateId.trim());
					count=pstmt.executeUpdate();
					if (count > 0) {
						status = MessageConstants.UPDATE_LOCATION_SUCCESS;
					} else {
						status = MessageConstants.UPDATE_LOCATION_FAIL;
					}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl : insertLocationDetailsAction : Ending");

		return status;
	}
	@Override
	public boolean validateLocName(LocationMasterForm form1) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationMasterDAOImpl : validateLocName : Starting");

		PreparedStatement loc_pstmt = null;
		ResultSet loc_rs = null;
		boolean locame_available = false;
		int count = 0;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			loc_pstmt = conn.prepareStatement(SQLUtilConstants.VALIDATE_LOC_NAME);
			loc_pstmt.setString(1, form1.getLocationname());
			loc_rs = loc_pstmt.executeQuery();

			while (loc_rs.next()) {
				count = loc_rs.getInt("Location_Name");
			}

			if (count > 0) {
				locame_available = true;
			} else {
				locame_available = false;
			}

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			logger.error(sqle);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (loc_rs != null && (!loc_rs.isClosed())) {

					loc_rs.close();
				}
				if (loc_pstmt != null && (!loc_pstmt.isClosed())) {

					loc_pstmt.close();
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

		return locame_available;
	}

	public boolean validateLocNameUpdate(LocationVO validateUpdate) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationMasterDAOImpl: validateDeptNameUpdate : Starting");

		PreparedStatement loc_pstmt = null;
		ResultSet loc_rs = null;

		boolean locame_available = false;
		int count = 0;

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			loc_pstmt = conn.prepareStatement(SQLUtilConstants.VALIDATE_LOCATION_UPDATE);
			loc_pstmt.setString(1, validateUpdate.getLocationname());
			loc_pstmt.setString(2, validateUpdate.getLocation_id());
			loc_rs = loc_pstmt.executeQuery();

			while (loc_rs.next()) {
				count = loc_rs.getInt("Location_Name");
			}
			if (count > 0) {
				locame_available = true;
			} else {
				locame_available = false;
			}

		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (loc_rs != null && (!loc_rs.isClosed())) {

					loc_rs.close();
				}
				if (loc_pstmt != null && (!loc_pstmt.isClosed())) {

					loc_pstmt.close();
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
				+ " Control in LocationMasterDAOImpl: validateDeptNameUpdate : Ending");

		return locame_available;

	}
	@Override
	public LocationVO editLocation(String edit) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationMasterDAOImpl: editLocation : Starting");

		PreparedStatement loc_pstmt = null;
		ResultSet loc_rs = null;
		Connection conn = null;
		LocationVO addLocationVO = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			loc_pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_LOCATION);
			loc_pstmt.setString(1, edit);
			loc_rs = loc_pstmt.executeQuery();

			while (loc_rs.next()) {
				addLocationVO = new LocationVO();
				addLocationVO.setLocation_id(loc_rs.getString("Location_Id"));
				addLocationVO.setLocationname(loc_rs.getString("Location_Name"));
				addLocationVO.setDesc(loc_rs.getString("Description"));

			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (loc_rs != null && (!loc_rs.isClosed())) {

					loc_rs.close();
				}
				if (loc_pstmt != null && (!loc_pstmt.isClosed())) {

					loc_pstmt.close();
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
				+ " Control in LocationMasterDAOImpl: editLocation  : Ending");

		return addLocationVO;

	}
	@Override
	public String deleteDepartmentDetails(String[] locid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationMasterDAOImpl: deletelocationDetails : Starting");

		ResultSet location_rs = null;

		PreparedStatement location_pstmt = null;
		PreparedStatement ps_deletelocation = null;
		ResultSet rs_deletelocation = null;
		int no = 0;
		String status = null;
		int count=0;
		Connection conn = null;
		try {
			for(int i=0;i<locid.length;i++){
				conn = JDBCConnection.getSeparateConnection();

				location_pstmt = conn.prepareStatement(SQLUtilConstants.CHECK_LOCATION_MAP);
				location_pstmt.setString(1, locid[i]);
				rs_deletelocation = location_pstmt.executeQuery();

				while (rs_deletelocation.next()) {
					no = rs_deletelocation.getInt("locname");
					if (no > 0) {
						count++;
					} 
				}
				rs_deletelocation.close();
				location_pstmt.close();
				if(no == 0){
					PreparedStatement pstmt = conn.prepareStatement("select count(*) locname from campus_holidaymaster where LOC_ID=?");
					pstmt.setString(1,locid[i]);
					System.out.println(pstmt);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()){
						no = rs.getInt("locname");
						if (no > 0) {
							count++;
						} 
					}
					rs.close();
					pstmt.close();

					if(no == 0){

						PreparedStatement pstmtleave = conn.prepareStatement("select count(*) locname from campus_new_leave_bank where Loc_ID=?");
						pstmtleave.setString(1, locid[i]);
						System.out.println(pstmtleave);
						ResultSet rsleave = pstmtleave.executeQuery();
						while(rsleave.next()){
							no = rsleave.getInt("locname");
							if (no > 0) {
								count++;
							} 
						}
						pstmtleave.close();
						rsleave.close();

						if(no == 0 ){
							ps_deletelocation = (PreparedStatement) JDBCConnection .getStatement(SQLUtilConstants.DELETE_LOCATION);
							ps_deletelocation.setString(1, locid[i]);

							System.out.println(ps_deletelocation);
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
		catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		} finally {

			try {

				if (rs_deletelocation != null
						&& (!rs_deletelocation.isClosed())) {

					rs_deletelocation.close();
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
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationMasterDAOImpl: deleteLocationDetails : Ending");


		System.out.println(status);
		return status;
	}
	@Override
	public List<LocationVO> searchLocationDetails(String searchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationMasterDAOImpl : searchLocationDetails Starting");

		ArrayList<LocationVO> locationlist = new ArrayList<LocationVO>();
		PreparedStatement location_pstmt = null;
		ResultSet location_rs = null;
		Connection conn = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			location_pstmt = conn.prepareStatement(SQLUtilConstants.SEARCH_LOCATION_DETAILS);
			location_pstmt.setString(1,searchName+"%");
			location_pstmt.setString(2,searchName+"%");
			location_rs = location_pstmt.executeQuery();

			while (location_rs.next()) {
				LocationVO location = new LocationVO();

				location.setLocation_id(location_rs.getString("Location_Id"));
				location.setLocationname(location_rs.getString("Location_Name"));
				location.setDesc(location_rs.getString("Description"));
				locationlist.add(location);

			}

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (location_rs != null && (!location_rs.isClosed())) {

					location_rs.close();
				}
				if (location_pstmt != null && (!location_pstmt.isClosed())) {

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

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LocationMasterDAOImpl: searchLocationDetails  : Ending");
		}
		return locationlist;
	}

}