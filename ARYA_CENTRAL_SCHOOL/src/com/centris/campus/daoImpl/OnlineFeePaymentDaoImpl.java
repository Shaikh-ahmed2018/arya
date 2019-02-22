package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.OnlineFeePaymentDAO;
import com.centris.campus.util.FeeCollectionSqlUtils;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.OnlinePaymentVo;
import com.centris.campus.vo.StudentRegistrationVo;

public class OnlineFeePaymentDaoImpl implements OnlineFeePaymentDAO {
	private static final Logger logger = Logger
			.getLogger(StudentRegistrationDaoImpl.class);

	public List<OnlinePaymentVo> getStudentOnlineFeePaymentDetails(
			String userType, String userCode) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeePaymentDaoImpl : getStudentOnlineFeePaymentDetails  Starting");
		List<OnlinePaymentVo> list = new ArrayList<OnlinePaymentVo>();
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement("SELECT stg.amount,csc.specilization,csc.student_imgurl_var,csc.locationId,csc.classdetail_id_int,csc.student_rollno,cs.student_id_int,CONCAT(cs.student_fname_var,' ',cs.student_lname_var) studentName,CONCAT(cc.classdetails_name_var,'-',ccs.classsection_name_var) classdetails_name_var,cs.student_admissionno_var,stg.stage_name,stg.stage_id,rt.RouteCode,rt.RouteName FROM campus_student_classdetails csc  JOIN campus_student cs ON csc.student_id_int=cs.student_id_int JOIN campus_parentchildrelation cp ON csc.student_id_int=cp.stu_addmissionNo  JOIN campus_classdetail cc ON csc.classdetail_id_int=cc.classdetail_id_int AND cc.locationId=csc.locationId JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN `campus_student_transportdetails` ctr ON csc.student_id_int=ctr.student_id_int LEFT JOIN `campus_fee_stage` stg ON ctr.StageId=stg.stage_id LEFT JOIN `transport_route` rt ON ctr.route=rt.RouteCode WHERE  cp.parentid=? AND csc.fms_acadamicyear_id_int=?");
			pst.setString(1, userCode);
			pst.setString(2, HelperClass.getCurrentYearID());
			
