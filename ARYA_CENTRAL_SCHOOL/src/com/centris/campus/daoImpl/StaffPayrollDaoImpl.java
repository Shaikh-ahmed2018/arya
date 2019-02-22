package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.centris.campus.dao.StaffPayrollDao;
import com.centris.campus.forms.LeaveRequestForm;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.PayrollCalculations;
import com.centris.campus.util.StaffPayrollUtil;
import com.centris.campus.vo.PayRollVo;
import com.centris.campus.vo.SalaryDetailsVo;
import com.centris.campus.vo.StaffDetailsForPayrollVo;
import com.centris.campus.vo.StaffPayrollListVo;
import com.centris.campus.vo.StaffSearchVo;


public class StaffPayrollDaoImpl implements StaffPayrollDao{ 
	
	private static final Logger logger = Logger.getLogger(StaffPayrollDaoImpl.class);

	public ArrayList<StaffPayrollListVo> getPayrollList(String year,String month) {
	   
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollDaoImpl: getPayrollList : Starting");
		
		PreparedStatement ps_payroll_list = null;
		ResultSet rs_payroll=null;
		Connection conn = null;
	    
	    ArrayList<StaffPayrollListVo> payrollList=new ArrayList<StaffPayrollListVo>();
	 	
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			int currentYear=HelperClass.getCurrentYear();
			int mounthcount=12;
		
			ps_payroll_list = conn.prepareStatement(StaffPayrollUtil.GET_PAYROLL_LIST);
			
			if(year==null || "".equalsIgnoreCase(year)){
			
			
			for(int i=1;i<=mounthcount;i++){
				
			boolean flag=false;
			
			ps_payroll_list.setInt(1, i);
			ps_payroll_list.setInt(2, currentYear);
			
			System.out.println(HelperClass.getMonthFullName(i+"") +" ::: "+ps_payroll_list);
			
			rs_payroll=ps_payroll_list.executeQuery();
			
			StaffPayrollListVo payrollVo=new StaffPayrollListVo();
			payrollVo.setMonth(HelperClass.getMonthFullName(i+""));
			payrollVo.setYear(currentYear+"");
			payrollVo.setMonth_int(i);
			
			while(rs_payroll.next()){
				
				flag=true;
				
				payrollVo.setStatus("Generated");
				payrollVo.setCreatedby(getCreatedBY(rs_payroll.getString("CreatedBy")));
				payrollVo.setCreateTime(rs_payroll.getString("CreatedDate"));
				if(rs_payroll.getString("UpdatedBy")!=null && "".equalsIgnoreCase(rs_payroll.getString("UpdatedBy")) ){
					
					payrollVo.setUpdatedby(getCreatedBY(rs_payroll.getString("UpdatedBy")));
					payrollVo.setUpdatetime(rs_payroll.getString("UpdatedDate"));
				}else{
					
					payrollVo.setUpdatedby("-");
					payrollVo.setUpdatetime("-");
				}
				
				
			}
			
			if(!flag){
				
				payrollVo.setStatus("Not Generated");
				payrollVo.setCreatedby("-");
				payrollVo.setCreateTime("-");
				payrollVo.setUpdatedby("-");
				payrollVo.setUpdatetime("-");
			}
			
			payrollList.add(payrollVo);
			
			}
			
			}else{
				
			
				boolean flag=false;
				
				ps_payroll_list.setInt(1, Integer.parseInt(month));
				ps_payroll_list.setInt(2, Integer.parseInt(year));
				
				System.out.println(HelperClass.getMonthFullName(month) +" ::: "+ps_payroll_list);
				
				rs_payroll=ps_payroll_list.executeQuery();
				
				StaffPayrollListVo payrollVo=new StaffPayrollListVo();
				payrollVo.setMonth(HelperClass.getMonthFullName(month));
				payrollVo.setYear(year);
				
				while(rs_payroll.next()){
					
					flag=true;
					
					payrollVo.setStatus("Generated");
					payrollVo.setCreatedby(getCreatedBY(rs_payroll.getString("CreatedBy")));
					payrollVo.setCreateTime(rs_payroll.getString("CreatedDate"));
					if(rs_payroll.getString("UpdatedBy")!=null && "".equalsIgnoreCase(rs_payroll.getString("UpdatedBy")) ){
						
						payrollVo.setUpdatedby(getCreatedBY(rs_payroll.getString("UpdatedBy")));
						payrollVo.setUpdatetime(rs_payroll.getString("UpdatedDate"));
					}else{
						
						payrollVo.setUpdatedby("-");
						payrollVo.setUpdatetime("-");
					}
					
					
				}
				
				if(!flag){
					
					payrollVo.setStatus("Not Generated");
					payrollVo.setCreatedby("-");
					payrollVo.setCreateTime("-");
					payrollVo.setUpdatedby("-");
					payrollVo.setUpdatetime("-");
				}
				
				payrollList.add(payrollVo);
				
			}
			
			
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				
				if (ps_payroll_list != null&& (!ps_payroll_list.isClosed())) {
					ps_payroll_list.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollDaoImpl: getPayrollList: Ending");
		
		return payrollList;
	}
	
	
	
	public String getCreatedBY(String userID){
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffPayrollDaoImpl: getPayrollList : Starting");
			
			PreparedStatement ps_payroll_list = null;
			ResultSet rs_payroll=null;
			Connection conn = null;
			
			String userId="-";
		    
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
				
				ps_payroll_list = conn.prepareStatement(StaffPayrollUtil.GET_CREATEDBY);
				
					ps_payroll_list.setString(1, userID);
					
					rs_payroll=ps_payroll_list.executeQuery();
					
					
					while(rs_payroll.next()){
						
						userId=rs_payroll.getString("createdby");
						
					}
					
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					
					if (ps_payroll_list != null&& (!ps_payroll_list.isClosed())) {
						ps_payroll_list.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} catch (Exception e1) {

					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
			}

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffPayrollDaoImpl: getPayrollList: Ending");
			
			return userId;
		}
	
	
	public synchronized Map<String, StaffDetailsForPayrollVo> getEmployeeDetails(int month, int year,Connection connection) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: getEmployeeDetails :Starting");
		logger.setLevel(Level.DEBUG);
		
		Map<String, StaffDetailsForPayrollVo> salaryDetailsmap = new HashMap<String, StaffDetailsForPayrollVo>();
		StaffDetailsForPayrollVo employeeDetailsVo = null;
		Connection conn=null;
		
		try {
			
			conn=connection;

			List<StaffDetailsForPayrollVo> empList = getEmployeeList();
			
			for (int i = 0; i < empList.size(); i++) {
				
				employeeDetailsVo = new StaffDetailsForPayrollVo();

				String empId = empList.get(i).getEmpId();
				
				employeeDetailsVo.setEmpId(empId);
				employeeDetailsVo.setEmpName(empList.get(i).getEmpName());
				employeeDetailsVo.setRegId(empList.get(i).getRegId());

				employeeDetailsVo.setTotalDaysInMonth(HelperClass.getDaysByMonthAndYear(month, year) + "");

				Map<String, String> attandanceMap=getEmployeeTotalAttendance(empId, month, year);
				
				employeeDetailsVo.setTotalPresent(attandanceMap.get("totalPresent"));
				employeeDetailsVo.setTotalPresentDate(new ArrayList<String>(Arrays.asList(attandanceMap.get("totalpresentdates").split(","))));
				
				employeeDetailsVo.setTotalAbsent(attandanceMap.get("totalabsent"));
				employeeDetailsVo.setTotalAbsentDate(new ArrayList<String>(Arrays.asList(attandanceMap.get("totalabsentdates").split(","))));
				
				employeeDetailsVo.setTotalLeave(attandanceMap.get("totalleave"));
				employeeDetailsVo.setLeavedates(new ArrayList<String>(Arrays.asList(attandanceMap.get("totalleavedates").split(","))));
				
				employeeDetailsVo.setTotalHoliday(attandanceMap.get("totalholiday"));
				employeeDetailsVo.setHolidayDates(new ArrayList<String>(Arrays.asList(attandanceMap.get("totalholidaydates").split(","))));
				
				//employeeDetailsVo.setStaffSalaryDetails(new StaffEmployementDaoImpl().getStaffEmployementEntry(empId));
				
				salaryDetailsmap.put(empId, employeeDetailsVo);
				
				
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: getEmployeeDetails: Ending");
		return salaryDetailsmap;
	}

	public synchronized List<StaffDetailsForPayrollVo> getEmployeeList() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: getEmployeeList :Starting");
		logger.setLevel(Level.DEBUG);
		List<StaffDetailsForPayrollVo> EmployeeList = new ArrayList<StaffDetailsForPayrollVo>();
		PreparedStatement ps_employee = null;
		ResultSet rs_employee = null;
		StaffDetailsForPayrollVo employeeVO = null;

		try {
			ps_employee = (PreparedStatement) JDBCConnection
					.getStatement(StaffPayrollUtil.GET_ACTIVE_EMP);

			rs_employee = ps_employee.executeQuery();
			while (rs_employee.next()) {
				employeeVO = new StaffDetailsForPayrollVo();
				employeeVO.setEmpId(rs_employee.getString("TeacherID"));
				employeeVO.setEmpName(rs_employee.getString("FirstName"));
				employeeVO.setRegId(rs_employee.getString("registerId"));

				EmployeeList.add(employeeVO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: getEmployeeList: Ending");
		return EmployeeList;

	}

	
	public synchronized Map<String, String> getEmployeeTotalAttendance(String empId, int month, int year) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: getEmployeeTotalAttendance :Starting");
		logger.setLevel(Level.DEBUG);
		Map<String, String> empAttMap = new HashMap<String, String>();
		PreparedStatement ps_totalPresentAbsent = null;
		ResultSet rs_totalPresentAbsent = null;

		try {
			
			ps_totalPresentAbsent = (PreparedStatement) JDBCConnection.getStatement(StaffPayrollUtil.GET_STAFF_ATTENDANCE);
			ps_totalPresentAbsent.setString(1, empId);
			ps_totalPresentAbsent.setString(2, year + "-" + month + "-01");
			ps_totalPresentAbsent.setString(3, year + "-" + month + "-"+ HelperClass.getDaysByMonthAndYear(month, year));
			rs_totalPresentAbsent = ps_totalPresentAbsent.executeQuery();
			
			boolean flag = false;
			
			while (rs_totalPresentAbsent.next()) {
				flag = true;
				
				//for present
				
				String totalpresent = rs_totalPresentAbsent.getString("totalPresent");
				System.out.println("totalpresent "+totalpresent);
				if (totalpresent == null) {
					
					totalpresent = "0";
				}
				
				empAttMap.put("totalPresent", totalpresent);

				String totalpresentdates = rs_totalPresentAbsent.getString("prsentdate");

				if (totalpresentdates == null) {

					totalpresentdates = "";
				}
				
				empAttMap.put("totalpresentdates",totalpresentdates);
				
				//for absent
				
				String totalabsent = rs_totalPresentAbsent.getString("totalabsent");
				
				if (totalabsent == null) {
					
					totalabsent = "0";
				}
				
				empAttMap.put("totalabsent", totalabsent);

				String totalabsentdates = rs_totalPresentAbsent.getString("absentdate");

				if (totalabsentdates == null) {

					totalabsentdates = "";
				}
				
				empAttMap.put("totalabsentdates",totalabsentdates);
				
				//for leave
				
				String totalleave = rs_totalPresentAbsent.getString("totalleave");
				
				if (totalleave == null) {
					
					totalleave = "0";
				}
				
				empAttMap.put("totalleave", totalleave);

				String totalleavedates = rs_totalPresentAbsent.getString("leavedate");

				if (totalleavedates == null) {

					totalleavedates = "";
				}
				
				empAttMap.put("totalleavedates",totalleavedates);
				
				//for Holiday
				
				String totalholiday = rs_totalPresentAbsent.getString("totalholiday");
				
				if (totalholiday == null) {
					
					totalholiday = "0";
				}
				
				empAttMap.put("totalholiday", totalholiday);

				String totalholidaydates = rs_totalPresentAbsent.getString("holidaydate");

				if (totalholidaydates == null) {

					totalholidaydates = "";
				}
				
				empAttMap.put("totalholidaydates",totalholidaydates);
				
				//for Weeklyoff
				
				String totalweeklyoff = rs_totalPresentAbsent.getString("totalweeklyoff");
				
				if (totalweeklyoff == null) {
					
					totalweeklyoff = "0";
				}
				
				empAttMap.put("totalweeklyoff", totalweeklyoff);

				String totalweeklydates = rs_totalPresentAbsent.getString("weeklyoffdate");
				System.out.println("totalweeklydates is "+totalweeklydates);
				if (totalweeklydates == null) {

					totalweeklydates = "";
				}
				
				empAttMap.put("totalweeklyoffdates",totalweeklydates);
			}
			
			if (!flag) {
				
				empAttMap.put("totalPresent", "0");
				empAttMap.put("totalpresentdates", "");
				
				empAttMap.put("totalabsent", "0");
				empAttMap.put("totalabsentdates", "");
				
				empAttMap.put("totalleave", "0");
				empAttMap.put("totalleavedates", "");
				
				empAttMap.put("totalholiday", "0");
				empAttMap.put("totalholidaydates","");
				
				empAttMap.put("totalweeklyoff", "0");
				empAttMap.put("totalweeklydates","");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: getEmployeeTotalAttendance: Ending");
		return empAttMap;
	}
	
	
	
	public synchronized String savePayrollDetails(ArrayList<PayRollVo> payrollList) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: savePayrollDetails :Starting");
		logger.setLevel(Level.DEBUG);
		
		PreparedStatement ps_payroll = null;
		
		PreparedStatement ps_payroll_count = null;
		ResultSet rs_payroll_count = null;
		
		
		String status=null;
		int count=0;
		

		try {
			
			ps_payroll_count = (PreparedStatement) JDBCConnection.getStatement(StaffPayrollUtil.CHECK_PAYROLL_EXIST);
			
			
			for(int i=0;i<payrollList.size();i++){
				
				int ps_count=0;
				
				ps_payroll_count.setInt(1, payrollList.get(i).getMonth());
				ps_payroll_count.setInt(2, payrollList.get(i).getYear());
				ps_payroll_count.setString(3, payrollList.get(i).getEmpId());
				
				rs_payroll_count=ps_payroll_count.executeQuery();
				
				while(rs_payroll_count.next()){
					
					ps_count=rs_payroll_count.getInt(1);
				}
				
				if(ps_count==0){
				
					ps_payroll = (PreparedStatement) JDBCConnection.getStatement(StaffPayrollUtil.SAVE_PAYROLL_DETAILS);
						ps_payroll.setString(1, payrollList.get(i).getEmpId());
						ps_payroll.setInt(2, payrollList.get(i).getMonthDays());
						ps_payroll.setInt(3, payrollList.get(i).getPayabledays());
						ps_payroll.setDouble(4, payrollList.get(i).getBasic());  
						ps_payroll.setDouble(5, payrollList.get(i).getDa());
						ps_payroll.setDouble(6, payrollList.get(i).getHra());
						ps_payroll.setDouble(7, payrollList.get(i).getCa());
						ps_payroll.setDouble(8, payrollList.get(i).getConvieance());
						ps_payroll.setDouble(9, payrollList.get(i).getMedicalallowances());
						ps_payroll.setDouble(10, payrollList.get(i).getOthers());
						ps_payroll.setDouble(11, payrollList.get(i).getGrosssalary());
						ps_payroll.setDouble(12, payrollList.get(i).getEmployee_pf());
						ps_payroll.setDouble(13, payrollList.get(i).getEmployer_pf());
						ps_payroll.setDouble(14, payrollList.get(i).getPt());
						ps_payroll.setDouble(15, payrollList.get(i).getIncomeTax());
						ps_payroll.setString(16, payrollList.get(i).getAdvanceTaken());
						ps_payroll.setDouble(17, payrollList.get(i).getLeavededuction());
						ps_payroll.setDouble(18, payrollList.get(i).getOtherdeduction());
						ps_payroll.setDouble(19, payrollList.get(i).getTotaldeductions());
						ps_payroll.setDouble(20, payrollList.get(i).getNetsalary());
						ps_payroll.setDouble(21, payrollList.get(i).getCtc());
						ps_payroll.setDouble(22, payrollList.get(i).getMonth());
						ps_payroll.setInt(23, payrollList.get(i).getYear());
						ps_payroll.setString(24, payrollList.get(i).getUserId());
						ps_payroll.setTimestamp(25, HelperClass.getCurrentTimestamp());
						
						System.out.println("insert payroll ::: "+ps_payroll);
						
						count=ps_payroll.executeUpdate();
				
				}else{
					
					ps_payroll = (PreparedStatement) JDBCConnection.getStatement(StaffPayrollUtil.UPDATE_PAYROLL_DETAILS);
					
					ps_payroll.setInt(1, payrollList.get(i).getMonthDays());
					ps_payroll.setInt(2, payrollList.get(i).getPayabledays());
					ps_payroll.setDouble(3, payrollList.get(i).getBasic());  
					ps_payroll.setDouble(4, payrollList.get(i).getDa());
					ps_payroll.setDouble(5, payrollList.get(i).getHra());
					ps_payroll.setDouble(6, payrollList.get(i).getCa());
					ps_payroll.setDouble(7, payrollList.get(i).getConvieance());
					ps_payroll.setDouble(8, payrollList.get(i).getMedicalallowances());
					ps_payroll.setDouble(9, payrollList.get(i).getOthers());
					ps_payroll.setDouble(10, payrollList.get(i).getGrosssalary());
					ps_payroll.setDouble(11, payrollList.get(i).getEmployee_pf());
					ps_payroll.setDouble(12, payrollList.get(i).getEmployer_pf());
					ps_payroll.setDouble(13, payrollList.get(i).getPt());
					ps_payroll.setDouble(14, payrollList.get(i).getIncomeTax());
					ps_payroll.setString(15, payrollList.get(i).getAdvanceTaken());
					ps_payroll.setDouble(16, payrollList.get(i).getLeavededuction());
					ps_payroll.setDouble(17, payrollList.get(i).getOtherdeduction());
					ps_payroll.setDouble(18, payrollList.get(i).getTotaldeductions());
					ps_payroll.setDouble(19, payrollList.get(i).getNetsalary());
					ps_payroll.setDouble(20, payrollList.get(i).getCtc());
					ps_payroll.setString(21, payrollList.get(i).getUserId());
					ps_payroll.setTimestamp(22, HelperClass.getCurrentTimestamp());
					
					ps_payroll.setDouble(23, payrollList.get(i).getMonth());
					ps_payroll.setInt(24, payrollList.get(i).getYear());
					ps_payroll.setString(25, payrollList.get(i).getEmpId());
					
					System.out.println("update payroll ::: "+ps_payroll);
					
					count=ps_payroll.executeUpdate();
					
					
				}
			}
			
			if(count>0){
				
				status="true";
			}else{
				
				status="false";
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: getEmployeeTotalAttendance: Ending");
		
		return status;
	}
	
	
	public synchronized ArrayList<PayRollVo> getPayrollDetails(int month,int year) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: savePayrollDetails :Starting");
		logger.setLevel(Level.DEBUG);
		
		PreparedStatement ps_payroll = null;
		
		ResultSet rs_payroll = null;
		ArrayList<PayRollVo> payrollList =new ArrayList<PayRollVo>();
		int count=0;
		
		try {
			
					ps_payroll = (PreparedStatement) JDBCConnection.getStatement(StaffPayrollUtil.GET_PAYROLL_DETAILS);
					
					ps_payroll.setInt(1, month);
					ps_payroll.setInt(2, year);
					
					System.out.println("Get Payroll ::: "+ps_payroll);
					
					rs_payroll=ps_payroll.executeQuery();
					
					while(rs_payroll.next()){
						
						PayRollVo vo=new PayRollVo();
						count++;
						vo.setEmpId(rs_payroll.getString("Emp_Id"));
						vo.setRegId(rs_payroll.getString("registerId"));
						vo.setEmpName(rs_payroll.getString("teachername"));
						if(rs_payroll.getString("BankAccNumber")==null || "".equalsIgnoreCase(rs_payroll.getString("BankAccNumber"))){
						vo.setAccountnumber("-");
						}else{
							vo.setAccountnumber(rs_payroll.getString("BankAccNumber"));
						}
						vo.setMonthDays(rs_payroll.getInt("No_of_Actualdays"));
						vo.setPayabledays(rs_payroll.getInt("PayableDays"));
						vo.setBasic(rs_payroll.getDouble("Basic"));
						vo.setDa(rs_payroll.getDouble("DA"));
						vo.setHra(rs_payroll.getDouble("HRA"));
						vo.setCa(rs_payroll.getDouble("CA"));
						vo.setMedicalallowances(rs_payroll.getDouble("Medical_Allowances"));
						vo.setConvieance(rs_payroll.getDouble("Conviance"));
						vo.setOthers(rs_payroll.getDouble("Others"));
						vo.setGrosssalary(rs_payroll.getDouble("Gross"));
						vo.setEmployee_pf(rs_payroll.getDouble("Employee_Pf"));
						vo.setEmployer_pf(rs_payroll.getDouble("Employer_Pf"));
						vo.setPt(rs_payroll.getDouble("P_Tax"));
						vo.setIncomeTax(rs_payroll.getDouble("IncomeTax"));
						vo.setLeavededuction(rs_payroll.getDouble("Leave_Deductions"));
						vo.setOtherdeduction(rs_payroll.getDouble("Other_Deductions"));
						if(rs_payroll.getString("Salary_Advance")==null || "".equalsIgnoreCase(rs_payroll.getString("Salary_Advance"))){
						
							vo.setAdvanceTaken("-");
						 
						}else{
							
							vo.setAdvanceTaken(rs_payroll.getString("Salary_Advance"));
						}
						vo.setTotaldeductions(rs_payroll.getDouble("Total_Deductions"));
						vo.setNetsalary(rs_payroll.getDouble("Net_Salary"));
						vo.setSno(count);
						
						payrollList.add(vo);
					}
					
					
				
				
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: getEmployeeTotalAttendance: Ending");
		
		JSONArray array=new JSONArray();
		array.put(payrollList);
		
		System.out.println("array :: "+array);
		
		return payrollList;
	}



	@Override
	public ArrayList<PayRollVo> getEmpMonthPayrollDetails(String month,String  year, String empId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: savePayrollDetails :Starting");
		logger.setLevel(Level.DEBUG);
		
		PreparedStatement ps_payroll = null;
		
		ResultSet rs_payroll = null;
		ArrayList<PayRollVo> payrollList =new ArrayList<PayRollVo>();
		int count=0;
		
		try {
			
					ps_payroll = (PreparedStatement) JDBCConnection.getStatement(StaffPayrollUtil.GET_PAYSLIP_DETAILS);
					
					ps_payroll.setInt(1, Integer.parseInt(month));
					ps_payroll.setInt(2, Integer.parseInt(year));
					ps_payroll.setString(3, empId);
					
					System.out.println("Get Payroll ::: "+ps_payroll);
					
					rs_payroll=ps_payroll.executeQuery();
					
					while(rs_payroll.next()){
						
						PayRollVo vo=new PayRollVo();
						count++;
						
						if(rs_payroll.getString("BankAccNumber")==null || "".equalsIgnoreCase(rs_payroll.getString("BankAccNumber"))){
								
							vo.setPfnumber("-");
							
						}else{
								vo.setPfnumber(rs_payroll.getString("BankAccNumber"));
						}
						
						if(rs_payroll.getString("EmployeePfNo")==null || "".equalsIgnoreCase(rs_payroll.getString("EmployeePfNo"))){
							
							vo.setEsinumber("-");
							
						}else{
								vo.setEsinumber(rs_payroll.getString("EmployeePfNo"));
						}
						
						if(rs_payroll.getString("ESI_No")==null || "".equalsIgnoreCase(rs_payroll.getString("ESI_No"))){
							
							vo.setPannumber("-");
							
						}else{
								vo.setPannumber(rs_payroll.getString("ESI_No"));
						}
						
					
						vo.setEmpId(rs_payroll.getString("Emp_Id"));
						vo.setRegId(rs_payroll.getString("registerId"));
						vo.setEmpName(rs_payroll.getString("teachername"));
						if(rs_payroll.getString("BankAccNumber")==null || "".equalsIgnoreCase(rs_payroll.getString("BankAccNumber"))){
						vo.setAccountnumber("-");
						}else{
							vo.setAccountnumber(rs_payroll.getString("BankAccNumber"));
						}
						vo.setMonthDays(rs_payroll.getInt("No_of_Actualdays"));
						vo.setPayabledays(rs_payroll.getInt("PayableDays"));
						vo.setBasic(rs_payroll.getDouble("Basic"));
						vo.setDa(rs_payroll.getDouble("DA"));
						vo.setHra(rs_payroll.getDouble("HRA"));
						vo.setCa(rs_payroll.getDouble("CA"));
						vo.setMedicalallowances(rs_payroll.getDouble("Medical_Allowances"));
						vo.setConvieance(rs_payroll.getDouble("Conviance"));
						vo.setOthers(rs_payroll.getDouble("Others"));
						vo.setGrosssalary(rs_payroll.getDouble("Gross"));
						vo.setEmployee_pf(rs_payroll.getDouble("Employee_Pf"));
						vo.setEmployer_pf(rs_payroll.getDouble("Employer_Pf"));
						vo.setPt(rs_payroll.getDouble("P_Tax"));
						vo.setIncomeTax(rs_payroll.getDouble("IncomeTax"));
						vo.setLeavededuction(rs_payroll.getDouble("Leave_Deductions"));
						vo.setOtherdeduction(rs_payroll.getDouble("Other_Deductions"));
						if(rs_payroll.getString("Salary_Advance")==null || "".equalsIgnoreCase(rs_payroll.getString("Salary_Advance"))){
						
							vo.setAdvanceTaken("-");
						 
						}else{
							
							vo.setAdvanceTaken(rs_payroll.getString("Salary_Advance"));
						}
						vo.setTotaldeductions(rs_payroll.getDouble("Total_Deductions"));
						vo.setNetsalary(rs_payroll.getDouble("Net_Salary"));
						vo.setSno(count);
						
						payrollList.add(vo);
					}
					
					
				
				
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: getEmployeeTotalAttendance: Ending");
		
		JSONArray array=new JSONArray();
		array.put(payrollList);
		
		System.out.println("array :: "+array);
		
		return payrollList;
	}

	@Override
	public ArrayList<PayRollVo> getPayRorllMonthList(String accyearid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: getPayRorllMonthList :Starting");
		logger.setLevel(Level.DEBUG);
		
		Connection conn=null;
		PreparedStatement ps_payroll = null;
		String year=null;
		ResultSet rs_payroll = null;
		
		ArrayList<PayRollVo> payrollList =new ArrayList<PayRollVo>();
		try {

			conn=JDBCConnection.getSeparateConnection();
			
			ps_payroll = conn.prepareStatement(StaffPayrollUtil.GET_ACADMICYR_DATE);
			ps_payroll.setString(1, accyearid);
			System.out.println("GET ACADEMIC YEAR="+ps_payroll);
			rs_payroll=ps_payroll.executeQuery();
			int count=0;
			while(rs_payroll.next()){
				HashMap<String,String> monthyaerset=new HashMap<String,String>();
				String startDate[]=rs_payroll.getString("startDate").split("-");
				String endDate[]=rs_payroll.getString("endDate").split("-");
				String accyear=rs_payroll.getString("acadamic_year");
				String startmonth=startDate[1];
				String endmonth=endDate[1];
				String startYear=startDate[0];
				String endYear=endDate[0];
				int j=Integer.parseInt(startmonth);
				int k=j;
				year=startYear;
				for(int i=Integer.parseInt(startmonth);i<rs_payroll.getInt("monthCount")+Integer.parseInt(startmonth)+1;i++){
					count++;
					PayRollVo vo=new PayRollVo();
					if(j>12){
						k=j-12;
						year=endYear;
					}
					String	monthName=HelperClass.getMonthName(Integer.toString(k));
					int monthVal=k;
					String moonthva=monthVal+"";
					j++;
					k++;
					String selectMonth=monthName+"-"+year;
					vo.setMonthcount(monthVal);
					vo.setMonthName(selectMonth);
					vo.setYear(Integer.parseInt(year));
					monthyaerset.put(moonthva, year);
					vo.setMonthYearSet(monthyaerset);
					payrollList.add(vo);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				rs_payroll.close();
				ps_payroll.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: getPayRorllMonthList: Ending");
		
		return payrollList;
	}

	@Override
	public ArrayList<PayRollVo> getPayRollList(String accyearid, String locationId, String monthId,String yearname) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollDaoImpl: getPayrollList : Starting");

		PreparedStatement ps_payroll = null;
		Connection conn=null;
		ArrayList<PayRollVo> payrollList =new ArrayList<PayRollVo>();
		int count=0,payabledayscount=0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			int month_int=Integer.parseInt(monthId);
			int year_int=Integer.parseInt(yearname);
			int totalDaysInMonth=HelperClass.getDaysByMonthAndYear(month_int, year_int);

			Map<String,StaffDetailsForPayrollVo> teacherlist=getEmployeeDetailsForPayroll(month_int, year_int,conn,locationId);

			Iterator itr = teacherlist.entrySet().iterator();

			while(itr.hasNext())
			{

				count++;

				PayRollVo generatePayrollVo=new PayRollVo();

				Map.Entry<String, StaffDetailsForPayrollVo> empdetails=(Map.Entry<String, StaffDetailsForPayrollVo>)itr.next();

				StaffDetailsForPayrollVo staffDetailsForPayrollVO=teacherlist.get(empdetails.getKey());

				List<String> presentdates =staffDetailsForPayrollVO.getPresentdates();

				List<String> absentdates =staffDetailsForPayrollVO.getAbsentdates();

				List<String> leavedates =staffDetailsForPayrollVO.getLeavedates();

				List<String> holidaydates =staffDetailsForPayrollVO.getHolidaydates();

				List<String> weekoffdates =staffDetailsForPayrollVO.getWeekoffdates();


				HashSet<String> payyabledays=new HashSet<String>();

				if(!(presentdates.size()==0)){
					for(int i=0;i<presentdates.size();i++){

						payyabledays.add(presentdates.get(i));
					}
				}

				/*if(!(weekoffdates.size()==0)){
					for(int i=0;i<weekoffdates.size();i++){

						payyabledays.add(weekoffdates.get(i));
					}
				}

				if(!(holidaydates.size()==0)){
					for(int i=0;i<holidaydates.size();i++){

						payyabledays.add(holidaydates.get(i));
					}
				}
*/


				payabledayscount=payyabledays.size();

				SalaryDetailsVo salary_vo=new SalaryDetailsVo();
				salary_vo.setMonth(month_int);
				salary_vo.setYear(year_int);

				salary_vo=getSalaryDetails(empdetails.getKey(),conn,locationId);
				/*System.out.println(salary_vo.getLeavedeductions());
		        	if(!("Y".equalsIgnoreCase(salary_vo.getLeavedeductions())))
		        	{   

		        		if(!(leavedates.size()==0)){
		        			for(int i=0;i<leavedates.size();i++){

		        				payyabledays.add(leavedates.get(i));
		        			}
		        		}

		        		payabledayscount=payyabledays.size();
		        	}*/

				generatePayrollVo.setSno(count);

				generatePayrollVo.setEmpId(staffDetailsForPayrollVO.getEmpId());
				generatePayrollVo.setRegId(staffDetailsForPayrollVO.getRegId());
				generatePayrollVo.setEmpName(staffDetailsForPayrollVO.getEmpName());
				generatePayrollVo.setDeptName(staffDetailsForPayrollVO.getDepartmentname());
				generatePayrollVo.setDesignationname(staffDetailsForPayrollVO.getDesignation());
				generatePayrollVo.setDoj(staffDetailsForPayrollVO.getDoj());
				generatePayrollVo.setMonth(month_int);
				generatePayrollVo.setYear(year_int);
				generatePayrollVo.setLocationId(locationId);
				generatePayrollVo.setAccyearId(accyearid);

				String pfNo=salary_vo.getPfnumber();
				String esicode=salary_vo.getEsinumber();

				if(esicode==null){
					esicode="";
				}
				if(pfNo==null){
					pfNo="";
				}

				generatePayrollVo.setEsinumber(esicode);
				generatePayrollVo.setPfnumber(pfNo); 
				/* Actual details */
				generatePayrollVo.setMonthDays(totalDaysInMonth);
				generatePayrollVo.setBasic(salary_vo.getBasic());
				generatePayrollVo.setDa(salary_vo.getDa());
				generatePayrollVo.setHra(salary_vo.getHra());
				generatePayrollVo.setConvieance(salary_vo.getConvieance());
				generatePayrollVo.setPerformanceIncentive(salary_vo.getPerformanceIncentive());
				generatePayrollVo.setSpecialAllowance(salary_vo.getSpecialAllowance());
				generatePayrollVo.setFoodAllowance(salary_vo.getFoodAllowance());
				generatePayrollVo.setChildEdu(salary_vo.getChildEdu());
				generatePayrollVo.setArrears(salary_vo.getArrears());
				generatePayrollVo.setReimbursement(salary_vo.getReimbursement());
				generatePayrollVo.setInternetExpense(salary_vo.getInternetExpense());
				generatePayrollVo.setDriverSalary(salary_vo.getDriverSalary());
				generatePayrollVo.setLeaveEncash(salary_vo.getLeaveEncash());
				generatePayrollVo.setMedicalReimbursement(salary_vo.getMedicalReimbursement());
				generatePayrollVo.setOthers(salary_vo.getOthers());
				generatePayrollVo.setTotalsalary(salary_vo.getTotalsalary());
				
				/* Earned details */
				generatePayrollVo.setPayabledays(payabledayscount);
				double earned_grossSalary=PayrollCalculations.calEarnedGross(salary_vo, payabledayscount, totalDaysInMonth);
				double earnedBasic=PayrollCalculations.calculateBasic(salary_vo,payabledayscount,totalDaysInMonth);
				double earnedDa=PayrollCalculations.calculateDa(salary_vo,payabledayscount,totalDaysInMonth);
				
				generatePayrollVo.setBasic_earned(earnedBasic);
				generatePayrollVo.setDa_earned(earnedDa);
				generatePayrollVo.setConvinience_earned(PayrollCalculations.calEarnedConveyance(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setHra_earned(PayrollCalculations.calEarnedHra(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setPerformanceIncentive_earned(PayrollCalculations.calEarnedPerformenceIncentive(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setSpecialAllowance_earned(PayrollCalculations.calEarnedSpecial(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setFoodAllowance_earned(PayrollCalculations.calEarnedFood(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setChildEdu_earned(PayrollCalculations.calEarnedChildEdu(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setArrears_earned(PayrollCalculations.calEarnedArrear(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setReimbursement_earned(PayrollCalculations.calEarnedReimburse(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setInternetExpense_earned(PayrollCalculations.calEarnedInternet(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setDriverSalary_earned(PayrollCalculations.calEarnedDriver(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setLeaveEncash_earned(PayrollCalculations.calEarnedLeave(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setMedicalReimbursement_earned(PayrollCalculations.calEarnedMedical(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setOthers_earned(PayrollCalculations.calEarnedOthers(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setTotalsalary_earned(earned_grossSalary);
				
				double earnedBasicDa=Math.round(earnedBasic+earnedDa);
				
				generatePayrollVo.setPfempr_earned(PayrollCalculations.calculatePfEmpr(salary_vo, earnedBasicDa));
	        	generatePayrollVo.setPfempy_earned(PayrollCalculations.calculatePfEmpy(salary_vo, earnedBasicDa));
	        	generatePayrollVo.setEsiempr_earned(PayrollCalculations.calculateEsiEmpr(salary_vo, earned_grossSalary));
	        	generatePayrollVo.setEsiempy_earned(PayrollCalculations.calculateEsiEmpy(salary_vo, earned_grossSalary));
	        	generatePayrollVo.setPf(generatePayrollVo.getPfempr_earned()+generatePayrollVo.getPfempy_earned());
	        	generatePayrollVo.setEsi(generatePayrollVo.getEsiempr_earned()+generatePayrollVo.getEsiempy_earned());
	        	generatePayrollVo.setPtax_earned(PayrollCalculations.calculateProfTax(salary_vo,earnedBasicDa));
	        	generatePayrollVo.setIncometax_earned(PayrollCalculations.calculateIncomeTax(salary_vo,earnedBasicDa));
				
	        	
	        	double totDeductions=generatePayrollVo.getPfempr_earned()+generatePayrollVo.getEsiempr_earned()+generatePayrollVo.getPfempy_earned()+generatePayrollVo.getEsiempy_earned()
        								+generatePayrollVo.getPtax_earned()+generatePayrollVo.getIncometax_earned();
	        	
	        	generatePayrollVo.setTotaldeductions(totDeductions);
	        	
	        	generatePayrollVo.setTakehome(earned_grossSalary-totDeductions);
	        	generatePayrollVo.setNetpay(earned_grossSalary-totDeductions);
				payrollList.add(generatePayrollVo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if (ps_payroll != null&& (!ps_payroll.isClosed())) {
					ps_payroll.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollDaoImpl: getPayrollList: Ending");

		return payrollList;
	}

	@Override
	public String GenerateMultiplePayroll(PayRollVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollDaoImpl: getPayrollList : Starting");
		PreparedStatement ps_payroll = null;
		Connection conn=null;
		String status=null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			conn.setAutoCommit(false);
			int count=0,uploadStatus=0;
			
			for (int i = 0; i < vo.getTeacher().length; i++) {
				ps_payroll = conn.prepareStatement(StaffPayrollUtil.INSERT_GENERATE_PAYROLL);
				//Emp_Id,No_of_Actualdays,PayableDays,Basic,DA,HRA,Conviance,performace_incentive,food_allowance,special_allowance,child_edu,arrears,reimbursement,internet,drivery_sal,leave,medical,gross_salary,Employee_Pf,Employer_Pf,Employee_esi,Employer_esi,P_Tax,IncomeTax,Salary_Advance,Total_Deductions,Net_Salary,salary_pending,CTC,Month,Year,CreatedBy,CreatedDate,locationId,accyearId
				ps_payroll.setString(1,vo.getTeacher()[i]);
				ps_payroll.setString(2, vo.getMonthdays()[i]);
				ps_payroll.setString(3,vo.getPayableday()[i]);
				ps_payroll.setString(4, vo.getEarnbasic()[i]);
				ps_payroll.setString(5, vo.getEarnda()[i]);
				ps_payroll.setString(6, vo.getEarnhra()[i]);
				ps_payroll.setString(7, vo.getEarnconvience()[i]);
				ps_payroll.setString(8, vo.getEarnperform()[i]);
				ps_payroll.setString(9, vo.getEarnfood()[i]);
				ps_payroll.setString(10, vo.getEarnspecial()[i]);
				ps_payroll.setString(11, vo.getEarnchild()[i]);
				ps_payroll.setString(12, vo.getEarnarrears()[i]);
				ps_payroll.setString(13, vo.getEarnreimburse()[i]);
				ps_payroll.setString(14, vo.getEarninternet()[i]);
				ps_payroll.setString(15, vo.getEarndriver()[i]);
				ps_payroll.setString(16, vo.getEarnleave()[i]);
				ps_payroll.setString(17, vo.getEarnmedical()[i]);
				ps_payroll.setString(18, vo.getEarntotal()[i]);
				ps_payroll.setString(19, vo.getEarnpfempy()[i]);
				ps_payroll.setString(20, vo.getEarnpfempr()[i]);
				ps_payroll.setString(21, vo.getEarnesiempy()[i]);
				ps_payroll.setString(22, vo.getEarnesiempr()[i]);
				ps_payroll.setString(23, vo.getMonthlyptax()[i]);
				ps_payroll.setString(24, vo.getMonthlyincometax()[i]);
				ps_payroll.setString(25, vo.getMonthlyadvance()[i]);
				ps_payroll.setString(26, vo.getMonthlytotaldeduction()[i]);
				ps_payroll.setString(27, vo.getMonthlynetpay()[i]);
				ps_payroll.setString(28, vo.getMonthlysalpending()[i]);
				ps_payroll.setString(29, vo.getMonthlytakehome()[i]);
				ps_payroll.setString(30, vo.getMonthName());
				ps_payroll.setString(31, vo.getYearval());
				ps_payroll.setString(32,vo.getEmpId());
				ps_payroll.setTimestamp(33, HelperClass.getCurrentTimestamp());
				ps_payroll.setString(34, vo.getLocationId());
				ps_payroll.setString(35, vo.getAccyearId());
				ps_payroll.setString(36, "Generated");
				
				count=ps_payroll.executeUpdate();
			}
			
			if(count>0){
				conn.commit();
				status = "success";
			} else {
				status = "fail";
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {

				if (ps_payroll != null&& (!ps_payroll.isClosed())) {
					ps_payroll.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollDaoImpl: getPayrollList: Ending");

		return status;
	}



	@Override
	public ArrayList<PayRollVo> getAllPayrollList(PayRollVo payrollvo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in GenaratePayrollDaoImpl : getpayrollList :Starting");

		PreparedStatement ps_payroll=null,ps_payroll1=null,ps_payroll2=null;
		ResultSet rs_empdet=null,rs_empdet1=null,rs_empdet2=null;
		ArrayList<PayRollVo> emp_payroll_list=new ArrayList<PayRollVo>();
		ArrayList<PayRollVo> list=new ArrayList<PayRollVo>();
		Connection connection=null;
		int count=0;
		String accyearid=null,accyearname=null;
		try {
			connection=JDBCConnection.getSeparateConnection();
			PayRollVo vo =null;
			if(payrollvo.getMonthval().equalsIgnoreCase("all")){
				for(int i=1; i<=12; i++){
					if(payrollvo.getLocationId() == "all" || payrollvo.getLocationId().equalsIgnoreCase("all")){
						ps_payroll = connection.prepareStatement(StaffPayrollUtil.GET_COUNT_PAYROLL_DETAILS);
						ps_payroll.setInt(1, i);
						ps_payroll.setString(2, payrollvo.getYearcode().get(i-1).getMonthYearSet().get(Integer.toString(i)));
						ps_payroll.setString(3, payrollvo.getCurryear());
						rs_empdet= ps_payroll.executeQuery();
						while (rs_empdet.next()) {
							count = rs_empdet.getInt(1);
							accyearid=rs_empdet.getString(2);
							accyearname=rs_empdet.getString(3);
						}
						vo=new PayRollVo();
						if(count>0){
							ps_payroll1 = connection.prepareStatement(StaffPayrollUtil.GET_PAYROLL_SEARCH_DETAILS1);
							ps_payroll1.setInt(1, i);
							ps_payroll1.setString(2,payrollvo.getYearcode().get(i-1).getMonthYearSet().get(Integer.toString(i)));
							rs_empdet1= ps_payroll1.executeQuery();
							while(rs_empdet1.next()){
								String Mnth=rs_empdet1.getString("Month");
								vo.setYearval(accyearname);
								vo.setYearvalcode(accyearid);
								vo.setCreatedby(rs_empdet1.getString("username"));
								if(rs_empdet1.getString("Location_Id") != null || rs_empdet1.getString("Location_Id") !="" ){
									if(payrollvo.getLocationId() == "all" || payrollvo.getLocationId().equalsIgnoreCase("all")){
										vo.setLocationId("all");
										vo.setLocationName("All");
									}
								}
								String s1 =rs_empdet1.getString("CreatedDate");
								String s2[]=s1.split(" ");
								vo.setCreateddate(s2[0]);
								vo.setCreatedtime(s2[1]);
								int mincount=rs_empdet1.getInt("mincount");
								int maxcount=rs_empdet1.getInt("maxcount");
								
								if(Mnth.equalsIgnoreCase("1"))
								{
									vo.setMonthvalcode("1");
									String monthname=HelperClass.getMonthFullName("01");
									vo.setMonthval(monthname);
									String year=getYearName("01",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("2"))
								{
									vo.setMonthvalcode("2");
									String monthname=HelperClass.getMonthFullName("02");
									vo.setMonthval(monthname);
									String year=getYearName("02",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("3"))
								{
									vo.setMonthvalcode("3");
									String monthname=HelperClass.getMonthFullName("03");
									vo.setMonthval(monthname);
									String year=getYearName("03",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("4"))
								{
									vo.setMonthvalcode("4");
									String monthname=HelperClass.getMonthFullName("04");
									vo.setMonthval(monthname);
									String year=getYearName("04",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("5"))
								{
									vo.setMonthvalcode("5");
									String monthname=HelperClass.getMonthFullName("05");
									vo.setMonthval(monthname);
									String year=getYearName("05",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("6"))
								{
									vo.setMonthvalcode("6");
									String monthname=HelperClass.getMonthFullName("06");
									vo.setMonthval(monthname);
									String year=getYearName("06",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("7"))
								{
									vo.setMonthvalcode("7");
									String monthname=HelperClass.getMonthFullName("07");
									vo.setMonthval(monthname);
									String year=getYearName("07",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("8"))
								{
									vo.setMonthvalcode("8");
									String monthname=HelperClass.getMonthFullName("08");
									vo.setMonthval(monthname);
									String year=getYearName("08",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("9"))
								{
									vo.setMonthvalcode("9");
									String monthname=HelperClass.getMonthFullName("09");
									vo.setMonthval(monthname);
									String year=getYearName("09",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("10"))
								{
									vo.setMonthvalcode("10");
									String monthname=HelperClass.getMonthFullName("10");
									vo.setMonthval(monthname);
									String year=getYearName("10",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("11"))
								{
									vo.setMonthvalcode("11");
									String monthname=HelperClass.getMonthFullName("11");
									vo.setMonthval(monthname);
									String year=getYearName("11",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("12"))
								{
									vo.setMonthvalcode("12");
									String monthname=HelperClass.getMonthFullName("12");
									vo.setMonthval(monthname);
									String year=getYearName("12",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}
							}
						}else{
							vo.setYearval(accyearname);
							vo.setYearvalcode(accyearid);
							vo.setLocationId("all");
							vo.setLocationName("All");
							vo.setCreatedby("-");
							vo.setCreateddate("-");
							vo.setCreatedtime("-");
							
							String s1=String.valueOf((i));
							if(s1.equalsIgnoreCase("1"))
							{
								vo.setMonthvalcode("1");
								String monthname=HelperClass.getMonthFullName("01");
								vo.setMonthval(monthname);
								String year=getYearName("01",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("2"))
							{
								vo.setMonthvalcode("2");
								String monthname=HelperClass.getMonthFullName("02");
								vo.setMonthval(monthname);
								String year=getYearName("02",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}


							if(s1.equalsIgnoreCase("3"))
							{
								vo.setMonthvalcode("3");
								String monthname=HelperClass.getMonthFullName("03");
								vo.setMonthval(monthname);
								String year=getYearName("03",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("4"))
							{
								vo.setMonthvalcode("4");
								String monthname=HelperClass.getMonthFullName("04");
								vo.setMonthval(monthname);
								String year=getYearName("04",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("5"))
							{
								vo.setMonthvalcode("5");
								String monthname=HelperClass.getMonthFullName("05");
								vo.setMonthval(monthname);
								String year=getYearName("05",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("6"))
							{
								vo.setMonthvalcode("6");
								String monthname=HelperClass.getMonthFullName("06");
								vo.setMonthval(monthname);
								String year=getYearName("06",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("7"))
							{
								vo.setMonthvalcode("7");
								String monthname=HelperClass.getMonthFullName("07");
								vo.setMonthval(monthname);
								String year=getYearName("07",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("8"))
							{
								vo.setMonthvalcode("8");
								String monthname=HelperClass.getMonthFullName("08");
								vo.setMonthval(monthname);
								String year=getYearName("08",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("9"))
							{
								vo.setMonthvalcode("9");
								String monthname=HelperClass.getMonthFullName("09");
								vo.setMonthval(monthname);
								String year=getYearName("09",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("10"))
							{
								vo.setMonthvalcode("10");
								String monthname=HelperClass.getMonthFullName("10");
								vo.setMonthval(monthname);
								String year=getYearName("10",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("11"))
							{
								vo.setMonthvalcode("11");
								String monthname=HelperClass.getMonthFullName("11");
								vo.setMonthval(monthname);
								String year=getYearName("11",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("12"))
							{
								vo.setMonthvalcode("12");
								String monthname=HelperClass.getMonthFullName("12");
								vo.setMonthval(monthname);
								String year=getYearName("12",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}
						}
						emp_payroll_list.add(vo);
					}else{
						ps_payroll = connection.prepareStatement(StaffPayrollUtil.GET_COUNT_PAYROLL_DETAILS1);
						ps_payroll.setInt(1, i);
						ps_payroll.setString(2, payrollvo.getYearcode().get(i-1).getMonthYearSet().get(Integer.toString(i)));
						ps_payroll.setString(3, payrollvo.getCurryear());
						ps_payroll.setString(4, payrollvo.getLocationId());
						rs_empdet= ps_payroll.executeQuery();

						while (rs_empdet.next()) {
							count = rs_empdet.getInt(1);
							accyearid=rs_empdet.getString(2);
							accyearname=rs_empdet.getString(3);
						}
						vo=new PayRollVo();
						if(count>0){
							ps_payroll1 = connection.prepareStatement(StaffPayrollUtil.GET_PAYROLL_SEARCH_DETAILS);
							ps_payroll1.setInt(1, i);
							ps_payroll1.setString(2, payrollvo.getYearcode().get(i-1).getMonthYearSet().get(Integer.toString(i)));
							ps_payroll1.setString(3, payrollvo.getLocationId());
							ps_payroll1.setString(4, payrollvo.getLocationId());
							rs_empdet1= ps_payroll1.executeQuery();
							while(rs_empdet1.next()){
								String Mnth=rs_empdet1.getString("Month");
								vo.setYearval(accyearname);
								vo.setYearvalcode(accyearid);
								vo.setCreatedby(rs_empdet1.getString("username"));
								if(rs_empdet1.getString("Location_Id") != null || rs_empdet1.getString("Location_Id") !="" ){
									if(payrollvo.getLocationId() == "all" || payrollvo.getLocationId().equalsIgnoreCase("all")){
										vo.setLocationId("all");
										vo.setLocationName("All");
									}else{
										vo.setLocationId(rs_empdet1.getString("Location_Id"));
										vo.setLocationName(rs_empdet1.getString("Location_Name"));
									}
								}
								String s1 =rs_empdet1.getString("CreatedDate");
								String s2[]=s1.split(" ");
								vo.setCreateddate(s2[0]);
								vo.setCreatedtime(s2[1]);
								int mincount=rs_empdet1.getInt("mincount");
								int maxcount=rs_empdet1.getInt("maxcount");
								
								if(Mnth.equalsIgnoreCase("1"))
								{
									vo.setMonthvalcode("1");
									String monthname=HelperClass.getMonthFullName("01");
									vo.setMonthval(monthname);
									String year=getYearName("01",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("2"))
								{
									vo.setMonthvalcode("2");
									String monthname=HelperClass.getMonthFullName("02");
									vo.setMonthval(monthname);
									String year=getYearName("02",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("3"))
								{
									vo.setMonthvalcode("3");
									String monthname=HelperClass.getMonthFullName("03");
									vo.setMonthval(monthname);
									String year=getYearName("03",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("4"))
								{
									vo.setMonthvalcode("4");
									String monthname=HelperClass.getMonthFullName("04");
									vo.setMonthval(monthname);
									String year=getYearName("04",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("5"))
								{
									vo.setMonthvalcode("5");
									String monthname=HelperClass.getMonthFullName("05");
									vo.setMonthval(monthname);
									String year=getYearName("05",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("6"))
								{
									vo.setMonthvalcode("6");
									String monthname=HelperClass.getMonthFullName("06");
									vo.setMonthval(monthname);
									String year=getYearName("06",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("7"))
								{
									vo.setMonthvalcode("7");
									String monthname=HelperClass.getMonthFullName("07");
									vo.setMonthval(monthname);
									String year=getYearName("07",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("8"))
								{
									vo.setMonthvalcode("8");
									String monthname=HelperClass.getMonthFullName("08");
									vo.setMonthval(monthname);
									String year=getYearName("08",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("9"))
								{
									vo.setMonthvalcode("9");
									String monthname=HelperClass.getMonthFullName("09");
									vo.setMonthval(monthname);
									String year=getYearName("09",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("10"))
								{
									vo.setMonthvalcode("10");
									String monthname=HelperClass.getMonthFullName("10");
									vo.setMonthval(monthname);
									String year=getYearName("10",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("11"))
								{
									vo.setMonthvalcode("11");
									String monthname=HelperClass.getMonthFullName("11");
									vo.setMonthval(monthname);
									String year=getYearName("11",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}

								if(Mnth.equalsIgnoreCase("12"))
								{
									vo.setMonthvalcode("12");
									String monthname=HelperClass.getMonthFullName("12");
									vo.setMonthval(monthname);
									String year=getYearName("12",accyearid);
									vo.setYearName(year);
									if(mincount == maxcount){
										vo.setStatus("Generated");
									}else{
										vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
									}
								}
							}
						}else{
							vo.setYearval(accyearname);
							vo.setYearvalcode(accyearid);
							vo.setCreatedby("-");
							vo.setCreateddate("-");
							vo.setCreatedtime("-");
							
							ps_payroll1 = connection.prepareStatement(StaffPayrollUtil.GET_LOCTION_LIST);
							ps_payroll1.setString(1, payrollvo.getLocationId());
							rs_empdet1=ps_payroll1.executeQuery();
							while(rs_empdet1.next()){
								vo.setLocationId(rs_empdet1.getString("Location_Id"));
								vo.setLocationName(rs_empdet1.getString("Location_Name"));
							}

							String s1=String.valueOf((i));
							if(s1.equalsIgnoreCase("1"))
							{
								vo.setMonthvalcode("1");
								String monthname=HelperClass.getMonthFullName("01");
								vo.setMonthval(monthname);
								String year=getYearName("01",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}


							if(s1.equalsIgnoreCase("2"))
							{
								vo.setMonthvalcode("2");
								String monthname=HelperClass.getMonthFullName("02");
								vo.setMonthval(monthname);
								String year=getYearName("02",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}


							if(s1.equalsIgnoreCase("3"))
							{
								vo.setMonthvalcode("3");
								String monthname=HelperClass.getMonthFullName("03");
								vo.setMonthval(monthname);
								String year=getYearName("03",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("4"))
							{
								vo.setMonthvalcode("4");
								String monthname=HelperClass.getMonthFullName("04");
								vo.setMonthval(monthname);
								String year=getYearName("04",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("5"))
							{
								vo.setMonthvalcode("5");
								String monthname=HelperClass.getMonthFullName("05");
								vo.setMonthval(monthname);
								String year=getYearName("05",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("6"))
							{
								vo.setMonthvalcode("6");
								String monthname=HelperClass.getMonthFullName("06");
								vo.setMonthval(monthname);
								String year=getYearName("06",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("7"))
							{
								vo.setMonthvalcode("7");
								String monthname=HelperClass.getMonthFullName("07");
								vo.setMonthval(monthname);
								String year=getYearName("07",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("8"))
							{
								vo.setMonthvalcode("8");
								String monthname=HelperClass.getMonthFullName("08");
								vo.setMonthval(monthname);
								String year=getYearName("08",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("9"))
							{
								vo.setMonthvalcode("9");
								String monthname=HelperClass.getMonthFullName("09");
								vo.setMonthval(monthname);
								String year=getYearName("09",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("10"))
							{
								vo.setMonthvalcode("10");
								String monthname=HelperClass.getMonthFullName("10");
								vo.setMonthval(monthname);
								String year=getYearName("10",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("11"))
							{
								vo.setMonthvalcode("11");
								String monthname=HelperClass.getMonthFullName("11");
								vo.setMonthval(monthname);
								String year=getYearName("11",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}

							if(s1.equalsIgnoreCase("12"))
							{
								vo.setMonthvalcode("12");
								String monthname=HelperClass.getMonthFullName("12");
								vo.setMonthval(monthname);
								String year=getYearName("12",accyearid);
								vo.setYearName(year);
								vo.setStatus("Not Generated");

							}
						}
						emp_payroll_list.add(vo);
					}
				}
			}else{
				if(payrollvo.getLocationId() == "all" || payrollvo.getLocationId().equalsIgnoreCase("all")){
					ps_payroll = connection.prepareStatement(StaffPayrollUtil.GET_COUNT_PAYROLL_DETAILS);
					ps_payroll.setInt(1, Integer.parseInt(payrollvo.getMonthval()));
					ps_payroll.setString(2, payrollvo.getYearcode().get(Integer.parseInt(payrollvo.getMonthval())-1).getMonthYearSet().get(payrollvo.getMonthval()));
					ps_payroll.setString(3, payrollvo.getCurryear());
					rs_empdet= ps_payroll.executeQuery();
					while (rs_empdet.next()) {
						count = rs_empdet.getInt(1);
						accyearid=rs_empdet.getString(2);
						accyearname=rs_empdet.getString(3);
					}
					vo=new PayRollVo();
					if(count>0){
						ps_payroll1 = connection.prepareStatement(StaffPayrollUtil.GET_PAYROLL_SEARCH_DETAILS1);
						ps_payroll1.setInt(1, Integer.parseInt(payrollvo.getMonthval()));
						ps_payroll1.setString(2, payrollvo.getYearcode().get(Integer.parseInt(payrollvo.getMonthval())-1).getMonthYearSet().get(payrollvo.getMonthval()));
						rs_empdet1= ps_payroll1.executeQuery();
						while(rs_empdet1.next()){
							String Mnth=rs_empdet1.getString("Month");
							vo.setYearval(accyearname);
							vo.setYearvalcode(accyearid);
							vo.setCreatedby(rs_empdet1.getString("username"));
							if(rs_empdet1.getString("Location_Id") != null || rs_empdet1.getString("Location_Id") !="" ){
								if(payrollvo.getLocationId() == "all" || payrollvo.getLocationId().equalsIgnoreCase("all")){
									vo.setLocationId("all");
									vo.setLocationName("All");
								}
							}
							String s1 =rs_empdet1.getString("CreatedDate");
							String s2[]=s1.split(" ");
							vo.setCreateddate(s2[0]);
							vo.setCreatedtime(s2[1]);
							int mincount=rs_empdet1.getInt("mincount");
							int maxcount=rs_empdet1.getInt("maxcount");
							
							if(Mnth.equalsIgnoreCase("1"))
							{
								vo.setMonthvalcode("1");
								String monthname=HelperClass.getMonthFullName("01");
								vo.setMonthval(monthname);
								String year=getYearName("01",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("2"))
							{
								vo.setMonthvalcode("2");
								String monthname=HelperClass.getMonthFullName("02");
								vo.setMonthval(monthname);
								String year=getYearName("02",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("3"))
							{
								vo.setMonthvalcode("3");
								String monthname=HelperClass.getMonthFullName("03");
								vo.setMonthval(monthname);
								String year=getYearName("03",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("4"))
							{
								vo.setMonthvalcode("4");
								String monthname=HelperClass.getMonthFullName("04");
								vo.setMonthval(monthname);
								String year=getYearName("04",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("5"))
							{
								vo.setMonthvalcode("5");
								String monthname=HelperClass.getMonthFullName("05");
								vo.setMonthval(monthname);
								String year=getYearName("05",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("6"))
							{
								vo.setMonthvalcode("6");
								String monthname=HelperClass.getMonthFullName("06");
								vo.setMonthval(monthname);
								String year=getYearName("06",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("7"))
							{
								vo.setMonthvalcode("7");
								String monthname=HelperClass.getMonthFullName("07");
								vo.setMonthval(monthname);
								String year=getYearName("07",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("8"))
							{
								vo.setMonthvalcode("8");
								String monthname=HelperClass.getMonthFullName("08");
								vo.setMonthval(monthname);
								String year=getYearName("08",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("9"))
							{
								vo.setMonthvalcode("9");
								String monthname=HelperClass.getMonthFullName("09");
								vo.setMonthval(monthname);
								String year=getYearName("09",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("10"))
							{
								vo.setMonthvalcode("10");
								String monthname=HelperClass.getMonthFullName("10");
								vo.setMonthval(monthname);
								String year=getYearName("10",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("11"))
							{
								vo.setMonthvalcode("11");
								String monthname=HelperClass.getMonthFullName("11");
								vo.setMonthval(monthname);
								String year=getYearName("11",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("12"))
							{
								vo.setMonthvalcode("12");
								String monthname=HelperClass.getMonthFullName("12");
								vo.setMonthval(monthname);
								String year=getYearName("12",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}
						}
					}else{
						vo.setYearval(accyearname);
						vo.setYearvalcode(accyearid);
						vo.setLocationId("all");
						vo.setLocationName("All");
						vo.setCreatedby("-");
						vo.setCreateddate("-");
						vo.setCreatedtime("-");
						
						String s1=String.valueOf((Integer.parseInt(payrollvo.getMonthval())));
						if(s1.equalsIgnoreCase("1"))
						{
							vo.setMonthvalcode("1");
							String monthname=HelperClass.getMonthFullName("01");
							vo.setMonthval(monthname);
							String year=getYearName("01",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");

						}

						if(s1.equalsIgnoreCase("2"))
						{
							vo.setMonthvalcode("2");
							String monthname=HelperClass.getMonthFullName("02");
							vo.setMonthval(monthname);
							String year=getYearName("02",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");

						}


						if(s1.equalsIgnoreCase("3"))
						{
							vo.setMonthvalcode("3");
							String monthname=HelperClass.getMonthFullName("03");
							vo.setMonthval(monthname);
							String year=getYearName("03",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");

						}

						if(s1.equalsIgnoreCase("4"))
						{
							vo.setMonthvalcode("4");
							String monthname=HelperClass.getMonthFullName("04");
							vo.setMonthval(monthname);
							String year=getYearName("04",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");

						}

						if(s1.equalsIgnoreCase("5"))
						{
							vo.setMonthvalcode("5");
							String monthname=HelperClass.getMonthFullName("05");
							vo.setMonthval(monthname);
							String year=getYearName("05",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");

						}

						if(s1.equalsIgnoreCase("6"))
						{
							vo.setMonthvalcode("6");
							String monthname=HelperClass.getMonthFullName("06");
							vo.setMonthval(monthname);
							String year=getYearName("06",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");

						}

						if(s1.equalsIgnoreCase("7"))
						{
							vo.setMonthvalcode("7");
							String monthname=HelperClass.getMonthFullName("07");
							vo.setMonthval(monthname);
							String year=getYearName("07",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");

						}

						if(s1.equalsIgnoreCase("8"))
						{
							vo.setMonthvalcode("8");
							String monthname=HelperClass.getMonthFullName("08");
							vo.setMonthval(monthname);
							String year=getYearName("08",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");

						}

						if(s1.equalsIgnoreCase("9"))
						{
							vo.setMonthvalcode("9");
							String monthname=HelperClass.getMonthFullName("09");
							vo.setMonthval(monthname);
							String year=getYearName("09",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");

						}

						if(s1.equalsIgnoreCase("10"))
						{
							vo.setMonthvalcode("10");
							String monthname=HelperClass.getMonthFullName("10");
							vo.setMonthval(monthname);
							String year=getYearName("10",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");

						}

						if(s1.equalsIgnoreCase("11"))
						{
							vo.setMonthvalcode("11");
							String monthname=HelperClass.getMonthFullName("11");
							vo.setMonthval(monthname);
							String year=getYearName("11",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");

						}

						if(s1.equalsIgnoreCase("12"))
						{
							vo.setMonthvalcode("12");
							String monthname=HelperClass.getMonthFullName("12");
							vo.setMonthval(monthname);
							String year=getYearName("12",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");

						}
					}
					emp_payroll_list.add(vo);
				}else{
					ps_payroll = connection.prepareStatement(StaffPayrollUtil.GET_COUNT_PAYROLL_DETAILS1);
					ps_payroll.setInt(1, Integer.parseInt(payrollvo.getMonthval()));
					ps_payroll.setString(2, payrollvo.getMonthYear());
					ps_payroll.setString(3, payrollvo.getCurryear());
					ps_payroll.setString(4, payrollvo.getLocationId());
					rs_empdet= ps_payroll.executeQuery();

					while (rs_empdet.next()) {
						count = rs_empdet.getInt(1);
						accyearid=rs_empdet.getString(2);
						accyearname=rs_empdet.getString(3);
					}
					vo=new PayRollVo();
					if(count>0){
						ps_payroll1 = connection.prepareStatement(StaffPayrollUtil.GET_PAYROLL_SEARCH_DETAILS);
						ps_payroll1.setInt(1, Integer.parseInt(payrollvo.getMonthval()));
						ps_payroll1.setString(2, payrollvo.getMonthYear());
						ps_payroll1.setString(3, payrollvo.getLocationId());
						ps_payroll1.setString(4, payrollvo.getLocationId());
						rs_empdet1= ps_payroll1.executeQuery();
						while(rs_empdet1.next()){
							String Mnth=rs_empdet1.getString("Month");
							vo.setYearval(accyearname);
							vo.setYearvalcode(accyearid);
							vo.setCreatedby(rs_empdet1.getString("username"));
							if(rs_empdet1.getString("Location_Id") != null || rs_empdet1.getString("Location_Id") !="" ){
								if(payrollvo.getLocationId() == "all" || payrollvo.getLocationId().equalsIgnoreCase("all")){
									vo.setLocationId("all");
									vo.setLocationName("All");
								}else{
									vo.setLocationId(rs_empdet1.getString("Location_Id"));
									vo.setLocationName(rs_empdet1.getString("Location_Name"));
								}
							}
							
							String s1 =rs_empdet1.getString("CreatedDate");
							String s2[]=s1.split(" ");
							vo.setCreateddate(s2[0]);
							vo.setCreatedtime(s2[1]);
							int mincount=rs_empdet1.getInt("mincount");
							int maxcount=rs_empdet1.getInt("maxcount");
							
							if(Mnth.equalsIgnoreCase("1"))
							{
								vo.setMonthvalcode("1");
								String monthname=HelperClass.getMonthFullName("01");
								vo.setMonthval(monthname);
								String year=getYearName("01",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("2"))
							{
								vo.setMonthvalcode("2");
								String monthname=HelperClass.getMonthFullName("02");
								vo.setMonthval(monthname);
								String year=getYearName("02",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("3"))
							{
								vo.setMonthvalcode("3");
								String monthname=HelperClass.getMonthFullName("03");
								vo.setMonthval(monthname);
								String year=getYearName("03",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("4"))
							{
								vo.setMonthvalcode("4");
								String monthname=HelperClass.getMonthFullName("04");
								vo.setMonthval(monthname);
								String year=getYearName("04",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("5"))
							{
								vo.setMonthvalcode("5");
								String monthname=HelperClass.getMonthFullName("05");
								vo.setMonthval(monthname);
								String year=getYearName("05",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("6"))
							{
								vo.setMonthvalcode("6");
								String monthname=HelperClass.getMonthFullName("06");
								vo.setMonthval(monthname);
								String year=getYearName("06",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("7"))
							{
								vo.setMonthvalcode("7");
								String monthname=HelperClass.getMonthFullName("07");
								vo.setMonthval(monthname);
								String year=getYearName("07",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("8"))
							{
								vo.setMonthvalcode("8");
								String monthname=HelperClass.getMonthFullName("08");
								vo.setMonthval(monthname);
								String year=getYearName("08",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("9"))
							{
								vo.setMonthvalcode("9");
								String monthname=HelperClass.getMonthFullName("09");
								vo.setMonthval(monthname);
								String year=getYearName("09",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("10"))
							{
								vo.setMonthvalcode("10");
								String monthname=HelperClass.getMonthFullName("10");
								vo.setMonthval(monthname);
								String year=getYearName("10",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("11"))
							{
								vo.setMonthvalcode("11");
								String monthname=HelperClass.getMonthFullName("02");
								vo.setMonthval(monthname);
								String year=getYearName("11",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}

							if(Mnth.equalsIgnoreCase("12"))
							{
								vo.setMonthvalcode("12");
								String monthname=HelperClass.getMonthFullName("12");
								vo.setMonthval(monthname);
								String year=getYearName("12",accyearid);
								vo.setYearName(year);
								if(mincount == maxcount){
									vo.setStatus("Generated");
								}else{
									vo.setStatus(maxcount+" Out Off "+mincount+" Generated");
								}
							}
						}
					}else{

						vo.setYearval(accyearname);
						vo.setYearvalcode(accyearid);
						vo.setCreatedby("-");
						vo.setCreateddate("-");
						vo.setCreatedtime("-");
						
						ps_payroll1 = connection.prepareStatement(StaffPayrollUtil.GET_LOCTION_LIST);
						ps_payroll1.setString(1, payrollvo.getLocationId());
						rs_empdet1=ps_payroll1.executeQuery();
						while(rs_empdet1.next()){
							vo.setLocationId(rs_empdet1.getString("Location_Id"));
							vo.setLocationName(rs_empdet1.getString("Location_Name"));
						}

						String s1=String.valueOf(Integer.parseInt(payrollvo.getMonthval()));
						if(s1.equalsIgnoreCase("1"))
						{
							vo.setMonthvalcode("1");
							String monthname=HelperClass.getMonthFullName("01");
							vo.setMonthval(monthname);
							String year=getYearName("01",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");

						}

						if(s1.equalsIgnoreCase("2"))
						{
							vo.setMonthvalcode("2");
							String monthname=HelperClass.getMonthFullName("02");
							vo.setMonthval(monthname);
							String year=getYearName("02",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");
						}

						if(s1.equalsIgnoreCase("3"))
						{
							vo.setMonthvalcode("3");
							String monthname=HelperClass.getMonthFullName("03");
							vo.setMonthval(monthname);
							String year=getYearName("03",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");
						}

						if(s1.equalsIgnoreCase("4"))
						{
							vo.setMonthvalcode("4");
							String monthname=HelperClass.getMonthFullName("04");
							vo.setMonthval(monthname);
							String year=getYearName("04",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");
						}

						if(s1.equalsIgnoreCase("5"))
						{
							vo.setMonthvalcode("5");
							String monthname=HelperClass.getMonthFullName("05");
							vo.setMonthval(monthname);
							String year=getYearName("05",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");
						}

						if(s1.equalsIgnoreCase("6"))
						{
							vo.setMonthvalcode("6");
							String monthname=HelperClass.getMonthFullName("06");
							vo.setMonthval(monthname);
							String year=getYearName("06",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");
						}

						if(s1.equalsIgnoreCase("7"))
						{
							vo.setMonthvalcode("7");
							String monthname=HelperClass.getMonthFullName("07");
							vo.setMonthval(monthname);
							String year=getYearName("07",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");
						}

						if(s1.equalsIgnoreCase("8"))
						{
							vo.setMonthvalcode("8");
							String monthname=HelperClass.getMonthFullName("08");
							vo.setMonthval(monthname);
							String year=getYearName("08",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");
						}

						if(s1.equalsIgnoreCase("9"))
						{
							vo.setMonthvalcode("9");
							String monthname=HelperClass.getMonthFullName("09");
							vo.setMonthval(monthname);
							String year=getYearName("09",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");
						}

						if(s1.equalsIgnoreCase("10"))
						{
							vo.setMonthvalcode("10");
							String monthname=HelperClass.getMonthFullName("10");
							vo.setMonthval(monthname);
							String year=getYearName("10",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");
						}

						if(s1.equalsIgnoreCase("11"))
						{
							vo.setMonthvalcode("11");
							String monthname=HelperClass.getMonthFullName("11");
							vo.setMonthval(monthname);
							String year=getYearName("11",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");
						}

						if(s1.equalsIgnoreCase("12"))
						{
							vo.setMonthvalcode("12");
							String monthname=HelperClass.getMonthFullName("12");
							vo.setMonthval(monthname);
							String year=getYearName("12",accyearid);
							vo.setYearName(year);
							vo.setStatus("Not Generated");
						}
					}
					emp_payroll_list.add(vo);
				}
			}
		}catch (SQLException sqle) {

			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);

		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		finally{

			try {

				if(rs_empdet != null && (!rs_empdet.isClosed())){

					rs_empdet.close();
				}
				if(ps_payroll != null && (!ps_payroll.isClosed())){

					ps_payroll.close();
				}if(connection != null && (!connection.isClosed())){

					connection.close();
				}


			} catch (SQLException e) {

				logger.error(e.getMessage(),e);
				e.printStackTrace();
			}catch (Exception e1) {
				logger.error(e1.getMessage(),e1);
				logger.error(e1);
				e1.printStackTrace();
			}
		}


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in GenaratePayrollDaoImpl: getpayrollList : Ending");

		return emp_payroll_list;
}

	@Override
	public ArrayList<PayRollVo> getGeneratePayrollList(LeaveRequestForm payrollform, String empId) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String getLoctionName(String locationId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollDaoImpl: getPayrollList : Starting");

		PreparedStatement ps_payroll_list = null;
		ResultSet rs_payroll=null;
		Connection conn = null;
		String locationname=null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			ps_payroll_list = conn.prepareStatement(StaffPayrollUtil.GET_LOCTION_NAME);
			ps_payroll_list.setString(1, locationId);
			rs_payroll=ps_payroll_list.executeQuery();
			while(rs_payroll.next()){
				locationname=rs_payroll.getString("Location_Name");
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (ps_payroll_list != null&& (!ps_payroll_list.isClosed())) {
					ps_payroll_list.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollDaoImpl: getPayrollList: Ending");

		return locationname;
	}



	@Override
	public String getAccYearName(String yearvalcode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollDaoImpl: getPayrollList : Starting");

		PreparedStatement ps_payroll_list = null;
		ResultSet rs_payroll=null;
		Connection conn = null;
		String accyear=null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			ps_payroll_list = conn.prepareStatement(StaffPayrollUtil.GET_ACCYEAR_NAME);
			ps_payroll_list.setString(1, yearvalcode);
			rs_payroll=ps_payroll_list.executeQuery();
			while(rs_payroll.next()){
				accyear=rs_payroll.getString("acadamic_year");
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (ps_payroll_list != null&& (!ps_payroll_list.isClosed())) {
					ps_payroll_list.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollDaoImpl: getPayrollList: Ending");

		return accyear;
	}



	@Override
	public String getMonthName(String yearvalcode, String monthvalcode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: getPayRorllMonthList :Starting");
		logger.setLevel(Level.DEBUG);
		
		PreparedStatement ps_payroll = null;
		ResultSet rs_payroll = null;
		String monthname=null;
		try {

			ps_payroll = (PreparedStatement) JDBCConnection.getStatement(StaffPayrollUtil.GET_ACADMICYR_DATE);
			ps_payroll.setString(1, yearvalcode);

			rs_payroll=ps_payroll.executeQuery();
			while(rs_payroll.next()){
				
				String startDate[]=rs_payroll.getString("startDate").split("-");
				String endDate[]=rs_payroll.getString("endDate").split("-");
				String startYear=startDate[0];
				String endYear=endDate[0];

				int startMonth=Integer.parseInt(startDate[1]);
				int j=Integer.parseInt(monthvalcode);
				String	monthName=HelperClass.getMonthName(monthvalcode);
				if(j<startMonth){
					String endyear=endYear;
					monthname=monthName+"-"+endyear;
				}
				else{
					String startyear=startYear;
					monthname=monthName+"-"+startyear;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				rs_payroll.close();
				ps_payroll.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: getPayRorllMonthList: Ending");
		
		return monthname;
	}

	public String getYearName(String monthId, String accyearid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: getPayRorllMonthList :Starting");
		logger.setLevel(Level.DEBUG);
		
		Connection conn=null;
		PreparedStatement ps_payroll = null;
		String yearName=null;
		ResultSet rs_payroll = null;
		
		try {

			conn=JDBCConnection.getSeparateConnection();
			
			ps_payroll = conn.prepareStatement(StaffPayrollUtil.GET_ACADMICYR_DATE);
			ps_payroll.setString(1, accyearid);
			rs_payroll=ps_payroll.executeQuery();
			while(rs_payroll.next()){

				String startDate[]=rs_payroll.getString("startDate").split("-");
				String endDate[]=rs_payroll.getString("endDate").split("-");
				String startYear=startDate[0];
				String endYear=endDate[0];
				int startMonth=Integer.parseInt(startDate[1]);
				int j=Integer.parseInt(monthId);

				if(j<startMonth){
					yearName=endYear;
				}
				else{
					yearName=startYear;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				rs_payroll.close();
				ps_payroll.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SalaryDetailsDaoImpl: getPayRorllMonthList: Ending");
		
		return yearName;
	}

	private Map<String, StaffDetailsForPayrollVo> getEmployeeDetailsForPayroll(int month, int year, Connection connn, String locationId) {
		Map<String,StaffDetailsForPayrollVo> StaffDetailsForPayrollMap = new HashMap<String, StaffDetailsForPayrollVo>();
		StaffDetailsForPayrollVo staffpayroll=null;
		
		Connection conn=connn;
	
		try{
			
			List<StaffSearchVo> empList= getStaffList(conn,locationId,month,year);
			
			for(int i=0;i<empList.size();i++){
				
				staffpayroll = new StaffDetailsForPayrollVo();
				
				String staffId=empList.get(i).getStaffid();
				
				staffpayroll.setEmpId(staffId);
				staffpayroll.setRegId(empList.get(i).getRegId());
				staffpayroll.setEmpName(empList.get(i).getStaffname());
				staffpayroll.setDepartmentname(empList.get(i).getDeptname());
				staffpayroll.setDesignation(empList.get(i).getDesignation());
				staffpayroll.setDoj(empList.get(i).getDoj());
				staffpayroll.setTotalDaysInMonth(HelperClass.getDaysByMonthAndYear(month, year)+"");
				
				staffpayroll.setTotalPresent(getEmployeeTotalAttendance( staffId, month, year).get("totalPresent"));
				staffpayroll.setTotalAbsent(getEmployeeTotalAttendance( staffId, month, year).get("totalAbsent"));
				staffpayroll.setTotalLeave(getEmployeeTotalAttendance( staffId, month, year).get("totalleave"));
				staffpayroll.setTotalHoliday(getEmployeeTotalAttendance( staffId, month, year).get("holidaydate"));
				staffpayroll.setTotalWeeklyOff(getEmployeeTotalAttendance( staffId, month, year).get("totalweeklyoff"));
				
				String totalabsentdates=  getEmployeeTotalAttendance( staffId, month, year).get("totalabsentdates");
				String totalpresentdates=  getEmployeeTotalAttendance( staffId, month, year).get("totalpresentdates");
				String totalleavedates=  getEmployeeTotalAttendance( staffId, month, year).get("totalleavedates");
				String totalholidaydates=  getEmployeeTotalAttendance( staffId, month, year).get("totalholidaydates");
				String totalweekoffdates=  getEmployeeTotalAttendance( staffId, month, year).get("totalweeklyoffdates");
				
				ArrayList<String> absentdates_list=new ArrayList<String>();
				ArrayList<String> presentdates_list=new ArrayList<String>();
				ArrayList<String> leavedates_list=new ArrayList<String>();
				ArrayList<String> holidaydates_list=new ArrayList<String>();
				ArrayList<String> weekoffdates_list=new ArrayList<String>();
				
				
				if(!(totalabsentdates.equalsIgnoreCase(""))){
					String[] tot_absentdate= totalabsentdates.split(",");
					for(int j=0;j<tot_absentdate.length;j++){

						absentdates_list.add(tot_absentdate[j]);

					}
					staffpayroll.setAbsentdates(absentdates_list);
				}else{
					
					staffpayroll.setAbsentdates(absentdates_list);
				}
				
				if(!(totalpresentdates.equalsIgnoreCase(""))){
					String[] tot_presentdate= totalpresentdates.split(",");
					for(int j=0;j<tot_presentdate.length;j++){

						presentdates_list.add(tot_presentdate[j]);

					}
					staffpayroll.setPresentdates(presentdates_list);
				}else{

					staffpayroll.setPresentdates(presentdates_list);
				}
				
				if(!(totalleavedates.equalsIgnoreCase(""))){
					String[] tot_leavedate= totalleavedates.split(",");
					for(int j=0;j<tot_leavedate.length;j++){

						leavedates_list.add(tot_leavedate[j]);

					}
					staffpayroll.setLeavedates(leavedates_list);
				}else{
					staffpayroll.setLeavedates(leavedates_list);
				}
				
				if(!(totalholidaydates.equalsIgnoreCase(""))){
					String[] tot_holidaydate= totalholidaydates.split(",");
					for(int j=0;j<tot_holidaydate.length;j++){

						holidaydates_list.add(tot_holidaydate[j]);

					}
					staffpayroll.setHolidaydates(holidaydates_list);
				}else{
					staffpayroll.setHolidaydates(holidaydates_list);
				}
				
				/*if(!(totalweekoffdates.equalsIgnoreCase(""))){
					String[] tot_weekoffdate= totalweekoffdates.split(",");
					for(int j=0;j<tot_weekoffdate.length;j++){

						weekoffdates_list.add(tot_weekoffdate[j]);

					}
					staffpayroll.setHolidaydates(weekoffdates_list);
				}else{
					staffpayroll.setHolidaydates(weekoffdates_list);
				}*/
				
				StaffDetailsForPayrollMap.put(staffId, staffpayroll);
			}
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return StaffDetailsForPayrollMap;
	}

	private List<StaffSearchVo> getPayrollGeneratedStaffList(Connection connn, String locationId, int month, int year) {
		List<StaffSearchVo> staffList = new ArrayList<StaffSearchVo>();
		PreparedStatement ps_employee=null;
		ResultSet rs_employee=null;
		StaffSearchVo staffvo=null;
		Connection conn=connn;
		try{
			String monthname=String.valueOf(month);
			String yearname=String.valueOf(year);
			if(!locationId.equalsIgnoreCase("all")){
				ps_employee = conn.prepareStatement(StaffPayrollUtil.GET_GENERATED_STAFFLIST);
				ps_employee.setString(1, locationId);
				ps_employee.setString(2, monthname);
				ps_employee.setString(3, yearname);
			}else{
				ps_employee = conn.prepareStatement(StaffPayrollUtil.GET_GENERATED_STAFFLIST_ALL);
				ps_employee.setString(1, monthname);
				ps_employee.setString(2, yearname);
			}
			
			//System.out.println("get employees ::: "+ps_employee.toString());

			rs_employee = ps_employee.executeQuery();
			while(rs_employee.next()){

				staffvo= new StaffSearchVo();
				staffvo.setStaffid(rs_employee.getString("TeacherID"));
				staffvo.setRegId(rs_employee.getString("registerId"));
				staffvo.setStaffname(rs_employee.getString("staffname"));
				staffvo.setDeptname(rs_employee.getString("DEPT_NAME"));
				staffvo.setDesignation(rs_employee.getString("designationName"));
				staffvo.setDoj(rs_employee.getString("dateofJoining"));
				staffList.add(staffvo);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{

		}
		return staffList;
	}

	public SalaryDetailsVo getSalaryDetails(String empid,Connection connection, String locationId){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in GeneratePayrollDaoImpl: getSalaryDetails : Starting");

		PreparedStatement salarystatement=null;
		ResultSet rs_salary=null;
		SalaryDetailsVo salaryDetailsVo=new SalaryDetailsVo();
		Connection conn=connection;

		try {

			salarystatement = conn.prepareStatement(StaffPayrollUtil.SALARY_LIST);
			salarystatement.setString(1, empid);
			salarystatement.setString(2, locationId);

			rs_salary=salarystatement.executeQuery();

			while(rs_salary.next()){
				salaryDetailsVo.setTeacherId(rs_salary.getString("TeacherID"));
				salaryDetailsVo.setPfnumber(rs_salary.getString("EmployeePfNo"));
				salaryDetailsVo.setBankaccno(rs_salary.getString("BankAccNumber"));
				salaryDetailsVo.setEsinumber(rs_salary.getString("ESI_No"));
				salaryDetailsVo.setBasic(rs_salary.getDouble("Basic"));
				salaryDetailsVo.setHra(rs_salary.getDouble("Hra"));
				salaryDetailsVo.setConvieance(rs_salary.getDouble("Convenience"));
				salaryDetailsVo.setDa(rs_salary.getDouble("Da"));
				salaryDetailsVo.setPerformanceIncentive(rs_salary.getDouble("PerformanceIncentive"));
				salaryDetailsVo.setSpecialAllowance(rs_salary.getDouble("SpecialAllowance"));
				salaryDetailsVo.setFoodAllowance(rs_salary.getDouble("FoodAllowance"));
				salaryDetailsVo.setChildEdu(rs_salary.getDouble("childEdu"));
				salaryDetailsVo.setArrears(rs_salary.getDouble("arrears"));
				salaryDetailsVo.setReimbursement(rs_salary.getDouble("reimbursement"));
				salaryDetailsVo.setInternetExpense(rs_salary.getDouble("internetExpense"));
				salaryDetailsVo.setDriverSalary(rs_salary.getDouble("driverSalary"));
				salaryDetailsVo.setLeaveEncash(rs_salary.getDouble("leaveEncash"));
				salaryDetailsVo.setMedicalReimbursement(rs_salary.getDouble("medicalReimbursement"));
				salaryDetailsVo.setOthers(rs_salary.getDouble("OtherAllowance"));
				salaryDetailsVo.setTotalsalary(rs_salary.getDouble("totalSalary"));
				salaryDetailsVo.setPfEmployerAmount(rs_salary.getDouble("pfEmployerAmount"));
				salaryDetailsVo.setEsiEmployerAmount(rs_salary.getDouble("esiEmployerAmount"));
	
				salaryDetailsVo.setTaxworkProfTax(rs_salary.getDouble("taxworkProfTax"));
				salaryDetailsVo.setTaxworkPerMonth(rs_salary.getDouble("taxworkPerMonth"));
				salaryDetailsVo.setTaxworkDedTillDate(rs_salary.getDouble("taxworkDedTillDate"));
				salaryDetailsVo.setTaxworkToBeDeducted(rs_salary.getDouble("taxworkToBeDeducted"));
				salaryDetailsVo.setTaxworkNetTax(rs_salary.getDouble("taxworkNetTax"));
			}


		} catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1);
		}finally{
			try{

				if(rs_salary!=null && (!rs_salary.isClosed())){

					rs_salary.close();
				}
				if(salarystatement!=null && (!salarystatement.isClosed())){

					salarystatement.close();
				}


			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle);
			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error(e1);
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in GeneratePayrollDaoImpl: getSalaryDetails : Ending");

		return salaryDetailsVo;


	}

	@Override
	public ArrayList<PayRollVo> getGeneratedPayRollList(String accyearid, String locationId, String monthId,String yearname) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollDaoImpl: getPayrollList : Starting");

		PreparedStatement ps_payroll = null;
		Connection conn=null;
		ArrayList<PayRollVo> payrollList =new ArrayList<PayRollVo>();
		int count=0,payabledayscount=0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			int month_int=Integer.parseInt(monthId);
			int year_int=Integer.parseInt(yearname);
			int totalDaysInMonth=HelperClass.getDaysByMonthAndYear(month_int, year_int);

			Map<String,StaffDetailsForPayrollVo> teacherlist=getGeneratedEmployeeDetailsForPayroll(month_int, year_int,conn,locationId);

			Iterator itr = teacherlist.entrySet().iterator();

			while(itr.hasNext())
			{

				count++;

				PayRollVo generatePayrollVo=new PayRollVo();

				Map.Entry<String, StaffDetailsForPayrollVo> empdetails=(Map.Entry<String, StaffDetailsForPayrollVo>)itr.next();

				StaffDetailsForPayrollVo staffDetailsForPayrollVO=teacherlist.get(empdetails.getKey());

				List<String> presentdates =staffDetailsForPayrollVO.getPresentdates();

				List<String> absentdates =staffDetailsForPayrollVO.getAbsentdates();

				List<String> leavedates =staffDetailsForPayrollVO.getLeavedates();

				List<String> holidaydates =staffDetailsForPayrollVO.getHolidaydates();

				List<String> weekoffdates =staffDetailsForPayrollVO.getWeekoffdates();


				HashSet<String> payyabledays=new HashSet<String>();

				if(!(presentdates.size()==0)){
					for(int i=0;i<presentdates.size();i++){

						payyabledays.add(presentdates.get(i));
					}
				}

				/*if(!(weekoffdates.size()==0)){
					for(int i=0;i<weekoffdates.size();i++){

						payyabledays.add(weekoffdates.get(i));
					}
				}

				if(!(holidaydates.size()==0)){
					for(int i=0;i<holidaydates.size();i++){

						payyabledays.add(holidaydates.get(i));
					}
				}
*/


				payabledayscount=payyabledays.size();

				SalaryDetailsVo salary_vo=new SalaryDetailsVo();
				salary_vo.setMonth(month_int);
				salary_vo.setYear(year_int);

				salary_vo=getSalaryDetails(empdetails.getKey(),conn,locationId);
				/*System.out.println(salary_vo.getLeavedeductions());
		        	if(!("Y".equalsIgnoreCase(salary_vo.getLeavedeductions())))
		        	{   

		        		if(!(leavedates.size()==0)){
		        			for(int i=0;i<leavedates.size();i++){

		        				payyabledays.add(leavedates.get(i));
		        			}
		        		}

		        		payabledayscount=payyabledays.size();
		        	}*/

				generatePayrollVo.setSno(count);

				generatePayrollVo.setEmpId(staffDetailsForPayrollVO.getEmpId());
				generatePayrollVo.setRegId(staffDetailsForPayrollVO.getRegId());
				generatePayrollVo.setEmpName(staffDetailsForPayrollVO.getEmpName());
				generatePayrollVo.setDeptName(staffDetailsForPayrollVO.getDepartmentname());
				generatePayrollVo.setDesignationname(staffDetailsForPayrollVO.getDesignation());
				generatePayrollVo.setDoj(staffDetailsForPayrollVO.getDoj());
				generatePayrollVo.setMonth(month_int);
				generatePayrollVo.setYear(year_int);
				generatePayrollVo.setLocationId(locationId);
				generatePayrollVo.setAccyearId(accyearid);

				String pfNo=salary_vo.getPfnumber();
				String esicode=salary_vo.getEsinumber();

				if(esicode==null){
					esicode="";
				}
				if(pfNo==null){
					pfNo="";
				}

				generatePayrollVo.setEsinumber(esicode);
				generatePayrollVo.setPfnumber(pfNo); 
				generatePayrollVo.setBankaccNo(salary_vo.getBankaccno()); 
				/* Actual details */
				generatePayrollVo.setMonthDays(totalDaysInMonth);
				generatePayrollVo.setBasic(salary_vo.getBasic());
				generatePayrollVo.setDa(salary_vo.getDa());
				generatePayrollVo.setHra(salary_vo.getHra());
				generatePayrollVo.setConvieance(salary_vo.getConvieance());
				generatePayrollVo.setPerformanceIncentive(salary_vo.getPerformanceIncentive());
				generatePayrollVo.setSpecialAllowance(salary_vo.getSpecialAllowance());
				generatePayrollVo.setFoodAllowance(salary_vo.getFoodAllowance());
				generatePayrollVo.setChildEdu(salary_vo.getChildEdu());
				generatePayrollVo.setArrears(salary_vo.getArrears());
				generatePayrollVo.setReimbursement(salary_vo.getReimbursement());
				generatePayrollVo.setInternetExpense(salary_vo.getInternetExpense());
				generatePayrollVo.setDriverSalary(salary_vo.getDriverSalary());
				generatePayrollVo.setLeaveEncash(salary_vo.getLeaveEncash());
				generatePayrollVo.setMedicalReimbursement(salary_vo.getMedicalReimbursement());
				generatePayrollVo.setOthers(salary_vo.getOthers());
				generatePayrollVo.setTotalsalary(salary_vo.getTotalsalary());
				
				/* Earned details */
				generatePayrollVo.setPayabledays(payabledayscount);
				double earned_grossSalary=PayrollCalculations.calEarnedGross(salary_vo, payabledayscount, totalDaysInMonth);
				double earnedBasic=PayrollCalculations.calculateBasic(salary_vo,payabledayscount,totalDaysInMonth);
				double earnedDa=PayrollCalculations.calculateDa(salary_vo,payabledayscount,totalDaysInMonth);
				
				generatePayrollVo.setBasic_earned(earnedBasic);
				generatePayrollVo.setDa_earned(earnedDa);
				generatePayrollVo.setConvinience_earned(PayrollCalculations.calEarnedConveyance(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setHra_earned(PayrollCalculations.calEarnedHra(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setPerformanceIncentive_earned(PayrollCalculations.calEarnedPerformenceIncentive(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setSpecialAllowance_earned(PayrollCalculations.calEarnedSpecial(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setFoodAllowance_earned(PayrollCalculations.calEarnedFood(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setChildEdu_earned(PayrollCalculations.calEarnedChildEdu(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setArrears_earned(PayrollCalculations.calEarnedArrear(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setReimbursement_earned(PayrollCalculations.calEarnedReimburse(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setInternetExpense_earned(PayrollCalculations.calEarnedInternet(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setDriverSalary_earned(PayrollCalculations.calEarnedDriver(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setLeaveEncash_earned(PayrollCalculations.calEarnedLeave(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setMedicalReimbursement_earned(PayrollCalculations.calEarnedMedical(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setOthers_earned(PayrollCalculations.calEarnedOthers(salary_vo, payabledayscount, totalDaysInMonth));
				generatePayrollVo.setTotalsalary_earned(earned_grossSalary);
				
				double earnedBasicDa=Math.round(earnedBasic+earnedDa);
				
				generatePayrollVo.setPfempr_earned(PayrollCalculations.calculatePfEmpr(salary_vo, earnedBasicDa));
	        	generatePayrollVo.setPfempy_earned(PayrollCalculations.calculatePfEmpy(salary_vo, earnedBasicDa));
	        	generatePayrollVo.setEsiempr_earned(PayrollCalculations.calculateEsiEmpr(salary_vo, earned_grossSalary));
	        	generatePayrollVo.setEsiempy_earned(PayrollCalculations.calculateEsiEmpy(salary_vo, earned_grossSalary));
	        	generatePayrollVo.setPf(generatePayrollVo.getPfempr_earned()+generatePayrollVo.getPfempy_earned());
	        	generatePayrollVo.setEsi(generatePayrollVo.getEsiempr_earned()+generatePayrollVo.getEsiempy_earned());
	        	generatePayrollVo.setPtax_earned(PayrollCalculations.calculateProfTax(salary_vo,earnedBasicDa));
	        	generatePayrollVo.setIncometax_earned(PayrollCalculations.calculateIncomeTax(salary_vo,earnedBasicDa));
				
	        	
	        	double totDeductions=generatePayrollVo.getPfempr_earned()+generatePayrollVo.getEsiempr_earned()+generatePayrollVo.getPfempy_earned()+generatePayrollVo.getEsiempy_earned()
        								+generatePayrollVo.getPtax_earned()+generatePayrollVo.getIncometax_earned();
	        	
	        	generatePayrollVo.setTotaldeductions(totDeductions);
	        	
	        	generatePayrollVo.setTakehome(earned_grossSalary-totDeductions);
	        	generatePayrollVo.setNetpay(earned_grossSalary-totDeductions);
				payrollList.add(generatePayrollVo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if (ps_payroll != null&& (!ps_payroll.isClosed())) {
					ps_payroll.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollDaoImpl: getPayrollList: Ending");

		return payrollList;
	}
	
	private Map<String, StaffDetailsForPayrollVo> getGeneratedEmployeeDetailsForPayroll(int month, int year, Connection connn, String locationId) {
		Map<String,StaffDetailsForPayrollVo> StaffDetailsForPayrollMap = new HashMap<String, StaffDetailsForPayrollVo>();
		StaffDetailsForPayrollVo staffpayroll=null;
		
		Connection conn=connn;
	
		try{
			
			List<StaffSearchVo> empList= getPayrollGeneratedStaffList(conn,locationId,month,year);
			
			for(int i=0;i<empList.size();i++){
				
				staffpayroll = new StaffDetailsForPayrollVo();
				
				String staffId=empList.get(i).getStaffid();
				
				staffpayroll.setEmpId(staffId);
				staffpayroll.setRegId(empList.get(i).getRegId());
				staffpayroll.setEmpName(empList.get(i).getStaffname());
				staffpayroll.setDepartmentname(empList.get(i).getDeptname());
				staffpayroll.setDesignation(empList.get(i).getDesignation());
				staffpayroll.setDoj(empList.get(i).getDoj());
				staffpayroll.setTotalDaysInMonth(HelperClass.getDaysByMonthAndYear(month, year)+"");
				
				staffpayroll.setTotalPresent(getEmployeeTotalAttendance( staffId, month, year).get("totalPresent"));
				staffpayroll.setTotalAbsent(getEmployeeTotalAttendance( staffId, month, year).get("totalAbsent"));
				staffpayroll.setTotalLeave(getEmployeeTotalAttendance( staffId, month, year).get("totalleave"));
				staffpayroll.setTotalHoliday(getEmployeeTotalAttendance( staffId, month, year).get("holidaydate"));
				staffpayroll.setTotalWeeklyOff(getEmployeeTotalAttendance( staffId, month, year).get("totalweeklyoff"));
				
				String totalabsentdates=  getEmployeeTotalAttendance( staffId, month, year).get("totalabsentdates");
				String totalpresentdates=  getEmployeeTotalAttendance( staffId, month, year).get("totalpresentdates");
				String totalleavedates=  getEmployeeTotalAttendance( staffId, month, year).get("totalleavedates");
				String totalholidaydates=  getEmployeeTotalAttendance( staffId, month, year).get("totalholidaydates");
				String totalweekoffdates=  getEmployeeTotalAttendance( staffId, month, year).get("totalweeklyoffdates");
				
				ArrayList<String> absentdates_list=new ArrayList<String>();
				ArrayList<String> presentdates_list=new ArrayList<String>();
				ArrayList<String> leavedates_list=new ArrayList<String>();
				ArrayList<String> holidaydates_list=new ArrayList<String>();
				ArrayList<String> weekoffdates_list=new ArrayList<String>();
				
				
				if(!(totalabsentdates.equalsIgnoreCase(""))){
					String[] tot_absentdate= totalabsentdates.split(",");
					for(int j=0;j<tot_absentdate.length;j++){

						absentdates_list.add(tot_absentdate[j]);

					}
					staffpayroll.setAbsentdates(absentdates_list);
				}else{
					
					staffpayroll.setAbsentdates(absentdates_list);
				}
				
				if(!(totalpresentdates.equalsIgnoreCase(""))){
					String[] tot_presentdate= totalpresentdates.split(",");
					for(int j=0;j<tot_presentdate.length;j++){

						presentdates_list.add(tot_presentdate[j]);

					}
					staffpayroll.setPresentdates(presentdates_list);
				}else{

					staffpayroll.setPresentdates(presentdates_list);
				}
				
				if(!(totalleavedates.equalsIgnoreCase(""))){
					String[] tot_leavedate= totalleavedates.split(",");
					for(int j=0;j<tot_leavedate.length;j++){

						leavedates_list.add(tot_leavedate[j]);

					}
					staffpayroll.setLeavedates(leavedates_list);
				}else{
					staffpayroll.setLeavedates(leavedates_list);
				}
				
				if(!(totalholidaydates.equalsIgnoreCase(""))){
					String[] tot_holidaydate= totalholidaydates.split(",");
					for(int j=0;j<tot_holidaydate.length;j++){

						holidaydates_list.add(tot_holidaydate[j]);

					}
					staffpayroll.setHolidaydates(holidaydates_list);
				}else{
					staffpayroll.setHolidaydates(holidaydates_list);
				}
				
				/*if(!(totalweekoffdates.equalsIgnoreCase(""))){
					String[] tot_weekoffdate= totalweekoffdates.split(",");
					for(int j=0;j<tot_weekoffdate.length;j++){

						weekoffdates_list.add(tot_weekoffdate[j]);

					}
					staffpayroll.setHolidaydates(weekoffdates_list);
				}else{
					staffpayroll.setHolidaydates(weekoffdates_list);
				}*/
				
				StaffDetailsForPayrollMap.put(staffId, staffpayroll);
			}
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return StaffDetailsForPayrollMap;
	}

	private List<StaffSearchVo> getStaffList(Connection connn, String locationId, int month, int year) {
		List<StaffSearchVo> staffList = new ArrayList<StaffSearchVo>();
		PreparedStatement ps_employee=null;
		ResultSet rs_employee=null;
		StaffSearchVo staffvo=null;
		Connection conn=connn;
		try{
			String monthname=String.valueOf(month);
			String yearname=String.valueOf(year);
			if(!locationId.equalsIgnoreCase("all")){
				ps_employee = conn.prepareStatement(StaffPayrollUtil.GET_STAFFLIST);
				ps_employee.setString(1, locationId);
				ps_employee.setString(2, monthname);
				ps_employee.setString(3, yearname);
			}else{
				ps_employee = conn.prepareStatement(StaffPayrollUtil.GET_STAFFLIST_ALL);
				ps_employee.setString(1, monthname);
				ps_employee.setString(2, yearname);
			}
			
			System.out.println(ps_employee);
			rs_employee = ps_employee.executeQuery();
			while(rs_employee.next()){

				staffvo= new StaffSearchVo();
				staffvo.setStaffid(rs_employee.getString("TeacherID"));
				staffvo.setRegId(rs_employee.getString("registerId"));
				staffvo.setStaffname(rs_employee.getString("staffname"));
				staffvo.setDeptname(rs_employee.getString("DEPT_NAME"));
				staffvo.setDesignation(rs_employee.getString("designationName"));
				staffvo.setDoj(rs_employee.getString("dateofJoining"));
				staffList.add(staffvo);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{

		}
		return staffList;
	}



	@Override
	public ArrayList<PayRollVo> getEmpMonthPayrollDetailsDownload(String accyear, String locationId, String monthId,String yearname) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in GeneratePayrollDaoImpl: getEmpMonthPayrollDetailsDownload : Starting");

		PreparedStatement salarystatement=null;
		Connection conn=null;
		ArrayList<PayRollVo> payrollList=new ArrayList<PayRollVo>();
		ResultSet rs_payroll=null;
		int count=0;

		try {

			conn=JDBCConnection.getSeparateConnection();
			String monthname=HelperClass.getMonthFullName(monthId);
			if(locationId.equalsIgnoreCase("all")){
				salarystatement = conn.prepareStatement(StaffPayrollUtil.GET_GENERATED_PAYROLL_DETAILS_ALL);
				salarystatement.setString(1, monthId);
				salarystatement.setString(2, yearname);
			}else{
				salarystatement = conn.prepareStatement(StaffPayrollUtil.GET_GENERATED_PAYROLL_DETAILS);
				salarystatement.setString(1, locationId);
				salarystatement.setString(2, monthId);
				salarystatement.setString(3, yearname);
			}

			rs_payroll=salarystatement.executeQuery();
			
			while(rs_payroll.next()){
				count++;

				PayRollVo payrollVo=new PayRollVo();
				payrollVo.setSno(count);
				payrollVo.setEmpId(rs_payroll.getString("registerId"));
				payrollVo.setEmpName(rs_payroll.getString("staffname"));
				payrollVo.setDesignationname(rs_payroll.getString("designationName"));
				payrollVo.setDeptName(rs_payroll.getString("DEPT_NAME"));
				payrollVo.setDoj(rs_payroll.getString("dateofJoining"));
				payrollVo.setPfnumber(rs_payroll.getString("EmployeePfNo"));
				payrollVo.setEsinumber(rs_payroll.getString("ESI_No"));
				payrollVo.setMonthDays(rs_payroll.getInt("No_of_Actualdays"));
				payrollVo.setPayabledays(rs_payroll.getInt("PayableDays"));
				payrollVo.setBasic_earned(rs_payroll.getDouble("Basic"));
				payrollVo.setDa_earned(rs_payroll.getDouble("DA"));
				payrollVo.setHra_earned(rs_payroll.getDouble("HRA"));
				payrollVo.setConvinience_earned(rs_payroll.getDouble("Conviance"));
				payrollVo.setChildEdu_earned(rs_payroll.getDouble("child_edu"));
				payrollVo.setSpecialAllowance_earned(rs_payroll.getDouble("special_allowance"));
				payrollVo.setArrears_earned(rs_payroll.getDouble("arrears"));
				payrollVo.setPerformanceIncentive_earned(rs_payroll.getDouble("performace_incentive"));
				payrollVo.setMedicalReimbursement_earned(rs_payroll.getDouble("medical"));
				payrollVo.setLeaveEncash_earned(rs_payroll.getDouble("leaveEncash"));
				payrollVo.setFoodAllowance_earned(rs_payroll.getDouble("food_allowance"));
				payrollVo.setReimbursement_earned(rs_payroll.getDouble("reimbursement"));
				payrollVo.setInternetExpense_earned(rs_payroll.getDouble("internet"));
				payrollVo.setDriverSalary_earned(rs_payroll.getDouble("drivery_sal"));
				payrollVo.setOthers_earned(rs_payroll.getDouble("Others"));
				payrollVo.setTotalsalary_earned(rs_payroll.getDouble("gross_salary"));
				
				payrollVo.setPfempr_earned(rs_payroll.getDouble("Employer_Pf"));
				payrollVo.setPfempy_earned(rs_payroll.getDouble("Employee_Pf"));
				payrollVo.setEsiempr_earned(rs_payroll.getDouble("Employer_esi"));
				payrollVo.setEsiempy_earned(rs_payroll.getDouble("Employee_esi"));
				payrollVo.setPtax_earned(rs_payroll.getDouble("P_Tax"));
				payrollVo.setIncometax_earned(rs_payroll.getDouble("IncomeTax"));
				payrollVo.setTotaldeductions(rs_payroll.getDouble("Total_Deductions"));
				double netsalary=(payrollVo.getTotalsalary_earned()-payrollVo.getTotaldeductions());
				payrollVo.setTotalsalary(netsalary);

				payrollVo.setLocationName(rs_payroll.getString("Location_Name"));
				payrollVo.setLocationAddress(rs_payroll.getString("Location_Address"));
				payrollVo.setLocationPhone(rs_payroll.getString("Location_Phone"));
				payrollVo.setPaymentType(rs_payroll.getString("paymentType"));
				payrollVo.setBankname(rs_payroll.getString("BankName"));
				payrollVo.setBankaccNo(rs_payroll.getString("BankAccNumber"));
				payrollVo.setPannumber(rs_payroll.getString("PanNo"));
				payrollVo.setMonthName(monthname);
				payrollVo.setYearName(yearname);
				
				payrollList.add(payrollVo);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1);
		}finally{
			try{
				if(salarystatement!=null && (!salarystatement.isClosed())){

					salarystatement.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle);
			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error(e1);
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in GeneratePayrollDaoImpl: getEmpMonthPayrollDetailsDownload : Ending");

		return payrollList;

	}
	
}



