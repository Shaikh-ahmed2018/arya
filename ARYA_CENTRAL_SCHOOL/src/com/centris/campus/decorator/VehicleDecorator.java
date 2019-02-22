package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.VehicleDetailsVO;

public class VehicleDecorator extends TableDecorator {

	String check;

	public String getCheck() {

		String chkbx = "";

		VehicleDetailsVO vehicledata = (VehicleDetailsVO) getCurrentRowObject();
		chkbx = "<input type=\"checkbox\" class=\"vehicle_Checkbox_Class\" id=\"VehicleCheckBox_"
				+ vehicledata.getVehiclecode()
				+ ","
				+ vehicledata.getVehicleregno()
				+ ","
				+ vehicledata.getVehicletype()
				+ ","
				+ vehicledata.getTypeofbody()
				+ ","
				+ vehicledata.getMakersname()
				+ ","
				+ vehicledata.getManufacturerdate()
				+ ","
				+ vehicledata.getChassisno()
				+ ","
				+ vehicledata.getSettingcapacity()
				+ ","
				+ vehicledata.getFuelusedintheengine()
				+ ","
				+ vehicledata.getColorofbody()
				+ ","
				+ vehicledata.getVehiclename() + "\" />";

		return chkbx;

	}

}
