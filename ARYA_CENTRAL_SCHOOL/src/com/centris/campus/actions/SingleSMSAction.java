package com.centris.campus.actions;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import com.centris.campus.daoImpl.SmsDaoIMPL;
import com.centris.campus.delegate.SmsDeligate;
import com.centris.campus.forms.SingleSMSForm;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;

public class SingleSMSAction extends DispatchAction {
	private static final Logger logger = Logger.getLogger(SingleSMSAction.class);
	
	public ActionForward storeSingleSms(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SingleSMSAction: storeSingleSms Starting");
		try
		{
			SingleSMSForm smsform = new SingleSMSForm();
			SmsDaoIMPL smsdao=new SmsDaoIMPL();
			String mobileno = request.getParameter("mobileno");
			String message = request.getParameter("message");
			smsform.setMobileno(mobileno);
			smsform.setMessage(message);
			String result=smsdao.singlesms(smsform);
			JSONObject jsonobj = new JSONObject();
			
			jsonobj.put("jsonResponse", result);
			
			response.getWriter().print(jsonobj);
			//String result=smsdao.;
			//UserRegisterDAO dao = new UserRegisterDAO();
			//String result = new SmsDeligate().addlatecomers(meetingvo);
					//String result = new SmsDeligate().inserthomework(vo);
		}
		 catch (Exception e) {
				logger.error(e.getMessage(),e);
				e.printStackTrace();
			}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  SmsAction  : insertmeeting Ending");
		
	return 	null;	
	}
}
