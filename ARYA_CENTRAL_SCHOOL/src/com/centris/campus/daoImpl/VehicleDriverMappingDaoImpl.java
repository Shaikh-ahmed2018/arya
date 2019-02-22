package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.VehicleDriverMappingDao;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.VehicleDriverMappingSqlUtil;
import com.centris.campus.vo.DriverVo;
import com.centris.campus.vo.VehicleDriverMappingPojo;
import com.centris.campus.vo.VehicleDriverMappingVo;

public class VehicleDriverMappingDaoImpl implements VehicleDriverMappingDao{
	
	private static final Logger logger = Logger
			.getLogger(VehicleDriverMappingDaoImpl.class);

	public synchronized ArrayList<DriverVo> getAllDriversDetails() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : getAllvehicleDetails  Starting");
		
		ArrayList<DriverVo> driverDetails = new ArrayList<DriverVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(VehicleDriverMappingSqlUtil.GET_DRIVER_LIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DriverVo driverVo = new DriverVo();

				driverVo.setDriverId(rs.getString("TeacherID"));
				driverVo.setDriverName(rs.getString("drivername"));

				driverDetails.add(driverVo);
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : getAllvehicleDetails  Starting");

		return driverDetails;
	}

	@Override
	public ArrayList<VehicleDriverMappingVo> getAvailableVehicles() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : getAvailableVehicles  Starting");
		
		ArrayList<VehicleDriverMappingVo> vehicleList = new ArrayList<VehicleDriverMappingVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(VehicleDriverMappingSqlUtil.GET_AVAILABLE_VEHICLES_LIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				VehicleDriverMappingVo vehiclevo = new VehicleDriverMappingVo();

				vehiclevo.setVehiclecode(rs.getString("VehicleCode"));
				vehiclevo.setVehiclename(rs.getString("vehiclename"));

				vehicleList.add(vehiclevo);
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : getAvailableVehicles  Starting");

		return vehicleList;
	}

	@Override
	public ArrayList<VehicleDriverMappingVo> getMappedVehicles(String driverID) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : getMappedVehicles  Starting");
		
		ArrayList<VehicleDriverMappingVo> vehicleList = new ArrayList<VehicleDriverMappingVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(VehicleDriverMappingSqlUtil.GET_MAPPED_VEHICLES_LIST);
			pstmt.setString(1, driverID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				VehicleDriverMappingVo vehiclevo = new VehicleDriverMappingVo();

				vehiclevo.setVehiclecode(rs.getString("VehicleCode"));
				vehiclevo.setVehiclename(rs.getString("vehiclename"));

				vehicleList.add(vehiclevo);
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : getMappedVehicles  Starting");

		return vehicleList;
	}

	@Override
	public String mapVehicle(VehicleDriverMappingPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : mapVehicle  Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String status=null;
		int count=0;
		PreparedStatement pstmt1=null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt1= conn.prepareStatement(VehicleDriverMappingSqlUtil.DELETE_DRIVER_VEHICLE_MAP_DETAILS);
			pstmt1.setString(1, pojo.getDrivercode());
			
			pstmt1.executeUpdate();
			
			pstmt = conn.prepareStatement(VehicleDriverMappingSqlUtil.INSERT_DRIVER_VEHICLE_MAP_DETAILS);
			pstmt.setString(1, pojo.getDrivercode());
			pstmt.setString(2, pojo.getVehiclecode());
			pstmt.setString(3, pojo.getCurrentuser());
			pstmt.setTimestamp(4, HelperClass.getCurrentTimestamp());
			
			count= pstmt.executeUpdate();
			
			if(count>0){
				
				status="true";
			}else{
				
				status="false";
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : mapVehicle  Starting");

		return status;
	}

	@Override
	public ArrayList<VehicleDriverMappingVo> getVehicleDriverMappingList() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : getVehicleDriverMappingList  Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<VehicleDriverMappingVo> mappedList=new ArrayList<VehicleDriverMappingVo>();
		ResultSet rs=null;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(VehicleDriverMappingSqlUtil.GET_DRIVER_VEHICLE_MAP_DETAILS);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				VehicleDriverMappingVo vo=new VehicleDriverMappingVo();
				
				vo.setSno(rs.getInt("Sno"));
				vo.setDriverName(rs.getString("drivername"));
				vo.setVehiclename(rs.getString("vehiclename"));
				
				mappedList.add(vo);
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : getVehicleDriverMappingList  Starting");

		return mappedList;
	}

