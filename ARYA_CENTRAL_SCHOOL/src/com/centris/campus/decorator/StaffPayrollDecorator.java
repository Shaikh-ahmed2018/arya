package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;
import com.centris.campus.vo.StaffPayrollListVo;

public class StaffPayrollDecorator extends TableDecorator{
	
	String check;

	public String getCheck() {

		String chkbx = "";
		StaffPayrollListVo obj = (StaffPayrollListVo) getCurrentRowObject();
		chkbx = "<input type=\"checkbox\" class=\"vehicle_Checkbox_Class\" id=\""
				+ obj.getMonth_int()+","+obj.getYear() + "\" />";

		return chkbx;

	}

}
