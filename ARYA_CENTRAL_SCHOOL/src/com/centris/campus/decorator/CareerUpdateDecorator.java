package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.CareersViewVo;

public class CareerUpdateDecorator extends TableDecorator {

	String check;

	public String getCheck() {

		String chkbx = "";

		CareersViewVo cardetils = (CareersViewVo) getCurrentRowObject();
		chkbx = "<input type=\"checkbox\" class=\"Checkbox_Class\"  id=\"CheckBox_"
				+ cardetils.getJobcode()
				+ ","
				+ cardetils.getTitle()
				+ ","
				+ "\"   value=\""+cardetils.getStatus()+"\" />";

		return chkbx;

	}

}
