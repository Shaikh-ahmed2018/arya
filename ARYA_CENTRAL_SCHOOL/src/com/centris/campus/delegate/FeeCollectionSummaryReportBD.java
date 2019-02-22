package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.forms.FeeCollectionSummaryReportForm;
import com.centris.campus.service.FeeCollectionSummaryReportService;
import com.centris.campus.serviceImpl.FeeCollectionSummaryReportServiceImpl;
import com.centris.campus.vo.FeeReportDetailsVo;

public class FeeCollectionSummaryReportBD {
	
	public ArrayList<FeeReportDetailsVo> getClassFeeSummaryReport(FeeCollectionSummaryReportForm feeReportForm){
		
		FeeCollectionSummaryReportService feeService=new FeeCollectionSummaryReportServiceImpl();
		
		return feeService.getClassFeeSummaryReport(feeReportForm);
	}

}
