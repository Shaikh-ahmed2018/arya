package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.QuotaMasterDao;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.QuotaMasterDaoImpl;
import com.centris.campus.forms.QuotaDetailsForms;
import com.centris.campus.pojo.QuotaMasterPojo;
import com.centris.campus.service.QuotaMasterService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.QuotaMasterVO;

public class QuotaMasterServiceIMPL implements QuotaMasterService {

	private static final Logger logger = Logger
			.getLogger(QuotaMasterServiceIMPL.class);

	public ArrayList<QuotaMasterVO> getQuotaDetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterServiceIMPL:getQuotaDetails Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterServiceIMPL:getQuotaDetails Ending");
		QuotaMasterDao quotaMasterDao = new QuotaMasterDaoImpl();
		return quotaMasterDao.getQuotaDetails();
	}

	public String insertQuotaDetails(QuotaDetailsForms qForm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterServiceIMPL:insertQuotaDetailsService Starting");
		QuotaMasterPojo pojo = null;
		try {

			if (qForm.getQuotacode() == "" || qForm.getQuotacode() == null) {

				pojo = new QuotaMasterPojo();
				pojo.setQuotaName(qForm.getQuotaname());
				pojo.setQuotaDescription(qForm.getQuotadescription());
				pojo.setCreated_by(qForm.getCreated_by());

				pojo.setQuotaid(IDGenerator.getPrimaryKeyID("campus_quota"));
				return new QuotaMasterDaoImpl().addQuotaDetails(pojo);

			} else {

				pojo = new QuotaMasterPojo();
				pojo.setQuotaid(qForm.getQuotacode());
				pojo.setQuotaName(qForm.getQuotaname());
				pojo.setQuotaDescription(qForm.getQuotadescription());
				pojo.setUpdated_by(qForm.getUpdated_by());
				return new QuotaMasterDaoImpl().updateQuotaDetails(pojo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterServiceIMPL:insertQuotaDetailsService Ending");

		return null;

	}

	public String deleteQuotaDetails(QuotaMasterVO deletelist) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterServiceIMPL:deleteQuotaDetailsService Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterServiceIMPL:deleteQuotaDetailsService Ending");

		QuotaMasterDao quotaMasterDao = new QuotaMasterDaoImpl();
		return quotaMasterDao.deleteQuotaDetails(deletelist);

	}

	public QuotaMasterPojo editQuotaDetails(QuotaMasterPojo editlist) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterServiceIMPL:editQuotaDetailsService Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterServiceIMPL:editQuotaDetailsService Ending");

		QuotaMasterDao quotaMasterDao = new QuotaMasterDaoImpl();
		return quotaMasterDao.editQuotaDetails(editlist);

	}

	public ArrayList<QuotaMasterVO> searchQuota(QuotaMasterVO searchvo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterServiceIMPL:searchQuotaService Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterServiceIMPL:searchQuotaService Ending");

		QuotaMasterDao quotaMasterDao = new QuotaMasterDaoImpl();
		return quotaMasterDao.searchQuota(searchvo);
	}

	public boolean validateQuotaName(QuotaDetailsForms quotaform) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterServiceIMPL:validateQuotaNameService Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterServiceIMPL:validateQuotaNameService Ending");
		QuotaMasterDao quotaMasterDao = new QuotaMasterDaoImpl();
		return quotaMasterDao.validateQuotaName(quotaform);
	}

	public boolean validateQuotaNameUpdate(QuotaDetailsForms validateupdate) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterServiceIMPL:validateQuotaNameUpdateService Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterServiceIMPL:validateQuotaNameUpdateService Ending");
		QuotaMasterDao quotaMasterDao = new QuotaMasterDaoImpl();
		return quotaMasterDao.validateQuotaNameUpdate(validateupdate);
	}

}
