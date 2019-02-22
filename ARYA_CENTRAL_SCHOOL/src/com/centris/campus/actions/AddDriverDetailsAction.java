package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.apache.struts.upload.FormFile;
import org.json.JSONObject;

import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.delegate.FeeMasterDelegate;
import com.centris.campus.delegate.TransportBD;
import com.centris.campus.forms.DriverTransportForm;
import com.centris.campus.forms.TransportForm;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.DriverMsaterListVo;
import com.centris.campus.vo.VehicleDetailsVO;

public class AddDriverDetailsAction extends DispatchAction {


	private static final Logger logger = Logger
			.getLogger(AddDriverDetailsAction.class);
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");

	private static String ImageName = res.getString("ImageName");

	public ActionForward addDriver(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : addDriver Starting");


		try {


			String arg = "Add Driver Details";
			request.setAttribute("driverdetails", arg);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_DRIVERMASTER);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : addDriver  Ending");



		return mapping.findForward(MessageConstants.ADD_DRIVER_DETAILS);


	}


	/*public ActionForward savedriverval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		System.out.println("savedriver action");



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : saveDriver Starting");


		System.out.println("savedriver action");


		String status = request.getParameter("result");

		if (status != null) {

			if (status.equalsIgnoreCase("Driver Created Successfully")) {

				request.setAttribute("successmessagediv",
						"Driver Created Successfully");
			}
		}



			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);

			TransportForm formobj = new TransportForm();



			try {	

				 String createUser = HelperClass.getCurrentUserID(request);

			    String license = request.getParameter("license");

				formobj.setLicense(license);
				formobj.setDrivercode(request.getParameter("drivercode"));
				formobj.setDriverName(request.getParameter("name"));
				formobj.setFather_name(request.getParameter("fatherName"));
				formobj.setDateofBirth(request.getParameter("dob"));
				formobj.setGender(request.getParameter("gender"));
				formobj.setMobile(request.getParameter("mobile"));
				formobj.setEmerg_contact(request.getParameter("emerg_contact"));
				formobj.setDateofJoin(request.getParameter("enq_dateofJoin"));
				formobj.setExperience(request.getParameter("exp"));
				formobj.setAddress(request.getParameter("address"));
				formobj.setAge(request.getParameter("age"));
				formobj.setDrivingliecenseNo(request
						.getParameter("drivingLicenseNo"));
				formobj.setDl_issued_date(request.getParameter("dl_issued_date"));
				formobj.setDl_validity(request.getParameter("dlValidity"));
				formobj.setCreateUser(createUser);



				String result = new TransportBD().addDriverBD(formobj);


				JSONObject jsonobj = new JSONObject();

				jsonobj.put("jsonResponse", result);

				response.getWriter().print(jsonobj);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : saveDriver  Ending");

		return mapping.findForward(MessageConstants.ADD_DRIVER_DETAILS);

	}*/

	public ActionForward editDriver(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : editDriver Starting");


		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_DRIVERMASTER);



		try {


			String args = "Modify Driver Details";
			request.setAttribute("driverdetails", args);


			String drivercode = request.getParameter("driverCode");
			System.out.println("drivercode"+drivercode);

			DriverMsaterListVo drivervo = new DriverMsaterListVo();

			drivervo.setDriverCode(drivercode);




			DriverMsaterListVo result = new TransportBD().editDriverBD(drivervo);

			request.setAttribute("driverlist", result);
			request.setAttribute("drivercode", drivercode);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : editDriver  Ending");


		return mapping.findForward(MessageConstants.ADD_DRIVER_DETAILS);



	}

	public ActionForward deleteDriver(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : deleteDriver Starting");


		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);



		try {


			String drivercode  = request.getParameter("driverCode");
			String[] drivervo=drivercode.split(",");

			String check =new TransportBD().deleteDriverBD(drivervo);

			System.out.println("check check is comming"+check);
			JSONObject json= new JSONObject();

			json.put("status", check);

			response.getWriter().print(json);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : deleteDriver  Ending");
		return null;

	}

