package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

import com.centris.campus.delegate.ClassFeeSetupBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ClassFeeSetupVo;
import com.centris.campus.vo.ReportMenuVo;


public class ClassFeeSetupAction extends DispatchAction {


	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	private static final Logger logger = Logger.getLogger(ClassFeeSetupAction.class);

	public ActionForward getEditedFeeSetupDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupAction : getFeeSetupDetails Starting");

		String forward="";
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_CLASSFEESETUP);

			String accYear=request.getParameter("accYear");
			String classId=request.getParameter("classId");
			String loc_id =request.getParameter("loc_id");
			
			ClassFeeSetupBD feeSetupBD = new ClassFeeSetupBD();
			
			ArrayList<ClassFeeSetupVo> getActiveFeeMasterSetupDetails = null;
			ArrayList<ClassFeeSetupVo> getSpecializationFeeMasterSetupDetails = null;
			ArrayList<ClassFeeSetupVo> getHeading = null;
			ClassFeeSetupVo cvo=new ClassFeeSetupVo();
			
			ClassFeeSetupPojo feeSetupPojo = new ClassFeeSetupPojo();
			
			feeSetupPojo.setClassId(classId);
			feeSetupPojo.setAcadamicYear(accYear);
			feeSetupPojo.setLocation_id(loc_id);
			request.setAttribute("locationId", loc_id);
			
			try {
				
				getHeading=feeSetupBD.getHeading(feeSetupPojo);
				
				if(getHeading.get(0).getSpecilazationCount()==0){
					
				String location = loc_id;	
				getActiveFeeMasterSetupDetails = feeSetupBD.getAllFees(feeSetupPojo,location);
				request.setAttribute("getActiveFeeMasterSetupDetails",getActiveFeeMasterSetupDetails);
				forward=MessageConstants.FEE_SETUP_ENTRY;
				
				}	
				else{
					getSpecializationFeeMasterSetupDetails = feeSetupBD.getApprovedFees(feeSetupPojo);
					
					
					request.setAttribute("getSpecializationFeeMasterSetupDetails",getSpecializationFeeMasterSetupDetails);
					forward=MessageConstants.FEE_SETUP_ENTRY_WITH_SPECIALIZATION;
				}
				

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			request.setAttribute("getHeading", getHeading);
			
			
			request.setAttribute("classId", classId);
			request.setAttribute("accYear", accYear);
			request.getSession().setAttribute("classidVal", getHeading.get(0).getClassname());
			request.getSession().setAttribute("acadamicYearVal", getHeading.get(0).getAccyear());
			


		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupAction : getFeeSetupDetails Ending");
		
		return mapping.findForward(forward);
	}
	
	public ActionForward insertApproveFees(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupAction : insertApproveFees Starting");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");	

		String uniqueId = request.getParameter("FeeCode");
		String feeId = request.getParameter("FeeIdtoApprove");
		String accyearId=uniqueId.split(",")[0];
		String classId=uniqueId.split(",")[1];
		String term=uniqueId.split(",")[2];
		String specCode=uniqueId.split(",")[3];
		String user = HelperClass.getCurrentUserID(request);
		
		String[] feeId_array = feeId.split(",");
