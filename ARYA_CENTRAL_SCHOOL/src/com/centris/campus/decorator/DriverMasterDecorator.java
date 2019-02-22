package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;
import com.centris.campus.vo.DriverMsaterListVo;

public class DriverMasterDecorator extends TableDecorator{
	
	public String getCheck() {
	DriverMsaterListVo pojo=(DriverMsaterListVo)getCurrentRowObject();
	
	String driverDetailsCheckBox="<input class=\"driver_Checkbox_Class\" type=\"checkbox\" id=\"driverDetailsCheckBox_"
			+pojo.getDriverCode()+","+pojo.getType()+","+pojo.getDriverName()+","+pojo.getFather_name()+","+pojo.getDateofBirth()+","+pojo.getAge()+","
			+pojo.getGender()+","+pojo.getMobile()+","+pojo.getEmerg_contact()+","+pojo.getDateofJoin()+","+pojo.getAddress()+","
			+pojo.getDrivingliecenseNo()+","+pojo.getDl_issued_date()+","+pojo.getDl_validity()+","+pojo.getLicense()+","+pojo.getExperience()+"\"/>";
	
	
	
	return driverDetailsCheckBox;
	
	
	
	}
	

}
