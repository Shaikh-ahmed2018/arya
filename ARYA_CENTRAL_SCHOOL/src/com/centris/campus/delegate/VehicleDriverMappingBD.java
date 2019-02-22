package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.service.VehicleDriverMappingService;
import com.centris.campus.serviceImpl.VehicleDriverMappingServiceImpl;
import com.centris.campus.vo.DriverVo;
import com.centris.campus.vo.VehicleDriverMappingPojo;
import com.centris.campus.vo.VehicleDriverMappingVo;

public class VehicleDriverMappingBD {
	
	public ArrayList<DriverVo> getAllDriversDetails(){
		
		VehicleDriverMappingService vehicleService=new VehicleDriverMappingServiceImpl();
		
		return vehicleService.getAllDriversDetails();
	}

	public ArrayList<VehicleDriverMappingVo> getAvailableVehicles(){
		
		VehicleDriverMappingService vehicleService=new VehicleDriverMappingServiceImpl();
		
		return vehicleService.getAvailableVehicles();
	}
	
	public ArrayList<VehicleDriverMappingVo> getMappedVehicles(String driverID){
		
		VehicleDriverMappingService vehicleService=new VehicleDriverMappingServiceImpl();
		
		return vehicleService.getMappedVehicles(driverID);
	}
	
	public String mapVehicle(VehicleDriverMappingPojo pojo){
		
		VehicleDriverMappingService vehicleService=new VehicleDriverMappingServiceImpl();
		
		return vehicleService.mapVehicle(pojo);
	}
	
	public ArrayList<VehicleDriverMappingVo> getVehicleDriverMappingList(){
		
		VehicleDriverMappingService vehicleService=new VehicleDriverMappingServiceImpl();
		
		return vehicleService.getVehicleDriverMappingList();
	}
	
	public VehicleDriverMappingVo editVehicleDriverMapping(VehicleDriverMappingPojo pojo){
		
		VehicleDriverMappingService vehicleService=new VehicleDriverMappingServiceImpl();
		
		return vehicleService.editVehicleDriverMapping(pojo);
	}
	
	public String updateMappedVehicle(VehicleDriverMappingPojo pojo){
		
		VehicleDriverMappingService vehicleService=new VehicleDriverMappingServiceImpl();
		
		return vehicleService.updateMappedVehicle(pojo);
	}
	
	public ArrayList<VehicleDriverMappingVo> getSearchVehicleDriverMappingList(String searchTerm){
		
		VehicleDriverMappingService vehicleService=new VehicleDriverMappingServiceImpl();
		
		return vehicleService.getSearchVehicleDriverMappingList(searchTerm);
	}
	
	public String deleteVehicleDriverMapping(VehicleDriverMappingPojo pojo){
		
		VehicleDriverMappingService vehicleService=new VehicleDriverMappingServiceImpl();
		
		return vehicleService.deleteVehicleDriverMapping(pojo);
	}
	
	
}
