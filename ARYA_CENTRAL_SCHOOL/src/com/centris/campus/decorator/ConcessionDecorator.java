package com.centris.campus.decorator;
import org.displaytag.decorator.TableDecorator;
import com.centris.campus.pojo.ConcessionDetailsPojo;


public class ConcessionDecorator extends TableDecorator {
	
public String getConcessionDetailsCheckBox(){
		
	    ConcessionDetailsPojo detailsVO=(ConcessionDetailsPojo)getCurrentRowObject();
		String concessionDetailsCheckBox="<input class=\"streamDetails_Checkbox_Class\" type=\"checkbox\" id=\"streamDetailsCheckBox_"
		+detailsVO.getConcessionId()+","+detailsVO.getConcessionName()+","+detailsVO.getDescription()+","+detailsVO.getPercentage()+"\"/>";
		return concessionDetailsCheckBox;
	}
}
