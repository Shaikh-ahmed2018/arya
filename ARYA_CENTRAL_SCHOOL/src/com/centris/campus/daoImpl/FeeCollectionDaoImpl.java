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
import org.json.JSONArray;

import com.centris.campus.admin.SMSThread;
import com.centris.campus.dao.FeeCollectionDao;
import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.util.FeeCollectionSqlUtils;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SmsUtilsConstants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.ClassFeeSetupVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.StudentConcessionVo;
import com.centris.campus.vo.StudentRegistrationVo;

public class FeeCollectionDaoImpl implements FeeCollectionDao{
	
	private static final Logger logger = Logger.getLogger(FeeCollectionDaoImpl.class);

	@Override
	public ArrayList<FeeNameVo> getFeeCollectionDetails(String FeeDetails) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in FeeCollectionDaoImpl: getFeeCollectionDetails : Starting");
			
			String studentId=FeeDetails.split(",")[0];
			String accyearId=FeeDetails.split(",")[1];
			String classId=FeeDetails.split(",")[2];
			String termId=FeeDetails.split(",")[3];
			String specialization=FeeDetails.split(",")[4];
			
			PreparedStatement ps_feelist=null;
			ResultSet rs_feelist=null;
			
			Connection conn = null;
			ArrayList<FeeNameVo> feeCollectionList=new ArrayList<FeeNameVo>();
			int count=0;
			PreparedStatement ps1= null;
			ResultSet rs1=null;
			
			try {
				
				conn = JDBCConnection.getSeparateGodaddyConnection();
					
					ps_feelist=conn.prepareStatement(FeeCollectionSqlUtils.GET_FEECOLLECTION_AMOUNT);
					ps_feelist.setString(1, classId);
					ps_feelist.setString(2, accyearId);
					ps_feelist.setString(3, termId);
					ps_feelist.setString(4, specialization);
					
					System.out.println("fee name list :: "+ps_feelist);
					rs_feelist=ps_feelist.executeQuery();
				
					
					while(rs_feelist.next()){
						count++;
						FeeNameVo feeNameVo=new FeeNameVo();
						
						
						feeNameVo.setSno(count);
						feeNameVo.setFeecode(rs_feelist.getString("FeeCode"));
						feeNameVo.setFeename(rs_feelist.getString("FeeName"));
						
						double actualamt=rs_feelist.getDouble("feeAmount");
						
						
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
					+ " Control in FeeCollectionDaoImpl: getFeeCollectionDetails: Ending");
			
			return feeCollectionList;
		}

	public FeeCollectionVo getFeeCollectionAmount(String feecollectioncode) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in FeeCollectionDaoImpl: getFeeCollectionAmount : Starting");
			PreparedStatement ps_feeInformation=null;
			ResultSet rs_getfeeInformation=null;
			PreparedStatement ps_insertPlan = null;
			ResultSet rs=null;
			Connection conn = null;
			int count=0;
			
			PreparedStatement ps_collection_count= null;
			ResultSet rs_collection_count=null;
			PreparedStatement ps_feelist= null;
			ResultSet rs_feelist=null;

			int lateDay=0;
			String paymode = null;
			String ddno = null;
			String dddate =null;
			double fineAmount=0.00;
			
			FeeCollectionVo collectionVo=new FeeCollectionVo();
			ArrayList<FeeNameVo> feeNameList=new ArrayList<FeeNameVo>();
			try {
				
				String[] feeDatails=feecollectioncode.split(",");
				
				String addmissionno=feeDatails[0];
				
				String accyearId=feeDatails[1];
				String locationId=feeDatails[2];
				String termId=null;
				String classId=null;
				String specCode=null;
				String feeSettingcode=null;
				String termname=null;
				double advanceCarry=0.0;
				double duesCarry=0.0;
				String prevTermId="";
				conn = JDBCConnection.getSeparateGodaddyConnection();
			
				ps_insertPlan = conn.prepareStatement(FeeCollectionSqlUtils.GET_FEECOLLECTION_DEDDER);
				ps_insertPlan.setString(1,addmissionno);
				ps_insertPlan.setString(2,accyearId);
				ps_insertPlan.setString(3,locationId);
				System.out.println("ps_insertPlan"+ps_insertPlan);
				rs=ps_insertPlan.executeQuery();
				while(rs.next()){
					String concessionType="no";
					double concessionPercent=0.0;
					PreparedStatement sclpstmt=conn.prepareStatement("SELECT concessionType FROM campus_scholorship WHERE admissionNo=? AND academic_year=?");
					sclpstmt.setString(1, rs.getString("student_admissionno_var"));
					sclpstmt.setString(2, accyearId);
					
					ResultSet sclRs=sclpstmt.executeQuery();
					while(sclRs.next()){
						concessionType=sclRs.getString("concessionType");
					}
					collectionVo.setStudentid(addmissionno);
					collectionVo.setAddmissionno(rs.getString("student_admissionno_var"));
					collectionVo.setStudentname(rs.getString("studentname"));
					
					collectionVo.setClassname(rs.getString("classdetails_name_var"));
					specCode=rs.getString("specilization");
					classId=rs.getString("classdetail_id_int");
					collectionVo.setClassId(classId);
					collectionVo.setSpecialization(specCode);
					collectionVo.setSectionname(rs.getString("classsection_name_var"));
					collectionVo.setAccYear(accyearId);
					collectionVo.setAccYearname(rs.getString("acadamic_year"));
					collectionVo.setConcession(rs.getDouble("percentage"));
					collectionVo.setImgurl(rs.getString("imgurl"));
					
					ps_feeInformation=conn.prepareStatement("SELECT cfs.feeSettingcode,cfs.TermCode,cft.termname,TIMESTAMPDIFF(DAY,cft.startdate,NOW( )) lateDay FROM campus_fee_setup cfs JOIN campus_fee_termdetails cft ON cfs.TermCode=cft.termid WHERE cfs.ClassCode=? AND cfs.AccyearCode=? AND cfs.specialization=? AND cfs.locationId=? ORDER BY CAST(SUBSTR(cfs.TermCode,4) AS UNSIGNED)");
					ps_feeInformation.setString(1, classId);
					ps_feeInformation.setString(2, accyearId);
					ps_feeInformation.setString(3, specCode);
					ps_feeInformation.setString(4, locationId);
					
					System.out.println("ps_feeInformation"+ps_feeInformation);
					rs_getfeeInformation=ps_feeInformation.executeQuery();
					while(rs_getfeeInformation.next()){
						PreparedStatement concessionStatement=null;
						ResultSet concessionRs=null;
						double concession=0.0;
						
						
						feeSettingcode=rs_getfeeInformation.getString(1);
						termId=rs_getfeeInformation.getString(2);
						termname=rs_getfeeInformation.getString("termname");
						lateDay=rs_getfeeInformation.getInt("lateDay");
						
						ps_collection_count = conn.prepareStatement(FeeCollectionSqlUtils.FEE_COLLECTION_COUNT);
						
						ps_collection_count.setString(1, addmissionno);
						ps_collection_count.setString(2, accyearId);
						ps_collection_count.setString(3, termId);
						rs_collection_count=ps_collection_count.executeQuery();
						
						
						if(concessionType.endsWith("equal")){
							 concessionStatement=conn.prepareStatement("SELECT concession FROM campus_scholorship WHERE admissionNo=? AND academic_year=? AND classId=? ");
							 concessionStatement.setString(1, rs.getString("student_admissionno_var"));
							 concessionStatement.setString(2, accyearId);
							 concessionStatement.setString(3, classId);
							}
							else{
								 concessionStatement=conn.prepareStatement("SELECT concession FROM campus_scholorship WHERE admissionNo=? AND academic_year=? AND classId=? AND termcode=?");
								 concessionStatement.setString(1, rs.getString("student_admissionno_var"));
								 concessionStatement.setString(2, accyearId);
								 concessionStatement.setString(3, classId);
								 concessionStatement.setString(4, termId);
								
							}
						
						concessionRs=concessionStatement.executeQuery();
						while(concessionRs.next()){
							concession=concessionRs.getDouble("concession");
						}
						int feeCollectionCount=0;
						String paidDate=null;
						
						int recieptNo=0;
						while(rs_collection_count.next()){
							feeCollectionCount=rs_collection_count.getInt(1);
							if(rs_collection_count.getString(2)!=null) {
								paidDate=HelperClass.convertDatabaseToUI(rs_collection_count.getString(2));
							}
							recieptNo=rs_collection_count.getInt(3);
							fineAmount=rs_collection_count.getDouble(4);
							paymode = rs_collection_count.getString("paymentMode");
							ddno = rs_collection_count.getString("paymentParticulars");
							dddate = rs_collection_count.getString("dd_cheque_date");
						
						}
						
						PreparedStatement ps_collection_count1 = conn.prepareStatement(FeeCollectionSqlUtils.FEE_COLLECTION_COUNT);
						
						ps_collection_count1.setString(1, addmissionno);
						ps_collection_count1.setString(2, accyearId);
						ps_collection_count1.setString(3, prevTermId);
						ResultSet rs_collection_count1=ps_collection_count1.executeQuery();
						
						while(rs_collection_count1.next()){
							if(rs_collection_count1.getString(2)!=null){
								
								advanceCarry=rs_collection_count1.getDouble("advance_amount");
								duesCarry=rs_collection_count1.getDouble("due_amount");
								
								
							}
							
							
						}
						
						rs_collection_count1.close();
						ps_collection_count1.close();
						if(feeCollectionCount==0){
							int fiCount=0;
							PreparedStatement finecount=conn.prepareStatement("SELECT COUNT(*) FROM campus_fineconfiguration where classId=? and termid=?");
							finecount.setString(1, classId);
							finecount.setString(2, termId);
							
						
							ResultSet countRs=finecount.executeQuery();
							while(countRs.next()){
								fiCount=countRs.getInt(1);
							}
							if(fiCount>0){
							PreparedStatement finep=conn.prepareStatement("SELECT * FROM campus_fineconfiguration where classId=? and termid=? ORDER BY date");
							finep.setString(1, classId);
							finep.setString(2, termId);
							ResultSet finers=finep.executeQuery();
							while(finers.next()){
								
								
								String isApp=finers.getString("IsApplicable");
								if(isApp.equalsIgnoreCase("Y")){
									if(HelperClass.getCurrentSqlDate().compareTo(finers.getDate("date")) > 0){
										fineAmount=finers.getDouble("amount");
										System.out.println("Table"+fineAmount);
									}
									else{
										fineAmount=0.0;
									}
									
								}
								else{
									fineAmount=0.0;
								}
							}
							}
							else{
								fineAmount=0.0;
							}
							
							ps_feelist=conn.prepareStatement(FeeCollectionSqlUtils.GET_FEECOLLECTION_TOTAL_AMOUNT);
							ps_feelist.setString(1, feeSettingcode);
							rs_feelist=ps_feelist.executeQuery();
							while(rs_feelist.next()){
								
								FeeNameVo feeNameVo=new FeeNameVo();
								count++;
								feeNameVo.setSno(count);
								feeNameVo.setActualAmt(rs_feelist.getDouble("totalFeeAmount"));
								feeNameVo.setPayingAmountArray(rs_feelist.getDouble("totalFeeAmount"));
								feeNameVo.setStatus("Not Paid");
								feeNameVo.setTerm(termname);
								feeNameVo.setTermId(termId);
								feeNameVo.setFeeId(feeSettingcode);
								feeNameVo.setFineAmount(fineAmount);
								feeNameVo.setAdvanceCarry(advanceCarry);
								feeNameVo.setDueCarry(duesCarry);
								feeNameVo.setPaidAmt(0.0);
								feeNameVo.setPaymode(paymode);
								feeNameVo.setDdno(ddno);
								feeNameVo.setDddate(dddate);
								feeNameVo.setConcessionAmt(concession);
								feeNameList.add(feeNameVo);
							
							}
							
							collectionVo.setFeeNamelist(feeNameList);
						}
						else{
							ps_feelist=conn.prepareStatement(FeeCollectionSqlUtils.GET_FEEPAID_TOTAL_AMOUNT);
							ps_feelist.setString(1, addmissionno);
							ps_feelist.setString(2, accyearId);
							ps_feelist.setString(3, termId);
							rs_feelist=ps_feelist.executeQuery();
							while(rs_feelist.next()){
								FeeNameVo feeNameVo=new FeeNameVo();
								count++;
								feeNameVo.setSno(count);
								
							
								feeNameVo.setActualAmt(rs_feelist.getDouble("actualamount"));
								feeNameVo.setStatus("Paid");
								feeNameVo.setPayingAmountArray(rs_feelist.getDouble("totalamount")-rs_feelist.getDouble("fineAmount"));
								feeNameVo.setPaidDate(paidDate);
								feeNameVo.setRecieptNo(recieptNo);
								feeNameVo.setTerm(termname);
								feeNameVo.setTermId(termId);
								feeNameVo.setFineAmount(rs_feelist.getDouble("fineAmount"));
								feeNameVo.setAdvanceCarry(advanceCarry);
								feeNameVo.setDueCarry(duesCarry);
								feeNameVo.setPaymode(paymode);
								feeNameVo.setDdno(ddno);
								
							/* System.out.println(ddno); */
								feeNameVo.setDddate(dddate);
								feeNameVo.setPaidAmt(rs_feelist.getDouble("amount_paid"));
								feeNameVo.setConcessionAmt(rs_feelist.getDouble("concessionAmt"));
								feeNameList.add(feeNameVo);
							}
							collectionVo.setFeeNamelist(feeNameList);
						}
						prevTermId=termId;
					}
					if(feeNameList.size()<1){
						collectionVo.setFeeNamelist(feeNameList);
					}
					
				}
				
				
				
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (rs_getfeeInformation != null&& (!rs_getfeeInformation.isClosed())) {
						rs_getfeeInformation.close();
					}
					
					if (rs_collection_count != null&& (!rs_collection_count.isClosed())) {
						rs_collection_count.close();
					}
					if (rs_feelist != null&& (!rs_feelist.isClosed())) {
						rs_feelist.close();
					}
					
					if (ps_feeInformation != null&& (!ps_feeInformation.isClosed())) {
						ps_feeInformation.close();
					}
					if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
						ps_insertPlan.close();
					}
					if (ps_collection_count != null&& (!ps_collection_count.isClosed())) {
						ps_collection_count.close();
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
					+ " Control in FeeCollectionDaoImpl: getFeeCollectionAmount: Ending");
			
