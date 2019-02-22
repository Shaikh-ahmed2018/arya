package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.forms.QuotaDetailsForms;
import com.centris.campus.pojo.QuotaMasterPojo;
import com.centris.campus.vo.QuotaMasterVO;

public interface QuotaMasterService {

	ArrayList<QuotaMasterVO> getQuotaDetails();

	String insertQuotaDetails(QuotaDetailsForms qForm);

	String deleteQuotaDetails(QuotaMasterVO deletelist);

	QuotaMasterPojo editQuotaDetails(QuotaMasterPojo editlist);

	ArrayList<QuotaMasterVO> searchQuota(QuotaMasterVO searchvo);

	boolean validateQuotaName(QuotaDetailsForms quotaform);

	boolean validateQuotaNameUpdate(QuotaDetailsForms validateupdate);

}
