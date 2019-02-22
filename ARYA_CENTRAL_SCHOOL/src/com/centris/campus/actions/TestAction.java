package com.centris.campus.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.centris.campus.delegate.CreateExaminationBD;
import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.NewRegistrationBD;
import com.centris.campus.forms.NewRegistrationForm;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;

public class TestAction extends DispatchAction {
	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);
	
	
	
	
	
	
	
	public ActionForward enquiryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		return mapping.findForward(MessageConstants.ENQUIRY_LIST);
		}
	
	
	
	
	
	public ActionForward newregistration(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		
		return mapping.findForward(MessageConstants.NEWREG);
		}
	
	
	
	
	
	
	
	public ActionForward submitForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		return mapping.findForward("ttest");
	}
	
	
	
	


public ActionForward inflowslist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

	
		
		return mapping.findForward(MessageConstants.REG_LIST);
	}






public ActionForward regsform(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	
	NewRegistrationForm newregform = (NewRegistrationForm)form;
	NewRegistrationBD newregbd = new NewRegistrationBD();
	newregbd.getregDetails(newregform);
	
	
	return mapping.findForward(MessageConstants.REG_LIST);
}




public ActionForward shortAdmission(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	
	
	
	return mapping.findForward(MessageConstants.ADMLIST);
}













	public ActionForward revelist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXPENSES);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXPENSES);
			
			String searchTerm = request.getParameter("searchTerm");
			
			
			List<ExaminationDetailsVo> examvo = new ArrayList<ExaminationDetailsVo>();
			
			ExamDetailsBD examdeligate = new ExamDetailsBD();

			if (searchTerm != null) {

			 examvo = new CreateExaminationBD().searchExamination(searchTerm);

				request.setAttribute("searchexamlist", searchTerm);
			} else {

				
				examvo = examdeligate.getexamdeligate();
				
			}

			

			request.setAttribute("examDetailsList", examvo);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examList Ending");

		return mapping.findForward(MessageConstants.REVENUE_LIST_TEST);
	}
	public ActionForward expenseslist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXPENSES);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXPENSES);
			
			String searchTerm = request.getParameter("searchTerm");
			
			
			List<ExaminationDetailsVo> examvo = new ArrayList<ExaminationDetailsVo>();
			
			ExamDetailsBD examdeligate = new ExamDetailsBD();

			if (searchTerm != null) {

			 examvo = new CreateExaminationBD().searchExamination(searchTerm);

				request.setAttribute("searchexamlist", searchTerm);
			} else {

				
				examvo = examdeligate.getexamdeligate();
				
			}


			request.setAttribute("examDetailsList", examvo);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examList Ending");

		return mapping.findForward(MessageConstants.EXPENSES_LIST_TEST);
	}
	
	public ActionForward getTransactionDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTransactionDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSACTIONS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTransactionDetails Ending");

		return mapping.findForward(MessageConstants.GET_TRANSACTION_LIST_TEST);
	}
	
	
}
