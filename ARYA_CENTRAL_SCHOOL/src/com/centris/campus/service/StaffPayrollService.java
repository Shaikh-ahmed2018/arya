package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.LeaveRequestForm;
import com.centris.campus.forms.TeacherForm;
import com.centris.campus.vo.PayRollVo;
import com.centris.campus.vo.StaffPayrollListVo;


public interface StaffPayrollService {
	
	public ArrayList<StaffPayrollListVo> getPayrollList(String year,String month);
	public ArrayList<PayRollVo> getFlatpayRollProcess(String year,String month,String userId);
	public ArrayList<PayRollVo> getPayrollDetails(int year,int month);
	public ArrayList<PayRollVo> getEmpMonthPayrollDetails(String month,String year,String empId);
	public List<PayRollVo> getPayRorllMonthList(String accyearid);
	public List<PayRollVo> getPayRollList(String accyearid, String locationId, String monthId, String yearname);
	public String GenerateMultiplePayroll(PayRollVo vo);
	public ArrayList<PayRollVo> getAllPayrollList(PayRollVo payrollvo);
	public ArrayList<PayRollVo> getGeneratePayrollList(LeaveRequestForm payrollform, String empId);
	public String getLoctionName(String locationId);
	public String getAccYearName(String yearvalcode);
	public String getMonthName(String yearvalcode, String monthvalcode);
	public String getYearName(String month, String year);
	public List<PayRollVo> getGeneratedPayRollList(String accyearid, String locationId, String monthId,String yearname);
	public List<PayRollVo> getEmpMonthPayrollDetailsDownload(String accyear, String locationId, String monthId,String yearname);

}
