package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.forms.SectionForm;
import com.centris.campus.pojo.ClassPojo;

public class SectionDecorator extends TableDecorator{
	
	String check;

	public String getCheck() {

		String chkbx = "";

		SectionForm secData = (SectionForm) getCurrentRowObject();
		chkbx = "<input type=\"checkbox\" class=\"class_Checkbox_Section\" id=\"sectionCheckBox_"
				+ secData.getSectionId()
				+ ","
				+ secData.getSecDetailsId()
				+ ","
				+ secData.getStreamName()
				+ ","
				+ secData.getSecDetailsName()
				+ ","
				+ secData.getSectionStrength()
		 + "\" />";

		return chkbx;

	}

}
