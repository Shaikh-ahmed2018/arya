package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.secadmissionformformatVO;

public class SecondAdmissionDetailsDecorator extends TableDecorator{
	
	
	public String getTemporary_details_checkbox() {

		secadmissionformformatVO detailsVO = (secadmissionformformatVO) getCurrentRowObject();

		String streamDetailsCheckBox = "<input class=\"temporary_admission_details_Checkbox_Class\" type=\"checkbox\" id=\"streamDetailsCheckBox_"
				+ detailsVO.getStud_id()
				+ ","
				+ detailsVO.getStu_firstname()
				+ "\"/>";

		return streamDetailsCheckBox;
	}

	


}
