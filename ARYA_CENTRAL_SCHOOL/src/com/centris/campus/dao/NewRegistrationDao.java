package com.centris.campus.dao;

import com.centris.campus.forms.NewRegistrationForm;
import com.centris.campus.forms.NewUserRegistrationForm;
import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.vo.NewRegistrationVO2;

public interface NewRegistrationDao {

	void getregdetails(NewRegistrationForm newregform);

	String InsertNewRegistrationUser(NewUserRegistrationForm registrationform);

	String saveparentsubmittingdetailstoschool(
			ParentRequiresAppointmentForm registrationform);
	

}
