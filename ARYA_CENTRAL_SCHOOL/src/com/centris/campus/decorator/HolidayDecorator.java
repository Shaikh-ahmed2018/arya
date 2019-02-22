package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;


import com.centris.campus.vo.HolidayMasterVo;

public class HolidayDecorator extends TableDecorator{

	
	public String getEdit()
	{
		HolidayMasterVo careersViewVo = (HolidayMasterVo)getCurrentRowObject();
		String date=careersViewVo.getDate();
		String deptid=careersViewVo.getLocId();
		String holid=careersViewVo.getHolId();
		return "<span onclick='callDialgEdit(\""+date+","+deptid+","+holid+"\")'>EDIT</span>";
		
	}
	public String getDelete()
	{
		HolidayMasterVo careersViewVo = (HolidayMasterVo)getCurrentRowObject();
		String date=careersViewVo.getDate();
		String deptid=careersViewVo.getLocId();
		String holid=careersViewVo.getHolId();
		System.out.println(deptid);
		
		return "<span onclick='callDialgDelete(\""+date+","+deptid+","+holid+"\")'>DELETE</span>";
	
	}  


}
