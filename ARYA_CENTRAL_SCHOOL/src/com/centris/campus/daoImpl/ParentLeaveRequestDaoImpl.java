package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.ParentLeaveRequestDao;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ParentModuleUtil;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.RemainderMasterVO;

public class ParentLeaveRequestDaoImpl implements ParentLeaveRequestDao {
	private static final Logger logger = Logger
			.getLogger(ParentLeaveRequestDaoImpl.class);
	
	
	
	
	
	public String leaveRequestEntryDao(LeaveRequestVo leavevo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : leaveRequestEntryDao Starting");
		
		
		
		
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
	
		  String status = MessageConstants.REQUESTFALSE;
		  
		  
		  try {
			  
			
			  conn = JDBCConnection.getSeparateConnection();
				pstmt = conn
						.prepareStatement(ParentModuleUtil.SAVE_LEAVE_REQUEST);
				
				
				
				pstmt.setString(1,leavevo.getRequestto());
				pstmt.setString(2,leavevo.getStudentFname());
				pstmt.setString(3,leavevo.getFromdate());
				pstmt.setString(4,leavevo.getTodate());
				pstmt.setString(5,leavevo.getStarttime());
				pstmt.setString(6,leavevo.getEndtime());
				pstmt.setString(7,leavevo.getLeavetype());
				pstmt.setString(8,leavevo.getTotalleave());
				pstmt.setString(9,leavevo.getReason());
				pstmt.setString(10,leavevo.getFileupload());
				pstmt.setString(11, "NOTAPPROVED");
				pstmt.setString(12,leavevo.getCreateuser());
				pstmt.setTimestamp(13, time_stamp);
				
				
				
				
				int count = pstmt.executeUpdate();
				
				if(count>0){
					
					status = MessageConstants.REQUESTTRUE;
				}
			  
		} catch (Exception e) {
			logger.error(e.getMessage(), e); 
			e.printStackTrace();
		}
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : leaveRequestEntryDao  Ending");
		
		return status;
	}

	
	public String updateleaveRequestEntryDao(LeaveRequestVo leavevo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updateleaveRequestEntryDao Starting");
		
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		PreparedStatement pstmt = null;
		int rs = 0;
		String result ="";

		Connection conn = null;
	
		  String status = MessageConstants.REQUESTFALSE;
		  
			
		  try {
			 
			  conn = JDBCConnection.getSeparateConnection();
				pstmt = conn
						.prepareStatement(ParentModuleUtil.UPDATE_LEAVE_REQUEST);
		
				pstmt.setString(1,leavevo.getRequestto());
				pstmt.setString(2,leavevo.getStudentFname());
				
				pstmt.setString(3,leavevo.getTotalleave());
				pstmt.setString(4,leavevo.getFromdate());
				pstmt.setString(5,leavevo.getTodate());
				pstmt.setString(6,leavevo.getStarttime());
				pstmt.setString(7,leavevo.getEndtime());
				pstmt.setString(8,leavevo.getLeavetype());
				pstmt.setString(9,leavevo.getFileupload());
				pstmt.setString(10,leavevo.getReason());
				pstmt.setTimestamp(11,time_stamp);
				pstmt.setString(12,leavevo.getCreateuser());
				pstmt.setString(13, "NOTAPPROVED");
				pstmt.setInt(14, leavevo.getSno());
				
				
				rs = pstmt.executeUpdate();
				
			  
		} catch (Exception e) {
			logger.error(e.getMessage(), e); 
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updateleaveRequestEntryDao  Ending");
		return result;
	}



	public LeaveRequestVo getRequestLeaveDao(int sno) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getRequestLeaveDao Starting");
		
		LeaveRequestVo leavelist = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			
			leavelist = new LeaveRequestVo();
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn
					.prepareStatement(ParentModuleUtil.GET_LEAVE_DETAILS);
			
			pstmt.setInt(1, sno);
		
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				leavelist.setRequestto(rs.getString("teachername"));
				leavelist.setTotalleave(rs.getString("NoofLeaves"));
				leavelist.setFromdate(HelperClass.convertDatabaseToUI(rs.getString("StartDate")));
				
				leavelist.setStudentFname(rs.getString("student_fname_var"));
				leavelist.setTodate(HelperClass.convertDatabaseToUI(rs.getString("EndDate")));
				leavelist.setStarttime(rs.getString("SessionStart"));
				leavelist.setEndtime(rs.getString("SessionEnd"));
				leavelist.setLeavetype(rs.getString("LeaveType"));
				leavelist.setFileupload(rs.getString("filepaath"));
				leavelist.setReason(rs.getString("ReasonForLeave"));
				leavelist.setRequesttoid(rs.getString("RequestedTo"));
				leavelist.setSno(sno);
				
			
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e); 
			e.printStackTrace();
		}
		
	
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getRequestLeaveDao  Ending");
		
		return leavelist;
	}


	
	public ArrayList<RemainderMasterVO> getRemainderlistDao() {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getRemainderlistDao Starting");
		
		
		 ArrayList<RemainderMasterVO> remainderlist = new ArrayList<RemainderMasterVO>();
		 PreparedStatement pstmt = null;
			ResultSet rs = null;
			Connection connection = null;
			
			try {
				
				connection = JDBCConnection.getSeparateConnection();
				pstmt = connection.prepareStatement(ParentModuleUtil.GET_PARENT_REMAINDER);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					
					RemainderMasterVO vo = new RemainderMasterVO();
					
					vo.setName(rs.getString("remainder_title"));
					vo.setDescription(rs.getString("description"));
					vo.setRemtype(rs.getString("remainder_to"));
					
					
					remainderlist.add(vo);
					
				
				}
					
			} catch (Exception e) {
				logger.error(e.getMessage(), e); 
				e.printStackTrace();
			}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getRemainderlistDao  Ending");
		
			return remainderlist;
	}

}