	public ActionForward validateDriver(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : validateDriver Starting");


		try {
			String drivercode = request.getParameter("drivercode");
			String name = request.getParameter("name");
			String dob = request.getParameter("dob");
			String mob = request.getParameter("mobile");
			String doj = request.getParameter("enq_dateofJoin");


			DriverMsaterListVo drivervo = new DriverMsaterListVo();

			drivervo.setDriverCode(drivercode);
			drivervo.setDriverName(name);
			drivervo.setDateofBirth(dob);
			drivervo.setMobile(mob);
			drivervo.setDateofJoin(doj);


			boolean driver_Available= new TransportBD().validateDriverBD(drivervo);
			JSONObject jsonobject= new JSONObject();

			if(driver_Available){

				jsonobject.put("status", "true");
			}else{
				jsonobject.put("status", "false");
			}
			response.getWriter().print(jsonobject);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}




		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : validateDriver  Ending");
		return null;





	}



	// for license validation//


	public ActionForward validateLicense(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : validateLicense Starting");


		try {
			String drivingliecenseNo = request.getParameter("drivingliecenseNo");
			System.out.println("drivingliecenseNo is "+drivingliecenseNo);

			DriverMsaterListVo drivervo = new DriverMsaterListVo();
			drivervo.setDrivingliecenseNo(drivingliecenseNo);

			boolean License_Available= new TransportBD().validateLicenseBD(drivervo);
			JSONObject jsonobject= new JSONObject();
			jsonobject.put("status", License_Available);
			response.getWriter().print(jsonobject);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}




		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : validateLicense  Ending");
		return null;





	}


	public ActionForward DriverDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : downloadStreamDetailsAction  Starting");

