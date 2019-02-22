package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.LeaveRequestForm;
import com.centris.campus.forms.TeacherForm;
import com.centris.campus.service.StaffPayrollService;
import com.centris.campus.serviceImpl.StaffPayrollServiceImpl;
import com.centris.campus.vo.PayRollVo;
import com.centris.campus.vo.StaffPayrollListVo;

public class StaffPayrollBD {
	
	public ArrayList<StaffPayrollListVo> getPayrollList(String year,String month) {

		StaffPayrollService service = new StaffPayrollServiceImpl();
		
		return service.getPayrollList(year,month);

	}
	
	public ArrayList<PayRollVo> getFlatpayRollProcess(String year,String month,String userId) {

		StaffPayrollService service = new StaffPayrollServiceImpl();
		
		return service.getFlatpayRollProcess(year,month,userId);

	}
	
	public ArrayList<PayRollVo> getPayrollDetails(int year,int month) {

		StaffPayrollService service = new StaffPayrollServiceImpl();
		
		return service.getPayrollDetails(year,month);

	}
	
	public ArrayList<PayRollVo> getEmpMonthPayrollDetails(String month,String year,String empId) {

		StaffPayrollService service = new StaffPayrollServiceImpl();
		
		return service.getEmpMonthPayrollDetails(month,year,empId);

	}

	public List<PayRollVo> getPayRorllMonthList(String accyearid) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		
		return service.getPayRorllMonthList(accyearid);
	}

	public List<PayRollVo> getPayRollList(String accyearid, String locationId, String monthId, String yearname) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
	
		return service.getPayRollList(accyearid,locationId,monthId,yearname);
	}

	public String GenerateMultiplePayroll(PayRollVo vo) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		
		return service.GenerateMultiplePayroll(vo);
	}

	public ArrayList<PayRollVo> getAllPayrollList(PayRollVo payrollvo) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		
		return service.getAllPayrollList(payrollvo);
	}

	public ArrayList<PayRollVo> getGeneratePayrollList(LeaveRequestForm payrollform, String empId) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		
		return service.getGeneratePayrollList(payrollform,empId);
	}

	public String getLoctionName(String locationId) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		return service.getLoctionName(locationId);
	}

	public String getAccYearName(String yearvalcode) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		return service.getAccYearName(yearvalcode);
	}

	public String getMonthName(String yearvalcode, String monthvalcode) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		return service.getMonthName(yearvalcode,monthvalcode);
	}

	public String getYearName(String month, String year) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		return service.getYearName(month,year);
	}

	public List<PayRollVo> getGeneratedPayRollList(String accyearid, String locationId, String monthId,String yearname) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		return service.getGeneratedPayRollList(accyearid,locationId,monthId,yearname);
	}

	public List<PayRollVo> getEmpMonthPayrollDetailsDownload(String accyear, String locationId, String monthId,String yearname) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		return service.getEmpMonthPayrollDetailsDownload(accyear,locationId,monthId,yearname);
	}
	
	
	

}
