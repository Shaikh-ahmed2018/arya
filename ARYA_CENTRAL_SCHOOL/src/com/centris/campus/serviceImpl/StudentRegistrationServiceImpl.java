/**
 * 
 */
package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StudentRegistrationDao;
import com.centris.campus.daoImpl.StudentRegistrationDaoImpl;
import com.centris.campus.forms.StudentRegistrationForm;
import com.centris.campus.pojo.PageFilterpojo;
import com.centris.campus.service.StudentRegistrationService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentAttendanceVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TransportTypeVO;
import com.centris.campus.vo.ViewallSubjectsVo;
import com.centris.campus.vo.registrationvo;

/**
 * @author sathish
 * 
 */
public class StudentRegistrationServiceImpl implements
		StudentRegistrationService {
	private static final Logger logger = Logger
			.getLogger(StudentRegistrationServiceImpl.class);

	@Override
	public List<StudentRegistrationVo> getAcademicYear() throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:getAcademicYear  Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:getAcademicYear  Ending");
		// TODO Auto-generated method stub
		return new StudentRegistrationDaoImpl().getAcademicYear();
	}

	@Override
	public Map<String, String> saveStudentRegistration(StudentRegistrationForm studentRegistrationForm) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:saveStudentRegistration  Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:saveStudentRegistration Ending");
		return new StudentRegistrationDaoImpl().saveStudentRegistration(studentRegistrationForm);
	}

	@Override   
	public List<StudentRegistrationVo> studentSearch(
			StudentRegistrationVo studentRegistrationVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:studentSearch  Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:studentSearch  Ending");
		// TODO Auto-generated method stub
		return new StudentRegistrationDaoImpl()
				.studentSearch(studentRegistrationVo);
	}

	@Override
	public List<StudentRegistrationVo> searchStudentResult(
			StudentRegistrationVo registrationVo) {
		// TODO Auto-generated method stub
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:searchStudentResult  Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:searchStudentResult  Ending");
		return new StudentRegistrationDaoImpl()
				.searchStudentResult(registrationVo);
	}

	public String modifyStudentDetails(StudentRegistrationForm modifyform) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl: modifyStudentDetails Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl: modifyStudentDetails  Ending");
		// TODO Auto-generated method stub

		return new StudentRegistrationDaoImpl().modifyStudentDetails(modifyform);
	}

	@Override
	public List<StudentRegistrationVo> getStudentquota() throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:getStudentquota   Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:getStudentquota  Ending");
		// TODO Auto-generated method stub
		return new StudentRegistrationDaoImpl().getStudentquota();
	}

	@Override
	public List<StudentRegistrationVo> studentSearchByParent(
			StudentRegistrationVo registrationVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:studentSearchByParent   Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:studentSearchByParent Ending");

		// TODO Auto-generated method stub
		return new StudentRegistrationDaoImpl()
				.studentSearchByParent(registrationVo);
	}

	@Override
	public List<StudentRegistrationVo> getStudentCaste() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentCaste  Starting");
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentCaste  Ending");
		// TODO Auto-generated method stub
		return new StudentRegistrationDaoImpl().getStudentCaste();
	}

	@Override
	public int validateDuplicateData(StudentRegistrationForm formObj,
			String type) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : validateDuplicateData  Starting");
		StudentRegistrationDao obj = new StudentRegistrationDaoImpl();
		int count = 0;
		try {
			count = obj.validateDuplicateData(formObj, type);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : validateDuplicateData  Ending");
		return count;
	}

	public ArrayList<StudentInfoVO> getAllStudentsDetails(String classname,
			String accodamicyr) {

		return new StudentRegistrationDaoImpl().getAllStudentsDetails(
				classname, accodamicyr);
	}

	public String getTransportCategoryType(String trnsportTypeId) {

		return new StudentRegistrationDaoImpl()
				.getTransportCategoryType(trnsportTypeId);
	}

	public List<StudentRegistrationVo> studentSearchbyClass(
			StudentRegistrationVo studentRegistrationVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : studentSearchbyClass  Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : studentSearchbyClass  Ending");
		// TODO Auto-generated method stub
		return new StudentRegistrationDaoImpl()
				.studentSearchbyClass(studentRegistrationVo);
	}

	public List<StudentRegistrationVo> getChildCategory(String schoolLocation) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getChildCategory  Starting");
		List<StudentRegistrationVo> categorylist = null;
		try {
			categorylist = new StudentRegistrationDaoImpl().getChildCategory(schoolLocation);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getChildCategory  Ending");
		// TODO Auto-generated method stub

		return categorylist;
	}

	public List<StudentRegistrationVo> getClassDetails(String catecory) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getClassDetails(catecory);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> getSection(String classval) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getSection  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getSection(classval);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getSection  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> getConcessionDetails() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConcessionDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getConcessionDetails();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConcessionDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<TransportTypeVO> transportypedetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : transportypedetails  Starting");
		List<TransportTypeVO> list = null;
		try {
			list = new StudentRegistrationDaoImpl().transportypedetails();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : transportypedetails  Ending");
		// TODO Auto-generated method stub

		return list;

	}

	public List<StageMasterVo> getStageDetails(String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStageDetails  Starting");
		List<StageMasterVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStageDetails(accyear);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStageDetails  Ending");
		// TODO Auto-generated method stub
		return list;

	}

	public List<StudentRegistrationVo> getStudentDetails1(String userType, String userCode, String academic_year, String schoolLocation) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			System.out.println("inside service impl");
			list = new StudentRegistrationDaoImpl().getStudentDetails1(userType,userCode,academic_year,schoolLocation);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetails  Ending");
	

		return list;
	}

	public boolean deactivateStudent(StudentRegistrationVo registrationVo) {
		// TODO Auto-generated method stub
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:deactivateStudent Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:deactivateStudent  Ending");
		return new StudentRegistrationDaoImpl()
				.deactivateStudent(registrationVo);
	}

	public ArrayList<ViewallSubjectsVo> getSubjectByClass(String classID) {

		// TODO Auto-generated method stub
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getSubjectByClass Starting");

		ArrayList<ViewallSubjectsVo> subjectList = new ArrayList<ViewallSubjectsVo>();

		try {

			subjectList = new StudentRegistrationDaoImpl()
					.getSubjectByClass(classID);
		} catch (Exception e) {

			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getSubjectByClass  Ending");
		return subjectList;

	}
	
	
	public StudentRegistrationVo editStudent(StudentRegistrationVo registrationVo){
		// TODO Auto-generated method stub
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editStudent  Starting");
		
		StudentRegistrationVo studentVo=new StudentRegistrationVo();
		
		try{
			
			studentVo=new StudentRegistrationDaoImpl().editStudent(registrationVo);
			
		}catch(Exception e){
			
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editStudent  Ending");
		return studentVo;
	}
	public List<registrationvo> StudentDetails(String academic_year) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetails  Starting");
		List<registrationvo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().StudentDetails(academic_year);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}


	public List<StudentRegistrationVo> getTranscationcategory(String transloc)
	{
		// TODO Auto-generated method stub
		return new StudentRegistrationDaoImpl().getTranscationcategory(transloc);
	}


	public List<StudentRegistrationVo> getStudentDetails(
			String searchName, String location, String academic_year, String schoolLocation) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : searchStreamDetailsService  Starting");
		
		List<StudentRegistrationVo> searchlist = null;
		
		try {
			
			searchlist = new StudentRegistrationDaoImpl().getStudentDetails(searchName,location);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : searchStreamDetailsService  Ending");
		
		return searchlist;
	}

	public List<StudentRegistrationVo> getClassDetailWithOutStream(String location) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getClassDetailWithOutStream(location);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> getClassDetailsByTemp() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getClassDetailsByTemp();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> getClassDetailSrSecondary() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getClassDetailSrSecondary();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> getTempRegistrationDetails(StudentRegistrationVo registrationVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getTempRegistrationDetails(registrationVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> getClassDetailMontessori() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getClassDetailMontessori();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> getTransportStudentDetails(
			String userType, String userCode, String academic_year) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getTransportStudentDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			System.out.println("inside service impl");
			list = new StudentRegistrationDaoImpl().getTransportStudentDetails(userType,userCode,academic_year);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getTransportStudentDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> getTransportStudentDetails(
			String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getTransportStudentDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			System.out.println("inside service impl");
			list = new StudentRegistrationDaoImpl().getTransportStudentDetails(searchTerm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getTransportStudentDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> TCGeneration1(String academic_year, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentList  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().TCGeneration1(academic_year,location);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentList  Ending");
	

		return list;
	}

	public List<StudentRegistrationVo> getClassByLocation(String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassByLocation  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getClassByLocation(locationid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassByLocation  Ending");
	

		return list;
	}

	public List<StudentRegistrationVo> getAllStudentDetails(String academic_year, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getAllStudentDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getAllStudentDetails(academic_year,location);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getAllStudentDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> studentFullList(String studentId,
			String accYear, String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : studentFullList  Starting");
		
		List<StudentRegistrationVo> student_Vo = null;
		
		try{
			
			student_Vo=new StudentRegistrationDaoImpl().studentFullList(studentId,accYear,locationId);
			
		}catch(Exception e){
			
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : studentFullList  Ending");
		return student_Vo;
	}

	public List<StudentRegistrationVo> getStudentDetailsList(String locationid, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetails  Starting");
		
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentDetailsList(locationid,accyear);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetails  Ending");
	

		return list;
}


	public List<StudentRegistrationVo> getStudentDetailsLByFilter(String locationid, String accyear, String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetailsLByFilter  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentDetailsLByFilter(locationid,accyear,classname);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetailsLByFilter  Ending");
	

		return list;
	}

	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname,
			String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentListBySection(locationid,accyear,classname,sectionid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentListBySection  Ending");
	

		return list;
	}
	
	
	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname,
			String sectionid,String sortingby,String orderby) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentListBySection  Ending");
	

		return list;
	}

	public List<StudentRegistrationVo> singleStudentDetailsList(String studentId, String accYear, String locationId, String flag) {
		
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentRegistrationServiceImpl : singleStudentDetailsList  Starting");
			List<StudentRegistrationVo> list = null;
			try {
				list = new StudentRegistrationDaoImpl().singleStudentDetailsList(studentId,accYear,locationId,flag);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentRegistrationServiceImpl : singleStudentDetailsList  Ending");
		

			return list;

		}

	public List<StudentRegistrationVo> getStudentLocationList(String academic_year, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentLocationList  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentLocationList(academic_year,location);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentLocationList  Ending");
	

		return list;
	}

	public List<StudentRegistrationVo> searchAllAcademicYearDetails(String locationId, String accYear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : searchAllAcademicYearDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().searchAllAcademicYearDetails(locationId,accYear);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : searchAllAcademicYearDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByList(String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByList  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentSearchByList(searchTerm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByList  Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchListByAccYear(String searchTerm, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchListByAccYear  Starting");
		
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentSearchListByAccYear(searchTerm,accyear);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchListByAccYear  Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchListByLocation(String searchTerm, String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchListByLocation  Starting");
		
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentSearchListByLocation(searchTerm,locationid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchListByLocation  Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByFilter(String searchTerm, String locationid, String accyear, String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByFilter  Starting");
		
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentSearchByFilter(searchTerm,locationid,accyear,classname);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByFilter  Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByClass(String searchTerm, String locationid, String accyear,String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByClass  Starting");
		
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentSearchByClass(searchTerm,locationid,accyear,classname);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByClass  Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByAllFilter  Starting");
		
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByAllFilter  Ending");
		
		return list;
	}

	public List<FeeReportDetailsVo> getfeedetails(String studentId,
			String accYear, String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentLocationList  Starting");
		List<FeeReportDetailsVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getfeedetails(studentId,accYear,locationId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentLocationList  Ending");
	

		return list;
	}

	public String AddConfidentialDetails(String entryDate,
			String comments, String studentId, String accyear, String locationid,String userName) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : AddConfidentialDetails  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : AddConfidentialDetails  Ending");
		
		return new StudentRegistrationDaoImpl().AddConfidentialDetails(entryDate,comments,studentId,accyear,locationid,userName);
	}

	public List<StudentRegistrationVo> getConfidentialReportStudents(String academic_year, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfidentialReportStudents  Starting");
		
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getConfidentialReportStudents(academic_year,location);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfidentialReportStudents  Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> searchAllAccYearDetailsConfReport(String locationId, String accYear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : searchAllAccYearDetailsConfReport  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().searchAllAccYearDetailsConfReport(locationId,accYear);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : searchAllAccYearDetailsConfReport  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getConfDetailsLByFilter(String locationid, String accyear, String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfDetailsLByFilter  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getConfDetailsLByFilter(locationid,accyear,classname);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfDetailsLByFilter  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentBySectionForConfReport(String locationid, String accyear, String classname,
			String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentBySectionForConfReport  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentBySectionForConfReport(locationid,accyear,classname,sectionid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentBySectionForConfReport  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByConfReport(
			String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByConfReport  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentSearchByConfReport(searchTerm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByConfReport  Ending");

		return list;
	}
	
	public List<StudentRegistrationVo> getIDCard(String studentId,
			String accYear, String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : studentFullList  Starting");
		
		List<StudentRegistrationVo> student_Vo = null;
		
		try{
			
			student_Vo=new StudentRegistrationDaoImpl().getIDCard(studentId,accYear,locationId);
			
		}catch(Exception e){
			
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : studentFullList  Ending");
		return student_Vo;
	}
	
	public String saveStudentPromotion(StudentRegistrationVo registrationVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeExpenseDetailsServiceImpl : saveEmployeeExpenseDetails Starting");

		String student_Details = null;
		StudentRegistrationDao daoObj = new StudentRegistrationDaoImpl();
		try {

			student_Details = daoObj.saveStudentPromotion(registrationVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeExpenseDetailsServiceImpl : saveEmployeeExpenseDetails Ending");

		return student_Details;
	}

	public String getNextAcademicYearId(String academicyearid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetails  Starting");
		String academicyear = "";
		try {
			academicyear = new StudentRegistrationDaoImpl().getNextAcademicYearId(academicyearid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetails  Ending");
		// TODO Auto-generated method stub

		return academicyear;
	}

	public List<StudentRegistrationVo> getPromotedListDetails(StudentRegistrationVo regVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getPromotedListDetails(regVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Ending");

		return list;

	}

	public List<StudentRegistrationVo> singleStudentConfDetails(String studentId, String accYear, String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : singleStudentConfDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().singleStudentConfDetails(studentId,accYear,locationId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : singleStudentConfDetails  Ending");

		return list;

	}

	public List<StudentRegistrationVo> getConfSearchListByAccYear(String searchTerm, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : singleStudentConfDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getConfSearchListByAccYear(searchTerm,accyear);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : singleStudentConfDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getConfSearchListByLocation(String searchTerm, String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchListByLocation  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getConfSearchListByLocation(searchTerm,locationid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchListByLocation  Ending");

		return list;
	
	}

	public List<ExaminationDetailsVo> getExaminationDetails(
			StudentRegistrationVo svo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Starting");
		List<ExaminationDetailsVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getExaminationDetails(svo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Ending");

		return list;

	}

	public List<ExaminationDetailsVo> getExaminationCodes(String loc_id,
			String acyear_id) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Starting");
		List<ExaminationDetailsVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getExaminationCodes(loc_id,acyear_id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Ending");

		return list;

	}

	public String getExamName(String examcode) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Starting");
		String exmname = null;
		try {
			exmname = new StudentRegistrationDaoImpl().getExamName(examcode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Ending");

		return exmname;

	}

	public List<StudentAttendanceVo> getStudentAttendance(String stud_id) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Starting");
		List<StudentAttendanceVo> stuatten = null;
		try {
			stuatten = new StudentRegistrationDaoImpl().getStudentAttendance(stud_id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Ending");

		return stuatten;

	}

	public List<StudentAttendanceVo> getStudentAppraisal(
			StudentRegistrationVo spvo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Starting");
		List<StudentAttendanceVo> stuaprsal = null;
		try {
			stuaprsal = new StudentRegistrationDaoImpl().getStudentAppraisal(spvo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Ending");

		return stuaprsal;

	}

	public List<ExaminationDetailsVo> getExaminationDetailsBasedonExams(
			StudentRegistrationVo svo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Starting");
		List<ExaminationDetailsVo> stuaprsal = null;
		try {
			stuaprsal = new StudentRegistrationDaoImpl().getExaminationDetailsBasedonExams(svo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Ending");

		return stuaprsal;

	}
	public List<PageFilterVo> getIDCardStaff(PageFilterpojo filterpojo) {
		 return new StudentRegistrationDaoImpl().getIDCardStaff(filterpojo);
	}

	public List<StudentRegistrationVo> getConfSearchByFilter(String searchTerm,String locationid, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchByFilter  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getConfSearchByFilter(searchTerm,locationid,accyear);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchByFilter  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getConfSearchByClass(String searchTerm,String locationid, String accyear, String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchByClass  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getConfSearchByClass(searchTerm,locationid,accyear,classname);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchByClass  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getConfSearchByAllFilter(String searchTerm, String locationid, String accyear,
			String classname, String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchByAllFilter  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getConfSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchByAllFilter  Ending");

		return list;
	}

	public String deleteConfidentialDetails(String deleteId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : deleteConfidentialDetails  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : deleteConfidentialDetails  Ending");
		
		return new StudentRegistrationDaoImpl().deleteConfidentialDetails(deleteId);
	}

	public String editConfidentialDetails(String entryDate,String comments, String editId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Ending");
		
		return new StudentRegistrationDaoImpl().editConfidentialDetails(entryDate,comments,editId);
	}

	public List<StudentRegistrationVo> getStudentDetails(String userType, String userCode, String academic_year) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			System.out.println("inside service impl");
			list = new StudentRegistrationDaoImpl().getStudentDetails(userType,userCode,academic_year);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetails  Ending");


		return list;
	}

	public List<StudentRegistrationVo> getStudentFeeSearchByList(
			String searchTerm,String accyear ) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Ending");
		
		return new StudentRegistrationDaoImpl().getStudentFeeSearchByList(searchTerm,accyear);
	}

	public List<StudentRegistrationVo> getStudentFeeSearchListByAccYear(
			String searchTerm, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Ending");
		
		return new StudentRegistrationDaoImpl().getStudentFeeSearchListByAccYear(searchTerm,accyear);
	}

	public List<StudentRegistrationVo> getStudentFeeSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid)  {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Ending");
		
		return new StudentRegistrationDaoImpl().getStudentFeeSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
	}

	public List<StudentRegistrationVo> getStudentSearchByFeeClass(
			String searchTerm, String locationid, String accyear,
			String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Ending");
		
		return new StudentRegistrationDaoImpl().getStudentSearchByFeeClass(searchTerm,locationid,accyear,classname);
	}

	public List<StudentRegistrationVo> getStudentSearchByFeeFilter(
			String searchTerm, String locationid, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Ending");
		
		return new StudentRegistrationDaoImpl().getStudentSearchByFeeFilter(searchTerm,locationid,accyear);
	}

	public List<StudentRegistrationVo> getStudentDetailsLByFeeFilter(
			String locationid, String accyear, String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Ending");
		
		return new StudentRegistrationDaoImpl().getStudentDetailsLByFeeFilter(locationid,accyear,classname);
	}

	public List<StudentRegistrationVo> getStudentListByFeeSection(
			String locationid, String accyear, String classname,
			String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Ending");
		
		return new StudentRegistrationDaoImpl().getStudentListByFeeSection(locationid,accyear,classname,sectionid);
	}

	
	
	public List<StudentRegistrationVo> getAllAcademicYearDemotedDetails(String locationId, String accYear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getAllAcademicYearDemotedDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getAllAcademicYearDemotedDetails(locationId,accYear);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getAllAcademicYearDemotedDetails  Ending");

		return list;
	}
	
	public List<StudentRegistrationVo> getStudentDemotedSearchByList(String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByList  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentDemotedSearchByList(searchTerm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByList  Ending");

		return list;
	}

	
	public List<StudentRegistrationVo> getAllAcademicYearPromotedDetails(String locationId, String accYear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getAllAcademicYearPromotedDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getAllAcademicYearPromotedDetails(locationId,accYear);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getAllAcademicYearPromotedDetails  Ending");

		return list;
	}




	public String toCheckNextYearAvailable(String accyear) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Starting");
		String accyearid = null;
		try {
			accyearid = new StudentRegistrationDaoImpl().toCheckNextYearAvailable(accyear);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Ending");

		return accyearid;

	}
	
public List<StudentRegistrationVo> getPromotedClassSectionList(StudentRegistrationVo regVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedClassSectionList  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getPromotedClassSectionList(regVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedClassSectionList  Ending");

		return list;

	}
	public String toCheckNextClassAvailable( String toclass,String locationId) {
		return new StudentRegistrationDaoImpl().toCheckNextClassAvailable(toclass,locationId);
	}
	
	public List<StudentRegistrationVo> getAllStudentPromotionClassSectionDetails(String academic_year, String location,String classId, String sectionid) {
		return new StudentRegistrationDaoImpl().getAllStudentPromotionClassSectionDetails(academic_year,location,classId,sectionid);
	}


public List<StudentRegistrationVo> getPromotedClassList(StudentRegistrationVo regVo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getPromotedClassList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getPromotedClassList(regVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getPromotedClassList  Ending");

	return list;

}

public List<StudentRegistrationVo> getDemotedListDetails(StudentRegistrationVo regVo) {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getDemotedListDetails  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getDemotedListDetails(regVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getDemotedListDetails  Ending");

	return list;

}

public List<StudentRegistrationVo> getDemotedClassSectionList(StudentRegistrationVo regVo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getDemotedClassSectionList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getDemotedClassSectionList(regVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getDemotedClassSectionList  Ending");

	return list;

}

public List<StudentRegistrationVo> getStudentPromotedChange(String studentId, String accYear, String locationId, String promotedId) {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedChange  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentPromotedChange(studentId,accYear,locationId,promotedId);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedChange  Ending");

	return list;

}

public List<StudentRegistrationVo> getDemotedClassList(StudentRegistrationVo regVo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getDemotedClassList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getDemotedClassList(regVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getDemotedClassList  Ending");

	return list;

}
public List<StudentRegistrationVo> getStudentWisePromotion(String studentId, String accYear, String locationId) {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentWisePromotion(studentId,accYear,locationId);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Ending");

	return list;

}

public List<StudentRegistrationVo> getStudentPromotedSearchByList(String searchTerm) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchByList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentPromotedSearchByList(searchTerm);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchByList  Ending");

	return list;
}

public List<StudentRegistrationVo> getStudentPromotedSearchListByAccYear(String searchTerm, String accyear) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentSearchListByAccYear  Starting");

	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentPromotedSearchListByAccYear(searchTerm,accyear);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentSearchListByAccYear  Ending");

	return list;
}

public List<StudentRegistrationVo> getStudentPromotedSearchListByLocation(String searchTerm, String locationid) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchListByLocation  Starting");

	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentPromotedSearchListByLocation(searchTerm,locationid);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchListByLocation  Ending");

	return list;
}

public List<StudentRegistrationVo> getStudentPromotedSearchByFilter(String searchTerm, String locationid, String accyear) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchByFilter  Starting");

	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentPromotedSearchByFilter(searchTerm,locationid,accyear);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchByFilter  Ending");

	return list;
}

public List<StudentRegistrationVo> getStudentPromotedSearchByClass(String searchTerm, String locationid, String accyear,String classname) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchByClass  Starting");

	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentPromotedSearchByClass(searchTerm,locationid,accyear,classname);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchByClass  Ending");

	return list;
}

public List<StudentRegistrationVo> getStudentPromotedSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchByAllFilter  Starting");

	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentPromotedSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchByAllFilter  Ending");

	return list;
}


public List<StudentRegistrationVo> getStudentDetails(String searchName, String location) {
	return new StudentRegistrationDaoImpl().getStudentDetails(searchName,location);
}

public List<StudentRegistrationVo> getStudentDetailsexcel(String searchTerm, String location, String currentaccyear) {
	return new StudentRegistrationDaoImpl().getStudentDetailsexcel(searchTerm,location,currentaccyear);
}



public List<StudentRegistrationVo> getStudentDetailsByJs(
		StudentRegistrationVo data) {
	return new StudentRegistrationDaoImpl().getStudentDetailsByJs(data);
}

public String toGetStreamName(String toClass, String locationId) {
	return new StudentRegistrationDaoImpl().toGetStreamName(toClass,locationId);
}
public String getlocationteacher(String userCode) {
	return new StudentRegistrationDaoImpl().getlocationteacher(userCode);
}

public List<StudentRegistrationVo> individualStudentSearch(String studentId) {
	return new StudentRegistrationDaoImpl().individualStudentSearch(studentId);
}

public List<StudentRegistrationVo> getLanguageList(String classId, String schoolLocation) {
	return new StudentRegistrationDaoImpl().getLanguageList(classId,schoolLocation);
}

public String AddApparisalDetails(StudentRegistrationVo vo) {
	
	return new StudentRegistrationDaoImpl(). AddApparisalDetails(vo);
}

public List<StudentRegistrationVo> singleStudentDetailReport(String studentId,String accyear, String locationid) {
	return new StudentRegistrationDaoImpl().singleStudentDetailReport(studentId,accyear,locationid);
}

public String deleteApparaisalDetails(String deleteId) {
	return new StudentRegistrationDaoImpl(). deleteApparaisalDetails(deleteId);
}


public List<StudentRegistrationVo> getStudentWithheldList(String academic_year, String location) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentWithheldList  Starting");
	
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getConfidentialReportStudents(academic_year,location);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentWithheldList  Ending");
	
	return list;
}

public List<StudentRegistrationVo> singleStudentWithHeld(String studentId, String accYear, String locationId) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : singleStudentWithHeld  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().singleStudentWithHeld(studentId,accYear,locationId);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : singleStudentWithHeld  Ending");

	return list;
}

public String AddWithHeldDetails(StudentRegistrationVo studentvo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : AddWithHeldDetails  Starting");
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : AddWithHeldDetails  Ending");
	
	return new StudentRegistrationDaoImpl().AddWithHeldDetails(studentvo);
}

public String EditWithHeldDetails(StudentRegistrationVo studentvo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : EditWithHeldDetails  Starting");
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : EditWithHeldDetails  Ending");
	
	return new StudentRegistrationDaoImpl().EditWithHeldDetails(studentvo);
}

public List<StudentRegistrationVo> singleStudentWithHeldDetailsList(String studentId, String accyear,
		String locationid, String flag, String status) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : singleStudentWithHeldDetailsList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().singleStudentWithHeldDetailsList(studentId,accyear,locationid,flag,status);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : singleStudentWithHeldDetailsList  Ending");

	return list;
}

public String deleteWithHeldDetails(String deleteId) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : deleteWithHeldDetails  Starting");
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : deleteWithHeldDetails  Ending");
	
	return new StudentRegistrationDaoImpl().deleteWithHeldDetails(deleteId);
}

public List<StudentRegistrationVo> getStudentListBySpecialization(ExaminationDetailsVo details) 
{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentListBySpecialization  Starting");
	
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentListBySpecialization(details);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentListBySpecialization  Ending");
	
	return list;
}

public String getAdmissionNo(String locationId) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentListBySpecialization  Starting");
	
	String admissionno = null;
	try {
		admissionno = new StudentRegistrationDaoImpl().getAdmissionNo(locationId);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentListBySpecialization  Ending");
	
	return admissionno;
}


public List<StudentRegistrationVo> getIDCardPhotoSheet(String sectionId,String classId,
		String accYear, String locationId) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : studentFullList  Starting");
	
	List<StudentRegistrationVo> student_Vo = null;
	
	try{
		
		student_Vo=new StudentRegistrationDaoImpl().getIDCardPhotoSheet(sectionId,classId,accYear,locationId);
		
	}catch(Exception e){
		
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : studentFullList  Ending");
	return student_Vo;
}

public List<StudentRegistrationVo> ShowStudentAddress(String studentId,
		String accYear, String locationId) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : studentFullList  Starting");
	
	List<StudentRegistrationVo> student_Vo = null;
	
	try{
		
		student_Vo=new StudentRegistrationDaoImpl().ShowStudentAddress(studentId,accYear,locationId);
		
	}catch(Exception e){
		
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : studentFullList  Ending");
	return student_Vo;
}


public String generateStudentTC(String[] splitlocation, String[] splitaccyear, String[] splitstudentid,String[] splitadmid,String[] splitclassid,String examdetails,String reason,String remarks,String result) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentLocationList  Starting");
	String list = null;
	try {
		list = new StudentRegistrationDaoImpl().generateStudentTC(splitlocation,splitaccyear,splitstudentid,splitadmid,splitclassid,examdetails,reason,remarks,result);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentLocationList  Ending");


	return list;
}

public List<StudentRegistrationVo> TCGeneration(String academic_year, String location) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentLocationList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().TCGeneration(academic_year,location);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentLocationList  Ending");


	return list;
}

public List<StudentRegistrationVo> getStudentList(String academic_year, String location) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentList(academic_year,location);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentList  Ending");


	return list;
}


public List<StudentRegistrationVo> getNotGenTC(StudentRegistrationVo vo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentLocationList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getNotGenTC(vo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentLocationList  Ending");


	return list;
}

public List<StudentRegistrationVo> getStudentListByTC(String locationid, String accyear, String classname,
		String sectionid,String sortingby,String orderby) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentListBySection  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentListByTC(locationid,accyear,classname,sectionid,sortingby,orderby);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentListBySection  Ending");


	return list;
}


public List<StudentRegistrationVo> TransferCertificateDownload(String locationId, String accyear, String studentid, String admid, String classid){
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentListBySection  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().TransferCertificateDownload(locationId,accyear,studentid,admid,classid);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentListBySection  Ending");


	return list;
}

public List<StudentRegistrationVo> GenTCListFilter(StudentRegistrationVo vo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentLocationList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().GenTCListFilter(vo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentLocationList  Ending");


	return list;
}

public List<StudentRegistrationVo> GenTCListSearch(StudentRegistrationVo vo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentLocationList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().GenTCListSearch(vo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentLocationList  Ending");


	return list;
}







}
