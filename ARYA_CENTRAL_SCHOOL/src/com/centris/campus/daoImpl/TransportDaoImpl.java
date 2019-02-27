package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.centris.campus.dao.TransportDao;
import com.centris.campus.forms.TransportCategoryForm;
import com.centris.campus.forms.TransportForm;
import com.centris.campus.pojo.TransportPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.util.TransportUtilConstants;
import com.centris.campus.vo.DriverMsaterListVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TransportVo;
import com.centris.campus.vo.VehicleDetailsVO;
import com.centris.campus.vo.VehicleTypeVo;

public class TransportDaoImpl implements TransportDao {

	private static final Logger logger = Logger
			.getLogger(TransportDaoImpl.class);

	public ArrayList<VehicleDetailsVO> getAllvehicleDetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getAllvehicleDetails Starting");
		ArrayList<VehicleDetailsVO> getvehiclelist = new ArrayList<VehicleDetailsVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		int sno = 0;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.GET_ALL_VEHICLE_DETAILS);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				sno++;
				VehicleDetailsVO detailsVO = new VehicleDetailsVO();
				detailsVO.setSno(String.valueOf(sno));
				detailsVO.setVehiclecode(rs.getString("VehicleCode"));
				detailsVO.setVehicleregno(rs.getString("Vehicle_Reg_No"));
				detailsVO.setVehiclename(rs.getString("VehicleName"));
				detailsVO.setEnginenumber(rs.getString("Engine_number"));
				detailsVO.setChassisno(rs.getString("Chassis_No"));
				detailsVO.setVehicletype(rs.getString("VehicleType"));
				detailsVO.setTaxpaid(HelperClass.convertDatabaseToUI(rs.getString("Tax_paid")));
				detailsVO.setTaxexpirydate(HelperClass.convertDatabaseToUI(rs.getString("TaxExpiryDate")));
				detailsVO.setPollution(HelperClass.convertDatabaseToUI(rs.getString("Pollution")));
				getvehiclelist.add(detailsVO);
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
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getAllvehicleDetails  Ending");
		return getvehiclelist;
	}

	public String saveVehicleDetails(VehicleDetailsVO transportForm,
			String createUser, String vehiclecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : saveVehicleDetails Starting");
		int count = 0;
		String status = "";
		PreparedStatement pstmt = null;
		Connection connection = null;
		PreparedStatement updatepstmt = null;

		PreparedStatement updatepstmt2 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt3 = null;

		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt4 = null;

		try {

			connection = JDBCConnection.getSeparateConnection();
			
			System.out.println("Status in DIOMPL:: "+transportForm.getStatus());
			
			if (!transportForm.getStatus().equals("update")) {
				
				pstmt = connection.prepareStatement(TransportUtilConstants.SAVE_VEHICLE_DETAILS);
				
				System.out.println("In DIOMPL RC file "+transportForm.getRcfile());
				pstmt.setString(1, vehiclecode);
				pstmt.setString(2, transportForm.getVehicleregno());
				pstmt.setString(3, transportForm.getVehiclename());
				pstmt.setString(4, transportForm.getEnginenumber());
				pstmt.setString(5, transportForm.getChassisno());
				pstmt.setString(6, transportForm.getVehicletype());
				pstmt.setString(7, HelperClass.convertUIToDatabase(transportForm.getTaxpaid()));
				pstmt.setString(8, HelperClass.convertUIToDatabase(transportForm.getTaxexpirydate()));
				pstmt.setString(9, HelperClass.convertUIToDatabase(transportForm.getPollution()));
				pstmt.setString(10, transportForm.getRcfile());
				pstmt.setString(11, createUser);
				
				
				System.out.println("VEHICLE_DETAILS::"+pstmt);

				count = pstmt.executeUpdate();

				pstmt1 = connection.prepareStatement(TransportUtilConstants.SAVE_VEHICLE_INSURANCE_DETAILS);
			
				
				pstmt1.setString(1, vehiclecode);
				pstmt1.setString(2, transportForm.getCompanyname());
				pstmt1.setString(3, HelperClass.convertUIToDatabase(transportForm.getIssueddate()));
				pstmt1.setString(4, HelperClass.convertUIToDatabase(transportForm.getExpirydate()));
				pstmt1.setString(5, transportForm.getDoneby());
				pstmt1.setString(6, HelperClass.convertUIToDatabase(transportForm.getFc()));
				pstmt1.setString(7, HelperClass.convertUIToDatabase(transportForm.getPermitvalidity()));
				pstmt1.setString(8, transportForm.getInsurancefile());
				pstmt1.setString(9, createUser);
				
				
				
		      /*  pstmt.setString(7, createUser);*/

				count = pstmt1.executeUpdate();
				
				System.out.println(transportForm.getDriverCode().length());
				
				if (transportForm.getDriverCode() == ""|| transportForm.getDriverCode() == null||transportForm.getDriverCode().length()==0) 
				
				{
					
					
				}
				
				else
				{
					


					updatepstmt2 = connection
							.prepareStatement(TransportUtilConstants.MAP_DRIVER_VEHICLE_INSERTING);
					
					updatepstmt2.setString(1, transportForm.getDriverCode());
					updatepstmt2.setString(2, vehiclecode);
					updatepstmt2.setString(3, createUser);
					System.out.println("DIRverCOde in MAP_DRIVER_VEHICLE_INSERTING:  "+updatepstmt2);
					count=updatepstmt2.executeUpdate();
					
					pstmt3 = connection
							.prepareStatement(TransportUtilConstants.MAP_DRIVER_VEHICLE_ROUTE_MAPPING);
					
					pstmt3.setString(1, transportForm.getRoutecodeid());
					pstmt3.setString(2, vehiclecode);
					pstmt3.setString(3, createUser);
					pstmt3.executeUpdate();
					System.out.println("DIRverCOde in MAP_DRIVER_VEHICLE_INSERTING:  "+pstmt3);
					
				}
				if (count > 0) {
					status = "save";
				}
			} 
			
			else {
				System.out.println("else updTE");
				
				System.out.println("In DIOMPL RC file (update):::  "+transportForm.getRcfile());
				
				updatepstmt = connection.prepareStatement(TransportUtilConstants.UPDATE_VEHICLE_DETAILS);

				
				updatepstmt.setString(1, transportForm.getVehicleregno());
				updatepstmt.setString(2, transportForm.getVehiclename());
				updatepstmt.setString(3, transportForm.getEnginenumber());
				updatepstmt.setString(4, transportForm.getChassisno());
				updatepstmt.setString(5, transportForm.getVehicletype());
				updatepstmt.setString(6, HelperClass.convertUIToDatabase(transportForm.getTaxpaid()));
				updatepstmt.setString(7, HelperClass.convertUIToDatabase(transportForm.getTaxexpirydate()));
				updatepstmt.setString(8, HelperClass.convertUIToDatabase(transportForm.getPollution()));
				updatepstmt.setString(9, transportForm.getRcfile());
				updatepstmt.setTimestamp(10, HelperClass.getCurrentTimestamp());
				updatepstmt.setString(11, createUser);
				updatepstmt.setString(12, transportForm.getUpdateVehicleCode());
				System.out.println("Vehicle Update: "+updatepstmt);
				count = updatepstmt.executeUpdate();

				updatepstmt = connection.prepareStatement(TransportUtilConstants.UPDATE_VEHICLE_INSURANCE_DETAILS);
		
				
				updatepstmt.setString(1, transportForm.getCompanyname());
				updatepstmt.setString(2, HelperClass.convertUIToDatabase(transportForm.getIssueddate()));
				updatepstmt.setString(3, HelperClass.convertUIToDatabase(transportForm.getExpirydate()));
				updatepstmt.setString(4, transportForm.getDoneby());
				updatepstmt.setString(5, HelperClass.convertUIToDatabase(transportForm.getFc()));
				updatepstmt.setString(6, HelperClass.convertUIToDatabase(transportForm.getPermitvalidity()));
				updatepstmt.setString(7, transportForm.getInsurancefile());
				updatepstmt.setString(8, createUser);
				updatepstmt.setTimestamp(9, HelperClass.getCurrentTimestamp());
				updatepstmt.setString(10, transportForm.getUpdateVehicleCode());
				
				System.out.println("Insurance Update: "+updatepstmt);
				count = updatepstmt.executeUpdate();

				if (transportForm.getDriverCode() != ""	|| transportForm.getDriverCode() != null) {

					pstmt2 = connection
							.prepareStatement(TransportUtilConstants.CHECKING_DRIVER);

					pstmt2.setString(1, transportForm.getDriverCode());

					ResultSet rst = pstmt2.executeQuery();
					int count1 = 0;
					while (rst.next()) {
						count1 = rst.getInt(1);
					}

					if (count1 > 0) {
						updatepstmt2 = connection
								.prepareStatement(TransportUtilConstants.MAP_DRIVER_VEHICLE_WHILE_UPDATE);
						updatepstmt2
								.setString(1, transportForm.getDriverCode());
						updatepstmt2.setString(2,
								transportForm.getUpdateVehicleCode());
						updatepstmt2.setString(3, createUser);
						updatepstmt2.setString(4,
								transportForm.getUpdateVehicleCode());

						updatepstmt2.executeUpdate();
					} else {
						updatepstmt2 = connection
								.prepareStatement(TransportUtilConstants.MAP_DRIVER_VEHICLE_INSERTING);
						updatepstmt2
								.setString(1, transportForm.getDriverCode());
						updatepstmt2.setString(2,
								transportForm.getUpdateVehicleCode());
						updatepstmt2.setString(3, createUser);
						updatepstmt2.executeUpdate();
					}

				}
				pstmt4 = connection
						.prepareStatement(TransportUtilConstants.CHECK_VEHICLE_MAPPING);
				pstmt4.setString(1, transportForm.getUpdateVehicleCode());
				ResultSet rs = pstmt4.executeQuery();
				int cou = 0;
				while (rs.next()) {
					cou = rs.getInt(1);
				}
				if (cou > 0) {
					pstmt3 = connection
							.prepareStatement(TransportUtilConstants.MAP_DRIVER_VEHICLE_ROUTE_MAPPING_UPDATING);
					pstmt3.setString(1, transportForm.getRoutecodeid());
					pstmt3.setString(2, transportForm.getUpdateVehicleCode());
					pstmt3.setString(3, createUser);
					pstmt3.setString(4, transportForm.getUpdateVehicleCode());
					pstmt3.executeUpdate();
				} else {
					pstmt3 = connection
							.prepareStatement(TransportUtilConstants.MAP_DRIVER_VEHICLE_ROUTE_MAPPING);
					pstmt3.setString(1, transportForm.getRoutecodeid());
					pstmt3.setString(2, transportForm.getUpdateVehicleCode());
					pstmt3.setString(3, createUser);
					pstmt3.executeUpdate();
				}
				if (count > 0) {
					status = "update";
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
				if (updatepstmt  != null && !updatepstmt .isClosed()) {
					updatepstmt .close();
				}
				if (updatepstmt2  != null && !updatepstmt2 .isClosed()) {
					updatepstmt2 .close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
				}
				if (pstmt2 != null && !pstmt2.isClosed()) {
					pstmt2.close();
				}
				if (pstmt3 != null && !pstmt3.isClosed()) {
					pstmt3.close();
				}
				if (pstmt4 != null && !pstmt4.isClosed()) {
					pstmt4.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (connection  != null && !connection .isClosed()) {

					connection .close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : saveVehicleDetails  Ending");
		
		System.out.println("status"+status);
		return status;
	}

	public boolean checkingVehicleInsuranceDate(VehicleDetailsVO vehiclecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : checkingVehicleInsuranceDate Starting");
		int count = 0;
		boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection
					.prepareStatement(TransportUtilConstants.CHECKING_VEHICLE_INSURANCE_DATE);
			pstmt.setString(1, vehiclecode.getVehiclecode());
			pstmt.setString(2, HelperClass.convertUIToDatabase(vehiclecode
					.getIssueddate()));
			pstmt.setString(3, HelperClass.convertUIToDatabase(vehiclecode
					.getExpirydate()));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				status = true;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : checkingVehicleInsuranceDate  Ending");
		return status;
	}

	public VehicleDetailsVO getSingleVehicleDetails(String vehiclecode) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getSingleVehicleDetails Starting");
		int count = 0;
		boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		VehicleDetailsVO obj = null;
		try {
			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection
					.prepareStatement(TransportUtilConstants.GET_VEHCILE_INSURANCE_DETAILS);
			pstmt.setString(1, vehiclecode);
			
			
			System.out.println("edit vehicle pstmt::"+pstmt);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				obj = new VehicleDetailsVO();
				obj.setVehiclecode(rs.getString("VehicleCode"));
				obj.setVehicleregno(rs.getString("Vehicle_Reg_No"));
				obj.setVehiclename(rs.getString("VehicleName"));
				obj.setEnginenumber(rs.getString("Engine_Number"));
				obj.setChassisno(rs.getString("Chassis_No"));
				obj.setVehicletype(rs.getString("VehicleType"));
				obj.setTaxpaid(HelperClass.convertDatabaseToUI(rs
						.getString("Tax_Paid")));
				obj.setTaxexpirydate(HelperClass.convertDatabaseToUI(rs
						.getString("TaxExpiryDate")));
				obj.setPollution(HelperClass.convertDatabaseToUI(rs
						.getString("Pollution")));
				obj.setCompanyname(rs.getString("CompanyName"));
				obj.setIssueddate(HelperClass.convertDatabaseToUI(rs
						.getString("IssuedDate")));
				obj.setExpirydate(HelperClass.convertDatabaseToUI(rs
						.getString("ExpiryDate")));
				obj.setDoneby(rs.getString("doneby"));
				obj.setFc(HelperClass.convertDatabaseToUI(rs
						.getString("Fc")));
				obj.setPermitvalidity(HelperClass.convertDatabaseToUI(rs
						.getString("Permit_validity")));
				
				obj.setRcfile(rs.getString("RCFileUpload"));
				obj.setInsurancefile(rs.getString("InsuranceFleUpload"));
				
				obj.setStatus("update");

				System.out.println(""+rs.getString("RCFileUpload"));
				System.out.println(""+rs.getString("InsuranceFleUpload"));
			}
/*
			System.out.println("regditration no in dao "
					+ obj.getVehicleregno());
			*/
			

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getSingleVehicleDetails  Ending");
		return obj;
	}

	@SuppressWarnings("resource")
	public String deleteVehicleDetails(String[] vehiclecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : deleteVehicleDetails Starting");
		int count = 0;
		
		String status = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		VehicleDetailsVO obj = null;
		try {
			
			for(int i=0;i<vehiclecode.length;i++){
			connection = JDBCConnection.getSeparateConnection();
			
				pstmt = connection
						.prepareStatement(TransportUtilConstants.DELETE_VEHICLE_DETAILS);
				pstmt.setString(1, vehiclecode[i]);
				
				System.out.println("vehicle code while Deleting"+pstmt);
				count = pstmt.executeUpdate();

				pstmt = connection.prepareStatement(TransportUtilConstants.DELETE_INSURANCE_DETAILS);
				pstmt.setString(1, vehiclecode[i]);
				count = pstmt.executeUpdate();
				
				
				pstmt = connection.prepareStatement(TransportUtilConstants.DELETE_DRIVER_VEHICLE_MAPPING);
				pstmt.setString(1, vehiclecode[i]);
				count = pstmt.executeUpdate();
				
				
				pstmt = connection.prepareStatement(TransportUtilConstants.DELETE_VEHICLE_ROUTE_MAPPIG);
				pstmt.setString(1, vehiclecode[i]);
				count = pstmt.executeUpdate();
				
				if (count > 0 ) {
					
					 status = MessageConstants.VEHICLE_DELETE_SUCCESS;
				}
				else{
					status = MessageConstants.VEHICLE_DELETE_FAIL;
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
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : deleteVehicleDetails  Ending");
		return status;
	}

	public boolean registernumberValidation(String vehicleregno) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : registernumberValidation Starting");
		int count = 0;
		boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection
					.prepareStatement(TransportUtilConstants.VALIDATE_VEHICLE_REG_NO);

			pstmt.setString(1, vehicleregno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				status = true;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : registernumberValidation  Ending");
		return status;
	}

	public boolean updateregisternumberValidation(String vehicleregno,
			String vehicleCode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updateregisternumberValidation Starting");
		int count = 0;
		boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.VALIDATE_UPDATE_VEHICLE_REG_NO);

			pstmt.setString(1, vehicleregno);
			pstmt.setString(2, vehicleCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				status = true;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updateregisternumberValidation  Ending");

		return status;
	}

	public boolean chassisnovalidationvalidation(String chassisno) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : chassisnovalidationvalidation Starting");
		boolean status = false;
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {

			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection
					.prepareStatement(TransportUtilConstants.VALIDATE_VEHICLE_CHASSIS_NO);

			pstmt.setString(1, chassisno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				status = true;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : chassisnovalidationvalidation  Ending");
		return status;
	}

	public boolean updatechassisnovalidation(String chassisno,
			String vehicleCode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updatechassisnovalidation Starting");
		int count = 0;
		boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.VALIDATE_UPDATE_VEHICLE_CHASSIS_NO);

			pstmt.setString(1, chassisno);
			pstmt.setString(2, vehicleCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				status = true;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updatechassisnovalidation  Ending");
		return status;
	}

	public ArrayList<VehicleDetailsVO> searchvehicledetails(String searchTerm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : searchvehicledetails Starting");
		ArrayList<VehicleDetailsVO> getvehiclelist = new ArrayList<VehicleDetailsVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		int sno = 0;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.SEARCH_VEHICLE_DETAILS);
			pstmt.setString(1, "%" + searchTerm + "%");
			pstmt.setString(2, "%" + searchTerm + "%");
			pstmt.setString(3, "%" + searchTerm + "%");
			pstmt.setString(4, "%" + searchTerm + "%");
			pstmt.setString(5, "%" + searchTerm + "%");
		
		
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sno++;
				VehicleDetailsVO detailsVO = new VehicleDetailsVO();
				detailsVO.setSno(String.valueOf(sno));
				detailsVO.setVehiclecode(rs.getString("VehicleCode"));
				detailsVO.setVehicleregno(rs.getString("Vehicle_Reg_No"));
				detailsVO.setEnginenumber(rs.getString("Engine_number"));
				detailsVO.setVehiclename(rs.getString("VehicleName"));
				detailsVO.setVehicletype(rs.getString("VehicleType"));
				detailsVO.setTaxpaid(HelperClass
						.convertDatabaseToUI(rs.getString("Tax_Paid")));
				getvehiclelist.add(detailsVO);
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
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : searchvehicledetails  Ending");
		return getvehiclelist;
	}

	public boolean checkforduplicateAddTime(VehicleDetailsVO obj) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updatechassisnovalidation Starting");
		int count = 0;
		boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.CHECK_FOR_DUPLICATE_ADD_TIME);
			System.out.println("simple date"+pstmt);
			pstmt.setString(1, obj.getVehicleregno());
			pstmt.setString(2, obj.getVehiclename());
			pstmt.setString(3, obj.getEnginenumber());
			pstmt.setString(4, obj.getChassisno());
			pstmt.setString(5, obj.getVehicletype());
			pstmt.setString(6, HelperClass.convertUIToDatabase(obj.getTaxpaid()));
			pstmt.setString(7,	HelperClass.convertUIToDatabase(obj.getPollution()));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				status = true;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updatechassisnovalidation  Ending");
		return status;

	}

	public boolean checkforduplicateUpdateTime(VehicleDetailsVO obj) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : checkforduplicateUpdateTime Starting");
		int count = 0;
		boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			System.out.println("tax paid "+obj.getTaxpaid());
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.CHECK_FOR_DUPLICATE_UPDATE_TIME);
			//Vehicle_Reg_No=? and VehicleName=? and VehicleType=? and Engine_number=? and Chassis_No=? and VehicleType=?  and Tax_Paid=? 
			//and Pollution=? and VehicleCode!=?
			pstmt.setString(1, obj.getVehicleregno());
			pstmt.setString(2, obj.getVehiclename());
			pstmt.setString(3, obj.getEnginenumber());
			pstmt.setString(4, obj.getChassisno());
			pstmt.setString(5, obj.getVehicletype());
			pstmt.setString(6,	HelperClass.convertUIToDatabase(obj.getTaxpaid()));
			pstmt.setString(7,	HelperClass.convertUIToDatabase(obj.getPollution()));
			pstmt.setString(8, obj.getVehiclecode());
			System.out.println("check for duplicate data"+pstmt);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				status = true;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : checkforduplicateUpdateTime  Ending");
		return status;

	}

	// route master start
	public List<TransportPojo> getTransportMasterDaoDetails(String accyear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportSetupDaoImpl: getTransportMasterDaoDetails Starting");
		ArrayList<TransportPojo> tpMasterPojoList = new ArrayList<TransportPojo>();
		PreparedStatement pstmt = null;
		TransportPojo obj = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection.prepareStatement(TransportUtilConstants.ROUTE_ALLLISTDATAS);
			pstmt.setString(1, accyear);
		
			if (pstmt != null) {
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {

					obj = new TransportPojo();
					obj.setRouteNo(resultSet.getString("Route_No"));
					obj.setRouteName(resultSet.getString("RouteName"));
					obj.setRouteCode(resultSet.getString("RouteCode"));
					obj.setStartTime(resultSet.getString("Start_Time"));
					obj.setEndTime(resultSet.getString("End_Time"));
					obj.setTotalStops(resultSet.getString("Total_Stops"));
					obj.setTotalDistance(resultSet.getString("TotalDistance"));
					obj.setHalttime(resultSet.getString("HaltTime"));
					//obj.setDestination(resultSet.getString("Destination"));

					tpMasterPojoList.add(obj);
				}
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
				+ " Control in FeeSetupDaoImpl: getRouteMasterDaoDetails Ending");
		return tpMasterPojoList;
	}

	public String insertRouteMasterDetails(TransportPojo tpPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl: insertRouteMasterDetails Starting");
		String result_Status = null;

		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection connection = null;
		PreparedStatement pstmt1 = null;
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		try {

			connection = JDBCConnection.getSeparateConnection();

			
			if(tpPojo.getHrouteCode().equalsIgnoreCase("")) {
				if ("NULL".equalsIgnoreCase(tpPojo.getCheck())) {

					conn = JDBCConnection.getSeparateConnection();
					pstmt1 = conn.prepareStatement(TransportUtilConstants.TRANSPORT_ROUTECHECK);
					pstmt1.setString(1, tpPojo.getRouteNo());
					rs = pstmt1.executeQuery();

					while (rs.next()) {
						count = rs.getInt(1);
					}
					if (count == 0) {
					
						pstmt = connection
								.prepareStatement(TransportUtilConstants.ROUTE_INSERT);
						pstmt.setString(1, tpPojo.getRouteCode());
						pstmt.setString(2, tpPojo.getRouteName());
						pstmt.setString(3, tpPojo.getRouteNo());
						pstmt.setString(4, tpPojo.getRouteLogicName());
						/*pstmt.setString(5, tpPojo.getDestination());*/
						pstmt.setString(5, tpPojo.getHalttime());
						pstmt.setString(6, tpPojo.getStartTime());
						pstmt.setString(7, tpPojo.getEndTime());
						pstmt.setString(8, tpPojo.getTotalStops());
						pstmt.setString(9, tpPojo.getTotalDistance());
						pstmt.setTimestamp(10, HelperClass.getCurrentTimestamp());
						pstmt.setString(11, tpPojo.getCreateUser());
						result1 = pstmt.executeUpdate();
						
						if (result1 > 0) {
							
							
							for(int j=0;j< tpPojo.getStagesidArray().length;j++){
								
								PreparedStatement pstmt2=connection.prepareStatement(TransportUtilConstants.ROUTE_INSERT_STAGE);
								pstmt2.setString(1, tpPojo.getRouteCode());
								pstmt2.setString(2, tpPojo.getStagesidArray()[j]);
								pstmt2.setString(3, tpPojo.getCreateUser());
								pstmt2.setString(4, tpPojo.getAccyear());
								int stagers=pstmt2.executeUpdate();	
							}	
							result_Status = MessageConstants.SUCCESS;
						} else {
							result_Status = MessageConstants.FAILD;
						}
						
						
						
					}
				

				} else {

					pstmt = connection
							.prepareStatement(TransportUtilConstants.ROUTE_UPDATEROUTEMASTER);
					pstmt.setString(1, tpPojo.getRouteName());
					pstmt.setString(2, tpPojo.getRouteNo());

					pstmt.setString(3, tpPojo.getRouteLogicName());
					pstmt.setString(4, tpPojo.getHalttime());
					pstmt.setString(5, tpPojo.getStartTime());
					pstmt.setString(6, tpPojo.getEndTime());
					pstmt.setString(7, tpPojo.getTotalStops());
					pstmt.setString(8, tpPojo.getTotalDistance());
					pstmt.setTimestamp(9, HelperClass.getCurrentTimestamp());
					pstmt.setString(10, tpPojo.getCreateUser());
					pstmt.setString(11, tpPojo.getRouteCode());
					result1 = pstmt.executeUpdate();
					if (result1 > 0) {
						PreparedStatement pstmt3=connection.prepareStatement(TransportUtilConstants.DELETE_INSERT_STAGE);
						pstmt3.setString(1, tpPojo.getRouteCode());
						pstmt3.setString(2, tpPojo.getAccyear());
						System.out.println("now=="+pstmt3);
						int rtd=pstmt3.executeUpdate();
						if(rtd>0){
						for(int j=0;j< tpPojo.getStagesidArray().length;j++){
							
							
							PreparedStatement pstmt2=connection.prepareStatement(TransportUtilConstants.ROUTE_INSERT_STAGE);
							pstmt2.setString(1, tpPojo.getRouteCode());
							pstmt2.setString(2, tpPojo.getStagesidArray()[j]);
							pstmt2.setString(3, tpPojo.getCreateUser());
							pstmt2.setString(4, tpPojo.getAccyear());
							System.out.println("noeeew=="+pstmt2);
							int stagers=pstmt2.executeUpdate();	
							
						}
						}
						result_Status = MessageConstants.SUCCESS1;
					} else {
						result_Status = MessageConstants.FAILD;
					}

				}
			}
			else {
				PreparedStatement pstmt3=connection.prepareStatement(TransportUtilConstants.DELETE_INSERT_STAGE);
				pstmt3.setString(1, tpPojo.getRouteCode());
				pstmt3.setString(2, tpPojo.getAccyear());
				int rtd=pstmt3.executeUpdate();
			
				System.out.println("I m here");
				
				for(int j=0;j< tpPojo.getStagesidArray().length;j++){
					
					System.out.println("I m here in loop");
					PreparedStatement pstmt2=connection.prepareStatement(TransportUtilConstants.ROUTE_INSERT_STAGE);
					pstmt2.setString(1, tpPojo.getRouteCode());
					pstmt2.setString(2, tpPojo.getStagesidArray()[j]);
					pstmt2.setString(3, tpPojo.getCreateUser());
					pstmt2.setString(4, tpPojo.getAccyear());
					int stagers=pstmt2.executeUpdate();	
					
				}
				
				result_Status = MessageConstants.SUCCESS1;
			}
			
			

		} catch (SQLException sqlExp) {
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
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
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
				+ " Control in TransportDaoImpl: insertRouteMasterDetails Ending");

		return result_Status;
	}

	@Override
	public int getTpRouteCheckDao(String tpName) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RouteSetupDaoImpl: getRouteCheckDao Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();

			pstmt = (PreparedStatement) JDBCConnection
					.getStatement(TransportUtilConstants.TRANSPORT_ROUTECHECK);
			pstmt.setString(1, tpName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException sqlExp) {
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
				+ " Control in FeeSetupDaoImpl: getFeeNameCheckDao Ending");
		return count;
	}

	public String removeRouteMasterDetails(String[] routecode) {
		PreparedStatement pstmt = null;
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl: removeRouteMasterDetails Starting");
		
		PreparedStatement route_pstmt = null;
		
		ResultSet rs_deleteroute = null;
		
		
		
		
		int count1 = 0;
		int count2 = 0;
		
		String status = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			for(int i=0;i<routecode.length;i++){
				
				connection = JDBCConnection.getSeparateConnection();

					route_pstmt = connection
							.prepareStatement(SQLUtilConstants.CHECK_ROUTE_MAP);

					route_pstmt.setString(1, routecode[i]);
					rs_deleteroute = route_pstmt.executeQuery();

					while (rs_deleteroute.next()) {

						count1 = rs_deleteroute.getInt("routename");

						
					}
					rs_deleteroute.close();
					route_pstmt.close();
			if(count1 == 0){
			pstmt = connection
					.prepareStatement(TransportUtilConstants.ROUTE_REMOVEFEE);
			pstmt.setString(1, routecode[i]);
			count1 = pstmt.executeUpdate();

			
			PreparedStatement pstmt3=connection.prepareStatement(TransportUtilConstants.DELETE_INSERT_STAGE);
			pstmt3.setString(1, routecode[i]);
			count2=pstmt3.executeUpdate();
			}
			if (count1 > 0 && count2>0) {
				
				 status = MessageConstants.ROUTE_DELETE_SUCCESS;
			}
			else{
				status = MessageConstants.ROUTE_DELETE_FAIL;
			}
		}
		

	}  catch (SQLException sqlExp) {
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
				if (route_pstmt  != null && !route_pstmt .isClosed()) {
					route_pstmt .close();
				}
				if (rs_deleteroute   != null && !rs_deleteroute  .isClosed()) {
					rs_deleteroute  .close();
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
				+ " Control in TransportDaoImpl: removeRouteMasterDetails Ending");
		return status;
	}


	
	public boolean addRoute(TransportForm routeForm) {
		boolean status = false;

		PreparedStatement pstm = null;
		Connection connection = null;
		int result = 0;
		try {

			connection = JDBCConnection.getSeparateConnection();

			if (routeForm.getRouteCode() != null) {

				String noArray[] = routeForm.getStopNoArray().trim().split(",");
				String nameArray[] = routeForm.getStopNameArray().trim()
						.split(",");
				String arrivalArray[] = routeForm.getStopArrivalTimeArray()
						.trim().split(",");
				String haltArray[] = routeForm.getStopHaltTimeArray().trim()
						.split(",");
				String deptArray[] = routeForm.getStopDepartureTimeArray()
						.trim().split(",");
				String distArray[] = routeForm.getStopDistanceArray()
						.split(",");

				for (int i = 0; i < noArray.length; i++) {

					pstm = connection
							.prepareStatement(TransportUtilConstants.ADD_ROUTE_STOPDETAILS);
					pstm.setString(
							1,
							IDGenerator.getPrimaryKeyID(
									"transport_stopsdetails").trim());
					pstm.setString(2, routeForm.getRouteCode());

					pstm.setString(3, nameArray[i].trim());
					pstm.setString(4, arrivalArray[i].trim());
					pstm.setString(5, haltArray[i].trim());
					pstm.setString(6, deptArray[i].trim());
					pstm.setDouble(7,
							Double.parseDouble(distArray[i].toString()));
					result = pstm.executeUpdate();

				}

			}

		}

		catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null && (!pstm.isClosed())) {
					pstm.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		if (result == 1) {
			status = true;
		} else {
			status = false;
		}

		return status;

	}

	public boolean checkrouteNo(TransportPojo Pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : checkrouteNo Starting");
		int count = 0;
		boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			if(Pojo.getAccyear().equalsIgnoreCase("")) {
				if ("NULL".equalsIgnoreCase((Pojo.getRouteCode()))) {
		connection = JDBCConnection.getSeparateConnection();

		pstmt = connection.prepareStatement(TransportUtilConstants.TRANSPORT_ROUTECHECK);

		pstmt.setString(1, Pojo.getRouteNo());

		rs = pstmt.executeQuery();

		while (rs.next()) {
			count = rs.getInt(1);
		}
		if (count > 0) {
			status = true;
		}
				} else {
		connection = JDBCConnection.getSeparateConnection();

		pstmt = connection
				.prepareStatement(TransportUtilConstants.TRANSPORT_ROUTECHECK_WHILE_UPDATING);

		pstmt.setString(1, Pojo.getRouteNo());
		pstmt.setString(2, Pojo.getRouteCode());
		System.out.println(pstmt);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			count = rs.getInt(1);
		}
		if (count > 0) {
			status = true;
		}
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
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : checkrouteNo  Ending");
		return status;
	}

	public TransportVo editRouteMasterDetails(String routecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : editRouteMasterDetails Starting");
		TransportVo obj = new TransportVo();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection.prepareStatement(TransportUtilConstants.ETID_ROUTE_ALLLISTDATAS);

			System.out.println("istiyak Details"+routecode);
			
			
			pstmt.setString(1, routecode);
			System.out.println("istiyak Details"+pstmt);
			if (pstmt != null) {
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {
					obj.setRouteName(resultSet.getString("RouteName"));
					obj.setRouteNo(resultSet.getString("Route_No"));
					obj.setRouteCode(resultSet.getString("RouteCode"));
					obj.setRouteLogicName(resultSet.getString("Route_logical_name"));
					/*obj.setDestination((resultSet.getString("Destination")));*/
					obj.setHalttime(resultSet.getString("HaltTime"));
					obj.setStratTime(resultSet.getString("Start_Time"));
					obj.setEndTime(resultSet.getString("End_Time"));
					obj.setTotalStops(resultSet.getString("Total_Stops"));
					obj.setTotalDistance(resultSet.getString("TotalDistance"));
					
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
				+ " Control in TransportDaoImpl : editRouteMasterDetails  Ending");

		return obj;
	}

	public List<TransportVo> searchDetails(String SearchName,String searchYear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : searchDetails Starting");
		ArrayList<TransportVo> list = new ArrayList<TransportVo>();
		PreparedStatement pstmt = null;
		
		ResultSet resultSet = null;
		Connection connection = null;
		int sno = 0;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.ROUTE_SEARCHLISTDATAS);
		
			
			pstmt.setString(1, "%" + SearchName + "%");
			pstmt.setString(2, "%" + SearchName + "%");
			pstmt.setString(3, "%" + SearchName + "%");
			pstmt.setString(4, "%" + SearchName + "%");
			pstmt.setString(5, searchYear);
			
			if (pstmt != null) {
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {
					sno++;
					
					TransportVo obj = new TransportVo();
					obj = new TransportVo();
					obj.setRouteNo(resultSet.getString("Route_No"));
					obj.setRouteName(resultSet.getString("RouteName"));
					obj.setRouteCode(resultSet.getString("RouteCode"));
					obj.setRouteLogicName(resultSet.getString("Route_logical_name"));
					obj.setRouteType(resultSet.getString("RouteType"));
					obj.setCostPerPerson(resultSet.getString("Cost_per_person"));
					obj.setStratTime(resultSet.getString("Start_Time"));
					obj.setEndTime(resultSet.getString("End_Time"));
					obj.setTotalStops(resultSet.getString("Total_Stops"));
					obj.setTotalDistance(resultSet.getString("TotalDistance"));
					obj.setHalttime(resultSet.getString("HaltTime"));
					list.add(obj);
				}
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
				+ " Control in TransportDaoImpl : searchDetails  Ending");

		return list;

	}

	public ArrayList<DriverMsaterListVo> getdriverListDao() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getdriverListDao Starting");

		System.out.println("driver dao");

		ArrayList<DriverMsaterListVo> driverlist = new ArrayList<DriverMsaterListVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TransportUtilConstants.GET_DRIVER_LIST);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DriverMsaterListVo listvo = new DriverMsaterListVo();
				String IssuedDate = "";
				String validity = "";
				String dlno = "";

				if (rs.getString("DLIssuedDate") != null
						&& !rs.getString("DLIssuedDate").isEmpty()) {
					IssuedDate = HelperClass.convertDatabaseToUI(rs
							.getString("DLIssuedDate"));

				}

				if (rs.getString("DLExpirayDate") != null
						&& !rs.getString("DLExpirayDate").isEmpty()) {
					validity = HelperClass.convertDatabaseToUI(rs
							.getString("DLExpirayDate"));

				}

				if (rs.getString("DLNo") != null
						&& !rs.getString("DLNo").isEmpty()) {
					dlno = rs.getString("DLNo");

				}

				listvo.setType(rs.getString("type"));
				listvo.setDriverName(rs.getString("Name"));
				listvo.setDateofBirth(HelperClass.convertDatabaseToUI(rs
						.getString("DOB")));
				listvo.setDateofJoin(HelperClass.convertDatabaseToUI(rs
						.getString("DOJ")));
				listvo.setMobile(rs.getString("MobileNo"));
				listvo.setDrivingliecenseNo(dlno);
				listvo.setDriverCode(rs.getString("DriverCode"));
				listvo.setFather_name(rs.getString("FatherName"));
				listvo.setEmerg_contact(rs.getString("EmergencyContactNo"));
				listvo.setAddress(rs.getString("Address"));
				listvo.setAge(rs.getString("Age"));
				listvo.setGender(rs.getString("Gender"));
				listvo.setDl_issued_date(IssuedDate);
				listvo.setDl_validity(validity);
				listvo.setLicense(rs.getString("LicencetoDrive"));
				listvo.setExperience(rs.getString("Experience"));
				driverlist.add(listvo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

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
				+ " Control in TransportDaoImpl : getdriverListDao  Ending");
		return driverlist;
	}

/*	public int addDriverDao(DriverMsaterListVo drivervo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : addDriverDao Starting");

		PreparedStatement pstmt = null;
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		Connection connection = null;
		int result = 0;
		int rs = 0;
		try {

			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.ADD_DRIVER);

			pstmt.setString(1, IDGenerator.getPrimaryKeyID("transport_driver"));
			pstmt.setString(2, "driver");
			pstmt.setString(3, drivervo.getDriverName());
			pstmt.setString(4, drivervo.getFather_name());
			pstmt.setString(5,
					HelperClass.convertUIToDatabase(drivervo.getDateofBirth()));
			pstmt.setString(6, drivervo.getMobile());
			pstmt.setString(7, drivervo.getEmerg_contact());
			pstmt.setString(8, drivervo.getAddress());
			pstmt.setString(9,
					HelperClass.convertUIToDatabase(drivervo.getDateofJoin()));
			pstmt.setString(10, drivervo.getAge());
			pstmt.setString(11, drivervo.getGender());
			pstmt.setString(12, drivervo.getDrivingliecenseNo());
			pstmt.setString(13, HelperClass.convertUIToDatabase(drivervo
					.getDl_issued_date()));
			pstmt.setString(14,
					HelperClass.convertUIToDatabase(drivervo.getDl_validity()));
			pstmt.setString(15, drivervo.getLicense());
			pstmt.setTimestamp(16, time_stamp);
			pstmt.setString(17, drivervo.getCreateUser());
			pstmt.setString(18, drivervo.getExperience());

			rs = pstmt.executeUpdate();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : addDriverDao  Ending");
		return result;
	}
*/
	public DriverMsaterListVo editDriverDao(DriverMsaterListVo drivervo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : editDriverDao Starting");

		DriverMsaterListVo driverobj = null;
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			driverobj = new DriverMsaterListVo();
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TransportUtilConstants.GET_DRIVER_DETAILS);

			pstmt.setString(1, drivervo.getDriverCode());
			rs = pstmt.executeQuery();

			while (rs.next()) {

				driverobj.setDriverCode(rs.getString("DriverCode"));
				driverobj.setDriverName(rs.getString("Name"));
				driverobj.setFather_name(rs.getString("FatherName"));
				driverobj.setDateofBirth(HelperClass.convertDatabaseToUI(rs
						.getString("DOB")));
				driverobj.setMobile(rs.getString("MobileNo"));
				driverobj.setEmerg_contact(rs.getString("EmergencyContactNo"));
				driverobj.setExperience(rs.getString("Experience"));
				driverobj.setAddress(rs.getString("Address"));
				driverobj.setDateofJoin(HelperClass.convertDatabaseToUI(rs
						.getString("DOJ")));
				driverobj.setAge(rs.getString("Age"));
				driverobj.setGender(rs.getString("Gender"));
				driverobj.setDrivingliecenseNo(rs.getString("DLNo"));
				driverobj.setDl_issued_date(HelperClass.convertDatabaseToUI(rs
						.getString("DLIssuedDate")));
				driverobj.setDl_validity(HelperClass.convertDatabaseToUI(rs
						.getString("DLExpirayDate")));
				driverobj.setLicense(rs.getString("LicencetoDrive"));
				driverobj.setLicensedrive(rs.getString("DrivingLicenceFile"));
				
				
			driverobj.setStatus("update");
				System.out.println("LicencetoDrive:::"+rs.getString("LicencetoDrive"));

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally {

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
				+ " Control in TransportDaoImpl : editDriverDao  Ending");

		return driverobj;
	}

	public String deleteDriverDao(String[] drivervo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : deleteDriverDao Starting");

		
		PreparedStatement dirver_pstmt = null;
		PreparedStatement pstmt = null;
		String check = null;
		int no=0;
		int streamcount = 0;
		//String drivecode = drivervo.getDriverCode();
		int count = 0;
		Connection conn = null;
		ResultSet rs_deletedriver = null;
		ResultSet rs = null;

		try {
			for(int i=0;i<drivervo.length;i++){
				
				conn = JDBCConnection.getSeparateConnection();

				dirver_pstmt = conn
						.prepareStatement("select count(*)driver from transport_driver_vehicle_mapping where DriverCode =? ");

				dirver_pstmt.setString(1, drivervo[i]);
				rs_deletedriver = dirver_pstmt.executeQuery();

				while (rs_deletedriver.next()) {

					streamcount = rs_deletedriver.getInt("driver");
					
				}
				rs_deletedriver.close();
				dirver_pstmt.close();
			
			if (streamcount == 0) {
				
				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn
						.prepareStatement(TransportUtilConstants.DELETE_DRIVER);
				pstmt.setString(1, drivervo[i]);

				streamcount = pstmt.executeUpdate();

				if (streamcount > 0) {

					check = MessageConstants.DELETE_SUCCESS_DRIVER;
				} else {
					check = MessageConstants.DELETE_FAIL_DRIVER;
				}
			} else {

				check = MessageConstants.DELETE_WARNING_DRIVER;

			}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (rs_deletedriver != null && !rs_deletedriver.isClosed()) {
					rs_deletedriver.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (dirver_pstmt != null && !dirver_pstmt.isClosed()) {
					dirver_pstmt.close();
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
				+ " Control in TransportDaoImpl : deleteDriverDao  Ending");

		return check;
	}

	public ArrayList<DriverMsaterListVo> searchDriverDao(String searchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : searchDriverDao Starting");

		ArrayList<DriverMsaterListVo> driverlist = new ArrayList<DriverMsaterListVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TransportUtilConstants.SEARCH_DRIVER_DETAILS);

			pstmt.setString(1, "%" + searchName + "%");
			pstmt.setString(2, "%" + searchName + "%");
			pstmt.setString(3, "%" + searchName + "%");
			pstmt.setString(4, "%" + searchName + "%");
			pstmt.setString(5, "%" + searchName + "%");
			pstmt.setString(6, "%" + searchName + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				sno++;
				DriverMsaterListVo driverobj = new DriverMsaterListVo();

				driverobj.setSno(String.valueOf(sno));
				 driverobj.setDriverCode(rs.getString("DriverCode"));
				driverobj.setDriverName(rs.getString("Name"));
				driverobj.setDateofJoin(rs.getString("DOJ"));
				driverobj.setMobile(rs.getString("MobileNo"));
				driverobj.setDrivingliecenseNo(rs.getString("DLNo"));
				driverobj.setDl_issued_date(rs.getString("DLIssuedDate"));
				driverobj.setDl_validity(rs.getString("DLExpirayDate"));

				driverlist.add(driverobj);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

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
				+ " Control in TransportDaoImpl : searchDriverDao  Ending");
		return driverlist;
	}

/*	public int updateDriverDao(DriverMsaterListVo drivervo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updateDriverDao Starting");

		PreparedStatement pstmt = null;
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		int result = 0;
		Connection connection = null;

		try {

			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.UPDATE_DRIVER);

			 pstmt.setString(1, drivervo.getType()); 
			pstmt.setString(1, drivervo.getDriverName());
			pstmt.setString(2, drivervo.getFather_name());
			pstmt.setString(3,
					HelperClass.convertUIToDatabase(drivervo.getDateofBirth()));
			pstmt.setString(4, drivervo.getMobile());
			pstmt.setString(5, drivervo.getEmerg_contact());
			pstmt.setString(6, drivervo.getAddress());
			pstmt.setString(7,
					HelperClass.convertUIToDatabase(drivervo.getDateofJoin()));
			pstmt.setString(8, drivervo.getAge());
			pstmt.setString(9, drivervo.getGender());
			pstmt.setString(10, drivervo.getDrivingliecenseNo());
			pstmt.setString(11, HelperClass.convertUIToDatabase(drivervo
					.getDl_issued_date()));
			pstmt.setString(12,
					HelperClass.convertUIToDatabase(drivervo.getDl_validity()));
			pstmt.setString(13, drivervo.getLicense());
			pstmt.setTimestamp(14, time_stamp);
			pstmt.setString(15, drivervo.getCreateUser());
			pstmt.setString(16, drivervo.getExperience());
			pstmt.setString(17, drivervo.getDriverCode());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updateDriverDao  Ending");
		return result;
	}
*/
	public boolean validateDriverDao(DriverMsaterListVo drivervo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : validateDriverDao Starting");

		boolean driver_validate = false;

		int count = 0;
		PreparedStatement pscheckExamName = null;
		ResultSet rsCheckExamName = null;
		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();

			pscheckExamName = conn
					.prepareStatement(TransportUtilConstants.VALIDATE_DRIVER);

			pscheckExamName.setString(1, drivervo.getDriverName());
			pscheckExamName.setString(2, drivervo.getDateofBirth());
			pscheckExamName.setString(3, drivervo.getMobile());
			pscheckExamName.setString(4, drivervo.getAddress());
			pscheckExamName.setString(5, drivervo.getDateofJoin());

			rsCheckExamName = pscheckExamName.executeQuery();

			while (rsCheckExamName.next()) {

				count = rsCheckExamName.getInt(1);

			}

			if (count > 0) {

				driver_validate = true;

			} else {

				driver_validate = false;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {
				if (pscheckExamName != null && !pscheckExamName.isClosed()) {
					pscheckExamName.close();
				}
				if (rsCheckExamName != null && !rsCheckExamName.isClosed()) {
					rsCheckExamName.close();
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
				+ " Control in TransportDaoImpl : validateDriverDao  Ending");

		return driver_validate;
	}

	// for driver license validation//
	
	public boolean validateLicenseDao(DriverMsaterListVo drivervo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : validateDriverDao Starting");

		boolean license_validate = false;

		int count = 0;
		PreparedStatement pscheckExamName = null;
		ResultSet rsCheckExamName = null;
		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();

			pscheckExamName = conn
					.prepareStatement(TransportUtilConstants.VALIDATE_LICENSE);

			pscheckExamName.setString(1, drivervo.getDrivingliecenseNo());
			

			rsCheckExamName = pscheckExamName.executeQuery();

			while (rsCheckExamName.next()) {

				count = rsCheckExamName.getInt(1);

			}

			if (count > 0) {

				license_validate = true;

			} else {

				license_validate = false;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {
				if (rsCheckExamName != null && !rsCheckExamName.isClosed()) {
					rsCheckExamName.close();
				}
				if (pscheckExamName != null && !pscheckExamName.isClosed()) {
					pscheckExamName.close();
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
				+ " Control in TransportDaoImpl : validateDriverDao  Ending");

		return license_validate;
	}
	
	
	public ArrayList<DriverMsaterListVo> getDriverNamesDetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getDriverNamesDetails Starting");
		ArrayList<DriverMsaterListVo> driverlist = new ArrayList<DriverMsaterListVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(TransportUtilConstants.GET_UNMAPPED_DRIVER_DETAILS1);
			System.out.println("pstmt is "+pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DriverMsaterListVo listVo = new DriverMsaterListVo();

				listVo.setDriverCode(rs.getString("DriverCode"));
				listVo.setDriverName(rs.getString("Name"));
				driverlist.add(listVo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
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
				+ " Control in TransportDaoImpl : getDriverNamesDetails  Ending");
		return driverlist;

	}

	public ArrayList<DriverMsaterListVo> getDriverEntireDetails(String driverid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getDriverEntireDetails Starting");
		ArrayList<DriverMsaterListVo> driverlist = new ArrayList<DriverMsaterListVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TransportUtilConstants.GET_DRIVER_DETAILS_BY_ID);
			
			System.out.println("details of driver"+pstmt);
			
			
			pstmt.setString(1, driverid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DriverMsaterListVo listVo = new DriverMsaterListVo();

				listVo.setDriverCode(rs.getString("DriverCode"));
				listVo.setDriverName(rs.getString("Name"));
				listVo.setMobile(rs.getString("MobileNo"));
				listVo.setExperience(rs.getString("Experience"));
				listVo.setDateofJoin(HelperClass.convertDatabaseToUI(rs
						.getString("DOJ")));
				listVo.setDrivingliecenseNo(rs.getString("DLNo"));
				listVo.setDl_issued_date(HelperClass.convertDatabaseToUI(rs
						.getString("DLIssuedDate")));
				listVo.setDl_validity(HelperClass.convertDatabaseToUI(rs
						.getString("DLExpirayDate")));
				listVo.setLicense(rs.getString("LicencetoDrive"));
				driverlist.add(listVo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
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
				+ " Control in TransportDaoImpl : getDriverEntireDetails  Ending");

		return driverlist;

	}

	public ArrayList<DriverMsaterListVo> getDriverDetailsWhileUpdate(
			String vehiclecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getDriverDetailsWhileUpdate Starting");
		ArrayList<DriverMsaterListVo> driverlist = new ArrayList<DriverMsaterListVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TransportUtilConstants.GET_UNMAPPED_DRIVER_DETAILS_WHILE_UPDATE);
			pstmt.setString(1, vehiclecode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DriverMsaterListVo listVo = new DriverMsaterListVo();

				listVo.setDriverCode(rs.getString("DriverCode"));
				listVo.setDriverName(rs.getString("Name"));
				driverlist.add(listVo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
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
				+ " Control in TransportDaoImpl : getDriverDetailsWhileUpdate  Ending");
		return driverlist;

	}

	public DriverMsaterListVo getSingleDriverDetails(String vehiclecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getSingleDriverDetails Starting");
		DriverMsaterListVo driverlist = new DriverMsaterListVo();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TransportUtilConstants.GET_SINGLE_DRIVER_DETAILS);
			pstmt.setString(1, vehiclecode);
			pstmt.setString(2, vehiclecode);
			rs = pstmt.executeQuery();
			System.out.println("istiyak driver code"+pstmt);
			while (rs.next()) {

				driverlist.setDriverCode(rs.getString("DriverCode"));
				driverlist.setDriverName(rs.getString("Name"));
				driverlist.setMobile(rs.getString("MobileNo"));
				driverlist.setExperience(rs.getString("Experience"));
				driverlist.setDateofJoin(HelperClass.convertDatabaseToUI(rs
						.getString("DOJ")));
				driverlist.setDrivingliecenseNo(rs.getString("DLNo"));
				driverlist.setDl_issued_date(HelperClass.convertDatabaseToUI(rs
						.getString("DLIssuedDate")));
				driverlist.setDl_validity(HelperClass.convertDatabaseToUI(rs
						.getString("DLExpirayDate")));
				driverlist.setLicense(rs.getString("LicencetoDrive"));
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
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
				+ " Control in TransportDaoImpl : getSingleDriverDetails  Ending");

		return driverlist;

	}

	public ArrayList<StageMasterVo> getStopNames(String searchTerm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getStopNames Starting");
		ArrayList<StageMasterVo> stoplist = new ArrayList<StageMasterVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TransportUtilConstants.GET_STOP_NAMES);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				StageMasterVo stopvo = new StageMasterVo();
				stopvo.setStageCode(rs.getString("stage_id"));
				stopvo.setStageName(rs.getString("stage_name"));
				stoplist.add(stopvo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
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
				+ " Control in TransportDaoImpl : getStopNames  Ending");

		return stoplist;

	}

	public ArrayList<TransportVo> getRouteDetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getRouteDetails Starting");
		ArrayList<TransportVo> transportlist = new ArrayList<TransportVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(TransportUtilConstants.GET_ROUTE_NAMES);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TransportVo vo = new TransportVo();
				vo.setRouteCode(rs.getString("RouteCode"));
				vo.setRouteName(rs.getString("RouteName"));
				transportlist.add(vo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
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
				+ " Control in TransportDaoImpl : getRouteDetails  Ending");

		return transportlist;
	}

	public ArrayList<TransportVo> GetRouteEntireDetails(String route) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : GetRouteEntireDetails Starting");
		ArrayList<TransportVo> transportlist = new ArrayList<TransportVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TransportUtilConstants.GET_ROUTE_DETAILS);
			pstmt.setString(1, route);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TransportVo vo = new TransportVo();
				vo.setRouteCode(rs.getString("RouteCode"));
				vo.setRouteName(rs.getString("RouteName"));
				vo.setTotalDistance(rs.getString("TotalDistance"));
				vo.setRouteCode(rs.getString("Route_No"));
				vo.setTotalStops(rs.getString("Total_Stops"));
				vo.setDestination(rs.getString("Destination"));
				vo.setHalttime(rs.getString("HaltTime"));
				transportlist.add(vo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
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
				+ " Control in TransportDaoImpl : GetRouteEntireDetails  Ending");

		return transportlist;

	}

	public TransportVo getRouteDetails(String vehiclecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRouteDetails Starting");
		TransportVo vo = new TransportVo();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TransportUtilConstants.GET_ROUTE_DETAILS_EDIT);
			pstmt.setString(1, vehiclecode);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo.setRouteCode(rs.getString("RouteCode"));
				vo.setRouteName(rs.getString("RouteName"));
				vo.setTotalDistance(rs.getString("TotalDistance"));
				vo.setRouteNo(rs.getString("Route_No"));
				vo.setTotalStops(rs.getString("Total_Stops"));
				//vo.setDestination(rs.getString("Destination"));
				vo.setHalttime(rs.getString("HaltTime"));
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {

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
				+ " Control in TransportServiceImpl : getRouteDetails  Ending");

		return vo;

	}

	@Override
	public List<TransportPojo> getTransportMasterDaoDetailsXLS() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportSetupDaoImpl: getTransportMasterDaoDetails Starting");
		ArrayList<TransportPojo> tpMasterPojoList = new ArrayList<TransportPojo>();
		PreparedStatement pstmt = null;
		TransportPojo obj = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			System.out.println("DownloadDAOIMPLis WORKING");
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.ROUTE_ALLLISTDATASTOP);
			if (pstmt != null) {
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {

					obj = new TransportPojo();
					obj.setRouteName(resultSet.getString("RouteName"));
					obj.setRouteNo(resultSet.getString("Route_No"));
					obj.setRouteCode(resultSet.getString("RouteCode"));
					obj.setStartTime(resultSet.getString("Start_Time"));
					obj.setEndTime(resultSet.getString("End_Time"));
					obj.setTotalStops(resultSet.getString("Total_Stops"));
					obj.setTotalDistance(resultSet.getString("TotalDistance"));
					obj.setHalttime(resultSet.getString("HaltTime"));
					obj.setDestination(resultSet.getString("Destination"));
					obj.setStopname(resultSet.getString("Stop_Name"));
					obj.setArrivaltime(resultSet.getString("ArrivalTime"));
					obj.setDeparturetime(resultSet.getString("DepTime"));
					obj.setDistance(resultSet.getString("Distance"));
					
					tpMasterPojoList.add(obj);
				}
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
				+ " Control in FeeSetupDaoImpl: getRouteMasterDaoDetails Ending");
		return tpMasterPojoList;
	}

	@Override
	
    public boolean addDriverDao(DriverMsaterListVo drivervo, String createuser) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : addDriverDao Starting");

		PreparedStatement pstmt = null;
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		Connection connection = null;
		boolean result = false;
		int rs = 0;
		try {

			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.ADD_DRIVER);

			pstmt.setString(1, drivervo.getDriverCode());
			pstmt.setString(2, "driver");
			pstmt.setString(3, drivervo.getDriverName());
			pstmt.setString(4, drivervo.getFather_name());
			pstmt.setString(5,
					HelperClass.convertUIToDatabase(drivervo.getDateofBirth()));
			pstmt.setString(6, drivervo.getMobile());
			pstmt.setString(7, drivervo.getEmerg_contact());
			pstmt.setString(8, drivervo.getAddress());
			pstmt.setString(9,
					HelperClass.convertUIToDatabase(drivervo.getDateofJoin()));
			pstmt.setString(10, drivervo.getAge());
			pstmt.setString(11, drivervo.getGender());
			pstmt.setString(12, drivervo.getDrivingliecenseNo());
			pstmt.setString(13, HelperClass.convertUIToDatabase(drivervo
					.getDl_issued_date()));
			pstmt.setString(14,
					HelperClass.convertUIToDatabase(drivervo.getDl_validity()));
			pstmt.setString(15, drivervo.getDriving_license_types());
			pstmt.setTimestamp(16, time_stamp);
			pstmt.setString(17, createuser);
			pstmt.setString(18, drivervo.getExperience());
			
			pstmt.setString(19, drivervo.getUploadinglicense());

			System.out.println("Insert:::::"+pstmt);

			rs = pstmt.executeUpdate();
			
			System.out.println("rs:::"+rs);
			
			
			if (rs > 0) {

				result = true;
				drivervo.setStatus("success1");

			} else {

				result = false;
				drivervo.setStatus("success2");

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {
				
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
				+ " Control in TransportDaoImpl : addDriverDao  Ending");
		return result;
	}

	@Override
	public boolean updateDriverDao(DriverMsaterListVo drivervo, String createuser) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updateDriverDao Starting");

		PreparedStatement pstmt = null;
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		boolean result = false;
		Connection connection = null;
		int rs = 0;


		try {

			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.UPDATE_DRIVER);

			
			
			
			/* pstmt.setString(1, drivervo.getType()); */
			pstmt.setString(1, drivervo.getDriverName());
			pstmt.setString(2, drivervo.getFather_name());
			pstmt.setString(3,
					HelperClass.convertUIToDatabase(drivervo.getDateofBirth()));
			pstmt.setString(4, drivervo.getMobile());
			pstmt.setString(5, drivervo.getEmerg_contact());
			pstmt.setString(6, drivervo.getAddress());
			pstmt.setString(7,
					HelperClass.convertUIToDatabase(drivervo.getDateofJoin()));
			pstmt.setString(8, drivervo.getAge());
			pstmt.setString(9, drivervo.getGender());
			pstmt.setString(10, drivervo.getDrivingliecenseNo());
			pstmt.setString(11, HelperClass.convertUIToDatabase(drivervo
					.getDl_issued_date()));
			pstmt.setString(12,
					HelperClass.convertUIToDatabase(drivervo.getDl_validity()));
			
		
			
			pstmt.setString(13,drivervo.getDriving_license_types());
			pstmt.setTimestamp(14, time_stamp);
			pstmt.setString(15, drivervo.getCreateUser());
			pstmt.setString(16, drivervo.getExperience());
			pstmt.setString(17, drivervo.getUploadinglicense());

			pstmt.setString(18, drivervo.getDriverCode());

			
			System.out.println("Update:::::"+pstmt);


			rs = pstmt.executeUpdate();
			
			System.out.println("rs:::"+rs);

			
			if (rs > 0) {

				result = true;
				drivervo.setStatus("success3");


			} else {

				result = false;
				drivervo.setStatus("success4");

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {
				
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
				+ " Control in TransportDaoImpl : updateDriverDao  Ending");
		return result;
	}

	@Override
	public List<TransportVo> editRouteStages(TransportPojo tpMasterPojo,String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : editRouteStages Starting");
		
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;
		int count=0;
		ArrayList<TransportVo> list = new ArrayList<TransportVo>();
		
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.ETID_ROUTE_STAGES);

			pstmt.setString(1, tpMasterPojo.getRouteCode());
			pstmt.setString(2, accyear);
			
			resultSet = pstmt.executeQuery();
			System.out.println("Route Code: "+tpMasterPojo.getRouteCode());
				while (resultSet.next()) {
					count++;
					TransportVo vo = new TransportVo();
					vo.setStage_name(resultSet.getString("stage_name"));
					
					vo.setStage_id(resultSet.getString("StageCode"));
					
					vo.setCount(count);
					System.out.println("Stage Name: "+resultSet.getString("stage_name"));
					System.out.println("Stage Code: "+resultSet.getString("StageCode"));
					list.add(vo);
					
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
				+ " Control in TransportDaoImpl : editRouteStages  Ending");

		return list;
	}

	
	public List<TransportVo> unmappedRouteStages(TransportPojo tpMasterPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : unmappedRouteStages Starting");
		
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;
		int count=0;
		ArrayList<TransportVo> list = new ArrayList<TransportVo>();
		
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection.prepareStatement(TransportUtilConstants.UNMMAPEDSTAGES);

			/*pstmt.setString(1, tpMasterPojo.getRouteCode());
			pstmt.setString(2, tpMasterPojo.getAccyear());*/
			pstmt.setString(1, tpMasterPojo.getAccyear());
				System.out.println("pstmt::::"+pstmt);
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {
					count++;
					TransportVo vo = new TransportVo();
					vo.setStage_name(resultSet.getString("stage_name"));
					vo.setStageName(resultSet.getString("stage_name"));
					vo.setStage_id(resultSet.getString("stage_id"));
					vo.setStageCode(resultSet.getString("stage_id"));
					vo.setCount(count);
			
					list.add(vo);
					
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
				+ " Control in TransportDaoImpl : unmappedRouteStages  Ending");

		return list;
	}

	@Override
	public ArrayList<TransportVo> getRouteDetailsByLocation(String transferlocation) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getRouteDetails Starting");
		ArrayList<TransportVo> transportlist = new ArrayList<TransportVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(TransportUtilConstants.GET_ROUTE_NAMES_BY_LOCATION);
			pstmt.setString(1, transferlocation.trim());

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TransportVo vo = new TransportVo();
				vo.setRouteCode(rs.getString("RouteCode"));
				vo.setRouteName(rs.getString("RouteName"));
				transportlist.add(vo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
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
				+ " Control in TransportDaoImpl : getRouteDetails  Ending");

		return transportlist;
	}

	public ArrayList<TransportVo> getRouteDetailsByName() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getRouteDetails Starting");
		ArrayList<TransportVo> transportlist = new ArrayList<TransportVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(TransportUtilConstants.GET_ROUTE_DETAILS_BY_VECHILE);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TransportVo vo = new TransportVo();
				vo.setRouteCode(rs.getString("RouteCode"));
				vo.setRouteName(rs.getString("RouteName"));
				transportlist.add(vo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
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
				+ " Control in TransportDaoImpl : getRouteDetails  Ending");

		return transportlist;
	}

	@Override
	public ArrayList<TransportVo> getStudentBusCardDetails(TransportVo obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getRouteDetails Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<TransportVo> studentData = new ArrayList<TransportVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("SELECT CONCAT(st.student_fname_var,' ',st.student_lname_var) AS student,pa.mobileno,CASE WHEN trf.reciept_no IS NULL THEN '-' ELSE trf.reciept_no END reciept_no,CONCAT(cd.classdetails_name_var,' ',cs.classsection_name_var)AS class,cf.stage_name,CASE WHEN rn.RouteName IS NULL THEN '-' ELSE rn.RouteName END RouteName FROM campus_student st JOIN campus_student_transportdetails tra ON st.student_id_int = tra.student_id_int JOIN campus_student_classdetails csc ON csc.student_id_int = st.student_id_int JOIN campus_classdetail cd ON  csc.classdetail_id_int = cd.classdetail_id_int AND csc.locationId = cd.locationId JOIN campus_classsection cs ON csc.classsection_id_int = cs.classsection_id_int AND csc.locationId = cs.locationId JOIN campus_parentchildrelation pra ON pra.stu_addmissionNo = tra.student_id_int JOIN campus_parents pa ON pa.parentid = pra.ParentID  JOIN transport_route rn ON rn.RouteCode = tra.route JOIN campus_fee_stage cf ON cf.stage_id = tra.StageId   LEFT  JOIN campus_tranport_fee_collection_details trf ON tra.student_id_int=trf.admissionNo AND trf.accYear = ? AND trf.termcode = ? WHERE  isTransport = 'Y' AND csc.student_id_int = ? AND csc.fms_acadamicyear_id_int = ? AND csc.locationId =? GROUP BY trf.termcode");
			pstmt.setString(1, obj.getAcy_id());
			pstmt.setString(2, obj.getTermId());
			pstmt.setString(3, obj.getStudentId());
			pstmt.setString(4, obj.getAcy_id());
			pstmt.setString(5, obj.getLoc_id());
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				TransportVo vo = new TransportVo();
				vo.setStudent_name(rs.getString("student"));
				vo.setClassname(rs.getString("class"));
				vo.setReceiptNo(rs.getString("reciept_no"));
				vo.setStopname(rs.getString("stage_name"));
				vo.setRouteName(rs.getString("RouteName"));
				vo.setMobileNo(rs.getString("mobileno"));
				studentData.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return studentData;
	}

	public String insertVehicleType(TransportCategoryForm form,String createCode) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : insertVehicleType Starting");
		int count = 0;
		String status = "";
		PreparedStatement pstmt = null;
		Connection connection = null;

		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		try
		{
			connection = JDBCConnection.getSeparateConnection();
			
			if(form.getUpdateid()== null || form.getUpdateid().equalsIgnoreCase("")){
				pstmt = connection.prepareStatement(TransportUtilConstants.INSERT_VECHICLE_TYPE);
				
				pstmt.setString(1, form.getVehicle_id());
				pstmt.setString(2, form.getVehicleType().trim());
				pstmt.setString(3, form.getDescription().trim());
				pstmt.setString(4, createCode);
				count = pstmt.executeUpdate();
				System.out.println("pstmt from trans dao impl :"+pstmt);
				if(count>0)
				{
					status = "Success";
				}
				else
				{
					status = "Failure";
				}
			}else
			{
				pstmt = connection.prepareStatement(TransportUtilConstants.UPDATE_VECHICLE_TYPE);
				pstmt.setString(1, form.getVehicleType());
				pstmt.setString(2, form.getDescription());
				pstmt.setString(3, createCode);
				pstmt.setTimestamp(4, time_stamp);
				pstmt.setString(5, form.getUpdateid());//Here the values are coming from action class.
				count = pstmt.executeUpdate();
				if(count>0)
				{
					status = "UpdateSuccess";
				}
				else
				{
					status = "UpdateFailure";
				}
			}
			
		}
		catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return status;
	}
	
	public List<VehicleTypeVo> getAllvehicletypeDetails() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getAllvehicletypeDetails Starting");
		List<VehicleTypeVo> getvehiclelist = new ArrayList<VehicleTypeVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.GET_ALL_VEHICLE_TYPE_DETAILS);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				VehicleTypeVo tform = new VehicleTypeVo();
				tform.setVehicle_id(rs.getString("type_id"));
				tform.setVehicleType(rs.getString("type_name"));
				tform.setDesc(rs.getString("type_description"));
				getvehiclelist.add(tform);
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
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getAllvehicletypeDetails  Ending");
		return getvehiclelist;
	}
	
	public VehicleTypeVo edittransporttype(String transportCatergory) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : edittransporttype Starting");
		VehicleTypeVo tform = new VehicleTypeVo();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TransportUtilConstants.EDIT_TRANSPORT_CATEGORY_DETAILS);
			pstmt.setString(1, transportCatergory);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tform.setVehicle_id(rs.getString("type_id"));
				tform.setVehicleType(rs.getString("type_name"));
				tform.setDesc(rs.getString("type_description"));
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
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
				+ " Control in TransportServiceImpl : edittransporttype  Ending");

		return tform;

	}
	
	public String deleteVehicleType(String[] code) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : deleteVehicleType Starting");
		int count = 0;
		
		String status = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		VehicleDetailsVO obj = null;
		try {
			
			for(int i=0;i<code.length;i++){
			connection = JDBCConnection.getSeparateConnection();
			
				pstmt = connection
						.prepareStatement(TransportUtilConstants.DELETE_VEHICLE_TYPE_DETAILS);
				pstmt.setString(1, code[i]);
				
				count = pstmt.executeUpdate();
			
				if (count > 0 ) {
					
					 status = MessageConstants.VEHICLE_DELETE_SUCCESS;
				}
				else{
					status = MessageConstants.VEHICLE_DELETE_FAIL;
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
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : deleteVehicleType  Ending");
		return status;
	}

	public boolean validateVehicleType(VehicleTypeVo vehicleadd) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : validateVehicleType Starting");
		int count = 0;
		boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection
					.prepareStatement(TransportUtilConstants.VALIDATE_VEHICLE_TYPE);

			pstmt.setString(1, vehicleadd.getVehicleType().trim());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				status = true;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : validateVehicleType  Ending");
		return status;
	}

	
	@SuppressWarnings("finally")
	public List<VehicleTypeVo> searchVehicletypeDetails(String searchName) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : searchVehicletypeDetails Starting");

		ArrayList<VehicleTypeVo> getvehiclelist = new ArrayList<VehicleTypeVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(TransportUtilConstants.SEARCH_VEHICLE_TYPE);

			pstmt.setString(1, "%"+searchName+"%");
			pstmt.setString(2, "%"+searchName+"%");
			pstmt.setString(3, "%"+searchName+"%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				VehicleTypeVo detailsearch = new VehicleTypeVo();
				
				detailsearch.setVehicle_id(rs.getString("type_id"));
				detailsearch.setVehicleType(rs.getString("type_name"));
				detailsearch.setDesc(rs.getString("type_description"));
				getvehiclelist.add(detailsearch);
			}

		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
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
				+ " Control in TransportDaoImpl: searchVehicletypeDetails Ending");

		return getvehiclelist;
	}
}

	public TransportVo gettransportdetailsstudentwise(TransportVo tvo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : gettransportdetailsstudentwise Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		TransportVo tvo1 = null;
		String months[] = {"January", "February", "March", "April",
                "May", "June", "July", "August", "September",
                "October", "November", "December"}; 
//select cs.student_admissionno_var,concat(cs.student_fname_var,' ',cs.student_lname_var) as student,cp.parentid,cc.classdetail_id_int,cc.classdetails_name_var  from campus_student cs join campus_parentchildrelation cp  on cs.student_id_int= cp.stu_addmissionNo and cs.student_id_int=? and cp.stu_addmissionNo=? and cs.fms_acadamicyear_id_int=? and cs.locationId=? join campus_classdetail cc  where cc.classdetail_id_int=? and cc.locationId=? ";
			try {
				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(TransportUtilConstants.GET_STU_DETAILS);
				pstmt.setString(1,tvo.getStudentId());
				pstmt.setString(2,tvo.getLoc_id());
				pstmt.setString(3,tvo.getClassId());
				pstmt.setString(4,tvo.getSectionId());
				pstmt.setString(5,tvo.getAcy_id());
				
				System.out.println("pstmtpstmt=="+pstmt);
				rs = pstmt.executeQuery();
				while(rs.next()){
					tvo1=new TransportVo();
					tvo1.setStudent_name(rs.getString("student"));
					tvo1.setAdmisssion_no(rs.getString("student_admissionno_var"));
					tvo1.setClassname(rs.getString("classdetails_name_var"));
					tvo1.setSection_name(rs.getString("classsection_name_var"));
					tvo1.setStudent_status(rs.getString("student_status"));
					tvo1.setAddress(rs.getString("address"));
					tvo1.setStmonth(rs.getString("start_month"));
					tvo1.setEndmonth(rs.getString("end_month"));
					tvo1.setStatus(rs.getString("isTransport"));
					if(rs.getString("isTransport").equalsIgnoreCase("Y")){
						tvo1.setTransport_status("AVAILABLE");
						tvo1.setStage_id(rs.getString("StageId"));
						tvo1.setRouteCode(rs.getString("route"));
						
					
					}else{
						tvo1.setTransport_status("NOTAVAILABLE");
					}
				}
			}
	
			 catch (Exception e) 
			   {
					e.printStackTrace();
				}

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

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportDaoImpl: gettransportdetailsstudentwise Ending");

	return tvo1;
	}

	@Override
	public ArrayList<TransportVo> settranporttermdetailsforstudent(
			TransportVo tvo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : settranporttermdetailsforstudent Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		int sno=0;
		ArrayList<TransportVo> getranportdetails = new ArrayList<TransportVo>();
        

		
			try {
				
				
				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement("select termid,termname,startdate,enddate from campus_fee_transport_termdetails where accyear=?");
				pstmt.setString(1,tvo.getAcy_id());
				rs = pstmt.executeQuery();
				while(rs.next())
				{  
					String termname = null;
					String termid = null;
					String startdate = null;
					String enddate = null;
	                termname = rs.getString("termname");
					termid = rs.getString("termid");
					startdate=(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
					enddate=(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
					
					
						String state_month=startdate.substring(3,5);
						int start =Integer.parseInt(state_month);
						String end_Month=enddate.substring(3,5);
						int end=Integer.parseInt(end_Month);
						for(int i=start-1;i<end;i++)
						{
							
						TransportVo setvo = new TransportVo();
						sno++;
						setvo.setSno(sno);
						System.out.println("state_month:" +state_month);
						System.out.println("end_Month:" +end_Month);
						 String months[] = {"January", "February", "March", "April",
			                     "May", "June", "July", "August", "September",
			                     "October", "November", "December"};
						setvo.setTerm_name(termname); 
						setvo.setTermId(termid);
					    setvo.setMonths(months[i]);
						getranportdetails.add(setvo);
						}
					}
			}
			 catch (Exception e) 
			   {
					e.printStackTrace();
				}

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

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportDaoImpl: settranporttermdetailsforstudent Ending");

	return getranportdetails;
}

	@Override
	public ArrayList<TransportVo> getRouteNamelist(TransportVo tvo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TransportVo> getroutenamelist = new ArrayList<TransportVo>();
         try{
         conn = JDBCConnection.getSeparateConnection();
		 pstmt =conn.prepareStatement("select RouteCode,RouteName from transport_route");
		
	     rs = pstmt.executeQuery();
	     while(rs.next())
		 {
			 TransportVo setvo = new TransportVo();
			 setvo.setRouteCode(rs.getString("RouteCode"));
			 setvo.setRouteName(rs.getString("RouteName"));
			 getroutenamelist.add(setvo);
		 }
		 } 
          catch (Exception e) 
          {
          e.printStackTrace();
          }

         try {
        	 if (rs != null && (!rs.isClosed())) {
        		 rs.close();
        	}
        	if (pstmt != null && (!pstmt.isClosed())){
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

         logger.setLevel(Level.DEBUG);
         JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
         logger.info(JDate.getTimeString(new Date())+ " Control in TransportDaoImpl: searchVehicletypeDetails Ending");

return getroutenamelist;
}

	@Override
	public ArrayList<TransportVo> getstoplist(TransportVo tvo) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<TransportVo> getroutenamelist = new ArrayList<TransportVo>();
         try{
         String stagecode =null;
         conn = JDBCConnection.getSeparateConnection();
		 pstmt =conn.prepareStatement("select StageCode from transport_route_stage_mapping where RouteCode=? and accyear=?");
		 pstmt.setString(1, tvo.getRouteCode());
		 pstmt.setString(2, tvo.getAccyear());
	     rs = pstmt.executeQuery();
	     while(rs.next())
		 {   
			  TransportVo setvo = new TransportVo();
			  stagecode=(rs.getString("StageCode"));
			  PreparedStatement pstmt1 =conn.prepareStatement("select stage_id,stage_name,amount from campus_fee_stage where stage_id=?");
			  pstmt1.setString(1, stagecode);
			  ResultSet rs1 = pstmt1.executeQuery();
			 // System.out.println("stopname for transport:" +pstmt1);
			  while(rs1.next())
			  {   
				  setvo.setStage_id(rs1.getString("stage_id"));
				  setvo.setStopname(rs1.getString("stage_name"));
				  setvo.setAmount(rs1.getInt("amount"));
			  }

			  getroutenamelist.add(setvo);
		 }
		 } 
          catch (Exception e) 
          {
          e.printStackTrace();
          }

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

logger.setLevel(Level.DEBUG);
JLogger.log(0, JDate.getTimeString(new Date())
+ MessageConstants.END_POINT);
logger.info(JDate.getTimeString(new Date())
+ " Control in TransportDaoImpl: searchVehicletypeDetails Ending");

return getroutenamelist;
}

	@Override
	public String getamountandstatus(TransportVo tvo) {
		// TODO Auto-generated method stub
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getamountandstatus Starting");
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;String status = null;
		String amountstatus=null;
	double amount =0.0;
         try{
         conn = JDBCConnection.getSeparateConnection();
		
			   pstmt =conn.prepareStatement("select stage_id,stage_name,amount,status from campus_fee_stage where stage_id=?");
			   pstmt.setString(1, tvo.getStage_id());
			   rs = pstmt.executeQuery();
			  // System.out.println("For Execute The Query for amount:" +pstmt);
			   
			   while(rs.next())
			  {  
				
				  amount=(rs.getInt("amount"));
				  status=(rs.getString("status"));
				  if(status.equalsIgnoreCase("unmapped"))
				  {
					  status="unpaid";
				  }
				  amountstatus = amount +"," + status;  
			  }
               } 
          catch (Exception e) 
          {
          e.printStackTrace();
          }

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

logger.setLevel(Level.DEBUG);
JLogger.log(0, JDate.getTimeString(new Date())
+ MessageConstants.END_POINT);
logger.info(JDate.getTimeString(new Date())
+ " Control in TransportDaoImpl: getamountandstatus Ending");

return amountstatus;
}

	@Override
	public String savetransportrequest(TransportPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : savetransportrequest Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		String result = null;
		int count = 0;
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("update campus_student_transportdetails set isTransport = 'Y',StageId = ?,route = ? where student_id_int = ? and fms_acadamicyear_id_int = ? and locationId = ?");
			pstmt.setString(1,pojo.getStageid());
			pstmt.setString(2,pojo.getRouteCode());
		
			pstmt.setString(3,pojo.getStuid());
			pstmt.setString(4,pojo.getAccyear());
			pstmt.setString(5,pojo.getLocid());
		
			
			System.out.println(pstmt);
			count = pstmt.executeUpdate();
			if(count > 0){
				PreparedStatement insrt=conn.prepareStatement("INSERT INTO campus_student_route_stage_mapping (mapped_id,accyear,route_id,stage_id,month) VALUES(?,?,?,?,?)");
				for(int i=0;i<pojo.getEndmonth().split(",").length;i++) {
					insrt.setString(1, pojo.getStuid());
					insrt.setString(2,	pojo.getAccyear());
					insrt.setString(3,	pojo.getRouteCode());
					insrt.setString(4, pojo.getStageid());
					insrt.setString(5, pojo.getEndmonth().split(",")[i]);
					insrt.executeUpdate();
				}
				
			
				
				result = "success";
			}else{
				result = "fail";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

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
		return result;
	}

	@Override
	public List<StudentRegistrationVo> searchAllAcademicYearDetailsTrans(String locationId, String accYear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchAllAcademicYearDetails Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String acc_year = accYear;
		String loc_Id = locationId;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(TransportUtilConstants.GET_STUDENTS_BY_LOCATION_ACCYEAR);
			pst.setString(1, loc_Id);
			pst.setString(2, acc_year);
			//System.out.println(pst);
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				//raji
				PreparedStatement pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt1.setString(1, accYear);
				ResultSet  rs1 =pstmt1.executeQuery();
				while(rs1.next())
				{
					stuReg.setAcademicYear(rs1.getString("acadamic_year"));
				}
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSection_id(rs.getString("classsection_id_int"));
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchAllAcademicYearDetails Ending");
		
		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentList(String academic_year, String schoolLocation) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentList  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
	

		try {
			conn = JDBCConnection.getSeparateConnection();

			pst = conn.prepareStatement(TransportUtilConstants.GET_STUDENT_LIST);
			pst.setString(1, schoolLocation);
			pst.setString(2, academic_year);

			rs = pst.executeQuery();
			//System.out.println("Student list from fee Collection:" +pst);
			
			
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
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
				+ " Control in StudentRegistrationDaoImpl : getStudentList  Ending");

		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentListByClass(String locationid, String accyear, String classname){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;

		try {
			if(classname.equalsIgnoreCase("all"))
			{
				classname="%%";
				
				conn = JDBCConnection.getSeparateConnection();
				pst = conn.prepareStatement(TransportUtilConstants.GET_FILTERED_STUDENTDETAILS);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				
			}else{
				conn = JDBCConnection.getSeparateConnection();
				pst = conn.prepareStatement(TransportUtilConstants.GET_FILTERED_STUDENTDETAILS);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
			}
		

			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
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
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Ending");

		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname,String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;

		try {
			if(sectionid.equalsIgnoreCase("all")){
				sectionid="%%";
				conn = JDBCConnection.getSeparateConnection();
				pst = conn.prepareStatement(TransportUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
			}
			else{
				conn = JDBCConnection.getSeparateConnection();
				pst = conn.prepareStatement(TransportUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
			}
		
			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
				registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
				registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
				registrationVo.setSpecilization(rs.getString("specilization"));
		PreparedStatement SecondlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
		SecondlanguageName.setString(1, rs.getString("secondlanguage"));
		ResultSet SecondLangaugeRs=SecondlanguageName.executeQuery();
		while(SecondLangaugeRs.next()){
			registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
		}
		PreparedStatement thirdlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
		thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
		ResultSet thirdlanguageRs=thirdlanguageName.executeQuery();
		while(thirdlanguageRs.next()){
			registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
		}		
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Ending");

		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchByList(String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByList Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(TransportUtilConstants.GET_STUDENTS_SEARCH_BY_LIST);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				list.add(stuReg);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByList Ending");
		
		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchListByAccYear(String searchTerm, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByAccYear Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String accYear = accyear;
		
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(TransportUtilConstants.GET_STUDENTS_SEARCH_BY_ACCYEAR);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, accYear);
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByAccYear Ending");
		
		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchListByLocation(String searchTerm, String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(TransportUtilConstants.GET_STUDENTS_SEARCH_BY_LOCATION);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				//raji
				PreparedStatement pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt1.setString(1, stuReg.getAcademicYearId());
				ResultSet  rs1 =pstmt1.executeQuery();
				while(rs1.next())
				{
					stuReg.setAcademicYear(rs1.getString("acadamic_year"));
				}
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Ending");
		
		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchByFilter(String searchTerm, String locationid, String accyear,
			String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByFilter Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(TransportUtilConstants.GET_STUDENTS_SEARCH_BY_FILTER);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");

	
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByFilter Ending");
		
		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchByClass(String searchTerm, String locationid, String accyear,
			String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByClass Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(TransportUtilConstants.GET_STUDENTS_SEARCH_BY_CLASS);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");
			pst.setString(9, "%"+accYear+"%");
			pst.setString(10, "%"+className+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				list.add(stuReg);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByClass Ending");
		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchByAllFilter(String searchTerm, String locationid, String accyear,
			String classname, String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByAllFilter Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		String section = sectionid;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(TransportUtilConstants.GET_STUDENTS_SEARCH_BY_ALL_FILTER);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");
			pst.setString(9, "%"+accYear+"%");
			pst.setString(10, className);
			pst.setString(11, section);
			rs = pst.executeQuery();
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				list.add(stuReg);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByAllFilter Ending");
		
		return list;
	}

	@Override
	public String waivedOfftransportrequest(TransportPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : savetransportrequest Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		String result = null;
		int count = 0;
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(TransportUtilConstants.UPDATE_TRANSPORT_WAIVEDOFF);
			pstmt.setString(1,pojo.getStuid());
			pstmt.setString(2,pojo.getAccyear());
			pstmt.setString(3,pojo.getLocid());
			
			count = pstmt.executeUpdate();
			if(count > 0){
				result = "success";
			}else{
				result = "fail";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

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
				+ " Control in TransportDaoImpl : waivedOfftransportrequest Ending");
		return result;
	}

	@Override
	public ArrayList<TransportVo> getMonthList(String accyear, String loc_id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getMonthList Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String startDate= null;
		String endDate=null;
		String todaysdate = null;
		ArrayList<TransportVo> list = new ArrayList<TransportVo>();
		try{
				String [] currdate = HelperClass.getCurrentSqlDate().toString().split("-");
				int currdate1 = Integer.parseInt(currdate[1]);
				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement("select termid,termname,startdate,enddate from campus_fee_transport_termdetails where accyear=? AND locationId=?");
				pstmt.setString(1,accyear);
				pstmt.setString(2,loc_id);
				rs = pstmt.executeQuery();
				while(rs.next())
				{  
					String termname = null;
					String termid = null;
					String startdate = null;
					String enddate = null;
	                termname = rs.getString("termname");
					termid = rs.getString("termid");
					startdate=(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
					enddate=(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
					
						String state_month=startdate.substring(3,5);
						int start =Integer.parseInt(state_month);
						String end_Month=enddate.substring(3,5);
						int end=Integer.parseInt(end_Month);
						for(int i=start-1;i<end;i++){
					TransportVo vo = new TransportVo();
					String monthname[] = {"January", "February", "March", "April",
		                     "May", "June", "July", "August", "September",
		                     "October", "November", "December"}; 
					vo.setMonths(monthname[i]);
					vo.setMonthNo(accyear+"-"+(i+1));
					list.add(vo);
				}
				
			/*	for(int i=start-1;i<end;i++)
				{
					TransportVo vo = new TransportVo();
					String monthname[] = {"January", "February", "March", "April",
		                     "May", "June", "July", "August", "September",
		                     "October", "November", "December"};
					vo.setMonths(monthname[i]);
					vo.setMonthNo(accyear+"-"+(i+1));
					list.add(vo);*/
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getMonthList Ending");
		return list;
	}

	public ArrayList<TransportPojo> getRouteScreen(String searchterm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentSearch Starting");
		
		ArrayList<TransportPojo> routeNameList = new ArrayList<TransportPojo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmObj = conn.prepareStatement("SELECT * FROM `transport_route` WHERE RouteName LIKE ?");

			pstmObj.setString(1, searchterm+"%");
			
			System.out.println("query is "+pstmObj);

			rs = pstmObj.executeQuery();

			while (rs.next()) {
				TransportPojo routeNameVo = new TransportPojo();
				routeNameVo.setRouteName(rs.getString("RouteName"));;
				routeNameVo.setRouteCode(rs.getString("RouteCode"));
				routeNameList.add(routeNameVo);

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
				+ " Control in StudentRegistrationDaoImpl : studentSearch Ending");

		return routeNameList;

	}

	public ArrayList<TransportVo> getRouteScreenDetailsfor(String searchterm) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : editRouteMasterDetails Starting");
		ArrayList<TransportVo> routeList = new ArrayList<TransportVo>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection.prepareStatement("SELECT * FROM  `transport_route` WHERE RouteCode=?");

			
			
			pstmt.setString(1, searchterm);
			System.out.println("istiyak Details"+pstmt);
			if (pstmt != null) {
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {
					TransportVo obj=new TransportVo();
					obj.setRouteName(resultSet.getString("RouteName"));
					obj.setRouteNo(resultSet.getString("Route_No"));
					obj.setRouteCode(resultSet.getString("RouteCode"));
					obj.setRouteLogicName(resultSet
							.getString("Route_logical_name"));
					/*obj.setDestination((resultSet.getString("Destination")));*/
					obj.setHalttime(resultSet.getString("HaltTime"));
					obj.setStratTime(resultSet.getString("Start_Time"));
					obj.setEndTime(resultSet.getString("End_Time"));
					obj.setTotalStops(resultSet.getString("Total_Stops"));
					obj.setTotalDistance(resultSet.getString("TotalDistance"));
					routeList.add(obj);
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
				+ " Control in TransportDaoImpl : editRouteMasterDetails  Ending");

		return routeList;
	
	}

	public ArrayList<TransportVo> getmappingList(TransportVo tvo) {



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getmappingList Starting");
		ArrayList<TransportVo> routeList = new ArrayList<TransportVo>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection.prepareStatement("SELECT tr.RouteName,cfs.stage_name,mapp.month,mapp.id FROM  `campus_student_route_stage_mapping` mapp JOIN transport_route tr ON mapp.route_id=tr.RouteCode JOIN campus_fee_stage cfs ON mapp.stage_id=cfs.stage_id  WHERE mapp.mapped_id=? AND mapp.accyear=?");
			pstmt.setString(1, tvo.getStudentId());
			pstmt.setString(2, tvo.getAcy_id());
			
			System.out.println("Hello pstmt   "+ pstmt);
			
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {
					TransportVo obj=new TransportVo();
					obj.setRouteName(resultSet.getString("RouteName"));
					obj.setStageName(resultSet.getString("stage_name"));
					obj.setMonths(resultSet.getString("month"));
					obj.setSno(resultSet.getInt("id"));
					routeList.add(obj);
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
				+ " Control in TransportDaoImpl : getmappingList  Ending");

		return routeList;
	
	
	}

	public ArrayList<TransportVo> getMappedMonth(String studentid, String accyearid) {



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getmappingList Starting");
		ArrayList<TransportVo> routeList = new ArrayList<TransportVo>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection.prepareStatement("SELECT month FROM  `campus_student_route_stage_mapping`  WHERE mapped_id=? AND accyear=? order by id");
			pstmt.setString(1, studentid);
			pstmt.setString(2, accyearid);
			
			
			
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {
					TransportVo obj=new TransportVo();
					
					obj.setMonthNo(accyearid+"-"+HelperClass.getMothNumberByShortName(resultSet.getString("month").substring(0, 3).toLowerCase()));
					obj.setMonths(resultSet.getString("month"));
					routeList.add(obj);
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
				+ " Control in TransportDaoImpl : getmappingList  Ending");

		return routeList;
	
	
	}

	public List<TransportVo> getRouteWiseStudentDetailwithClassAndSection(String location, String accyear,
			String classId, String section) {



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getRouteWiseStudentDetailwithClassAndSection Starting");
		ArrayList<TransportVo> stuList = new ArrayList<TransportVo>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection.prepareStatement("SELECT CONCAT(cs.student_fname_var,' ',cs.student_lname_var) AS student,cs.student_id_int,cs.student_admissionno_var,ccd.`classdetails_name_var`,ccs.`classsection_name_var`,cst.route_id,GROUP_CONCAT(DISTINCT cst.stage_id) stage_id  FROM `campus_student_route_stage_mapping` cst JOIN campus_student cs ON cst.mapped_id=cs.student_id_int JOIN campus_student_classdetails csc ON cs.student_id_int=csc.student_id_int AND cst.accyear=csc.fms_acadamicyear_id_int JOIN `campus_classdetail` ccd ON csc.classdetail_id_int=ccd.classdetail_id_int AND csc.locationId=ccd.locationId JOIN `campus_classsection` ccs ON csc.classsection_id_int=ccs.classsection_id_int WHERE cst.accyear=? AND ccd.classdetail_id_int LIKE ? AND ccs.classsection_id_int LIKE ? GROUP BY mapped_id,route_id");
			pstmt.setString(1, accyear);
			pstmt.setString(2, classId);
			pstmt.setString(3, section);
			
			
			
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {
					
					TransportVo obj=new TransportVo();
					PreparedStatement squery=connection.prepareStatement("SELECT RouteName FROM `transport_route` WHERE RouteCode=?");
					squery.setString(1, resultSet.getString("route_id"));
					ResultSet srs=squery.executeQuery();
					if(srs.next()) {
						obj.setRouteName(srs.getString("RouteName"));
					}
					srs.close();
					squery.close();
					String stage_idgroup[]=resultSet.getString("stage_id").split(",");
					String stage_name="";
					String amount="";
					for(int i=0;i<stage_idgroup.length;i++) {
						squery=connection.prepareStatement("SELECT stage_name,amount FROM `campus_fee_stage` WHERE stage_id=? AND accyear=?");
						squery.setString(1, stage_idgroup[i]);
						squery.setString(2, accyear);
						 srs=squery.executeQuery();
						if(srs.next()) {
							stage_name=stage_name+srs.getString("stage_name")+",";
							amount=amount+srs.getString("amount")+",";
						}
					}
					srs.close();
					squery.close();
					
					squery=connection.prepareStatement("SELECT cpr.`parentid`,cpr.`relationship`,cp.FatherName,cp.mobileno,cp.student_mothername_var,cp.student_mothermobileno_var,cp.student_gaurdianname_var,cp.student_gardian_mobileno FROM `campus_parentchildrelation` cpr JOIN `campus_parents` cp ON cpr.parentid=cp.ParentID WHERE cpr.stu_addmissionNo=?");
					squery.setString(1, resultSet.getString("student_id_int"));
					 srs=squery.executeQuery();
					if(srs.next()) {
						if(srs.getString("mobileNo") !=null && !srs.getString("mobileNo").trim().equalsIgnoreCase("")) {
							obj.setCostPerPerson(srs.getString("FatherName"));
							obj.setMobileNo(srs.getString("mobileNo"));
						}
						else if(srs.getString("student_mothermobileno_var") !=null && !srs.getString("student_mothermobileno_var").trim().equalsIgnoreCase("")) {
							obj.setCostPerPerson(srs.getString("student_mothername_var"));
							obj.setMobileNo(srs.getString("student_mothermobileno_var"));
						}
						else {
							obj.setCostPerPerson(srs.getString("student_gaurdianname_var"));
							obj.setMobileNo(srs.getString("student_gardian_mobileno"));
						} 
					}
					obj.setStage_name(stage_name);
					obj.setDistance(amount);
					obj.setStudent_name(resultSet.getString("student"));
					obj.setAdmisssion_no(resultSet.getString("student_admissionno_var"));
					obj.setClassname(resultSet.getString("classdetails_name_var")+" "+resultSet.getString("classsection_name_var"));
					stuList.add(obj);
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
				+ " Control in TransportDaoImpl : getRouteWiseStudentDetailwithClassAndSection  Ending");

		return stuList;
	
	
	}

	public List<TransportVo> getRouteWiseStudentDetail(String location, String accyear, String routeNo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getRouteWiseStudentDetail Starting");
		ArrayList<TransportVo> stuList = new ArrayList<TransportVo>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection.prepareStatement("SELECT CONCAT(cs.student_fname_var,' ',cs.student_lname_var) AS student,cs.student_id_int,cs.student_admissionno_var,ccd.`classdetails_name_var`,ccs.`classsection_name_var`,cst.route_id,GROUP_CONCAT(DISTINCT cst.stage_id) stage_id  FROM `campus_student_route_stage_mapping` cst JOIN campus_student cs ON cst.mapped_id=cs.student_id_int JOIN campus_student_classdetails csc ON cs.student_id_int=csc.student_id_int AND cst.accyear=csc.fms_acadamicyear_id_int JOIN `campus_classdetail` ccd ON csc.classdetail_id_int=ccd.classdetail_id_int AND csc.locationId=ccd.locationId JOIN `campus_classsection` ccs ON csc.classsection_id_int=ccs.classsection_id_int WHERE cst.accyear=? AND cst.route_id LIKE ?  GROUP BY mapped_id,route_id");
			pstmt.setString(1, accyear);
			pstmt.setString(2, routeNo);
			
			
			
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {
					
					TransportVo obj=new TransportVo();
					PreparedStatement squery=connection.prepareStatement("SELECT RouteName FROM `transport_route` WHERE RouteCode=?");
					squery.setString(1, resultSet.getString("route_id"));
					ResultSet srs=squery.executeQuery();
					if(srs.next()) {
						obj.setRouteName(srs.getString("RouteName"));
					}
					srs.close();
					squery.close();
					String stage_idgroup[]=resultSet.getString("stage_id").split(",");
					String stage_name="";
					String amount="";
					for(int i=0;i<stage_idgroup.length;i++) {
						squery=connection.prepareStatement("SELECT stage_name,amount FROM `campus_fee_stage` WHERE stage_id=? AND accyear=?");
						squery.setString(1, stage_idgroup[i]);
						squery.setString(2, accyear);
						 srs=squery.executeQuery();
						if(srs.next()) {
							stage_name=stage_name+srs.getString("stage_name")+",";
							amount=amount+srs.getString("amount")+",";
						}
					}
					srs.close();
					squery.close();
					

					squery=connection.prepareStatement("SELECT cpr.`parentid`,cpr.`relationship`,cp.FatherName,cp.mobileno,cp.student_mothername_var,cp.student_mothermobileno_var,cp.student_gaurdianname_var,cp.student_gardian_mobileno FROM `campus_parentchildrelation` cpr JOIN `campus_parents` cp ON cpr.parentid=cp.ParentID WHERE cpr.stu_addmissionNo=?");
					squery.setString(1, resultSet.getString("student_id_int"));
					 srs=squery.executeQuery();
					if(srs.next()) {
						if(srs.getString("mobileNo") !=null && !srs.getString("mobileNo").trim().equalsIgnoreCase("")) {
							obj.setCostPerPerson(srs.getString("FatherName"));
							obj.setMobileNo(srs.getString("mobileNo"));
						}
						else if(srs.getString("student_mothermobileno_var") !=null && !srs.getString("student_mothermobileno_var").trim().equalsIgnoreCase("")) {
							obj.setCostPerPerson(srs.getString("student_mothername_var"));
							obj.setMobileNo(srs.getString("student_mothermobileno_var"));
						}
						else {
							obj.setCostPerPerson(srs.getString("student_gaurdianname_var"));
							obj.setMobileNo(srs.getString("student_gardian_mobileno"));
						} 
					}
				
					srs.close();
					squery.close();
					
					obj.setStage_name(stage_name);
					obj.setDistance(amount);
					obj.setStudent_name(resultSet.getString("student"));
					obj.setAdmisssion_no(resultSet.getString("student_admissionno_var"));
					obj.setClassname(resultSet.getString("classdetails_name_var")+" "+resultSet.getString("classsection_name_var"));
					stuList.add(obj);
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
				+ " Control in TransportDaoImpl : getRouteWiseStudentDetail  Ending");

		return stuList;
	
	
	}

	public String getMappedMonthDelete(String id) {



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getMappedMonthDelete Starting");
		String status="";
		PreparedStatement pstmt = null;
		
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection.prepareStatement("DELETE FROM  `campus_student_route_stage_mapping`  WHERE id=?");
			pstmt.setString(1, id);
			
			
			
				int result = pstmt.executeUpdate();
				if(result>0) {
					status="true";
				}
				else {
					status="false";
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
				+ " Control in TransportDaoImpl : getMappedMonthDelete  Ending");

		return status;
	
	
	}
}

