package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;



import com.centris.campus.vo.CareersViewVo;

public class CareersApplyDecorator extends TableDecorator{
	
	public String getApply()
	{
		CareersViewVo careersViewVo = (CareersViewVo)getCurrentRowObject();
		
		
		String Id=careersViewVo.getJobcode();
		return " <a href='publicMenu.html?method=applyJob&jobid="+Id+"' class=\"decorator\" ><span>Apply</span></a>";
	
	}
  
	
 
}