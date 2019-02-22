package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.FeeReportform;
import com.centris.campus.serviceImpl.FeeReportServiceImpl;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.feeReportVO;

public class FeeReportsBD {



	public ArrayList<feeReportVO> expandAll(feeReportVO obj) {
		return FeeReportServiceImpl.expandAll(obj);
		
	}

	public ArrayList<feeReportVO> getclasssectionDetails(feeReportVO obj) {
		return FeeReportServiceImpl.getclasssectionDetails(obj);
	}


	

}
