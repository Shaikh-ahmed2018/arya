package com.centris.campus.serviceImpl;

import com.centris.campus.dao.NewRegistrationDao;
import com.centris.campus.daoImpl.NewRegistrationDaoImpl;
import com.centris.campus.forms.NewRegistrationForm;
import com.centris.campus.forms.NewUserRegistrationForm;
import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.service.NewRgistrationService;

public class NewRegistrationServiceImpl implements NewRgistrationService {
	
	NewRegistrationDao newregdao= new NewRegistrationDaoImpl();

	public void getregdetails(NewRegistrationForm newregform) {
		
		
		
		newregdao.getregdetails(newregform);
	}

	@Override
	public String InsertNewRegistrationUser(
			NewUserRegistrationForm registrationform) {
		// TODO Auto-generated method stub
		return newregdao.InsertNewRegistrationUser(registrationform);
	}

	@Override
	public String saveparentsubmittingdetailstoschool(
			ParentRequiresAppointmentForm registrationform) {
		// TODO Auto-generated method stub
		return newregdao.saveparentsubmittingdetailstoschool(registrationform);
	}
}
