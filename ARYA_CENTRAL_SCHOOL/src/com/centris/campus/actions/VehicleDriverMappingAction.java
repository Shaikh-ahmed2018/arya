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
import org.json.JSONObject;

import com.centris.campus.delegate.VehicleDriverMappingBD;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.DriverVo;
import com.centris.campus.vo.VehicleDriverMappingPojo;
import com.centris.campus.vo.VehicleDriverMappingVo;

public class VehicleDriverMappingAction extends DispatchAction {
	
private static Logger logger = Logger.getLogger(VehicleDriverMappingAction.class);
	
	public synchronized ActionForward addVehicleDriverMapping(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingAction : addVehicleDriverMapping Starting");

		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);
			
			ArrayList<DriverVo> getDriverList = new VehicleDriverMappingBD().getAllDriversDetails();
			request.setAttribute("getDriverList", getDriverList);
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingAction : addVehicleDriverMapping Ending");

		return mapping.findForward(MessageConstants.VehiclDriverMapEntry);
	}
	
	public synchronized ActionForward getAvailableVehicles(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingAction : getAvailableVehicles Starting");

		try {
			
			ArrayList<VehicleDriverMappingVo> VehicleList = new VehicleDriverMappingBD().getAvailableVehicles();
			
			JSONObject object=new JSONObject();
			object.put("AvailableVehicleList", VehicleList);
			
			response.getWriter().print(object);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingAction : getAvailableVehicles Ending");

		return null;
	}
	
	public synchronized ActionForward getMappedVehicles(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingAction : getMappedVehicles Starting");

		try {
			
			String driverID=request.getParameter("DriverCode");
			ArrayList<VehicleDriverMappingVo> VehicleList = new VehicleDriverMappingBD().getMappedVehicles(driverID);
			
			JSONObject object=new JSONObject();
			object.put("MappedVehicleList", VehicleList);
			
			response.getWriter().print(object);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingAction : getMappedVehicles Ending");

		return null;
	}
	
	public synchronized ActionForward mapVehicle(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingAction : mapVehicle Starting");

		try {
			
			String driverId=request.getParameter("DriverId");
			String mappedVehicle=request.getParameter("MappedVehicles");
			String usercode = HelperClass.getCurrentUserID(request);
			
			VehicleDriverMappingPojo vehiclepojo=new VehicleDriverMappingPojo();
			
			vehiclepojo.setDrivercode(driverId);
			vehiclepojo.setVehiclecode(mappedVehicle);
			vehiclepojo.setCurrentuser(usercode);
			
			String status = new VehicleDriverMappingBD().mapVehicle(vehiclepojo);
			
			JSONObject object=new JSONObject();
			object.put("Status", status);
			
			response.getWriter().print(object);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingAction : mapVehicle Ending");

		return null;
	}

	public synchronized ActionForward editVehicleDriverMapping(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingAction : editVehicleDriverMapping Starting");

		try {
			
			String mappingID=request.getParameter("MappingSno");
			
			ArrayList<DriverVo> getDriverList = new VehicleDriverMappingBD().getAllDriversDetails();
			request.setAttribute("getDriverList", getDriverList);
			
			VehicleDriverMappingPojo vehiclepojo=new VehicleDriverMappingPojo();
			
			vehiclepojo.setMappingID(mappingID);
			
			VehicleDriverMappingVo mappingVO = new VehicleDriverMappingBD().editVehicleDriverMapping(vehiclepojo);
			
			request.setAttribute("MappingVO", mappingVO);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingAction : editVehicleDriverMapping Ending");

		return mapping.findForward(MessageConstants.VehiclDriverMapEntry);
	}
	
	public synchronized ActionForward updateMappedVehicle(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingAction : updateMappedVehicle Starting");

		try {
			
			String DriverVehicleMapCode=request.getParameter("DriverVehicleMapCode");
			String driverId=request.getParameter("DriverId");
			String mappedVehicle=request.getParameter("MappedVehicles");
			String usercode = HelperClass.getCurrentUserID(request);
			
			
			VehicleDriverMappingPojo vehiclepojo=new VehicleDriverMappingPojo();
			
			vehiclepojo.setDrivercode(driverId);
			vehiclepojo.setVehiclecode(mappedVehicle);
			vehiclepojo.setCurrentuser(usercode);
			vehiclepojo.setMappingID(DriverVehicleMapCode);
			
			String status = new VehicleDriverMappingBD().updateMappedVehicle(vehiclepojo);
			
			JSONObject object=new JSONObject();
			object.put("Status", status);
			
			response.getWriter().print(object);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingAction : updateMappedVehicle Ending");

		return null;
	}
	
	
	public synchronized ActionForward deleteVehicleDriverMapping(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingAction : deleteVehicleDriverMapping Starting");

		try {
			
			String mappingIds=request.getParameter("vehicle_id");
			
			VehicleDriverMappingPojo vehiclepojo=new VehicleDriverMappingPojo();
			
			vehiclepojo.setMappingID(mappingIds);
			
			String status = new VehicleDriverMappingBD().deleteVehicleDriverMapping(vehiclepojo);
			
			JSONObject object=new JSONObject();
			object.put("Status", status);
			
			response.getWriter().print(object);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VehicleDriverMappingAction : deleteVehicleDriverMapping Ending");

		return null;
	}
	

}
