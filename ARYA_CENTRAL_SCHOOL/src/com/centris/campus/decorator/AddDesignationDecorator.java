package com.centris.campus.decorator;



import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.AddDesignationVO;

public class AddDesignationDecorator extends TableDecorator {
	


	public String getDesignationCheckBox() {
		
		
			
		 AddDesignationVO designationvo=(AddDesignationVO)getCurrentRowObject();
			
			String desc=designationvo.getDesgdes();
			if(desc==null){
				desc="";
			}
			
			 String designationCheckBox = "<input class=\"designationDetails_Checkbox_Class\" type=\"checkbox\" id=\"desgCheckBox_"
					+ designationvo.getDesgid()
					+ ","
					+ designationvo.getDesgname()
					+ ","
					+ desc + "\"/>";

			
			
			return designationCheckBox;
		
	}
	
	

}
