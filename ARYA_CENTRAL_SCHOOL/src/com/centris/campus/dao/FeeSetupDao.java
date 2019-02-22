package com.centris.campus.dao;

import java.util.List;

import com.centris.campus.forms.ConcessionForm;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.vo.FeeConcessionVO;

public interface FeeSetupDao {
	
	public List<ConcessionDetailsPojo> getconcessiondetails(ConcessionDetailsPojo vo);
	public String insertConcesssionDetails(ConcessionDetailsPojo detailsPojo);
	public ConcessionForm EditConcesssionDetails(ConcessionForm detailsForm);
	public String deleteconcession(FeeConcessionVO vo) ;
	public boolean getnamecount(FeeConcessionVO vo);
	public String updateConcessionDao(ConcessionDetailsPojo detailsPojo);



}
