package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.forms.AddSubjectForm;
import com.centris.campus.vo.ViewallSubjectsVo;

public class ViewAllSubjectDecorator extends TableDecorator {

	String check;

	public String getCheck() {

		String chkbx = "";

		ViewallSubjectsVo obj = (ViewallSubjectsVo) getCurrentRowObject();
		chkbx = "<input type=\"checkbox\" class=\"vehicle_Checkbox_Class\" id=\"VehicleCheckBox_"
				+ obj.getSubjectid() + "\" />";

		return chkbx;

	}

	public String getdownload() {

		ViewallSubjectsVo obj = (ViewallSubjectsVo) getCurrentRowObject();

		String subid = obj.getSubjectid();
		String classname = obj.getClassid();
		String category = obj.getCategory();

		return "<a href='subject.html?method=getsyllabusdownload&subjectid="
				+ subid  
				+ "' ><img id='dwnd1' src='images/download.png'/> </a>";
	}
	
	public String getlabdownload() {

		AddSubjectForm obj = (AddSubjectForm) getCurrentRowObject();

		String subid = obj.getLab_id();

		return "<a href='subject.html?method=getLabsyllabusdownload&labid="
				+ subid  
				+ "' ><img id='dwnd1' src='images/download.png'/> </a>";
	}
	
	

}