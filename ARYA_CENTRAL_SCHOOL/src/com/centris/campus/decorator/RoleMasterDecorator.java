package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.pojo.RoleMasterPojo;


public class RoleMasterDecorator extends TableDecorator {
String check;
public String getCheckBox(){
	String checkBox="";
	RoleMasterPojo masterPojo=(RoleMasterPojo)getCurrentRowObject();
	//String roleCode=masterPojo.getRoleCode();
	
	//checkBox="<input type='checkbox' name='rolename' class='roleDetails_Checkbox_Class' value='"+roleCode+"' id='"+roleCode+"' />";
	checkBox = "<input class=\"roleDetails_Checkbox_Class\" name='rolename' type=\"checkbox\" id=\""+ masterPojo.getRoleCode()
			+"\"/>";

	return checkBox;
}
}
