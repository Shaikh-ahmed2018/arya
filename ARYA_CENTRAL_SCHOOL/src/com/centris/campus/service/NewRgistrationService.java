package com.centris.campus.service;

import com.centris.campus.forms.NewRegistrationForm;
import com.centris.campus.forms.NewUserRegistrationForm;
import com.centris.campus.forms.ParentRequiresAppointmentForm;

public interface NewRgistrationService {
	
	public void getregdetails(NewRegistrationForm newregform);

	public String InsertNewRegistrationUser(NewUserRegistrationForm registrationform);

	public String saveparentsubmittingdetailstoschool(
			ParentRequiresAppointmentForm registrationform);
}
