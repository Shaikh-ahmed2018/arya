package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.TransportCategoryForm;
import com.centris.campus.forms.TransportForm;
import com.centris.campus.pojo.TransportPojo;
import com.centris.campus.service.ReportsMenuService;
import com.centris.campus.service.TransportService;
import com.centris.campus.serviceImpl.LocationServiceImpl;
import com.centris.campus.serviceImpl.ReportsMenuServiceImpl;
import com.centris.campus.serviceImpl.TransportServiceImpl;
import com.centris.campus.vo.DriverMsaterListVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TransportVo;
import com.centris.campus.vo.VehicleDetailsVO;
import com.centris.campus.vo.VehicleTypeVo;

public class TransportBD {
	static TransportService service;
	static {
		service=new TransportServiceImpl();
	}

	//TransportService service = new TransportServiceImpl();

	public ArrayList<VehicleDetailsVO> getAllvehicleDetails() {
		return service.getAllvehicleDetails();
	}

	public String saveVehicleDetails(VehicleDetailsVO transportForm,String createUser, String vehiclecode) {
		return service.saveVehicleDetails(transportForm, createUser,vehiclecode);
	}

	public boolean checkingVehicleInsuranceDate(VehicleDetailsVO vehiclecode) {
		return service.checkingVehicleInsuranceDate(vehiclecode);
	}

	public VehicleDetailsVO getSingleVehicleDetails(String vehiclecode) {
		return service.getSingleVehicleDetails(vehiclecode);
	}

	public String deleteVehicleDetails(String[] vehiclecode) {
		return service.deleteVehicleDetails(vehiclecode);
	}

	public boolean registernumberValidation(String vehicleregno) {
		return service.registernumberValidation(vehicleregno);
	}

	public boolean updateregisternumberValidation(String vehicleregno,
			String vehicleCode) {
		return service
				.updateregisternumberValidation(vehicleregno, vehicleCode);
	}

	public boolean chassisnovalidationvalidation(String chassisno) {
		return service.chassisnovalidationvalidation(chassisno);
	}

	public boolean updatechassisnovalidation(String chassisno,
			String vehicleCode) {
		return service.updatechassisnovalidation(chassisno, vehicleCode);
	}

	public ArrayList<VehicleDetailsVO> searchvehicledetails(String searchTerm) {
		return service.searchvehicledetails(searchTerm);
	}

	public boolean checkforduplicateAddTime(VehicleDetailsVO obj) {
		return service.checkforduplicateAddTime(obj);
	}

	public boolean checkforduplicateUpdateTime(VehicleDetailsVO obj) {
		return service.checkforduplicateUpdateTime(obj);
	}

	// route master start

	public List<TransportVo> getTransportMasterDetails(String accyear) {
		TransportService transpService = new TransportServiceImpl();
		List<TransportVo> transportDetails = transpService.getTransportMasterServiceDetails(accyear);
		return transportDetails;
	}

	public String insertRouteMasterDetails(TransportPojo tpMasterPojo) {
		TransportService tpService = new TransportServiceImpl();

		String check = tpService.insertRouteMasterDetails(tpMasterPojo);
		return check;
	}

	public String removeRouteMasterDetails(String[] routecode) {
		TransportService routeService = new TransportServiceImpl();
		return routeService.removeRouteMasterDetails(routecode);
	}

	public boolean addRoute(TransportForm routeForm) {
		return new TransportServiceImpl().addRoute(routeForm);
	}

	public boolean checkrouteNo(TransportPojo Pojo) {
		return service.checkrouteNo(Pojo);
	}

	public TransportVo editRouteMasterDetails(String routecode) {
		TransportService tpService = new TransportServiceImpl();

		TransportVo list = tpService.editRouteMasterDetails(routecode);
		return list;
	}

	public List<TransportVo> searchDetails(String SearchName, String searchYear) {
		TransportService transpService = new TransportServiceImpl();
		List<TransportVo> transportDetails = transpService.searchDetails(SearchName,searchYear);
		return transportDetails;
	}

