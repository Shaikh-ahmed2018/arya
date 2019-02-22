package com.centris.campus.actions;

import java.awt.print.PrinterJob;
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

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import org.json.JSONObject;

import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.StudentRegistrationDaoImpl;
import com.centris.campus.daoImpl.TransportDaoImpl;
import com.centris.campus.delegate.ExamDetailsBD;

import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StageDelegateBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.StudentTransferCertifivateReportBD;
import com.centris.campus.delegate.TermMasterDelegate;
import com.centris.campus.delegate.TransportBD;
import com.centris.campus.forms.TransportCategoryForm;
import com.centris.campus.forms.TransportForm;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.TransportPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;

import com.centris.campus.vo.AddStageVO;
import com.centris.campus.vo.DriverMsaterListVo;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TermMasterVo;
import com.centris.campus.vo.TransportVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.VehicleDetailsVO;
import com.centris.campus.vo.VehicleTypeVo;


public class TransportAction extends DispatchAction {
	private static final Logger logger = Logger
			.getLogger(TransportAction.class);
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	public ActionForward addvehicledetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : addvehicledetails Starting");

		try {
			
			String title = "Vehicle Details";
			request.setAttribute("vehicledetails", title);
			
			String succesmessage = request.getParameter("message");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_VEHICLEMASTER);

			request.setAttribute("successmessagediv", succesmessage);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : addvehicledetails Ending");

