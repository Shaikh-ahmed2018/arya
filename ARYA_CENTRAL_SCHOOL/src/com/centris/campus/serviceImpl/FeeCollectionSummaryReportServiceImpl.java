package com.centris.campus.serviceImpl;

import java.util.ArrayList;

import com.centris.campus.dao.FeeCollectionSummaryReportDao;
import com.centris.campus.daoImpl.FeeCollectionSummaryReportDaoImpl;
import com.centris.campus.forms.FeeCollectionSummaryReportForm;
import com.centris.campus.service.FeeCollectionSummaryReportService;
import com.centris.campus.vo.FeeReportDetailsVo;

public class FeeCollectionSummaryReportServiceImpl implements FeeCollectionSummaryReportService{
	
public ArrayList<FeeReportDetailsVo> getClassFeeSummaryReport(FeeCollectionSummaryReportForm feeReportForm){
		
		FeeCollectionSummaryReportDao feeDao=new FeeCollectionSummaryReportDaoImpl();
		
		return feeDao.getClassFeeSummaryReport(feeReportForm);
	}

}
