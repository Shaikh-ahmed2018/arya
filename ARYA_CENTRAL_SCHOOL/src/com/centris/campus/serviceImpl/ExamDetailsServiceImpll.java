package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.ExamDetailsDao;
import com.centris.campus.daoImpl.ExamDetailsDaoImpl;
import com.centris.campus.pojo.ExamDetailsPojo;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.service.ExamDetailsService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.StreamDetailsVO;

public class ExamDetailsServiceImpll implements ExamDetailsService {
	private static final Logger logger = Logger
			.getLogger(StreamDetailsServiceImpl.class);
	
	static ExamDetailsDao daoimpl;
	
	static{
		 daoimpl= new ExamDetailsDaoImpl();
	}
	
	
	@Override
	public List<ExaminationDetailsVo> getExamDetailsService() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsServiceImpl : getExamDetailsService Starting");
		
		List<ExaminationDetailsVo> examvolist = new ArrayList<ExaminationDetailsVo>();
		List<ExamDetailsPojo> examPojoList = new ArrayList<ExamDetailsPojo>();
		
		try {
			
			daoimpl = new ExamDetailsDaoImpl();
			examPojoList=daoimpl.getExamDetailsDao();
			
			for (ExamDetailsPojo examPojo : examPojoList) {
				ExaminationDetailsVo examVo = new ExaminationDetailsVo();
				
				examVo.setExamid(examPojo.getExamid());
				examVo.setExamName(examPojo.getExamName());
				examVo.setStartDate(examPojo.getStartDate());
				examVo.setEndDate(examPojo.getEndDate());
				examVo.setAccyear(examPojo.getAccyear());
				examVo.setDescription(examPojo.getDescription());
				examvolist.add(examVo);
				
			}	
		} catch (Exception e) {
			
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		

		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsServiceImpl : getExamDetailsService Ending");
		
		
		
		return examvolist;
	}


	@Override
	public String getaccyName(String accyear) {
		
		return daoimpl.getaccyName(accyear);
	}

	@Override
	public String insertGradeSettings(ExamDetailsPojo obj) {
		return daoimpl.insertGradeSettings(obj);
	}

	@Override
	public ArrayList<ExamDetailsPojo> displayGradeSettings(String accyear,String location) {
		return daoimpl.displayGradeSettings(accyear,location);
	}

	@Override
	public String deleteGradeSettings(String gradeid,String locname,String accyear) {
		return daoimpl.deleteGradeSettings(gradeid,locname,accyear);
	}

	@Override
	public String editGradeSettings(ExaminationDetailsVo gradeid) {
		return daoimpl.editGradeSettings(gradeid);
	}

	@Override
	public String checkduplicateGrade(String accyear, String gradename,String loc) {
		return daoimpl.checkduplicateGrade(accyear,gradename,loc);
	}


	@Override
	public ArrayList<ExaminationDetailsVo> getSubjectClass(String accyear,
			String examid,String locid,String classid) {
		return daoimpl.getSubjectClass(accyear,examid,locid,classid);
		
	}


