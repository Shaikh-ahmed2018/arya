package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.daoImpl.CreateExaminationDaoImpl;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.forms.CreateExaminationForm;
import com.centris.campus.pojo.LstmsAccadamicYear;
import com.centris.campus.pojo.LstmsExaminationPOJO;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;

public class CreateExaminationServiceIMPL {
	private static final Logger logger = Logger
			.getLogger(CreateExaminationServiceIMPL.class);
	
	public Map<String, String> getAccadamicYearsServiceImpl() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreateExaminationServiceIMPL:getAccadamicYearsServiceImpl Starting");
		
		ArrayList<LstmsAccadamicYear> al = new CreateExaminationDaoImpl().getAccadamicYearsDaoImpl();
		

		Map<String, String> hs = new HashMap<String, String>();

		Iterator<LstmsAccadamicYear> e = al.iterator();
		try {
			while (e.hasNext()) {
				
				LstmsAccadamicYear type = (LstmsAccadamicYear) e.next();

				hs.put(type.getAccyearid(), type.getAccyear());
			}
			
			
			
		} catch (Exception exception) {
			logger.error(e);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreateExaminationServiceIMPL:getAccadamicYearsServiceImpl Ending");
		return hs;
	}

	public List getAllExamNames(CreateExaminationForm examform) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreateExaminationServiceIMPL:getAllExamNames Starting");
		List examnamelist = null;
		try {
			 examnamelist = new CreateExaminationDaoImpl()
					.getAllExamNames(examform);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreateExaminationServiceIMPL:getAllExamNames Ending");
		return examnamelist;
		
	}

	public String createExamination(
			CreateExaminationForm examform) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreateExaminationServiceIMPL:createExaminationServiceImpl Starting");
		
		LstmsExaminationPOJO exam = new LstmsExaminationPOJO();
		String result = null;
		
		if(examform.getExamId().equalsIgnoreCase("")||examform.getExamId()==null)
		
		{
			
			
		try {
			System.out.println("create exam service"+examform.getCreateUser());
		    exam.setExamdate(HelperClass.convertUIToDatabase(examform.getExamdate()));
			exam.setAccadamicyear(examform.getAccyear());
			exam.setDescription(examform.getDescription());
			exam.setExamName(examform.getExamname());
			exam.setEnddate(HelperClass.convertUIToDatabase(examform.getEnddate()));
			exam.setCreateuser(examform.getCreateUser());
			
			int res = new CreateExaminationDaoImpl().createExamination(exam);
			if(res==1){
				
				result = "Exam Creation Failed";
			}
			else {
				result = "Exam Created Successfully";
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		}
		
		else if (!examform.getExamId().equalsIgnoreCase(""))
		
		{
			
			try {
				System.out.println("create exam service"+examform.getCreateUser());
			    exam.setExamdate(HelperClass.convertUIToDatabase(examform.getExamdate()));
				exam.setAccadamicyear(examform.getAccyear());
				exam.setDescription(examform.getDescription());
				exam.setExamId(examform.getExamId());
				exam.setExamName(examform.getExamname());
				exam.setEnddate(HelperClass.convertUIToDatabase(examform.getEnddate()));
				
			
				exam.setCreateuser(examform.getCreateUser());
				
				int res = new CreateExaminationDaoImpl().UpdateExamination(exam);
				if(res==1){
					
					result = "Exam Updated Failed";
				}
				else {
					result = "Exam Updated Successfully";
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreateExaminationServiceIMPL:createExaminationServiceImpl Ending");
		
		
		return result;
	}

	public ExaminationDetailsVo editExamination(ExaminationDetailsVo examvo) {
	
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl :editExamDetailsService Starting");
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : editExamDetailsService  Ending");
		
		return  new CreateExaminationDaoImpl().editExamination(examvo);
	}

	public String deleteExamination(ExaminationDetailsVo examvo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : deleteExamDetailsService Starting");
		
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : deleteExamDetailsService  Ending");
		
		return  new CreateExaminationDaoImpl().deleteExamination(examvo);
		
		
	}

	public ArrayList<ExaminationDetailsVo> searchExamination(String searchName) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : searchExamDetailsService Starting");
		
		ArrayList<ExaminationDetailsVo> examlist = null;
		
		examlist= new CreateExaminationDaoImpl().searchExamination(searchName);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : searchExamDetailsService  Ending");
		return examlist;
	}

	public boolean validateExamination(ExaminationDetailsVo examvo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : validateExamDetailsService Starting");
		
		boolean examname_validate =false;
		examname_validate =  new CreateExaminationDaoImpl().validExamination(examvo);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : searchExamDetailsService  Ending");
		
		return examname_validate;
	}

}
