package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.ExaminationDetailsVo;

public class ExaminationDecorator extends TableDecorator{
	
	public String getCheck(){
		
		ExaminationDetailsVo examvo =(ExaminationDetailsVo)getCurrentRowObject();
		
		String  examDetailsCheckBox = "<input type=\"checkbox\" class=\"class_Checkbox_Exam\" id=\"examDetailsCheckBox_" 
				 +examvo.getExamid()+","
				 +examvo.getExamName()+"," 
				 +examvo.getStartDate()+","
				 +examvo.getEndDate()+","
				 +examvo.getAccyear()+","
				 +examvo.getDescription()+"," +"\" />";
		 
		return examDetailsCheckBox;
		
	}
	
	
	
}
