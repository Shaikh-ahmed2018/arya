package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.forms.QuotaDetailsForms;
import com.centris.campus.pojo.QuotaMasterPojo;
import com.centris.campus.service.QuotaMasterService;
import com.centris.campus.serviceImpl.QuotaMasterServiceIMPL;
import com.centris.campus.vo.QuotaMasterVO;



public class QuotaMasterBD {
	
	public ArrayList<QuotaMasterVO> getQuotaDetails() {
		
		QuotaMasterService quotaMasterService = new QuotaMasterServiceIMPL();
		return quotaMasterService.getQuotaDetails();
	}

	public String insertQuotaDetails(QuotaDetailsForms qForm) {
		QuotaMasterService quotaMasterService = new QuotaMasterServiceIMPL();
		return quotaMasterService.insertQuotaDetails(qForm);
	}

	

	public String deleteQuotaDetails(QuotaMasterVO deletelist) {
		QuotaMasterService quotaMasterService = new QuotaMasterServiceIMPL();
		return quotaMasterService.deleteQuotaDetails(deletelist);
	}
	

	public QuotaMasterPojo editQuotaDetails(QuotaMasterPojo editlist) {
		QuotaMasterService quotaMasterService = new QuotaMasterServiceIMPL();
		return quotaMasterService.editQuotaDetails(editlist);
	}

	public ArrayList<QuotaMasterVO> searchQuota(QuotaMasterVO searchvo) {
		QuotaMasterService quotaMasterService = new QuotaMasterServiceIMPL();
		return quotaMasterService.searchQuota(searchvo);

	}

	public boolean validateQuotaName(QuotaDetailsForms quotaform) {
		QuotaMasterService quotaMasterService = new QuotaMasterServiceIMPL();
		return quotaMasterService.validateQuotaName(quotaform);
	}

	public boolean validateQuotaNameUpdate(QuotaDetailsForms validateupdate) {
		QuotaMasterService quotaMasterService = new QuotaMasterServiceIMPL();
		return quotaMasterService.validateQuotaNameUpdate(validateupdate);
	}


}
