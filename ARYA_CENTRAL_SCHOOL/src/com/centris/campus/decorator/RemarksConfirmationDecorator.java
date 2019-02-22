package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.UpcomingRemarksVO;

public class RemarksConfirmationDecorator extends TableDecorator{

	public  String getCheck() {

		UpcomingRemarksVO remarksVO = (UpcomingRemarksVO) getCurrentRowObject();

		 String check = "<input  type=\"checkbox\" class=\"class_Checkbox_remark\" id=\"check_"
		
				 
				+remarksVO.getRemarkcode()+	 "\","+ "\""
				+remarksVO.getStreamname()+	 "\","+ "\""
				+remarksVO.getClassname()+	 "\","+ "\""
				+remarksVO.getSectionname()+	 "\","+ "\""
				+remarksVO.getDateId()+	 "\","+ "\""
				+remarksVO.getSubjectName()+	 "\","+ "\""
				+remarksVO.getRemarksto()+	 "\","+ "\""
				+remarksVO.getStudentName()+	 "\","+ "\""
				+remarksVO.getTeacherName()+	 "\","+ "\""
				+remarksVO.getRemarks()+	 "\","+ "\""
				
				
					+"\" />";
			
				
				 
			

	
		 
		return check;
	
	
	
	}
	
}
