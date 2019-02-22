package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.vo.DriverMasterVO;
import com.centris.campus.vo.FuelMaintenanceVO;
import com.centris.campus.vo.VehicleDetailsVO;

public interface FuelMasterService {

	 List<FuelMaintenanceVO> getfuelMaintenanceList();

	String addFuelDetails(FuelMaintenanceVO addFuellist);

	ArrayList<VehicleDetailsVO> searchVehicle(VehicleDetailsVO searchterm);

	List<VehicleDetailsVO> getVehicleNoBycode(VehicleDetailsVO searchTerm);

	boolean deleteFuelDetails(FuelMaintenanceVO deleteFueldetails);

	FuelMaintenanceVO editFuelDetails(FuelMaintenanceVO editFueldetails);

	ArrayList<FuelMaintenanceVO> searchFuelDetails(FuelMaintenanceVO searchvo);

	List<DriverMasterVO> searchDriver(DriverMasterVO searchTerm);

	ArrayList<VehicleDetailsVO> getVehicleList();

	ArrayList<VehicleDetailsVO> getDriverNames();
	
	

}
