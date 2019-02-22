package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.ClassDao;
import com.centris.campus.dao.SectionDao;
import com.centris.campus.daoImpl.ClassDaoImpl;
import com.centris.campus.daoImpl.SectionDaoImpl;
import com.centris.campus.forms.SectionForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.service.SectionService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.SectionVO;

public class SectionServiceImpl implements SectionService{
	
	private static final Logger logger = Logger
			.getLogger(SectionServiceImpl.class);
	SectionDao campusClassSectionDao = new SectionDaoImpl();

	@Override
	public synchronized String insertCampusClassSectionService(
			SectionForm campusClassSectionForm) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionServiceImpl:insertCampusClassSectionService  Starting");
		// TODO Auto-generated method stub
		String check = "";
		try {
			SectionPojo sectionPojo = new SectionPojo();
			sectionPojo.setLocationId(campusClassSectionForm.getLocationId());
			sectionPojo.setSecDetailsId(campusClassSectionForm.getSecDetailsId());
			sectionPojo.setSecDetailsName(campusClassSectionForm.getSecDetailsName());
			sectionPojo.setSectionId(campusClassSectionForm.getSectionId());
			sectionPojo.setSectionName(campusClassSectionForm.getSectionName());
			sectionPojo.setSectionStrength(campusClassSectionForm
					.getSectionStrength());
			sectionPojo.setCreateUser(campusClassSectionForm.getCreateUser());
			sectionPojo.setStatus(campusClassSectionForm.getStatus());
			sectionPojo.setSecId(campusClassSectionForm.getSecId());
			if(sectionPojo.getStatus().equalsIgnoreCase("update")){
				check = campusClassSectionDao.updateCampusClassSectionDao(sectionPojo);
			}else{
			check = campusClassSectionDao
					.insertCampusClassSectionDao(sectionPojo);
			}
		} catch (Exception exception) {
			logger.error(exception);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionServiceImpl: Ending");
		return check;
	}

	@Override
	public synchronized String updateCampusClassSectionService(
			SectionForm campusClassSectionForm) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionServiceImpl:updateCampusClassSectionService Starting");
		SectionPojo sectionPojo = new SectionPojo();
		try {

			sectionPojo.setStreamName(campusClassSectionForm.getStreamName());
			sectionPojo.setSectionId(campusClassSectionForm.getSectionId());
			sectionPojo.setSecDetailsId(campusClassSectionForm
					.getSecDetailsId());
			sectionPojo.setSectionName(campusClassSectionForm.getSectionName());
			sectionPojo.setSectionStrength(campusClassSectionForm
					.getSectionStrength());
			sectionPojo.setModifyUser(campusClassSectionForm.getModifyUser());
		} catch (Exception exception) {
			logger.error(exception);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionServiceImpl:updateCampusClassSectionService  Ending");

		return campusClassSectionDao.updateCampusClassSectionDao(sectionPojo);

	}

	@Override
	public synchronized boolean deleteCampusClassSectionService(String id[],String[] locationList) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionServiceImpl:deleteCampusClassSectionService Starting");
		// TODO Auto-generated method stub
		SectionPojo sectionPojo=null;
		try {
			 /*sectionPojo = new SectionPojo();
			sectionPojo.setSectionId(campusClassSectionForm.getSectionId());*/
			
		} catch (Exception exception) {
			logger.error(exception);

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionServiceImpl:deleteCampusClassSectionService  Ending");
     return campusClassSectionDao.deleteCampusClassSectionDao(id,locationList);
	}

	@Override
	public synchronized List<SectionForm> getCampusClassSectionAndClassDetailsService(String schoolLocation)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionServiceImpl:getCampusClassSectionAndClassDetailsService Starting");

		// TODO Auto-generated method stub
		List<SectionForm> classSectionFormList = new ArrayList<SectionForm>();
		List<SectionPojo> classSectionPojoList = new ArrayList<SectionPojo>();
		classSectionPojoList = campusClassSectionDao.getCampusClassSectionAndClassDetailsDao(schoolLocation);
		try {
			for (SectionPojo pojoList : classSectionPojoList) {
				SectionForm sectionForm = new SectionForm();
				sectionForm.setSecDetailsId(pojoList.getSecDetailsId());
				sectionForm.setSecDetailsName(pojoList.getSecDetailsName());
				sectionForm.setStreamName(pojoList.getStreamName());
				sectionForm.setSectionId(pojoList.getSectionId());
				sectionForm.setSectionName(pojoList.getSectionName());
				sectionForm.setSectionStrength(pojoList.getSectionStrength());
				sectionForm.setLocationName(pojoList.getLocationName());
				sectionForm.setLocationId(pojoList.getLocationId());
				classSectionFormList.add(sectionForm);
			}
		} catch (Exception exception) {
			logger.error(exception);
		}
		//
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionServiceImpl:getCampusClassSectionAndClassDetailsService Ending");

