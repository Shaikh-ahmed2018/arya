package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.LeaveBankVO;

public class LeaveBankDecorator extends TableDecorator{


	public String getCheck() {
			
			
				
			LeaveBankVO leavebankvo=(LeaveBankVO)getCurrentRowObject();
						
		 			 
				 String leavebankCheckBox="<input class=\"leavebankdetails_Checkbox_Class\" type=\"checkbox\" id=\"leaveCheckBox_"
							+leavebankvo.getAcademicyear()+","
							+leavebankvo.getAccyear()+","
						    +leavebankvo.getTotalleaves()+","
						    +leavebankvo.getSno()+","
							+leavebankvo.getPermonth()+"\"/>";
					
				System.out.println("getAcademicyear "+ leavebankvo.getAcademicyear());
				System.out.println("getTotalleaves"+leavebankvo.getTotalleaves());
				System.out.println("getPermonth"+leavebankvo.getPermonth());
				
				return leavebankCheckBox;
			
		}
		
		

	}

