package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.vo.DriverMasterVO;
import com.centris.campus.vo.FuelMaintenanceVO;
import com.centris.campus.vo.VehicleDetailsVO;

public interface FuelMaintenanceDao {

	ArrayList<FuelMaintenanceVO> getfuelMaintenanceList();

	String addFuelDetails(FuelMaintenanceVO addFuellist);

	ArrayList<VehicleDetailsVO> searchVehicle(VehicleDetailsVO searchterm);

	ArrayList<VehicleDetailsVO> getVehicleNoBycode(VehicleDetailsVO searchTerm);

	boolean deleteFuelDetails(FuelMaintenanceVO deleteFueldetails);

	FuelMaintenanceVO editFuelDetails(FuelMaintenanceVO editFueldetails);

	String updateFuelDetails(FuelMaintenanceVO addFuellist);

	ArrayList<FuelMaintenanceVO> searchFuelDetails(FuelMaintenanceVO searchvo);

	ArrayList<DriverMasterVO> searchDriver(DriverMasterVO searchTerm);

	ArrayList<VehicleDetailsVO> getVehicleList();

	ArrayList<VehicleDetailsVO> getDriverNames();



}
