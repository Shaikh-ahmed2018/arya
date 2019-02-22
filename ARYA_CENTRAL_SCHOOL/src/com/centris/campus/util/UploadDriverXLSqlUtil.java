package com.centris.campus.util;

public class UploadDriverXLSqlUtil {
	
	public static final String CHECK_BEFORINSERT_COUNT= "select count(*) from campus_teachers";
	
	public static final String REGISTRATION_DUPLICATE= "select count(*) from campus_teachers where  registerId=?";
	
	public static final String INSERT_DRIVER = "insert into transport_driver(DriverCode,type,Name,FatherName,DOB,MobileNo,EmergencyContactNo, Experience,Address,DOJ,Age,Gender,DLIssuedDate,DLExpirayDate,LicencetoDrive,CreateDate,CreateUser) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String DRIVER_DUPLICATE="select count(*) transport_driver from where DLNo=?";
	
	
}

