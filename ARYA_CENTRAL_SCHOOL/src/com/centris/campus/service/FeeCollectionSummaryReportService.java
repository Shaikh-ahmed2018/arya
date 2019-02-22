package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.forms.FeeCollectionSummaryReportForm;
import com.centris.campus.vo.FeeReportDetailsVo;

public interface FeeCollectionSummaryReportService {
	
	public ArrayList<FeeReportDetailsVo> getClassFeeSummaryReport(FeeCollectionSummaryReportForm feeReportForm);

}
