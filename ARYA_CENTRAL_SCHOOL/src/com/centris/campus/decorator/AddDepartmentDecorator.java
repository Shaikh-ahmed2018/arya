package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;
import com.centris.campus.vo.DepartmentMasterVO;

public class AddDepartmentDecorator extends TableDecorator {

	public String getDepartmentCheckBox() {

		DepartmentMasterVO depVo = (DepartmentMasterVO) getCurrentRowObject();

		String departmentCheckBox = "<input class=\"dep_Checkbox_Class\" type=\"checkbox\" id=\"depCheckBox_"
				+ depVo.getDepId()
				+ ","
				+ depVo.getDepName()
				+ ","
				+ depVo.getDesc() + "\"/>";

		return departmentCheckBox;

	}

}
