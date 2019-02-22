package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.actions.TDSComputationAction;
import com.centris.campus.dao.TDSComputationDAO;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.StaffEmployementVo;

public class TDSComputationDAOIMPL implements TDSComputationDAO {

	
	private static final Logger logger = Logger
			.getLogger(TDSComputationDAOIMPL.class);
	@Override
	public StaffEmployementVo getEmployeeDetails(String user,String currentUserId,String academic_year) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn=null;
		StaffEmployementVo list = new StaffEmployementVo();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_TEACHER_DETAILS_FOR_ADDING_TDS);
			
			pstmt.setString(1,currentUserId );
			pstmt.setString(2,academic_year);
			
			System.out.println(pstmt);
			DecimalFormat decimal = new DecimalFormat("#.00");
			rs=pstmt.executeQuery();
			int months=0,totalmonths=0,remainingmonths=0;
			while(rs.next()){
				list.setEmpid(rs.getString("TeacherID"));
				list.setTeacherId(rs.getString("registerId"));
				list.setName(rs.getString("FirstName")+" "+rs.getString("LastName"));
				list.setDoj(HelperClass.convertDatabaseToUI(rs.getString("dateofJoining")));
				list.setPannumber(rs.getString("pannumber"));
				list.setBasic(rs.getDouble("Basic"));
				list.setHra(rs.getDouble("Hra"));
				list.setAllowance(rs.getDouble("Convenience"));
				list.setDa(rs.getDouble("Da"));
				list.setPerfincentive(rs.getDouble("PerformanceIncentive"));
				list.setFoodallowance(rs.getDouble("FoodAllowance"));
				list.setSpecial(rs.getDouble("SpecialAllowance"));
				list.setOthers(rs.getDouble("OtherAllowance"));
				list.setChildedu(rs.getDouble("childEdu"));
				list.setArrears(rs.getDouble("arrears"));
				list.setReimburse(rs.getDouble("reimbursement"));
				list.setInternet(rs.getDouble("internetExpense"));
				list.setDriver(rs.getDouble("driverSalary"));
				list.setLeave(rs.getDouble("leaveEncash"));
				list.setMedical(rs.getDouble("medicalReimbursement"));
				list.setPfamount(rs.getDouble("pfEmployerAmount"));
				list.setTotalsalary(rs.getDouble("totalSalary"));
				list.setOtherIncome(rs.getDouble("incomeOtherThanSalary"));
				list.setIncomeOtherHousingLoanSelf(rs.getDouble("incomeOtherHousingLoanSelf"));
				list.setIncomeOtherHousingLoanLetout(rs.getDouble("incomeOtherHousingLoanLetout"));
				list.setOtherIncomeTotal(rs.getDouble("incomeOtherTotal"));
				list.setExemptHra(rs.getDouble("exemptHRA"));
				list.setExemptChildEdu(rs.getDouble("exemptChildEdu"));
				list.setExemptMedReimb(rs.getDouble("exemptMedReimb"));
				list.setExemptTransAllowance(rs.getDouble("exemptTransAllowance"));
				list.setExemptLTA(rs.getDouble("exemptLTA"));
				list.setExemptFoodCoupon(rs.getDouble("exemptFoodCoupon"));
				list.setExemptTelephoneReimb(rs.getDouble("exemptTelephoneReimb"));
				list.setExemptCarExpReimb(rs.getDouble("exemptCarExpReimb"));
				list.setExemptInternetExp(rs.getDouble("exemptInternetExp"));
				list.setExemptDriverSal(rs.getDouble("exemptDriverSal"));
				list.setExemptOtherExp(rs.getDouble("exemptOtherExp"));
				list.setExemptTotal(rs.getDouble("exemptTotal"));
				list.setSection80CEmpPF(rs.getDouble("section80CEmpPF"));
				list.setSection80CTutionFee(rs.getDouble("section80CTutionFee"));
				list.setSection80CFixedDeposit(rs.getDouble("section80CFixedDeposit"));
				list.setSection80CLIC(rs.getDouble("section80CLIC"));
				list.setSection80CMutualFund(rs.getDouble("section80CMutualFund"));
				list.setSection80CNationalSaveCerti(rs.getDouble("section80CNationalSaveCerti"));
				list.setSection80CAccruNSC(rs.getDouble("section80CAccruNSC"));
				list.setSection80CPublicPF(rs.getDouble("section80CPublicPF"));
				list.setSection80CRepayHousingLoan(rs.getDouble("section80CRepayHousingLoan"));
				list.setSection80CULIP(rs.getDouble("section80CULIP"));
				list.setSection80CSeniorCitizenSaving(rs.getDouble("section80CSeniorCitizenSaving"));
				list.setSection80CPostSaving(rs.getDouble("section80CPostSaving"));
				list.setSection80COther(rs.getDouble("section80COther"));
				list.setSection80CTotal(rs.getDouble("section80CTotal"));
				list.setSection80DMediClaim1(rs.getDouble("section80DMediClaim1"));
				list.setSection80DMediClaim2(rs.getDouble("section80DMediClaim2"));
				list.setSection80DMediClaim3(rs.getDouble("section80DMediClaim3"));
				list.setSection80DDBMedTreatment1(rs.getDouble("section80DDBMedTreatment1"));
				list.setSection80DDBMedTreatment2(rs.getDouble("section80DDBMedTreatment2"));
				list.setSection80EEduLoanInterest(rs.getDouble("section80EEduLoanInterest"));
				list.setSection80CCDNPS(rs.getDouble("section80CCDNPS"));
				list.setSection80UDeduction1(rs.getDouble("section80UDeduction1"));
				list.setSection80UDeduction2(rs.getDouble("section80UDeduction2"));
				list.setSection80CCGRajivEquitySaving(rs.getDouble("section80CCGRajivEquitySaving"));
				list.setSection80TTASavingBankInterest(rs.getDouble("section80TTASavingBankInterest"));
				list.setSection80DDMedTreatment1(rs.getDouble("section80DDMedTreatment1"));
				list.setSection80DDMedTreatment2(rs.getDouble("section80DDMedTreatment2"));
				list.setSection80D80UTotal(rs.getDouble("section80D80UTotal"));
				list.setTdstaxableincome(rs.getDouble("TDSTaxbleIncome"));
				list.setTdsctc(rs.getDouble("TDSCtc"));
				list.setTdspt(rs.getDouble("TDSPT"));
				list.setStandarddedu(rs.getDouble("TDSStandardDeductions"));
				list.setHrapercentage(rs.getInt("HRAPercentage"));
				list.setPt(rs.getDouble("Pt"));
				list.setTdspayabletax(Math.round(rs.getDouble("TDSTaxPayble")/12));
				totalmonths=rs.getInt("months")+1;
				
				if(totalmonths>12){
					totalmonths=12;
				}
				list.setMonth(totalmonths);
				//houseRentFromDate,houseRentToDate,houseRentCity,houseRentLandlordName,houseRentAddress,houseRentPANNo,houseRentDeclaredAmount
				if(rs.getString("houseRentFromDate") !=null){
					list.setHousefromdate(HelperClass.convertDatabaseToUI(rs.getString("houseRentFromDate")));
				}else{
					list.setHousefromdate("");
				}
				if(rs.getString("houseRentFromDate") !=null){
					list.setHousetodate(HelperClass.convertDatabaseToUI(rs.getString("houseRentToDate")));
				}else{
					list.setHousetodate("");
				}
				
				list.setHousecity(rs.getString("houseRentCity"));
				list.setLandlordname(rs.getString("houseRentLandlordName"));
				list.setHouseaddress(rs.getString("houseRentAddress"));
				list.setPancardno(rs.getString("houseRentPANNo"));
				list.setHouseamount(rs.getDouble("houseRentDeclaredAmount"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();

				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();

				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();

				}

			} catch (SQLException sqle) {
				logger.error(sqle.getMessage(),sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(),e1);
				e1.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public StaffEmployementVo getStaffMaximumLimitDetails(String academic_year, String location) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		Connection conn=null;
		StaffEmployementVo list = new StaffEmployementVo();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_STAFF_MAXIMUM_LIMIT);
			
			rs=pstmt.executeQuery();
		
			while(rs.next()){
				list.setIncomeOtherHousingLoanLetoutMax(rs.getDouble("incomeOtherHousingLoanLetout"));
				list.setIncomeOtherHousingLoanSelfMax(rs.getDouble("incomeOtherHousingLoanSelf"));
				list.setExemptMedReimbMax(rs.getDouble("exemptMedReimb"));
				list.setExemptTransAllowanceMax(rs.getDouble("exemptTransAllowance"));
				list.setSection80CMax(rs.getDouble("section80C"));
				list.setSection80DMediClaim1Max(rs.getDouble("section80DMediClaim1"));
				list.setSection80DMediClaim2Max(rs.getDouble("section80DMediClaim2"));
				list.setSection80DMediClaim3Max(rs.getDouble("section80DMediClaim3"));
				list.setSection80DDBMedTreatment1Max(rs.getDouble("section80DDBMedTreatment1"));
				list.setSection80DDBMedTreatment2Max(rs.getDouble("section80DDBMedTreatment2"));
				list.setSection80CCDNPSMax(rs.getDouble("section80CCDNPS"));
				list.setSection80UDeduction1Max(rs.getDouble("section80UDeduction1"));
				list.setSection80UDeduction2Max(rs.getDouble("section80UDeduction2"));
				list.setSection80CCGRajivEquitySavingMax(rs.getDouble("section80CCGRajivEquitySaving"));
				list.setSection80TTASavingBankInterestMax(rs.getDouble("section80TTASavingBankInterest"));
				list.setSection80DDMedTreatment1Max(rs.getDouble("section80DDMedTreatment1"));
				list.setSection80DDMedTreatment2Max(rs.getDouble("section80DDMedTreatment2"));
				list.setPfMaxAmount(rs.getDouble("pfAmount"));
				list.setPfPercentage(rs.getDouble("pfPercentage"));
				list.setEsiMaxAmount(rs.getDouble("esiAmount"));
				list.setEsiPercentage(rs.getDouble("esiPercentage"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();

				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();

				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();

				}

			} catch (SQLException sqle) {
				logger.error(sqle.getMessage(),sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(),e1);
				e1.printStackTrace();
			}
		}
		return list;
	}

}
