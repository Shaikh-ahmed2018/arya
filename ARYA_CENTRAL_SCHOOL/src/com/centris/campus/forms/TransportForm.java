package com.centris.campus.forms;

import java.sql.Date;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class TransportForm extends ActionForm{

	public String vehicleregno;
	public String vehicletype;
	public String typeofbody;
	public String makersname;
	public String manufacturerdate;
	public String chassisno;
	public String settingcapacity;
	public String fuelengine;
	public String colorofbody;
	
	public String companyname;
	public String issueddate;
	public String expirydate;
	public String doneby;
	private String licensetodrive;
	private String routename;
	private String halttime;
	private String totaldistance;
	private String routeno;
	private String destination;
	private String totalstops;
	private String driverCode;
	private String updatevehicleCode;
	
	private String status;
	
	private String routecodeid;
	private String enginenumber;
	private String taxpaid;
	private String fc;
	private String permitvalidity;
	private String nameofvehicle;
	private Date expireDate;
	private String pollution;
	private String hrcfileid;
	private String hinsurancefileid;
	private String taxexpirydate;
	
	
	
	
	public String getHrcfileid() {
		return hrcfileid;
	}
	public void setHrcfileid(String hrcfileid) {
		this.hrcfileid = hrcfileid;
	}
	public String getHinsurancefileid() {
		return hinsurancefileid;
	}
	public void setHinsurancefileid(String hinsurancefileid) {
		this.hinsurancefileid = hinsurancefileid;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getTaxexpirydate() {
		return taxexpirydate;
	}
	public void setTaxexpirydate(String taxexpirydate) {
		this.taxexpirydate = taxexpirydate;
	}
	public String getIssueddate() {
		return issueddate;
	}
	public String getEnginenumber() {
		return enginenumber;
	}
	public void setEnginenumber(String enginenumber) {
		this.enginenumber = enginenumber;
	}
	public void setIssueddate(String issueddate) {
		this.issueddate = issueddate;
	}
	public String getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}
	public String getDoneby() {
		return doneby;
	}
	public void setDoneby(String doneby) {
		this.doneby = doneby;
	}
	public String getLicensetodrive() {
		return licensetodrive;
	}
	public void setLicensetodrive(String licensetodrive) {
		this.licensetodrive = licensetodrive;
	}
	public String getRoutename() {
		return routename;
	}
	public void setRoutename(String routename) {
		this.routename = routename;
	}
	public String getHalttime() {
		return halttime;
	}
	public void setHalttime(String halttime) {
		this.halttime = halttime;
	}
	public String getTotaldistance() {
		return totaldistance;
	}
	public void setTotaldistance(String totaldistance) {
		this.totaldistance = totaldistance;
	}
	public String getRouteno() {
		return routeno;
	}
	public void setRouteno(String routeno) {
		this.routeno = routeno;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTotalstops() {
		return totalstops;
	}
	public void setTotalstops(String totalstops) {
		this.totalstops = totalstops;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}


	private FormFile rcfile;
	private FormFile insurancefile;
	private String rcfile1;
	private String insurancefile1;
	
	
	
	public FormFile getRcfile() {
		return rcfile;
	}
	public void setRcfile(FormFile rcfile) {
		this.rcfile = rcfile;
	}
	public FormFile getInsurancefile() {
		return insurancefile;
	}
	public String getNameofvehicle() {
		return nameofvehicle;
	}
	public void setNameofvehicle(String nameofvehicle) {
		this.nameofvehicle = nameofvehicle;
	}
	public void setInsurancefile(FormFile insurancefile) {
		this.insurancefile = insurancefile;
	}
	public String getRcfile1() {
		return rcfile1;
	}
	public void setRcfile1(String rcfile1) {
		this.rcfile1 = rcfile1;
	}
	public String getInsurancefile1() {
		return insurancefile1;
	}
	public void setInsurancefile1(String insurancefile1) {
		this.insurancefile1 = insurancefile1;
	}
	public String getVehicleregno() {
		return vehicleregno;
	}
	public void setVehicleregno(String vehicleregno) {
		this.vehicleregno = vehicleregno;
	}
	public String getVehicletype() {
		return vehicletype;
	}
	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}
	public String getTypeofbody() {
		return typeofbody;
	}
	public void setTypeofbody(String typeofbody) {
		this.typeofbody = typeofbody;
	}
	public String getMakersname() {
		return makersname;
	}
	public void setMakersname(String makersname) {
		this.makersname = makersname;
	}
	public String getManufacturerdate() {
		return manufacturerdate;
	}
	public void setManufacturerdate(String manufacturerdate) {
		this.manufacturerdate = manufacturerdate;
	}
	public String getChassisno() {
		return chassisno;
	}
	public void setChassisno(String chassisno) {
		this.chassisno = chassisno;
	}
	public String getSettingcapacity() {
		return settingcapacity;
	}
	public void setSettingcapacity(String settingcapacity) {
		this.settingcapacity = settingcapacity;
	}
	public String getFuelengine() {
		return fuelengine;
	}
	public void setFuelengine(String fuelengine) {
		this.fuelengine = fuelengine;
	}
	public String getColorofbody() {
		return colorofbody;
	}
	public void setColorofbody(String colorofbody) {
		this.colorofbody = colorofbody;
	}
	
	
	// Driver master start
			private String driver_code;
			private String type;
			private String driverName;
			private String father_name;
			private String dateofBirth;
			private String age;
			private String gender;
			private String mobile;
			private String emerg_contact;
			private String dateofJoin;
			private String experience;
			private String address;
			private String drivingliecenseNo;
			private String dl_issued_date;
			private String dl_validity;
			private String license;
			private String createUser;
			
			public String getType() {
				return type;
			}
			public void setType(String type) {
				this.type = type;
			}
			public String getDriverName() {
				return driverName;
			}
			public void setDriverName(String driverName) {
				this.driverName = driverName;
			}
			public String getFather_name() {
				return father_name;
			}
			public void setFather_name(String father_name) {
				this.father_name = father_name;
			}
			public String getDateofBirth() {
				return dateofBirth;
			}
			public void setDateofBirth(String dateofBirth) {
				this.dateofBirth = dateofBirth;
			}
			public String getAge() {
				return age;
			}
			public void setAge(String age) {
				this.age = age;
			}
			public String getGender() {
				return gender;
			}
			public void setGender(String gender) {
				this.gender = gender;
			}
			public String getMobile() {
				return mobile;
			}
			public void setMobile(String mobile) {
				this.mobile = mobile;
			}
			public String getEmerg_contact() {
				return emerg_contact;
			}
			public void setEmerg_contact(String emerg_contact) {
				this.emerg_contact = emerg_contact;
			}
			public String getDateofJoin() {
				return dateofJoin;
			}
			public void setDateofJoin(String dateofJoin) {
				this.dateofJoin = dateofJoin;
			}
			public String getExperience() {
				return experience;
			}
			public void setExperience(String experience) {
				this.experience = experience;
			}
			public String getAddress() {
				return address;
			}
			public void setAddress(String address) {
				this.address = address;
			}
			public String getDrivingliecenseNo() {
				return drivingliecenseNo;
			}
			public void setDrivingliecenseNo(String drivingliecenseNo) {
				this.drivingliecenseNo = drivingliecenseNo;
			}
			public String getDl_issued_date() {
				return dl_issued_date;
			}
			public void setDl_issued_date(String dl_issued_date) {
				this.dl_issued_date = dl_issued_date;
			}
			public String getDl_validity() {
				return dl_validity;
			}
			public void setDl_validity(String dl_validity) {
				this.dl_validity = dl_validity;
			}
			
			
			public String getDriver_code() {
				return driver_code;
			}
			public void setDriver_code(String driver_code) {
				this.driver_code = driver_code;
			}
			public String getCreateUser() {
				return createUser;
			}
			public void setCreateUser(String createUser) {
				this.createUser = createUser;
			}
			
			//Driver Master End
			
			
			// Fuel Maintenance Start
			
			private String date;
			private String vehiclename;
			private String vehiclenumber;
			private String drivername;
			private String fuelType;
			private String quantity;
			private String price;
			private String meterReading;
			private String location;
		    private String createuser;
		    private String fuelcode;
		    private String drivercode;
		    private String vehiclecode;
		    private String time;
			
			
			
			public String getDate() {
				return date;
			}
			public void setDate(String date) {
				this.date = date;
			}
			public String getVehiclename() {
				return vehiclename;
			}
			public void setVehiclename(String vehiclename) {
				this.vehiclename = vehiclename;
			}
			public String getVehiclenumber() {
				return vehiclenumber;
			}
			public void setVehiclenumber(String vehiclenumber) {
				this.vehiclenumber = vehiclenumber;
			}
			public String getDrivername() {
				return drivername;
			}
			public void setDrivername(String drivername) {
				this.drivername = drivername;
			}
			public String getFuelType() {
				return fuelType;
			}
			public void setFuelType(String fuelType) {
				this.fuelType = fuelType;
			}
			public String getQuantity() {
				return quantity;
			}
			public void setQuantity(String quantity) {
				this.quantity = quantity;
			}
			public String getPrice() {
				return price;
			}
			public void setPrice(String price) {
				this.price = price;
			}
			public String getMeterReading() {
				return meterReading;
			}
			public void setMeterReading(String meterReading) {
				this.meterReading = meterReading;
			}
			public String getLocation() {
				return location;
			}
			public void setLocation(String location) {
				this.location = location;
			}
			public String getCreateuser() {
				return createuser;
			}
			public void setCreateuser(String createuser) {
				this.createuser = createuser;
			}
			public String getFuelcode() {
				return fuelcode;
			}
			public void setFuelcode(String fuelcode) {
				this.fuelcode = fuelcode;
			}
			public String getVehiclecode() {
				return vehiclecode;
			}
			public void setVehiclecode(String vehiclecode) {
				this.vehiclecode = vehiclecode;
			}
			public String getDrivercode() {
				return drivercode;
			}
			public void setDrivercode(String drivercode) {
				this.drivercode = drivercode;
			}
			public String getTime() {
				return time;
			}
			public void setTime(String time) {
				this.time = time;
			}

             //Fuel Maintenance End
			
			private String stopNoArray;
			private String stopNameArray;
			private String stopArrivalTimeArray;
			private String stopHaltTimeArray;
			private String stopDepartureTimeArray;
			private String stopDistanceArray;
			private String routeList;
			private String currentUser;
			private String routeCode;
			private String updatedRouteCode;
			
			
			
			public String getUpdatedRouteCode() {
				return updatedRouteCode;
			}
			public void setUpdatedRouteCode(String updatedRouteCode) {
				this.updatedRouteCode = updatedRouteCode;
			}
			public String getRouteCode() {
				return routeCode;
			}
			public void setRouteCode(String routeCode) {
				this.routeCode = routeCode;
			}
			private String routeNamesList;
			
			public String getCurrentUser() {
				return currentUser;
			}
			public void setCurrentUser(String currentUser) {
				this.currentUser = currentUser;
			}
			public String getRouteNamesList() {
				return routeNamesList;
			}
			public void setRouteNamesList(String routeNamesList) {
				this.routeNamesList = routeNamesList;
			}
			public String getStopNoArray() {
				return stopNoArray;
			}
			public void setStopNoArray(String stopNoArray) {
				this.stopNoArray = stopNoArray;
			}
			public String getStopNameArray() {
				return stopNameArray;
			}
			public void setStopNameArray(String stopNameArray) {
				this.stopNameArray = stopNameArray;
			}
			public String getStopArrivalTimeArray() {
				return stopArrivalTimeArray;
			}
			public void setStopArrivalTimeArray(String stopArrivalTimeArray) {
				this.stopArrivalTimeArray = stopArrivalTimeArray;
			}
			public String getStopHaltTimeArray() {
				return stopHaltTimeArray;
			}
			public void setStopHaltTimeArray(String stopHaltTimeArray) {
				this.stopHaltTimeArray = stopHaltTimeArray;
			}
			public String getStopDepartureTimeArray() {
				return stopDepartureTimeArray;
			}
			public void setStopDepartureTimeArray(String stopDepartureTimeArray) {
				this.stopDepartureTimeArray = stopDepartureTimeArray;
			}
			public String getStopDistanceArray() {
				return stopDistanceArray;
			}
			public void setStopDistanceArray(String stopDistanceArray) {
				this.stopDistanceArray = stopDistanceArray;
			}
			public String getRouteList() {
				return routeList;
			}
			public void setRouteList(String routeList) {
				this.routeList = routeList;
			}
			public String getPollution() {
				return pollution;
			}
			public void setPollution(String pollution) {
				this.pollution = pollution;
			}
			public String getLicense() {
				return license;
			}
			public void setLicense(String license) {
				this.license = license;
			}
			public String getUpdatevehicleCode() {
				return updatevehicleCode;
			}
			public void setUpdatevehicleCode(String updatevehicleCode) {
				this.updatevehicleCode = updatevehicleCode;
			}
			public String getTaxpaid() {
				return taxpaid;
			}
			public void setTaxpaid(String taxpaid) {
				this.taxpaid = taxpaid;
			}
			public String getFc() {
				return fc;
			}
			public void setFc(String fc) {
				this.fc = fc;
			}
			public String getPermitvalidity() {
				return permitvalidity;
			}
			public void setPermitvalidity(String permitvalidity) {
				this.permitvalidity = permitvalidity;
			}
			public String getStatus() {
				return status;
			}
			public void setStatus(String status) {
				this.status = status;
			}
			public String getDriverCode() {
				return driverCode;
			}
			public void setDriverCode(String driverCode) {
				this.driverCode = driverCode;
			}
			public String getRoutecodeid() {
				return routecodeid;
			}
			public void setRoutecodeid(String routecodeid) {
				this.routecodeid = routecodeid;
			}
			
			
			
	

}
