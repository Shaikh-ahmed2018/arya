package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.TermMasterVo;

public class TermMasterDecorator extends TableDecorator 

{

	public String getView() {
		
		
		TermMasterVo term = (TermMasterVo) getCurrentRowObject();
		
		String result = "<span name=\"select\" class=\"academic_Checkbox_Class\" id=\"academicCheckBox_"
				+ term.getTermid()
				+ ","
				+ "\" >"+term.getSno()+"</span>";

	
		return result;
	}
	
	
}
