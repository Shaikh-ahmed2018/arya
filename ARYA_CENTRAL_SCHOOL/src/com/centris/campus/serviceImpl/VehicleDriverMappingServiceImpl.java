package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.AcademicYearMasterDao;
import com.centris.campus.dao.VehicleDriverMappingDao;
import com.centris.campus.daoImpl.AcademicYearMasterDaoImpl;
import com.centris.campus.daoImpl.VehicleDriverMappingDaoImpl;
import com.centris.campus.service.VehicleDriverMappingService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.DriverVo;
import com.centris.campus.vo.VehicleDriverMappingPojo;
import com.centris.campus.vo.VehicleDriverMappingVo;

public class VehicleDriverMappingServiceImpl implements VehicleDriverMappingService{
	
	private static final Logger logger = Logger
			.getLogger(VehicleDriverMappingServiceImpl.class);


	public ArrayList<DriverVo> getAllDriversDetails() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : getAllvehicleDetails  Starting");
		
			ArrayList<DriverVo> getDriverList = null;
			VehicleDriverMappingDao mappingdao = new VehicleDriverMappingDaoImpl();
		
		try {

			getDriverList = mappingdao.getAllDriversDetails();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : getAllvehicleDetails  Starting");

		return getDriverList;
	}


	@Override
	public ArrayList<VehicleDriverMappingVo> getAvailableVehicles() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : getAllvehicleDetails  Starting");
		
			ArrayList<VehicleDriverMappingVo> getVehicleList = null;
			VehicleDriverMappingDao mappingdao = new VehicleDriverMappingDaoImpl();
		
		try {

			getVehicleList = mappingdao.getAvailableVehicles();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : getAllvehicleDetails  Starting");

		return getVehicleList;
	}


	@Override
	public ArrayList<VehicleDriverMappingVo> getMappedVehicles(String driverID) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : getMappedVehicles  Starting");
		
			ArrayList<VehicleDriverMappingVo> getVehicleList = null;
			VehicleDriverMappingDao mappingdao = new VehicleDriverMappingDaoImpl();
		
		try {

			getVehicleList = mappingdao.getMappedVehicles(driverID);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : getMappedVehicles  Starting");

		return getVehicleList;
	}


	@Override
	public String mapVehicle(VehicleDriverMappingPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : mapVehicle  Starting");
		
			String status = null;
			VehicleDriverMappingDao mappingdao = new VehicleDriverMappingDaoImpl();
		
		try {

			status = mappingdao.mapVehicle(pojo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : mapVehicle  Starting");

		return status;
	}


	@Override
	public ArrayList<VehicleDriverMappingVo> getVehicleDriverMappingList() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : getVehicleDriverMappingList  Starting");
		
			ArrayList<VehicleDriverMappingVo> mappedVehicleList = null;;
			VehicleDriverMappingDao mappingdao = new VehicleDriverMappingDaoImpl();
		
		try {

			mappedVehicleList = mappingdao.getVehicleDriverMappingList();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : getVehicleDriverMappingList  Starting");

		return mappedVehicleList;
	}


	@Override
	public VehicleDriverMappingVo editVehicleDriverMapping(VehicleDriverMappingPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : editVehicleDriverMapping  Starting");
		
		VehicleDriverMappingVo mappedVehicleVo = null;;
			VehicleDriverMappingDao mappingdao = new VehicleDriverMappingDaoImpl();
		
		try {

			mappedVehicleVo = mappingdao.editVehicleDriverMapping(pojo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : editVehicleDriverMapping  Starting");

		return mappedVehicleVo;
	}


	@Override
	public String updateMappedVehicle(VehicleDriverMappingPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : updateMappedVehicle  Starting");
		
			String status = null;
			VehicleDriverMappingDao mappingdao = new VehicleDriverMappingDaoImpl();
		
		try {

			status = mappingdao.updateMappedVehicle(pojo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : updateMappedVehicle  Starting");

		return status;
	}


	@Override
	public ArrayList<VehicleDriverMappingVo> getSearchVehicleDriverMappingList(
			String searchTerm) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : getSearchVehicleDriverMappingList  Starting");
		
			ArrayList<VehicleDriverMappingVo> mappedVehicleList = null;;
			VehicleDriverMappingDao mappingdao = new VehicleDriverMappingDaoImpl();
		
		try {

			mappedVehicleList = mappingdao.getSearchVehicleDriverMappingList(searchTerm);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : getSearchVehicleDriverMappingList  Starting");

		return mappedVehicleList;
	}


	@Override
	public String deleteVehicleDriverMapping(VehicleDriverMappingPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : deleteVehicleDriverMapping  Starting");
		
		VehicleDriverMappingDao mappingdao = new VehicleDriverMappingDaoImpl();
		String status=null;
		
		try {

			status = mappingdao.deleteVehicleDriverMapping(pojo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingServiceImpl : deleteVehicleDriverMapping  Starting");

		return status;
	}

}