		return mapping.findForward(MessageConstants.ADD_VEHICLE);
	}

	public ActionForward saveVehicleDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : saveVehicleDetails Starting");
		
		
		String args = "Vehicle Details";
		request.setAttribute("vehicledetails", args);
		String path = null;
		int i = 0; 
		
		File fileURL = null;
		FileOutputStream outputStream = null;
		TransportForm vehicliform =(TransportForm)form;
		VehicleDetailsVO vehiclivo = new VehicleDetailsVO();
		
		try {

				String createuser = HelperClass.getCurrentUserID(request);
				String vehiclecode = IDGenerator.getPrimaryKeyID("transport_vehicle");
				vehicliform.setVehiclecode(vehiclecode);
				vehicliform.setCreateuser(createuser);

				try {
					System.out.println("Rcfile text box Value: "+vehicliform.getRcfile());
					System.out.println("Rcfile text box Value: size:  "+vehicliform.getRcfile().getFileSize());
					if(vehicliform.getRcfile() == null || vehicliform.getRcfile().getFileSize()==0){
						vehiclivo.setRcfile(vehicliform.getHrcfileid()); 
						System.out.println("Rcfile1 action:===> hidden : "+vehicliform.getHrcfileid());
						
					}
					else{


						String extension=null;

						int j = vehicliform.getRcfile().getFileName().lastIndexOf('.');

						if (j >= 0) {
							extension = vehicliform.getRcfile().getFileName().substring(i+1);
						}


						String rcfilepath = "FIles/TRANSPORT/"+vehiclecode+"."+extension;

						String filePath = request.getRealPath("/") + "FIles/TRANSPORT/" + vehiclecode+ "." +extension;

						//System.out.println("Action Class--> RC filePath: "+filePath);

						if (vehicliform.getRcfile().getFileSize() > 0) {

							byte[] btDataFile = vehicliform.getRcfile().getFileData();
							File of = new File(filePath);
							FileOutputStream osf = new FileOutputStream(of);
							osf.write(btDataFile);
							osf.flush();

						}
						else{

							rcfilepath = ""; 

						}

						/* vehicliform.setRcfile1(rcfilepath);*/
						
						vehiclivo.setRcfile(rcfilepath); 
						System.out.println("Rcfile1 action:===> "+rcfilepath);

					}


				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}

				
				try{
					System.out.println("getInsurancefile text box Value: "+vehicliform.getInsurancefile());
					
					
					if(vehicliform.getInsurancefile() == null || vehicliform.getInsurancefile().getFileSize() == 0){
						vehiclivo.setInsurancefile(vehicliform.getHinsurancefileid()); 
						System.out.println("Insurancefile1 action: hidden====>     "+vehicliform.getHinsurancefileid());
					}
					else{


						String extension=null;	

						int j = vehicliform.getInsurancefile().getFileName().lastIndexOf('.');

						if (j >= 0) {
							extension = vehicliform.getInsurancefile().getFileName().substring(i+1);
						}
						String insurancefilepath = "FIles/TRANSPORT/Insfile_"+vehiclecode+"."+extension;
						String filePath = request.getRealPath("/") + "FIles/TRANSPORT/Insfile_" + vehiclecode+ "." +extension;

						//System.out.println("INSURANCE filePath"+ filePath);

						if (vehicliform.getInsurancefile().getFileSize() > 0) {

							byte[] btDataFile = vehicliform.getInsurancefile().getFileData();
							File of = new File(filePath);
							FileOutputStream osf = new FileOutputStream(of);
							osf.write(btDataFile);
							osf.flush();

						}
						else{

							insurancefilepath = ""; 

						}
						
						vehiclivo.setInsurancefile(insurancefilepath); 
						System.out.println("Insurancefile1 action:====>     "+insurancefilepath);
					
						
					}

				} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}


				vehiclivo.setUpdateVehicleCode(vehicliform.getUpdatevehicleCode());	
				vehiclivo.setVehicleregno(vehicliform.getVehicleregno());	
				vehiclivo.setVehicletype(vehicliform.getVehicletype());
				vehiclivo.setMakersname(vehicliform.getMakersname());
				vehiclivo.setChassisno(vehicliform.getChassisno());
				vehiclivo.setFuelusedintheengine(vehicliform.getFuelengine());
				vehiclivo.setVehiclename(vehicliform.getVehiclename());
				vehiclivo.setTypeofbody(vehicliform.getTypeofbody());
				vehiclivo.setManufacturerdate(vehicliform.getManufacturerdate());
				vehiclivo.setSettingcapacity(vehicliform.getSettingcapacity());
				vehiclivo.setColorofbody(vehicliform.getColorofbody());
				vehiclivo.setIssueddate(vehicliform.getIssueddate());
				vehiclivo.setCompanyname(vehicliform.getCompanyname());
				vehiclivo.setDoneby(vehicliform.getDoneby());
				vehiclivo.setDriverCode(vehicliform.getDriverName());
				vehiclivo.setRoutecodeid(vehicliform.getRoutename());
				vehiclivo.setExpirydate(vehicliform.getExpirydate());
				vehiclivo.setDoneby(vehicliform.getDoneby());
				vehiclivo.setDriverName(vehicliform.getDriverName());
				vehiclivo.setExperience(vehicliform.getExperience());
				vehiclivo.setDl_issued_date(vehicliform.getDl_issued_date());
				vehiclivo.setLicensetodrive(vehicliform.getLicensetodrive());
				vehiclivo.setMobile(vehicliform.getMobile());
				vehiclivo.setDateofJoin(vehicliform.getDateofJoin());
				vehiclivo.setDl_validity(vehicliform.getDl_validity());
				vehiclivo.setDrivingliecenseNo(vehicliform.getDrivingliecenseNo());
				vehiclivo.setRoutename(vehicliform.getRoutename());
				vehiclivo.setHalttime(vehicliform.getHalttime());
				vehiclivo.setTotaldistance(vehicliform.getTotaldistance());
				vehiclivo.setRouteno(vehicliform.getRouteno());
				vehiclivo.setDestination(vehicliform.getDestination());
				vehiclivo.setTotalstops(vehicliform.getTotalstops());
				vehiclivo.setStatus(vehicliform.getStatus());
				vehiclivo.setEnginenumber(vehicliform.getEnginenumber());
				vehiclivo.setTaxpaid(vehicliform.getTaxpaid());
				vehiclivo.setTaxexpirydate(vehicliform.getTaxexpirydate());	
				vehiclivo.setPollution(vehicliform.getPollution());
				vehiclivo.setFc(vehicliform.getFc());
				vehiclivo.setPermitvalidity(vehicliform.getPermitvalidity());


				
				System.out.println("Actin class: Rc file: "+vehicliform.getRcfile());
				System.out.println("Actin class: insurance file : "+vehicliform.getInsurancefile());
				System.out.println("Actin class: Tax expiry date : "+vehicliform.getTaxexpirydate());
				

				System.out.println("getVehiclecode::"+vehicliform.getVehiclecode());

				String status = new TransportBD().saveVehicleDetails(vehiclivo, createuser, vehiclecode);
				
				System.out.println("status in action" + status);
				if(status=="save"){
					request.setAttribute("successmessagediv", "Adding Record Progressing...");
				}else if(status == "update"){
					request.setAttribute("successmessagediv", "Updating Record Progressing...");
				}
				else{
					request.setAttribute("errormessagediv1", "Vehicle Details not Added");
				}

				JSONObject object = new JSONObject();
				object.put("status", status);
				response.getWriter().print(object);

			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
	     	
		request.setAttribute("vehicleidDetails", vehicliform);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : saveVehicleDetails  Ending");

		return mapping.findForward(MessageConstants.ADD_VEHICLE);
	}

	public ActionForward checkingVehicleInsuranceDate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : checkingVehicleInsuranceDate Starting");
		try {

			System.out.println("checking vehicle details issued date ");

			VehicleDetailsVO vehiclecode = new VehicleDetailsVO();

			vehiclecode.setVehiclecode(request.getParameter("vehiclename"));
			vehiclecode.setIssueddate(request.getParameter("issueddate"));
			vehiclecode.setExpirydate(request.getParameter("expirydate"));

			boolean status = new TransportBD()
					.checkingVehicleInsuranceDate(vehiclecode);

			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : checkingVehicleInsuranceDate  Ending");

		return null;
	}

	public ActionForward getSingleVehicleDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getSingleVehicleDetails Starting");

		try {
			
			String args = "Modify Vehicle Details";
			request.setAttribute("vehicledetails", args);
			String vehiclecode = request.getParameter("vehicleIdlist");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_VEHICLEMASTER);

			System.out.println("edit vehile"+vehiclecode);
			
			VehicleDetailsVO vehicleDetails = new TransportBD()
					.getSingleVehicleDetails(vehiclecode);

			DriverMsaterListVo driverDetails = new TransportBD()
					.getSingleDriverDetails(vehiclecode);
			
			System.out.println("Action class Driver Code: "+driverDetails.getDriverCode());

			TransportVo RouteDetails = new TransportBD()
					.getRouteDetails(vehiclecode);
			
			
			request.setAttribute("vehiclecode", vehiclecode);
			request.setAttribute("vehicleDetails", vehicleDetails);
			request.setAttribute("driverDetails", driverDetails);
			request.setAttribute("RouteDetails", RouteDetails);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getSingleVehicleDetails Ending");

		return mapping.findForward(MessageConstants.ADD_VEHICLE);
	}

	public ActionForward deleteVehicleDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : deleteVehicleDetails Starting");

		try {

			System.out.println("delete action");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);
			
			
			String vehiclecode = request.getParameter("vehicleIdlist");
			System.out.println(vehiclecode);
			String[] code=vehiclecode.split(",");
			
			System.out.println("get Parameter Values:" +code.length);
			
			String status = new TransportBD().deleteVehicleDetails(code);
			

			System.out.println("delete status " + status);

			 request.setAttribute("vehicleDetails", status); 

			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : deleteVehicleDetails Ending");

		return null;
	}

	public ActionForward registernumberValidation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : registernumberValidation Starting");
		try {

			String vehicleregno = request.getParameter("vehicleregno");

			boolean status = new TransportBD()
					.registernumberValidation(vehicleregno);

			JSONObject object = new JSONObject();
			object.put("status", status);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : registernumberValidation  Ending");

		return null;
	}

	public ActionForward updateregisternumberValidation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : updateregisternumberValidation Starting");
		try {

			String vehicleregno = request.getParameter("vehicleregno");
			String vehicleCode = request.getParameter("vehicleCode");

			boolean status = new TransportBD().updateregisternumberValidation(
					vehicleregno, vehicleCode);
			JSONObject object = new JSONObject();
			object.put("status", status);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : updateregisternumberValidation  Ending");

		return null;
	}

	public ActionForward chassisnovalidationvalidation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : chassisnovalidationvalidation Starting");
		try {

			String chassisno = request.getParameter("chassisno");

			boolean status = new TransportBD()
					.chassisnovalidationvalidation(chassisno);

			JSONObject object = new JSONObject();
			object.put("status", status);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : chassisnovalidationvalidation  Ending");

		return null;
	}

	public ActionForward updatechassisnovalidation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : chassisnovalidationvalidation Starting");
		try {

			String chassisno = request.getParameter("chassisno");
			String vehicleCode = request.getParameter("vehicleCode");

			boolean status = new TransportBD().updatechassisnovalidation(
					chassisno, vehicleCode);

			JSONObject object = new JSONObject();
			object.put("status", status);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : chassisnovalidationvalidation  Ending");

		return null;
	}

	public ActionForward searchvehicledetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchvehicledetails Starting");
		System.out.println("search vehicle");
		try {
			String SearchName = request.getParameter("searchname");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);

			ArrayList<VehicleDetailsVO> getvehiclelist = new TransportBD()
					.searchvehicledetails(SearchName);
			request.setAttribute("getvehiclelist", getvehiclelist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchvehicledetails Ending");

		return mapping.findForward(MessageConstants.VEHICLE_LIST);
	}

	public ActionForward checkforduplicateAddTime(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : checkforduplicateAddTime Starting");
		try {
			VehicleDetailsVO obj = new VehicleDetailsVO();
			obj.setVehicleregno(request.getParameter("vehicleregno"));
			obj.setVehiclename(request.getParameter("vehiclename"));
			obj.setVehiclename(request.getParameter("enginenumber"));
			obj.setChassisno(request.getParameter("chassisno"));
			obj.setVehicletype(request.getParameter("vehicletype"));
			obj.setTaxpaid(request.getParameter("taxpaid"));
			obj.setPollution(request.getParameter("pollution"));
			boolean status = new TransportBD().checkforduplicateAddTime(obj);

			JSONObject object = new JSONObject();
			object.put("status", status);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : checkforduplicateAddTime  Ending");

		return null;
	}

	public ActionForward checkforduplicateUpdateTime(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : checkforduplicateUpdateTime Starting");
		try {
			VehicleDetailsVO obj = new VehicleDetailsVO();
			obj.setVehicleregno(request.getParameter("vehicleregno"));
			obj.setVehiclename(request.getParameter("vehiclename"));
			obj.setVehicletype(request.getParameter("enginenumber"));
			obj.setTypeofbody(request.getParameter("chassisno"));
			obj.setMakersname(request.getParameter("vehicletype"));
			obj.setManufacturerdate(request.getParameter("manufacturerdate"));
			obj.setChassisno(request.getParameter("chassisno"));
			obj.setFuelusedintheengine(request.getParameter("fuelengine"));
			obj.setVehiclecode(request.getParameter("vehicleCode"));
			obj.setTaxpaid(request.getParameter("taxpaid"));
			obj.setPollution(request.getParameter("pollution"));
			boolean status = new TransportBD().checkforduplicateUpdateTime(obj);

			JSONObject object = new JSONObject();
			object.put("status", status);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : checkforduplicateUpdateTime  Ending");

		return null;
	}

	public ActionForward removeMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : removeMessage Starting");
		System.out.println("remove message");
		try {
			/* request.setAttribute("errormessagediv", null); */
			request.setAttribute("successmessagediv", "");
			response.getWriter().print("Message Removed");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : removeMessage  Ending");
		return null;
	}

	// Route master Start

	public ActionForward addRouteScreen(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : addRouteScreen Starting");
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_ROUTEMASTER);
			

			String title = "New Route Master";
			request.setAttribute("Route", title);
			request.setAttribute("haccyear", request.getParameter("accyear"));
			AddStageVO vo = new AddStageVO();

			vo.setStageName(request.getParameter("searchvalue"));
			vo.setAccyearCode(request.getParameter("accyear"));
			ArrayList<AddStageVO> list = new StageDelegateBD().SelectAllSatges(vo);

			request.setAttribute("StageDetails", list);
			
			
			
			
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : addRouteScreen  Ending");

		return mapping.findForward(MessageConstants.ADDROUTESCREEN);

	}

		
	

	public ActionForward insertRouteMasterDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RouteSetupAction : insertRouteMasterDetails Starting");
		try {

			TransportPojo tpMasterPojo = new TransportPojo();
			String routeName = request.getParameter("routeName");
			String routeNo = request.getParameter("routeNo");
			String routeLogicName = request.getParameter("routeLogicName");
			/*String destination = request.getParameter("destination");*/
			String halttime = request.getParameter("haltTime");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String totalStops = request.getParameter("totalStops");
			String totalDistance = request.getParameter("totalDistance");
			String stagesid=request.getParameter("stagesidArray");
			String stagesidArray[]=stagesid.split(",");
			System.out.println(stagesidArray.length+"     "+stagesid);
			String createUser = HelperClass.getCurrentUserID(request);
			tpMasterPojo.setStagesidArray(stagesidArray);
			tpMasterPojo.setHalttime(halttime);
			tpMasterPojo.setEndTime(endTime);
			tpMasterPojo.setRouteLogicName(routeLogicName);
			tpMasterPojo.setRouteName(routeName);
			tpMasterPojo.setRouteNo(routeNo);
			/*tpMasterPojo.setDestination(destination);*/
			tpMasterPojo.setStartTime(startTime);
			tpMasterPojo.setTotalDistance(totalDistance);
			tpMasterPojo.setTotalStops(totalStops);
			tpMasterPojo.setAccyear(request.getParameter("accyear"));
			tpMasterPojo.setCreateUser(createUser);
			tpMasterPojo.setHrouteCode(request.getParameter("hRouteCode"));
			
			
			if(request.getParameter("hRouteCode").equalsIgnoreCase("")) {
				if ("NULL".equalsIgnoreCase(request.getParameter("routeid"))) {
					tpMasterPojo.setRouteCode(IDGenerator.getPrimaryKeyID("transport_route"));
					tpMasterPojo.setCheck("NULL");
				} else {
					tpMasterPojo.setRouteCode(request.getParameter("routeid"));
				}
			}
			else {
				tpMasterPojo.setRouteCode(request.getParameter("hRouteCode"));
			}
			

			String status = new TransportBD().insertRouteMasterDetails(tpMasterPojo);
			
			

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("status", status);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RouteSetupAction : insertRouteMasterDetails Ending");
		return null;
	}

	public ActionForward editRouteMasterDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportSetupAction : editRouteMasterDetails Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_ROUTEMASTER);
			String args = "Modify Route Master";
			request.setAttribute("Route", args);
			

			/*tpMasterPojo.setRouteCode(request.getParameter("Code"));*/
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);
			String routecode = request.getParameter("routeIdlist");
			String accyear=request.getParameter("accyear");
			TransportPojo tpMasterPojo = new TransportPojo();
			TransportVo masterDetails = new TransportBD().editRouteMasterDetails(routecode);
			
			tpMasterPojo.setRouteCode(routecode);
			tpMasterPojo.setAccyear(accyear);
			System.out.println("=====================");
			List<TransportVo> Stages=new TransportBD().editRouteStages(tpMasterPojo,accyear);
			
			
			System.out.println("stage size in Action class: "+Stages.size());
			
			List<TransportVo> stageDetails=new TransportBD().unmappedRouteStages(tpMasterPojo);
			
			
			System.out.println("unmappedRouteStages size in Action class: "+Stages.size());
			request.setAttribute("masterDetails", masterDetails);
			
			request.setAttribute("StageDetails", stageDetails);
			request.setAttribute("haccyear", accyear);
			request.setAttribute("unmappedStages", Stages);
			
			// for popup //
			
			AddStageVO vo = new AddStageVO();

			vo.setStageName(request.getParameter("searchvalue"));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RouteSetupAction : editRouteMasterDetails Ending");
		return mapping.findForward(MessageConstants.ADDROUTESCREEN);
	}

	public ActionForward removeRouteMasterDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RouteSetupAction : removeRouteMasterDetails Starting");
		try {

			String routecode = request.getParameter("routeIdlist");
			
			String[] code=routecode.split(",");
			

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);

		String status = new TransportBD().removeRouteMasterDetails(code);

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("status", status);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RouteSetupAction : removeRouteMasterDetails Ending");
		return null;
	}

	public ActionForward addRoute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RouteSetupAction : addRoute Starting");

		TransportForm routeForm = (TransportForm) form;

		routeForm.setCurrentUser(HelperClass.getCurrentUser(request));

		boolean status = new TransportBD().addRoute(routeForm);

		List<TransportVo> getTpMasterDetails = new TransportBD().getTransportMasterDetails(HelperClass.getCurrentYearID());

		if (status) {
			request.setAttribute("message", "Route Details Saved SuccessFully");
		} else {
			request.setAttribute("error",
					"Route Details not-Saved, Please try again");
		}

		request.setAttribute("getTpMasterDetails", getTpMasterDetails);
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_TRANSPORT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_TRANSPORT);

		return mapping.findForward(MessageConstants.TRANSPORTMASTER);
	}

	public ActionForward checkrouteNo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : checkrouteNo Starting");
		try {
			TransportPojo Pojo = new TransportPojo();

			Pojo.setRouteNo(request.getParameter("routeNo"));
			Pojo.setRouteCode(request.getParameter("routeid"));
			Pojo.setAccyear(request.getParameter("accyear"));
			boolean status = new TransportBD().checkrouteNo(Pojo);

			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : checkrouteNo  Ending");

		return null;
	}

	public ActionForward stopDetailsScreen(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : stopDetailsScreen Starting");
		try {
			String stopdetail = request.getParameter("stopdetails");

			String temp[] = stopdetail.split(",");

			TransportVo transportVo = new TransportVo();

			transportVo.setTotalStops(temp[0]);
			transportVo.setRouteCode(temp[1]);
			transportVo.setHalttime(temp[2]);

			System.out
					.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
							+ temp[2]);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);

			request.setAttribute("stopdetails", transportVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : stopDetailsScreen  Ending");

		return mapping.findForward(MessageConstants.STOPDETAILSSCREEN);

	}

	// Route master End

	public ActionForward getDriverDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getDriverDetails Starting");
		try {

			ArrayList<DriverMsaterListVo> drivernamelist = new TransportBD()
					.getDriverNamesDetails();

			JSONObject object = new JSONObject();
			object.put("drivernamelist", drivernamelist);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getDriverDetails  Ending");

		return null;
	}

	public ActionForward getDriverEntireDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getDriverEntireDetails Starting");
		try {

			ArrayList<DriverMsaterListVo> driverlist = new TransportBD()
					.getDriverEntireDetails(request.getParameter("driverid"));
			
			System.out.println(request.getParameter("driverid"));

			JSONObject object = new JSONObject();
			object.put("driverlist", driverlist);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getDriverEntireDetails  Ending");

		return null;
	}

	public ActionForward getDriverDetailsWhileUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getDriverDetailsWhileUpdate Starting");
		try {


			/*ArrayList<DriverMsaterListVo> drivernamelist = new TransportBD()
					.getDriverDetailsWhileUpdate(request
							.getParameter("vehicleIdlist"));*/

			ArrayList<DriverMsaterListVo> drivernamelist = new TransportBD().getDriverDetailsWhileUpdate(request.getParameter("vehicleCode"));


			JSONObject object = new JSONObject();
			object.put("drivernamelist", drivernamelist);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getDriverDetailsWhileUpdate  Ending");

		return null;
	}

	public ActionForward getStopNames(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getStopNames Starting");
		try {

			ArrayList<StageMasterVo> list = new TransportBD().getStopNames(request.getParameter("searchTerm"));

			JSONObject object = new JSONObject();
			object.put("stopslist", list);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getStopNames  Ending");

		return null;
	}

	public ActionForward getRouteDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getRouteDetails Starting");
		try {
			String transferlocation = request.getParameter("value");
			System.out.println("transferlocation is "+transferlocation);
			
			ArrayList<TransportVo> routelist = new TransportBD().getRouteDetailsByLocation(transferlocation);

			JSONObject object = new JSONObject();
			object.put("routelist", routelist);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getRouteDetails  Ending");

		return null;
	}
	
	
	// for new method getting  route data //
	
	
	public ActionForward getrouteDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getRouteDetails Starting");
		try {
			
			
			ArrayList<TransportVo> routelist = new TransportBD().getRouteDetailsByName();

			JSONObject object = new JSONObject();
			object.put("routelist", routelist);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getRouteDetails  Ending");

		return null;
	}

	public ActionForward GetRouteEntireDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : GetRouteEntireDetails Starting");
		try {

			ArrayList<TransportVo> routelist = new TransportBD()
					.GetRouteEntireDetails(request.getParameter("routeid"));

			JSONObject object = new JSONObject();
			object.put("routelist", routelist);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : GetRouteEntireDetails  Ending");

		return null;
	}
	
	public ActionForward VehicleDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : VehicleDetailsXLS  Starting");
		
		try {
		System.out.println("DOWNLOADING EXCEL");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/VehicleDetailsXLSReport1.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			/*List<VehicleDetailsVO> List = new ArrayList<VehicleDetailsVO>();
			List = (List<VehicleDetailsVO>) request.getSession(false).getAttribute("vehiclelistdownload");
*/
			ArrayList<VehicleDetailsVO> List = new TransportBD()
			.getAllvehicleDetails();
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					List);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/VehicleDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "vehicle Details Excel Report" };
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
					request.getRealPath("Reports/VehicleDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=VehicleDetailsXLSReport.xls");
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
				+ " Control in TransportAction : VehicleDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward VehicleDetailsPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in TransportAction : VehicleDetailsPDFReport  Starting");

			try {

				System.out.println("downloading pdf");

				/*List<VehicleDetailsVO> Details = new ArrayList<VehicleDetailsVO>();
				Details = (List<VehicleDetailsVO>) request.getSession(false).getAttribute("vehiclelistdownload");
*/
				ArrayList<VehicleDetailsVO> Details = new TransportBD()
				.getAllvehicleDetails();
				
				
				
				
				String sourceFileName = request
						.getRealPath("Reports/VehicleDetailsPDF.jrxml");
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
						+ "VehicleDetailsPDF - " + ".pdf\"");

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
					+ " Control in TransportAction : VehicleDetailsPDFReport  Ending");
			return null;

		}
	
	public ActionForward RouteDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : RouteDetailsXLS  Starting");
		
		try {
		System.out.println("DOWNLOADING EXCEL");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			
			
			
			
			String sourceFileName = request
					.getRealPath("Reports/RouteDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			/*List<AddFeeVO> List = new ArrayList<AddFeeVO>();
			List = (List<AddFeeVO>) request.getSession(false)
					.getAttribute("feelistdownload");*/
			
			List<TransportVo> List=new ArrayList<TransportVo>();
			List = new TransportBD().getTransportMasterDetailsXLS();
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					List);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/RouteDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Route Details XLS Report" };
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
					request.getRealPath("Reports/RouteDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=RouteDetailsXLSReport.xls");
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
				+ " Control in TransportAction : RouteDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward RouteDetailsPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in TransportAction : RouteDetailsPDFReport  Starting");

			try {

				System.out.println("downloading pdf");

				List<TransportVo> Details=new ArrayList<TransportVo>();
				Details = new TransportBD().getTransportMasterDetailsXLS();
				
				String sourceFileName = request
						.getRealPath("Reports/RouteDetailsPDFReport.jrxml");
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
						+ "RouteDetailsPDFReport - " + ".pdf\"");

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
					+ " Control in TransportAction : RouteDetailsPDFReport  Ending");
			return null;

		}
	
	
	
	public ActionForward downloadInsuranceFile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : downloadInsuranceFile  Starting");
		
		
		try {
			
			
			String inspath = request.getParameter("Path");
			
			System.out.println("inspath "+inspath);
			
		/*	String filepath = new ParentExamdetailsBD().getfeedbackfilepath(inspath);*/
			
			System.out.println("filepath "+inspath);
			
			String fileName = "FileName";
			fileName=inspath;
			
			
			
			
			response.addHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			File docFile = new File(request.getRealPath("/") + inspath);
			response.setContentLength((int) docFile.length());

			FileInputStream input = new FileInputStream(docFile);
			BufferedInputStream buf = new BufferedInputStream(input);
			int readBytes = 0;
			ServletOutputStream stream = response.getOutputStream();
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
				+ " Control in TransportAction : downloadInsuranceFile  Ending");
		return null;
	}
		
		
	public ActionForward downloadRcFile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : downloadRcFile  Starting");
		
		