			System.out.println(pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				
				List<OnlinePaymentVo> termlist = new ArrayList<OnlinePaymentVo>();
				List<OnlinePaymentVo> transporttermlist = new ArrayList<OnlinePaymentVo>();
				
				OnlinePaymentVo feeVo = new OnlinePaymentVo();
				feeVo.setAdmissionNo(rs.getString("student_admissionno_var"));
				feeVo.setStudentId(rs.getString("student_id_int"));
				feeVo.setStudentRollNo(rs.getString("student_rollno"));
				feeVo.setStudentName(rs.getString("studentName"));
				feeVo.setClassDetail(rs.getString("classdetails_name_var"));
				feeVo.setImgurl(rs.getString("student_imgurl_var"));
				feeVo.setStragename(rs.getString("stage_name"));
				feeVo.setRoutename(rs.getString("RouteName"));
				
				
				PreparedStatement pstmt=conn.prepareStatement("SELECT cfs.`feeSettingcode`,cfs.TermCode,cft.termname,TIMESTAMPDIFF(DAY,cft.startdate,NOW( )) lateDay FROM `campus_fee_setup` cfs JOIN `campus_fee_termdetails` cft ON cfs.TermCode=cft.termid WHERE TermCode NOT IN(SELECT termcode FROM `campus_fee_collection_details` WHERE accYear=? AND admissionNo=?) AND cfs.ClassCode=? AND cfs.locationId=? AND cfs.specialization=? AND cfs.AccyearCode=? ORDER BY LENGTH(cfs.TermCode),cfs.TermCode");
				pstmt.setString(1, HelperClass.getCurrentYearID());
				pstmt.setString(2, rs.getString("student_id_int"));
				pstmt.setString(3, rs.getString("classdetail_id_int"));
				pstmt.setString(4, rs.getString("locationId"));
				pstmt.setString(5, rs.getString("specilization"));
				pstmt.setString(6, HelperClass.getCurrentYearID());
				ResultSet rst=pstmt.executeQuery();
				while(rst.next()) {
					OnlinePaymentVo feeVo1 = new OnlinePaymentVo();
					
					
					feeVo1.setFeeCode(rst.getString("feeSettingcode"));
					feeVo1.setTermid(rst.getString("TermCode"));
					feeVo1.setTerm(rst.getString("termname"));
					feeVo1.setClassId(rs.getString("classdetail_id_int"));
					feeVo1.setSpec(rs.getString("specilization"));
					
					int lateDay=rst.getInt("lateDay");
					double fineAmount=0.0;
					double feeAmount=0.0;
					
					
					int fiCount=0;
					PreparedStatement finecount=conn.prepareStatement("SELECT COUNT(*) FROM campus_fineconfiguration where classId=? and termid=?");
					finecount.setString(1, rs.getString("classdetail_id_int"));
					finecount.setString(2, rst.getString("TermCode"));
					ResultSet countRs=finecount.executeQuery();
					while(countRs.next()){
						fiCount=countRs.getInt(1);
					}
					if(fiCount>0){
						PreparedStatement finep=conn.prepareStatement("SELECT * FROM campus_fineconfiguration where classId=? and termid=? ORDER BY date");
						finep.setString(1, rs.getString("classdetail_id_int"));
						finep.setString(2, rst.getString("TermCode"));
						ResultSet finers=finep.executeQuery();
						while(finers.next()){
							
							
							String isApp=finers.getString("IsApplicable");
							if(isApp.equalsIgnoreCase("Y")){
								if(HelperClass.getCurrentSqlDate().compareTo(finers.getDate("date")) > 0){
									fineAmount=finers.getDouble("amount");
									System.out.println("Table"+fineAmount);
								}
								
								
							}
							else{
								fineAmount=0.0;
							}
						}
					
					
					try {

						if (finers != null && (!finers.isClosed())) {
							finers.close();
						}
						if (finep != null && (!finep.isClosed())) {
							finep.close();
						}
						
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					}
					else{
						fineAmount=0.0;
					}
					PreparedStatement feeAmt=conn.prepareStatement("SELECT SUM(feeAmount) feeAmount FROM `campus_fee_setupdetails` WHERE feeSettingCode=?");
					feeAmt.setString(1, rst.getString("feeSettingcode"));
					ResultSet fst=feeAmt.executeQuery();
					if(fst.next()) {
						feeAmount=fst.getDouble("feeAmount");
					}
					
					feeVo1.setDueFee(feeAmount+fineAmount);
					feeVo1.setFeeAmt(feeAmount);
					feeVo1.setFineAmt(fineAmount);
					termlist.add(feeVo1);
				
					
					
					try {

						if (fst != null && (!fst.isClosed())) {
							fst.close();
						}
						if (feeAmt != null && (!feeAmt.isClosed())) {
							feeAmt.close();
						}
						if (countRs != null && (!countRs.isClosed())) {
							countRs.close();
						}
						if (finecount != null && (!finecount.isClosed())) {
							finecount.close();
						}
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}
				
				
				if(rs.getString("stage_name") !=null) {
					PreparedStatement pstmt1=conn.prepareStatement("SELECT termname,termid,TIMESTAMPDIFF(MONTH,startdate,enddate) month FROM   `campus_fee_transport_termdetails` WHERE termid NOT IN(SELECT termcode FROM `campus_tranport_fee_collection_details` WHERE accYear=? AND admissionNo=?)  AND locationId=? ORDER BY LENGTH(termid),termid");
					pstmt1.setString(1, HelperClass.getCurrentYearID());
					pstmt1.setString(2, rs.getString("student_id_int"));
					pstmt1.setString(3, rs.getString("locationId"));
					ResultSet rst1=pstmt1.executeQuery();
					while(rst1.next()) {
						OnlinePaymentVo feeVo1 = new OnlinePaymentVo();
						feeVo1.setFeeCode("transport");
						feeVo1.setTermid(rst1.getString("termid"));
						feeVo1.setTerm(rst1.getString("termname"));
						feeVo1.setClassId(rs.getString("classdetail_id_int"));
						feeVo1.setSpec(rs.getString("specilization"));
						feeVo1.setDueFee(rs.getDouble("amount")*(rst1.getInt("month")+1));
						feeVo1.setFeeAmt(rs.getDouble("amount")*(rst1.getInt("month")+1));
						feeVo1.setFineAmt(0.0);
						transporttermlist.add(feeVo1);
					}
					
					rst1.close();	
					pstmt1.close();
				}
				
				feeVo.setTermlist(termlist);
				feeVo.setTransporttermlist(transporttermlist);
				list.add(feeVo);
				rst.close();
				pstmt.close();	
				
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeePaymentDaoImpl : getStudentOnlineFeePaymentDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public String onlinefeetransactionId(String studentId, String feeCode, String termcode, String bank, String tranID, String grandtotal, String tokenid, String fineAmt) {
		
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeePaymentDaoImpl : onlinefeetransactionId  Starting");
		String status ="";
		PreparedStatement pst = null;
		int count = 0;
		Connection conn = null;
		
		
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement("INSERT INTO online_transactionid_table (transactionID,sudentID,feeCode,termId,bank,tokenid,totalamount,fineAmt) VALUES(?,?,?,?,?,?,?,?)");
			pst.setString(1, tranID);
			pst.setString(2, studentId);
			pst.setString(3, feeCode);
			pst.setString(4, termcode);
			pst.setString(5, bank);
			pst.setString(6, tokenid);
			pst.setDouble(7, Double.parseDouble(grandtotal));
			pst.setString(8, fineAmt);
			count=pst.executeUpdate();
			if(count>0) {
				status=tranID;
			}
			else {
				status="false";
			}
			
			
		} catch (Exception e) {
			status="false";
		}
		
		finally {
			try {

				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeePaymentDaoImpl : onlinefeetransactionId  Ending");
		return status;
	}

	public List<FeeCollectionVo> saveonlinefeetransactionId(String transactionId, String userCode) {
		
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in OnlineFeePaymentDaoImpl : getonlinefeetokenId  Starting");

		List<FeeCollectionVo> status =new ArrayList<FeeCollectionVo>();
		PreparedStatement pst = null;
		ResultSet rs=null;
		
		PreparedStatement pstmt1=null;
		ResultSet rs1=null;
		PreparedStatement ps_insertPlan=null;
		PreparedStatement ps_insertPlan1=null;
		PreparedStatement ps_insertReciept=null;
		PreparedStatement ps1= null;
		PreparedStatement ps2= null;
		PreparedStatement ps3= null;
		PreparedStatement getReiptPs=null;
		PreparedStatement transportdetails = null;
		ResultSet getReiptRs=null;
		ResultSet rs3=null;
		int recieptNo=0;
		
		int countTrans=0;
		int result=0;
		int count_insertReciept=0;
		
	int	count=0;
		Connection conn = null;
		
		
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			String studentId="";
			String feecode="";
			String termid="";
			String transactionID="";
			String bank="";
			double totalamount=0.0;
			String fineSamt="";
			String transactionStatus="";
			
			pst = conn.prepareStatement("SELECT * FROM online_transactionid_table WHERE transactionID=?");
			pst.setString(1, transactionId);
			rs=pst.executeQuery();
			if(rs.next()) {
				studentId=rs.getString("sudentID");
				feecode=rs.getString("feeCode");
				termid=rs.getString("termId");
				bank=rs.getString("bank");
				transactionID=rs.getString("transactionID");
				totalamount=rs.getDouble("totalamount");
				fineSamt=rs.getString("fineAmt");
				transactionStatus=rs.getString("status");
			}
			
			for(int f=0;f<feecode.split(",").length;f++) {
			
				FeeCollectionVo vo=new FeeCollectionVo();
				vo.setStudentid(studentId);
				vo.setTermid(termid.split(",")[f]);
				vo.setTot_actual_amt(totalamount);
				vo.setFineAmount(Double.parseDouble(fineSamt.split(",")[f]));
				if(feecode.split(",")[f].equalsIgnoreCase("transport")) {
					vo.setStatus("transport");
					double transportamt=0.0;
					
					String startdate="";
					int monthh=0;
					ps3=conn.prepareStatement("SELECT startdate,TIMESTAMPDIFF(MONTH,startdate,enddate)+1 monthh FROM `campus_fee_transport_termdetails` WHERE termid=?");
					ps3.setString(1,termid.split(",")[f]);
					rs3=ps3.executeQuery();
					while(rs3.next()){
						monthh=rs3.getInt("monthh");
						startdate=rs3.getString("startdate");
					}
					int startMonth=Integer.parseInt(startdate.split("-")[1]);
					List<String> monthList=new ArrayList<String>();
					for(int k=startMonth;k<startMonth+monthh;k++) {
						int j=k;
						if(j>12) {
							j=j-12;
						}
						String monthValue=j+"";
						if(j<10) {
							monthValue="0"+j;
						}
						monthList.add(HelperClass.getMonthFullName(monthValue));
					}
					
					
					
					pstmt1=conn.prepareStatement("SELECT amount FROM `campus_fee_stage` WHERE stage_id IN(SELECT StageId FROM `campus_student_transportdetails` WHERE student_id_int=? AND fms_acadamicyear_id_int=?)");
					pstmt1.setString(1, studentId);
					pstmt1.setString(2,HelperClass.getCurrentYearID());
					rs1=pstmt1.executeQuery();
					while(rs1.next()) {
						transportamt=rs1.getDouble("amount");
					}
					
					double transporttotamt=transportamt*monthh;
					 getReiptPs=conn.prepareStatement("SELECT CASE WHEN  MAX(reciept_no) IS NULL THEN '1001' ELSE MAX(reciept_no) END reciept_no FROM campus_tranport_fee_collection_details");
					 getReiptRs=getReiptPs.executeQuery();
					while(getReiptRs.next()){
						recieptNo=getReiptRs.getInt("reciept_no")+1;
					}
					transportdetails=conn.prepareStatement("INSERT INTO campus_transport_fees_payments(receiptno,totalamt,paidAmount,balance,advance) VALUES(?,?,?,?,?)");
					transportdetails.setInt(1, recieptNo);
					transportdetails.setDouble(2,transporttotamt);
					transportdetails.setDouble(3, transporttotamt);
					transportdetails.setDouble(4, 0);
					transportdetails.setDouble(5, 0);
					 countTrans=transportdetails.executeUpdate();
					
					 
					 for(int i=0;i<monthList.size();i++) {
							
							ps_insertPlan = conn.prepareStatement("INSERT INTO campus_tranport_fee_collection_details(admissionNo,accYear,termcode,is_paid,totalamount,amount_paid,balance_amount,paidDate,createdby,createdtime,MonthName,reciept_no,modeofpayment,bankname,dd_cheque_date,dd_cheque_no) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
							ps_insertPlan.setString(1,studentId);
							ps_insertPlan.setString(2,HelperClass.getCurrentYearID());
							ps_insertPlan.setString(3,termid.split(",")[f]);
							ps_insertPlan.setString(4,"Y");
							ps_insertPlan.setDouble(5,transportamt);
							ps_insertPlan.setDouble(6,transportamt);
							ps_insertPlan.setDouble(7,0);	
							ps_insertPlan.setDate(8,HelperClass.getCurrentSqlDate());
							ps_insertPlan.setString(9,userCode);
							ps_insertPlan.setTimestamp(10,HelperClass.getCurrentTimestamp());
							ps_insertPlan.setString(11, monthList.get(i));
							ps_insertPlan.setInt(12, recieptNo);
							ps_insertPlan.setString(13, "ONLINE");
							ps_insertPlan.setString(14, bank);
							ps_insertPlan.setString(15, "");
							ps_insertPlan.setString(16, transactionID);
							System.out.println("ps_insertPlan:::::"+ps_insertPlan);
							
							count=ps_insertPlan.executeUpdate();
						}
					
					vo.setRefrecieptNo(Integer.toString(recieptNo));
					
				}
				else {
					vo.setStatus("schoolfee");
					double feeWithoutFine=0.0;
					List<String> feetypeCode=new ArrayList<String>();
					List<Double> feetypeAmount=new ArrayList<Double>();
					pstmt1=conn.prepareStatement("SELECT * FROM `campus_fee_setupdetails` WHERE feeSettingCode=?");
					pstmt1.setString(1,feecode.split(",")[f]);
					rs1=pstmt1.executeQuery();
					while(rs1.next()) {
						feetypeCode.add(rs1.getString("feecode"));
						feetypeAmount.add(rs1.getDouble("feeAmount"));
					}
					
					for(int k=0;k<feetypeAmount.size();k++) {
						feeWithoutFine=feeWithoutFine+feetypeAmount.get(k);
					}
					double fineAmt=Double.parseDouble(fineSamt.split(",")[f]);
					
					String primaryKey=IDGenerator.getPrimaryKeyID("campus_fee_collection");
					
					ps_insertPlan = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION);
					
					ps_insertPlan.setString(1,primaryKey);
					ps_insertPlan.setString(2,studentId);
					ps_insertPlan.setString(3,HelperClass.getCurrentYearID());
					ps_insertPlan.setString(4,termid.split(",")[f]);
					ps_insertPlan.setString(5,"Y");
				
					
					ps_insertPlan.setDouble(6,feeWithoutFine+fineAmt);
					ps_insertPlan.setDouble(7,feeWithoutFine);
					ps_insertPlan.setDouble(8,0.0);
					ps_insertPlan.setDouble(9,0.0);
					ps_insertPlan.setDate(10,HelperClass.getCurrentSqlDate());
					ps_insertPlan.setString(11,userCode);
					ps_insertPlan.setTimestamp(12,HelperClass.getCurrentTimestamp());
					ps_insertPlan.setDouble(13,feeWithoutFine+fineAmt);
					ps_insertPlan.setDouble(14,0.0);
					ps_insertPlan.setDouble(15,fineAmt);
					ps_insertPlan.setString(16,transactionID);
					ps_insertPlan.setString(17,"ONLINE");
					ps_insertPlan.setString(18,"");
					ps_insertPlan.setString(19,bank);
					ps_insertPlan.setDouble(20,0.0);
					ps_insertPlan.setDouble(21,feeWithoutFine+fineAmt);
					//System.out.println("ps_insertPlan:::::"+ps_insertPlan);
					
					count=ps_insertPlan.executeUpdate();
					
					
					ps2 = conn.prepareStatement("insert into campus_fee_indetail (FeeInDetailedCode,admissionNo,accYear,term_id,totalamount,actualamount,balance_amount,amount_paid,conc_amount,conc_percent,paidDate,createdby,createdtime)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
					
					ps2.setString(1,IDGenerator.getPrimaryKeyID("campus_fee_indetail"));
					ps2.setString(2,studentId);
					ps2.setString(3,HelperClass.getCurrentYearID());
					ps2.setString(4,termid.split(",")[f]);
					ps2.setDouble(5,feeWithoutFine+fineAmt);
					ps2.setDouble(6,feeWithoutFine+fineAmt);
					ps2.setDouble(7,0.0);
					ps2.setDouble(8,feeWithoutFine+fineAmt);
					ps2.setDouble(9,0.0);
					ps2.setDouble(10,0.0);
					ps2.setDate(11,HelperClass.getCurrentSqlDate());
					ps2.setString(12,userCode);
					ps2.setTimestamp(13,HelperClass.getCurrentTimestamp());
					ps2.executeUpdate();
					
					
					if(count>0){
						
						ps1 = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION_DETAILS);
						
						for(int i=0;i<feetypeCode.size();i++){
							
							ps1.setString(1,primaryKey);
							ps1.setString(2,feetypeCode.get(i));
							ps1.setDouble(3,feetypeAmount.get(i));
							ps1.setDouble(4,feetypeAmount.get(i));
							ps1.setDouble(5,0.0);
							ps1.setDouble(6,0);
							ps1.setDouble(7,0.0);
							
							result=ps1.executeUpdate();
						}
						
						ps_insertReciept = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_RECIEPT);
						
						for(int i=0;i<feetypeCode.size();i++){
							
							ps_insertReciept.setString(1,primaryKey);
							ps_insertReciept.setString(2,feetypeCode.get(i));
							ps_insertReciept.setDouble(3,feetypeAmount.get(i));
							ps_insertReciept.setDouble(4,feetypeAmount.get(i));
							ps_insertReciept.setDouble(5,0.0);
							ps_insertReciept.setDouble(6,0);
							ps_insertReciept.setDouble(7,0.0);
							//System.out.println("ps1:::"+ps1);
							count_insertReciept=ps_insertReciept.executeUpdate();
						}
				
					}
					if(result>0){
						
						
						
						
						PreparedStatement ps_insert_detail=conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION_D);
						
						ps_insert_detail.setString(1,studentId);
						ps_insert_detail.setString(2,HelperClass.getCurrentYearID());
						ps_insert_detail.setString(3,termid.split(",")[f]);
						ps_insert_detail.setString(4,"Y");
						
						
						ps_insert_detail.setString(5,Double.toString(feeWithoutFine+fineAmt));
						ps_insert_detail.setDouble(6,feeWithoutFine+fineAmt);
						ps_insert_detail.setDouble(7,0.0);
						ps_insert_detail.setDouble(8,0);
						ps_insert_detail.setDate(9,HelperClass.getCurrentSqlDate());
						ps_insert_detail.setString(10,userCode);
						ps_insert_detail.setTimestamp(11,HelperClass.getCurrentTimestamp());
						ps_insert_detail.setDouble(12,feeWithoutFine+fineAmt);
						ps_insert_detail.setDouble(13,0.0);
						
						//System.out.println("ps_insertPlan:::::"+ps_insertPlan);
						
						int counts=ps_insert_detail.executeUpdate();
						
						
					}
					
					
				}
				status.add(vo);
			}
			
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs3 != null && (!rs3.isClosed())) {
					rs3.close();
				}
				if (getReiptRs != null && (!getReiptRs.isClosed())) {
					getReiptRs.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (ps_insertPlan != null && (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
				}
				if (ps_insertPlan1 != null && (!ps_insertPlan1.isClosed())) {
					ps_insertPlan1.close();
				}
				if (ps_insertReciept != null && (!ps_insertReciept.isClosed())) {
					ps_insertReciept.close();
				}
				if (getReiptPs != null && (!getReiptPs.isClosed())) {
					getReiptPs.close();
				}
				if (transportdetails != null && (!transportdetails.isClosed())) {
					transportdetails.close();
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
		JLogger.log(0, JDate.getTimeString(new Date())	+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in OnlineFeePaymentDaoImpl : getonlinefeetokenId  Ending");
		return status;
	}

	public String getonlinefeetokenId(String tranID) {logger.setLevel(Level.DEBUG);
	
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in OnlineFeePaymentDaoImpl : getonlinefeetokenId  Starting");
	
	
	String status ="";
	PreparedStatement pst = null;
	ResultSet rs=null;
	Connection conn = null;
	
	
	
	try {
		conn = JDBCConnection.getSeparateConnection();
		pst = conn.prepareStatement("SELECT tokenid FROM online_transactionid_table WHERE transactionID=? AND status='pending'");
		pst.setString(1, tranID);
		rs=pst.executeQuery();
		System.out.println("hjsagdhjsa"+pst);
		if(rs.next()) {
			status=rs.getString("tokenid");
		}
		else {
			status="false";
		}
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				pst.close();
			}
			if (rs != null && (!rs.isClosed())) {
				rs.close();
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
	JLogger.log(0, JDate.getTimeString(new Date())	+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())	+ " Control in OnlineFeePaymentDaoImpl : getonlinefeetokenId  Ending");
	return status;
	}

	public String updateOnlineTable(String tokenid,String txnstatuu, String trackID) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeePaymentDaoImpl : updateOnlineTable  Starting");
		String status ="";
		PreparedStatement pst = null;
		int count = 0;
		Connection conn = null;
		int councheck=0;
		
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			PreparedStatement pscheck=conn.prepareStatement("SELECT COUNT(*) FROM online_transactionid_table WHERE status='SUCCESS' AND tokenid=?");
			pscheck.setString(1,tokenid);
			ResultSet rschk=pscheck.executeQuery();
			while(rschk.next()) {
				councheck=rschk.getInt(1);
			}
			if(councheck==0) {
				pst = conn.prepareStatement("UPDATE online_transactionid_table SET trackId=?,status=? WHERE tokenid=?");
				pst.setString(1, trackID);
				pst.setString(2, txnstatuu);
				pst.setString(3, tokenid);
				System.out.println("update=="+pst);
				count=pst.executeUpdate();
				if(count>0) {
					status="success";
				}
				else {
					status="false";
				}
			}
			else {
				status="false";
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {

				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeePaymentDaoImpl : updateOnlineTable  Ending");
		return status;
	}
	public ArrayList<FeeNameVo> getFeePaidDetails(String studentId, String termId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeePaymentDaoImpl: getFeePaidDetails : Starting");
		
		
		
		PreparedStatement ps_feelist=null;
		ResultSet rs_feelist=null;
		
		Connection conn = null;
		ArrayList<FeeNameVo> feeCollectionList=new ArrayList<FeeNameVo>();
		int count=0;
		PreparedStatement ps1= null;
		ResultSet rs1=null;
		String feeCollectionCode=null;
		try {
			
			String accyearId=HelperClass.getCurrentYearID();
				conn = JDBCConnection.getSeparateConnection();
				ps_feelist=conn.prepareStatement(FeeCollectionSqlUtils.GET_FEEPAID_AMOUNT);
				ps_feelist.setString(1, studentId);
				ps_feelist.setString(2, accyearId);
				ps_feelist.setString(3, termId);
				
				System.out.println("fee name list :: "+ps_feelist);
				rs_feelist=ps_feelist.executeQuery();
				
				while(rs_feelist.next()){
					feeCollectionCode=rs_feelist.getString("feeCollectionCode");
					
				}
				ps1=conn.prepareStatement("SELECT cfr.feeCode,cfr.feeAmount,cfm.FeeName FROM campus_fee_reciept cfr JOIN  campus_fee_master cfm ON cfr.feeCode=cfm.FeeCode WHERE cfr.feeCollectionCode=?");
				ps1.setString(1, feeCollectionCode);
				rs1=ps1.executeQuery();
				while(rs1.next()){
				count++;
				FeeNameVo feeNameVo=new FeeNameVo();
				
				
				feeNameVo.setSno(count);
				feeNameVo.setFeecode(rs1.getString("feeCode"));
				feeNameVo.setFeename(rs1.getString("FeeName"));
				
				double actualamt=rs1.getDouble("feeAmount");
				feeNameVo.setActualAmt(actualamt);
				feeCollectionList.add(feeNameVo);
				}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs_feelist != null&& (!rs_feelist.isClosed())) {
					rs_feelist.close();
				}
				if (ps_feelist != null&& (!ps_feelist.isClosed())) {
					ps_feelist.close();
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
				+ " Control in OnlineFeePaymentDaoImpl: getFeePaidDetails: Ending");
		
		return feeCollectionList;
	}
	public String transportFeepaid(String receipt) {
		
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeePaymentDaoImpl: transportFeepaid : Starting");
		String paid="";
		PreparedStatement ps_feelist=null;
		ResultSet rs_feelist=null;
		
		Connection conn = null;
		
		
		try {
			
				conn = JDBCConnection.getSeparateConnection();
				ps_feelist=conn.prepareStatement("SELECT paidAmount FROM `campus_transport_fees_payments` WHERE receiptno=?");
				ps_feelist.setString(1, receipt);
				
				rs_feelist=ps_feelist.executeQuery();
				
				while(rs_feelist.next()){
					paid=rs_feelist.getString("paidAmount");
					
				}
				
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs_feelist != null&& (!rs_feelist.isClosed())) {
					rs_feelist.close();
				}
				if (ps_feelist != null&& (!ps_feelist.isClosed())) {
					ps_feelist.close();
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
		+ " Control in OnlineFeePaymentDaoImpl: transportFeepaid: Ending");
		return paid;
	}

}
