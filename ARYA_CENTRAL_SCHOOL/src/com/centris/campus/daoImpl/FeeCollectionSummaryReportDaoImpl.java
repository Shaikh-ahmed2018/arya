package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.centris.campus.dao.FeeCollectionSummaryReportDao;
import com.centris.campus.forms.FeeCollectionSummaryReportForm;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.vo.FeeReportDetailsVo;

public class FeeCollectionSummaryReportDaoImpl implements FeeCollectionSummaryReportDao{
	
	private static final Logger logger = Logger
			.getLogger(FeeCollectionSummaryReportDaoImpl.class);
	
	public ArrayList<FeeReportDetailsVo> getClassFeeSummaryReport(FeeCollectionSummaryReportForm feeForm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionSummaryReportDaoImpl : getClassFeeSummaryReport Starting");

		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rst = null;
		ResultSet rst1 = null;
		Connection conn = null;
		
		PreparedStatement ps_section=null;
		ResultSet rs_section=null;
		
		PreparedStatement ps_paidfee=null;
		ResultSet rs_paidfee=null;
		
		PreparedStatement ps_stdCount=null;
		ResultSet rs_stdcount=null;

		ArrayList<FeeReportDetailsVo> termFeeDetails = new ArrayList<FeeReportDetailsVo>();
		
		int count=0;

		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			String accId = feeForm.getAccyear().trim();
			String classId = feeForm.getClassname().trim();
			String secId = feeForm.getSection().trim();
			String termId = feeForm.getTerm().trim();
			
			ArrayList<String> sectionlist=new ArrayList<String>();
			
			if(secId.equalsIgnoreCase("all")){
				
				ps_section=conn.prepareStatement(ReportsMenuSqlConstants.GET_TOT_SECTIONS);
				ps_section.setString(1, classId);
				
				System.out.println("getsections :: "+ps_section);
				
				rs_section=ps_section.executeQuery();
				
				while(rs_section.next()){
					
					
					sectionlist.add(rs_section.getString("classsection_id_int"));
				}
				
			}else{
				
				sectionlist.add(secId);
			}

			
		for(int i=0;i<sectionlist.size();i++){
			
			count++;
			
			FeeReportDetailsVo feereportvo=new FeeReportDetailsVo();
			
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_TOT_TRANSPORTFEE);
			pstmt.setString(1, classId);
			pstmt.setString(2, sectionlist.get(i));
			pstmt.setString(3, accId);
			pstmt.setString(4, termId);
			
			System.out.println("get transport fee :: "+pstmt);
			
			rst=pstmt.executeQuery();
			
			double tot_transport_amt=0;
		
			while (rst.next()) {
				
				tot_transport_amt=rst.getDouble("transportfee");
				
				
			}
			
			ps_paidfee = conn.prepareStatement(ReportsMenuSqlConstants.GET_TOT_PAID_FEE);
			ps_paidfee.setString(1, classId);
			ps_paidfee.setString(2, sectionlist.get(i));
			ps_paidfee.setString(3, accId);
			ps_paidfee.setString(4, termId);
			
			System.out.println("get paid fee :: "+ps_paidfee);
			
			rs_paidfee=ps_paidfee.executeQuery();
			
			double tot_paid_fee=0;
		
			while (rs_paidfee.next()) {
				
				tot_paid_fee=rs_paidfee.getDouble("paidfee");
				
			}
			
			ps_stdCount=conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENTCOUNT);
			ps_stdCount.setString(1, classId);
			ps_stdCount.setString(2, sectionlist.get(i));
			
			System.out.println("get student count  :: "+ps_stdCount);
			
			rs_stdcount=ps_stdCount.executeQuery();
			
			int studentCount=0;
			
			while(rs_stdcount.next()){
				
				studentCount=rs_stdcount.getInt("studentcount");
			}
			
			pstmt1 = conn.prepareStatement(ReportsMenuSqlConstants.GET_TOT_CLASSFEE);
			pstmt1.setString(1, classId);
			pstmt1.setString(2, sectionlist.get(i));
			pstmt1.setString(3, accId);
			pstmt1.setString(4, termId);
			
			System.out.println("get tot class fee  :: "+pstmt1);
			
			rst1=pstmt1.executeQuery();
			
			while (rst1.next()) {
				
				feereportvo.setSno(count);
				feereportvo.setClassName(rst1.getString("ClassName"));
				feereportvo.setStudTotal(studentCount);
				feereportvo.setActualAmount(Math.round(rst1.getDouble("ActualAmount")+tot_transport_amt));
				feereportvo.setPaidAmount(Math.round(tot_paid_fee));
				feereportvo.setBalanceAmount(Math.round((rst1.getDouble("ActualAmount")+tot_transport_amt)-tot_paid_fee));
			
				termFeeDetails.add(feereportvo);
			}
			
		}
				

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();

		} finally {

			try {
				if (rst1 != null && !rst1.isClosed()) {
					rst1.close();
				}
				if (rst != null && !rst.isClosed()) {
					rst.close();
				}
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionSummaryReportDaoImpl : getClassFeeSummaryReport Ending");
		
		JSONArray array=new JSONArray();
		array.put(termFeeDetails);
		
		System.out.println("array ::: "+array);
		
		return termFeeDetails;
	}
	
	public static void main(String[] args) {
		
		FeeCollectionSummaryReportForm form=new FeeCollectionSummaryReportForm();
		form.setAccyear("ACY1");
		form.setClassname("CCD10");
		form.setSection("all");
		form.setTerm("TRM1");
		
		new FeeCollectionSummaryReportDaoImpl().getClassFeeSummaryReport(form);
		
	}

}
