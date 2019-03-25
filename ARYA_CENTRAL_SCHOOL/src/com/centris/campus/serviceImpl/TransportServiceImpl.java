package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.TransportDao;
import com.centris.campus.daoImpl.CreateExaminationDaoImpl;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.ReportsMenuDaoImpl;
import com.centris.campus.daoImpl.TransportDaoImpl;
import com.centris.campus.delegate.TransportBD;
import com.centris.campus.forms.TransportCategoryForm;
import com.centris.campus.forms.TransportForm;
import com.centris.campus.pojo.TransportPojo;
import com.centris.campus.service.TransportService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.DriverMasterVO;
import com.centris.campus.vo.DriverMsaterListVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TransportVo;
import com.centris.campus.vo.VehicleDetailsVO;
import com.centris.campus.vo.VehicleTypeVo;

public class TransportServiceImpl implements TransportService {

	private static final Logger logger = Logger
			.getLogger(TransportServiceImpl.class);
	static TransportDaoImpl transportdao;
	static{
		transportdao = new TransportDaoImpl();
	}

	//TransportDao transportdao = new TransportDaoImpl();

	public ArrayList<VehicleDetailsVO> getAllvehicleDetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAllvehicleDetails Starting");
		ArrayList<VehicleDetailsVO> getvehiclelist = null;
		try {
			getvehiclelist = transportdao.getAllvehicleDetails();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAllvehicleDetails  Ending");
		return getvehiclelist;
	}