try {
			
			
			String rcpath = request.getParameter("Path");
			
			/*String filepath = new ParentExamdetailsBD().getfeedbackfilepath(rcpath);*/
			
			System.out.println("filepath "+rcpath);
			
			String fileName = "FileName";
			fileName=rcpath;
			
			
			
			
			response.addHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			File docFile = new File(request.getRealPath("/") + rcpath);
			response.setContentLength((int) docFile.length());

			FileInputStream input = new FileInputStream(docFile);
			BufferedInputStream buf = new BufferedInputStream(input);
			int readBytes = 0;
			ServletOutputStream stream = response.getOutputStream();
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
				+ " Control in TransportAction : downloadRcFile  Ending");
		
		return null;
	}
		
		
		
		
	// separete transport add termdetails//
	
	public ActionForward addtSeparateTransportTermdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{

		String status = request.getParameter("result");

		if (status != null) {

			if (status.equalsIgnoreCase(MessageConstants.SuccesstermMsg)) {

				request.setAttribute("successmessagediv",
						MessageConstants.SuccesstermMsg);
			}
			if (status.equalsIgnoreCase(MessageConstants.SuccesstermUpMsg)) {

				request.setAttribute("successmessagediv",
						MessageConstants.SuccesstermUpMsg);
			}
		}

		String accyear = (String) request.getSession(false).getAttribute(
				"current_academicYear");
		TermMasterDelegate delegates = new TermMasterDelegate();
		TermMasterVo vo = new TermMasterVo();

		vo.setAccid(accyear);

		try

		{
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_TERMSETUP);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			String locationId="%%";
			String date=HelperClass.getCurrentSqlDate().toString();
			String statuss = delegates.separatedateOverLapValidate(date,accyear,locationId);
			
			if(statuss==null || statuss.equalsIgnoreCase(null) || statuss.equalsIgnoreCase("")){
				statuss=Integer.toString(HelperClass.getPastDateofAcademicYear(request)+1);
			}
			
			TermMasterDelegate delegate = new TermMasterDelegate();
			
			
			String enddate=Integer.toString(HelperClass.getForDateofAcademicYear(request));
			TermMasterVo acclist = delegate.getaccdetails(vo);
			request.setAttribute("statuss", statuss);
			request.setAttribute("acclist", acclist);
			request.setAttribute("enddate", enddate);

		}

		catch (Exception e)

		{

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : addtSeparateTransportTermdetails Ending");

		return mapping.findForward(MessageConstants.ADD_SEPARATE_TRANSPORT_TERM);

	}
	
	
	
	
	// separate term for transport//
	

