package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.OnlineFeeReceiptDao;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.vo.OnlineFeeReceiptVo;
import com.centris.campus.vo.ParentVO;

public class OnlineFeeReceiptDaoImpl implements OnlineFeeReceiptDao{

	private static final Logger logger = Logger.getLogger(OnlineFeeReceiptDaoImpl.class);
	
	public List<ParentVO> getParentChildDao(String sectionid, String huserid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeeReceiptDaoImpl : getParentChildDao Starting");	
		

		List<ParentVO> seclist = new ArrayList<ParentVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(ReportsMenuSqlConstants.GET_PARENT_STUDENT_DETAILS);
			
			pstmt.setString(1, sectionid);
			
			pstmt.setString(2, huserid);
			
			
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				
				ParentVO pojo = new ParentVO();
				
				pojo.setStudentid(rs.getString("student_id_int"));
				pojo.setStudentFnameVar(rs.getString("studentname"));
				
				
				
				seclist.add(pojo);
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeeReceiptDaoImpl : getParentChildDao Ending");
		
		
		return seclist;
	}

	
	public ArrayList<OnlineFeeReceiptVo> getSearchStudentFeeReceiptDao(
			OnlineFeeReceiptVo feevo) {
	
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeeReceiptDaoImpl : getSearchStudentFeeReceiptDao Starting");	
		
		
		
		
		
		ArrayList<OnlineFeeReceiptVo> seclist = new ArrayList<OnlineFeeReceiptVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count=0;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn
					.prepareStatement(ReportsMenuSqlConstants.GET_FEE_RECEIPT_LIST);
			
			
			pstmt.setString(1, feevo.getAccyearid());
			pstmt.setString(2, feevo.getStudentid());
			
		
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				count++;
				
				OnlineFeeReceiptVo vo = new OnlineFeeReceiptVo();
				vo.setCount(count);
				
				vo.setAddmissionnum(rs.getString("student_admissionno_var"));
				
				if(rs.getString("feepaiddate")=="0002-11-30"||rs.getString("feepaiddate").equalsIgnoreCase("0002-11-30"))
				{
					vo.setPaiddate("-");
				}
				else
				{
					vo.setPaiddate(HelperClass.convertDatabaseToUI(rs.getString("feepaiddate")));
				}
				
				

				
				vo.setStudentid(rs.getString("student_id_int"));
				vo.setStudentname(rs.getString("studentname"));
				vo.setClassname(rs.getString("classdetails_name_var"));
				vo.setSectionname(rs.getString("classsection_name_var"));
				vo.setTotalamount(rs.getDouble("feeAmount"));
				vo.setTermname(rs.getString("termname"));
				vo.setFeename(rs.getString("FeeName"));
/*				vo.setFeeamount(rs.getDouble("feeAmount"));*/
				vo.setAmt_paid(rs.getDouble("finalFeeAmtcollected"));
				vo.setBalance_amt(rs.getDouble("outstandingfee"));
				vo.setFeeAfterconcs(rs.getDouble("consfeeAmount"));
				vo.setConscper(rs.getDouble("concessionPercent")+"%");
				vo.setAccyearname(rs.getString("acadamic_year"));
				
				seclist.add(vo);
			}
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeeReceiptDaoImpl : getSearchStudentFeeReceiptDao Ending");
		
		
		return seclist;
	}

	
	
	
	
	
	
	
	
	
	
	
}
