package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.Issuedmenuvo;

public class issuedFormDetailsDecorator extends TableDecorator {
		
		
		public String getissued_details_checkbox() {

			Issuedmenuvo detailsVO = (Issuedmenuvo) getCurrentRowObject();

			String issuedDetailsCheckBox = "<input class=\"temporary_admission_details_Checkbox_Class\" type=\"checkbox\" id=\"streamDetailsCheckBox_"
					+ detailsVO.getEnquiryid()
					+ ","
					+ detailsVO.getParentfirstName()
					+ "\"/>";
System.out.println("From dec:" +issuedDetailsCheckBox);
			return issuedDetailsCheckBox;
		}
}
