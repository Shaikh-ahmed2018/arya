package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.vo.VehicleDetailsVO;

public class ClassDecorator extends TableDecorator{
	
	String check;

	public String getCheck() {

		String chkbx = "";

		ClassPojo classData = (ClassPojo) getCurrentRowObject();
		chkbx = "<input type=\"checkbox\" class=\"class_Checkbox_Class\" id=\"classCheckBox_"
				+ classData.getClassId()
				+ ","
				+ classData.getClassName()
				+ ","
				+ classData.getStreamName()
		 + "\" />";

		return chkbx;

	}

}
