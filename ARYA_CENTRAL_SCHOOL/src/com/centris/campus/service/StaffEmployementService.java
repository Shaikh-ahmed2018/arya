package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.forms.StaffEmployementForm;
import com.centris.campus.serviceImpl.StaffEmployementServiceImpl;
import com.centris.campus.vo.StaffEmployementVo;
import com.centris.campus.vo.StaffSearchVo;

public interface StaffEmployementService {
	
	public StaffEmployementVo getStaffEmployementEntry(String teachercode, String academic_year);
	public String saveStaffSalaryDetails(StaffEmployementForm staffForm);
	public boolean validateEmployeePfNumber(String emppfno,String empid);

	public boolean validateBankAccNumber(String accnumber,String empid);
	public String saveStaffIncomeTaxTDSDeductionDetails(StaffEmployementForm staffForm);

}
