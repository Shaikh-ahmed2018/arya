package com.centris.campus.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.centris.campus.dao.StaffPayrollDao;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.StaffEmployementDaoImpl;
import com.centris.campus.daoImpl.StaffPayrollDaoImpl;
import com.centris.campus.forms.LeaveRequestForm;
import com.centris.campus.forms.TeacherForm;
import com.centris.campus.service.StaffPayrollService;
import com.centris.campus.util.CalculateStaffSalaryDetails;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.PayRollVo;
import com.centris.campus.vo.StaffDetailsForPayrollVo;
import com.centris.campus.vo.StaffPayrollListVo;

public class StaffPayrollServiceImpl implements StaffPayrollService{
	
	private static final Logger logger = Logger.getLogger(StaffPayrollServiceImpl.class);


	@Override
	public ArrayList<StaffPayrollListVo> getPayrollList(String year,String month) {
		// TODO Auto-generated method stub

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollServiceImpl : getPayrollList  Starting");
		ArrayList<StaffPayrollListVo> payrollList = null;
		StaffPayrollDao staffDao=new StaffPayrollDaoImpl();
		try {

			payrollList = staffDao.getPayrollList(year,month);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollServiceImpl : getPayrollList  Starting");

		return payrollList;
	
	}


	@Override
	public ArrayList<PayRollVo> getFlatpayRollProcess(String year,
			String month,String userId) {
		// TODO Auto-generated method stub

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollServiceImpl : getFlatpayRollProcess  Starting");
		
		ArrayList<PayRollVo> emp_payroll_list=new ArrayList<PayRollVo>();
		int count=0;
		Connection connection=null;
		
		try {
			
			
			connection=JDBCConnection.getSeparateConnection();
		
			int month_int=Integer.parseInt(month);
			int year_int=Integer.parseInt(year);
		
			int totalDaysInMonth = HelperClass.getDaysByMonthAndYear(month_int, year_int);
		
			Map<String,StaffDetailsForPayrollVo> emp_details_map=new StaffPayrollDaoImpl().getEmployeeDetails(month_int, year_int,connection);
			
			
			Iterator itr = emp_details_map.entrySet().iterator();
		
		
        while(itr.hasNext())
        {
        	
        		
        	count++;
        	
        	PayRollVo payrollvo=new PayRollVo();
        	
        	Map.Entry<String, StaffDetailsForPayrollVo> empdetails=(Map.Entry<String, StaffDetailsForPayrollVo>)itr.next();
          
        	StaffDetailsForPayrollVo employeeDetailsForPayrollVO=emp_details_map.get(empdetails.getKey());
    
        	List<String> holiday =employeeDetailsForPayrollVO.getHolidayDates();
        	
        	List<String> presentdates =employeeDetailsForPayrollVO.getTotalPresentDate();
        	
        	List<String> absentdates =employeeDetailsForPayrollVO.getTotalAbsentDate();
        	
        	List<String> leavdates =employeeDetailsForPayrollVO.getLeavedates();
        	
        	
        	
        	HashSet<String> payyabledays=new HashSet<String>();
        	
        	if(!(presentdates.size()==0)){
        	for(int i=0;i<presentdates.size();i++){
        		
        		payyabledays.add(presentdates.get(i));
        	}
        	}
        	
        	if(!(holiday.size()==0)){
        		for(int i=0;i<holiday.size();i++){
        			
        			payyabledays.add(holiday.get(i));
        		}
        	}
        	
        	
        	if("N".equalsIgnoreCase(employeeDetailsForPayrollVO.getStaffSalaryDetails().getLeavedeductions()))
        			{   
        		
        		if(!(leavdates.size()==0)){
            		for(int i=0;i<leavdates.size();i++){
            			
            			payyabledays.add(leavdates.get(i));
            		}
            	}
        	
        	}
        	
        int payabledayscount=payyabledays.size();
        	
        CalculateStaffSalaryDetails cal_payroll=new CalculateStaffSalaryDetails();
        	
        double Month_Basic_Sal = (employeeDetailsForPayrollVO.getStaffSalaryDetails().getBasic()/totalDaysInMonth)*payyabledays.size();
        
        payrollvo.setUserId(userId);
        payrollvo.setSno(count);
    	payrollvo.setEmpId(empdetails.getKey());
    	payrollvo.setRegId(employeeDetailsForPayrollVO.getRegId());
    	payrollvo.setEmpName(employeeDetailsForPayrollVO.getEmpName());
/*    	payrollvo.setAccountnumber(employeeDetailsForPayrollVO.getStaffSalaryDetails().getAccnumber());
*/    	
    	payrollvo.setMonth(month_int);
    	payrollvo.setMonthName(HelperClass.getMonthFullName(month_int+""));
    	payrollvo.setYear(year_int);
    	
    	payrollvo.setMonthDays(totalDaysInMonth);
    	payrollvo.setPayabledays(payabledayscount);
    	payrollvo.setNo_of_leaves(Integer.parseInt(employeeDetailsForPayrollVO.getTotalLeave()));
    	
    	payrollvo.setBasic(Math.round(Month_Basic_Sal));
/*    	payrollvo.setDa(cal_payroll.daMonth(employeeDetailsForPayrollVO,payabledayscount,totalDaysInMonth));
*/    	payrollvo.setCa(cal_payroll.caMonth(employeeDetailsForPayrollVO,payabledayscount,totalDaysInMonth));
    	payrollvo.setHra(cal_payroll.hraMonth(employeeDetailsForPayrollVO,payabledayscount,totalDaysInMonth));
    	payrollvo.setMedicalallowances(cal_payroll.medicalAllowMonth(employeeDetailsForPayrollVO,payabledayscount,totalDaysInMonth));
    	payrollvo.setConvieance(cal_payroll.convieanceMonth(employeeDetailsForPayrollVO,payabledayscount,totalDaysInMonth));
    	
    		
    	payrollvo.setEmployee_pf(cal_payroll.empPfMonth(employeeDetailsForPayrollVO,payabledayscount,totalDaysInMonth));
    	payrollvo.setEmployer_pf(cal_payroll.emprPfMonth(employeeDetailsForPayrollVO,payabledayscount,totalDaysInMonth));
    	payrollvo.setPt(cal_payroll.ptMonth(employeeDetailsForPayrollVO,payabledayscount,totalDaysInMonth));
        payrollvo.setIncomeTax(cal_payroll.incomeTaxMonth(employeeDetailsForPayrollVO,payabledayscount,totalDaysInMonth));	
    	
        double latededuction=cal_payroll.leavededuction(totalDaysInMonth, employeeDetailsForPayrollVO);
    	
    	if("Y".equalsIgnoreCase(employeeDetailsForPayrollVO.getStaffSalaryDetails().getLeavedeductions())){
    	
    		payrollvo.setLeavededuction(latededuction);
    	
    	}
    	
/*    	double otherDeductions=cal_payroll.otherDeductions(employeeDetailsForPayrollVO, payabledayscount, totalDaysInMonth);
*/     	
/*     	payrollvo.setOtherdeduction(otherDeductions);
*/     	
        
/*        double grossSalary=cal_payroll.calGross(Month_Basic_Sal,employeeDetailsForPayrollVO, payabledayscount, totalDaysInMonth);
        
        payrollvo.setGrosssalary(Math.round(grossSalary));
    	payrollvo.setNetsalary(Math.round(grossSalary-latededuction));*/
        	
    	emp_payroll_list.add(payrollvo);
    	
    		new StaffPayrollDaoImpl().savePayrollDetails(emp_payroll_list);
    	
        }
        
	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				if(connection != null && (!connection.isClosed())){
					
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollServiceImpl : getFlatpayRollProcess  Starting");

		return emp_payroll_list;
	
	}
	
	@Override
	public ArrayList<PayRollVo> getPayrollDetails(int year,int month) {
		// TODO Auto-generated method stub

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollServiceImpl : getPayrollDetails  Starting");
		ArrayList<PayRollVo> payrollList = null;
		StaffPayrollDao staffDao=new StaffPayrollDaoImpl();
		try {

			payrollList = staffDao.getPayrollDetails(year,month);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollServiceImpl : getPayrollDetails  Starting");

		return payrollList;
	
	}


	@Override
	public ArrayList<PayRollVo> getEmpMonthPayrollDetails(String month,String year, String empId) {
		// TODO Auto-generated method stub

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollServiceImpl : getEmpMonthPayrollDetails  Starting");
		ArrayList<PayRollVo> payrollList = null;
		StaffPayrollDao staffDao=new StaffPayrollDaoImpl();
		try {

			payrollList = staffDao.getEmpMonthPayrollDetails(month, year,empId);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollServiceImpl : getEmpMonthPayrollDetails  Starting");

		return payrollList;
	
	}


	@Override
	public List<PayRollVo> getPayRorllMonthList(String accyearid) {
		// TODO Auto-generated method stub

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollServiceImpl : getPayRorllMonthList  Starting");
		ArrayList<PayRollVo> payrollList = null;
		StaffPayrollDao staffDao=new StaffPayrollDaoImpl();
		try {

			payrollList = staffDao.getPayRorllMonthList(accyearid);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollServiceImpl : getPayRorllMonthList  Starting");

		return payrollList;
	
	}


	@Override
	public List<PayRollVo> getPayRollList(String accyearid, String locationId, String monthId,String yearname) {
		// TODO Auto-generated method stub

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollServiceImpl : getPayRollList  Starting");
		ArrayList<PayRollVo> payrollList = null;
		StaffPayrollDao staffDao=new StaffPayrollDaoImpl();
		try {

			payrollList = staffDao.getPayRollList(accyearid,locationId,monthId,yearname);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollServiceImpl : getPayRollList  Starting");

		return payrollList;
	
	}


	@Override
	public String GenerateMultiplePayroll(PayRollVo vo) {
		StaffPayrollDao staffDao=new StaffPayrollDaoImpl();
		
		return staffDao.GenerateMultiplePayroll(vo);
	}


	@Override
	public ArrayList<PayRollVo> getAllPayrollList(PayRollVo payrollvo) {
		StaffPayrollDao staffDao=new StaffPayrollDaoImpl();
		
		return staffDao.getAllPayrollList(payrollvo);
	}


	@Override
	public ArrayList<PayRollVo> getGeneratePayrollList(LeaveRequestForm payrollform, String empId) {
		StaffPayrollDao staffDao=new StaffPayrollDaoImpl();
		
		return staffDao.getGeneratePayrollList(payrollform,empId);
	}


	@Override
	public String getLoctionName(String locationId) {
		StaffPayrollDao staffDao=new StaffPayrollDaoImpl();
		
		return staffDao.getLoctionName(locationId);
	}


	@Override
	public String getAccYearName(String yearvalcode) {
		StaffPayrollDao staffDao=new StaffPayrollDaoImpl();
		
		return staffDao.getAccYearName(yearvalcode);
	}


	@Override
	public String getMonthName(String yearvalcode, String monthvalcode) {
		StaffPayrollDao staffDao=new StaffPayrollDaoImpl();
		
		return staffDao.getMonthName(yearvalcode,monthvalcode);
	}


	@Override
	public String getYearName(String month, String year) {
		StaffPayrollDao staffDao=new StaffPayrollDaoImpl();
		
		return staffDao.getYearName(month,year);
	}

	@Override
	public List<PayRollVo> getGeneratedPayRollList(String accyearid, String locationId, String monthId,String yearname) {
		// TODO Auto-generated method stub

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollServiceImpl : getPayRollList  Starting");
		ArrayList<PayRollVo> payrollList = null;
		StaffPayrollDao staffDao=new StaffPayrollDaoImpl();
		try {

			payrollList = staffDao.getGeneratedPayRollList(accyearid,locationId,monthId,yearname);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollServiceImpl : getPayRollList  Starting");

		return payrollList;
	
	}


	@Override
	public List<PayRollVo> getEmpMonthPayrollDetailsDownload(String accyear, String locationId, String monthId,String yearname) {
		// TODO Auto-generated method stub

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollServiceImpl : getPayRollList  Starting");
		ArrayList<PayRollVo> payrollList = null;
		StaffPayrollDao staffDao=new StaffPayrollDaoImpl();
		try {

			payrollList = staffDao.getEmpMonthPayrollDetailsDownload(accyear,locationId,monthId,yearname);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollServiceImpl : getPayRollList  Starting");

		return payrollList;
	
	}
	

}
