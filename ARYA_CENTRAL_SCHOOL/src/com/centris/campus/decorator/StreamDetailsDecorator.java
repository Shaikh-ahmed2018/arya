package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.StreamDetailsVO;

public class StreamDetailsDecorator  extends TableDecorator {
	
public String getStreamDetailsCheckBox(){
		
	StreamDetailsVO detailsVO=(StreamDetailsVO)getCurrentRowObject();
	String streamDetailsCheckBox="<input class=\"streamDetails_Checkbox_Class\" type=\"checkbox\" id=\"streamDetailsCheckBox_"
	+detailsVO.getStreamId()+","+detailsVO.getStreamName()+","+detailsVO.getDescription()+"\"/>";
	return streamDetailsCheckBox;
}

}
