package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;
import com.centris.campus.vo.AssignmentUploadVo;
import com.centris.campus.vo.ProjectVO;

public class ProjectDecorator extends TableDecorator{

	String check;

	public String getCheck() {

		String chkbx = "";

		ProjectVO details = (ProjectVO) getCurrentRowObject();
		chkbx = "<input type=\"checkbox\" name=\"selectBox\" class=\"Checkbox_Class\"  id=\""
				
				+ details.getProjectcode()
				+ "\" />";

		return chkbx;

	}
}
