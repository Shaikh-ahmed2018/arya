package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.TransportCategoryForm;
import com.centris.campus.forms.TransportForm;
import com.centris.campus.pojo.TransportPojo;
import com.centris.campus.vo.DriverMsaterListVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TransportVo;
import com.centris.campus.vo.VehicleDetailsVO;
import com.centris.campus.vo.VehicleTypeVo;

public interface TransportService {
	public ArrayList<VehicleDetailsVO> getAllvehicleDetails();

	public String saveVehicleDetails(VehicleDetailsVO transportForm, String createUser, String vehiclecode);

	public boolean checkingVehicleInsuranceDate(VehicleDetailsVO vehiclecode);

	public VehicleDetailsVO getSingleVehicleDetails(String vehiclecode);

	public String deleteVehicleDetails(String[] vehiclecode);

	public boolean registernumberValidation(String vehicleregno);

	public boolean updateregisternumberValidation(String vehicleregno,
			String vehicleCode);

	public boolean chassisnovalidationvalidation(String chassisno);

	public boolean updatechassisnovalidation(String chassisno,
			String vehicleCode);

	public ArrayList<VehicleDetailsVO> searchvehicledetails(String searchTerm);

	public boolean checkforduplicateAddTime(VehicleDetailsVO obj);

	public boolean checkforduplicateUpdateTime(VehicleDetailsVO obj);

	// route master start

	public List<TransportVo> getTransportMasterServiceDetails(String accyear);

	public List<TransportVo> getTransportMasterServiceDetailsXLS();

	public String insertRouteMasterDetails(TransportPojo tpMasterPojo);

	public String removeRouteMasterDetails(String[] routecode);

	public boolean checkrouteNo(TransportPojo Pojo);

	public boolean addRoute(TransportForm routeForm);

	public TransportVo editRouteMasterDetails(String routecode);

	public List<TransportVo> searchDetails(String SearchName, String searchYear);

	// route master end

	public List<DriverMsaterListVo> getdriverListService();


	public DriverMsaterListVo editDriverService(DriverMsaterListVo drivervo);

	public String deleteDriverService(String[] drivervo);

	public ArrayList<DriverMsaterListVo> searchDriverService(String searchName);

	public boolean validateDriverService(DriverMsaterListVo drivervo);

	public ArrayList<DriverMsaterListVo> getDriverNamesDetails();

	public ArrayList<DriverMsaterListVo> getDriverEntireDetails(String driverid);

	public ArrayList<DriverMsaterListVo> getDriverDetailsWhileUpdate(
			String vehiclecode);

	public DriverMsaterListVo getSingleDriverDetails(String vehiclecode);

	public ArrayList<StageMasterVo> getStopNames(String searchTerm);

	public ArrayList<TransportVo> getRouteDetails();

	public ArrayList<TransportVo> GetRouteEntireDetails(String route);

	public TransportVo getRouteDetails(String vehiclecode);
	
	public boolean addDriverService(DriverMsaterListVo drivervo, String createuser);

	public List<TransportVo> editRouteStages(TransportPojo tpMasterPojo, String accyear);

	public List<TransportVo> unmappedRouteStages(TransportPojo tpMasterPojo);

	public ArrayList<TransportVo> getRouteDetailsByLocation(String transferlocation);

	public ArrayList<TransportVo> getRouteDetailsByName();

	public boolean validateLicenseService(DriverMsaterListVo drivervo);

	public ArrayList<TransportVo> getStudentBusCardDetails(TransportVo obj);
	
	public List<VehicleTypeVo> searchVehicletypeDetails(String searchName);

	public String insertVehicleType(TransportCategoryForm form,String createCode);

	public List<VehicleTypeVo> getAllvehicletypeDetails();

	public VehicleTypeVo edittransporttype(String transportCatergory);

	public String deleteVehicleType(String[] code);

	public boolean validateVehicleType(VehicleTypeVo vehicleadd);

	public TransportVo gettransportdetailsstudentwise(TransportVo tvo);

	public ArrayList<TransportVo> settranporttermdetailsforstudent(
			TransportVo tvo);

	public ArrayList<TransportVo> getRouteNamelist(TransportVo tvo);

	public ArrayList<TransportVo> getstoplist(TransportVo tvo);

	public String getamountandstatus(TransportVo tvo);

	public String savetransportrequest(TransportPojo pojo);

	public List<StudentRegistrationVo> searchAllAcademicYearDetailsTrans(String locationId, String accYear);

	public List<StudentRegistrationVo> getStudentList(String academic_year, String schoolLocation);

	public List<StudentRegistrationVo> getStudentListByClass(String locationid, String accyear, String classname);

	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname, String sectionid);

	public List<StudentRegistrationVo> getStudentSearchByList(String searchTerm);

	public List<StudentRegistrationVo> getStudentSearchListByAccYear(String searchTerm, String accyear);

	public List<StudentRegistrationVo> getStudentSearchListByLocation(String searchTerm, String locationid);

	public List<StudentRegistrationVo> getStudentSearchByFilter(String searchTerm, String locationid, String accyear,
			String classname);

	public List<StudentRegistrationVo> getStudentSearchByClass(String searchTerm, String locationid, String accyear,
			String classname);

	public List<StudentRegistrationVo> getStudentSearchByAllFilter(String searchTerm, String locationid, String accyear,
			String classname, String sectionid);

	public String waivedOfftransportrequest(TransportPojo pojo);

	public ArrayList<TransportVo> getMonthList(String accyear, String loc_id);
	public ArrayList<TransportVo> getonlinelist(String locationid,String accyear, String classid, String setionid, String paymodeid,String paymenttype, String termId);

	public ArrayList<TransportVo> getFeeCollectionPaymodeReport(String locationid, String accyear, String classid,
			String setionid, String paymodeid, String paymenttype, String termId);
}