	@Override
	public ArrayList<ExaminationDetailsVo> getexamclassDetails(
			ExaminationDetailsVo obj) {
		return daoimpl.getexamclassDetails(obj);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getSubjectmarksStatus(String acyear) {
		return daoimpl.getSubjectmarksStatus(acyear);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getSubjectmarksList(String accyear,String schoolLocation) {
		return daoimpl.getSubjectmarksList(accyear,schoolLocation);
	}
	
	@Override
	public String getexamName(String examid, String accyear,String locid) {
		return daoimpl.getexamName(examid,accyear,locid);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> classWiseSubject(ExaminationDetailsVo obj) {
		return daoimpl.classWiseSubject(obj);
	}

	@Override
	public String getsubDetails(ExaminationDetailsVo obj) {
		return daoimpl.getsubDetails(obj);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getstudentsList(ExaminationDetailsVo obj,String schoolLocation) {
		return daoimpl.getstudentsList(obj,schoolLocation);
	}


	@Override
	public ArrayList<ExaminationDetailsVo> classWiseStudent(
			ExaminationDetailsVo obj) {
		return daoimpl.classWiseStudent(obj);
	}

  @Override
	public ArrayList<ExaminationDetailsVo> getStudentDetails(ExaminationDetailsVo obj1) {
		return daoimpl.getStudentDetails(obj1);
	}


	@Override
	public String insertmarkentrydetails(
			ExaminationDetailsVo obj) {
		// TODO Auto-generated method stub
		return daoimpl.insertmarkentrydetails(obj);
	}
	
	
	@Override
	public String getlocationname(String schoolLocation) {
		// TODO Auto-generated method stub
		return daoimpl.getlocationname(schoolLocation);
	}
	
	@Override
	public String insertmarkentrysubjectwise(ExaminationDetailsVo obj,String schoolLocation) {
		return daoimpl.insertmarkentrysubjectwise(obj,schoolLocation);
	
	}
	
	@Override
	public String getclassname(String classid) {
		return daoimpl.getclassname(classid);
	}
	
	@Override
	public ArrayList<ExaminationDetailsVo> getsubjectstudent(String accyear,
			String examid, String locid) {
		return daoimpl.getsubjectstudent(accyear,examid,locid);
	}
	
	@Override
	public ArrayList<ExaminationDetailsVo> getlistofExamCodes(String schoolLocation) {
		// TODO Auto-generated method stub
		return daoimpl.getlistofExamCodes(schoolLocation);
	}


	@Override
	public String updatemarkentrysubjectwise(ExaminationDetailsVo obj,
			String schoolLocation) {
		return daoimpl.updatemarkentrysubjectwise(obj,schoolLocation);
	}


	@Override
	public ArrayList<ExaminationDetailsVo> examTimeTableListYear(String accyear,String loc) {
		return daoimpl.examTimeTableListYear(accyear,loc);
	}


	@Override
	public List<ExaminationDetailsVo> getExamClassByLocation(String loc,String accyear) {
		return daoimpl.getExamClassByLocation(loc,accyear);
	}


	@Override
	public List<ExaminationDetailsVo> getexamlistbyclass(ExamTimetablePojo pojo) {
		return daoimpl.getexamlistbyclass(pojo);
	}


	@Override
	public ExaminationDetailsVo getexamdetails(ExamTimetablePojo pojo) {
		return daoimpl.getexamdetails(pojo);
	}


	@Override
	public ArrayList<ExaminationDetailsVo> getsubdetails(ExamTimetablePojo pojo) {
		return daoimpl.getsubdetails(pojo);
	}


	@Override
	public String savetimetabledetails(ExamTimetablePojo pojo, String[] subid1, String[] starttime1, String[] endtime1,
			String[] startdate) {
		return daoimpl.savetimetabledetails(pojo,subid1,starttime1,endtime1,startdate);
	}


	@Override
	public ArrayList<ExaminationDetailsVo> getexamsbtselection(String accyear, String locid) {
		return daoimpl.getexamsbtselection(accyear,locid);
	}


	@Override
	public List<ExaminationDetailsVo> getExamClassByLocation(String locid, String accyear, String examid) {
		return daoimpl.getExamClassByLocation(accyear,locid,examid);
	}


	@Override
	public ArrayList<ExaminationDetailsVo> getexamsettingslist(String accyear, String locid) {
		return daoimpl.getexamsettingslist(accyear,locid);
	}


	@Override
	public String checkduplicatedate(ExamTimetablePojo pojo) {
		return daoimpl.checkduplicatedate(pojo);
	}


	@Override
	public ExaminationDetailsVo getexamdetailsbyset(ExamTimetablePojo pojo) {
		return daoimpl.getexamdetailsbyset(pojo);
	}


	@Override
	public ArrayList<ExaminationDetailsVo> getsubdetailsset(ExamTimetablePojo pojo) {
		return daoimpl.getsubdetailsset(pojo);
	}


	@Override
	public String savetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1,
			String[] endtime1, String[] startdate) {
		return daoimpl.savetimetabledetailsset(pojo,subid1,starttime1,endtime1,startdate);
	}


	@Override
	public String updatetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1,
			String[] endtime1, String[] startdate) {
		return daoimpl.updatetimetabledetailsset(pojo,subid1,starttime1,endtime1,startdate);
	}
	@Override
	public ArrayList<ExaminationDetailsVo> getexamsettingslistfordep(String accyear, String locid) {
		return daoimpl.getexamsettingslistfordep(accyear,locid);
	}


	@Override
	public ArrayList<ExaminationDetailsVo> getSubjectClassBySpec(String accyear, String examid, String locid,String classid) {
		return daoimpl.getSubjectClassBySpec(accyear,examid,locid,classid);
	}
}
