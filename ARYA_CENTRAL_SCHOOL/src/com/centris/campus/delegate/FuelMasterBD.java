package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;
import com.centris.campus.service.FuelMasterService;
import com.centris.campus.serviceImpl.FuelServiceImpl;
import com.centris.campus.vo.DriverMasterVO;
import com.centris.campus.vo.FuelMaintenanceVO;
import com.centris.campus.vo.VehicleDetailsVO;

public class FuelMasterBD {

	public List<FuelMaintenanceVO> getfuelMaintenanceList() {
		FuelMasterService fuelservice = new FuelServiceImpl();
		return fuelservice.getfuelMaintenanceList();
	}

	public String addFuelDetails(FuelMaintenanceVO addFuellist) {
		FuelMasterService fuelservice = new FuelServiceImpl();
		return fuelservice.addFuelDetails(addFuellist);
	}

	public ArrayList<VehicleDetailsVO> searchVehicle(VehicleDetailsVO searchterm) {
		FuelMasterService fuelservice = new FuelServiceImpl();
		return fuelservice.searchVehicle(searchterm);
	}

	public List<VehicleDetailsVO> getVehicleNoBycode(VehicleDetailsVO searchTerm) {
		FuelMasterService fuelservice = new FuelServiceImpl();
		return fuelservice.getVehicleNoBycode(searchTerm);
	}

	public boolean deleteFuelDetails(FuelMaintenanceVO deleteFueldetails) {
		FuelMasterService fuelservice = new FuelServiceImpl();
		return fuelservice.deleteFuelDetails(deleteFueldetails);
	}

	public FuelMaintenanceVO editFuelDetails(FuelMaintenanceVO editFueldetails) {
		FuelMasterService fuelservice = new FuelServiceImpl();
		return fuelservice.editFuelDetails(editFueldetails);
	}

	public ArrayList<FuelMaintenanceVO> searchFuelDetails(
			FuelMaintenanceVO searchvo) {
		FuelMasterService fuelservice = new FuelServiceImpl();
		return fuelservice.searchFuelDetails(searchvo);
	}

	public List<DriverMasterVO> searchDriver(DriverMasterVO searchTerm) {
		FuelMasterService fuelservice = new FuelServiceImpl();
		return fuelservice.searchDriver(searchTerm);
	}

	public ArrayList<VehicleDetailsVO> getVehicleList() {

		FuelMasterService vehicleList= new FuelServiceImpl();
		return vehicleList.getVehicleList();
	
	}

	public ArrayList<VehicleDetailsVO> getDriverNames() {
		FuelMasterService driverNameList =new  FuelServiceImpl();
		return driverNameList.getDriverNames();
	}

}
