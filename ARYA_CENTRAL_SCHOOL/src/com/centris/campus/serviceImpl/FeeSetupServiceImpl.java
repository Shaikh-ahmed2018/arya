package com.centris.campus.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.FeeSetupDao;
import com.centris.campus.daoImpl.FeeSetupDaoImpl;
import com.centris.campus.forms.ConcessionForm;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.service.FeeSetupService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.FeeConcessionVO;

public class FeeSetupServiceImpl implements FeeSetupService {
	FeeSetupDao dao = new FeeSetupDaoImpl();
	private static final Logger logger = Logger
			.getLogger(FeeSetupServiceImpl.class);

	public List<ConcessionDetailsPojo> getconcessiondetails(
			ConcessionDetailsPojo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupServiceImpl: getconcessiondetails Starting");
		FeeSetupDao daoImpl = null;
		try {
			daoImpl = new FeeSetupDaoImpl();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupServiceImpl: getconcessiondetails Ending");
		return daoImpl.getconcessiondetails(vo);
	}

	public String insertConcesssionDetails(ConcessionForm detailsForm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupServiceImpl: insertConcesssionDetails Starting");
		FeeSetupDao daoImpl = new FeeSetupDaoImpl();

		System.out.println("ID" + detailsForm.getConcessionId());

		ConcessionDetailsPojo detailsPojo = new ConcessionDetailsPojo();
		System.out.println("Service");
		String check = "";

		if (detailsForm.getConcessionId().equalsIgnoreCase(""))

		{

			System.out.println("If loop working");
			try

			{

				System.out.println("Insert service IMPL");
				detailsPojo.setConcessionId(detailsForm.getConcessionId());

				detailsPojo.setConcessionName(detailsForm.getConcesionName());
				detailsPojo.setDescription(detailsForm.getDescription());
				detailsPojo.setCreateUser(detailsForm.getCreateUser());
				detailsPojo.setPercentage(detailsForm.getPercentage());
				check = dao.insertConcesssionDetails(detailsPojo);

			}

			catch (Exception exception)

			{
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		} else if (!detailsForm.getConcessionId().equalsIgnoreCase(""))

		{
			try

			{

				System.out.println("Update service IMPL");
				
				detailsPojo.setConcessionId(detailsForm.getConcessionId());
				detailsPojo.setConcessionName(detailsForm.getConcesionName());
				detailsPojo.setDescription(detailsForm.getDescription());
				detailsPojo.setCreateUser(detailsForm.getCreateUser());
				detailsPojo.setPercentage(detailsForm.getPercentage());
				check = dao.updateConcessionDao(detailsPojo);

			}

			catch (Exception exception)

			{
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);

		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupServiceImpl: insertConcesssionDetails Ending");
		return check;
	}

	public ConcessionForm EditConcesssionDetails(ConcessionForm detailsForm) {

		return new FeeSetupDaoImpl().EditConcesssionDetails(detailsForm);
	}

	@Override
	public String deleteconcession(FeeConcessionVO vo) {
		// TODO Auto-generated method stub
		return new FeeSetupDaoImpl().deleteconcession(vo);
	}

	@Override
	public boolean getnamecount(FeeConcessionVO vo) {
		// TODO Auto-generated method stub
		return new FeeSetupDaoImpl().getnamecount(vo);
	}

}
