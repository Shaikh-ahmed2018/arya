package com.centris.campus.service;

import java.util.List;

import com.centris.campus.forms.ConcessionForm;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.vo.FeeConcessionVO;

public interface FeeSetupService {
	
	public List<ConcessionDetailsPojo> getconcessiondetails(ConcessionDetailsPojo vo);
	
	public String insertConcesssionDetails(ConcessionForm detailsForm);

	public ConcessionForm EditConcesssionDetails(ConcessionForm detailsForm);

	public String deleteconcession(FeeConcessionVO vo);

	public boolean getnamecount(FeeConcessionVO vo); 

}