public ActionForward addtermSeparatefeedetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : addtermfeedetails Starting");

		
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMIN);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_ADMIN);

			TermMasterVo vo = new TermMasterVo();
			vo.setTermid(request.getParameter("id"));
			vo.setTermname(request.getParameter("name"));
			vo.setDescription(request.getParameter("description"));
			vo.setStartdate(request.getParameter("startdate"));
			vo.setEnddate(request.getParameter("enddate"));
			vo.setAccyear(request.getParameter("academic_year"));
			vo.setLocationId(request.getParameter("locationId"));
			vo.setTransporttype(request.getParameter("transId"));
			vo.setCreateuser(HelperClass.getCurrentUserID(request));

			TermMasterDelegate delegate = new TermMasterDelegate();

			String result = delegate.addtermSeparatefeedetails(vo);

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
				+ " Control in TermMasterAction : addtermfeedetails Ending");

		return null;

	}
	
	
	
	
public ActionForward deleteSeparateTermDetails(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response)

{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TermMasterAction : deleteTermDetails Starting");

	String result = "";

	
	String name = request.getParameter("getDataArray");//Name should be according to js name4
	String getDataArray[]=name.split(",");

	try {

		TermMasterVo vo = new TermMasterVo();
		vo.setGetDataArray(getDataArray);//-------------5

		

		result = new TermMasterDelegate().deleteSeparateTermDetails(vo);

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("jsonResponse", result);

		response.getWriter().println(jsonObject);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TermMasterAction : deleteTermDetails Ending");

	return null;

}
	
	// Separate term date validation


	
	
public ActionForward getTermnamecount(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)

{

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : getTermnamecount Starting");

	String name = request.getParameter("name");
	String accyear = request.getParameter("accyear");
	String id = request.getParameter("id");
	String locationId=request.getParameter("locationId");

	boolean status = false;

	try {

		TermMasterVo vo = new TermMasterVo();

		vo.setTermname(name);
		vo.setAccyear(accyear);
		vo.setTermid(id);
		vo.setLocationId(locationId);

		TermMasterDelegate delegate = new TermMasterDelegate();

		status = delegate.getTermnamecount(vo);

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("message", status);

		response.getWriter().println(jsonObject);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : getTermnamecount Ending");

	return null;

}
	
public ActionForward getStudentBusCard(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : getStudentBusCard Starting");
	
	try{
		
		String stuId = request.getParameter("stuId");
		String classId = request.getParameter("ClassId");
		String sectionId = request.getParameter("sectionId");
		
		String term = request.getParameter("termId").trim();
		String accyear = request.getParameter("accyear");
		String locid = request.getParameter("locid");
		String locName =new ExamDetailsBD().getlocationname(locid);
		String date = HelperClass.convertDatabaseToUI(HelperClass.getCurrentSqlDate().toString());
		TransportVo obj = new TransportVo();
		obj.setStudentId(stuId);
		obj.setClassId(classId);
		obj.setSectionId(sectionId);
		obj.setTermId(term);
		obj.setLoc_id(locid);
		obj.setAcy_id(accyear);
		ArrayList<TransportVo> list = new TransportBD().getStudentBusCardDetails(obj);
		List<TermMasterVo> termList = new TermMasterDelegate().getTermDetails(accyear,locid);
		String sourceFileName = null;
		String termName = null;
		
		
		if(term.equalsIgnoreCase(termList.get(0).getTermid()) ){
			termName = "Term1";
		sourceFileName = request
				.getRealPath("Reports/buspasscard.jrxml");
		}
		else if(term.equalsIgnoreCase(termList.get(1).getTermid().trim())){
			termName = "Term2";
			sourceFileName = request
					.getRealPath("Reports/buspasscard2term.jrxml");
		}
		else if(term.equalsIgnoreCase(termList.get(2).getTermid())){
			termName = "Term3";
			sourceFileName = request
					.getRealPath("Reports/buspasscard3term.jrxml");
		}
		JasperDesign design = JRXmlLoader.load(sourceFileName);

		JasperReport jasperreport = JasperCompileManager
				.compileReport(design);
		
		
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				list);

		String PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();

		String schName = res.getString("SchoolName");
		String schAddLine1 = res.getString("AddressLine1");

		Map parameters = new HashMap();
		parameters.put("locname", locName);
		parameters.put("date", date);
		byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,parameters,beanColDataSource);

		response.setContentType("application/pdf");

		response.setContentLength(bytes.length);

		response.setHeader("Content-Disposition", "outline; filename=\""
				+ "buspasscard"+termName+".pdf\"");

		ServletOutputStream outstream = response.getOutputStream();

		outstream.write(bytes, 0, bytes.length);

		outstream.flush();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}
public ActionForward transportFeeSearch(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){


	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction    : studentSearch Starting");
	
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

	String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
	UserDetailVO userDetailVO = (UserDetailVO) request
			.getSession(false).getAttribute("UserDetails");
	String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	String userType = userDetailVO.getUserNametype();
	String userCode = userDetailVO.getUserId();
	String location = "%%";
	try {
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_TRANSPORTREQUESTORCANCEL);
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);

		List<StudentRegistrationVo> List = null;
		List = new StudentRegistrationDelegate().getStudentDetails1(
				userType, userCode, academic_year,schoolLocation);
	if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
		academic_year = HelperClass.getCurrentYearID();
	}
	   String currentlocation =null;
	 if(schoolLocation.equalsIgnoreCase("all")){
		   schoolLocation="%%";
		   request.setAttribute("currentlocation", "All");
		   request.setAttribute("locationId",schoolLocation);
	   }
	  else{
		   currentlocation =new ExamDetailsBD().getlocationname(schoolLocation);
		   request.setAttribute("currentlocation", currentlocation);
		   request.setAttribute("locationId",schoolLocation);
	   }
	 
	 request.setAttribute("accyear",academic_year);
	
	ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
	request.setAttribute("locationList", locationList);

	ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
	request.setAttribute("AccYearList", accYearList);


	List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
	request.setAttribute("classlist", classlist);
	


	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);

	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : studentSearch Ending");

	return mapping.findForward(MessageConstants.TRANSPORT_FEE_STUDENT_LIST);
}
public ActionForward getStudentSearchByTransport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
return null;	
}

	public ActionForward settransportstudentwise(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_TRANSPORTREQUESTORCANCEL);
		String studentid = request.getParameter("student_id");
		String loc_id = request.getParameter("loc_id");
		String class_id = request.getParameter("classid");
		String sectionid = request.getParameter("sectionid");
		String acy_id = request.getParameter("acy_id");
		String accyear = (String) request.getSession(false).getAttribute("current_academicYear");

		TransportVo tvo = new TransportVo();
		tvo.setStudentId(studentid);
		tvo.setLoc_id(loc_id);
		tvo.setClassId(class_id);
		tvo.setSectionId(sectionid);
		tvo.setAcy_id(acy_id);
		TransportVo tvolist = new TransportBD().gettransportdetailsstudentwise(tvo);

		String currentlocation = new ExamDetailsBD().getlocationname(loc_id);
		String accyname = new ExamDetailsBD().getaccyName(acy_id);

		request.setAttribute("currentlocation", currentlocation);
		request.setAttribute("accyname", accyname);
		request.setAttribute("accyearid", acy_id);
		request.setAttribute("locid", loc_id);
		request.setAttribute("stuid", studentid);
		request.setAttribute("studentDetailsList", tvolist);
		ArrayList<TransportVo> termtransport = new TransportBD().settranporttermdetailsforstudent(tvo);
		request.setAttribute("termdetailslist", termtransport);

		ArrayList<TransportVo> mappingList = new TransportDaoImpl().getmappingList(tvo);
		request.setAttribute("mappingList", mappingList);
		
		
		ArrayList<TransportVo> monthList = new TransportBD().getMonthList(accyear,loc_id);
		request.setAttribute("monthList", monthList);

		return mapping.findForward(MessageConstants.SET_TRANSPORT_DETAILS_FOR_INDIVIDUAL_STUDENT);
	}
