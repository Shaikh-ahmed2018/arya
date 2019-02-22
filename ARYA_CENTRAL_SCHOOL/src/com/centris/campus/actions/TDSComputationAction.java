package com.centris.campus.actions;

import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.centris.campus.daoImpl.StudentRegistrationDaoImpl;
import com.centris.campus.delegate.TDSComputationBD;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.StaffEmployementVo;

public class TDSComputationAction extends DispatchAction {

	private static final Logger logger = Logger
			.getLogger(TDSComputationAction.class);

	public ActionForward AddTdsComputationDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportTermList Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_ITDECLARATION);

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			
			if (academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				System.out.println("HelperClass.getCurrentYearID()"	+ HelperClass.getCurrentYearID());
				academic_year = HelperClass.getCurrentYearID();
			}
			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			if(location.equalsIgnoreCase("all")){
				location="%%";
			}
			
			String User = HelperClass.getCurrentUser(request);
			String currentUserId = HelperClass.getCurrentUserID(request);
			

			String year = new StudentRegistrationDaoImpl().getSingleAcademicYear(academic_year);
			request.setAttribute("academic_year", year);
			
			StaffEmployementVo list = new StaffEmployementVo();
			StaffEmployementVo list1 = new StaffEmployementVo();
			
			list = new TDSComputationBD().getEmployeeDetails(User,currentUserId,academic_year);
			
			request.setAttribute("list", list);
			
			list1 = new TDSComputationBD().getStaffMaximumLimitDetails(academic_year,location);
			
			request.setAttribute("maximumlist", list1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportTermList Ending");

		return mapping.findForward(MessageConstants.ADD_TDS_COMPUTATION_DETAILS);
	}

}
