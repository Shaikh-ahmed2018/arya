package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.AddStageVO;

public class AddStageDecorator extends TableDecorator

{
		
	 public String getCheck()
	 {
		 AddStageVO stageMasterVo=(AddStageVO) getCurrentRowObject();
		 
		String desc=stageMasterVo.getDescription();
		
		if(desc==null)
		
		{
			desc="";
		}
		
		 String chkbx = "<input class=\"stageMaster_Checkbox_Class\" type=\"checkbox\" id=\"stageCheckBox_"
				+ stageMasterVo.getStageCode()
				+ ","
				+ stageMasterVo.getStageName()
				+ ","
				
				+ desc + "\"/>";

		
		
		return chkbx;
	
}

}
