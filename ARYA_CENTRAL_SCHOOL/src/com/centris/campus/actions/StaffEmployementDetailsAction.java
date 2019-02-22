package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import com.centris.campus.daoImpl.StudentRegistrationDaoImpl;
import com.centris.campus.delegate.StaffEmployementBD;
import com.centris.campus.delegate.TDSComputationBD;
import com.centris.campus.delegate.TeacherRegistrationBD;
import com.centris.campus.forms.StaffEmployementForm;
import com.centris.campus.util.CalculateStaffSalaryDetails;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.StaffEmployementVo;
import com.centris.campus.vo.UserDetailVO;



public class StaffEmployementDetailsAction extends DispatchAction {
	
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	
	
private static Logger logger = Logger.getLogger(StaffEmployementDetailsAction.class);
	
	public synchronized ActionForward getStaffEmployementEntry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementDetailsAction : getStaffEmployementEntry Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_STAFFRENUMERATION);
			
			String teachercode=request.getParameter("teachercode");
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			
			StaffEmployementVo stafflist=new StaffEmployementBD().getStaffEmployementEntry(teachercode,academic_year);
			
			request.setAttribute("teachercode", teachercode);
			
			request.setAttribute("Stafflist", stafflist);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementDetailsAction : getStaffEmployementEntry Ending");

		return mapping.findForward(MessageConstants.STAFF_EMPLOYEMENT_ENTRY);
	}
	
	public ActionForward calculateEmpSalaryDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
		{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementDetailsAction: calculateEmpSalaryDetails : Starting");
		try{
			
			
			System.out.println("salary calculations start");
			
			double basic =Double.parseDouble((String)request.getParameter("basic"));
				
				System.out.println("basic:::"+basic);
				double hrapercentag=Double.parseDouble((String)request.getParameter("hrapercentag"));
				double otherallowance=0.0;
				double perfincentive=0.0;
				double specialallowance=0.0;
				double shiftallowance=0.0;
				double foodallowance=0.0;
				double washingallowance=0.0;
				
				double standardded=0.0;
				
				
				 if(request.getParameter("otherallowance").trim()==""){
					
				 }else{
					 otherallowance=Double.parseDouble((String)request.getParameter("otherallowance"));
				}
			
				 
				 
				 if(request.getParameter("perfincentive").trim()==""){
						
					}else{
						perfincentive=Double.parseDouble((String)request.getParameter("perfincentive"));
				}
				 
				 
				 
				 
				 if(request.getParameter("specialallowance").trim()==""){
						
					}else{
						specialallowance=Double.parseDouble((String)request.getParameter("specialallowance"));
				}
				 
				 
				 if(request.getParameter("foodallowance").trim()==""){
						
					}else{
						foodallowance=Double.parseDouble((String)request.getParameter("foodallowance"));
							
				}
				 
				 
				 
				 if(request.getParameter("washingallowance").trim()==""){
						
					}else{
						washingallowance=Double.parseDouble((String)request.getParameter("washingallowance"));
							
				}
				 
				
							 
				 if(request.getParameter("standardded").trim()==""){
					 
				}else{
					standardded=Double.parseDouble((String)request.getParameter("standardded"));
				}
			
				 
				 
				/* 
				Calculate_emp_payroll payroll_details=new Calculate_emp_payroll();
				
				ArrayList<EmployeeSalaryDetailsVo> list=new ArrayList<EmployeeSalaryDetailsVo>();
				
				EmployeeSalaryDetailsVo emp_sal_vo=new EmployeeSalaryDetailsVo();*/
				 
				 CalculateStaffSalaryDetails payroll_details=new CalculateStaffSalaryDetails();
				 
				ArrayList<StaffEmployementVo> list=new ArrayList<StaffEmployementVo>();
					
				StaffEmployementVo emp_sal_vo=new StaffEmployementVo();
				
				
				if(request.getParameter("ca").trim()=="0.0"){
					
					System.out.println("in");
					emp_sal_vo.setCa(Double.parseDouble((String)request.getParameter("ca")) );

					
				}else{
					System.out.println("out");
					
					emp_sal_vo.setCa(MessageConstants.cca);

				}
				
				
				
				
				
				if(request.getParameter("medicalallowances").trim()==""){
					
					
					emp_sal_vo.setMedicalallowances(Double.parseDouble((String)request.getParameter("medicalallowances")));
					
				}else{
					
					emp_sal_vo.setMedicalallowances(MessageConstants.MEDICA_REIMUBURSEMENT);


				}
				
				
				
				
				if(request.getParameter("telephonereim").trim()==""){
					
					emp_sal_vo.setTelephonereim(Double.parseDouble((String)request.getParameter("telephonereim")));
					
				}else{
					
					emp_sal_vo.setTelephonereim(MessageConstants.TELEPHON_REUMBURSEMENT);

				}
				
				
				emp_sal_vo.setHra(payroll_details.cal_Hra(basic,hrapercentag));
				emp_sal_vo.setPerfincentive(perfincentive);
/*				emp_sal_vo.setShiftallowance(shiftallowance);
*/				emp_sal_vo.setSpecialallowance(specialallowance);
				emp_sal_vo.setFoodallowance(foodallowance);
				emp_sal_vo.setWashingallowance(washingallowance);
				emp_sal_vo.setOthers(otherallowance);
				
				emp_sal_vo.setEmployerpf(payroll_details.cal_Empr_Pf(basic));
				
				
				
				emp_sal_vo.setGrosssalary(basic+emp_sal_vo.getCa()+emp_sal_vo.getHra()+emp_sal_vo.getMedicalallowances()+emp_sal_vo.getOthers()+emp_sal_vo.getTelephonereim()
						+emp_sal_vo.getPerfincentive() +emp_sal_vo.getSpecialallowance()+ emp_sal_vo.getFoodallowance() +
						emp_sal_vo.getWashingallowance());
				
				/*System.out.println("GROSS::::::::"+(basic+emp_sal_vo.getCa()+emp_sal_vo.getHra()+emp_sal_vo.getMedicalallowances()+emp_sal_vo.getOthers()+emp_sal_vo.getTelephonereim()
						+emp_sal_vo.getPerfincentive() +emp_sal_vo.getSpecialallowance()+ emp_sal_vo.getFoodallowance() +
						emp_sal_vo.getWashingallowance()));*/
				
				emp_sal_vo.setEmployerESI(payroll_details.cal_Empr_ESI(basic,emp_sal_vo.getGrosssalary()));
				emp_sal_vo.setEmployeepf(payroll_details.cal_Emp_Pf(basic));
				
				emp_sal_vo.setPt(payroll_details.cal_Pt(emp_sal_vo.getGrosssalary()));
				
				emp_sal_vo.setEmployeeESI(payroll_details.cal_Emp_ESI(basic,emp_sal_vo.getGrosssalary()));
				emp_sal_vo.setCtc(emp_sal_vo.getGrosssalary()+emp_sal_vo.getEmployerpf()+emp_sal_vo.getEmployerESI());
				
				
				emp_sal_vo.setTdsctc(emp_sal_vo.getCtc() * 12);
				
				 if(request.getParameter("standardded").trim()==""){
					 
						emp_sal_vo.setStandarddedu(standardded);

						
				}else{
					
					emp_sal_vo.setStandarddedu(MessageConstants.STANDARD_DEDUCTIONS);

				}
			
				 
				 double total = emp_sal_vo.getStandarddedu() + emp_sal_vo.getEmployeepf() * 12 + emp_sal_vo.getPt() * 12 ;
				if(emp_sal_vo.getTdsctc() > emp_sal_vo.getStandarddedu())
				
				{
					
					emp_sal_vo.setTaxbleincomestatury(emp_sal_vo.getTdsctc() - emp_sal_vo.getStandarddedu());
					emp_sal_vo.setTdspf(emp_sal_vo.getEmployeepf() * 12);
					emp_sal_vo.setTdspt(emp_sal_vo.getPt() * 12);
				
					if(emp_sal_vo.getTdsctc() > total){
						
						emp_sal_vo.setTaxableincome(emp_sal_vo.getTaxbleincomestatury() - emp_sal_vo.getTdspf() - emp_sal_vo.getTdspt());
						emp_sal_vo.setTaxpayble(payroll_details.cal_tax_payable(emp_sal_vo.getTaxableincome()));
						
						emp_sal_vo.setIncometax(Math.round(emp_sal_vo.getTaxpayble()/12));
					}
				
				}
				
				
				
				list.add(emp_sal_vo);
			
				
				JSONObject object=new JSONObject();
				object.put("employeesalarydetails", list);
				response.getWriter().print(object);
				
				
			
		}catch(Exception e){
			
			e.printStackTrace();
			logger.error(e);
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementDetailsAction: calculateEmpSalaryDetails : Ending");

		return null;	
	}
	
	public synchronized ActionForward saveStaffSalaryDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementDetailsAction : saveStaffSalaryDetails Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			
			StaffEmployementForm staffForm=(StaffEmployementForm)form;
			String currentUser=HelperClass.getCurrentUserID(request);
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			UserDetailVO usrvo = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String location=usrvo.getLocationid();
			
			if (academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			
			staffForm.setAccyearId(academic_year);
			staffForm.setLocationId(location);
			staffForm.setCreatedby(currentUser);
			String status=new StaffEmployementBD().saveStaffSalaryDetails(staffForm);
			
			
			if("true".equalsIgnoreCase(status)){
				
				request.setAttribute("successmessagediv", "Salary details saved successfully");
			}else{
				
				request.setAttribute("errorMessage", "Salary details not saved,Try later");
			}
			
			
			
			ArrayList<AllTeacherDetailsVo> list = new ArrayList<AllTeacherDetailsVo>();
			list = new TeacherRegistrationBD().getAllTeacherDetails();
			request.setAttribute("allTeacherDetailsList", list);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementDetailsAction : saveStaffSalaryDetails Ending");

		return mapping.findForward(MessageConstants.STAFF_EMPLOYEMENT);
	}

	public ActionForward validateEmployeePfNumber(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeSalaryDetailsAction: validateEmployeePfNumber : Starting");
		try{
			
			System.out.println("validateEmployeePfNumber");
			
			String employeepfnumber=request.getParameter("employeepfno");
			String empid=request.getParameter("empid");
					
			
			boolean status=new StaffEmployementBD().validateEmployeePfNumber(employeepfnumber,empid);
			JSONObject object=new JSONObject();
			object.put("status", status);
			
			response.getWriter().print(object);
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			logger.error(e);
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeSalaryDetailsAction: validateEmployeePfNumber : Ending");

		return null;	
	}
	
	public ActionForward validateBankAccNumber(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeSalaryDetailsAction: validateEmployeePfNumber : Starting");
		try{
			System.out.println("validateBankAccNumber");
			
			String bankaccnumber=request.getParameter("bankaccnumber");
			String empid=request.getParameter("empid");
			
			boolean status=new StaffEmployementBD().validateBankAccNumber(bankaccnumber,empid);
			JSONObject object=new JSONObject();
			object.put("status", status);
			
			response.getWriter().print(object);
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			logger.error(e);
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeSalaryDetailsAction: validateEmployeePfNumber : Ending");

		return null;	
	}
	
	
	
	public ActionForward downloadStaffEmploymentDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementDetailsAction : downloadStaffEmploymentDetailsXLS  Starting");
		
		try {
		
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/staffemploymentdetailsxls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			ArrayList<AllTeacherDetailsVo> list = new ArrayList<AllTeacherDetailsVo>();
			list = new TeacherRegistrationBD().getAllTeacherDetails();
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/staffemploymentdetailsxls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Staff Employment Details Excel Report" };
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,
					sheetNames);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
					Boolean.FALSE);

			exporter.exportReport();

			pdfxls = new File(
					request.getRealPath("Reports/staffemploymentdetailsxls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StaffEmploymentDetailsxls.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
		
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementDetailsAction : downloadStaffEmploymentDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward downloadStaffEmploymentDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffEmployementDetailsAction : downloadStaffEmploymentDetailsPDF  Starting");

			try {

				System.out.println("downloading pdf");

				ArrayList<AllTeacherDetailsVo> Details = new ArrayList<AllTeacherDetailsVo>();
				Details = new TeacherRegistrationBD().getAllTeacherDetails();
				
				String sourceFileName = request
						.getRealPath("Reports/staffemploymentPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						Details);

				String PropfilePath = getServlet().getServletContext().getRealPath(
						"")
						+ "\\images\\" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				
				parameters.put("Image", PropfilePath);


				/*parameters.put("Image", clientImage);

				parameters.put("ClientName", ClientName);

				parameters.put("ClientAddress", ClientAddress_l1);*/

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StaffEmploymentPDF - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();

			}

			catch (Exception e)

			{
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffEmployementDetailsAction : downloadStaffEmploymentDetailsPDF  Ending");
			return null;

		}
	public synchronized ActionForward saveStaffIncomeTaxTDSDeductionDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementDetailsAction : saveStaffIncomeTaxTDSDeductionDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_ITDECLARATION);

			StaffEmployementForm staffForm=(StaffEmployementForm)form;
			String currentUser=HelperClass.getCurrentUserID(request);
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			UserDetailVO usrvo = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String location=usrvo.getLocationid();
			if (academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}

			staffForm.setAccyearId(academic_year);
			staffForm.setLocationId(location);
			staffForm.setCreatedby(currentUser);
			String status=new StaffEmployementBD().saveStaffIncomeTaxTDSDeductionDetails(staffForm);


			if("true".equalsIgnoreCase(status)){

				request.setAttribute("successmessagediv", "IT Declaration Saved Successfully");
			}else{

				request.setAttribute("errorMessage", "IT Declaration Not Saved,Try later");
			}

			UserDetailVO user=(UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userId=user.getUserId();
			String userType=user.getUserNametype();


			StaffEmployementVo list = new StaffEmployementVo();
			StaffEmployementVo list1 = new StaffEmployementVo();

			list = new TDSComputationBD().getEmployeeDetails(userType,userId,academic_year);

			request.setAttribute("list", list);

			list1 = new TDSComputationBD().getStaffMaximumLimitDetails(academic_year,location);

			request.setAttribute("maximumlist", list1);

			String year = new StudentRegistrationDaoImpl().getSingleAcademicYear(academic_year);

			request.setAttribute("academic_year", year);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementDetailsAction : saveStaffIncomeTaxTDSDeductionDetails Ending");

		return mapping.findForward(MessageConstants.TDS_COMPUTATION_DETAILS);
	}
	
}
