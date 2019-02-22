package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.LeaveRequestForm;
import com.centris.campus.vo.PayRollVo;
import com.centris.campus.vo.StaffPayrollListVo;

public interface StaffPayrollDao {

	public ArrayList<StaffPayrollListVo> getPayrollList(String year,String month);
	public ArrayList<PayRollVo> getPayrollDetails(int year,int month);
	public ArrayList<PayRollVo> getEmpMonthPayrollDetails(String month,String  year, String empId);
	public ArrayList<PayRollVo> getPayRorllMonthList(String accyearid);
	public ArrayList<PayRollVo> getPayRollList(String accyearid, String locationId, String monthId, String yearname);
	public String GenerateMultiplePayroll(PayRollVo vo);
	public ArrayList<PayRollVo> getAllPayrollList(PayRollVo payrollvo);
	public ArrayList<PayRollVo> getGeneratePayrollList(LeaveRequestForm payrollform, String empId);
	public String getLoctionName(String locationId);
	public String getAccYearName(String yearvalcode);
	public String getMonthName(String yearvalcode, String monthvalcode);
	public String getYearName(String month, String year);
	public ArrayList<PayRollVo> getGeneratedPayRollList(String accyearid, String locationId, String monthId,String yearname);
	public ArrayList<PayRollVo> getEmpMonthPayrollDetailsDownload(String accyear, String locationId, String monthId,String yearname);
}
