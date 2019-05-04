package com.centris.campus.daoImpl;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.runner.Request;

import com.centris.campus.dao.ReportsMenuDao;
import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.pojo.FeeStatusReportPojo;
import com.centris.campus.pojo.MarksPOJO;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.ITFeeVo;
import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.ReligionCasteCasteCategoryVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.TransportVo;
import com.centris.campus.vo.ViewallSubjectsVo;
import com.itextpdf.text.log.SysoLogger;

public class ReportsMenuDaoImpl implements ReportsMenuDao{
	
	private static final Logger logger = Logger.getLogger(ReportsMenuDaoImpl.class);

	@Override
	public ArrayList<ReportMenuVo> getAccYears() {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getAccYears : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ArrayList<ReportMenuVo> accYearList=new ArrayList<ReportMenuVo>();
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_ACCYEAR);
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					
					ReportMenuVo yearVo=new ReportMenuVo();
					yearVo.setAccyearId(rs.getString("acadamic_id").trim());
					yearVo.setAccyearname(rs.getString("acadamic_year"));
					
					accYearList.add(yearVo);
					
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
					if (pstmt != null&& (!pstmt.isClosed())) {
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

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getAccYears : Ending");
			
			return accYearList;
		}

	@Override
	public ArrayList<ReportMenuVo> getStream() {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getStream : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ArrayList<ReportMenuVo> streamList=new ArrayList<ReportMenuVo>();
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STREAMS);
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					
					ReportMenuVo streamVo=new ReportMenuVo();
					
					streamVo.setStreamId(rs.getString("classstream_id_int"));
					streamVo.setStreamname(rs.getString("classstream_name_var"));
					
					streamList.add(streamVo);
					
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
					if (pstmt != null&& (!pstmt.isClosed())) {
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

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getStream : Ending");
			
			return streamList;
		}

	public ArrayList<ReportMenuVo> getStudentClass(String location) {
		   
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentClass : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> classList=new ArrayList<ReportMenuVo>();
	 	
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASS);
			pstmt.setString(1,location);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				ReportMenuVo classVo=new ReportMenuVo();
				
				classVo.setClassId(rs.getString("classdetail_id_int"));
				classVo.setClassname(rs.getString("classdetails_name_var"));
				
				classList.add(classVo);
				
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
				if (pstmt != null&& (!pstmt.isClosed())) {
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentClass : Ending");
		
		return classList;
	}
	
