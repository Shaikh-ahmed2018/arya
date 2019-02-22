package com.centris.campus.serviceImpl;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.CareersViewDao;
import com.centris.campus.daoImpl.CareersViewDaoImpl;
import com.centris.campus.pojo.CareersViewPojo;
import com.centris.campus.service.CareersViewService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.CareerUpdatePopuVO;
import com.centris.campus.vo.CareersViewVo;

public class CareersViewServiceImpl implements CareersViewService {
	private static final Logger logger = Logger
			.getLogger(CareersViewServiceImpl.class);
	CareersViewDao careersview;
	List<CareersViewVo> getCareers;
	List<CareerUpdatePopuVO> listcareview;

	String result;

	@Override
	public List<CareersViewVo> getCareersView() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewServiceImpl:getCareersView Starting");
		try {
			careersview = new CareersViewDaoImpl();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		getCareers = careersview.getCareersViewDao();
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewServiceImpl:getCareersView Ending");
		return getCareers;
	}

	@Override
	public CareersViewVo getValues(CareersViewPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewServiceImpl:getValues Starting");
		CareersViewVo listcareview = null;
		try {
			careersview = new CareersViewDaoImpl();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		listcareview = careersview.getValues(pojo);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewServiceImpl:getValues Ending");
		return listcareview;
	}

	@Override
	public boolean getDelete(CareersViewPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewServiceImpl:getDelete Starting");

		boolean status = false;
		try {
			careersview = new CareersViewDaoImpl();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		status = careersview.getDelete(pojo);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewServiceImpl:getDelete Ending");
		return status;
	}

	public List<CareersViewVo> getAllCareersView() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewServiceImpl:getAllCareersView Starting");
		try {
			careersview = new CareersViewDaoImpl();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		getCareers = careersview.getAllCareersView();
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewServiceImpl:getAllCareersView Ending");
		return getCareers;
	}

	public String addJobs(CareersViewPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewServiceImpl:addJobs Starting");
		String status = null;
		try {
			careersview = new CareersViewDaoImpl();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		status = careersview.addJobs(pojo);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewServiceImpl:addJobs Ending");
		return status;
	}

	public boolean checkTitle(CareersViewPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewServiceImpl:checkTitle Starting");

		boolean status = false;
		try {
			careersview = new CareersViewDaoImpl();
			status = careersview.checkTitle(pojo);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewServiceImpl:checkTitle Ending");
		return status;
	}

	public List<CareersViewVo> searchDetails(String name) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewServiceImpl:searchDetails Starting");

		List<CareersViewVo> list = null;
		try {
			careersview = new CareersViewDaoImpl();
			list = careersview.searchDetails(name);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewServiceImpl:searchDetails Ending");

		return list;
	}
}