	@Override
	public VehicleDriverMappingVo editVehicleDriverMapping(
			VehicleDriverMappingPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : editVehicleDriverMapping  Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		VehicleDriverMappingVo vo=new VehicleDriverMappingVo();
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(VehicleDriverMappingSqlUtil.GET_SINGLE_DRIVER_VEHICLE_MAP_DETAILS);
			pstmt.setInt(1, Integer.parseInt(pojo.getMappingID()));
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				vo.setSno(Integer.parseInt(pojo.getMappingID()));
				vo.setDrivercode(rs.getString("DriverCode"));
				vo.setVehiclecode(rs.getString("VehicleCode"));
				vo.setDriverName(rs.getString("drivername"));
				vo.setVehiclename(rs.getString("vehiclename"));
				
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : editVehicleDriverMapping  Starting");

		return vo;
	}

	@Override
	public String updateMappedVehicle(VehicleDriverMappingPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : updateMappedVehicle  Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String status=null;
		PreparedStatement pstmt1 = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt1= conn.prepareStatement(VehicleDriverMappingSqlUtil.DELETE_DRIVER_VEHICLE_MAP_DETAILS);
			pstmt1.setString(1, pojo.getDrivercode());
			
			pstmt1.executeUpdate();
			
			
			pstmt = conn.prepareStatement(VehicleDriverMappingSqlUtil.INSERT_DRIVER_VEHICLE_MAP_DETAILS);
			pstmt.setString(1, pojo.getDrivercode());
			pstmt.setString(2, pojo.getVehiclecode());
			pstmt.setString(3, pojo.getCurrentuser());
			pstmt.setTimestamp(4, HelperClass.getCurrentTimestamp());
			
			count= pstmt.executeUpdate();
			
			if(count>0){
				
				status="true";
			}else{
				
				status="false";
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : updateMappedVehicle  Starting");

		return status;
	}

	@Override
	public ArrayList<VehicleDriverMappingVo> getSearchVehicleDriverMappingList(
			String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : getSearchVehicleDriverMappingList  Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<VehicleDriverMappingVo> mappedList=new ArrayList<VehicleDriverMappingVo>();
		ResultSet rs=null;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(VehicleDriverMappingSqlUtil.GET_SEARCH_DRIVER_VEHICLE_MAP_DETAILS);
			pstmt.setString(1,"%"+searchTerm+"%");
			pstmt.setString(2,"%"+searchTerm+"%");
			pstmt.setString(3,"%"+searchTerm+"%");
			pstmt.setString(4,"%"+searchTerm+"%");
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				VehicleDriverMappingVo vo=new VehicleDriverMappingVo();
				
				vo.setSno(rs.getInt("Sno"));
				vo.setDriverName(rs.getString("drivername"));
				vo.setVehiclename(rs.getString("vehiclename"));
				
				mappedList.add(vo);
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : getSearchVehicleDriverMappingList  Starting");

		return mappedList;
	}

	@Override
	public String deleteVehicleDriverMapping(VehicleDriverMappingPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : deleteVehicleDriverMapping  Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String status=null;
		String[] mappingIds=pojo.getMappingID().split(",");
		int count=0;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(VehicleDriverMappingSqlUtil.DELETE_VEHICLE_DRIVER_MAPPING);
			for(int i=0;i<mappingIds.length;i++){
			
			pstmt.setString(1,mappingIds[i]);
			}
			count=pstmt.executeUpdate();
			
			if(count>0){
				
				status="true";
			}else{
				
				status="false";
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingDaoImpl : deleteVehicleDriverMapping  Starting");

		return status;
	}


}
