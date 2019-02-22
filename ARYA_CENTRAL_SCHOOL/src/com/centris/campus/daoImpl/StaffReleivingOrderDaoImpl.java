package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StaffReleivingOrderDao;
import com.centris.campus.pojo.LstmsStudentPOJO;
import com.centris.campus.pojo.RelievingOrderPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReleivingOrderVo;
import com.centris.campus.vo.UserDetailVO;
import com.mysql.jdbc.PreparedStatement;

public class StaffReleivingOrderDaoImpl implements StaffReleivingOrderDao{

	private static final Logger logger = Logger
			.getLogger(StaffReleivingOrderDaoImpl.class);
	
	
	
	public List<AllTeacherDetailsVo> getTeachingListDaoImpl(
			AllTeacherDetailsVo vo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getTeachingListDaoImpl Starting");
		
		ArrayList<AllTeacherDetailsVo> teachinglist = new ArrayList<AllTeacherDetailsVo>();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
		try {
			
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_TEACHER_TEACHING_LIST);
			
			pstmt.setString(1, vo.getUsertype());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				AllTeacherDetailsVo teavo = new AllTeacherDetailsVo();
				
				teavo.setTeacherId(rs.getString("TeacherID"));
				teavo.setTeacherName(rs.getString("teachername"));
				
				
				
				teachinglist.add(teavo);
			}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getTeachingListDaoImpl Ending");
		
		
		
		return teachinglist;
	}



	public List<AllTeacherDetailsVo> getNonTeachingListDaoImpl(
			AllTeacherDetailsVo vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getTeachingListDaoImpl Starting");
		
		ArrayList<AllTeacherDetailsVo> teachinglist = new ArrayList<AllTeacherDetailsVo>();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
		try {
			
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_NONTEACHER_TEACHING_LIST);
			
			pstmt.setString(1, vo.getUsertype());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				AllTeacherDetailsVo teavo = new AllTeacherDetailsVo();
				
				teavo.setTeacherId(rs.getString("TeacherID"));
				teavo.setTeacherName(rs.getString("teachername"));
				
				
				
				teachinglist.add(teavo);
			}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getTeachingListDaoImpl Ending");
		
		
		
		return teachinglist;
	}



	
	public List<UserDetailVO> getUsersListDaoImpl() {
	
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getUsersListDaoImpl Starting");
		
		 List<UserDetailVO> userlist = new  ArrayList<UserDetailVO>();
		 
		 PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_USER_LIST);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				UserDetailVO vo = new UserDetailVO();
				
				vo.setUserId(rs.getString("RoleCode"));
				vo.setUserName(rs.getString("RoleName"));
				
				userlist.add(vo);
				
			}
			
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getUsersListDaoImpl Ending");
		
		return userlist;
	}


	public List<ReleivingOrderVo> getReleivingDetailsDaoImpl(
			RelievingOrderPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getReleivingDetailsDaoImpl Starting");
		
		 List<ReleivingOrderVo> details = new  ArrayList<ReleivingOrderVo>();
		 
		 PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
		 
		 
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_RELEIVING_DETAILS_LIST);
			
			pstmt.setString(1, pojo.getTeachername());
			
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				ReleivingOrderVo revpojo = new ReleivingOrderVo();
				
				revpojo.setTeachername1(rs.getString("teachername"));
				revpojo.setEmpid(rs.getString("registerId"));
				revpojo.setDoj(rs.getString("dateofJoining"));
				revpojo.setDesignation(rs.getString("designationName"));
				revpojo.setPfnumber(rs.getString("EmployeePfNo"));
				revpojo.setSalary(rs.getString("CTC"));
				
				
				
				
				details.add(revpojo);
				
				
				
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getReleivingDetailsDaoImpl Ending");
		
		
		return details;
	}

}
