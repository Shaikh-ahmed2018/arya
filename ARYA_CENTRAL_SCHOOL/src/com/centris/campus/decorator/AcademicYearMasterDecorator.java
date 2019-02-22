package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.AcademicYearVO;

public class AcademicYearMasterDecorator extends TableDecorator {

	String check;

	public String getCheck() {

		String chkbx = "";

		AcademicYearVO acdetils = (AcademicYearVO) getCurrentRowObject();
		chkbx = "<input type=\"checkbox\" class=\"academic_Checkbox_Class\" onblur=\"checkcheckbox()\" id=\"academicCheckBox_"
				+ acdetils.getAcadamic_id()
				+ ","
				+ acdetils.getAcadamic_name()
				+ ","
				+ acdetils.getStartDate()
				+ ","
				+ acdetils.getEndDate()
				+ "," + acdetils.getDescription() + "," + "\" />";

		return chkbx;

	}

}
