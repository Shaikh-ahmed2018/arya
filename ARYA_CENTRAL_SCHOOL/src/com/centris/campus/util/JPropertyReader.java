package com.centris.campus.util;

import java.util.ResourceBundle;


public class JPropertyReader {
	
	public static String getProperty(String key) {
		 
		String PROPERTY_FILE=MessageConstants.prop_dir;
		ResourceBundle rb = ResourceBundle.getBundle(PROPERTY_FILE); 
		String propvalue=rb.getString(key);
		 
		return propvalue;
	}
	public static String  readlog4JProperty(String strKey) 
	{	String PROPERTY_NAME=StringUtilContants.CAMPUS;
		ResourceBundle rb=ResourceBundle.getBundle(PROPERTY_NAME);
		String strPropertyValue=rb.getString(strKey);
		return strPropertyValue;
	}
}
