package com.centris.campus.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StudentTransferCertifivateReportDao;
import com.centris.campus.daoImpl.StudentTransferCertifivateReportDaoImpl;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.service.StudentTransferCertifivateReportService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ParentVO;

public class StudentTransferCertifivateReportServiceImpl implements StudentTransferCertifivateReportService{

	private static final Logger logger = Logger.getLogger(StudentTransferCertifivateReportServiceImpl.class);
	
	StudentTransferCertifivateReportDao dao = new StudentTransferCertifivateReportDaoImpl();
	
	
	public List<ClassPojo> getClassDetailsService() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertifivateReportServiceImpl : getClassDetailsService Starting");
		
		List<ClassPojo> pojo = null;
		
		try {
			
			
			pojo = dao.getClassDetailsDao();
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertifivateReportServiceImpl : getClassDetailsService Ending");
	
		return pojo;
	}


	
	public List<SectionPojo> getSectionListService(String classname,String location) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertifivateReportServiceImpl : getSectionListDetailsService Starting");
		
		List<SectionPojo> seclist = null;
		
		try {
			
			seclist = dao.getSectionListDao(classname,location);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertifivateReportServiceImpl : getSectionListDetailsService Ending");
		
		return seclist;
	}



	
	public List<ParentVO> getAllStudentNamesReportService(String sectionid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertifivateReportServiceImpl : getAllStudentNamesReportService Starting");
		
       List<ParentVO> stulist = null;
		
		try {
			
			stulist = dao.getAllStudentNamesReportDao(sectionid);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertifivateReportServiceImpl : getAllStudentNamesReportService Ending");
		
		
		return stulist;
	}

}
