package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.TransportCategoryForm;
import com.centris.campus.forms.TransportForm;
import com.centris.campus.pojo.TransportPojo;
import com.centris.campus.vo.DriverMsaterListVo;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TransportVo;
import com.centris.campus.vo.VehicleDetailsVO;
import com.centris.campus.vo.VehicleTypeVo;

public interface TransportDao {

	public ArrayList<VehicleDetailsVO> getAllvehicleDetails();

	public String saveVehicleDetails(VehicleDetailsVO transportForm,String createUser, String vehiclecode);

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
	public List<TransportPojo> getTransportMasterDaoDetails(String accyear);
	
	public List<TransportPojo> getTransportMasterDaoDetailsXLS();


	public int getTpRouteCheckDao(String tpName);

	public String insertRouteMasterDetails(TransportPojo tpPojo);

	public String removeRouteMasterDetails(String[] routecode);

	public boolean addRoute(TransportForm routeForm);

	public boolean checkrouteNo(TransportPojo Pojo);

	public TransportVo editRouteMasterDetails(String routecode);

	public List<TransportVo> searchDetails(String SearchName, String searchYear);


	// route master end

	public ArrayList<DriverMsaterListVo> getdriverListDao();

	public boolean addDriverDao(DriverMsaterListVo drivervo, String createuser);

	public DriverMsaterListVo editDriverDao(DriverMsaterListVo drivervo);

	public String deleteDriverDao(String[] drivervo);

	public ArrayList<DriverMsaterListVo> searchDriverDao(String searchName);

	public boolean updateDriverDao(DriverMsaterListVo drivervo, String createuser);

	public boolean validateDriverDao(DriverMsaterListVo drivervo);

	public ArrayList<DriverMsaterListVo> getDriverNamesDetails();

	public ArrayList<DriverMsaterListVo> getDriverEntireDetails(String driverid);

	public ArrayList<DriverMsaterListVo> getDriverDetailsWhileUpdate(
			String vehiclecode);

	public DriverMsaterListVo getSingleDriverDetails(String vehiclecode);

	public ArrayList<StageMasterVo> getStopNames(String searchTerm);

	public ArrayList<TransportVo> getRouteDetails();

	public ArrayList<TransportVo> GetRouteEntireDetails(String route);

	public TransportVo getRouteDetails(String vehiclecode);

	public List<TransportVo> editRouteStages(TransportPojo tpMasterPojo, String accyear);

	public List<TransportVo> unmappedRouteStages(TransportPojo tpMasterPojo);

	public ArrayList<TransportVo> getRouteDetailsByLocation(String transferlocation);

	public ArrayList<TransportVo> getRouteDetailsByName();

	public boolean validateLicenseDao(DriverMsaterListVo drivervo);

	public ArrayList<TransportVo> getStudentBusCardDetails(TransportVo obj);

	public String insertVehicleType(TransportCategoryForm form, String createCode);

	public List<VehicleTypeVo> getAllvehicletypeDetails();

	public VehicleTypeVo edittransporttype(String transportCatergory);
	
	public String deleteVehicleType(String[] code);

	public boolean validateVehicleType(VehicleTypeVo vehicleadd);

	public List<VehicleTypeVo> searchVehicletypeDetails(String searchName);

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

	

	

/*	public ArrayList<TransportVo> GetRouteEntireDetails(TransportVo transportVo);
*/}
