package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.FuelMaintenanceDao;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.TransportUtilConstants;
import com.centris.campus.vo.DriverMasterVO;
import com.centris.campus.vo.FuelMaintenanceVO;
import com.centris.campus.vo.VehicleDetailsVO;

public class FuelMaintenanceDaoImpl implements FuelMaintenanceDao {

	private static final Logger logger = Logger
			.getLogger(FuelMaintenanceDaoImpl.class);

	public synchronized ArrayList<FuelMaintenanceVO> getfuelMaintenanceList() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl: getfuelMaintenanceList Starting");
		ArrayList<FuelMaintenanceVO> fuelList = new ArrayList<FuelMaintenanceVO>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;

		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.GET_FUEL_DETAILS);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				FuelMaintenanceVO voobj = new FuelMaintenanceVO();
				voobj.setDate((HelperClass
						.convertDatabaseToUI(resultSet.getString("fueldate"))));

				voobj.setVehiclename(resultSet.getString("VehicleName"));
				voobj.setVehiclenumber(resultSet.getString("Vehicle_Reg_No"));
				voobj.setDrivername(resultSet.getString("Name"));
				voobj.setFuelType(resultSet.getString("FuelType"));
				voobj.setFuelCode(resultSet.getString("FuelCode"));
				voobj.setQuantity(resultSet.getString("Quantity"));
				voobj.setPrice(resultSet.getString("FuelCost"));
				voobj.setMeterReading(resultSet.getString("MeterReading"));
				voobj.setLocation(resultSet.getString("Location"));
				voobj.setVehicleCode(resultSet.getString("VehicleCode"));
				voobj.setDriverCode(resultSet.getString("DriverCode"));
				// voobj.setSno(++count);
				fuelList.add(voobj);
			}
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
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl : getfuelMaintenanceList Ending");
		return fuelList;
	}

	public synchronized String addFuelDetails(FuelMaintenanceVO addFuellist) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl: addFuelDetails Starting");

		PreparedStatement pstmt = null;
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		ResultSet resultSet = null;
		Connection connection = null;
		int result = 0;
		String status = null;

		try {
			
			System.out.println("VehicleCode in DIOMPL :: :  "+addFuellist.getVehiclename());
			System.out.println("DriverCode in DIOMPL :: :  "+addFuellist.getDrivername());
			connection = JDBCConnection.getSeparateConnection();
			String dateTime = addFuellist.getDate() + " "
					+ addFuellist.getTime();

			pstmt = connection
					.prepareStatement(TransportUtilConstants.ADD_FUEL_DETAILS);
			pstmt.setString(1,
					IDGenerator.getPrimaryKeyID("transport_fuel_maintainence"));
			pstmt.setString(2, addFuellist.getVehicleCode());
			pstmt.setString(3,
					HelperClass.convertUItoDatabaseWithDateTime(dateTime));
			// pstmt.setString(3,addFuellist.getDate());
			/* pstmt.setString(3,obj.getDate()); */
			pstmt.setString(4, addFuellist.getFuelType());
			pstmt.setString(5, addFuellist.getDriverCode());
			pstmt.setString(6, addFuellist.getQuantity());
			pstmt.setString(7, addFuellist.getPrice());
			pstmt.setString(8, addFuellist.getMeterReading());
			pstmt.setString(9, addFuellist.getLocation());
			pstmt.setTimestamp(10, time_stamp);
			pstmt.setString(11, addFuellist.getCreateUser());

			result = pstmt.executeUpdate();
			System.out.println("Fulemaintanance Query: : "+pstmt);
			if (result > 0) {

				status = MessageConstants.ADD_FUEL_SUCCESS;
			} else {

				status = MessageConstants.ADD_FUEL_FAIL;
			}
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
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl : addFuelDetails Ending");
		return status;
	}

	@Override
	public synchronized ArrayList<VehicleDetailsVO> searchVehicle(
			VehicleDetailsVO searchterm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl: searchVehicle Starting");

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;

		ArrayList<VehicleDetailsVO> vehicleList = new ArrayList<VehicleDetailsVO>();
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.SEARCH_VEHICLE);

			pstmt.setString(1, "%" + searchterm.getSearch() + "%");
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				VehicleDetailsVO voobj = new VehicleDetailsVO();
				voobj.setVehiclecode(resultSet.getString("VehicleCode"));
				voobj.setVehicleregno(resultSet.getString("Vehicle_Reg_No"));
				voobj.setVehicletype(resultSet.getString("VehicleType"));

				vehicleList.add(voobj);
			}

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
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl : searchVehicle Ending");

		return vehicleList;
	}

	@Override
	public synchronized ArrayList<VehicleDetailsVO> getVehicleNoBycode(
			VehicleDetailsVO searchTerm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl: getVehicleNoBycode Starting");

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;

		ArrayList<VehicleDetailsVO> vehicleList = new ArrayList<VehicleDetailsVO>();
		try {
			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection
					.prepareStatement(TransportUtilConstants.GET_VEHICLE_NO);

			pstmt.setString(1, searchTerm.getSearch());
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				VehicleDetailsVO voobj = new VehicleDetailsVO();
				voobj.setVehiclecode(resultSet.getString("VehicleCode"));
				voobj.setVehicleregno(resultSet.getString("Vehicle_Reg_No"));
				voobj.setFuelusedintheengine(resultSet.getString("Fuel_Type"));

				vehicleList.add(voobj);
			}

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
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl : getVehicleNoBycode Ending");

		return vehicleList;

	}

	public synchronized boolean deleteFuelDetails(
			FuelMaintenanceVO deleteFueldetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl: deleteFuelDetails Starting");

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;
		int result = 0;
		boolean status = false;

		try {

			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.DELETE_FUEL_DETAILS);

			pstmt.setString(1, deleteFueldetails.getFuelCode());

			result = pstmt.executeUpdate();
			if (result > 0) {

				status = true;

			} else {

				status = false;
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
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl : deleteFuelDetails Ending");

		return status;
	}

	public synchronized FuelMaintenanceVO editFuelDetails(
			FuelMaintenanceVO editFueldetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl: editFuelDetails Starting");

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;
		FuelMaintenanceVO voobj = null;

		try {

			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.EDIT_FUEL_DETAILS);

			pstmt.setString(1, editFueldetails.getFuelCode());

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				voobj = new FuelMaintenanceVO();
				voobj.setDate((HelperClass
						.convertDatabaseToUIWithDateTime(resultSet
								.getString("fueldate")).substring(0, 10)));
				voobj.setTime(HelperClass.convertDatabaseToUIWithDateTime(
						resultSet.getString("fueldate")).substring(11, 19));
				voobj.setFuelCode(resultSet.getString("FuelCode"));
				voobj.setVehiclename(resultSet.getString("VehicleName"));
				voobj.setVehiclenumber(resultSet.getString("Vehicle_Reg_No"));
				voobj.setDrivername(resultSet.getString("Name"));
				voobj.setFuelType(resultSet.getString("FuelType"));
				voobj.setQuantity(resultSet.getString("Quantity"));
				voobj.setPrice(resultSet.getString("FuelCost"));
				voobj.setMeterReading(resultSet.getString("MeterReading"));
				voobj.setLocation(resultSet.getString("Location"));
				voobj.setVehicleCode(resultSet.getString("VehicleCode"));
				voobj.setDriverCode(resultSet.getString("DriverCode"));

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
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl : editFuelDetails Ending");

		return voobj;

	}

	public synchronized String updateFuelDetails(FuelMaintenanceVO addFuellist) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl: updateFuelDetails Starting");

		PreparedStatement pstmt = null;
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		int result = 0;
		String status = null;
		Connection connection = null;

		try {
			connection = JDBCConnection.getSeparateConnection();

			String dateTime = addFuellist.getDate() + " "
					+ addFuellist.getTime();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.UPDATE_FUEL_DETAILS);

			pstmt.setString(1, addFuellist.getVehicleCode());
			pstmt.setString(2,
					HelperClass.convertUItoDatabaseWithDateTime(dateTime));
			/* pstmt.setString(3,obj.getDate()); */
			pstmt.setString(3, addFuellist.getFuelType());
			pstmt.setString(4, addFuellist.getDriverCode());
			pstmt.setString(5, addFuellist.getQuantity());
			pstmt.setString(6, addFuellist.getPrice());
			pstmt.setString(7, addFuellist.getMeterReading());
			pstmt.setString(8, addFuellist.getLocation());
			pstmt.setTimestamp(9, time_stamp);
			pstmt.setString(10, addFuellist.getCreateUser());
			pstmt.setString(11, addFuellist.getFuelCode());

			result = pstmt.executeUpdate();

			if (result > 0) {

				status = MessageConstants.UPDATE_FUEL_SUCCESS;
			} else {

				status = MessageConstants.UPDATE_FUEL_FAIL;
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

				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl : updateFuelDetails Ending");

		return status;
	}

	public synchronized ArrayList<FuelMaintenanceVO> searchFuelDetails(
			FuelMaintenanceVO searchvo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl: searchFuelDetails Starting");

		ArrayList<FuelMaintenanceVO> searchfuel = new ArrayList<FuelMaintenanceVO>();
		PreparedStatement pstmt = null;

		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.SEARCH_FUEL_DETAILS);
			pstmt.setString(1, "%" + searchvo.getSearch() + "%");
			pstmt.setString(2, "%" + searchvo.getSearch() + "%");
			pstmt.setString(3, "%" + searchvo.getSearch() + "%");
			pstmt.setString(4, "%" + searchvo.getSearch() + "%");
			pstmt.setString(5, "%" + searchvo.getSearch() + "%");
			pstmt.setString(6, "%" + searchvo.getSearch() + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {

				FuelMaintenanceVO search = new FuelMaintenanceVO();

				search.setDate(rs.getString("fueldate"));
				search.setLocation(rs.getString("Location"));
				search.setFuelType(rs.getString("FuelType"));
				search.setVehiclename(rs.getString("VehicleType"));
				search.setVehiclenumber(rs.getString("Vehicle_Reg_No"));
				search.setDrivername(rs.getString("Name"));
				search.setQuantity(rs.getString("Quantity"));
				search.setPrice(rs.getString("FuelCost"));
				search.setMeterReading(rs.getString("MeterReading"));
				searchfuel.add(search);

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

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl : searchFuelDetails Ending");

		return searchfuel;
	}

	public synchronized ArrayList<DriverMasterVO> searchDriver(
			DriverMasterVO searchTerm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl: searchDriver Starting");

		ArrayList<DriverMasterVO> driverlist = new ArrayList<DriverMasterVO>();
		PreparedStatement pstmt = null;

		Connection connection = null;
		ResultSet resultSet = null;

		try {

			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.SEARCH_DRIVER);

			pstmt.setString(1, "%" + searchTerm.getSearch() + "%");
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				DriverMasterVO voobj = new DriverMasterVO();
				voobj.setDriverCode(resultSet.getString("DriverCode"));
				voobj.setDriverName(resultSet.getString("Name"));

				driverlist.add(voobj);

			}

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
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl : searchDriver Ending");

		return driverlist;
	}

	@Override
	public ArrayList<VehicleDetailsVO> getVehicleList() {

		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl: getVehicleList Starting");
		
		ArrayList<VehicleDetailsVO> vehicleList =new ArrayList<VehicleDetailsVO>();
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet resultSet = null;
		try{
		connection = JDBCConnection.getSeparateConnection();
		pstmt = connection
				.prepareStatement(TransportUtilConstants.VEHICLE_NAME);
		resultSet=pstmt.executeQuery();
		while (resultSet.next()){
			
			VehicleDetailsVO list =new VehicleDetailsVO();
			list.setVehiclecode(resultSet.getString("VehicleCode"));
			list.setVehiclename(resultSet.getString("VehicleName"));
			list.setVehicleregno(resultSet.getString("Vehicle_Reg_No"));
			list.setVehicletype(resultSet.getString("VehicleType"));
			list.setFuelusedintheengine(resultSet.getString("Fuel_Type"));
			vehicleList.add(list);
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {

			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (connection != null && !connection.isClosed()) {

					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl : getVehicleList Ending");
		
		
		return vehicleList;
	
	}

	@Override
	public ArrayList<VehicleDetailsVO> getDriverNames() {

		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceDaoImpl: getDriverNames Starting");
		
		ArrayList<VehicleDetailsVO> driverList =new ArrayList<VehicleDetailsVO>();
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet resultSet = null;
		try{
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.DRIVER_NAME);
			resultSet=pstmt.executeQuery();
			
			while(resultSet.next()){
				VehicleDetailsVO list =new VehicleDetailsVO();
				list.setDriverCode(resultSet.getString("DriverCode"));
				list.setDriverName(resultSet.getString("Name"));
				driverList.add(list);			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		finally {

			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (connection != null && !connection.isClosed()) {

					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		return driverList;
	
	}

}
