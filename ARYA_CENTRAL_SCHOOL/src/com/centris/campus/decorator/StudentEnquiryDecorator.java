package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.StudentEnquiryVo;

public class StudentEnquiryDecorator extends TableDecorator {

	String check;

	public String getCheck() {

		String chkbx = "";

		StudentEnquiryVo Enquirydetils = (StudentEnquiryVo) getCurrentRowObject();
		chkbx = "<input type=\"checkbox\" class=\"enquiry_Checkbox_Class\" onblur=\"checkcheckbox()\" id=\"enquiryCheckBox_"
				+ Enquirydetils.getEnq_Id()
				+ ","
				+ Enquirydetils.getEnq_fullname() + "," + "\" />";

		return chkbx;

	}

}