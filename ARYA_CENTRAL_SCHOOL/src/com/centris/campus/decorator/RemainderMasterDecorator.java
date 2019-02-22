package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.RemainderMasterVO;


public class RemainderMasterDecorator extends TableDecorator 

{

public String getView() {
		
		
	RemainderMasterVO vo = (RemainderMasterVO) getCurrentRowObject();
		
		String result = "<input type=\"checkbox\" class=\"academic_Checkbox_Class\" id=\"academicCheckBox_"
				+ vo.getId()
				+ ","
				+ "\" />";

	
		return result;
	}
	
}