			return collectionVo;
		}

	@Override
	public String saveFeeCollection(FeeCollectionVo collectionVo) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in FeeCollectionDaoImpl: saveFeeCollection : Starting");
			
			PreparedStatement ps_insertPlan = null;
			PreparedStatement ps_insertPlan1=null;
			PreparedStatement ps_insertReciept=null;
			Connection conn = null;
			int count=0;
			PreparedStatement ps1= null;
			PreparedStatement ps2= null;
			PreparedStatement ps3= null;
			int result=0;
			String status=null;
			int count_insertReciept=0;
			
			PreparedStatement ps_collection_count=null;
			ResultSet rs_collection_count=null;
			
			try {
				
				conn = JDBCConnection.getSeparateGodaddyConnection();
				conn.setAutoCommit(false);
				
				ps_collection_count = conn.prepareStatement(FeeCollectionSqlUtils.GET_COLLECTION_UPDATE_CNT);
				
				ps_collection_count.setString(1, collectionVo.getAddmissionno());
				ps_collection_count.setString(2, collectionVo.getAccYear());
				ps_collection_count.setString(3, collectionVo.getTermid());
				
				
				rs_collection_count=ps_collection_count.executeQuery();
				
				String feeCollectionCount=null;
				
				while(rs_collection_count.next()){
					
					feeCollectionCount=rs_collection_count.getString(1);
				}
				String primaryKey=IDGenerator.getPrimaryKeyID("campus_fee_collection",conn);
				if(feeCollectionCount==null){
			
				ps_insertPlan = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION);
				
				ps_insertPlan.setString(1,primaryKey);
				ps_insertPlan.setString(2,collectionVo.getAddmissionno());
				ps_insertPlan.setString(3,collectionVo.getAccYear());
				ps_insertPlan.setString(4,collectionVo.getTermid());
				
				
				if(collectionVo.getOutstanding_balance()==0.0||collectionVo.getOutstanding_balance()==0||collectionVo.getOutstanding_balance()==0.00)
				{
					ps_insertPlan.setString(5,"Y");
				}
				else
					
				{
					ps_insertPlan.setString(5,"N");
				}
				
				ps_insertPlan.setDouble(6,collectionVo.getTot_actual_amt());
				ps_insertPlan.setDouble(7,collectionVo.getTot_actual_amt()-collectionVo.getFineAmount());
				ps_insertPlan.setDouble(8,collectionVo.getDuesCarry());
				ps_insertPlan.setDouble(9,collectionVo.getAdvanceCarry());
				ps_insertPlan.setDate(10,HelperClass.getCurrentSqlDate());
				ps_insertPlan.setString(11,collectionVo.getUserID());
				ps_insertPlan.setTimestamp(12,HelperClass.getCurrentTimestamp());
				ps_insertPlan.setDouble(13,collectionVo.getCurrent_payment());
				ps_insertPlan.setDouble(14,collectionVo.getDuesCarry());
				ps_insertPlan.setDouble(15,collectionVo.getFineAmount());
				ps_insertPlan.setString(16,collectionVo.getPaymentPatriculars());
				ps_insertPlan.setString(17,collectionVo.getPaymentMode());
				ps_insertPlan.setString(18,collectionVo.getDd_cheque_date());
				ps_insertPlan.setString(19,collectionVo.getDd_cheque_bank());
				ps_insertPlan.setDouble(20,collectionVo.getConcession());
				ps_insertPlan.setDouble(21,collectionVo.getCurrent_payment());
				//System.out.println("ps_insertPlan:::::"+ps_insertPlan);
				
				count=ps_insertPlan.executeUpdate();
				
				
			
				ps2 = conn.prepareStatement("insert into campus_fee_indetail (FeeInDetailedCode,admissionNo,accYear,term_id,totalamount,actualamount," +
						"balance_amount,amount_paid,conc_amount,conc_percent,paidDate,createdby,createdtime)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				ps2.setString(1,IDGenerator.getPrimaryKeyID("campus_fee_indetail",conn));
				ps2.setString(2,collectionVo.getAddmissionno());
				ps2.setString(3,collectionVo.getAccYear());
				ps2.setString(4,collectionVo.getTermid());
				ps2.setDouble(5,collectionVo.getTot_paid_amt());
				ps2.setDouble(6,collectionVo.getTot_actual_amt());
				ps2.setDouble(7,collectionVo.getOutstanding_balance());
				ps2.setDouble(8,collectionVo.getCurrent_payment());
				ps2.setDouble(9,collectionVo.getTot_concession_amt());
				ps2.setDouble(10,collectionVo.getConcession());
				ps2.setDate(11,HelperClass.getCurrentSqlDate());
				ps2.setString(12,collectionVo.getUserID());
				ps2.setTimestamp(13,HelperClass.getCurrentTimestamp());
				ps2.executeUpdate();
			
				if(count>0){
					
					ps1 = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION_DETAILS);
					
					for(int i=0;i<collectionVo.getFeeNamelist().size();i++){
						
						ps1.setString(1,primaryKey);
						ps1.setString(2,collectionVo.getFeeNamelist().get(i).getFeecode());
						ps1.setDouble(3,collectionVo.getFeeNamelist().get(i).getFeeAmountArray());
						ps1.setDouble(4,collectionVo.getFeeNamelist().get(i).getFeePayingAmountArray());
						ps1.setDouble(5,collectionVo.getFeeNamelist().get(i).getOutStandingAmountArray());
						ps1.setDouble(6,collectionVo.getFeeNamelist().get(i).getConcessionpercentArray());
						ps1.setDouble(7,collectionVo.getFeeNamelist().get(i).getConsfeeAmountArray());
						
						result=ps1.executeUpdate();
					}
					
					ps_insertReciept = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_RECIEPT);
					
					for(int i=0;i<collectionVo.getFeeNamelist().size();i++){
						
						ps_insertReciept.setString(1,primaryKey);
						ps_insertReciept.setString(2,collectionVo.getFeeNamelist().get(i).getFeecode());
						ps_insertReciept.setDouble(3,collectionVo.getFeeNamelist().get(i).getFeeAmountArray());
						ps_insertReciept.setDouble(4,collectionVo.getFeeNamelist().get(i).getFeePayingAmountArray());
						ps_insertReciept.setDouble(5,collectionVo.getFeeNamelist().get(i).getOutStandingAmountArray());
						ps_insertReciept.setDouble(6,collectionVo.getFeeNamelist().get(i).getConcessionpercentArray());
						ps_insertReciept.setDouble(7,collectionVo.getFeeNamelist().get(i).getConsfeeAmountArray());
						//System.out.println("ps1:::"+ps1);
						count_insertReciept=ps_insertReciept.executeUpdate();
					}
				
				}
			}
				
				if(result>0){
					
					
					
					
					PreparedStatement ps_insert_detail=conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION_D);
					
					ps_insert_detail.setString(1,collectionVo.getAddmissionno());
					ps_insert_detail.setString(2,collectionVo.getAccYear());
					ps_insert_detail.setString(3,collectionVo.getTermid());
					ps_insert_detail.setString(4,"Y");
					
					
					ps_insert_detail.setString(5,collectionVo.getTot_opning_amt());
					ps_insert_detail.setDouble(6,collectionVo.getTot_actual_amt());
					ps_insert_detail.setDouble(7,collectionVo.getTot_concession_amt());
					ps_insert_detail.setDouble(8,collectionVo.getConcession());
					ps_insert_detail.setDate(9,HelperClass.getCurrentSqlDate());
					ps_insert_detail.setString(10,collectionVo.getUserID());
					ps_insert_detail.setTimestamp(11,HelperClass.getCurrentTimestamp());
					ps_insert_detail.setDouble(12,collectionVo.getCurrent_payment());
					ps_insert_detail.setDouble(13,collectionVo.getOutstanding_balance());
					
					//System.out.println("ps_insertPlan:::::"+ps_insertPlan);
					
					int counts=ps_insert_detail.executeUpdate();
					if(counts>0){
						status="true";
						conn.commit();
					}
					else{
						status="false";
					}
					
				}else{
					
					status="false";
				}
				
				
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					
					if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
						ps_insertPlan.close();
					}
					if (ps_insertPlan1 != null&& (!ps_insertPlan1.isClosed())) {
						ps_insertPlan1.close();
					}
					if ( ps_insertReciept != null&& (! ps_insertReciept.isClosed())) {
						 ps_insertReciept.close();
					}
					if (ps1 != null&& (!ps1.isClosed())) {
						ps1.close();
					}
					if (ps2 != null&& (!ps2.isClosed())) {
						ps2.close();
					}
					if (ps3 != null&& (!ps3.isClosed())) {
						ps3.close();
					}
					if (ps_collection_count != null&& (!ps_collection_count.isClosed())) {
						ps_collection_count.close();
					}
					if (rs_collection_count != null&& (!rs_collection_count.isClosed())) {
						rs_collection_count.close();
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
					+ " Control in FeeCollectionDaoImpl: getFeeCollectionDetails: Ending");
			
			return status;
		}

	@Override
	public ArrayList<FeeNameVo> getSearchFeeCollectionDetails(
			String currentYear, String classid, String sectionId, String termId, String stuId) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in FeeCollectionDaoImpl: getSearchFeeCollectionDetails : Starting");
			
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in FeeCollectionDaoImpl: getSearchFeeCollectionDetails: Ending");
			
			return null;
		}

	public List<ParentVO> getAllStudentNamesReportDao(String sectionid ,String classname, String accyear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getAllStudentNamesReportDao : Starting");

		List<ParentVO> seclist = new ArrayList<ParentVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			System.out.println("DAOIMPL:::" + accyear);
			conn = JDBCConnection.getSeparateGodaddyConnection();

			pstmt = conn
					.prepareStatement(FeeCollectionSqlUtils.GET_STUDENT_DETAILS);

			pstmt.setString(1, sectionid);
			pstmt.setString(2, accyear);
			pstmt.setString(3, classname);
			
			System.out.println(pstmt);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ParentVO pojo = new ParentVO();

				pojo.setStudentid(rs.getString("student_id_int"));
				pojo.setStudentFnameVar(rs.getString("studentname"));

				seclist.add(pojo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try{
				if(rs !=null && !rs.isClosed()){
					rs.close();
				}
				if(pstmt !=null && !pstmt.isClosed()){
					pstmt.close();
				}
				
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getAllStudentNamesReportDao : Ending");

		return seclist;
	}
	public ArrayList<ClassFeeSetupVo> getAllFeesDao(
			ClassFeeSetupPojo feeSetupPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getAllFeesDao : Starting");

		ArrayList<ClassFeeSetupVo> allfeeslist = new ArrayList<ClassFeeSetupVo>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		String amt = null;
		ClassFeeSetupVo feeSetupVo = null; 

		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmt = conn.prepareStatement(FeeCollectionSqlUtils.GET_ALL_FEES);

			pstmt.setString(1, feeSetupPojo.getAccYear());
			pstmt.setString(2, feeSetupPojo.getClassId());
				System.out.println(":::::::::::::::::::::::::;"+pstmt);
			rs = pstmt.executeQuery();

			PreparedStatement pstmt1 = null;
			ResultSet rs1 = null;

			pstmt1 = conn.prepareStatement(FeeCollectionSqlUtils.GET_TOTAL_FEES);

			pstmt1.setString(1, feeSetupPojo.getAccYear());
			pstmt1.setString(2, feeSetupPojo.getClassId());

			rs1 = pstmt1.executeQuery();

			while (rs1.next()) {
				amt = rs1.getString("amount");

			}

			while (rs.next()) {
				count++;
				feeSetupVo = new ClassFeeSetupVo();
				feeSetupVo.setSno(count);
				feeSetupVo.setFeecode(rs.getString("FeeCode"));
				feeSetupVo.setFeename(rs.getString("FeeName"));
				feeSetupVo.setFeeamount(rs.getString("feeAmount"));
				feeSetupVo.setFeesettingcode(rs.getString("feeSettingCode"));
				feeSetupVo.setTotalfee(amt);

				allfeeslist.add(feeSetupVo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try{
				if(rs !=null && !rs.isClosed()){
					rs.close();
				}
				if(pstmt !=null && !pstmt.isClosed()){
					pstmt.close();
				}
				
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getAllFeesDao : Ending");

		return allfeeslist;
	}

	public ClassFeeSetupVo getStudentValDao(ClassFeeSetupPojo feeSetupPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getStudentValDao : Starting");

		ClassFeeSetupVo feeSetupVo = new ClassFeeSetupVo();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmt = conn
					.prepareStatement(FeeCollectionSqlUtils.GET_STUDENT_VAL);

			pstmt.setString(1, feeSetupPojo.getStudentId());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				feeSetupVo.setStudentid(rs.getString("student_id_int"));
				feeSetupVo.setStudentname(rs.getString("studentname"));
				feeSetupVo.setStdadmissionNo(rs
						.getString("student_admissionno_var"));
				feeSetupVo.setClassid(rs.getString("classdetail_id_int"));
				feeSetupVo.setClassname(rs.getString("classdetails_name_var"));

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try{
				if(rs !=null && !rs.isClosed()){
					rs.close();
				}
				if(pstmt !=null && !pstmt.isClosed()){
					pstmt.close();
				}
				
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getStudentValDao : Ending");

		return feeSetupVo;
	}

	public ArrayList<ClassFeeSetupVo> getPaymentTypeDAO(
			ClassFeeSetupPojo feeSetupPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getPaymentTypeDAO : Starting");

		ArrayList<ClassFeeSetupVo> paymentlist = new ArrayList<ClassFeeSetupVo>();
		ClassFeeSetupVo feeSetupVo = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;

		Double paidamount = 0.0;

		try {

			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmt = conn
					.prepareStatement(FeeCollectionSqlUtils.GET_PAMENT_TYPE_DETAILS);

			pstmt.setString(1, feeSetupPojo.getStudentId().trim());
			pstmt.setString(2, feeSetupPojo.getAddmissionNo().trim());
			pstmt.setString(3, feeSetupPojo.getFeesettingCode().trim());
			pstmt.setString(4, feeSetupPojo.getClassId().trim());

			System.out.println("pstmt:::::::::::" + pstmt);

			rs = pstmt.executeQuery();

			feeSetupVo = new ClassFeeSetupVo();

			if (rs.next()) {

				feeSetupVo.setPaymenyid(rs.getString("patment_id"));
				feeSetupVo.setPaymentdate(HelperClass.convertDatabaseToUI(rs
						.getString("payment_date")));

				paidamount = rs.getDouble("paid_amount");

				feeSetupVo.setPaidamount(paidamount);

				System.out.println(rs.getDouble("paid_amount"));

				/*
				 * feeSetupVo.setJanuary(rs.getString("january"));
				 * feeSetupVo.setFebruary(rs.getString("february"));
				 * feeSetupVo.setMarch(rs.getString("march"));
				 * feeSetupVo.setApril(rs.getString("april"));
				 * feeSetupVo.setMay(rs.getString("may"));
				 * feeSetupVo.setJune(rs.getString("june"));
				 * feeSetupVo.setJuly(rs.getString("july"));
				 * feeSetupVo.setAugust(rs.getString("august"));
				 * feeSetupVo.setSeptember(rs.getString("september"));
				 * feeSetupVo.setOctober(rs.getString("october"));
				 * feeSetupVo.setNovember(rs.getString("november"));
				 * feeSetupVo.setDecember(rs.getString("december"));
				 */

				if (rs.getString("january").equals("Y")) {

					feeSetupVo.setJanuary("JAN");

				} else {

				}

				if (rs.getString("february").equals("Y")) {

					feeSetupVo.setFebruary("FEB");

				} else {

				}
				if (rs.getString("march").equals("Y")) {

					feeSetupVo.setMarch("MAR");

				} else {

				}
				if (rs.getString("april").equals("Y")) {

					feeSetupVo.setApril("APR");

				} else {

				}
				if (rs.getString("may").equals("Y")) {

					feeSetupVo.setMay("MAY");

				} else {

				}
				if (rs.getString("june").equals("Y")) {

					feeSetupVo.setJune("JUN");

				} else {

				}
				if (rs.getString("july").equals("Y")) {

					feeSetupVo.setJuly("JUL");

				} else {

				}
				if (rs.getString("august").equals("Y")) {

					feeSetupVo.setAugust("AUG");

				} else {

				}
				if (rs.getString("september").equals("Y")) {

					feeSetupVo.setSeptember("SEP");

				} else {

				}
				if (rs.getString("october").equals("Y")) {

					feeSetupVo.setOctober("OCT");

				} else {

				}
				if (rs.getString("november").equals("Y")) {

					feeSetupVo.setNovember("NOV");

				} else {

				}
				if (rs.getString("december").equals("Y")) {

					feeSetupVo.setDecember("DEC");

				} else {

				}

			}

			else

			{

				feeSetupVo.setPaymenyid("");
				feeSetupVo.setPaymentdate("");
				feeSetupVo.setPaidamount(0.0);
				feeSetupVo.setJanuary("");
				feeSetupVo.setFebruary("");
				feeSetupVo.setMarch("");
				feeSetupVo.setApril("");
				feeSetupVo.setMay("");
				feeSetupVo.setJune("");
				feeSetupVo.setJuly("");
				feeSetupVo.setAugust("");
				feeSetupVo.setSeptember("");
				feeSetupVo.setOctober("");
				feeSetupVo.setNovember("");
				feeSetupVo.setDecember("");

			}

			paymentlist.add(feeSetupVo);

			/*
			 * JSONArray obj= new JSONArray(paymentlist);
			 * System.out.println("paymentlist "+obj);
			 */

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try{
				if(rs !=null && !rs.isClosed()){
					rs.close();
				}
				if(pstmt !=null && !pstmt.isClosed()){
					pstmt.close();
				}
				
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getPaymentTypeDAO : Ending");

		return paymentlist;
	}

	@Override
	public int getstudentcount(String studentid)

	{

		int count = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateGodaddyConnection();

			pstmt = conn
					.prepareStatement(FeeCollectionSqlUtils.GET_FEECOLLECTIONCOUNT);
			pstmt.setString(1, studentid.trim());

			System.out.println(pstmt);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
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
		return count;

	}

	@Override
	public boolean inserfeecollection(ClassFeeSetupVo vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: saveFeeCollection : Starting");

		PreparedStatement ps_insertPlan = null;
		Connection conn = null;
		int count = 0;
		PreparedStatement ps1 = null;
		int result = 0;
		boolean status = false;

		PreparedStatement ps_collection_count = null;
		ResultSet rs_collection_count = null;

		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String january = "N";
		String february = "N";
		String march = "N";
		String april = "N";
		String may = "N";
		String june = "N";
		String july = "N";
		String august = "N";
		String september = "N";
		String october = "N";
		String november = "N";
		String december = "N";

		try {

			System.out.println("DAOIML:::" + vo.getMonthlist());
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			String primaryKey = IDGenerator.getPrimaryKeyID("campus_payment_collection",conn);

			

			pstmt = conn.prepareStatement(FeeCollectionSqlUtils.GET_FEE_COLLECTION_DETAILS);
	
			pstmt.setString(1, primaryKey);
			pstmt.setString(2, vo.getFeecode());
			pstmt.setString(3, vo.getStudentid());
			pstmt.setString(4, vo.getPaymentmode());
			pstmt.setString(5, vo.getCheque_no());
			pstmt.setString(6, vo.getPaymenttype());
			pstmt.setString(7, vo.getMonthlist());
			pstmt.setDouble(8, vo.getPaidamount());
			pstmt.setDouble(9, vo.getDueamount());
			pstmt.setDouble(10,vo.getTotalfeeamount());
			pstmt.setString(11,HelperClass.convertUIToDatabase(vo.getPaymentdate()));
			pstmt.setString(12,vo.getCurrentuser());
			pstmt.setTimestamp(13, HelperClass.getCurrentTimestamp());
			
			System.out.println(pstmt);

			pstmt.executeUpdate();

			String statusval = "";

			String s1 = vo.getMonthlist();

			String s2[] = s1.split(",");

			for (int i = 0; i < s2.length; i++)

			{

				// JAN
				if (s2[i].equals("JAN")) {

					System.out.println("JAN iF");
					january = "Y";
				} else {

					if (january.equals("Y")) {
						january = "Y";
					} else {
						january = "N";
					}
				}

				// FEBRUARY

				if (s2[i].equals("FEB")) {
					february = "Y";
				} else {
					if (february.equals("Y")) {
						february = "Y";
					} else {
						february = "N";
					}
				}

				// march

				if (s2[i].equals("MAR")) {
					march = "Y";
				} else {
					if (march.equals("Y"))

					{
						march = "Y";
					}

					else

					{
						march = "N";
					}
				}

				// April

				if (s2[i].equals("APR")) {
					april = "Y";
				} else {

					if (april.equals("Y"))

					{

						april = "Y";

					}

					else

					{

						april = "N";

					}
				}

				// MAY

				if (s2[i].equals("MAY")) {
					may = "Y";
				} else {

					if (may.equals("Y"))

					{

						may = "Y";

					}

					else

					{

						may = "N";

					}
				}

				// JUNE

				if (s2[i].equals("JUN")) {
					june = "Y";
				} else {

					if (june.equals("Y"))

					{

						june = "Y";

					}

					else

					{

						june = "N";

					}
				}

				// JULY

				if (s2[i].equals("JUL")) {
					july = "Y";
				} else {

					if (july.equals("Y"))

					{

						july = "Y";

					}

					else

					{

						july = "N";

					}
				}

				// AUGUST

				if (s2[i].equals("AUG")) {
					august = "Y";
				} else {

					if (august.equals("Y"))

					{

						august = "Y";

					}

					else

					{

						august = "N";

					}
				}

				// SEP

				if (s2[i].equals("SEP")) {

					september = "Y";
				} else {

					if (september.equals("Y"))

					{

						september = "Y";

					}

					else

					{

						september = "N";

					}
				}

				// OCTOBER

				if (s2[i].equals("OCT")) {

					october = "Y";
				} else {

					if (october.equals("Y"))

					{

						october = "Y";

					}

					else

					{

						october = "N";

					}
				}

				// November

				if (s2[i].equals("NOV")) {

					november = "Y";
				} else {

					if (november.equals("Y"))

					{

						november = "Y";

					}

					else

					{

						november = "N";

					}
				}

				// December

				if (s2[i].equals("DEC")) {

					december = "Y";
				} else {

					if (december.equals("Y"))

					{

						december = "Y";

					}

					else

					{

						december = "N";

					}
				}

			}

			conn = JDBCConnection.getSeparateGodaddyConnection();

			

			ps_insertPlan = conn
					.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_PAYMENT_COLLECTION);

			ps_insertPlan.setString(1, primaryKey);
			ps_insertPlan.setString(2, vo.getAcadamicyear());
			ps_insertPlan.setString(3, vo.getFeecode());
			ps_insertPlan.setString(4, vo.getStdadmissionNo());
			ps_insertPlan.setString(5, vo.getStudentid());
			ps_insertPlan.setString(6, vo.getPaymentmode());
			ps_insertPlan.setString(7, vo.getPaymenttype());
			ps_insertPlan.setDouble(8, vo.getPaidamount());
			ps_insertPlan.setDouble(9, vo.getTotalfeeamount());
			ps_insertPlan.setDouble(10, vo.getDueamount());
			ps_insertPlan.setString(11,	HelperClass.convertUIToDatabase(vo.getPaymentdate()));
			ps_insertPlan.setString(12, january);
			ps_insertPlan.setString(13, february);
			ps_insertPlan.setString(14, march);
			ps_insertPlan.setString(15, april);
			ps_insertPlan.setString(16, may);
			ps_insertPlan.setString(17, june);
			ps_insertPlan.setString(18, july);
			ps_insertPlan.setString(19, august);
			ps_insertPlan.setString(20, september);
			ps_insertPlan.setString(21, october);
			ps_insertPlan.setString(22, november);
			ps_insertPlan.setString(23, december);
			ps_insertPlan.setString(24, vo.getClassname());
			ps_insertPlan.setString(25, vo.getCurrentuser());
			ps_insertPlan.setTimestamp(26, HelperClass.getCurrentTimestamp());

			System.out.println(ps_insertPlan);
			count = ps_insertPlan.executeUpdate();

			if (count > 0) {

				status = true;
				
				Runnable r = new SMSThread(primaryKey,HelperClass.getCurrentTimestamp().toString(),"Fee");
				new Thread(r).start();
				
				storeFeeSMSDetails(primaryKey,vo);
				

			} else {

				status = false;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if (ps_insertPlan != null && (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
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
				+ " Control in FeeCollectionDaoImpl: getFeeCollectionDetails: Ending");

		return status;
	}

	@Override
	
	public boolean updatefeecollection(ClassFeeSetupVo vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: saveFeeCollection : Starting");

		PreparedStatement ps_insertPlan = null;
		Connection conn = null;
		int count = 0;
		PreparedStatement ps1 = null;
		int result = 0;
		boolean status = false;

		PreparedStatement ps_collection_count = null;
		ResultSet rs_collection_count = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		
		String patment_id=null;

		String january = "N";
		String february = "N";
		String march = "N";
		String april = "N";
		String may = "N";
		String june = "N";
		String july = "N";
		String august = "N";
		String september = "N";
		String october = "N";
		String november = "N";
		String december = "N";
		double paidamount=0;
		double dueamount=0;
		double totalamount=0;
		
		
		

		try {

			System.out.println("UPDATE:::" + vo.getMonthlist());

			String statusval = "";

			String s1 = vo.getMonthlist();

			String s2[] = s1.split(",");

			for (int i = 0; i < s2.length; i++)

			{

				// JAN
				if (s2[i].equals("JAN")) {

					System.out.println("JAN iF");
					january = "Y";
				} else {

					if (january.equals("Y")) {
						january = "Y";
					} else {
						january = "N";
					}
				}

				// FEBRUARY

				if (s2[i].equals("FEB")) {
					february = "Y";
				} else {
					if (february.equals("Y")) {
						february = "Y";
					} else {
						february = "N";
					}
				}

				// march

				if (s2[i].equals("MAR")) {
					march = "Y";
				} else {
					if (march.equals("Y"))

					{
						march = "Y";
					}

					else

					{
						march = "N";
					}
				}

				// April

				if (s2[i].equals("APR")) {
					april = "Y";
				} else {

					if (april.equals("Y"))

					{

						april = "Y";

					}

					else

					{

						april = "N";

					}
				}

				// MAY

				if (s2[i].equals("MAY")) {
					may = "Y";
				} else {

					if (may.equals("Y"))

					{

						may = "Y";

					}

					else

					{

						may = "N";

					}
				}

				// JUNE

				if (s2[i].equals("JUN")) {
					june = "Y";
				} else {

					if (june.equals("Y"))

					{

						june = "Y";

					}

					else

					{

						june = "N";

					}
				}

				// JULY

				if (s2[i].equals("JUL")) {
					july = "Y";
				} else {

					if (july.equals("Y"))

					{

						july = "Y";

					}

					else

					{

						july = "N";

					}
				}

				// AUGUST

				if (s2[i].equals("AUG")) {
					august = "Y";
				} else {

					if (august.equals("Y"))

					{

						august = "Y";

					}

					else

					{

						august = "N";

					}
				}

				// SEP

				if (s2[i].equals("SEP")) {

					september = "Y";
				} else {

					if (september.equals("Y"))

					{

						september = "Y";

					}

					else

					{

						september = "N";

					}
				}

				// OCTOBER

				if (s2[i].equals("OCT")) {

					october = "Y";
				} else {

					if (october.equals("Y"))

					{

						october = "Y";

					}

					else

					{

						october = "N";

					}
				}

				// November

				if (s2[i].equals("NOV")) {

					november = "Y";
				} else {

					if (november.equals("Y"))

					{

						november = "Y";

					}

					else

					{

						november = "N";

					}
				}

				// December

				if (s2[i].equals("DEC")) {

					december = "Y";
				} else {

					if (december.equals("Y"))

					{

						december = "Y";

					}

					else

					{

						december = "N";

					}
				}

			}

			conn = JDBCConnection.getSeparateGodaddyConnection();
			
			
			
			
			pst=conn.prepareStatement(FeeCollectionSqlUtils.GET_FEE_PARTICULARS);
			
			pst.setString(1,vo.getStudentid());
			pst.setString(2,vo.getAcadamicyear());
			
			rs=pst.executeQuery();

			while(rs.next())
			{
				
				paidamount=rs.getDouble("paid_amount");
				totalamount=rs.getDouble("total_amount");
				dueamount=rs.getDouble("due_amount");
				patment_id=rs.getString("patment_id");
				
			}
			
			double totalpaidamount=0;
			double total_dueamount=0;
			
			
			totalpaidamount=paidamount+vo.getPaidamount();
			
			System.out.println("totalpaidamount::::"+totalpaidamount);
			
			
			total_dueamount=totalamount-totalpaidamount;
			
			System.out.println("total_dueamount:::"+total_dueamount);
			

			ps_insertPlan = conn
					.prepareStatement(FeeCollectionSqlUtils.UPDATE_FEE_PAYMENT_COLLECTION);
			
			ps_insertPlan.setString(1, vo.getFeecode());
			ps_insertPlan.setString(2, vo.getStdadmissionNo());
			ps_insertPlan.setString(3, vo.getPaymentmode());
			ps_insertPlan.setString(4, vo.getPaymenttype());
			ps_insertPlan.setDouble(5, totalpaidamount);
			ps_insertPlan.setDouble(6, vo.getTotalfeeamount());
			ps_insertPlan.setDouble(7, total_dueamount);
			ps_insertPlan.setString(8, HelperClass.convertUIToDatabase(vo.getPaymentdate()));
			ps_insertPlan.setString(9, january);
			ps_insertPlan.setString(10, february);
			ps_insertPlan.setString(11, march);
			ps_insertPlan.setString(12, april);
			ps_insertPlan.setString(13, may);
			ps_insertPlan.setString(14, june);
			ps_insertPlan.setString(15, july);
			ps_insertPlan.setString(16, august);
			ps_insertPlan.setString(17, september);
			ps_insertPlan.setString(18, october);
			ps_insertPlan.setString(19, november);
			ps_insertPlan.setString(20, december);
			ps_insertPlan.setString(21, vo.getCurrentuser());
			ps_insertPlan.setTimestamp(22, HelperClass.getCurrentTimestamp());
			ps_insertPlan.setString(23, vo.getStudentid());
			ps_insertPlan.setString(24, vo.getAcadamicyear());
			ps_insertPlan.setString(25, vo.getClassname());



			System.out.println(ps_insertPlan);
			count = ps_insertPlan.executeUpdate();
			
			
			
			
			
			conn = JDBCConnection.getSeparateGodaddyConnection();

			pstmt = conn.prepareStatement(FeeCollectionSqlUtils.GET_FEE_COLLECTION_DETAILS);
	
			pstmt.setString(1, patment_id);
			pstmt.setString(2, vo.getFeecode());
			pstmt.setString(3, vo.getStudentid());
			pstmt.setString(4, vo.getPaymentmode());
			pstmt.setString(5, vo.getCheque_no());
			pstmt.setString(6, vo.getPaymenttype());
			pstmt.setString(7, vo.getMonthlist());
			pstmt.setDouble(8, vo.getPaidamount());
			pstmt.setDouble(9, vo.getDueamount());
			pstmt.setDouble(10,vo.getTotalfeeamount());
			pstmt.setString(11,HelperClass.convertUIToDatabase(vo.getPaymentdate()));
			pstmt.setString(12,vo.getCurrentuser());
			pstmt.setTimestamp(13, HelperClass.getCurrentTimestamp());
			
			System.out.println("update:::"+pstmt);

			pstmt.executeUpdate();
			
			
			

			if (count > 0) {

				status = true;
				
				
				storeFeeSMSDetails(vo.getFeecode(),vo);

			} else {

				status = false;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if (ps_insertPlan != null && (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
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
				+ " Control in FeeCollectionDaoImpl: getFeeCollectionDetails: Ending");

		return status;
	}

	
	
	public void storeFeeSMSDetails(String feecode,ClassFeeSetupVo vo)
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: storeFeeSMSDetails : Starting");

		PreparedStatement ps_fee = null,ps_fee_details=null;
		Connection conn = null;
		int count = 0;
		ResultSet rs =null;
	
		try {
			
			System.out.println( " Control in FeeCollectionDaoImpl: storeFeeSMSDetails : Starting");
			conn = JDBCConnection.getSeparateGodaddyConnection();	
			String feeId = IDGenerator.getPrimaryKeyID("sms_fee_details",conn);
			
			
			
			ps_fee_details = conn.prepareStatement(SmsUtilsConstants.GET_FEE_DETAILS);
			ps_fee_details.setString(1, feecode);
			
			rs = ps_fee_details.executeQuery();
			
			System.out.println("GET_FEE_DETAILS" +ps_fee_details);
			
			while(rs.next())
			{
				
				
				String name =  rs.getString("name");
				
				ps_fee = conn.prepareStatement(SmsUtilsConstants.INSERT_FEE_DETAILS);
				ps_fee.setString(1, feeId );
				ps_fee.setTimestamp(2, HelperClass.getCurrentTimestamp());
				ps_fee.setString(3, vo.getStudentid());
				ps_fee.setString(4, "Fees paid for Academic Year "+ HelperClass.getAcademicYear() +" for your ward " + name +" bearing Admission No. " + rs.getString("student_admissionno_var") + " is Rs." + rs.getString("paid_amount") + " and Balance Amount is Rs." + rs.getString("due_amount"));
				ps_fee.setString(5, rs.getString("total_amount"));
				ps_fee.setString(6, rs.getString("paid_amount"));
				ps_fee.setString(7, rs.getString("due_amount"));
				ps_fee.setString(8, vo.getCurrentuser());
				ps_fee.setTimestamp(9, HelperClass.getCurrentTimestamp());
				
				count = ps_fee.executeUpdate();
				
				System.out.println("INSERT_FEE_DETAILS" +ps_fee);
				
				if(count>0)
				{
					System.out.println("SMS Inserted Successfully");
					
					Runnable r = new SMSThread(feeId,HelperClass.getCurrentTimestamp().toString().substring(0,10),"Fee");
					new Thread(r).start();
					
				}
				
				
			}
			
			
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if (ps_fee != null && (!ps_fee.isClosed())) {
					ps_fee.close();
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
				+ " Control in FeeCollectionDaoImpl: getFeeCollectionDetails: Ending");

		
	}

	@Override
	public FeeCollectionVo getTranportFeeCollectionAmount(String feeCodeDetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getTranportFeeCollectionAmount : Starting");
		PreparedStatement ps_feeInformation=null;
		ResultSet rs_getfeeInformation=null;
		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		int count=0;
		
		PreparedStatement ps_collection_count= null;
		ResultSet rs_collection_count=null;
		PreparedStatement ps_feelist= null;
		ResultSet rs_feelist=null;
		
		/*double amount_paid_so_far =0.00;
		double opening_balance =0.00;*/
		String stageid=null;
		
		
		FeeCollectionVo collectionVo=new FeeCollectionVo();
		ArrayList<FeeNameVo> feeNameList=new ArrayList<FeeNameVo>();
		try {
			
			String[] feeDatails=feeCodeDetails.split(",");
			
			String addmissionno=feeDatails[0];
			
			String accyearId=feeDatails[1];
			String locId=feeDatails[2];
			String termId=null;
			String classId=null;
			String specCode=null;
			String termname=null;
			int start_month=0;
			int end_month=0;
			int month=0;
			int startmonth=0;
			conn = JDBCConnection.getSeparateGodaddyConnection();
		
			ps_insertPlan = conn.prepareStatement(FeeCollectionSqlUtils.GET_TRANSPORT_FEECOLLECTION_DEDDER);
			
			ps_insertPlan.setString(1,addmissionno);
			ps_insertPlan.setString(2,accyearId);
			ps_insertPlan.setString(3,locId);
			rs=ps_insertPlan.executeQuery();
			while(rs.next()){
				collectionVo.setStudentid(addmissionno);
				collectionVo.setAddmissionno(rs.getString("student_admissionno_var"));
				collectionVo.setStudentname(rs.getString("studentname"));
				
				collectionVo.setClassname(rs.getString("classdetails_name_var"));
				specCode=rs.getString("specilization");
				classId=rs.getString("classdetail_id_int");
				collectionVo.setClassId(classId);
				collectionVo.setSpecialization(specCode);
				collectionVo.setSectionname(rs.getString("classsection_name_var"));
				collectionVo.setAccYear(accyearId);
				collectionVo.setAccYearname(rs.getString("acadamic_year"));
				collectionVo.setConcession(rs.getDouble("percentage"));
				
				if(rs.getString("srtm")!=null)
				start_month=Integer.parseInt(rs.getString("srtm").split("-")[1]);
				if(rs.getString("endm")!=null)
				end_month=Integer.parseInt(rs.getString("endm").split("-")[1]);
				
				ps_feeInformation=conn.prepareStatement("SELECT termid,termname,CAST(SUBSTR(startdate,6,2) AS UNSIGNED) startmonth,TIMESTAMPDIFF(MONTH,startdate,enddate)+1 MONTH FROM campus_fee_transport_termdetails WHERE accyear=? AND locationId = ?");
				ps_feeInformation.setString(1, accyearId);
				ps_feeInformation.setString(2, locId);
				rs_getfeeInformation=ps_feeInformation.executeQuery();
				
				while(rs_getfeeInformation.next()){
					
					termId=rs_getfeeInformation.getString("termid");
					termname=rs_getfeeInformation.getString("termname");
					month=rs_getfeeInformation.getInt("month");
					startmonth=rs_getfeeInformation.getInt("startmonth");
					if(rs.getString("srtm")==null){
						start_month=startmonth;
					}
					for(int i=startmonth;i<(startmonth+month);i++){
						
						
						ps_collection_count = conn.prepareStatement("select count(*),paidDate,reciept_no from campus_tranport_fee_collection_details where admissionNo=? and accYear=? and termcode=? and MonthName=?");
						
						ps_collection_count.setString(1, addmissionno);
						ps_collection_count.setString(2, accyearId);
						ps_collection_count.setString(3, termId);
						ps_collection_count.setString(4, HelperClass.getMonthFullName(Integer.toString(i)));
						
						rs_collection_count=ps_collection_count.executeQuery();
						int feeCollectionCount=0;
						int recieptNo=0;
						String paidDate=null;
						while(rs_collection_count.next()){
							feeCollectionCount=rs_collection_count.getInt(1);
							if(rs_collection_count.getString(2)!=null)
							paidDate=HelperClass.convertDatabaseToUI(rs_collection_count.getString(2));
							recieptNo=rs_collection_count.getInt(3);
						}
						
						PreparedStatement stg=conn.prepareStatement("SELECT stage_id FROM campus_student_route_stage_mapping WHERE mapped_id=? AND accyear=? AND month=?");
						stg.setString(1, addmissionno);
						stg.setString(2, accyearId);
						stg.setString(3, HelperClass.getMonthFullName(Integer.toString(i)));
						System.out.println("stg="+stg);
						ResultSet srs=stg.executeQuery();
						if(srs.next()) {
							stageid=srs.getString("stage_id");
						}
						srs.close();
						stg.close();
						
						
						if(feeCollectionCount==0){
							
								System.out.println("loop in condition "+i);	
								
							
								
							ps_feelist=conn.prepareStatement("SELECT amount FROM campus_fee_stage WHERE stage_id=? AND accyear=?");
							ps_feelist.setString(1, stageid);
							ps_feelist.setString(2, accyearId);
							rs_feelist=ps_feelist.executeQuery();
							
							while(rs_feelist.next()){
								FeeNameVo feeNameVo=new FeeNameVo();
								count++;
								feeNameVo.setSno(count);
								feeNameVo.setActualAmt(rs_feelist.getDouble("amount"));
								feeNameVo.setStatus("Not Paid");
								feeNameVo.setTerm(termname); 
								feeNameVo.setTermId(termId);
								feeNameVo.setMonthName(HelperClass.getMonthFullName(Integer.toString(i)));
								feeNameList.add(feeNameVo);
							}
							System.out.println("collectionVo:if");
							
							collectionVo.setFeeNamelist(feeNameList);
							
							
							}
							
						
						else{
							
							ps_feelist=conn.prepareStatement("SELECT amount_paid,MonthName FROM campus_tranport_fee_collection_details WHERE admissionNo=? and MonthName=? and accYear=?");
							ps_feelist.setString(1, addmissionno);
							ps_feelist.setString(2, HelperClass.getMonthFullName(Integer.toString(i)));
							ps_feelist.setString(3, accyearId);
							System.out.println("collectionVo:ps_feelist "+ps_feelist);
							rs_feelist=ps_feelist.executeQuery();
							while(rs_feelist.next()){
								FeeNameVo feeNameVo=new FeeNameVo();
								count++;
								feeNameVo.setSno(count);
								feeNameVo.setActualAmt(rs_feelist.getDouble("amount_paid"));
								feeNameVo.setStatus("Paid");
								feeNameVo.setPaidDate(paidDate);
								feeNameVo.setRecieptNo(recieptNo);
								feeNameVo.setTerm(termname);
								feeNameVo.setTermId(termId);
								feeNameVo.setMonthName(rs_feelist.getString("MonthName"));
								feeNameList.add(feeNameVo);
							}
							System.out.println("collectionVo:else");
							collectionVo.setFeeNamelist(feeNameList);
						}
						
					
						
					}
					
					System.out.println("collectionVo:oute");
				}
				if(feeNameList.size()<1){
					collectionVo.setFeeNamelist(feeNameList);
				}
				
				System.out.println("collectionVo:outer dedder");
			}
			
			JSONArray array=new JSONArray();
			
			array.put(collectionVo.getFeeNamelist());
			
		
			
			System.out.println("collectionVo :: "+array);
			
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rs_getfeeInformation != null&& (!rs_getfeeInformation.isClosed())) {
					rs_getfeeInformation.close();
				}
				if (rs_feelist != null&& (!rs_feelist.isClosed())) {
					rs_feelist.close();
				}
				if (rs_collection_count != null&& (!rs_collection_count.isClosed())) {
					rs_collection_count.close();
				}
				
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
				}
				if (ps_feeInformation != null&& (!ps_feeInformation.isClosed())) {
					ps_feeInformation.close();
				}
				if (ps_feelist != null&& (!ps_feelist.isClosed())) {
					ps_feelist.close();
				}
				if (ps_collection_count != null&& (!ps_collection_count.isClosed())) {
					ps_collection_count.close();
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
				+ " Control in FeeCollectionDaoImpl: getTranportFeeCollectionAmount: Ending");
		
		return collectionVo;
	}

	@Override
	public String saveTransportFeeCollection(FeeCollectionVo collectionVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: saveTransportFeeCollection : Starting");
		Connection conn = null;
		PreparedStatement ps_insertPlan = null;
		PreparedStatement getReiptPs=null;
		PreparedStatement transportdetails = null;
		ResultSet getReiptRs=null;
		int count=0;
		String status=null;
		int recieptNo=0;
		double advance=0.0;
		double balance=0.0;
		int countTrans=0;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			conn.setAutoCommit(false);
			if(collectionVo.getRefundstatus()==null || collectionVo.getRefundstatus().equalsIgnoreCase("null"))
			{
				 getReiptPs=conn.prepareStatement("SELECT CASE WHEN  MAX(reciept_no) IS NULL THEN '1001' ELSE MAX(reciept_no) END reciept_no FROM campus_tranport_fee_collection_details");
				 getReiptRs=getReiptPs.executeQuery();
				while(getReiptRs.next()){
					recieptNo=getReiptRs.getInt("reciept_no")+1;
				}
				transportdetails=conn.prepareStatement("INSERT INTO campus_transport_fees_payments(receiptno,totalamt,paidAmount,balance,advance) VALUES(?,?,?,?,?)");
				transportdetails.setInt(1, recieptNo);
				transportdetails.setDouble(2, collectionVo.getTot_actual_amt());
				if(collectionVo.getTot_actual_amt()>collectionVo.getCurrent_payment()){
					balance=collectionVo.getTot_actual_amt()-collectionVo.getCurrent_payment();
				}
				else{
					advance=collectionVo.getCurrent_payment()-collectionVo.getTot_actual_amt();
				}
				transportdetails.setDouble(3, collectionVo.getCurrent_payment());
				transportdetails.setDouble(4, balance);
				transportdetails.setDouble(5, advance);
				 countTrans=transportdetails.executeUpdate();
				 transportdetails.close();
				 
			if(countTrans>0){	 
			for(int i=0;i<collectionVo.getTermIdArray().length;i++){
			ps_insertPlan = conn.prepareStatement("INSERT INTO campus_tranport_fee_collection_details(admissionNo,accYear,termcode,is_paid,totalamount,amount_paid,balance_amount,paidDate,createdby,createdtime,MonthName,reciept_no,modeofpayment,bankname,dd_cheque_date,dd_cheque_no) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps_insertPlan.setString(1,collectionVo.getAddmissionno());
			ps_insertPlan.setString(2,collectionVo.getAccYear());
			ps_insertPlan.setString(3,collectionVo.getTermIdArray()[i]);
			ps_insertPlan.setString(4,"Y");
			ps_insertPlan.setDouble(5,Double.parseDouble(collectionVo.getMonthlyAmount()[i]));
			ps_insertPlan.setDouble(6,Double.parseDouble(collectionVo.getMonthlyAmount()[i]));
			ps_insertPlan.setDouble(7,Double.parseDouble(collectionVo.getMonthlyAmount()[i])-Double.parseDouble(collectionVo.getMonthlyAmount()[i]));	
			ps_insertPlan.setDate(8,HelperClass.getCurrentSqlDate());
			ps_insertPlan.setString(9,collectionVo.getUserID());
			ps_insertPlan.setTimestamp(10,HelperClass.getCurrentTimestamp());
			ps_insertPlan.setString(11, collectionVo.getMonthName()[i]);
			ps_insertPlan.setInt(12, recieptNo);
			ps_insertPlan.setString(13, collectionVo.getPaymentMode());
			ps_insertPlan.setString(14, collectionVo.getDd_cheque_bank());
			ps_insertPlan.setString(15, collectionVo.getDd_cheque_date());
			ps_insertPlan.setString(16, collectionVo.getPaymentPatriculars());
			System.out.println("ps_insertPlan:::::"+ps_insertPlan);
			
			count=ps_insertPlan.executeUpdate();
			
			if(count>0){
				status="true";
			}else{
				status="false";
			}
			}
			conn.commit();
			}
			else{
				status="false";
			}
			}
			else{
				PreparedStatement transportdetailsDelete=null;
				double subAmount=0.0;
				double subPaidAmount=0.0;
				int cnt=0;
				transportdetails=conn.prepareStatement("SELECT * FROM campus_transport_fees_payments WHERE receiptno=?");
				transportdetails.setString(1, collectionVo.getRefrecieptNo());
				ResultSet rs=transportdetails.executeQuery();
				if(rs.next()){
					subAmount=rs.getDouble("totalamt")-Double.parseDouble(collectionVo.getMonthlyAmount()[0]);
					subPaidAmount=rs.getDouble("paidAmount")-Double.parseDouble(collectionVo.getMonthlyAmount()[0]);
				}
				rs.close();
				transportdetails.close();
				if(subAmount>0){
					transportdetailsDelete=conn.prepareStatement("UPDATE campus_transport_fees_payments SET totalamt=?,paidAmount=? WHERE receiptno=?");
					transportdetailsDelete.setDouble(1, subAmount);
					transportdetailsDelete.setDouble(2, subPaidAmount);
					transportdetailsDelete.setString(3, collectionVo.getRefrecieptNo());
					 cnt=transportdetailsDelete.executeUpdate();
					
				
				}
				else{
					 transportdetailsDelete=conn.prepareStatement("DELETE FROM campus_transport_fees_payments WHERE receiptno=?");
					transportdetailsDelete.setString(1, collectionVo.getRefrecieptNo());
					 cnt=transportdetailsDelete.executeUpdate();
					
				}
				for(int i=0;i<collectionVo.getTermIdArray().length;i++){
					ps_insertPlan = conn.prepareStatement("DELETE FROM campus_tranport_fee_collection_details WHERE admissionNo=? AND accYear=? AND termcode=? AND MonthName=?");
					ps_insertPlan.setString(1,collectionVo.getAddmissionno());
					ps_insertPlan.setString(2,collectionVo.getAccYear());
					ps_insertPlan.setString(3,collectionVo.getTermIdArray()[i]);
					ps_insertPlan.setString(4, collectionVo.getMonthName()[i]);
					System.out.println("ps_insertPlan:::::"+ps_insertPlan);
					
					count=ps_insertPlan.executeUpdate();
					
					if(count>0){
						status="true";
					}else{
						status="false";
					}
				}
				if(cnt>0){
					conn.commit();
				}
				transportdetailsDelete.close();
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (getReiptRs != null&& (!getReiptRs.isClosed())) {
					getReiptRs.close();
				}
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
				}
				if (getReiptPs != null&& (!getReiptPs.isClosed())) {
					getReiptPs.close();
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
				+ " Control in FeeCollectionDaoImpl: saveTransportFeeCollection: Ending");
		
		return status;
	}

	@Override
	public ArrayList<FeeNameVo> getFeePaidDetails(String feeCodeDetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getFeeCollectionDetails : Starting");
		
		String studentId=feeCodeDetails.split(",")[0];
		String accyearId=feeCodeDetails.split(",")[1];
		String classId=feeCodeDetails.split(",")[2];
		String termId=feeCodeDetails.split(",")[3];
		String specialization=feeCodeDetails.split(",")[4];
		
		PreparedStatement ps_feelist=null;
		ResultSet rs_feelist=null;
		
		Connection conn = null;
		ArrayList<FeeNameVo> feeCollectionList=new ArrayList<FeeNameVo>();
		int count=0;
		PreparedStatement ps1= null;
		ResultSet rs1=null;
		String feeCollectionCode=null;
		try {
			
				conn = JDBCConnection.getSeparateGodaddyConnection();
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
				+ " Control in FeeCollectionDaoImpl: getFeeCollectionDetails: Ending");
		
		return feeCollectionList;
	}

	public String addScholorshipStudent(StudentConcessionVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: addScholorshipStudent : Starting");
		Connection conn = null;
		PreparedStatement ps_insertPlan = null;
		PreparedStatement ps_check=null;
		ResultSet rs_check=null;
		int count=0;
		int countcheck=0;
		String status=null;
		
		try {
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			ps_check=conn.prepareStatement("SELECT COUNT(*) FROM campus_scholorship WHERE admissionNo=? AND academic_year=?");
		
			ps_check.setString(1, vo.getAdmissionNo());
			ps_check.setString(2, vo.getAcademicYear());
			rs_check=ps_check.executeQuery();
			if(rs_check.next()){
				countcheck=rs_check.getInt(1);
			}
		if(countcheck ==0){	
			for(int i=0;i<vo.getConcessionAmount().split(",").length;i++){
				
			
			ps_insertPlan=conn.prepareStatement("INSERT INTO campus_scholorship (admissionNo,classId,termcode,feecode,concessionType,concession,academic_year) VALUES(?,?,?,?,?,?,?)");
			ps_insertPlan.setString(1, vo.getAdmissionNo());
			ps_insertPlan.setString(2, vo.getClassId());
			ps_insertPlan.setString(3, vo.getTermcode().split(",")[i]);
			ps_insertPlan.setString(4, vo.getFeecode().split(",")[i]);
			ps_insertPlan.setString(5, vo.getContype());
			ps_insertPlan.setString(6, vo.getConcessionAmount().split(",")[i]);
			ps_insertPlan.setString(7, vo.getAcademicYear());
			count=ps_insertPlan.executeUpdate();
			if(count>0){
				status="true";
			}
			else{
				status="false";
			}
		}
		
		}
		else{
			PreparedStatement pstmtd=conn.prepareStatement("DELETE FROM campus_scholorship WHERE admissionNo=? AND academic_year=?");
			pstmtd.setString(1, vo.getAdmissionNo());
			pstmtd.setString(2, vo.getAcademicYear());
			int abc=pstmtd.executeUpdate();
			pstmtd.close();
			for(int i=0;i<vo.getConcessionAmount().split(",").length;i++){
				
				
				ps_insertPlan=conn.prepareStatement("INSERT INTO campus_scholorship (admissionNo,classId,termcode,feecode,concessionType,concession,academic_year) VALUES(?,?,?,?,?,?,?)");
				ps_insertPlan.setString(1, vo.getAdmissionNo());
				ps_insertPlan.setString(2, vo.getClassId());
				ps_insertPlan.setString(3, vo.getTermcode().split(",")[i]);
				ps_insertPlan.setString(4, vo.getFeecode().split(",")[i]);
				ps_insertPlan.setString(5, vo.getContype());
				ps_insertPlan.setString(6, vo.getConcessionAmount().split(",")[i]);
				ps_insertPlan.setString(7, vo.getAcademicYear());
				count=ps_insertPlan.executeUpdate();
				if(count>0){
					status="true";
				}
				else{
					status="false";
				}
			}
			
		}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
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
				+ " Control in FeeCollectionDaoImpl: addScholorshipStudent: Ending");
		
		return status;
	}

	public String deleteScholorDetails(AddFeeVO vo) {
		// TODO Auto-generated method stub
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: deleteScholorDetails : Starting");
		Connection conn = null;
		PreparedStatement ps_insertPlan = null;
		int count=0;
		String status=null;
		
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			for(int i=0;i<vo.getGetDataArray().length;i++){
			ps_insertPlan=conn.prepareStatement("DELETE FROM campus_scholorship WHERE admissionNo=? AND academic_year=?");
			ps_insertPlan.setString(1, vo.getGetDataArray()[i]);
			ps_insertPlan.setString(2, vo.getAccYearArray()[i]);
			count=ps_insertPlan.executeUpdate();
			if(count>0){
				status="true";
			}
			else{
				status="false";
			}
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
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
				+ " Control in FeeCollectionDaoImpl: deleteScholorDetails: Ending");
		
		return status;
	}

	public List<StudentRegistrationVo> studentSearchbyadmissionNo(
		StudentRegistrationVo registrationVo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : studentSearchbyadmissionNo Starting");
	String searchTerm = registrationVo.getSearchTerm() + "%";
	List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pstmObj = null;
	ResultSet rs = null;
	Connection conn = null;
	try {

		conn = JDBCConnection.getSeparateGodaddyConnection();
		pstmObj = conn.prepareStatement("SELECT student_admissionno_var FROM campus_student  WHERE student_admissionno_var LIKE ? ");
		pstmObj.setString(1, searchTerm);
		rs = pstmObj.executeQuery();

		while (rs.next()) {
			StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
			studentRegistrationVo.setAdmissionNo(rs.getString("student_admissionno_var"));
			registrationList.add(studentRegistrationVo);

		}
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmObj != null && (!pstmObj.isClosed())) {
				pstmObj.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : studentSearchbyadmissionNo Ending");

	return registrationList;
}

	   
	public ArrayList<AddFeeVO> getDefaulterFeeList(String locId,String classId, String divId, String termId, String accId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getDefaulterFeeList : Starting");
		Connection conn=null;
		PreparedStatement psmt = null;
		ResultSet rs =null;
		ArrayList<AddFeeVO> list = new ArrayList<AddFeeVO>();
		try{
			conn=JDBCConnection.getSeparateConnection();
			psmt=conn.prepareStatement("SELECT CONCAT(cs.student_fname_var, ' ',student_lname_var) AS studentName,cs.`student_admissionno_var`,cs.`student_id_int`,l.Location_Name,cc.classdetails_name_var,ccs.classsection_name_var,t.`termname`,t.`termid`,t.enddate,csc.`classdetail_id_int`,csc.fms_acadamicyear_id_int,csc.locationId,csc.specilization FROM `campus_student` cs  JOIN `campus_student_classdetails` csc ON cs.student_id_int = csc.student_id_int   JOIN campus_classdetail cc ON (csc.`classdetail_id_int`=cc.classdetail_id_int AND csc.locationId=cc.locationId)  JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int   JOIN `campus_location` l ON l.`Location_Id`=cs.`locationId`   JOIN `campus_fee_termdetails` t ON t.`locationId`=cs.`locationId` WHERE cs.`student_id_int`  NOT IN (SELECT `admissionNo` FROM `campus_fee_collection` WHERE `accYear`=? AND `termcode`= ?) AND cs.`student_admissionno_var` NOT IN (SELECT admissionNo FROM campus_scholorship WHERE concessionType='full' AND academic_year=?) AND csc.fms_acadamicyear_id_int=? AND cs.`locationId`= ? AND csc.classdetail_id_int LIKE ? AND ccs.classsection_id_int LIKE ? AND t.`termid`=? order by LENGTH(csc.classdetail_id_int),csc.classdetail_id_int,LENGTH(csc.classsection_id_int),csc.classsection_id_int");
			
			psmt.setString(1, accId);
			psmt.setString(2, termId);
			psmt.setString(3, accId);
			psmt.setString(4, accId);
			psmt.setString(5, locId);
			psmt.setString(6, classId);
			psmt.setString(7, divId);
			psmt.setString(8, termId);
			System.out.println("&&&&&&&&&&&&  **************  ::: -- :::"+psmt);
			rs=psmt.executeQuery();
			while(rs.next()){
					double dueAmt=0.0;
					double fineAmt=0.0;
				
					PreparedStatement psmt1 = conn.prepareStatement("SELECT SUM(feeAmount) dueAmt FROM `campus_fee_setupdetails` WHERE `feeSettingCode` IN (SELECT `feeSettingcode` FROM `campus_fee_setup` WHERE `ClassCode`=? AND `AccyearCode`=? AND `locationId`=? AND `Termcode` IN (SELECT termid FROM `campus_fee_termdetails` WHERE enddate<=? AND accyear=? AND locationId=? AND termid NOT IN(SELECT termcode FROM `campus_fee_collection` WHERE admissionNo=?)) AND specialization=?)");
					psmt1.setString(1, rs.getString("classdetail_id_int"));
					psmt1.setString(2, rs.getString("fms_acadamicyear_id_int"));
					psmt1.setString(3, rs.getString("locationId"));
					psmt1.setString(4, rs.getString("enddate"));
					psmt1.setString(5, accId);
					psmt1.setString(6, locId);
					
					psmt1.setString(7, rs.getString("student_id_int"));
					psmt1.setString(8, rs.getString("specilization"));
					ResultSet rs1 = psmt1.executeQuery();	 
					while(rs1.next()){
						dueAmt=rs1.getDouble("dueAmt");
						PreparedStatement pstmtcon=conn.prepareStatement("SELECT * FROM campus_scholorship WHERE admissionNo=? AND termcode=? AND academic_year=?");
						pstmtcon.setString(1, rs.getString("student_admissionno_var"));
						pstmtcon.setString(2, termId);
						pstmtcon.setString(3, accId);
						
						ResultSet rscon=pstmtcon.executeQuery();
						if(rscon.next()) {
							dueAmt=dueAmt-(rscon.getDouble("concession"));
						}
						if (rscon != null && (!rscon.isClosed())) {
							rscon.close();
						}
						if (pstmtcon != null && (!pstmtcon.isClosed())) {
							pstmtcon.close();
						}
						
					}
					rs1.close();
					psmt1.close();
					
					psmt1 = conn.prepareStatement("SELECT * FROM `campus_fineconfiguration` WHERE termid IN (SELECT termid FROM `campus_fee_termdetails` WHERE enddate<=? AND accyear=? AND locationId=? AND termid NOT IN(SELECT termcode FROM `campus_fee_collection` WHERE admissionNo=?)) AND classId=?");
					psmt1.setString(1, rs.getString("enddate"));
					psmt1.setString(2, accId);
					psmt1.setString(3, locId);
					psmt1.setString(4, rs.getString("student_id_int"));
					psmt1.setString(5, rs.getString("classdetail_id_int"));
					rs1 = psmt1.executeQuery();	 
					while(rs1.next()){
						if(HelperClass.getCurrentSqlDate().compareTo(rs1.getDate("date")) > 0)
							fineAmt+=rs1.getDouble("amount");
					}
					
					
					rs1.close();
					psmt1.close();
					
					
					
					
					
				AddFeeVO vo = new AddFeeVO();
				vo.setAdmissionNo(rs.getString("student_admissionno_var"));
				vo.setStudentName(rs.getString("studentName"));
				vo.setLocationName(rs.getString("Location_Name"));
				vo.setClassName(rs.getString("classdetails_name_var"));
				vo.setDivisionName(rs.getString("classsection_name_var"));
				vo.setTermName(rs.getString("termname"));
				vo.setDueAmt(dueAmt+fineAmt);
				
				
				psmt1=conn.prepareStatement("SELECT cpr.`parentid`,cpr.`relationship`,cp.FatherName,cp.mobileno,cp.student_mothername_var,cp.student_mothermobileno_var,cp.student_gaurdianname_var,cp.student_gardian_mobileno FROM `campus_parentchildrelation` cpr JOIN `campus_parents` cp ON cpr.parentid=cp.ParentID WHERE cpr.stu_addmissionNo=?");
				psmt1.setString(1, rs.getString("student_id_int"));
				rs1=psmt1.executeQuery();
				if(rs1.next()) {
					if(rs1.getString("mobileNo") !=null && !rs1.getString("mobileNo").trim().equalsIgnoreCase("")) {
						vo.setName(rs1.getString("mobileNo"));
					}
					else if(rs1.getString("student_mothermobileno_var") !=null && !rs1.getString("student_mothermobileno_var").trim().equalsIgnoreCase("")) {
						vo.setName(rs1.getString("student_mothermobileno_var"));
					}
					else {
						vo.setName(rs1.getString("student_gardian_mobileno"));
					} 
				}
				
				rs1.close();
				psmt1.close();
				
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
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
				+ " Control in FeeCollectionDaoImpl: getDefaulterFeeList: Ending");
		return list;
	}

	public ArrayList<AddFeeVO> getAdvanceOrBalanceForTransportFee(String stuId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getAdvanceOrBalanceForTransportFee : Starting");
		Connection conn=null;
		PreparedStatement psmt = null;
		ResultSet rs =null;
		ArrayList<AddFeeVO> list = new ArrayList<AddFeeVO>();
		try{
			double dueAmt=0.0;
			double advance=0.0;
			conn=JDBCConnection.getSeparateGodaddyConnection();
			psmt=conn.prepareStatement("SELECT * FROM campus_tranport_fee_collection_details WHERE admissionNo=? ORDER BY createdtime DESC LIMIT 1");
			psmt.setString(1, stuId);
			rs=psmt.executeQuery();
			
			if(rs.next()){
					
					PreparedStatement psmt1 = conn.prepareStatement("SELECT * FROM campus_transport_fees_payments WHERE receiptno=?");
					psmt1.setString(1, rs.getString("reciept_no"));
					ResultSet rs1 = psmt1.executeQuery();	 
					while(rs1.next()){
						dueAmt=rs1.getDouble("balance");
						advance=rs1.getDouble("advance");
					}
				AddFeeVO vo = new AddFeeVO();
				vo.setAdvanceAmt(advance);
				vo.setDueAmt(dueAmt);
				list.add(vo);
			}
			else {
				AddFeeVO vo = new AddFeeVO();
				vo.setAdvanceAmt(advance);
				vo.setDueAmt(dueAmt);
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
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
				+ " Control in FeeCollectionDaoImpl: getAdvanceOrBalanceForTransportFee: Ending");
		return list;
	}

	public ArrayList<AddFeeVO> getfeeCancellationList(String studentId, String accYear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getfeeCancellationList : Starting");
		Connection conn=null;
		PreparedStatement psmt = null;
		ResultSet rs =null;
		ArrayList<AddFeeVO> list = new ArrayList<AddFeeVO>();
		try{
			
			conn=JDBCConnection.getSeparateGodaddyConnection();
			psmt=conn.prepareStatement("SELECT cfc.*,CONCAT(cs.student_fname_var, ' ',cs.student_lname_var) studentName,cs.student_admissionno_var,cc.classdetails_name_var,cft.termname FROM campus_fee_collection cfc JOIN campus_student cs ON cfc.admissionNo=cs.student_id_int JOIN campus_student_classdetails csc ON (cfc.admissionNo=csc.student_id_int AND cfc.accYear=csc.fms_acadamicyear_id_int) JOIN campus_classdetail cc ON (csc.classdetail_id_int=cc.classdetail_id_int AND csc.locationId=cc.locationId) JOIN campus_fee_termdetails cft ON cfc.termcode=cft.termid WHERE cfc.admissionNo=? AND cfc.accYear=? ");
			psmt.setString(1, studentId);
			psmt.setString(2, accYear);
			rs=psmt.executeQuery();
			
			while(rs.next()){		
				AddFeeVO vo = new AddFeeVO();
				vo.setId(rs.getString("feeCollectionCode"));
				vo.setAdmissionNo(rs.getString("student_admissionno_var"));
				vo.setStudentName(rs.getString("studentName"));
				vo.setClassName(rs.getString("classdetails_name_var"));
				vo.setTermName(rs.getString("termname"));
				vo.setPaidAmt(rs.getDouble("amount_paid"));
				
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
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
				+ " Control in FeeCollectionDaoImpl: getfeeCancellationList: Ending");
		return list;
	}

	public String cancelFee(String feecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getfeeCancellationList : Starting");
		Connection conn=null;
		PreparedStatement psmt = null;
		ResultSet rs =null;
		String status = null;
		try{
			String studentId=null;
			String accYear=null;
			String termcode=null;
			
			conn=JDBCConnection.getSeparateGodaddyConnection();
			
			conn.setAutoCommit(false);
			psmt=conn.prepareStatement("SELECT admissionNo,accYear,termcode FROM campus_fee_collection WHERE feeCollectionCode=?");
			psmt.setString(1, feecode);
			rs=psmt.executeQuery();
			while(rs.next()){
				studentId=rs.getString("admissionNo");
				accYear=rs.getString("accYear");
				termcode=rs.getString("termcode");
			}
			PreparedStatement delete=conn.prepareStatement("DELETE FROM campus_fee_collection WHERE feeCollectionCode=?");
			delete.setString(1, feecode);
			int a=delete.executeUpdate();
			delete.close();
			
			
			delete=conn.prepareStatement("DELETE FROM campus_fee_indetail WHERE admissionNo=? AND term_id=? AND accYear=?");
			delete.setString(1, studentId);
			delete.setString(2, termcode);
			delete.setString(3, accYear);
			int d=delete.executeUpdate();
			delete.close();
			
			delete=conn.prepareStatement("DELETE FROM campus_fee_collection_details WHERE admissionNo=? AND termcode=? AND accYear=?");
			delete.setString(1, studentId);
			delete.setString(2, termcode);
			delete.setString(3, accYear);
			int e=delete.executeUpdate();
			
			if(a > 0  && d>0 && e>0){
				conn.commit();
				status="true";
			}else{
				conn.rollback();
			}
			delete.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
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
				+ " Control in FeeCollectionDaoImpl: getfeeCancellationList: Ending");
		return status;
	}

	public String addScholorshipStudentForEqual(StudentConcessionVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: addScholorshipStudentForEqual : Starting");
		Connection conn = null;
		PreparedStatement ps_insertPlan = null;
		PreparedStatement ps_check=null;
		ResultSet rs_check=null;
		int count=0;
		int countcheck=0;
		String status=null;
		
		try {
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			ps_check=conn.prepareStatement("SELECT COUNT(*) FROM campus_scholorship WHERE admissionNo=? AND academic_year=?");
		
			ps_check.setString(1, vo.getAdmissionNo());
			ps_check.setString(2, vo.getAcademicYear());
			rs_check=ps_check.executeQuery();
			if(rs_check.next()){
				countcheck=rs_check.getInt(1);
			}
		if(countcheck ==0){	
			
			ps_insertPlan=conn.prepareStatement("INSERT INTO campus_scholorship (admissionNo,classId,termcode,feecode,concessionType,concession,academic_year) VALUES(?,?,?,?,?,?,?)");
			ps_insertPlan.setString(1, vo.getAdmissionNo());
			ps_insertPlan.setString(2, vo.getClassId());
			ps_insertPlan.setString(3, vo.getTermcode());
			ps_insertPlan.setString(4, vo.getFeecode());
			ps_insertPlan.setString(5, vo.getContype());
			ps_insertPlan.setString(6, vo.getConcessionAmount());
			ps_insertPlan.setString(7, vo.getAcademicYear());
			count=ps_insertPlan.executeUpdate();
			if(count>0){
				status="true";
			}
			else{
				status="false";
			}
		
		
		}
		else{
			PreparedStatement pstmtd=conn.prepareStatement("DELETE FROM campus_scholorship WHERE admissionNo=? AND academic_year=?");
			pstmtd.setString(1, vo.getAdmissionNo());
			pstmtd.setString(2, vo.getAcademicYear());
			int abc=pstmtd.executeUpdate();
			pstmtd.close();
		
				
				ps_insertPlan=conn.prepareStatement("INSERT INTO campus_scholorship (admissionNo,classId,termcode,feecode,concessionType,concession,academic_year) VALUES(?,?,?,?,?,?,?)");
				ps_insertPlan.setString(1, vo.getAdmissionNo());
				ps_insertPlan.setString(2, vo.getClassId());
				ps_insertPlan.setString(3, vo.getTermcode());
				ps_insertPlan.setString(4, vo.getFeecode());
				ps_insertPlan.setString(5, vo.getContype());
				ps_insertPlan.setString(6, vo.getConcessionAmount());
				ps_insertPlan.setString(7, vo.getAcademicYear());
				count=ps_insertPlan.executeUpdate();
				if(count>0){
					status="true";
				}
				else{
					status="false";
				}
			
			
		
			
		}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
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
				+ " Control in FeeCollectionDaoImpl: addScholorshipStudentForEqual: Ending");
		
		return status;
	}

}