	public List<TransportVo> getTransportMasterDetailsXLS() {
		TransportService transpService = new TransportServiceImpl();
		List<TransportVo> transportDetails = transpService
				.getTransportMasterServiceDetailsXLS();
		return transportDetails;
	}

	// route master end

	public List<DriverMsaterListVo> getdriverList() {

		TransportService driverservice = new TransportServiceImpl();
		return driverservice.getdriverListService();
	}

	public DriverMsaterListVo editDriverBD(DriverMsaterListVo drivervo) {
		TransportService service = new TransportServiceImpl();
		return service.editDriverService(drivervo);
	}

	public String deleteDriverBD(String[] drivervo) {

		TransportService service = new TransportServiceImpl();
		return service.deleteDriverService(drivervo);
	}

	public ArrayList<DriverMsaterListVo> searchDriverBD(String searchName) {

		TransportService service = new TransportServiceImpl();
		return service.searchDriverService(searchName);
	}

	public boolean validateDriverBD(DriverMsaterListVo drivervo) {

		TransportService service = new TransportServiceImpl();
		return service.validateDriverService(drivervo);
	}

	public ArrayList<DriverMsaterListVo> getDriverNamesDetails() {

		TransportService service = new TransportServiceImpl();
		return service.getDriverNamesDetails();
	}

	public ArrayList<DriverMsaterListVo> getDriverEntireDetails(String driverid) {
		TransportService service = new TransportServiceImpl();
		return service.getDriverEntireDetails(driverid);
	}

	public ArrayList<DriverMsaterListVo> getDriverDetailsWhileUpdate(
			String vehiclecode) {

		TransportService service = new TransportServiceImpl();
		return service.getDriverDetailsWhileUpdate(vehiclecode);
	}

	public DriverMsaterListVo getSingleDriverDetails(String vehiclecode) {
		TransportService service = new TransportServiceImpl();
		return service.getSingleDriverDetails(vehiclecode);
	}

	public ArrayList<StageMasterVo> getStopNames(String searchTerm) {
		TransportService service = new TransportServiceImpl();
		return service.getStopNames(searchTerm);
	}

	public ArrayList<TransportVo> getRouteDetails() {

		TransportService service = new TransportServiceImpl();
		return service.getRouteDetails();
	}

	public ArrayList<TransportVo> GetRouteEntireDetails(String route) {

		TransportService service = new TransportServiceImpl();
		return service.GetRouteEntireDetails(route);
	}

	public TransportVo getRouteDetails(String vehiclecode) {
		TransportService service = new TransportServiceImpl();
		return service.getRouteDetails(vehiclecode);
	}

	public boolean addDriverBD(DriverMsaterListVo drivervo, String createuser
			) {
		TransportService service = new TransportServiceImpl();
		return service.addDriverService( drivervo,  createuser);
	}

	public List<TransportVo> editRouteStages(TransportPojo tpMasterPojo, String accyear) {
		TransportService service = new TransportServiceImpl();
		return service.editRouteStages(tpMasterPojo,accyear);
	}

	public List<TransportVo> unmappedRouteStages(TransportPojo tpMasterPojo) {
		TransportService service = new TransportServiceImpl();
		return service.unmappedRouteStages(tpMasterPojo);
	}


	public ArrayList<TransportVo> getRouteDetailsByLocation(String transferlocation) {
		TransportService service = new TransportServiceImpl();
		return service.getRouteDetailsByLocation(transferlocation);
	}

	public List<VehicleDetailsVO> searchVehicleDetails(String searchName) {
		return service.searchvehicledetails(searchName);
	}

	public ArrayList<TransportVo> getRouteDetailsByName() {
		TransportService service = new TransportServiceImpl();
		return service.getRouteDetailsByName();
	}

	public boolean validateLicenseBD(DriverMsaterListVo drivervo) {
		TransportService service = new TransportServiceImpl();
		return service.validateLicenseService(drivervo);
	}

	public ArrayList<TransportVo> getStudentBusCardDetails(TransportVo obj) {
		TransportService service = new TransportServiceImpl();
		return service.getStudentBusCardDetails(obj);
	}
	
