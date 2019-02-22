package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StudentAttendanceDao;
import com.centris.campus.daoImpl.StudentAttendanceDaoImpl;
import com.centris.campus.pojo.StudentAttendancePojo;
import com.centris.campus.service.StudentAttendanceService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.StudentAttendanceVo;

import java.util.List;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.LstmsStudentPOJO;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentAttendanceReportVo;

public class StudentAttendanceServiceImpl implements StudentAttendanceService{

	private static final Logger logger = Logger.getLogger(StudentAttendanceServiceImpl.class);
	
	@Override
	public ArrayList<StudentAttendanceVo> getStudentsAttendanceList(String startDate, String endDate) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentsAttendanceList : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
			
			try {
				
				staffAttendanceList =	dao.getStudentsAttendanceList(startDate,endDate);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentsAttendanceList: Ending");
			
			return staffAttendanceList;
		}

	@Override
	public ArrayList<StudentAttendanceVo> getStudentAttendanceDetails(StudentAttendancePojo studentAttPojo) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentAttendanceDetails : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
			
			try {
				
				staffAttendanceList =	dao.getStudentAttendanceDetails(studentAttPojo);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentAttendanceDetails: Ending");
			
			return staffAttendanceList;
		}

	@Override
	public String updateAttendanceStatus(StudentAttendancePojo studentAttPojo) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: updateAttendanceStatus : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			String status=null;
			
			try {
				
				status =	dao.updateAttendanceStatus(studentAttPojo);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: updateAttendanceStatus: Ending");
			
			return status;
		}

	
	
StudentAttendanceDao dao = new StudentAttendanceDaoImpl();
	
	public List<ParentVO> getAllStudentService(String classVal,
			String sectionVal) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl:getAllStudentService Starting");
		
		List<ParentVO> student = new ArrayList<ParentVO>();
		
		
		ParentVO parentvo;
		try {
			
			
			List<LstmsStudentPOJO> POJOList =dao.getAllStudentDao(classVal,sectionVal);
			
			for (int i = 0; i < POJOList.size(); i++) {
				
				parentvo = new ParentVO();
				LstmsStudentPOJO lstmsStudentPOJO = (LstmsStudentPOJO) POJOList.get(i);
				parentvo.setStudentFnameVar(lstmsStudentPOJO
						.getStudentFnameVar());
				parentvo.setStudentid((lstmsStudentPOJO.getStudentIdInt()));
				parentvo.setStdAdmisiionNo(lstmsStudentPOJO.getStudentAdmissionnoVar());
				student.add(parentvo);
				
			}
			
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getAllStudentService Ending");
		
		
		return student;
	}


