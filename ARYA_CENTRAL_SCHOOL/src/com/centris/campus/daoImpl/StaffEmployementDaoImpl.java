package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StaffEmployementDao;
import com.centris.campus.forms.StaffEmployementForm;
import com.centris.campus.util.ClassUtilsConstants;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.StaffEmployementUtil;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.vo.StaffEmployementVo;
import com.centris.campus.vo.StaffSearchVo;
import com.itextpdf.text.log.SysoLogger;

public class StaffEmployementDaoImpl implements StaffEmployementDao{

	private static final Logger logger = Logger.getLogger(StaffEmployementDaoImpl.class);
	

	@Override
	public StaffEmployementVo  getStaffEmployementEntry(String teachercode,String academic_year) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementDaoImpl : getStaffEmployementEntry Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs_salary=null;
		String accyearStart=null,accEndDate=null,doj=null;
		StaffEmployementVo empvo=new StaffEmployementVo();
		int noofmonthsInt=0,month = 0,day=0;
		try {
			
				conn = JDBCConnection.getSeparateConnection();
				
				PreparedStatement accyearpstmt = conn.prepareStatement("select startDate,endDate from campus_acadamicyear where acadamic_id = ?");
				accyearpstmt.setString(1,academic_year);
				ResultSet accyearrs = accyearpstmt.executeQuery();
				while(accyearrs.next()){
					accyearStart = accyearrs.getString("startDate");
					accEndDate = accyearrs.getString("endDate");
				}
				
				
				pstmt = conn.prepareStatement(StaffEmployementUtil.GET_SALARY_DETAILS);
				pstmt.setString(1, teachercode);
				
				System.out.println("GET_SALARY_DETAILS"+pstmt);
				
				rs_salary=pstmt.executeQuery();
			
				while(rs_salary.next()){
					
					empvo.setEmpname(rs_salary.getString("staffName"));
					empvo.setBankaccnumber(rs_salary.getLong("BankAccNumber"));
					empvo.setEmployeepfno(rs_salary.getString("EmployeePfNo"));
					empvo.setBankname(rs_salary.getString("BankName"));
					empvo.setIFSCCode(rs_salary.getString("IFSCCode"));
					empvo.setPaymenttype(rs_salary.getString("PaymentType"));
					empvo.setEmpid(rs_salary.getString("registerId"));

					empvo.setBasic(rs_salary.getDouble("Basic"));
					empvo.setHra(rs_salary.getDouble("Hra"));
					empvo.setAllowance(rs_salary.getDouble("Convenience"));
					empvo.setDa(rs_salary.getDouble("Da"));
					empvo.setPerfincentive(rs_salary.getDouble("PerformanceIncentive"));
					empvo.setFoodallowance(rs_salary.getDouble("FoodAllowance"));
					empvo.setSpecial(rs_salary.getDouble("SpecialAllowance"));
					empvo.setOthers(rs_salary.getDouble("OtherAllowance"));
					empvo.setChildedu(rs_salary.getDouble("childEdu"));
					empvo.setArrears(rs_salary.getDouble("arrears"));
					empvo.setReimburse(rs_salary.getDouble("reimbursement"));
					empvo.setInternet(rs_salary.getDouble("internetExpense"));
					empvo.setDriver(rs_salary.getDouble("driverSalary"));
					empvo.setLeave(rs_salary.getDouble("leaveEncash"));
					empvo.setMedical(rs_salary.getDouble("medicalReimbursement"));
					empvo.setTotalsalary(rs_salary.getDouble("totalSalary"));
					empvo.setPfemployer(rs_salary.getDouble("pfEmployerAmount"));
					empvo.setEsiemployer(rs_salary.getDouble("esiEmployerAmount"));
					empvo.setEmployerpf(rs_salary.getDouble("EmployerPf"));
					empvo.setEmployerESI(rs_salary.getDouble("EmployerESI"));
					
				
					empvo.setTaxworkNetTax(rs_salary.getDouble("taxworkNetTax"));
					empvo.setTaxworkTaxableIncome(rs_salary.getDouble("taxworkTaxableIncome"));
					
					doj = rs_salary.getString("dateofJoining");
				
					empvo.setHrapercentage(rs_salary.getInt("HRAPercentage"));
					empvo.setMonthlyhraamount(rs_salary.getDouble("hraperamount"));
					empvo.setExemptTotal(rs_salary.getDouble("exemptTotal"));
					empvo.setOtherIncomeTotal(rs_salary.getDouble("incomeOtherTotal"));
					empvo.setSection80CTotal(rs_salary.getDouble("section80CTotal"));
					empvo.setSection80D80UTotal(rs_salary.getDouble("section80D80UTotal"));
					empvo.setIncometax(rs_salary.getDouble("IncomeTax"));
					empvo.setPt(rs_salary.getDouble("Pt"));
					empvo.setTdspt(rs_salary.getDouble("TDSPT"));
					empvo.setTaxpayble(rs_salary.getDouble("TDSTaxPayble"));
					empvo.setTaxableincome(rs_salary.getDouble("TDSTaxbleIncome"));
					empvo.setTotalExemptions(rs_salary.getDouble("TDSExemption"));
					empvo.setTotaltdsundervia(rs_salary.getDouble("TDSUnderChapterVIA"));
					empvo.setTotalTDSIncomeFromOther(rs_salary.getDouble("TDSIncomeFromOther"));
					empvo.setEmployeeESI(rs_salary.getDouble("EmployeeESI"));
					empvo.setEmployeepf(rs_salary.getDouble("EmployeePf"));
					empvo.setMonth(noofmonthsInt);
				}

			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				try {
					if (rs_salary != null && !rs_salary.isClosed()) {
						rs_salary.close();
					}
					if (pstmt != null && (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
		
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StaffEmployementDaoImpl : getStaffEmployementEntry  Starting");
	
	return empvo;

	}

	@Override
	public String saveStaffSalaryDetails(StaffEmployementForm espojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementDaoImpl : saveStaffSalaryDetails Starting");
		Connection conn = null;
		PreparedStatement pstmt1 = null,salarystatement = null;

		ResultSet rs = null;

		String status=null;
		int count=0,uploadStatus=0;

		Timestamp currentdate=HelperClass.getCurrentTimestamp();
		int existCount=0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			conn.setAutoCommit(false);
			pstmt1= conn.prepareStatement(StaffEmployementUtil.GET_SALARY_COUNT);
			pstmt1.setString(1, espojo.getHemloyeeid());

			rs=pstmt1.executeQuery();

			while(rs.next()){
				existCount=rs.getInt(1);
			}
			if(existCount==0){
				salarystatement = conn.prepareStatement(StaffEmployementUtil.INSERT_SALARY);
				
				salarystatement.setString(1, espojo.getHemloyeeid());
				salarystatement.setLong(2,espojo.getBankaccnumber());
				salarystatement.setString(3, espojo.getEmployeepfno());
				salarystatement.setString(4, espojo.getBankname());
				salarystatement.setString(5, espojo.getIFSCCode());
				salarystatement.setString(6, espojo.getPaymenttype());
				salarystatement.setDouble(7, espojo.getBasic());
				salarystatement.setDouble(8, espojo.getDa());
				salarystatement.setDouble(9, espojo.getHra());
				salarystatement.setDouble(10, espojo.getAllowance());
				salarystatement.setDouble(11, espojo.getChildedu());
				salarystatement.setDouble(12, espojo.getSpecial());
				salarystatement.setDouble(13, espojo.getArrears());
				salarystatement.setDouble(14, espojo.getPerformace());
				salarystatement.setDouble(15, espojo.getMedical());
				salarystatement.setDouble(16, espojo.getLeave());
				salarystatement.setDouble(17, espojo.getFood());
				salarystatement.setDouble(18, espojo.getReimburse());
				salarystatement.setDouble(19, espojo.getInternet());
				salarystatement.setDouble(20, espojo.getDriver());
				salarystatement.setDouble(21, espojo.getOther());
				salarystatement.setDouble(22, espojo.getTotalsalary());
				salarystatement.setDouble(23, espojo.getPfemployer());
				salarystatement.setDouble(24, espojo.getEsiemployer());
				salarystatement.setDouble(25, espojo.getPfamount());
				salarystatement.setDouble(26, espojo.getEsiamount());
				salarystatement.setString(27, espojo.getCreatedby());
				salarystatement.setTimestamp(28, currentdate);
				salarystatement.setInt(29, espojo.getHrapercentage());
				salarystatement.setDouble(30, espojo.getMonthlyhraamount());
				salarystatement.setDouble(31, espojo.getTdsctc());
				salarystatement.setDouble(32, espojo.getStandarddedu());
				salarystatement.setDouble(33, espojo.getTdsexmption());
				salarystatement.setDouble(34, espojo.getTdspt());
				salarystatement.setDouble(35, espojo.getTaxableincome());
				salarystatement.setDouble(36, espojo.getTaxpayble());
				salarystatement.setDouble(37, espojo.getIncometax());
				salarystatement.setDouble(38, espojo.getPt());
				salarystatement.setDouble(39, espojo.getDemployeeESI());
				salarystatement.setDouble(40, espojo.getDemployeepf());
				salarystatement.setDouble(41, espojo.getTdsundervia());

				System.out.println("insert pstmt :: "+salarystatement);

				count=salarystatement.executeUpdate();
				if(count >0){
					if ((espojo.getHemloyeeid() != null) && !(espojo.getHemloyeeid().equalsIgnoreCase("")) ) {
						uploadStatus = saveStaffTdsDetails(espojo,conn);
						conn.commit();
						status="true";
					}else {
						try {
							conn.rollback();
						} catch (SQLException e1) {
							logger.error(e1.getMessage(), e1);
							e1.printStackTrace();
						}
						status="false";
					}
				}else {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						logger.error(e1.getMessage(), e1);
						e1.printStackTrace();
					}
					status="false";
				}
			}else{
				salarystatement = conn.prepareStatement(StaffEmployementUtil.UPDATE_SALARY);
				
				salarystatement.setLong(1,espojo.getBankaccnumber());
				salarystatement.setString(2, espojo.getEmployeepfno());
				salarystatement.setString(3, espojo.getBankname());
				salarystatement.setString(4, espojo.getIFSCCode());
				salarystatement.setString(5, espojo.getPaymenttype());
				salarystatement.setDouble(6, espojo.getBasic());
				salarystatement.setDouble(7, espojo.getDa());
				salarystatement.setDouble(8, espojo.getHra());
				salarystatement.setDouble(9, espojo.getAllowance());
				salarystatement.setDouble(10, espojo.getChildedu());
				salarystatement.setDouble(11, espojo.getSpecial());
				salarystatement.setDouble(12, espojo.getArrears());
				salarystatement.setDouble(13, espojo.getPerformace());
				salarystatement.setDouble(14, espojo.getMedical());
				salarystatement.setDouble(15, espojo.getLeave());
				salarystatement.setDouble(16, espojo.getFood());
				salarystatement.setDouble(17, espojo.getReimburse());
				salarystatement.setDouble(18, espojo.getInternet());
				salarystatement.setDouble(19, espojo.getDriver());
				salarystatement.setDouble(20, espojo.getOther());
				salarystatement.setDouble(21, espojo.getTotalsalary());
				salarystatement.setDouble(22, espojo.getPfemployer());
				salarystatement.setDouble(23, espojo.getEsiemployer());
				salarystatement.setDouble(24, espojo.getPfamount());
				salarystatement.setDouble(25, espojo.getEsiamount());
				salarystatement.setString(26, espojo.getCreatedby());
				salarystatement.setTimestamp(27, currentdate);
				salarystatement.setInt(28, espojo.getHrapercentage());
				salarystatement.setDouble(29, espojo.getMonthlyhraamount());
				salarystatement.setDouble(30, espojo.getTdsctc());
				salarystatement.setDouble(31, espojo.getStandarddedu());
				salarystatement.setDouble(32, espojo.getTdsexmption());
				salarystatement.setDouble(33, espojo.getTdspt());
				salarystatement.setDouble(34, espojo.getTaxableincome());
				salarystatement.setDouble(35, espojo.getTaxpayble());
				salarystatement.setDouble(36, espojo.getIncometax());
				salarystatement.setDouble(37, espojo.getPt());
				salarystatement.setDouble(38, espojo.getEmployeeESI());
				salarystatement.setDouble(39, espojo.getEmployeepf());
				salarystatement.setDouble(40, espojo.getTdsundervia());
				salarystatement.setString(41, espojo.getHemloyeeid());

				System.out.println("update salary pstmt :: "+salarystatement);

				count=salarystatement.executeUpdate();
				
				if(count >0){
					if ((espojo.getHemloyeeid() != null) && !(espojo.getHemloyeeid().equalsIgnoreCase("")) ) {
						uploadStatus = updateStaffTdsDetails(espojo, conn);
						conn.commit();
						status="true";
					}else {
						try {
							conn.rollback();
						} catch (SQLException e1) {
							logger.error(e1.getMessage(), e1);
							e1.printStackTrace();
						}
						status="false";
					}
				}else {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						logger.error(e1.getMessage(), e1);
						e1.printStackTrace();
					}
					status="false";
				}
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
				}
				if (salarystatement != null && (!salarystatement.isClosed())) {
					salarystatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementDaoImpl : saveStaffSalaryDetails  Starting");

		return status;

	}
	
	public boolean validateEmployeePfNumber(String emppfno,String empid){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeSalaryDetailsDaoImpl: validateEmployeePfNumber : Starting");
		PreparedStatement salarystatement2=null;
		ResultSet rs_salary2=null;
		boolean status=false;
		Connection conn = null;
		int pf_count=0;
		
				try {
					
					conn = JDBCConnection.getSeparateConnection();
					
					salarystatement2=conn.prepareStatement(StaffEmployementUtil.CHECK_DUPLICATE_PF_NO);
					salarystatement2.setString(1, emppfno);
					salarystatement2.setString(2, empid.trim());
					
					System.out.println("CHECK_DUPLICATE_PF_NO"+salarystatement2);
					rs_salary2=salarystatement2.executeQuery();
					
					while(rs_salary2.next()){
						
						pf_count++;						
					}
					
				
					 
					 if(pf_count>0){
						 status=true;
					 }else{
						 
						 status=false;
					 }
					
					 

				}catch (SQLException sqle) {
					sqle.printStackTrace();
					logger.error(sqle);
				} catch (Exception e1) {
					e1.printStackTrace();
					logger.error(e1);
				}finally{
						try{
							if (rs_salary2 != null && !rs_salary2.isClosed()) {
								rs_salary2.close();
							}
							if (salarystatement2 != null && (!salarystatement2.isClosed())) {
								salarystatement2.close();
							}
							if (conn != null && (!conn.isClosed())) {
								conn.close();
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
						+ " Control in EmployeeSalaryDetailsDaoImpl: validateEmployeePfNumber : Ending");
				
				return status;
			

	}


	public boolean validateBankAccNumber(String accnumber,String empid){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeSalaryDetailsDaoImpl: validateBankAccNumber : Starting");

		
		PreparedStatement salarystatement=null;
		ResultSet rs_salary=null;
		boolean status=false;
		Connection conn = null;
		int count=0;

		
				try {
					
					conn = JDBCConnection.getSeparateConnection();
					 salarystatement = conn.prepareStatement(StaffEmployementUtil.CHECK_BANK_ACC_NUMBER);
					 
					 salarystatement.setString(1, accnumber);
					 salarystatement.setString(2, empid);
					 System.out.println("CHECK_BANK_ACC_NUMBER"+salarystatement);
					 rs_salary=salarystatement.executeQuery();
					 
					 while(rs_salary.next()){
						 
						 count=rs_salary.getInt(1);
						 
					 }
					 
					 if(count>0){
						 status=true;
					 }else{
						 
						 status=false;
					 }
					 

				} catch (SQLException sqle) {
					sqle.printStackTrace();
					logger.error(sqle);
				} catch (Exception e1) {
					e1.printStackTrace();
					logger.error(e1);
				}finally{
					try{
						if (rs_salary != null && !rs_salary.isClosed()) {
							rs_salary.close();
						}
						if (salarystatement != null && (!salarystatement.isClosed())) {
							salarystatement.close();
						}
						if (conn != null && (!conn.isClosed())) {
							conn.close();
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
						+ " Control in EmployeeSalaryDetailsDaoImpl: validateBankAccNumber : Ending");
				
				return status;
	}
	
	private int saveStaffTdsDetails(StaffEmployementForm espojo, Connection conn) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Starting");

		PreparedStatement salarystatement = null;
		Connection con = null;
		int status = 0;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		try {
			con = conn;
			
			salarystatement = conn.prepareStatement(StaffEmployementUtil.INSERT_SALARY_FULLDETAILS);
			/*TeacherID,pfMonthlyEmplrAmount,pfAmount,esiMonthlyAmount,esiAmount,salaryMonthlyBasic,salaryMonthlyDA,salaryMonthlyHRA,salaryMonthlyTransConveyAllow,
			salaryMonthlyChildEdu,salaryMonthlySplAllowance,salaryMonthlyArrears,salaryMonthlyPerformIncentive,salaryMonthlyMedicalReimb,
			salaryMonthlyLeaveEncash,salaryMonthlyFoodCoupon,salaryMonthlyReimbursement,salaryMonthlyInternetExp,salaryMonthlyDriversalary,salaryMonthlyOther,
			salaryMonthlyTotal,salaryGrossBasic,salaryGrossDA,salaryGrossHRA,salaryGrossTransConveyAllow,salaryGrossChildEdu,salaryGrossSplAllowance,
			salaryGrossArrears,salaryGrossPerformIncentive,salaryGrossMedicalReimb,salaryGrossLeaveEncash,salaryGrossFoodCoupon,salaryGrossReimbursement,
			salaryGrossInternetExp,salaryGrossDriverGross,salaryGrossOther,salaryGrossTotal,created_date,created_by,locationId,accyearId*/
			
			salarystatement.setString(1, espojo.getHemloyeeid());
			salarystatement.setDouble(2, espojo.getPfemployer());
			salarystatement.setDouble(3, espojo.getPfamount());
			salarystatement.setDouble(4, espojo.getEsiemployer());
			salarystatement.setDouble(5, espojo.getEsiamount());
			salarystatement.setDouble(6, espojo.getBasic());
			salarystatement.setDouble(7, espojo.getDa());
			salarystatement.setDouble(8, espojo.getHra());
			salarystatement.setDouble(9, espojo.getAllowance());
			salarystatement.setDouble(10, espojo.getChildedu());
			salarystatement.setDouble(11, espojo.getSpecial());
			salarystatement.setDouble(12, espojo.getArrears());
			salarystatement.setDouble(13, espojo.getPerformace());
			salarystatement.setDouble(14, espojo.getMedical());
			salarystatement.setDouble(15, espojo.getLeave());
			salarystatement.setDouble(16, espojo.getFood());
			salarystatement.setDouble(17, espojo.getReimburse());
			salarystatement.setDouble(18, espojo.getInternet());
			salarystatement.setDouble(19, espojo.getDriver());
			salarystatement.setDouble(20, espojo.getOther());
			salarystatement.setDouble(21, espojo.getTotalsalary());
			
			salarystatement.setDouble(22, espojo.getBasictotal());
			salarystatement.setDouble(23, espojo.getDatotal());
			salarystatement.setDouble(24, espojo.getHratotal());
			salarystatement.setDouble(25, espojo.getAllowancetotal());
			salarystatement.setDouble(26, espojo.getChildedutotal());
			salarystatement.setDouble(27, espojo.getSpecialtotal());
			salarystatement.setDouble(28, espojo.getArrearstotal());
			salarystatement.setDouble(29, espojo.getPerformacetotal());
			salarystatement.setDouble(30, espojo.getMedicaltotal());
			salarystatement.setDouble(31, espojo.getLeavetotal());
			salarystatement.setDouble(32, espojo.getFoodtotal());
			salarystatement.setDouble(33, espojo.getReimbursetotal());
			salarystatement.setDouble(34, espojo.getInternettotal());
			salarystatement.setDouble(35, espojo.getDrivertotal());
			salarystatement.setDouble(36, espojo.getOthertotal());
			salarystatement.setDouble(37, espojo.getYearlytotalamount());
			salarystatement.setTimestamp(38, createdDate);
			salarystatement.setString(39, espojo.getCreatedby());
			salarystatement.setString(40, espojo.getAccyearId());
			salarystatement.setString(41, espojo.getLocationId());

			System.out.println("insert pstmt :: "+salarystatement);

			status=salarystatement.executeUpdate();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			
			try {
				if (salarystatement != null && (!salarystatement.isClosed())) {

					salarystatement.close();
				  }
			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Ending");
		return status;
	}
	
	private int updateStaffTdsDetails(StaffEmployementForm espojo, Connection conn) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Starting");

		PreparedStatement salarystatement = null;
		Connection con = null;
		int status = 0;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		try {
			con = conn;
			
			salarystatement = conn.prepareStatement(StaffEmployementUtil.UPDATE_SALARY_FULLDETAILS);
			
			salarystatement.setDouble(1, espojo.getPfemployer());
			salarystatement.setDouble(2, espojo.getPfamount());
			salarystatement.setDouble(3, espojo.getEsiemployer());
			salarystatement.setDouble(4, espojo.getEsiamount());
			salarystatement.setDouble(5, espojo.getBasic());
			salarystatement.setDouble(6, espojo.getDa());
			salarystatement.setDouble(7, espojo.getHra());
			salarystatement.setDouble(8, espojo.getAllowance());
			salarystatement.setDouble(9, espojo.getChildedu());
			salarystatement.setDouble(10, espojo.getSpecial());
			salarystatement.setDouble(11, espojo.getArrears());
			salarystatement.setDouble(12, espojo.getPerformace());
			salarystatement.setDouble(13, espojo.getMedical());
			salarystatement.setDouble(14, espojo.getLeave());
			salarystatement.setDouble(15, espojo.getFood());
			salarystatement.setDouble(16, espojo.getReimburse());
			salarystatement.setDouble(17, espojo.getInternet());
			salarystatement.setDouble(18, espojo.getDriver());
			salarystatement.setDouble(19, espojo.getOther());
			salarystatement.setDouble(20, espojo.getTotalsalary());
			
			salarystatement.setDouble(21, espojo.getBasictotal());
			salarystatement.setDouble(22, espojo.getDatotal());
			salarystatement.setDouble(23, espojo.getHratotal());
			salarystatement.setDouble(24, espojo.getAllowancetotal());
			salarystatement.setDouble(25, espojo.getChildedutotal());
			salarystatement.setDouble(26, espojo.getSpecialtotal());
			salarystatement.setDouble(27, espojo.getArrearstotal());
			salarystatement.setDouble(28, espojo.getPerformacetotal());
			salarystatement.setDouble(29, espojo.getMedicaltotal());
			salarystatement.setDouble(30, espojo.getLeavetotal());
			salarystatement.setDouble(31, espojo.getFoodtotal());
			salarystatement.setDouble(32, espojo.getReimbursetotal());
			salarystatement.setDouble(33, espojo.getInternettotal());
			salarystatement.setDouble(34, espojo.getDrivertotal());
			salarystatement.setDouble(35, espojo.getOthertotal());
			salarystatement.setDouble(36, espojo.getYearlytotalamount());
			salarystatement.setTimestamp(37, createdDate);
			salarystatement.setString(38, espojo.getCreatedby());
			salarystatement.setString(39, espojo.getHemloyeeid());

			System.out.println("update full details pstmt :: "+salarystatement);

			status=salarystatement.executeUpdate();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			
			try {
				if (salarystatement != null && (!salarystatement.isClosed())) {

					salarystatement.close();
				  }
			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Ending");
		return status;
	}

	@Override
	public String saveStaffIncomeTaxTDSDeductionDetails(StaffEmployementForm staffForm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementDaoImpl : saveStaffSalaryDetails Starting");
		Connection conn = null;
		PreparedStatement salarystatement = null,pstmt=null;
		String status=null;
		ResultSet rs=null;
		int count=0,uploadStatus=0,staffcount=0,updateSalary=0;

		Timestamp currentdate=HelperClass.getCurrentTimestamp();
		DecimalFormat df = new DecimalFormat("#.00");
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement("select count(TeacherID) as counts from campus_staff_income_section where TeacherID=?");
			pstmt.setString(1, staffForm.getHemloyeeid());
			rs=pstmt.executeQuery();
			while(rs.next()){
				staffcount=rs.getInt("counts");
			}
			if(staffcount == 0){
				salarystatement = conn.prepareStatement(StaffEmployementUtil.INSERT_INCOME_SECTION);
				salarystatement.setString(1, staffForm.getHemloyeeid());
				salarystatement.setDouble(2, Double.parseDouble(df.format(staffForm.getHouseincomefromother())));
				salarystatement.setDouble(3, Double.parseDouble(df.format(staffForm.getHouseintrestloan())));
				salarystatement.setDouble(4, Double.parseDouble(df.format(staffForm.getOtherIncomeSelf())));
				salarystatement.setDouble(5, Double.parseDouble(df.format(staffForm.getTotalofOtherIncome())));
				salarystatement.setDouble(6, Double.parseDouble(df.format(staffForm.getHraExemptionSection())));
				salarystatement.setDouble(7, Double.parseDouble(df.format(staffForm.getChildeducationexemption())));
				salarystatement.setDouble(8, Double.parseDouble(df.format(staffForm.getMedicalsectionexemption())));
				salarystatement.setDouble(9, Double.parseDouble(df.format( staffForm.getTransportsectionexemption())));
				salarystatement.setDouble(10, Double.parseDouble(df.format(staffForm.getLtasectionexemption())));
				salarystatement.setDouble(11, Double.parseDouble(df.format(staffForm.getFoodsectionexemption())));
				salarystatement.setDouble(12, Double.parseDouble(df.format(staffForm.getTelephoneexemption())));
				salarystatement.setDouble(13, Double.parseDouble(df.format(staffForm.getCarreimburseexemption())));
				salarystatement.setDouble(14, Double.parseDouble(df.format(staffForm.getInternetsectionexemption())));
				salarystatement.setDouble(15, Double.parseDouble(df.format(staffForm.getDriversectionexemption())));
				salarystatement.setDouble(16, Double.parseDouble(df.format(staffForm.getOtherssectionexpense())));
				salarystatement.setDouble(17, Double.parseDouble(df.format(staffForm.getTotalSectionAmount())));
				salarystatement.setDouble(18, Double.parseDouble(df.format(staffForm.getEmpprovidentfund())));
				salarystatement.setDouble(19, Double.parseDouble(df.format(staffForm.getTutionfee())));
				salarystatement.setDouble(20, Double.parseDouble(df.format(staffForm.getFixeddeposit())));
				salarystatement.setDouble(21, Double.parseDouble(df.format(staffForm.getLifeinspremium())));
				salarystatement.setDouble(22, Double.parseDouble(df.format(staffForm.getMutualfund())));
				salarystatement.setDouble(23, Double.parseDouble(df.format(staffForm.getNationalsavecertificate())));
				salarystatement.setDouble(24, Double.parseDouble(df.format(staffForm.getAccruednsci())));
				salarystatement.setDouble(25, Double.parseDouble(df.format(staffForm.getPublicpf())));
				salarystatement.setDouble(26, Double.parseDouble(df.format(staffForm.getRepaymenthouseloan())));
				salarystatement.setDouble(27, Double.parseDouble(df.format(staffForm.getUnitLinkInsPlan())));
				salarystatement.setDouble(28, Double.parseDouble(df.format(staffForm.getSeniorcitizensave())));
				salarystatement.setDouble(29, Double.parseDouble(df.format(staffForm.getPostsavingbank())));
				salarystatement.setDouble(30, Double.parseDouble(df.format(staffForm.getOthersection())));
				salarystatement.setDouble(31, Double.parseDouble(df.format(staffForm.getTotalChapter())));
				salarystatement.setDouble(32, Double.parseDouble(df.format(staffForm.getMediclaimself())));
				salarystatement.setDouble(33, Double.parseDouble(df.format(staffForm.getMediclaimparents())));
				salarystatement.setDouble(34, Double.parseDouble(df.format(staffForm.getMediclaimparentsabove())));
				salarystatement.setDouble(35, Double.parseDouble(df.format(staffForm.getMeditreatbeloweighty())));
				salarystatement.setDouble(36, Double.parseDouble(df.format(staffForm.getMeditreataboveeighty())));
				salarystatement.setDouble(37, Double.parseDouble(df.format(staffForm.getInteresteduloan())));
				salarystatement.setDouble(38, Double.parseDouble(df.format(staffForm.getAdditionalcontribute())));
				salarystatement.setDouble(39, Double.parseDouble(df.format(staffForm.getDeductionofphysicalbelow())));
				salarystatement.setDouble(40, Double.parseDouble(df.format(staffForm.getDeductionofphysicalabove())));
				salarystatement.setDouble(41, Double.parseDouble(df.format(staffForm.getRajivgandhiequity())));
				salarystatement.setDouble(42, Double.parseDouble(df.format(staffForm.getEightyttainterestsaving())));
				salarystatement.setDouble(43, Double.parseDouble(df.format(staffForm.getEightyddmedicaltreatbelow())));
				salarystatement.setDouble(44, Double.parseDouble(df.format(staffForm.getEightyddmedicaltreatabove())));
				salarystatement.setDouble(45, Double.parseDouble(df.format(staffForm.getTotalChapter80Up())));
				salarystatement.setTimestamp(46, currentdate);
				salarystatement.setString(47, staffForm.getCreatedby());
				salarystatement.setString(48, staffForm.getLocationId());
				salarystatement.setString(49, staffForm.getAccyearId());
				System.out.println("insert staff section :: "+salarystatement);

				count=salarystatement.executeUpdate();
				if(count >0){
					uploadStatus = saveHouseRentDetails(staffForm, conn);
					uploadStatus = saveTaxTdsDetails(staffForm, conn);
					updateSalary =updateTdsCalculation(staffForm, conn);
					conn.commit();
					status = "true";
				}else{
					try {
						conn.rollback();
					} catch (SQLException e1) {
						logger.error(e1.getMessage(), e1);
						e1.printStackTrace();
					}
					status="false";
				}
			}else{
				salarystatement = conn.prepareStatement(StaffEmployementUtil.UPDATE_INCOME_SECTION);
				
				salarystatement.setDouble(1, Double.parseDouble(df.format(staffForm.getHouseincomefromother())));
				salarystatement.setDouble(2, Double.parseDouble(df.format(staffForm.getHouseintrestloan())));
				salarystatement.setDouble(3, Double.parseDouble(df.format(staffForm.getOtherIncomeSelf())));
				salarystatement.setDouble(4, Double.parseDouble(df.format(staffForm.getTotalofOtherIncome())));
				salarystatement.setDouble(5, Double.parseDouble(df.format(staffForm.getHraExemptionSection())));
				salarystatement.setDouble(6, Double.parseDouble(df.format(staffForm.getChildeducationexemption())));
				salarystatement.setDouble(7, Double.parseDouble(df.format(staffForm.getMedicalsectionexemption())));
				salarystatement.setDouble(8, Double.parseDouble(df.format(staffForm.getTransportsectionexemption())));
				salarystatement.setDouble(9, Double.parseDouble(df.format(staffForm.getLtasectionexemption())));
				salarystatement.setDouble(10, Double.parseDouble(df.format(staffForm.getFoodsectionexemption())));
				salarystatement.setDouble(11, Double.parseDouble(df.format(staffForm.getTelephoneexemption())));
				salarystatement.setDouble(12, Double.parseDouble(df.format(staffForm.getCarreimburseexemption())));
				salarystatement.setDouble(13, Double.parseDouble(df.format(staffForm.getInternetsectionexemption())));
				salarystatement.setDouble(14, Double.parseDouble(df.format(staffForm.getDriversectionexemption())));
				salarystatement.setDouble(15, Double.parseDouble(df.format(staffForm.getOtherssectionexpense())));
				salarystatement.setDouble(16, Double.parseDouble(df.format(staffForm.getTotalSectionAmount())));
				salarystatement.setDouble(17, Double.parseDouble(df.format(staffForm.getEmpprovidentfund())));
				salarystatement.setDouble(18, Double.parseDouble(df.format(staffForm.getTutionfee())));
				salarystatement.setDouble(19, Double.parseDouble(df.format(staffForm.getFixeddeposit())));
				salarystatement.setDouble(20, Double.parseDouble(df.format(staffForm.getLifeinspremium())));
				salarystatement.setDouble(21, Double.parseDouble(df.format(staffForm.getMutualfund())));
				salarystatement.setDouble(22, Double.parseDouble(df.format(staffForm.getNationalsavecertificate())));
				salarystatement.setDouble(23, Double.parseDouble(df.format(staffForm.getAccruednsci())));
				salarystatement.setDouble(24, Double.parseDouble(df.format(staffForm.getPublicpf())));
				salarystatement.setDouble(25, Double.parseDouble(df.format(staffForm.getRepaymenthouseloan())));
				salarystatement.setDouble(26, Double.parseDouble(df.format(staffForm.getUnitLinkInsPlan())));
				salarystatement.setDouble(27, Double.parseDouble(df.format(staffForm.getSeniorcitizensave())));
				salarystatement.setDouble(28, Double.parseDouble(df.format(staffForm.getPostsavingbank())));
				salarystatement.setDouble(29, Double.parseDouble(df.format(staffForm.getOthersection())));
				salarystatement.setDouble(30, Double.parseDouble(df.format(staffForm.getTotalChapter())));
				salarystatement.setDouble(31, Double.parseDouble(df.format(staffForm.getMediclaimself())));
				salarystatement.setDouble(32, Double.parseDouble(df.format(staffForm.getMediclaimparents())));
				salarystatement.setDouble(33, Double.parseDouble(df.format(staffForm.getMediclaimparentsabove())));
				salarystatement.setDouble(34, Double.parseDouble(df.format(staffForm.getMeditreatbeloweighty())));
				salarystatement.setDouble(35, Double.parseDouble(df.format(staffForm.getMeditreataboveeighty())));
				salarystatement.setDouble(36, Double.parseDouble(df.format(staffForm.getInteresteduloan())));
				salarystatement.setDouble(37, Double.parseDouble(df.format(staffForm.getAdditionalcontribute())));
				salarystatement.setDouble(38, Double.parseDouble(df.format(staffForm.getDeductionofphysicalbelow())));
				salarystatement.setDouble(39, Double.parseDouble(df.format(staffForm.getDeductionofphysicalabove())));
				salarystatement.setDouble(40, Double.parseDouble(df.format(staffForm.getRajivgandhiequity())));
				salarystatement.setDouble(41, Double.parseDouble(df.format(staffForm.getEightyttainterestsaving())));
				salarystatement.setDouble(42, Double.parseDouble(df.format(staffForm.getEightyddmedicaltreatbelow())));
				salarystatement.setDouble(43, Double.parseDouble(df.format(staffForm.getEightyddmedicaltreatabove())));
				salarystatement.setDouble(44, Double.parseDouble(df.format(staffForm.getTotalChapter80Up())));
				salarystatement.setTimestamp(45, currentdate);
				salarystatement.setString(46, staffForm.getCreatedby());
				salarystatement.setString(47, staffForm.getHemloyeeid());
				salarystatement.setString(48, staffForm.getLocationId());
				salarystatement.setString(49, staffForm.getAccyearId());
				System.out.println("update staff section :: "+salarystatement);

				count=salarystatement.executeUpdate();
				if(count >0){
					uploadStatus = updateHouseRentDetails(staffForm, conn);
					uploadStatus = updateTaxTdsDetails(staffForm, conn);
					updateSalary =updateTdsCalculation(staffForm, conn);
					conn.commit();
					status = "true";
				}else{
					try {
						conn.rollback();
					} catch (SQLException e1) {
						logger.error(e1.getMessage(), e1);
						e1.printStackTrace();
					}
					status="false";
				}
			
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (salarystatement != null && (!salarystatement.isClosed())) {
					salarystatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementDaoImpl : saveStaffSalaryDetails  Starting");

		return status;

	}

	private int saveHouseRentDetails(StaffEmployementForm staffForm, Connection conn) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Starting");

		PreparedStatement pstmt = null;
		Connection con = null;
		int status = 0;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			con = conn;
			
			pstmt = con.prepareStatement(StaffEmployementUtil.INSERT_HOUSE_RENT);
			
			pstmt.setString(1, staffForm.getHemloyeeid());
			System.out.println("from date "+staffForm.getHousefromdate());
			if(staffForm.getHousefromdate().equalsIgnoreCase("") || staffForm.getHousefromdate() == null){
				pstmt.setString(2,"");
			}else{
				pstmt.setString(2, HelperClass.convertUIToDatabase(staffForm.getHousefromdate()));
			}
			if(staffForm.getHousetodate().equalsIgnoreCase("") || staffForm.getHousetodate() == null){
				pstmt.setString(3,"");
			}else{
				pstmt.setString(3, HelperClass.convertUIToDatabase(staffForm.getHousetodate()));
			}
			pstmt.setString(4, staffForm.getHousecity());
			pstmt.setString(5, staffForm.getLandlordname());
			pstmt.setString(6, staffForm.getHouseaddress());
			pstmt.setString(7, staffForm.getPancardno());
			pstmt.setDouble(8, Double.parseDouble(df.format(staffForm.getHouseamount())));
			pstmt.setTimestamp(9, createdDate);
			pstmt.setString(10, staffForm.getCreatedby());
			pstmt.setString(11, staffForm.getLocationId());
			pstmt.setString(12, staffForm.getAccyearId());
			
			status=pstmt.executeUpdate();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			
			try {
				if (pstmt != null && (!pstmt.isClosed())) {

					  pstmt.close();
				  }
			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Ending");
		return status;
	}

	private int updateHouseRentDetails(StaffEmployementForm staffForm, Connection conn) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Starting");

		PreparedStatement pstmt = null;
		Connection con = null;
		int status = 0;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			con = conn;
			
			pstmt = con.prepareStatement(StaffEmployementUtil.UPDATE_HOUSE_RENT);
			
			pstmt.setString(1, HelperClass.convertUIToDatabase(staffForm.getHousefromdate()));
			pstmt.setString(2, HelperClass.convertUIToDatabase(staffForm.getHousetodate()));
			pstmt.setString(3, staffForm.getHousecity());
			pstmt.setString(4, staffForm.getLandlordname());
			pstmt.setString(5, staffForm.getHouseaddress());
			pstmt.setString(6, staffForm.getPancardno());
			pstmt.setDouble(7, Double.parseDouble(df.format(staffForm.getHouseamount())));
			pstmt.setTimestamp(8, createdDate);
			pstmt.setString(9, staffForm.getCreatedby());
			pstmt.setString(10, staffForm.getHemloyeeid());
			pstmt.setString(11, staffForm.getLocationId());
			pstmt.setString(12, staffForm.getAccyearId());
			
			status=pstmt.executeUpdate();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			
			try {
				if (pstmt != null && (!pstmt.isClosed())) {

					  pstmt.close();
				  }
			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Ending");
		return status;
	}
	
	private int saveTaxTdsDetails(StaffEmployementForm staffForm, Connection conn) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Starting");

		PreparedStatement pstmt = null;
		Connection con = null;
		int status = 0;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			con = conn;
			
			pstmt = con.prepareStatement(StaffEmployementUtil.INSERT_TAX_TDS);
			//insert into campus_staff_tax_tds_deduction(TeacherID,taxableExemption,taxableAnyOtherIncome,taxworkUnderCapterVIA,taxworkTaxableIncome,taxworkTotalTax,taxworkNetTax,taxworkPerMonth,created_date,created_by,locationId,accyearId)
			//TeacherID,taxableExemption,taxableAnyOtherIncome,taxworkUnderCapterVIA,taxworkTaxableIncome,taxworkTotalTax,taxworkNetTax,taxworkPerMonth,created_date,created_by,locationId,accyearId
			
			pstmt.setString(1, staffForm.getHemloyeeid());
			pstmt.setDouble(2, Double.parseDouble(df.format(staffForm.getTaxableExemption())));
			pstmt.setDouble(3, Double.parseDouble(df.format(staffForm.getTaxableAnyOtherIncome())));
			pstmt.setDouble(4, Double.parseDouble(df.format(staffForm.getTaxworkUnderCapterVIA())));
			pstmt.setDouble(5, Double.parseDouble(df.format(staffForm.getTaxableTotalAmount())));
			pstmt.setDouble(6, Double.parseDouble(df.format(staffForm.getTotalTaxAmount())));
			pstmt.setDouble(7, Double.parseDouble(df.format(staffForm.getNetTax())));
			pstmt.setDouble(8, Double.parseDouble(df.format(staffForm.getTaxworkPerMonth())));
			pstmt.setTimestamp(9, createdDate);
			pstmt.setString(10, staffForm.getCreatedby());
			pstmt.setString(11, staffForm.getLocationId());
			pstmt.setString(12, staffForm.getAccyearId());
			status=pstmt.executeUpdate();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			
			try {
				if (pstmt != null && (!pstmt.isClosed())) {

					  pstmt.close();
				  }
			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Ending");
		return status;
	}
	
	private int updateTaxTdsDetails(StaffEmployementForm staffForm, Connection conn) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Starting");

		PreparedStatement pstmt = null;
		Connection con = null;
		int status = 0;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			con = conn;
			
			pstmt = con.prepareStatement(StaffEmployementUtil.UPDATE_TAX_TDS);
			
			pstmt.setDouble(1, Double.parseDouble(df.format(staffForm.getTaxableExemption())));
			pstmt.setDouble(2, Double.parseDouble(df.format(staffForm.getTaxableAnyOtherIncome())));
			pstmt.setDouble(3, Double.parseDouble(df.format(staffForm.getTaxworkUnderCapterVIA())));
			pstmt.setDouble(4, Double.parseDouble(df.format(staffForm.getTaxableTotalAmount())));
			pstmt.setDouble(5, Double.parseDouble(df.format(staffForm.getTotalTaxAmount())));
			pstmt.setDouble(6, Double.parseDouble(df.format(staffForm.getNetTax())));
			pstmt.setDouble(7, Double.parseDouble(df.format(staffForm.getTaxworkPerMonth())));
			pstmt.setTimestamp(8, createdDate);
			pstmt.setString(9, staffForm.getCreatedby());
			pstmt.setString(10, staffForm.getHemloyeeid());
			pstmt.setString(11, staffForm.getLocationId());
			pstmt.setString(12, staffForm.getAccyearId());
			
			System.out.println("update tax "+pstmt);
			status=pstmt.executeUpdate();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			
			try {
				if (pstmt != null && (!pstmt.isClosed())) {

					  pstmt.close();
				  }
			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Ending");
		return status;
	}
	
	private int updateTdsCalculation(StaffEmployementForm staffForm, Connection conn) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Starting");

		PreparedStatement pstmt = null;
		Connection con = null;
		int status = 0;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			con = conn;
			
			pstmt = con.prepareStatement(StaffEmployementUtil.UPDATE_TAX_TDS_SALARY);
			//TDSTaxbleIncome,TDSTaxPayble,TDSExemption,TDSUnderChapterVIA,TDSIncomeFromOther,TeacherID,taxableTotalAmount
			pstmt.setDouble(1, Double.parseDouble(df.format(staffForm.getTaxableTotalAmount())));
			pstmt.setDouble(2, Double.parseDouble(df.format(staffForm.getNetTax())));
			pstmt.setDouble(3, Double.parseDouble(df.format(staffForm.getTaxableExemption())));
			pstmt.setDouble(4, Double.parseDouble(df.format(staffForm.getTaxworkUnderCapterVIA())));
			pstmt.setDouble(5, Double.parseDouble(df.format(staffForm.getTaxableAnyOtherIncome())));
			pstmt.setString(6, staffForm.getHemloyeeid());
			
			System.out.println("update tax "+pstmt);
			status=pstmt.executeUpdate();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			
			try {
				if (pstmt != null && (!pstmt.isClosed())) {

					  pstmt.close();
				  }
			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Ending");
		return status;
	}

}