	public List<VehicleTypeVo> searchVehicletypeDetails(String searchName) {
			return service.searchVehicletypeDetails(searchName);
	}

	public String insertVehicleType(TransportCategoryForm form,String createCode) {
		return service.insertVehicleType(form,createCode);
	}

	public List<VehicleTypeVo> getAllvehicletypeDetails() {
		return service.getAllvehicletypeDetails();
	}

	public VehicleTypeVo edittransporttype(String transportCatergory) {
		return service.edittransporttype(transportCatergory);
	}

	public String deleteVehicleType(String[] code) {
		return service.deleteVehicleType(code);
	}

	public boolean validateVehicleType(VehicleTypeVo vehicleadd) {
		return service.validateVehicleType(vehicleadd);
	}

	public TransportVo gettransportdetailsstudentwise(
			TransportVo tvo) {
		return service.gettransportdetailsstudentwise(tvo);
	}

	public ArrayList<TransportVo> settranporttermdetailsforstudent(
			TransportVo tvo) {
		return service.settranporttermdetailsforstudent(tvo);
	}

	public ArrayList<TransportVo> getRouteNamelist(TransportVo tvo) {
		return service.getRouteNamelist(tvo);
	}

	public ArrayList<TransportVo> getstoplist(TransportVo tvo) {
		return service.getstoplist(tvo);
	}

	public String getamountandstatus(TransportVo tvo) {
		return service.getamountandstatus(tvo);

	}

	public String savetransportrequest(TransportPojo pojo) {
		return service.savetransportrequest(pojo);

	}

	public List<StudentRegistrationVo> searchAllAcademicYearDetailsTrans(String locationId, String accYear) {
		return service.searchAllAcademicYearDetailsTrans(locationId,accYear);
	}

	public java.util.List<StudentRegistrationVo> getStudentList(String academic_year, String schoolLocation) {
		return service.getStudentList(academic_year,schoolLocation);
	}

	public List<StudentRegistrationVo> getStudentListByClass(String locationid, String accyear, String classname) {
		return service.getStudentListByClass(locationid,accyear,classname);
	}

	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname,
			String sectionid) {
		return service.getStudentListBySection(locationid,accyear,classname,sectionid);
	}

	public List<StudentRegistrationVo> getStudentSearchByList(String searchTerm) {
		return service.getStudentSearchByList(searchTerm);
	}

	public List<StudentRegistrationVo> getStudentSearchListByAccYear(String searchTerm, String accyear) {
		return service.getStudentSearchListByAccYear(searchTerm,accyear);
	}

	public List<StudentRegistrationVo> getStudentSearchListByLocation(String searchTerm, String locationid) {
		return service.getStudentSearchListByLocation(searchTerm,locationid);
	}

	public List<StudentRegistrationVo> getStudentSearchByFilter(String searchTerm, String locationid, String accyear,
			String classname) {
		return service.getStudentSearchByFilter(searchTerm,locationid,accyear,classname);
	}

	public List<StudentRegistrationVo> getStudentSearchByClass(String searchTerm, String locationid, String accyear,
			String classname) {
		return service.getStudentSearchByClass(searchTerm,locationid,accyear,classname);
	}

	public List<StudentRegistrationVo> getStudentSearchByAllFilter(String searchTerm, String locationid, String accyear,
			String classname, String sectionid) {
		return service.getStudentSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
	}

	public String waivedOfftransportrequest(TransportPojo pojo) {
		return service.waivedOfftransportrequest(pojo);
	}

	public ArrayList<TransportVo> getMonthList(String accyear, String loc_id) {
		return service.getMonthList(accyear,loc_id);
	}

	public static ArrayList<TransportVo> getonlinelist(String locationid, String accyear, String classid,
			String setionid, String paymodeid, String paymenttype, String termId) {
		// TODO Auto-generated method stub
		return service.getonlinelist(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId);
	}
	public static ArrayList<TransportVo> getFeeCollectionPaymodeReport(
			String locationid, String accyear, String classid, String setionid,
			String paymodeid, String paymenttype, String termId) {
			return service.getFeeCollectionPaymodeReport(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId);
	}

}
