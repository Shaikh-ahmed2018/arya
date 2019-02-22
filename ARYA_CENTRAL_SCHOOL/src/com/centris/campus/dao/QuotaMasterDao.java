package com.centris.campus.dao;

import java.util.ArrayList;
import com.centris.campus.forms.QuotaDetailsForms;
import com.centris.campus.pojo.QuotaMasterPojo;
import com.centris.campus.vo.QuotaMasterVO;

public interface QuotaMasterDao {

	ArrayList<QuotaMasterVO> getQuotaDetails();

	String addQuotaDetails(QuotaMasterPojo pojo);

	String deleteQuotaDetails(QuotaMasterVO deletelist);

	QuotaMasterPojo editQuotaDetails(QuotaMasterPojo editlist);

	ArrayList<QuotaMasterVO> searchQuota(QuotaMasterVO searchvo);

	boolean validateQuotaName(QuotaDetailsForms quotaform);

	boolean validateQuotaNameUpdate(QuotaDetailsForms validateupdate);

}
