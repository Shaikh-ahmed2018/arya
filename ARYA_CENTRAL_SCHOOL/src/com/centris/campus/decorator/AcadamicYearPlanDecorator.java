package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.AcadamicYearPlanVO;
import com.centris.campus.vo.TransportTypeVO;

public class AcadamicYearPlanDecorator extends TableDecorator{
	
	public String getCheckBox(){
		
		AcadamicYearPlanVO planvo=(AcadamicYearPlanVO)getCurrentRowObject();
	
		String checkBox = "<input class=\"typeDetails_Checkbox_Class\" name='checkboxname' type=\"checkbox\" id=\""+ planvo.getEventid()
				+"\"/>";

		return checkBox;
	}

}
