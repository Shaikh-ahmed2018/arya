package com.centris.campus.delegate;

import com.centris.campus.forms.NewRegistrationForm;
import com.centris.campus.forms.NewUserRegistrationForm;
import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.service.NewRgistrationService;
import com.centris.campus.serviceImpl.NewRegistrationServiceImpl;

public class NewRegistrationBD {

	NewRgistrationService newregserviceimpl = new NewRegistrationServiceImpl();
	
	public void getregDetails(NewRegistrationForm newregform) {
		
		newregserviceimpl.getregdetails(newregform);
		
	}


	public String InsertNewRegistrationUser(
			NewUserRegistrationForm registrationform) {
		// TODO Auto-generated method stub
		return newregserviceimpl.InsertNewRegistrationUser(registrationform);
	}


	public String saveparentsubmittingdetailstoschool(
			ParentRequiresAppointmentForm registrationform) {
		// TODO Auto-generated method stub
		return newregserviceimpl.saveparentsubmittingdetailstoschool(registrationform);
	}}
