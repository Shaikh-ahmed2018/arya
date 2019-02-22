package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.AddFeeVO;



public class FeeMasterDecorator extends TableDecorator 

{

	public String getView() {
		
		
		AddFeeVO fee = (AddFeeVO) getCurrentRowObject();
		
		String result = "<input type=\"checkbox\" class=\"academic_Checkbox_Class\" id=\"academicCheckBox_"
				+ fee.getId()
				+ ","
				+ fee.getName()
				+ ","
				+ fee.getDescription()
				+ ","
				+ "\" />";

	
		return result;
	}
	
	
}