		return classSectionFormList;
	}

	@Override
	public synchronized List<SectionForm> getCampusClassDetailsIDandClassDetailsNameService(String locationId)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionServiceImpl:getCampusClassDetailsIDandClassDetailsNameService Starting");
		// TODO Auto-generated method stub

		List<SectionForm> classSectionFormList = new ArrayList<SectionForm>();
		List<SectionPojo> classSectionPojoList = new ArrayList<SectionPojo>();
		classSectionPojoList = campusClassSectionDao
				.getCampusClassDetailsIDandClassDetailsNameDao(locationId);
		try {
			for (SectionPojo pojoList : classSectionPojoList) {
				SectionForm sectionForm = new SectionForm();
				sectionForm.setSecDetailsId(pojoList.getSecDetailsId());
				sectionForm.setSecDetailsName(pojoList.getSecDetailsName());

				classSectionFormList.add(sectionForm);
			}
		} catch (Exception exception) {
			logger.error(exception);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionServiceImpl:getCampusClassDetailsIDandClassDetailsNameService Ending");

		return classSectionFormList;
	}

	
	public synchronized boolean checkSectionNameForUpdate(String sectionId,
			String sectionName, String className) {
		
		return new  SectionDaoImpl().checkSectionNameForUpdate(sectionId,sectionName,className);
	}
	
	@Override
	public List<SectionForm> searchSection(SectionForm searchText) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserServiceImpl :searchUserDetails Starting");
		List<SectionForm> getCourseDetailsBySearchText=new ArrayList<SectionForm>();
		SectionDao secDao=new SectionDaoImpl();
		try{
			getCourseDetailsBySearchText=secDao.searchSection(searchText);
			
		}
		catch(Exception exception){
			exception.printStackTrace();
			
			logger.error(exception.getMessage(),exception);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserServiceImpl :searchUserDetails  Ending");
		return getCourseDetailsBySearchText;
	}
	
	public synchronized SectionForm editSection(SectionForm classCode) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : editAcademicYear  Starting");
		SectionForm classDetails = null;
		SectionDao secDao=new SectionDaoImpl();
		try {

			classDetails = secDao.editSection(classCode);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : editAcademicYear  Starting");
		return classDetails;
	}

	@Override
	public List<SectionVO> sectiondetailsdownload() throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionServiceImpl:getCampusClassSectionAndClassDetailsService Starting");

		// TODO Auto-generated method stub
		List<SectionVO> classSectionFormList = new ArrayList<SectionVO>();
		List<SectionPojo> classSectionPojoList = new ArrayList<SectionPojo>();
		classSectionPojoList = campusClassSectionDao.sectiondetailsdownload();
		try {
			for (SectionPojo pojoList : classSectionPojoList) {
				SectionVO sectionvo = new SectionVO();
				sectionvo.setSecDetailsId(pojoList.getSecDetailsId());
				sectionvo.setSecDetailsName(pojoList.getSecDetailsName());
				sectionvo.setStreamName(pojoList.getStreamName());
				sectionvo.setSectionId(pojoList.getSectionId());
				sectionvo.setSectionName(pojoList.getSectionName());
				sectionvo.setSectionStrength(pojoList.getSectionStrength());
				classSectionFormList.add(sectionvo);
			}
		} catch (Exception exception) {
			logger.error(exception);
		}
		//
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionServiceImpl:getCampusClassSectionAndClassDetailsService Ending");

		return classSectionFormList;
	}

	@Override
	public boolean sectionNameCheck(SectionForm sectionForm) {
		SectionDao secDao=new SectionDaoImpl();
		return secDao.sectionNameCheck(sectionForm);
	}

	public List<SectionForm> getstreamdetailsforOnchange(String locationid,String classname, String streamId) {
		return new SectionDaoImpl().getstreamdetailsforOnchange( locationid, classname,  streamId);
	}

}
