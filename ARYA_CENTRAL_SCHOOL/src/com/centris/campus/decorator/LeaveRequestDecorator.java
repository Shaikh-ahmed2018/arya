package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.LeaveRequestVo;

public class LeaveRequestDecorator extends TableDecorator {
	
	
	
	public  String getCheck() {
	
		
		LeaveRequestVo vo = (LeaveRequestVo) getCurrentRowObject();
		
		
		
             String check = "<input  type=\"checkbox\" class=\"class_Checkbox_leave\" id=\"check_"
				 
				 
				 +vo.getSno()+ "\","+ "\""
				 +vo.getRequestby()+ "\","+ "\""
				 +vo.getStudentname()+ "\","+ "\""
				 +vo.getRequestto()+ "\","+ "\""
				 +vo.getFromdate()+ "\","+ "\""
				 +vo.getTodate()+ "\","+ "\""
				 +vo.getStarttime()+ "\","+ "\""
				 +vo.getEndtime()+ "\","+ "\""
				 +vo.getTotalleave()+ "\","+ "\""
				 +vo.getReason()+ "\","+ "\""
				 +vo.getLeavetype()+ "\","+ "\""
		
				
				
					+"\" />";
		
		
		
		
		
		
		return null;
		
		
		
		
		
		
	}
	

}
