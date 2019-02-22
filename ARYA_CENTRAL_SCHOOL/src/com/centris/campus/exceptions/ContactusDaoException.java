package com.centris.campus.exceptions;


import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
 
public class ContactusDaoException extends ExceptionHandler{
 
  private static final Logger log = 
      Logger.getLogger(ContactusDaoException.class);
 
  @Override
  public ActionForward execute(Exception ex, ExceptionConfig ae,
	ActionMapping mapping, ActionForm formInstance,
	HttpServletRequest request, HttpServletResponse response)
	throws ServletException {

		String prop_path=null;
		try {
			prop_path = JLogger.getlog4JPropertyPath();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	   /*configure the given path*/   
PropertyConfigurator.configure(prop_path);
   log.info("--------INFO message-------");
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	log.info("---------log file executed-----------------");

	//log the error message
	log.error("Records nor found for yhids cclass");
 
	return super.execute(ex, ae, mapping, formInstance, request, response);
  }
}
