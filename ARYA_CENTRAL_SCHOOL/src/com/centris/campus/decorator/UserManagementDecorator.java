package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.UserRecordVO;

public class UserManagementDecorator extends TableDecorator{
	

	public String getSelect() {
		
		UserRecordVO userRecordVO =(UserRecordVO)getCurrentRowObject();
		
		String select="<input type='radio' value="+userRecordVO.getUserId()+" name='userid'></input>";
		
		return select;
	}

	
	
	
	
	
	
	
}
