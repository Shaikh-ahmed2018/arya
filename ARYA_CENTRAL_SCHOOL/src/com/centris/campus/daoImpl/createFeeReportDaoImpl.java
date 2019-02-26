package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.forms.FeeReportform;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.feeReportVO;

public class createFeeReportDaoImpl {

	private static final Logger logger = Logger.getLogger(ReportsMenuDaoImpl.class);

	public static ArrayList<feeReportVO> expandAll(feeReportVO obj) {
	
			   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in createFeeReportDaoImpl: expandAll : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			PreparedStatement pstmt1 = null;
			ResultSet rs1=null;
			
			Connection conn = null;
			ArrayList<feeReportVO> list=new ArrayList<feeReportVO>();
			int sno = 0;
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.Get_Fee_Report);
				pstmt.setString(1, obj.getAccyearid());
				pstmt.setString(2, obj.getClassnameid());
				pstmt.setString(3, obj.getSectionid());
				pstmt.setString(4, HelperClass.convertUIToDatabase(obj.getExamdate()));
				pstmt.setString(5, HelperClass.convertUIToDatabase(obj.getEnddate()));
				
				System.out.println(pstmt);
				
			
				System.out.println("academic id DOIMPL is ================>"+obj.getAccyearid());

				System.out.println("academic id  DOIMPL niw is ================>"+obj.getClassnameid());
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					
					sno++;
					feeReportVO feeVo=new feeReportVO();
					feeVo.setSno(sno);
					feeVo.setStudentName(rs.getString("student_fname_var"));
					feeVo.setTermname(rs.getString("termname"));
					feeVo.setTotalAmount(rs.getDouble("totalamount"));
					feeVo.setPaidAmount(rs.getDouble("paidamount"));
					feeVo.setPaidDate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
					
					if(rs.getString("is_paid").equalsIgnoreCase("Y"))
					{
						feeVo.setStatus("PAID");
					}else{
						feeVo.setStatus("NOT PAID");
					}
					pstmt1 = conn.prepareStatement("SELECT (SELECT acadamic_year FROM campus_acadamicyear WHERE acadamic_id=?) AS yearName,(SELECT classdetails_name_var FROM campus_classdetail WHERE classdetail_id_int=?) AS className");
					pstmt1.setString(1, obj.getAccyearid());
					pstmt1.setString(2, obj.getClassnameid());
					rs1=pstmt1.executeQuery();
					while(rs1.next()){
						feeVo.setAccyear(rs1.getString("yearName"));
						feeVo.setClassname(rs1.getString("className"));
					}
					
					list.add(feeVo);
					
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
					+ " Control in createFeeReportDaoImpl : expandAll : Ending");
			
			return list;
	
	}

	public static ArrayList<feeReportVO> getclasssectionDetails(feeReportVO obj) {
		
		return null;
	}

	/*public ArrayList<ReportMenuVo> getTerm(String locId) {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in createFeeReportDaoImpl: getTerm : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ArrayList<ReportMenuVo> list=new ArrayList<ReportMenuVo>();
			int sno = 0;
			try {
				conn = JDBCConnection.getSeparateConnection();
				pstmt=conn.prepareStatement("SELECT `termid`,`termname` FROM `campus_fee_termdetails` WHERE `locationId`=?");
				pstmt.setString(1, locId);
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
					+ " Control in createFeeReportDaoImpl : expandAll : Ending");
			
			return list;
		
	}
*/


}
