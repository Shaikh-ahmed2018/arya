package com.centris.campus.forms;

import java.io.File;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class DriverTransportForm extends ActionForm {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
    private String driving_license_types;
	private FormFile licensedrive;
	private String status;
	private String uploadinglicense;
	private String licenseupload;
	
	
	
	
	
	public String getDriving_license_types() {
		return driving_license_types;
	}
	public void setDriving_license_types(String driving_license_types) {
		this.driving_license_types = driving_license_types;
	}
	public String getLicenseupload() {
		return licenseupload;
	}
	public void setLicenseupload(String licenseupload) {
		this.licenseupload = licenseupload;
	}
	public String getUploadinglicense() {
		return uploadinglicense;
	}
	public void setUploadinglicense(String uploadinglicense) {
		this.uploadinglicense = uploadinglicense;
	}

	
	
	

	
	
	
	
	
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public FormFile getLicensedrive() {
		return licensedrive;
	}
	public void setLicensedrive(FormFile licensedrive) {
		this.licensedrive = licensedrive;
	}
	
	
	public String getDriver_code() {
		return driver_code;
	}
	public void setDriver_code(String driver_code) {
		this.driver_code = driver_code;
	}
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
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	
	
	
	
	
	

}
