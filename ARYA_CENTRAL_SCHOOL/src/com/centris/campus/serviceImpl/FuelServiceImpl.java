package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.FuelMaintenanceDao;
import com.centris.campus.daoImpl.FuelMaintenanceDaoImpl;
import com.centris.campus.service.FuelMasterService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.DriverMasterVO;
import com.centris.campus.vo.FuelMaintenanceVO;
import com.centris.campus.vo.VehicleDetailsVO;

public class FuelServiceImpl implements FuelMasterService {

	private static final Logger logger = Logger
			.getLogger(FuelServiceImpl.class);
	
	FuelMaintenanceDao fuelMaintenanceDao = new FuelMaintenanceDaoImpl();
	
	public List<FuelMaintenanceVO> getfuelMaintenanceList() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelServiceImpl : getfuelMaintenanceList Starting");
		ArrayList<FuelMaintenanceVO> driverlist = null;
		try 
		{
			FuelMaintenanceDao fuelMaintenanceDao = new FuelMaintenanceDaoImpl();
			driverlist = fuelMaintenanceDao.getfuelMaintenanceList();
		}
		catch (Exception exception) 
		{
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelServiceImpl : getfuelMaintenanceList Ending");
		return driverlist;
	}


	public String addFuelDetails(FuelMaintenanceVO addFuellist) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelServiceImpl : addFuelDetails Starting");
		
		
		try{
			System.out.println("ServiceIMPL: vehicle Code: "+addFuellist.getVehicleCode());
			System.out.println("ServiceIMPL: vehicle name : "+addFuellist.getVehiclename());
			
			if (addFuellist.getFuelCode() == "" || addFuellist.getFuelCode()== null) {
				
				
				FuelMaintenanceDao fuelMaintenanceDao = new FuelMaintenanceDaoImpl();
				return  fuelMaintenanceDao.addFuelDetails(addFuellist);

			} else {

			
				FuelMaintenanceDao fuelMaintenanceDao = new FuelMaintenanceDaoImpl();
				return  fuelMaintenanceDao.updateFuelDetails(addFuellist);
			}
			
		}
		catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelServiceImpl : addFuelDetails Ending");
		
		FuelMaintenanceDao fuelMaintenanceDao = new FuelMaintenanceDaoImpl();
		return fuelMaintenanceDao.addFuelDetails(addFuellist);
		
	}


	@Override
	public ArrayList<VehicleDetailsVO> searchVehicle(VehicleDetailsVO searchterm) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelServiceImpl : searchVehicle Starting");
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelServiceImpl : searchVehicle Ending");
		
		FuelMaintenanceDao fuelMaintenanceDao = new FuelMaintenanceDaoImpl();
		return fuelMaintenanceDao.searchVehicle(searchterm);
		
	}

	
	public List<VehicleDetailsVO> getVehicleNoBycode(VehicleDetailsVO searchTerm) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelServiceImpl : getVehicleNoBycode Starting");
		
		
		ArrayList<VehicleDetailsVO> vehicleList = null;
		
		try {
			
			vehicleList = fuelMaintenanceDao.getVehicleNoBycode(searchTerm);
		} 
		catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelServiceImpl : getVehicleNoBycode Ending");
		return vehicleList;
	}


	@Override
	public boolean deleteFuelDetails(FuelMaintenanceVO deleteFueldetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelServiceImpl : deleteFuelDetails Starting");

		try {

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelServiceImpl : deleteFuelDetails Ending");
		return fuelMaintenanceDao.deleteFuelDetails(deleteFueldetails);
	}


	@Override
	public FuelMaintenanceVO editFuelDetails(FuelMaintenanceVO editFueldetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelServiceImpl : editFuelDetails Starting");
		
		try {

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelServiceImpl : editFuelDetails Ending");
		return fuelMaintenanceDao.editFuelDetails(editFueldetails);

		
	}


	public ArrayList<FuelMaintenanceVO> searchFuelDetails(FuelMaintenanceVO searchvo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelServiceImpl : searchFuelDetails Starting");
		
		try {

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelServiceImpl : searchFuelDetails Ending");
		
		return fuelMaintenanceDao.searchFuelDetails(searchvo);
	}


	
	public List<DriverMasterVO> searchDriver(DriverMasterVO searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelServiceImpl : searchDriver Starting");
		
		ArrayList<DriverMasterVO> driverlist = null;
		try {
			driverlist = fuelMaintenanceDao.searchDriver(searchTerm);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelServiceImpl : searchDriver Ending");
		
		return driverlist;
	}


	@Override
	public ArrayList<VehicleDetailsVO> getVehicleList() {

		return fuelMaintenanceDao.getVehicleList() ;
	}


	@Override
	public ArrayList<VehicleDetailsVO> getDriverNames() {
		return fuelMaintenanceDao.getDriverNames() ;
	}

}
