package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;
import com.centris.campus.vo.AssignmentUploadVo;

public class AssignmentDecorator extends TableDecorator{

	String check;

	public String getCheck() {

		String chkbx = "";

		AssignmentUploadVo detils = (AssignmentUploadVo) getCurrentRowObject();
		chkbx = "<input type=\"checkbox\" name=\"selectBox\" class=\"Checkbox_Class\"  id=\""
				
				+ detils.getAssignmentId()
				+ "\" />";

		return chkbx;

	}
}