	@Override
	public ArrayList<ReportMenuVo> getClassesByStream(String streamId,String location) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getClassesByStream : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ArrayList<ReportMenuVo> streamList=new ArrayList<ReportMenuVo>();
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASSES);
				pstmt.setString(1, streamId);
				
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					
					ReportMenuVo streamVo=new ReportMenuVo();
					
					streamVo.setClassId(rs.getString("classdetail_id_int"));
					streamVo.setClassname(rs.getString("classdetails_name_var"));
					
					streamList.add(streamVo);
					
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
					if (pstmt != null&& (!pstmt.isClosed())) {
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

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getClassesByStream : Ending");
			
			return streamList;
		}

	@Override
	public ArrayList<ReportMenuVo> getSectionsByClass(String classId,String location) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getSectionsByClass : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ArrayList<ReportMenuVo> streamList=new ArrayList<ReportMenuVo>();
		 	
			try {
				conn = JDBCConnection.getSeparateConnection();
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_SECTIONS);
				pstmt.setString(1, classId);
				pstmt.setString(2, location);
			
			System.out.println("getSectionsByClass="+pstmt);
				rs=pstmt.executeQuery();
				
				
				while(rs.next()){
					
					ReportMenuVo streamVo=new ReportMenuVo();
					
					streamVo.setSectionId(rs.getString("classsection_id_int"));
					streamVo.setSectionname(rs.getString("classsection_name_var"));
					
					streamList.add(streamVo);
					
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
					if (pstmt != null&& (!pstmt.isClosed())) {
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

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getSectionsByClass : Ending");
			
			return streamList;
		}

	@Override
	public ArrayList<StudentInfoVO> getStudentDetailsReport(ReportMenuForm reform) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getStudentDetailsReport : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ArrayList<StudentInfoVO> studentInfoList=new ArrayList<StudentInfoVO>();
			int count=0;
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENTSiNFORMATION);
				pstmt.setString(1, reform.getAccyear().trim());
				pstmt.setString(2, reform.getStream().trim());
				pstmt.setString(3, reform.getClassname().trim());
				pstmt.setString(4, reform.getSection().trim());
				
				
				
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					
					StudentInfoVO studentinfo=new StudentInfoVO();
					
					count++;
					studentinfo.setSno(count);
					studentinfo.setAdmissionno(rs.getString("student_admissionno_var").trim());
					studentinfo.setName(rs.getString("studentname").trim());
					studentinfo.setAge(rs.getString("student_age_int").trim());
					studentinfo.setDoj(rs.getString("student_doj_var").trim());
					studentinfo.setFathername(rs.getString("FatherName").trim());
					studentinfo.setFathermobno(rs.getString("mobileno").trim());
					studentinfo.setMothername(rs.getString("student_mothername_var").trim());
					studentinfo.setMonthermobno(rs.getString("student_mothermobileno_var").trim());
					studentinfo.setStudentId(rs.getString("student_id_int"));
					studentInfoList.add(studentinfo);
					
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
					if (pstmt != null&& (!pstmt.isClosed())) {
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

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getStudentDetailsReport : Ending");
			
			return studentInfoList;
		}

	@Override
	public ReportMenuVo getSelectedItems(ReportMenuForm reform) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getSelectedItems : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ReportMenuVo selecteditems=new ReportMenuVo();
			int count=0;
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_SELECTED_ITEMS);
				pstmt.setString(1, reform.getAccyear().trim());
				pstmt.setString(2, reform.getStream().trim());
				pstmt.setString(3, reform.getClassname().trim());
				pstmt.setString(4, reform.getSection().trim());
				pstmt.setString(5, reform.getLocation().trim());
				
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					
					count++;
					selecteditems.setAccyearname(rs.getString("acadamic_year"));
					selecteditems.setStreamname(rs.getString("classstream_name_var"));
					selecteditems.setClassname(rs.getString("classdetails_name_var"));
					selecteditems.setSectionname(rs.getString("classsection_name_var"));
					selecteditems.setLocationName(rs.getString("Location_Name"));
					selecteditems.setAccyearId(reform.getAccyear().trim());
					selecteditems.setStreamId(reform.getStream().trim());
					selecteditems.setClassId(reform.getClassname().trim());
					selecteditems.setSectionId(reform.getSection().trim());
					selecteditems.setLocationId(reform.getLocationid());
					
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
					if (pstmt != null&& (!pstmt.isClosed())) {
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

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getSelectedItems : Ending");
			
			return selecteditems;
		}

	@Override
	public HashMap<String, ArrayList<FeeReportDetailsVo>> getStdFeeStatusReportDetails(
			ReportMenuForm reform) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getStdFeeStatusReportDetails : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			
			PreparedStatement ps_classfesAmt = null;
			ResultSet rs_classfesAmt=null;
			
			PreparedStatement ps_stagefesAmt = null;
			ResultSet rs_stagefesAmt=null;
			
			Connection conn = null;
			ArrayList<FeeReportDetailsVo> feeStatusList=new ArrayList<FeeReportDetailsVo>();
			HashMap<String, ArrayList<FeeReportDetailsVo>> feeMap=new HashMap<String, ArrayList<FeeReportDetailsVo>>();
			int count=0;
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
				double classFeeAmt=0.0;
				
				ps_classfesAmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASS_FEE_AMOUNT);
				
				ps_classfesAmt.setString(1, reform.getClassname().trim());
				ps_classfesAmt.setString(2, reform.getAccyear().trim());
				ps_classfesAmt.setString(3, reform.getTerm().trim());
			
				
				
				rs_classfesAmt=ps_classfesAmt.executeQuery();
				
				while(rs_classfesAmt.next()){
					
					classFeeAmt=rs_classfesAmt.getInt("ActualAmount");
					
				}
				
				double stageFeeAmt=0.0;
				
				ps_stagefesAmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STAGE_FEE_AMOUNT);
				
				ps_stagefesAmt.setString(1, reform.getClassname().trim());
				ps_stagefesAmt.setString(2, reform.getAccyear().trim());
				ps_stagefesAmt.setString(3, reform.getTerm().trim());
			
				
				
				rs_stagefesAmt=ps_stagefesAmt.executeQuery();
				
				while(rs_stagefesAmt.next()){
					
					stageFeeAmt=rs_stagefesAmt.getInt("transportfee");
					
				}
				
				
				if(reform.getStatus().equalsIgnoreCase("ALL")){
					
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_FEE_STATUS_REPORT);
					pstmt.setString(1, reform.getTerm().trim());
					pstmt.setString(2, reform.getAccyear().trim());
					pstmt.setString(3, reform.getClassname().trim());
					if(reform.getSection().trim().equalsIgnoreCase("all")){
						pstmt.setString(4, "%%");
					}else{
						pstmt.setString(4, reform.getSection().trim());
					}
					pstmt.setString(5,reform.getStudentId());
					
					rs=pstmt.executeQuery();
					
					while(rs.next()){
						
					
						FeeReportDetailsVo vo=new FeeReportDetailsVo();
					
					
					count++;
					
					vo.setSno(count);
					vo.setAdmissionNo(rs.getString("student_admissionno_var"));
					vo.setStudentName(rs.getString("studentname"));
					vo.setTotalAmount(rs.getString("totalamount"));
					vo.setStudentId(rs.getString("student_id_int"));
					vo.setActualAmount(rs.getDouble("actualamount"));
					vo.setBalanceAmount(rs.getDouble("balance_amount"));
					vo.setPaidAmount(Math.round(rs.getDouble("amount_paid")));
					vo.setClassName(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
					vo.setSectionname(rs.getString("classsection_name_var"));
					vo.setSectioncode(rs.getString("classsection_id_int"));
					
					if(rs.getString("is_paid")=="N"||rs.getString("is_paid").equalsIgnoreCase("N"))
					{
					vo.setStatus("Not Paid");
					}
					else
					{
						vo.setStatus("Paid");	
					}
					
					
					feeStatusList.add(vo);
					
					}
					
					
					
					if(feeStatusList.size()!=0){
						
						feeMap.put(feeStatusList.get(feeStatusList.size()-1).getClassName()+"-"+feeStatusList.get(feeStatusList.size()-1).getSectionname(), feeStatusList);
					}
					
				}
				
				
				
				else if(reform.getStatus().equalsIgnoreCase("Paid")){
					
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_FEEPAID_STATUS_REPORT);
					pstmt.setString(1, reform.getClassname().trim());
					if(reform.getSection().trim().equalsIgnoreCase("all")){
						pstmt.setString(2, "%%");
					}else{
						pstmt.setString(2, reform.getSection().trim());
					}
					pstmt.setString(3, reform.getTerm().trim());
					pstmt.setString(4, reform.getAccyear().trim());
					pstmt.setString(5,reform.getStudentId());
					
					rs=pstmt.executeQuery();
					
					while(rs.next()){
					

						FeeReportDetailsVo vo=new FeeReportDetailsVo();
						
						if(feeStatusList.size()!=0){
						
						if(!(feeStatusList.get(feeStatusList.size()-1).getSectioncode().equalsIgnoreCase(rs.getString("classsection_id_int")))){
							
							feeMap.put(feeStatusList.get(feeStatusList.size()-1).getClassName()+"-"+feeStatusList.get(feeStatusList.size()-1).getSectionname(), feeStatusList);
							
							count=0;
							feeStatusList = new ArrayList<FeeReportDetailsVo>();
						}
						
						}
						
						count++;
						
						vo.setSno(count);
						vo.setAdmissionNo(rs.getString("student_admissionno_var"));
						vo.setStudentName(rs.getString("studentname"));
						vo.setActualAmount(Math.round(rs.getDouble("actualamount")));
						vo.setConcAmount(rs.getDouble("conc_amount"));
						vo.setPaidAmount(Math.round(rs.getDouble("totalamount")));
						vo.setPaidDate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
						vo.setBalanceAmount(Math.round(rs.getDouble("actualamount")-rs.getDouble("totalamount")-rs.getDouble("conc_amount")));
						vo.setStatus("Paid");
						vo.setClassName(rs.getString("classdetails_name_var"));
						vo.setSectioncode(rs.getString("classsection_id_int"));
						vo.setSectionname(rs.getString("classsection_name_var"));
						feeStatusList.add(vo);
						
					}
					
					if(feeStatusList.size()!=0){
						
						feeMap.put(feeStatusList.get(feeStatusList.size()-1).getClassName()+"-"+feeStatusList.get(feeStatusList.size()-1).getSectionname(), feeStatusList);
					}
					
				
				}else if(reform.getStatus().equalsIgnoreCase("NotPaid")){
					
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_FEENOTPAID_STATUS_REPORT);
					pstmt.setString(1, reform.getClassname().trim());
					if(reform.getSection().trim().equalsIgnoreCase("all")){
						pstmt.setString(2, "%%");
					}else{
						pstmt.setString(2, reform.getSection().trim());
					}
					pstmt.setString(3, reform.getTerm().trim());
					pstmt.setString(4, reform.getAccyear().trim());
					pstmt.setString(5,reform.getStudentId());
					rs=pstmt.executeQuery();
					
					while(rs.next()){
					

						FeeReportDetailsVo vo=new FeeReportDetailsVo();
						
						if(feeStatusList.size()!=0){
						
						if(!(feeStatusList.get(feeStatusList.size()-1).getSectioncode().equalsIgnoreCase(rs.getString("classsection_id_int")))){
							
							feeMap.put(feeStatusList.get(feeStatusList.size()-1).getClassName()+"-"+feeStatusList.get(feeStatusList.size()-1).getSectionname(), feeStatusList);
							
							count=0;
							feeStatusList = new ArrayList<FeeReportDetailsVo>();
						}
						
						}
						
						count++;
						
						vo.setSno(count);
						vo.setAdmissionNo(rs.getString("student_admissionno_var"));
						vo.setStudentName(rs.getString("studentname"));
						vo.setActualAmount(Math.round(classFeeAmt+stageFeeAmt));
						vo.setConcAmount(0.0);
						vo.setPaidDate("-");
						vo.setBalanceAmount(Math.round(classFeeAmt+stageFeeAmt));
						vo.setStatus("Not Paid");
						vo.setClassName(rs.getString("classdetails_name_var"));
						vo.setSectioncode(rs.getString("classsection_id_int"));
						vo.setSectionname(rs.getString("classsection_name_var"));
						feeStatusList.add(vo);
						
					}
					
					if(feeStatusList.size()!=0){
						
						feeMap.put(feeStatusList.get(feeStatusList.size()-1).getClassName()+"-"+feeStatusList.get(feeStatusList.size()-1).getSectionname(), feeStatusList);
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
					if (rs_classfesAmt != null&& (!rs_classfesAmt.isClosed())) {
						rs_classfesAmt.close();
					}
					if (rs_stagefesAmt != null&& (!rs_stagefesAmt.isClosed())) {
						rs_stagefesAmt.close();
					}
					if (pstmt != null&& (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (ps_classfesAmt != null&& (!ps_classfesAmt.isClosed())) {
						ps_classfesAmt.close();
					}
					if (ps_stagefesAmt  != null&& (!ps_stagefesAmt .isClosed())) {
						ps_stagefesAmt .close();
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
					+ " Control in ReportsMenuDaoImpl : getStdFeeStatusReportDetails : Ending");
			
			
			
			return feeMap;
		}
	
	@Override
	
	
	public ArrayList<FeeReportDetailsVo> getStdFeeStatusReportDownload(FeeStatusReportPojo feestatuspojo) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getStdFeeStatusReportDownload : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			
			PreparedStatement ps_classfesAmt = null;
			ResultSet rs_classfesAmt=null;
			
			PreparedStatement ps_stagefesAmt = null;
			ResultSet rs_stagefesAmt=null;
			
			Connection conn = null;
			ArrayList<FeeReportDetailsVo> feeStatusList=new ArrayList<FeeReportDetailsVo>();
			int count=0;
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
				double classFeeAmt=0.0;
				
				ps_classfesAmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASS_FEE_AMOUNT);
				
				ps_classfesAmt.setString(1, feestatuspojo.getClassname().trim());
				ps_classfesAmt.setString(2, feestatuspojo.getAccyear().trim());
				ps_classfesAmt.setString(3, feestatuspojo.getTerm().trim());
			
				
				
				rs_classfesAmt=ps_classfesAmt.executeQuery();
				
				while(rs_classfesAmt.next()){
					
					classFeeAmt=rs_classfesAmt.getInt("ActualAmount");
					
				}
				
				double stageFeeAmt=0.0;
				
				ps_stagefesAmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STAGE_FEE_AMOUNT);
				
				ps_stagefesAmt.setString(1, feestatuspojo.getClassname().trim());
				ps_stagefesAmt.setString(2, feestatuspojo.getAccyear().trim());
				ps_stagefesAmt.setString(3, feestatuspojo.getTerm().trim());
			
				
				
				rs_stagefesAmt=ps_stagefesAmt.executeQuery();
				
				while(rs_stagefesAmt.next()){
					
					stageFeeAmt=rs_stagefesAmt.getInt("transportfee");
					
				}
				
				if(feestatuspojo.getStatus().trim().equalsIgnoreCase("ALL")){
					
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_FEE_STATUS_REPORT);
					pstmt.setString(1, feestatuspojo.getTerm().trim());
					pstmt.setString(2, feestatuspojo.getAccyear().trim());
					pstmt.setString(3, feestatuspojo.getClassname().trim());
					if(feestatuspojo.getSection().trim().equalsIgnoreCase("all")){
						pstmt.setString(4, "%%");
					}else{
						pstmt.setString(4, feestatuspojo.getSection().trim());
					}
					pstmt.setString(5, feestatuspojo.getStudent().trim());
					
					rs=pstmt.executeQuery();
					
					while(rs.next()){
						
					
						FeeReportDetailsVo vo=new FeeReportDetailsVo();
						count++;
					
					vo.setSno(count);
					vo.setAdmissionNo(rs.getString("student_admissionno_var"));
					vo.setStudentName(rs.getString("studentname"));
					vo.setTotalAmount(rs.getString("totalamount"));
					vo.setActualAmount(rs.getDouble("actualamount"));
					vo.setBalanceAmount(rs.getDouble("balance_amount"));
					vo.setPaidAmount(Math.round(rs.getDouble("amount_paid")));
					vo.setClassName(rs.getString("classdetails_name_var"));
					vo.setSectionname(rs.getString("classsection_name_var"));
					vo.setSectioncode(rs.getString("classsection_id_int"));
					
					if(rs.getString("is_paid")=="N"||rs.getString("is_paid").equalsIgnoreCase("N"))
					{
					vo.setStatus("Not Paid");
					}
					else
					{
						vo.setStatus("Paid");	
					}
					
					
					feeStatusList.add(vo);
					
					}
					
					
					
					
				}else if(feestatuspojo.getStatus().trim().equalsIgnoreCase("Paid")){
					
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_FEEPAID_STATUS_REPORT);
					pstmt.setString(1, feestatuspojo.getClassname().trim());
					if(feestatuspojo.getSection().trim().equalsIgnoreCase("all")){
						pstmt.setString(2, "%%");
					}else{
						pstmt.setString(2, feestatuspojo.getSection().trim());
					}
					pstmt.setString(3, feestatuspojo.getTerm().trim());
					pstmt.setString(4, feestatuspojo.getAccyear().trim());
					pstmt.setString(5, feestatuspojo.getStudent().trim());
					
					rs=pstmt.executeQuery();
					
					while(rs.next()){
					

						FeeReportDetailsVo vo=new FeeReportDetailsVo();
											
						count++;
						
						vo.setSno(count);
						vo.setAdmissionNo(rs.getString("student_admissionno_var"));
						vo.setStudentName(rs.getString("studentname"));
						vo.setActualAmount(Math.round(rs.getDouble("actualamount")));
						vo.setConcAmount(rs.getDouble("conc_amount"));
						vo.setPaidAmount(Math.round(rs.getDouble("totalamount")));
						vo.setPaidDate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
						vo.setBalanceAmount(Math.round(rs.getDouble("actualamount")-rs.getDouble("totalamount")-rs.getDouble("conc_amount")));
						vo.setStatus("Paid");
						vo.setClassName(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
						vo.setSectioncode(rs.getString("classsection_id_int"));
						vo.setSectionname(rs.getString("classsection_name_var"));
						feeStatusList.add(vo);
						
					}
					
								
				}else if(feestatuspojo.getStatus().trim().equalsIgnoreCase("NotPaid")){
					
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_FEENOTPAID_STATUS_REPORT);
					pstmt.setString(1, feestatuspojo.getClassname().trim());
					if(feestatuspojo.getSection().trim().equalsIgnoreCase("all")){
						pstmt.setString(2, "%%");
					}else{
						pstmt.setString(2, feestatuspojo.getSection().trim());
					}
					pstmt.setString(3, feestatuspojo.getTerm().trim());
					pstmt.setString(4, feestatuspojo.getAccyear().trim());
					pstmt.setString(5, feestatuspojo.getStudent().trim());
					rs=pstmt.executeQuery();
					
					while(rs.next()){
					

						FeeReportDetailsVo vo=new FeeReportDetailsVo();
						
						count++;
						
						vo.setSno(count);
						vo.setAdmissionNo(rs.getString("student_admissionno_var"));
						vo.setStudentName(rs.getString("studentname"));
						vo.setActualAmount(Math.round(classFeeAmt+stageFeeAmt));
						vo.setConcAmount(0.0);
						vo.setPaidDate("-");
						vo.setBalanceAmount(Math.round(classFeeAmt+stageFeeAmt));
						vo.setStatus("Not Paid");
						vo.setClassName(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
						vo.setSectioncode(rs.getString("classsection_id_int"));
						vo.setSectionname(rs.getString("classsection_name_var"));
						feeStatusList.add(vo);
						
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
					if (rs_classfesAmt != null&& (!rs_classfesAmt.isClosed())) {
						rs_classfesAmt.close();
					}
					if (rs_stagefesAmt != null&& (!rs_stagefesAmt.isClosed())) {
						rs_stagefesAmt.close();
					}
					if (pstmt != null&& (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (ps_classfesAmt != null&& (!ps_classfesAmt.isClosed())) {
						ps_classfesAmt.close();
					}
					if (ps_stagefesAmt  != null&& (!ps_stagefesAmt .isClosed())) {
						ps_stagefesAmt .close();
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
					+ " Control in ReportsMenuDaoImpl : getStdFeeStatusReportDownload : Ending");
			
	
			
			return feeStatusList;
		}

	@Override
	public HashMap<String, ArrayList<MarksUploadVO>> getStdMarksDetails(
			ReportMenuForm reform) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getStdMarksDetails : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			
			Connection conn = null;
			ArrayList<MarksUploadVO> marksList=new ArrayList<MarksUploadVO>();
			
			HashMap<String, ArrayList<MarksUploadVO>> marksMap=new HashMap<String, ArrayList<MarksUploadVO>>();
			
			int count=0;
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
									
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_MARKS);
					pstmt.setString(1, reform.getExam().trim());
					pstmt.setString(2, reform.getClassname().trim());
					pstmt.setString(3, reform.getSection().trim());
					pstmt.setString(4, reform.getStudentId().trim());
					pstmt.setString(5, reform.getAccyear().trim());
					
					
					rs=pstmt.executeQuery();
					
					while(rs.next()){
					
						MarksUploadVO vo=new MarksUploadVO();
					
						count++;
						
						vo.setSno(count);
						vo.setAdmissionno(rs.getString("student_admissionno_var"));
						vo.setStudentname(rs.getString("studentName"));
						vo.setExamname(rs.getString("examname"));
						vo.setSubjectname(rs.getString("subjectName"));
						vo.setMaxmarks(rs.getString("maximum_marks"));
						vo.setReqmarks(rs.getString("required_marks"));
						vo.setScoredmarks(rs.getString("scoredmarks"));
						
						vo.setMarkspercent((Double.parseDouble(rs.getString("scoredmarks"))/Double.parseDouble(rs.getString("maximum_marks")))*100);
						
						
						marksList.add(vo);
						
					}
					
					if(marksList.size()!=0){
						marksMap.put(marksList.get(marksList.size()-1).getStudentname()+"-"+marksList.get(marksList.size()-1).getExamname(), marksList);
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
					if (pstmt != null&& (!pstmt.isClosed())) {
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

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getStdFeeStatusReportDetails : Ending");
			
			return marksMap;
		}
	
	@Override
	public  ArrayList<MarksUploadVO> getStdMarksDetailsDownload(MarksPOJO reform) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getStdMarksDetails : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			
			Connection conn = null;
			ArrayList<MarksUploadVO> marksList=new ArrayList<MarksUploadVO>();
			
			int count=0;
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
									
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_MARKS);
					pstmt.setString(1, reform.getExamid().trim());
					pstmt.setString(2, reform.getClassid().trim());
					pstmt.setString(3, reform.getSection().trim());
					pstmt.setString(4, reform.getStudentid().trim());
					pstmt.setString(5, reform.getAccyear().trim());
					
					
					rs=pstmt.executeQuery();
					
					while(rs.next()){
					
						MarksUploadVO vo=new MarksUploadVO();
					
						count++;
						
						vo.setSno(count);
						vo.setAdmissionno(rs.getString("student_admissionno_var"));
						vo.setStudentname(rs.getString("studentName")+"-"+rs.getString("examname"));
						vo.setExamname(rs.getString("examname"));
						vo.setSubjectname(rs.getString("subjectName"));
						vo.setMaxmarks(rs.getString("maximum_marks"));
						vo.setReqmarks(rs.getString("required_marks"));
						vo.setScoredmarks(rs.getString("scoredmarks"));
						
						vo.setMarkspercent((Double.parseDouble(rs.getString("scoredmarks"))/Double.parseDouble(rs.getString("maximum_marks")))*100);
						
						
						marksList.add(vo);
						
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
					if (pstmt != null&& (!pstmt.isClosed())) {
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

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getStdFeeStatusReportDetails : Ending");
			
			return marksList;
		}

	@Override
	public ArrayList<ExaminationDetailsVo> examReportClassWiseDetails(ReportMenuForm reform) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: examReportClassWiseDetails : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			
			Connection conn = null;
			ArrayList<ExaminationDetailsVo> examlist=new ArrayList<ExaminationDetailsVo>();
			
			int count=0;
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
									
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASS_WISE_EXAM_DETAILS);
				
					pstmt.setString(1, reform.getClassname().trim());
					pstmt.setString(2, reform.getAccyear().trim());
					
					
					rs=pstmt.executeQuery();
					
					while(rs.next()){
					
						ExaminationDetailsVo vo=new ExaminationDetailsVo();
					
						count++;
						
						vo.setSno1(count);
						vo.setExamName(rs.getString("examname"));
						vo.setSubjectName(rs.getString("subjectName"));
						vo.setMaxmarks(rs.getString("maximum_marks"));
						vo.setRequiredmarks(rs.getString("required_marks"));
						vo.setExaminationdate(HelperClass.convertDatabaseToUI(rs.getString("examdate")));
						vo.setStartTime(rs.getString("examtime"));
						vo.setEndTime(rs.getString("endtime"));
						
						examlist.add(vo);
						
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
					if (pstmt != null&& (!pstmt.isClosed())) {
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

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : examReportClassWiseDetails : Ending");
			
			return examlist;
		}
	
	public ArrayList<StudentInfoVO> geInactivetStudentDetailsReport(ReportMenuForm reform) {
		   
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentDetailsReport : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentInfoVO> studentInfoList=new ArrayList<StudentInfoVO>();
		int count=0;
	 	
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_INACTIVE_STUDENTSiNFORMATION);
			pstmt.setString(1, reform.getAccyear().trim());
			pstmt.setString(2, reform.getStream().trim());
			pstmt.setString(3, reform.getClassname().trim());
			pstmt.setString(4, reform.getSection().trim());
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				StudentInfoVO studentinfo=new StudentInfoVO();
				
				count++;
				studentinfo.setSno(count);
				studentinfo.setAdmissionno(rs.getString("student_admissionno_var").trim());
				studentinfo.setName(rs.getString("studentname").trim());
				studentinfo.setAge(rs.getString("student_age_int").trim());
				studentinfo.setDoj(rs.getString("student_doj_var").trim());
				studentinfo.setFathername(rs.getString("FatherName").trim());
				studentinfo.setFathermobno(rs.getString("mobileno").trim());
				studentinfo.setMothername(rs.getString("student_mothername_var").trim());
				studentinfo.setMonthermobno(rs.getString("student_mothermobileno_var").trim());
				
				studentInfoList.add(studentinfo);
				
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
				if (pstmt != null&& (!pstmt.isClosed())) {
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentDetailsReport : Ending");
		
		return studentInfoList;
	}

	public ArrayList<StudentInfoVO> geInactivetStudentFeeDetailsReport(ReportMenuForm reform) {
		   
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentDetailsReport : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentInfoVO> studentInfoList=new ArrayList<StudentInfoVO>();
		int count=0;
	 	
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_INACTIVE_STUDENTS_FEE_INFORMATION);
			pstmt.setString(1, reform.getAccyear().trim());
			
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				StudentInfoVO studentinfo=new StudentInfoVO();
				
				count++;
				studentinfo.setSno(count);
				studentinfo.setAdmissionno(rs.getString("student_admissionno_var").trim());
				studentinfo.setName(rs.getString("studentname").trim());
				studentinfo.setClassnmae(rs.getString("ClassName").trim());
				studentinfo.setAmount(rs.getDouble("totalamount"));
				
				if(rs.getString("is_paid").trim()=="Y" ||rs.getString("is_paid").equalsIgnoreCase("Y"))
				{
					studentinfo.setIspaid("Paid");
				}
				else
				{
					studentinfo.setIspaid("Not Paid");
				}
				
				
				
				
				if(rs.getString("paidDate")==null||rs.getString("paidDate")=="")
				{
					studentinfo.setPaiddate("-");
				}
				else
				{
					studentinfo.setPaiddate(rs.getString("paidDate"));
				}
				
				
				studentinfo.setTermname(rs.getString("termname"));
				
				if (rs.getString("FatherName") == null)
				{
					if (rs.getString("student_mothername_var") == null)
					{
						
						studentinfo.setFathername(rs.getString("student_gaurdianname_var"));
					} 
					else 
					{
						studentinfo.setFathername(rs.getString("student_mothername_var"));
					}
				} 
				else
				{
					studentinfo.setFathername(rs.getString("FatherName"));
				}

				if (rs.getString("mobileno") == null) 
				{
					if (rs.getString("student_mothermobileno_var") == null)
					{
						studentinfo.setFathermobno(rs.getString("student_gardian_mobileno"));
					} 
					else
					{
						studentinfo.setFathermobno(rs.getString("student_mothermobileno_var"));
					}
				}
				else 
				{
					studentinfo.setFathermobno(rs.getString("mobileno"));
				}
				
				
				
				studentInfoList.add(studentinfo);
				
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
				if (pstmt != null&& (!pstmt.isClosed())) {
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentDetailsReport : Ending");
		
		return studentInfoList;
	}

	public ReportMenuVo getSelectedoneItems(ReportMenuForm reform) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getSelectedItems : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ReportMenuVo selecteditems=new ReportMenuVo();
			int count=0;
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_ONE_SELECTED_ITEMS);
				pstmt.setString(1, reform.getAccyear().trim());
			
				
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					
					count++;
					selecteditems.setAccyearname(rs.getString("acadamic_year"));
					
					
					selecteditems.setAccyearId(reform.getAccyear().trim());
					
					
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
					if (pstmt != null&& (!pstmt.isClosed())) {
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

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getSelectedItems : Ending");
			
			return selecteditems;
		}
	
	public ArrayList<FeeReportDetailsVo> getSingleStdFeeStatusReportDetails(
			String stdId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStdFeeStatusReportDetails : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1=null;
		
		
		
		Connection conn = null;
		ArrayList<FeeReportDetailsVo> feeStatusList=new ArrayList<FeeReportDetailsVo>();
		
	
	 	
		try {
			String feeCollectionCode=null;
			conn = JDBCConnection.getSeparateConnection();
			
			
			
			
		
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_SINGLE_FEE_STATUS_REPORT);
				pstmt.setString(1,stdId);
				
				rs=pstmt.executeQuery();
				
				while(rs.next()){
				
					FeeReportDetailsVo vo=new FeeReportDetailsVo();
			
				vo.setAdmissionNo(rs.getString("admissionNo"));
				vo.setStudentName(rs.getString("student_fname_var")+" "+rs.getString("student_lname_var"));
				vo.setTotalAmount(rs.getString("actualamount"));
				vo.setActualAmount(rs.getDouble("actualamount"));
				vo.setBalanceAmount(rs.getDouble("balance_amount"));
				vo.setPaidAmount(Math.round(rs.getDouble("amount_paid")));
				feeCollectionCode=rs.getString("feeCollectionCode");
				
				feeStatusList.add(vo);
				
				}
				int count=0;
				pstmt1 = conn.prepareStatement(ReportsMenuSqlConstants.GET_SINGLE_FEE_STATUS_REPORT_DAYWISE);
				pstmt1.setString(1,feeCollectionCode);
				rs1=pstmt1.executeQuery();
				while(rs1.next()){
					
					
					FeeReportDetailsVo vo=new FeeReportDetailsVo();
					
				
				count++;
				
				vo.setSno(count);
				vo.setFeeName(rs1.getString("FeeName"));
			
				vo.setOpeningfeeAmount(rs1.getDouble("finalFeeAmtCollected")+rs1.getDouble("outstandingfee"));
				vo.setFeeAmountCollected(rs1.getDouble("finalFeeAmtCollected"));
				vo.setBlancefeeAmount(rs1.getDouble("outstandingfee"));
				vo.setPaidDate(HelperClass.convertDatabaseToUI(rs1.getString("feepaiddate")));
				
				
				feeStatusList.add(vo);
				
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
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
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
				+ " Control in ReportsMenuDaoImpl : getStdFeeStatusReportDetails : Ending");
		
		
		
		return feeStatusList;
	}

	@Override
	public String gettempregid() {
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String schl_adm_id=null;
		String  currid="";
		
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
		
			pstmt =conn.prepareStatement(ReportsMenuSqlConstants.GET_TEMP_ID);
			   rs = pstmt.executeQuery();
				while(rs.next())
				{
					schl_adm_id=rs.getString("schl_adm_id");
				String regno=schl_adm_id.substring(0,3);	
				String befsplit=schl_adm_id.substring(3);
				
				
				int preid =Integer.parseInt(befsplit);
		        currid = regno + ++preid;
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
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		return currid;
	
	}

	@Override
	public String getthirdRegNo() {
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String schl_third_id=null;
		String  currid="";
		
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
		
			pstmt =conn.prepareStatement(ReportsMenuSqlConstants.GET_THIRD_ID);
			   rs = pstmt.executeQuery();
				while(rs.next())
				{
					schl_third_id=rs.getString("third_admid");
				String regno=schl_third_id.substring(0,3);	
				String befsplit=schl_third_id.substring(3);
				
				
				int preid =Integer.parseInt(befsplit);
		        currid = regno + ++preid;
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
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		return currid;
	}

	public ArrayList<ReportMenuVo> getlocationList() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getlocationList : Starting");
		
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETLOCATION);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo vo =new ReportMenuVo();
				vo.setLocationId(rs.getString("Location_Id"));
				vo.setLocationName(rs.getString("Location_Name"));
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getlocationList : Ending");
		
		return list;

	}

	public ArrayList<ReportMenuVo> getstudentDOBWise(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentDOBWise : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSDOBWISE);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setDob(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				obj.setClass_and_section(rs.getString("classdetails_name_var")+" "+rs.getString("classsection_name_var"));
				
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentDOBWise : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getstudentFatherOccuWise(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentFatherOccuWise : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTS_FATHER_OCC_WISE);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setFatherName(rs.getString("FatherName"));
				obj.setOccupation(rs.getString("occupation"));
				
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentFatherOccuWise : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getclasssectionDetails(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getclasssectionDetails : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select cay.acadamic_year,cl.Location_Name,ccs.classsection_strength_int,cd.classdetails_name_var,ccs.classsection_name_var from campus_acadamicyear cay,campus_classdetail cd,campus_classsection ccs,campus_location cl where cd.classdetail_id_int like ? and ccs.classsection_id_int like ? and cay.acadamic_id=? and cl.Location_Id LIKE ?");
			pstmt.setString(1, vo.getClassId().trim());
			pstmt.setString(2, vo.getSectionId().trim());
			pstmt.setString(3, vo.getAccyearId().trim());
			pstmt.setString(4, vo.getLocationId().trim());
			rs = pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo obj =  new ReportMenuVo();
				obj.setAccYear(rs.getString("acadamic_year"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setClass_and_section(rs.getString("classdetails_name_var")+" - "+rs.getString("classsection_name_var"));
				obj.setTotal_strength(rs.getString("classsection_strength_int"));
				obj.setLocationName(rs.getString("Location_Name"));
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getclasssectionDetails : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getstudentMotherOccuWise(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentMotherOccuWise : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTS_MOTHER_OCC_WISE);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setStudent_mothername_var(rs.getString("student_mothername_var"));
				obj.setOccupation(rs.getString("occupation"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentMotherOccuWise : Ending");
		return list;

	}

	public ArrayList<ReportMenuVo> getstudentDetailsReligionWise(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentDetailsReligionWise : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSRELIGIONWISE);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			rs=pstmt.executeQuery();
			
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setReligion(rs.getString("religion"));
				obj.setCaste(rs.getString("caste"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentDetailsReligionWise : Ending");
		return list;
	}
	
	public ArrayList<ReportMenuVo> getClassDetails() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getClassDetails : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GETCLASSDETAILS);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo vo = new ReportMenuVo();
				vo.setClassId(rs.getString("classdetail_id_int"));
				vo.setClassname(rs.getString("classdetails_name_var"));
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getClassDetails : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getstudentCategoryWise(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentCategoryWise : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSCATEGORYWISE);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setReligion(rs.getString("religion"));
				obj.setCaste(rs.getString("caste"));
				obj.setCasteCategory(rs.getString("casteCategory"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentCategoryWise : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getstudentParentWise(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentParentWise : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSPARENTWISE);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setFatherName(rs.getString("FatherName"));
				obj.setMobileno(rs.getString("mobileno"));
				obj.setStudent_mothername_var(rs.getString("student_mothername_var"));
				obj.setStudent_mothermobileno_var(rs.getString("student_mothermobileno_var"));
				obj.setAddress(rs.getString("address"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentParentWise : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getstudentList(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentList : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSLIST);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentList : Ending");
		return list;
	}
	
	public ArrayList<ReportMenuVo> getstudentDetailsList(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentDetailsList : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSLIST);
			pstmt.setString(1, vo.getSection());
			pstmt.setString(2, vo.getClassId());
			pstmt.setString(3, vo.getAccyear());
			pstmt.setString(4, vo.getLocation());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setRollNo(0);
				obj.setStudentnamelabel(rs.getString("student"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentDetailsList : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getstudentStandardWise(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentStandardWise : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSSTANDARDLIST);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setStudent_gender_var(rs.getString("student_gender_var"));
				obj.setDob(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				obj.setReligion(rs.getString("religion"));
				obj.setCaste(rs.getString("caste"));
				obj.setCasteCategory(rs.getString("casteCategory"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentStandardWise : Ending");
		return list;
	}
	
	public ArrayList<ReportMenuVo> getstudentContactDetails(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentContactDetails : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.STUDENTSCONTACTDETAILS);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setAddress(rs.getString("address"));
				obj.setFatherName(rs.getString("FatherName"));
				obj.setMobileno(rs.getString("mobileno"));
				obj.setStudent_mothername_var(rs.getString("student_mothername_var"));
				obj.setStudent_mothermobileno_var(rs.getString("student_mothermobileno_var"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentContactDetails : Ending");
		return list;
	}
	
	public ArrayList<ExaminationDetailsVo> accYearListStatus() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: accYearListStatus : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select acadamic_id,acadamic_year from campus_acadamicyear");
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setAccyearid(rs.getString("acadamic_id"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setSno1(slno);
				PreparedStatement statuspstmt = conn.prepareStatement("select count(*) from campus_exam_gradesettings where accyear LIKE ?");
				statuspstmt.setString(1,rs.getString("acadamic_id"));
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				if(count > 0){
					obj.setStatus("Set");
				}
				else{
					obj.setStatus("Not Set");
				}
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		return list;

	}

	public ArrayList<ReportMenuVo> gettransportfeeDetails(ReportMenuVo obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: gettransportfeeDetails : Starting");
		PreparedStatement pstmt= null;
		PreparedStatement pstmt2=null;
	
		ResultSet rs=null;
		
		Connection conn = null;
	
		int slno = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		ArrayList<ReportMenuVo> termlist=new ArrayList<ReportMenuVo>();
	
		try{
			
				
			conn = JDBCConnection.getSeparateConnection();
			if(obj.getTermStatusId().equalsIgnoreCase("Y"))
			{
				pstmt = conn.prepareStatement("SELECT  ft.termid,st.student_id_int,st.student_admissionno_var,CONCAT(st.student_fname_var,' ',st.student_lname_var) AS student,ft.termname, cd.classdetails_name_var,fp.paidAmount amount_paid,cs.classsection_name_var,stage.stage_name,fc.is_paid,fc.reciept_no,fc.modeofpayment,fc.paidDate FROM campus_student st JOIN campus_student_classdetails csc ON st.student_id_int = csc.student_id_int JOIN  campus_classdetail cd ON cd.classdetail_id_int = csc.classdetail_id_int AND cd.locationId = csc.locationId JOIN   campus_classsection cs ON cs.classsection_id_int = csc.classsection_id_int AND cs.locationId = csc.locationId JOIN campus_student_transportdetails tra ON tra.student_id_int = csc.student_id_int JOIN `campus_student_route_stage_mapping` rt ON csc.student_id_int=rt.mapped_id AND csc.fms_acadamicyear_id_int=rt.accyear JOIN campus_fee_stage stage ON stage.stage_id = rt.stage_id  JOIN campus_fee_transport_termdetails ft ON ft.locationId = csc.locationId AND ft.accyear = csc.fms_acadamicyear_id_int AND termid LIKE ?  LEFT JOIN campus_tranport_fee_collection_details fc ON fc.admissionNo = csc.student_id_int AND fc.termcode = ft.termid AND csc.fms_acadamicyear_id_int=fc.accYear  JOIN `campus_transport_fees_payments` fp ON  fc.reciept_no=fp.receiptno WHERE tra.isTransport='Y' AND csc.fms_acadamicyear_id_int =? AND csc.classdetail_id_int like ? AND csc.classsection_id_int like ? AND csc.locationId=? AND fc.is_paid='Y' GROUP BY ft.termname,st.student_id_int ORDER BY startdate,LENGTH(cd.classdetail_id_int),cd.classdetail_id_int,cs.classsection_name_var,student");
				System.out.println("fee"+pstmt);
			}
			else if(obj.getTermStatusId().equalsIgnoreCase("N")) {
				pstmt = conn.prepareStatement("SELECT  ft.termid,st.student_id_int,st.student_admissionno_var,CONCAT(st.student_fname_var,' ',st.student_lname_var) AS student,ft.termname,CASE WHEN fc.amount_paid IS NULL THEN '-' ELSE SUM(fc.amount_paid) END amount_paid, cd.classdetails_name_var,cs.classsection_name_var,stage.stage_name,CASE WHEN fc.is_paid IS NULL THEN 'NOT PAID' ELSE fc.is_paid END is_paid,fc.reciept_no,fc.modeofpayment,fc.paidDate FROM campus_student st JOIN campus_student_classdetails csc ON st.student_id_int = csc.student_id_int JOIN  campus_classdetail cd ON cd.classdetail_id_int = csc.classdetail_id_int AND cd.locationId = csc.locationId JOIN   campus_classsection cs ON cs.classsection_id_int = csc.classsection_id_int AND cs.locationId = csc.locationId JOIN campus_student_transportdetails tra ON tra.student_id_int = csc.student_id_int JOIN campus_fee_stage stage ON stage.stage_id = tra.StageId  JOIN campus_fee_transport_termdetails ft ON ft.locationId = csc.locationId AND ft.accyear = csc.fms_acadamicyear_id_int AND termid LIKE ?  LEFT JOIN campus_tranport_fee_collection_details fc ON fc.admissionNo = csc.student_id_int AND fc.termcode = ft.termid  WHERE tra.isTransport='Y' AND csc.fms_acadamicyear_id_int =? AND csc.classdetail_id_int like ? AND csc.classsection_id_int like ? AND csc.locationId=? AND fc.is_paid IS NULL GROUP BY ft.termname,st.student_id_int ORDER BY startdate,LENGTH(cd.classdetail_id_int),cd.classdetail_id_int,cs.classsection_name_var,student");
				System.out.println("fee"+pstmt);
			}
			else {
				pstmt = conn.prepareStatement("SELECT  ft.termid,st.student_id_int,st.student_admissionno_var,CONCAT(st.student_fname_var,' ',st.student_lname_var) AS student,ft.termname,CASE WHEN fc.amount_paid IS NULL THEN '-' ELSE SUM(fc.amount_paid) END amount_paid, cd.classdetails_name_var,cs.classsection_name_var,stage.stage_name,CASE WHEN fc.is_paid IS NULL THEN 'NOT PAID' ELSE fc.is_paid END is_paid,fc.reciept_no,fc.modeofpayment,fc.paidDate FROM campus_student st JOIN campus_student_classdetails csc ON st.student_id_int = csc.student_id_int JOIN  campus_classdetail cd ON cd.classdetail_id_int = csc.classdetail_id_int AND cd.locationId = csc.locationId JOIN   campus_classsection cs ON cs.classsection_id_int = csc.classsection_id_int AND cs.locationId = csc.locationId JOIN campus_student_transportdetails tra ON tra.student_id_int = csc.student_id_int JOIN campus_fee_stage stage ON stage.stage_id = tra.StageId  JOIN campus_fee_transport_termdetails ft ON ft.locationId = csc.locationId AND ft.accyear = csc.fms_acadamicyear_id_int AND termid LIKE ?  LEFT JOIN campus_tranport_fee_collection_details fc ON fc.admissionNo = csc.student_id_int AND fc.termcode = ft.termid  WHERE tra.isTransport='Y' AND csc.fms_acadamicyear_id_int =? AND csc.classdetail_id_int like ? AND csc.classsection_id_int like ? AND csc.locationId=? GROUP BY ft.termname,st.student_id_int ORDER BY startdate,LENGTH(cd.classdetail_id_int),cd.classdetail_id_int,cs.classsection_name_var,student");
				System.out.println("fee"+pstmt);
			}
			
			pstmt.setString(1, obj.getTermId());
			pstmt.setString(2,obj.getAccyearId());
			pstmt.setString(3,obj.getClassId());
			pstmt.setString(4,obj.getSectionId());
			pstmt.setString(5,obj.getLocationId());
		
		
			rs = pstmt.executeQuery();
			System.out.println("fee"+rs);
			
			System.out.println("fee details query>>>"+pstmt);
			while(rs.next()){
				ReportMenuVo vo = new ReportMenuVo();
				slno++;
				vo.setSno(slno);
				
				vo.setStudentnamelabel(rs.getString("student"));
				vo.setAdmisssion_no(rs.getString("student_admissionno_var"));
				vo.setClassname(rs.getString("classdetails_name_var"));
				vo.setSectionname(rs.getString("classsection_name_var"));
				vo.setClass_and_section(rs.getString("classdetails_name_var")+" '" +rs.getString("classsection_name_var")+"'");
				vo.setTermname(rs.getString("termname"));
	
				
				
				if(rs.getString("is_paid")!=null && rs.getString("is_paid").equalsIgnoreCase("Y"))
				{
					vo.setStatus("PAID");
					vo.setAmount_paid(rs.getString("amount_paid"));
							
						
				}
				else
				{
					vo.setStatus("NOT PAID");
					vo.setAmount_paid(rs.getString("amount_paid"));
				}
							
				list.add(vo);
				}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		return list;
	}

	private void printf() {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<ReportMenuVo> getTerm() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getTerm Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		
		try{
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement("select termid,termname from campus_fee_transport_termdetails;");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ReportMenuVo vo = new ReportMenuVo();
				vo.setTermId(rs.getString("termid"));
				vo.setTermname(rs.getString("termname"));
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getTerm Ending");
		return list;

	}

	public ArrayList<ExaminationDetailsVo> getAccYearsSubject(String accyear, String locid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: accYearListStatus : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0,examidcount=0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select acadamic_id,acadamic_year,Location_Id,Location_Name from campus_acadamicyear,campus_location where acadamic_id like ? and Location_Id like ? order by startDate,Location_Name");
			pstmt.setString(1,accyear);
			pstmt.setString(2,locid);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setAccyearid(rs.getString("acadamic_id"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setLocationid(rs.getString("Location_Id"));
				obj.setLocname(rs.getString("Location_Name"));
				obj.setSno1(slno);
				/*PreparedStatement statuspstmt = conn.prepareStatement("select count(examid) from campus_examination where loc_id=? and acadamicyear=?");
				statuspstmt.setString(1,rs.getString("Location_Id"));
				statuspstmt.setString(2,rs.getString("acadamic_id"));
				System.out.println(statuspstmt);
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}*/
				PreparedStatement pstmt1 = conn.prepareStatement("select count(distinct ExamId) from campus_subject_marks_wise where Accyear_Id=? and loc_id=?");
				pstmt1.setString(1,rs.getString("acadamic_id"));
				pstmt1.setString(2,rs.getString("Location_Id"));
				System.out.println(pstmt1);
				ResultSet rs1 = pstmt1.executeQuery();
				while(rs1.next())
				{
					examidcount=rs1.getInt(1);
				}
				if( examidcount > 0){
					obj.setStatus("Completed");
				}//if(count == examidcount)
				else{
					obj.setStatus("Pending");
				}
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		return list;
	}

	public ArrayList<ReportMenuVo> getAllLocationName() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getlocationList : Starting");
		
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETLOCATION);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo vo =new ReportMenuVo();
				vo.setLocationId(rs.getString("Location_Id"));
				vo.setLocationName(rs.getString("Location_Name"));
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getlocationList : Ending");
		
		return list;

	}

	public ArrayList<ExaminationDetailsVo> accYearhouseSettings(String locid, String accyear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: accYearhouseSettings : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select acadamic_id,acadamic_year,Location_Id,Location_Name from campus_acadamicyear,campus_location where acadamic_id like ? and Location_Id like ? order by startDate");
			pstmt.setString(1,accyear);
			pstmt.setString(2,locid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setAccyearid(rs.getString("acadamic_id"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setLocationid(rs.getString("Location_Id"));
				obj.setLocname(rs.getString("Location_Name"));
				obj.setSno1(slno);
				PreparedStatement statuspstmt = conn.prepareStatement("select count(*) from campus_house_settings where accyear_id = ? and loc_id like ? ");
				statuspstmt.setString(1,rs.getString("acadamic_id"));
				statuspstmt.setString(2,locid.trim());
				System.out.println(statuspstmt);
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				if(count > 0){
					obj.setStatus("Set");
				}
				else{
					obj.setStatus("Not Set");
				}
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		return list;
	}

	public ArrayList<ExaminationDetailsVo> accYeargeneratehouseSettings(String locid, String accyear) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: accYearhouseSettings : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int count1 = 0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select acadamic_id,acadamic_year,Location_Id,Location_Name from campus_acadamicyear,campus_location where acadamic_id like ? and Location_Id like ? order by startDate");
			pstmt.setString(1,accyear);
			pstmt.setString(2,locid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setAccyearid(rs.getString("acadamic_id"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setLocationid(rs.getString("Location_Id"));
				obj.setLocname(rs.getString("Location_Name"));
				obj.setSno1(slno);
				
				PreparedStatement statuspstmt = conn.prepareStatement("select count(*) from campus_student_classdetails where fms_acadamicyear_id_int = ? and locationId = ?");
				statuspstmt.setString(1,rs.getString("acadamic_id"));
				statuspstmt.setString(2,rs.getString("Location_Id"));
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				statusrs.close();
				statuspstmt.close();
				PreparedStatement allocatedpstmt = conn.prepareStatement("select sum(allocated_strength)as total from campus_generate_house where accyearid = ? and locid = ?");
				allocatedpstmt.setString(1,rs.getString("acadamic_id"));
				allocatedpstmt.setString(2,rs.getString("Location_Id"));
				ResultSet allocatedrs = allocatedpstmt.executeQuery();
				while(allocatedrs.next()){
					count1 = allocatedrs.getInt("total");
				}
				allocatedrs.close();
				allocatedpstmt.close();
				if(count!=0 && count1!=0){
					if(count == count1){
					obj.setStatus("Set");
					}
					else{
						obj.setStatus("Not Set");
					}
				}
				else{
					obj.setStatus("Not Set");
				}
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		return list;
	
	}

	public ArrayList<ReportMenuVo> getstudentDepartmentList(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentDepartmentList  Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSDEPARTMENTLIST);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setSpecializationName(rs.getString("Specialization_name"));
			
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentDepartmentList  Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getstudentBusRouteWise(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentBusRouteWise  Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSBUSROUTEWISE);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setBusStageName(rs.getString("stage_name"));
				obj.setStageAmount(rs.getString("amount"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentBusRouteWise  Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getstudentOptionalSubjectDetails(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentOptionalSubjectDetails  Starting");
		PreparedStatement pstmt= null;
		PreparedStatement pstmt1= null;
		ResultSet rs=null;
		ResultSet rst=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSOPTIONALSUBJECT);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setClass_and_section(rs.getString("classdetails_name_var")+" "+rs.getString("classsection_name_var"));
				
				if(rs.getString("secondlanguage") != null || rs.getString("secondlanguage") != "-"){
					pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_SUBJECT);
					pstmt1.setString(1, rs.getString("secondlanguage"));
					rst=pstmt1.executeQuery();
					if(rst.next()){
						obj.setSecondLanguage(rst.getString("subjectName"));
					}else{
						obj.setSecondLanguage("-");
					}
				}
				

				if(rs.getString("thirdlanguage") != null || rs.getString("thirdlanguage") != "-"){
					pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_SUBJECT);
					pstmt1.setString(1, rs.getString("thirdlanguage"));
                    rst=pstmt1.executeQuery();
					if(rst.next()){
						obj.setThirdLanguage(rst.getString("subjectName"));
					}else{
						obj.setThirdLanguage("-");
					}
				}
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentOptionalSubjectDetails  Ending");
		return list;
	}
	
	
	public ArrayList<ReportMenuVo> getstudentsWithheld(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentsWithheld  Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement("select cc.classdetails_name_var,ccs.classsection_name_var,cl.Location_Name,stu.student_admissionno_var,case when cscd.student_rollno is null then '-' else cscd.student_rollno end student_rollno,cscd.student_rollno,concat(stu.student_fname_var,' ',stu.student_lname_var)as studentName,cscd.student_status from campus_student stu left join campus_student_classdetails cscd on cscd.student_id_int=stu.student_id_int join campus_classdetail cc on cscd.classdetail_id_int=cc.classdetail_id_int and cscd.locationId=cc.locationId join campus_classsection ccs on cscd.classsection_id_int=ccs.classsection_id_int join campus_location cl on cscd.locationId=cl.Location_Id  where cscd.fms_acadamicyear_id_int=? and cscd.locationId like ? and cscd.classdetail_id_int like ? and cscd.classsection_id_int like ? and student_status='WITHHELD' order by LENGTH(cscd.locationId),cscd.locationId,LENGTH(cscd.classdetail_id_int),cscd.classdetail_id_int,LENGTH(cscd.classsection_id_int),cscd.classsection_id_int,cscd.student_rollno");
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			rs=pstmt.executeQuery();
			System.out.println(pstmt);
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStatus(rs.getString("student_status"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSection(rs.getString("classsection_name_var"));
				obj.setLocation(rs.getString("Location_Name"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentsWithheld  Ending");
		return list;
	}
	
	public ArrayList<ReportMenuVo> getstudentWithPhoneNumber(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentWithPhoneNumber  Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSWITHPHONENUMBER);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setFatherName(rs.getString("FatherName"));
				obj.setFatherMobileNo(rs.getString("mobileno"));
				obj.setStudent_mothername_var(rs.getString("student_mothername_var"));
				obj.setStudent_mothermobileno_var(rs.getString("student_mothermobileno_var"));
				obj.setGuardianName(rs.getString("student_gaurdianname_var"));
				obj.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentWithPhoneNumber  Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getOldStudentsList(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentWithPhoneNumber  Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETOLDSTUDENTLIST);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentWithPhoneNumber  Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getStudentsStrength(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentsStrength  Starting");
		String accYear = vo.getAccyearId();
		String location = vo.getLocationId();
		String className = vo.getClassId();
		String sectionName = vo.getSectionId();
		
		PreparedStatement pstmt= null;
		PreparedStatement pstmt2= null;
		PreparedStatement pstmObj1= null;
		PreparedStatement pstmObj2= null;
		ResultSet rs=null;
		ResultSet rst=null;
		ResultSet rst1=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		int total = 0;
		int gcount =0;
		int bcount =0;
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select distinct sc.fms_acadamicyear_id_int,cd.classdetails_name_var,cd.classdetail_id_int,cs.classsection_id_int,cs.classsection_name_var  from campus_classdetail cd join campus_student_classdetails sc on cd.classdetail_id_int = sc.classdetail_id_int join campus_classsection cs on cs.classsection_id_int = sc.classsection_id_int where sc.locationId = cd.locationId and sc.locationId = cs.locationId and sc.fms_acadamicyear_id_int like ? and sc.locationId like ? AND sc.student_status !='TC' order by length(cd.classdetail_id_int),cd.classdetail_id_int,cs.classsection_name_var");
			
			pstmt.setString(1, accYear);
			pstmt.setString(2, location);
			rs=pstmt.executeQuery();
			while(rs.next()){
				
				String clsName = rs.getString("classdetail_id_int");
				String secName = rs.getString("classsection_id_int");
				pstmt2=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSSTRENGTH);
				pstmt2.setString(1, accYear);
				pstmt2.setString(2, location);
				pstmt2.setString(3, clsName);
				pstmt2.setString(4, secName);
				rst = pstmt2.executeQuery();
				ReportMenuVo obj = new ReportMenuVo();
				while(rst.next())
				{					
					if(rst.getString("student_gender_var").equalsIgnoreCase("female")){
						
						gcount = rst.getInt(1);
					}else{
						bcount = rst.getInt(1);
					}
				}
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setGirls(gcount);
				obj.setBoys(bcount);
				total = gcount+bcount;
				obj.setTotalStudentsInDiv(total);
				
				pstmObj2=conn.prepareStatement("select count(student_id_int) from campus_student_classdetails where classdetail_id_int=? and fms_acadamicyear_id_int like ? and locationId like ? ");
				pstmObj2.setString(1, clsName);
				pstmObj2.setString(2, accYear);
				pstmObj2.setString(3, location);
				System.out.println(pstmObj2);
				rst1 = pstmObj2.executeQuery();
				while(rst1.next()){
					obj.setTotalStudentsInCls(rst1.getInt(1));
				}
				list.add(obj);
				
				/*if(rs.getString("secondlanguage") != null ){
					pstmObj1 = conn.prepareStatement(SQLUtilConstants.GET_SUBJECT);
					pstmObj1.setString(1, rs.getString("secondlanguage"));
					rs1=pstmObj1.executeQuery();
					if(rs1.next()){
						obj.setSecondLanguage(rs1.getString("subjectName"));
					}
				}

				if(rs.getString("thirdlanguage") != null ){
					pstmObj1 = conn.prepareStatement(SQLUtilConstants.GET_SUBJECT);
					pstmObj1.setString(1, rs.getString("thirdlanguage"));
					System.out.println(pstmObj1);
					rs1=pstmObj1.executeQuery();
					if(rs1.next()){
						obj.setThirdLanguage(rs1.getString("subjectName"));
					}
				}
				obj.setSubjectName((obj.getSecondLanguage())+" / "+(obj.getThirdLanguage()));*/
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmObj1 != null&& (!pstmObj1.isClosed())) {
					pstmObj1.close();
				}
				if (pstmObj2 != null&& (!pstmObj2.isClosed())) {
					pstmObj2.close();
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
				+ " Control in ReportsMenuDaoImpl : getStudentsStrength  Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getStudentsNewAdmissionList(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentsNewAdmissionList  Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSNEWADMISSONLIST);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
	
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setDateOfJoining(HelperClass.convertDatabaseToUI(rs.getString("student_doj_var")));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentsNewAdmissionList  Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getStudentPromotionList(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentsNewAdmissionList  Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSPROMOTIONLIST);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getClassId());
			pstmt.setString(3, vo.getSectionId());
	
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setPromotionStatus(rs.getString("studentpromotion_status"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentsNewAdmissionList  Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getStudentListGenderWise(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentListGenderWise : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSLISTGENDERWISE);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getGender());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				
				if(rs.getString("student_gender_var").equalsIgnoreCase("Male"))
				{
					obj.setGender("Boys");
				}else{
					obj.setGender("Girls");
				}
				
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentListGenderWise : Ending");
		return list;
	}

	public ArrayList<ExaminationDetailsVo> accYearListStatusGrade(String accyear, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: accYearListStatus : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select acadamic_id,acadamic_year,Location_Id,Location_Name from campus_acadamicyear,campus_location where acadamic_id like ? and Location_Id like ? order by startDate");
			pstmt.setString(1,accyear);
			pstmt.setString(2,location);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setAccyearid(rs.getString("acadamic_id"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setLocationid(rs.getString("Location_Id"));
				obj.setLocname(rs.getString("Location_Name"));
				obj.setSno1(slno);
				PreparedStatement statuspstmt = conn.prepareStatement("select count(*) from campus_exam_gradesettings where accyear = ? and loc_id = ?");
				statuspstmt.setString(1,rs.getString("acadamic_id"));
				statuspstmt.setString(2,rs.getString("Location_Id"));
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				if(count > 0){
					obj.setStatus("Set");
				}
				else{
					obj.setStatus("Not Set");
				}
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		return list;

	}


	public ArrayList<SubjectPojo> getSubjectByClass(String classId, String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentListGenderWise : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		SubjectPojo subjectpojo = new SubjectPojo();
		ArrayList<SubjectPojo> list = new ArrayList<SubjectPojo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSUBJECTDETAILS);
			System.out.println(classId);
			pstmt.setString(1, classId);
			pstmt.setString(2, locationId);
		
			rs=pstmt.executeQuery();
			System.out.println("query for subject list.....");
			System.out.println(pstmt);
			
			while(rs.next()){
				SubjectPojo sub=new SubjectPojo();
				sub.setSubjectId(rs.getString("subjectID"));
				sub.setSubjectName(rs.getString("subjectName"));
				
			
				
				list.add(sub);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentListGenderWise : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getaccessionNo(){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getlocationList : Starting");
		
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETACCESSION_NO);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo vo =new ReportMenuVo();
				
				vo.setAccessionNo(rs.getString("Accession_number"));
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getlocationList : Ending");
		
		return list;

	}

	
public List<ExaminationDetailsVo> getSubjectOnClass(String classId, String studentId, String accYear, String locationId, String examCode){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: accYearListStatus : Starting");
		PreparedStatement pstmt=null,pstmt1= null,pstmt2=null,pstmt3=null
				          ,pstmt4=null,pstmt5=null,pstmt6=null,pstmt7=null,
				          pstmt8=null,pstmt9=null,pstmt10=null,pstmt11=null,
				          pstmt12=null;
		ResultSet rs=null,rs0=null,rs1=null,rs2=null,
				  rs3=null,rs4=null,rs5=null,rs6=null,
				  rs7=null,rs8=null,rs9=null,rs10=null;
		ResultSet rsscored=null;
		ResultSet rsgrade=null;
		Connection conn = null;
		int Totalmark=0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("SELECT `subjectID`,`subjectName`,`totalMarks`,`passMarks`,`Sub_Code`,`isLanguage` FROM `campus_subject` WHERE `classid`=? AND `locationId`=? AND isExam='Y'");
			pstmt.setString(1,classId);
			pstmt.setString(2,locationId);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			String gradename="";
			while(rs.next()){
				ExaminationDetailsVo objresult = new ExaminationDetailsVo();
				objresult.setSubId(rs.getString("subjectID"));
				objresult.setSubCode(rs.getString("Sub_Code"));
				objresult.setSubjectName(rs.getString("subjectName"));
				
				objresult.setPassmarks(rs.getString("passMarks"));
				PreparedStatement pstscoredmarks = conn.prepareStatement("SELECT * FROM campus_studentwise_mark_details WHERE stu_id=? and sub_id=? and exam_id=?");
				pstscoredmarks.setString(1,studentId);
				pstscoredmarks.setString(2,objresult.getSubId());
				pstscoredmarks.setString(3,examCode);
				rsscored=pstscoredmarks.executeQuery();
				while(rsscored.next())
				{
					
					double totMarks=rsscored.getDouble("total_marks")+rsscored.getDouble("max_periodic_marks")+rsscored.getDouble("max_notebook_marks")+rsscored.getDouble("max_subjenrich_marks");
					objresult.setTot_marks(Double.toString(totMarks));
					objresult.setStudenprimid(rsscored.getString("Stu_mark_id"));
					objresult.setAttendace(rsscored.getString("attendance_status"));
					
					//PROJECT
					pstmt1=conn.prepareStatement("SELECT `obtainedMarks`,`MaxMarks` FROM `campus_project_studentwise` WHERE `studentId`=? AND `projectCode`=(SELECT `projectCode` FROM `campus_project` WHERE `SubjectId`=? AND `ClassId`=? AND `LocationId`=? AND `AccYear`=?)");
					pstmt1.setString(1,studentId);
					pstmt1.setString(2,rs.getString("subjectID"));
					pstmt1.setString(3,classId);
					pstmt1.setString(4,locationId);
					pstmt1.setString(5,accYear);
					System.out.println("PSTMT1 "+pstmt1);
					rs0=pstmt1.executeQuery();
					
					int projectdep=0;
					int assigndep=0;
					int labdep=0;
					int attendacedep=0;
					while(rs0.next())
					{
						objresult.setOther(rs0.getString("MaxMarks"));
						pstmt2=conn.prepareStatement("SELECT `project`,`assignment`,`practical`,`attendance` FROM `campus_grade_dependency` WHERE `loc_id`=? AND `class_Id`=? AND `exam_code`=?");
						pstmt2.setString(1,locationId);
						pstmt2.setString(2,classId);
						pstmt2.setString(3,examCode);
						rs1=pstmt2.executeQuery();
						System.out.println("PSTMT 2 "+pstmt2);
						while(rs1.next()){
							int total=(rs.getInt("totalMarks")*rs1.getInt("project"))/100;
							projectdep=rs1.getInt("project");
							double project=((rs0.getInt("obtainedMarks")*total)/(double)rs0.getInt("MaxMarks"));
							int project_round=(int) Math.round(project);
							objresult.setProject_scored(project_round);
							
						}
					}
					//ASSIGNMENT
					pstmt3=conn.prepareStatement("SELECT `AcquiredMarks`,`MaxMarks` FROM `campus_assignmentdetails` WHERE `student_id`=? AND `AssignmentCode`=(SELECT `AssignmentCode` FROM `campus_assignment` WHERE `SubjectID`=? AND `ClassID`=? AND `locationId`=? AND `AcadamicID`=?)");
					pstmt3.setString(1,studentId);
					pstmt3.setString(2,rs.getString("subjectID"));
					pstmt3.setString(3,classId);
					pstmt3.setString(4,locationId);
					pstmt3.setString(5,accYear);
					rs2=pstmt3.executeQuery();
					System.out.println("PSTMT2 "+pstmt2);
					while(rs2.next()){
						pstmt4=conn.prepareStatement("SELECT `project`,`assignment`,`practical`,`attendance` FROM `campus_grade_dependency` WHERE `loc_id`=? AND `class_Id`=? AND `exam_code`=?");
						pstmt4.setString(1,locationId);
						pstmt4.setString(2,classId);
						pstmt4.setString(3,examCode);
						rs3=pstmt4.executeQuery();
						System.out.println("PSTMT 4 "+pstmt4);
						while(rs3.next()){
							assigndep=rs3.getInt("assignment");
							int total=(rs.getInt("totalMarks")*rs3.getInt("assignment"))/100;
							double assignment=((rs2.getInt("AcquiredMarks")*total)/(double)rs2.getInt("MaxMarks"));
							int assignment_round=(int) Math.round(assignment);
							objresult.setScoredmarks(Integer.toString(assignment_round));
							objresult.setAssignment_scored(assignment_round);
						}
					}
					//LAB
					
					pstmt5=conn.prepareStatement("SELECT `lab_id`,`Total_Marks`,`Lab_Name`,`Pass_Marks` FROM `laboratory_details` WHERE `Subject`=? AND `Class_Name`=? AND `School_Name`=?");
					pstmt5.setString(1,rs.getString("subjectID"));
					pstmt5.setString(2,classId);
					pstmt5.setString(3,locationId);
					rs4=pstmt5.executeQuery();
					while(rs4.next()){
						pstmt6=conn.prepareStatement("SELECT csmd.`scored_marks` FROM `campus_studentwise_mark_details` csmd WHERE csmd.`sub_id`=? AND csmd.`stu_id`=? AND csmd.`exam_id`=? AND csmd.`classid`=? AND csmd.`location_id`=?");
						pstmt6.setString(1,rs4.getString("lab_id"));
						pstmt6.setString(2,studentId);
						pstmt6.setString(3,examCode);
						pstmt6.setString(4,classId);
						pstmt6.setString(5,locationId);
						rs5=pstmt6.executeQuery();
						while(rs5.next()){
							pstmt7=conn.prepareStatement("SELECT `project`,`assignment`,`practical`,`attendance` FROM `campus_grade_dependency` WHERE `loc_id`=? AND `class_Id`=? AND `exam_code`=?");
							pstmt7.setString(1,locationId);
							pstmt7.setString(2,classId);
							pstmt7.setString(3,examCode);
							rs6=pstmt7.executeQuery();
							while(rs6.next()){
								labdep=rs6.getInt("practical");
								int total=(rs.getInt("totalMarks")*rs6.getInt("practical"))/100;
								double lab=((rs5.getInt("scored_marks")*total)/(double)rs4.getInt("Total_Marks"));
								int lab_round=(int) Math.round(lab);
								objresult.setScoredmarks(Integer.toString(lab_round));
								objresult.setLab_scored(lab_round);
							}
							
						}
						
					}
					//ATTENDANCE
					int total_attendance=0;
					int attended=0;
					int attdep=0;
					pstmt8=conn.prepareStatement("SELECT COUNT(`attendencedate`) FROM `campus_attendence` WHERE  `addmissionno`=?");
					pstmt8.setString(1, studentId);
			    	rs7=pstmt8.executeQuery();
			    	while(rs7.next()){
			    		total_attendance=rs7.getInt(1);
			    	}
			    	pstmt9=conn.prepareStatement("SELECT COUNT(`attendence`) FROM `campus_attendence` WHERE  `addmissionno`=? AND `attendence`='Present'");
					pstmt9.setString(1, studentId);
			    	rs8=pstmt9.executeQuery();
			    	System.out.println("PSTMT 9"+pstmt9);
			    	while(rs8.next()){
			    		attended=rs8.getInt(1);
			    	}
			    	pstmt10=conn.prepareStatement("SELECT `project`,`assignment`,`practical`,`attendance` FROM `campus_grade_dependency` WHERE `loc_id`=? AND `class_Id`=? AND `exam_code`=?");
					pstmt10.setString(1,locationId);
					pstmt10.setString(2,classId);
					pstmt10.setString(3,examCode);
					rs9=pstmt10.executeQuery();
					System.out.println("PSTMT10 "+pstmt10);
					while(rs9.next()){
						attdep=rs9.getInt("attendance");
						int total=(rs.getInt("totalMarks")*rs9.getInt("attendance"))/100;
						double attendance=((attended*total)/(double)total_attendance);
						int att_round=(int) Math.round(attendance);
						objresult.setScoredmarks(Integer.toString(att_round));
						objresult.setAttendence_per(att_round);
					}
					//TOTAL
					int main=projectdep+assigndep+labdep+attdep;
					
					double maiMark=rsscored.getDouble("scored_marks")+rsscored.getDouble("notebook_marks")+rsscored.getDouble("subject_enrich_marks")+rsscored.getDouble("periodic_test");
					double theory=(maiMark*(100-main))/(double)100;
					int theory1=(int) Math.round(theory);
					objresult.setScoredmarks(Integer.toString((theory1+objresult.getProject_scored()+objresult.getAssignment_scored()+objresult.getLab_scored()+objresult.getAttendence_per())));
					Totalmark=Integer.parseInt(objresult.getScoredmarks());
				    projectdep=0;
				    assigndep=0;
				    labdep=0;
				    attdep=0;
				    
				    int gradeMarks=(int) Math.round((maiMark/totMarks)*100);
					
					if(rsscored.getString("attendance_status").equalsIgnoreCase("Absent"))
					{
						gradename="Absent";
					}
					else
					{
					PreparedStatement pstgrade = conn.prepareStatement("SELECT grade_name FROM campus_exam_gradesettings WHERE min_marks<=? AND max_marks>=?");
					pstgrade.setInt(1,gradeMarks);
					pstgrade.setInt(2,gradeMarks);
					rsgrade=pstgrade.executeQuery();
					while(rsgrade.next())
					{
						gradename=rsgrade.getString("grade_name");
					}
				}
					if(gradename==null){
						gradename="F";
					}
					objresult.setGradename(gradename);
					gradename=null;
					}
	/*END------>*/list.add(objresult);
				}
			
			int insert=0;
			double ind_scored=0;
			double ind_total=0;
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getScoredmarks() !=null){
					ind_scored=ind_scored+Double.parseDouble(list.get(i).getScoredmarks());
				}else{
					ind_scored=0;
				}
			}
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getScoredmarks() !=null){
					ind_total=ind_total+Double.parseDouble(list.get(i).getTot_marks());
				}else{
					ind_total=0;
				}
			}
				//INDIVIDUAL INTO CAMPUS_MARKS
				
			    pstmt11=conn.prepareStatement("SELECT COUNT(*) FROM `campus_marks` WHERE `StudentId`=? AND `Exam`=? AND `Accyear`=? AND `Classs`=? AND `LocationId`=?");
				pstmt11.setString(1, studentId);
				pstmt11.setString(2, examCode);
				pstmt11.setString(3, accYear);
				pstmt11.setString(4, classId);
				pstmt11.setString(5, locationId);
				rs10=pstmt11.executeQuery();
				while(rs10.next()){
					insert=rs10.getInt(1);
				}
				if(insert==0){
					
					System.out.println("INSERTING");
					pstmt12=conn.prepareStatement("INSERT INTO `campus_marks`(`StudentId`,`Exam`,`ScoredMarks`,`TotalMarks`,`Accyear`,`Classs`,`LocationId`) VALUES(?,?,?,?,?,?,?)");
					pstmt12.setString(1, studentId);
					pstmt12.setString(2, examCode);
					pstmt12.setString(3, Double.toString(ind_scored));
					pstmt12.setString(4, Double.toString(ind_total));
					pstmt12.setString(5, accYear);
					pstmt12.setString(6, classId);
					pstmt12.setString(7, locationId);
					pstmt12.executeUpdate();
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rsscored != null&& (!rsscored.isClosed())) {
					rsscored.close();
				}if (rsgrade != null&& (!rsgrade.isClosed())) {
					rsgrade.close();
				}if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}if (rs0 != null&& (!rs0.isClosed())) {
					rs0.close();
				}if (rs1 != null&& (!rs1.isClosed())) {
					rs.close();
				}if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}if (rs2 != null&& (!rs2.isClosed())) {
					rs2.close();
				}if (rs3 != null&& (!rs3.isClosed())) {
					rs3.close();
				}if (rs4 != null&& (!rs4.isClosed())) {
					rs4.close();
				}if (rs5 != null&& (!rs5.isClosed())) {
					rs5.close();
				}if (rs6 != null&& (!rs6.isClosed())) {
					rs6.close();
				}if (rs7 != null&& (!rs7.isClosed())) {
					rs7.close();
				}if (rs8 != null&& (!rs8.isClosed())) {
					rs8.close();
				}if (rs9 != null&& (!rs9.isClosed())) {
					rs9.close();
				}if (rs10 != null&& (!rs10.isClosed())) {
					rs10.close();
				}if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
				}if (pstmt3 != null&& (!pstmt3.isClosed())) {
					pstmt3.close();
				}if (pstmt4 != null&& (!pstmt4.isClosed())) {
					pstmt4.close();
				}if (pstmt5 != null&& (!pstmt5.isClosed())) {
					pstmt5.close();
				}if (pstmt6 != null&& (!pstmt6.isClosed())) {
					pstmt6.close();
				}if (pstmt7 != null&& (!pstmt7.isClosed())) {
					pstmt7.close();
				}if (pstmt8 != null&& (!pstmt8.isClosed())) {
					pstmt8.close();
				}if (pstmt9 != null&& (!pstmt9.isClosed())) {
					pstmt9.close();
				}if (pstmt10 != null&& (!pstmt10.isClosed())) {
					pstmt10.close();
				}if (pstmt11 != null&& (!pstmt11.isClosed())) {
					pstmt11.close();
				}if (pstmt12 != null&& (!pstmt12.isClosed())) {
					pstmt12.close();
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
		return list;

	}
	public ArrayList<ExaminationDetailsVo> getExam(String studentId,String accyear, String locationId, String classDetailId,String sectionId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getlocationList : Starting");
		
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		ResultSet rs0=null;
		Connection conn = null;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_EXAM);
			pstmt.setString(1, accyear);
			pstmt.setString(2, locationId);
			pstmt.setString(3, classDetailId);
			pstmt.setString(4, sectionId);
			rs=pstmt.executeQuery();
			System.out.println(pstmt);
			while(rs.next()){
				ExaminationDetailsVo vo =new ExaminationDetailsVo();
				vo.setExamName(rs.getString("examcode"));
				PreparedStatement pstmtcode=conn.prepareStatement("SELECT `examcode` FROM `campus_examination` WHERE `examid`=?");
				pstmtcode.setString(1, rs.getString("examcode"));
				rs0=pstmtcode.executeQuery();
				while(rs0.next()){
					vo.setExamCode(rs0.getString("examcode"));
				       }
				    list.add(vo);
			        
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs0 != null&& (!rs0.isClosed())) {
					rs0.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getlocationList : Ending");
		
		return list;

	}

	public ArrayList<ExaminationDetailsVo> getExamDependencides(String examCode,String studentId, String accYear, String locationId, String classId, String sectionId, int scored) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getlocationList : Starting");
		
		PreparedStatement pstmt= null,pstmt1= null;
		ResultSet rs=null,rs1=null,rs0=null;
		Connection conn = null;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		ArrayList<ExaminationDetailsVo> setExamlist = new ArrayList<ExaminationDetailsVo>();
		
		int totaldepScoredMarks = 0;
		
		int outofftotal=0;
		try{
			conn= JDBCConnection.getSeparateConnection();

			pstmt=conn.prepareStatement("SELECT ce.examname,SUM(stm.`scored_marks`) AS scored_marks,SUM(stm.`notebook_marks`) notebook_marks,SUM(stm.`subject_enrich_marks`) subject_enrich_marks,SUM(stm.`periodic_test`) periodic_test,SUM(stm.`total_marks`) AS totalmarks,SUM(stm.`max_periodic_marks`) AS max_periodic_marks,SUM(stm.`max_notebook_marks`) max_notebook_marks,SUM(stm.`max_subjenrich_marks`) max_subjenrich_marks FROM campus_examination ce LEFT JOIN campus_studentwise_mark_details stm ON stm.`exam_id`=ce.examid LEFT JOIN `campus_subject` sub ON sub.`subjectID`= stm.`sub_id` WHERE ce.examid=? AND stm.`stu_id`=? AND sub.`classid`=?");
			pstmt.setString(1,examCode);
			pstmt.setString(2,studentId);
			pstmt.setString(3,classId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ExaminationDetailsVo vo =new ExaminationDetailsVo();
				vo.setMainexam(rs.getString("examname"));
				vo.setMainexammark(scored);
				vo.setMaintotal(rs.getInt("totalmarks")+rs.getInt("max_periodic_marks")+rs.getInt("max_notebook_marks")+rs.getInt("max_subjenrich_marks"));
				int scoredmark=scored;
				if(scoredmark != 0 ){
					int totalmarks=rs.getInt("totalmarks")+rs.getInt("max_periodic_marks")+rs.getInt("max_notebook_marks")+rs.getInt("max_subjenrich_marks");
					double grademark1=(scoredmark/(double)totalmarks);
					int grademark=(int) (grademark1*100);
					String grade=getGradeBasedOnMarks(grademark);
					vo.setMainexamgrade(grade);
				}else{
					String grade="-";
					vo.setMainexamgrade(grade);
				}
				
				pstmt1=conn.prepareStatement(ReportsMenuSqlConstants.DEPENDENT_EXAM);
				pstmt1.setString(1,examCode);
				rs1=pstmt1.executeQuery();
				while(rs1.next()){
					ExaminationDetailsVo vo1 =new ExaminationDetailsVo();
					vo1.setExamCode(rs1.getString("Exam_code"));
					vo1.setDependentExamCode(rs1.getString("Dependency_Exam_code"));
					vo1.setDepPerce(rs1.getString("Dependency_perce"));
					PreparedStatement namepstmt=conn.prepareStatement(ReportsMenuSqlConstants.DEPENDENT_EXAM_SCORE);
					namepstmt.setString(1, studentId);
					namepstmt.setString(2, rs1.getString("Dependency_Exam_code"));
					namepstmt.setString(3,accYear);
					namepstmt.setString(4,classId);
					namepstmt.setString(5,locationId);
					rs0=namepstmt.executeQuery();
					while(rs0.next()){
						vo1.setExamName(rs0.getString("examname"));
						int depscoredmark=((rs0.getInt("ScoredMarks")*Integer.parseInt(rs1.getString("Dependency_perce")))/100);
						int deptotalmark=((rs0.getInt("TotalMarks")*Integer.parseInt(rs1.getString("Dependency_perce")))/100);
						vo1.setDepExamScoredMarks(depscoredmark);
						vo1.setDepExamTotalMarks(deptotalmark);
						if(depscoredmark != 0 ){
							double depgrademark1=(depscoredmark/(double)deptotalmark);
							int depgrademark=(int) (depgrademark1*100);
							String depgrade=getGradeBasedOnMarks(depgrademark);
							vo1.setDepExamGrade(depgrade);
						}else{
							String grade="-";
							vo1.setDepExamGrade(grade);
						}
						totaldepScoredMarks = totaldepScoredMarks+depscoredmark;
                        outofftotal=outofftotal+deptotalmark;
						setExamlist.add(vo1);
					}
					vo.setTotalDepScoredMarks(totaldepScoredMarks);
					vo.setOutOffG(outofftotal);
					vo.setExamlist(setExamlist);
				}
				list.add(vo);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs0 != null&& (!rs0.isClosed())) {
					rs0.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
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
				+ " Control in ReportsMenuDaoImpl : getlocationList : Ending");
		
		return list;

	}

	public String getGradeBasedOnMarks(int grademark) {
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		String grade=null;
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("SELECT grade_name FROM campus_exam_gradesettings WHERE min_marks<=? AND max_marks>=?");
			pstmt.setInt(1,grademark);
			pstmt.setInt(2,grademark);
			System.out.println("pstmt is "+pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				grade=rs.getString("grade_name");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return grade;
	}


	public ArrayList<ReportMenuVo> getAccessionNo() {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getAccessionNo : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ArrayList<ReportMenuVo> accessionList=new ArrayList<ReportMenuVo>();
		 	
			try {
				conn = JDBCConnection.getSeparateConnection();
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_ACCYEAR);
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					
					ReportMenuVo yearVo=new ReportMenuVo();
					yearVo.setAccyearId(rs.getString("acadamic_id").trim());
					yearVo.setAccyearname(rs.getString("acadamic_year"));
					
					accessionList.add(yearVo);
					
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
					if (pstmt != null&& (!pstmt.isClosed())) {
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

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getAccessionNo : Ending");
			
			return accessionList;
		}




	public List<ExaminationDetailsVo> getIndividualStudentMarksClass(String classId, String studentId, String accYear,
			String locationId, String examCode, String sectionId) {
		ArrayList<ExaminationDetailsVo> list=new ArrayList<ExaminationDetailsVo>();
		PreparedStatement pstmt= null,pstscoredmarks=null,pstscoredmarks1=null,pstmt1= null,pstmt2= null,pstmtn= null;
		ResultSet rs=null,rsscored=null,rs1=null,rsscored1=null,rs2=null,rsn=null;
		Connection conn = null;
		int count=0,count1=0,count2=0,count3=0,count4=0,count5=0,count6=0,count7=0,count8=0,count9=0;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		 TreeMap<String, Integer> sorted_map = null;
		try {
			int maintotmark=0;
			int mainscored=0;
			int mperse=0;
			ExaminationDetailsVo objresult = new ExaminationDetailsVo();
			conn = JDBCConnection.getSeparateConnection();
			pstmt1 = conn.prepareStatement("SELECT `student_id_int` FROM `campus_student_classdetails` WHERE `locationId`=? AND `classdetail_id_int`=? AND `classsection_id_int`=?");
			pstmt1.setString(1, locationId);
			pstmt1.setString(2, classId);
			pstmt1.setString(3, sectionId);
			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				int totmark=0,scored=0,perse=0;
				pstmt = conn.prepareStatement("SELECT `subjectID`,`subjectName`,`totalMarks`,`passMarks`,`Sub_Code`,`isLanguage` FROM `campus_subject` WHERE `classid`=? AND `locationId`=?");
				pstmt.setString(1, classId);
				pstmt.setString(2, locationId);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					totmark = totmark + rs.getInt("totalMarks");
					objresult.setSubId(rs.getString("subjectID"));
					pstscoredmarks = conn.prepareStatement("SELECT scored_marks FROM campus_studentwise_mark_details WHERE stu_id=? and sub_id=? and exam_id=?");
					pstscoredmarks.setString(1, rs1.getString("student_id_int"));
					pstscoredmarks.setString(2, objresult.getSubId());
					pstscoredmarks.setString(3, examCode);
					rsscored = pstscoredmarks.executeQuery();
					while (rsscored.next()) {
						
						
						scored = scored + rsscored.getInt("scored_marks");
					}
				}
				perse = (int) Math.round((scored * 100) / (double) totmark);
				/*<<<HashMap>>>*/
				ComparatorDaoImpl val = new ComparatorDaoImpl(map);
				sorted_map = new TreeMap<String, Integer>(val);
		        map.put(rs1.getString("student_id_int"), perse);
		        sorted_map.putAll(map);
				/*<<<HashMap>>>*/
				if (perse > 90) {
					count9++;
				} else if (perse > 80) {
					count8++;
				} else if (perse > 70) {
					count7++;
				} else if (perse > 60) {
					count6++;
				} else if (perse > 50) {
					count5++;
				} else if (perse > 40) {
					count4++;
				} else if (perse > 30) {
					count3++;
				} else if (perse > 20) {
					count2++;
				} else if (perse > 10) {
					count1++;
				} else if (perse > 0) {
					count++;
				}
				
			}
			objresult.setCount(count);
			objresult.setCount1(count1);
			objresult.setCount2(count2);
			objresult.setCount3(count3);
			objresult.setCount4(count4);
			objresult.setCount5(count5);
			objresult.setCount6(count6);
			objresult.setCount7(count7);
			objresult.setCount8(count8);
			objresult.setCount9(count9);
			pstmt2 = conn.prepareStatement("SELECT `subjectID`,`subjectName`,`totalMarks`,`passMarks`,`Sub_Code`,`isLanguage` FROM `campus_subject` WHERE `classid`=? AND `locationId`=?");
			pstmt2.setString(1, classId);
			pstmt2.setString(2, locationId);
			rs2 = pstmt2.executeQuery();
			while (rs2.next()) {
				maintotmark = maintotmark + rs2.getInt("totalMarks");
				objresult.setSubId(rs2.getString("subjectID"));
				pstscoredmarks1 = conn.prepareStatement("SELECT scored_marks FROM campus_studentwise_mark_details WHERE stu_id=? and sub_id=? and exam_id=?");
				pstscoredmarks1.setString(1, studentId);
				pstscoredmarks1.setString(2, objresult.getSubId());
				pstscoredmarks1.setString(3, examCode);
				rsscored1 = pstscoredmarks1.executeQuery();
				while (rsscored1.next()) {
					mainscored = mainscored + rsscored1.getInt("scored_marks");
				}
			}
			
			
			mperse = (int) Math.round((mainscored * 100) / (double) maintotmark);
			
			if (mperse > 90) {
				objresult.setMainCount(100);
			} else if (mperse > 80) {
				objresult.setMainCount(90);
			} else if (mperse > 70) {
				objresult.setMainCount(80);
			} else if (mperse > 60) {
				objresult.setMainCount(70);
			} else if (mperse > 50) {
				objresult.setMainCount(60);
			} else if (mperse > 40) {
				objresult.setMainCount(50);
			} else if (mperse > 30) {
				objresult.setMainCount(40);
			} else if (mperse > 20) {
				objresult.setMainCount(30);
			} else if (mperse > 10) {
				objresult.setMainCount(20);
			} else if (mperse > 0) {
				objresult.setMainCount(10);
			}
			List key=new ArrayList(sorted_map.keySet());
	        for (int i = 0; i < key.size(); i++) {
	        	if(key.get(i).equals(studentId)){
				i=i+1;
				objresult.setRank("Rank "+i);
	        	}
			}
			pstmtn=conn.prepareStatement("SELECT `student_fname_var` FROM `campus_student` WHERE `student_id_int`=? AND `locationId`=? AND `fms_acadamicyear_id_int`=?");
			pstmtn.setString(1,studentId);
			pstmtn.setString(2,locationId);
			pstmtn.setString(3,accYear);
			rsn=pstmtn.executeQuery();
			while(rsn.next()){
				objresult.setMainName(rsn.getString("student_fname_var"));
			}
			list.add(objresult);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (rsscored != null&& (!rsscored.isClosed())) {
					rsscored.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstscoredmarks != null&& (!pstscoredmarks.isClosed())) {
					pstscoredmarks.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmtn != null&& (!pstmtn.isClosed())) {
					pstmtn.close();
				}
				
				if (pstscoredmarks1 != null&& (!pstscoredmarks1.isClosed())) {
					pstscoredmarks1.close();
				}
				if (pstscoredmarks != null&& (!pstscoredmarks.isClosed())) {
					pstscoredmarks.close();
				}
				
				
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<ReportMenuVo> getTermBaseOnLocation(String locId, String accId) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in createFeeReportDaoImpl: getTerm : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list=new ArrayList<ReportMenuVo>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement("SELECT `termid`,`termname` FROM `campus_fee_termdetails` WHERE `locationId`=? and accyear=? ORDER BY CAST(SUBSTRING(termid,4) AS UNSIGNED)");
			pstmt.setString(1, locId);
			pstmt.setString(2, accId);
			System.out.println("term** "+pstmt);
			System.out.println();
			rs= pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo vo= new ReportMenuVo();
				vo.setTermname(rs.getString("termid"));
				vo.setTermId(rs.getString("termname"));
				list.add(vo);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}  finally {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in createFeeReportDaoImpl : expandAll : Ending");
		
		return list;
	
	}


	public ArrayList<FeeCollectionVo> getFeeCollectionReport(String locationid, String accyear, String termId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getFeeCollectionReport : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        int count=0;
		ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
		try{
			
			
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_FEE_COLLECTION_REPORT);
			pstmt.setString(1, locationid);
			pstmt.setString(2, accyear);
			pstmt.setString(3, termId);
			rs=pstmt.executeQuery();
			System.out.println("//////////////////////////"+pstmt);
			while(rs.next()){
				count++;
				FeeCollectionVo vo=new FeeCollectionVo();
				vo.setSno(count);
				vo.setBilldate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
				vo.setChlnno(rs.getString("chln_no"));
				vo.setClassname(rs.getString("class"));
				vo.setStudentname(rs.getString("student"));
				vo.setPermanentaddress(rs.getString("address"));
				vo.setPaymentMode(rs.getString("paymentMode"));
				vo.setAmount_paid_so_far(rs.getDouble("amount_paid"));
				vo.setTermName(rs.getString("termname"));
				list.add(vo);
		
			
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(rs!=null){
				try{
					rs.close();
				}
				
				
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				
				
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getFeeCollectionReport : Ending");
		
		return list;
	}

	public ArrayList<FeeCollectionVo> getfeecollectionclasslist(
			String locationid, String accyear, String classid, String termId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getFeeCollectionReport : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        int count=0;
		ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
		try{
			
			
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_FEE_COLLECTION_CLASS_REPORT);
			pstmt.setString(1, locationid);
			pstmt.setString(2, accyear);
			pstmt.setString(3, classid);
			pstmt.setString(4, termId);
			rs=pstmt.executeQuery();
			System.out.println("//////////////////////////"+pstmt);
			while(rs.next()){
				count++;
				FeeCollectionVo vo=new FeeCollectionVo();
				vo.setSno(count);
				vo.setBilldate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
				vo.setChlnno(rs.getString("chln_no"));
				vo.setClassname(rs.getString("class"));
				vo.setStudentname(rs.getString("student"));
				vo.setPermanentaddress(rs.getString("address"));
				vo.setPaymentMode(rs.getString("paymentMode"));
				vo.setAmount_paid_so_far(rs.getDouble("amount_paid"));
				vo.setTermName(rs.getString("termname"));
				list.add(vo);
		
			
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(rs!=null){
				try{
					rs.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getFeeCollectionReport : Ending");
		
		return list;
	}

	public ArrayList<FeeCollectionVo> getFeeCollectionSectionReport(
			String locationid, String accyear, String classid, String setionid, String termId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getFeeCollectionReport : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        int count=0;
		ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
		try{
			
			
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_FEE_COLLECTION_SECTION_REPORT);
			pstmt.setString(1, locationid);
			pstmt.setString(2, accyear);
			pstmt.setString(3, classid);
			pstmt.setString(4, setionid);
			pstmt.setString(5, termId);
			rs=pstmt.executeQuery();
			System.out.println("//////////////////////////"+pstmt);
			while(rs.next()){
				count++;
				FeeCollectionVo vo=new FeeCollectionVo();
				vo.setSno(count);
				vo.setBilldate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
				vo.setChlnno(rs.getString("chln_no"));
				vo.setClassname(rs.getString("class"));
				vo.setStudentname(rs.getString("student"));
				vo.setPermanentaddress(rs.getString("address"));
				vo.setPaymentMode(rs.getString("paymentMode"));
				vo.setAmount_paid_so_far(rs.getDouble("amount_paid"));
				vo.setTermName(rs.getString("termname"));
				list.add(vo);
		
			
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(rs!=null){
				try{
					rs.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getFeeCollectionReport : Ending");
		
		return list;
	}

	public ArrayList<FeeCollectionVo> getFeeCollectionPaymodeReport(
			String locationid, String accyear, String classid, String setionid,
			String paymodeid, String paymenttype, String termId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getFeeCollectionPaymodeReport : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        int count=0;
     
		ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
		try{
			    conn=JDBCConnection.getSeparateConnection();
			    pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_FEE_COLLECTION_PAYMODE_REPORT);
				pstmt.setString(1, locationid);
				pstmt.setString(2, accyear);
				pstmt.setString(3, classid);
				pstmt.setString(4, setionid);
				pstmt.setString(5, paymodeid);
				pstmt.setString(6, termId);
				
				rs=pstmt.executeQuery();
				System.out.println("//"+pstmt);
				while(rs.next()){
					count++;
					FeeCollectionVo vo=new FeeCollectionVo();
					vo.setSno(count);
					vo.setBilldate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
					vo.setChlnno(rs.getString("chln_no"));
					vo.setClassname(rs.getString("class"));
					vo.setStudentname(rs.getString("student"));
					vo.setPermanentaddress(rs.getString("address"));
					vo.setPaymentMode(rs.getString("paymentMode"));
					vo.setAmount_paid_so_far(rs.getDouble("amount_paid"));
					vo.setTermName(rs.getString("termname"));
					list.add(vo);
			
				
				}
			
			
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(rs!=null){
				try{
					rs.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getFeeCollectionPaymodeReport : Ending");
		
		return list;
	}


	public ArrayList<ReportMenuVo> getterms(String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentClass : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> termList=new ArrayList<ReportMenuVo>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_TERMS);
			pstmt.setString(1,location);
			
			System.out.println("Teram List query"+pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo termVo=new ReportMenuVo();
				termVo.setTermId(rs.getString("termid"));
				termVo.setTermname(rs.getString("termname"));
				termList.add(termVo);
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
				if (pstmt != null&& (!pstmt.isClosed())) {
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentClass : Ending");
		
		return termList;
	}

	public ArrayList<ReportMenuVo> DDReportList(String termid, String academic_year, String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: DDReportList : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> termList=new ArrayList<ReportMenuVo>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_DD_REPORT_LIST);
			pstmt.setString(1,termid);
			pstmt.setString(2,academic_year);
			pstmt.setString(3,locationid);
			
			rs=pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo termVo=new ReportMenuVo();
				termVo.setTermId(rs.getString("termcode"));
				termVo.setBankName(rs.getString("bank_name"));
				termVo.setDdNo(rs.getString("paymentParticulars"));
				termVo.setDdDate(rs.getString("dd_cheque_date"));
				termVo.setPaidDate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
				termVo.setAmount_paid(rs.getString("amount_paid"));
				termList.add(termVo);
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
				if (pstmt != null&& (!pstmt.isClosed())) {
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : DDReportList : Ending");
		
		return termList;
	}

	public ArrayList<ReportMenuVo> DDReportListDateWise(String locationid, String termid, String startDate, String endDate) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: DDReportList : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> termList=new ArrayList<ReportMenuVo>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("SELECT cfc.termcode,cfc.bank_name,cfc.paymentParticulars,cfc.dd_cheque_date,cfc.paidDate,cfc.amount_paid FROM campus_fee_collection cfc JOIN campus_fee_termdetails cft ON cft.termid=cfc.termcode  WHERE cfc.termcode LIKE ? AND cft.locationId LIKE ? AND cfc.paidDate BETWEEN ? AND ? AND cfc.paymentMode='DD'");
			pstmt.setString(1,termid);
			pstmt.setString(2,locationid);
			pstmt.setString(3,HelperClass.convertUIToDatabase(startDate));
			pstmt.setString(4,HelperClass.convertUIToDatabase(endDate));
			
			rs=pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo termVo=new ReportMenuVo();
				termVo.setTermId(rs.getString("termcode"));
				termVo.setBankName(rs.getString("bank_name"));
				termVo.setDdNo(rs.getString("paymentParticulars"));
				termVo.setDdDate(rs.getString("dd_cheque_date"));
				termVo.setPaidDate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
				termVo.setAmount_paid(rs.getString("amount_paid"));
				termList.add(termVo);
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
				if (pstmt != null&& (!pstmt.isClosed())) {
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : DDReportList : Ending");
		
		return termList;
	}
	public ArrayList<FeeCollectionVo> getonlinelist(String locationid,
			String accyear, String classid, String setionid, String paymodeid,
			String paymenttype, String termId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getonlinelist : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        int count=0;
     
		ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
		try{
			    conn=JDBCConnection.getSeparateConnection();
			    pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_FEE_COLLECTION_PAYMODE_ONLINE_REPORT);
				pstmt.setString(1, locationid);
				pstmt.setString(2, accyear);
				pstmt.setString(3, classid);
				pstmt.setString(4, setionid);
				pstmt.setString(5, termId);

                
				
				rs=pstmt.executeQuery();
				System.out.println("////"+pstmt);
				while(rs.next()){
					count++;
					FeeCollectionVo vo=new FeeCollectionVo();
					vo.setSno(count);
					vo.setBilldate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
					vo.setChlnno(rs.getString("chln_no"));
					vo.setClassname(rs.getString("class"));
					vo.setStudentname(rs.getString("student"));
					vo.setPermanentaddress(rs.getString("address"));
					vo.setPaymentMode(rs.getString("paymentMode"));
					vo.setTermName(rs.getString("termname"));
					vo.setAmount_paid_so_far(rs.getDouble("amount_paid"));
					list.add(vo);
			
				
				}
			
			
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getonlinelist : Ending");
		
		return list;
	}


	@Override
	public ArrayList<ReportMenuVo> getClassesByStream(String streamId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getClassesByStream : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> streamList=new ArrayList<ReportMenuVo>();
	 	
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASSES);
			pstmt.setString(1, streamId);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				ReportMenuVo streamVo=new ReportMenuVo();
				
				streamVo.setClassId(rs.getString("classdetail_id_int"));
				streamVo.setClassname(rs.getString("classdetails_name_var"));
				
				streamList.add(streamVo);
				
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
				if (pstmt != null&& (!pstmt.isClosed())) {
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getClassesByStream : Ending");
		
		return streamList;
	}

	@Override
	public ArrayList<ReportMenuVo> getSectionsByClassLoc(String classId,String location) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getSectionsByClassLoc : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ArrayList<ReportMenuVo> streamList=new ArrayList<ReportMenuVo>();
		 	
			try {
			
				conn = JDBCConnection.getSeparateConnection();
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.get_sections_by_class_loc);
				pstmt.setString(1, classId);
				pstmt.setString(2,location);
			
				rs=pstmt.executeQuery();
				
				System.out.println("section by class query1>>>"+pstmt);
				
				while(rs.next()){
					
					ReportMenuVo streamVo=new ReportMenuVo();
					
					streamVo.setSectionId(rs.getString("classsection_id_int"));
					streamVo.setSectionname(rs.getString("classsection_name_var"));
					
					streamList.add(streamVo);
					
				}
				
				System.out.println("list size>>>"+streamList.size());
			
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
					if (pstmt != null&& (!pstmt.isClosed())) {
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

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getSectionsByClassLoc : Ending");
			
			return streamList;
		}

	public ArrayList<ReportMenuVo> getTerm(String accyear, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getTerm Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		
		try{
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement("SELECT termid,termname FROM campus_fee_transport_termdetails WHERE `accyear`=? AND `locationId`=?");
			pstmt.setString(1,accyear);
			pstmt.setString(2,location);
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ReportMenuVo vo = new ReportMenuVo();
				vo.setTermId(rs.getString("termid"));
				vo.setTermname(rs.getString("termname"));
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getTerm Ending");
		return list;

	}

	public ITFeeVo gettgetITFeeerms(String studentId, String accyer,
			String locationId) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getSectionsByClassLoc : Starting");
			
			PreparedStatement pstmt = null,pstmt1 = null,pstmt2 = null,pstmt3 = null,pstmt4= null,pstmt5= null;
			ResultSet rs=null, rs1=null, rs2=null,rs3=null,rs4=null,rs5=null;
			Connection conn = null;
			String FeeName=null;
			double TutionFeeAmount=0.0;
			double LabFeeAmount=0.0;
			String TutionFeeAmountinString=null;
			String LabFeeAmountString=null;
			ITFeeVo Vo=new ITFeeVo();
			NumberToWord nw=new NumberToWord();
			try {
				
				System.out.println("classId value>>>>");
				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_TERM);
				pstmt.setString(1, studentId);
				pstmt.setString(2,accyer);
				rs=pstmt.executeQuery();
				while(rs.next()){
					
					System.out.println(rs.getString("termcode"));
					pstmt1=conn.prepareStatement(ReportsMenuSqlConstants.GET_IT_FEE);
					pstmt1.setString(1, accyer);
					pstmt1.setString(2,rs.getString("termcode"));
					pstmt1.setString(3,locationId);
					rs1=pstmt1.executeQuery();
					System.out.println("GET FEE "+pstmt1);
					while(rs1.next()){
						FeeName=rs1.getString("FeeName");
						
						if(FeeName.toLowerCase().startsWith("tution")){
							TutionFeeAmount = TutionFeeAmount+ rs1.getDouble("feeAmount");
						}
						if(FeeName.toLowerCase().startsWith("compu")||FeeName.toLowerCase().startsWith("lab")){
							LabFeeAmount = LabFeeAmount+rs1.getDouble("feeAmount");
						}
					}
				}
				pstmt2=conn.prepareStatement(ReportsMenuSqlConstants.GET_PARENT);
				pstmt2.setString(1, studentId);
				rs2=pstmt2.executeQuery();
				while(rs2.next()){
					Vo.setParentname(rs2.getString("FatherName"));
				 }
				pstmt3=conn.prepareStatement(ReportsMenuSqlConstants.GET_YEAR);
				pstmt3.setString(1, accyer);
				rs3=pstmt3.executeQuery();
				while(rs3.next()){
					Vo.setAccyear(rs3.getString("acadamic_year"));
				 }
				pstmt4=conn.prepareStatement("SELECT `classsection_id_int`,`classdetail_id_int` FROM `campus_student_classdetails` WHERE `student_id_int`=?");
				pstmt4.setString(1, studentId);
				rs4=pstmt4.executeQuery();
				while(rs4.next()){
					pstmt5=conn.prepareStatement("SELECT CONCAT(cs.`student_fname_var`,' ',cs.`student_lname_var`) stuName,CONCAT(ca.`classdetails_name_var`,' ',cc.`classsection_name_var`) Class FROM `campus_student` cs LEFT JOIN `campus_classdetail` ca ON  cs.`locationId`=ca.`locationId` LEFT JOIN `campus_classsection` cc ON cs.`locationId`=cc.`locationId` WHERE cs.`student_id_int`=? AND ca.`classdetail_id_int`=? AND cc.`classsection_id_int`=?");
					pstmt5.setString(1, studentId);
					pstmt5.setString(2, rs4.getString("classdetail_id_int"));
					pstmt5.setString(3, rs4.getString("classsection_id_int"));
					rs5=pstmt5.executeQuery();
					while(rs5.next()){
						Vo.setStuName(rs5.getString("stuName"));
						Vo.setClassSec(rs5.getString("Class"));
					}
				}
				
				
				TutionFeeAmountinString=nw.convert(Math.round(TutionFeeAmount));
				LabFeeAmountString=nw.convert(Math.round(LabFeeAmount));
				
				Vo.setStringtution(TutionFeeAmountinString);
				Vo.setStringclab(LabFeeAmountString);
				Vo.setClab(LabFeeAmount);
				Vo.setTutorial(TutionFeeAmount);
				
				System.out.println(TutionFeeAmount);
				System.out.println(LabFeeAmount);
				System.out.println(TutionFeeAmountinString);
				System.out.println(LabFeeAmountString);
			
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
					if (pstmt != null&& (!pstmt.isClosed())) {
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

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getSectionsByClassLoc : Ending");
			return Vo;
		}

	public ArrayList<ReportMenuVo> getStudentsTempNewAdmissionList(
			ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentsNewAdmissionList  Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement("SELECT  * FROM `campus_temporary_admisssion_details`  WHERE accyear=? AND classname LIKE ?");
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getClassId());
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("temporary_admission_id"));
				obj.setStudentnamelabel(rs.getString("studentfirstName")+" "+rs.getString("studentlastname"));
				PreparedStatement Pstmt1=conn.prepareStatement("SELECT DISTINCT classdetails_name_var FROM campus_classdetail WHERE classdetail_id_int=?");
				Pstmt1.setString(1, rs.getString("classname"));
				ResultSet Rs1=Pstmt1.executeQuery();
				if(Rs1.next()){
					obj.setClassname(Rs1.getString("classdetails_name_var"));
				}
				Rs1.close();
				Pstmt1.close();
				
				Pstmt1=conn.prepareStatement("SELECT DISTINCT acadamic_year FROM campus_acadamicyear WHERE acadamic_id=?");
				Pstmt1.setString(1, rs.getString("accyear"));
				Rs1=Pstmt1.executeQuery();
				if(Rs1.next()){
					obj.setAccyear(Rs1.getString("acadamic_year"));
				}
				Rs1.close();
				Pstmt1.close();
				obj.setFatherName(rs.getString("fathername"));
				obj.setFatherMobileNo(rs.getString("fathermobileno"));
				obj.setAddress(rs.getString("addressofcommunication"));
				obj.setMotherName(rs.getString("mothername"));
				obj.setMotherResidenceNo(rs.getString("mothermobile"));
				
				Pstmt1=conn.prepareStatement("SELECT DISTINCT occupation FROM campus_occupation WHERE occupationId=?");
				Pstmt1.setString(1, rs.getString("fatheroccupation"));
				Rs1=Pstmt1.executeQuery();
				if(Rs1.next()){
					obj.setFatherOccupation(Rs1.getString("occupation"));
				}
				
				Rs1.close();
				Pstmt1.close();
				Pstmt1=conn.prepareStatement("SELECT DISTINCT occupation FROM campus_occupation WHERE occupationId=?");
				Pstmt1.setString(1, rs.getString("motheroccupation"));
				Rs1=Pstmt1.executeQuery();
				if(Rs1.next()){
					obj.setMotherOccupation(Rs1.getString("occupation"));
				}
				
				Rs1.close();
				Pstmt1.close();
				obj.setFatherIncome(rs.getString("fathermonthincome"));
				obj.setMotherIncome(rs.getString("mothermothlyincome"));
				obj.setFatherOfficeAddress(rs.getString("fatherofficialaddress"));
				obj.setMotherOfficeAddress(rs.getString("motherofficialaddress"));
				obj.setNationality(rs.getString("nationality"));
				Pstmt1=conn.prepareStatement("SELECT DISTINCT religion FROM campus_religion WHERE religionId=?");
				Pstmt1.setString(1, rs.getString("religion"));
				Rs1=Pstmt1.executeQuery();
				if(Rs1.next()){
					obj.setReligion(Rs1.getString("religion"));
				}
				
				Rs1.close();
				Pstmt1.close();
				
				Pstmt1=conn.prepareStatement("SELECT DISTINCT caste FROM campus_caste WHERE casteId=?");
				Pstmt1.setString(1, rs.getString("caste"));
				Rs1=Pstmt1.executeQuery();
				if(Rs1.next()){
					obj.setCaste(Rs1.getString("caste"));
				}
				else{
					obj.setCaste(rs.getString("othercaste"));
				}
				Rs1.close();
				Pstmt1.close();
				
				Pstmt1=conn.prepareStatement("SELECT DISTINCT casteCategory FROM campus_caste_category WHERE castCatId=?");
				Pstmt1.setString(1, rs.getString("castecategory"));
				Rs1=Pstmt1.executeQuery();
				if(Rs1.next()){
					obj.setCasteCategory(Rs1.getString("castecategory"));
				}
				else{
					obj.setCasteCategory(rs.getString("othercastecategory"));
				}
				Rs1.close();
				Pstmt1.close();
				
				obj.setDob(HelperClass.convertDatabaseToUI(rs.getString("dateofBirthId")));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentsNewAdmissionList  Ending");
		return list;
	}

	public List<ReportMenuVo> getStudentListAdmiWise(ReportMenuVo vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentListAdmiWise  Starting");
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn =null;
		List<ReportMenuVo> stuList = new ArrayList<ReportMenuVo>();
		int count = 0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			
			if(!vo.getLocationId().equalsIgnoreCase("all") && !vo.getClassId().equalsIgnoreCase("all") && !vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement("SELECT CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollno,cd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId WHERE cs.classdetail_id_int = ? AND cs.classsection_id_int = ? AND cs.locationId = ? AND cs.fms_acadamicyear_id_int = ?"
						+ "ORDER BY LENGTH(st.student_admissionno_var),st.student_admissionno_var AND cs.student_status !='TC'");
			}
			else if(vo.getClassId().equalsIgnoreCase("all") && vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement("SELECT CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollno,cd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId WHERE cs.classdetail_id_int like ? AND cs.classsection_id_int like ? AND cs.locationId like ? AND cs.fms_acadamicyear_id_int = ?"
						+ "ORDER BY LENGTH(st.student_admissionno_var),st.student_admissionno_var AND cs.student_status !='TC'");
			}else if(vo.getClassId().equalsIgnoreCase("all") && !vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement("SELECT CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollno,cd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId WHERE cs.classdetail_id_int like ? AND cs.classsection_id_int = ? AND cs.locationId like ? AND cs.fms_acadamicyear_id_int = ?"
						+ "ORDER BY LENGTH(st.student_admissionno_var),st.student_admissionno_var AND cs.student_status !='TC'");
			}else if(!vo.getClassId().equalsIgnoreCase("all") && vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement("SELECT CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollno,cd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId WHERE cs.classdetail_id_int = ? AND cs.classsection_id_int like ? AND cs.locationId like ? AND cs.fms_acadamicyear_id_int = ?"
						+ "ORDER BY LENGTH(st.student_admissionno_var),st.student_admissionno_var AND cs.student_status !='TC'");
			}else{
				pstmt = conn.prepareStatement("SELECT CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollnocd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = st.classdetail_id_int AND "
						+ "cd.locationId = st.locationId JOIN campus_classsection sec ON sec.classsection_id_int = st.classsection_id_int AND sec.locationId = cs.locationId WHERE cs.classdetail_id_int = ? AND cs.classsection_id_int = ? AND cs.locationId like ? AND cs.fms_acadamicyear_id_int = ?"
						+ "ORDER BY LENGTH(st.student_admissionno_var),st.student_admissionno_var AND cs.student_status !='TC'");
			}
			
			if(vo.getLocationId().equalsIgnoreCase("all")){
				vo.setLocationId("%%");
			}
			if(vo.getClassId().equalsIgnoreCase("all")){
				vo.setClassId("%%");
			}
			if(vo.getSectionId().equalsIgnoreCase("all")){
				vo.setSectionId("%%");
			}
			
			pstmt.setString(1,vo.getClassId());
			pstmt.setString(2,vo.getSectionId());
			pstmt.setString(3,vo.getLocationId());
			pstmt.setString(4,vo.getAccyearId());
			rs = pstmt.executeQuery();
			while(rs.next()){
				count ++;
				ReportMenuVo obj = new ReportMenuVo();
				obj.setSno(count);
				obj.setStudentnamelabel(rs.getString("student"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				stuList.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		return stuList;
	}

	public List<ReportMenuVo> getstudentRollNoWise(ReportMenuVo vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentListAdmiWise  Starting");
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn =null;
		List<ReportMenuVo> stuList = new ArrayList<ReportMenuVo>();
		int count = 0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			
			if(!vo.getLocationId().equalsIgnoreCase("all") && !vo.getClassId().equalsIgnoreCase("all") && !vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement("SELECT CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollno,cd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId WHERE cs.classdetail_id_int = ? AND cs.classsection_id_int = ? AND cs.locationId = ? AND cs.fms_acadamicyear_id_int = ? AND cs.student_status !='TC'"
						+ "ORDER BY LENGTH(cs.classdetail_id_int),cd.classdetail_id_int,LENGTH(sec.classsection_name_var),sec.classsection_name_var,LENGTH(cs.student_rollno),cs.student_rollno");
			}
			else if(vo.getClassId().equalsIgnoreCase("all") && vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement("SELECT CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollno,cd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId WHERE cs.classdetail_id_int like ? AND cs.classsection_id_int like ? AND cs.locationId like ? AND cs.fms_acadamicyear_id_int = ? AND cs.student_status !='TC'"
						+ "ORDER BY LENGTH(cs.classdetail_id_int),cd.classdetail_id_int,LENGTH(sec.classsection_name_var),sec.classsection_name_var,LENGTH(cs.student_rollno),cs.student_rollno");
			}else if(vo.getClassId().equalsIgnoreCase("all") && !vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement("SELECT CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollno,cd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId WHERE cs.classdetail_id_int like ? AND cs.classsection_id_int = ? AND cs.locationId like ? AND cs.fms_acadamicyear_id_int = ? AND cs.student_status !='TC'"
						+ "ORDER BY LENGTH(cs.classdetail_id_int),cd.classdetail_id_int,LENGTH(sec.classsection_name_var),sec.classsection_name_var,LENGTH(cs.student_rollno),cs.student_rollno");
			}else if(!vo.getClassId().equalsIgnoreCase("all") && vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement("SELECT CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollno,cd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId WHERE cs.classdetail_id_int = ? AND cs.classsection_id_int like ? AND cs.locationId like ? AND cs.fms_acadamicyear_id_int = ? AND cs.student_status !='TC'"
						+ "ORDER BY LENGTH(cs.classdetail_id_int),cd.classdetail_id_int,LENGTH(sec.classsection_name_var),sec.classsection_name_var,LENGTH(cs.student_rollno),cs.student_rollno");
			}else{
				pstmt = conn.prepareStatement("SELECT CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollnocd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = st.classdetail_id_int AND "
						+ "cd.locationId = st.locationId JOIN campus_classsection sec ON sec.classsection_id_int = st.classsection_id_int AND sec.locationId = cs.locationId WHERE cs.classdetail_id_int = ? AND cs.classsection_id_int = ? AND cs.locationId like ? AND cs.fms_acadamicyear_id_int = ? AND cs.student_status !='TC'"
						+ "ORDER BY LENGTH(cs.classdetail_id_int),cd.classdetail_id_int,LENGTH(sec.classsection_name_var),sec.classsection_name_var,LENGTH(cs.student_rollno),cs.student_rollno");
			}
			
			if(vo.getLocationId().equalsIgnoreCase("all")){
				vo.setLocationId("%%");
			}
			if(vo.getClassId().equalsIgnoreCase("all")){
				vo.setClassId("%%");
			}
			if(vo.getSectionId().equalsIgnoreCase("all")){
				vo.setSectionId("%%");
			}
			
			pstmt.setString(1,vo.getClassId());
			pstmt.setString(2,vo.getSectionId());
			pstmt.setString(3,vo.getLocationId());
			pstmt.setString(4,vo.getAccyearId());
			rs = pstmt.executeQuery();
			while(rs.next()){
				count ++;
				ReportMenuVo obj = new ReportMenuVo();
				obj.setSno(count);
				obj.setStudentnamelabel(rs.getString("student"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				stuList.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		return stuList;
	}

	public List<ReportMenuVo> getstudentAlphaWise(ReportMenuVo vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentListAdmiWise  Starting");
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn =null;
		List<ReportMenuVo> stuList = new ArrayList<ReportMenuVo>();
		int count = 0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			
			if(!vo.getLocationId().equalsIgnoreCase("all") && !vo.getClassId().equalsIgnoreCase("all") && !vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement("SELECT CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollno,cd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId WHERE cs.classdetail_id_int = ? AND cs.classsection_id_int = ? AND cs.locationId = ? AND cs.fms_acadamicyear_id_int = ? AND cs.student_status !='TC'"
						+ "ORDER BY student");
			}
			else if(vo.getClassId().equalsIgnoreCase("all") && vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement("SELECT CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollno,cd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId WHERE cs.classdetail_id_int like ? AND cs.classsection_id_int like ? AND cs.locationId like ? AND cs.fms_acadamicyear_id_int = ? AND cs.student_status !='TC'"
						+ "ORDER BY student");
			}else if(vo.getClassId().equalsIgnoreCase("all") && !vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement("SELECT CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollno,cd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId WHERE cs.classdetail_id_int like ? AND cs.classsection_id_int = ? AND cs.locationId like ? AND cs.fms_acadamicyear_id_int = ? AND cs.student_status !='TC'"
						+ "ORDER BY student");
			}else if(!vo.getClassId().equalsIgnoreCase("all") && vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement("SELECT CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollno,cd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId WHERE cs.classdetail_id_int = ? AND cs.classsection_id_int like ? AND cs.locationId like ? AND cs.fms_acadamicyear_id_int = ? AND cs.student_status !='TC'"
						+ "ORDER BY student");
			}else{
				pstmt = conn.prepareStatement("SELECT CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollnocd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = st.classdetail_id_int AND "
						+ "cd.locationId = st.locationId JOIN campus_classsection sec ON sec.classsection_id_int = st.classsection_id_int AND sec.locationId = cs.locationId WHERE cs.classdetail_id_int = ? AND cs.classsection_id_int = ? AND cs.locationId like ? AND cs.fms_acadamicyear_id_int = ? AND cs.student_status !='TC'"
						+ "ORDER BY student");
			}
			
			if(vo.getLocationId().equalsIgnoreCase("all")){
				vo.setLocationId("%%");
			}
			if(vo.getClassId().equalsIgnoreCase("all")){
				vo.setClassId("%%");
			}
			if(vo.getSectionId().equalsIgnoreCase("all")){
				vo.setSectionId("%%");
			}
			System.out.println("hello");
			pstmt.setString(1,vo.getClassId());
			pstmt.setString(2,vo.getSectionId());
			pstmt.setString(3,vo.getLocationId());
			pstmt.setString(4,vo.getAccyearId());
			
			System.out.println(pstmt);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				count ++;
				ReportMenuVo obj = new ReportMenuVo();
				obj.setSno(count);
				obj.setStudentnamelabel(rs.getString("student"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				stuList.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		return stuList;
	}

	public ArrayList<HashMap<String, String>> getCustomizableStudentReportsExcell(String formValueArray, String location, String accyear, String className, String section, String orderbyArray) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: CustomizableStudentReportsExcell  Starting");
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn =null;
		
		ArrayList<HashMap<String, String>> fullstudentList=new ArrayList<HashMap<String,String>>();
		
		
		try{
			conn = JDBCConnection.getSeparateConnection();
			if(location.equalsIgnoreCase("all")){
				pstmt=conn.prepareStatement("SELECT "+formValueArray+" FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classstream ccst ON ccst.classstream_id_int=cs.fms_classstream_id_int AND ccst.locationId=cs.locationId LEFT JOIN campus_class_specialization csp ON cs.specilization=csp.Specialization_Id JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId  JOIN campus_parentchildrelation pra ON st.student_id_int=pra.stu_addmissionNo JOIN campus_parents pr ON pr.ParentID=pra.parentid"
						+ " LEFT JOIN campus_caste_category cct ON cct.castCatId=st.casteCategory LEFT JOIN campus_caste ct ON ct.casteId=st.student_caste LEFT JOIN campus_religion crl ON crl.religionId=st.student_religion_var LEFT JOIN campus_house_settings chs ON chs.house_id=cs.student_house LEFT JOIN campus_students_contacts ccn ON st.student_id_int=ccn.studentId LEFT JOIN campus_subject s ON cs.secondlanguage=s.subjectID LEFT JOIN  campus_subject t ON cs.thirdlanguage=t.subjectID  WHERE cs.fms_acadamicyear_id_int=? ORDER BY "+orderbyArray);
			
				pstmt.setString(1, accyear);
			}
			else if(className.equalsIgnoreCase("all")){
				pstmt=conn.prepareStatement("SELECT "+formValueArray+" FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classstream ccst ON ccst.classstream_id_int=cs.fms_classstream_id_int AND ccst.locationId=cs.locationId LEFT JOIN campus_class_specialization csp ON cs.specilization=csp.Specialization_Id JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId  JOIN campus_parentchildrelation pra ON st.student_id_int=pra.stu_addmissionNo JOIN campus_parents pr ON pr.ParentID=pra.parentid"
						+ " LEFT JOIN campus_caste_category cct ON cct.castCatId=st.casteCategory LEFT JOIN campus_caste ct ON ct.casteId=st.student_caste LEFT JOIN campus_religion crl ON crl.religionId=st.student_religion_var LEFT JOIN campus_house_settings chs ON chs.house_id=cs.student_house LEFT JOIN campus_students_contacts ccn ON st.student_id_int=ccn.studentId LEFT JOIN campus_subject s ON cs.secondlanguage=s.subjectID LEFT JOIN  campus_subject t ON cs.thirdlanguage=t.subjectID WHERE cs.fms_acadamicyear_id_int=? and cs.locationId=? ORDER BY "+orderbyArray);
			
				pstmt.setString(1, accyear);
				pstmt.setString(2, location);
				
			}
			else if(section.equalsIgnoreCase("all")){
				pstmt=conn.prepareStatement("SELECT "+formValueArray+" FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classstream ccst ON ccst.classstream_id_int=cs.fms_classstream_id_int AND ccst.locationId=cs.locationId LEFT JOIN campus_class_specialization csp ON cs.specilization=csp.Specialization_Id JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId  JOIN campus_parentchildrelation pra ON st.student_id_int=pra.stu_addmissionNo JOIN campus_parents pr ON pr.ParentID=pra.parentid"
						+ " LEFT JOIN campus_caste_category cct ON cct.castCatId=st.casteCategory LEFT JOIN campus_caste ct ON ct.casteId=st.student_caste LEFT JOIN campus_religion crl ON crl.religionId=st.student_religion_var LEFT JOIN campus_house_settings chs ON chs.house_id=cs.student_house LEFT JOIN campus_students_contacts ccn ON st.student_id_int=ccn.studentId  LEFT JOIN campus_subject s ON cs.secondlanguage=s.subjectID LEFT JOIN  campus_subject t ON cs.thirdlanguage=t.subjectID WHERE cs.fms_acadamicyear_id_int=? and cs.locationId=? and cs.classdetail_id_int=? ORDER BY "+orderbyArray);
			
				pstmt.setString(1, accyear);
				pstmt.setString(2, location);
				pstmt.setString(3, className);
				
			}
			else{
				pstmt=conn.prepareStatement("SELECT "+formValueArray+" FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classstream ccst ON ccst.classstream_id_int=cs.fms_classstream_id_int AND ccst.locationId=cs.locationId LEFT JOIN campus_class_specialization csp ON cs.specilization=csp.Specialization_Id JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId  JOIN campus_parentchildrelation pra ON st.student_id_int=pra.stu_addmissionNo JOIN campus_parents pr ON pr.ParentID=pra.parentid"
						+ " LEFT JOIN campus_caste_category cct ON cct.castCatId=st.casteCategory LEFT JOIN campus_caste ct ON ct.casteId=st.student_caste LEFT JOIN campus_religion crl ON crl.religionId=st.student_religion_var LEFT JOIN campus_house_settings chs ON chs.house_id=cs.student_house LEFT JOIN campus_students_contacts ccn ON st.student_id_int=ccn.studentId  LEFT JOIN campus_subject s ON cs.secondlanguage=s.subjectID LEFT JOIN  campus_subject t ON cs.thirdlanguage=t.subjectID WHERE cs.fms_acadamicyear_id_int=? and cs.locationId=? and cs.classdetail_id_int=? and cs.classsection_id_int=? ORDER BY "+orderbyArray);
			
				pstmt.setString(1, accyear);
				pstmt.setString(2, location);
				pstmt.setString(3, className);
				pstmt.setString(4, section);
				
			}
			System.out.println(pstmt);
			rs=pstmt.executeQuery();
		   while(rs.next()){
			   HashMap<String, String> studentList=new HashMap<String, String>();
			   for(int i=0;i<formValueArray.split(",").length;i++){
				   
			if(formValueArray.split(",")[i].equalsIgnoreCase("student_doj_var") || formValueArray.split(",")[i].equalsIgnoreCase("student_dob_var")) {
				studentList.put(formValueArray.split(",")[i],HelperClass.convertDatabaseToUI(rs.getString(i+1)));
			}else {
				studentList.put(formValueArray.split(",")[i],rs.getString(i+1));
			}	   
			   
			   }
			   fullstudentList.add(studentList);
		   }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		return fullstudentList;
	}
	
	public ReportMenuVo getTerm1Exams(String accyear, String classId, String locationid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getTerm Starting");
		
		PreparedStatement pstmt = null,pstmt1 = null,pstmt2 = null;
		ResultSet rs = null,rs1 = null,rs2 = null;
		Connection conn = null;
		String startdate=null,examid="",enddate=null,examtype="",exam_prefix="";
		ReportMenuVo vo=new ReportMenuVo();
		try{
			conn = JDBCConnection.getSeparateConnection();
			
			//String enddate=getClassTermEndDate();
			
			pstmt = conn.prepareStatement("SELECT `startDate`,`endDate` FROM `campus_acadamicyear` WHERE `acadamic_id`=?");
			pstmt.setString(1,accyear);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				startdate=rs.getString("startDate");
				enddate=rs.getString("endDate");
				
				pstmt1 = conn.prepareStatement("SELECT `examid`,examtype FROM `campus_examination` WHERE `startDate` BETWEEN ? AND ? and `classid`=? AND `loc_id`=? ORDER BY examtype");
				pstmt1.setString(1,startdate);
				pstmt1.setString(2,startdate.split("-")[0]+"-10-15");
				pstmt1.setString(3,classId);
				pstmt1.setString(4,locationid);
				rs1 = pstmt1.executeQuery();
				while(rs1.next()){
					examid+=rs1.getString("examid")+",";
				}
				vo.setExamname(examid);
				//SELECT COUNT(`examtype`) FROM `campus_examination` WHERE `startDate` BETWEEN '2017-04-01' AND '2018-03-31' AND `classid`='CCD12' AND `loc_id`='LOC1' GROUP BY `examtype`

				pstmt2 = conn.prepareStatement("SELECT `examtype`,et.`exam_prefix` FROM `campus_examination` LEFT JOIN `campus_examtype` et ON et.`examtypeid`=examtype WHERE `startDate` BETWEEN ? AND ? AND `classid`=? AND `loc_id`=? GROUP BY `examtype`");
				pstmt2.setString(1,startdate);
				pstmt2.setString(2,startdate.split("-")[0]+"-10-15");
				pstmt2.setString(3,classId);
				pstmt2.setString(4,locationid);
				System.out.println(pstmt2);
				rs2 = pstmt2.executeQuery();
				while(rs2.next()){
					examtype+=rs2.getString("examtype")+",";
					exam_prefix+=rs2.getString("exam_prefix")+",";
				}
				vo.setExamtypeid(examtype);
				vo.setExamtypeprefix(exam_prefix);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs2 != null&& (!rs2.isClosed())) {
					rs2.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
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
				+ " Control in ReportsMenuDaoImpl : getTerm Ending");
		return vo;

	
	}

	public ReportMenuVo getTerm2Exams(String accyear, String classId, String locationid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getTerm Starting");
		
		PreparedStatement pstmt = null,pstmt1 = null,pstmt2 = null;
		ResultSet rs = null,rs1 = null,rs2 = null;
		Connection conn = null;
		String startdate=null,examid="",enddate=null,examtype="",exam_prefix="";
		ReportMenuVo vo=new ReportMenuVo();
		try{
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement("SELECT `startDate`,`endDate` FROM `campus_acadamicyear` WHERE `acadamic_id`=?");
			pstmt.setString(1,accyear);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				startdate=rs.getString("startDate");
				enddate=rs.getString("endDate");
				
				pstmt1 = conn.prepareStatement("SELECT `examid`,examtype FROM `campus_examination` WHERE `startDate` BETWEEN ? AND ? and `classid`=? AND `loc_id`=? ORDER BY examtype");
				pstmt1.setString(1,startdate.split("-")[0]+"-10-15");
				pstmt1.setString(2,enddate);
				pstmt1.setString(3,classId);
				pstmt1.setString(4,locationid);
				rs1 = pstmt1.executeQuery();
				while(rs1.next()){
					examid+=rs1.getString("examid")+",";
				}
				vo.setExamname(examid);
				//SELECT COUNT(`examtype`) FROM `campus_examination` WHERE `startDate` BETWEEN '2017-04-01' AND '2018-03-31' AND `classid`='CCD12' AND `loc_id`='LOC1' GROUP BY `examtype`

				pstmt2 = conn.prepareStatement("SELECT `examtype`,et.`exam_prefix` FROM `campus_examination` LEFT JOIN `campus_examtype` et ON et.`examtypeid`=examtype WHERE `startDate` BETWEEN ? AND ? AND `classid`=? AND `loc_id`=? GROUP BY `examtype`");
				pstmt2.setString(1,startdate.split("-")[0]+"-10-15");
				pstmt2.setString(2,enddate);
				pstmt2.setString(3,classId);
				pstmt2.setString(4,locationid);
				rs2 = pstmt2.executeQuery();
				while(rs2.next()){
					examtype+=rs2.getString("examtype")+",";
					exam_prefix+=rs2.getString("exam_prefix")+",";
				}
				vo.setExamtypeid(examtype);
				vo.setExamtypeprefix(exam_prefix);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs2 != null&& (!rs2.isClosed())) {
					rs2.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
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
				+ " Control in ReportsMenuDaoImpl : getTerm Ending");
		return vo;

	
	}
	
	public ReportMenuVo getFinalExams(String accyear, String classId, String locationid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getTerm Starting");
		
		PreparedStatement pstmt = null,pstmt1 = null,pstmt2 = null;
		ResultSet rs = null,rs1 = null,rs2 = null;
		Connection conn = null;
		String startdate=null,examid="",enddate=null,examtype="",exam_prefix="";
		ReportMenuVo vo=new ReportMenuVo();
		try{
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement("SELECT `startDate`,`endDate` FROM `campus_acadamicyear` WHERE `acadamic_id`=?");
			pstmt.setString(1,accyear);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				startdate=rs.getString("startDate");
				enddate=rs.getString("endDate");
				
				pstmt1 = conn.prepareStatement("SELECT `examid`,examtype FROM `campus_examination` WHERE `startDate` BETWEEN ? AND ? and `classid`=? AND `loc_id`=? ORDER BY examtype");
				pstmt1.setString(1,startdate);
				pstmt1.setString(2,enddate);
				pstmt1.setString(3,classId);
				pstmt1.setString(4,locationid);
				rs1 = pstmt1.executeQuery();
				while(rs1.next()){
					examid+=rs1.getString("examid")+",";
				}
				vo.setExamname(examid);
				//SELECT COUNT(`examtype`) FROM `campus_examination` WHERE `startDate` BETWEEN '2017-04-01' AND '2018-03-31' AND `classid`='CCD12' AND `loc_id`='LOC1' GROUP BY `examtype`

				pstmt2 = conn.prepareStatement("SELECT `examtype`,et.`exam_prefix` FROM `campus_examination` LEFT JOIN `campus_examtype` et ON et.`examtypeid`=examtype WHERE `startDate` BETWEEN ? AND ? AND `classid`=? AND `loc_id`=? GROUP BY `examtype`");
				pstmt2.setString(1,startdate);
				pstmt2.setString(2,enddate);
				pstmt2.setString(3,classId);
				pstmt2.setString(4,locationid);
				rs2 = pstmt2.executeQuery();
				while(rs2.next()){
					examtype+=rs2.getString("examtype")+",";
					exam_prefix+=rs2.getString("exam_prefix")+",";
				}
				vo.setExamtypeid(examtype);
				vo.setExamtypeprefix(exam_prefix);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs2 != null&& (!rs2.isClosed())) {
					rs2.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
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
				+ " Control in ReportsMenuDaoImpl : getTerm Ending");
		return vo;

	
	}

	public List<ReportMenuVo> getTermWiseReportCard(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getTerm Starting");
		
		PreparedStatement pstmt = null,pstmt1 = null,pstgrade=null,pstmt2=null,pstmt3=null,pstmt4=null,pstgrade1=null;
		ResultSet rs = null,rs1 = null,rsgrade=null,rs2=null,rs3=null,rs4=null,rsgrade1=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		
		
		try{
			conn = JDBCConnection.getSeparateConnection();
			
			String examtypeId=vo.getExamtypeid();
			String examtypeIdterm2=vo.getExamstypeidterm2();
			String termexam1=vo.getTerm1();
			String termexam2=vo.getTerm2();
			String[] splitExamTypeId=examtypeId.split(",");
			String[] splitExamTypeIdTerm2=examtypeIdterm2.split(",");
			String[] splitTerm1Exam=termexam1.split(",");
			String[] splitTerm2Exam=termexam2.split(",");
			
			if(vo.getStudentId() != "" && !vo.getSectionId().equalsIgnoreCase("all")){
				pstmt=conn.prepareStatement("SELECT csmd.`stu_id`,acc.`acadamic_year`,ccs.`classdetails_name_var`,CONCAT(ccs.`classdetails_name_var`,'/',sec.`classsection_name_var`) AS classsection,csc.`student_rollno`,stu.student_application_no,stu.`student_admissionno_var`,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentname,par.`FatherName` AS fathername,par.`student_mothername_var` AS mothername,stu.`student_dob_var`,par.`mobileno`,par.`address` FROM `campus_studentwise_mark_details` csmd LEFT JOIN `campus_student_classdetails` csc ON csc.`student_id_int`=csmd.`stu_id` AND csc.`fms_acadamicyear_id_int`=csmd.`Academic_yearid` AND csc.`locationId`=csmd.`location_id` LEFT JOIN `campus_acadamicyear` acc ON acc.`acadamic_id`=csmd.`Academic_yearid` LEFT JOIN `campus_classdetail` ccs ON ccs.`classdetail_id_int`=csmd.`classid` LEFT JOIN `campus_classsection` sec ON sec.`classsection_id_int`=csmd.`sectionid` AND sec.`classdetail_id_int`=ccs.`classdetail_id_int` LEFT JOIN `campus_student` stu ON stu.`student_id_int`=csc.`student_id_int` LEFT JOIN `campus_parentchildrelation` parc ON parc.`stu_addmissionNo`=csmd.`stu_id` LEFT JOIN `campus_parents` par ON par.`ParentID`=parc.`parentid` LEFT JOIN `campus_student_co_scholastic_areas` cscsa ON cscsa.`student_id`=csmd.`stu_id` AND cscsa.`exam_id`=csmd.`exam_id` WHERE csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? and csmd.`stu_id`=? GROUP BY csmd.`stu_id`");
				pstmt.setString(1, vo.getAccYear());
				pstmt.setString(2, vo.getLocationId());
				pstmt.setString(3, vo.getClassId());
				pstmt.setString(4, vo.getStudentId());
			}else if(vo.getStudentId() == "" && !vo.getSectionId().equalsIgnoreCase("all")){
				pstmt=conn.prepareStatement("SELECT csmd.`stu_id`,acc.`acadamic_year`,ccs.`classdetails_name_var`,CONCAT(ccs.`classdetails_name_var`,'/',sec.`classsection_name_var`) AS classsection,csc.`student_rollno`,stu.student_application_no,stu.`student_admissionno_var`,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentname,par.`FatherName` AS fathername,par.`student_mothername_var` AS mothername,stu.`student_dob_var`,par.`mobileno`,par.`address` FROM `campus_studentwise_mark_details` csmd LEFT JOIN `campus_student_classdetails` csc ON csc.`student_id_int`=csmd.`stu_id` AND csc.`fms_acadamicyear_id_int`=csmd.`Academic_yearid` AND csc.`locationId`=csmd.`location_id` LEFT JOIN `campus_acadamicyear` acc ON acc.`acadamic_id`=csmd.`Academic_yearid` LEFT JOIN `campus_classdetail` ccs ON ccs.`classdetail_id_int`=csmd.`classid` LEFT JOIN `campus_classsection` sec ON sec.`classsection_id_int`=csmd.`sectionid` AND sec.`classdetail_id_int`=ccs.`classdetail_id_int` LEFT JOIN `campus_student` stu ON stu.`student_id_int`=csc.`student_id_int` LEFT JOIN `campus_parentchildrelation` parc ON parc.`stu_addmissionNo`=csmd.`stu_id` LEFT JOIN `campus_parents` par ON par.`ParentID`=parc.`parentid` LEFT JOIN `campus_student_co_scholastic_areas` cscsa ON cscsa.`student_id`=csmd.`stu_id` AND cscsa.`exam_id`=csmd.`exam_id` WHERE csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? and csmd.`sectionid`=? GROUP BY csmd.`stu_id`");
				pstmt.setString(1, vo.getAccYear());
				pstmt.setString(2, vo.getLocationId());
				pstmt.setString(3, vo.getClassId());
				pstmt.setString(4, vo.getSectionId());
			}else{
				pstmt=conn.prepareStatement("SELECT csmd.`stu_id`,acc.`acadamic_year`,ccs.`classdetails_name_var`,CONCAT(ccs.`classdetails_name_var`,'/',sec.`classsection_name_var`) AS classsection,csc.`student_rollno`,stu.student_application_no,stu.`student_admissionno_var`,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentname,par.`FatherName` AS fathername,par.`student_mothername_var` AS mothername,stu.`student_dob_var`,par.`mobileno`,par.`address` FROM `campus_studentwise_mark_details` csmd LEFT JOIN `campus_student_classdetails` csc ON csc.`student_id_int`=csmd.`stu_id` AND csc.`fms_acadamicyear_id_int`=csmd.`Academic_yearid` AND csc.`locationId`=csmd.`location_id` LEFT JOIN `campus_acadamicyear` acc ON acc.`acadamic_id`=csmd.`Academic_yearid` LEFT JOIN `campus_classdetail` ccs ON ccs.`classdetail_id_int`=csmd.`classid` LEFT JOIN `campus_classsection` sec ON sec.`classsection_id_int`=csmd.`sectionid` AND sec.`classdetail_id_int`=ccs.`classdetail_id_int` LEFT JOIN `campus_student` stu ON stu.`student_id_int`=csc.`student_id_int` LEFT JOIN `campus_parentchildrelation` parc ON parc.`stu_addmissionNo`=csmd.`stu_id` LEFT JOIN `campus_parents` par ON par.`ParentID`=parc.`parentid` LEFT JOIN `campus_student_co_scholastic_areas` cscsa ON cscsa.`student_id`=csmd.`stu_id` AND cscsa.`exam_id`=csmd.`exam_id` WHERE csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? GROUP BY csmd.`stu_id`");
				pstmt.setString(1, vo.getAccYear());
				pstmt.setString(2, vo.getLocationId());
				pstmt.setString(3, vo.getClassId());
			}
			rs = pstmt.executeQuery();
			while(rs.next()){

				ArrayList<ReportMenuVo> list1 = new ArrayList<ReportMenuVo>();				
				ArrayList<ReportMenuVo> list2 = new ArrayList<ReportMenuVo>();				
				ReportMenuVo vo1=new ReportMenuVo();
				String studentid=rs.getString("stu_id");
				vo1.setAccyearname(rs.getString("acadamic_year"));
				vo1.setClassname(rs.getString("classdetails_name_var"));
				vo1.setClass_and_section(rs.getString("classsection")+"/"+rs.getString("student_rollno"));
				//vo1.setStudentRollNo(rs.getString("student_rollno"));
				vo1.setAdmissionNo(rs.getString("student_admissionno_var"));
				vo1.setStudentnamelabel(rs.getString("studentname"));
				vo1.setFatherName(rs.getString("fathername"));
				vo1.setMotherName(rs.getString("mothername"));
				vo1.setDob(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				vo1.setMobileno(rs.getString("mobileno"));
				vo1.setAddress(rs.getString("address"));
				vo1.setRegNo(rs.getString("student_application_no"));
				//cscsa.`work_edu_grade`,cscsa.`art_edu_grade`,cscsa.`heath_edu_grade`,cscsa.`discipline_grade`
				
				String subjectid="";
				
				
				if(vo.getTerm2() != ""){
					//pstmt1 = conn.prepareStatement("SELECT sub.`subjectName`,csmd.sub_id,csmd.`scored_marks`,csmd.`notebook_marks`,csmd.`subject_enrich_marks` FROM campus_studentwise_mark_details csmd LEFT JOIN `campus_subject` sub ON sub.`subjectID`=csmd.`sub_id` WHERE csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? ");
					pstmt1 = conn.prepareStatement("SELECT csmd.sub_id FROM campus_studentwise_mark_details csmd WHERE csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? GROUP BY csmd.sub_id ");
					pstmt1.setString(1, studentid);
					pstmt1.setString(2, vo.getAccYear());
					pstmt1.setString(3, vo.getLocationId());
					pstmt1.setString(4, vo.getClassId());
					rs1 = pstmt1.executeQuery();

					while(rs1.next())
					{
						subjectid=rs1.getString("sub_id");
						pstmt2 = conn.prepareStatement("SELECT CAST(csmd.`max_periodic_marks` AS UNSIGNED) max_periodic_marks,CAST(csmd.scored_marks AS UNSIGNED) scored_marks,CAST(csmd.`periodic_test` AS UNSIGNED) periodic_test,ce.`exam_prefix`,sub.`subjectName`,CAST(csmd.notebook_marks AS UNSIGNED) notebook_marks,CAST(csmd.subject_enrich_marks AS UNSIGNED) subject_enrich_marks,CAST(csmd.total_marks AS UNSIGNED) total_marks,CAST(csmd.max_notebook_marks AS UNSIGNED) max_notebook_marks,CAST(csmd.max_subjenrich_marks AS UNSIGNED) max_subjenrich_marks,cscsa.`remarks`,cscsa.`work_edu_grade`,cscsa.`art_edu_grade`,cscsa.`health_edu_grade`,cscsa.`discipline_grade` FROM campus_studentwise_mark_details csmd LEFT JOIN `campus_subject` sub ON sub.`subjectID`=csmd.`sub_id` LEFT JOIN `campus_examination` cex ON csmd.exam_id=cex.examid LEFT JOIN `campus_examtype` ce ON cex.examtype=ce.examtypeid LEFT JOIN `campus_student_co_scholastic_areas` cscsa ON cscsa.`exam_id`=cex.examid WHERE csmd.`sub_id`=? AND csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? AND ce.examtypeid=? and cex.`examid`=? GROUP BY csmd.`sub_id`");
						pstmt2.setString(1, subjectid);
						pstmt2.setString(2, studentid);
						pstmt2.setString(3, vo.getAccYear());
						pstmt2.setString(4, vo.getLocationId());
						pstmt2.setString(5, vo.getClassId());

						float pt=0,sm=0,nbm=0,sem=0;
						String work_edu="",art_edu="",health_edu="",discipline_edu="",remarks="";
						ReportMenuVo vo2 = new ReportMenuVo();
						for(int s=0;s<splitExamTypeId.length;s++){

							pstmt2.setString(6, splitExamTypeId[s]);
							pstmt2.setString(7, splitTerm1Exam[s]);
							rs2 = pstmt2.executeQuery();

							if(rs2.next())
							{

								if(rs2.getString("exam_prefix").equalsIgnoreCase("prdxm")){
									pt=((rs2.getFloat("periodic_test"))*10)/(rs2.getFloat("max_periodic_marks"));
								}
								else{
									sm=((rs2.getFloat("scored_marks"))*80)/(rs2.getFloat("total_marks"));
									nbm=((rs2.getFloat("notebook_marks"))*5)/(rs2.getFloat("max_notebook_marks"));
									sem=((rs2.getFloat("subject_enrich_marks"))*5)/(rs2.getFloat("max_subjenrich_marks"));
									work_edu=rs2.getString("work_edu_grade");
									art_edu=rs2.getString("art_edu_grade");
									health_edu=rs2.getString("health_edu_grade");
									discipline_edu=rs2.getString("discipline_grade");
									remarks=rs2.getString("remarks");
								}
							}
						}
						vo1.setWork_edu_grade(work_edu);
						vo1.setArt_edu_grade(art_edu);
						vo1.setHeath_edu_grade(health_edu);
						vo1.setDiscipline_grade(discipline_edu);
						if(remarks != null){
							vo1.setRemarks(remarks);
						}else{
							vo1.setRemarks("");
						}
						vo2.setSubjectName(getSubjectName(subjectid));
						vo2.setPeriodictest(Math.round(pt));
						vo2.setScored_marks(Math.round(sm));
						vo2.setNotebook_marks(Math.round(nbm));
						vo2.setSubject_enrich_marks(Math.round(sem));
						int obtainmark=(int) (vo2.getScored_marks()+vo2.getPeriodictest()+vo2.getSubject_enrich_marks()+vo2.getNotebook_marks());
						vo2.setObtainedmarks(obtainmark);
						String gradename=null;
						if(obtainmark == 0){
							gradename="E";
						}else{
							pstgrade = conn.prepareStatement("SELECT grade_name FROM campus_exam_gradesettings WHERE min_marks<=? AND max_marks>=?");
							pstgrade.setInt(1,obtainmark);
							pstgrade.setInt(2,obtainmark);
							rsgrade=pstgrade.executeQuery();
							
							while(rsgrade.next())
							{
								gradename=rsgrade.getString("grade_name");
							}
						}
						vo2.setGrade(gradename);
						list1.add(vo2);
					}
					
					Collections.sort(list1,HelperClass.SubjectComparator);
					
					
					pstmt3 = conn.prepareStatement("SELECT csmd.sub_id FROM campus_studentwise_mark_details csmd WHERE csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? GROUP BY csmd.sub_id ");
					pstmt3.setString(1, studentid);
					pstmt3.setString(2, vo.getAccYear());
					pstmt3.setString(3, vo.getLocationId());
					pstmt3.setString(4, vo.getClassId());
					rs3 = pstmt3.executeQuery();

					while(rs3.next())
					{
						subjectid=rs3.getString("sub_id");
						pstmt4 = conn.prepareStatement("SELECT CAST(csmd.`max_periodic_marks` AS UNSIGNED) max_periodic_marks,CAST(csmd.scored_marks AS UNSIGNED) scored_marks,CAST(csmd.`periodic_test` AS UNSIGNED) periodic_test,ce.`exam_prefix`,sub.`subjectName`,CAST(csmd.notebook_marks AS UNSIGNED) notebook_marks,CAST(csmd.subject_enrich_marks AS UNSIGNED) subject_enrich_marks,CAST(csmd.total_marks AS UNSIGNED) total_marks,CAST(csmd.max_notebook_marks AS UNSIGNED) max_notebook_marks,CAST(csmd.max_subjenrich_marks AS UNSIGNED) max_subjenrich_marks,cscsa.`remarks`,cscsa.`work_edu_grade`,cscsa.`art_edu_grade`,cscsa.`health_edu_grade`,cscsa.`discipline_grade` FROM campus_studentwise_mark_details csmd LEFT JOIN `campus_subject` sub ON sub.`subjectID`=csmd.`sub_id` LEFT JOIN `campus_examination` cex ON csmd.exam_id=cex.examid LEFT JOIN `campus_examtype` ce ON cex.examtype=ce.examtypeid LEFT JOIN `campus_student_co_scholastic_areas` cscsa ON cscsa.`exam_id`=cex.examid WHERE csmd.`sub_id`=? AND csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? AND ce.examtypeid=? and cex.`examid`=?");
						pstmt4.setString(1, subjectid);
						pstmt4.setString(2, studentid);
						pstmt4.setString(3, vo.getAccYear());
						pstmt4.setString(4, vo.getLocationId());
						pstmt4.setString(5, vo.getClassId());

						float pt=0,sm=0,nbm=0,sem=0;
						String work_edu="",art_edu="",health_edu="",discipline_edu="",remarks="";
						ReportMenuVo vo3 = new ReportMenuVo();
						for(int s=0;s<splitExamTypeIdTerm2.length;s++){

							pstmt4.setString(6, splitExamTypeIdTerm2[s]);
							pstmt4.setString(7, splitTerm2Exam[s]);
							rs4 = pstmt4.executeQuery();

							if(rs4.next())
							{

								if(rs4.getString("exam_prefix").equalsIgnoreCase("prdxm")){
									pt=((rs4.getFloat("periodic_test"))*10)/(rs4.getFloat("max_periodic_marks"));
								}
								else{
									sm=((rs4.getFloat("scored_marks"))*80)/(rs4.getFloat("total_marks"));
									nbm=((rs4.getFloat("notebook_marks"))*5)/(rs4.getFloat("max_notebook_marks"));
									sem=((rs4.getFloat("subject_enrich_marks"))*5)/(rs4.getFloat("max_subjenrich_marks"));
									work_edu=rs4.getString("work_edu_grade");
									art_edu=rs4.getString("art_edu_grade");
									health_edu=rs4.getString("health_edu_grade");
									discipline_edu=rs4.getString("discipline_grade");
									remarks=rs4.getString("remarks");
								}
							}
						}
						vo1.setWork_edu_grade1(work_edu);
						vo1.setArt_edu_grade1(art_edu);
						vo1.setHealth_edu_grade1(health_edu);
						vo1.setDiscipline_grade1(discipline_edu);
						if(remarks != null){
							vo1.setRemarks(remarks);
						}else{
							vo1.setRemarks("");
						}
						vo3.setSubjectName(getSubjectName(subjectid));
						vo3.setPeriodictest(Math.round(pt));
						vo3.setScored_marks(Math.round(sm));
						vo3.setNotebook_marks(Math.round(nbm));
						vo3.setSubject_enrich_marks(Math.round(sem));
						

						int obtainmark=(int) (vo3.getScored_marks()+vo3.getPeriodictest()+vo3.getSubject_enrich_marks()+vo3.getNotebook_marks());
						vo3.setObtainedmarks(obtainmark);
						String gradename=null;
						if(obtainmark == 0){
							gradename="E";
						}else{
							pstgrade1 = conn.prepareStatement("SELECT grade_name FROM campus_exam_gradesettings WHERE min_marks<=? AND max_marks>=?");
							pstgrade1.setInt(1,obtainmark);
							pstgrade1.setInt(2,obtainmark);
							rsgrade1=pstgrade1.executeQuery();
							while(rsgrade1.next())
							{
								gradename=rsgrade1.getString("grade_name");
							}
						}
						
						vo3.setGrade(gradename);
						list2.add(vo3);
					}
					
					for (ReportMenuVo li : list1) {
						if(li.getGrade().contains("E")){
							vo1.setGrade1("Demoted");
							break;
						}else{
							vo1.setGrade1("Promoted");
						}
					}
					
					for (ReportMenuVo s : list2) {
						if (s.getGrade().contains("E")){
							vo1.setGrade2("Demoted");
							break;
						}else{
							vo1.setGrade2("Promoted");
						}
					}
					
					if((vo1.getGrade1() == "Promoted") && (vo1.getGrade2() == "Promoted") ){
						vo1.setResult("Passed");
					}else{
						vo1.setResult("Failed");
					}
					Collections.sort(list2,HelperClass.SubjectComparator);
					vo1.setExamMarkList(list1);
					vo1.setExamMarkList1(list2);
				}else{
					System.out.println("Inside if loop");
					pstmt1 = conn.prepareStatement("SELECT csmd.sub_id FROM campus_studentwise_mark_details csmd WHERE csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? GROUP BY csmd.sub_id ");
					pstmt1.setString(1, studentid);
					pstmt1.setString(2, vo.getAccYear());
					pstmt1.setString(3, vo.getLocationId());
					pstmt1.setString(4, vo.getClassId());
					rs1 = pstmt1.executeQuery();

					while(rs1.next())
					{
						subjectid=rs1.getString("sub_id");
						pstmt2 = conn.prepareStatement("SELECT CAST(csmd.`max_periodic_marks` AS UNSIGNED) max_periodic_marks,CAST(csmd.scored_marks AS UNSIGNED) scored_marks,CAST(csmd.`periodic_test` AS UNSIGNED) periodic_test,ce.`exam_prefix`,sub.`subjectName`,CAST(csmd.notebook_marks AS UNSIGNED) notebook_marks,CAST(csmd.subject_enrich_marks AS UNSIGNED) subject_enrich_marks,CAST(csmd.total_marks AS UNSIGNED) total_marks,CAST(csmd.max_notebook_marks AS UNSIGNED) max_notebook_marks,CAST(csmd.max_subjenrich_marks AS UNSIGNED) max_subjenrich_marks,cscsa.`remarks`,cscsa.`work_edu_grade`,cscsa.`art_edu_grade`,cscsa.`health_edu_grade`,cscsa.`discipline_grade` FROM campus_studentwise_mark_details csmd LEFT JOIN `campus_subject` sub ON sub.`subjectID`=csmd.`sub_id` LEFT JOIN `campus_examination` cex ON csmd.exam_id=cex.examid LEFT JOIN `campus_examtype` ce ON cex.examtype=ce.examtypeid LEFT JOIN `campus_student_co_scholastic_areas` cscsa ON cscsa.`exam_id`=cex.examid WHERE csmd.`sub_id`=? AND csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? AND ce.examtypeid=? and cex.`examid`=? GROUP BY csmd.`sub_id`");
						pstmt2.setString(1, subjectid);
						pstmt2.setString(2, studentid);
						pstmt2.setString(3, vo.getAccYear());
						pstmt2.setString(4, vo.getLocationId());
						pstmt2.setString(5, vo.getClassId());

						float pt=0,sm=0,nbm=0,sem=0;
						String work_edu="",art_edu="",health_edu="",discipline_edu="",remarks="";
						ReportMenuVo vo2 = new ReportMenuVo();
						for(int s=0;s<splitExamTypeId.length;s++){

							pstmt2.setString(6, splitExamTypeId[s]);
							pstmt2.setString(7, splitTerm1Exam[s]);
							rs2 = pstmt2.executeQuery();

							if(rs2.next())
							{

								if(rs2.getString("exam_prefix").equalsIgnoreCase("prdxm")){
									pt=((rs2.getFloat("periodic_test"))*10)/(rs2.getFloat("max_periodic_marks"));
								}
								else{
									sm=((rs2.getFloat("scored_marks"))*80)/(rs2.getFloat("total_marks"));
									nbm=((rs2.getFloat("notebook_marks"))*5)/(rs2.getFloat("max_notebook_marks"));
									sem=((rs2.getFloat("subject_enrich_marks"))*5)/(rs2.getFloat("max_subjenrich_marks"));
									work_edu=rs2.getString("work_edu_grade");
									art_edu=rs2.getString("art_edu_grade");
									health_edu=rs2.getString("health_edu_grade");
									discipline_edu=rs2.getString("discipline_grade");
									remarks=rs2.getString("remarks");
								}
							}
						}
						vo1.setWork_edu_grade(work_edu);
						vo1.setArt_edu_grade(art_edu);
						vo1.setHeath_edu_grade(health_edu);
						vo1.setDiscipline_grade(discipline_edu);
						if(remarks != null){
							vo1.setRemarks(remarks);
						}else{
							vo1.setRemarks("");
						}
						vo2.setSubjectName(getSubjectName(subjectid));
						vo2.setPeriodictest(Math.round(pt));
						vo2.setScored_marks(Math.round(sm));
						vo2.setNotebook_marks(Math.round(nbm));
						vo2.setSubject_enrich_marks(Math.round(sem));

						int obtainmark=(int) (vo2.getScored_marks()+vo2.getPeriodictest()+vo2.getSubject_enrich_marks()+vo2.getNotebook_marks());
						vo2.setObtainedmarks(obtainmark);
						String gradename=null;
						if(obtainmark == 0){
							gradename="E";
						}else{
							pstgrade = conn.prepareStatement("SELECT grade_name FROM campus_exam_gradesettings WHERE min_marks<=? AND max_marks>=?");
							pstgrade.setInt(1,(int)(obtainmark));
							pstgrade.setInt(2,(int)(obtainmark));
							rsgrade=pstgrade.executeQuery();
							
							while(rsgrade.next())
							{
								gradename=rsgrade.getString("grade_name");
							}
						}
						vo2.setGrade(gradename);
						list1.add(vo2);
					}
					for(ReportMenuVo s : list1) {
						if(s.getGrade().contains("E")){
							vo1.setResult("Failed");
							break;
						}else{
							vo1.setResult("Passed");
						}
					}
					Collections.sort(list1,HelperClass.SubjectComparator);
					vo1.setExamMarkList(list1);
				}
				
				list.add(vo1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs2 != null&& (!rs2.isClosed())) {
					rs2.close();
				}
				if (rs3 != null&& (!rs3.isClosed())) {
					rs3.close();
				}
				if (rs4 != null&& (!rs4.isClosed())) {
					rs4.close();
				}
				if (rsgrade != null&& (!rsgrade.isClosed())) {
					rsgrade.close();
				}
				if (rsgrade1 != null&& (!rsgrade1.isClosed())) {
					rsgrade1.close();
				}
				
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmt3 != null&& (!pstmt3.isClosed())) {
					pstmt3.close();
				}
				if (pstmt4 != null&& (!pstmt4.isClosed())) {
					pstmt4.close();
				}
				if (pstgrade1 != null&& (!pstgrade1.isClosed())) {
					pstgrade1.close();
				}
				if (pstgrade != null&& (!pstgrade.isClosed())) {
					pstgrade.close();
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
				+ " Control in ReportsMenuDaoImpl : getTerm Ending");
		return list;

	}

	private String getSubjectName(String subjectid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getTerm Starting");
		
		PreparedStatement pstmt = null,pstmt1 = null;
		ResultSet rs = null,rs1 = null;
		Connection conn = null;
		String subjectname=null;
		
		try{
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement("SELECT `subjectName` FROM `campus_subject` WHERE `subjectID`=?");
			pstmt.setString(1,subjectid);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				subjectname=rs.getString("subjectName");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
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
				+ " Control in ReportsMenuDaoImpl : getTerm Ending");
		return subjectname;

	
	}
	
	public List<ReportMenuVo> getAcademicYearWiseReportCard(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getTerm Starting");
		
		PreparedStatement pstmt = null,pstmt1 = null,pstgrade=null,pstmt2=null;
		ResultSet rs = null,rs1 = null,rsgrade=null,rs2=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		
		
		try{
			conn = JDBCConnection.getSeparateConnection();
			
			String term1=vo.getTermname();
			String examtypeId=vo.getExamtypeid();
			String[] arrStringVal=term1.split(",");
			String[] splitExamTypeId=examtypeId.split(",");
			if(vo.getStudentId() != "" && !vo.getSectionId().equalsIgnoreCase("all")){
				pstmt=conn.prepareStatement("SELECT stu.student_application_no,cscsa.`remarks`,cscsa.`work_edu_grade`,cscsa.`art_edu_grade`,cscsa.`health_edu_grade`,cscsa.`discipline_grade`,csmd.`stu_id`,acc.`acadamic_year`,ccs.`classdetails_name_var`,CONCAT(ccs.`classdetails_name_var`,'/',sec.`classsection_name_var`) AS classsection,csc.`student_rollno`,stu.`student_admissionno_var`,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentname,par.`FatherName` AS fathername,par.`student_mothername_var` AS mothername,stu.`student_dob_var`,par.`mobileno`,par.`address` FROM `campus_studentwise_mark_details` csmd LEFT JOIN `campus_student_classdetails` csc ON csc.`student_id_int`=csmd.`stu_id` AND csc.`fms_acadamicyear_id_int`=csmd.`Academic_yearid` AND csc.`locationId`=csmd.`location_id` LEFT JOIN `campus_acadamicyear` acc ON acc.`acadamic_id`=csmd.`Academic_yearid` LEFT JOIN `campus_classdetail` ccs ON ccs.`classdetail_id_int`=csmd.`classid` LEFT JOIN `campus_classsection` sec ON sec.`classsection_id_int`=csmd.`sectionid` AND sec.`classdetail_id_int`=ccs.`classdetail_id_int` LEFT JOIN `campus_student` stu ON stu.`student_id_int`=csc.`student_id_int` LEFT JOIN `campus_parentchildrelation` parc ON parc.`stu_addmissionNo`=csmd.`stu_id` LEFT JOIN `campus_parents` par ON par.`ParentID`=parc.`parentid` LEFT JOIN `campus_student_co_scholastic_areas` cscsa ON cscsa.`student_id`=csmd.`stu_id` AND cscsa.`exam_id`=csmd.`exam_id` WHERE csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? and csmd.`stu_id`=? GROUP BY csmd.`stu_id`");
				pstmt.setString(1, vo.getAccYear());
				pstmt.setString(2, vo.getLocationId());
				pstmt.setString(3, vo.getClassId());
				pstmt.setString(4, vo.getStudentId());
			}else if(vo.getStudentId() == "" && !vo.getSectionId().equalsIgnoreCase("all")){
				pstmt=conn.prepareStatement("SELECT stu.student_application_no,cscsa.`remarks`,cscsa.`work_edu_grade`,cscsa.`art_edu_grade`,cscsa.`health_edu_grade`,cscsa.`discipline_grade`,csmd.`stu_id`,acc.`acadamic_year`,ccs.`classdetails_name_var`,CONCAT(ccs.`classdetails_name_var`,'/',sec.`classsection_name_var`) AS classsection,csc.`student_rollno`,stu.`student_admissionno_var`,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentname,par.`FatherName` AS fathername,par.`student_mothername_var` AS mothername,stu.`student_dob_var`,par.`mobileno`,par.`address` FROM `campus_studentwise_mark_details` csmd LEFT JOIN `campus_student_classdetails` csc ON csc.`student_id_int`=csmd.`stu_id` AND csc.`fms_acadamicyear_id_int`=csmd.`Academic_yearid` AND csc.`locationId`=csmd.`location_id` LEFT JOIN `campus_acadamicyear` acc ON acc.`acadamic_id`=csmd.`Academic_yearid` LEFT JOIN `campus_classdetail` ccs ON ccs.`classdetail_id_int`=csmd.`classid` LEFT JOIN `campus_classsection` sec ON sec.`classsection_id_int`=csmd.`sectionid` AND sec.`classdetail_id_int`=ccs.`classdetail_id_int` LEFT JOIN `campus_student` stu ON stu.`student_id_int`=csc.`student_id_int` LEFT JOIN `campus_parentchildrelation` parc ON parc.`stu_addmissionNo`=csmd.`stu_id` LEFT JOIN `campus_parents` par ON par.`ParentID`=parc.`parentid` LEFT JOIN `campus_student_co_scholastic_areas` cscsa ON cscsa.`student_id`=csmd.`stu_id` AND cscsa.`exam_id`=csmd.`exam_id` WHERE csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? and csmd.`sectionid`=? GROUP BY csmd.`stu_id`");
				pstmt.setString(1, vo.getAccYear());
				pstmt.setString(2, vo.getLocationId());
				pstmt.setString(3, vo.getClassId());
				pstmt.setString(4, vo.getSectionId());
			}else{
				pstmt=conn.prepareStatement("SELECT stu.student_application_no,cscsa.`remarks`,cscsa.`work_edu_grade`,cscsa.`art_edu_grade`,cscsa.`health_edu_grade`,cscsa.`discipline_grade`,csmd.`stu_id`,acc.`acadamic_year`,ccs.`classdetails_name_var`,CONCAT(ccs.`classdetails_name_var`,'/',sec.`classsection_name_var`) AS classsection,csc.`student_rollno`,stu.`student_admissionno_var`,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentname,par.`FatherName` AS fathername,par.`student_mothername_var` AS mothername,stu.`student_dob_var`,par.`mobileno`,par.`address` FROM `campus_studentwise_mark_details` csmd LEFT JOIN `campus_student_classdetails` csc ON csc.`student_id_int`=csmd.`stu_id` AND csc.`fms_acadamicyear_id_int`=csmd.`Academic_yearid` AND csc.`locationId`=csmd.`location_id` LEFT JOIN `campus_acadamicyear` acc ON acc.`acadamic_id`=csmd.`Academic_yearid` LEFT JOIN `campus_classdetail` ccs ON ccs.`classdetail_id_int`=csmd.`classid` LEFT JOIN `campus_classsection` sec ON sec.`classsection_id_int`=csmd.`sectionid` AND sec.`classdetail_id_int`=ccs.`classdetail_id_int` LEFT JOIN `campus_student` stu ON stu.`student_id_int`=csc.`student_id_int` LEFT JOIN `campus_parentchildrelation` parc ON parc.`stu_addmissionNo`=csmd.`stu_id` LEFT JOIN `campus_parents` par ON par.`ParentID`=parc.`parentid` LEFT JOIN `campus_student_co_scholastic_areas` cscsa ON cscsa.`student_id`=csmd.`stu_id` AND cscsa.`exam_id`=csmd.`exam_id` WHERE csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? GROUP BY csmd.`stu_id`");
				pstmt.setString(1, vo.getAccYear());
				pstmt.setString(2, vo.getLocationId());
				pstmt.setString(3, vo.getClassId());
			}
			rs = pstmt.executeQuery();
			while(rs.next()){

				ArrayList<ReportMenuVo> list1 = new ArrayList<ReportMenuVo>();				
				ReportMenuVo vo1=new ReportMenuVo();
				String studentid=rs.getString("stu_id");
				vo1.setAccyearname(rs.getString("acadamic_year"));
				vo1.setClassname(rs.getString("classdetails_name_var"));
				vo1.setClass_and_section(rs.getString("classsection")+"/"+rs.getString("student_rollno"));
				vo1.setStudentRollNo(rs.getString("student_rollno"));
				vo1.setAdmissionNo(rs.getString("student_admissionno_var"));
				vo1.setStudentnamelabel(rs.getString("studentname"));
				vo1.setFatherName(rs.getString("fathername"));
				vo1.setMotherName(rs.getString("mothername"));
				vo1.setDob(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				vo1.setMobileno(rs.getString("mobileno"));
				vo1.setAddress(rs.getString("address"));
				//cscsa.`work_edu_grade`,cscsa.`art_edu_grade`,cscsa.`heath_edu_grade`,cscsa.`discipline_grade`
				vo1.setWork_edu_grade(rs.getString("work_edu_grade"));
				vo1.setArt_edu_grade(rs.getString("art_edu_grade"));
				vo1.setHeath_edu_grade(rs.getString("health_edu_grade"));
				vo1.setDiscipline_grade(rs.getString("discipline_grade"));
				vo1.setRegNo(rs.getString("student_application_no"));
				if(rs.getString("remarks") != null){
					vo1.setRemarks(rs.getString("remarks"));
				}else{
					vo1.setRemarks("");
				}

				String subjectid="";
				//pstmt1 = conn.prepareStatement("SELECT sub.`subjectName`,csmd.sub_id,csmd.`scored_marks`,csmd.`notebook_marks`,csmd.`subject_enrich_marks` FROM campus_studentwise_mark_details csmd LEFT JOIN `campus_subject` sub ON sub.`subjectID`=csmd.`sub_id` WHERE csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? ");
				pstmt1 = conn.prepareStatement("SELECT csmd.sub_id FROM campus_studentwise_mark_details csmd WHERE csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? GROUP BY csmd.sub_id ");
				pstmt1.setString(1, studentid);
				pstmt1.setString(2, vo.getAccYear());
				pstmt1.setString(3, vo.getLocationId());
				pstmt1.setString(4, vo.getClassId());
				rs1 = pstmt1.executeQuery();

				while(rs1.next())
				{
					subjectid=rs1.getString("sub_id");
					pstmt2 = conn.prepareStatement("SELECT exam_prefix,subjectName,SUM(periodic_test) as periodic_test,SUM(scored_marks) AS scored_marks,SUM(`notebook_marks`) AS notebookmarks,SUM(`subject_enrich_marks`) AS subject_enrich_marks,SUM(`total_marks`) AS total_marks,SUM(`max_notebook_marks`) AS max_notebook_marks,SUM(`max_subjenrich_marks`) AS max_subjenrich_marks,SUM(`max_periodic_marks`) AS max_periodic_marks  FROM (SELECT CAST(csmd.`max_periodic_marks` AS UNSIGNED) max_periodic_marks,CAST(csmd.scored_marks AS UNSIGNED) scored_marks,CAST(csmd.`periodic_test` AS UNSIGNED) periodic_test,ce.`exam_prefix`,sub.`subjectName`,CAST(csmd.notebook_marks AS UNSIGNED) notebook_marks,CAST(csmd.subject_enrich_marks AS UNSIGNED) subject_enrich_marks,CAST(csmd.total_marks AS UNSIGNED) total_marks,CAST(csmd.max_notebook_marks AS UNSIGNED) max_notebook_marks,CAST(csmd.max_subjenrich_marks AS UNSIGNED) max_subjenrich_marks FROM campus_studentwise_mark_details csmd LEFT JOIN `campus_subject` sub ON sub.`subjectID`=csmd.`sub_id` LEFT JOIN `campus_examination` cex ON csmd.exam_id=cex.examid LEFT JOIN `campus_examtype` ce ON cex.examtype=ce.examtypeid WHERE csmd.`sub_id`=? AND csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? AND ce.examtypeid=? ORDER BY CAST(csmd.`periodic_test` AS UNSIGNED) DESC LIMIT 2) AS subt;");
					pstmt2.setString(1, subjectid);
					pstmt2.setString(2, studentid);
					pstmt2.setString(3, vo.getAccYear());
					pstmt2.setString(4, vo.getLocationId());
					pstmt2.setString(5, vo.getClassId());

					float pt=0,sm=0,nbm=0,sem=0;
					ReportMenuVo vo2 = new ReportMenuVo();
					for(int s=0;s<splitExamTypeId.length;s++){

						pstmt2.setString(6, splitExamTypeId[s]);
						rs2 = pstmt2.executeQuery();

						if(rs2.next())
						{

							if(rs2.getString("exam_prefix").equalsIgnoreCase("prdxm")){
								pt=((rs2.getFloat("periodic_test")/2)*10)/(rs2.getFloat("max_periodic_marks")/2);
							}
							else{
								sm=((rs2.getFloat("scored_marks"))*80)/(rs2.getFloat("total_marks"));
								nbm=((rs2.getFloat("notebookmarks"))*5)/(rs2.getFloat("max_notebook_marks"));
								sem=((rs2.getFloat("subject_enrich_marks"))*5)/(rs2.getFloat("max_subjenrich_marks"));
							}
						}
					}
					vo2.setSubjectName(getSubjectName(subjectid));
					vo2.setPeriodictest(Math.round(pt));
					vo2.setScored_marks(Math.round(sm));
					vo2.setNotebook_marks(Math.round(nbm));
					vo2.setSubject_enrich_marks(Math.round(sem));

					int obtainmark=(int) (vo2.getScored_marks()+vo2.getPeriodictest()+vo2.getSubject_enrich_marks()+vo2.getNotebook_marks());
					vo2.setObtainedmarks(obtainmark);
					String gradename=null;
					if(obtainmark == 0){
						gradename="E";
					}else{
						pstgrade = conn.prepareStatement("SELECT grade_name FROM campus_exam_gradesettings WHERE min_marks<=? AND max_marks>=?");
						pstgrade.setInt(1,(int)(obtainmark));
						pstgrade.setInt(2,(int)(obtainmark));
						rsgrade=pstgrade.executeQuery();
						while(rsgrade.next())
						{
							gradename=rsgrade.getString("grade_name");
						}
					}
					
					vo2.setGrade(gradename);
					list1.add(vo2);
				}
				for (ReportMenuVo s : list1) {
					if (s.getGrade().contains("E")){
						vo1.setResult("Failed");
						break;
					}else{
						vo1.setResult("Passed");
					}
				}
				Collections.sort(list1,HelperClass.SubjectComparator);
				vo1.setExamMarkList(list1);
				list.add(vo1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs2 != null&& (!rs2.isClosed())) {
					rs2.close();
				}
				
				if (rsgrade != null&& (!rsgrade.isClosed())) {
					rsgrade.close();
				}
				
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				
				if (pstgrade != null&& (!pstgrade.isClosed())) {
					pstgrade.close();
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
				+ " Control in ReportsMenuDaoImpl : getTerm Ending");
		return list;

	}

	public List<ReportMenuVo> getStudentClassSectionWiseListForReport(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentClassSectionWiseListForReport : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> stuList=new ArrayList<ReportMenuVo>();
	 	
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_LIST_BY_SECTION);
			pstmt.setString(1, vo.getLocationId());
			pstmt.setString(2, vo.getAccyearId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getExamId());
			pstmt.setString(6, vo.getSubjectid());
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				//ce.`examname`,ce.`examcode`,cet.`examtypename`,sub.`subjectName`,ce.`startdate`,ce.`enddate`
				ReportMenuVo vo1=new ReportMenuVo();
				vo1.setStudentIdNo(rs.getString("student_admissionno_var"));
				vo1.setAccyearname(rs.getString("acadamic_year"));
				vo1.setStudentnamelabel(rs.getString("studentname"));
				vo1.setClassname(rs.getString("classdetails_name_var"));
				vo1.setSectionname(rs.getString("classsection_name_var"));
				vo1.setLocationName(rs.getString("Location_Name"));
				vo1.setStreamname(rs.getString("classstream_name_var"));
				vo1.setExamname(rs.getString("examname"));
				vo1.setExamcode(rs.getString("examcode"));
				vo1.setExamTypeName(rs.getString("examtypename"));
				vo1.setSubjectName(rs.getString("subjectName"));
				vo1.setExamStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				vo1.setExamEndDate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
				stuList.add(vo1);
				
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
				if (pstmt != null&& (!pstmt.isClosed())) {
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentClassSectionWiseListForReport : Ending");
		
		return stuList;
	}

	public List<ReportMenuVo> getStudentClassSectionWiseListForReportByAll(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentClassSectionWiseListForReport : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> stuList=new ArrayList<ReportMenuVo>();
	 	
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_LIST_BY_SECTION1);
			pstmt.setString(1, vo.getLocationId());
			pstmt.setString(2, vo.getAccyearId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getExamId());
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				//ce.`examname`,ce.`examcode`,cet.`examtypename`,sub.`subjectName`,ce.`startdate`,ce.`enddate`
				ReportMenuVo vo1=new ReportMenuVo();
				vo1.setStudentIdNo(rs.getString("student_admissionno_var"));
				vo1.setAccyearname(rs.getString("acadamic_year"));
				vo1.setStudentnamelabel(rs.getString("studentname"));
				vo1.setClassname(rs.getString("classdetails_name_var"));
				vo1.setSectionname(rs.getString("classsection_name_var"));
				vo1.setLocationName(rs.getString("Location_Name"));
				vo1.setStreamname(rs.getString("classstream_name_var"));
				vo1.setExamname(rs.getString("examname"));
				vo1.setExamcode(rs.getString("examcode"));
				vo1.setExamTypeName(rs.getString("examtypename"));
				vo1.setExamStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				vo1.setExamEndDate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
				stuList.add(vo1);
				
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
				if (pstmt != null&& (!pstmt.isClosed())) {
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentClassSectionWiseListForReport : Ending");
		
		return stuList;
	}

	public ArrayList<FeeCollectionVo> getfeecollectiondatelist(String locationid, String accyear, String classid,
			String termId, String startdate, String enddate, String paymode) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getFeeCollectionReport : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        int count=0;
		ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
		try{
			
			
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement("SELECT fc.concessionAmt,fc.fineAmount,cft.termname,fc.paidDate,fc.chln_no,CONCAT(cd.classdetails_name_var, ' ',cs.classsection_name_var) AS class, CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student ,fc.paymentMode,fc.amount_paid FROM  campus_fee_collection fc JOIN campus_student_classdetails csc ON fc.admissionNo = csc.student_id_int AND fc.accYear=csc.fms_acadamicyear_id_int JOIN campus_student st  ON fc.admissionNo = st.student_id_int  JOIN campus_classdetail cd ON cd.classdetail_id_int=csc.classdetail_id_int AND cd.locationId = csc.locationId JOIN campus_classsection cs ON cs.classsection_id_int=csc.classsection_id_int AND cs.locationId = csc.locationId  JOIN campus_fee_termdetails cft ON cft.termid=fc.termcode where csc.locationId like ? and fc.accYear like ? and cd.classdetail_id_int like ? AND fc.termcode like ? and fc.paidDate BETWEEN ? AND ? AND fc.paymentMode LIKE ? order by fc.paymentMode,fc.paidDate and cd.classdetails_name_var and cs.classsection_name_var and st.student_fname_var and st.student_lname_var");
			pstmt.setString(1, locationid);
			pstmt.setString(2, accyear);
			pstmt.setString(3, classid);
			pstmt.setString(4, termId);
			pstmt.setString(5, HelperClass.convertUIToDatabase(startdate));
			pstmt.setString(6, HelperClass.convertUIToDatabase(enddate));
			pstmt.setString(7, paymode);
			rs=pstmt.executeQuery();
		
			while(rs.next()){
				count++;
				FeeCollectionVo vo=new FeeCollectionVo();
				vo.setSno(count);
				vo.setBilldate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
				vo.setChlnno(rs.getString("chln_no"));
				vo.setClassname(rs.getString("class"));
				vo.setStudentname(rs.getString("student"));
				vo.setPaymentMode(rs.getString("paymentMode"));
				vo.setAmount_paid_so_far(rs.getDouble("amount_paid")-rs.getDouble("concessionAmt"));
				vo.setTermName(rs.getString("termname"));
				vo.setFineAmount(rs.getDouble("fineAmount"));
				list.add(vo);
		
			
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getFeeCollectionReport : Ending");
		
		return list;
	}
	
	
	
	public ArrayList<FeeCollectionVo> getFeeCollectionMonthWiseReport(String locationid, String accyear, String classid,
			String termId, String monthName, String sectionId, String paymode) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getFeeCollectionMonthWiseReport : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        int count=0;
		ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
		try{
			
			String monthNo=HelperClass.getMothNumberByShortName(monthName);
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement("SELECT fc.concessionAmt,fc.fineAmount,cft.termname,fc.paidDate,fc.chln_no,CONCAT(cd.classdetails_name_var, ' ',cs.classsection_name_var) AS class, CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student ,fc.paymentMode,fc.amount_paid FROM  campus_fee_collection fc JOIN campus_student_classdetails csc ON fc.admissionNo = csc.student_id_int AND fc.accYear=csc.fms_acadamicyear_id_int JOIN campus_student st  ON fc.admissionNo = st.student_id_int  JOIN campus_classdetail cd ON cd.classdetail_id_int=csc.classdetail_id_int AND cd.locationId = csc.locationId JOIN campus_classsection cs ON cs.classsection_id_int=csc.classsection_id_int AND cs.locationId = csc.locationId  JOIN campus_fee_termdetails cft ON cft.termid=fc.termcode where csc.locationId like ? and fc.accYear like ? and cd.classdetail_id_int like ? AND fc.termcode like ? and fc.paidDate Like ? and csc.classsection_id_int LIKE ? AND fc.paymentMode Like ?  order by fc.paymentMode,fc.paidDate,cd.classdetails_name_var,cs.classsection_name_var,st.student_fname_var,st.student_lname_var");
			pstmt.setString(1, locationid);
			pstmt.setString(2, accyear);
			pstmt.setString(3, classid);
			pstmt.setString(4, termId);
			pstmt.setString(5, "%-"+monthNo+"-%");
			pstmt.setString(6,sectionId);
			pstmt.setString(7,paymode);
			rs=pstmt.executeQuery();
		
			while(rs.next()){
				count++;
				FeeCollectionVo vo=new FeeCollectionVo();
				vo.setSno(count);
				vo.setBilldate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
				vo.setChlnno(rs.getString("chln_no"));
				vo.setClassname(rs.getString("class"));
				vo.setStudentname(rs.getString("student"));
				vo.setPaymentMode(rs.getString("paymentMode"));
				vo.setAmount_paid_so_far(rs.getDouble("amount_paid")-rs.getDouble("concessionAmt"));
				vo.setTermName(rs.getString("termname"));
				vo.setFineAmount(rs.getDouble("fineAmount"));
				vo.setSingleMonthName(HelperClass.getMonthFullName(monthNo));
				list.add(vo);
		
			
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getFeeCollectionMonthWiseReport : Ending");
		
		return list;
	}

	public ArrayList<FeeCollectionVo> getFeeCollectionAdmissionWiseReport(String locationid, String accyear,
			String classid, String termId, String sectionId, String paymentType, String paymode) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getFeeCollectionAdmissionWiseReport : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        int count=0;
		ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
		try{
			
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement("SELECT fc.concessionAmt,fc.fineAmount,cft.termname,fc.paidDate,fc.chln_no,CONCAT(cd.classdetails_name_var, ' ',cs.classsection_name_var) AS class, CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var ,fc.paymentMode,fc.amount_paid FROM  campus_fee_collection fc JOIN campus_student_classdetails csc ON fc.admissionNo = csc.student_id_int AND fc.accYear=csc.fms_acadamicyear_id_int JOIN campus_student st  ON fc.admissionNo = st.student_id_int JOIN campus_classdetail cd ON cd.classdetail_id_int=csc.classdetail_id_int AND cd.locationId = csc.locationId JOIN campus_classsection cs ON cs.classsection_id_int=csc.classsection_id_int AND cs.locationId = csc.locationId  JOIN campus_fee_termdetails cft ON cft.termid=fc.termcode where csc.locationId like ? and fc.accYear like ? and cd.classdetail_id_int like ? AND fc.termcode like ?  and csc.classsection_id_int LIKE ? AND fc.paymentMode LIKE ?  order by fc.paymentMode,st.student_admissionno_var");
			pstmt.setString(1, locationid);
			pstmt.setString(2, accyear);
			pstmt.setString(3, classid);
			pstmt.setString(4, termId);
			pstmt.setString(5,sectionId);
			pstmt.setString(6, paymode);
			
			rs=pstmt.executeQuery();
		
			while(rs.next()){
				count++;
				FeeCollectionVo vo=new FeeCollectionVo();
				vo.setSno(count);
				vo.setBilldate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
				vo.setChlnno(rs.getString("chln_no"));
				vo.setClassname(rs.getString("class"));
				vo.setAddmissionno(rs.getString("student_admissionno_var"));
				vo.setStudentname(rs.getString("student"));
				vo.setPaymentMode(rs.getString("paymentMode"));
				vo.setAmount_paid_so_far(rs.getDouble("amount_paid")-rs.getDouble("concessionAmt"));
				vo.setTermName(rs.getString("termname"));
				vo.setFineAmount(rs.getDouble("fineAmount"));
				
				list.add(vo);
		
			
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getFeeCollectionAdmissionWiseReport : Ending");
		
		return list;
	}

	public ArrayList<FeeCollectionVo> getFeeCollectionBillWiseReport(String locationid, String accyear, String classid,
			String termId, String sectionId, String paymentType, String paymode) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getFeeCollectionBillWiseReport : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        int count=0;
		ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
		try{
			
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement("SELECT fc.concessionAmt,fc.fineAmount,cft.termname,fc.paidDate,fc.chln_no,CONCAT(cd.classdetails_name_var, ' ',cs.classsection_name_var) AS class, CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var ,fc.paymentMode,fc.amount_paid FROM  campus_fee_collection fc JOIN campus_student_classdetails csc ON fc.admissionNo = csc.student_id_int AND fc.accYear=csc.fms_acadamicyear_id_int JOIN campus_student st  ON fc.admissionNo = st.student_id_int JOIN campus_classdetail cd ON cd.classdetail_id_int=csc.classdetail_id_int AND cd.locationId = csc.locationId JOIN campus_classsection cs ON cs.classsection_id_int=csc.classsection_id_int AND cs.locationId = csc.locationId  JOIN campus_fee_termdetails cft ON cft.termid=fc.termcode where csc.locationId like ? and fc.accYear like ? and cd.classdetail_id_int like ? AND fc.termcode like ?  and csc.classsection_id_int LIKE ? AND fc.paymentMode LIKE ?  order by fc.paymentMode,fc.chln_no");
			pstmt.setString(1, locationid);
			pstmt.setString(2, accyear);
			pstmt.setString(3, classid);
			pstmt.setString(4, termId);
			pstmt.setString(5,sectionId);
			pstmt.setString(6, paymode);
			rs=pstmt.executeQuery();
		
			while(rs.next()){
				count++;
				FeeCollectionVo vo=new FeeCollectionVo();
				vo.setSno(count);
				vo.setBilldate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
				vo.setChlnno(rs.getString("chln_no"));
				vo.setClassname(rs.getString("class"));
				vo.setAddmissionno(rs.getString("student_admissionno_var"));
				vo.setStudentname(rs.getString("student"));
				vo.setPaymentMode(rs.getString("paymentMode"));
				vo.setAmount_paid_so_far(rs.getDouble("amount_paid")-rs.getDouble("concessionAmt"));
				vo.setTermName(rs.getString("termname"));
				vo.setFineAmount(rs.getDouble("fineAmount"));
				
				list.add(vo);
		
			
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getFeeCollectionBillWiseReport : Ending");
		
		return list;
	}

	public List<ReportMenuVo> getstudentHouseWise(ReportMenuVo vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentListAdmiWise  Starting");
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn =null;
		List<ReportMenuVo> stuList = new ArrayList<ReportMenuVo>();
		int count = 0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			
			if(!vo.getLocationId().equalsIgnoreCase("all") && !vo.getClassId().equalsIgnoreCase("all") && !vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement("SELECT csh.housename,CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollno,cd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId LEFT JOIN campus_house_settings csh ON cs.student_house=csh.house_id WHERE cs.classdetail_id_int = ? AND cs.classsection_id_int = ? AND cs.locationId = ? AND cs.fms_acadamicyear_id_int = ? AND cs.student_status !='TC' "
						+ "ORDER BY csh.housename,LENGTH(cs.classdetail_id_int),cd.classdetail_id_int,LENGTH(sec.classsection_name_var),sec.classsection_name_var,LENGTH(cs.student_rollno),cs.student_rollno");
			}
			else if(vo.getClassId().equalsIgnoreCase("all") && vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement("SELECT csh.housename,CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollno,cd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId LEFT JOIN campus_house_settings csh ON cs.student_house=csh.house_id WHERE cs.classdetail_id_int like ? AND cs.classsection_id_int like ? AND cs.locationId like ? AND cs.fms_acadamicyear_id_int = ? AND cs.student_status !='TC'"
						+ "ORDER BY csh.housename,LENGTH(cs.classdetail_id_int),cd.classdetail_id_int,LENGTH(sec.classsection_name_var),sec.classsection_name_var,LENGTH(cs.student_rollno),cs.student_rollno");
			}else if(vo.getClassId().equalsIgnoreCase("all") && !vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement("SELECT csh.housename,CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollno,cd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId LEFT JOIN campus_house_settings csh ON cs.student_house=csh.house_id WHERE cs.classdetail_id_int like ? AND cs.classsection_id_int = ? AND cs.locationId like ? AND cs.fms_acadamicyear_id_int = ? AND cs.student_status !='TC'"
						+ "ORDER BY csh.housename,LENGTH(cs.classdetail_id_int),cd.classdetail_id_int,LENGTH(sec.classsection_name_var),sec.classsection_name_var,LENGTH(cs.student_rollno),cs.student_rollno");
			}else if(!vo.getClassId().equalsIgnoreCase("all") && vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement("SELECT csh.housename,CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollno,cd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND "
						+ "cd.locationId = cs.locationId JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId LEFT JOIN campus_house_settings csh ON cs.student_house=csh.house_id WHERE cs.classdetail_id_int = ? AND cs.classsection_id_int like ? AND cs.locationId like ? AND cs.fms_acadamicyear_id_int = ? AND cs.student_status !='TC'"
						+ "ORDER BY csh.housename,LENGTH(cs.classdetail_id_int),cd.classdetail_id_int,LENGTH(sec.classsection_name_var),sec.classsection_name_var,LENGTH(cs.student_rollno),cs.student_rollno");
			}else{
				pstmt = conn.prepareStatement("SELECT csh.housename,CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student,st.student_admissionno_var,cs.student_rollnocd.classdetails_name_var,sec.classsection_name_var,loc.Location_Name FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId JOIN campus_classdetail cd ON cd.classdetail_id_int = st.classdetail_id_int AND "
						+ "cd.locationId = st.locationId JOIN campus_classsection sec ON sec.classsection_id_int = st.classsection_id_int AND sec.locationId = cs.locationId LEFT JOIN campus_house_settings csh ON cs.student_house=csh.house_id WHERE  cs.classdetail_id_int = ? AND cs.classsection_id_int = ? AND cs.locationId like ? AND cs.fms_acadamicyear_id_int = ? AND cs.student_status !='TC'"
						+ "ORDER BY csh.housename,LENGTH(cs.classdetail_id_int),cd.classdetail_id_int,LENGTH(sec.classsection_name_var),sec.classsection_name_var,LENGTH(cs.student_rollno),cs.student_rollno");
			}
			
			if(vo.getLocationId().equalsIgnoreCase("all")){
				vo.setLocationId("%%");
			}
			if(vo.getClassId().equalsIgnoreCase("all")){
				vo.setClassId("%%");
			}
			if(vo.getSectionId().equalsIgnoreCase("all")){
				vo.setSectionId("%%");
			}
			
			pstmt.setString(1,vo.getClassId());
			pstmt.setString(2,vo.getSectionId());
			pstmt.setString(3,vo.getLocationId());
			pstmt.setString(4,vo.getAccyearId());
			rs = pstmt.executeQuery();
			while(rs.next()){
				count ++;
				ReportMenuVo obj = new ReportMenuVo();
				obj.setSno(count);
				obj.setStudentnamelabel(rs.getString("student"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				if(rs.getString("student_rollno")!=null)
				obj.setStudentRollNo(rs.getString("student_rollno"));
				else
				obj.setStudentRollNo("-");
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("housename")!=null)
				obj.setStudentHouse(rs.getString("housename"));
				else
					obj.setStudentHouse("-");	
				stuList.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		return stuList;
	}

	public ArrayList<TransportVo> getBusRouteDetail(TransportVo obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getBusRouteDetail : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        int count=0;
		ArrayList<TransportVo> list=new ArrayList<TransportVo>();
		try{
			
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement("SELECT tr.RouteName,stg.stage_name,stg.amount FROM `transport_route_stage_mapping` tmap JOIN `transport_route` tr ON tmap.RouteCode=tr.RouteCode JOIN  `campus_fee_stage` stg ON stg.stage_id=tmap.StageCode AND tmap.accyear=stg.accyear WHERE tmap.accyear=? AND tmap.RouteCode LIKE ?");
			pstmt.setString(1, obj.getAccyear());
			pstmt.setString(2, obj.getRouteNo());
			
			
			rs=pstmt.executeQuery();
		
			while(rs.next()){
				count++;
				TransportVo vo=new TransportVo();
				vo.setSno(count);
				vo.setRouteName(rs.getString("RouteName"));;
				vo.setStage_name(rs.getString("stage_name"));
				vo.setAmount(rs.getInt("amount"));
				
				list.add(vo);
		
			
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getBusRouteDetail : Ending");
		
		return list;
	}

	public List<ReportMenuVo> getStudentCountSelectionWise(String accyear, String selection, String locationid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getBusRouteDetail : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        int count=0;
		ArrayList<ReportMenuVo> list=new ArrayList<ReportMenuVo>();
		try{
			
			conn=JDBCConnection.getSeparateConnection();
			if(selection.equalsIgnoreCase("classwise")) {
				pstmt=conn.prepareStatement("SELECT count(*) count,cs.student_religion_var,csc.classdetail_id_int,cr.religion,cs.student_gender_var FROM campus_student_classdetails csc JOIN campus_student cs ON csc.student_id_int=cs.student_id_int JOIN campus_religion cr ON cs.student_religion_var=cr.religionId WHERE csc.fms_acadamicyear_id_int=? AND csc.locationId=? GROUP BY csc.classdetail_id_int,cr.religion,cs.student_gender_var ORDER BY LENGTH(csc.classdetail_id_int),csc.classdetail_id_int");
			}
			else {
				pstmt=conn.prepareStatement("SELECT count(*) count,cs.student_religion_var,concat(csc.classdetail_id_int,'-',csc.classsection_id_int) classdetail_id_int,cr.religion,cs.student_gender_var FROM campus_student_classdetails csc JOIN campus_student cs ON csc.student_id_int=cs.student_id_int JOIN campus_religion cr ON cs.student_religion_var=cr.religionId WHERE csc.fms_acadamicyear_id_int=? AND csc.locationId=? GROUP BY csc.classdetail_id_int,csc.classsection_id_int,cr.religion,cs.student_gender_var ORDER BY LENGTH(csc.classdetail_id_int),csc.classdetail_id_int");
			}
			pstmt.setString(1,accyear);
			pstmt.setString(2, locationid);
			
			System.out.println(pstmt);
			rs=pstmt.executeQuery();
		
			while(rs.next()){
				
				ReportMenuVo vo=new ReportMenuVo();
				vo.setSno(rs.getInt("count"));
				vo.setClassId(rs.getString("classdetail_id_int"));
				vo.setCasteCategory(rs.getString("religion")+"_"+rs.getString("student_gender_var"));
				list.add(vo);
		
			
			}
			
			
			if(selection.equalsIgnoreCase("classwise")) {
				pstmt=conn.prepareStatement("SELECT count(*) count,cs.student_religion_var,csc.classdetail_id_int,cr.casteCategory,cs.student_gender_var FROM campus_student_classdetails csc JOIN campus_student cs ON csc.student_id_int=cs.student_id_int JOIN campus_caste_category cr ON cs.casteCategory=cr.castCatId WHERE csc.fms_acadamicyear_id_int=? AND csc.locationId=? GROUP BY csc.classdetail_id_int,cr.casteCategory,cs.student_gender_var ORDER BY LENGTH(csc.classdetail_id_int),csc.classdetail_id_int");
			}
			else {
				pstmt=conn.prepareStatement("SELECT count(*) count,cs.student_religion_var,concat(csc.classdetail_id_int,'-',csc.classsection_id_int) classdetail_id_int,cr.casteCategory,cs.student_gender_var FROM campus_student_classdetails csc JOIN campus_student cs ON csc.student_id_int=cs.student_id_int JOIN campus_caste_category cr ON cs.casteCategory=cr.castCatId WHERE csc.fms_acadamicyear_id_int=? AND csc.locationId=? GROUP BY csc.classdetail_id_int,csc.classsection_id_int,cr.casteCategory,cs.student_gender_var ORDER BY LENGTH(csc.classdetail_id_int),csc.classdetail_id_int");
			}
			pstmt.setString(1,accyear);
			pstmt.setString(2, locationid);
			
			System.out.println("hdjhf"+pstmt);
			rs=pstmt.executeQuery();
		
			while(rs.next()){
				
				ReportMenuVo vo1=new ReportMenuVo();
				vo1.setSno(rs.getInt("count"));
				vo1.setClassId(rs.getString("classdetail_id_int"));
				vo1.setCasteCategory("ct_"+rs.getString("casteCategory")+"_"+rs.getString("student_gender_var"));
				list.add(vo1);
		
			
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getBusRouteDetail : Ending");
		
		return list;
	
	}

	public List<ReligionCasteCasteCategoryVo> categoryName() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getBusRouteDetail : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ReligionCasteCasteCategoryVo> list=new ArrayList<ReligionCasteCasteCategoryVo>();
	try{
			
			conn=JDBCConnection.getSeparateConnection();
			
				pstmt=conn.prepareStatement("SELECT casteCategory FROM campus_caste_category GROUP BY casteCategory");
				
			
			
			
			rs=pstmt.executeQuery();
		
			while(rs.next()){
				ReligionCasteCasteCategoryVo vo=new ReligionCasteCasteCategoryVo();
				vo.setCasteCategory(rs.getString("casteCategory"));
				
				list.add(vo);
			
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getBusRouteDetail : Ending");
		
		return list;
	}

	public List<ReportMenuVo> getClassListSelectionWise(String accyear, String selection, String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getBusRouteDetail : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        int count=0;
		ArrayList<ReportMenuVo> list=new ArrayList<ReportMenuVo>();
		try{
			
			conn=JDBCConnection.getSeparateConnection();
			if(selection.equalsIgnoreCase("classwise")) {
				pstmt=conn.prepareStatement("SELECT * FROM campus_classdetail WHERE locationId=? ORDER BY LENGTH(classdetail_id_int),classdetail_id_int");
			}
			else {
				pstmt=conn.prepareStatement("SELECT concat(ccd.classdetail_id_int,'-',cc.classsection_id_int) classdetail_id_int,concat(ccd.classdetails_name_var,'-',cc.classsection_name_var) classdetails_name_var FROM campus_classsection cc JOIN campus_classdetail ccd ON ccd.classdetail_id_int=cc.classdetail_id_int WHERE ccd.locationId=? ORDER BY LENGTH(ccd.classdetail_id_int),ccd.classdetail_id_int,cc.classsection_name_var;");
				
			}
			pstmt.setString(1,locationid);
			System.out.println(pstmt);
			rs=pstmt.executeQuery();
		
			while(rs.next()){
				count++;
				ReportMenuVo vo=new ReportMenuVo();
				vo.setSno(count);
				vo.setClassId(rs.getString("classdetail_id_int"));
				vo.setClassname(rs.getString("classdetails_name_var"));
				list.add(vo);
		
			
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getBusRouteDetail : Ending");
		
		return list;
	}
}
   
