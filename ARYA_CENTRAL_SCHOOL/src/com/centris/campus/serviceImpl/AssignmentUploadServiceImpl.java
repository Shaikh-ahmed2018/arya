package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.AssignmentUploadDao;
import com.centris.campus.dao.FeeSetupDao;
import com.centris.campus.daoImpl.AssignmentUploadDaoImpl;
import com.centris.campus.daoImpl.FeeSetupDaoImpl;
import com.centris.campus.forms.AssignmentUploadForm;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.service.AssignmentUploadService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AssignmentUploadVo;
import com.centris.campus.vo.AssignmentViewVO;
import com.centris.campus.vo.ProjectVO;
import com.centris.campus.vo.ReportMenuVo;

public class AssignmentUploadServiceImpl implements AssignmentUploadService{
	
	private static final Logger logger = Logger.getLogger(AssignmentUploadServiceImpl.class);

	public ArrayList<AssignmentUploadVo> getStudentDetails(String section) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: getStudentDetails Starting");
		
		AssignmentUploadDao assignmentDao=new AssignmentUploadDaoImpl();
		ArrayList<AssignmentUploadVo> studentList=new ArrayList<AssignmentUploadVo>();
		
		try {
			
			studentList = assignmentDao.getStudentDetails(section);
		
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: getStudentDetails Ending");
		
		return studentList;
	}

	@Override
	public String insertAssignment(AssignmentViewVO viewVo) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: insertAssignment Starting");
		
		AssignmentUploadDao assignmentDao=new AssignmentUploadDaoImpl();
		String status=null;
		
		try {
			
			status = assignmentDao.insertAssignment(viewVo);
		
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: insertAssignment Ending");
		
		return status;
	}
	
	@Override
	public ArrayList<AssignmentUploadVo> getAssignment(String userId,String accYearString,String searchTerm) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: getAssignment Starting");
		
		AssignmentUploadDao assignmentDao=new AssignmentUploadDaoImpl();
		ArrayList<AssignmentUploadVo> assignmentList=new ArrayList<AssignmentUploadVo>();
		
		try {
			
			assignmentList = assignmentDao.getAssignment(userId,accYearString,searchTerm);
		
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: getAssignment Ending");
		
		return assignmentList;
	}

	@Override
	public ArrayList<AssignmentUploadVo> getAssignmentDetails(String assignmentId) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: getAssignmentDetails Starting");
		
		AssignmentUploadDao assignmentDao=new AssignmentUploadDaoImpl();
		ArrayList<AssignmentUploadVo> assignmentList=new ArrayList<AssignmentUploadVo>();
		
		try {
			
			assignmentList = assignmentDao.getAssignmentDetails(assignmentId);
		
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: getAssignmentDetails Ending");
		
		return assignmentList;
	}

	@Override
	public AssignmentUploadVo getSingleAssignment(String assignmentId) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: getSingleAssignment Starting");
		
		AssignmentUploadDao assignmentDao=new AssignmentUploadDaoImpl();
		AssignmentUploadVo assimentVo=new AssignmentUploadVo();
		
		try {
			
			assimentVo = assignmentDao.getSingleAssignment(assignmentId);
		
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: getSingleAssignment Ending");
		
		return assimentVo;
	}

	@Override
	public String updateAssignmentDetails(AssignmentUploadVo assignmentVo) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: updateAssignmentDetails Starting");
		
		AssignmentUploadDao assignmentDao=new AssignmentUploadDaoImpl();
		String status=null;
		
		try {
			
			status = assignmentDao.updateAssignmentDetails(assignmentVo);
		
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: updateAssignmentDetails Ending");
		
		return status;
	}

	@Override
	public String deleteAssignment(AssignmentViewVO assignmentCode) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: deleteAssignment Starting");
		
		AssignmentUploadDao assignmentDao=new AssignmentUploadDaoImpl();
		String status=null;
		
		try {
			
			status = assignmentDao.deleteAssignment(assignmentCode);
		
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: deleteAssignment Ending");
		
		return status;
	}

	@Override
	public String addProject(ProjectVO viewVo) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: deleteAssignment Starting");
		
		AssignmentUploadDao assignmentDao=new AssignmentUploadDaoImpl();
		String status=null;
		
		try {
			
			status = assignmentDao.addProject(viewVo);
		
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: deleteAssignment Ending");
		
		return status;
	}

	@Override
	public ArrayList<ProjectVO> getProjectList(String userId,String accYearString,String searchTerm) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: getAssignmentDetails Starting");
		
		AssignmentUploadDao assignmentDao=new AssignmentUploadDaoImpl();
		ArrayList<ProjectVO> List=new ArrayList<ProjectVO>();
		
		try {
			
			List = assignmentDao.getProjectList(userId,accYearString,searchTerm);
		
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: getAssignmentDetails Ending");
		
		return List;
	}

	@Override
	public ArrayList<ProjectVO> getProjectDetails(String projectId) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: getAssignmentDetails Starting");
		
		AssignmentUploadDao assignmentDao=new AssignmentUploadDaoImpl();
		ArrayList<ProjectVO> projectList=new ArrayList<ProjectVO>();
		
		try {
			
			projectList = assignmentDao.getProjectDetails(projectId);
		
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: getAssignmentDetails Ending");
		
		return projectList;
	}

	@Override
	public ProjectVO getSingleProject(String projectId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: getSingleProject Starting");
		
		AssignmentUploadDao assignmentDao=new AssignmentUploadDaoImpl();
		ProjectVO projectvo=new ProjectVO();
		
		try {
			
			projectvo = assignmentDao.getSingleProject(projectId);
		
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: getSingleProject Ending");
		
		return projectvo;
	}

	@Override
	public String updateProjectDetails(ProjectVO projectvo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: updateProjectDetails Starting");
		
		AssignmentUploadDao assignmentDao=new AssignmentUploadDaoImpl();
		String status=null;
		
		try {
			
			status = assignmentDao.updateProjectDetails(projectvo);
		
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: updateProjectDetails Ending");
		
		return status;
	}

	@Override
	public String deleteProject(ProjectVO project) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: deleteProject Starting");
		
		AssignmentUploadDao assignmentDao=new AssignmentUploadDaoImpl();
		String status=null;
		
		try {
			
			status = assignmentDao.deleteProject(project);
		
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadServiceImpl: deleteProject Ending");
		
		return status;
	}

}
