package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.SuddenHolidaySMSVO;

public class SuddenHolidayDecorator extends TableDecorator{

	String check;

	public String getCheck() {

		String chkbx = "";

		SuddenHolidaySMSVO details = (SuddenHolidaySMSVO) getCurrentRowObject();
		
		System.out.println("student name  ::: "+details.getSuddenholidayscode() );
		
		chkbx = "<input type=\"checkbox\" class=\"Checkbox_Class\"  id=\"CheckBox_"
				+ details.getSuddenholidayscode()
			/*	+ ","
				+ details.getDate()*/
				+ "," + "\" />";
								
		return chkbx;

	}
	
}
