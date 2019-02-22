package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.OnlineFeeReceiptDao;
import com.centris.campus.daoImpl.OnlineFeeReceiptDaoImpl;
import com.centris.campus.service.OnlineFeeReceiptService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.OnlineFeeReceiptVo;
import com.centris.campus.vo.ParentVO;

public class OnlineFeeReceiptServiceImpl implements OnlineFeeReceiptService {

	
	private static final Logger logger = Logger.getLogger(OnlineFeeReceiptServiceImpl.class);
	
	
	
	OnlineFeeReceiptDao dao = new OnlineFeeReceiptDaoImpl();
	
	
	
	public List<ParentVO> getParentChildService(String sectionid, String huserid) {
		
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeeReceiptServiceImpl : getParentChildService Starting");
		
       List<ParentVO> stulist = null;
		
		try {
			
			stulist = dao.getParentChildDao(sectionid,huserid);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeeReceiptServiceImpl : getParentChildService Ending");
		
		
		return stulist;
	}



	
	public ArrayList<OnlineFeeReceiptVo> getSearchStudentFeeReceiptService(
			OnlineFeeReceiptVo feevo) {
		
		
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeeReceiptServiceImpl : getSearchStudentFeeReceiptService Starting");
		
		
		ArrayList<OnlineFeeReceiptVo> feelist = null;
		
		try {
			
			
			
			feelist = dao.getSearchStudentFeeReceiptDao(feevo);
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeeReceiptServiceImpl : getSearchStudentFeeReceiptService Ending");
		
		return feelist;
	}

}