	public ArrayList<StudentAttendanceReportVo> getStudentAttendanceReportService(
			StudentAttendanceReportVo vo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl : getStudentAttendanceReportService Starting");
		
		ArrayList<StudentAttendanceReportVo> studentlist = null;
		
		try {
			
			studentlist = dao. getStudentAttendanceReportDao(vo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getStudentAttendanceReportService Ending");
		return studentlist;
	}


	
	public ArrayList<StudentAttendanceReportVo> getStudentAttendanceListReportService(
			StudentAttendanceReportVo stuvo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl : getStudentAttendanceListReportService Starting");
		
		ArrayList<StudentAttendanceReportVo> studentlist = null;
		
		try {
			
			studentlist = dao. getStudentAttendanceListReportDao(stuvo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getStudentAttendanceListReportService Ending");
		
		
		
		return studentlist;
	}


	
	public StreamDetailsVO getStreamNameService(String stream) {
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl : getStreamNameService Starting");
		
		StreamDetailsVO selected = null;
		try {
			
			selected=dao. getStreamNameDaoImpl(stream);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getStreamNameService Ending");
		
		return selected;
	}


	
	public ClassPojo getClassNameService(String classname) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl : getClassNameService Starting");
		
		ClassPojo selected = null;
		try {
			
			selected=dao. getClassNameDaoImpl(classname);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getClassNameService Ending");
		
		return selected;
	}


	
	public SectionPojo getSectionNameService(String sectionname) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl : getSectionNameService Starting");
		
		SectionPojo selected = null;
		try {
			
			selected=dao. getSectionNameDaoImpl(sectionname);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getSectionNameService Ending");
		
		return selected;
	}


	
	public ParentVO getStudentNameService(String student) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl : getSectionNameService Starting");
		
		ParentVO selected = null;
		try {
			
			selected=dao. getStudentNameDaoImpl(student);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getSectionNameService Ending");
		
		return selected;
	}

	@Override
	public StudentAttendanceVo getStudentPeriodAttendance(StudentAttendancePojo AttendancePojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl : getStudentPeriodAttendance Starting");
		
		StudentAttendanceVo selected = null;
		try {
			
			selected=dao. getStudentPeriodAttendance(AttendancePojo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getStudentPeriodAttendance Ending");
		
		return selected;
	}

	@Override
	public String updateStudentPeriodAtt(StudentAttendancePojo AttendancePojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl : updateStudentPeriodAtt Starting");
		
		String status = null;
		try {
			
			status=dao. updateStudentPeriodAtt(AttendancePojo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :updateStudentPeriodAtt Ending");
		
		return status;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getteacherByClass(String classId, String sectionId) {
		
		return dao.getteacherByClass(classId,sectionId);
	}

	@Override
	public ArrayList<StudentAttendanceVo> getClassSpec(String classId) {
		return dao.getClassSpec(classId);
	}

	@Override
	public StudentAttendanceVo editAttendance(StudentAttendancePojo pojo) {
		return dao.editAttendance(pojo);
	}

	@Override
	public List<ParentVO> getStudentByTransport(String classId, String sectionId) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl:getAllStudentService Starting");
		
		List<ParentVO> student = new ArrayList<ParentVO>();
		
		
		ParentVO parentvo;
		try {
			
			
			List<LstmsStudentPOJO> POJOList =dao. getStudentByTransport(classId,sectionId);
			
			for (int i = 0; i < POJOList.size(); i++) {
				
				parentvo = new ParentVO();
				LstmsStudentPOJO lstmsStudentPOJO = (LstmsStudentPOJO) POJOList.get(i);
				parentvo.setStudentFnameVar(lstmsStudentPOJO
						.getStudentFnameVar());
				parentvo.setStudentid((lstmsStudentPOJO.getStudentIdInt()));
				parentvo.setStdAdmisiionNo(lstmsStudentPOJO.getStudentAdmissionnoVar());
				student.add(parentvo);
				
			}
			
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getAllStudentService Ending");
		
		
		return student;
	}

	@Override
	public ArrayList<StudentAttendanceVo> searchStudentsAttendanceList(String locationId, String accYear) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentsAttendanceList : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
			
			try {
				
				staffAttendanceList =	dao.searchStudentsAttendanceList(locationId,accYear);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentsAttendanceList: Ending");
			
			return staffAttendanceList;
		}

	@Override
	public List<StudentAttendanceVo> getAttendenceByClassList(String locationid, String accyear, String classname) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentsAttendanceList : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
			
			try {
				
				staffAttendanceList =	dao.getAttendenceByClassList(locationid,accyear,classname);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentsAttendanceList: Ending");
			
			return staffAttendanceList;
		}

	@Override
	public List<StudentAttendanceVo> getAttendenceByClassSectionList(String locationid, String accyear,String classname, String sectionid) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentsAttendanceList : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
			
			try {
				
				staffAttendanceList =	dao.getAttendenceByClassSectionList(locationid,accyear,classname,sectionid);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentsAttendanceList: Ending");
			
			return staffAttendanceList;
		}

	@Override
	public List<StudentAttendanceVo> getTeacherList(String locationid) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getTeacherList : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			ArrayList<StudentAttendanceVo> staffList=new ArrayList<StudentAttendanceVo>();
			
			try {
				
				staffList =	dao.getTeacherList(locationid);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getTeacherList: Ending");
			
			return staffList;
		}

	@Override
	public List<StudentAttendanceVo> getAttendanceListByTeacher(String locationid, String accyear, String classname,String sectionid, String teacherid) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentsAttendanceList : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
			
			try {
				
				staffAttendanceList =	dao.getAttendanceListByTeacher(locationid,accyear,classname,sectionid,teacherid);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentsAttendanceList: Ending");
			
			return staffAttendanceList;
		}

	@Override
	public List<StudentAttendanceVo> getAttendanceListByDate(String startdate, String enddate) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentsAttendanceList : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
			
			try {
				
				staffAttendanceList =	dao.getAttendanceListByDate(startdate,enddate);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentsAttendanceList: Ending");
			
			return staffAttendanceList;
		}
	

	
}
