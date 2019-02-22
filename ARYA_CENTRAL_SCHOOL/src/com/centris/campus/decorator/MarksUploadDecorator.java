package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.StudentRegistrationVo;

public class MarksUploadDecorator extends TableDecorator{
	int cont;
	int count;
	public String getSerialNumber(){
		cont++;
		return "<span class='123' id="+cont+">"+cont+"</span>";
	}
	
	public String getEnterMarks(){
		count++;
		return "<input class='234' id="+count+" ></input>";
	}
	
	String check;

	public String getCheck() {

		String chkbx = "";

		MarksUploadVO detils = (MarksUploadVO) getCurrentRowObject();
		chkbx = "<input type=\"checkbox\" class=\"Checkbox_Class\"  id=\"CheckBox_"
				+ detils.getExamid()
				+ ","
				+ detils.getClassid()
				+ ","
				+ detils.getSectionid()
				+ ","
				+ detils.getSubjectId()
				+ ","
				+ detils.getExamname()
				+ ","
				+ detils.getClassname()
				+ ","
				+ detils.getSectionname()
				+ ","
				+ detils.getSubjectname()
				+ "," + "\" />";

		return chkbx;

	}
}