System.out.println("specCode"+specCode);
		ArrayList<ClassFeeSetupPojo> approvefeelist = new ArrayList<ClassFeeSetupPojo>();
		int message = 0;

		try {

			for (int i = 0; i < feeId_array.length; i++) {

				ClassFeeSetupPojo feeSetupPojo = new ClassFeeSetupPojo();

				feeSetupPojo.setClassId(classId);
				feeSetupPojo.setAcadamicYear(accyearId);
				feeSetupPojo.setTerm(term);
				feeSetupPojo.setSpecialization(specCode);
				feeSetupPojo.setFeeId(feeId_array[i]);
				feeSetupPojo.setCreatedby(user);
				

				approvefeelist.add(feeSetupPojo);

			}
			
			message = new ClassFeeSetupBD().insertApproveFees(approvefeelist);
			
			ClassFeeSetupPojo feeSetupPojos = new ClassFeeSetupPojo();
			feeSetupPojos.setClassId(classId);
			feeSetupPojos.setAcadamicYear(accyearId);
			feeSetupPojos.setTerm(term);
			feeSetupPojos.setSpecialization(specCode);
			
			request.setAttribute("getApprovedFeeMasterSetupDetails",
					new ClassFeeSetupBD().getApprovedFees(feeSetupPojos));
			request.setAttribute("getActiveFeeMasterSetupDetails",
					new ClassFeeSetupBD().getAllFees(feeSetupPojos,location));
			
			if (message == 1) {
				
				request.setAttribute("success",
						MessageConstants.FEE_APPROVE_SUCCESS);

			} else {

				request.setAttribute("error",
						MessageConstants.FEE_APPROVE_SUCCESS);
			}
			
			request.getSession().setAttribute("classidVal", classId);
			request.getSession().setAttribute("acadamicYearVal", accyearId);
			request.getSession().setAttribute("termVal", term);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupAction : insertApproveFees Ending");
		return mapping.findForward(MessageConstants.FEE_SETUP_ENTRY);
	}
	
	public ActionForward deleteFees(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupAction : deleteFees Starting");
		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");	

		try {
			String feeSettingsCode = request.getParameter("FeeSettingsCode");
			String FeeCode = request.getParameter("FeeCode");
			String classid = request.getParameter("classid");
			String acadamicYear = request.getParameter("acadamicYear");
			String term = request.getParameter("term");
			String specCode = request.getParameter("specCode");

			ClassFeeSetupPojo feeSetupPojo = new ClassFeeSetupPojo();
			feeSetupPojo.setFeeId(FeeCode);
			feeSetupPojo.setFeesettingCode(feeSettingsCode);
			feeSetupPojo.setClassId(classid);
			feeSetupPojo.setAcadamicYear(acadamicYear);
			feeSetupPojo.setTerm(term);
			feeSetupPojo.setSpecialization(specCode);
			
			boolean status = new ClassFeeSetupBD().deleteFees(feeSetupPojo);

			request.setAttribute("getActiveFeeMasterSetupDetails",
					new ClassFeeSetupBD().getAllFees(feeSetupPojo,location));
			request.setAttribute("getApprovedFeeMasterSetupDetails",
					new ClassFeeSetupBD().getApprovedFees(feeSetupPojo));
			
			request.getSession().setAttribute("classidVal", classid);
			request.getSession().setAttribute("acadamicYearVal", acadamicYear);
			request.getSession().setAttribute("termVal", term);
			
			if (status) {

				request.setAttribute("success",MessageConstants.FEE_SETUP_DELETE_SUCCESS);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupAction : deleteFees Ending");

		return mapping.findForward(MessageConstants.FEE_SETUP_ENTRY);

	}
	
	public ActionForward insertFeeAmount(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupAction : insertFeeAmount Starting");

		int flag = 0;

		try {
			String classid = request.getParameter("classCode");
			String acadamicYear = request.getParameter("academicYearCode");
			String FeeCode = request.getParameter("feeCodeArray");
			String loc_id=request.getParameter("loc_id");
			String specializationCode[]=request.getParameter("specializationCode").split(",");
		System.out.println("acadamicYear="+acadamicYear);
			
			

			String[] feecode_array = FeeCode.split(",");
		
			
			ArrayList<ClassFeeSetupPojo> feeAmountList=new ArrayList<ClassFeeSetupPojo>();

			for (int i = 0; i < feecode_array.length; i++) {

				ClassFeeSetupPojo feeSetupPojo = new ClassFeeSetupPojo();
				feeSetupPojo.setFeeId(feecode_array[i].split("-")[0]);
				feeSetupPojo.setFeeamount(feecode_array[i].split("-")[2]);
				feeSetupPojo.setClassId(classid);
				feeSetupPojo.setAcadamicYear(acadamicYear);
				feeSetupPojo.setLocation_id(loc_id);
				feeSetupPojo.setTerm(feecode_array[i].split("-")[1]);
				if(specializationCode.length>0)
				{
				feeSetupPojo.setSpecialization(specializationCode[i]);
				}
				else
				{
					feeSetupPojo.setSpecialization("-");
				}
				feeSetupPojo.setCreatedby(HelperClass.getCurrentUserID(request));
				feeAmountList.add(feeSetupPojo);
			}
			
			flag = new ClassFeeSetupBD().insertFeeAmount(feeAmountList);
			
			
			JSONObject obj=new JSONObject();
			if (flag>0) {
				
				obj.put("status","true");
				
				
			} else {
				
				obj.put("status","false");
				
			}
			response.getWriter().print(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupAction : insertFeeAmount Ending");

		return null;

	}
	
	public ActionForward downloadclassfeesetupXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupAction : downloadclassfeesetupXLS  Starting");
		
		try {
		
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/classfeesetupdetailsxls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			/*String currentaccyear = request.getSession(false)
					.getAttribute("current_academicYear").toString();
			
			
			ArrayList<ClassFeeSetupVo> classSetupList = new ArrayList<ClassFeeSetupVo>();
			classSetupList = new ClassFeeSetupBD().getFeeSetupDetails(currentaccyear);
			*/
			String currentaccyear = request.getSession(false)
					.getAttribute("current_academicYear").toString();
			String searchTerm = request.getParameter("searchTerm");

			ArrayList<ClassFeeSetupVo> classSetupList = new ArrayList<ClassFeeSetupVo>();

			if (searchTerm == null || "".equalsIgnoreCase(searchTerm)) {

				classSetupList = new ClassFeeSetupBD()
						.getFeeSetupDetails(currentaccyear);

			} else {

				classSetupList = new ClassFeeSetupBD()
						.getSearchFeeSetupDetails(searchTerm, currentaccyear);

			}
			
			
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					classSetupList);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/Classfeesetupdetailsxls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Class Fee Setup Details Excel Report" };
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
					request.getRealPath("Reports/Classfeesetupdetailsxls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Classfeesetupdetailsxls.xls");
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
				+ " Control in ClassFeeSetupAction : downloadclassfeesetupXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward downloadclassfeesetupPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ClassFeeSetupAction : downloadclassfeesetupPDF  Starting");

			try {

				System.out.println("downloading pdf");
			/*	String currentaccyear = request.getSession(false)
						.getAttribute("current_academicYear").toString();
				ArrayList<ClassFeeSetupVo> classSetupList = new ArrayList<ClassFeeSetupVo>();
				classSetupList = new ClassFeeSetupBD()
				.getFeeSetupDetails(currentaccyear);*/
				
				String currentaccyear = request.getSession(false)
						.getAttribute("current_academicYear").toString();
				String searchTerm = request.getParameter("searchTerm");

				ArrayList<ClassFeeSetupVo> classSetupList = new ArrayList<ClassFeeSetupVo>();

				if (searchTerm == null || "".equalsIgnoreCase(searchTerm)) {

					classSetupList = new ClassFeeSetupBD()
							.getFeeSetupDetails(currentaccyear);

				} else {

					classSetupList = new ClassFeeSetupBD()
							.getSearchFeeSetupDetails(searchTerm, currentaccyear);

				}
				
				String sourceFileName = request
						.getRealPath("Reports/classfeesetupDetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						classSetupList);

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
						+ "ClassFeeSetupDetailsPDF - " + ".pdf\"");

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
					+ " Control in ClassFeeSetupAction : downloadclassfeesetupPDF  Ending");
			return null;

		}

	public ActionForward getClassFeeSetupByJs(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getClassFeeSetup Starting");

		try {
		
			String currentaccyear = request.getParameter("accyear");
			String locationId=request.getParameter("locationId");
			ArrayList<ClassFeeSetupVo> classSetupList = new ArrayList<ClassFeeSetupVo>();
			if(locationId==null || locationId.equalsIgnoreCase("")){
				locationId="%%";
			}
			classSetupList = new ClassFeeSetupBD().getFeeSetupDetails(currentaccyear+","+locationId);
			JSONObject obj=new JSONObject();
			obj.put("classSetupList", classSetupList);
			response.getWriter().print(obj);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getClassFeeSetup Ending");

		return null;
	}
	
}