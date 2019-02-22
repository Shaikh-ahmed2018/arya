package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.FuelMaintenanceVO;

public class FuelMaintenanceDecorator extends TableDecorator {
	
		
		public String getCheck() {
			
			
			FuelMaintenanceVO pojo = (FuelMaintenanceVO) getCurrentRowObject();
			
			String fueldetailsCheckBox ="<input class=\"fuelDetails_Checkbox_Class\" type=\"checkbox\" id=\"fuelDetailsCheckBox_"
			+ pojo.getFuelCode()+ "," 
			+ pojo.getDate() + "," 
			+ pojo.getDrivername()+","
			+ pojo.getFuelType()+","
			+ pojo.getLocation()+","
			+ pojo.getMeterReading()+","
			+ pojo.getPrice()+","
			+ pojo.getQuantity()+","
			+ pojo.getVehiclename()+","
			+ pojo.getVehiclenumber()+","
			+ pojo.getVehicleCode()+","
			+ pojo.getDriverCode()+"\"/>";
			
			
			return fueldetailsCheckBox;

			}
		

	}



