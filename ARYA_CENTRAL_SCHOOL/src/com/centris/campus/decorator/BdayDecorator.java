package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.UpcomingBdayVo;

public class BdayDecorator  extends TableDecorator {
	
	public  String getCheck() {
		
		
		
		
		UpcomingBdayVo bdayvo = (UpcomingBdayVo) getCurrentRowObject();

		 String check = "<input  type=\"checkbox\" class=\"class_Checkbox_bday\" id=\"check_"
				 
				 + bdayvo.getBdayDate()+"\","+ "\""
				 + bdayvo.getBdayperson()+"\","+ "\""
				 + bdayvo.getClassname()+"\","+ "\""
				 + bdayvo.getSectionname()+"\","+ "\""
				 + bdayvo.getContent()+"\","+ "\""
				
					+"\" />";
			

		return check;
		
	}

	
	
	
	
}