	public String saveVehicleDetails(VehicleDetailsVO transportForm,
			String createUser, String vehiclecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : saveVehicleDetails Starting");
		
		System.out.println("save vehicle service impl");
		
		String status = "";
		try {
			
			System.out.println("ServiceIMPL RC File: "+ transportForm.getRcfile());
			status = transportdao.saveVehicleDetails(transportForm, createUser,vehiclecode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : saveVehicleDetails  Ending");
		return status;
	}

	public boolean checkingVehicleInsuranceDate(VehicleDetailsVO vehiclecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkingVehicleInsuranceDate Starting");
		boolean status = false;
		try {
			status = transportdao.checkingVehicleInsuranceDate(vehiclecode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkingVehicleInsuranceDate  Ending");
		return status;
	}

	public VehicleDetailsVO getSingleVehicleDetails(String vehiclecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkingVehicleInsuranceDate Starting");
		VehicleDetailsVO ovj = null;
		try {
			ovj = transportdao.getSingleVehicleDetails(vehiclecode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkingVehicleInsuranceDate  Ending");
		return ovj;
	}

	public String deleteVehicleDetails(String[] vehiclecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : deleteVehicleDetails Starting");
		String status = null;
		try {
			status = transportdao.deleteVehicleDetails(vehiclecode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : deleteVehicleDetails  Ending");
		return status;
	}

	public boolean registernumberValidation(String vehicleregno) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : registernumberValidation Starting");
		boolean status = false;
		try {
			status = transportdao.registernumberValidation(vehicleregno);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : registernumberValidation  Ending");
		return status;
	}

	public boolean updateregisternumberValidation(String vehicleregno,
			String vehicleCode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : updateregisternumberValidation Starting");
		boolean status = false;
		try {
			status = transportdao.updateregisternumberValidation(vehicleregno,
					vehicleCode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : updateregisternumberValidation  Ending");
		return status;
	}

	public boolean chassisnovalidationvalidation(String chassisno) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : registernumberValidation Starting");
		boolean status = false;
		try {
			status = transportdao.chassisnovalidationvalidation(chassisno);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : registernumberValidation  Ending");
		return status;
	}

	public boolean updatechassisnovalidation(String chassisno,
			String vehicleCode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : updatechassisnovalidation Starting");
		boolean status = false;
		try {
			status = transportdao.updatechassisnovalidation(chassisno,
					vehicleCode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : updatechassisnovalidation  Ending");
		return status;
	}

	public ArrayList<VehicleDetailsVO> searchvehicledetails(String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAllvehicleDetails Starting");
		ArrayList<VehicleDetailsVO> getvehiclelist = null;
		try {
			getvehiclelist = transportdao.searchvehicledetails(searchTerm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAllvehicleDetails  Ending");
		return getvehiclelist;
	}

	public boolean checkforduplicateAddTime(VehicleDetailsVO obj) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkforduplicateAddTime Starting");
		boolean status = false;
		try {
			status = transportdao.checkforduplicateAddTime(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkforduplicateAddTime  Ending");
		return status;
	}

	public boolean checkforduplicateUpdateTime(VehicleDetailsVO obj) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkforduplicateUpdateTime Starting");
		boolean status = false;
		try {
			status = transportdao.checkforduplicateUpdateTime(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkforduplicateUpdateTime  Ending");
		return status;
	}

	// route master start
	public List<TransportVo> getTransportMasterServiceDetails(String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Transport ServiceImpl: getTransportMasterServiceDetails Starting");
		TransportDao tpSetupDao = new TransportDaoImpl();
		List<TransportVo> transportVOList = new ArrayList<TransportVo>();
		try {
			List<TransportPojo> tplist = tpSetupDao.getTransportMasterDaoDetails(accyear);
			for (TransportPojo gatDatas : tplist) {
				
				TransportVo tpVoObj = new TransportVo();
				tpVoObj.setRouteNo(gatDatas.getRouteNo());
				tpVoObj.setEndTime(gatDatas.getEndTime());
				tpVoObj.setRouteName(gatDatas.getRouteName());
				tpVoObj.setStratTime(gatDatas.getStartTime());
				tpVoObj.setTotalDistance(gatDatas.getTotalDistance());
				tpVoObj.setTotalStops(gatDatas.getTotalStops());
				tpVoObj.setRouteCode(gatDatas.getRouteCode());
				tpVoObj.setHalttime(gatDatas.getHalttime());
				tpVoObj.setAccyear(accyear);
				//tpVoObj.setDestination(gatDatas.getDestination());
				
				transportVOList.add(tpVoObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupServiceImpl: getTransportMasterServiceDetails Ending");
		return transportVOList;
	}

	public String insertRouteMasterDetails(TransportPojo tpMasterPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportSetupServiceImpl:insertRouteMasterDetails  Starting");
		
		String check = null;
		TransportDao tpSetupDao = new TransportDaoImpl();
		try {

			check = tpSetupDao.insertRouteMasterDetails(tpMasterPojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupServiceImpl:insertRouteMasterDetails Ending");
		return check;
	}

	public String removeRouteMasterDetails(String[] routecode) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl: removeRouteMasterDetails Starting");
		TransportDao routeDaoObj = new TransportDaoImpl();
		String message = null;
		try {
			message = routeDaoObj.removeRouteMasterDetails(routecode);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl: removeRouteMasterDetails Ending");
		return message;
	}

	public boolean addRoute(TransportForm routeForm) {
		return new TransportDaoImpl().addRoute(routeForm);
	}

	public boolean checkrouteNo(TransportPojo Pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkrouteNo Starting");
		boolean status = false;
		try {
			status = transportdao.checkrouteNo(Pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkrouteNo  Ending");
		return status;
	}

	public TransportVo editRouteMasterDetails(String routecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : editRouteMasterDetails Starting");
		TransportVo list = null;
		try {
			list = transportdao.editRouteMasterDetails(routecode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : editRouteMasterDetails  Ending");

		return list;
	}

	public List<TransportVo> searchDetails(String SearchName,String searchYear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchDetails Starting");
		List<TransportVo> list = null;
		try {
			list = transportdao.searchDetails(SearchName,searchYear);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchDetails  Ending");

		return list;

	}// route master end

	public List<DriverMsaterListVo> getdriverListService() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getdriverListService Starting");

		ArrayList<DriverMsaterListVo> driverlist = null;
		try {

			driverlist = transportdao.getdriverListDao();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getdriverListService  Ending");

		return driverlist;
	}

	/*public String addDriverService(TransportForm obj) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : addDriverService Starting");

		DriverMsaterListVo drivervo = new DriverMsaterListVo();
		String result = null;

		if (obj.getDrivercode().equalsIgnoreCase("")|| obj.getDrivercode() == null)

		{

			try {
				
				int res = transportdao.addDriverDao(drivervo);

				if (res == 1) {

					result = "Driver Creation Failed";
				} else {
					result = "Driver Created Successfully";
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

		}

		else if (!obj.getDrivercode().equalsIgnoreCase("")
				|| obj.getDrivercode() == null) {

			try {

				int res = transportdao.updateDriverDao(drivervo);

				if (res == 1) {

					result = "Driver Update Successfully";
				} else {
					result = "Driver not Update Successfully";
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

		}

		
		 * try {
		 * 
		 * drivervo.setAddress(obj.getAddress()); drivervo.setAge(obj.getAge());
		 * drivervo.setDateofBirth(obj.getDateofBirth());
		 * drivervo.setDateofJoin(obj.getDateofJoin());
		 * drivervo.setDl_issued_date(obj.getDl_issued_date());
		 * drivervo.setDl_validity(obj.getDl_validity());
		 * drivervo.setDriverName(obj.getDriverName());
		 * drivervo.setDrivingliecenseNo(obj.getDrivingliecenseNo());
		 * drivervo.setEmerg_contact(obj.getEmerg_contact());
		 * drivervo.setExperience(obj.getExperience());
		 * drivervo.setFather_name(obj.getFather_name());
		 * drivervo.setGender(obj.getGender());
		 * drivervo.setLicense(obj.getLicense());
		 * drivervo.setMobile(obj.getMobile()); drivervo.setType(obj.getType());
		 * drivervo.setDriverCode(obj.getDriver_code());
		 * drivervo.setCreateUser(obj.getCreateUser());
		 * 
		 * 
		 * } catch (Exception e) { logger.error(e.getMessage(), e);
		 * e.printStackTrace(); }
		 

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : addDriverService  Ending");
		return result;
	}*/

	public DriverMsaterListVo editDriverService(DriverMsaterListVo drivervo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : editDriverService Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : editDriverService  Ending");

		return transportdao.editDriverDao(drivervo);
	}

	public String deleteDriverService(String[] drivervo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : editDriverService Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : editDriverService  Ending");
		return transportdao.deleteDriverDao(drivervo);
	}

	public ArrayList<DriverMsaterListVo> searchDriverService(String searchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchDriverService Starting");

		ArrayList<DriverMsaterListVo> driverlist = transportdao
				.searchDriverDao(searchName);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchDriverService  Ending");

		return driverlist;
	}

	public boolean validateDriverService(DriverMsaterListVo drivervo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : validateDriverService Starting");

		boolean driver_validate = false;

		driver_validate = transportdao.validateDriverDao(drivervo);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : validateDriverService  Ending");
		return driver_validate;
	}
	
	
	// for driver license validation //
	
	public boolean validateLicenseService(DriverMsaterListVo drivervo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : validateLicenseService Starting");

		boolean license_validate = false;

		license_validate = transportdao.validateLicenseDao(drivervo);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : validateLicenseService  Ending");
		return license_validate;
	}
	
	
	
	
	

	public ArrayList<DriverMsaterListVo> getDriverNamesDetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getDriverNamesDetails Starting");
		ArrayList<DriverMsaterListVo> driverlist = null;
		try {
			driverlist = transportdao.getDriverNamesDetails();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getDriverNamesDetails  Ending");

		return driverlist;

	}

	public ArrayList<DriverMsaterListVo> getDriverEntireDetails(String driverid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getDriverEntireDetails Starting");
		ArrayList<DriverMsaterListVo> driverlist = null;
		try {
			driverlist = transportdao.getDriverEntireDetails(driverid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getDriverEntireDetails  Ending");

		return driverlist;

	}

	public ArrayList<DriverMsaterListVo> getDriverDetailsWhileUpdate(
			String vehiclecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getDriverDetailsWhileUpdate Starting");
		ArrayList<DriverMsaterListVo> driverlist = null;
		try {
			driverlist = transportdao.getDriverDetailsWhileUpdate(vehiclecode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getDriverDetailsWhileUpdate  Ending");

		return driverlist;

	}

	public DriverMsaterListVo getSingleDriverDetails(String vehiclecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getSingleDriverDetails Starting");
		DriverMsaterListVo driverlist = null;
		try {
			driverlist = transportdao.getSingleDriverDetails(vehiclecode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getSingleDriverDetails  Ending");

		return driverlist;

	}

	public ArrayList<StageMasterVo> getStopNames(String searchTerm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getStopNames Starting");
		ArrayList<StageMasterVo> driverlist = null;
		try {
			driverlist = transportdao.getStopNames(searchTerm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getStopNames  Ending");

		return driverlist;

	}

	public ArrayList<TransportVo> getRouteDetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRouteDetails Starting");
		ArrayList<TransportVo> transportlist = null;
		try {
			transportlist = transportdao.getRouteDetails();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRouteDetails  Ending");

		return transportlist;
	}

	public ArrayList<TransportVo> GetRouteEntireDetails(String route) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : GetRouteEntireDetails Starting");
		ArrayList<TransportVo> transportlist = null;
		try {
			transportlist = transportdao.GetRouteEntireDetails(route);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : GetRouteEntireDetails  Ending");

		return transportlist;

	}

	public TransportVo getRouteDetails(String vehiclecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRouteDetails Starting");
		TransportVo transportlist = null;
		try {
			transportlist = transportdao.getRouteDetails(vehiclecode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRouteDetails  Ending");

		return transportlist;

	}
	
	public List<TransportVo> getTransportMasterServiceDetailsXLS() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Transport ServiceImpl: getTransportMasterServiceDetails Starting");
		TransportDao tpSetupDao = new TransportDaoImpl();
		List<TransportVo> transportVOList = new ArrayList<TransportVo>();
		try {
			List<TransportPojo> tplist = tpSetupDao
					.getTransportMasterDaoDetailsXLS();
			for (TransportPojo gatDatas : tplist) {
				TransportVo tpVoObj = new TransportVo();

				tpVoObj.setEndTime(gatDatas.getEndTime());
				tpVoObj.setRouteName(gatDatas.getRouteName());
				tpVoObj.setRouteNo(gatDatas.getRouteNo());
				tpVoObj.setStratTime(gatDatas.getStartTime());
				tpVoObj.setTotalDistance(gatDatas.getTotalDistance());
				tpVoObj.setTotalStops(gatDatas.getTotalStops());
				tpVoObj.setRouteCode(gatDatas.getRouteCode());
				tpVoObj.setHalttime(gatDatas.getHalttime());
				tpVoObj.setDestination(gatDatas.getDestination());
				tpVoObj.setStopname(gatDatas.getStopname());

				tpVoObj.setArrivaltime(gatDatas.getArrivaltime());

				tpVoObj.setDeparturetime(gatDatas.getDeparturetime());

				tpVoObj.setDistance(gatDatas.getDistance());

				transportVOList.add(tpVoObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupServiceImpl: getFeeMasterServiceDetails Ending");
		return transportVOList;
	}

	@Override
	public boolean addDriverService(DriverMsaterListVo drivervo, String createuser) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : addDriverService Starting");

		boolean result = false;
		
		System.out.println("Drivercode::::::"+drivervo.getDriverCode());
		 
		
		if (drivervo.getDriverCode() ==null  || drivervo.getDriverCode().equalsIgnoreCase("")) 
		{
				System.out.println("Driver code is null or blank ");
			try {
				IDGenerator id = new IDGenerator();
				String drivercode1 = id.getPrimaryKeyID("transport_driver");
				
				drivervo.setDriverCode(drivercode1);
				
				System.out.println("insert operation");
				
				result = transportdao.addDriverDao( drivervo,
						 createuser);

				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

		} else{
			System.out.println("Driver code is not null:: "+drivervo.getDriverCode());

			try {

				System.out.println("update operation");

				result = transportdao.updateDriverDao( drivervo,createuser);


			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

		}

		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : addDriverService  Ending");
		return result;
	}
	
	public List<TransportVo> editRouteStages(TransportPojo tpMasterPojo, String accyear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : editRouteStages Starting");
		List<TransportVo> list = null;
		try {
			list = transportdao.editRouteStages(tpMasterPojo,accyear);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : editRouteStages  Ending");

		return list;

	}
	
	public List<TransportVo> unmappedRouteStages(TransportPojo tpMasterPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : unmappedRouteStages Starting");
		List<TransportVo> list = null;
		try {
			list = transportdao.unmappedRouteStages(tpMasterPojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : unmappedRouteStages  Ending");

		return list;

	}

	@Override
	public ArrayList<TransportVo> getRouteDetailsByLocation(String transferlocation) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : unmappedRouteStages Starting");
		ArrayList<TransportVo> list = null;
		try {
			list = transportdao.getRouteDetailsByLocation(transferlocation);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : unmappedRouteStages  Ending");

		return list;

	}

	
	
	public ArrayList<TransportVo> getRouteDetailsByName() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : unmappedRouteStages Starting");
		ArrayList<TransportVo> list = null;
		try {
			list = transportdao.getRouteDetailsByName();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : unmappedRouteStages  Ending");

		return list;

	}

	@Override
	public ArrayList<TransportVo> getStudentBusCardDetails(TransportVo obj) {
		TransportDao list = new TransportDaoImpl();
		return list.getStudentBusCardDetails(obj);
	}

	public List<VehicleTypeVo> searchVehicletypeDetails(String SearchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchVehicletypeDetails Starting");
		List<VehicleTypeVo> list = null;
		try {
			list = transportdao.searchVehicletypeDetails(SearchName);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchVehicletypeDetails  Ending");

		return list;

	}
	
	public String insertVehicleType(TransportCategoryForm form,String createCode) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : insertVehicleType Starting");
		
		String status = "";
		try {
			
			status = transportdao.insertVehicleType(form,createCode);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : insertVehicleType  Ending");
		return status;
	}
	
	public List<VehicleTypeVo> getAllvehicletypeDetails() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAllvehicleTypeDetails Starting");
		List<VehicleTypeVo> getvehiclelist = null;
		try {
			getvehiclelist = transportdao.getAllvehicletypeDetails();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAllvehicleTypeDetails  Ending");
		return getvehiclelist;
	}

	public VehicleTypeVo edittransporttype(String transportCatergory) {
		return transportdao.edittransporttype(transportCatergory);
	}

	public String deleteVehicleType(String[] code) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : deleteVehicleType Starting");
		String status = null;
		try {
			status = transportdao.deleteVehicleType(code);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : deleteVehicleType  Ending");
		return status;
	}
	
	public boolean validateVehicleType(VehicleTypeVo vehicleadd) 
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : validateVehicleType Starting");

		boolean vehi_validate = false;

		vehi_validate = transportdao.validateVehicleType(vehicleadd);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : validateVehicleType  Ending");
		return vehi_validate;
	}
	@Override
	public ArrayList<TransportVo> getonlinelist(String locationid,
			String accyear, String classid, String setionid, String paymodeid,
			String paymenttype,String termId) {
		
		return transportdao.getonlinelist(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId);
	}


	public TransportVo gettransportdetailsstudentwise(TransportVo tvo) {
		// TODO Auto-generated method stub
		TransportVo list = null;
		list= transportdao.gettransportdetailsstudentwise(tvo);
		return list;
		 
	}

	@Override
	public ArrayList<TransportVo> settranporttermdetailsforstudent(
			TransportVo tvo) {
		ArrayList<TransportVo> list = null;
		list= transportdao.settranporttermdetailsforstudent(tvo);
		return list;
	}

	@Override
	public ArrayList<TransportVo> getRouteNamelist(TransportVo tvo) {
		ArrayList<TransportVo> list = null;
		list= transportdao.getRouteNamelist(tvo);
		return list;
	}

	@Override
	public ArrayList<TransportVo> getstoplist(TransportVo tvo) {
		ArrayList<TransportVo> list = null;
		list= transportdao.getstoplist(tvo);
		return list;
	}

	@Override
	public String getamountandstatus(TransportVo tvo) {
	
		return transportdao.getamountandstatus(tvo);
		 
	}

	@Override
	public String savetransportrequest(TransportPojo pojo) {
		return transportdao.savetransportrequest(pojo);
	}

	@Override
	public List<StudentRegistrationVo> searchAllAcademicYearDetailsTrans(String locationId, String accYear) {
		return transportdao.searchAllAcademicYearDetailsTrans(locationId,accYear);
	}

	@Override
	public List<StudentRegistrationVo> getStudentList(String academic_year, String schoolLocation) {
		return transportdao.getStudentList(academic_year,schoolLocation);
	}

	@Override
	public List<StudentRegistrationVo> getStudentListByClass(String locationid, String accyear, String classname) {
		return transportdao.getStudentListByClass(locationid,accyear,classname);
	}

	@Override
	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname,String sectionid) {
		return transportdao.getStudentListBySection(locationid,accyear,classname,sectionid);
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchByList(String searchTerm) {
		return transportdao.getStudentSearchByList(searchTerm);
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchListByAccYear(String searchTerm, String accyear) {
		return transportdao.getStudentSearchListByAccYear(searchTerm,accyear);
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchListByLocation(String searchTerm, String locationid) {
		return transportdao.getStudentSearchListByLocation(searchTerm,locationid);
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchByFilter(String searchTerm, String locationid, String accyear,
			String classname) {
		return transportdao.getStudentSearchByFilter(searchTerm,locationid,accyear,classname);
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchByClass(String searchTerm, String locationid, String accyear,
			String classname) {
		return transportdao.getStudentSearchByClass(searchTerm,locationid,accyear,classname);
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchByAllFilter(String searchTerm, String locationid, String accyear,
			String classname, String sectionid) {
		return transportdao.getStudentSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);	}

	@Override
	public String waivedOfftransportrequest(TransportPojo pojo) {
		return transportdao.waivedOfftransportrequest(pojo);
	}

	@Override
	public ArrayList<TransportVo> getMonthList(String accyear,String loc_id) {
		return transportdao.getMonthList(accyear,loc_id);
	}
	@Override
	public ArrayList<TransportVo> getFeeCollectionPaymodeReport(
			String locationid, String accyear, String classid, String setionid,
			String paymodeid,String paymenttype,String termId) {
		
		return transportdao.getFeeCollectionPaymodeReport(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId);
	}	
}






