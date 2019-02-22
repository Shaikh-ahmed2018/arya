package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.forms.FeeCollectionSummaryReportForm;
import com.centris.campus.vo.FeeReportDetailsVo;

public interface FeeCollectionSummaryReportDao {
	
	public ArrayList<FeeReportDetailsVo> getClassFeeSummaryReport(FeeCollectionSummaryReportForm feeReportForm);

}