		try {
			System.out.println("DOWNLOADING EXCEL");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/DriverDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			/*List<AddFeeVO> List = new ArrayList<AddFeeVO>();
			List = (List<AddFeeVO>) request.getSession(false)
					.getAttribute("feelistdownload");*/
			List<DriverMsaterListVo> List = new TransportBD()
					.getdriverList();

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					List);
			Map parameters = new HashMap();


			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/DriverDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Driver Details Excel Report" };
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
					request.getRealPath("Reports/DriverDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=DriverDetailsXLSReport.xls");
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
				+ " Control in StreamDetailsAction : downloadStreamDetailsAction   Ending");
		return null;


	}

	public ActionForward DriverDetailsPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeConcession : ConcessionDetailsPDFReport  Starting");

		try {

			System.out.println("downloading pdf");

			/*List<AddFeeVO> Details = new ArrayList<AddFeeVO>();
				Details = (List<AddFeeVO>) request.getSession(false).getAttribute("feelistdownload");
			 */                List<DriverMsaterListVo> Details = new TransportBD()
					 .getdriverList();

			 String sourceFileName = request
					 .getRealPath("Reports/DriverDetailsPDF.jrxml");
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
					 + "DriverDetailsPDF - " + ".pdf\"");

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
				+ " Control in FeeConcession : ConcessionDetailsPDFReport  Ending");
		return null;

	}



	public ActionForward searchDriverDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : searchDriverDetails Starting");


		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);


		try {

			String SearchName = request.getParameter("searchname");

			ArrayList<DriverMsaterListVo> driverlist = new TransportBD().searchDriverBD(SearchName.trim());



			request.setAttribute("driverList", driverlist);
			request.setAttribute("searchname", SearchName);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : searchDriverDetails  Ending");

		return mapping.findForward(MessageConstants.driver_list);

	}

	public ActionForward savedriverval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDriverDetailsAction : savedriverval Starting");

		System.out.println("Action Class Working");

		String path = null;
		int i = 0;  
		File fileURL = null;
		FileOutputStream outputStream = null;


		DriverTransportForm driverform =(DriverTransportForm)form;
		/*TransportForm driverform = new TransportForm();*/

		FormFile file = driverform.getLicensedrive();


		DriverMsaterListVo drivervo  = new DriverMsaterListVo();
		String success=null;

		try {
			String args = "Modify Driver Details";
			request.setAttribute("driverdetails", args);

			String createuser = HelperClass.getCurrentUserID(request);

			driverform.setCreateUser(createuser);

			System.out.println("createuser:::"+createuser);

			try {
				if(driverform.getLicensedrive() == null || driverform.getLicensedrive().getFileSize()==0){
					drivervo.setUploadinglicense(driverform.getLicenseupload()); 
					System.out.println("getLicensedrive action:===> hidden : "+driverform.getLicensedrive());

				}else{

					String extension=null;

					int j = driverform.getLicensedrive().getFileName().lastIndexOf('.');
					if (j >= 0) {
						extension = driverform.getLicensedrive().getFileName().substring(i+1);
					}

					String birthcertificate_path = "FIles/TRANSPORT/DriverLicence_" +driverform.getDriver_code()+ "." +extension;

					System.out.println("birthcertificate_path::"+birthcertificate_path);

					String filePath = request.getRealPath("/") + "FIles/TRANSPORT/DriverLicence_" +driverform.getDriver_code()+ "." +extension;
					if (driverform.getLicensedrive().getFileSize() > 0) {

						byte[] btDataFile = driverform.getLicensedrive().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();

					} else {

						birthcertificate_path = "";
					}


					drivervo.setUploadinglicense(birthcertificate_path);

				}



				drivervo.setDriverName(driverform.getDriverName());	
				drivervo.setFather_name(driverform.getFather_name());	
				drivervo.setDateofBirth(driverform.getDateofBirth());
				drivervo.setGender(driverform.getGender());
				drivervo.setMobile(driverform.getMobile());
				drivervo.setEmerg_contact(driverform.getEmerg_contact());
				drivervo.setDateofJoin(driverform.getDateofJoin());
				drivervo.setExperience(driverform.getExperience());
				drivervo.setAddress(driverform.getAddress());
				drivervo.setDrivingliecenseNo(driverform.getDrivingliecenseNo());
				drivervo.setDl_issued_date(driverform.getDl_issued_date());
				drivervo.setDl_validity(driverform.getDl_validity());
				drivervo.setAge(driverform.getAge());
				drivervo.setStatus(driverform.getStatus());
				drivervo.setDriverCode(driverform.getDriver_code());
				drivervo.setDriving_license_types(driverform.getDriving_license_types());

				System.out.println("getStatus::::"+driverform.getStatus());
				System.out.println("driverform.getDriving_license_types()===="+driverform.getDriving_license_types());

				boolean status = new TransportBD().addDriverBD(drivervo, createuser);
				System.out.println("status in action" + status);

				JSONObject object = new JSONObject();
				object.put("status", status);
				response.getWriter().print(object);


				if(drivervo.getStatus()=="success1")
				{

					success=MessageConstants.DRIVER_SUCCESS;

					request.setAttribute("successmessagediv", success);

				}

				else if(drivervo.getStatus()=="success2")
				{

					success=MessageConstants.DRIVER_FAIL;

					request.setAttribute("errormessagediv", success);


				}
				else if(drivervo.getStatus()=="success3")
				{

					success=MessageConstants.DRIVER_UPDATE_SUCCESS;
					request.setAttribute("successmessagediv", success);


				}else if(drivervo.getStatus()=="success4")
				{

					success=MessageConstants.DRIVER_UPDATE_FAIL;

					request.setAttribute("errormessagediv", success);


				}


			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}



			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AddDriverDetailsAction : savedriverval  Ending");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return mapping.findForward(MessageConstants.ADD_DRIVER_DETAILS);
	}




	public ActionForward downloaddriverlicenc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		System.out.println("ownload file is working");

		try {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentRegistrationAction: downloadDocument Starting");

			try {
				String docPath = request.getParameter("Path");
				response.setContentType("application/octet-stream");
				String fileName = "FileName";
				fileName=docPath;

				response.addHeader("Content-Disposition", "attachment; filename="+ fileName);
				File docFile = new File(request.getRealPath("/") + docPath);
				response.setContentLength((int) docFile.length());

				FileInputStream input = new FileInputStream(docFile);
				BufferedInputStream buf = new BufferedInputStream(input);
				int readBytes = 0;
				ServletOutputStream stream = response.getOutputStream();
				while ((readBytes = buf.read()) != -1) {
					stream.write(readBytes);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentRegistrationAction: downloadDocument Ending");

			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;


	}



}