public ActionForward getRouteNameList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	try
	{
	 String loc_id =request.getParameter("locationid");
	 TransportVo tvo = new TransportVo();
	 tvo.setLoc_id(loc_id);
	 ArrayList<TransportVo>  routelist = new TransportBD().getRouteNamelist(tvo);
	
	 JSONObject jsonObject = new JSONObject(routelist);
	 jsonObject.accumulate("routelist", routelist);
	 response.getWriter().print(jsonObject);
	 
	
	}
	 catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	return null;
}


public ActionForward getstoplist(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	try
	{
	 String route_id =request.getParameter("routeid");
	 String accyear=request.getParameter("accyear");
	 TransportVo tvo = new TransportVo();
	 
	 tvo.setRouteCode(route_id);
	 tvo.setAccyear(accyear);
	 
	 ArrayList<TransportVo>  stoplist = new TransportBD().getstoplist(tvo);
	 JSONObject jsonObject = new JSONObject(stoplist);
	 jsonObject.accumulate("stoplist", stoplist);
	 response.getWriter().print(jsonObject);
	 
	
	}
	 catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	return null;
}
public ActionForward getamount(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	try
	{
	  String stageid =request.getParameter("stageid");
	  TransportVo tvo = new TransportVo();
	  tvo.setStage_id(stageid);
	String  getamount = new TransportBD().getamountandstatus(tvo);
	System.out.println("get");
	  System.out.println("from action it reurns or not:" +getamount);
	  JSONObject obj=new JSONObject();
	  obj.put("getamount", getamount);
	  response.getWriter().print(obj);
	}
	 catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	return null;
}
public ActionForward savetransportrequest(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : savetransportrequest Starting");
	try{
		
		TransportPojo pojo = new TransportPojo();
		pojo.setStuid(request.getParameter("stuid"));
		pojo.setAccyear(request.getParameter("accyear"));
		pojo.setLocid(request.getParameter("locid"));
		pojo.setRouteCode(request.getParameter("routeid"));
		pojo.setStageid(request.getParameter("stageid"));
		pojo.setStmonth(request.getParameter("stmonths"));
		pojo.setEndmonth(request.getParameter("endmonth"));
		pojo.setMonthCount(request.getParameter("monthCount"));
		
		String  savetransport = new TransportBD().savetransportrequest(pojo);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("status",savetransport);
		response.getWriter().print(jsonobj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : savetransportrequest Ending");
	 return null;
}
public ActionForward getStudentListByLoc(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationAction    : searchAllAcademicYearDetails Starting");
	
	String accYear = request.getParameter("accyear");
	String locationId = request.getParameter("location");
	
		
	List<StudentRegistrationVo> list= null;
	
	try {
			if(locationId.equalsIgnoreCase("all") && !(accYear.equalsIgnoreCase("all"))){
			locationId="%%";
			list = new TransportBD().searchAllAcademicYearDetailsTrans(locationId,accYear);
		}
		else if(accYear.equalsIgnoreCase("all") && !(locationId.equalsIgnoreCase("all"))){
			accYear="%%";
			list = new TransportBD().searchAllAcademicYearDetailsTrans(locationId,accYear);
		}
		else if(accYear.equalsIgnoreCase("all") && locationId.equalsIgnoreCase("all")){
			locationId="%%";
			accYear="%%";
			list = new TransportBD().searchAllAcademicYearDetailsTrans(locationId,accYear);
		}else{
			list = new TransportBD().searchAllAcademicYearDetailsTrans(locationId,accYear);
		}
		
		request.setAttribute("SearchList", list);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("SearchList", list);
		response.getWriter().print(jsonobj);

	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);

	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationAction : searchAllAcademicYearDetails Ending");

	return null;
}
public ActionForward getStudentListByClass(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getStudentDetailsLByFilter Starting");

	try {

		String locationid = request.getParameter("location");
		String accyear = request.getParameter("accyear");
		String classname = request.getParameter("classId");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		list = new TransportBD().getStudentListByClass(locationid,accyear,classname);
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("getClassWiseList", list);

			response.getWriter().print(jsonobj);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
	e.printStackTrace();
}


	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getStudentDetailsLByFilter Ending");

	return null;

}
public ActionForward getStudentListBySection(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())

			+ " Control in AdminMenuAction : gradeList Starting");
	
	try{
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);
		
		ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
				.accYearListStatus();
		request.setAttribute("AccYearList", accYearList);
		String locationid = request.getParameter("location");
		String accyear = request.getParameter("accyear");
		String classname = request.getParameter("classId");
		String sectionid = request.getParameter("sectionid");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		list = new TransportBD().getStudentListBySection(locationid,accyear,classname,sectionid);
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("getSectionWiseList", list);

			response.getWriter().print(jsonobj);

	} catch (Exception e) {

		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getStudentListBySection Ending");

	return null;
}

