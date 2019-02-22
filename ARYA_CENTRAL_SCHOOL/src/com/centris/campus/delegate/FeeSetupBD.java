package com.centris.campus.delegate;


import java.util.List;

import com.centris.campus.forms.ConcessionForm;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.service.FeeSetupService;
import com.centris.campus.serviceImpl.AddDesignationServiceImpl;
import com.centris.campus.serviceImpl.FeeSetupServiceImpl;
import com.centris.campus.vo.AddDesignationVO;
import com.centris.campus.vo.FeeConcessionVO;

public class FeeSetupBD {
	
	FeeSetupService service = new FeeSetupServiceImpl();

	
	
	public List<ConcessionDetailsPojo> getconcessiondetails(ConcessionDetailsPojo vo) {
		FeeSetupService detailsService = new FeeSetupServiceImpl();
		return detailsService.getconcessiondetails(vo);
	}

	public String insertConcesssionDetails(ConcessionForm detailsForm) {
		// TODO Auto-generated method stub
		System.out.println("Delegate insert concession");
		FeeSetupService detailsService = new FeeSetupServiceImpl();
		return detailsService.insertConcesssionDetails(detailsForm);

	}
	public ConcessionForm EditConcesssionDetails(ConcessionForm detailsForm) {
		// TODO Auto-generated method stub
		FeeSetupService detailsService = new FeeSetupServiceImpl();
		return detailsService.EditConcesssionDetails(detailsForm);
	}
	
	public String deleteconcession(FeeConcessionVO vo) 
	{
		System.out.println("Delete concession delegate is Working");

		FeeSetupService detailsService = new FeeSetupServiceImpl();
		return detailsService.deleteconcession(vo);
	}
	public boolean getnamecount(FeeConcessionVO vo) {
		
		FeeSetupService detailsService = new FeeSetupServiceImpl();
		return detailsService.getnamecount(vo);
	}
}
