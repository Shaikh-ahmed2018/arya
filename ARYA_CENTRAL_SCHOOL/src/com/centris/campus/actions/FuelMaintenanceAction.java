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
import com.centris.campus.delegate.FuelMasterBD;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.DriverMasterVO;
import com.centris.campus.vo.FuelMaintenanceVO;

import com.centris.campus.vo.VehicleDetailsVO;

public class FuelMaintenanceAction extends DispatchAction {
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");

	private static final Logger logger = Logger
			.getLogger(FuelMaintenanceAction.class);

	public ActionForward AddFuelDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : AddFuelDetails Starting");

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_TRANSPORT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_TRANSPORT);
		
		ArrayList<VehicleDetailsVO> vehicleList = new FuelMasterBD()
				.getVehicleList();
		request.getSession(false).setAttribute("vehiclenameList", vehicleList);
		
		ArrayList<VehicleDetailsVO> driverList = new FuelMasterBD().getDriverNames();
		request.getSession(false).setAttribute("driverList", driverList);
		
		request.setAttribute("vehiclenameList", vehicleList);
		request.setAttribute("driverList", driverList);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : AddFuelDetails  Ending");

		return mapping.findForward(MessageConstants.ADD_FUEL_DETAILS);

	}

	public ActionForward insertFuelDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : insertFuelDetails Starting");

		try {
			String createUser = HelperClass.getCurrentUserID(request);

			FuelMaintenanceVO addFuellist = new FuelMaintenanceVO();

			addFuellist.setVehicleCode(request.getParameter("vehiclecode").trim());
			addFuellist.setDate(request.getParameter("date"));
			addFuellist.setVehiclename(request.getParameter("vehiclename"));
			addFuellist.setVehiclenumber(request.getParameter("vehicleno"));
			addFuellist.setDrivername(request.getParameter("drivername"));
			addFuellist.setFuelType(request.getParameter("fueltype"));
			addFuellist.setQuantity(request.getParameter("quantity"));
			addFuellist.setPrice(request.getParameter("price"));
			addFuellist.setMeterReading(request.getParameter("meter_reading"));
			addFuellist.setLocation(request.getParameter("location"));
			addFuellist.setCreateUser(createUser);
			addFuellist.setTime(request.getParameter("time"));
			addFuellist.setDriverCode(request.getParameter("drivercode").trim());
			addFuellist.setFuelCode(request.getParameter("fuelcode").trim());
			
			System.out.println("vehicle code: Acction Class:: "+request.getParameter("vehiclecode").trim());
			System.out.println("driver code: Acction Class:: "+request.getParameter("drivercode").trim());
			String status = new FuelMasterBD().addFuelDetails(addFuellist);

			request.setAttribute("status", status);

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
				+ " Control in FuelMaintenanceAction : insertFuelDetails  Ending");

		return null;

	}

	public ActionForward searchVehicle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : searchVehicle Starting");
		try {

			String search = request.getParameter("searchterm");

			VehicleDetailsVO searchterm = new VehicleDetailsVO();

			searchterm.setSearch(search);

			ArrayList<VehicleDetailsVO> vehicleList = new FuelMasterBD()
					.searchVehicle(searchterm);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("vehicle", vehicleList);

			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : searchVehicle  Ending");

		return null;
	}

	public ActionForward getVehicleNoBycode(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : getVehicleNoBycode Starting");
		try {

			String search = request.getParameter("v_id");

			VehicleDetailsVO searchTerm = new VehicleDetailsVO();

			searchTerm.setSearch(search);

			List<VehicleDetailsVO> vehicleList = new FuelMasterBD()
					.getVehicleNoBycode(searchTerm);

			System.out.println("vehicleList.get(0).getVehicleregno()--"+vehicleList.get(0).getVehicleregno());
			System.out.println("vehicleList.get(0).getFuelusedintheengine()"+vehicleList.get(0).getFuelusedintheengine());
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("status", vehicleList);

			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : getVehicleNoBycode  Ending");

		return null;
	}

	public ActionForward searchDriver(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : searchDriver Starting");
		try {

			String search = request.getParameter("searchTerm");

			DriverMasterVO searchTerm = new DriverMasterVO();

			searchTerm.setSearch(search);

			List<DriverMasterVO> driverList = new FuelMasterBD()
					.searchDriver(searchTerm);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("driver", driverList);

			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : searchDriver  Ending");
		return null;
	}

	public ActionForward deleteFuelDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : deleteFuelDetails  Starting");

		String success = MessageConstants.DELETE_FUEL_MAINTENANCE_SUCCESS;
		String FAIL = MessageConstants.DELETE_FUEL_MAINTENANCE_FAIL;

		try {
			String fuelcode = request.getParameter("FuelCode");

			FuelMaintenanceVO deleteFueldetails = new FuelMaintenanceVO();

			deleteFueldetails.setFuelCode(fuelcode);
			boolean status = new FuelMasterBD()
					.deleteFuelDetails(deleteFueldetails);

			JSONObject jsonObject = new JSONObject();
			if (status) {

				jsonObject.put("status", success);
			} else {

				jsonObject.put("status", FAIL);
			}
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : deleteFuelDetails   Ending");

		return null;

	}

	public ActionForward editFuelDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : editFuelDetails  Starting");

		try {

			String editcode = request.getParameter("name");

			FuelMaintenanceVO editFueldetails = new FuelMaintenanceVO();

			editFueldetails.setFuelCode(editcode);
			FuelMaintenanceVO status = new FuelMasterBD()
					.editFuelDetails(editFueldetails);
			request.setAttribute("fuellist", status);
			
			ArrayList<VehicleDetailsVO> vehicleList = new FuelMasterBD()
			.getVehicleList();
			request.setAttribute("vehiclenameList", vehicleList);
			
			ArrayList<VehicleDetailsVO> driverList = new FuelMasterBD().getDriverNames();
			request.getSession(false).setAttribute("driverList", driverList);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : editFuelDetails   Ending");

		return mapping.findForward(MessageConstants.ADD_FUEL_DETAILS);

	}

	public ActionForward searchFuelDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : searchFuelDetails  Starting");

		try {
			String Search_Name = request.getParameter("searchname");
			String SearchName = Search_Name.trim();

			FuelMaintenanceVO searchvo = new FuelMaintenanceVO();
			searchvo.setSearch(SearchName);
			ArrayList<FuelMaintenanceVO> fuellist = new FuelMasterBD()
					.searchFuelDetails(searchvo);

			request.setAttribute("fuelList", fuellist);

			request.setAttribute("searchdetails", SearchName);

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : searchFuelDetails   Ending");

		return mapping.findForward(MessageConstants.FUEL_MAINTENANCE);

	}

	public ActionForward downloadFuelMaintenanceDetailsXLS(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : downloadFuelMaintenanceDetailsXLS  Starting");
		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/FuelMaintenanceDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			/*
			 * List<FuelMaintenanceVO> QuotaMasterList = new
			 * ArrayList<FuelMaintenanceVO>(); QuotaMasterList =
			 * (List<FuelMaintenanceVO>) request.getSession(
			 * false).getAttribute("EXcelDownLoad");
			 */List<FuelMaintenanceVO> QuotaMasterList = new FuelMasterBD()
					.getfuelMaintenanceList();

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					QuotaMasterList);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/FuelMaintenanceDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Fuel Maintenance details Excel Report" };
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
					request.getRealPath("Reports/FuelMaintenanceDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=FuelMaintenanceDetailsXLSReport.xls");
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
				+ " Control in FuelMaintenanceAction : downloadFuelMaintenanceDetailsXLS  Ending");
		return null;

	}

	public ActionForward downloadFuelMaintenanceDetailsPDF(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeConcession : ConcessionDetailsPDFReport  Starting");

		try {

			System.out.println("downloading pdf");

			// List<FuelMaintenanceVO> Details = new
			// ArrayList<FuelMaintenanceVO>();
			List<FuelMaintenanceVO> Details = new FuelMasterBD()
					.getfuelMaintenanceList();
			// Details = (List<FuelMaintenanceVO>)
			// request.getSession(false).getAttribute("EXcelDownLoad");

			String sourceFileName = request
					.getRealPath("Reports/FuelMaintenanceDetailsPDF.jrxml");
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

			/*
			 * parameters.put("Image", clientImage);
			 * 
			 * parameters.put("ClientName", ClientName);
			 * 
			 * parameters.put("ClientAddress", ClientAddress_l1);
			 */

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "FuelMaintenanceDetailsPDF - " + ".pdf\"");

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

}