public ActionForward getStudentSearchByList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getStudentSearchByList Starting");
	
	List<StudentRegistrationVo> list = null;
	
	try {
		
		String locationid = request.getParameter("location");
		String accyear = request.getParameter("accyear");
		String classname = request.getParameter("classId");
		String sectionid = request.getParameter("sectionid");

		String searchTerm = request.getParameter("searchname".trim());

		System.out.println(locationid+" "+accyear+" "+classname+" "+sectionid+" "+searchTerm);
		
		if(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")){
			
			list = new TransportBD().getStudentSearchByList(searchTerm);
		}
		else if(locationid.equalsIgnoreCase("all") && !(accyear.equalsIgnoreCase("all"))){
			
			list =  new TransportBD().getStudentSearchListByAccYear(searchTerm,accyear);
		}
		else if(accyear.equalsIgnoreCase("all") && !(locationid.equalsIgnoreCase("all"))){
			
			list = new TransportBD().getStudentSearchListByLocation(searchTerm,locationid);
		}
		else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")) && classname.equalsIgnoreCase("all")){
			
			list = new TransportBD().getStudentSearchByFilter(searchTerm,locationid,accyear,classname);
		}
		else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all")) && sectionid.equalsIgnoreCase("all")){
			
			list = new TransportBD().getStudentSearchByClass(searchTerm,locationid,accyear,classname);
		}
		else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all") && sectionid.equalsIgnoreCase("all"))){
			
			list = new TransportBD().getStudentSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
		}
		
		request.setAttribute("SearchList", list);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("SearchList", list);
		response.getWriter().print(jsonobj);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getStudentSearchByList Ending");

	return null;
}
public ActionForward waivedOfftransportrequest(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : waivedOfftransportrequest Starting");
	try{
		
		TransportPojo pojo = new TransportPojo();
		pojo.setStuid(request.getParameter("stuid"));
		pojo.setAccyear(request.getParameter("accyear"));
		pojo.setLocid(request.getParameter("locid"));
		pojo.setRouteCode(request.getParameter("routeid"));
		pojo.setStageid(request.getParameter("stageid"));
		String  savetransport = new TransportBD().waivedOfftransportrequest(pojo);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("status",savetransport);
		response.getWriter().print(jsonobj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : waivedOfftransportrequest Ending");
	 return null;
}
public ActionForward addTransportType(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : addTransportType Starting");
	try {
		String arg = "Add New Transport Type";
		request.setAttribute("pageName", arg);

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : addTransportType Ending");

	return mapping.findForward(MessageConstants.ADD_TRANSPORT_TYPE);
}

	public ActionForward insertVehicleType(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : insertVehicleType Starting");
	try{
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);
		
		
		TransportCategoryForm tform = new TransportCategoryForm();
		
		String createCode = HelperClass.getCurrentUserID(request);
		String s1=null;
		
		String vehicletype=request.getParameter("vehicleType".trim());
		String desc=request.getParameter("desc".trim());
		String veh_id = request.getParameter("upd_vehicle_id");
		s1=veh_id;//For insertion s1 is empty.
		
		if (veh_id == "" || veh_id.equalsIgnoreCase("") || veh_id == null) 
		{
			 s1 = IDGenerator.getPrimaryKeyID("transport_typedetails");
		}//This is for inserting.
	
		tform.setVehicleType(vehicletype);
		tform.setDescription(desc);
		tform.setVehicle_id(s1);
		tform.setUpdateid(veh_id);
		
		TransportBD details=new TransportBD();
		
		String result = details.insertVehicleType(tform,createCode);
		
		
		JSONObject object = new JSONObject();
		object.put("status", result);//Here status in one key having the value of result.
		response.getWriter().print(object);//Always pass the refrence of JSON Object.
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : insertVehicleType Ending");
	
	return null;
}

	public ActionForward validateVehicleType(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction: validateVehicleType Starting");

	try {
		String vehicletype = request.getParameter("completeurl");//Here the name is forwading from data passes in $.ajax() of  AddTransportDetails.js.
		
		VehicleTypeVo vehicleadd = new VehicleTypeVo();
		vehicleadd.setVehicleType(vehicletype);
		boolean vehname_available = new TransportBD().validateVehicleType(vehicleadd);
		JSONObject object = new JSONObject();
		object.put("vehi_available", vehname_available);
		response.getWriter().print(object);
		

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction: validateVehicleType Ending");
	return null;
}

	public ActionForward edittransporttype(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : edittransporttype Starting");

	try {
		String args = "Modify Transport Type";
		request.setAttribute("pageName", args);
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_TRANSPORT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_TRANSPORT);
		
		String transportCatergory = request.getParameter("vehicleid");
		
		VehicleTypeVo transportdetails = new TransportBD().edittransporttype(transportCatergory);
		
		request.setAttribute("transportTypedetails", transportdetails);//Here the name in "" equals the name in jsp.
		
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : edittransporttype Ending");
	
	return mapping.findForward(MessageConstants.ADD_TRANSPORT_TYPE);
}

	public ActionForward deleteVehicleType(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : deleteVehicleType Starting");

	try {

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_TRANSPORT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_TRANSPORT);
		
		String vehiclecode = request.getParameter("vehicleid");
		String[] code=vehiclecode.split(",");
		
		String status = new TransportBD().deleteVehicleType(code);
		
		request.setAttribute("vehicleDetails", status); 

		JSONObject object = new JSONObject();
		object.put("status", status);
		response.getWriter().print(object);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : deleteVehicleType Ending");

	return null;
}
	public ActionForward printStudentBusCard(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getStudentBusCard Starting");
		try{
			String printFileName = null;
			String stuId = request.getParameter("stuId");
			String classId = request.getParameter("ClassId");
			String sectionId = request.getParameter("sectionId");
			String term = request.getParameter("termId").trim();
			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String locName =new ExamDetailsBD().getlocationname(locid);
			
			String date = HelperClass.convertDatabaseToUI(HelperClass.getCurrentSqlDate().toString());
			TransportVo obj = new TransportVo();
			obj.setStudentId(stuId);
			obj.setClassId(classId);
			obj.setSectionId(sectionId);
			obj.setTermId(term);
			obj.setLoc_id(locid);
			obj.setAcy_id(accyear);
			ArrayList<TransportVo> list = new TransportBD().getStudentBusCardDetails(obj);
			List<TermMasterVo> termList = new TermMasterDelegate().getTermDetails(accyear,locid);
			String sourceFileName = null;
			String termName = null;
			
			
			if(term.equalsIgnoreCase(termList.get(0).getTermid()) ){
				termName = "Term1";
			sourceFileName = request
					.getRealPath("Reports/buspasscard.jrxml");
			}
			else if(term.equalsIgnoreCase(termList.get(1).getTermid().trim())){
				termName = "Term2";
				sourceFileName = request.getRealPath("Reports/buspasscard2term.jrxml");
			}
			else if(term.equalsIgnoreCase(termList.get(2).getTermid())){
				termName = "Term3";
				sourceFileName = request.getRealPath("Reports/buspasscard3term.jrxml");
			}
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

			String PropfilePath = request.getRealPath("")+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("locname", locName);
			parameters.put("date", date);
			
			JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
	
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);
			
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.setVisible(true);
			
			PrinterJob job = PrinterJob.getPrinterJob();
			   int selectedService = 0;
			   selectedService = 0;
			   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
			   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
			   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
			
			   printRequestAttributeSet.add(new Copies(1));
			   JRPrintServiceExporter exporter;
			   exporter = new JRPrintServiceExporter();
			   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
			   exporter.exportReport();
			   job.print(printRequestAttributeSet);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward(MessageConstants.STUDENTBUSCARD);
	}
	
	
	public ActionForward getRouteScreen(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : getRouteScreen Starting");
	try{
		
		
		
		
		String searchterm = request.getParameter("searchTerm");

		ArrayList<TransportPojo> tpMasterPojo = new ArrayList<TransportPojo>();
		TransportDaoImpl daoImpl = new TransportDaoImpl();
		tpMasterPojo = daoImpl.getRouteScreen(searchterm);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(MessageConstants.JSON_RESPONSE, tpMasterPojo);

		response.getWriter().print(jsonObject);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : getRouteScreen Ending");
	
	return null;
}
	
	
	public ActionForward getRouteScreenDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportSetupAction : getRouteScreenDetails Starting");

		try {
		
			
			String searchterm = request.getParameter("searchTerm");
			
			ArrayList<TransportVo> masterDetails = new TransportDaoImpl().getRouteScreenDetailsfor(searchterm);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, masterDetails);

			response.getWriter().print(jsonObject);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RouteSetupAction : getRouteScreenDetails Ending");
		return null;
	}
	
	
	public ActionForward getMappedMonth(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getMappedMonth Starting");

		try {
		
			
			String studentid = request.getParameter("studentid");
			String accyearid = request.getParameter("accyearid");
			ArrayList<TransportVo> mappedMonth = new TransportDaoImpl().getMappedMonth(studentid,accyearid);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, mappedMonth);

			response.getWriter().print(jsonObject);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getMappedMonth Ending");
		return null;
	}
	
	public ActionForward getMappedMonthDelete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getMappedMonthDelete Starting");

		try {
		
			
			String id = request.getParameter("id");
			String mappedMonthDelete = new TransportDaoImpl().getMappedMonthDelete(id);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, mappedMonthDelete);

			response.getWriter().print(jsonObject);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getMappedMonthDelete Ending");
		return null;
	}
	
}






	

	


