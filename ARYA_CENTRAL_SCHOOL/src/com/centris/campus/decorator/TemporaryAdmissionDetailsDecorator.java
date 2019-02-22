package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.ParentRequiresAppointmentVO;

public class TemporaryAdmissionDetailsDecorator extends TableDecorator {

	public String getTemporary_details_checkbox() {

		ParentRequiresAppointmentVO detailsVO = (ParentRequiresAppointmentVO) getCurrentRowObject();

		String streamDetailsCheckBox = "<input class=\"temporary_admission_details_Checkbox_Class\" type=\"checkbox\" id=\"streamDetailsCheckBox_"
				+ detailsVO.getTemporary_id()
				+ ","
				+ detailsVO.getStudentfirstName()
				+ "\"/>";

		return streamDetailsCheckBox;
	}

}
