package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;
import com.centris.campus.vo.TransportTypeVO;

public class TransportTypeDecorator extends TableDecorator{
	
	public String getCheckBox(){
		
		TransportTypeVO typevo=(TransportTypeVO)getCurrentRowObject();
	
		String checkBox = "<input class=\"typeDetails_Checkbox_Class\" name='checkboxname' type=\"checkbox\" id=\""+ typevo.getTransptyId()
				+"\"/>";

		return checkBox;
	}
	

}
