package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.daoImpl.TeacherAttendanceDaoImpl;
import com.centris.campus.forms.TeacherAttendanceForm;
import com.centris.campus.service.TeacherAttendanceService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.PostAttendanceVO;
import com.centris.campus.vo.TeacherAttendanceStatusVo;
import com.centris.campus.vo.TeacherAttendanceVo;
import com.centris.campus.vo.TeacherManualAttendanceVO;

public class TeacherAttendanceServiceImpl implements TeacherAttendanceService {
	private static final Logger logger = Logger
			.getLogger(TeacherAttendanceServiceImpl.class);

	@Override
	public boolean insertTeacherAttendanceService(
			TeacherAttendanceForm attendanceForm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceServiceImpl:insertTeacherAttendanceService Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceServiceImpl:insertTeacherAttendanceService Ending");
		// TODO Auto-generated method stub
		return new TeacherAttendanceDaoImpl()
				.insertTeacherAttendanceDao(attendanceForm);
	}

	@Override
	public List<TeacherAttendanceVo> getTeacherAttendanceService(
			TeacherAttendanceForm attendanceForm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceServiceImpl:getTeacherAttendanceService Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceServiceImpl:getTeacherAttendanceService Ending");
		// TODO Auto-generated method stub
		return new TeacherAttendanceDaoImpl()
				.getTeachersAttendanceDetailsDao(attendanceForm);
	}

	public String PostAttendance(PostAttendanceVO postatendancevo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceServiceImpl: PostAttendance Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceServiceImpl: PostAttendance Ending");
		// TODO Auto-generated method stub
		return new TeacherAttendanceDaoImpl().PostAttendance(postatendancevo);
	}

	@Override
	public ArrayList<TeacherAttendanceStatusVo> getTeacherAttendanceStatus() {
		// TODO Auto-generated method stub
		return new TeacherAttendanceDaoImpl().getTeacherAttendanceStatus();
	}

	@Override
	public ArrayList<TeacherAttendanceStatusVo> getTeacherAttendanceByDate(
			String attendanceDate) {
		// TODO Auto-generated method stub
		return new TeacherAttendanceDaoImpl()
				.getTeacherAttendanceByDate(attendanceDate);
	}

	@Override
	public int approvedByPrincipal(
			ArrayList<TeacherAttendanceStatusVo> attendanceStatusVo) {
		// TODO Auto-generated method stub
		return new TeacherAttendanceDaoImpl()
				.approvedByPrincipal(attendanceStatusVo);
	}

	public ArrayList<TeacherManualAttendanceVO> GetTeacherAttendance(String user,String date) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceServiceImpl: GetTeacherAttendance Starting");
		ArrayList<TeacherManualAttendanceVO> statuslist = null;
		try {
			statuslist = new TeacherAttendanceDaoImpl()
					.GetTeacherAttendance(user,date);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceServiceImpl: GetTeacherAttendance Ending");
		return statuslist;
	}

	public String updateTeacherAttendanceDetails(String teacherid[],
			String TeacherStatus[],String date,String updateby) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceServiceImpl: updateTeacherAttendanceDetails Starting");
		String status=null;
		try {
			status=new TeacherAttendanceDaoImpl().updateTeacherAttendanceDetails(teacherid, TeacherStatus,date,updateby);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceServiceImpl: updateTeacherAttendanceDetails Ending");
		return status;
	}
}
